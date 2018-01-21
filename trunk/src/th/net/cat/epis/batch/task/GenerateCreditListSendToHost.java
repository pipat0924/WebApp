package th.net.cat.epis.batch.task;

import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.rightPad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;

@Component("generateCreditListSendToHost")
public class GenerateCreditListSendToHost implements Tasklet {

	private static final Logger logger = Logger.getLogger(GenerateCreditListSendToHost.class.getName());
	@Value("${epis.creditlimit.export.output.path}") String exportOutputPath;
	@Value("${epis.task.active}") String taskActive;
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@Autowired PaymentService paymentService;
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) {
		
		Connection transaction = null;
		try{
		transaction = episJdbcTemplate.getDataSource().getConnection();
		transaction.setAutoCommit(false);
		
			final Map<String, String> receiptNoMap = new HashMap<String, String>();
			final List<String> payemntAndReceiptList = new ArrayList<String>();
			episJdbcTemplate.query(
					"SELECT rownum " +
							", cre.CONTRACT  " +
							", cre.AR_REF " +
							", cre.PAY_TYPE " +
							", cre.AMOUNT_INC_VAT " +
							", cre.PAY_DATE " +
							", cre.MSISDN " +
//							", receipt.RECEIPTNO as RECEIPTNO " +
							", cre.RECEIPT_ID as RECEIPTNO " +
							", cre.VAT_AMOUNT " +
							", cre.AMOUNT_EX_VAT " +
							", cre.POST_DATE " +
							", cre.ACCOUNT_NO " +
							", cre.AR_INVDATE " +
							", cre.AR_DUEDATE " +
							",cre.STATUS " +
							",cre.UPDATED_TIME " +
							", cre.RECEIPT_ID as RECEIPT_ID " +
							" FROM CREDIT_LIMIT_TRANS cre "+ //"left join CORRECEIPT receipt " +
//							" on cre.RECEIPT_ID = receipt.RECEIPTID "+
							" where cre.STATUS='N'  ", new RowCountCallbackHandler() {
						//		+ " AND inv.attributes !='A' "
						//		+ "  AND rcp.updatesystem = 'CLT' ", new RowCountCallbackHandler(){
						
						@Override
						public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
							StringBuilder stringBuilder = new StringBuilder();
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("CONTRACT")), 10, " "));
//							String arRef = StringUtils.trimToEmpty(resultSet.getString("AR_REF"));
//							arRef = arRef.replace("Advance Payment", "0");
//							stringBuilder.append(AppUtil.toRightPaddingString(arRef, 9, ' '));
							stringBuilder.append(AppUtil.toRightPaddingString(StringUtils.trimToEmpty(resultSet.getString("AR_REF")).replace("Advance Payment", "0"), 9, ' '));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("PAY_TYPE")), 1, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AMOUNT_INC_VAT")), 13, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("PAY_DATE")), 8, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("MSISDN")), 20, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("RECEIPTNO")), 22, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("VAT_AMOUNT")), 13, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AMOUNT_EX_VAT")), 13, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("POST_DATE")), 19, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("ACCOUNT_NO")), 10, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AR_INVDATE")), 8, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AR_DUEDATE")), 8, " "));
							String key = resultSet.getString("CONTRACT") + "|" + "" + resultSet.getString("AR_REF") + "|" + resultSet.getString("RECEIPT_ID");
							receiptNoMap.put(key, key);
							payemntAndReceiptList.add(stringBuilder.toString());
						}
					});
			String creditLimitFileName = null;
			 
			
				if (payemntAndReceiptList.size() > 0) {
						creditLimitFileName = generateFileAndSendToHost(payemntAndReceiptList);
						Set set = receiptNoMap.entrySet();
						Iterator iterator = set.iterator();
						List<Object[]> parameters = new ArrayList<Object[]>();
						while (iterator.hasNext()) { //update -> batchupdate
							Map.Entry mentry = (Map.Entry) iterator.next();
							String key = (String) mentry.getKey();
							String[] key_array = key.split("\\|");
							String contract = key_array[0];
							String arRef = key_array[1];
							String receipId = key_array[2];
							parameters.add(new Object[] {contract, arRef, receipId });
							
							
						}
						episJdbcTemplate.batchUpdate("UPDATE CREDIT_LIMIT_TRANS SET STATUS = 'Y' WHERE CONTRACT = ? and AR_REF = ? and RECEIPT_ID = ? ", parameters);
						if (creditLimitFileName != null) {
							ftpCreditLimitToHost(creditLimitFileName);
						}
					
				}
				//logger.info("start commit");
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try{
					
				transaction.rollback();
				}catch(Exception e1){
					e.printStackTrace();
				}
				
			} finally {
				try{
					transaction.close();
				}catch(Exception e){}
			}
		return RepeatStatus.FINISHED;
	}
	/*public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		final Map<String, String> receiptNoMap = new HashMap<String, String>();
		final List<String> payemntAndReceiptList = new ArrayList<String>();	
		episJdbcTemplate.query(
				" SELECT rownum"
					+ ", cus.accountno AS billingNo "
					+ ", inv.invoiceno AS invoiceNo "
					+ ", inv.attributes AS attributes "
					+ ", (inv.charge * 100) AS charge "
					+ ", to_char(rcp.receiptdttm, 'YYYYMMDD') AS recepitDttm "
					+ ", cre.serviceno AS serviceNo "
					+ ", cre.creditmode AS creditMode "
					+ ", rcp.receiptno AS receiptNo "
					+ ", (inv.vat * 100) AS vat "
					+ ", (inv.totalcharge * 100) AS totalCharge "
					+ ", to_char(rcp.updatedttm, 'YYYYMMDD HH24:MI:SS') AS updateDttm "
					+ ", cus.accountno AS accountNo "
					+ ", to_char(inv.issuedate, 'YYYYMMDD') AS issueDate "
					+ ", to_char(inv.duedate, 'YYYYMMDD') AS dueDate "
				+ " FROM CORRECEIPT rcp "
				+ " INNER JOIN CORPAYMENT pay ON pay.paymentid = rcp.paymentid "	
				+ " LEFT JOIN TMPINVOICE inv ON inv.receiptid = rcp.receiptid "
				+ " LEFT JOIN TMPACCOUNT cus ON cus.accountid = inv.accountid "
				+ " LEFT JOIN MASCREDITLIMITS cre ON cre.invoiceid = inv.invoiceid "
				//+ " WHERE pay.paymenttype = '"+ AppConstants.PAYMENT_TYPE_SERVICE_CHARGE +"' "
						+ " WHERE pay.paymenttype in('"+ AppConstants.PAYMENT_TYPE_SERVICE_CHARGE +"', '"+ AppConstants.PAYMENT_TYPE_WRITEOFF_SERVICE_CHARGE+"')"
				//+ "  AND cus.billgroup "+ paymentService.findBillingGroupByCategory()
						+ "  AND cus.billgroup ='T2 External  Int''l Fixed line TOT' "

				+ "  AND rcp.updatesystem IS NULL ", new RowCountCallbackHandler(){
				//		+ " AND inv.attributes !='A' "
				//		+ "  AND rcp.updatesystem = 'CLT' ", new RowCountCallbackHandler(){
			@Override
			public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("billingNo")), 10, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("invoiceNo")), 9, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(
					"M".equals(resultSet.getString("creditMode")) ? "M": resultSet.getString("attributes")), 1, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("charge")), 13, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("recepitDttm")), 8, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("serviceNo")), 20, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("receiptNo")), 20, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("vat")), 13, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("totalCharge")), 13, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("updateDttm")), 19, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("accountNo")), 10, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("issueDate")), 8, " "));
				stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("dueDate")), 8, " "));
				
				receiptNoMap.put(resultSet.getString("receiptNo"), resultSet.getString("receiptNo"));
				payemntAndReceiptList.add(stringBuilder.toString());
			}
		});
		String creditLimitFileName = null;
		try {
			if(payemntAndReceiptList.size() > 0) { 
				try {
					creditLimitFileName = generateFileAndSendToHost(payemntAndReceiptList);
				} catch(Exception e) {
			    	 e.printStackTrace();
			    }
			}
		} catch(Exception e) {
			throw new Exception();
		} finally {
			 Set set = receiptNoMap.entrySet();
		     Iterator iterator = set.iterator();
		     while(iterator.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         episJdbcTemplate.update("UPDATE CORRECEIPT SET updatesystem = 'CLT' WHERE receiptno = ?", mentry.getKey());
		     }
			creditLimitFileName = null;
		     if(creditLimitFileName != null) { 
				try {
					ftpCreditLimitToHost(creditLimitFileName);
				} catch(Exception e) {
			    	 e.printStackTrace();
			    }
			}
		}

		return RepeatStatus.FINISHED;
	}*/

	public String generateFileAndSendToHost(List<String> payemntAndReceiptList) throws IOException {
		
		final String fileName = getFileFormatter();
			// auto create directory when not exists.
		//File folderOnline = new File(EXPORT_OUTPUT_PATH_TXT);
		File folderOnline = new File(exportOutputPath);
		if(!folderOnline.exists()) folderOnline.mkdir();
		File file = new File(exportOutputPath.concat(fileName));
		//File file = new File(EXPORT_OUTPUT_PATH_TXT.concat(fileName));
        	// creates the file.
        file.createNewFile();
        	// creates a FileWriter Object.
        FileWriter writer = new FileWriter(file); 
        	// writes the content to the file.
        for(String recepitData : payemntAndReceiptList) {
        	writer.write(recepitData); 
	        writer.write(System.getProperty( "line.separator" ));
        }
        
        writer.flush();
        writer.close();

		return fileName;
	}
	
	@Value("${epis.creditlimit.server}") String server;
	@Value("${epis.creditlimit.port}") int port;
	@Value("${epis.creditlimit.login.username}") String username;
	@Value("${epis.creditlimit.login.password}") String password;
	@Value("${epis.creditlimit.remote.directory}") String directory;

	public void ftpCreditLimitToHost(String creditLimitFileName) throws Exception {
		FTPClient ftpClient = new FTPClient();
		try {
			logger.info("Creating FTP connection.");
			logger.info("server = "+ server);
			logger.info("port = "+ port);
			logger.info("username = "+ username);
			logger.info("password = "+ password);
			logger.info("directory = "+ directory);
			logger.info("fileName = "+ creditLimitFileName);
//			ftpClient.connect(StringUtils.trim(server), port);
			ftpClient.connect(StringUtils.trim(server));
			ftpClient.login(StringUtils.trim(username), StringUtils.trim(password));
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			logger.info("Creating FTP connection created.");
			// APPROACH #1: uploads first file using an InputStream
			//File localFile = new File(EXPORT_OUTPUT_PATH_TXT.concat(creditLimitFileName));
			File localFile = new File(exportOutputPath.concat(creditLimitFileName));
			logger.info("localFile = "+ localFile.getAbsolutePath());
			String remoteFile = StringUtils.trim(directory).concat(creditLimitFileName);
			logger.info("remoteFile = "+ remoteFile);
			InputStream inputStream = new FileInputStream(localFile);
			logger.info("Start uploading file");
			logger.info("inputStream = "+ inputStream);
			boolean done = ftpClient.storeFile(remoteFile, inputStream);
			inputStream.close();
			if(done) {
				logger.info("The file is uploaded successfully.");
			}
			if(ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFileFormatter() {
		return new StringBuilder("PAY_EPIS").append("_").append(FastDateFormat.getInstance("yyyyMMdd", ENGLISH).format(new Date())).append("_").append(FastDateFormat.getInstance("hhmmss", ENGLISH).format(new Date())).append(AppConstants.REPORT_TYPE_TXT).toString();
	}
}
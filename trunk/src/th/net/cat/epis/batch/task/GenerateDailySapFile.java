package th.net.cat.epis.batch.task;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;

@Component("generateDailySapFile")
public class GenerateDailySapFile implements Tasklet {
	
	private static Logger logger = Logger.getLogger(GenerateDailySapFile.class);
	@Value("${epis.idoc.export.output.path}") String exportOutputPath;
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@Value("${epis.task.active}") String taskActive;
	
	int headerRowCount;
	
	String receiptUpdatedDateStr;
	String receiptDateStr;
	String currencyStr;
	String currencyRateStr;
	String branchNameStr;
	String branchCodeStr;
	String totalChargeStr;
	String chargeStr13;
	String chargeStr15;
//	String totalAmtStr;
	String vatStr;
	String wtStr;
	String transAmtStr;
	String taxBaseAmtStr13;
	String taxBaseAmtStr15;
	String branchAreaStr;
	String productCodeStr12;
	String productCodeStr18;
	String methodCodeStr;
	String chequeNoStr;
	String regionSapStr;
	String tradingPartStr;
	String canceledByStr;
	String receiveAmtStr;
	String billingGroupStr;
	String baNoStr;
	String invNoStr;
	String text;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		taskActive = "1";
		if("1".equals(taskActive)) {
			try{
				Date date = new Date();
				generateIdocFiles(date);
//				generateBillingIdoc(date);
//				generateReversePaymentIdoc(date);
			}catch(Exception e){
				logger.error(e);
				throw e;
			}
		}
		return RepeatStatus.FINISHED;
	}
/*	
	private void generateBillingIdoc(Date date) throws Exception {//date = DateUtil.convertToDate("2017-06-30", DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		StringBuffer strBuff = new StringBuffer();
    	strBuff.setLength(0);
//    	Date date = DateUtil.convertToDate(dateParam, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);	//new Date();
    	String dateStr = DateUtil.convertToString(date, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
    	headerRowCount = 1;
    	
		episJdbcTemplate.query(
			" select "
			+"	r.UPDATEDTTM  "
			+"	,r.RECEIPTDTTM "
			+"	,inv_sum_ibacss.CURR , r.CURRENCYCODE, curr.MESSAGE "
			+"	,p.CURRENCYRATE "
			+"	,r.BRANCHNAME "
			+"	,r.BRANCHCODE "
			+"	,i.TOTALCHARGE "
			+"  ,i.CHARGE "
			+"	,inv_sum_ibacss.TOTAL_AMOUNT "
			+"	,i.VAT "
			+"	,d.WT, i.DEDUCTION "
			+"	,r.BRANCHAREA "
			+"	,inv_sum_ibacss.PRODUCT_CODE, tmp_inv_svc.PRODUCTCODE "
			+"	,m.CODE "
			+" ,cheque.CHEQUENO, cheque.PUBLISHER, cheque.BRANCH "
//			+" --,money_transf.MONEYTRANSFERNO, money_transf.PUBLISHERNAME, money_transf.BRANCH "
			+"	,inv_sum_ibacss.REGION_SAP "
//			+"	--,PM:yyyymmdd(วันที่หักล้าง(posting date)) "
			+"	,inv_sum_ibacss.TRADING_PART "
				
			+" from "
			+" shop_closing shop_close "
//			+" --inner join tmpinvoiceservice tmp_inv_svc on shop_close.SHOP_CLOSING_ID = tmp_inv_svc.SHOP_CLOSING_ID "
			+" inner join correceipt r on shop_close.SHOP_CLOSING_ID = r.SHOP_CLOSING_ID "
			+" inner join corpayment p on p.PAYMENTID = r.PAYMENTID "
			+" inner join tmpinvoice i on p.PAYMENTID = i.PAYMENTID and r.RECEIPTID = i.RECEIPTID "
			+" inner join tmpinvoiceservice tmp_inv_svc on tmp_inv_svc.RECEIPTID = r.RECEIPTID and tmp_inv_svc.INVOICEID = i.INVOICEID "
			+" left join inv_summary_sap_ibacss inv_sum_ibacss on (''||inv_sum_ibacss.BI_REF) = i.INVOICENO "
			+" left join (select PAYMENTID, sum(AMOUNT) as WT from trsdeduction where DEDUCTIONTYPE in ('3TREDECIM','69BIS','69TRE') group by PAYMENTID) d on p.PAYMENTID = d.PAYMENTID "
			+" left join trsmethod m on m.PAYMENTID = p.PAYMENTID "
			+" left join trschequeref cheque on cheque.PAYMENTID = p.PAYMENTID "
//			+" --left join trsmoneytransferref money_transf on money_transf.PAYMENTID = p.PAYMENTID "
			+" left join maschangerate curr on curr.CURRENCYCODE = i.CURRENCYCODE "
			+" where "
			+" trunc(shop_close.CREATEDTTM) =  to_date('"+dateStr+"', 'YYYY-MM-DD') "
			+" and (r.ATTRIBUTES not LIKE '%R%' or (trunc(p.PAYMENTDATE) = trunc(r.CANCELDTTM))) "
			
			, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					
			    	receiptUpdatedDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("UPDATEDTTM"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	receiptDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("RECEIPTDTTM"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	currencyStr = AppUtil.toLeftPaddingString(row.getString("MESSAGE"), 5, ' ');
			    	currencyRateStr = AppUtil.toLeftPaddingString((row.getBigDecimal("CURRENCYRATE") != null) ? row.getBigDecimal("CURRENCYRATE").toString() : "", 11, ' ');
			    	branchNameStr = AppUtil.toLeftPaddingString(row.getString("BRANCHNAME"), 16, ' ');
			    	branchNameStr = branchNameStr.replace("ศูนย์บริการลูกค้า", "ศบล. ");
			    	if(branchNameStr.length() > 16){
			    		branchNameStr.substring(0,16);
			    	}
			    	branchCodeStr = AppUtil.toLeftPaddingString(row.getString("BRANCHCODE"), 4, ' ');
			    	if(branchCodeStr.length() > 4) {
			    		branchCodeStr = branchCodeStr.substring(1, 5);
			    	}
			    	BigDecimal totalCharge = (row.getBigDecimal("TOTALCHARGE") != null) ? row.getBigDecimal("TOTALCHARGE") : BigDecimal.ZERO;
			    	BigDecimal vat = (row.getBigDecimal("VAT") != null) ? row.getBigDecimal("VAT") : BigDecimal.ZERO;
			    	BigDecimal wt = (row.getBigDecimal("WT") != null) ? row.getBigDecimal("WT") : BigDecimal.ZERO;
			    	BigDecimal transAmt = totalCharge.subtract(wt);
			    	BigDecimal taxBaseAmt = totalCharge.subtract(vat);
			    	
			    	totalChargeStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(totalCharge, "0.00") , 13, ' ');
			    	chargeStr13 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(row.getBigDecimal("CHARGE"), "0.00") , 13, ' ');
			    	chargeStr15 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(row.getBigDecimal("CHARGE"), "0.00") , 15, ' ');
//			    	totalAmtStr = toLeftPaddingString( toNumberFormat(row.getBigDecimal("TOTAL_AMOUNT"), "0.00") , 13, ' ');
			    	vatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(vat, "0.00") , 13, ' ');
			    	wtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(wt, "0.00") , 13, ' ');
			    	transAmtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(transAmt, "0.00") , 13, ' ');
			    	taxBaseAmtStr13 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(taxBaseAmt, "0.00") , 13, ' ');
			    	taxBaseAmtStr15 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(taxBaseAmt, "0.00") , 15, ' ');
			    	branchAreaStr = AppUtil.toLeftPaddingString(row.getString("BRANCHAREA"), 4, ' ');
			    	productCodeStr12 = AppUtil.toLeftPaddingString(row.getString("PRODUCT_CODE"), 12, ' ');
			    	productCodeStr18 = AppUtil.toLeftPaddingString(row.getString("PRODUCT_CODE"), 18, '0');
//			    	methodCodeStr = row.getInt("CODE");
			    	chequeNoStr = AppUtil.toLeftPaddingString(row.getString("CHEQUENO"), 18, ' ');
			    	regionSapStr = AppUtil.toLeftPaddingString(row.getString("REGION_SAP"), 12, ' ');
			    	tradingPartStr = AppUtil.toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	
					// Header Loop
					for (int i = 1; i <= 2; i++) {
						
						// Detail Loop
						if(headerRowCount % 2 > 0){	//Header row 1
							for (int detailRowCount = 1; detailRowCount <= 3; detailRowCount++) {
								// Header
								strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( receiptDateStr );//B
								strBuff.append("RV");//C							
								strBuff.append("1000");//D
								strBuff.append( receiptDateStr ); //E
								strBuff.append( receiptDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( receiptUpdatedDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( AppUtil.toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( receiptDateStr.substring(0,4) ); //N
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":(detailRowCount==3)?"S":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":(detailRowCount==3)?"40":"  " );//T
								strBuff.append( "S" );//U
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("11010102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("11149102", 10, '0') );//V
								}else if(detailRowCount == 3){
									strBuff.append( AppUtil.toRightPaddingString("11903102", 10, '0') );//V
								}
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
								if(detailRowCount == 1){
									strBuff.append( transAmtStr );//X
									strBuff.append( transAmtStr );//Y
								}else if(detailRowCount == 2){	
									strBuff.append( totalChargeStr );//X
									strBuff.append( totalChargeStr );//Y
								}else if(detailRowCount == 3){
									strBuff.append( wtStr );//X
									strBuff.append( wtStr );//Y
								}
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//Z
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AA
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AB
								if(detailRowCount == 1 || detailRowCount == 2){
									strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AC
								}else if(detailRowCount == 3){
									strBuff.append( branchCodeStr );//AC
								}
								strBuff.append( (detailRowCount == 1) ? branchAreaStr : AppUtil.toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( receiptDateStr );//AE
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( (detailRowCount == 2) ? productCodeStr18 : AppUtil.toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}else if(detailRowCount == 2 || detailRowCount == 3){
									strBuff.append( AppUtil.toLeftPaddingString("PM:"+receiptDateStr, 50, ' ') );//AW
								}
								strBuff.append( (detailRowCount == 2) ? regionSapStr : AppUtil.toLeftPaddingString("", 12, ' ') );//AX
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AY
								strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( (detailRowCount == 1 || detailRowCount == 2) ? tradingPartStr : AppUtil.toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CE
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//CF
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//CG
								strBuff.append( AppUtil.toLeftPaddingString("", 1, ' ') );//CH
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CI
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CJ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CK
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CL
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CM
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CN
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CO
//								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
								strBuff.append("\n");
								
							}
						}else{	//Header row 2
							for (int detailRowCount = 1; detailRowCount <= 2; detailRowCount++) {
								// Header
								strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( receiptUpdatedDateStr );//B
								strBuff.append("DW");//C
								strBuff.append("1000");//D
								strBuff.append( receiptUpdatedDateStr ); //E
								strBuff.append( receiptDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( receiptUpdatedDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( AppUtil.toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( receiptDateStr.substring(0,4) ); //N
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":"  " );//T
								strBuff.append( "S" );//U
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("21115102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("21110102", 10, '0') );//V
								}
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
								strBuff.append( vatStr );//X
								strBuff.append( vatStr );//Y
								strBuff.append( taxBaseAmtStr13 );//Z
								strBuff.append( taxBaseAmtStr13 );//AA
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//AB
								strBuff.append( branchCodeStr );//AC
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AE								
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toLeftPaddingString("PM:"+receiptDateStr, 50, ' ') );//AW
								}else{
									strBuff.append( AppUtil.toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}
								strBuff.append( regionSapStr );//AX
								strBuff.append( productCodeStr12 );//AY
								strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //CE
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//CF
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("21115102", 10, '0') );//CG
									strBuff.append( "S" );//CH
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("21110102", 10, '0') );//CG
									strBuff.append( "H" );//CH
								}
								strBuff.append( taxBaseAmtStr15 );//CI 
								strBuff.append( taxBaseAmtStr15 );//CJ 
								strBuff.append( vatStr );//CK
								strBuff.append( vatStr );//CL
								strBuff.append( "MWS" );//CM
								strBuff.append( "MWAS" );//CN
								strBuff.append( branchCodeStr );//CO
//								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
								strBuff.append("\n");
								
							}
						}
						headerRowCount++;
					}
				}
			}
		);
		
//		String path = "D:\\temp\\";
//		writeFileData(path+"EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(new Date(), "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC", strBuff);
		File file = new File(exportOutputPath+"EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(date, "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC");
		FileUtils.writeStringToFile(file, strBuff.toString(), "Cp1252");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void generateReversePaymentIdoc(Date date) throws Exception {//date = DateUtil.convertToDate("2017-06-30", DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		StringBuffer strBuff = new StringBuffer();
		strBuff.setLength(0);
		String dateStr = DateUtil.convertToString(date, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		headerRowCount = 0;
		
		episJdbcTemplate.query(
			" select "
			+"	r.UPDATEDTTM  "
			+"	,r.RECEIPTDTTM "
			+"	,inv_sum_ibacss.CURR , r.CURRENCYCODE, curr.MESSAGE "
			+"	,p.CURRENCYRATE "
			+"	,r.BRANCHNAME "
			+"	,r.BRANCHCODE "
			+"	,i.TOTALCHARGE "
			+"  ,i.CHARGE "
			+"	,inv_sum_ibacss.TOTAL_AMOUNT "
			+"	,i.VAT "
			+"	,d.WT, i.DEDUCTION "
			+"	,r.BRANCHAREA "
			+"	,inv_sum_ibacss.PRODUCT_CODE, tmp_inv_svc.PRODUCTCODE "
			+"	,m.CODE "
			+" ,cheque.CHEQUENO, cheque.PUBLISHER, cheque.BRANCH "
	//		+" --,money_transf.MONEYTRANSFERNO, money_transf.PUBLISHERNAME, money_transf.BRANCH "
			+"	,inv_sum_ibacss.REGION_SAP "
	//		+"	--,PM:yyyymmdd(วันที่หักล้าง(posting date)) "
			+"	,inv_sum_ibacss.BILLGROUP ,inv_sum_ibacss.CONTRNO, inv_sum_ibacss.BI_REF "
			+"	,inv_sum_ibacss.TRADING_PART "
			+"	,r.CANCELEDBY "
			+"  ,i.RECEIVED "
				
			+" from "
			+" shop_closing shop_close "
	//		+" --inner join tmpinvoiceservice tmp_inv_svc on shop_close.SHOP_CLOSING_ID = tmp_inv_svc.SHOP_CLOSING_ID "
			+" inner join correceipt r on shop_close.SHOP_CLOSING_ID = r.SHOP_CLOSING_ID "
			+" inner join corpayment p on p.PAYMENTID = r.PAYMENTID "
			+" inner join tmpinvoice i on p.PAYMENTID = i.PAYMENTID and r.RECEIPTID = i.RECEIPTID "
			+" inner join tmpinvoiceservice tmp_inv_svc on tmp_inv_svc.RECEIPTID = r.RECEIPTID and tmp_inv_svc.INVOICEID = i.INVOICEID "
			+" left join inv_summary_sap_ibacss inv_sum_ibacss on (''||inv_sum_ibacss.BI_REF) = i.INVOICENO "
			+" left join (select PAYMENTID, sum(AMOUNT) as WT from trsdeduction where DEDUCTIONTYPE in ('3TREDECIM','69BIS','69TRE') group by PAYMENTID) d on p.PAYMENTID = d.PAYMENTID "
			+" left join trsmethod m on m.PAYMENTID = p.PAYMENTID "
			+" left join trschequeref cheque on cheque.PAYMENTID = p.PAYMENTID "
	//		+" --left join trsmoneytransferref money_transf on money_transf.PAYMENTID = p.PAYMENTID "
			+" left join maschangerate curr on curr.CURRENCYCODE = i.CURRENCYCODE "
			+" where "
			+" trunc(shop_close.CREATEDTTM) =  to_date('"+dateStr+"', 'YYYY-MM-DD') "
			+" and (r.ATTRIBUTES not LIKE '%R%' or (trunc(p.PAYMENTDATE) = trunc(r.CANCELDTTM))) "
			
			, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					
			    	receiptUpdatedDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("UPDATEDTTM"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	receiptDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("RECEIPTDTTM"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	currencyStr = AppUtil.toLeftPaddingString(row.getString("MESSAGE"), 5, ' ');
			    	currencyRateStr = AppUtil.toLeftPaddingString((row.getBigDecimal("CURRENCYRATE") != null) ? row.getBigDecimal("CURRENCYRATE").toString() : "", 11, ' ');
			    	branchNameStr = AppUtil.toLeftPaddingString(row.getString("BRANCHNAME"), 16, ' ');
			    	branchNameStr = branchNameStr.replace("ศูนย์บริการลูกค้า", "ศบล. ");
			    	if(branchNameStr.length() > 16){
			    		branchNameStr.substring(0,16);
			    	}
			    	branchCodeStr = AppUtil.toLeftPaddingString(row.getString("BRANCHCODE"), 4, ' ');
			    	if(branchCodeStr.length() > 4) {
			    		branchCodeStr = branchCodeStr.substring(1, 5);
			    	}
			    	BigDecimal totalCharge = (row.getBigDecimal("TOTALCHARGE") != null) ? row.getBigDecimal("TOTALCHARGE") : BigDecimal.ZERO;
			    	BigDecimal vat = (row.getBigDecimal("VAT") != null) ? row.getBigDecimal("VAT") : BigDecimal.ZERO;
			    	BigDecimal wt = (row.getBigDecimal("WT") != null) ? row.getBigDecimal("WT") : BigDecimal.ZERO;
			    	BigDecimal transAmt = totalCharge.subtract(wt);
			    	BigDecimal taxBaseAmt = totalCharge.subtract(vat);
			    	BigDecimal receiveAmt = (row.getBigDecimal("RECEIVED") != null) ? row.getBigDecimal("RECEIVED") : BigDecimal.ZERO;
			    	
			    	totalChargeStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(totalCharge, "0.00") , 13, ' ');
			    	chargeStr13 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(row.getBigDecimal("CHARGE"), "0.00") , 13, ' ');
			    	chargeStr15 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(row.getBigDecimal("CHARGE"), "0.00") , 15, ' ');
	//		    	totalAmtStr = toLeftPaddingString( toNumberFormat(row.getBigDecimal("TOTAL_AMOUNT"), "0.00") , 13, ' ');
			    	vatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(vat, "0.00") , 13, ' ');
			    	wtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(wt, "0.00") , 13, ' ');
			    	transAmtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(transAmt, "0.00") , 13, ' ');
			    	taxBaseAmtStr13 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(taxBaseAmt, "0.00") , 13, ' ');
			    	taxBaseAmtStr15 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(taxBaseAmt, "0.00") , 15, ' ');
			    	branchAreaStr = AppUtil.toLeftPaddingString(row.getString("BRANCHAREA"), 4, ' ');
			    	productCodeStr12 = AppUtil.toLeftPaddingString(row.getString("PRODUCT_CODE"), 12, ' ');
			    	productCodeStr18 = AppUtil.toLeftPaddingString(row.getString("PRODUCT_CODE"), 18, '0');
	//		    	methodCodeStr = row.getInt("CODE");
			    	chequeNoStr = AppUtil.toLeftPaddingString(row.getString("CHEQUENO"), 18, ' ');
			    	regionSapStr = AppUtil.toLeftPaddingString(row.getString("REGION_SAP"), 12, ' ');
			    	tradingPartStr = AppUtil.toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	canceledByStr = AppUtil.toLeftPaddingString(row.getString("CANCELEDBY"), 16, ' ');
			    	receiveAmtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(receiveAmt, "0.00") , 13, ' ');
			    	billingGroupStr = row.getString("BILLGROUP");
			    	baNoStr = row.getString("CONTRNO");
			    	invNoStr = (row.getLong("BI_REF") == 0) ? "" : ""+row.getLong("BI_REF");
			    	text = "RE:"+billingGroupStr+":"+baNoStr+":"+invNoStr+":";
			    	
			    	
			    	
					// Detail Loop
					for (int detailRowCount = 1; detailRowCount <= 2; detailRowCount++) {
						// Header
						headerRowCount += (detailRowCount%2);
						strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
						strBuff.append( receiptDateStr );//B
						strBuff.append("DB");//C							
						strBuff.append("1000");//D
						strBuff.append( receiptDateStr ); //E
						strBuff.append( receiptDateStr.substring(4,6) ); //F
						strBuff.append( currencyStr );//G
						strBuff.append( currencyRateStr );//H
						strBuff.append( receiptUpdatedDateStr );// I
						strBuff.append( canceledByStr );//J
						strBuff.append( AppUtil.toLeftPaddingString("Reverse Payment", 25, ' ') );//K
						strBuff.append( branchCodeStr );//L
						strBuff.append( "RFBU" );//M
						strBuff.append( receiptDateStr.substring(0,4) ); //N
						strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
						strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
						
						//Detail
						strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
						strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":" " );//S
						strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":"  " );//T
						strBuff.append( "S" );//U
						if(detailRowCount == 1){
							strBuff.append( AppUtil.toRightPaddingString("11149102", 10, '0') );//V
						}else if(detailRowCount == 2){
							strBuff.append( AppUtil.toRightPaddingString("11140104", 10, '0') );//V
						}
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
						if(detailRowCount == 1){
							strBuff.append( totalChargeStr );//X
							strBuff.append( totalChargeStr );//Y
						}else if(detailRowCount == 2){	
							strBuff.append( receiveAmtStr );//X
							strBuff.append( receiveAmtStr );//Y
						}
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//Z
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AA
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AB
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AC
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AD
						strBuff.append( receiptDateStr );//AE
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
						strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
						strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
						strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
						strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
						strBuff.append( productCodeStr18 );//AN
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
						strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
						strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
						strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
						strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
//						strBuff.append( AppUtil.toLeftPaddingString("PM:"+receiptDateStr, 50, ' ') );//AW
						strBuff.append( AppUtil.toLeftPaddingString(text, 50, ' ') );//AW
						strBuff.append( regionSapStr );//AX
						strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AY
						strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
						strBuff.append( tradingPartStr );//BA
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
						strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
						strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
						strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
						strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CE
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//CF
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//CG
						strBuff.append( AppUtil.toLeftPaddingString("", 1, ' ') );//CH
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CI
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CJ
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CK
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CL
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CM
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CN
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CO
//							strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
						strBuff.append("\n");
						
					}

				}
			}
		);
		
		File file = new File(exportOutputPath+"EPISPAYMENTSUM_QAS_REVERSE_PAYMENT_"+DateUtil.convertToString(date, "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC");
		FileUtils.writeStringToFile(file, strBuff.toString(), "Cp1252");
		
	}
*/
	
	
	
	
	String payDateStr;
	String issueDateStr;
//	String currencyStr;
//	String currencyRateStr;
	String exchangeDateStr;
//	String branchNameStr;
//	String branchCodeStr;
	String accountNoStr;
	String amountStr;
//	String vatStr;
//	String wtStr;
	String payAmountStr;
	String amountIncludeVatStr;
	String amountExcludeVatStr13;
	String amountExcludeVatStr15;
	String businessAreaCodeStr;
//	String productCodeStr12;
//	String productCodeStr18;
//	String tradingPartStr;
	String postingDateStr;
//	String regionSapStr;
	String partnerCodeStr;
	String approveByStr;

	
	
	private void generateBillingIdoc(Date date) throws Exception {
		date = new Date();//date = DateUtil.convertToDate("2017-08-30", DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		StringBuffer strBuff = new StringBuffer();
    	strBuff.setLength(0);
    	String dateStr = DateUtil.convertToString(date, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
    	headerRowCount = 1;
    	
		episJdbcTemplate.query(
			" select "
			+"	trunc(t.PAY_DATE_DT) PAY_DATE "
			+"	,t.CURR "
			+"	,t.LOCATION_NAME "
			+"	,t.BUSINESS_PLACE "
			+"	,t.GL_ACCOUNT "
			+"	,t.PAYTYPE "
			+"	,sum(t.INV_AMT_BEFORE_VAT) INV_AMT_BEFORE_VAT "
			+"	,sum(t.INV_VAT) INV_VAT "
//				+"	,sum(t.PAY_WT) PAY_WT "
			+"  ,sum(t.PAY_TOTALAMOUNT) PAY_TOTALAMOUNT" 
			+"	,t.BUSINESS_AREA "
			+"	,t.PRO_PRODUCT_CODE "
			+"	,t.REGION_SAP "
			+"	,t.TRADING_PART "
			+"	,trunc(t.POST_DATE_DT) POST_DATE "
			+" from DW_EPIS t "
//				+" where trunc(t.SHOP_CLOSING_DATE_DT) = to_date('"+dateStr+"', 'YYYY-MM-DD') "
//				+" and (t.RECEIPT_STATUS not like '%R%' or trunc(t.REVERSE_DATE_DT) > trunc(t.PAY_DATE_DT)) "
			+" where paymentid in (4375) "
			+" group by "
			+"	trunc(t.PAY_DATE_DT) "
			+"	,t.CURR "
			+"	,t.LOCATION_NAME "
			+"	,t.BUSINESS_PLACE "
			+"	,t.GL_ACCOUNT "
			+"	,t.PAYTYPE "
			+"	,t.BUSINESS_AREA "
			+"	,t.PRO_PRODUCT_CODE "
			+"	,t.REGION_SAP "
			+"	,t.TRADING_PART "
			+"	,trunc(t.POST_DATE_DT) "
			
			, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
			    	
			    	payDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	issueDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	currencyStr = AppUtil.toLeftPaddingString(row.getString("CURR"), 5, ' ');
			    	currencyRateStr = AppUtil.toLeftPaddingString("1", 11, ' ');
			    	exchangeDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	branchNameStr = AppUtil.toLeftPaddingString(row.getString("LOCATION_NAME"), 16, ' ');
			    	branchNameStr = branchNameStr.replace("ศูนย์บริการลูกค้า", "ศบล. ");
			    	if(branchNameStr.length() > 16){
			    		branchNameStr = branchNameStr.substring(0,16);
			    	}
			    	branchCodeStr = AppUtil.toLeftPaddingString(row.getString("BUSINESS_PLACE"), 4, ' ');
			    	if(branchCodeStr.length() > 4) {
			    		branchCodeStr = branchCodeStr.substring(1, 5);
			    	}
			    	accountNoStr = AppUtil.toRightPaddingString(row.getString("GL_ACCOUNT"), 10, '0');
//				    	BigDecimal payAmount = (row.getBigDecimal("PAY_TOTALAMOUNT") != null) ? row.getBigDecimal("PAY_TOTALAMOUNT") : BigDecimal.ZERO;
			    	BigDecimal amountExcludeVat;// = (row.getBigDecimal("INV_AMT_BEFORE_VAT") != null) ? row.getBigDecimal("INV_AMT_BEFORE_VAT") : BigDecimal.ZERO;
			    	BigDecimal vat;// = (row.getBigDecimal("INV_VAT") != null) ? row.getBigDecimal("INV_VAT") : BigDecimal.ZERO;
			    	BigDecimal wt;// = (row.getBigDecimal("PAY_WT") != null) ? row.getBigDecimal("PAY_WT") : BigDecimal.ZERO;
			    	BigDecimal amountIncludeVat;// = amountExcludeVat.add(vat).add(wt);
			    	BigDecimal payAmount;// = amountExcludeVat.add(vat).subtract(wt);
			    	
			    	if("WT".equalsIgnoreCase(row.getString("PAYTYPE"))){
			    		amountExcludeVat = (row.getBigDecimal("INV_AMT_BEFORE_VAT") != null) ? row.getBigDecimal("INV_AMT_BEFORE_VAT") : BigDecimal.ZERO;
				    	vat = (row.getBigDecimal("INV_VAT") != null) ? row.getBigDecimal("INV_VAT") : BigDecimal.ZERO;
				    	wt = (row.getBigDecimal("PAY_TOTALAMOUNT") != null) ? row.getBigDecimal("PAY_TOTALAMOUNT") : BigDecimal.ZERO;
				    	amountIncludeVat = amountExcludeVat.add(vat).add(wt);
				    	payAmount = BigDecimal.ZERO;//amountExcludeVat.add(vat).subtract(wt);
			    	}else{
			    		amountExcludeVat = (row.getBigDecimal("INV_AMT_BEFORE_VAT") != null) ? row.getBigDecimal("INV_AMT_BEFORE_VAT") : BigDecimal.ZERO;
				    	vat = (row.getBigDecimal("INV_VAT") != null) ? row.getBigDecimal("INV_VAT") : BigDecimal.ZERO;
				    	wt = BigDecimal.ZERO;//(row.getBigDecimal("PAY_WT") != null) ? row.getBigDecimal("PAY_WT") : BigDecimal.ZERO;
				    	amountIncludeVat = amountExcludeVat.add(vat).add(wt);
				    	payAmount = amountExcludeVat.add(vat).subtract(wt);
			    	}
			    	
			    	payAmountStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(payAmount, "0.00") , 13, ' ');
			    	amountIncludeVatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountIncludeVat, "0.00") , 13, ' ');
			    	vatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(vat, "0.00") , 13, ' ');
			    	wtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(wt, "0.00") , 13, ' ');
			    	amountExcludeVatStr13 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountExcludeVat, "0.00") , 13, ' ');
			    	amountExcludeVatStr15 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountExcludeVat, "0.00") , 15, ' ');
			    	businessAreaCodeStr = AppUtil.toLeftPaddingString(row.getString("BUSINESS_AREA"), 4, ' ');
			    	productCodeStr12 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 12, ' ');
			    	productCodeStr18 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 18, ' ');//productCodeStr18 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 18, '0');
//				    	tradingPartStr = AppUtil.toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	postingDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("POST_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	regionSapStr = AppUtil.toLeftPaddingString(row.getString("REGION_SAP"), 12, ' ');
			    	partnerCodeStr = AppUtil.toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	
			    	
			    	
			    	
					// Header Loop
					for (int i = 1; i <= 2; i++) {
						
						// Detail Loop
						if(headerRowCount % 2 > 0){	//Header row 1
							for (int detailRowCount = 1; detailRowCount <= 3; detailRowCount++) {
								// Header
								strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( payDateStr );//B
								strBuff.append("RV");//C							
								strBuff.append("1000");//D
								strBuff.append( payDateStr ); //E
								strBuff.append( payDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( exchangeDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( AppUtil.toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( payDateStr.substring(0,4) ); //N
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":(detailRowCount==3)?"S":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":(detailRowCount==3)?"40":"  " );//T
								strBuff.append( "S" );//U
//									strBuff.append( accountNoStr );//V
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("11010102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("11149102", 10, '0') );//V
								}else if(detailRowCount == 3){
									strBuff.append( AppUtil.toRightPaddingString("11903102", 10, '0') );//V
								}
								
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
								if(detailRowCount == 1){
									strBuff.append( payAmountStr );//X
									strBuff.append( payAmountStr );//Y
								}else if(detailRowCount == 2){	
									strBuff.append( amountIncludeVatStr );//X
									strBuff.append( amountIncludeVatStr );//Y
								}else if(detailRowCount == 3){
									strBuff.append( wtStr );//X
									strBuff.append( wtStr );//Y
								}
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//Z
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AA
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AB
								if(detailRowCount == 1 || detailRowCount == 2){
									strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AC
								}else if(detailRowCount == 3){
									strBuff.append( branchCodeStr );//AC
								}
								strBuff.append( (detailRowCount == 1) ? businessAreaCodeStr : AppUtil.toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( payDateStr );//AE
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( (detailRowCount == 2) ? productCodeStr18 : AppUtil.toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}else if(detailRowCount == 2 || detailRowCount == 3){
									strBuff.append( AppUtil.toLeftPaddingString("PM:"+postingDateStr, 50, ' ') );//AW
								}
								strBuff.append( (detailRowCount == 2) ? regionSapStr : AppUtil.toLeftPaddingString("", 12, ' ') );//AX
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AY
								strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( (detailRowCount == 1 || detailRowCount == 2) ? partnerCodeStr : AppUtil.toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CE
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//CF
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//CG
								strBuff.append( AppUtil.toLeftPaddingString("", 1, ' ') );//CH
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CI
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CJ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CK
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CL
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CM
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CN
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CO
//									strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
								strBuff.append("\n");
								
							}
						}else{	//Header row 2
							for (int detailRowCount = 1; detailRowCount <= 2; detailRowCount++) {
								// Header
								strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( issueDateStr );//B
								strBuff.append("DW");//C
								strBuff.append("1000");//D
								strBuff.append( issueDateStr ); //E
								strBuff.append( payDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( exchangeDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( AppUtil.toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( payDateStr.substring(0,4) ); //N
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":"  " );//T
								strBuff.append( "S" );//U
//									strBuff.append( accountNoStr );//V
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("21115102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("21110102", 10, '0') );//V
								}
								
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
								strBuff.append( vatStr );//X
								strBuff.append( vatStr );//Y
								strBuff.append( amountExcludeVatStr13 );//Z
								strBuff.append( amountExcludeVatStr13 );//AA
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//AB
								strBuff.append( branchCodeStr );//AC
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AE								
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toLeftPaddingString("PM:"+postingDateStr, 50, ' ') );//AW
								}else{
									strBuff.append( AppUtil.toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}
								strBuff.append( regionSapStr );//AX
								strBuff.append( productCodeStr12 );//AY
								strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //CE
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//CF
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("21115102", 10, '0') );//CG
									strBuff.append( "S" );//CH
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("21110102", 10, '0') );//CG
									strBuff.append( "H" );//CH
								}
								strBuff.append( amountExcludeVatStr15 );//CI 
								strBuff.append( amountExcludeVatStr15 );//CJ 
								strBuff.append( vatStr );//CK
								strBuff.append( vatStr );//CL
								strBuff.append( "MWS" );//CM
								strBuff.append( "MWAS" );//CN
								strBuff.append( branchCodeStr );//CO
//									strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
								strBuff.append("\n");
								
							}
						}
						headerRowCount++;
					}
				}
			}
		);
		
//			String path = "D:\\temp\\";
//			writeFileData(path+"EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(new Date(), "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC", strBuff);
		File file = new File(exportOutputPath+"EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(date, "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC");
		FileUtils.writeStringToFile(file, strBuff.toString(), "Cp1252");
		
	}

	
	
	private void generateReversePaymentIdoc(Date date) throws Exception {date = DateUtil.convertToDate("2017-08-30", DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		StringBuffer strBuff = new StringBuffer();
		strBuff.setLength(0);
		String dateStr = DateUtil.convertToString(date, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		headerRowCount = 0;		
			
		episJdbcTemplate.query(
			" select "
			+" trunc(t.PAY_DATE) PAY_DATE "
			+" ,t.CURR "
			+" ,t.USER_APPROVE_BY "
			+" ,t.BUSINESS_PLACE "
			+" ,sum(t.INV_AMT_BEFORE_VAT) INV_AMT_BEFORE_VAT "
			+" ,sum(t.INV_VAT) INV_VAT "
			+" ,sum(t.PAY_WT) PAY_WT "
			+" ,t.PRO_PRODUCT_CODE "
			+" ,t.BILL_GROUP "
			+" ,t.CONTRNO "
			+" ,t.AR_REF "
			+" ,t.REGION_SAP "
			+" ,t.TRADING_PART "
			+" from DW_EPIS t "
			+" where trunc(t.SHOP_CLOSING_DATE) = to_date('"+dateStr+"', 'YYYY-MM-DD') "
			+" and (t.RECEIPT_STATUS not like '%R%' or trunc(t.REVERSE_DATE) <> trunc(t.PAY_DATE)) "
			+" and t.CANCELREASON like '002%' "
			+" group by "
			+" trunc(t.PAY_DATE) "
			+" ,t.CURR "
			+" ,t.USER_APPROVE_BY "
			+" ,t.BUSINESS_PLACE "
			+" ,t.PRO_PRODUCT_CODE "
			+" ,t.BILL_GROUP "
			+" ,t.CONTRNO "
			+" ,t.AR_REF "
			+" ,t.REGION_SAP "
			+" ,t.TRADING_PART "
			
			, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {    	
			    	
			    	payDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	currencyStr = AppUtil.toLeftPaddingString(row.getString("CURR"), 5, ' ');
			    	currencyRateStr = AppUtil.toLeftPaddingString("1", 11, ' ');
			    	exchangeDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	approveByStr = AppUtil.toLeftPaddingString(row.getString("USER_APPROVE_BY"), 16, ' ');
			    	branchCodeStr = AppUtil.toLeftPaddingString(row.getString("BUSINESS_PLACE"), 4, ' ');
			    	if(branchCodeStr.length() > 4) {
			    		branchCodeStr = branchCodeStr.substring(1, 5);
			    	}
//			    	BigDecimal payAmount = (row.getBigDecimal("PAY_TOTALAMOUNT") != null) ? row.getBigDecimal("PAY_TOTALAMOUNT") : BigDecimal.ZERO;
			    	BigDecimal amountExcludeVat = (row.getBigDecimal("INV_AMT_BEFORE_VAT") != null) ? row.getBigDecimal("INV_AMT_BEFORE_VAT") : BigDecimal.ZERO;
			    	BigDecimal vat = (row.getBigDecimal("INV_VAT") != null) ? row.getBigDecimal("INV_VAT") : BigDecimal.ZERO;
			    	BigDecimal wt = (row.getBigDecimal("PAY_WT") != null) ? row.getBigDecimal("PAY_WT") : BigDecimal.ZERO;
			    	BigDecimal amountIncludeVat = amountExcludeVat.add(vat);
			    	BigDecimal payAmount = amountExcludeVat.add(vat).subtract(wt);
			    	payAmountStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(payAmount, "0.00") , 13, ' ');
			    	amountIncludeVatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountIncludeVat, "0.00") , 13, ' ');
			    	vatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(vat, "0.00") , 13, ' ');
			    	wtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(wt, "0.00") , 13, ' ');
			    	amountExcludeVatStr13 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountExcludeVat, "0.00") , 13, ' ');
			    	amountExcludeVatStr15 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountExcludeVat, "0.00") , 15, ' ');
			    	productCodeStr12 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 12, ' ');
			    	productCodeStr18 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 18, '0');
			    	billingGroupStr = row.getString("BILL_GROUP");
			    	baNoStr = row.getString("CONTRNO");
			    	invNoStr = row.getString("AR_REF");
			    	text = "RE:"+billingGroupStr+":"+baNoStr+":"+invNoStr+":";
			    	regionSapStr = AppUtil.toLeftPaddingString(row.getString("REGION_SAP"), 12, ' ');
			    	partnerCodeStr = AppUtil.toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	
			    	
					// Detail Loop
					for (int detailRowCount = 1; detailRowCount <= 2; detailRowCount++) {
						// Header
						headerRowCount += (detailRowCount%2);
						strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
						strBuff.append( payDateStr );//B
						strBuff.append("DB");//C							
						strBuff.append("1000");//D
						strBuff.append( payDateStr ); //E
						strBuff.append( payDateStr.substring(4,6) ); //F
						strBuff.append( currencyStr );//G
						strBuff.append( currencyRateStr );//H
						strBuff.append( exchangeDateStr );// I
						strBuff.append( approveByStr );//J
						strBuff.append( AppUtil.toLeftPaddingString("Reverse Payment", 25, ' ') );//K
						strBuff.append( branchCodeStr );//L
						strBuff.append( "RFBU" );//M
						strBuff.append( payDateStr.substring(0,4) ); //N
						strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
						strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
						
						//Detail
						strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
						strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":" " );//S
						strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":"  " );//T
						strBuff.append( "S" );//U
						if(detailRowCount == 1){
							strBuff.append( AppUtil.toRightPaddingString("11149102", 10, '0') );//V
						}else if(detailRowCount == 2){
							strBuff.append( AppUtil.toRightPaddingString("11140104", 10, '0') );//V
						}
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
						if(detailRowCount == 1){
							strBuff.append( amountIncludeVatStr );//X
							strBuff.append( amountIncludeVatStr );//Y
						}else if(detailRowCount == 2){	
							strBuff.append( payAmountStr );//X
							strBuff.append( payAmountStr );//Y
						}
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//Z
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AA
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AB
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AC
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AD
						strBuff.append( payDateStr );//AE
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
						strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
						strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
						strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
						strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
						strBuff.append( productCodeStr18 );//AN
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
						strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
						strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
						strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
						strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
						strBuff.append( AppUtil.toLeftPaddingString(text, 50, ' ') );//AW
						strBuff.append( regionSapStr );//AX
						strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AY
						strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
						strBuff.append( partnerCodeStr );//BA
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
						strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
						strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
						strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
						strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
						strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CE
						strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//CF
						strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//CG
						strBuff.append( AppUtil.toLeftPaddingString("", 1, ' ') );//CH
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CI
						strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CJ
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CK
						strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CL
						strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CM
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CN
						strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CO
//							strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
						strBuff.append("\n");
						
					}

				}
			}
		);
		
		File file = new File(exportOutputPath+"EPISPAYMENTSUM_QAS_REVERSE_PAYMENT_"+DateUtil.convertToString(date, "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC");
		FileUtils.writeStringToFile(file, strBuff.toString(), "Cp1252");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void generateIdocFiles(Date date) throws Exception {
		date = new Date();//date = DateUtil.convertToDate("2017-08-30", DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		StringBuffer strBuff = new StringBuffer();
    	strBuff.setLength(0);
    	String dateStr = DateUtil.convertToString(date, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
    	
    	List<String> idocTypes = episJdbcTemplate.query("select IDOC_TYPE from IDOC_DATA group by IDOC_TYPE "
    		, new RowMapper<String>(){
	            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("IDOC_TYPE");
	            }
    	});
    	
    	if(idocTypes != null){
    		for (String type : idocTypes) {
    			generateIdocFileByType(type, date);		
			}
    	}
    			
	}
	
	
	
	String headerType;
	int detailRowCount;

	private void generateIdocFileByType(String idocType, Date date) throws Exception {
		
		StringBuffer strBuff = new StringBuffer();
    	strBuff.setLength(0);
    	headerRowCount = 0;
    	detailRowCount = 1;
    	headerType = "";
    	
		episJdbcTemplate.query(
				"select "
//				+"	IDOC_TYPE, "
				+"  B_H_DOC_DATE || C_H_DOC_TYPE || D_H_COMP_CODE || E_H_POST_DATE || F_H_FISC_PERIOD || G_H_CCY || H_H_EXCHANGE_RATE || I_H_TRANS_DATE || J_H_REF || K_H_DOC_TEXT || L_H_BRANCH || M_H_BUSS_TRANS || N_H_FISC_YEAR || O_H_USER_NAME || P_H_REF_PROC || Q_H_REF_DOC_NO as HEADER, "
//				+"	--A_H_SEQ, -- Line No "
				+"	B_H_DOC_DATE, C_H_DOC_TYPE, D_H_COMP_CODE, E_H_POST_DATE, F_H_FISC_PERIOD, G_H_CCY, H_H_EXCHANGE_RATE, I_H_TRANS_DATE, J_H_REF, K_H_DOC_TEXT, L_H_BRANCH, M_H_BUSS_TRANS, N_H_FISC_YEAR, O_H_USER_NAME, P_H_REF_PROC, Q_H_REF_DOC_NO, " 
//				+"	--R_D_LINE_NO, -- Line No "
				+"	S_D_DEBIT_CREDIT, T_D_POST_KEY, U_D_ACCT_TYPE, V_D_ACCOUNT, W_D_ALT_ACCT, "
				+"	sum(X_D_AMT_TRANS) as X_D_AMT_TRANS, "
				+"	sum(Y_D_AMT_LOCAL) as Y_D_AMT_LOCAL, "
				+"	sum(Z_D_TAX_BASE_TRANS) as Z_D_TAX_BASE_TRANS, "
				+"	sum(AA_D_TAX_BASE_LOCAL) as AA_D_TAX_BASE_LOCAL, "
				+"	AB_D_TAX_CODE, AC_D_BP, AD_D_BA, AE_D_VALUE_DATE, AF_D_PAYMENT_TERM, AG_D_BASELINE_DATE, "
				+"	sum(AH_D_AMT_DISC_BASE) as AH_D_AMT_DISC_BASE, "
				+"	AI_D_CCTR, AJ_D_FUNC_AREA, AK_D_WBS, AL_D_ACT_TYPE, AM_D_SEGMENT, AN_D_PRODUCT, AO_D_SUB_PRODUCT, AP_D_REV_TYPE, AQ_D_MATERIAL, AR_D_PLANT, AS_D_BUSS_PROCESS, AT_D_CUST_GROUP, AU_D_PAYMENT_REF, AV_D_ASSIGNMENT, AW_D_TEXT, AX_D_REF_1, AY_D_REF_2, AZ_D_REF_3, BA_D_TRADING_PART, "
//				+"	--BB_D_LINE_NO, -- Line No "
				+"	BC_D_TITLE, BD_D_NAME_1, BE_D_NAME_2, BF_D_NAME_3, BG_D_NAME_4, BH_D_HOUSE_NO, BI_D_CITY, BJ_D_POST_CODE, BK_D_COUNTRY_KEY, BL_D_TAX_NO_1, BM_D_TAX_NO_2, BN_D_PAYEE_CODE, BO_D_BANK_KEY, BP_D_BANK_ACCT, BQ_D_BANK_COUNTRY, "
//				+"	--BR_D_LINE_NO, -- Line No "
				+"	BS_D_WT_TYPE_1, BT_D_WT_CODE_1, "
				+"	sum(BU_D_WT_BASE_DOC_1) as BU_D_WT_BASE_DOC_1, "
				+"	sum(BV_D_WT_BASE_LOCAL_1) as BV_D_WT_BASE_LOCAL_1, "
				+"	sum(BW_D_WT_AMT_DOC_1) as BW_D_WT_AMT_DOC_1, "
				+"	sum(BX_D_WT_AMT_LOCAL_1) as BX_D_WT_AMT_LOCAL_1, "
				+"	BY_D_WT_TYPE_2, BZ_D_WT_CODE_2, "
				+"	sum(CA_D_WT_BASE_DOC_2) as CA_D_WT_BASE_DOC_2, "
				+"	sum(CB_D_WT_BASE_LOCAL_2) as CB_D_WT_BASE_LOCAL_2, "
				+"	sum(CC_D_WT_AMT_DOC_2) as CC_D_WT_AMT_DOC_2, "
				+"	sum(CD_D_WT_AMT_LOCAL_2) as CD_D_WT_AMT_LOCAL_2, "
//				+"	--CE_D_LINE_NO, -- Line No "
				+"	CF_D_TAX_SALE_CODE, CG_D_GENERAL_LEDGER_ACCT, CH_D_DEBIT_CREDIT_IND, "
				+"	sum(CI_D_LOCAL_TAX_BASE_AMT) as CI_D_LOCAL_TAX_BASE_AMT, "
				+"	sum(CJ_D_TAX_BASE_AMT) as CJ_D_TAX_BASE_AMT, "
				+"	sum(CK_D_LOCAL_TAX_AMT) as CK_D_LOCAL_TAX_AMT, "
				+"	sum(CL_D_TAX_AMT) as CL_D_TAX_AMT, "
				+"	CM_D_TRANS_KEY, CN_D_CONDITION_TYPE, CO_D_BP, CP_D_CARIER_OPERATOR "
	
				+"from IDOC_DATA "
				+"where IDOC_TYPE = '"+idocType+"' "
				+"group by "
				+"	IDOC_TYPE, "
//				+"	--A_H_SEQ, -- Line No "
				+"	B_H_DOC_DATE, C_H_DOC_TYPE, D_H_COMP_CODE, E_H_POST_DATE, F_H_FISC_PERIOD, G_H_CCY, H_H_EXCHANGE_RATE, I_H_TRANS_DATE, J_H_REF, K_H_DOC_TEXT, L_H_BRANCH, M_H_BUSS_TRANS, N_H_FISC_YEAR, O_H_USER_NAME, P_H_REF_PROC, Q_H_REF_DOC_NO, " 
//				+"	--R_D_LINE_NO, -- Line No "
				+"	S_D_DEBIT_CREDIT, T_D_POST_KEY, U_D_ACCT_TYPE, V_D_ACCOUNT, W_D_ALT_ACCT, "
//				+"	--sum(X_D_AMT_TRANS), "
//				+"	--sum(Y_D_AMT_LOCAL), "
//				+"	--sum(Z_D_TAX_BASE_TRANS), "
//				+"	--sum(AA_D_TAX_BASE_LOCAL), "
				+"	AB_D_TAX_CODE, AC_D_BP, AD_D_BA, AE_D_VALUE_DATE, AF_D_PAYMENT_TERM, AG_D_BASELINE_DATE, "
//				+"	--sum(AH_D_AMT_DISC_BASE), "
				+"	AI_D_CCTR, AJ_D_FUNC_AREA, AK_D_WBS, AL_D_ACT_TYPE, AM_D_SEGMENT, AN_D_PRODUCT, AO_D_SUB_PRODUCT, AP_D_REV_TYPE, AQ_D_MATERIAL, AR_D_PLANT, AS_D_BUSS_PROCESS, AT_D_CUST_GROUP, AU_D_PAYMENT_REF, AV_D_ASSIGNMENT, AW_D_TEXT, AX_D_REF_1, AY_D_REF_2, AZ_D_REF_3, BA_D_TRADING_PART, "
//				+"	--BB_D_LINE_NO, -- Line No "
				+"	BC_D_TITLE, BD_D_NAME_1, BE_D_NAME_2, BF_D_NAME_3, BG_D_NAME_4, BH_D_HOUSE_NO, BI_D_CITY, BJ_D_POST_CODE, BK_D_COUNTRY_KEY, BL_D_TAX_NO_1, BM_D_TAX_NO_2, BN_D_PAYEE_CODE, BO_D_BANK_KEY, BP_D_BANK_ACCT, BQ_D_BANK_COUNTRY, "
//				+"	--BR_D_LINE_NO, -- Line No "
				+"	BS_D_WT_TYPE_1, BT_D_WT_CODE_1, "
//				+"	--sum(BU_D_WT_BASE_DOC_1), "
//				+"	--sum(BV_D_WT_BASE_LOCAL_1), "
//				+"	--sum(BW_D_WT_AMT_DOC_1), "
//				+"	--sum(BX_D_WT_AMT_LOCAL_1), "
				+"	BY_D_WT_TYPE_2, BZ_D_WT_CODE_2, "
//				+"	--sum(CA_D_WT_BASE_DOC_2), "
//				+"--	sum(CB_D_WT_BASE_LOCAL_2), "
//				+"--	sum(CC_D_WT_AMT_DOC_2), "
//				+"--	sum(CD_D_WT_AMT_LOCAL_2), "
//				+"	--CE_D_LINE_NO, -- Line No "
				+"	CF_D_TAX_SALE_CODE, CG_D_GENERAL_LEDGER_ACCT, CH_D_DEBIT_CREDIT_IND, "
//				+"--	sum(CI_D_LOCAL_TAX_BASE_AMT), "
//				+"--	sum(CJ_D_TAX_BASE_AMT), "
//				+"--	sum(CK_D_LOCAL_TAX_AMT), "
//				+"--	sum(CL_D_TAX_AMT), "
				+"	CM_D_TRANS_KEY, CN_D_CONDITION_TYPE, CO_D_BP, CP_D_CARIER_OPERATOR "
				+"order by L_H_BRANCH, C_H_DOC_TYPE, S_D_DEBIT_CREDIT "
			
			, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {

					if(!headerType.equalsIgnoreCase(row.getString("HEADER"))){
						headerType = row.getString("HEADER");
						headerRowCount++;
						detailRowCount = 1;
					}
					
					String _J = row.getString("J_H_REF");
					if(_J != null){	System.out.println("type = "+idocType+", org -> "+_J);
						_J = _J.replace("ศูนย์บริการลูกค้า", "ศบล. ");	System.out.println("replace -> "+_J);
						_J = AppUtil.toLeftPaddingString(_J, 16, ' ');	System.out.println("padding -> "+_J);
						if(_J.length() > 16){
							_J.subSequence(0, 16);	System.out.println("substr -> "+_J);
						}
					}
					
					BigDecimal _X = (row.getBigDecimal("X_D_AMT_TRANS") != null) ? row.getBigDecimal("X_D_AMT_TRANS") : null;
					BigDecimal _Y = (row.getBigDecimal("Y_D_AMT_LOCAL") != null) ? row.getBigDecimal("Y_D_AMT_LOCAL") : null;
					BigDecimal _Z = (row.getBigDecimal("Z_D_TAX_BASE_TRANS") != null) ? row.getBigDecimal("Z_D_TAX_BASE_TRANS") : null;
					BigDecimal _AA = (row.getBigDecimal("AA_D_TAX_BASE_LOCAL") != null) ? row.getBigDecimal("AA_D_TAX_BASE_LOCAL") : null;
					BigDecimal _AH = (row.getBigDecimal("AH_D_AMT_DISC_BASE") != null) ? row.getBigDecimal("AH_D_AMT_DISC_BASE") : null;
					BigDecimal _BU = (row.getBigDecimal("BU_D_WT_BASE_DOC_1") != null) ? row.getBigDecimal("BU_D_WT_BASE_DOC_1") : null; 
					BigDecimal _BV = (row.getBigDecimal("BV_D_WT_BASE_LOCAL_1") != null) ? row.getBigDecimal("BV_D_WT_BASE_LOCAL_1") : null; 
					BigDecimal _BW = (row.getBigDecimal("BW_D_WT_AMT_DOC_1") != null) ? row.getBigDecimal("BW_D_WT_AMT_DOC_1") : null; 
					BigDecimal _BX = (row.getBigDecimal("BX_D_WT_AMT_LOCAL_1") != null) ? row.getBigDecimal("BX_D_WT_AMT_LOCAL_1") : null;
					BigDecimal _CA = (row.getBigDecimal("CA_D_WT_BASE_DOC_2") != null) ? row.getBigDecimal("CA_D_WT_BASE_DOC_2") : null; 
					BigDecimal _CB = (row.getBigDecimal("CB_D_WT_BASE_LOCAL_2") != null) ? row.getBigDecimal("CB_D_WT_BASE_LOCAL_2") : null; 
					BigDecimal _CC = (row.getBigDecimal("CC_D_WT_AMT_DOC_2") != null) ? row.getBigDecimal("CC_D_WT_AMT_DOC_2") : null; 
					BigDecimal _CD = (row.getBigDecimal("CD_D_WT_AMT_LOCAL_2") != null) ? row.getBigDecimal("CD_D_WT_AMT_LOCAL_2") : null; 
					BigDecimal _CI = (row.getBigDecimal("CI_D_LOCAL_TAX_BASE_AMT") != null) ? row.getBigDecimal("CI_D_LOCAL_TAX_BASE_AMT") : null; 
					BigDecimal _CJ = (row.getBigDecimal("CJ_D_TAX_BASE_AMT") != null) ? row.getBigDecimal("CJ_D_TAX_BASE_AMT") : null; 
					BigDecimal _CK = (row.getBigDecimal("CK_D_LOCAL_TAX_AMT") != null) ? row.getBigDecimal("CK_D_LOCAL_TAX_AMT") : null; 
					BigDecimal _CL = (row.getBigDecimal("CL_D_TAX_AMT") != null) ? row.getBigDecimal("CL_D_TAX_AMT") : null; 
					

			    	strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("B_H_DOC_DATE"), 8, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("C_H_DOC_TYPE"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("D_H_COMP_CODE"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("E_H_POST_DATE"), 8, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("F_H_FISC_PERIOD"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("G_H_CCY"), 5, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("H_H_EXCHANGE_RATE"), 11, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("I_H_TRANS_DATE"), 8, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(_J, 16, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("K_H_DOC_TEXT"), 25, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("L_H_BRANCH"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("M_H_BUSS_TRANS"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("N_H_FISC_YEAR"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("O_H_USER_NAME"), 12, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("P_H_REF_PROC"), 5, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("Q_H_REF_DOC_NO"), 10, ' ') );
			    	strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("S_D_DEBIT_CREDIT"), 1, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("T_D_POST_KEY"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("U_D_ACCT_TYPE"), 1, ' ') );
			    	if(row.getString("V_D_ACCOUNT") == null || row.getString("V_D_ACCOUNT").length() == 0){
			    		strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );
			    	}else{
			    		strBuff.append( AppUtil.toRightPaddingString(row.getString("V_D_ACCOUNT"), 10, '0') );
			    	}
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("W_D_ALT_ACCT"), 10, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_X!=null)?AppUtil.toNumberFormat(_X, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_Y!=null)?AppUtil.toNumberFormat(_Y, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_Z!=null)?AppUtil.toNumberFormat(_Z, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_AA!=null)?AppUtil.toNumberFormat(_AA, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AB_D_TAX_CODE"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AC_D_BP"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AD_D_BA"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AE_D_VALUE_DATE"), 8, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AF_D_PAYMENT_TERM"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AG_D_BASELINE_DATE"), 8, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_AH!=null)?AppUtil.toNumberFormat(_AH, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AI_D_CCTR"), 10, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AJ_D_FUNC_AREA"), 16, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AK_D_WBS"), 24, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AL_D_ACT_TYPE"), 6, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AM_D_SEGMENT"), 10, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AN_D_PRODUCT"), 18, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AO_D_SUB_PRODUCT"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AP_D_REV_TYPE"), 10, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AQ_D_MATERIAL"), 18, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AR_D_PLANT"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AS_D_BUSS_PROCESS"), 12, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AT_D_CUST_GROUP"), 10, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AU_D_PAYMENT_REF"), 30, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AV_D_ASSIGNMENT"), 18, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AW_D_TEXT"), 50, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AX_D_REF_1"), 12, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AY_D_REF_2"), 12, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("AZ_D_REF_3"), 20, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BA_D_TRADING_PART"), 6, ' ') );
			    	strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BC_D_TITLE"), 15, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BD_D_NAME_1"), 35, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BE_D_NAME_2"), 35, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BF_D_NAME_3"), 35, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BG_D_NAME_4"), 35, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BH_D_HOUSE_NO"), 35, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BI_D_CITY"), 35, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BJ_D_POST_CODE"), 10, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BK_D_COUNTRY_KEY"), 3, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BL_D_TAX_NO_1"), 16, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BM_D_TAX_NO_2"), 11, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BN_D_PAYEE_CODE"), 16, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BO_D_BANK_KEY"), 15, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BP_D_BANK_ACCT"), 18, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BQ_D_BANK_COUNTRY"), 3, ' ') );
			    	strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BS_D_WT_TYPE_1"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BT_D_WT_CODE_1"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_BU!=null)?AppUtil.toNumberFormat(_BU, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_BV!=null)?AppUtil.toNumberFormat(_BV, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_BW!=null)?AppUtil.toNumberFormat(_BW, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_BX!=null)?AppUtil.toNumberFormat(_BX, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BY_D_WT_TYPE_2"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("BZ_D_WT_CODE_2"), 2, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CA!=null)?AppUtil.toNumberFormat(_CA, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CB!=null)?AppUtil.toNumberFormat(_CB, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CC!=null)?AppUtil.toNumberFormat(_CC, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CD!=null)?AppUtil.toNumberFormat(_CD, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("CF_D_TAX_SALE_CODE"), 2, ' ') );
			    	if(row.getString("CG_D_GENERAL_LEDGER_ACCT") == null || row.getString("CG_D_GENERAL_LEDGER_ACCT").length() == 0){
			    		strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );
			    	}else{
			    		strBuff.append( AppUtil.toRightPaddingString(row.getString("CG_D_GENERAL_LEDGER_ACCT"), 10, '0') );
			    	}
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("CH_D_DEBIT_CREDIT_IND"), 1, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CI!=null)?AppUtil.toNumberFormat(_CI, "0.00"):"" , 15, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CJ!=null)?AppUtil.toNumberFormat(_CJ, "0.00"):"" , 15, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CK!=null)?AppUtil.toNumberFormat(_CK, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString( (_CL!=null)?AppUtil.toNumberFormat(_CL, "0.00"):"" , 13, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("CM_D_TRANS_KEY"), 3, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("CN_D_CONDITION_TYPE"), 4, ' ') );
			    	strBuff.append( AppUtil.toLeftPaddingString(row.getString("CO_D_BP"), 4, ' ') );
			    	strBuff.append("\n");
			    	
			    	detailRowCount++;
					
				}
			}
		);
		
		File file = new File(exportOutputPath+"2_"+idocType+"_EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(date, "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC");
//		FileUtils.writeStringToFile(file, strBuff.toString(), "Cp1252");
		FileUtils.writeStringToFile(file, strBuff.toString(), "TIS-620");
		
	}
	
	
	
	
}

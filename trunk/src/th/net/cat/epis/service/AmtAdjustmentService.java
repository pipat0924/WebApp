package th.net.cat.epis.service;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.net.cat.billing.repo.AdjustSapInsertTmpRepository;
import th.net.cat.epis.repo.AdjustSapInsertTmpLogRepository;
import th.net.cat.epis.repo.AmountAdjustmentDetailRepository;
import th.net.cat.epis.repo.AmountAdjustmentHeaderRepository;
import th.net.cat.epis.repo.AmountAdjustmentRepository;
import th.net.cat.epis.repo.AttachmentRepository;
import th.net.cat.epis.repo.ShopRepository;

import static java.util.Locale.ENGLISH;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.net.cat.billing.entity.AdjustSapInsertTmp;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.AmountAdjustmentDTO;
import th.net.cat.epis.dto.AmountAdjustmentSummaryDTO;
import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.dto.Invoice;
import th.net.cat.epis.dto.InvoiceDTO;
import th.net.cat.epis.dto.MasterDataDTO;
import th.net.cat.epis.dto.PaymentHistory;
import th.net.cat.epis.dto.PaymentHistoryDTO;
import th.net.cat.epis.entity.AdjustSapInsertTmpLog;
import th.net.cat.epis.entity.AmountAdjustment;
import th.net.cat.epis.entity.AmountAdjustmentDetail;
import th.net.cat.epis.entity.AmountAdjustmentHeader;
import th.net.cat.epis.entity.Attachment;
import th.net.cat.epis.entity.Shop;

import static org.apache.commons.lang.StringUtils.leftPad;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;
import th.net.cat.epis.util.ThaiBahtUtil;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.InvoiceHeaderBO;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderRequest;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderResponse;
import th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoRequest;
import th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse;

@org.springframework.stereotype.Service
public class AmtAdjustmentService {
	
	private static BigDecimal VAT = new BigDecimal(0.07);
	private static BigDecimal ZERO = new BigDecimal(0);
	private static BigDecimal ONE_HUNDRED = new BigDecimal("100");
	private static String ADJUST_TYPE_TAX = "01";
	private static String ADJUST_TYPE_CREDIT_NOTE = "02";
	
	private static Logger logger = Logger.getLogger(AmtAdjustmentService.class);
	@Autowired
	AmountAdjustmentRepository amtAdjmtRepo;
	
	@Autowired
	AmountAdjustmentHeaderRepository amtAdjmtHeaderRepo;
	
	@Autowired
	AmountAdjustmentDetailRepository amtAdjmtDetailRepo;
	
	@Autowired
	AdjustSapInsertTmpRepository adjSapInsertTmpRepo;
	
	@Autowired
	AdjustSapInsertTmpLogRepository adjSapInsertTmpLogRepo;
	
	@Autowired
	UtilityService us;
	
	@Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;
	
	@Resource(name = "viewCrmJdbcTemplate") JdbcTemplate viewCrmJdbcTemplate;
	
	@Resource(name="episNamedParamJdbcTemplate")
	NamedParameterJdbcTemplate episNamedParamJdbcTemplate;
	
	@Resource(name="billingJdbcTemplate")
    JdbcTemplate billingJdbcTemplate;
	
	@Resource(name="billingNamedParamJdbcTemplate")
	NamedParameterJdbcTemplate billingNamedParamJdbcTemplate;
	
	
	@Autowired th.net.cat.epis.ws.service.ESBWS_F01RetrieveInvoiceHeaderService _f01RetrieveInvoiceHeaderService;
	@Autowired th.net.cat.epis.ws.service.ESBWS_F03RetrieveInvoiceChargesService _f03RetrieveInvoiceChargesService;
	
	@Autowired PaymentService paymentService;
	
	@Autowired ShopRepository shopRepository;
	
	@Autowired 
	AttachmentService attService;
	
	@Transactional
	public AmountAdjustmentHeader createAmountAdjustment(AmountAdjustmentHeader aah , List<AmountAdjustment> aal, List<AmountAdjustmentDetail> aadl,List<Attachment> attl) {
		logger.info("service createAmountAdjustment....Start");	
		String branchCode = EpContextHolder.getContext().getBranchCode();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String posNo = EpContextHolder.getContext().getPosNo();
		String docType = "CT";
		logger.info("service createAmountAdjustment....> ReceiptId : "+aah.getReceiptId());	
		if(aah.getReceiptId() == null || aah.getReceiptId() == 0 ){
			docType = "CN";
		}
		Calendar cal = Calendar.getInstance();
		
		//Create adjust amount header first.
		aah.setCreateDate(cal.getTime());
		aah.setUpdateDate(cal.getTime());
		aah.setCreateUser(userName);
		aah.setUpdateUser(userName);
		aah.setAdjustShopId(EpContextHolder.getContext().getBranchId());//Setting for creation report and approval report.
		aah.setNo(getAmountAdjustmentNo(branchCode,AppConstants.SEQ_AMOUNT_ADJUSTMENT_NO, posNo, cal, docType));
		logger.info("createAmountAdjustment....Adjust Amount Header Id(1) = "+aah.getId());	
		aah = amtAdjmtHeaderRepo.save(aah);
		logger.info("createAmountAdjustment....Adjust Amount Header Id(2) = "+aah.getId());
		
		//Create adjust amount
		List<AmountAdjustment> aalRes = new ArrayList<AmountAdjustment>();
		for(AmountAdjustment aa : aal){
			aa.setCreateDate(cal.getTime());
			aa.setUpdateDate(cal.getTime());
			aa.setCreateUser(userName);
			aa.setUpdateUser(userName);
			aa.setAmountAjdmtHeaderId(aah.getId());
			logger.info("createAmountAdjustment....Adjust Amount Id(1) = "+aa.getId());	
			AmountAdjustment aaRes = amtAdjmtRepo.save(aa);
			logger.info("createAmountAdjustment....Adjust Amount Id(2) = "+aa.getId());	
			aalRes.add(aaRes);
		}
		
		//Total diference
		BigDecimal adjustAmountTotalDif = new BigDecimal(0);
		//Create adjust amount detail
		List<AmountAdjustmentDetail> aadlRes = new ArrayList<AmountAdjustmentDetail>();
		if(aadl != null && aadl.size() > 0 && aalRes.size() > 0){
			for(AmountAdjustment aa : aalRes){
				for(AmountAdjustmentDetail aad : aadl){
					logger.info("createAmountAdjustment....Adjust Amount Id(3) = "+aa.getId());	
					adjustAmountTotalDif = adjustAmountTotalDif.add(aad.getAdjustAmount());
					if(aa.getInvoiceId().equals(aad.getInvoiceId())){
						logger.info("createAmountAdjustment....Adjust Amount Id(3) = "+aa.getId());	
						aad.setAdjustAmtId(aa.getId());
						logger.info("createAmountAdjustment....Adjust Amount Id(4) = "+aa.getId());	
						aad.setCreateDate(cal.getTime());
						aad.setUpdateDate(cal.getTime());
						aad.setCreateUser(userName);
						aad.setUpdateUser(userName);
						aadlRes.add(amtAdjmtDetailRepo.save(aad));
					}
				}
				
			}
		}
		
		//Set total difernce
		aah.setAdjustAmountTotalDif(adjustAmountTotalDif);
		aah.setAdjustAmountTotalDifApv(adjustAmountTotalDif);
		logger.info("createAmountAdjustment....Total Differnce : "+adjustAmountTotalDif);	
		
		//Set total difernce * vat7%
		BigDecimal adjustAmountTotalDifVat = adjustAmountTotalDif.multiply(VAT);
		aah.setAdjustAmountTotalDifVat(adjustAmountTotalDifVat);
		aah.setAdjustAmountTotalDifVatApv(adjustAmountTotalDifVat);
		logger.info("createAmountAdjustment....Total Differnce VAT : "+aah.getAdjustAmountTotalDifVat());	
		
		//Set Total Differnce + Differnce*VAT
		BigDecimal adjustAmountTotalDifTotal = adjustAmountTotalDif.add(adjustAmountTotalDifVat);
		aah.setAdjustAmountTotal(adjustAmountTotalDifTotal);
		aah.setAdjustAmountTotalApv(adjustAmountTotalDifTotal);
		logger.info("createAmountAdjustment....Total Differnce + Differnce*VAT : "+aah.getAdjustAmountTotal());
		
		aah = amtAdjmtHeaderRepo.save(aah);
		
		//Create attachment in database.
		if(attl != null && attl.size() > 0){
			for(Attachment att : attl){
				att.setParentType("AMOUNT_ADJMT_HEADER");
				att.setParentId(aah.getId());
				attService.createAttachment(att);
			}
		}
		
		logger.info("service createAmountAdjustment....Done");	
		return aah;
	}
	
	public void exportAdjustData(AmountAdjustmentHeader aah , List<AmountAdjustment> aalRes , List<AmountAdjustmentDetail> aadlRes){
		
	}
	
	public String getAmountAdjustmentNo(String branchCode,String seqName, String posNo , Calendar cal , String docType){
		logger.info("service getAmountAdjustmentNo....Start");	
		String dt = FastDateFormat.getInstance("yyMMdd", ENGLISH).format(cal);
		//Get next sequence id;
		String nextSeqVal = us.getNextSequence(seqName).toString();
		
		StringBuilder sb = new StringBuilder();
		sb.append("EPO").append(branchCode).append(posNo).append(docType).append(dt).append(leftPad(nextSeqVal,4,"0"));
		String no = sb.toString();
		logger.info("service getAmountAdjustmentNo....Done >>"+no);
		return no;
	}
	
	public List<AmountAdjustment> createAmountAdjustmentReport(ServletContext context,HttpServletRequest request, HttpServletResponse response,AmountAdjustmentSummaryDTO gd) throws Exception {
		logger.info("service createAmountAdjustmentReport....Start");
		Map<String, String> header = gd.getHeader();
		String headerId = header.get("id");
		logger.info("headerId : "+headerId);
		//Find AmountAdjustmentHeader.
		AmountAdjustmentHeader aah = amtAdjmtHeaderRepo.findById(new Long(headerId));
		
		//Initial param
		String branchCode = EpContextHolder.getContext().getBranchCode();
		String branchName = EpContextHolder.getContext().getBranchName();
		List<MasterDataDTO> mdList = AppUtil.getGroupMasterData("ADJUST_BRANCH_NAME");
		logger.info("service createAmountAdjustmentReport....Start");
		//Get adjust amount type
		boolean isAdjustAmountTax = ADJUST_TYPE_TAX.equals(aah.getAdjustType())? true : false;
		
		//Update summary data into adjust amount header.
		if(isAdjustAmountTax){
			aah = updateHeader(aah);
		}
		
		
		//Prepare header report data.
		Map<String, Object> parameters = null;
		if(isAdjustAmountTax){
			parameters = getAmountAdjustHeaderDetail(headerId);
		}else{
			parameters = getAmountAdjustHeaderDetailCreditNote(aah);
		}

		String branchNameDefault = "";
		String branchNameOther = "";
		if(mdList != null && mdList.size() > 0){
			for(int i = 0 ; i< mdList.size() ; i++){
				MasterDataDTO md = mdList.get(i);
				if(md.getKey().equals(AppConstants.BRANCH_DEFAULT)){
					branchNameDefault = md.getValue();
				}else if (md.getKey().equals(AppConstants.BRANCH_ADJUST_AMOUNT)){
					branchNameOther = md.getValue();
				}
			}
		}
		if(branchCode != null && branchCode.equals(AppConstants.BRANCH_DEFAULT)){
			branchName = branchNameDefault+" " +branchCode+" " + branchName;
		}else{
			branchName = branchNameOther+" " +branchCode+" " + branchName;
		}
		
		parameters.put("branchAdjust", branchName);
		
		//Setting amount in Thai , for testing only
		parameters.put("amountThai",new ThaiBahtUtil().getText(parameters.get("adjustAmountTotal").toString()));
		
		//Select adjust amount detail record.
		List<Map<String,Object>> listItems = getAmountAdjustDetailList(headerId);
		if(listItems != null && listItems.size() > 0){
			parameters.put("invoiceNo",listItems.get(0).get("invoiceNo") );
			for(int index = 0 ; index < listItems.size() ;index++ ){
				listItems.get(index).put("no", (index+1)+"");
			}
		}
         
         /* Convert List to JRBeanCollectionDataSource */
         JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

         /* Map to hold Jasper report Parameters */
         parameters.put("AdjustmentAmountDsList", itemsJRBean);
		
		//Create pdf file
		try {
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			String reportName = "AdjustAmountDeductTax";
			if(!isAdjustAmountTax){
				reportName = "AdjustAmountDeductCreditNote";
			}
			net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(AppConstants.JASPER_JRXML_PATH) + File.separatorChar + reportName + AppConstants.FILE_TYPE_JRXML);
			//JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(context.getRealPath(AppConstants.JASPER_JRXML_PATH) + File.separatorChar + reportName+ AppConstants.FILE_TYPE_JASPER );
			jasperPrints.add(JasperFillManager.fillReport(report, parameters, new JREmptyDataSource()));
			response.setContentType("application/pdf");
			
			AppUtil.pushReportToOutputStream(response, jasperPrints);
		} catch (JRException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("service createAmountAdjustmentReport....Done");
		return null;
	}
	
	public List<AmountAdjustment> createAmountAdjustmentReportApv(ServletContext context,HttpServletRequest request, HttpServletResponse response,AmountAdjustmentSummaryDTO gd) throws Exception {
		logger.info("service createAmountAdjustmentReportApv....Start");
		Map<String, String> header = gd.getHeader();
		String headerId = header.get("id");
		logger.info("headerId : "+headerId);
		//Find AmountAdjustmentHeader.
		AmountAdjustmentHeader aah = amtAdjmtHeaderRepo.findById(new Long(headerId));
		
		//Initial param
		//String branchCode = EpContextHolder.getContext().getBranchCode();
		//String branchName = EpContextHolder.getContext().getBranchName();
		
		Shop shop = shopRepository.findById(aah.getAdjustShopId());
		String branchCode = shop.getBusinessPlace();
		String branchName = shop.getName();
		
		List<MasterDataDTO> mdList = AppUtil.getGroupMasterData("ADJUST_BRANCH_NAME");
		logger.info("service createAmountAdjustmentReportApv....Start");
		//Get adjust amount type
		boolean isAdjustAmountTax = ADJUST_TYPE_TAX.equals(aah.getAdjustType())? true : false;
		
		//Prepare header report data.
		Map<String, Object> parameters = null;
		if(isAdjustAmountTax){
			parameters = getAmountAdjustHeaderDetailApv(headerId);
		}else{
			parameters = getAmountAdjustHeaderDetailCreditNoteApv(aah);
		}

		String branchNameDefault = "";
		String branchNameOther = "";
		if(mdList != null && mdList.size() > 0){
			for(int i = 0 ; i< mdList.size() ; i++){
				MasterDataDTO md = mdList.get(i);
				if(md.getKey().equals(AppConstants.BRANCH_DEFAULT)){
					branchNameDefault = md.getValue();
				}else if (md.getKey().equals(AppConstants.BRANCH_ADJUST_AMOUNT)){
					branchNameOther = md.getValue();
				}
			}
		}
		if(branchCode != null && branchCode.equals(AppConstants.BRANCH_DEFAULT)){
			branchName = branchNameDefault+" " +branchCode+" " + branchName;
		}else{
			branchName = branchNameOther+" " +branchCode+" " + branchName;
		}
		
		parameters.put("branchAdjust", branchName);
		
		//Setting amount in Thai
		//parameters.put("amountThai",new ThaiBahtUtil().getText(parameters.get("adjustAmountTotal").toString()));
		
		//Select adjust amount detail record.
		List<Map<String,Object>> listItems = getAmountAdjustDetailListApv(headerId);
		if(listItems != null && listItems.size() > 0){
			parameters.put("invoiceNo",listItems.get(0).get("invoiceNo") );
			for(int index = 0 ; index < listItems.size() ;index++ ){
				listItems.get(index).put("no", (index+1)+"");
			}
		}
         
         /* Convert List to JRBeanCollectionDataSource */
         JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

         /* Map to hold Jasper report Parameters */
         parameters.put("AdjustmentAmountDsList", itemsJRBean);
		
		//Create pdf file
		try {
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			String reportName = "AdjustAmountDeductTaxApv";
			if(!isAdjustAmountTax){
				reportName = "AdjustAmountDeductCreditNoteApv";
			}
			net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(AppConstants.JASPER_JRXML_PATH) + File.separatorChar + reportName + AppConstants.FILE_TYPE_JRXML);
			//JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(context.getRealPath(AppConstants.JASPER_JRXML_PATH) + File.separatorChar + reportName+ AppConstants.FILE_TYPE_JASPER );
			jasperPrints.add(JasperFillManager.fillReport(report, parameters, new JREmptyDataSource()));
			response.setContentType("application/pdf");
			
			AppUtil.pushReportToOutputStream(response, jasperPrints);
		} catch (JRException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("service createAmountAdjustmentReportApv....Done");
		return null;
	}
	
	public Map<String, Object> getAmountAdjustHeaderDetail(String headerId){
		logger.info("getAmountAdjustHeaderDetail....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT ac.ACCOUNTNO custNo , ac.NAME custName , ac.RECEIPTADDRESS address  ")
		.append(", ac.TAXID taxId , coalesce(ac.BRANCH,'') branch , md.VALUE adjustReason , rc.RECEIPTNO refNo , aah.AMOUNT_ADJMT_HEADER_NO amountAdjustNo ")
		.append(", aah.AMOUNT_ADJMT_HEADER_NO amountAdjustNo,TO_CHAR(aah.CREATE_DATE, 'mm/dd/yyyy hh24:mm') amountAdjustDate ")
		.append(", TO_CHAR(aah.RECEIPT_AMOUNT, '999,999,999.99') receiptAmount ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL_CORRECT, '999,999,999.99') adjustAmountTotalCorrect ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL_DIF	, '999,999,999.99') adjustAmountTotalDif ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL_DIF_VAT, '999,999,999.99') adjustAmountTotalDifVat ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL, '999,999,999.99') adjustAmountTotal ")
		.append("	FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append(" LEFT JOIN EPIS.TMPACCOUNT ac ON aah.ACCOUNTID = ac.ACCOUNTID ")
		.append(" LEFT JOIN EPIS.MASTER_DATA md ON md.KEY = aah.ADJUST_REASON AND md.GROUP_KEY = 'ADJUST_DEBT_REASON_TYPE' AND md.STATUS = 'Y' ")
		.append(" LEFT JOIN EPIS.CORRECEIPT rc ON rc.RECEIPTID = aah.RECEIPTID ")
		.append("WHERE aah.AMOUNT_ADJMT_HEADER_ID = ? ");
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlSb.toString(), headerId);
		Map<String, Object> data = new HashMap<String, Object>();
		if(dataList != null && dataList.size() > 0){
			data = dataList.get(0);
		}
		logger.info("getAmountAdjustHeaderDetail....Done");
		return data;
	}
	
	public Map<String, Object> getAmountAdjustHeaderDetailApv(String headerId){
		logger.info("getAmountAdjustHeaderDetailApv....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT ac.ACCOUNTNO custNo , ac.NAME custName , ac.RECEIPTADDRESS address  ")
		.append(", ac.TAXID taxId , coalesce(ac.BRANCH,'') branch , md.VALUE adjustReason , rc.RECEIPTNO refNo , aah.AMOUNT_ADJMT_HEADER_NO amountAdjustNo ")
		.append(", aah.AMOUNT_ADJMT_HEADER_NO amountAdjustNo,TO_CHAR(aah.CREATE_DATE, 'mm/dd/yyyy hh24:mm') amountAdjustDate ")
		.append(", TO_CHAR(aah.RECEIPT_AMOUNT, '999,999,999.99') receiptAmount ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_CORRECT_APV, '999,999,999.99') adjustAmountTotalCorrect ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_DIF_APV	, '999,999,999.99') adjustAmountTotalDif ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_DIF_VAT_APV, '999,999,999.99') adjustAmountTotalDifVat ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_APV, '999,999,999.99') adjustAmountTotal ")
		.append("	FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append(" LEFT JOIN EPIS.TMPACCOUNT ac ON aah.ACCOUNTID = ac.ACCOUNTID ")
		.append(" LEFT JOIN EPIS.MASTER_DATA md ON md.KEY = aah.ADJUST_REASON AND md.GROUP_KEY = 'ADJUST_DEBT_REASON_TYPE' AND md.STATUS = 'Y' ")
		.append(" LEFT JOIN EPIS.CORRECEIPT rc ON rc.RECEIPTID = aah.RECEIPTID ")
		.append("WHERE aah.AMOUNT_ADJMT_HEADER_ID = ? ");
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlSb.toString(), headerId);
		Map<String, Object> data = new HashMap<String, Object>();
		if(dataList != null && dataList.size() > 0){
			data = dataList.get(0);
		}
		logger.info("getAmountAdjustHeaderDetailApv....Done");
		return data;
	}
	
	public Map<String, Object> getAmountAdjustHeaderDetailCreditNote(AmountAdjustmentHeader aah){
		logger.info("getAmountAdjustHeaderDetailCreditNote....Start");
		aah.getAccountNo();
		//Get billing account information.
		Map<String, Object> baMap = getBillingAcountInfo(aah.getAccountNo());
		
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT aah.AMOUNT_ADJMT_HEADER_NO amountAdjustNo,TO_CHAR(aah.CREATE_DATE, 'mm/dd/yyyy hh24:mm') amountAdjustDate ")
		.append(", md.VALUE adjustReason, TO_CHAR(aah.RECEIPT_AMOUNT, '999,999,999.99') receiptAmount ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL_CORRECT, '999,999,999.99') adjustAmountTotalCorrect ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL_DIF	, '999,999,999.99') adjustAmountTotalDif ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL_DIF_VAT, '999,999,999.99') adjustAmountTotalDifVat ")
		.append(",TO_CHAR(aah.ADJUST_AMOUNT_TOTAL, '999,999,999.99') adjustAmountTotal ")
		.append("	FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append(" LEFT JOIN EPIS.MASTER_DATA md ON md.KEY = aah.ADJUST_REASON AND md.GROUP_KEY = 'ADJUST_DEBT_REASON_TYPE' AND md.STATUS = 'Y' ")
		.append("WHERE aah.AMOUNT_ADJMT_HEADER_ID = ? ");
		String sqlStr = sqlSb.toString() ;
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlStr, aah.getId());
		Map<String, Object> data = new HashMap<String, Object>();
		if(dataList != null && dataList.size() > 0){
			data = dataList.get(0);
			baMap.putAll(data);
			
		}
		logger.info("getAmountAdjustHeaderDetailCreditNote....Start");
		return baMap;
	}
	
	public Map<String, Object> getAmountAdjustHeaderDetailCreditNoteApv(AmountAdjustmentHeader aah){
		logger.info("getAmountAdjustHeaderDetailCreditNoteApv....Start");
		aah.getAccountNo();
		//Get billing account information.
		Map<String, Object> baMap = getBillingAcountInfo(aah.getAccountNo());
		
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT aah.AMOUNT_ADJMT_HEADER_NO amountAdjustNo,TO_CHAR(aah.CREATE_DATE, 'mm/dd/yyyy hh24:mm') amountAdjustDate ")
		.append(", md.VALUE adjustReason, TO_CHAR(aah.RECEIPT_AMOUNT, '999,999,999.99') receiptAmount ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_CORRECT_APV, '999,999,999.99') adjustAmountTotalCorrect ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_DIF_APV	, '999,999,999.99') adjustAmountTotalDif ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_DIF_VAT_APV, '999,999,999.99') adjustAmountTotalDifVat ")
		.append(",TO_CHAR(aah.ADJMT_AMOUNT_TOTAL_APV, '999,999,999.99') adjustAmountTotal ")
		.append("	FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append(" LEFT JOIN EPIS.MASTER_DATA md ON md.KEY = aah.ADJUST_REASON AND md.GROUP_KEY = 'ADJUST_DEBT_REASON_TYPE' AND md.STATUS = 'Y' ")
		.append("WHERE aah.AMOUNT_ADJMT_HEADER_ID = ? ");
		String sqlStr = sqlSb.toString() ;
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlStr, aah.getId());
		Map<String, Object> data = new HashMap<String, Object>();
		if(dataList != null && dataList.size() > 0){
			data = dataList.get(0);
			baMap.putAll(data);
			
		}
		logger.info("getAmountAdjustHeaderDetailCreditNoteApv....Start");
		return baMap;
	}
	
	public List<AmountAdjustmentDTO> getAmountAdjustList(AmountAdjustmentHeader aah){
		List<AmountAdjustmentDTO> amtAdjustList = new ArrayList<AmountAdjustmentDTO>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT aah.AMOUNT_ADJMT_HEADER_NO amountAdjustNo , aa.BILL_REF_NO billRefNo , rc.RECEIPTNO receiptNo , aah.ACCOUNT_NO custNo , ac.NAME custName ")
        .append(" , p.PAYMENTCASE payMethod , aa.TOTAL_CHARGE totalAmt , aa.ADJUST_AMOUNT adjustAmt , NVL(rc.BRANCHNAME,' ') branch , rc.UPDATEUSER userName , m.VALUE status ")
        .append("  FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
        .append(" LEFT JOIN EPIS.AMOUNT_ADJUSTMENT aa ON aa.AMOUNT_ADJMT_HEADER_ID = aah.AMOUNT_ADJMT_HEADER_ID ")
        .append(" LEFT JOIN EPIS.TMPACCOUNT ac ON aah.ACCOUNTID = ac.ACCOUNTID ")
        .append(" LEFT JOIN EPIS.TMPINVOICE inv ON aa.INVOICEID = inv.INVOICEID ")
        .append(" LEFT JOIN EPIS.CORRECEIPT rc ON rc.RECEIPTID = aah.RECEIPTID ")
        .append(" LEFT JOIN EPIS.CORPAYMENT p ON p.PAYMENTID = aa.PAYMENTID ")
        .append(" LEFT JOIN EPIS.MASTER_DATA m ON m.KEY = aa.ADJUST_STATUS AND m.GROUP_KEY = 'ADJUST_AMOUNT_STATUS' AND m.STATUS = 'Y' ")
        .append(" WHERE aah.AMOUNT_ADJMT_HEADER_ID = ? ");
        amtAdjustList = episJdbcTemplate.query(query.toString(),new Object[]{aah.getId()}, BeanPropertyRowMapper.newInstance(AmountAdjustmentDTO.class));
        return amtAdjustList;
	}
	
	public List<AmountAdjustmentDTO> getAmountAdjustGeneralList(AmountAdjustmentHeader aah){
		logger.info("getAmountAdjustGeneralList....Start");
		List<AmountAdjustmentDTO> amtAdjustList = getAmountAdjustList(aah);
		
		//Get BA information
		Map<String, Object> baMap = getBillingAcountInfo(aah.getAccountNo());
		logger.info("findAdjustAmountList.... baMap.get(\"custName\") : "+baMap.get("custName"));
		//Set customer name
		for(AmountAdjustmentDTO aa:amtAdjustList){
			aa.setCustName((String)baMap.get("custName"));
		}
		logger.info("getAmountAdjustGeneralList....End");
		return amtAdjustList;
	}
	
	public List<Map<String, Object>> getAmountAdjustDetailList(String headerId){
		logger.info("getAmountAdjustDetailList....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT ssi.PRODUCT_NAME prodName , TO_CHAR(ssi.AMOUNT,'999,999,999.99') amount , TO_CHAR(ssi.BI_REF) invoiceNo ")
		.append(" , TO_CHAR(aad.ADJUST_AMOUNT,'999,999,999.99') adjustAmount , TO_CHAR(ssi.AMOUNT - aad.ADJUST_AMOUNT,'999,999,999.99') correctAmount , aa.BILL_CYCLE billCycle")
		.append(" FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append(" LEFT JOIN EPIS.AMOUNT_ADJUSTMENT aa ON aah.AMOUNT_ADJMT_HEADER_ID = aa.AMOUNT_ADJMT_HEADER_ID ")
		.append(" LEFT JOIN EPIS.AMOUNT_ADJMT_DTL aad ON aa.ADJUST_AMOUNT_ID = aad.ADJUST_AMOUNT_ID ")
		.append(" LEFT JOIN EPIS.INV_SUMMARY_SAP_IBACSS ssi ON aad.BI_REF = ssi.BI_REF AND aad.CONTRNO = ssi.CONTRNO ")
		.append("  	AND aad.PRODUCT_CODE = ssi.PRODUCT_CODE AND aad.SUB_PRODUCT_CODE = ssi.SUB_PRODUCT_CODE ")
		.append(" WHERE aah.AMOUNT_ADJMT_HEADER_ID = ?");
		String sql = sqlSb.toString() ;
		logger.info("getAmountAdjustDetailList....sql >> "+sql);
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sql, headerId);
		return dataList;
	}
	
	public List<Map<String, Object>> getAmountAdjustDetailListApv(String headerId){
		logger.info("getAmountAdjustDetailListApv....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT ssi.PRODUCT_NAME prodName , TO_CHAR(ssi.AMOUNT,'999,999,999.99') amount , TO_CHAR(ssi.BI_REF) invoiceNo ")
		.append(" , TO_CHAR(aad.ADJUST_AMOUNT,'999,999,999.99') adjustAmount , TO_CHAR(ssi.AMOUNT - aad.APPROVE_AMOUNT,'999,999,999.99') correctAmount , aa.BILL_CYCLE billCycle")
		.append(" FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append(" LEFT JOIN EPIS.AMOUNT_ADJUSTMENT aa ON aah.AMOUNT_ADJMT_HEADER_ID = aa.AMOUNT_ADJMT_HEADER_ID ")
		.append(" LEFT JOIN EPIS.AMOUNT_ADJMT_DTL aad ON aa.ADJUST_AMOUNT_ID = aad.ADJUST_AMOUNT_ID ")
		.append(" LEFT JOIN EPIS.INV_SUMMARY_SAP_IBACSS ssi ON aad.BI_REF = ssi.BI_REF AND aad.CONTRNO = ssi.CONTRNO ")
		.append("  	AND aad.PRODUCT_CODE = ssi.PRODUCT_CODE AND aad.SUB_PRODUCT_CODE = ssi.SUB_PRODUCT_CODE ")
		.append(" WHERE aah.AMOUNT_ADJMT_HEADER_ID = ?");
		String sql = sqlSb.toString() ;
		logger.info("getAmountAdjustDetailListApv....sql >> "+sql);
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sql, headerId);
		return dataList;
	}
	
	public BigDecimal getReceiptTotal(Long headerId){
		logger.info("getReceiptTotal....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT SUM(ssi.AMOUNT) amount FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append(" LEFT JOIN EPIS.AMOUNT_ADJUSTMENT aa ON aah.AMOUNT_ADJMT_HEADER_ID = aa.AMOUNT_ADJMT_HEADER_ID ")
		.append(" LEFT JOIN EPIS.AMOUNT_ADJMT_DTL aad ON aa.ADJUST_AMOUNT_ID = aad.ADJUST_AMOUNT_ID ")
		.append(" LEFT JOIN EPIS.INV_SUMMARY_SAP_IBACSS ssi ON aad.BI_REF = ssi.BI_REF AND aad.CONTRNO = ssi.CONTRNO ")
		.append("  	AND aad.PRODUCT_CODE = ssi.PRODUCT_CODE AND aad.SUB_PRODUCT_CODE = ssi.SUB_PRODUCT_CODE ")
		.append(" WHERE aah.AMOUNT_ADJMT_HEADER_ID = ?");
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlSb.toString(), headerId);
		Map<String, Object> data = new HashMap<String, Object>();
		BigDecimal receiptTotal = ZERO;
		if(dataList != null && dataList.size() > 0){
			data = dataList.get(0);
			for (Map.Entry<String, Object> entry : data.entrySet())
			{
				logger.info("getReceiptTotal....k/v : "+entry.getKey() + "/" + entry.getValue());
			}
			receiptTotal = new BigDecimal(data.get("amount").toString());
		}
		logger.info("getReceiptTotal....Receipt Total : "+receiptTotal);
		logger.info("getReceiptTotal....Done");
		return receiptTotal;
	}
	
	public AmountAdjustmentHeader updateHeader(AmountAdjustmentHeader aah){
		//Set receipt total
		aah.setReceiptAmount(getReceiptTotal(aah.getId()));
		
		//Set correct amount
		aah.setAdjustAmountTotalCorrect(aah.getReceiptAmount().subtract(aah.getAdjustAmountTotalDif()));
		
		aah = amtAdjmtHeaderRepo.save(aah);
		return aah;
	}
	
	public List<Map<String, Object>> findInvoiceForAdjustAmountList(String invNo) throws Exception{
		logger.info("findInvoiceForAdjustAmountList....Start : invoice no = "+invNo);
		List<Map<String, Object>> dataRes = new ArrayList<Map<String, Object>>();
		dataRes.add(findInvoiceForAdjustAmount(invNo));
		logger.info("findInvoiceForAdjustAmountList....Done : invoice no = "+invNo);
		return dataRes;
	}
	
	public Map<String, Object> findInvoiceForAdjustAmount(String invNo) throws Exception{
		logger.info("findInvoiceForAdjustAmount....Start : invoice no = "+invNo);
		//Call F01 to get invoice number.
		RetrieveInvoiceHeaderResponse response = getInvoiceInfo(invNo);
		String accountNo = "";
		Map<String,Object> dataMap = new HashMap<String,Object>();
		if(_f01RetrieveInvoiceHeaderService.isCalledSuccesful("0", response)) {
			if (response.getInvoiceHeaderList() != null & response.getInvoiceHeaderList().size() > 0) {
					InvoiceHeaderBO ih = response.getInvoiceHeaderList().get(0);
					dataMap.put("billRefNo", ih.getBillRefNo());
					dataMap.put("accountNo", ih.getBillingAccountNo());
					accountNo = ih.getBillingAccountNo();
					dataMap.put("status", "เธ�เธ�เธ•เธด");
					dataMap.put("statusCode", "C");
					dataMap.put("issueDt",ih.getIssueDate() != null? DateUtil.getDisplay(ih.getIssueDate().toGregorianCalendar().getTime(), DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE): null);
					dataMap.put("dueDt", ih.getDueDate() != null? DateUtil.getDisplay(ih.getDueDate().toGregorianCalendar().getTime(), DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE): null);
					dataMap.put("charge", ih.getAmount() == null ? BigDecimal.ZERO : BigDecimal.valueOf(ih.getAmount()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
					dataMap.put("vat", ih.getVat() == null ? BigDecimal.ZERO : BigDecimal.valueOf(ih.getVat()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
					dataMap.put("discount", ih.getVolumnDiscount() == null ? BigDecimal.ZERO : BigDecimal.valueOf(ih.getVolumnDiscount()));
					dataMap.put("totalCharge", ih.getTotal() == null ? BigDecimal.ZERO : BigDecimal.valueOf(ih.getTotal()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
					dataMap.put("balanceDue", ih.getBalanceDue() == null ? BigDecimal.ZERO : BigDecimal.valueOf(ih.getBalanceDue()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
					String chargeFromDateStr = DateUtil.getDisplay(ih.getChargeFromDate().toGregorianCalendar().getTime(), DateUtil.STANDARD_DATE_PATTERN, DateUtil.ENG_LOCALE);
					String chargeToDateStr = DateUtil.getDisplay(ih.getChargeToDate().toGregorianCalendar().getTime(), DateUtil.STANDARD_DATE_PATTERN, DateUtil.ENG_LOCALE);
					dataMap.put("billCycle", chargeFromDateStr + " - "+chargeToDateStr);
			}
		}
		
		//Get account information
		Map<String, Object> dataMapBa = getBillingAcountInfo(accountNo);
		
		//Get charge information.
		RetrieveInvoiceChargeInfoResponse chargeRes = getChargeInfo(invNo);
		
		//Get isStateAgency
		boolean isStateAgency = isStateAgency(dataMapBa);
		
		dataMap.put("deduction",(chargeRes.getRentalCharge() * (isStateAgency ? 0.01 : 0.05)) + (chargeRes.getUsageCharge() * (isStateAgency ? 0.01 : 0.03)));
		dataMap.put("accountName", dataMapBa.get("custName"));
		dataMap.put("accountType", dataMapBa.get("acctCatDesc"));
		
		logger.info("findInvoiceForAdjustAmount....Done : invoice no = "+invNo);
		return dataMap;
	}
	
	public List<Map<String, Object>> findInvoiceForAdjustAmountList(AmountAdjustmentSummaryDTO gd) throws Exception{
		logger.info("findInvoiceForAdjustAmountList...Start");
		List<Map<String, Object>> dataRes = new ArrayList<Map<String, Object>>();
		List<Map<String,String>> recordsList = gd.getRecords();
		logger.info("findInvoiceForAdjustAmountList...recordsList.size() > "+recordsList.size());
		for(Map<String,String> dataMap: recordsList){
			for (Map.Entry<String, String> entry : dataMap.entrySet()){
				logger.info(entry.getKey() + "/" + entry.getValue());
				dataRes.add(findInvoiceForAdjustAmount(entry.getKey()));
			}
		}
		logger.info("findInvoiceForAdjustAmountList...Done");
		return dataRes;
	}
	
	public RetrieveInvoiceChargeInfoResponse getChargeInfo(String invNo) throws Exception{
		logger.info("getChargeInfo....Start");
		//Select charge
		RetrieveInvoiceChargeInfoRequest chargeReq = new RetrieveInvoiceChargeInfoRequest();
		chargeReq.setBillRefNo(Integer.parseInt(invNo));
		chargeReq.setTransactionLog(_f03RetrieveInvoiceChargesService.buildTransactionLogCBO());
		RetrieveInvoiceChargeInfoResponse chargeRes = _f03RetrieveInvoiceChargesService.callInterface(chargeReq);
		logger.info("getChargeInfo....Done");
		return chargeRes;
	}
	
	public boolean isStateAgency(Map<String, Object> dataBa){
		logger.info("isStateAgency....Start");
		boolean isStateAgency = false;
		if(dataBa != null && dataBa.size() > 0){
			for (Map.Entry<String, Object> entry : dataBa.entrySet()){
				logger.info("getReceiptTotal....k/v : "+entry.getKey() + "/" + entry.getValue());
				if(entry.getKey().equalsIgnoreCase("custSegment")){
					isStateAgency = entry.getValue().toString().equals("2")?true:false;
					break;
				}
			}
		}
		logger.info("isStateAgency....Done..isStateAgency : "+isStateAgency);
		return isStateAgency;
	}
	
	public Map<String, Object> getBillingAcountInfo(String accountNo){
		logger.info("getBillingAcountInfo....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT cp.CAT_CUSTOMER_SEGMENT custSegment , cp.CUSTOMER_FULL_NAME custName , ba.CAT_BILL_ACCT_NUMBER custNo ")
		.append(" ,NVL(ba.BRANCH_ID,' ') branch , cp.CAT_CARD_NUMBER taxId , cp.CUSTOMER_TYPE custType")
		.append(", ba.BILL_ADDR_LINE1||ba.BILL_ADDR_LINE2||ba.BILL_ADDR_LINE3||' '||ba.BILL_ADDR_LINE4 ||' '||ba.BILL_ADDR_KHET_AMPHUR ")
		.append("||' '||ba.BILL_ADDR_PROVINCE_LKP||' '||ba.BILL_ADDR_POST_CODE  address " ) 
		.append(", lk.TEXT_STRING acctCatDesc ")
		.append("	FROM CRMDATA.CAT_BILL_ACCT ba " )
		.append(" LEFT JOIN CRMDATA.V_CATCRM_CUSTOMER_PROFILE cp ON ba.CUSTOMER_ID = cp.CUSTOMER_ID " )
		.append(" LEFT JOIN CRMDATA.LOOKUP lk ON lk.CODE_INT = ba.ACCT_CAT_LKP and lk.LOOKUP_CATEGORY_NAME_UP ='CAT_BILL_ACCOUNT_CATEGORY_LKP' and lk.OBSOLETE_FLAG = 0 " )
		.append(" 	WHERE ba.CAT_BILL_ACCT_NUMBER = ? ");
		
		List<Map<String, Object>> dataList = viewCrmJdbcTemplate.queryForList(sqlSb.toString(), accountNo);
		Map<String, Object> data = new HashMap<String, Object>();
		if(dataList != null && dataList.size() > 0){
			data = dataList.get(0);
		}
		logger.info("getBillingAcountInfo....Done");
		return data;
	}
	
	public List<Map<String, Object>> findExistReceiptAdjustAmountList(String invNo) throws Exception{
		logger.info("findExistReceiptAdjustAmountList....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT inv.INVOICENO , rc.ATTRIBUTES  FROM EPIS.TMPINVOICE INV ")
		.append(" LEFT JOIN EPIS.CORRECEIPT rc ON inv.RECEIPTID = rc.RECEIPTID ")
		.append("WHERE  INSTR(rc.ATTRIBUTES,'R') = 0  ")
		.append(" AND inv.INVOICENO = ? " );
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlSb.toString(), invNo);
		
		logger.info("findExistReceiptAdjustAmountList....Done");
		return dataList;
	}
	
	public CommonStatus<Map<String,Object>> findPaymentHistoryReceipt(){
		
		return null;
	}
	
	public List<Object> findInvoiceReceiptAdjustAmountList(String invNo,String no, String payType, Pageable p) throws Exception{
		//Check exist receipt
		List<Map<String, Object>> dataExistList = null;
		List<Map<String, Object>> dataRes = null;
		List<Object> dataObjList = new ArrayList<Object>();
		if(invNo != null && invNo.trim().length() > 0){
			dataExistList = findExistReceiptAdjustAmountList(invNo);
		}	
		//Call receipt history only
		if((dataExistList == null || dataExistList.size() == 0)&&(invNo != null && invNo.trim().length() > 0)){
			dataRes = findInvoiceForAdjustAmountList(invNo);
			if(dataRes != null && dataRes.size() > 0){
				for(Map dataMap : dataRes){
					dataObjList.add(dataMap);
				}
			}
		}
		//Call invoice list only
		else{
			PaymentHistoryDTO phDto = paymentService.findPaymentHistoryReceipt(no, invNo, payType, p);
			List<PaymentHistory> phList = phDto.getData();
			if(phList != null && phList.size() > 0){
				//Search Ba information
				Map<String, Object> dataMapBa = getBillingAcountInfo(phList.get(0).getAccountNo());
				String acctCatDesc = (String)dataMapBa.get("acctCatDesc");
				for(PaymentHistory ph : phList){
					if(ph!= null && ph.getStatusCode() != null && ph.getStatusCode().indexOf("R") == -1 ){
						ph.setAccountType(acctCatDesc);
						dataObjList.add(ph);
					}
				}
			}
		}
		
		return dataObjList;
	}
	
	public List<Map<String, Object>> findAdjustAmountList(String  invoiceNo,String  receiptNo,String  accountNo,String  amountAdjustHeaderNo){
		logger.info("findAdjustAmountList....Start >>> invoiceNo : "+invoiceNo+" , receiptNo : "+receiptNo +" , accountNo : "+accountNo+" , amountAdjustHeaderNo : "+amountAdjustHeaderNo);
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT aah.AMOUNT_ADJMT_HEADER_NO \"headerNo\" , aa.BILL_REF_NO \"billRefNo\" , rc.RECEIPTNO \"receiptNo\" , TO_CHAR(rc.RECEIPTDTTM, 'dd/mm/yyyy') \"receiptDate\",aah.ACCOUNT_NO \"accountNo\" , acc.NAME \"custName\" ") 
		.append(" , pm.PAYMENTCASE \"payMethod\" , TRIM(TO_CHAR(aa.ADJUST_AMOUNT, '999999999.99')) \"adjustAmount\",rc.BRANCHNAME \"branchName\" ,aah.CREATE_USER \"createUser\" , TO_CHAR(aah.CREATE_DATE, 'dd/mm/yyyy') \"createDate\" , md.VALUE \"status\" ")
		.append(", md2.VALUE \"reason\" , aah.ADJUST_REASON \"reasonCd\" , aah.AMOUNT_ADJMT_HEADER_ID \"headerId\" , aa.ADJUST_AMOUNT_ID \"adjAmtId\" , TRIM(TO_CHAR(aa.APPROVE_AMOUNT, '999999999.99')) \"approveAmount\"")
		.append(" , aah.ADJUST_STATUS \"adjustStatus\" , aah.ADJUST_CONDITION_TYPE \"adjustConditionType\" ")
		.append(" FROM EPIS.AMOUNT_ADJMT_HEADER aah ")
		.append("	LEFT JOIN EPIS.AMOUNT_ADJUSTMENT aa ON aah.AMOUNT_ADJMT_HEADER_ID = aa.AMOUNT_ADJMT_HEADER_ID ")
		.append("	LEFT JOIN EPIS.CORRECEIPT rc ON rc.RECEIPTID = aah.RECEIPTID AND aah.RECEIPTID <> 0 ")
		.append("   LEFT JOIN EPIS.TMPACCOUNT acc ON aah.ACCOUNTID = acc.ACCOUNTID AND aah.ACCOUNTID <> 0 " )
		.append("	LEFT JOIN EPIS.CORPAYMENT pm ON aa.PAYMENTID = pm.PAYMENTID AND aa.PAYMENTID  <> 0 " )
		.append("	LEFT JOIN EPIS.MASTER_DATA md ON aa.ADJUST_STATUS = md.KEY AND md.GROUP_KEY = 'ADJUST_AMOUNT_STATUS' AND md.STATUS = 'Y' ")
		.append("   LEFT JOIN EPIS.MASTER_DATA md2 ON md2.KEY = aah.ADJUST_REASON AND md2.GROUP_KEY = 'ADJUST_DEBT_REASON_TYPE' AND md2.STATUS = 'Y' ")
		.append(" WHERE 1=1")
		.append("      AND (:receiptNo is null or rc.RECEIPTNO = :receiptNo )")
		.append("		AND (:amountAdjustHeaderNo is null or aah.AMOUNT_ADJMT_HEADER_NO=:amountAdjustHeaderNo)")
		.append("		AND (:invoiceNo is null or aa.BILL_REF_NO = :invoiceNo)")
		.append("		AND (:accountNo is null or aah.ACCOUNT_NO = :accountNo)")
		.append(" ORDER BY aah.CREATE_DATE DESC ")
		;
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("invoiceNo", invoiceNo == ""|| invoiceNo == null?null:invoiceNo);
		params.addValue("receiptNo", receiptNo == ""|| receiptNo == null?null:receiptNo);
		params.addValue("accountNo", accountNo == ""|| accountNo == null?null:accountNo);
		params.addValue("amountAdjustHeaderNo", amountAdjustHeaderNo == ""|| amountAdjustHeaderNo == null?null:amountAdjustHeaderNo);
		String sql = sqlSb.toString();
		logger.info("findAdjustAmountList.... sql : "+sql);
		List<Map<String, Object>> dataList = episNamedParamJdbcTemplate.queryForList(sql, params);
		
		//Select Ba Information if there is no customer name
		if(dataList != null && dataList.size() > 0){
			Map<String, Object> dataMap = dataList.get(0);
			logger.info("findAdjustAmountList.... dataList.size() : "+dataList.size());
			logger.info("findAdjustAmountList.... dataMap.get(\"custName\") : "+dataMap.get("custName"));
			accountNo = accountNo==null||accountNo==""?dataMap.get("accountNo").toString():accountNo;
			Map<String, Object> baMap = getBillingAcountInfo(accountNo);
			logger.info("findAdjustAmountList.... baMap.get(\"custName\") : "+baMap.get("custName"));
			//Set customer name
			for(Map<String,Object> data:dataList){
				data.put("custName", baMap.get("custName"));
				data.put("custType", baMap.get("custType"));
				data.put("acctCatDesc", baMap.get("acctCatDesc"));
				
			}
			
		}
		logger.info("findAdjustAmountList....Done >>> ");
		return dataList;
		
	}
	
	public RetrieveInvoiceHeaderResponse getInvoiceInfo(String invNo){
		logger.info("getInvoiceInfo....Start");
		//Call F01 to get invoice number.
		RetrieveInvoiceHeaderRequest req = new RetrieveInvoiceHeaderRequest();
		req.setBillRefNo(invNo);
		req.setHasBalance(false);
		req.setTransactionLog(_f01RetrieveInvoiceHeaderService.buildTransactionLogCBO());
		RetrieveInvoiceHeaderResponse response = _f01RetrieveInvoiceHeaderService.callInterface(req);
		logger.info("getInvoiceInfo....Done");
		return response;
	}
	
	public List<Map<String, Object>> findAdjustAmountDetailList(String adjAmtId){
		logger.info("findAdjustAmountDetailList....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT aad.CONTRNO \"contrno\", aad.BI_REF \"billRefNo\",aad.PRODUCT_CODE \"productCode\"" )
		.append("   ,aad.SUB_PRODUCT_CODE \"subProductCode\",TRIM(TO_CHAR(aad.ADJUST_AMOUNT, '999,999,999.99')) \"adjustAmount\"  , aad.AMOUNT_ADJMT_DTL_ID \"adjAmtDtlId\"")
		.append(" , aad.COST_CENTER \"costCenter\"")
		.append(" FROM EPIS.AMOUNT_ADJMT_DTL aad WHERE aad.ADJUST_AMOUNT_ID =?" );
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlSb.toString(), adjAmtId);
		
		logger.info("findAdjustAmountDetailList....Done");
		return dataList;
	}
	
	@Transactional
	public void approveAdjustAmount(AmountAdjustmentHeader aah,List<AmountAdjustment> aal , List<AmountAdjustmentDetail> aadl) throws IllegalAccessException, InvocationTargetException{
		logger.info("approveAdjustAmount....Start");
		String updateUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Calendar cal = Calendar.getInstance();
		Date updateDate = cal.getTime();
		AmountAdjustmentHeader aahCurr = amtAdjmtHeaderRepo.findById(aah.getId());
		aahCurr.setAdjustStatus(aah.getAdjustStatus());
		aahCurr.setRemark(aah.getRemark());
		aahCurr.setAdjustApvReason(aah.getAdjustApvReason());
		aahCurr.setUpdateUser(updateUser);
		aahCurr.setUpdateDate(updateDate);
		aahCurr.setForceAdjType(aah.getForceAdjType());
		aahCurr.setAdjustAreaNo(aah.getAdjustAreaNo());
		aahCurr.setExportFileAdjustType(aah.getExportFileAdjustType());
		aahCurr.setPeriod(aah.getPeriod());
		aahCurr.setAnnotation(aah.getAnnotation());
		aahCurr.setAdjustAreaType(aah.getAdjustAreaType());
		//Total diference approve
		BigDecimal adjustAmountTotalDifApv = new BigDecimal(0);
		List<AmountAdjustment> aaCurrL = new ArrayList<AmountAdjustment>();
		if(aal != null && aal.size() > 0){
			for(int i = 0 ; i < aal.size() ; i++){
				AmountAdjustment aa = aal.get(i);
				AmountAdjustment aaCurr = amtAdjmtRepo.findById(aa.getId());
				aaCurr.setAdjustStatus(aah.getAdjustStatus());
				aaCurr.setApproveAmount(aa.getApproveAmount());
				aaCurr.setUpdateUser(updateUser);
				aaCurr.setUpdateDate(updateDate);
				aaCurrL.add(amtAdjmtRepo.save(aaCurr));
			}
			
			List<AmountAdjustmentDetail> aadCurrL = amtAdjmtDetailRepo.findByAdjustAmtId(aal.get(0).getId());
			if(aadCurrL != null && aadCurrL.size() > 0){
				for(int i = 0 ; i < aadCurrL.size() ; i++){
					AmountAdjustmentDetail aadCurr = aadCurrL.get(i);
					if(aadl != null && aadl.size() > 0 ){
						logger.info("approveAdjustAmount....aadCurr.getId() >> "+aadCurr.getId());
						Optional<AmountAdjustmentDetail> firstAmountAjmtDtl =  aadl.stream().filter(aadTemp -> aadCurr.getId().equals(aadTemp.getId())).findFirst();
						logger.info("approveAdjustAmount....firstAmountAjmtDtl.isPresent >> "+firstAmountAjmtDtl.isPresent());
						if (firstAmountAjmtDtl.isPresent()) {
							AmountAdjustmentDetail aadMod = firstAmountAjmtDtl.get();
							aadCurr.setApproveAmount(aadMod.getApproveAmount());
						}
					}
					
					adjustAmountTotalDifApv = adjustAmountTotalDifApv.add(aadCurr.getApproveAmount());
					
					aadCurr.setUpdateUser(updateUser);
					aadCurr.setUpdateDate(updateDate);
					AdjustSapInsertTmp asit = createAdjustSapInsertTmp(aahCurr, aadCurr);
					
					aadCurr.setSapId(asit.getId());
					amtAdjmtDetailRepo.save(aadCurr);
				}
			}
		}	
		
		//If AdjustStatus is "02" (Approve) then create approval no.
		if(aah.getAdjustStatus().equals(AppConstants.ADJUST_APPROVE)){
			String posNo = EpContextHolder.getContext().getPosNo();
			String docType = "CR";
			String branchCode = EpContextHolder.getContext().getBranchCode();
			aahCurr.setApvNo(getAmountAdjustmentNo(branchCode, AppConstants.SEQ_AMOUNT_ADJUSTMENT_NO_APV, posNo, cal, docType));
		}
		
		//Set total difernce (Apv)
		aahCurr.setAdjustAmountTotalDifApv(adjustAmountTotalDifApv);
		logger.info("createAmountAdjustment....Total Differnce(Apv) : "+adjustAmountTotalDifApv);	
		
		//Set total difernce * vat7% (Apv)
		BigDecimal adjustAmountTotalDifVatApv = adjustAmountTotalDifApv.multiply(VAT);
		aahCurr.setAdjustAmountTotalDifVatApv(adjustAmountTotalDifVatApv);
		logger.info("createAmountAdjustment....Total Differnce VAT(Apv) : "+aah.getAdjustAmountTotalDifVat());	
		
		//Set Total Differnce + Differnce*VAT (Apv)
		aahCurr.setAdjustAmountTotalApv(adjustAmountTotalDifApv.add(adjustAmountTotalDifVatApv));
		logger.info("createAmountAdjustment....Total Differnce + Differnce*VAT(Apv): "+aah.getAdjustAmountTotalApv());

		//Get adjust amount type
		boolean isAdjustAmountTax = ADJUST_TYPE_TAX.equals(aahCurr.getAdjustType())? true : false;
		//Set correct amount (Apv)
		if(isAdjustAmountTax){
			aahCurr.setAdjustAmountTotalCorrectApv(aahCurr.getReceiptAmount().subtract(aahCurr.getAdjustAmountTotalDifApv()));
		}
		
		aahCurr = amtAdjmtHeaderRepo.save(aahCurr);
		logger.info("approveAdjustAmount....Done");
	}
	
	public AdjustSapInsertTmp createAdjustSapInsertTmp(AmountAdjustmentHeader aahCurr,AmountAdjustmentDetail aadCurr) throws IllegalAccessException, InvocationTargetException{
		logger.info("createAdjustSapInsertTmp....Start");
		AdjustSapInsertTmp asit = new AdjustSapInsertTmp();
		Calendar now =  Calendar .getInstance();
		asit.setContractNo(aahCurr.getAccountNo());
		asit.setBillRefNo(aadCurr.getBiRef());
		asit.setAmount(aadCurr.getApproveAmount());
		asit.setAnnotation(aahCurr.getAnnotation());
		asit.setAdjReasonCode(aahCurr.getAdjustReason());
		asit.setAccountCodeNew(aadCurr.getAccountCodeNew());
		asit.setProductCode(aadCurr.getProdCd());
		asit.setSubProductCode(aadCurr.getSubProdCd());
		asit.setRevTypeCode(aadCurr.getRevTypeCode());
		asit.setCostCenter(aadCurr.getCostCenter());
		asit.setReqUser(aahCurr.getCreateUser());
		asit.setForceAdj(aahCurr.getForceAdjType());
		asit.setTradingPartner(aadCurr.getTradingPart());
		asit.setProcessDate(now.getTime());
		asit.setAppDate(aadCurr.getCreateDate());
		String createDtStr = DateUtil.getDisplay(aadCurr.getCreateDate(), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		asit.setFileName("dispute_"+aahCurr.getAdjustAreaType()+"_ibacss_"+createDtStr+"_"+aahCurr.getAdjustAreaNo()+(aahCurr.getExportFileAdjustType() == null || aahCurr.getExportFileAdjustType().trim().length() == 0 ?"":"_"+aahCurr.getExportFileAdjustType())+".xls");
		asit.setDocDate(createDtStr);
		asit.setProcessStatus(new Long(0));
		asit.setPeriod(aahCurr.getPeriod());
		asit.setBatchId(Long.parseLong(us.getNextSequenceBilling("ARBOR.CAT_ADJ_TMP_SEQ").toString()));
		asit.setId(Long.parseLong(us.getNextSequenceBilling("ARBOR.KEE_ADJ_SAP_IBACSS_SEQ").toString()));
		asit = adjSapInsertTmpRepo.save(asit);
		
		//Create Log in EPIS system
		AdjustSapInsertTmpLog asitLogDest = new AdjustSapInsertTmpLog();
		BeanUtils.copyProperties(asitLogDest, asit);
		adjSapInsertTmpLogRepo.save(asitLogDest);
		logger.info("createAdjustSapInsertTmp....Done");
		return asit;
	}
	
	public List<Map<String, Object>> findProductList(String invNo) throws Exception{
		logger.info("findProductList....Start");
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append(" SELECT BI_REF \"billRefNo\", PRODUCT_CODE \"productCode\",PRODUCT_NAME \"productName\" ,  SUB_PRODUCT_CODE \"subProductCode\" ,SUB_PRODUCT_NAME \"subProductName\" ")
		.append(" , ACCOUNT_CODE_NEW \"accountCodeNew\" , CCTR_SAP \"costCenter\" , TRADING_PART \"tradingPart\" , POSTING_DATE \"postingDate\" , REGION \"region\" , CONTRNO \"contrno\" ")
		.append(" , REVENUE_TYPE_CODE \"revTypeCode\" ,REV_TYPE_NAME \"revTypeName\" , TOTAL_AMOUNT \"amount\" FROM EPIS.INV_SUMMARY_SAP_IBACSS ")
		.append(" WHERE BI_REF=? ");
		List<Map<String, Object>> dataList = episJdbcTemplate.queryForList(sqlSb.toString(), invNo);
		
		logger.info("findProductList....Done");
		return dataList;
	}
	
	public static void main(String[] args){
	}

}
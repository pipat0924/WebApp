package th.net.cat.epis.controller.bouncecheque;

import static org.apache.commons.lang.StringUtils.stripToEmpty;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import th.net.cat.billing.entity.PaymentSapEpisCrDtl;
import th.net.cat.billing.entity.PaymentSapEpisOthoCrDtl;
import th.net.cat.billing.entity.PaymentSapEpisOthoRev;
import th.net.cat.billing.entity.PaymentSapEpisReverse;
import th.net.cat.billing.repo.PaymentSapEpisCrDtlRepository;
import th.net.cat.billing.repo.PaymentSapEpisOthoCrDtlRepository;
import th.net.cat.billing.repo.PaymentSapEpisOthoRevRepository;
import th.net.cat.billing.repo.PaymentSapEpisReverseRepository;

@Controller
public class ExportFileController {
	 private static final String UPLOAD_FOLDER = "/home/epis_user/cat/online/";
	
	@Autowired
	JdbcTemplate episJdbcTemplate;
	
	@Autowired
	PaymentSapEpisCrDtlRepository paymentSapEpisCrDtlRepository;
	
	@Autowired
	PaymentSapEpisOthoCrDtlRepository paymentSapEpisOthoCrDtlRepository;
	
	@Autowired
	PaymentSapEpisOthoRevRepository paymentSapEpisOthoRevRepository;
	
	@Autowired
	PaymentSapEpisReverseRepository paymentSapEpisReverseRepository;
	
	private static final String PAYMENT_SAP_EPIS_CR_DTL = "PAY_SAP_EPIS_CR_DTL";
	private static final String PAYMENT_SAP_EPIS_OTHO_CR_DTL = "PAY_SAP_EPIS_OTHO_CR_DTL";
	private static final String PAYMENT_SAP_EPIS_OTHO_REV = "PAY_SAP_EPIS_OTHO_REV";
	private static final String PAYMENT_SAP_EPIS_REVERSE = "PAY_SAP_EPIS_REVERSE";
	private static final DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static final String[] BILL_PAYMENT_IBACSS = { "BILLING_ACC_ID", "INVOICE_NO", "CUSTOMER_GROUP", "BILLGROUP",
			"SEGMENT_CODE", "PRODUCT_CODE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "INVDATE", "DUEDATE",
			"PAYMENT_LOCATION_CODE", "PAY_TYPE_CODE", "PAYMENT_DATETIME", "PERIOD", "PAY_AMOUNT", "VAT_AMOUNT", "TOTAL_AMOUNT",
			"COLLECTION_CODE", "REGION_SAP", "STATUS_FLAG", "ACCOUNT_CODE_NEW", "JOBDATE" };
	private static final String BILL_PAYMENT_IBACSS_HEAD = "BILLING_ACC_ID|INVOICE_NO|CUSTOMER_GROUP|BILLGROUP|SEGMENT_CODE|PRODUCT_CODE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|INVDATE|DUEDATE|PAYMENT_LOCATION_CODE|PAY_TYPE_CODE|PAYMENT_DATETIME|PERIOD|PAY_AMOUNT|VAT_AMOUNT|TOTAL_AMOUNT|COLLECTION_CODE|REGION_SAP|STATUS_FLAG|ACCOUNT_CODE_NEW|JOBDATE";
	private static final String[] BILL_PAYMENT_OTBOSS = { "BILLING_ACC_ID", "INVOICE_NO", "CUSTOMER_GROUP", "BILLGROUP",
			"SEGMENT_CODE", "PRODUCT_CODE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "INVDATE", "DUEDATE",
			"PAYMENT_LOCATION_CODE", "PAY_TYPE_CODE", "PAYMENT_DATETIME", "PERIOD", "PAY_AMOUNT", "VAT_AMOUNT", "TOTAL_AMOUNT",
			"COLLECTION_CODE", "REGION_SAP", "STATUS_FLAG", "ACCOUNT_CODE_NEW", "JOBDATE" };
	private static final String BILL_PAYMENT_OTBOSS_HEAD = "BILLING_ACC_ID|INVOICE_NO|CUSTOMER_GROUP|BILLGROUP|SEGMENT_CODE|PRODUCT_CODE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|"
			+ "INVDATE|DUEDATE|PAYMENT_LOCATION_CODE|PAY_TYPE_CODE|PAYMENT_DATETIME|PERIOD|PAY_AMOUNT|VAT_AMOUNT|TOTAL_AMOUNT|COLLECTION_CODE|REGION_SAP|STATUS_FLAG|ACCOUNT_CODE_NEW|JOBDATE";
	private static final String[] REVERSE_IBACSS = { "BILLING_ACC_ID", "INVOICE_NO", "CUSTOMER_GROUP", "BILLGROUP",
			"SEGMENT_CODE", "PRODUCT_CODE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "INVDATE", "DUEDATE",
			"PAYMENT_LOCATION_CODE", "PAY_TYPE_CODE", "PAYMENT_DATETIME", "PERIOD", "PAY_AMOUNT", "VAT_AMOUNT", "TOTAL_AMOUNT",
			"COLLECTION_CODE", "REGION_SAP", "STATUS_FLAG", "ACCOUNT_CODE_NEW", "JOBDATE" };
	private static final String REVERSE_IBACSS_HEAD = "BILLING_ACC_ID|INVOICE_NO|CUSTOMER_GROUP|BILLGROUP|SEGMENT_CODE|PRODUCT_CODE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|INVDATE|DUEDATE|"
			+ "PAYMENT_LOCATION_CODE|PAY_TYPE_CODE|PAYMENT_DATETIME|PERIOD|PAY_AMOUNT|VAT_AMOUNT|TOTAL_AMOUNT|COLLECTION_CODE|REGION_SAP|STATUS_FLAG|ACCOUNT_CODE_NEW|JOBDATE";
	private static final String[] REVERSE_OTBOSS = { "BILLING_ACC_ID", "INVOICE_NO", "CUSTOMER_GROUP", "BILLGROUP",
			"SEGMENT_CODE", "PRODUCT_CODE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "INVDATE", "DUEDATE",
			"PAYMENT_LOCATION_CODE", "PAY_TYPE_CODE", "PAYMENT_DATETIME", "PERIOD", "PAY_AMOUNT", "VAT_AMOUNT", "TOTAL_AMOUNT",
			"COLLECTION_CODE", "REGION_SAP", "STATUS_FLAG", "ACCOUNT_CODE_NEW", "JOBDATE" };
	private static final String REVERSE_OTBOSS_HEAD = "BILLING_ACC_ID|INVOICE_NO|CUSTOMER_GROUP|BILLGROUP|SEGMENT_CODE|PRODUCT_CODE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|INVDATE|DUEDATE|"
			+ "PAYMENT_LOCATION_CODE|PAY_TYPE_CODE|PAYMENT_DATETIME|PERIOD|PAY_AMOUNT|VAT_AMOUNT|TOTAL_AMOUNT|COLLECTION_CODE|REGION_SAP|STATUS_FLAG|ACCOUNT_CODE_NEW|JOBDATE";
	private static final String[] WEBBASE_RECEIPT = { "LOCATION_CODE", "RECEIPT_NO", "SERVICE_CODE", "PAY_DATE",
			"LOG_DATE", "USER_NAME", "AMT_BEFORE_VAT", "VAT_AMOUNT", "NET_AMOUNT", "RECEIPT_TYPE", "RECEIPT_STATUS",
			"BP", "BA", "POSTING_DATE", "CCTR" };
	private static final String WEBBASE_RECEIPT_HEAD = "LOCATION_CODE|RECEIPT_NO|SERVICE_CODE|PAY_DATE|LOG_DATE|USER_NAME|AMT_BEFORE_VAT|VAT_AMOUNT|NET_AMOUNT|RECEIPT_TYPE|RECEIPT_STATUS|BP|BA|POSTING_DATE|CCTR";

	private static final String[] WEBBASE_RECEIPT_DETAIL = { "RECEIPT_NO", "SEQ", "MATERIAL_CODE", "MATERIAL_NAME",
			"SEGMENT_CODE", "PRODUCT_CODE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "QUANTITY", "COST", "DISCOUNT",
			"ACCOUNT_CODE_NEW", "AMT_BEFORE_VAT", "VAT_AMOUNT", "NET_AMOUNT" };
	private static final String WEBBASE_RECEIPT_DETAIL_HEAD = "RECEIPT_NO|SEQ|MATERIAL_CODE|MATERIAL_NAME|SEGMENT_CODE|PRODUCT_CODE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|QUANTITY|COST|DISCOUNT|ACCOUNT_CODE_NEW|AMT_BEFORE_VAT|VAT_AMOUNT|NET_AMOUNT|";
	private static final String[] SAP_EPIS_CR_DTL = { "CONTRNO", "AR_REF", "PAY_LOCATION", "PAY_DATE", "PAY_AMOUNT",
			"PAY_VAT", "PAY_WT", "GL_ACCOUNT", "TRADING_PART", "BUSINESS_AREA", "BUSINESS_PLACE", "REGION",
			"PROCESS_DATETIME", "PRODUCT_CODE", "LOCATION_NAME", "BILL_GROUP", "CCTR", "POST_DATE", "RECEIPT_NO",
			"PAY_TOTALAMOUNT", "TYPE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "CUSTOMER_GROUP", "REMARK", "BILL_TYPE",
			"SERVICE_PRIORITY_PRODUCT", "SERVICE_PRIORITY_PRODUCT", "REVERSE_ID", "REGION_DW", "INV_DATE", "DUE_DATE",
			"PAY_TYPE", "DEFAULT_PROD", "USAGE_PERIOD", "COLLECTION_UNIT", "COLLECTION_CODE" };
	private static final String SAP_EPIS_CR_DTL_HEAD = "CONTRNO|AR_REF|PAY_LOCATION|PAY_DATE|PAY_AMOUNT|PAY_VAT|PAY_WT|GL_ACCOUNT|TRADING_PART|BUSINESS_AREA"
			+ "|BUSINESS_PLACE|REGION|PROCESS_DATETIME|PRODUCT_CODE|LOCATION_NAME|BILL_GROUP|CCTR|POST_DATE|RECEIPT_NO|PAY_TOTALAMOUNT|TYPE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|"
			+ "CUSTOMER_GROUP|REMARK|BILL_TYPE|SERVICE_PRIORITY_PRODUCT|REVERSE_ID|REGION_DW|INV_DATE|DUE_DATE|PAY_TYPE|DEFAULT_PROD|USAGE_PERIOD|COLLECTION_UNIT|COLLECTION_CODE";
	private static final String[] PAY_SAP_EPIS_OTHO_CR_DTL = { "CONTRNO", "AR_REF", "PAY_LOCATION", "PAY_DATE",
			"PAY_AMOUNT", "PAY_VAT", "PAY_WT", "GL_ACCOUNT", "TRADING_PART", "BUSINESS_AREA", "BUSINESS_PLACE",
			"REGION", "PROCESS_DATETIME", "PRODUCT_CODE", "LOCATION_NAME", "BILL_GROUP", "POST_DATE", "CCTR",
			"PAY_TOTALAMOUNT", "REF", "TYPE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "CUSTOMER_GROUP", "CAT_SERVICE",
			"PERIOD", "CHANNEL", "SERVICE_PRIORITY_PRODUCT", "RECEIPT_NO", "SERVICE_PRIORITY_PRODUCT", "REGION_DW",
			"INV_DATE", "DUE_DATE", "DEFAULT_PROD", "USAGE_PERIOD", "COLLECTION_UNIT", "COLLECTION_CODE" };
	private static final String PAY_SAP_EPIS_OTHO_CR_DTL_HEAD = "CONTRNO|AR_REF|PAY_LOCATION|PAY_DATE|PAY_AMOUNT|PAY_VAT|"
			+ "PAY_WT|GL_ACCOUNT|TRADING_PART|BUSINESS_AREA|BUSINESS_PLACE|REGION|PROCESS_DATETIME|PRODUCT_CODE|LOCATION_NAME|BILL_GROUP|"
			+ "POST_DATE|CCTR|PAY_TOTALAMOUNT|REF|TYPE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|CUSTOMER_GROUP|CAT_SERVICE|PERIOD|CHANNEL|"
			+ "RECEIPT_NO|SERVICE_PRIORITY_PRODUCT|REGION_DW|INV_DATE|DUE_DATE|PAY_TYPE|DEFAULT_PROD|USAGE_PERIOD|COLLECTION_UNIT|COLLECTION_CODE|";

	private static final String[] PAY_SAP_EPIS_OTHO_REV = { "RECEIPT_NO", "SERVICE_PRIORITY_PRODUCT", "REGION_DW",
			"INV_DATE", "DUE_DATE", "PAY_TYPE", "DEFAULT_PROD", "USAGE_PERIOD", "REVERSE_DATE", "COLLECTION_UNIT",
			"COLLECTION_CODE", "CONTRNO", "AR_REF", "PAY_LOCATION", "PAY_DATE", "PAY_AMOUNT", "PAY_VAT", "PAY_WT",
			"GL_ACCOUNT", "TRADING_PART", "BUSINESS_AREA", "BUSINESS_PLACE", "REGION", "PROCESS_DATETIME",
			"PRODUCT_CODE", "LOCATION_NAME", "BILL_GROUP", "POST_DATE", "CCTR", "PAY_TOTALAMOUNT", "REF", "TYPE",
			"SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "CUSTOMER_GROUP", "CAT_SERVICE", "PERIOD", "CHANNEL" };
	private static final String PAY_SAP_EPIS_OTHO_REV_HEAD = "RECEIPT_NO|SERVICE_PRIORITY_PRODUCT|REGION_DW|INV_DATE|DUE_DATE|PAY_TYPE|DEFAULT_PROD|USAGE_PERIOD|REVERSE_DATE|COLLECTION_UNIT|COLLECTION_CODE"
			+ "|CONTRNO|AR_REF|PAY_LOCATION|PAY_DAT|PAY_AMOUNT|PAY_VAT|PAY_WT|GL_ACCOUNT|TRADING_PART|BUSINESS_AREA|BUSINESS_PLACE|REGION|PROCESS_DATETIME|PRODUCT_CODE"
			+ "|LOCATION_NAME|BILL_GROUP|POST_DATE|CCTR|PAY_TOTALAMOUNT|REF|TYPE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|CUSTOMER_GROUP|CAT_SERVICE|PERIOD|CHANNEL|";

	private static final String[] PAY_SAP_EPIS_REVERSE = { "CONTRNO", "AR_REF", "PAY_LOCATION", "PAY_DATE",
			"PAY_AMOUNT", "PAY_VAT", "PAY_WT", "GL_ACCOUNT", "TRADING_PART", "BUSINESS_AREA", "BUSINESS_PLACE",
			"REGION", "PROCESS_DATETIME", "PRODUCT_CODE", "LOCATION_NAME", "BILL_GROUP", "CCTR", "POST_DATE",
			"REFERENCE", "PAY_TOTALAMOUNT", "TYPE", "SUB_PRODUCT_CODE", "REVENUE_TYPE_CODE", "CUSTOMER_GROUP", "REMARK",
			"BILL_TYPE", "SERVICE_PRIORITY_PRODUCT", "REVERSE_ID", "CHANNEL", "REVERSE_DATE", "REGION_DW", "INV_DATE",
			"DUE_DATE", "PAY_TYPE", "PERIOD", "COLLECTION_CODE", "COLLECTION_UNIT" };
	private static final String PAY_SAP_EPIS_REVERSE_HEAD = "CONTRNO|AR_REF|PAY_LOCATION|PAY_DATE|PAY_AMOUNT|PAY_VAT|PAY_WT|GL_ACCOUNT|TRADING_PART|BUSINESS_AREA|BUSINESS_PLACE|REGION|PROCESS_DATETIME|RODUCT_CODE|LOCATION_NAME|BILL_GROUP"
			+ "|CCTR|POST_DATE|REFERENCE|PAY_TOTALAMOUNT|TYPE|SUB_PRODUCT_CODE|REVENUE_TYPE_CODE|CUSTOMER_GROUP|REMARK|BILL_TYPE|SERVICE_PRIORITY_PRODUCT|REVERSE_ID|CHANNEL"
			+ "|REVERSE_DATE|REGION_DW|INV_DATE|DUE_DATE|PAY_TYPE|PERIOD|COLLECTION_CODE|COLLECTION_UNIT|";

	@RequestMapping(value = "DWEXPROT.EXPORT", method = RequestMethod.GET)
	public void billPaymentIbacss(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String reportID = stripToEmpty(request.getParameter("reportID"));
		String reportDate = ForMateDate(stripToEmpty(request.getParameter("SelectDate")));
		//System.out.println("reportID###################################################################>" + reportID);
		//System.out.println("SelectDate###################################################################>" + reportDate);
		byte[] outArray = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if ("DW_BILL_PAYMENT_IBACSS".equals(reportID)) {
			getData(baos, BILL_PAYMENT_IBACSS_HEAD, BILL_PAYMENT_IBACSS, reportID,"PAYMENT_DATETIME",reportDate);
		} else if ("DW_BILL_PAYMENT_OTBOSS".equals(reportID)) {
			getData(baos, BILL_PAYMENT_OTBOSS_HEAD, BILL_PAYMENT_OTBOSS, reportID,"PAYMENT_DATETIME",reportDate);
		} else if ("DW_REVERSE_IBACSS".equals(reportID)) {
			getData(baos, REVERSE_IBACSS_HEAD, REVERSE_IBACSS, reportID,"REVERSE_DATE",reportDate);
		} else if ("DW_REVERSE_OTBOSS".equals(reportID)) {
			getData(baos, REVERSE_OTBOSS_HEAD, REVERSE_OTBOSS, reportID,"REVERSE_DATE",reportDate);
		} else if ("DW_WEBBASE_RECEIPT".equals(reportID)) {
			getData(baos, WEBBASE_RECEIPT_HEAD, WEBBASE_RECEIPT, reportID,"PAY_DATE",reportDate);
		} else if ("DW_WEBBASE_RECEIPT_DETAIL".equals(reportID)) {
			getData(baos, WEBBASE_RECEIPT_DETAIL_HEAD, WEBBASE_RECEIPT_DETAIL, reportID,"PAY_DATE",reportDate);
		} else if ("PAY_SAP_EPIS_CR_DTL".equals(reportID)) {
			getData(baos, SAP_EPIS_CR_DTL_HEAD, SAP_EPIS_CR_DTL, reportID,"PAY_DATE",reportDate);
		} else if ("PAY_SAP_EPIS_OTHO_CR_DTL".equals(reportID)) {
			getData(baos, PAY_SAP_EPIS_OTHO_CR_DTL_HEAD, PAY_SAP_EPIS_OTHO_CR_DTL, reportID,"PAY_DATE",reportDate);
		} else if ("PAY_SAP_EPIS_REVERSE".equals(reportID)) {
			getData(baos, PAY_SAP_EPIS_REVERSE_HEAD, PAY_SAP_EPIS_REVERSE, reportID,"REVERSE_DATE",reportDate);
		} else if ("PAY_SAP_EPIS_OTHO_REV".equals(reportID)) {
			getData(baos, PAY_SAP_EPIS_OTHO_REV_HEAD, PAY_SAP_EPIS_OTHO_REV, reportID,"REVERSE_DATE",reportDate);
		}

		try {
			Date day = new Date();
			String dayS = sdf.format(day);
			outArray = baos.toByteArray();
			OutputStream outStream = setFile(response, outArray.length, reportID);
			outStream.write(outArray); //" + reportID + "_" + dayS + ".txt
			FileUtils.writeByteArrayToFile(new File(UPLOAD_FOLDER + reportID + "_" + dayS + ".txt"), outArray);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// fos.close();
			baos.close();
		}
	}
	
	public String ForMateDate(String date) throws Exception {
		//Date date1 = new SimpleDateFormat("yyyyMMdd").parse(date);  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = formatter.parse(date);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String reportDate = df.format(date1);
		return reportDate;
	}

	public OutputStream setFile(HttpServletResponse response, int outArraySize, String reportID) throws IOException {

		Date day = new Date();
		String dayS = sdf.format(day);
		response.setContentType("text/plain");
		response.setContentLength(outArraySize);
		response.setHeader("Content-Disposition", "attachment; filename=" + reportID + "_" + dayS + ".txt");
		return response.getOutputStream();
	}

	@Transactional
	public void getData(ByteArrayOutputStream baos, String header, String[] schema, String tablename,String  condito,String reportDate)
			throws IOException {
		// the string representation of date (month/day/year)
		//DateFormat df = new SimpleDateFormat("yyyyMMdd");

		// Get the date today using Calendar object.
		//Date today = Calendar.getInstance().getTime();   
		//String reportDate = df.format(today);
		List<Map<String, Object>> result;
		List<Map<String, Object>> resultSave;
		
		List<String> resutlString = new ArrayList<String>();
		
			result = episJdbcTemplate.queryForList("SELECT * FROM " + tablename+" WHERE "+condito+" = '"+reportDate+"'");
			//System.out.println("SQL######################################>>" +"SELECT * FROM " + tablename+" WHERE "+condito+" = '"+reportDate+"'");
			resultSave = result;


		for (Map<String, Object> m : result) {
			StringBuilder s = new StringBuilder();
			for (String a : schema) {
				if (null != m.get(a)) {
					s.append(m.get(a) + "|");
				} else {
					s.append(" " + "|");
				}
			}
			//System.out.println(s);
			// s.append("\n");
			resutlString.add(s.toString());

		}
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos, "CP1252"));
		writer.write(header);
		writer.write(getSystemNewline());
		for (String a : resutlString) {
			writer.write(a);
			writer.write(getSystemNewline());
			// writer.write("\r\n");
		}
		// baos.writeTo(fos);
		writer.close();
		
		//insert to table PAYMENT CAT
		if(resultSave.size() > 0){
		SavePaymentEpis(result,schema, tablename);
		}
	
	}
	
	@Transactional
	public void SavePaymentEpis(List<Map<String, Object>> result,String[] schema,String tablename){
		
		if(tablename.equals(PAYMENT_SAP_EPIS_CR_DTL)){
			//int i = 1;
			for (Map<String, Object> m : result) {
				PaymentSapEpisCrDtl data= new PaymentSapEpisCrDtl();
				
				if(null != m.get("CONTRNO")){	
				//data.setId((long) i++);
				data.setContrno(m.get("CONTRNO")+"");
				data.setAr_ref(m.get("AR_REF")+"");
				data.setPay_location(m.get("PAY_LOCATION") + "");
				data.setPay_date(m.get("PAY_DATE") + "");
				data.setPay_amount(new BigDecimal(m.get("PAY_AMOUNT")+""));//m.get("PAY_AMOUNT")+""
				data.setPay_vat(new BigDecimal(m.get("PAY_VAT")+"")); //m.get("PAY_VAT")+""
				data.setPay_wt(new BigDecimal(m.get("PAY_WT")+""));//m.get("PAY_WT")+""
				data.setGl_account(m.get("GL_ACCOUNT") + "");
				data.setTrading_part(m.get("TRADING_PART") + "");
				data.setBusiness_area(m.get("BUSINESS_AREA") + "");
				data.setBusiness_place(m.get("BUSINESS_PLACE") + "");
				data.setRegion(m.get("REGION") +"");
				data.setProcess_datetime(m.get("PROCESS_DATETIME") + "");
				data.setProduct_code(m.get("PRODUCT_CODE") + "");
				data.setLocation_name(m.get("LOCATION_NAME") +""); //size 16 
				data.setBill_group(m.get("BILL_GROUP") +"");
				data.setCctr(m.get("CCTR") + "");
				data.setPost_date(m.get("POST_DATE") +"");
				data.setReceipt_no(m.get("RECEIPT_NO") + "");
				data.setPay_totalamount(new BigDecimal(m.get("PAY_TOTALAMOUNT")+""));//m.get("PAY_TOTALAMOUNT")+""
				data.setType(m.get("TYPE") +""); //size 3
				data.setSub_product_code(m.get("SUB_PRODUCT_CODE")+"");
				data.setRevenue_type_code(m.get("REVENUE_TYPE_CODE") +"");
				data.setCustomer_group(m.get("CUSTOMER_GROUP") +"");
				//data.setRemark(m.get("REMARK")+""); //10
				data.setBill_type(m.get("BILL_TYPE") +"");
				data.setService_priority_product(m.get("SERVICE_PRIORITY_PRODUCT") +"");
				data.setReverse_id(m.get("REVERSE_ID") +"");
				data.setRegion_dw(m.get("REGION_DW") +"");
				data.setInv_date(m.get("INV_DATE") +"");
				data.setDue_date(m.get("DUE_DATE") +"");
				data.setPay_type(m.get("PAY_TYPE") +"");
				data.setDefault_prod(m.get("DEFAULT_PROD") +"");  // size 16 
				data.setUsage_period(m.get("USAGE_PERIOD") +"");
				data.setCollection_unit(m.get("COLLECTION_UNIT") +"");
				data.setCollection_code(m.get("COLLECTION_CODE") +"");
					
				paymentSapEpisCrDtlRepository.save(data);
				//System.out.println("##########################################SAVE paymentSapEpisCrDtlRepository SUCCESS############################################################");
				}
			}	
		}else if (tablename.equals(PAYMENT_SAP_EPIS_OTHO_CR_DTL)) {
			//int i = 1;
			for (Map<String, Object> m : result) {
				PaymentSapEpisOthoCrDtl data = new PaymentSapEpisOthoCrDtl();
				if(null != m.get("CONTRNO")){	
					//data.setId((long) i++);
					data.setContrno(m.get("CONTRNO")+"");
					data.setAr_ref(m.get("AR_REF")+"");
					data.setPay_location(m.get("PAY_LOCATION") + "");
					data.setPay_date(m.get("PAY_DATE") + "");
					data.setPay_amount(new BigDecimal(m.get("PAY_AMOUNT")+""));//m.get("PAY_AMOUNT")+""
					data.setPay_vat(new BigDecimal(m.get("PAY_VAT")+"")); //m.get("PAY_VAT")+""
					data.setPay_wt(new BigDecimal(m.get("PAY_WT")+""));//m.get("PAY_WT")+""
					data.setGl_account(m.get("GL_ACCOUNT") + "");
					data.setTrading_part(m.get("TRADING_PART") + "");
					data.setBusiness_area(m.get("BUSINESS_AREA") + "");
					data.setBusiness_place(m.get("BUSINESS_PLACE") + "");
					data.setRegion(m.get("REGION") +"");
					data.setProcess_datetime(m.get("PROCESS_DATETIME") + "");
					data.setProduct_code(m.get("PRODUCT_CODE") + "");
					data.setLocation_name(m.get("LOCATION_NAME") +""); //size 16 
					data.setBill_group(m.get("BILL_GROUP") +"");
					data.setPost_date(m.get("POST_DATE") +"");
					data.setCctr(m.get("CCTR") + "");
					data.setPay_totalamount(new BigDecimal(m.get("PAY_TOTALAMOUNT")+""));//m.get("PAY_TOTALAMOUNT")+""
					data.setRef(m.get("REF") + "");
					data.setType(m.get("TYPE") +""); //size 3
					data.setPay_type(m.get("PAY_TYPE")+"");
					data.setSub_product_code(m.get("SUB_PRODUCT_CODE")+"");
					data.setRevenue_type_code(m.get("REVENUE_TYPE_CODE") +"");
					data.setCustomer_group(m.get("CUSTOMER_GROUP") +"");
					data.setCat_service(m.get("CAT_SERVICE") +"");
					data.setPeriod(m.get("PERIOD") +"");
					data.setChannel(m.get("CHANNEL") +"");
					data.setService_priority_product(m.get("SERVICE_PRIORITY_PRODUCT") +"");
					data.setReceipt_no(m.get("RECEIPT_NO") + "");	
					data.setRegion_dw(m.get("REGION_DW") +"");
					data.setInv_date(m.get("INV_DATE") +"");
					data.setDue_date(m.get("DUE_DATE") +"");
					data.setDefault_prod(m.get("DEFAULT_PROD") +"");  // size 16 
					data.setUsage_period(m.get("USAGE_PERIOD") +"");
					data.setCollection_unit(m.get("COLLECTION_UNIT") +"");
					data.setCollection_code(m.get("COLLECTION_CODE") +"");
					
					paymentSapEpisOthoCrDtlRepository.save(data);
					//System.out.println("##########################################SAVE paymentSapEpisOthoCrDtlRepository SUCCESS############################################################");
				}
			}
			
		}else if (tablename.equals( PAYMENT_SAP_EPIS_OTHO_REV)) {
			//int i = 1;
			for (Map<String, Object> m : result) {
				PaymentSapEpisOthoRev data = new PaymentSapEpisOthoRev();
				if(null != m.get("CONTRNO")){	
					//data.setId((long) i++);
					data.setContrno(m.get("CONTRNO")+"");
					data.setAr_ref(m.get("AR_REF")+"");
					data.setPay_location(m.get("PAY_LOCATION") + "");
					data.setPay_date(m.get("PAY_DATE") + "");
					data.setPay_amount(new BigDecimal(m.get("PAY_AMOUNT")+""));//m.get("PAY_AMOUNT")+""
					data.setPay_vat(new BigDecimal(m.get("PAY_VAT")+"")); //m.get("PAY_VAT")+""
					data.setPay_wt(new BigDecimal(m.get("PAY_WT")+""));//m.get("PAY_WT")+""
					data.setGl_account(m.get("GL_ACCOUNT") + "");
					data.setTrading_part(m.get("TRADING_PART") + "");
					data.setBusiness_area(m.get("BUSINESS_AREA") + "");
					data.setBusiness_place(m.get("BUSINESS_PLACE") + "");
					data.setRegion(m.get("REGION") +"");
					data.setProcess_datetime(m.get("PROCESS_DATETIME") + "");
					data.setProduct_code(m.get("PRODUCT_CODE") + "");
					data.setLocation_name(m.get("LOCATION_NAME") +""); //size 16 
					data.setBill_group(m.get("BILL_GROUP") +"");
					data.setPost_date(m.get("POST_DATE") +"");
					data.setCctr(m.get("CCTR") + "");
					data.setPay_totalamount(new BigDecimal(m.get("PAY_TOTALAMOUNT")+""));//m.get("PAY_TOTALAMOUNT")+""
					data.setRef(m.get("REF") + "");
					data.setType(m.get("TYPE") +""); //size 3
					data.setSub_product_code(m.get("SUB_PRODUCT_CODE")+"");
					data.setRevenue_type_code(m.get("REVENUE_TYPE_CODE") +"");
					data.setCustomer_group(m.get("CUSTOMER_GROUP") +"");
					data.setCat_service(m.get("CAT_SERVICE") +"");
					data.setPeriod(m.get("PERIOD") +"");
					data.setChannel(m.get("CHANNEL") +"");
					data.setService_priority_product(m.get("SERVICE_PRIORITY_PRODUCT") +"");
					data.setReceipt_no(m.get("RECEIPT_NO") + "");	
					data.setRegion_dw(m.get("REGION_DW") +"");
					data.setInv_date(m.get("INV_DATE") +"");
					data.setDue_date(m.get("DUE_DATE") +"");
					data.setPay_type(m.get("PAY_TYPE") +"");
					data.setDefault_prod(m.get("DEFAULT_PROD") +"");  // size 16 
					data.setUsage_period(m.get("USAGE_PERIOD") +"");
					data.setReverse_date(m.get("REVERSE_DATE") +"");
					data.setCollection_unit(m.get("COLLECTION_UNIT") +"");
					data.setCollection_code(m.get("COLLECTION_CODE") +"");
					
					paymentSapEpisOthoRevRepository.save(data);
					//System.out.println("##########################################SAVE paymentSapEpisOthoRevRepository SUCCESS############################################################");
				}
			}
			
		}else if (tablename.equals(PAYMENT_SAP_EPIS_REVERSE)) {
			//int i = 1;
			for (Map<String, Object> m : result) {
				PaymentSapEpisReverse data = new PaymentSapEpisReverse();
				if(null != m.get("CONTRNO")){	
					//data.setId((long) i++);
					data.setContrno(m.get("CONTRNO")+"");
					data.setAr_ref(new BigDecimal(m.get("AR_REF")+"") );
					data.setPay_location(m.get("PAY_LOCATION") + "");
					data.setPay_date(m.get("PAY_DATE") + "");
					data.setPay_amount(new BigDecimal(m.get("PAY_AMOUNT")+""));//m.get("PAY_AMOUNT")+""
					data.setPay_vat(new BigDecimal(m.get("PAY_VAT")+"")); //m.get("PAY_VAT")+""
					data.setPay_wt(new BigDecimal(m.get("PAY_WT")+""));//m.get("PAY_WT")+""
					data.setGl_account(m.get("GL_ACCOUNT") + "");
					data.setTrading_part(m.get("TRADING_PART") + "");
					data.setBusiness_area(m.get("BUSINESS_AREA") + "");
					data.setBusiness_place(m.get("BUSINESS_PLACE") + "");
					data.setRegion(m.get("REGION") +"");
					data.setProcess_datetime(m.get("PROCESS_DATETIME") + "");
					data.setProduct_code(m.get("PRODUCT_CODE") + "");
					data.setLocation_name(m.get("LOCATION_NAME") +""); //size 16 
					data.setBill_group(m.get("BILL_GROUP") +"");
					data.setCctr(m.get("CCTR") + "");
					data.setPost_date(m.get("POST_DATE") +"");
					data.setReference(m.get("REFERENCE") +"");
					data.setPay_totalamount(new BigDecimal(m.get("PAY_TOTALAMOUNT")+""));//m.get("PAY_TOTALAMOUNT")+""
					data.setType(m.get("TYPE") +""); //size 3
					data.setSub_product_code(m.get("SUB_PRODUCT_CODE")+"");
					data.setRevenue_type_code(m.get("REVENUE_TYPE_CODE") +"");
					data.setCustomer_group(m.get("CUSTOMER_GROUP") +"");
					data.setRemark(m.get("REMARK")+"");
					data.setBill_type(m.get("BILL_TYPE")+"");
					data.setService_priority_product(m.get("SERVICE_PRIORITY_PRODUCT") +"");
					data.setReverse_id(m.get("REVERSE_ID") +"");
					data.setChannel(m.get("CHANNEL") +"");	
					data.setReverse_date(m.get("REVERSE_DATE") +"");
					data.setRegion_dw(m.get("REGION_DW") +"");
					data.setInv_date(m.get("INV_DATE") +"");
					data.setDue_date(m.get("DUE_DATE") +"");
					data.setPay_type(m.get("PAY_TYPE") +"");
					data.setPeriod(m.get("PERIOD") +"");					
					data.setCollection_unit(m.get("COLLECTION_UNIT") +"");
					data.setCollection_code(m.get("COLLECTION_CODE") +"");
			
				paymentSapEpisReverseRepository.save(data);
				//System.out.println("##########################################SAVE paymentSapEpisReverseRepository SUCCESS############################################################");
				}
			}
			
		}
	}
	
	public static String getSystemNewline(){
	    String eol = null;
	    String os = System.getProperty("os.name").toLowerCase();
	    if(os.contains("mac")){
	        int v = Integer.parseInt(System.getProperty("os.version"));
	        eol = (v <= 9 ? "\r" : "\n");
	    }
	    if(os.contains("nix"))
	        eol = "\n";
	    if(os.contains("win"))
	        eol = "\r\n";
	    if(os.contains("linux"))
	        eol = "\r\n";
	    
	    
	    return eol;
	}
}



package th.net.cat.epis.controller.report;

import static th.net.cat.epis.util.AppConstants.AFTERNOON_WORKING_PERIOD;
import static th.net.cat.epis.util.AppConstants.FULL_WORKING_PERIOD;
import static th.net.cat.epis.util.AppConstants.MORNING_WORKING_PERIOD;
import java.text.ParseException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDateTime;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.controller.management.MasterDataController;
import th.net.cat.epis.dto.*;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.ServiceRepository;
import th.net.cat.epis.repo.ShopRepository;
import th.net.cat.epis.service.ReportService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;

@Controller
public class ReportController {

	@Autowired OfficerRepository officerRepository;
	@Autowired ShopRepository shopRepository;
	@Autowired ServiceRepository serviceRepo;
	@Autowired ReportService reportService;
	
	String payTypeName = "";
	final Map<String, String> invoiceMap = new HashMap<String, String>();
	final Map<String, String> paymentMap = new HashMap<String, String>();
	final Map<String, String> documentMap = new HashMap<String, String>();
	final Map<String, String> subPaymentMap = new HashMap<String, String>();
	
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@ResponseBody
	@RequestMapping(value="/getDailyPaymentReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getDailyPaymentReportJSON(HttpServletRequest request,
			@RequestParam(value="printPdf", required=true) String printPdf, 
			@RequestParam(value="startDate", required=true) String searchDate,
			@RequestParam(value="endDate", required=true) String searchEndDate,
			@RequestParam(value="accountID", required=true) String searchAccountID,
			@RequestParam(value="vatRate", required=true) String searchVatRate,
			@RequestParam(value="shop", required=true) String searchShop,
			@RequestParam(value="officer", required=true) String searchOfficer,
			@RequestParam(value="soureType", required=true) String searchSoureType,
			@RequestParam(value="serviceType", required=true) String serviceType
			) throws Exception {

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("endDate = "+ searchEndDate);
		System.out.println("accountID = "+ searchAccountID);
		System.out.println("vatRate = "+ searchVatRate);
		System.out.println("shop = "+ searchShop);
		System.out.println("officer = "+ searchOfficer);
		System.out.println("soureType = "+ searchSoureType);
		System.out.println("serviceType = "+ serviceType);
		
		/*final Map<String, String> invoiceMap = new HashMap<String, String>();
		final Map<String, String> paymentMap = new HashMap<String, String>();
		final Map<String, String> documentMap = new HashMap<String, String>();
		final Map<String, String> subPaymentMap = new HashMap<String, String>();*/
		final Map<String, String> idMap = new HashMap<String, String>();

		StringBuilder whereBuilder = new StringBuilder();
		if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchEndDate)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
		}
		if(!"all".equals(searchOfficer)) {
			whereBuilder.append(" AND cp.updateuser = '" + searchOfficer +"'");
		}
		// 20170828 pict 
		if(!"all".equals(searchShop)) {
			whereBuilder.append(" AND ms.shopno = '"+ searchShop +"'");
		}
		// 20170828 end pict 
		if("all".equals(searchSoureType)) {
			if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
				whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_AGENT+"') "
						+ "AND CR.receiptno not like 'EPO%'");
			} else if(AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(serviceType)) {
				whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"') ");
			} else {
				whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
			}
		}
		if(!"all".equals(searchSoureType) && searchSoureType != null) {
			if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
				whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_AGENT+"') "
						+ "AND cr.ref1 = '"+searchSoureType+"' AND CR.receiptno not like 'EPO%'");
			}else{
			whereBuilder.append(" AND cp.paymenttype = '"+searchSoureType+"' ");
			}	
		}
		if((!"all".equals(searchVatRate) && searchVatRate!=null)){
			whereBuilder.append(" AND cr.vatrate = '"+searchVatRate+"' ");
		}
		if(AppConstants.PAYMENT_TYPE_FOREIGN.equalsIgnoreCase(serviceType)) {
			whereBuilder.append(" AND ti.CURRENCYCODE <> '12' AND ti.CURRENCYCODE IS NOT NULL ");
		}
		
		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		
		// picht 20170920
		queryBuilder.append(" SELECT DISTINCT cp.paymentid, cp.paymenttype, cr.receiptno, cr.accountno, cr.receiptname, sap.TB_CODETEXT as collectionunit , ti.invoiceno, ti.charge, ti.vat, ti.totalcharge, cr.attributes, cr.charge, cr.vat, cr.totalcharge, cr.vatrate, cr.updateuser, ti.status, cr.discount, (select MESSAGE from MASCHANGERATE m where ti.CURRENCYCODE = m.CURRENCYCODE) as currency, cr.VATRATE, (select m.RATEUNIT from MASCHANGERATE m where ti.CURRENCYCODE = m.CURRENCYCODE) as currencyRate, ti.CURRENCYCODE, cr.receiptid  ");
		queryBuilder.append(" ,MA.AGENT_NAME,MA.COMPANY_NAME,TS.REF1,TS.REF2,MA.TAXID, MO.OFFICERGIVENNAME ||' ' || MO.OFFICERFAMILYNAME AS USERNAME ,pim.service_type as SERVICETYPE ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
		queryBuilder.append(" LEFT JOIN tmpaccount ta ON ta.paymentid = cp.paymentid ");
		queryBuilder.append(" LEFT JOIN tmpinvoice ti ON ti.paymentid = cp.paymentid and ti.RECEIPTID = cr.RECEIPTID");
		queryBuilder.append(" LEFT JOIN MAS_AGENT MA ON MA.AGENT_CODE = CR.REF1 ");
		queryBuilder.append(" LEFT JOIN tmpinvoiceservice ts ON ts.receiptid = cr.receiptid ");
		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
		queryBuilder.append(" LEFT JOIN SAP_REVENUE_DEPT@CATPCU1_DEV sap ON substr(cp.collectionunit,1,1)||'000' = sap.TB_REGION_CODE  ");
		queryBuilder.append(" LEFT JOIN MASOFFICER MO ON CP.OFFICERID = MO.OFFICERID ");
		queryBuilder.append(" LEFT JOIN PAYMENT_MANUAL pm ON pm.payment_id = cr.paymentid  ");
		queryBuilder.append(" LEFT JOIN PAYMENT_INVOICE_MANUAL pim ON pim.manual_id = pm.manual_id  ");
		queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
		queryBuilder.append(whereBuilder.toString());
		queryBuilder.append(" ORDER BY collectionunit,cr.receiptno ");
		System.out.println("getDailyPaymentReport : " + queryBuilder.toString());
		//end picht 20170920
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setPaymentId(row.getString(1));
				reportPayment.setPaymentType(row.getString(2));
				reportPayment.setReceiptId(row.getLong(23));
				reportPayment.setReceiptNo(row.getString(3));
				reportPayment.setAccountNo(row.getString(4));
				reportPayment.setReceiptName(row.getString(5));
				reportPayment.setCollectionUnit(row.getString(6));
				reportPayment.setInvoiceNo(row.getString(7));
				reportPayment.setCharge((row.getString(8) == null)?row.getString(12): row.getString(8));
				reportPayment.setVat((row.getString(9) == null)? row.getString(13): row.getString(9));
				//reportPayment.setVat(row.getString(9));//by NSD กรณีเงินรายได้อื่นไม่มีภาษี
				reportPayment.setAttributes(row.getString(11));
				reportPayment.setVatRate(row.getString(15));
				reportPayment.setPaymentUser(row.getString(16));
                String sumCharge = (row.getString(10) == null)?row.getString(14): row.getString(10);
                reportPayment.setTotalCharge((row.getString(10) == null)?row.getString(14): row.getString(10));
				idMap.put(row.getString(7), row.getString(1));
			
				String a = row.getString("SERVICETYPE")+"";
				
				if(null!=reportPayment.getInvoiceNo() && !a.equals("MANUAL")){//by NSD 19-02-2017 there is no invoice case topup
					reportPayment.setStatus(null!=row.getString(17)?(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : "-"):"-");
				}else if(a.equals("MANUAL")) {
					reportPayment.setStatus(row.getString("SERVICETYPE"));
				}else{
					reportPayment.setStatus(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : "-");
				
				}
				reportPayment.setCurrencyRate(row.getString(21));
				reportPayment.setDiscount(row.getString(18));
				reportPayment.setMsgForeign(row.getString(19));
				reportPayment.setAgentCode(row.getString(24));
				reportPayment.setAgentName(row.getString(25));
				reportPayment.setRef1(row.getString(26));
				reportPayment.setRef2(row.getString(27));
				reportPayment.setTaxId(row.getString(28));
				reportPayment.setPaymentUser(row.getString(16)+" "+row.getString(29));
				


                String currencyCode = row.getString(22)!=null?row.getString(22):"12";
                BigDecimal vatRate = new BigDecimal(row.getString(20)!=null?row.getString(20): "0");
                BigDecimal currencyRate = new BigDecimal(row.getString(21)!=null?row.getString(21):"0");
                BigDecimal hundPer = new BigDecimal("100");
                BigDecimal hundPerVat = null!=vatRate?hundPer.add(vatRate):hundPer.add(BigDecimal.ZERO);

                // Case Foreign
                if (currencyCode!=null) {
                    if (!currencyCode.equalsIgnoreCase("12") /*|| !row.getString(19).equalsIgnoreCase("THB")*/) {
                        //totalCharge
                        BigDecimal totalCharge = new BigDecimal(reportPayment.getTotalCharge());
                        totalCharge = totalCharge.multiply(currencyRate);
                        reportPayment.setTotalCharge(totalCharge.toString());
                        //charge
                        BigDecimal charge = totalCharge.multiply(hundPer).divide(hundPerVat, 2, BigDecimal.ROUND_HALF_UP);
                        reportPayment.setCharge(charge.toString());
                        //vat
                        BigDecimal vat = totalCharge.multiply(vatRate).divide(hundPerVat, 2, BigDecimal.ROUND_HALF_UP);
                        reportPayment.setVat(vat.toString());
                        reportPayment.setTotalChargeForeign((row.getString(19)!=null?row.getString(19)+" ":"")+sumCharge);
                    }
                }


				payTypeName = convertPayTypeName(reportPayment.getPaymentType());
				reportPayment.setPayTypeName(payTypeName);
				
				reportPaymentDTO.addData(reportPayment);
			}
		});
		String tmpPaymentId = "";
		List<ReportPayment> reportPaymentOrderType = new ArrayList<ReportPayment>();
		
		prepareData(reportPaymentDTO, tmpPaymentId, reportPaymentOrderType);
		
		reportPaymentDTO.setData(reportPaymentOrderType);
		if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printDailyPaymentReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}
	
	

	@ResponseBody
	@RequestMapping(value="/getDailyPaymentDeductReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getDailyPaymentDeductReportJSON(HttpServletRequest request,
			@RequestParam(value="printXls", required=true) String printXls, 
			@RequestParam(value="sdate", required=true) String searchStartDate, 
			@RequestParam(value="edate", required=true) String searchEndDate,
			@RequestParam(value="period", required=false) String searchDatePeriod
			) throws Exception {

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT REC.RECEIPTNAME, TDD.DEDUCTIONNO, TDD.DEDUCTIONTYPE, TDD.AMOUNT, REC.REMARK , TDD.DEDUCTIONID ");
		queryBuilder.append("FROM CORRECEIPT REC ");
		queryBuilder.append("INNER JOIN CORPAYMENT PAY ON PAY.PAYMENTID = REC.PAYMENTID ");
		queryBuilder.append("INNER JOIN TRSDEDUCTION TDD ON TDD.PAYMENTID = PAY.PAYMENTID ");
		//queryBuilder.append("WHERE TRUNC(REC.RECEIPTDTTM) = TO_DATE('"+ searchDate +"', 'DD-MM-YYYY') ");
		queryBuilder.append("WHERE TRUNC(REC.UPDATEDTTM) BETWEEN TO_DATE('"+ searchStartDate +"', 'DD-MM-YYYY') and TO_DATE('"+ searchEndDate +"', 'DD-MM-YYYY') ");
		queryBuilder.append("AND REC.ATTRIBUTES not like '%R%' ");
		queryBuilder.append("AND TDD.AMOUNT IS NOT NULL ");
		queryBuilder.append("AND TDD.AMOUNT <> 0 ");
		queryBuilder.append("AND TDD.DEDUCTIONTYPE IN ('"+AppConstants.DEDUCT_METHOD_3TREDECIM+"','"+AppConstants.DEDUCT_METHOD_69BIS+"','"+AppConstants.DEDUCT_METHOD_69TRE+"') ");
		queryBuilder.append("GROUP BY REC.RECEIPTNAME, TDD.DEDUCTIONID , TDD.DEDUCTIONNO, TDD.DEDUCTIONTYPE, TDD.AMOUNT, REC.REMARK ");
		queryBuilder.append("ORDER BY TDD.DEDUCTIONNO ");

		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptName(row.getString(1));
				reportPayment.setDeductionNo(row.getString(2));
				reportPayment.setDeductionType(row.getString(3));
				reportPayment.setAmount3tredecim(AppConstants.DEDUCT_METHOD_3TREDECIM.equals(row.getString(3))?row.getString(4): "0");
				reportPayment.setAmount69bis(AppConstants.DEDUCT_METHOD_69BIS.equals(row.getString(3))?row.getString(4): "0");
				reportPayment.setAmount69tre(AppConstants.DEDUCT_METHOD_69TRE.equals(row.getString(3))?row.getString(4): "0");
				reportPayment.setRemark(row.getString(5));
				reportPayment.setDeductionId(row.getString(6));
				reportPaymentDTO.addData(reportPayment);
			}
		});
		if(AppUtil.isStringHasValue(printXls)) {
			request.setAttribute("printDailyPaymentDeductReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}

		// Report : ChaquePaymentReport
		//picht 08/09/2017
		@ResponseBody
		@RequestMapping(value="/getChequePaymentReport.json", method=RequestMethod.GET)
		public ReportPaymentDTO getChequePaymentReportJSON(HttpServletRequest request,
				@RequestParam(value="printPdf", required=true) String printPdf, 
				@RequestParam(value="startDate", required=true) String searchStartDate,
				@RequestParam(value="endDate", required=true) String searchEndDate,
				@RequestParam(value="shop", required=true) String searchShop,
				@RequestParam(value="officer", required=true) String searchOfficer
				//@RequestParam(value="soureType", required=true) String searchSoureType
				) throws Exception {
				
			System.out.println("printPdf = "+ printPdf);
			System.out.println("startDate = "+ searchStartDate);
			System.out.println("endDate = "+ searchEndDate);
			System.out.println("shop = "+ searchShop);
			System.out.println("officer = "+ searchOfficer);
			//System.out.println("soureType = "+ searchSoureType);

			StringBuilder whereBuilder = new StringBuilder();
		
			if(!"all".equals(searchOfficer)) {
				whereBuilder.append(" AND tc.UPDATEUSER = '"+ searchOfficer+"'");
			}
			
			if(!"-1".equals(searchShop)) {
				whereBuilder.append(" AND ms.shopno = '"+ searchShop+"'");
			}
//			if(!"all".equals(searchSoureType)) {
//				whereBuilder.append(" AND cp.paymenttype = '"+searchSoureType+"' ");
//			}
			
			
			final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
			StringBuilder queryBuilder = new StringBuilder(); 
			queryBuilder.append(" SELECT TO_CHAR(TC.UPDATEDTTM, 'dd/mm/yyyy') AS PAYDATE ,TO_CHAR(TC.UPDATEDTTM, 'HH24:MI:ss') AS PAYTIME ");
			queryBuilder.append(" ,CR.RECEIPTNO , CR.RECEIPTNAME, TC.AMOUNT,TC.CHEQUENO,TC.PUBLISHERID,TC.PUBLISHER,CR.REMARK ,CP.PAYMENTTYPE,TC.UPDATEUSER ");
			queryBuilder.append(" FROM TRSMETHOD TM ");
			queryBuilder.append(" LEFT JOIN TRSCHEQUEREF TC ON TC.PAYMENTID = TM.PAYMENTID ");
			queryBuilder.append(" INNER JOIN CORRECEIPT CR  ON TC.PAYMENTID = CR.PAYMENTID ");
			queryBuilder.append(" INNER JOIN CORPAYMENT CP  ON TC.PAYMENTID = CP.PAYMENTID ");
			queryBuilder.append(" LEFT JOIN MASSHOP MS ON MS.SHOPID = CP.SHOPID ");
			queryBuilder.append(" WHERE TM.CODE = 'CH' ");
			queryBuilder.append(" AND CR.RECEIPTDTTM BETWEEN TO_TIMESTAMP('"+ searchStartDate + "', 'DD-MM-YYYY HH24:MI') ");
			queryBuilder.append(" AND TO_TIMESTAMP('"+ searchEndDate +"', 'DD-MM-YYYY HH24:MI')  ");
			queryBuilder.append( whereBuilder.toString() );
			queryBuilder.append(" ORDER BY PAYDATE ASC,PAYTIME ASC ");
			
			System.out.println("getChequePaymentReport : " + queryBuilder.toString());

			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					ReportPayment reportPayment = new ReportPayment();
					reportPayment.setPaymentDate(row.getString(1));
					reportPayment.setPaymentTime(row.getString(2));
					reportPayment.setReceiptNo(row.getString(3));
					reportPayment.setReceiptName(row.getString(4));
					reportPayment.setSumAmount(row.getDouble(5));
					reportPayment.setChequeno(row.getString(6));
					reportPayment.setPublisherid(row.getString(7));
					reportPayment.setPublisher(row.getString(8));
					reportPayment.setReason(row.getString(9));
					reportPayment.setPaymentType(row.getString(10));
					reportPayment.setPaymentUser(row.getString(11));
					reportPaymentDTO.addData(reportPayment);
				}
			});
			
			
		
			if(AppUtil.isStringHasValue(printPdf)) {
				request.setAttribute("printChaquePaymentReport", reportPaymentDTO);
			}
			return reportPaymentDTO;
		}
		
		//end picht 2017/09/08
		
	@ResponseBody
	@RequestMapping(value="/getDailyDeductPaymentReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getDailyDeductPaymentReportJSON(HttpServletRequest request,
			@RequestParam(value="printPdf", required=true) String printPdf, 
			@RequestParam(value="startDate", required=true) String searchDate,
			@RequestParam(value="endDate", required=true) String searchEndDate,
			@RequestParam(value="shop", required=true) String searchShop,
			@RequestParam(value="officer", required=true) String searchOfficer,
			@RequestParam(value="serviceType", required=true) String serviceType
			) throws Exception {

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("endDate = "+ searchEndDate);
		System.out.println("shop = "+ searchShop);
		System.out.println("officer = "+ searchOfficer);
		System.out.println("serviceType = "+ serviceType);
		
		StringBuilder whereBuilder = new StringBuilder();
		if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchEndDate)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
		}
		if (AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
			whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		} else {
			whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		}
		if(!"all".equals(searchOfficer)) {
			whereBuilder.append(" AND cp.updateuser = '" + searchOfficer +"'");
		
		}
		if(!"-1".equals(searchShop)) {
			whereBuilder.append(" AND ms.shopno = '"+ searchShop+"'");
		}
		whereBuilder.append(" AND td.DEDUCTIONTYPE IN ('"+AppConstants.DEDUCT_METHOD_3TREDECIM+"','"+AppConstants.DEDUCT_METHOD_69BIS+"','"+AppConstants.DEDUCT_METHOD_69TRE+"') ");

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT cr.receiptname, td.deductionno, td.deductiontype, td.amount, cr.remark, cr.attributes ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
		queryBuilder.append(" INNER JOIN trsdeduction td ON td.paymentid = cp.paymentid ");
		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
		queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
		queryBuilder.append(whereBuilder.toString());

		System.out.println("getDailyDeductPaymentReport : "+ queryBuilder.toString());

		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptName(row.getString(1));
				reportPayment.setDeductionNo(row.getString(2));
				reportPayment.setDeductionType(row.getString(3));
				reportPayment.setAmount3tredecim(AppConstants.DEDUCT_METHOD_3TREDECIM.equals(row.getString(3))?row.getString(4): "0");
				reportPayment.setAmount69bis(AppConstants.DEDUCT_METHOD_69BIS.equals(row.getString(3))?row.getString(4): "0");
				reportPayment.setAmount69tre(AppConstants.DEDUCT_METHOD_69TRE.equals(row.getString(3))?row.getString(4): "0");
				reportPayment.setRemark(row.getString(5));
				reportPayment.setStatus(StringUtils.isNotBlank(row.getString(6))?row.getString(6).indexOf("R") > -1 ? "ยกเลิก" : "-":"-");
				reportPaymentDTO.addData(reportPayment);
			}
		});
		if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printDailyDeductPaymentReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}
	
	// pichanan 20170815

	@ResponseBody
	@RequestMapping(value = "/getMonthlyDeductPaymentReport.json", method = RequestMethod.GET)
	public ReportPaymentDTO getMonthlyDeductPaymentReportJSON(HttpServletRequest request,
			@RequestParam(value = "printPdf", required = true) String printPdf,
			@RequestParam(value = "period", required = true) String period,
			@RequestParam(value = "shop", required = true) String searchShop,
			@RequestParam(value = "officer", required = true) String searchOfficer,
			@RequestParam(value = "serviceType", required = true) String serviceType) throws Exception {

		System.out.println("printPdf = " + printPdf);
		System.out.println("period = " + period);
		System.out.println("shop = " + searchShop);
		System.out.println("officer = " + searchOfficer);
		System.out.println("serviceType = " + serviceType);

		StringBuilder whereBuilder = new StringBuilder();
		if (!StringUtil.isBlank(period) && !StringUtil.isBlank(period)) {
			whereBuilder.append(" AND TO_CHAR(cr.receiptdttm, 'MM-YYYY') = '"+period+"' " );
		}
		if (AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
			whereBuilder.append(" AND cp.paymenttype IN ('" + AppConstants.PAYMENT_TYPE_MNP + "','"
					+ AppConstants.PAYMENT_TYPE_AGENT + "') ");
		} else {
			whereBuilder.append(" AND cp.paymenttype NOT IN ('" + AppConstants.PAYMENT_TYPE_MNP + "','"
					+ AppConstants.PAYMENT_TYPE_AGENT + "') ");
		}
//		if (!"-1".equals(searchOfficer)) {
//			whereBuilder.append(" AND cp.officerid = " + searchOfficer);
//		}

		if (!"-1".equals(searchShop)) {
			whereBuilder.append(" AND ms.shopno = '" + searchShop+"'");
		}
		whereBuilder.append(" AND td.DEDUCTIONTYPE IN ('" + AppConstants.DEDUCT_METHOD_3TREDECIM + "','"
				+ AppConstants.DEDUCT_METHOD_69BIS + "','" + AppConstants.DEDUCT_METHOD_69TRE + "') ");

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(
				" select TO_CHAR(cr.receiptdttm, 'YYYY-MM-DD') AS receiptdttm,COUNT(CR.RECEIPTNO) AS CNT_RECEIPTNO ,td.DEDUCTIONTYPE ");
		queryBuilder.append(" , sum(TD.AMOUNT) AS AMOUNT  ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
		queryBuilder.append(" INNER JOIN trsdeduction td ON td.paymentid = cp.paymentid ");
		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
		queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
		queryBuilder.append(whereBuilder.toString());
		queryBuilder.append(" GROUP BY TO_CHAR(cr.receiptdttm, 'YYYY-MM-DD'),DEDUCTIONTYPE ");
		queryBuilder.append(" ORDER BY TO_CHAR(cr.receiptdttm, 'YYYY-MM-DD') ");
		

		System.out.println("getMonthlyDeductPaymentReport : "+ queryBuilder.toString());

		
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptDate(row.getString(1));
				reportPayment.setCntReceipt(row.getInt(2));
				reportPayment.setDeductionType(row.getString(3)); 
				
				reportPayment.setAmount3tredecim(
						AppConstants.DEDUCT_METHOD_3TREDECIM.equals(row.getString(3)) ? row.getString(4) : "0");
				reportPayment.setAmount69bis(
						AppConstants.DEDUCT_METHOD_69BIS.equals(row.getString(3)) ? row.getString(4) : "0");
				reportPayment.setAmount69tre(
						AppConstants.DEDUCT_METHOD_69TRE.equals(row.getString(3)) ? row.getString(4) : "0");
 
				reportPaymentDTO.addData(reportPayment);
			}
		});
		if (AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printMonthlyDeductPaymentReport", reportPaymentDTO);
		}
		
		//Remove duplicate
		Map<String,ReportPayment> dataMap = new TreeMap<String,ReportPayment>();
		List<ReportPayment> dataTmpList = reportPaymentDTO.getData();
		for(int i = 0 ; i < dataTmpList.size() ; i++){
			ReportPayment rpTmp =dataTmpList.get(i);
			ReportPayment rp = dataMap.get(rpTmp.getReceiptDate());
			if(rp != null){
				if(AppConstants.DEDUCT_METHOD_3TREDECIM.equals(rpTmp.getDeductionType())){
					rp.setCntReceipt(rp.getCntReceipt()+rpTmp.getCntReceipt());
					rp.setAmount3tredecim(rpTmp.getAmount3tredecim());
				}else 
					if(AppConstants.DEDUCT_METHOD_69BIS.equals(rpTmp.getDeductionType())){
					rp.setCntReceipt(rp.getCntReceipt()+rpTmp.getCntReceipt());
					rp.setAmount69bis(rpTmp.getAmount69bis());
				}else if(AppConstants.DEDUCT_METHOD_69TRE.equals(rpTmp.getDeductionType())){
					rp.setAmount69tre(rpTmp.getAmount69tre());
					rp.setCntReceipt(rp.getCntReceipt()+rpTmp.getCntReceipt());
				}
			}else{
				dataMap.put(rpTmp.getReceiptDate(), rpTmp);
			}
		}
		
		//Convert to list.
		List<ReportPayment> dataList = new ArrayList<ReportPayment>();
		for (Map.Entry<String, ReportPayment> entry : dataMap.entrySet()){
			dataList.add(entry.getValue());
		   // System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		
		reportPaymentDTO.setData(dataList);
		return reportPaymentDTO;
	}
	
	@ResponseBody
	@RequestMapping(value="/getSummaryDailyPaymentReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getSummaryDailyPaymentReportJSON(HttpServletRequest request,
			@RequestParam(value="printPdf", required=true) String printPdf, 
			@RequestParam(value="startDate", required=true) String searchDate,
			@RequestParam(value="endDate", required=true) String searchEndDate,
			@RequestParam(value="payCase", required=true) String searchPayCase,
			@RequestParam(value="shop", required=true) String searchShop,
			@RequestParam(value="officer", required=true) String searchOfficer,
			@RequestParam(value="serviceType", required=true) String serviceType
			) throws Exception {

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("endDate = "+ searchEndDate);
		System.out.println("payCase = "+ searchPayCase);
		System.out.println("shop = "+ searchShop);
		System.out.println("officer = "+ searchOfficer);
		System.out.println("serviceType = "+ serviceType);
		
		StringBuilder whereBuilder = new StringBuilder();
		if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchEndDate)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
		}
		if (StringUtils.isNotBlank(serviceType) && AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
			whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		} else {
			whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		}
		if(!"-1".equals(searchOfficer)) {
			whereBuilder.append(" AND cp.UPDATEUSER = '"+ searchOfficer+"'");
		}
		
		if(!"-1".equals(searchPayCase)) {
			whereBuilder.append(" AND met.CODE = '"+searchPayCase+"'");
		}
		
		if(!"-1".equals(searchShop)) {
			whereBuilder.append(" AND ms.shopno = "+ " '"+searchShop+"' ");
		}
        whereBuilder.append(" AND cr.ATTRIBUTES NOT LIKE '%R%' ");

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		//queryBuilder.append(" SELECT DISTINCT cr.updateuser, COUNT(*) AS amount, SUM(cr.totalcharge) AS totalcharge ");
//		queryBuilder.append(" SELECT aaa.UPDATEUSER, count(*) amount, sum(aaa.totalcharge) from ( ");//by NSD 24-04-2017
//		queryBuilder.append(" SELECT DISTINCT cr.updateuser, COUNT(*) AS amount, SUM(met.AMOUNT) AS totalcharge, cr.RECEIPTNO ");//by NSD 21-04-2017
//		queryBuilder.append(" FROM correceipt cr ");
//		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
//		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
//		//queryBuilder.append(" LEFT JOIN trsmethod met ON cp.PAYMENTID = met.PAYMENTID ");
//		queryBuilder.append(" INNER JOIN trsmethod met ON cp.PAYMENTID = met.PAYMENTID ");
//		if(!"-1".equals(searchPayCase)) {
//			queryBuilder.append(" AND met.CODE = '"+searchPayCase+"'");
//		}
//		queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
//		queryBuilder.append( whereBuilder.toString() );
//		queryBuilder.append(" GROUP BY cr.updateuser, cr.RECEIPTNO ");
//		queryBuilder.append(" ORDER BY cr.updateuser ASC");
//		queryBuilder.append(" ) aaa GROUP BY aaa.UPDATEUSER ORDER BY aaa.UPDATEUSER asc ");
		
		
		//picht 29082017
		
		queryBuilder.append(" SELECT cr.updateuser,cr.paymentid,DECODE_PAYTYPE(cr.paymentid) as paytype, ");
		queryBuilder.append("  1 AS CNT,cr.totalcharge AS totalcharge ,ms.shopno ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
		queryBuilder.append(" INNER JOIN trsmethod met ON cp.PAYMENTID = met.PAYMENTID ");
		queryBuilder.append(" WHERE cr.paymentid = cp.paymentid  ");
		queryBuilder.append(whereBuilder.toString());
		queryBuilder.append(" GROUP BY cr.updateuser,cr.paymentid,cr.totalcharge, DECODE_PAYTYPE(cr.paymentid), ms.shopno ");
		queryBuilder.append(" order by cr.updateuser desc,cr.paymentid ");
		System.out.println("getSummaryDailyPaymentReport : "+ queryBuilder.toString());
		
		
		
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setPaymentUser(row.getString(1));
			//	reportPayment.setPaymentId(row.getString(2));
				reportPayment.setPayTypeName(row.getString(3));
				reportPayment.setCntReceipt(row.getInt(4));
				reportPayment.setTotalCharge(row.getString(5));
				reportPayment.setShopNo(row.getString(6));
				reportPaymentDTO.addData(reportPayment);
			}
		});
		
		
		//Remove duplicate
				Map<String,ReportPayment> dataMap = new TreeMap<String,ReportPayment>();
				List<ReportPayment> dataTmpList = reportPaymentDTO.getData();
				for(int i = 0 ; i < dataTmpList.size() ; i++){
					ReportPayment rpTmp =dataTmpList.get(i);
					ReportPayment rp = dataMap.get(rpTmp.getPaymentUser() +rpTmp.getPayTypeName());
					
					if(rp != null){
						//System.out.println("1loop : "+ rpTmp.getPaymentUser() +rpTmp.getPayTypeName());
					
						double sumTotalCharge = (Double.parseDouble(rp.getTotalCharge())) + (Double.parseDouble(rpTmp.getTotalCharge()));
						
						rp.setCntReceipt(rp.getCntReceipt()+rpTmp.getCntReceipt());
						rp.setTotalCharge(String.valueOf(sumTotalCharge));
						rp.setReceiptFr(rp.getReceiptNo());
						rp.setReceiptTo(rpTmp.getReceiptNo());
									
					}else{
						//System.out.println("2paymentID : "+ rpTmp.getPaymentUser() + rpTmp.getPayTypeName());

						dataMap.put(rpTmp.getPaymentUser()+rpTmp.getPayTypeName(), rpTmp);
					}
				}
				
				//Convert to list.
				List<ReportPayment> dataList = new ArrayList<ReportPayment>();
				for (Map.Entry<String, ReportPayment> entry : dataMap.entrySet()){
					dataList.add(entry.getValue());
				}
				
				reportPaymentDTO.setData(dataList);
		
				//end picht 29082017
		if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printSummaryDailyPaymenReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}

	@ResponseBody
	@RequestMapping(value="/getDailySalesTaxReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getDailySalesTaxReportJSON(HttpServletRequest request, 
			@RequestParam(value="printPdf", required=true) String printPdf, 
			@RequestParam(value="startDate", required=true) String searchDate,
			@RequestParam(value="endDate", required=true) String searchEndDate,
			@RequestParam(value="type", required=true) String searchType,
			@RequestParam(value="serviceType", required=true) String serviceType
			) throws Exception {

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("endDate = "+ searchEndDate);
		List<String> username = EpContextHolder.getContext().getOwners();
		StringBuilder user = new StringBuilder();
		int i = 0;
		while (i < username.size()) {
			user.append("'" + username.get(i) + "'");
			if(i == (username.size() -1)) {
				break;
			}else {
				user.append(",");
			}
			i++;			
		}
		StringBuilder whereBuilder = new StringBuilder();
		if(!StringUtil.isBlank(searchDate)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
		}
		
		if (StringUtils.isNotBlank(serviceType) && AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
			whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		} else {
			whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		}
		
		if(printPdf=="1"){
			whereBuilder.append(" ORDER BY cr.updateuser, cr.receiptdttm, cr.receiptno ");
		}else{
			whereBuilder.append(" ORDER BY cr.receiptdttm, cr.receiptno ");
		}
		
		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy') AS receiptdttm, cr.receiptno, cr.receiptname, cr.taxid, cr.accountbranch, cr.charge, cr.vat, cr.vatrate, cr.totalcharge, cr.updateuser, cr.attributes ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
		queryBuilder.append(" WHERE cr.receipttype = '"+ searchType +"' AND cr.updateuser IN ("+user+")"); 
		queryBuilder.append(  whereBuilder.toString() );

		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptDate(row.getString(1));
				reportPayment.setReceiptNo(row.getString(2));
				reportPayment.setReceiptName(row.getString(3));
				reportPayment.setTaxId(row.getString(4));
				reportPayment.setBranchNo("00000".equalsIgnoreCase(row.getString(5))?"สำนักงานใหญ่":row.getString(5));
				reportPayment.setCharge(row.getString(6));
				reportPayment.setVat(row.getString(7));
				reportPayment.setVatRate(null!=row.getString(8)?row.getString(8):"");
				reportPayment.setTotalCharge(null!=row.getString(9)?row.getString(9):"");
				reportPayment.setPaymentUser(null!=row.getString(10)?row.getString(10):"");
				reportPayment.setStatus(StringUtils.isNotBlank(row.getString(11))?row.getString(11).indexOf("R") > -1 ? "ยกเลิก" : "-":"-");
				reportPaymentDTO.addData(reportPayment);
			}
		});
		if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printDailySalesTaxReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}

	// Pichanan 20170817
	
	@ResponseBody
	@RequestMapping(value = "/getMonthlySalesTaxReport.json", method = RequestMethod.GET)
	public ReportPaymentDTO getMonthlySalesTaxReportJSON(HttpServletRequest request,
			@RequestParam(value = "printPdf", required = true) String printPdf,
			@RequestParam(value = "period", required = true) String period,
			@RequestParam(value = "shop", required = true) String searchShop,
			@RequestParam(value = "type", required = true) String searchType,
			@RequestParam(value = "officer", required = true) String searchOfficer,
			@RequestParam(value = "serviceType", required = true) String serviceType) throws Exception {

		System.out.println("printPdf = " + printPdf);
		System.out.println("period = " + period);
		System.out.println("shop = " + searchShop);
		System.out.println("serviceType = " + serviceType);
		System.out.println("searchOfficer = " + searchOfficer);
		
		StringBuilder whereBuilder = new StringBuilder();
		if (!StringUtil.isBlank(period) && !StringUtil.isBlank(period)) {
			whereBuilder.append(" AND TO_CHAR(cr.receiptdttm, 'MM-YYYY') = '"+period+"' " );
		}

		if (StringUtils.isNotBlank(serviceType) && AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
			whereBuilder.append(" AND cp.paymenttype IN ('" + AppConstants.PAYMENT_TYPE_MNP + "','"
					+ AppConstants.PAYMENT_TYPE_AGENT + "') ");
		} else {
			whereBuilder.append(" AND cp.paymenttype NOT IN ('" + AppConstants.PAYMENT_TYPE_MNP + "','"
					+ AppConstants.PAYMENT_TYPE_AGENT + "') ");
		}
		
		if (!"all".equals(searchOfficer)) {
		whereBuilder.append(" AND cp.UPDATEUSER = '" + searchOfficer+"'");
	}
		
		if (!"-1".equals(searchShop)) {
			whereBuilder.append(" AND ms.shopno = '" + searchShop+"'");
		}
		
		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(
				" SELECT TO_CHAR(cr.receiptdttm, 'DD/MM/YYYY') AS receiptdttm ,MP.POSNAME , count(cr.RECEIPTNO) AS CNT_RECEIPTNO , cr.RECEIPTNO, cr.CHARGE, cr.vat ,cr.TOTALCHARGE");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid   ");
		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
		queryBuilder.append(" LEFT JOIN maspos mp ON mp.shopid = cp.shopid  and mp.posno  = substr(cr.RECEIPTNO,8,2) ");
		queryBuilder.append(" WHERE cr.receipttype = '" + searchType + "' and cr.attributes not like '%R%' ");
		queryBuilder.append(whereBuilder.toString());
		queryBuilder.append(" GROUP BY TO_CHAR(cr.receiptdttm, 'DD/MM/YYYY'),MP.POSNAME ,cr.RECEIPTNO, cr.CHARGE, cr.vat ,cr.TOTALCHARGE ");
		queryBuilder.append(" ORDER BY TO_CHAR(cr.receiptdttm, 'DD/MM/YYYY') ,MP.POSNAME,cr.receiptno ");
		System.out.println("getMonthlySalesTaxReport : "+ queryBuilder.toString());



		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptDate(row.getString(1));
				reportPayment.setPosNo(row.getString(2));
				reportPayment.setCntReceipt(row.getInt(3));
				reportPayment.setReceiptNo(row.getString(4));
				reportPayment.setReceiptFr(row.getString(4));
				reportPayment.setReceiptTo(row.getString(4));
				reportPayment.setCharge(row.getString(5));
				reportPayment.setVat(row.getString(6));
				reportPayment.setTotalCharge(row.getString(7));
				reportPaymentDTO.addData(reportPayment);
			}
		});
		
		

		//Remove duplicate
		Map<String,ReportPayment> dataMap = new TreeMap<String,ReportPayment>();
		List<ReportPayment> dataTmpList = reportPaymentDTO.getData();
		for(int i = 0 ; i < dataTmpList.size() ; i++){
			ReportPayment rpTmp =dataTmpList.get(i);
			ReportPayment rp = dataMap.get(rpTmp.getReceiptDate()+rpTmp.getPosNo());

			//System.out.println("test : "+ rpTmp.getReceiptDate()+rpTmp.getPosNo());\
			
			if(rp != null){
				//System.out.println("1 : "+ rpTmp.getReceiptDate()+rpTmp.getPosNo());
				//System.out.println("before: "+ rp.getVat() +" after : "+rpTmp.getVat());
				double sumCharge = (Double.parseDouble(rp.getCharge())) + (Double.parseDouble(rpTmp.getCharge()));
				double sumVat = (Double.parseDouble(rp.getVat())) + (Double.parseDouble(rpTmp.getVat()));
				double sumTotalCharge = (Double.parseDouble(rp.getTotalCharge())) + (Double.parseDouble(rpTmp.getTotalCharge()));

				//System.out.println("i : "+ i +" rcpt no : "+rp.getReceiptNo() + " rcpt tmp : " + rpTmp.getReceiptNo());
				
				rp.setCntReceipt(rp.getCntReceipt()+rpTmp.getCntReceipt());
				rp.setCharge(String.valueOf(sumCharge));
				rp.setVat(String.valueOf(sumVat));
				rp.setTotalCharge(String.valueOf(sumTotalCharge));
				rp.setReceiptFr(rp.getReceiptNo());
				rp.setReceiptTo(rpTmp.getReceiptNo());
							
			}else{
				
				dataMap.put(rpTmp.getReceiptDate()+rpTmp.getPosNo(), rpTmp);
			}
		}
		
		//Convert to list.
		List<ReportPayment> dataList = new ArrayList<ReportPayment>();
		for (Map.Entry<String, ReportPayment> entry : dataMap.entrySet()){
			dataList.add(entry.getValue());
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		
		reportPaymentDTO.setData(dataList);
		
		if (AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printMonthlySalesTaxReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}
	
	public static final String convertDateString(String str) {
		return str.replaceAll("([0-9]{2})-([0-9]{2})-([0-9]{4})", "$1/$2/$3");
	}
	
	// Report : summary close-endofday-report.
		//picht 04/09/2017
		@ResponseBody
		@RequestMapping(value="/getSumCloseEndOfDayReport.json", method=RequestMethod.GET)
		public ReportPaymentDTO getSumCloseEndOfDayReportJSON(HttpServletRequest request,
				@RequestParam(value="printPdf", required=true) String printPdf, 
				@RequestParam(value="startDate", required=true) String searchDate,
				@RequestParam(value="shop", required=true) String searchShop,
				@RequestParam(value="officer", required=true) String searchOfficer,
				@RequestParam(value="soureType", required=true) String searchSoureType,
				@RequestParam(value="posList", required=true) String searchPosNo,
				@RequestParam(value="serviceType", required=true) String searchServiceType

				) throws Exception {
				

			System.out.println("printPdf = "+ printPdf);
			System.out.println("startDate = "+ searchDate);
			System.out.println("shop = "+ searchShop);
			System.out.println("officer = "+ searchOfficer);
			System.out.println("soureType = "+ searchSoureType);
			System.out.println("searchPosNo = "+ searchPosNo);
			System.out.println("searchServiceType = "+ searchServiceType);


			
			StringBuilder whereBuilder = new StringBuilder();
		
			if(!"all".equals(searchOfficer)) {
				whereBuilder.append(" AND sh.USERNAME = '"+ searchOfficer+"'");
			}
			
			if(!"-1".equals(searchShop)) {
				whereBuilder.append(" AND ms.shopno = '"+ searchShop+"'");
			}
			if(!"all".equals(searchSoureType)) {
				whereBuilder.append(" AND cp.paymenttype = '"+searchSoureType+"' ");
			}
			if(!"all".equals(searchPosNo)) {
				whereBuilder.append(" AND cr.posno = '"+searchPosNo+"' ");
			}
			
			final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
			StringBuilder queryBuilder = new StringBuilder(); 
			queryBuilder.append(" SELECT CP.PAYMENTTYPE, CASE  WHEN CR.ATTRIBUTES LIKE '%R'  THEN 'R' ELSE SUBSTR(CR.ATTRIBUTES,1,1) END AS RECEIPTTYPE, ");
			queryBuilder.append(" CASE WHEN CR.ATTRIBUTES LIKE '%R'  THEN CR.TOTALCHARGE ELSE 0 END AS CANCELTOTALCHARGE, ");
			queryBuilder.append(" CASE WHEN CR.ATTRIBUTES NOT LIKE '%R'  THEN CR.TOTALCHARGE  ELSE 0 END AS TOTALCHARGE, ");
			queryBuilder.append(" CASE WHEN CR.ATTRIBUTES LIKE '%R'  THEN 1 ELSE 0 END AS CNTR,   ");
			queryBuilder.append(" CASE WHEN CR.ATTRIBUTES NOT LIKE '%R'  THEN 1  ELSE 0 END AS CNT, ");
			queryBuilder.append(" SH.USERNAME , MO.USERNAME || ':' || MO.OFFICERFAMILYNAME || ' ' ||MO.OFFICERFAMILYNAME  AS FULLNAME ");
			queryBuilder.append(" FROM EMP_CLOSING SH ");
			queryBuilder.append(" INNER JOIN CORRECEIPT CR   ON SH.EMP_CLOSING_ID = CR.EMP_CLOSING_ID ");
			queryBuilder.append(" INNER JOIN CORPAYMENT CP ON  CP.PAYMENTID = CR.PAYMENTID ");
			queryBuilder.append(" INNER JOIN MASSHOP MS ON MS.SHOPID = CP.SHOPID  ");
			queryBuilder.append(" INNER JOIN MASOFFICER MO ON  SH.USERNAME  = MO.USERNAME ");
			queryBuilder.append(" WHERE  TO_CHAR(SH.CLOSING_DATE,'dd-mm-yyyy') = '"+searchDate+"'");
			queryBuilder.append( whereBuilder.toString() );
			queryBuilder.append(" ORDER BY CP.PAYMENTTYPE, RECEIPTTYPE ");
			
			System.out.println("getSumCloseEndOfDayReport : " + queryBuilder.toString());

			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					ReportPayment reportPayment = new ReportPayment();
					reportPayment.setPaymentType(row.getString(1));
					reportPayment.setReceiptType(row.getString(2));
					reportPayment.setSumCancelAmount(row.getDouble(3));
					reportPayment.setSumAmount(row.getDouble(4));
					reportPayment.setSumCancelReceipt(row.getInt(5));
					reportPayment.setSumReceipt(row.getInt(6));
					reportPayment.setSumTotalReceipt(row.getInt(6));
					reportPayment.setSumTotalAmount(row.getDouble(4));
					reportPayment.setPaymentUser(row.getString(8));
					reportPaymentDTO.addData(reportPayment);
				}
			});

			//Remove duplicate
					Map<String,ReportPayment> dataMap = new TreeMap<String,ReportPayment>();
					List<ReportPayment> dataTmpList = reportPaymentDTO.getData();
					for(int i = 0 ; i < dataTmpList.size() ; i++){
						ReportPayment rpTmp =dataTmpList.get(i);
						ReportPayment rp = dataMap.get(rpTmp.getPaymentType()+rpTmp.getPaymentUser());

						//System.out.println("test : "+ rpTmp.getPaymentType()+rpTmp.getReceiptType());
						
						if(rp != null){
							//System.out.println("1");

							if("R".equals(rpTmp.getReceiptType())){	//Cancel	
								int cntR =  rp.getSumCancelReceipt() + rpTmp.getSumCancelReceipt();
								
								rp.setSumCancelReceipt(cntR);
								rp.setSumCancelAmount(rp.getSumCancelAmount()+rpTmp.getSumCancelAmount());

							}else{
							
							//System.out.println("before: "+ rp.getSumAmount() +" after : "+rpTmp.getSumAmount());
							
							double sumAmt = rp.getSumAmount() + rpTmp.getSumAmount();
							int cnt =  rp.getSumReceipt() + rpTmp.getSumReceipt();

							rp.setSumReceipt(cnt);
							rp.setSumAmount(sumAmt);
							}
						
							rp.setSumTotalReceipt(rp.getSumReceipt());
							rp.setSumTotalAmount(rp.getSumAmount());
										
						}else{
							dataMap.put(rpTmp.getPaymentType()+rpTmp.getPaymentUser(), rpTmp);
						}
					}
					
					//Convert to list.
					List<ReportPayment> dataList = new ArrayList<ReportPayment>();
					for (Map.Entry<String, ReportPayment> entry : dataMap.entrySet()){
						dataList.add(entry.getValue());
					    //System.out.println(entry.getKey() + "/" + entry.getValue());
					}
					
					reportPaymentDTO.setData(dataList);
				
		
			if(AppUtil.isStringHasValue(printPdf)) {
				request.setAttribute("printSumCloseEndOfDayReport", reportPaymentDTO);
			}
			return reportPaymentDTO;
		}
		
	// Report : close-endofday-report.
			//picht 04/09/2017
			@ResponseBody
			@RequestMapping(value="/getCloseEndOfDayReport.json", method=RequestMethod.GET)
			public ReportPaymentDTO getCloseEndOfDayReportJSON(HttpServletRequest request,
					@RequestParam(value="printPdf", required=true) String printPdf, 
					@RequestParam(value="startDate", required=true) String searchDate,
					@RequestParam(value="endDate", required=true) String endDate,
					@RequestParam(value="shop", required=true) String searchShop,
					@RequestParam(value="officer", required=true) String searchOfficer
					) throws Exception {
					

				System.out.println("printPdf = "+ printPdf);
				System.out.println("startDate = "+ searchDate);			
				System.out.println("endDate = "+ endDate);
				System.out.println("shop = "+ searchShop);
				System.out.println("officer = "+ searchOfficer);


				
				StringBuilder whereBuilder = new StringBuilder();
			
				if(!"all".equals(searchOfficer)) {
					whereBuilder.append(" AND sh.USERNAME = '"+ searchOfficer+"'");
				}
				
				if(!"-1".equals(searchShop)) {
					whereBuilder.append(" AND ms.shopno = '"+ searchShop+"'");
				}
				
				final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
				StringBuilder queryBuilder = new StringBuilder(); 
				queryBuilder.append(" SELECT TO_CHAR(CR.RECEIPTDTTM, 'dd/MM/yyyy') AS RECEIPTDTTM,MS.SHOPNO,MS.SHOPNAME,MP.POSNAME, SH.USERNAME , MO.USERNAME || ' : ' || MO.OFFICERFAMILYNAME || ' ' ||MO.OFFICERFAMILYNAME  AS FULLNAME  ");
				queryBuilder.append(" FROM EMP_CLOSING SH   ");
				queryBuilder.append(" INNER JOIN CORRECEIPT CR ON SH.EMP_CLOSING_ID = CR.EMP_CLOSING_ID ");
				queryBuilder.append(" INNER JOIN CORPAYMENT CP ON  CP.PAYMENTID = CR.PAYMENTID ");
				queryBuilder.append(" INNER JOIN MASOFFICER MO ON  SH.USERNAME  = MO.USERNAME  ");
				queryBuilder.append(" INNER JOIN MASPOS MP ON MP.POSID = CR.POSID ");
				queryBuilder.append(" INNER JOIN MASSHOP MS ON MS.SHOPID = CP.SHOPID  ");
				queryBuilder.append(" WHERE  SH.CLOSING_DATE BETWEEN TO_DATE('"+searchDate+"','dd-mm-yyyy') AND TO_DATE('"+endDate+"','dd-mm-yyyy')");
				queryBuilder.append( whereBuilder.toString() );
				queryBuilder.append(" ORDER BY CR.RECEIPTDTTM ");
				
				System.out.println("getCloseEndOfDayReport : " + queryBuilder.toString());

				episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet row) throws SQLException {
						ReportPayment reportPayment = new ReportPayment();
						reportPayment.setReceiptDate(row.getString(1));
						reportPayment.setShopNo(row.getString(2));
						reportPayment.setShopName(row.getString(3));
						reportPayment.setPosName(row.getString(4));
						reportPayment.setPaymentUser(row.getString(5));
						reportPayment.setPayFullname(row.getString(6));
						reportPaymentDTO.addData(reportPayment);
					}
				});

				
				if(AppUtil.isStringHasValue(printPdf)) {
					request.setAttribute("printCloseEndOfDayReport", reportPaymentDTO);
				}
				return reportPaymentDTO;
			}
	
			// Report : unclose-endofday-report.
			@ResponseBody
			@RequestMapping(value="/getUnCloseEndOfDayReport.json", method=RequestMethod.GET)
			public ReportPaymentDTO getUnCloseEndOfDayReportJSON(HttpServletRequest request,
					@RequestParam(value="printPdf", required=true) String printPdf, 
					@RequestParam(value="startDate", required=true) String searchDate,
					@RequestParam(value="period", required=true) String searchPeriod,
					@RequestParam(value="shop", required=true) String searchShop,
					@RequestParam(value="officer", required=true) String searchOfficer) throws Exception {

				System.out.println("printPdf = "+ printPdf);
				System.out.println("startDate = "+ searchDate);
				System.out.println("period = "+ searchPeriod);
				System.out.println("shop = "+ searchShop);
				System.out.println("officer = "+ searchOfficer);
				
				StringBuilder whereBuilder = new StringBuilder();
				if(!StringUtil.isBlank(searchDate)) {
					whereBuilder.append(" AND TRUNC(cr.receiptdttm) = TO_DATE('"+ searchDate +"', 'DD-MM-YYYY') ");
					Date givenDateFrom;
					try {
						givenDateFrom = AppUtil.formatter_EN.parse(convertDateString(searchDate));
					} catch(Exception e) {
						givenDateFrom = new Date();
					}
					Date givenDateTo = null;
					if(FULL_WORKING_PERIOD.equals(searchPeriod)) {
						// set givenDate to start from 00:00:00 to 23:59:59
						givenDateFrom = AppUtil.setStartOfDayTime(givenDateFrom);
						givenDateTo = AppUtil.setEndOfDayTime(givenDateFrom);
					} else if(MORNING_WORKING_PERIOD.equals(searchPeriod)) {
						// set givenDate to start from 00:00:00 to 11:59:59
						givenDateFrom = AppUtil.setStartOfDayTime(givenDateFrom);
						givenDateTo = AppUtil.setBeforeNoonTime(givenDateFrom);
					} else if(AFTERNOON_WORKING_PERIOD.equals(searchPeriod)) {
						// set givenDate to start from 12:00:00 to 23.59.59
						givenDateFrom = AppUtil.setNoonTime(givenDateFrom);
						givenDateTo = AppUtil.setEndOfDayTime(givenDateFrom);
					}
					whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+ AppUtil.format_ddMMyyyyHHmmss(givenDateFrom) +".981000000', 'DD-MM-YYYY HH24:MI:SS.FF') AND TO_TIMESTAMP('"+ AppUtil.format_ddMMyyyyHHmmss(givenDateTo) +".981000000', 'DD-MM-YYYY HH24:MI:SS.FF')");
				}
				if(!"-1".equals(searchOfficer)) {
					whereBuilder.append(" AND cp.officerid = "+ searchOfficer);
				}
				
				if(!"-1".equals(searchShop)) {
					whereBuilder.append(" AND cp.shopid = "+ searchShop);
				}
				// whereBuilder.append(" AND cp.paymenttype = 'SERVICE_CHARGE' ");
				
				final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
				StringBuilder queryBuilder = new StringBuilder();
				queryBuilder.append(" SELECT DISTINCT TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy') AS receiptdttm, mf.username, (mf.officergivenname ||' '|| mf.officerfamilyname) AS payfullname, ms.shopno, ms.shopname ");
				queryBuilder.append(" FROM correceipt cr ");
				queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
				queryBuilder.append(" INNER JOIN masofficer mf ON mf.username = cp.updateuser ");
				queryBuilder.append(" INNER JOIN masshop ms ON ms.shopid = cp.shopid ");
				queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
				queryBuilder.append(" AND cr.isendofday IS NULL ");
				queryBuilder.append( whereBuilder.toString() );
				queryBuilder.append(" ORDER BY receiptdttm ");
				
				episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet row) throws SQLException {
						ReportPayment reportPayment = new ReportPayment();
						reportPayment.setReceiptDate(row.getString(1));
						reportPayment.setPaymentUser(row.getString(2));
						reportPayment.setPayFullname(row.getString(3));
						reportPayment.setShopNo(row.getString(4));
						reportPayment.setShopName(row.getString(5));
						reportPaymentDTO.addData(reportPayment);
					}
				});
				if(AppUtil.isStringHasValue(printPdf)) {
					request.setAttribute("printUnCloseEndOfDayReport", reportPaymentDTO);
				}
				return reportPaymentDTO;
			}
	
	public ReportPayment paymentSummaryAttributes(ReportPaymentDTO reportPaymentDTO, String paymentTypeName) {
		boolean paymentAction = false;
		int sumCancelReceipt = 0, sumReceipt = 0, sumTotalReceipt = 0, sumRowPaymentFull = 0, sumRowPaymentABVR = 0;
		double sumCancelAmount = 0, sumAmount = 0, sumTotalAmount = 0, sumAmountPaymentFull = 0, sumAmountPaymentABVR = 0;
		ReportPayment paymentAttributes = null;
		for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
			if(paymentTypeName.equals(reportPayment.getPaymentType())) {
				if("FC".equals(reportPayment.getAttributes())) {
					sumReceipt++;
					sumAmount += Double.parseDouble(reportPayment.getTotalCharge());
				} else if(reportPayment.getAttributes().indexOf("FCR") != -1) {
					sumCancelReceipt++;
					sumCancelAmount += Double.parseDouble(reportPayment.getTotalCharge());
				}
				sumTotalReceipt++;
				sumTotalAmount += Double.parseDouble(reportPayment.getTotalCharge());
				paymentAction = true;
				if(AppConstants.RECEIPT_TYPE_FULL.equals(reportPayment.getReceiptType())) {
					sumRowPaymentFull += 1; 
					sumAmountPaymentFull += Double.parseDouble(reportPayment.getTotalCharge());
				}
				if(AppConstants.RECEIPT_TYPE_ABBREVIATION.equals(reportPayment.getReceiptType())) {
					sumRowPaymentABVR += 1;
					sumAmountPaymentABVR += Double.parseDouble(reportPayment.getTotalCharge());
				}
				//sumRowPenalty += 1; 
				//sumRowRetention += 1; 
				//sumRowCompensation += 1;
			}
		} if(paymentAction) {
			paymentAttributes = new ReportPayment();
			paymentAttributes.setPaymentType(paymentTypeName);
			paymentAttributes.setSumRowPaymentFull(AppUtil.toString(sumRowPaymentFull));
			paymentAttributes.setSumAmountPaymentFull(AppUtil.toString(sumAmountPaymentFull));
			paymentAttributes.setSumRowPaymentABVR(AppUtil.toString(sumRowPaymentABVR));
			paymentAttributes.setSumAmountPaymentABVR(AppUtil.toString(sumAmountPaymentABVR));
			paymentAttributes.setSumCancelReceipt(sumCancelReceipt);
			paymentAttributes.setSumCancelAmount(sumCancelAmount);
			paymentAttributes.setSumReceipt(sumReceipt);
			paymentAttributes.setSumAmount(sumAmount);
			paymentAttributes.setSumTotalReceipt(sumTotalReceipt);
			paymentAttributes.setSumTotalAmount(sumTotalAmount);
		}
		return paymentAttributes;
	}

	// Report : cancel-payment-report.
		@ResponseBody
		@RequestMapping(value="/getCancelPaymentReport.json", method=RequestMethod.GET)
		public ReportPaymentDTO getCancelPaymentReportJSON(HttpServletRequest request,
				@RequestParam(value="printPdf", required=true) String printPdf, 
				@RequestParam(value="startDate", required=true) String searchDate,
				@RequestParam(value="endDate", required=true) String searchEndDate,
				@RequestParam(value="invNo", required=true) String searchInvNo,
				@RequestParam(value="shop", required=true) String searchShop,
				@RequestParam(value="officer", required=true) String searchOfficer,
				@RequestParam(value="sourceType", required=true) String sourceType,
				@RequestParam(value="serviceType", required=true) String serviceType
				) throws Exception {

			System.out.println("printPdf = "+ printPdf);
			System.out.println("startDate = "+ searchDate);
			System.out.println("endDate = "+ searchEndDate);
			System.out.println("invNo = "+ searchInvNo);
			System.out.println("shop = "+ searchShop);
			System.out.println("officer = "+ searchOfficer);
			System.out.println("sourceType = "+ sourceType);
			System.out.println("serviceType = "+ serviceType);
			
			StringBuilder whereBuilder = new StringBuilder();
			if(!StringUtil.isBlank(searchDate) && !StringUtil.isBlank(searchEndDate)) {
				whereBuilder.append(" AND cr.CANCELDTTM BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
//				whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
			}
			if(!StringUtil.isBlank(searchInvNo) ) {
				whereBuilder.append(" AND cr.RECEIPTNO = '"+ searchInvNo +"' ");
			}
			if(!"all".equals(searchOfficer)) {
				whereBuilder.append(" AND cr.UPDATEUSER = '"+ searchOfficer +"' ");
			}
			
			if(!"-1".equals(searchShop)) {
				whereBuilder.append(" AND ms.shopno = '"+ searchShop +"' ");
			}
			
			if(!"all".equals(sourceType)) {
				whereBuilder.append(" AND ma.agent_code = '"+ sourceType +"' ");
			}
			
			
			if("CAT".equalsIgnoreCase(serviceType)) {
				whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_AGENT+"','"+AppConstants.PAYMENT_TYPE_MNP+"') ");
			}	
			if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
					whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_AGENT+"') "
							+ "AND CR.receiptno not like 'EPO%'");
				}
				if(AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(serviceType)) {
					whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"') ");
				} 
			
			
			final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT DISTINCT cp.paymentid, cr.accountno, cr.receiptname,CASE WHEN  CP.PAYMENTTYPE IN ('TOPUP','OTHER') THEN TS.PROFIT_CENTER_CODE ");
			queryBuilder.append(" WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE','OTBOSS','TBOSS','EXTEND_CHARGE','PENALTY_CHARGE') THEN SAP.TB_CODETEXT END AS COLLECTIONUNIT, ");
			queryBuilder.append(" CASE WHEN  CP.PAYMENTTYPE IN ('TOPUP','OTHER') THEN TS.SERVICENAME WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE','OTBOSS','TBOSS','EXTEND_CHARGE','PENALTY_CHARGE') THEN TI.INVOICENO END AS service ");  
			queryBuilder.append(" , cr.receiptno, cr.canceledby, TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy') AS receiptdttm, ti.charge, ti.vat, ti.totalcharge, ts.charge, ts.vat, ts.totalcharge, TO_CHAR(cr.canceldttm, 'dd/MM/yyyy') AS canceldttm  ");
			queryBuilder.append(" ,MA.AGENT_NAME,MA.COMPANY_NAME, MA.TAXID,TS.REF1,TS.REF2,cr.CANCELREASON ");
			queryBuilder.append(" ,cp.paymenttype, tm.name, TM.CHEQUENO , SAP.TB_CODETEXT, cr.updateuser ");
			queryBuilder.append(" FROM correceipt cr ");
			queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
			queryBuilder.append(" LEFT JOIN tmpaccount ta ON ta.paymentid = cp.paymentid ");
			queryBuilder.append(" LEFT JOIN tmpinvoice ti ON ti.paymentid = cp.paymentid ");
			queryBuilder.append(" LEFT JOIN tmpinvoiceservice ts ON ts.receiptid = cr.receiptid ");
			queryBuilder.append(" LEFT JOIN MAS_AGENT MA ON MA.AGENT_CODE = CR.REF1 ");
			queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
			queryBuilder.append(" LEFT JOIN trsmethod tm ON tm.paymentid = cr.paymentid ");
			queryBuilder.append(" LEFT JOIN SAP_REVENUE_DEPT@CATPCU1_DEV sap ON substr(cp.collectionunit,1,1)||'000' = sap.TB_REGION_CODE  ");
			queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
			queryBuilder.append(" AND cr.attributes LIKE '%R%' ");
			queryBuilder.append( whereBuilder.toString() );
			queryBuilder.append(" ORDER BY SAP.TB_CODETEXT,cr.receiptno ");
			
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					ReportPayment reportPayment = new ReportPayment();
					reportPayment.setPaymentId(row.getString(1));
					reportPayment.setAccountNo(row.getString(2));
					reportPayment.setReceiptName(row.getString(3));
					reportPayment.setCollectionUnit(row.getString(4));
					reportPayment.setInvoiceNo(row.getString(5));
					reportPayment.setReceiptNo(row.getString(6));
					reportPayment.setCancelByUser(row.getString(7));
					reportPayment.setReceiptDate(row.getString(8));
					reportPayment.setCharge((row.getString(9) == null)?row.getString(12): row.getString(9));
					reportPayment.setVat((row.getString(10) == null)? row.getString(13): row.getString(10));
					reportPayment.setTotalCharge((row.getString(11) == null)?row.getString(14): row.getString(11));
					reportPayment.setCancelDate(row.getString(15));
					
					reportPayment.setAgentCode(row.getString(16));
					reportPayment.setAgentName(row.getString(17));
					reportPayment.setTaxId(row.getString(18));
					reportPayment.setRef1(row.getString(19));
					reportPayment.setRef2(row.getString(20));
					reportPayment.setReason(row.getString(21));
					
					reportPayment.setPaymentType(row.getString(22));
					reportPayment.setPaymentCash(row.getString(23));
					reportPayment.setDocumentNo(row.getString(24));
					reportPayment.setPaymentUser(row.getString(26));

					reportPaymentDTO.addData(reportPayment);
									
				}
			});
			if(AppUtil.isStringHasValue(printPdf)) {
				request.setAttribute("printCancelPaymentReport", reportPaymentDTO);
			}
			return reportPaymentDTO;
		}
			/*final ReportPaymentDTO subReportPaymentDTO = new ReportPaymentDTO();
			for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
				StringBuilder subQueryBuilder = new StringBuilder();
				subQueryBuilder.append(" SELECT name, chequeno FROM trsmethod WHERE paymentid = "+ reportPayment.getPaymentId() +" ORDER BY updatedttm ");
				episJdbcTemplate.query(subQueryBuilder.toString(), new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet row) throws SQLException {
						ReportPayment reportPayment = new ReportPayment();
						reportPayment.setPaymentCash(row.getString(1));
						reportPayment.setDocumentNo(row.getString(2));
						subReportPaymentDTO.addData(reportPayment);
					}
				});
			}*/
//			int i = 0;
//			List<ReportPayment> reportPaymentOrderType = new ArrayList<ReportPayment>();
//			for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
//				reportPayment.setPaymentCash(subReportPaymentDTO.getData().get(i).getPaymentCash());
//				reportPayment.setDocumentNo(subReportPaymentDTO.getData().get(i).getDocumentNo());
//				reportPaymentOrderType.add(reportPayment);
//				i++;
//			}
//			reportPaymentDTO.setData(reportPaymentOrderType);
//			if(AppUtil.isStringHasValue(printPdf)) {
//				request.setAttribute("printCancelPaymentReport", reportPaymentDTO);
//			}
//			return reportPaymentDTO;
//		}
	// Report : offline-payment-report.
	@ResponseBody
	@RequestMapping(value="/getOfflinePaymentReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getOfflinePaymentReportJSON(HttpServletRequest request,
			@RequestParam(value="printPdf", required=true) String printPdf, 
			@RequestParam(value="startDate", required=true) String searchDate,
			@RequestParam(value="period", required=true) String searchPeriod,
			@RequestParam(value="shop", required=true) String searchShop,
			@RequestParam(value="officer", required=true) String searchOfficer) throws Exception {

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("period = "+ searchPeriod);
		System.out.println("shop = "+ searchShop);
		System.out.println("officer = "+ searchOfficer);
		
		StringBuilder whereBuilder = new StringBuilder();
		if(!StringUtil.isBlank(searchDate)) {
			whereBuilder.append(" AND TRUNC(cr.receiptdttm) = TO_DATE('"+ searchDate +"', 'DD-MM-YYYY') ");
			Date givenDateFrom;
			try {
				givenDateFrom = AppUtil.formatter_EN.parse(convertDateString(searchDate));
			} catch(Exception e) {
				givenDateFrom = new Date();
			}
			Date givenDateTo = null;
			if(FULL_WORKING_PERIOD.equals(searchPeriod)) {
				// set givenDate to start from 00:00:00 to 23:59:59
				givenDateFrom = AppUtil.setStartOfDayTime(givenDateFrom);
				givenDateTo = AppUtil.setEndOfDayTime(givenDateFrom);
			} else if(MORNING_WORKING_PERIOD.equals(searchPeriod)) {
				// set givenDate to start from 00:00:00 to 11:59:59
				givenDateFrom = AppUtil.setStartOfDayTime(givenDateFrom);
				givenDateTo = AppUtil.setBeforeNoonTime(givenDateFrom);
			} else if (AFTERNOON_WORKING_PERIOD.equals(searchPeriod)) {
				// set givenDate to start from 12:00:00 to 23.59.59
				givenDateFrom = AppUtil.setNoonTime(givenDateFrom);
				givenDateTo = AppUtil.setEndOfDayTime(givenDateFrom);
			}
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+ AppUtil.format_ddMMyyyyHHmmss(givenDateFrom) +".981000000', 'DD-MM-YYYY HH24:MI:SS.FF') AND TO_TIMESTAMP('"+ AppUtil.format_ddMMyyyyHHmmss(givenDateTo) +".981000000', 'DD-MM-YYYY HH24:MI:SS.FF')");

		}
		if(!"-1".equals(searchOfficer)) {
			whereBuilder.append(" AND cp.officerid = "+ searchOfficer);
		}
		
		if(!"-1".equals(searchShop)) {
			whereBuilder.append(" AND cp.shopid = "+ searchShop);
		}
		// whereBuilder.append(" AND cp.paymenttype = 'SERVICE_CHARGE' ");

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT cp.paymentid, cp.paymenttype, cr.receiptno, cr.accountno, cr.receiptname, cp.collectionunit, ti.invoiceno, ti.charge, ti.vat, ti.totalcharge, cr.attributes, cr.vatrate, cr.updateuser ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
		queryBuilder.append(" LEFT JOIN tmpaccount ta ON ta.paymentid = cp.paymentid ");
		queryBuilder.append(" LEFT JOIN tmpinvoice ti ON ti.paymentid = cp.paymentid ");
		queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
		queryBuilder.append(" 	AND cr.receiptno LIKE 'EPF%' ");
		queryBuilder.append( whereBuilder.toString() );
		queryBuilder.append(" ORDER BY cr.receiptno ");
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setPaymentId(row.getString(1));
				reportPayment.setPaymentType(row.getString(2));
				reportPayment.setReceiptNo(row.getString(3));
				reportPayment.setAccountNo(row.getString(4));
				reportPayment.setReceiptName(row.getString(5));
				reportPayment.setCollectionUnit(row.getString(6));
				reportPayment.setInvoiceNo(row.getString(7));
				reportPayment.setCharge(row.getString(8));
				reportPayment.setVat(row.getString(9));
				reportPayment.setTotalCharge(row.getString(10));
				reportPayment.setAttributes(row.getString(11));
				reportPayment.setVatRate(row.getString(12));
				reportPayment.setPaymentUser(row.getString(13));
				reportPaymentDTO.addData(reportPayment);
			}
		});
		final ReportPaymentDTO subReportPaymentDTO = new ReportPaymentDTO();
		for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
			StringBuilder subQueryBuilder = new StringBuilder();
			subQueryBuilder.append(" SELECT name, chequeno FROM trsmethod WHERE paymentid = "+ reportPayment.getPaymentId() +" ORDER BY updatedttm ");
			episJdbcTemplate.query(subQueryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					ReportPayment reportPayment = new ReportPayment();
					reportPayment.setPaymentCash(row.getString(1));
					reportPayment.setDocumentNo(row.getString(2));
					subReportPaymentDTO.addData(reportPayment);
				}
			});
		}
		int i = 0;
		List<ReportPayment> reportPaymentOrderType = new ArrayList<ReportPayment>();
		for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
			reportPayment.setPaymentCash(subReportPaymentDTO.getData().get(i).getPaymentCash());
			reportPayment.setDocumentNo(subReportPaymentDTO.getData().get(i).getDocumentNo());
			reportPaymentOrderType.add(reportPayment);
			i++;
		}
		reportPaymentDTO.setData(reportPaymentOrderType);
		
		if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printOfflinePaymentReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}
	
	// Report : egp-payment-report.
		@ResponseBody
		@RequestMapping(value="/getEgpPaymentReport.json", method=RequestMethod.GET)
		public ReportPaymentDTO getEgpPaymentReportJSON(HttpServletRequest request,
				@RequestParam(value="printPdf", required=true) String printPdf, 
				@RequestParam(value="startDate", required=true) String searchDate,
				@RequestParam(value="endDate", required=true) String searchEndDate,
				@RequestParam(value="accountID", required=true) String searchAccountID,
				@RequestParam(value="vatRate", required=true) String searchVatRate,
				@RequestParam(value="shop", required=true) String searchShop,
				@RequestParam(value="officer", required=true) String searchOfficer,
				@RequestParam(value="soureType", required=true) String searchSoureType) throws Exception {

			System.out.println("printPdf = "+ printPdf);
			System.out.println("startDate = "+ searchDate);
			System.out.println("endDate = "+ searchEndDate);
			System.out.println("accountID = "+ searchAccountID);
			System.out.println("vatRate = "+ searchVatRate);
			System.out.println("shop = "+ searchShop);
			System.out.println("officer = "+ searchOfficer);
			System.out.println("soureType = "+ searchSoureType);
			
			StringBuilder whereBuilder = new StringBuilder();
			if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchEndDate)) {
				whereBuilder.append(" AND cr.RECEIPTDTTM BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI') ");
			}
			
			whereBuilder.append(" AND egpMap.EGP_NO IS NOT NULL ");
			
			whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_OTHER+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
			
			if(!"all".equalsIgnoreCase(searchVatRate)){
				whereBuilder.append(" AND cr.vatrate = '"+searchVatRate+"' ");
			}
			
			if(!"-1".equals(searchOfficer)) {
				whereBuilder.append(" AND cp.officerid = '"+ searchOfficer+"'");
			}
			
			if(!"-1".equals(searchShop)) {
				whereBuilder.append(" AND ms.shopno = '"+ searchShop+"'");
			}
			
			if(!"-1".equals(searchSoureType)) {
				whereBuilder.append(" AND cp.paymenttype = '"+searchSoureType+"' ");
			}

			final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT egpMap.EGP_NO,  egpMap.EGP_CONTRACT, cr.receiptname, cr.accountno, ti.invoiceno, TO_CHAR(ti.issuedate, 'dd/MM/yyyy'), TO_CHAR(ti.duedate, 'dd/MM/yyyy'), ti.billcycle, TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy'), cr.branchname, ti.charge, ti.vat, ti.totalcharge, cp.paymenttype, cr.receiptno, cp.paymentid, sap.TB_CODETEXT as collectionunit , cr.ATTRIBUTES, ti.STATUS ");
			queryBuilder.append(" FROM correceipt cr ");
			queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
			queryBuilder.append(" LEFT JOIN tmpaccount ta ON ta.paymentid = cp.paymentid ");
			queryBuilder.append(" LEFT JOIN tmpinvoice ti ON ti.paymentid = cp.paymentid and ti.RECEIPTID = cr.RECEIPTID");
			queryBuilder.append(" LEFT JOIN tmpinvoiceservice ts ON ts.receiptid = cr.receiptid ");
			queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
			queryBuilder.append(" LEFT JOIN RECEIPT_EGP_MAPPING egpMap ON cr.RECEIPTNO = egpMap.RECEIPT_NO ");
			queryBuilder.append(" LEFT JOIN SAP_REVENUE_DEPT@CATPCU1_DEV sap ON substr(cp.collectionunit,1,1)||'000' = sap.TB_REGION_CODE    ");
			queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
			queryBuilder.append( whereBuilder.toString() );
			queryBuilder.append(" ORDER BY cr.receiptno ");
			
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					ReportPayment reportPayment = new ReportPayment();
					reportPayment.setEgpNo(row.getString(1));
					reportPayment.setEgpContract(row.getString(2));
					reportPayment.setReceiptName(row.getString(3));
					reportPayment.setAccountNo(row.getString(4));
					reportPayment.setInvoiceNo(row.getString(5));
					reportPayment.setIssueDate(row.getString(6));
					reportPayment.setDueDate(row.getString(7));
					reportPayment.setBillCycle(row.getString(8));
					reportPayment.setReceiptDate(row.getString(9));
					reportPayment.setBranchName(row.getString(10));
					reportPayment.setCharge(row.getString(11));
					reportPayment.setVat(row.getString(12));
					reportPayment.setTotalCharge(row.getString(13));
					reportPayment.setPaymentType(row.getString(14));
					reportPayment.setReceiptNo(row.getString(15));
					reportPayment.setPaymentId(row.getString(16));
					reportPayment.setCollectionUnit(row.getString(17));
					reportPayment.setAttributes(row.getString(18));
					reportPayment.setStatus(null!=row.getString(18)?(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : "-"):"-");
					
					payTypeName = convertPayTypeName(reportPayment.getPaymentType());
					reportPayment.setPayTypeName(payTypeName);
					reportPaymentDTO.addData(reportPayment);
					
				}

			});
			String tmpPaymentId = "";
			List<ReportPayment> reportPaymentOrderType = new ArrayList<ReportPayment>();
			
			prepareData(reportPaymentDTO, tmpPaymentId, reportPaymentOrderType);
			
			reportPaymentDTO.setData(reportPaymentOrderType);
			
			if(AppUtil.isStringHasValue(printPdf)) {
				request.setAttribute("printEgpPaymentReport", reportPaymentDTO);
			}
			return reportPaymentDTO;
		}
		
		private String convertPayTypeName(String paymentType) {
			if(AppConstants.PAYMENT_TYPE_SERVICE_CHARGE.equalsIgnoreCase(paymentType)) {
				payTypeName = "รับชำระค่าใช้บริการ";
	    	} else if(AppConstants.PAYMENT_TYPE_OTHER.equalsIgnoreCase(paymentType)) {
	    		payTypeName = "รับชำระค่าใช้บริการอื่นๆ";
	    	} else if(AppConstants.PAYMENT_TYPE_GFMIS.equalsIgnoreCase(paymentType)) {
	    		payTypeName = "รับชำระค่าใช้บริการ GFMIS";
	    	} else if(AppConstants.PAYMENT_TYPE_TOPUP.equalsIgnoreCase(paymentType)) {
	    		payTypeName = "รับชำระค่าใช้บริการ (TOPUP)";
	    	} else if(AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(paymentType)) {
	    		payTypeName = "รับชำระค่าใช้บริการ (MNP)";
	    	} else if(AppConstants.PAYMENT_TYPE_TBOSS.equalsIgnoreCase(paymentType)) {
	    		payTypeName = "รับชำระค่าใช้บริการหนี้สูญ TBOSS";
	    	} else if(AppConstants.PAYMENT_TYPE_OTBOSS.equalsIgnoreCase(paymentType)) {
	    		payTypeName = "รับชำระค่าใช้บริการหนี้นอก TBOSS";
			} else if(AppConstants.PAYMENT_TYPE_PENALTY_CHARGE.equalsIgnoreCase(paymentType)) {
				payTypeName = "รับชำระค่าปรับ";
			} else if(AppConstants.PAYMENT_TYPE_EXTEND_CHARGE.equalsIgnoreCase(paymentType)) {
				payTypeName = "รับชำระค่าต่อบริการ";
			} else {
	    		payTypeName = "รับชำระค่าใช้บริการ";
	    	}
			return payTypeName;
		}

		@ResponseBody 
		@RequestMapping(value="/getEgpProjectReport.json", method=RequestMethod.GET)
		public ReportPaymentDTO getEgpProjectReportJSON(HttpServletRequest request,
				@RequestParam(value="printPdf", required=true) String printPdf, 
				@RequestParam(value="startDate", required=true) String searchDate,
				@RequestParam(value="endDate", required=true) String searchEndDate,
				@RequestParam(value="shop", required=true) String searchShop,  //) throws Exception {
				@RequestParam(value="soureType", required=true) String searchSoureType)throws Exception {

			System.out.println("printPdf = "+ printPdf);
			System.out.println("startDate = "+ searchDate);
			System.out.println("endDate = "+ searchEndDate);
			System.out.println("shop = "+ searchShop);
			System.out.println("soureType = "+ searchSoureType);
			
			StringBuilder whereBuilder = new StringBuilder();
			if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchEndDate)) {
				whereBuilder.append(" AND cr.RECEIPTDTTM BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY') "); 
			}
			
			whereBuilder.append(" AND egpMap.EGP_NO IS NOT NULL ");
			
			whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_OTHER+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");

			if(!"-1".equals(searchShop)) {
				whereBuilder.append(" AND ms.shopno = '"+ searchShop+"'");
			}
			
			if(!"-1".equals(searchSoureType)) {
				whereBuilder.append(" AND cp.paymenttype = '"+searchSoureType+"' ");
			}

			final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
			StringBuilder queryBuilder = new StringBuilder();
			/*queryBuilder.append(" SELECT egpMap.EGP_NO,  egpMap.EGP_CONTRACT, cr.receiptname, cr.accountno, ti.invoiceno, TO_CHAR(ti.issuedate, 'dd/MM/yyyy'), TO_CHAR(ti.duedate, 'dd/MM/yyyy'), ti.billcycle, TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy'), cr.branchname, ti.charge, ti.vat, ti.totalcharge, cp.paymenttype, cr.receiptno, cp.paymentid, cp.COLLECTIONUNIT, cr.ATTRIBUTES, ti.STATUS ");
			queryBuilder.append(" FROM correceipt cr ");
			queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
			queryBuilder.append(" LEFT JOIN tmpaccount ta ON ta.paymentid = cp.paymentid ");
			queryBuilder.append(" LEFT JOIN tmpinvoice ti ON ti.paymentid = cp.paymentid and ti.RECEIPTID = cr.RECEIPTID");
			queryBuilder.append(" LEFT JOIN tmpinvoiceservice ts ON ts.receiptid = cr.receiptid ");
			queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
			queryBuilder.append(" LEFT JOIN RECEIPT_EGP_MAPPING egpMap ON cr.RECEIPTNO = egpMap.RECEIPT_NO ");
			queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
			queryBuilder.append( whereBuilder.toString() );
			queryBuilder.append(" ORDER BY cr.receiptno ");*/
			
			queryBuilder.append(" SELECT CRM.EGP_NUMBER,  CRM.EGP_CONTACT,TO_CHAR(CRM.EGP_START_DT,'DD-MON-YY'),TO_CHAR(CRM.EGP_END_DT,'DD-MON-YY'),CRM.EGP_VALUE, CR.RECEIPTNAME, CR.ACCOUNTNO,TI.INVOICENO, ");
			queryBuilder.append(" TA.BILLGROUP, TO_CHAR(TI.ISSUEDATE, 'dd/MM/yyyy') AS ISSUEDATE, TO_CHAR(TI.DUEDATE, 'dd/MM/yyyy') AS DUEDATE, TI.BILLCYCLE,  ");
			queryBuilder.append(" CASE WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE') THEN NVL(IB.AMOUNT,'0.00') WHEN CP.PAYMENTTYPE NOT IN ('SERVICE_CHARGE') THEN NVL(IO.AMOUNT,'0.00') END AS REVENUE, ");
			queryBuilder.append(" CASE WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE') THEN NVL(IB.VAT_AMOUNT,'0.00') WHEN CP.PAYMENTTYPE NOT IN ('SERVICE_CHARGE') THEN NVL(IO.VAT_AMOUNT,'0.00') END AS VAT, ");
			queryBuilder.append(" CASE WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE') THEN NVL(IB.TOTAL_AMOUNT,'0.00') WHEN CP.PAYMENTTYPE NOT IN ('SERVICE_CHARGE') THEN NVL(IO.TOTAL_AMOUNT,'0.00') END AS AMOUNT, ");
			queryBuilder.append(" CASE WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE') THEN IB.POSTING_DATE WHEN CP.PAYMENTTYPE NOT IN ('SERVICE_CHARGE') THEN IO.POSTING_DATE END AS POSTING_DATE, ");
			queryBuilder.append(" NVL(ADJ.EXC_VAT,'0.00') AS ADJ_REV, NVL(ADJ.VAT,'0.00'), NVL(ADJ.AMOUNT,'0.00') , ADJ.POSTING_DATE, ");
			queryBuilder.append(" TO_CHAR(CR.RECEIPTDTTM, 'dd/MM/yyyy') AS RECEIPTDTTM, CR.BRANCHNAME, TI.CHARGE, TI.VAT, TI.TOTALCHARGE, CP.PAYMENTTYPE, CR.RECEIPTNO, CP.PAYMENTID, CP.COLLECTIONUNIT, CR.ATTRIBUTES, TI.STATUS, ");
			queryBuilder.append(" CASE WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE') THEN (NVL(NETB.NET_BALANCE_AMT,'0.00') - NVL(NETB.VAT_AMOUNT,'0.00')) WHEN CP.PAYMENTTYPE NOT IN ('SERVICE_CHARGE') THEN (NVL(NETO.NET_BALANCE_AMT,'0.00') - NVL(NETO.VAT_AMOUNT,'0.00')) END AS REVENUE, ");
			queryBuilder.append(" CASE WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE') THEN NVL(NETB.VAT_AMOUNT,'0.00') WHEN CP.PAYMENTTYPE NOT IN ('SERVICE_CHARGE') THEN NVL(NETO.VAT_AMOUNT,'0.00') END AS VAT, ");
			queryBuilder.append(" CASE WHEN CP.PAYMENTTYPE IN ('SERVICE_CHARGE') THEN NVL(NETB.NET_BALANCE_AMT,'0.00') WHEN CP.PAYMENTTYPE NOT IN ('SERVICE_CHARGE') THEN NVL(NETO.NET_BALANCE_AMT,'0.00') END AS AMOUNT ");
			queryBuilder.append(" FROM  RECEIPT_EGP_MAPPING EGPMAP  ");
			queryBuilder.append(" LEFT JOIN CORRECEIPT CR  ON CR.RECEIPTNO = EGPMAP.RECEIPT_NO   ");
			queryBuilder.append(" LEFT JOIN CORPAYMENT CP ON CP.PAYMENTID = CR.PAYMENTID ");
			queryBuilder.append(" LEFT JOIN RECEIPT_EGP_MAPPING EGPMAP ON CR.RECEIPTNO = EGPMAP.RECEIPT_NO  ");
			queryBuilder.append(" LEFT JOIN CRMDATA.CAT_BILL_ACCT@CRMDEVWP_DEV CRM ON EGPMAP.EGP_NO = CRM.EGP_NUMBER AND CRM.CAT_BILL_ACCT_NUMBER = EGPMAP.BA_NO ");
			queryBuilder.append(" LEFT JOIN TMPACCOUNT TA ON TA.PAYMENTID = CP.PAYMENTID ");
			queryBuilder.append(" LEFT JOIN TMPINVOICE TI ON TI.PAYMENTID = CP.PAYMENTID AND TI.RECEIPTID = CR.RECEIPTID AND TI.STATUS IN ('N','SUCCESS') ");
			queryBuilder.append(" LEFT JOIN MASSHOP MS ON MS.SHOPID = CP.SHOPID  ");
			queryBuilder.append(" LEFT JOIN INV_SUMMARY_SAP_IBACSS IB ON IB.CONTRNO = CR.ACCOUNTNO ");
			queryBuilder.append(" LEFT JOIN INV_SUMMARY_SAP_OTBOSS IO ON IO.CONTRNO = CR.ACCOUNTNO ");
			queryBuilder.append(" LEFT JOIN CAT_ADJ_EPIS@CATPCU1_DEV  ADJ ON ADJ.CONTRACT_NO = CR.ACCOUNTNO ");
			queryBuilder.append(" LEFT JOIN PAYMENT_SAP_NONPAY_NET_IBACSS@CATPCU1_DEV NETB ON  NETB.CONTRNO = CR.ACCOUNTNO  ");
			queryBuilder.append(" LEFT JOIN PAYMENT_SAP_NONPAY_NET_OTBOSS@CATPCU1_DEV NETO ON  NETO.CONTRNO = CR.ACCOUNTNO ");
			queryBuilder.append(" WHERE CR.PAYMENTID = CP.PAYMENTID  AND EGPMAP.EGP_NO IS NOT NULL ");
			queryBuilder.append( whereBuilder.toString() );
			queryBuilder.append(" ORDER BY CRM.EGP_NUMBER,CRM.EGP_CONTACT,CR.RECEIPTDTTM,CRM.EGP_NUMBER,CRM.EGP_CONTACT ");
			
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					ReportPayment reportPayment = new ReportPayment();
					
					//String date_s = parse.(row.getString(3));
//					String oldstring = "2011-01-18";
//					LocalDateTime datetime = LocalDateTime.parse(oldstring, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					
					reportPayment.setEgpNo(row.getString(1));
					reportPayment.setEgpContract(row.getString(2));
					reportPayment.setStartDate(row.getString(3));
					reportPayment.setEndDate(row.getString(4));
					reportPayment.setEgpValue(row.getString(5));
					reportPayment.setReceiptName(row.getString(6));
					reportPayment.setAccountNo(row.getString(7));
					reportPayment.setInvoiceNo(row.getString(8));
					reportPayment.setBillGroup(row.getString(9));
					reportPayment.setIssueDate(row.getString(10));
					
					reportPayment.setDueDate(row.getString(11));
					reportPayment.setBillCycle(row.getString(12));
					reportPayment.setBeginCharge(row.getString(13));
					reportPayment.setBeginVat(row.getString(14));
					reportPayment.setBeginTotalCharge(row.getString(15));
					reportPayment.setPosting_date(row.getString(16));
					
					reportPayment.setAdjCharge(null!=row.getString(17)?row.getString(17):"0");
					reportPayment.setAdjVat(row.getString(18));
					reportPayment.setAdjTotalCharge(row.getString(19));
					reportPayment.setAdjPostingDate(row.getString(20));
					
					reportPayment.setReceiptDate(row.getString(21));
					reportPayment.setBranchName(row.getString(22));
					reportPayment.setCharge(row.getString(23));
					reportPayment.setVat(row.getString(24));
					reportPayment.setTotalCharge(row.getString(25));
					reportPayment.setPaymentType(row.getString(26));
					reportPayment.setReceiptNo(row.getString(27));
					reportPayment.setPaymentId(row.getString(28));
					reportPayment.setCollectionUnit(row.getString(29));
					reportPayment.setAttributes(row.getString(30));
					reportPayment.setStatus(null!=row.getString(31)?(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : "-"):"-");
					
					reportPayment.setNetCharge(row.getString(32));
					reportPayment.setNetVat(row.getString(33));
					reportPayment.setNetTotalCharge(row.getString(34));
					
					payTypeName = convertPayTypeName(reportPayment.getPaymentType());
					reportPayment.setPayTypeName(payTypeName);
					reportPaymentDTO.addData(reportPayment);
					
				}

			});
			String tmpPaymentId = "";
			List<ReportPayment> reportPaymentOrderType = new ArrayList<ReportPayment>();
			
			prepareData(reportPaymentDTO, tmpPaymentId, reportPaymentOrderType);
			
			reportPaymentDTO.setData(reportPaymentOrderType);
			
			if(AppUtil.isStringHasValue(printPdf)) {
				request.setAttribute("printEgpProjectReport", reportPaymentDTO);
			}
			return reportPaymentDTO;
		}
		
	
	private void prepareData(ReportPaymentDTO reportPaymentDTO, String tmpPaymentId, List<ReportPayment> reportPaymentOrderType) {
		for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
			final ReportPaymentDTO subReportPaymentDTO = new ReportPaymentDTO();
			StringBuilder subQueryBuilder = new StringBuilder();
			final StringBuilder documentNo = new StringBuilder();
			paymentMap.clear();
			documentMap.clear();
			subPaymentMap.clear();
			invoiceMap.clear();
			// Fix by EPIS8 30/12/2016 issue no 166
			// subQueryBuilder.append(" SELECT name, chequeno FROM trsmethod WHERE paymentid = "+ reportPayment.getPaymentId() +" ORDER BY updatedttm ");
			StringBuilder whereBuild = new StringBuilder();
			if(null != reportPayment.getPaymentId()) {
				whereBuild.append(" WHERE met.paymentid = '"+reportPayment.getPaymentId()+"'");
			}
			subQueryBuilder.append(" SELECT met.name, met.chequeno, credit.creditno, met.code, deduct.deductionno, NVL(deduct.invoice_no, 'ALL'), deduct.amount, met.paymentid ");
			subQueryBuilder.append(" FROM trsmethod met ");
			subQueryBuilder.append(" LEFT JOIN trscreditref credit ON credit.PAYMENTID = met.PAYMENTID ");
			subQueryBuilder.append(" LEFT JOIN trsdeduction deduct ON deduct.PAYMENTID = met.PAYMENTID ");
			subQueryBuilder.append( whereBuild.toString());
			subQueryBuilder.append(" ORDER BY met.updatedttm ");


			StringBuilder wtQueryBuilder = new StringBuilder();
			wtQueryBuilder.append("select count(1) from trsdeduction deduc where PAYMENTID = ? " +
					" and DEDUCTIONTYPE in ('"+AppConstants.DEDUCT_METHOD_3TREDECIM+"','"+AppConstants.DEDUCT_METHOD_69BIS+"','"+AppConstants.DEDUCT_METHOD_69TRE+"')");
			final int wt_count = episJdbcTemplate.queryForObject(
					wtQueryBuilder.toString(), new Object[] { reportPayment.getPaymentId() }, Integer.class);



			episJdbcTemplate.query(subQueryBuilder.toString(), new RowCallbackHandler(){
			
				@Override
				public void processRow(ResultSet row) throws SQLException {
					ReportPayment reportPayment = new ReportPayment();
					StringBuilder paymentcash = new StringBuilder();
					String rowStr5 = "";
					if (null!=row.getString(5)) {
						rowStr5 = row.getString(5);
					} else {
						rowStr5 = "";
					}

//					if(row.getString(5) != null || (row.getString(5) == null && row.getString(7) != null)){
					//if(!StringUtils.isBlank(rowStr5) || (StringUtils.isBlank(rowStr5) && row.getString(7) != null)){
					if(!StringUtils.isBlank(rowStr5)){//by NSD 13-03-2017
						//documentMap.put(row.getString(6), row.getString(5));
						if(documentMap != null &&
								documentMap.containsKey(row.getString(6)) && 
								!rowStr5.equalsIgnoreCase(documentMap.get(row.getString(6))))
							documentMap.put(row.getString(6), documentMap.get(row.getString(6))+", "+row.getString(5));
						else
							documentMap.put(row.getString(6), row.getString(5));
					}
					if("2".equalsIgnoreCase(row.getString(4)) && row.getString(2) != null && !"".equalsIgnoreCase(row.getString(2))){
						if(subPaymentMap.containsKey(row.getString(6)))
							subPaymentMap.put(row.getString(6), subPaymentMap.get(row.getString(6)).concat(" + ")+row.getString(2));
						else
							subPaymentMap.put(row.getString(6) ,row.getString(2));
						
						if(row.getString(1) != null && !"".equalsIgnoreCase(row.getString(1)))
							paymentMap.put(row.getString(1), row.getString(6));
					}
					else if("3".equalsIgnoreCase(row.getString(4)) && row.getString(3) != null && row.getString(3).length() == 16){
						if(subPaymentMap.containsKey(row.getString(6)))
							subPaymentMap.put(row.getString(6), subPaymentMap.get(row.getString(6)).concat(" + ")+AppUtil.maskCreditCardNumber(row.getString(3), "^^^^^^^^^^^^####"));
						else
							subPaymentMap.put(row.getString(6), AppUtil.maskCreditCardNumber(row.getString(3), "^^^^^^^^^^^^####"));
						if(row.getString(1) != null && !"".equalsIgnoreCase(row.getString(1)))
							paymentMap.put(row.getString(1), row.getString(6));
					} else {
						if(row.getString(1) != null && !"".equalsIgnoreCase(row.getString(1)))
							paymentMap.put(row.getString(1), row.getString(6));
					}
					invoiceMap.put(row.getString(6), "");

					reportPayment.setPaymentCash(paymentcash.toString());
					reportPayment.setDocumentNo(documentNo.toString());
					subReportPaymentDTO.addData(reportPayment);
				}
			});
			// End Fix by EPIS8 30/12/2016 issue no 166
			StringBuffer paymentbf = new StringBuffer();
			StringBuffer subpaymentsf = new StringBuffer();
			StringBuffer docsf = new StringBuffer();
			Map<String, String> tmpinvoiceMap = new HashMap<String, String>();
			
			for (Map.Entry<String, String> invoiceEntry : invoiceMap.entrySet()) {
				String invoiceNo = invoiceEntry.getKey();
				if(invoiceNo != null){
					for (Map.Entry<String, String> docEntry : documentMap.entrySet()) {
						if(invoiceNo.equalsIgnoreCase(docEntry.getKey())){
							tmpinvoiceMap.put(invoiceNo, docEntry.getValue() == null ? "" : docEntry.getValue());
						} else if("กรุณาเลือก".equalsIgnoreCase(invoiceNo)){
							tmpinvoiceMap.put(invoiceNo, docEntry.getValue() == null ? "" : docEntry.getValue());
						} 
					}
				}
			}
			invoiceMap.putAll(tmpinvoiceMap);
			
//			System.out.println("invoiceMap 1: "+invoiceMap);
			for (Map.Entry<String, String> invoiceEntry : invoiceMap.entrySet()) {
				String invoiceNo = invoiceEntry.getKey();
				if(invoiceNo != null){
					for (Map.Entry<String, String> subEntry : subPaymentMap.entrySet()) {
						if(invoiceNo.equalsIgnoreCase(subEntry.getKey())){
							String referenceNo = subEntry.getValue()+" + "+invoiceEntry.getValue();
							tmpinvoiceMap.put(invoiceNo, referenceNo);
						} 
						if(!subPaymentMap.containsKey(reportPayment.getInvoiceNo()))
							tmpinvoiceMap.put(reportPayment.getInvoiceNo(), subEntry.getValue());
					}
				}
			}
			invoiceMap.putAll(tmpinvoiceMap);
				
//			System.out.println("invoiceMap After: "+invoiceMap);
			for (Map.Entry<String, String> entry : paymentMap.entrySet()) {
				paymentbf.append(entry.getKey()+" + ");
			}
			
			String subPayment = subpaymentsf.toString();
			if (paymentbf.length()>0) {
                if(documentMap.containsKey(reportPayment.getInvoiceNo())){
                    //reportPayment.setPaymentCash(paymentbf.toString().substring(0, paymentbf.length()-3).concat(" + WT"));
                    reportPayment.setPaymentCash(paymentbf.toString().substring(0, paymentbf.length()-3));
                } else {
                    reportPayment.setPaymentCash(paymentbf.toString().substring(0, paymentbf.length()-3));
                    if(subPayment.length() > 0)
                        subPayment = subPayment.substring(0, subPayment.length()-3);
                }
            } else {
                reportPayment.setPaymentCash("");
            }

			if(wt_count>0)
				reportPayment.setPaymentCash(reportPayment.getPaymentCash()+" + WT " );

			for (Map.Entry<String, String> invoiceEntry : invoiceMap.entrySet()) {
				String invoiceNo = invoiceEntry.getKey();

				if(invoiceNo != null){
					if(invoiceNo.equalsIgnoreCase(reportPayment.getInvoiceNo())){
						reportPayment.setDocumentNo(invoiceEntry.getValue() == null ? "" : AppUtil.removeLastChar(invoiceEntry.getValue(), "\\+"));
					} else if("กรุณาเลือก".equalsIgnoreCase(invoiceNo)){
						reportPayment.setDocumentNo(invoiceEntry.getValue() == null ? "" : AppUtil.removeLastChar(invoiceEntry.getValue(), "\\+"));
					} else if("ALL".equalsIgnoreCase(invoiceNo)){
						reportPayment.setDocumentNo(invoiceEntry.getValue() == null ? "" : AppUtil.removeLastChar(invoiceEntry.getValue(), "\\+"));
					} else if (tmpPaymentId.equalsIgnoreCase(reportPayment.getPaymentId())){
						//reportPayment.setDocumentNo(invoiceEntry.getValue() == null ? "" : AppUtil.removeLastChar(invoiceEntry.getValue(), "\\+"));
					}
				} else if (subPaymentMap.size() > 0){
					for (Map.Entry<String, String> subEntry : subPaymentMap.entrySet()) {
							subpaymentsf.append(subEntry.getValue()+ " + ");
					}
					reportPayment.setDocumentNo(AppUtil.removeLastChar(subpaymentsf.toString(), "\\+"));
				} 
			}

			if(reportPayment.getPaymentType().equals("OTHER")){
				List<Service> services = serviceRepo.findByReceiptId(reportPayment.getReceiptId());
				String serviceName = "";
				for(int i=0; i<services.size();i++){
					if(serviceName == "")
						serviceName += services.get(i).getServiceName();
					else
						serviceName += ", " + services.get(i).getServiceName();
				}
				reportPayment.setInvoiceNo(serviceName);
			}

			tmpPaymentId = reportPayment.getPaymentId();
			reportPaymentOrderType.add(reportPayment);
		}
		
	}

	@ResponseBody
	@RequestMapping(value="/getDailyNewReceiptSaleReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getDailyNewReceiptSaleReportJSON(HttpServletRequest request,
			   @RequestParam(value="printPdf", required=true) String printPdf,
			   @RequestParam(value="startDate", required=true) String searchDate,
			   @RequestParam(value="endDate", required=true) String searchEndDate,
			   @RequestParam(value="serviceType", required=true) String serviceType
		) throws Exception {

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("endDate = "+ searchEndDate);

		List<String> username = EpContextHolder.getContext().getOwners();
		StringBuilder user = new StringBuilder();
		int i = 0;
		while (i < username.size()) {
			user.append("'" + username.get(i) + "'");
			if(i == (username.size() -1)) {
				break;
			}else {
				user.append(",");
			}
			i++;			
		}
		
		StringBuilder whereBuilder = new StringBuilder();
		if(!StringUtil.isBlank(searchDate)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
		}

		if (StringUtils.isNotBlank(serviceType) && AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
			whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		} else {
			whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
		}

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy') AS receiptdttm, cr.receiptno, cr.REF_RECEIPTNO, TO_CHAR(cp.PAYMENTDATE, 'dd/MM/yyyy') AS paymentdttm, cr.receiptname, cr.REMARK ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" 	INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid AND cr.updateuser IN ("+user+")");
		queryBuilder.append(" AND cr.ATTRIBUTES not like '%R%' and cr.REF_RECEIPTNO is not NULL and cr.REMARK is not NULL ");
		queryBuilder.append(  whereBuilder.toString() );
		queryBuilder.append(" ORDER BY cr.receiptno ");
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptDate(row.getString(1));
				reportPayment.setReceiptNo(row.getString(2));
				reportPayment.setPaymentDate(row.getString(4));
				reportPayment.setRefRecNo(row.getString(3));
				reportPayment.setReceiptName(row.getString(5));
				reportPaymentDTO.addData(reportPayment);
			}
		});
		if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printDailyNewReceiptSaleReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}
	
	
	//Pichanan 07/08/2017
	@ResponseBody
	@RequestMapping(value = "/getDailyReceiptSaleReport.json", method = RequestMethod.GET)
	public ReportPaymentDTO getDailyReceiptSaleReport(HttpServletRequest request,
			@RequestParam(value = "printPdf", required = true) String printPdf,
			@RequestParam(value = "startDate", required = true) String searchDate,
			@RequestParam(value = "endDate", required = true) String searchEndDate,
			@RequestParam(value = "serviceType", required = true) String serviceType) throws Exception {

		System.out.println("printPdf = " + printPdf);
		System.out.println("startDate = " + searchDate);
		System.out.println("endDate = " + searchEndDate);

		StringBuilder whereBuilder = new StringBuilder();
		if (!StringUtil.isBlank(searchDate)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('" + searchDate
					+ "', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('" + searchEndDate + "', 'DD-MM-YYYY HH24:MI')");
		}

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(
				" SELECT  cr.receiptname,cr.RECEIPTNO,cr.taxid , TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy') AS receiptdttm ,  TO_CHAR(TR.UPDATEDTTM, 'dd/MM/yyyy') AS updatedttm,tr.reason ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN TRSREPRINT TR ON TR.RECEIPTID = cr.RECEIPTID  and tr.category = '2' and tr.reason is not null ");
		queryBuilder.append(" INNER JOIN CORPAYMENT CP ON CR.PAYMENTID = CP.PAYMENTID AND PAYMENTTYPE NOT IN ('AGENT','MNP') and cr.RECEIPTTYPE = 'FULL' ");
		//queryBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('02-05-2017 00:00', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('04-08-2017 23:59', 'DD-MM-YYYY HH24:MI') ");
		queryBuilder.append(whereBuilder.toString());
		queryBuilder.append(" ORDER BY TR.UPDATEDTTM ASC ");
		System.out.println("getDailyReceiptSaleReport : "+ queryBuilder.toString());

		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptName(row.getString(1));
				reportPayment.setReceiptNo(row.getString(2));
				reportPayment.setTaxId(row.getString(3));
				reportPayment.setReceiptDate(row.getString(4));
				reportPayment.setUpdateDate(row.getString(5));
				reportPayment.setReason(row.getString(6));
				reportPaymentDTO.addData(reportPayment);
				
			}
		});
		if (AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printDailyReceiptSaleReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}
	//end Pichanan 07/08/2017
	
	@ResponseBody
	@RequestMapping(value="/getPnExtDailyPaymentReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getPnExtDailyPaymentReportJSON(HttpServletRequest request,
		  @RequestParam(value="printPdf", required=true) String printPdf,
		  @RequestParam(value="startDate", required=true) String searchDate,
		  @RequestParam(value="endDate", required=true) String searchEndDate,
		  @RequestParam(value="receiptNo", required=true) String searchReceiptNo,
		  @RequestParam(value="shop", required=true) String searchShop,
		  @RequestParam(value="officer", required=true) String searchOfficer,
		  @RequestParam(value="soureType", required=true) String searchSoureType,
		  @RequestParam(value="serviceType", required=true) String serviceType
	) throws Exception {

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("endDate = "+ searchEndDate);
		System.out.println("receipt = "+ searchReceiptNo);
		System.out.println("shop = "+ searchShop);
		System.out.println("officer = "+ searchOfficer);
		System.out.println("soureType = "+ searchSoureType);
		System.out.println("serviceType = "+ serviceType);

		final Map<String, String> idMap = new HashMap<String, String>();

		StringBuilder whereBuilder = new StringBuilder();
		if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchEndDate)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
		}
		if(!"all".equals(searchOfficer)) {
			whereBuilder.append(" AND cp.officerid = '"+ searchOfficer+"' ");
		}

		if(!"all".equals(searchShop)) {
			whereBuilder.append(" AND ms.shopno = ' "+ searchShop+ "' ");
		}

		if("all".equals(searchSoureType)) {
			if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
				whereBuilder.append(" AND cp.paymenttype IN ('PENALTY_CHARGE','EXTEND_CHARGE') AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
			} else if ("PNEXT".equalsIgnoreCase(serviceType)) {
				whereBuilder.append(" AND cp.paymenttype IN ('PENALTY_CHARGE','EXTEND_CHARGE') AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
			} else {
				whereBuilder.append(" AND cp.paymenttype IN ('PENALTY_CHARGE','EXTEND_CHARGE') AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
			}
		}


		if(!"all".equals(searchSoureType) && searchSoureType != null) {
			whereBuilder.append(" AND cp.paymenttype = '"+searchSoureType+"' ");
		}

		if( StringUtils.isNotBlank(searchReceiptNo) ) {
			whereBuilder.append(" AND cr.receiptno = '"+searchReceiptNo+"' ");
		}

		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		//comment by NSD 09-03-2017
		//queryBuilder.append(" SELECT DISTINCT cp.paymentid, cp.paymenttype, cr.receiptno, cr.accountno, cr.receiptname, cp.collectionunit, ti.invoiceno, ti.charge, ti.vat, ti.totalcharge, cr.attributes, ts.charge, ts.vat, ts.totalcharge, cr.vatrate, cr.updateuser, ti.status, cr.discount ");
		queryBuilder.append(" SELECT DISTINCT cp.paymentid, cp.paymenttype, cr.receiptno, cr.accountno, cr.receiptname, cp.collectionunit, ti.invoiceno, ti.charge, ti.vat, ti.totalcharge, cr.attributes, cr.charge, cr.vat, cr.totalcharge, cr.vatrate, cr.updateuser, ti.status, cr.discount, (select MESSAGE from MASCHANGERATE m where ti.CURRENCYCODE = m.CURRENCYCODE) as currency, cr.VATRATE, (select m.RATEUNIT from MASCHANGERATE m where ti.CURRENCYCODE = m.CURRENCYCODE) as currencyRate, ti.CURRENCYCODE ");
		queryBuilder.append(" FROM correceipt cr ");
		queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
		queryBuilder.append(" LEFT JOIN tmpaccount ta ON ta.paymentid = cp.paymentid ");
		// Fix by EPIS8 23/12/2016 refer issue no. 48 add "and ti.RECEIPTID = cr.RECEIPTID"
		queryBuilder.append(" LEFT JOIN tmpinvoice ti ON ti.paymentid = cp.paymentid and ti.RECEIPTID = cr.RECEIPTID");
		// End Fix by EPIS8 23/12/2016 refer issue no. 48  add "and ti.RECEIPTID = cr.RECEIPTID"
		queryBuilder.append(" LEFT JOIN tmpinvoiceservice ts ON ts.receiptid = cr.receiptid ");
		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
		queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
		queryBuilder.append( whereBuilder.toString() );
		queryBuilder.append(" ORDER BY cp.paymenttype,cr.receiptno ");
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setPaymentId(row.getString(1));
				reportPayment.setPaymentType(row.getString(2));
				reportPayment.setReceiptNo(row.getString(3));
				reportPayment.setAccountNo(row.getString(4));
				reportPayment.setReceiptName(row.getString(5));
				reportPayment.setCollectionUnit(row.getString(6));
				reportPayment.setInvoiceNo(row.getString(7));
				reportPayment.setCharge((row.getString(8) == null)?row.getString(12): row.getString(8));
				//reportPayment.setVat((row.getString(9) == null)? row.getString(13): row.getString(9));
				reportPayment.setVat(row.getString(9));//by NSD กรณีเงินรายได้อื่นไม่มีภาษี
				reportPayment.setAttributes(row.getString(11));
				reportPayment.setVatRate(row.getString(15));
				reportPayment.setPaymentUser(row.getString(16));
				String sumCharge = (row.getString(10) == null)?row.getString(14): row.getString(10);
				reportPayment.setTotalCharge((row.getString(10) == null)?row.getString(14): row.getString(10));
				idMap.put(row.getString(7), row.getString(1));
				if(null!=reportPayment.getInvoiceNo()){//by NSD 19-02-2017 there is no invoice case topup
					reportPayment.setStatus(null!=row.getString(17)?(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : "-"):"-");
				}else{
					reportPayment.setStatus(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : "-");
				}

				// Fix by EPIS8 reference by issue no 42. 23/12/2016
				/*if(row.getString(17) != null) {
					if(row.getString(17).equalsIgnoreCase(INVOICE_FROM_WRITEOFF) || row.getString(17).equalsIgnoreCase(INVOICE_FROM_TBOSS)) { // WriteOff type Invoice
						reportPayment.setStatus(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : (reportPayment.getAttributes().indexOf("C") > -1 ? "หนี้สูญรับคืน" : "รอหักล้างหนี้สูญ"));
					} else { // "N" - normal billing
						reportPayment.setStatus(reportPayment.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : (reportPayment.getAttributes().indexOf("C") > -1 ? "ปกติ" : "รอหักล้าง"));
					}
				}*/
				// End Fix by EPIS8 reference by issue no 42.

				reportPayment.setDiscount(row.getString(18));
				reportPayment.setMsgForeign(row.getString(19));

				String currencyCode = row.getString(22)!=null?row.getString(22):"12";
				BigDecimal vatRate = new BigDecimal(row.getString(20)!=null?row.getString(20): "0");
				BigDecimal currencyRate = new BigDecimal(row.getString(21)!=null?row.getString(21):"0");
				BigDecimal hundPer = new BigDecimal("100");
				BigDecimal hundPerVat = null!=vatRate?hundPer.add(vatRate):hundPer.add(BigDecimal.ZERO);

				// Case Foreign
				if (currencyCode!=null) {
					if (!currencyCode.equalsIgnoreCase("12") /*|| !row.getString(19).equalsIgnoreCase("THB")*/) {
						//totalCharge
						BigDecimal totalCharge = new BigDecimal(reportPayment.getTotalCharge());
						totalCharge = totalCharge.multiply(currencyRate);
						reportPayment.setTotalCharge(totalCharge.toString());
						//charge
						BigDecimal charge = totalCharge.multiply(hundPer).divide(hundPerVat, 2, BigDecimal.ROUND_HALF_UP);
						reportPayment.setCharge(charge.toString());
						//vat
						BigDecimal vat = totalCharge.multiply(vatRate).divide(hundPerVat, 2, BigDecimal.ROUND_HALF_UP);
						reportPayment.setVat(vat.toString());
						reportPayment.setTotalChargeForeign((row.getString(19)!=null?row.getString(19)+" ":"")+sumCharge);
					}
				}


				payTypeName = convertPayTypeName(reportPayment.getPaymentType());
				reportPayment.setPayTypeName(payTypeName);

				reportPaymentDTO.addData(reportPayment);
			}
		});
		String tmpPaymentId = "";
		List<ReportPayment> reportPaymentOrderType = new ArrayList<ReportPayment>();

		prepareData(reportPaymentDTO, tmpPaymentId, reportPaymentOrderType);

		reportPaymentDTO.setData(reportPaymentOrderType);
		if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printPnExtDailyPaymentReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}

	@RequestMapping(value="getSourceName.json", method=RequestMethod.GET)
	public MapAgentDTO getSourceName(ModelMap modelMap) {
		final MapAgentDTO dto = new MapAgentDTO();
		episJdbcTemplate.query("select a.code,a.name,a.type from (SELECT MESSAGECODE code, MESSAGECODE name, CATEGORY type "
				+ "from ARCENUMS WHERE CATEGORY in ('source') "
				+ " union "
				+ " select AGENT_CODE code,AGENT_NAME name,'Agent' type from MAS_AGENT)a ORDER BY a.type DESC ", new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				MapAgent result = new MapAgent();
				result.setAgentCode(row.getString("code"));
				result.setAgentName(row.getString("name"));
				result.setAgentType(row.getString("type"));
				dto.addData(result);
			}
		});
		modelMap.addAttribute("statusCode", "0");
		dto.setStatusCode("0");

		return dto;
	}


	@ResponseBody
	@RequestMapping(value="/getDiscountDailyCreditReport.json", method=RequestMethod.GET)
	public ReportPaymentDTO getDiscountDailyCreditReportJSON(HttpServletRequest request,
			@RequestParam(value="printPdf", required=true) String printPdf, 
			@RequestParam(value="startDate", required=true) String searchDate,
			@RequestParam(value="toDate", required=true) String searchDateTo,
			@RequestParam(value="period", required=true) String searchPeriod,
			@RequestParam(value="shop", required=true) String searchShop,
			@RequestParam(value="officer", required=true) String searchOfficer) throws Exception {		

		System.out.println("printPdf = "+ printPdf);
		System.out.println("startDate = "+ searchDate);
		System.out.println("shop = "+ searchShop);
		System.out.println("officer = "+ searchOfficer);

		
	       ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		   reportPaymentDTO = reportService.getDiscountDailyCreditReportService(request, searchDate, searchDateTo, searchPeriod, searchShop, searchOfficer);
		   if(AppUtil.isStringHasValue(printPdf)) {
			request.setAttribute("printDiscountDailyCreditReport", reportPaymentDTO);
		}
		return reportPaymentDTO;
	}
	
	// Report : getExchangeReport
			//picht 14/09/2017
			@ResponseBody
			@RequestMapping(value="/getExchangeReport.json", method=RequestMethod.GET)
			public ReportPaymentDTO getExchangeReporttJSON(HttpServletRequest request,
					@RequestParam(value="printPdf", required=true) String printPdf, 
					@RequestParam(value="startDate", required=true) String searchStartDate,
					@RequestParam(value="endDate", required=true) String searchEndDate,
					@RequestParam(value="shop", required=true) String searchShop,
					@RequestParam(value="officer", required=true) String searchOfficer
					) throws Exception {
					
				System.out.println("printPdf = "+ printPdf);
				System.out.println("startDate = "+ searchStartDate);
				System.out.println("endDate = "+ searchEndDate);
				System.out.println("shop = "+ searchShop);
				System.out.println("officer = "+ searchOfficer);

				StringBuilder whereBuilder = new StringBuilder();
			
				if(!"all".equals(searchOfficer)) {
					whereBuilder.append(" AND cr.UPDATEUSER = '"+ searchOfficer+"'");
				}
				
				if(!"-1".equals(searchShop)) {
					whereBuilder.append(" AND ms.shopno = '"+ searchShop+"'");
				}

				
				final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
				StringBuilder queryBuilder = new StringBuilder(); 
				queryBuilder.append(" SELECT CR.RECEIPTNO, CR.ACCOUNTNO, CR.RECEIPTNAME , TI.INVOICENO, M.MESSAGE AS CURRENCY, CR.TOTALCHARGE, Cp.CURRENCYRATE, ");
				queryBuilder.append(" TI.CURRENCYCODE, CR.ATTRIBUTES, SAP.TB_CODETEXT AS COLLECTIONUNIT ");
			//	queryBuilder.append("  ");
				queryBuilder.append(" FROM CORRECEIPT CR ");
				queryBuilder.append(" INNER JOIN CORPAYMENT CP ON CP.PAYMENTID = CR.PAYMENTID ");
				queryBuilder.append(" LEFT JOIN TMPACCOUNT TA ON TA.PAYMENTID = CP.PAYMENTID ");
				queryBuilder.append(" LEFT JOIN TMPINVOICE TI ON TI.PAYMENTID = CP.PAYMENTID AND TI.RECEIPTID = CR.RECEIPTID  ");
				queryBuilder.append(" LEFT JOIN TMPINVOICESERVICE TS ON TS.RECEIPTID = CR.RECEIPTID  ");
				queryBuilder.append(" LEFT JOIN MASCHANGERATE M ON TI.CURRENCYCODE = M.CURRENCYCODE ");
				queryBuilder.append(" LEFT JOIN MASSHOP MS ON MS.SHOPID = CP.SHOPID ");
				queryBuilder.append(" LEFT JOIN SAP_REVENUE_DEPT@CATPCU1_DEV SAP ON SUBSTR(CP.COLLECTIONUNIT,1,1)||'000' = SAP.TB_REGION_CODE ");
				queryBuilder.append(" WHERE CR.PAYMENTID = CP.PAYMENTID  AND  CP.PAYMENTTYPE IN ('SERVICE_CHARGE', 'TBOSS','OTBOSS') ");
				queryBuilder.append(" AND CR.RECEIPTDTTM BETWEEN TO_TIMESTAMP('"+ searchStartDate + "', 'DD-MM-YYYY HH24:MI') ");
				queryBuilder.append(" AND TO_TIMESTAMP('"+ searchEndDate +"', 'DD-MM-YYYY HH24:MI')   and M.MESSAGE not in ('THB')   ");
				queryBuilder.append( whereBuilder.toString() );
				queryBuilder.append(" ORDER BY COLLECTIONUNIT,CR.RECEIPTNO ");
				
				System.out.println("getExchangeReport : " + queryBuilder.toString());

				episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet row) throws SQLException {
						ReportPayment reportPayment = new ReportPayment();

						BigDecimal ChargeR = (row.getBigDecimal(6));
						BigDecimal CurrencyRateR = (row.getBigDecimal(7));
					//	BigDecimal ss = ChargeR*CurrencyRateR;
						reportPayment.setReceiptNo(row.getString(1));
						reportPayment.setAccountNo(row.getString(2));
						reportPayment.setReceiptName(row.getString(3));
						reportPayment.setInvoiceNo(row.getString(4));
						reportPayment.setCurrencyCode(row.getString(5));
						reportPayment.setChargeR(row.getBigDecimal(6));
						reportPayment.setCurrencyRateR(row.getBigDecimal(7));
						//reportPayment.setTotalChargeR(ChargeR*CurrencyRateR);
//						reportPayment.setReason(row.getString(9));
//						reportPayment.setPaymentType(row.getString(10));
//						reportPayment.setPaymentUser(row.getString(11));
						reportPaymentDTO.addData(reportPayment);
					}
				});
				
				
			
				if(AppUtil.isStringHasValue(printPdf)) {
					request.setAttribute("printExchangeReport", reportPaymentDTO);
				}
				return reportPaymentDTO;
			}
			
			//end picht 14/09/2017
			
			@ResponseBody
			@RequestMapping(value="/getSummaryStatisticPaymentReport.json", method=RequestMethod.GET)
			public ReportPaymentDTO getSummaryStatisticPaymentReportJSON(HttpServletRequest request,
					@RequestParam(value="printPdf", required=true) String printPdf, 
					@RequestParam(value="startDate", required=true) String searchDate,
					@RequestParam(value="endDate", required=true) String searchEndDate,
					@RequestParam(value="payCase", required=true) String searchPayCase,
					@RequestParam(value="shop", required=true) String searchShop,
					@RequestParam(value="soureType", required=true) String searchSoureType,
					@RequestParam(value="serviceType", required=true) String serviceType
					) throws Exception {

				MasterDataController masterDataController = new MasterDataController();
				List<MasterDataDTO> listMaster = new ArrayList<>();
				
				StringBuilder whereBuilder = new StringBuilder();
				if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchEndDate)) {
					whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
				}
		/*		if (StringUtils.isNotBlank(serviceType) && AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)) {
					whereBuilder.append(" AND cp.paymenttype IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
				} else {
					whereBuilder.append(" AND cp.paymenttype NOT IN ('"+AppConstants.PAYMENT_TYPE_MNP+"','"+AppConstants.PAYMENT_TYPE_AGENT+"') ");
				}*/
				if(!"".equals(searchSoureType)) {
					whereBuilder.append(" AND cp.paymenttype = '"+ searchSoureType+"'");
				}else {
					listMaster = masterDataController.getGroupData("MANUAL_PAY_SRC_TYPE");
					List<String> keylist = new ArrayList<String>();
					StringBuilder key = new StringBuilder();
					for(MasterDataDTO data :listMaster) {
						keylist.add(data.getKey());
					}
					int i = 0;
					while (i < keylist.size()) {
						key.append("'" + keylist.get(i) + "'");
						if(i == (keylist.size() -1)) {
							break;
						}else {
							key.append(",");
						}
						i++;			
					}
					whereBuilder.append(" AND cp.paymenttype in ("+key+")");
				}
				
				if(!"-1".equals(searchPayCase)) {
					whereBuilder.append(" AND met.CODE = '"+searchPayCase+"'");
				}
				
				if(!"-1".equals(searchShop)) {
					whereBuilder.append(" AND ms.shopno = "+ " '"+searchShop+"' ");
				}
		        whereBuilder.append(" AND cr.ATTRIBUTES NOT LIKE '%R%' ");

				final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
				StringBuilder queryBuilder = new StringBuilder();
				//queryBuilder.append(" SELECT DISTINCT cr.updateuser, COUNT(*) AS amount, SUM(cr.totalcharge) AS totalcharge ");
//				queryBuilder.append(" SELECT aaa.UPDATEUSER, count(*) amount, sum(aaa.totalcharge) from ( ");//by NSD 24-04-2017
//				queryBuilder.append(" SELECT DISTINCT cr.updateuser, COUNT(*) AS amount, SUM(met.AMOUNT) AS totalcharge, cr.RECEIPTNO ");//by NSD 21-04-2017
//				queryBuilder.append(" FROM correceipt cr ");
//				queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
//				queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
//				//queryBuilder.append(" LEFT JOIN trsmethod met ON cp.PAYMENTID = met.PAYMENTID ");
//				queryBuilder.append(" INNER JOIN trsmethod met ON cp.PAYMENTID = met.PAYMENTID ");
//				if(!"-1".equals(searchPayCase)) {
//					queryBuilder.append(" AND met.CODE = '"+searchPayCase+"'");
//				}
//				queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
//				queryBuilder.append( whereBuilder.toString() );
//				queryBuilder.append(" GROUP BY cr.updateuser, cr.RECEIPTNO ");
//				queryBuilder.append(" ORDER BY cr.updateuser ASC");
//				queryBuilder.append(" ) aaa GROUP BY aaa.UPDATEUSER ORDER BY aaa.UPDATEUSER asc ");
				
				
				//picht 29082017
				
				queryBuilder.append(" SELECT cp.paymenttype,cr.paymentid,DECODE_PAYTYPE(cr.paymentid) as paytype, ");
				queryBuilder.append("  1 AS CNT,cr.totalcharge AS totalcharge ");
				queryBuilder.append(" FROM correceipt cr ");
				queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
				queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
				queryBuilder.append(" INNER JOIN trsmethod met ON cp.PAYMENTID = met.PAYMENTID ");
				queryBuilder.append(" WHERE cr.paymentid = cp.paymentid  ");
				queryBuilder.append(whereBuilder.toString());
				queryBuilder.append(" GROUP BY cr.updateuser,cr.paymentid,cr.totalcharge,cp.paymenttype,DECODE_PAYTYPE(cr.paymentid)");
				queryBuilder.append(" order by cp.paymenttype");
//				System.out.println("getSummaryDailyPaymentReport : "+ queryBuilder.toString());
				
				
				
				episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet row) throws SQLException {
						ReportPayment reportPayment = new ReportPayment();
						reportPayment.setPaymentType(row.getString(1));
					//	reportPayment.setPaymentId(row.getString(2));
						reportPayment.setPayTypeName(row.getString(3));
						reportPayment.setCntReceipt(row.getInt(4));
						reportPayment.setTotalCharge(row.getString(5));
						reportPaymentDTO.addData(reportPayment);
					}
				});
				
				
				//Remove duplicate
						Map<String,ReportPayment> dataMap = new TreeMap<String,ReportPayment>();
						List<ReportPayment> dataTmpList = reportPaymentDTO.getData();
						for(int i = 0 ; i < dataTmpList.size() ; i++){
							ReportPayment rpTmp =dataTmpList.get(i);
							ReportPayment rp = dataMap.get(rpTmp.getPaymentType() +rpTmp.getPayTypeName());
							
							if(rp != null){
								/*System.out.println("1loop : "+ rpTmp.getPaymentUser() +rpTmp.getPayTypeName());*/
							
								double sumTotalCharge = (Double.parseDouble(rp.getTotalCharge())) + (Double.parseDouble(rpTmp.getTotalCharge()));
								
								rp.setCntReceipt(rp.getCntReceipt()+rpTmp.getCntReceipt());
								rp.setTotalCharge(String.valueOf(sumTotalCharge));
								rp.setReceiptFr(rp.getReceiptNo());
								rp.setReceiptTo(rpTmp.getReceiptNo());
											
							}else{
								System.out.println("2paymentID : "+ rpTmp.getPaymentType() + rpTmp.getPayTypeName());

								dataMap.put(rpTmp.getPaymentType()+rpTmp.getPayTypeName(), rpTmp);
							}
						}
						
						//Convert to list.
						List<ReportPayment> dataList = new ArrayList<ReportPayment>();
						for (Map.Entry<String, ReportPayment> entry : dataMap.entrySet()){
							dataList.add(entry.getValue());
						}
						
						reportPaymentDTO.setData(dataList);
				
						//end picht 29082017
				if(AppUtil.isStringHasValue(printPdf)) {
					request.setAttribute("printSummaryDailyPaymenReport", reportPaymentDTO);
				}
				return reportPaymentDTO;
			}
			
			// Report : cancel-payment-report.
			@ResponseBody
			@RequestMapping(value="/getListCancelPaymentReport.json", method=RequestMethod.GET)
			public ReportPaymentDTO getListCancelPaymentReportJSON(HttpServletRequest request,
					@RequestParam(value="printPdf", required=true) String printPdf, 
					@RequestParam(value="startDate", required=true) String searchDate,
					@RequestParam(value="endDate", required=true) String searchEndDate,
					@RequestParam(value="invNo", required=true) String searchInvNo,
					@RequestParam(value="shop", required=true) String searchShop,
					@RequestParam(value="officer", required=true) String searchOfficer,
					@RequestParam(value="serviceType", required=true) String serviceType
					) throws Exception {

				System.out.println("printPdf = "+ printPdf);
				System.out.println("startDate = "+ searchDate);
				System.out.println("endDate = "+ searchEndDate);
				System.out.println("invNo = "+ searchInvNo);
				System.out.println("shop = "+ searchShop);
				System.out.println("officer = "+ searchOfficer);
				System.out.println("serviceType = "+ serviceType);
				
				StringBuilder whereBuilder = new StringBuilder();
				if(!StringUtil.isBlank(searchDate) && !StringUtil.isBlank(searchEndDate)) {
					whereBuilder.append(" AND cr.CANCELDTTM BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
//					whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchEndDate+"', 'DD-MM-YYYY HH24:MI')");
				}
				if(!StringUtil.isBlank(searchInvNo) ) {
					whereBuilder.append(" AND cr.RECEIPTNO = '"+ searchInvNo +"' ");
				}
				if(!"-1".equals(searchOfficer)) {
					whereBuilder.append(" AND cp.officerid = "+ searchOfficer);
				}
				
				if(!"-1".equals(searchShop)) {
					whereBuilder.append(" AND ms.shopno = '"+ searchShop +"' ");
				}
				
				// whereBuilder.append(" AND cp.paymenttype = 'SERVICE_CHARGE' ");

				final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
				StringBuilder queryBuilder = new StringBuilder();
				queryBuilder.append(" SELECT DISTINCT cp.paymentid, cr.accountno, cr.receiptname, cp.collectionunit, ti.invoiceno, cr.receiptno, cr.canceledby, TO_CHAR(cr.receiptdttm, 'dd/MM/yyyy') AS receiptdttm, ti.charge, ti.vat, ti.totalcharge, ts.charge, ts.vat, ts.totalcharge, TO_CHAR(cr.canceldttm, 'dd/MM/yyyy') AS canceldttm ");
				queryBuilder.append(" FROM correceipt cr ");
				queryBuilder.append(" INNER JOIN corpayment cp ON cp.paymentid = cr.paymentid ");
				queryBuilder.append(" LEFT JOIN tmpaccount ta ON ta.paymentid = cp.paymentid ");
				queryBuilder.append(" LEFT JOIN tmpinvoice ti ON ti.paymentid = cp.paymentid ");
				queryBuilder.append(" LEFT JOIN tmpinvoiceservice ts ON ts.receiptid = cr.receiptid ");
				queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
				queryBuilder.append(" WHERE cr.paymentid = cp.paymentid ");
				queryBuilder.append(" AND (cr.attributes LIKE '%R%') ");
				queryBuilder.append( whereBuilder.toString() );
				queryBuilder.append(" ORDER BY cr.receiptno ");
				
				episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet row) throws SQLException {
						ReportPayment reportPayment = new ReportPayment();
						reportPayment.setPaymentId(row.getString(1));
						reportPayment.setAccountNo(row.getString(2));
						reportPayment.setReceiptName(row.getString(3));
						reportPayment.setCollectionUnit(row.getString(4));
						reportPayment.setInvoiceNo(row.getString(5));
						reportPayment.setReceiptNo(row.getString(6));
						reportPayment.setCancelByUser(row.getString(7));
						reportPayment.setReceiptDate(row.getString(8));
						reportPayment.setCharge((row.getString(9) == null)?row.getString(12): row.getString(9));
						reportPayment.setVat((row.getString(10) == null)? row.getString(13): row.getString(10));
						reportPayment.setTotalCharge((row.getString(11) == null)?row.getString(14): row.getString(11));
						reportPayment.setCancelDate(row.getString(15));
						reportPaymentDTO.addData(reportPayment);
					}
				});
				final ReportPaymentDTO subReportPaymentDTO = new ReportPaymentDTO();
				for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
					StringBuilder subQueryBuilder = new StringBuilder();
					subQueryBuilder.append(" SELECT name, chequeno FROM trsmethod WHERE paymentid = "+ reportPayment.getPaymentId() +" ORDER BY updatedttm ");
					episJdbcTemplate.query(subQueryBuilder.toString(), new RowCallbackHandler(){
						@Override
						public void processRow(ResultSet row) throws SQLException {
							ReportPayment reportPayment = new ReportPayment();
							reportPayment.setPaymentCash(row.getString(1));
							reportPayment.setDocumentNo(row.getString(2));
							subReportPaymentDTO.addData(reportPayment);
						}
					});
				}
				int i = 0;
				List<ReportPayment> reportPaymentOrderType = new ArrayList<ReportPayment>();
				for(ReportPayment reportPayment : reportPaymentDTO.getData()) {
					reportPayment.setPaymentCash(subReportPaymentDTO.getData().get(i).getPaymentCash());
					reportPayment.setDocumentNo(subReportPaymentDTO.getData().get(i).getDocumentNo());
					reportPaymentOrderType.add(reportPayment);
					i++;
				}
				reportPaymentDTO.setData(reportPaymentOrderType);
				if(AppUtil.isStringHasValue(printPdf)) {
					request.setAttribute("printCancelPaymentReport", reportPaymentDTO);
				}
				return reportPaymentDTO;
			}
			
			
			

}

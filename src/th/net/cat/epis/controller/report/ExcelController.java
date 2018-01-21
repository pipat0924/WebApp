package th.net.cat.epis.controller.report;

import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.stripToEmpty;
import static th.net.cat.epis.util.AppConstants.FILE_TYPE_JRXML;
import static th.net.cat.epis.util.AppConstants.JASPER_JRXML_PATH;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.ReportPayment;
import th.net.cat.epis.dto.ReportPaymentDTO;
import th.net.cat.epis.repo.EnumRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.MoneyTransferRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.ServiceRepository;
import th.net.cat.epis.repo.TransactionRepository;
import th.net.cat.epis.service.EpisService;
import th.net.cat.epis.service.ExcelReportService;
import th.net.cat.epis.to.report.DailyPaymentTO;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;

@Controller
@SessionAttributes(value={ AppConstants.DAILY_PAYMENT_DEDUCT_REPORT_KEY })
public class ExcelController implements ServletContextAware {
	private static Logger logger = Logger.getLogger(ExcelController.class);
	@Autowired ReceiptRepository receiptRepo;
	@Autowired TransactionRepository transactionRepo;
	@Autowired MoneyTransferRepository moneyTransferRepo;
	@Autowired InvoiceRepository invoiceRepo;
	@Autowired ServiceRepository serviceRepo;
	@Autowired OfficerRepository officerRepo;
	@Autowired EnumRepository enumRepo;
	@Autowired EpisService episService;
	@Resource(name="episJdbcTemplate") 
	JdbcTemplate episJdbcTemplate;
	@Autowired
	ExcelReportService excelReportService;
	@Autowired 
	private ReportController reportController;
	
	private ServletContext context;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;		
	}
	
	@RequestMapping(value="/printDailyPaymentDeductReportWithUpdate.xls", method=RequestMethod.POST)
	public void printDailyPaymentDeductReportWithUpdate(HttpServletRequest request, HttpServletResponse response, Model model
		  //,@RequestBody ReportPaymentDTO	 reportPaymentDTO
		  ) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyPaymentDeduct";
		ReportPaymentDTO	 reportPaymentDTO =(ReportPaymentDTO)model.asMap().get(AppConstants.DAILY_PAYMENT_DEDUCT_REPORT_KEY);
		logger.debug("reportPaymentDTO xxx["+reportPaymentDTO+"]");
		
		String sdate=reportPaymentDTO.getSdate();
		logger.debug("sdate["+sdate+"]");
		logger.debug("reportPaymentDTO["+reportPaymentDTO+"]");
		Map<String, Object> parameters = new HashMap<String, Object>();
		//reportController.getDailyPaymentDeductReportJSON(request, printXls, sdate,edate, period);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		//ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailyPaymentDeductReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		int sumRowAll = 0;
		double amountSumAll = 0;
		double vatSumAll = 0;
		double receivedSumAll = 0;
		double total = 0;
		
			List<ReportPayment> reportPayments = reportPaymentDTO.getData();
			// Update Deduction No
			episService.updateDeductionNo(reportPayments);
			for(int i=0; i<reportPayments.size(); i++) {
				
				ReportPayment reportPayment = reportPayments.get(i);
				String amount3tredecim = reportPayment.getAmount3tredecim().trim().replaceAll(",","");
				String amount69bis = reportPayment.getAmount69bis().trim().replaceAll(",","");
				String amount69tre = reportPayment.getAmount69tre().trim().replaceAll(",","");
				String remark = reportPayment.getRemark();
				logger.debug("reportPayment getAmount3tredecim["+reportPayment.getAmount3tredecim()+"] ["+amount3tredecim+"]");
				logger.debug("reportPayment getAmount69bis["+reportPayment.getAmount69bis()+"] ["+amount69bis+"]");
				logger.debug("reportPayment getAmount69tre["+reportPayment.getAmount69tre()+"] ["+amount69tre+"]");;
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setDeductionNo(AppUtil.toString(reportPayment.getDeductionNo()));
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setAmount3tredecim(AppUtil.toCurrencyPrint(new BigDecimal(amount3tredecim)));
				dailyPaymentTO.setAmount69bis(AppUtil.toCurrencyPrint(new BigDecimal(amount69bis)));
				dailyPaymentTO.setAmount69tre(AppUtil.toCurrencyPrint(new BigDecimal(amount69tre)));
				dailyPaymentTO.setRemark(AppUtil.toString(remark));
				
				sumRowAll = sumRowAll + 1;
				amountSumAll = amountSumAll + new Double(amount3tredecim);
				vatSumAll = vatSumAll + new Double(amount69bis);
				receivedSumAll = receivedSumAll + new Double(amount69tre);
				
				total = amountSumAll + vatSumAll + receivedSumAll;
				
				collections.add(dailyPaymentTO);
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(sdate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setSumRowAll(AppUtil.toString(sumRowAll));
		documentObject.setSumAmount3tredecim(AppUtil.toCurrencyPrint(amountSumAll));
		documentObject.setSumAmount69bis(AppUtil.toCurrencyPrint(vatSumAll));
		documentObject.setSumAmount69tre(AppUtil.toCurrencyPrint(receivedSumAll));
		
		documentObject.setTotal(AppUtil.toCurrencyPrint(total));
		
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToExcleOutputStream(response, jasperPrints);
	}
	
	@RequestMapping(value="/printDailyPaymentReport.xls", method=RequestMethod.POST)
	public void  printDailyPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model
		  ) throws Exception {
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String accountID = request.getParameter("accountID");
		String vatRate = request.getParameter("vatRate");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String soureType = request.getParameter("soureType");
		String serviceType = request.getParameter("serviceType");
		reportController.getDailyPaymentReportJSON(request, printPdf, startDate, endDate, accountID, vatRate, shop, officer, soureType, serviceType);
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailyPaymentReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setCollectionUnit(AppUtil.toString(reportPayment.getCollectionUnit()));
				dailyPaymentTO.setInvoiceNo(AppUtil.toString(reportPayment.getInvoiceNo()));
				dailyPaymentTO.setPaymentCash(reportPayment.getPaymentCash());
				dailyPaymentTO.setDocumentNo(reportPayment.getDocumentNo());
				dailyPaymentTO.setChargeB(new BigDecimal(reportPayment.getCharge()));
				dailyPaymentTO.setVatB(new BigDecimal(null!=reportPayment.getVat()?reportPayment.getVat():"0.00"));
				dailyPaymentTO.setTotalChargeB(new BigDecimal(reportPayment.getTotalCharge()));
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				dailyPaymentTO.setDiscount(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getDiscount()?!reportPayment.getDiscount().equalsIgnoreCase("")?reportPayment.getDiscount():"0":"0")));
				dailyPaymentTO.setTotalChargeForeign(reportPayment.getMsgForeign()+" "+ AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getTotalCharge()?!reportPayment.getTotalCharge().equalsIgnoreCase("")?reportPayment.getTotalCharge():"0":"0")));
				dailyPaymentTO.setTaxId(reportPayment.getTaxId());
				dailyPaymentTO.setAgentCode(reportPayment.getAgentCode());
				dailyPaymentTO.setAgentName(reportPayment.getAgentName());
				dailyPaymentTO.setRef1(reportPayment.getRef1());
				dailyPaymentTO.setRef2(reportPayment.getRef2());
				dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());
				dailyPaymentTO.setTotalChargeB(new BigDecimal (reportPayment.getTotalCharge()));

				
				String f = String.format("SUM(J%d,K%d)",new Object[] { i+7,i+7});
				System.out.println(f);
				dailyPaymentTO.setTotalChargeFomular(f);
					
		
				dailyPaymentTO.setPaymentType(reportPayment.getPayTypeName());

				
				collections.add(dailyPaymentTO);
			
				
			}
		}
		
//		DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
//		dailyPaymentTO.setRowSummary("Y");
//		collections.add(dailyPaymentTO);
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
	
		 byte[] outArray = excelReportService.generateExcelReportPrintDailyPaymentReportTemplate(context,documentObject, collections,serviceType);
		 response.setContentType("application/ms-excel");
		 response.setContentLength(outArray.length);
		 response.setHeader("Expires:", "0"); // eliminates browser caching
		 if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)){
			 response.setHeader("Content-Disposition", "attachment; filename=printAgentDailyPaymentReport.xls");
		 }else if(AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(serviceType)){
			 response.setHeader("Content-Disposition", "attachment; filename=printMNPDailyPaymentReport.xls");
		 }else{
			 response.setHeader("Content-Disposition", "attachment; filename=printDailyPaymentReport.xls");
		 	}
		 OutputStream outStream = response.getOutputStream();
		 outStream.write(outArray);
		 outStream.flush();
	}
	
	//pichanan 20170914
	@RequestMapping(value="/printChaquePaymentReport.xls", method=RequestMethod.POST)
	public void printChaquePaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		String JASPER_JRXML_FILENAME = "EpisDailyChequePayment";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
//		String soureType = request.getParameter("soureType");
	//	Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getChequePaymentReportJSON(request, printPdf, startDate, endDate, shop, officer);
	//	List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printChaquePaymentReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();

		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setPaymentDate(reportPayment.getPaymentDate());
				dailyPaymentTO.setPaymentTime(reportPayment.getPaymentTime());
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setSumAmt(new BigDecimal (reportPayment.getSumAmount()));
				dailyPaymentTO.setChequeNo(reportPayment.getChequeno());
				dailyPaymentTO.setBranchCode(reportPayment.getPublisherid());
				dailyPaymentTO.setBranchName(reportPayment.getPublisher());
				dailyPaymentTO.setRemark(reportPayment.getReason());


				collections.add(dailyPaymentTO);
			}
			
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			collections.add(dailyPaymentTO);
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		//String paydate = startDate + " " + endDate;
		documentObject.setPaymentDate(startDate.replaceAll("-", "/")+" - "+endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		

		//parameters.put("ReportSource", documentObject);

		
		 byte[] outArray = excelReportService.generateExcelReportPrintChequePaymentReportTemplate(context,documentObject, collections);
		 response.setContentType("application/ms-excel");
		 response.setContentLength(outArray.length);
		 response.setHeader("Expires:", "0"); // eliminates browser caching
		 response.setHeader("Content-Disposition", "attachment; filename=printDailyChequePaymentReport.xls");
		 OutputStream outStream = response.getOutputStream();
		 outStream.write(outArray);
		 outStream.flush();
	}
	

	@RequestMapping(value="/printDailyPaymentDeductReportStore1.json", method=RequestMethod.POST)
	@ResponseBody
	public String printDailyPaymentDeductReportStore(HttpServletRequest request, HttpServletResponse response, Model model,
		  @RequestBody ReportPaymentDTO	 reportPaymentDTO) throws Exception {
		String tokenXls=AppConstants.DAILY_PAYMENT_DEDUCT_REPORT_KEY;//"DailyPaymentDeductReportStoreKey";
		model.addAttribute(tokenXls,reportPaymentDTO);
		logger.debug("tokenXls["+tokenXls+"]");
		return tokenXls;
	}
	
	@RequestMapping(value="/printDailyPaymentReport.json", method=RequestMethod.POST)
	@ResponseBody
	public String printDailyPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestBody ReportPaymentDTO	 reportPaymentDTO) throws Exception {
		String tokenXls=AppConstants.DAILY_PAYMENT_DEDUCT_REPORT_KEY;//"DailyPaymentDeductReportStoreKey";
		model.addAttribute(tokenXls,reportPaymentDTO);
		logger.debug("tokenXls["+tokenXls+"]");
		return tokenXls;
	}
	
	@RequestMapping(value="/printSalesFullTaxDailyReport.xls", method=RequestMethod.POST)
	public void printSalesFullTaxDailyXLSReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSalesFullTaxDailyPayXls";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String type = stripToEmpty(request.getParameter("type"));
		String serviceType = stripToEmpty(request.getParameter("serviceType"));
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getDailySalesTaxReportJSON(request, printPdf, startDate, endDate, type, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailySalesTaxReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
//		Officer object = officerRepo.findOne(new Long(officer)); 
//		officer = object == null ? "": object.getCode();
		int rowPaymentUser = 0;
		double chargePaymentUser = 0, vatPaymentUser = 0, totalChargePaymentUser = 0;
		
		int row7Percent = 0;
		double charge7Percent = 0, vat7Percent = 0, totalCharge7Percent = 0;
		
		int row0Percent = 0;
		double charge0Percent = 0, vat0Percent = 0, totalCharge0Percent = 0;
		/*ICE FIXED CODE ข้อ 44 - set Parameter รวมทั้งสิ้น */
		int rowAll = 0;
		double chargeAll= 0, vatAll = 0, totalChargeAll = 0;
		
		if(reportPaymentDTO != null) {
			List<ReportPayment> reportPayments = reportPaymentDTO.getData();
			for(int i=0; i<reportPayments.size(); i++) {
				ReportPayment reportPayment = reportPayments.get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				
				String charge = null!=reportPayment.getCharge()?reportPayment.getCharge():"0";
				String vat = null!=reportPayment.getVat()?reportPayment.getVat():"0";
				String totalCharge = null!=reportPayment.getTotalCharge()?reportPayment.getTotalCharge():"0";
				
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setTaxId(AppUtil.toString(reportPayment.getTaxId()));
				dailyPaymentTO.setBranchNo(AppUtil.toString(reportPayment.getBranchNo()));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(charge)));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(vat)));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(totalCharge)));
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				collections.add(dailyPaymentTO);
				// SUM : PaymentUser.
				if(EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
					rowPaymentUser += 1;
					chargePaymentUser += new Double(charge);
					vatPaymentUser += new Double(vat);
					totalChargePaymentUser += new Double(totalCharge);
				}
				// SUM : Vat 7%.
				if("7".equals(reportPayment.getVatRate())) {
					row7Percent += 1;
					charge7Percent += new Double(charge);
					vat7Percent += new Double(vat);
					totalCharge7Percent += new Double(totalCharge);
				}
				// SUM : Vat 0%.
				if("0".equals(reportPayment.getVatRate())) {
					row0Percent += 1;
					charge0Percent += new Double(charge);
					vat0Percent += new Double(vat);
					totalCharge0Percent += new Double(totalCharge);
				}
				/*ICE FIXED CODE  ข้อ 44  -  SUM TOTAL รวมทั้งสิ้น */
				// SUM ALL
				rowAll +=1; //rowTotal = rowTotal+1
				chargeAll += new Double(charge);
				vatAll += new Double(vat);
				totalChargeAll += new Double(totalCharge);
				
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer("");//(officer);
		// SUM : PaymentUser.
		documentObject.setSumRowPaymentUser(AppUtil.toString(rowPaymentUser));
		documentObject.setSumChargePaymentUser(AppUtil.toCurrencyPrint(chargePaymentUser));
		documentObject.setSumVatPaymentUser(AppUtil.toCurrencyPrint(vatPaymentUser));
		documentObject.setSumTotalChargePaymentUser(AppUtil.toCurrencyPrint(totalChargePaymentUser));
		// SUM : Vat 7%.
		documentObject.setSumRow7Percent(AppUtil.toString(AppUtil.toString(row7Percent)));
		documentObject.setSumCharge7Percent(AppUtil.toCurrencyPrint(charge7Percent));
		documentObject.setSumVat7Percent(AppUtil.toCurrencyPrint(vat7Percent));
		documentObject.setSumTotalCharge7Percent(AppUtil.toCurrencyPrint(totalCharge7Percent));
		// SUM : Vat 0%.
		documentObject.setSumRow0Percent(AppUtil.toString(AppUtil.toString(row0Percent)));
		documentObject.setSumCharge0Percent(AppUtil.toCurrencyPrint(charge0Percent));
		documentObject.setSumVat0Percent(AppUtil.toCurrencyPrint(vat0Percent));
		documentObject.setSumTotalCharge0Percent(AppUtil.toCurrencyPrint(totalCharge0Percent));
		//sum ALL ICE FIXED ข้อ 44 

		documentObject.setSumRowAll(AppUtil.toString(AppUtil.toString(rowAll)));
		documentObject.setSumChargeAll(AppUtil.toCurrencyPrint(chargeAll));
		documentObject.setSumVatAll(AppUtil.toCurrencyPrint(vatAll));
		documentObject.setSumTotalChargeAll(AppUtil.toCurrencyPrint(totalChargeAll));
		
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
        pushReportToExcleOutputStream(response, jasperPrints);
	}
	
	@RequestMapping(value="/printSalesAbbrTaxDailyReport.xls", method=RequestMethod.POST)
	public void printSalesAbbrTaxDailyReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSalesAbbrTaxDailyPayXls";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String type = stripToEmpty(request.getParameter("type"));
		String serviceType = stripToEmpty(request.getParameter("serviceType"));
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getDailySalesTaxReportJSON(request, printPdf, startDate, endDate, type, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailySalesTaxReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
//		Officer object = officerRepo.findOne(new Long(officer)); 
//		officer = object == null ? "": object.getCode();
		int rowPaymentUser = 0;
		double chargePaymentUser = 0, vatPaymentUser = 0, totalChargePaymentUser = 0;
		
		int row7Percent = 0;
		double charge7Percent = 0, vat7Percent = 0, totalCharge7Percent = 0;
		
		int row0Percent = 0;
		double charge0Percent = 0, vat0Percent = 0, totalCharge0Percent = 0;
		
		/*ICE FIXED CODE ข้อ 50 - set Parameter รวมทั้งสิ้น */
		int rowAll = 0;
		double chargeAll= 0, vatAll = 0, totalChargeAll = 0;
		
		if(reportPaymentDTO != null) {
			List<ReportPayment> reportPayments = reportPaymentDTO.getData();
			for(int i=0; i<reportPayments.size(); i++) {
				ReportPayment reportPayment = reportPayments.get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName() != null?reportPayment.getReceiptName():"-");
				dailyPaymentTO.setTaxId(AppUtil.toString(reportPayment.getTaxId()));
				dailyPaymentTO.setBranchNo(AppUtil.toString(reportPayment.getBranchNo()));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getCharge())));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getVat())));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getTotalCharge())));
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				collections.add(dailyPaymentTO);
				// SUM : PaymentUser.
				if(EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
					rowPaymentUser += 1;
					chargePaymentUser += new Double(reportPayment.getCharge());
					vatPaymentUser += new Double(reportPayment.getVat());
					totalChargePaymentUser += new Double(reportPayment.getTotalCharge());
				}
				// SUM : Vat 7%.
				if("7".equals(reportPayment.getVatRate())) {
					row7Percent += 1;
					charge7Percent += new Double(reportPayment.getCharge());
					vat7Percent += new Double(reportPayment.getVat());
					totalCharge7Percent += new Double(reportPayment.getTotalCharge());
				}
				// SUM : Vat 0%.
				if("0".equals(reportPayment.getVatRate())) {
					row0Percent += 1;
					charge0Percent += new Double(reportPayment.getCharge());
					vat0Percent += new Double(reportPayment.getVat());
					totalCharge0Percent += new Double(reportPayment.getTotalCharge());
				}
				
				/*ICE FIXED CODE  ข้อ 50  -  SUM TOTAL รวมทั้งสิ้น */
				// SUM ALL
				rowAll +=1; //rowTotal = rowTotal+1
				chargeAll += new Double(reportPayment.getCharge());
				vatAll += new Double(reportPayment.getVat());
				totalChargeAll += new Double(reportPayment.getTotalCharge());
				
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/")+" ถึง "+endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer("");//(officer);
		// SUM : PaymentUser.
		documentObject.setSumRowPaymentUser(AppUtil.toString(rowPaymentUser));
		documentObject.setSumChargePaymentUser(AppUtil.toCurrencyPrint(chargePaymentUser));
		documentObject.setSumVatPaymentUser(AppUtil.toCurrencyPrint(vatPaymentUser));
		documentObject.setSumTotalChargePaymentUser(AppUtil.toCurrencyPrint(totalChargePaymentUser));
		// SUM : Vat 7%.
		documentObject.setSumRow7Percent(AppUtil.toString(AppUtil.toString(row7Percent)));
		documentObject.setSumCharge7Percent(AppUtil.toCurrencyPrint(charge7Percent));
		documentObject.setSumVat7Percent(AppUtil.toCurrencyPrint(vat7Percent));
		documentObject.setSumTotalCharge7Percent(AppUtil.toCurrencyPrint(totalCharge7Percent));
		// SUM : Vat 0%.
		documentObject.setSumRow0Percent(AppUtil.toString(AppUtil.toString(row0Percent)));
		documentObject.setSumCharge0Percent(AppUtil.toCurrencyPrint(charge0Percent));
		documentObject.setSumVat0Percent(AppUtil.toCurrencyPrint(vat0Percent));
		documentObject.setSumTotalCharge0Percent(AppUtil.toCurrencyPrint(totalCharge0Percent));
		
		//sum ALL ICE FIXED ข้อ 50

		documentObject.setSumRowAll(AppUtil.toString(AppUtil.toString(rowAll)));
		documentObject.setSumChargeAll(AppUtil.toCurrencyPrint(chargeAll));
		documentObject.setSumVatAll(AppUtil.toCurrencyPrint(vatAll));
		documentObject.setSumTotalChargeAll(AppUtil.toCurrencyPrint(totalChargeAll));
		
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
        pushReportToExcleOutputStream(response, jasperPrints);
        
	}
	
	@RequestMapping(value="/printDailyDeductPaymentReport.xls", method=RequestMethod.POST)
	public void printDailyDeductPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyDeductPaymentXLS";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String serviceType = request.getParameter("serviceType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getDailyDeductPaymentReportJSON(request, printPdf, startDate, endDate, shop, officer, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailyDeductPaymentReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		int sumRowAll = 0;
		double sumAmount3tredecim = 0, sumAmount69bis = 0, sumAmount69tre = 0, sumAmountTotal = 0;
		
		if(reportPaymentDTO != null) {
			List<ReportPayment> reportPayments = reportPaymentDTO.getData();
			for(int i=0; i<reportPayments.size(); i++) {
				ReportPayment reportPayment = reportPayments.get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setDeductionNo(AppUtil.toString(reportPayment.getDeductionNo()));
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setAmount3tredecim(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getAmount3tredecim())));
				dailyPaymentTO.setAmount69bis(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getAmount69bis())));
				dailyPaymentTO.setAmount69tre(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getAmount69tre())));
				dailyPaymentTO.setRemark(AppUtil.toString(reportPayment.getRemark()));
				collections.add(dailyPaymentTO);
				// SUM : Deducts.
				sumRowAll += 1;
				sumAmount3tredecim += new Double(reportPayment.getAmount3tredecim());
				sumAmount69bis += new Double(reportPayment.getAmount69bis());
				sumAmount69tre += new Double(reportPayment.getAmount69tre());
				sumAmountTotal += new Double(reportPayment.getAmount3tredecim()) + new Double(reportPayment.getAmount69bis()) + new Double(reportPayment.getAmount69tre());
				
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		// SUM : Deducts.
		documentObject.setSumRowAll(AppUtil.toString(sumRowAll));
		documentObject.setSumAmount3tredecim(AppUtil.toCurrencyPrint(sumAmount3tredecim));
		documentObject.setSumAmount69bis(AppUtil.toCurrencyPrint(sumAmount69bis));
		documentObject.setSumAmount69tre(AppUtil.toCurrencyPrint(sumAmount69tre));
		documentObject.setTotal(AppUtil.toCurrencyPrint(sumAmountTotal));
		
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToExcleOutputStream(response, jasperPrints);
	}
	//pichanan 20170914
	
		@RequestMapping(value="/printMonthlyDeductPaymentReport.xls", method=RequestMethod.POST)
		public void printMonthlyDeductPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
			String JASPER_JRXML_FILENAME = "EpisMonthlyDeductPaymentXLS";
			String printPdf = stripToEmpty(request.getParameter("printPdf"));
			String period = request.getParameter("period");
			String shop = request.getParameter("shop");
			String officer = request.getParameter("officer");
			String serviceType = request.getParameter("serviceType");
			Map<String, Object> parameters = new HashMap<String, Object>();
			reportController.getMonthlyDeductPaymentReportJSON(request, printPdf, period, shop, officer, serviceType);
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printMonthlyDeductPaymentReport");
			List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
			
			int sumRowAll = 0;
			double sumAmount3tredecim = 0, sumAmount69bis = 0, sumAmount69tre = 0, sumAmountTotal = 0;
			
			if(reportPaymentDTO != null) {
				List<ReportPayment> reportPayments = reportPaymentDTO.getData();
				for(int i=0; i<reportPayments.size(); i++) {
					ReportPayment reportPayment = reportPayments.get(i);
					DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
					dailyPaymentTO.setRowNo(String.valueOf(i+1));
					dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
					dailyPaymentTO.setCntReceipt(reportPayment.getCntReceipt());
					dailyPaymentTO.setSum3tredecim(new BigDecimal(reportPayment.getAmount3tredecim()));
					dailyPaymentTO.setSum69bis(new BigDecimal(reportPayment.getAmount69bis()));
					dailyPaymentTO.setSum69tre(new BigDecimal(reportPayment.getAmount69tre()));
					dailyPaymentTO.setRemark(AppUtil.toString(reportPayment.getRemark()));
					collections.add(dailyPaymentTO);
					if (!AppConstants.CANCEL_TH.equalsIgnoreCase(reportPayment.getStatus())) {
						// SUM : Deducts.
						sumRowAll += 1;
						sumAmount3tredecim += new Double(reportPayment.getAmount3tredecim());
						sumAmount69bis += new Double(reportPayment.getAmount69bis());
						sumAmount69tre += new Double(reportPayment.getAmount69tre());
						sumAmountTotal += new Double(reportPayment.getAmount3tredecim()) + new Double(reportPayment.getAmount69bis()) + new Double(reportPayment.getAmount69tre());
					}
				}
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				collections.add(dailyPaymentTO);
			}
			
			DailyPaymentTO documentObject = new DailyPaymentTO();
			
			String month="";
			if(period.substring(0,2).equals("01")){month = "มกราคม"; }
			else if(period.substring(0,2).equals("02")){month = "กุมภาพันธ์"; } 
			else if(period.substring(0,2).equals("03")){month = "มีนาคม"; } 
			else if(period.substring(0,2).equals("04")){month = "เมษายน"; } 
			else if(period.substring(0,2).equals("05")){month = "พฤษภาคม"; } 
			else if(period.substring(0,2).equals("06")){month = "มิถุนายน"; } 
			else if(period.substring(0,2).equals("07")){month = "กรกฎาคม"; } 
			else if(period.substring(0,2).equals("08")){month = "สิงหาคม"; } 
			else if(period.substring(0,2).equals("09")){month = "กันยายน"; } 
			else if(period.substring(0,2).equals("10")){month = "ตุลาคม"; } 
			else if(period.substring(0,2).equals("11")){month = "พฤศจิกายน"; } 
			else if(period.substring(0,2).equals("12")){month = "ธันวาคม"; } 
			
			documentObject.setPeriod(month +" " + (Integer.valueOf(period.substring(3,7))+543));
			documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
			documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
			documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
			documentObject.setPos(EpContextHolder.getContext().getPosNo());
			documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
			// SUM : Deducts.
			documentObject.setSumRowAll(AppUtil.toString(sumRowAll));
			documentObject.setSumAmount3tredecim(AppUtil.toCurrencyPrint(sumAmount3tredecim));
			documentObject.setSumAmount69bis(AppUtil.toCurrencyPrint(sumAmount69bis));
			documentObject.setSumAmount69tre(AppUtil.toCurrencyPrint(sumAmount69tre));
			documentObject.setTotal(AppUtil.toCurrencyPrint(sumAmountTotal));
			
			//picht 20170914
			 byte[] outArray = excelReportService.generateExcelReportPrintMonthlyDeductionTemplate(context,documentObject, collections);
			 response.setContentType("application/ms-excel");
			 response.setContentLength(outArray.length);
			 response.setHeader("Expires:", "0"); // eliminates browser caching
			 response.setHeader("Content-Disposition", "attachment; filename=printMonthlyDeductPaymentReport.xls");
			 OutputStream outStream = response.getOutputStream();
			 outStream.write(outArray);
			 outStream.flush();
				//end picht 20170914
		}
	@RequestMapping(value="/printSummaryDailyPaymentReport.xls", method=RequestMethod.POST)
	public void printSummaryDailyPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSummaryDailyPaymentXLS";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String payCase = request.getParameter("payCase");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String serviceType = request.getParameter("serviceType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getSummaryDailyPaymentReportJSON(request, printPdf, startDate, endDate, payCase, shop, officer, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printSummaryDailyPaymenReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		int sumRowAll = 0;
		double sumAmountTotal = 0;
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());
				dailyPaymentTO.setCntReceipt(reportPayment.getCntReceipt());
				dailyPaymentTO.setPayTypeName(reportPayment.getPayTypeName());
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getTotalCharge())));
				collections.add(dailyPaymentTO);
				// SUM : Total All.
				sumRowAll += 1;
				sumAmountTotal += new Double(reportPayment.getTotalCharge());
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/")+" ถึง "+endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer(officer);
		// SUM : Total All.
		documentObject.setSumRowAll(AppUtil.toString(sumRowAll));
		documentObject.setTotal(AppUtil.toCurrencyPrint(sumAmountTotal));
		
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToExcleOutputStream(response, jasperPrints);
	}
	//picht 20170914

	// Report : close-endofday-report.
	@RequestMapping(value="/printCloseEndOfDayReport.xls", method=RequestMethod.POST)
	public void  printCloseEndOfDayReport(HttpServletRequest request, HttpServletResponse response, Model model
		  ) throws Exception {
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String toDate = stripToEmpty(request.getParameter("endDate"));
		String period = request.getParameter("period");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getCloseEndOfDayReportJSON(request, printPdf, startDate, toDate, shop, officer);
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printCloseEndOfDayReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());
				dailyPaymentTO.setPayFullname(reportPayment.getPayFullname());
				dailyPaymentTO.setShopNo(reportPayment.getShopNo());
				dailyPaymentTO.setTaxId(reportPayment.getTaxId());
				dailyPaymentTO.setShopName(reportPayment.getShopName());
				if(reportPayment.getPosName() != null && !reportPayment.getPosName().equals("")) {
					dailyPaymentTO.setPosName(reportPayment.getPosName());
				}else {
					dailyPaymentTO.setPosName("-");
				}					
				collections.add(dailyPaymentTO);

			}
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +" - " + toDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setShopNo(shop);
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer(officer);

		
//		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
//		parameters.put("ReportSource", documentObject);
//
//		response.setContentType("application/xls");
		 byte[] outArray = excelReportService.generateExcelReportPrintCloseEndOfDay(context,documentObject, collections);
		 response.setContentType("application/ms-excel");
		 response.setContentLength(outArray.length);
		 response.setHeader("Expires:", "0"); // eliminates browser caching
		 response.setHeader("Content-Disposition", "attachment; filename=printCloseEndOfDayRepost.xls");
		 OutputStream outStream = response.getOutputStream();
		 outStream.write(outArray);
		 outStream.flush();
	}
	
	@RequestMapping(value="/printCancelPaymentReport.xls", method=RequestMethod.POST)
	public void printCancelPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisCancelPaymentXls";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String invNo = request.getParameter("invNo");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String sourceType = request.getParameter("sourceType");
		String serviceType = request.getParameter("serviceType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getCancelPaymentReportJSON(request, printPdf, startDate, endDate, invNo, shop, officer, sourceType,serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printCancelPaymentReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		int rowCollectionUnit = 0;
		double chargeCollectionUnit = 0, vatCollectionUnit = 0, totalChargeCollectionUnit = 0;
		
		int rowPaymentUser = 0;
		double chargePaymentUser = 0, vatPaymentUser = 0, totalChargePaymentUser = 0;
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setReceiptDate(AppUtil.toString(reportPayment.getReceiptDate()));
				dailyPaymentTO.setCancelDate(AppUtil.toString(reportPayment.getCancelDate()));
				dailyPaymentTO.setPaymentUser(AppUtil.toString(reportPayment.getCancelByUser()));
				dailyPaymentTO.setCollectionUnit(AppUtil.toString(reportPayment.getCollectionUnit()));
				dailyPaymentTO.setInvoiceNo(AppUtil.toString(reportPayment.getInvoiceNo()));
				dailyPaymentTO.setPaymentCash(reportPayment.getPaymentCash());
				dailyPaymentTO.setDocumentNo(AppUtil.toString(reportPayment.getDocumentNo()));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getCharge())));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getVat())));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getTotalCharge())));
				collections.add(dailyPaymentTO);
				// SUM : CollectionUnit.
				if(AppUtil.isStringHasValue(reportPayment.getCollectionUnit())) {
					rowCollectionUnit += 1;
					chargeCollectionUnit += new Double(reportPayment.getCharge());
					vatCollectionUnit += new Double(reportPayment.getVat());
					totalChargeCollectionUnit += new Double(reportPayment.getTotalCharge());
				}
				// SUM : PaymentUser.
				if(EpContextHolder.getContext().getUserCode().equals(reportPayment.getCancelByUser())) {
					rowPaymentUser += 1;
					chargePaymentUser += new Double(reportPayment.getCharge());
					vatPaymentUser += new Double(reportPayment.getVat());
					totalChargePaymentUser += new Double(reportPayment.getTotalCharge());
				}
			}
		}
		
		DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
		dailyPaymentTO.setRowSummary("Y");
		collections.add(dailyPaymentTO);
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/")+" ถึง "+endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		// SUM : CollectionUnit.
		documentObject.setSumRowCollectionUnit(AppUtil.toString(rowCollectionUnit));
		documentObject.setSumChargeCollectionUnit(AppUtil.toCurrencyPrint(chargeCollectionUnit));
		documentObject.setSumVatCollectionUnit(AppUtil.toCurrencyPrint(vatCollectionUnit));
		documentObject.setSumTotalChargeCollectionUnit(AppUtil.toCurrencyPrint(totalChargeCollectionUnit));
		// SUM : PaymentUser.
		documentObject.setSumRowPaymentUser(AppUtil.toString(rowPaymentUser));
		documentObject.setSumChargePaymentUser(AppUtil.toCurrencyPrint(chargePaymentUser));
		documentObject.setSumVatPaymentUser(AppUtil.toCurrencyPrint(vatPaymentUser));
		documentObject.setSumTotalChargePaymentUser(AppUtil.toCurrencyPrint(totalChargePaymentUser));
		
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
        pushReportToExcleOutputStream(response, jasperPrints);
	}	
	
	
	@RequestMapping(value="/printEgpPaymentReport.xls", method=RequestMethod.POST)
	public void printEgpPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model
		  ) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisEgpPaymentXLS";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String accountID = request.getParameter("accountID");
		String vatRate = request.getParameter("vatRate");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String soureType = request.getParameter("soureType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getEgpPaymentReportJSON(request, printPdf, startDate, endDate, accountID, vatRate, shop, officer, soureType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printEgpPaymentReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		int rowCollectionUnit = 0;
		double chargeCollectionUnit = 0, vatCollectionUnit = 0, totalChargeCollectionUnit = 0;
		
		int rowPaymentUser = 0;
		double chargePaymentUser = 0, vatPaymentUser = 0, totalChargePaymentUser = 0;
		
		int row7Percent = 0;
		double charge7Percent = 0, vat7Percent = 0, totalCharge7Percent = 0;
		
		int row0Percent = 0;
		double charge0Percent = 0, vat0Percent = 0, totalCharge0Percent = 0;
		
		int rowAll = 0;
		double chargeAll = 0, vatAll = 0, totalChargeAll = 0;
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setCollectionUnit(AppUtil.toString(reportPayment.getCollectionUnit()));
				dailyPaymentTO.setInvoiceNo(AppUtil.toString(reportPayment.getInvoiceNo()));
				dailyPaymentTO.setPaymentCash(reportPayment.getPaymentCash());
				dailyPaymentTO.setDocumentNo(AppUtil.toString(reportPayment.getDocumentNo()));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getCharge()?reportPayment.getCharge():"0")));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getVat()?reportPayment.getVat():"0")));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getTotalCharge()?reportPayment.getTotalCharge():"0")));
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				dailyPaymentTO.setDiscount(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getDiscount()?reportPayment.getDiscount():"0")));
				dailyPaymentTO.setPaymentType(reportPayment.getPayTypeName());
				dailyPaymentTO.setEgpNo(reportPayment.getEgpNo());
				dailyPaymentTO.setEgpContract(reportPayment.getEgpContract());
				/*if(!"".equals(reportPayment.getPaymentType())) {
					if(AppConstants.PAYMENT_TYPE_SERVICE_CHARGE.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าใช้บริการ");
					} 
					if(AppConstants.PAYMENT_TYPE_OTHER.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าใช้บริการอื่นๆ (ขายสด)");
					}
					if(AppConstants.PAYMENT_TYPE_TOPUP.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการ (Top UP)");
					}
					if(AppConstants.PAYMENT_TYPE_MNP.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการ (MNP)");
					}
					if(AppConstants.PAYMENT_TYPE_TBOSS.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการหนี้สูญ TBOSS");
					}
					if(AppConstants.PAYMENT_TYPE_OTBOSS.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการหนี้นอก TBOSS");
					}
				}*/
				
				collections.add(dailyPaymentTO);
				// SUM : CollectionUnit.
				if(AppUtil.isStringHasValue(reportPayment.getCollectionUnit())) {
					rowCollectionUnit += 1;
					chargeCollectionUnit += new Double(reportPayment.getCharge());
					vatCollectionUnit += new Double(reportPayment.getVat());
					totalChargeCollectionUnit += new Double(reportPayment.getTotalCharge());
				}
				// SUM : PaymentUser.
				if(EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
					rowPaymentUser += 1;
					chargePaymentUser += new Double(reportPayment.getCharge());
					vatPaymentUser += new Double(reportPayment.getVat());
					totalChargePaymentUser += new Double(reportPayment.getTotalCharge());
				}
				// SUM : Vat 7%.
				if("7".equals(reportPayment.getVatRate())) {
					row7Percent += 1;
					charge7Percent += new Double(reportPayment.getCharge());
					vat7Percent += new Double(reportPayment.getVat());
					totalCharge7Percent += new Double(reportPayment.getTotalCharge());
				}
				// SUM : Vat 0%.
				if("0".equals(reportPayment.getVatRate())) {
					row0Percent += 1;
					charge0Percent += new Double(reportPayment.getCharge());
					vat0Percent += new Double(reportPayment.getVat());
					totalCharge0Percent += new Double(reportPayment.getTotalCharge());
				}
				// SUM : All.
				rowAll += 1;
				chargeAll += new Double(reportPayment.getCharge());
				vatAll += new Double(reportPayment.getVat());
				totalChargeAll += new Double(reportPayment.getTotalCharge());
				
			}
		}
		
		DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
		dailyPaymentTO.setRowSummary("Y");
		collections.add(dailyPaymentTO);
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		// SUM : CollectionUnit.
		documentObject.setSumRowCollectionUnit(AppUtil.toString(rowCollectionUnit));
		documentObject.setSumChargeCollectionUnit(AppUtil.toCurrencyPrint(chargeCollectionUnit));
		documentObject.setSumVatCollectionUnit(AppUtil.toCurrencyPrint(vatCollectionUnit));
		documentObject.setSumTotalChargeCollectionUnit(AppUtil.toCurrencyPrint(totalChargeCollectionUnit));
		// SUM : PaymentUser.
		documentObject.setSumRowPaymentUser(AppUtil.toString(rowPaymentUser));
		documentObject.setSumChargePaymentUser(AppUtil.toCurrencyPrint(chargePaymentUser));
		documentObject.setSumVatPaymentUser(AppUtil.toCurrencyPrint(vatPaymentUser));
		documentObject.setSumTotalChargePaymentUser(AppUtil.toCurrencyPrint(totalChargePaymentUser));
		// SUM : Vat 7%.
		documentObject.setSumRow7Percent(AppUtil.toString(AppUtil.toString(row7Percent)));
		documentObject.setSumCharge7Percent(AppUtil.toCurrencyPrint(charge7Percent));
		documentObject.setSumVat7Percent(AppUtil.toCurrencyPrint(vat7Percent));
		documentObject.setSumTotalCharge7Percent(AppUtil.toCurrencyPrint(totalCharge7Percent));
		// SUM : Vat 0%.
		documentObject.setSumRow0Percent(AppUtil.toString(AppUtil.toString(row0Percent)));
		documentObject.setSumCharge0Percent(AppUtil.toCurrencyPrint(charge0Percent));
		documentObject.setSumVat0Percent(AppUtil.toCurrencyPrint(vat0Percent));
		documentObject.setSumTotalCharge0Percent(AppUtil.toCurrencyPrint(totalCharge0Percent));
		// SUM : All.
		documentObject.setSumRowAll(AppUtil.toString(AppUtil.toString(rowAll)));
		documentObject.setSumChargeAll(AppUtil.toCurrencyPrint(chargeAll));
		documentObject.setSumVatAll(AppUtil.toCurrencyPrint(vatAll));
		documentObject.setSumTotalChargeAll(AppUtil.toCurrencyPrint(totalChargeAll));
		
		
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
        pushReportToExcleOutputStream(response, jasperPrints);
	}
	
	private void pushReportToExcleOutputStream(HttpServletResponse response, List<JasperPrint> jasperPrints) throws IOException, JRException {
		OutputStream outputStream = response.getOutputStream();
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}


	// W3P 15-may-2017
	@RequestMapping(value="/printDailyNewReceiptSaleReport.xls", method=RequestMethod.POST)
	public void printDailyNewReceiptSaleReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSalesNewReceiptXls";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String serviceType = stripToEmpty(request.getParameter("serviceType"));
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getDailyNewReceiptSaleReportJSON(request, printPdf, startDate, endDate, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailyNewReceiptSaleReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();


		if(reportPaymentDTO != null) {
			List<ReportPayment> reportPayments = reportPaymentDTO.getData();
			for(int i=0; i<reportPayments.size(); i++) {
				ReportPayment reportPayment = reportPayments.get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setRefRecNo(reportPayment.getRefRecNo());
				dailyPaymentTO.setPaymentDate(reportPayment.getPaymentDate());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName() != null?reportPayment.getReceiptName():"-");
				collections.add(dailyPaymentTO);

			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}

		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/")+" ถึง "+endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer("");//(officer);

		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");

		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
		jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToExcleOutputStream(response, jasperPrints);

	}
	// pichanan 10/08/2017
	@RequestMapping(value="/printDailyReceiptSaleReport.xls", method=RequestMethod.POST)
	public void printDailyReceiptSaleReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSalesReceiptXLS";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String serviceType = stripToEmpty(request.getParameter("serviceType"));
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getDailyReceiptSaleReport(request, printPdf, startDate, endDate, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailyReceiptSaleReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();


		if(reportPaymentDTO != null) {
			List<ReportPayment> reportPayments = reportPaymentDTO.getData();
			for(int i=0; i<reportPayments.size(); i++) {
				ReportPayment reportPayment = reportPayments.get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setTaxId(reportPayment.getTaxId());
				//dailyPaymentTO.setRefRecNo(reportPayment.getRefRecNo());
				dailyPaymentTO.setUpdateDate(reportPayment.getUpdateDate());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName() != null?reportPayment.getReceiptName():"-");
				System.out.println(" vvvddv : " + dailyPaymentTO.toString());
				collections.add(dailyPaymentTO);
				System.out.println(" vvvv : " + collections);
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			
			
			collections.add(dailyPaymentTO);
			
			
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		
		documentObject.setPaymentDate(startDate.replaceAll("-", "/")+" ถึง "+endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer("");//(officer);

		System.out.println(" vvvv1 : " + documentObject.toString());
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");

		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
		jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToExcleOutputStream(response, jasperPrints);

}
	
	@RequestMapping(value="/printPnExtDailyPaymentReport.xls", method=RequestMethod.POST)
	public void printPnExtDailyPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model
	) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyPenaltyExtendXLS";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String receiptNo = request.getParameter("receiptNo");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String soureType = request.getParameter("soureType");
		String serviceType = request.getParameter("serviceType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		JASPER_JRXML_FILENAME = null!=serviceType || !("").equalsIgnoreCase(serviceType)?AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)?"AgentDailyPaymentXLS":AppConstants.PAYMENT_TYPE_FOREIGN.equalsIgnoreCase(serviceType)?"EpisDailyPaymentForeignXLS":JASPER_JRXML_FILENAME:JASPER_JRXML_FILENAME;
		reportController.getPnExtDailyPaymentReportJSON(request, printPdf, startDate, endDate, receiptNo, shop, officer, soureType, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printPnExtDailyPaymentReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		int rowCollectionUnit = 0;
		double chargeCollectionUnit = 0, vatCollectionUnit = 0, totalChargeCollectionUnit = 0;

		int rowPaymentUser = 0;
		double chargePaymentUser = 0, vatPaymentUser = 0, totalChargePaymentUser = 0;

		int row7Percent = 0;
		double charge7Percent = 0, vat7Percent = 0, totalCharge7Percent = 0;

		int row0Percent = 0;
		double charge0Percent = 0, vat0Percent = 0, totalCharge0Percent = 0;

		int rowAll = 0;
		double chargeAll = 0, vatAll = 0, totalChargeAll = 0;
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setCollectionUnit(AppUtil.toString(reportPayment.getCollectionUnit()));
				dailyPaymentTO.setInvoiceNo(AppUtil.toString(reportPayment.getInvoiceNo()));
				dailyPaymentTO.setPaymentCash(reportPayment.getPaymentCash());
				dailyPaymentTO.setDocumentNo(AppUtil.toString(reportPayment.getDocumentNo()));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getCharge()?!reportPayment.getCharge().equalsIgnoreCase("")?reportPayment.getCharge():"0":"0")));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getVat()?!reportPayment.getVat().equalsIgnoreCase("")?reportPayment.getVat():"0":"0")));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getTotalCharge()?!reportPayment.getTotalCharge().equalsIgnoreCase("")?reportPayment.getTotalCharge():"0":"0")));
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				dailyPaymentTO.setDiscount(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getDiscount()?!reportPayment.getDiscount().equalsIgnoreCase("")?reportPayment.getDiscount():"0":"0")));
				dailyPaymentTO.setTotalChargeForeign(reportPayment.getMsgForeign()+" "+ AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getTotalCharge()?!reportPayment.getTotalCharge().equalsIgnoreCase("")?reportPayment.getTotalCharge():"0":"0")));

				dailyPaymentTO.setPaymentType(reportPayment.getPayTypeName());
				/*if(!"".equals(reportPayment.getPaymentType())) {
					if(AppConstants.PAYMENT_TYPE_SERVICE_CHARGE.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าใช้บริการ");
					}
					if(AppConstants.PAYMENT_TYPE_OTHER.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าใช้บริการอื่นๆ (ขายสด)");
					}
					if(AppConstants.PAYMENT_TYPE_TOPUP.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการ (Top UP)");
					}
					if(AppConstants.PAYMENT_TYPE_MNP.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการ (MNP)");
					}
					if(AppConstants.PAYMENT_TYPE_TBOSS.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการหนี้สูญ TBOSS");
					}
					if(AppConstants.PAYMENT_TYPE_OTBOSS.equals(reportPayment.getPaymentType())) {
						dailyPaymentTO.setPaymentMessage("รับชำระ : ค่าบริการหนี้นอก TBOSS");
					}
				}*/

				collections.add(dailyPaymentTO);
				String charge = null!=reportPayment.getCharge()?!reportPayment.getCharge().equals("")?reportPayment.getCharge():"0":"0";
				String vat = null!=reportPayment.getVat()?!reportPayment.getVat().equals("")?reportPayment.getVat():"0":"0";
				String totalCharge = null!=reportPayment.getTotalCharge()?!reportPayment.getTotalCharge().equals("")?reportPayment.getTotalCharge():"0":"0";

				// SUM : CollectionUnit.
				if(AppUtil.isStringHasValue(reportPayment.getCollectionUnit())) {
					rowCollectionUnit += 1;
					chargeCollectionUnit += new Double(charge);
					vatCollectionUnit += new Double(vat);
					totalChargeCollectionUnit += new Double(totalCharge);
				}
				// SUM : PaymentUser.
				if(EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
					rowPaymentUser += 1;
					chargePaymentUser += new Double(charge);
					vatPaymentUser += new Double(vat);
					totalChargePaymentUser += new Double(totalCharge);
				}
				// SUM : Vat 7%.
				if("7".equals(reportPayment.getVatRate())) {
					row7Percent += 1;
					charge7Percent += new Double(charge);
					vat7Percent += new Double(vat);
					totalCharge7Percent += new Double(totalCharge);
				}
				// SUM : Vat 0%.
				if("0".equals(reportPayment.getVatRate())) {
					row0Percent += 1;
					charge0Percent += new Double(charge);
					vat0Percent += new Double(vat);
					totalCharge0Percent += new Double(totalCharge);
				}
				// SUM : All.
				rowAll += 1;
				chargeAll += new Double(charge);
				vatAll += new Double(vat);
				totalChargeAll += new Double(totalCharge);

			}
		}

		DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
		dailyPaymentTO.setRowSummary("Y");
		collections.add(dailyPaymentTO);

		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		// SUM : CollectionUnit.
		documentObject.setSumRowCollectionUnit(AppUtil.toString(rowCollectionUnit));
		documentObject.setSumChargeCollectionUnit(AppUtil.toCurrencyPrint(chargeCollectionUnit));
		documentObject.setSumVatCollectionUnit(AppUtil.toCurrencyPrint(vatCollectionUnit));
		documentObject.setSumTotalChargeCollectionUnit(AppUtil.toCurrencyPrint(totalChargeCollectionUnit));
		// SUM : PaymentUser.
		documentObject.setSumRowPaymentUser(AppUtil.toString(rowPaymentUser));
		documentObject.setSumChargePaymentUser(AppUtil.toCurrencyPrint(chargePaymentUser));
		documentObject.setSumVatPaymentUser(AppUtil.toCurrencyPrint(vatPaymentUser));
		documentObject.setSumTotalChargePaymentUser(AppUtil.toCurrencyPrint(totalChargePaymentUser));
		// SUM : Vat 7%.
		documentObject.setSumRow7Percent(AppUtil.toString(AppUtil.toString(row7Percent)));
		documentObject.setSumCharge7Percent(AppUtil.toCurrencyPrint(charge7Percent));
		documentObject.setSumVat7Percent(AppUtil.toCurrencyPrint(vat7Percent));
		documentObject.setSumTotalCharge7Percent(AppUtil.toCurrencyPrint(totalCharge7Percent));
		// SUM : Vat 0%.
		documentObject.setSumRow0Percent(AppUtil.toString(AppUtil.toString(row0Percent)));
		documentObject.setSumCharge0Percent(AppUtil.toCurrencyPrint(charge0Percent));
		documentObject.setSumVat0Percent(AppUtil.toCurrencyPrint(vat0Percent));
		documentObject.setSumTotalCharge0Percent(AppUtil.toCurrencyPrint(totalCharge0Percent));
		// SUM : All.
		documentObject.setSumRowAll(AppUtil.toString(AppUtil.toString(rowAll)));
		documentObject.setSumChargeAll(AppUtil.toCurrencyPrint(chargeAll));
		documentObject.setSumVatAll(AppUtil.toCurrencyPrint(vatAll));
		documentObject.setSumTotalChargeAll(AppUtil.toCurrencyPrint(totalChargeAll));

		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/xls");

		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
		jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToExcleOutputStream(response, jasperPrints);
	}

	@RequestMapping(value="/printUnCloseEndOfDayReport.xls", method=RequestMethod.POST)
	public void  printUnCloseEndOfDayReport(HttpServletRequest request, HttpServletResponse response, Model model
		  ) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisUnCloseEndOfDayPaymentXLS";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String toDate = stripToEmpty(request.getParameter("toDate"));
		String period = request.getParameter("period");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String serviceType = request.getParameter("serviceType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		//reportController.getUnCloseEndOfDayReportJSON(request, printPdf, startDate,toDate, period, shop, officer);
		reportController.getUnCloseEndOfDayReportJSON(request, printPdf, startDate, period, shop, officer);
		JASPER_JRXML_FILENAME = null!=serviceType || !("").equalsIgnoreCase(serviceType)?AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)?"AgentDailyPaymentXLS":AppConstants.PAYMENT_TYPE_FOREIGN.equalsIgnoreCase(serviceType)?"EpisDailyPaymentForeignXLS":JASPER_JRXML_FILENAME:JASPER_JRXML_FILENAME;
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printUnCloseEndOfDayReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());
				dailyPaymentTO.setPayFullname(reportPayment.getPayFullname());
				dailyPaymentTO.setShopNo(reportPayment.getShopNo());
				dailyPaymentTO.setTaxId(reportPayment.getTaxId());
				dailyPaymentTO.setShopName(reportPayment.getShopName());
				if(reportPayment.getPosName() != null && !reportPayment.getPosName().equals("")) {
					dailyPaymentTO.setPosName(reportPayment.getPosName());
				}else {
					dailyPaymentTO.setPosName("-");
				}					
				collections.add(dailyPaymentTO);

			}
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +" - " + toDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setShopNo(shop);
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer(officer);

		
//		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
//		parameters.put("ReportSource", documentObject);
//
//		response.setContentType("application/xls");
		 byte[] outArray = excelReportService.generateExcelReportPrintUnCloseEndOfDay(context,documentObject, collections);
		 response.setContentType("application/ms-excel");
		 response.setContentLength(outArray.length);
		 response.setHeader("Expires:", "0"); // eliminates browser caching
		 response.setHeader("Content-Disposition", "attachment; filename=printUnCloseEndOfDayRepost.xls");
		 OutputStream outStream = response.getOutputStream();
		 outStream.write(outArray);
		 outStream.flush();
	}
	
	
	@RequestMapping(value="/printDiscountDailyCreditReport.xls", method=RequestMethod.POST)
	public void  printDiscountDailyCreditReport(HttpServletRequest request, HttpServletResponse response, Model model
		  ) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisprintDiscountDailyCreditXLS";
		String printPdf = stripToEmpty(request.getParameter("printXLS"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String toDate = stripToEmpty(request.getParameter("toDate"));
		String period = request.getParameter("period");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String serviceType = request.getParameter("serviceType");
		reportController.getDiscountDailyCreditReportJSON(request, printPdf, startDate,toDate, period, shop, officer);
		JASPER_JRXML_FILENAME = null!=serviceType || !("").equalsIgnoreCase(serviceType)?AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)?"AgentDailyPaymentXLS":AppConstants.PAYMENT_TYPE_FOREIGN.equalsIgnoreCase(serviceType)?"EpisDailyPaymentForeignXLS":JASPER_JRXML_FILENAME:JASPER_JRXML_FILENAME;
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDiscountDailyCreditReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setInvoiceNo(reportPayment.getInvoiceNo());
				dailyPaymentTO.setTotalChargeB(new BigDecimal(reportPayment.getTotalCharge()));				
				dailyPaymentTO.setDiscountB(new BigDecimal(reportPayment.getDiscount()));
				dailyPaymentTO.setAfter_sale_discountB(new BigDecimal(reportPayment.getAfter_sale_discount()));
/*				dailyPaymentTO.setVatB(new BigDecimal(reportPayment.getVat()));*/
				dailyPaymentTO.setStatus(reportPayment.getAttributes().indexOf("R")>0?"ยกเลิก":"-");
				collections.add(dailyPaymentTO);

			}
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +" - " + toDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setShopNo(shop);
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer(officer);

		
//		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
//		parameters.put("ReportSource", documentObject);
//
//		response.setContentType("application/xls");
		 byte[] outArray = excelReportService.generateExcelReportprintDiscountDailyCredit(context,documentObject, collections);
		 response.setContentType("application/ms-excel");
		 response.setContentLength(outArray.length);
		 response.setHeader("Expires:", "0"); // eliminates browser caching
		 response.setHeader("Content-Disposition", "attachment; filename=prinDiscountDailyCreditReport.xls");
		 OutputStream outStream = response.getOutputStream();
		 outStream.write(outArray);
		 outStream.flush();
	}
	
	@RequestMapping(value="/printEgpProjectReport.xls", method=RequestMethod.POST)
	public void  printEgpProjectReport(HttpServletRequest request, HttpServletResponse response, Model model
		  ) throws Exception {
		String printPdf = stripToEmpty(request.getParameter("printXLS"));
		String toDate = stripToEmpty(request.getParameter("startDate"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String shop = request.getParameter("shop");
		String soureType = request.getParameter("soureType");
		
		System.out.println("excel = "+ printPdf);
		System.out.println("startDate = "+ startDate);
		System.out.println("endDate = "+ endDate);
		System.out.println("shop = "+ shop);
		System.out.println("soureType = "+ soureType);
		
		
//		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getEgpProjectReportJSON(request, printPdf, startDate,endDate, shop, soureType);
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printEgpProjectReport");
		List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				
				dailyPaymentTO.setEgpNo(reportPayment.getEgpNo());
				dailyPaymentTO.setEgpContract(reportPayment.getEgpContract());
				dailyPaymentTO.setStartDate(reportPayment.getStartDate());
				dailyPaymentTO.setEndDate(reportPayment.getEndDate());
				dailyPaymentTO.setEgpValue(new BigDecimal(reportPayment.getEgpValue()));
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setInvoiceNo(reportPayment.getInvoiceNo());
				dailyPaymentTO.setBillGroup(reportPayment.getBillGroup());
				dailyPaymentTO.setIssueDate(reportPayment.getIssueDate());
				
				dailyPaymentTO.setDueDate(reportPayment.getDueDate());
				dailyPaymentTO.setBillCycle(reportPayment.getBillCycle());
				dailyPaymentTO.setBeginCharge(new BigDecimal(reportPayment.getBeginCharge()));
				dailyPaymentTO.setBeginVat(new BigDecimal(reportPayment.getBeginVat()));
				dailyPaymentTO.setBeginTotalCharge(new BigDecimal(reportPayment.getBeginTotalCharge()));
				dailyPaymentTO.setPosting_date(reportPayment.getPosting_date());
				
				dailyPaymentTO.setAdjCharge(new BigDecimal(reportPayment.getAdjCharge()));
				dailyPaymentTO.setAdjVat(new BigDecimal(reportPayment.getAdjVat()));
				dailyPaymentTO.setAdjTotalCharge(new BigDecimal(reportPayment.getAdjTotalCharge()));
				dailyPaymentTO.setAdjPostingDate(reportPayment.getAdjPostingDate());
				
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setBranchName(reportPayment.getBranchName());
				dailyPaymentTO.setChargeB(new BigDecimal(reportPayment.getCharge()));
				dailyPaymentTO.setVatB(new BigDecimal(reportPayment.getVat()));
				dailyPaymentTO.setTotalChargeB(new BigDecimal(reportPayment.getTotalCharge()));
				dailyPaymentTO.setPaymentType(reportPayment.getPaymentType());
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setPaymentId(reportPayment.getPaymentId());
				dailyPaymentTO.setCollectionUnit(reportPayment.getCollectionUnit());
				dailyPaymentTO.setStatus(reportPayment.getStatus());
							
				dailyPaymentTO.setNetCharge(new BigDecimal(reportPayment.getNetCharge()));
				dailyPaymentTO.setNetVat(new BigDecimal(reportPayment.getNetVat()));
				dailyPaymentTO.setNetTotalCharge(new BigDecimal(reportPayment.getNetTotalCharge()));
				
				collections.add(dailyPaymentTO);

			}
		}
		
		System.out.println(" bbb : " + startDate.replaceAll("-", "/") +" - " + endDate.replaceAll("-", "/"));
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +" - " + endDate.replaceAll("-", "/"));
//		documentObject.setStartDate(startDate.replaceAll("-", "/") +" - " + toDate.replaceAll("-", "/"));
//		documentObject.setEndDate(endDate.replaceAll("-", "/") +" - " + toDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(startDate.replaceAll("-", "/") +" - " + endDate.replaceAll("-", "/"));

		//documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setShopNo(shop);
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		//documentObject.setOfficer(officer);

		
//		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
//		parameters.put("ReportSource", documentObject);
//		response.setContentType("application/xls");
		
		 byte[] outArray = excelReportService.generateExcelReportEgpProjectReport(context,documentObject, collections);
		 response.setContentType("application/ms-excel");
		 response.setContentLength(outArray.length);
		 response.setHeader("Expires:", "0"); // eliminates browser caching
		 response.setHeader("Content-Disposition", "attachment; filename=printEgpProjectReportTemplate.xls");
		 OutputStream outStream = response.getOutputStream();
		 outStream.write(outArray);
		 outStream.flush();
	}
}
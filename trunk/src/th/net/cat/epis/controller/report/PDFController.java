package th.net.cat.epis.controller.report;

import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.stripToEmpty;
import static th.net.cat.epis.util.AppConstants.FILE_TYPE_JRXML;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_1;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_2;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_3;
import static th.net.cat.epis.util.AppConstants.JASPER_JRXML_PATH;
import static th.net.cat.epis.util.AppConstants.RECEIPT_ACCOUNT_TYPE_CODE_GOVERNMENT;
import static th.net.cat.epis.util.AppConstants.RECEIPT_ACCOUNT_TYPE_CODE_INDIVIDUAL;
import static th.net.cat.epis.util.AppConstants.RECEIPT_ACCOUNT_TYPE_CODE_ORGANIZATION;
import static th.net.cat.epis.util.AppConstants.RECEIPT_FORMAT_RECEIVE_ONLY;
import static th.net.cat.epis.util.AppConstants.RECEIPT_FORMAT_RECEIVE_WH;
import static th.net.cat.epis.util.AppConstants.RECEIPT_FORMAT_WH_ONLY;
import static th.net.cat.epis.util.AppConstants.TAX_CODE_NONVAT;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

//import com.sun.javaws.security.AppContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;

import com.google.common.collect.Lists;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import th.co.softpos.ws.mg.s004.S004MGUpdFree;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.OTTBossDTO;
import th.net.cat.epis.dto.PrintDayEndClosingDTO;
import th.net.cat.epis.dto.PrintReceiptDTO;
import th.net.cat.epis.dto.ReportPayment;
import th.net.cat.epis.dto.ReportPaymentDTO;
import th.net.cat.epis.dto.SettlePaymentDTO.Customer;
import th.net.cat.epis.dto.bouncecheque.BounceChequeDTO;
import th.net.cat.epis.dto.bouncecheque.DetailARCustomerDTO;
import th.net.cat.epis.dto.bouncecheque.PayBounceChequeDTO;
import th.net.cat.epis.dto.bouncecheque.SapForReportDTO;
import th.net.cat.epis.entity.EmpClosing;
import th.net.cat.epis.entity.EndDayClosing;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.InvoiceVatDetail;
import th.net.cat.epis.entity.MasChangeRate;
import th.net.cat.epis.entity.OTTBossEntity;
import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.entity.Payment;
import th.net.cat.epis.entity.PromotionMappingEntity;
import th.net.cat.epis.entity.PromotionReceiptMappingEntity;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.ReceiptEgpMappingEntity;
import th.net.cat.epis.entity.ReceiptPrinttypeMapping;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.repo.AgentRepository;
import th.net.cat.epis.repo.EnumRepository;
import th.net.cat.epis.repo.ExchangeRateRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.MoneyTransferRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.PromotionBillingMappingRepository;
import th.net.cat.epis.repo.PromotionMappingRepository;
import th.net.cat.epis.repo.ReceiptEgpMappingRepository;
import th.net.cat.epis.repo.ReceiptPrintTypeMappingRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.ServiceRepository;
import th.net.cat.epis.repo.TransactionRepository;
import th.net.cat.epis.service.EndDayClosingService;
import th.net.cat.epis.service.EpisService;
import th.net.cat.epis.service.bouncecheqeue.BounceChequeService;
import th.net.cat.epis.service.otboss.OTBOssService;
import th.net.cat.epis.to.report.CustomerTaxReceiptForOthersPaymentTO;
import th.net.cat.epis.to.report.CustomerTaxReceiptTO;
import th.net.cat.epis.to.report.DailyPaymentTO;
import th.net.cat.epis.to.report.DayEndClosingTO;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppConstants.RECEIPT_PRINT_TYPES;
import th.net.cat.epis.util.AppConstants.S004_STATUS_TYPES;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;


@Controller
@SessionAttributes(value={ AppConstants.DAILY_PAYMENT_DEDUCT_REPORT_KEY })
public class PDFController implements ServletContextAware {
	private static Logger logger = Logger.getLogger(PDFController.class);
	@Autowired ReceiptRepository receiptRepo;
	@Autowired TransactionRepository transactionRepo;
	@Autowired MoneyTransferRepository moneyTransferRepo;
	@Autowired InvoiceRepository invoiceRepo;
	@Autowired ServiceRepository serviceRepo;
	@Autowired OfficerRepository officerRepo;
	@Autowired ReceiptPrintTypeMappingRepository receiptPrintTypeMappingRepo;
	@Autowired EnumRepository enumRepo;
	@Autowired EpisService episService;
	@Autowired ReceiptEgpMappingRepository egpMapRepo;
    @Autowired ReceiptPrintTypeMappingRepository rctPrintTypeMapRepo;
	@Autowired AgentRepository agentRepository;
	@Autowired
	EndDayClosingService endDayClosingService;
	@Autowired PromotionMappingRepository promotionMappingRepository;
	@Autowired PromotionBillingMappingRepository promotionBillingMappingRepository;
	@Autowired S004MGUpdFree s004MGUpdFree;
	@Autowired
	OTBOssService otbossservice;
	@Resource(name="episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	@Autowired
	protected MessageSource messages;
	
	@Autowired 
	private ReportController reportController;
	@Autowired ExchangeRateRepository exchangeRepo;
	@Autowired PaymentRepository paymentRepo;
	
	private ServletContext context;
	private final String TOPUP = "TOPUP";
	private final String MNP = "MNP";
	private final String AGENT = "AGENT";
	List<ReceiptEgpMappingEntity> egpMapFind = null;
	BigDecimal AmountBeforeVatTH, summaryAmountBeforeVatTH, summaryDiscountTH, summaryAmountSumBeforeVatTH, summaryAmountSumAfterVatTH,amountPaid, summaryVatTH, additionalDiscountAfterVatTH, AmountSumAfterVatTH = BigDecimal.ZERO;

	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	@RequestMapping(value="/printReceipt.pdf", method=RequestMethod.GET)
	public void printReceipt(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String receiptId) throws Exception {
		
	}

	private String getJasperFileName(PrintReceiptDTO printReceiptDTO, List<Invoice> invoiceList){
		String receiptLang = printReceiptDTO.getReceiptLang();
		String dfCurrencyCode = "12"; //default code 12 = TH
		String currencyCode = AppConstants.CURRENCY_CODE_TH, currencyCode_Inv = "12";
		
        String JASPER_JRXML_FILENAME = "EpisPaymentReceipt";//"";
        
        if (printReceiptDTO.getCustomers().size()>0) {
        	if (printReceiptDTO.getCustomers().get(0).getInvoices().size()>0) {
        		currencyCode_Inv = StringUtils.isNotEmpty(printReceiptDTO.getCustomers().get(0).getInvoices().get(0).getCurrencyCode())?
					printReceiptDTO.getCustomers().get(0).getInvoices().get(0).getCurrencyCode():dfCurrencyCode;
        	}
        } else if(invoiceList!=null){
        	if (invoiceList.size()>0) {
        		currencyCode_Inv = StringUtils.isNotEmpty(invoiceList.get(0).getCurrencyCode())?
					invoiceList.get(0).getCurrencyCode():dfCurrencyCode;
				currencyCode = StringUtils.isNotEmpty(invoiceList.get(0).getPayment().getCurrencyCode())?
					invoiceList.get(0).getPayment().getCurrencyCode():AppConstants.CURRENCY_CODE_TH;
			}
		}
        
        if (printReceiptDTO.isCopy()) {
        	if (!AppConstants.CURRENCY_CODE_TH.equalsIgnoreCase(currencyCode) || !dfCurrencyCode.equalsIgnoreCase(currencyCode_Inv)) {
        		JASPER_JRXML_FILENAME="EpisPaymentReceipt_CopyCurrency";
        	} else {
        		JASPER_JRXML_FILENAME="EpisPaymentCopyReceipt";
        	}
        } else {
        	if (!AppConstants.CURRENCY_CODE_TH.equalsIgnoreCase(currencyCode) || !dfCurrencyCode.equalsIgnoreCase(currencyCode_Inv)) {
        		JASPER_JRXML_FILENAME="EpisPaymentReceipt_Currency";
        	}
        }
        
		if(!StringUtils.isEmpty(receiptLang) && receiptLang.equals(AppConstants.LANGUAGE_KEY.ENG.name()))
			 JASPER_JRXML_FILENAME = JASPER_JRXML_FILENAME+"ENG";
		return JASPER_JRXML_FILENAME;
	}
	
	private String getJasperFileNameTBOSS(PrintReceiptDTO printReceiptDTO, List<Invoice> invoiceList){
		String receiptLang = printReceiptDTO.getReceiptLang();
		String dfCurrencyCode = "12"; //default code 12 = TH
		String currencyCode = "12"; //
		
		//String JASPER_JRXML_FILENAME = "EpisPaymentReceipt";//"";
        String JASPER_JRXML_FILENAME = "EpisPaymentReceiptTBOSS";//"";
        
        if (printReceiptDTO.getCustomers().size()>0) {
        	if (printReceiptDTO.getCustomers().get(0).getInvoices().size()>0) {
        		currencyCode = null!=printReceiptDTO.getCustomers().get(0).getInvoices().get(0).getCurrencyCode()?
        								!printReceiptDTO.getCustomers().get(0).getInvoices().get(0).getCurrencyCode().equalsIgnoreCase("")?
        										printReceiptDTO.getCustomers().get(0).getInvoices().get(0).getCurrencyCode():dfCurrencyCode:dfCurrencyCode;
        	}
        } else if(invoiceList!=null){
        	if (invoiceList.size()>0) {
				currencyCode = null!=invoiceList.get(0).getCurrencyCode()?
						!invoiceList.get(0).getCurrencyCode().equalsIgnoreCase("")?
								invoiceList.get(0).getCurrencyCode():dfCurrencyCode:dfCurrencyCode;
			}

		}
		if (!"12".equalsIgnoreCase(currencyCode) && printReceiptDTO.isCopy()) {
			JASPER_JRXML_FILENAME="EpisPaymentReceipt_CopyCurrency";
		} 
		if (!"12".equalsIgnoreCase(currencyCode) && !printReceiptDTO.isCopy()) {
			JASPER_JRXML_FILENAME="EpisPaymentReceipt_Currency";
		} 
		if ("12".equalsIgnoreCase(currencyCode) && printReceiptDTO.isCopy()) {
			JASPER_JRXML_FILENAME="EpisPaymentCopyReceipt";
		}
//		if(printReceiptDTO.isCopy())
//			JASPER_JRXML_FILENAME="EpisPaymentCopyReceipt";
		if(!StringUtils.isEmpty(receiptLang) && receiptLang.equals(AppConstants.LANGUAGE_KEY.ENG.name()))
			 JASPER_JRXML_FILENAME = JASPER_JRXML_FILENAME+"ENG";
		return JASPER_JRXML_FILENAME;
	}
	
	@RequestMapping(value="/printPaymentReceipt.pdf", method=RequestMethod.POST)
	public void printPaymentReceipt(HttpServletRequest request, HttpServletResponse response, PrintReceiptDTO printReceiptDTO, List<Invoice> invoiceList
			) throws Exception {
		String receiptLang = printReceiptDTO.getReceiptLang();
		String billingGroup = printReceiptDTO.getBillingGroup();
		String receiptFormat = printReceiptDTO.getReceiptFormat();
		String note = printReceiptDTO.getNote();

		boolean isSubstitute = (printReceiptDTO.getSubstitute()!=null)?Boolean.valueOf(printReceiptDTO.getSubstitute()):false;
		
		String JASPER_JRXML_FILENAME = getJasperFileName(printReceiptDTO, invoiceList);
		System.out.println("JASPER_JRXML_FILENAME ::: "+JASPER_JRXML_FILENAME);
	
		Map<String, Object> parameters = new HashMap<String, Object>();
		Officer officer = null;
		if(AppUtil.isCollectionHasValue(printReceiptDTO.getReceipts())) {
			officer = officerRepo.findByName(request.getUserPrincipal().getName());
		}
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		for (Receipt rct : printReceiptDTO.getReceipts()) {
			// check for ADV
			Receipt rct_forCheck = receiptRepo.findOne(rct.getId());
			if (rct_forCheck.getAttributes()!=null && rct_forCheck.getAttributes().endsWith("AC")){
				if(!printReceiptDTO.isCopy())
					JASPER_JRXML_FILENAME="EpisPaymentReceipt";
				else
					JASPER_JRXML_FILENAME="EpisPaymentCopyReceipt";
				if(!StringUtils.isEmpty(receiptLang) && receiptLang.equals(AppConstants.LANGUAGE_KEY.ENG.name()))
					 JASPER_JRXML_FILENAME = JASPER_JRXML_FILENAME+"ENG";
			} 
			
//			for (Customer cust : printReceiptDTO.getCustomers()) {
//				if(cust.getCustNo().equalsIgnoreCase(rct.getAccountNo())) {
				//String REPORT_OUTPUT_FILENAME = request.getUserPrincipal().getName()+"-EpisPaymentReceipt";

					List<CustomerTaxReceiptTO> collections = new ArrayList<CustomerTaxReceiptTO>();
					prepareCustomerTaxReceiptTO(collections, rct, printReceiptDTO);
					if(AppUtil.isCollectionHasValue(collections)) {
						CustomerTaxReceiptTO documentObject = (CustomerTaxReceiptTO)collections.get(0);
						//documentObject.setImagePathRpt(context.getRealPath("images")+File.separatorChar+"CATTelecom_Logo.png");
						/*if(documentObject.getCustomerNo()!=null &&
								(documentObject.getCustomerNo().equals("611001271") || documentObject.getCustomerNo().equals("611001305")) ){
							JASPER_JRXML_FILENAME="EpisPaymentReceipt";
						}*/
						
						documentObject.setDocumentDate(documentObject.getDocumentDate());
						documentObject.setBranchCode(documentObject.getBranchCode());
						documentObject.setBranchName(documentObject.getBranchName());
						documentObject.setDocumentNo(documentObject.getDocumentNo());
						documentObject.setUserName(officer.getGivenName() + " " + officer.getFamilyName());
						if(!"1".equals(printReceiptDTO.getCheckSpecial())){
							documentObject.setNote(note);
							documentObject.setAmountBeforeVatTax(documentObject.getAmountBeforeVat());
							documentObject.setDiscountTax(documentObject.getDiscount());
							documentObject.setAmountSumBeforeVatTax(documentObject.getAmountSumBeforeVat());
							documentObject.setVatTax(documentObject.getVat());
							documentObject.setAmountSumAfterVat(documentObject.getAmountSumAfterVat());
						}
						if (null!=documentObject.getCurrencyRate()) {
							DecimalFormatSymbols symbols = new DecimalFormatSymbols();
							symbols.setGroupingSeparator(',');
							symbols.setDecimalSeparator('.');
							String pattern = "#,##0.0#";
							DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
							decimalFormat.setParseBigDecimal(true);
							BigDecimal hundPer = new BigDecimal("100");
							BigDecimal hundPerVat = null!=documentObject.getVatRate()?hundPer.add(documentObject.getVatRate()):hundPer.add(BigDecimal.ZERO);
								summaryAmountBeforeVatTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryAmountSumAfterVat());
								summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(documentObject.getCurrencyRate());
								//summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(hundPer).divide(hundPerVat, 2, BigDecimal.ROUND_FLOOR);
								summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(hundPer).divide(hundPerVat, 2, BigDecimal.ROUND_HALF_UP);//by NSD 27-04-2017
								documentObject.setSummaryAmountBeforeVatTH(summaryAmountBeforeVatTH);

								summaryAmountSumAfterVatTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryAmountSumAfterVat());
								summaryAmountSumAfterVatTH = summaryAmountSumAfterVatTH.multiply(documentObject.getCurrencyRate());
								documentObject.setSummaryAmountSumAfterVatTH(summaryAmountSumAfterVatTH);

								summaryDiscountTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryDiscount());
								summaryDiscountTH = summaryDiscountTH.multiply(documentObject.getCurrencyRate());
								documentObject.setSummaryDiscountTH(summaryDiscountTH);
								
								summaryAmountSumBeforeVatTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryAmountSumBeforeVat());
								summaryAmountSumBeforeVatTH = summaryAmountBeforeVatTH.subtract(summaryDiscountTH);
								documentObject.setSummaryAmountSumBeforeVatTH(summaryAmountSumBeforeVatTH);
		
								summaryVatTH = summaryAmountSumAfterVatTH.subtract(summaryAmountBeforeVatTH);
								documentObject.setSummaryVatTH(summaryVatTH);
		
								additionalDiscountAfterVatTH = (BigDecimal) decimalFormat.parse(documentObject.getAdditionalDiscountAfterVat());
								additionalDiscountAfterVatTH = additionalDiscountAfterVatTH.multiply(documentObject.getCurrencyRate());
								documentObject.setAdditionalDiscountAfterVatTH(additionalDiscountAfterVatTH);
								
						}
						// setting billing Group
						//documentObject.setBillingGroup(billingGroup);
						if(StringUtils.equals(rct.getFlgHeader(), FLG_HEADER_1)){
							receiptFormat = RECEIPT_FORMAT_RECEIVE_WH;
						}else if(StringUtils.equals(rct.getFlgHeader(), FLG_HEADER_2)){
							receiptFormat = RECEIPT_FORMAT_RECEIVE_ONLY;
						}else if(StringUtils.equals(rct.getFlgHeader(), FLG_HEADER_3)){
							receiptFormat = RECEIPT_FORMAT_WH_ONLY;
						}
                        receiptFormat = documentObject.getReceiptFormat()!=null?documentObject.getReceiptFormat():receiptFormat;
						documentObject.setReceiptFormat(receiptFormat);
						documentObject.setSubstitute(isSubstitute);
						logger.info("getReceiptType===>["+documentObject.getReceiptType()+"]");
						
						// set Branch Name For English
						//if(!StringUtils.isEmpty(printReceiptDTO.getReceiptLang())
						//		&& printReceiptDTO.getReceiptLang().equals(AppConstants.LANGUAGE_KEY.ENG.name()))
							documentObject.setBranchName(calculateBranchNameForReceiptLang(rct,receiptLang));
						// set Title for Copy
						if(printReceiptDTO.isCopy())
							getReceiptTitle(documentObject,isSubstitute,receiptLang);
						
						if(isSubstitute)
							getReprintVersion(documentObject,rct.getId(),printReceiptDTO.getReason(),printReceiptDTO.getReprint(),receiptLang);
							
						if(StringUtils.isNotBlank(receiptFormat))
							setReceiptFormat(rct.getId(),receiptFormat);
						
						List<CustomerTaxReceiptTO> printCollections = new ArrayList<CustomerTaxReceiptTO>();
						for(int i=0; i<collections.size(); i++) {
							printCollections.add((CustomerTaxReceiptTO)collections.get(i));
						}
						parameters.put("ReportSource", documentObject);
		
						response.setContentType("application/pdf");
						
						net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
						JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
					}
//				}
//			}
		}
		pushReportToOutputStream(response, jasperPrints);
	}
	
	@RequestMapping(value = "/printSAPAR.pdf", method = RequestMethod.POST)
	public void printSAPAR(HttpServletRequest request, HttpServletResponse response, BounceChequeDTO bounceChequeDTO)
			throws Exception {
		
		PayBounceChequeDTO payBounceChequeDTO = new PayBounceChequeDTO();
		BounceChequeService bService = new BounceChequeService();
		SapForReportDTO sapForReportDTO = new SapForReportDTO();
		String JASPER_JRXML_FILENAME = "SapARReport";
		Double AmountBeforeVatTH, summaryAmountBeforeVatTH, summaryDiscountTH, summaryAmountSumBeforeVatTH, summaryAmountSumAfterVatTH,amountPaid, summaryVatTH, additionalDiscountAfterVatTH, AmountSumAfterVatTH = 0.00;
	
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		BigDecimal hundPer = new BigDecimal("100");
//		for (PayBounceChequeDTO dto : bounceChequeDTO.getPayBounceChequeDTOList()) {

					List<DetailARCustomerDTO> detailARCustomer = new ArrayList<DetailARCustomerDTO>();
//					if(AppUtil.isCollectionHasValue(detailARCustomer)) {
						DetailARCustomerDTO detailARCustomerObject;
						for(int i=0; i<bounceChequeDTO.getDetailARCustomerDTOList().size(); i++) {
							detailARCustomerObject = new DetailARCustomerDTO();
							detailARCustomerObject = bounceChequeDTO.getDetailARCustomerDTOList().get(i);
							boolean testDetail = false;
							if(testDetail) {
								detailARCustomerObject.setAddress(detailARCustomerObject.getAddress());
								detailARCustomerObject.setArAccountCode(detailARCustomerObject.getArAccountCode());
								detailARCustomerObject.setArGroup(detailARCustomerObject.getArGroup());
								detailARCustomerObject.setArName(detailARCustomerObject.getArName());
								detailARCustomerObject.setBranchAR(detailARCustomerObject.getBranchAR());
								detailARCustomerObject.setGlAccount(detailARCustomerObject.getGlAccount());
								detailARCustomerObject.setRegionKey1(detailARCustomerObject.getRegionKey1());
								detailARCustomerObject.setTaxId(detailARCustomerObject.getTaxId());
							}
							
							sapForReportDTO.setAddress(detailARCustomerObject.getAddress()==null?"-":detailARCustomerObject.getAddress());
							sapForReportDTO.setArAccountCode(detailARCustomerObject.getArAccountCode()==null?"-":detailARCustomerObject.getArAccountCode());
							sapForReportDTO.setArName(detailARCustomerObject.getArName()==null?"-":detailARCustomerObject.getArName());
							sapForReportDTO.setDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(detailARCustomerObject.getUpDDate())); // getDocumentDate
							sapForReportDTO.setNo(detailARCustomerObject.getReportNo()==null?"-":detailARCustomerObject.getReportNo());  // getDocumentNo
							sapForReportDTO.setPaidBy("โอนเงินต่างประเทศ");
							sapForReportDTO.setCurrency(bounceChequeDTO.getPayBounceChequeDTOList().get(0).getCurrency()==null?"-":bounceChequeDTO.getPayBounceChequeDTOList().get(0).getCurrency());
							sapForReportDTO.setRateChangeAmount(bounceChequeDTO.getPayBounceChequeDTOList().get(0).getRateChange()==null?"-":bounceChequeDTO.getPayBounceChequeDTOList().get(0).getRateChange().toString());
							sapForReportDTO.setRemark(detailARCustomerObject.getRemark()==null?"-":detailARCustomerObject.getRemark());
							sapForReportDTO.setServiceKey3(bounceChequeDTO.getPayBounceChequeDTOList().get(0).getServiceKey3()==null?"-":bounceChequeDTO.getPayBounceChequeDTOList().get(0).getServiceKey3());
//							sapForReportDTO.setSubNo("Sub No.");
							sapForReportDTO.setTotalCharge(bounceChequeDTO.getPayBounceChequeDTO().getTotalCharge()==null?0.00:bounceChequeDTO.getPayBounceChequeDTO().getTotalCharge());
							sapForReportDTO.setTotalChargeBath(bounceChequeDTO.getPayBounceChequeDTO().getTotalChargeBath()==null?0.00:bounceChequeDTO.getPayBounceChequeDTO().getTotalChargeBath());
							sapForReportDTO.setVat(bounceChequeDTO.getPayBounceChequeDTO().getVat()==null?0:bounceChequeDTO.getPayBounceChequeDTO().getVat().intValue());
							sapForReportDTO.setVatBath(bounceChequeDTO.getPayBounceChequeDTO().getVatBath()==null?0.00:bounceChequeDTO.getPayBounceChequeDTO().getVatBath());
							sapForReportDTO.setVatNo(detailARCustomerObject.getBranchName()==null?"-":detailARCustomerObject.getBranchName());  //getBranchName
//							sapForReportDTO.setVatRD(bounceChequeDTO.getPayBounceChequeDTO().getVat());
							sapForReportDTO.setAmountBath((BigDecimal) decimalFormat.parse(bounceChequeDTO.getPayBounceChequeDTO().getBalanceDueBath().toString()));
							sapForReportDTO.setChargeBath((BigDecimal) decimalFormat.parse(bounceChequeDTO.getPayBounceChequeDTO().getChargeBath().toString()));
							sapForReportDTO.setAmount((BigDecimal) decimalFormat.parse(bounceChequeDTO.getPayBounceChequeDTO().getBalanceDue().toString()));
							
//						}
						
//						if (null!=documentObject.getCurrencyRate()) {
							
//							BigDecimal hundPerVat = null!=documentObject.getVatRate()?hundPer.add(documentObject.getVatRate()):hundPer.add(BigDecimal.ZERO);
								summaryAmountBeforeVatTH = bounceChequeDTO.getPayBounceChequeDTO().getPreItemsDiscountBath();
//								summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(documentObject.getCurrencyRate());
//								summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(hundPer).divide(hundPerVat, 2, BigDecimal.ROUND_HALF_UP);//by NSD 27-04-2017
								payBounceChequeDTO.setPreItemsDiscountBath(summaryAmountBeforeVatTH);

								summaryAmountSumAfterVatTH = bounceChequeDTO.getPayBounceChequeDTO().getTotalChargeBath();
//								summaryAmountSumAfterVatTH = summaryAmountSumAfterVatTH.multiply(documentObject.getCurrencyRate());
								payBounceChequeDTO.setTotalChargeBath(summaryAmountSumAfterVatTH);

								summaryDiscountTH = bounceChequeDTO.getPayBounceChequeDTO().getVatBath();
//								summaryDiscountTH = summaryDiscountTH.multiply(documentObject.getCurrencyRate());
								payBounceChequeDTO.setVatBath(summaryDiscountTH);
								
								summaryAmountSumBeforeVatTH = bounceChequeDTO.getPayBounceChequeDTO().getTotalCharge();
//								summaryAmountSumBeforeVatTH = summaryAmountBeforeVatTH.subtract(summaryDiscountTH);
								payBounceChequeDTO.setTotalCharge(summaryAmountSumBeforeVatTH);
		
								summaryVatTH = bounceChequeDTO.getPayBounceChequeDTO().getVat();
								payBounceChequeDTO.setVat(summaryVatTH);
		
								additionalDiscountAfterVatTH = bounceChequeDTO.getPayBounceChequeDTO().getPreItemsDiscount();
//								additionalDiscountAfterVatTH = additionalDiscountAfterVatTH.multiply(new BigDecimal(dto.getPreItemsDiscount()));
								payBounceChequeDTO.setPreItemsDiscount(additionalDiscountAfterVatTH);
								
								payBounceChequeDTO.setDocHead("TestText");
								for(int ii=0; ii<bounceChequeDTO.getPayBounceChequeDTOList().size(); ii++) {
									bounceChequeDTO.getPayBounceChequeDTOList().get(ii).setOrderNo(Integer.toString(ii+1));
									bounceChequeDTO.getPayBounceChequeDTOList().get(ii).setAmountARin(bService.changeOut4In(bounceChequeDTO.getPayBounceChequeDTOList().get(ii).getAmountPay(), (BigDecimal) decimalFormat.parse(bounceChequeDTO.getPayBounceChequeDTOList().get(ii).getVat().toString())));
								}
//						}
						bounceChequeDTO.getPayBounceChequeDTO().setDocHead(payBounceChequeDTO.getDocHead());
						bounceChequeDTO.getPayBounceChequeDTO().setServiceKey3(bounceChequeDTO.getPayBounceChequeDTOList().get(0).getServiceKey3());
						parameters.put("ReportSource", sapForReportDTO);
		
						response.setContentType("application/pdf");
						
						net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
						JRDataSource dataSource = (bounceChequeDTO.getPayBounceChequeDTOList() != null && !bounceChequeDTO.getPayBounceChequeDTOList().isEmpty()) ? new JRBeanCollectionDataSource(bounceChequeDTO.getPayBounceChequeDTOList()) : new JREmptyDataSource();
				        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
					}
//				}
//			}
//		}
		pushReportToOutputStream(response, jasperPrints);
	}

	
	@RequestMapping(value="/printPaymentReceiptOTBOSS.pdf", method=RequestMethod.POST)
	public void printPaymentReceiptOTBOSS(HttpServletRequest request, HttpServletResponse response, PrintReceiptDTO printReceiptDTO, List<Invoice> invoiceList
			) throws Exception {
		String receiptLang = printReceiptDTO.getReceiptLang();
		//String billingGroup = printReceiptDTO.getBillingGroup();
		String receiptFormat = printReceiptDTO.getReceiptFormat();
		String note = printReceiptDTO.getNote();

		boolean isSubstitute = (printReceiptDTO.getSubstitute()!=null)?Boolean.valueOf(printReceiptDTO.getSubstitute()):false;
		
		String JASPER_JRXML_FILENAME = getJasperFileNameTBOSS(printReceiptDTO, invoiceList);
	
		Map<String, Object> parameters = new HashMap<String, Object>();
		Officer officer = null;
		if(AppUtil.isCollectionHasValue(printReceiptDTO.getReceipts())) {
			officer = officerRepo.findByName(request.getUserPrincipal().getName());
		}
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		for (Receipt rct : printReceiptDTO.getReceipts()) {
//			for (Customer cust : printReceiptDTO.getCustomers()) {
//				if(cust.getCustNo().equalsIgnoreCase(rct.getAccountNo())) {
				//String REPORT_OUTPUT_FILENAME = request.getUserPrincipal().getName()+"-EpisPaymentReceipt";

					List<CustomerTaxReceiptTO> collections = new ArrayList<CustomerTaxReceiptTO>();
					prepareCustomerTaxReceiptTOTBOSS(collections, rct, printReceiptDTO);
					if(AppUtil.isCollectionHasValue(collections)) {
						CustomerTaxReceiptTO documentObject = (CustomerTaxReceiptTO)collections.get(0);
						List<Invoice> invoices = invoiceRepo.findByReceiptId(rct.getId());
						 OTTBossDTO bossDTO = new OTTBossDTO();
						 for(Invoice inv : invoices) {
							
							
							 
						
							 bossDTO = otbossservice.seachCusOTBoss(documentObject.getCustomerNo(), inv.getSource(), documentObject.getInvoiceNo());
						 }
						documentObject.setDocumentDate(documentObject.getDocumentDate());
						documentObject.setBranchCode(documentObject.getBranchCode());
						documentObject.setBranchName(documentObject.getBranchName());
						documentObject.setDocumentNo(documentObject.getDocumentNo());
						documentObject.setBillingGroup(bossDTO.getData().get(0).getServ_desc());
						documentObject.setUserName(officer.getGivenName() + " " + officer.getFamilyName());
						if(!"1".equals(printReceiptDTO.getCheckSpecial())){
							documentObject.setNote(note);
							documentObject.setAmountBeforeVatTax(documentObject.getAmountBeforeVat());
							documentObject.setDiscountTax(documentObject.getDiscount());
							documentObject.setAmountSumBeforeVatTax(documentObject.getAmountSumBeforeVat());
							documentObject.setVatTax(documentObject.getVat());
							documentObject.setAmountSumAfterVat(documentObject.getAmountSumAfterVat());
						}
						if (null!=documentObject.getCurrencyRate()) {
							DecimalFormatSymbols symbols = new DecimalFormatSymbols();
							symbols.setGroupingSeparator(',');
							symbols.setDecimalSeparator('.');
							String pattern = "#,##0.0#";
							DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
							decimalFormat.setParseBigDecimal(true);
							BigDecimal hundPer = new BigDecimal("7");
							BigDecimal hundPerVat = null!=documentObject.getVatRate()?hundPer.add(documentObject.getVatRate()):hundPer.add(BigDecimal.ZERO);
								summaryAmountBeforeVatTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryAmountSumBeforeVat());
								summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(documentObject.getCurrencyRate());
								//summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(hundPer).divide(hundPerVat, 2, BigDecimal.ROUND_FLOOR);
								//summaryAmountBeforeVatTH = summaryAmountBeforeVatTH.multiply(hundPer).divide(hundPerVat, 2, BigDecimal.ROUND_HALF_UP);//by NSD 27-04-2017
								//documentObject.setSummaryAmountBeforeVatTH(summaryAmountBeforeVatTH);

								

								summaryDiscountTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryDiscount());
								summaryDiscountTH = summaryDiscountTH.multiply(documentObject.getCurrencyRate());
								//documentObject.setSummaryDiscountTH(summaryDiscountTH);
								
								summaryAmountSumBeforeVatTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryAmountSumBeforeVat());
//								summaryAmountSumBeforeVatTH = summaryAmountSumBeforeVatTH.subtract(summaryDiscountTH);
								documentObject.setSummaryAmountSumBeforeVatTH(summaryAmountBeforeVatTH);
		
								summaryVatTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryVat());
								documentObject.setSummaryVatTH(summaryVatTH);
		
								//summaryAmountSumAfterVatTH = (BigDecimal) decimalFormat.parse(documentObject.getSummaryAmountBeforeVat());
								//summaryAmountSumAfterVatTH = summaryAmountSumAfterVatTH.multiply(documentObject.getCurrencyRate());
								summaryAmountSumAfterVatTH = summaryAmountBeforeVatTH.add(summaryVatTH);
								summaryAmountSumAfterVatTH = summaryAmountSumAfterVatTH.subtract(summaryDiscountTH);
								documentObject.setSummaryAmountSumAfterVat(summaryAmountSumAfterVatTH.toEngineeringString());
								
								additionalDiscountAfterVatTH = (BigDecimal) decimalFormat.parse(documentObject.getAdditionalDiscountAfterVat());
								additionalDiscountAfterVatTH = additionalDiscountAfterVatTH.multiply(documentObject.getCurrencyRate());
								documentObject.setAdditionalDiscountAfterVatTH(additionalDiscountAfterVatTH);
								
								amountPaid = (BigDecimal) decimalFormat.parse(documentObject.getAmountPaid());
								
								amountPaid = summaryAmountSumAfterVatTH;
								
								documentObject.setAmountPaid(amountPaid.toEngineeringString());
								
								documentObject.setSummaryAmountBeforeVat(null);
								documentObject.setSummaryDiscount(null);
								
						}
						// setting billing Group
						//documentObject.setBillingGroup(billingGroup);
						if(StringUtils.equals(rct.getFlgHeader(), FLG_HEADER_1)){
							receiptFormat = RECEIPT_FORMAT_RECEIVE_WH;
						}else if(StringUtils.equals(rct.getFlgHeader(), FLG_HEADER_2)){
							receiptFormat = RECEIPT_FORMAT_RECEIVE_ONLY;
						}else if(StringUtils.equals(rct.getFlgHeader(), FLG_HEADER_3)){
							receiptFormat = RECEIPT_FORMAT_WH_ONLY;
						}
                        receiptFormat = documentObject.getReceiptFormat()!=null?documentObject.getReceiptFormat():receiptFormat;
						documentObject.setReceiptFormat(receiptFormat);
						documentObject.setSubstitute(isSubstitute);
						logger.info("getReceiptType===>["+documentObject.getReceiptType()+"]");
						
						// set Branch Name For English
						//if(!StringUtils.isEmpty(printReceiptDTO.getReceiptLang())
						//		&& printReceiptDTO.getReceiptLang().equals(AppConstants.LANGUAGE_KEY.ENG.name()))
							documentObject.setBranchName(calculateBranchNameForReceiptLang(rct,receiptLang));
						// set Title for Copy
						if(printReceiptDTO.isCopy())
							getReceiptTitle(documentObject,isSubstitute,receiptLang);
						
						if(isSubstitute)
							getReprintVersion(documentObject,rct.getId(),printReceiptDTO.getReason(),printReceiptDTO.getReprint(),receiptLang);
							
						if(StringUtils.isNotBlank(receiptFormat))
							setReceiptFormat(rct.getId(),receiptFormat);
						
						List<CustomerTaxReceiptTO> printCollections = new ArrayList<CustomerTaxReceiptTO>();
						for(int i=0; i<collections.size(); i++) {
							printCollections.add((CustomerTaxReceiptTO)collections.get(i));
						}
						parameters.put("ReportSource", documentObject);
		
						response.setContentType("application/pdf");
						
						net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
						JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
					}
//				}
//			}
		}
		pushReportToOutputStream(response, jasperPrints);
	}
	private void setReceiptFormat(Long receiptId,String receiptFormat){
		episService.setReceiptFormat(receiptId,receiptFormat);
	}

	private String getReceiptName(String FlgHeader,String language){
		String receiptName = episService.getReceiptName(FlgHeader,language);
		return receiptName;
	}
	private void getReprintVersion(CustomerTaxReceiptTO documentObject,Long receiptId,String reason,String reprint,String receiptLang){
		BigDecimal category =BigDecimal.valueOf(RECEIPT_PRINT_TYPES.SUBSTITUE.ordinal()+1);
		Integer nextVersion =episService.getNextVersionOfTrsReprint(receiptId,category,reprint);
		BigDecimal currentVersion =  "1".equalsIgnoreCase(reprint)?BigDecimal.valueOf(nextVersion):BigDecimal.valueOf(nextVersion-1);
		th.net.cat.epis.dto.Trsreprint trsReprintByVersion = episService.getTrsReprintByVersion(receiptId, category, currentVersion);
		if(trsReprintByVersion!=null && trsReprintByVersion.getReason()!=null)
			reason = trsReprintByVersion.getReason();
		StringBuilder sb = new StringBuilder();
		sb.append("SUBSTITUE_REASON");
		if(!StringUtils.isBlank(receiptLang) && AppConstants.LANGUAGE_KEY.ENG.name().equalsIgnoreCase(receiptLang)){
			sb.append("_ENG");
		} else {
			sb.append("_TH");
		}

		documentObject.setReasonOfSubstitute(messages.getMessage(sb.toString(), new Object[] { currentVersion }, null)+" "+reason);

	}
	//by NSD 23-02-2017
	private void getReprintVersionOther(CustomerTaxReceiptForOthersPaymentTO documentObject,Long receiptId,String reason,String reprint,String receiptLang){
		BigDecimal category =BigDecimal.valueOf(RECEIPT_PRINT_TYPES.SUBSTITUE.ordinal()+1);
		Integer nextVersion =episService.getNextVersionOfTrsReprint(receiptId,category,reprint);
		BigDecimal currentVersion =  "1".equalsIgnoreCase(reprint)?BigDecimal.valueOf(nextVersion):BigDecimal.valueOf(nextVersion-1);
		th.net.cat.epis.dto.Trsreprint trsReprintByVersion = episService.getTrsReprintByVersion(receiptId, category, currentVersion);
		if(trsReprintByVersion!=null && trsReprintByVersion.getReason()!=null)
			reason = trsReprintByVersion.getReason();
		StringBuilder sb = new StringBuilder();
		sb.append("SUBSTITUE_REASON");
		if(!StringUtils.isBlank(receiptLang) && AppConstants.LANGUAGE_KEY.ENG.name().equalsIgnoreCase(receiptLang)){
			sb.append("_ENG");
		} else {
			sb.append("_TH");
		}

		documentObject.setReasonOfSubstitute(messages.getMessage(sb.toString(), new Object[] { currentVersion }, null)+" "+reason);

	}
	private void getReceiptTitle(CustomerTaxReceiptTO documentObject,Boolean substitute,String receiptLang){
		StringBuilder sb = new StringBuilder();
		sb.append(documentObject.getReceiptType());
		sb.append("_");
		sb.append(documentObject.getReceiptFormat().toLowerCase());//by NSD 26-04-2017
		if(!StringUtils.isBlank(receiptLang) && AppConstants.LANGUAGE_KEY.ENG.name().equalsIgnoreCase(receiptLang)){
			sb.append("_ENG");
			documentObject.setReceiptTitle(messages.getMessage(sb.toString(), null, null));
		} else {
			documentObject.setTitle(messages.getMessage(AppConstants.RECEIPT_PRINT_COPY_KEY, null, null));
			documentObject.setReceiptTitle(messages.getMessage(sb.toString(), null, null));
		}
	}	
	@RequestMapping(value="/printPaymentOthersReceipt.pdf", method=RequestMethod.POST)
	public void printPaymentOthersReceipt(HttpServletRequest request, HttpServletResponse response, PrintReceiptDTO printReceiptDTO) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisPaymentOthersReceipt";

		Map<String, Object> parameters = new HashMap<String, Object>();
		Officer officer = null;
		boolean isSubstitute = (printReceiptDTO.getSubstitute()!=null)?Boolean.valueOf(printReceiptDTO.getSubstitute()):false;
		String receiptLang = printReceiptDTO.getReceiptLang();
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setMinimumIntegerDigits(1);
		
		if(AppUtil.isCollectionHasValue(printReceiptDTO.getReceipts())) {
			officer = officerRepo.findByName(request.getUserPrincipal().getName());
		}
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		for (Receipt rct : printReceiptDTO.getReceipts()) {
			
			List<CustomerTaxReceiptForOthersPaymentTO> collections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
			prepareCustomerTaxReceiptForOthersPaymentTO(collections, rct);
			if(AppUtil.isCollectionHasValue(collections)) {
				CustomerTaxReceiptForOthersPaymentTO documentObject = (CustomerTaxReceiptForOthersPaymentTO)collections.get(0);
				//documentObject.setImagePathRpt(context.getRealPath("images")+File.separatorChar+"CATTelecom_Logo.png");
				documentObject.setDocumentDate(documentObject.getDocumentDate());
				documentObject.setFlgHeader(documentObject.getFlgHeader());
				documentObject.setBranchCode(documentObject.getBranchCode());
				documentObject.setBranchName(documentObject.getBranchName());
				documentObject.setDocumentNo(documentObject.getDocumentNo());
				documentObject.setUserName(officer.getGivenName() + " " + officer.getFamilyName());
				documentObject.setPromotionTxt(rct.getPromotion());
				List<CustomerTaxReceiptForOthersPaymentTO> printCollections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
				for(int i=0; i<collections.size(); i++) {
					printCollections.add((CustomerTaxReceiptForOthersPaymentTO)collections.get(i));
				}
				
				if(!AppConstants.CURRENCY_CODE_TH.equalsIgnoreCase(documentObject.getCurrencyCode())) {
					MasChangeRate changeRate = new MasChangeRate();
					try { 
						String queryStr = "SELECT * FROM MASCHANGERATE WHERE CODE ='"+documentObject.getCurrencyCode()+"' ";

						episJdbcTemplate.query(queryStr, new RowCallbackHandler() {
							@Override
							public void processRow(ResultSet row) throws SQLException {
								changeRate.setId(row.getLong("MASCHANGERATEID"));
								changeRate.setRateCode(row.getString("RATECODE"));
								changeRate.setMessage(row.getString("MESSAGE"));
								changeRate.setDescription(row.getString("DESCRIPTION"));
								changeRate.setDateUsedShow(row.getString("DATEUSED_SHOW"));
								changeRate.setRateUnit(row.getBigDecimal("RATEUNIT"));
								changeRate.setCurrencySymbol(row.getString("CURRENCYSYMBOL"));
								changeRate.setCountry(row.getString("COUNTRY"));
							}
						});
//						changeRate = exchangeRepo.findByCode(documentObject.getCurrencyName()); 
					} catch (Exception e) { }
					
					// set Title for Copy
					if(printReceiptDTO.isCopy()){
						documentObject.setTitle(messages.getMessage(AppConstants.RECEIPT_PRINT_COPY_KEY, null, null));
						JASPER_JRXML_FILENAME = "EpisPayOthersExchangeReceipt_Copy";
						documentObject.setCurrencyName(changeRate.getMessage());
					}else{
						documentObject.setTitle("");
						JASPER_JRXML_FILENAME = "EpisPayOthersExchangeReceipt";
						documentObject.setCurrencyName(changeRate.getMessage());
					}
				} else {
					// set Title for Copy
					if(printReceiptDTO.isCopy()){
						documentObject.setTitle(messages.getMessage(AppConstants.RECEIPT_PRINT_COPY_KEY, null, null));
						JASPER_JRXML_FILENAME = "EpisPaymentOthersCopyReceipt";
					}else{
						documentObject.setTitle("");
					}
				}

				// set vat rate to print in receipt
				String stringVatRate = "";
				String status[] = {null,null};
				Receipt receipt = receiptRepo.findOne(rct.getId());
				for(Service serviceObj : receipt.getServices()){
					if(serviceObj.getVatRate()!=null){
						if(serviceObj.getVatRate().equals(BigDecimal.valueOf(0)) ){
							status[0] = "0%";
						}
						else{
							status[1] = serviceObj.getVatRate()+"%";
						}
					}
				}
				for(int i=0; i<status.length;i++){
					if(status[i] != null){
						if(stringVatRate != ""){
							stringVatRate += ", ";
						}
						stringVatRate += status[i];
					}
				}
				if(stringVatRate == "") stringVatRate += rct.getVatRate()+"%";
				documentObject.setStringVatRate(stringVatRate);
				documentObject.setExcDiscount(df.format(receipt.getSpecialDiscount()));
				documentObject.setSubstitute(isSubstitute);
				if(isSubstitute){
					getReprintVersionOther(documentObject,rct.getId(),printReceiptDTO.getReason(),printReceiptDTO.getReprint(),receiptLang);
				}

				if(StringUtils.isNotBlank(printReceiptDTO.getReceiptFormat()))
					setReceiptFormat(rct.getId(),printReceiptDTO.getReceiptFormat());

				parameters.put("ReportSource", documentObject);

				response.setContentType("application/pdf");
				net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			}
		}
		pushReportToOutputStream(response, jasperPrints);
	}
	@RequestMapping(value="/copyprintPaymentReceipt.pdf", method={RequestMethod.POST})
	public void copyprintPaymentReceipts(HttpServletRequest request, HttpServletResponse response,
			PrintReceiptDTO printReceiptDTO
			) throws Exception {

		if(printReceiptDTO !=null && printReceiptDTO.getId()!=null){
			Long receiptId = printReceiptDTO.getId();
			Receipt receipt = receiptRepo.findOne(receiptId);
			if(receipt == null) return;
			List<Invoice> invoiceList = invoiceRepo.findByReceiptId(receiptId);
			//PrintReceiptDTO printReceiptDTO = new PrintReceiptDTO();
			printReceiptDTO.setCopy(true);
			printReceiptDTO.setReceipts(Lists.newArrayList(receipt));
			printReceiptDTO.setBillingGroup(receipt.getBillingGroupFull());
			printReceiptDTO.setReceiptLang(receipt.getLanguage());

			printReceiptDTO.setReceiptFormat(setReceiptFormat(receipt.getId()));

			if(AppUtil.isCollectionHasValue(invoiceList)) {
				printPaymentReceipt(request, response, printReceiptDTO, invoiceList);
			}else if(StringUtils.equals(receipt.getPayment().getType(), TOPUP)){
				printPaymentTopUpReceipt(request, response, printReceiptDTO);
			}else if(StringUtils.equals(receipt.getPayment().getType(), MNP)){
				printPaymentMobileReceipt(request, response, printReceiptDTO);
			}else if(StringUtils.equals(receipt.getPayment().getType(), AGENT)){
				printPaymentAgentReceipt(request, response, printReceiptDTO);
			}else{
				printPaymentOthersReceipt(request, response, printReceiptDTO);
			}

		}
	}

	@RequestMapping(value="/printPaymentMobileReceipt.pdf", method=RequestMethod.POST)
	public void printPaymentMobileReceipt(HttpServletRequest request, HttpServletResponse response, PrintReceiptDTO printReceiptDTO) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisPaymentMNPReceipt";
		String SRC_CODE = printReceiptDTO.getReceipts().get(0).getRef1() ;//"AIS";
		String CATEGORY = "source";
		boolean isSubstitute = (printReceiptDTO.getSubstitute()!=null)?Boolean.valueOf(printReceiptDTO.getSubstitute()):false;
		String receiptLang = printReceiptDTO.getReceiptLang();
		String srcAddress = "";

		Map<String, Object> parameters = new HashMap<String, Object>();
		Officer officer = null;
		if(AppUtil.isCollectionHasValue(printReceiptDTO.getReceipts())) {
			officer = officerRepo.findByName(request.getUserPrincipal().getName());
		}
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		for (Receipt rct : printReceiptDTO.getReceipts()) {
			List<CustomerTaxReceiptForOthersPaymentTO> collections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
			prepareCustomerTaxReceiptForMobilePaymentTO(collections, rct);
			if(AppUtil.isCollectionHasValue(collections)) {
				CustomerTaxReceiptForOthersPaymentTO documentObject = (CustomerTaxReceiptForOthersPaymentTO)collections.get(0);
				//documentObject.setImagePathRpt(context.getRealPath("images")+File.separatorChar+"CATTelecom_Logo.png");
				documentObject.setDocumentDate(documentObject.getDocumentDate());
				documentObject.setBranchCode(documentObject.getBranchCode());
				documentObject.setBranchName(documentObject.getBranchName());
				documentObject.setDocumentNo(documentObject.getDocumentNo());
				documentObject.setUserName(officer.getGivenName() + " " + officer.getFamilyName());
				List<CustomerTaxReceiptForOthersPaymentTO> printCollections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
				Receipt receipt = receiptRepo.findOne(rct.getId());
				for(int i=0; i<collections.size(); i++) {
					printCollections.add((CustomerTaxReceiptForOthersPaymentTO)collections.get(i));
				}
				documentObject.setPayType("MNP");
				if(!StringUtils.isEmpty(SRC_CODE)){
					List<th.net.cat.epis.entity.Enum> enumList = enumRepo.findByCategoryAndCode(CATEGORY, SRC_CODE);
					srcAddress = enumList.get(0).getDescFullTh();
				}else if(!StringUtils.isEmpty(documentObject.getSourceAddress())){
					List<th.net.cat.epis.entity.Enum> enumList = enumRepo.findByCategoryAndCode(CATEGORY, documentObject.getSourceAddress());
					srcAddress = enumList.get(0).getDescFullTh();
				}
				documentObject.setSourceAddress(srcAddress);
				// set Title for Copy
				if(printReceiptDTO.isCopy()){
					documentObject.setTitle(messages.getMessage(AppConstants.RECEIPT_PRINT_COPY_KEY, null, null));
					JASPER_JRXML_FILENAME = "EpisPaymentMNPCopyReceipt";
					//documentObject.setReceiptTitle(messages.getMessage(sb.toString(), null, null));
				}else{
					documentObject.setTitle("");
				}
				//getReceiptTitle(documentObject,isSubstitute,receiptLang);
				documentObject.setSubstitute(isSubstitute);
				if(isSubstitute){
					//documentObject.setReasonOfSubstitute(printReceiptDTO.getReason());
					getReprintVersionOther(documentObject,rct.getId(),printReceiptDTO.getReason(),printReceiptDTO.getReprint(),receiptLang);
				}
				// Set String vat rate
				documentObject.setStringVatRate(receipt.getVatRate().toString()+"%");
				parameters.put("ReportSource", documentObject);

				response.setContentType("application/pdf");
				net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			}
		}
		pushReportToOutputStream(response, jasperPrints);
	}


	@RequestMapping(value="/printPaymentAgentReceipt.pdf", method=RequestMethod.POST)
	public void printPaymentAgentReceipt(HttpServletRequest request, HttpServletResponse response, PrintReceiptDTO printReceiptDTO) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisPaymentMNPReceipt";
		String SRC_CODE = printReceiptDTO.getReceipts().get(0).getRef2() ;
		boolean isSubstitute = (printReceiptDTO.getSubstitute()!=null)?Boolean.valueOf(printReceiptDTO.getSubstitute()):false;
		String receiptLang = printReceiptDTO.getReceiptLang();
		String srcAddress = "";

		Map<String, Object> parameters = new HashMap<String, Object>();
		Officer officer = null;
		if(AppUtil.isCollectionHasValue(printReceiptDTO.getReceipts())) {
			officer = officerRepo.findByName(request.getUserPrincipal().getName());
		}
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		for (Receipt rct : printReceiptDTO.getReceipts()) {
			List<CustomerTaxReceiptForOthersPaymentTO> collections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
			prepareCustomerTaxReceiptForMobilePaymentTO(collections, rct);
			if(AppUtil.isCollectionHasValue(collections)) {
				CustomerTaxReceiptForOthersPaymentTO documentObject = (CustomerTaxReceiptForOthersPaymentTO)collections.get(0);
				//documentObject.setImagePathRpt(context.getRealPath("images")+File.separatorChar+"CATTelecom_Logo.png");
				documentObject.setDocumentDate(documentObject.getDocumentDate());
				documentObject.setBranchCode(documentObject.getBranchCode());
				documentObject.setBranchName(calculateBranchNameForReceiptLang(rct,rct.getLanguage()));
//				documentObject.setBranchName(documentObject.getBranchName());
				documentObject.setDocumentNo(documentObject.getDocumentNo());
				documentObject.setUserName(officer.getGivenName() + " " + officer.getFamilyName());
				List<CustomerTaxReceiptForOthersPaymentTO> printCollections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
				Receipt receipt = receiptRepo.findOne(rct.getId());
				for(int i=0; i<collections.size(); i++) {
					printCollections.add((CustomerTaxReceiptForOthersPaymentTO)collections.get(i));
				}

				if(!StringUtils.isEmpty(SRC_CODE)&&!SRC_CODE.equalsIgnoreCase(AppConstants.DEDUCT_METHOD_FEE_IN)){
					List<th.net.cat.epis.entity.MasAgent> agentList = agentRepository.findByCode(SRC_CODE);
					srcAddress = agentList.get(0).getCompanyName()+ "\n" + agentList.get(0).getAddress()+ "\nเลขประจําตัวผู้เสียภาษีอากร: " + agentList.get(0).getTaxId();
				}else if(!StringUtils.isEmpty(documentObject.getSourceAddress())&&!documentObject.getSourceAddress().equalsIgnoreCase(AppConstants.DEDUCT_METHOD_FEE_IN)){
					List<th.net.cat.epis.entity.MasAgent> agentList = agentRepository.findByCode(documentObject.getSourceAddress());
					srcAddress = agentList.get(0).getCompanyName()+ "\n" + agentList.get(0).getAddress()+ "\nเลขประจําตัวผู้เสียภาษีอากร: " + agentList.get(0).getTaxId();
				}else {
					srcAddress = "";
				}
				documentObject.setSourceAddress(srcAddress);
				// set Title for Copy
				if(printReceiptDTO.isCopy()){
					documentObject.setTitle(messages.getMessage(AppConstants.RECEIPT_PRINT_COPY_KEY, null, null));
					JASPER_JRXML_FILENAME = "EpisPaymentMNPCopyReceipt";
					//documentObject.setReceiptTitle(messages.getMessage(sb.toString(), null, null));
				}else{
					documentObject.setTitle("");
				}
				//getReceiptTitle(documentObject,isSubstitute,receiptLang);
				documentObject.setSubstitute(isSubstitute);
				if(isSubstitute){
					//documentObject.setReasonOfSubstitute(printReceiptDTO.getReason());
					getReprintVersionOther(documentObject,rct.getId(),printReceiptDTO.getReason(),printReceiptDTO.getReprint(),receiptLang);
				}
				// Set String vat rate
				documentObject.setStringVatRate(receipt.getVatRate().toString()+"%");
				documentObject.setPayType("AGENT");
				documentObject.setBranchPayment(receipt.getAccountBranch());
				parameters.put("ReportSource", documentObject);

				response.setContentType("application/pdf");
				net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			}
		}
		pushReportToOutputStream(response, jasperPrints);
	}

	@RequestMapping(value="/printPaymentTopUpReceipt.pdf", method=RequestMethod.POST)
	public void printPaymentTopUpReceipt(HttpServletRequest request, HttpServletResponse response, PrintReceiptDTO printReceiptDTO) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisPaymentTopUpReceipt";

		Map<String, Object> parameters = new HashMap<String, Object>();
		Officer officer = null;
		String receiptLang = printReceiptDTO.getReceiptLang();
		String billingGroup = printReceiptDTO.getBillingGroup();
		String receiptFormat = printReceiptDTO.getReceiptFormat();
		boolean isSubstitute = (printReceiptDTO.getSubstitute()!=null)?Boolean.valueOf(printReceiptDTO.getSubstitute()):false;
		String serviceType = "";

		if(AppUtil.isCollectionHasValue(printReceiptDTO.getReceipts())) {
			officer = officerRepo.findByName(request.getUserPrincipal().getName());
		}
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		for (Receipt rct : printReceiptDTO.getReceipts()) {
			List<CustomerTaxReceiptForOthersPaymentTO> collections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
			prepareCustomerTaxReceiptForTopUpPaymentTO(collections, rct);
			if(AppUtil.isCollectionHasValue(collections)) {
                String refNo = "";//by NSD 16-02-2017
                //List<Service> serviceList = new ArrayList<>(rct.getServices());
				CustomerTaxReceiptForOthersPaymentTO documentObject = (CustomerTaxReceiptForOthersPaymentTO)collections.get(0);
				//documentObject.setImagePathRpt(context.getRealPath("images")+File.separatorChar+"CATTelecom_Logo.png");
				documentObject.setDocumentDate(documentObject.getDocumentDate());
				documentObject.setBranchCode(documentObject.getBranchCode());
				documentObject.setBranchName(documentObject.getBranchName());
				documentObject.setDocumentNo(documentObject.getDocumentNo());
				documentObject.setUserName(officer.getGivenName() + " " + officer.getFamilyName());
                if(!StringUtils.isEmpty(rct.getRef1())){
					refNo = rct.getRef1();//rct.getRefNo();//serviceList.get(0).getRefTransId();
				}else{
					refNo = rct.getRefNo();
				}

				serviceType = rct.getServiceType();
                //if(!StringUtils.isEmpty(rct.getRefNo())){
				/*if(null!=rct.getServices()){
					for(Service service: rct.getServices()){
						serviceType = service.getServiceName();
						refNo = service.getRefTransId();
					}
				}*/
				if(null!=rct.getServices()){
					for(Service service: rct.getServices()){
						serviceType = service.getServiceName();
					}
				}
				documentObject.setRefNo(refNo);
				documentObject.setServiceType(serviceType);
                documentObject.setPromotionTxt(rct.getPromotion());

               // }

				List<CustomerTaxReceiptForOthersPaymentTO> printCollections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
				for(int i=0; i<collections.size(); i++) {
					printCollections.add((CustomerTaxReceiptForOthersPaymentTO)collections.get(i));
				}
				// set Title for Copy
				if(printReceiptDTO.isCopy()){
					documentObject.setTitle(messages.getMessage(AppConstants.RECEIPT_PRINT_COPY_KEY, null, null));
					JASPER_JRXML_FILENAME = "EpisPaymentTopUpCopyReceipt";
					//documentObject.setReceiptTitle(messages.getMessage(sb.toString(), null, null));
				}else{
					documentObject.setTitle("");
				}
					//getReceiptTitle(documentObject,isSubstitute,receiptLang);
				documentObject.setSubstitute(isSubstitute);
				if(isSubstitute){
					//documentObject.setReasonOfSubstitute(printReceiptDTO.getReason());
					getReprintVersionOther(documentObject,rct.getId(),printReceiptDTO.getReason(),printReceiptDTO.getReprint(),receiptLang);
				}

				/*if(StringUtils.isNotBlank(receiptFormat))
					setReceiptFormat(rct.getId(),receiptFormat);*/

				parameters.put("ReportSource", documentObject);

				response.setContentType("application/pdf");
				net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			}
		}
		pushReportToOutputStream(response, jasperPrints);
	}

	@RequestMapping(value="/reprintPaymentReceipt.pdf", method=RequestMethod.POST)
	public void reprintPaymentReceipts(HttpServletRequest request, HttpServletResponse response,
			 PrintReceiptDTO printReceiptDTO
			) throws Exception {
		Long receiptId = printReceiptDTO.getId();
		Receipt receipt = receiptRepo.findOne(receiptId);
		//if(receipt == null) return;
		if(receiptId!=null){
			List<Invoice> invoiceList = invoiceRepo.findByReceiptId(receiptId);
			//PrintReceiptDTO printReceiptDTO = new PrintReceiptDTO();
			printReceiptDTO.setCopy(false);
			printReceiptDTO.setReceipts(Lists.newArrayList(receipt));
			printReceiptDTO.setBillingGroup(receipt.getBillingGroupFull());
			printReceiptDTO.setReceiptLang(receipt.getLanguage());

			printReceiptDTO.setReceiptFormat(setReceiptFormat(receipt.getId()));


			if(AppUtil.isCollectionHasValue(invoiceList)) {
				printPaymentReceipt(request, response, printReceiptDTO, invoiceList);
			}else if(StringUtils.equals(receipt.getPayment().getType(), TOPUP)){
				printPaymentTopUpReceipt(request, response, printReceiptDTO);
			}else if(StringUtils.equals(receipt.getPayment().getType(), MNP)){
				printPaymentMobileReceipt(request, response, printReceiptDTO);
			}else if(StringUtils.equals(receipt.getPayment().getType(), AGENT)){
				printPaymentAgentReceipt(request, response, printReceiptDTO);
			}else {
				printPaymentOthersReceipt(request, response, printReceiptDTO);
			}
		}
	}

	private String setReceiptFormat(Long receiptId){
		ReceiptPrinttypeMapping mapping = new  ReceiptPrinttypeMapping();
		mapping.setReceiptid(receiptId);
		List<ReceiptPrinttypeMapping> receiptPrintTypeMappingList = episService.getReceiptPrinttypeMapping(mapping);
				//receiptPrintTypeMappingRepo.findByReceiptid(receiptId);
		if(receiptPrintTypeMappingList != null && !receiptPrintTypeMappingList.isEmpty()){
			return receiptPrintTypeMappingList.get(0).getPrintType().toLowerCase();
		}else
			return "";
	}
	private String setReceiptFormat2(Receipt rct){
		String receiptFormat = "receive_only";

		return receiptFormat;
	}
	/*
	private String getPromotionMessage(Receipt rct){
		final StringBuilder sb=new StringBuilder();
		// if(rct.getAccountNo().equals("800175483")){
			//String packageID="111";
					episJdbcTemplate.query(
							 " select  promotion.* from PROMOTION_BILLING_MAPPING pro_mapping left join "+
							 " MASPACKAGE_PROMOTION promotion on (promotion.PACKAGE_ID= pro_mapping.PROMOTION_ID ) "+
							 " where pro_mapping.ACCOUNT_NO='"+rct.getAccountNo()+"' ", new RowCountCallbackHandler(){
						// "select * from MASPACKAGE_PROMOTION where package_id='"+packageID+"'", new RowCountCallbackHandler(){
					@Override
					public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
						sb.append(resultSet.getString("PROMOTION")+"\n");
					}
				});
		//}
		return sb.toString();
	}
	*/
	private String getPromotionMessage(Receipt rct,PrintReceiptDTO printReceiptDTO){
		final StringBuilder sb=new StringBuilder();
		// if(rct.getAccountNo().equals("800175483")){
			//String packageID="111";
	 if(printReceiptDTO!=null )
		 if(!printReceiptDTO.isCopy()){
			 if(rct.getAccountNo()!=null && rct.getAccountNo().length()>0){
				 List<PromotionMappingEntity> promotionMappingList= promotionMappingRepository.findActivePromotionMapping(rct.getAccountNo());
				 if(promotionMappingList!=null && promotionMappingList.size()>0){
					 PromotionMappingEntity promotionMappingEntity  = promotionMappingList.get(0);
					 sb.append(promotionMappingEntity.getPromotionName()+"\n");
					 /*
					 // Save to PromotionReceiptMappingEntity
					 PromotionReceiptMappingEntity promotionReceiptMappingEntity 
								= new PromotionReceiptMappingEntity();
					 promotionReceiptMappingEntity.setReceiptid(rct.getId());
					 promotionReceiptMappingEntity.setReceiptno(rct.getNo());
					 promotionReceiptMappingEntity.setPromotionName(promotionMappingEntity.getPromotionName());
					 promotionBillingMappingRepository.save(promotionReceiptMappingEntity);
					 
					 // update promotionMappingEntity to 'S'
					 th.co.softpos.ws.mg.s004.RqHeader rqHeader = new th.co.softpos.ws.mg.s004.RqHeader();
						rqHeader.setFuncNm("S004POS");
						rqHeader.setRqAppId("S004");
						GregorianCalendar c = new GregorianCalendar();
						c.setTime(new Date());
						try {
							rqHeader.setRqDt(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
						} catch (DatatypeConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 th.co.softpos.ws.mg.s004.RqBody rqBody = new th.co.softpos.ws.mg.s004.RqBody();
						th.co.softpos.ws.mg.s004.PosFreeBillno posFreeBillno =
						 		new th.co.softpos.ws.mg.s004.PosFreeBillno();
						posFreeBillno.setPaymentType("EPIS");
						posFreeBillno.setDocSendNo(promotionMappingEntity.getReceiveNo());
						posFreeBillno.setDocRefNo(rct.getNo());
						//posFreeBillno.setDocSendNo(promotionMappingEntity.getReceiveNo());
						//posFreeBillno.setDocRefNo(rct.getNo());
						posFreeBillno.setStatus(S004_STATUS_TYPES.S.name()); // S //
						rqBody.setPosFreeBillno(posFreeBillno);
						th.co.softpos.ws.mg.s004.Request _process_rq = new th.co.softpos.ws.mg.s004.Request();
						_process_rq.setRqHeader(rqHeader);
						_process_rq.setRqBody(rqBody);  
						th.co.softpos.ws.mg.s004.Response _response = s004MGUpdFree.process(_process_rq);
					 
					 promotionMappingEntity.setStatus(S004_STATUS_TYPES.S.name()); // S  
					 promotionMappingRepository.save(promotionMappingEntity);
					 */
				 }
			 }
		 }else {
			 PromotionReceiptMappingEntity promotionReceiptMappingEntity  = 
					 promotionBillingMappingRepository.findPromotionReceiptMappingByReceiptid(rct.getId());
			 if(promotionReceiptMappingEntity!=null && promotionReceiptMappingEntity.getPromotionName()!=null)
				 sb.append(promotionReceiptMappingEntity.getPromotionName()+"\n");
		 }
		/*
		
					episJdbcTemplate.query(x
							 " select  promotion.* from PROMOTION_BILLING_MAPPING pro_mapping left join "+
							 " MASPACKAGE_PROMOTION promotion on (promotion.PACKAGE_ID= pro_mapping.PROMOTION_ID ) "+
							 " where pro_mapping.ACCOUNT_NO='"+rct.getAccountNo()+"' ", new RowCountCallbackHandler(){
						// "select * from MASPACKAGE_PROMOTION where package_id='"+packageID+"'", new RowCountCallbackHandler(){
					@Override
					public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
						sb.append(resultSet.getString("PROMOTION")+"\n");
					}
				});
				*/
		//}
		return sb.toString();
	}
	/**
	 * Use to generate ReportSource for EpisPaymentReceipt
	 * @param collections
	 * @param
	 * @throws CloneNotSupportedException
	 */
	private void prepareCustomerTaxReceiptTO(List<CustomerTaxReceiptTO> collections, Receipt receipt, PrintReceiptDTO printReceiptDTO/* List<Customer> listCust*/) throws CloneNotSupportedException {
		Receipt rct = receiptRepo.findOne(receipt.getId());
		// Fix By EPIS8 29/12/2016 issue no 57
		String maskCC = AppUtil.maskCreditCardFromString(rct.getPaymentCase(), "************####");
		String paymentCase = AppUtil.hideWTNumber(maskCC);
		rct.setPaymentCase(paymentCase);
		// End Fix By EPIS8 29/12/2016 issue no 57
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setMinimumIntegerDigits(1);
		CustomerTaxReceiptTO customer = new CustomerTaxReceiptTO();
		CustomerTaxReceiptTO customerInv = null;


		if(printReceiptDTO.getCustomers()!=null && printReceiptDTO.getCustomers().size()!=0){
			for (Customer cust : printReceiptDTO.getCustomers()) {
			    if (null!=cust.getCustNo()) {
                    if(cust.getCustNo().equalsIgnoreCase(rct.getAccountNo())){
                        //Header Information
                        customer.setReceiptType(rct.getType());
                        //Customer Information
                        customer.setCustomerNo(rct.getAccountNo());
                        customer.setCustomerSubNo(rct.getAccountSubNo());
                        customer.setCustomerName(rct.getName());
                        customer.setAddressLine1(rct.getAddrLine1());
                        customer.setAddressLine2(rct.getAddrLine2());
                        customer.setTaxId(rct.getTaxNo());
                        customer.setPaymentType(rct.getPaymentCase());
                        customer.setBranchPayment(rct.getAccountBranch());

                        customer.setBranchCode(rct.getBranchCode());
                        customer.setBranchName(calculateBranchName(rct));
                        customer.setDocumentNo(rct.getNo());
                        customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
                        customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));

                        customer.setEgpNo(cust.getEgpNo());
                        customer.setEgpContract(cust.getEgpContract());

                        //Additional Information
                        customer.setNoteTxt(rct.getRemark());
                        //customer.setPromotionTxt(rct.getPromotion());
                        customer.setPromotionTxt(getPromotionMessage(rct,printReceiptDTO));
                        //change billing group to other description by NSD
                        customer.setBillingGroup(rct.getBillingServiceName());

                        //Summary Information
                        customer.setSummaryAmountBeforeVat(df.format(rct.getCharge().add(rct.getDiscount()))); // format
                        customer.setSummaryDiscount(df.format(rct.getDiscount()));
                        customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge()));
                        customer.setSummaryVat(df.format(rct.getVat()));
                        //customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));

					BigDecimal afterSaleDiscount = (rct.getAfterSaleDiscount()!=null)?rct.getAfterSaleDiscount():BigDecimal.ZERO;
					BigDecimal afterSaleDiscVat = (rct.getAfterSaleDiscVat()!=null)?rct.getAfterSaleDiscVat():BigDecimal.ZERO;
					//customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));//by NSD 05-04-2017
					customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge()));//by NSD 28-04-2017
					customer.setAdditionalDiscountAfterVat(df.format(afterSaleDiscount));
					//customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount)));
					customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));

                        //Detail
                        int i = 1;
                        List<Invoice> invoices = invoiceRepo.findByReceiptId(rct.getId());
                        for(Invoice inv : invoices) {
                            if(!CollectionUtils.isEmpty(inv.getInvoiceVatDetails()) && inv.getInvoiceVatDetails().size()>1){
                                for(InvoiceVatDetail dtl: inv.getInvoiceVatDetails()){
                                    customerInv = (CustomerTaxReceiptTO) customer.clone();
                                    customerInv.setOrderNo(String.valueOf(i++));
                                    customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                                    customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");

                                    customerInv.setAmountBeforeVat(df.format(dtl.getAmount()));
                                    customerInv.setDiscount(df.format(inv.getDiscount()));//wait for asking
                                    customerInv.setAmountSumBeforeVat(df.format(dtl.getAmount()));
                                    if(null!=dtl.getVat() && !StringUtils.equals(dtl.getTaxTypeCode(), TAX_CODE_NONVAT)){
                                        customerInv.setVat(df.format(dtl.getVat()));
                                    }else{
                                        customerInv.setVat("-");
                                    }
                                    customerInv.setAmountSumAfterVat(df.format(dtl.getAmount().add(dtl.getVat())));

                                    customerInv.setReceiptType(rct.getType());
                                    customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                                    if (null!=customerInv.getCurrencyRate()) {
                                        AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                        AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                        customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                        customerInv.setVatRate(rct.getVatRate());

                                        AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
                                        AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                        customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                                    }
                                    collections.add(customerInv);
                                }
                            }else{
                                customerInv = (CustomerTaxReceiptTO) customer.clone();
                                customerInv.setOrderNo(String.valueOf(i++));
                                customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                                
                                customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");

                                customerInv.setAmountBeforeVat(df.format(inv.getCharge().add(inv.getDiscount())));
                                customerInv.setDiscount(df.format(inv.getDiscount()));
                                customerInv.setAmountSumBeforeVat(df.format(inv.getCharge()));
                                if(null!=inv.getVat()){
                                    customerInv.setVat(df.format(inv.getVat()));
                                }else{
                                    customerInv.setVat("-");
                                }
                                customerInv.setAmountSumAfterVat(df.format(inv.getTotalCharge()));


                                customerInv.setReceiptType(rct.getType());
                                customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                                if (null!=customerInv.getCurrencyRate()) {
                                    AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                    AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                    customerInv.setVatRate(rct.getVatRate());

                                    //AmountSumAfterVatTH = BigDecimal.ZERO;
                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount()));
                                    if(inv.getVat()!=null)
                                    	AmountSumAfterVatTH = AmountSumAfterVatTH.add(inv.getVat());
                                    AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                                }
                                collections.add(customerInv);
                            }
                        }
                    }
                } else {
                    egpMapFind = egpMapRepo.findByReceiptNo(rct.getNo());//W3P 28-FEB-2017 set egpNo and egpContract

                    //Header Information
                    customer.setReceiptType(rct.getType());
                    //Customer Information
                    customer.setCustomerNo(rct.getAccountNo());
                    customer.setCustomerSubNo(rct.getAccountSubNo());
                    customer.setCustomerName(rct.getName());
                    customer.setAddressLine1(rct.getAddrLine1());
                    customer.setAddressLine2(rct.getAddrLine2());
                    customer.setTaxId(rct.getTaxNo());
                    customer.setPaymentType(rct.getPaymentCase());
                    customer.setBranchPayment(rct.getAccountBranch());

                    customer.setBranchCode(rct.getBranchCode());
                    customer.setBranchName(calculateBranchName(rct));
                    customer.setDocumentNo(rct.getNo());
                    customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
                    customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));


                    customer.setEgpNo(cust.getEgpNo()!=null?cust.getEgpNo():egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpNo()?!egpMapFind.get(0).getEgpNo().equalsIgnoreCase("")?egpMapFind.get(0).getEgpNo():"":"":"");
                    customer.setEgpContract(cust.getEgpContract()!=null?cust.getEgpContract():egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpContract()?!egpMapFind.get(0).getEgpContract().equalsIgnoreCase("")?egpMapFind.get(0).getEgpContract():"":"":"");

                    //Additional Information
                    customer.setNoteTxt(rct.getRemark());
                    //customer.setPromotionTxt(rct.getPromotion());
                    customer.setPromotionTxt(getPromotionMessage(rct,printReceiptDTO));

                    //Summary Information
                    customer.setSummaryAmountBeforeVat(df.format(rct.getCharge().add(rct.getDiscount()))); // format
                    customer.setSummaryDiscount(df.format(rct.getDiscount()));
                    customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge()));
                    customer.setSummaryVat(df.format(rct.getVat()));
                    //customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));
                    BigDecimal afterSaleDiscount = (rct.getAfterSaleDiscount()!=null)?rct.getAfterSaleDiscount():BigDecimal.ZERO;
                    BigDecimal afterSaleDiscVat = (rct.getAfterSaleDiscVat()!=null)?rct.getAfterSaleDiscVat():BigDecimal.ZERO;

                    customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));//by NSD 05-04-2017
                    customer.setAdditionalDiscountAfterVat(df.format(afterSaleDiscount));
                    customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount)));

                    customer.setBillingGroup(rct.getBillingServiceName());

                    //get receipt format
                    String titleReceiptName = "RECEIVE_WH";
                    if (rct.getFlgHeader()!=null && rct.getLanguage()!=null) {
                        titleReceiptName = getReceiptName(rct.getFlgHeader(),rct.getLanguage());
                    } else {
						titleReceiptName = episService.getReceiptNameByreceiptId(rct.getId().toString());
                    }

                    customer.setReceiptFormat(titleReceiptName);

                    //Detail
                    int i = 1;
                    List<Invoice> invoices = invoiceRepo.findByReceiptId(rct.getId());
                    for(Invoice inv : invoices) {
                        if(!CollectionUtils.isEmpty(inv.getInvoiceVatDetails()) && inv.getInvoiceVatDetails().size()>1){
                            for(InvoiceVatDetail dtl: inv.getInvoiceVatDetails()){
                                customerInv = (CustomerTaxReceiptTO) customer.clone();
                                customerInv.setOrderNo(String.valueOf(i++));
                                customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                                customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");

                                customerInv.setAmountBeforeVat(df.format(dtl.getAmount()));
                                customerInv.setDiscount(df.format(inv.getDiscount()));//wait for asking
                                customerInv.setAmountSumBeforeVat(df.format(dtl.getAmount()));
                                if(null!=dtl.getVat() && !StringUtils.equals(dtl.getTaxTypeCode(), TAX_CODE_NONVAT)){
                                    customerInv.setVat(df.format(dtl.getVat()));
                                }else{
                                    customerInv.setVat("-");
                                }
                                customerInv.setAmountSumAfterVat(df.format(dtl.getAmount().add(dtl.getVat())));

                                customerInv.setReceiptType(rct.getType());
                                customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                                if (null!=customerInv.getCurrencyRate()) {
                                    AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                    AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                    customerInv.setVatRate(rct.getVatRate());

                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
                                    AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                                }
                                collections.add(customerInv);
                            }
                        }else{
                            customerInv = (CustomerTaxReceiptTO) customer.clone();
                            customerInv.setOrderNo(String.valueOf(i++));
                            customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                            customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");
                            customerInv.setAmountBeforeVat(df.format(inv.getCharge().add(inv.getDiscount())));
                            customerInv.setDiscount(df.format(inv.getDiscount()));
                            customerInv.setAmountSumBeforeVat(df.format(inv.getCharge()));
                            if(null != inv.getVat()){
                                customerInv.setVat(df.format(inv.getVat()));
                            }else{
                                customerInv.setVat("-");
                            }
                            customerInv.setAmountSumAfterVat(df.format(inv.getTotalCharge()));
                            customerInv.setReceiptType(rct.getType());
                            customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                            if (null!=customerInv.getCurrencyRate()) {
                                AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                AmountBeforeVatTH=AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                customerInv.setVatRate(rct.getVatRate());
                                if(null!=inv.getVat()){
                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
                                }else{
                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount()));
                                }
                                AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                            }

                            collections.add(customerInv);
                        }
                    }
                }
			}
		}else{
			egpMapFind = egpMapRepo.findByReceiptNo(receipt.getNo());//W3P 28-FEB-2017 set egpNo and egpContract

			//Header Information
			customer.setReceiptType(rct.getType());
			//Customer Information
			customer.setCustomerNo(rct.getAccountNo());
			customer.setCustomerSubNo(rct.getAccountSubNo());
			customer.setCustomerName(rct.getName());
			customer.setAddressLine1(rct.getAddrLine1());
			customer.setAddressLine2(rct.getAddrLine2());
			customer.setTaxId(rct.getTaxNo());
			customer.setPaymentType(rct.getPaymentCase());
			customer.setBranchPayment(rct.getAccountBranch());

			customer.setBranchCode(rct.getBranchCode());
			customer.setBranchName(calculateBranchName(rct));
			customer.setDocumentNo(rct.getNo());
			customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
			customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));


			customer.setEgpNo(egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpNo()?!egpMapFind.get(0).getEgpNo().equalsIgnoreCase("")?egpMapFind.get(0).getEgpNo():"":"":"");
			customer.setEgpContract(egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpContract()?!egpMapFind.get(0).getEgpContract().equalsIgnoreCase("")?egpMapFind.get(0).getEgpContract():"":"":"");

			//Additional Information
			customer.setNoteTxt(rct.getRemark());
			//customer.setPromotionTxt(rct.getPromotion());
			customer.setPromotionTxt(getPromotionMessage(rct,printReceiptDTO));

			//Summary Information
			customer.setSummaryAmountBeforeVat(df.format(rct.getCharge().add(rct.getDiscount()))); // format
			customer.setSummaryDiscount(df.format(rct.getDiscount()));
			customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge()));
			customer.setSummaryVat(df.format(rct.getVat()));
			//customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));
			BigDecimal afterSaleDiscount = (rct.getAfterSaleDiscount()!=null)?rct.getAfterSaleDiscount():BigDecimal.ZERO;
			BigDecimal afterSaleDiscVat = (rct.getAfterSaleDiscVat()!=null)?rct.getAfterSaleDiscVat():BigDecimal.ZERO;

			//customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));//by NSD 05-04-2017
			customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge()));//by NSD 28-04-2017
			customer.setAdditionalDiscountAfterVat(df.format(afterSaleDiscount));
			//customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount)));
			customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));

			customer.setBillingGroup(rct.getBillingServiceName());

			//Detail
			int i = 1;
			List<Invoice> invoices = invoiceRepo.findByReceiptId(rct.getId());
			for(Invoice inv : invoices) {
				if(!CollectionUtils.isEmpty(inv.getInvoiceVatDetails()) && inv.getInvoiceVatDetails().size()>1){
					for(InvoiceVatDetail dtl: inv.getInvoiceVatDetails()){
						customerInv = (CustomerTaxReceiptTO) customer.clone();
						customerInv.setOrderNo(String.valueOf(i++));
						customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
						customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");

						customerInv.setAmountBeforeVat(df.format(dtl.getAmount()));
						customerInv.setDiscount(df.format(inv.getDiscount()));//wait for asking
						customerInv.setAmountSumBeforeVat(df.format(dtl.getAmount()));
						if(null!=dtl.getVat() && !StringUtils.equals(dtl.getTaxTypeCode(), TAX_CODE_NONVAT)){
							customerInv.setVat(df.format(dtl.getVat()));
						}else{
							customerInv.setVat("-");
						}
						customerInv.setAmountSumAfterVat(df.format(dtl.getAmount().add(dtl.getVat())));

						customerInv.setReceiptType(rct.getType());
						customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
						if (null!=customerInv.getCurrencyRate()) {
							AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
							AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
							customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
							customerInv.setVatRate(rct.getVatRate());

							AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
							AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
							customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
						}
						collections.add(customerInv);
					}
				}else{
					customerInv = (CustomerTaxReceiptTO) customer.clone();
					customerInv.setOrderNo(String.valueOf(i++));
					customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
					customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");
					customerInv.setAmountBeforeVat(df.format(inv.getCharge().add(inv.getDiscount())));
					customerInv.setDiscount(df.format(inv.getDiscount()));
					customerInv.setAmountSumBeforeVat(df.format(inv.getCharge()));
					if(null != inv.getVat()){
						customerInv.setVat(df.format(inv.getVat()));
					}else{
						customerInv.setVat("-");
					}
					customerInv.setAmountSumAfterVat(df.format(inv.getTotalCharge()));
					customerInv.setReceiptType(rct.getType());
					customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
					if (null!=customerInv.getCurrencyRate()) {
						AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
						AmountBeforeVatTH=AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
						customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
						customerInv.setVatRate(rct.getVatRate());
						if(null!=inv.getVat()){
							AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
						}else{
							AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount()));
						}
						AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
						customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
					}

					collections.add(customerInv);
				}
			}
		}

	}
	
	private void prepareCustomerTaxReceiptTOTBOSS(List<CustomerTaxReceiptTO> collections, Receipt receipt, PrintReceiptDTO printReceiptDTO/* List<Customer> listCust*/) throws CloneNotSupportedException {
		Receipt rct = receiptRepo.findOne(receipt.getId());
		// Fix By EPIS8 29/12/2016 issue no 57
		
		
		String maskCC = AppUtil.maskCreditCardFromString(rct.getPaymentCase(), "************####");
		String paymentCase = AppUtil.hideWTNumber(maskCC);
		rct.setPaymentCase(paymentCase);
		// End Fix By EPIS8 29/12/2016 issue no 57
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setMinimumIntegerDigits(1);
		CustomerTaxReceiptTO customer = new CustomerTaxReceiptTO();
		CustomerTaxReceiptTO customerInv = null;


		if(printReceiptDTO.getCustomers()!=null && printReceiptDTO.getCustomers().size()!=0){
			for (Customer cust : printReceiptDTO.getCustomers()) {
			    if (null!=cust.getCustNo()) {
                    if(cust.getCustNo().equalsIgnoreCase(rct.getAccountNo())){
                        //Header Information
                        customer.setReceiptType(rct.getType());
                        //Customer Information
                        customer.setCustomerNo(rct.getAccountNo());
                        customer.setCustomerSubNo(rct.getAccountSubNo());
                        customer.setCustomerName(rct.getName());
                        customer.setAddressLine1(rct.getAddrLine1());
                        customer.setAddressLine2(rct.getAddrLine2());
                        customer.setTaxId(rct.getTaxNo());
                        customer.setPaymentType(rct.getPaymentCase());
                        customer.setBranchPayment(rct.getAccountBranch());

                        customer.setBranchCode(rct.getBranchCode());
                        customer.setBranchName(calculateBranchName(rct));
                        customer.setDocumentNo(rct.getNo());
                        customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
                        customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));

                        customer.setEgpNo(cust.getEgpNo());
                        customer.setEgpContract(cust.getEgpContract());

                        //Additional Information
                        customer.setNoteTxt(rct.getRemark());
                        //customer.setPromotionTxt(rct.getPromotion());
                        customer.setPromotionTxt(getPromotionMessage(rct,printReceiptDTO));
                        //change billing group to other description by NSD
                        customer.setBillingGroup(rct.getBillingServiceName());

                        //Summary Information
                        customer.setSummaryAmountBeforeVat(df.format(rct.getCharge().add(rct.getDiscount()))); // format
                        customer.setSummaryDiscount(df.format(rct.getDiscount()));
                        customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge()));
                        customer.setSummaryVat(df.format(rct.getVat()));
                        //customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));

					BigDecimal afterSaleDiscount = (rct.getAfterSaleDiscount()!=null)?rct.getAfterSaleDiscount():BigDecimal.ZERO;
					BigDecimal afterSaleDiscVat = (rct.getAfterSaleDiscVat()!=null)?rct.getAfterSaleDiscVat():BigDecimal.ZERO;
					//customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));//by NSD 05-04-2017
					customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge()));//by NSD 28-04-2017
					customer.setAdditionalDiscountAfterVat(df.format(afterSaleDiscount));
					//customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount)));
					customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));

                        //Detail
                        int i = 1;
                        List<Invoice> invoices = invoiceRepo.findByReceiptId(rct.getId());
                        for(Invoice inv : invoices) {
                            if(!CollectionUtils.isEmpty(inv.getInvoiceVatDetails()) && inv.getInvoiceVatDetails().size()>1){
                                for(InvoiceVatDetail dtl: inv.getInvoiceVatDetails()){
                                	String bilCycle = inv.getBillCycle();
                                	
                                	String year = bilCycle.substring(0, 4);
                                	String mount = bilCycle.substring(4, 6);
                                    customerInv = (CustomerTaxReceiptTO) customer.clone();
                                    customerInv.setOrderNo(String.valueOf(i++));
                                    customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                                    
                                    customerInv.setPaymentCycle(mount + "/" + year);

                                    customerInv.setAmountBeforeVat(df.format(dtl.getAmount()));
                                    customerInv.setDiscount(df.format(inv.getDiscount()));//wait for asking
                                    customerInv.setAmountSumBeforeVat(df.format(dtl.getAmount()));
                                    if(null!=dtl.getVat() && !StringUtils.equals(dtl.getTaxTypeCode(), TAX_CODE_NONVAT)){
                                        customerInv.setVat(df.format(dtl.getVat()));
                                    }else{
                                        customerInv.setVat("-");
                                    }
                                    customerInv.setAmountSumAfterVat(df.format(dtl.getAmount().add(dtl.getVat())));

                                    customerInv.setReceiptType(rct.getType());
                                    customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                                    if (null!=customerInv.getCurrencyRate()) {
                                        AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                        AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                        customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                        customerInv.setVatRate(rct.getVatRate());

                                        AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
                                        AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                        customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                                    }
                                    collections.add(customerInv);
                                }
                            }else{
                            	String bilCycle = inv.getBillCycle();
                            	String mountYear ="-";
                            	if(bilCycle != null){
                            		String year = bilCycle.substring(0, 4);
                                	String mount = bilCycle.substring(4, 6);
                                	mountYear = mount + "/" + year;
                            	}
                            	
                                customerInv = (CustomerTaxReceiptTO) customer.clone();
                                customerInv.setOrderNo(String.valueOf(i++));
                                customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                                customerInv.setPaymentCycle(mountYear);
                                customerInv.setAmountBeforeVat(df.format(inv.getCharge().add(inv.getDiscount())));
                                customerInv.setDiscount(df.format(inv.getDiscount()));
                                customerInv.setAmountSumBeforeVat(df.format(inv.getCharge()));
                                if(null!=inv.getVat()){
                                    customerInv.setVat(df.format(inv.getVat()));
                                }else{
                                    customerInv.setVat("-");
                                }
                                customerInv.setAmountSumAfterVat(df.format(inv.getTotalCharge()));


                                customerInv.setReceiptType(rct.getType());
                                customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                                if (null!=customerInv.getCurrencyRate()) {
                                    AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                    AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                    customerInv.setVatRate(rct.getVatRate());
                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat() != null ?inv.getVat():BigDecimal.ZERO);
                                    AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                                }
                                collections.add(customerInv);
                            }
                        }
                    }
                } else {
                    egpMapFind = egpMapRepo.findByReceiptNo(rct.getNo());//W3P 28-FEB-2017 set egpNo and egpContract

                    //Header Information
                    customer.setReceiptType(rct.getType());
                    //Customer Information
                    customer.setCustomerNo(rct.getAccountNo());
                    customer.setCustomerSubNo(rct.getAccountSubNo());
                    customer.setCustomerName(rct.getName());
                    customer.setAddressLine1(rct.getAddrLine1());
                    customer.setAddressLine2(rct.getAddrLine2());
                    customer.setTaxId(rct.getTaxNo());
                    customer.setPaymentType(rct.getPaymentCase());
                    customer.setBranchPayment(rct.getAccountBranch());

                    customer.setBranchCode(rct.getBranchCode());
                    customer.setBranchName(calculateBranchName(rct));
                    customer.setDocumentNo(rct.getNo());
                    customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
                    customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));


                    customer.setEgpNo(cust.getEgpNo()!=null?cust.getEgpNo():egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpNo()?!egpMapFind.get(0).getEgpNo().equalsIgnoreCase("")?egpMapFind.get(0).getEgpNo():"":"":"");
                    customer.setEgpContract(cust.getEgpContract()!=null?cust.getEgpContract():egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpContract()?!egpMapFind.get(0).getEgpContract().equalsIgnoreCase("")?egpMapFind.get(0).getEgpContract():"":"":"");

                    //Additional Information
                    customer.setNoteTxt(rct.getRemark());
                    //customer.setPromotionTxt(rct.getPromotion());
                    customer.setPromotionTxt(getPromotionMessage(rct,null));

                    //Summary Information
                    customer.setSummaryAmountBeforeVat(df.format(rct.getCharge().add(rct.getDiscount()))); // format
                    customer.setSummaryDiscount(df.format(rct.getDiscount()));
                    customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge()));
                    customer.setSummaryVat(df.format(rct.getVat()));
                    //customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));
                    BigDecimal afterSaleDiscount = (rct.getAfterSaleDiscount()!=null)?rct.getAfterSaleDiscount():BigDecimal.ZERO;
                    BigDecimal afterSaleDiscVat = (rct.getAfterSaleDiscVat()!=null)?rct.getAfterSaleDiscVat():BigDecimal.ZERO;

                    customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));//by NSD 05-04-2017
                    customer.setAdditionalDiscountAfterVat(df.format(afterSaleDiscount));
                    customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount)));

                    customer.setBillingGroup(rct.getBillingServiceName());

                    //get receipt format
                    String titleReceiptName = "RECEIVE_WH";
                    if (rct.getFlgHeader()!=null && rct.getLanguage()!=null) {
                        titleReceiptName = getReceiptName(rct.getFlgHeader(),rct.getLanguage());
                    } else {
						titleReceiptName = episService.getReceiptNameByreceiptId(rct.getId().toString());
                    }

                    customer.setReceiptFormat(titleReceiptName);

                    //Detail
                    int i = 1;
                    List<Invoice> invoices = invoiceRepo.findByReceiptId(rct.getId());
                    for(Invoice inv : invoices) {
                        if(!CollectionUtils.isEmpty(inv.getInvoiceVatDetails()) && inv.getInvoiceVatDetails().size()>1){
                            for(InvoiceVatDetail dtl: inv.getInvoiceVatDetails()){
                                customerInv = (CustomerTaxReceiptTO) customer.clone();
                                customerInv.setOrderNo(String.valueOf(i++));
                                customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                                customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");

                                customerInv.setAmountBeforeVat(df.format(dtl.getAmount()));
                                customerInv.setDiscount(df.format(inv.getDiscount()));//wait for asking
                                customerInv.setAmountSumBeforeVat(df.format(dtl.getAmount()));
                                if(null!=dtl.getVat() && !StringUtils.equals(dtl.getTaxTypeCode(), TAX_CODE_NONVAT)){
                                    customerInv.setVat(df.format(dtl.getVat()));
                                }else{
                                    customerInv.setVat("-");
                                }
                                customerInv.setAmountSumAfterVat(df.format(dtl.getAmount().add(dtl.getVat())));

                                customerInv.setReceiptType(rct.getType());
                                customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                                if (null!=customerInv.getCurrencyRate()) {
                                    AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                    AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                    customerInv.setVatRate(rct.getVatRate());

                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
                                    AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                    customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                                }
                                collections.add(customerInv);
                            }
                        }else{
                            customerInv = (CustomerTaxReceiptTO) customer.clone();
                            customerInv.setOrderNo(String.valueOf(i++));
                            customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
                            customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");
                            customerInv.setAmountBeforeVat(df.format(inv.getCharge().add(inv.getDiscount())));
                            customerInv.setDiscount(df.format(inv.getDiscount()));
                            customerInv.setAmountSumBeforeVat(df.format(inv.getCharge()));
                            if(null != inv.getVat()){
                                customerInv.setVat(df.format(inv.getVat()));
                            }else{
                                customerInv.setVat("-");
                            }
                            customerInv.setAmountSumAfterVat(df.format(inv.getTotalCharge()));
                            customerInv.setReceiptType(rct.getType());
                            customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
                            if (null!=customerInv.getCurrencyRate()) {
                                AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
                                AmountBeforeVatTH=AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
                                customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
                                customerInv.setVatRate(rct.getVatRate());
                                if(null!=inv.getVat()){
                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
                                }else{
                                    AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount()));
                                }
                                AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
                                customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
                            }

                            collections.add(customerInv);
                        }
                    }
                }
			}
		}else{
			egpMapFind = egpMapRepo.findByReceiptNo(receipt.getNo());//W3P 28-FEB-2017 set egpNo and egpContract

			//Header Information
			customer.setReceiptType(rct.getType());
			//Customer Information
			customer.setCustomerNo(rct.getAccountNo());
			customer.setCustomerSubNo(rct.getAccountSubNo());
			customer.setCustomerName(rct.getName());
			customer.setAddressLine1(rct.getAddrLine1());
			customer.setAddressLine2(rct.getAddrLine2());
			customer.setTaxId(rct.getTaxNo());
			customer.setPaymentType(rct.getPaymentCase());
			customer.setBranchPayment(rct.getAccountBranch());

			customer.setBranchCode(rct.getBranchCode());
			customer.setBranchName(calculateBranchName(rct));
			customer.setDocumentNo(rct.getNo());
			customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
			customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));


			customer.setEgpNo(egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpNo()?!egpMapFind.get(0).getEgpNo().equalsIgnoreCase("")?egpMapFind.get(0).getEgpNo():"":"":"");
			customer.setEgpContract(egpMapFind.size()>0?null!=egpMapFind.get(0).getEgpContract()?!egpMapFind.get(0).getEgpContract().equalsIgnoreCase("")?egpMapFind.get(0).getEgpContract():"":"":"");

			//Additional Information
			customer.setNoteTxt(rct.getRemark());
			//customer.setPromotionTxt(rct.getPromotion());
			customer.setPromotionTxt(getPromotionMessage(rct,null));

			//Summary Information
			customer.setSummaryAmountBeforeVat(df.format(rct.getCharge().add(rct.getDiscount()))); // format
			customer.setSummaryDiscount(df.format(rct.getDiscount()));
			customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge()));
			customer.setSummaryVat(df.format(rct.getVat()));
			//customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));
			BigDecimal afterSaleDiscount = (rct.getAfterSaleDiscount()!=null)?rct.getAfterSaleDiscount():BigDecimal.ZERO;
			BigDecimal afterSaleDiscVat = (rct.getAfterSaleDiscVat()!=null)?rct.getAfterSaleDiscVat():BigDecimal.ZERO;

			//customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));//by NSD 05-04-2017
			customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge()));//by NSD 28-04-2017
			customer.setAdditionalDiscountAfterVat(df.format(afterSaleDiscount));
			//customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount)));
			customer.setAmountPaid(df.format(rct.getTotalCharge().subtract(afterSaleDiscount).subtract(afterSaleDiscVat)));

			customer.setBillingGroup(rct.getBillingServiceName());

			//Detail
			int i = 1;
			List<Invoice> invoices = invoiceRepo.findByReceiptId(rct.getId());
			for(Invoice inv : invoices) {
				if(!CollectionUtils.isEmpty(inv.getInvoiceVatDetails()) && inv.getInvoiceVatDetails().size()>1){
					for(InvoiceVatDetail dtl: inv.getInvoiceVatDetails()){
						customerInv = (CustomerTaxReceiptTO) customer.clone();
						customerInv.setOrderNo(String.valueOf(i++));
						customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
						customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");

						customerInv.setAmountBeforeVat(df.format(dtl.getAmount()));
						customerInv.setDiscount(df.format(inv.getDiscount()));//wait for asking
						customerInv.setAmountSumBeforeVat(df.format(dtl.getAmount()));
						if(null!=dtl.getVat() && !StringUtils.equals(dtl.getTaxTypeCode(), TAX_CODE_NONVAT)){
							customerInv.setVat(df.format(dtl.getVat()));
						}else{
							customerInv.setVat("-");
						}
						customerInv.setAmountSumAfterVat(df.format(dtl.getAmount().add(dtl.getVat())));

						customerInv.setReceiptType(rct.getType());
						customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
						if (null!=customerInv.getCurrencyRate()) {
							AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
							AmountBeforeVatTH = AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
							customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
							customerInv.setVatRate(rct.getVatRate());

							AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
							AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
							customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
						}
						collections.add(customerInv);
					}
				}else{
					customerInv = (CustomerTaxReceiptTO) customer.clone();
					customerInv.setOrderNo(String.valueOf(i++));
					customerInv.setInvoiceNo(!inv.getNo().equals("0")? inv.getNo():"-");
					customerInv.setPaymentCycle(!StringUtils.isEmpty(inv.getBillCycle())? inv.getBillCycle(): "-");
					customerInv.setAmountBeforeVat(df.format(inv.getCharge().add(inv.getDiscount())));
					customerInv.setDiscount(df.format(inv.getDiscount()));
					customerInv.setAmountSumBeforeVat(df.format(inv.getCharge()));
					if(null != inv.getVat()){
						customerInv.setVat(df.format(inv.getVat()));
					}else{
						customerInv.setVat("-");
					}
					customerInv.setAmountSumAfterVat(df.format(inv.getTotalCharge()));
					customerInv.setReceiptType(rct.getType());
					customerInv.setCurrencyRate(rct.getPayment().getCurrencyRate());
					if (null!=customerInv.getCurrencyRate()) {
						AmountBeforeVatTH = inv.getCharge().add(inv.getDiscount());
						AmountBeforeVatTH=AmountBeforeVatTH.multiply(customerInv.getCurrencyRate());
						customerInv.setAmountBeforeVatTH(AmountBeforeVatTH);
						customerInv.setVatRate(rct.getVatRate());
						if(null!=inv.getVat()){
							AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount())).add(inv.getVat());
						}else{
							AmountSumAfterVatTH = (inv.getCharge().add(inv.getDiscount()));
						}
						AmountSumAfterVatTH = AmountSumAfterVatTH.multiply(customerInv.getCurrencyRate());
						customerInv.setAmountSumAfterVatTH(AmountSumAfterVatTH);
					}

					collections.add(customerInv);
				}
			}
		}

	}

	/**
	 * Use to generate ReportSource for EpisPaymentOthersReceipt
	 * @param collections
	 * @param
	 * @throws CloneNotSupportedException
	 */
	private void prepareCustomerTaxReceiptForOthersPaymentTO(List<CustomerTaxReceiptForOthersPaymentTO> collections, Receipt receipt) throws CloneNotSupportedException {
		Receipt rct = receiptRepo.findOne(receipt.getId());
		Payment payment = null;
		try {
			payment = paymentRepo.findOne(rct.getPayment().getId());
		} catch(Exception e) { e.printStackTrace(); }
		
		// Fix By EPIS8 29/12/2016 issue no 159
		String maskCC = AppUtil.maskCreditCardFromString(rct.getPaymentCase(), "************####");
        String paymentCase = AppUtil.hideWTNumber(maskCC);
		rct.setPaymentCase(paymentCase);
		// End Fix By EPIS8 29/12/2016 issue no 159
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setMinimumIntegerDigits(1);
		CustomerTaxReceiptForOthersPaymentTO customer = new CustomerTaxReceiptForOthersPaymentTO();
		CustomerTaxReceiptForOthersPaymentTO customerServ;
		//Header Information
		customer.setReceiptType(rct.getType());
		customer.setFlgHeader(rct.getFlgHeader());
		//Customer Information
		customer.setCustomerNo(rct.getAccountNo());
		customer.setCustomerSubNo(rct.getAccountSubNo());
		customer.setCustomerName(rct.getName());
		customer.setAddressLine1(rct.getAddrLine1());
		customer.setAddressLine2(rct.getAddrLine2());
		customer.setTaxId(rct.getTaxNo());
		customer.setBranchPayment(rct.getAccountBranch());
		customer.setPaymentType(rct.getPaymentCase());
		customer.setBranchCode(rct.getBranchCode());
		customer.setBranchName(calculateBranchName(rct));
		customer.setDocumentNo(rct.getNo());
		customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
		customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));

		//Additional Information
		customer.setNoteTxt(rct.getRemark());
		//customer.setPromotionTxt(rct.getPromotion());
		customer.setPromotionTxt(getPromotionMessage(rct,null));
		//Summary Information
		customer.setSummaryAmountBeforeVat(df.format(rct.getAmount().subtract(rct.getDiscount()))); // format
//		customer.setSummaryAmountBeforeVat(df.format(rct.getAmount()));
		customer.setSummaryDiscount(df.format(rct.getDiscount()));
		//customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge().subtract(rct.getDiscount())));
		if(rct.getSpecialDiscount()!=null){customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge()));}else{customer.setSummaryAmountSumBeforeVat(df.format(rct.getAmount().subtract(rct.getDiscount())));}
		if(rct.getVat()!=null){
			customer.setSummaryVat(df.format(rct.getVat()));
		}else{
			customer.setSummaryVat("-");
		}
		customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));
		customer.setAmountPaid(df.format(rct.getTotalCharge()));
		if(rct.getExcDiscount() != null) customer.setExcDiscount(df.format(rct.getExcDiscount())); else customer.setExcDiscount(null);//by NSD 29-03-2017

		//get receipt format
		String titleReceiptName = "receive_only";

		titleReceiptName = getReceiptName(rct.getFlgHeader()!=null?rct.getFlgHeader():"2",rct.getLanguage()!=null?rct.getLanguage():"TH");

		customer.setReceiptFormat(titleReceiptName);
		customer.setCurrencyCode(payment.getCurrencyCode());
		customer.setCurrencyRate(payment.getCurrencyRate().toString());

		//Detail
		int i = 1;
		List<Service> services = serviceRepo.findByReceiptId(rct.getId());
		th.net.cat.epis.entity.Enum  serviceEnum = null;
		for(Service ser : services) {
			//serviceEnum = enumRepo.findByCategoryAndCode("payothers.service.name", ser.getProductName()).get(0);
			customerServ = (CustomerTaxReceiptForOthersPaymentTO) customer.clone();
			customerServ.setOrderNo(String.valueOf(i++));
			customerServ.setProductType(ser.getIncomeType());
			customerServ.setProductName(ser.getServiceName());
			//customerServ.setProductName(serviceEnum.getDescFullTh()+" "+ser.getIncomeAmount().intValueExact()+" "+ ((ser.getIncomeUnit() != null)? ser.getIncomeUnit() : "") );
			//customerServ.setAmount(df.format(ser.getCharge()));
			customerServ.setAmount(df.format(ser.getAmount()));//by NSD 29-03-2017
			if(ser.getVat()!=null){
				customerServ.setVat(df.format(ser.getVat()));
			}
			else{ //if vat=null set it to -
				customerServ.setVat("-");
			}
			customerServ.setDiscount(df.format(ser.getDiscount()));
			customerServ.setAmountBeforeVatAfterDiscount(df.format(ser.getCharge()));
			customerServ.setWat(df.format(ser.getDeduction()));
			customerServ.setTotalSum(df.format(ser.getTotalCharge()));
			customerServ.setCurrencyAmount(df.format(ser.getTotalCharge().divide(payment.getCurrencyRate(),0, BigDecimal.ROUND_HALF_UP)));
			collections.add(customerServ);
		}
	}

	/**
	 * Use to generate ReportSource for EpisPaymentMNPReceipt
	 * @param collections
	 * @param
	 * @throws CloneNotSupportedException
	 */
	private void prepareCustomerTaxReceiptForMobilePaymentTO(List<CustomerTaxReceiptForOthersPaymentTO> collections, Receipt receipt) throws CloneNotSupportedException {
		Receipt rct = receiptRepo.findOne(receipt.getId());
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setMinimumIntegerDigits(1);
		CustomerTaxReceiptForOthersPaymentTO customer = new CustomerTaxReceiptForOthersPaymentTO();
		CustomerTaxReceiptForOthersPaymentTO customerServ;
		//Header Information
		customer.setReceiptType(rct.getType());
		//Customer Information

        String maskCC = AppUtil.maskCreditCardFromString(rct.getPaymentCase(), "************####");//by NSD 26-02-2017
        String paymentCase = AppUtil.hideWTNumber(maskCC);
        rct.setPaymentCase(paymentCase);

		customer.setCustomerNo(rct.getAccountNo());
		customer.setCustomerSubNo(rct.getAccountSubNo());
		customer.setCustomerName(rct.getName());
		customer.setAddressLine1(rct.getAddrLine1());
		customer.setAddressLine2(rct.getAddrLine2());
		customer.setTaxId(rct.getTaxNo());
		customer.setBranchPayment(rct.getAccountBranch());
		customer.setPaymentType(rct.getPaymentCase());
		customer.setBranchCode(rct.getBranchCode());
		customer.setBranchName(calculateBranchName(rct));
		customer.setDocumentNo(rct.getNo());
		customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
		customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));

		//Additional Information
		customer.setNoteTxt(rct.getRemark());
		customer.setPromotionTxt(rct.getPromotion());

		//Summary Information
		customer.setSummaryAmountBeforeVat(df.format(rct.getCharge())); // format
		customer.setSummaryDiscount(df.format(rct.getDiscount()));
		customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge().subtract(rct.getDiscount())));
		if(rct.getVat()!=null){
			customer.setSummaryVat(df.format(rct.getVat()));
		}
		else{ //if summaryVat=null set it to -
			customer.setSummaryVat("-");
		}
		customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));
		customer.setAmountPaid(df.format(rct.getTotalCharge()));

		//Detail
		int i = 1;
		List<Service> services = serviceRepo.findByReceiptId(rct.getId());
		for(Service ser : services) {
			customerServ = (CustomerTaxReceiptForOthersPaymentTO) customer.clone();
			customerServ.setOrderNo(String.valueOf(i++));
			customerServ.setProductName(ser.getMdn() != null ? "ค่าบริการคงสิทธิเลขหมายโทรศัพท์เคลื่อนที่ "+ser.getMdn() : ser.getProductName()); //Edit by PM
			customerServ.setAmount(df.format(ser.getCharge()));
			customerServ.setVat(df.format(ser.getVat()));
			customerServ.setDiscount(df.format(ser.getDiscount()));
			customerServ.setAmountBeforeVatAfterDiscount(df.format(ser.getCharge()));
			customerServ.setWat(df.format(ser.getDeduction()));
			customerServ.setTotalSum(df.format(ser.getTotalCharge()));
			customerServ.setSourceAddress(rct.getRef2()!=null?rct.getRef2().equalsIgnoreCase(AppConstants.DEDUCT_METHOD_FEE_IN)?rct.getRef2():rct.getRef1():rct.getRef1());
			collections.add(customerServ);
		}
	}

	/**
	 * Use to generate ReportSource for EpisPaymentTopUpReceipt
	 * @param collections
	 * @param
	 * @throws CloneNotSupportedException
	 */
	private void prepareCustomerTaxReceiptForTopUpPaymentTO(List<CustomerTaxReceiptForOthersPaymentTO> collections, Receipt receipt) throws CloneNotSupportedException {
		Receipt rct = receiptRepo.findOne(receipt.getId());
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setMinimumIntegerDigits(1);
		CustomerTaxReceiptForOthersPaymentTO customer = new CustomerTaxReceiptForOthersPaymentTO();
		CustomerTaxReceiptForOthersPaymentTO customerServ;

		String maskCC = AppUtil.maskCreditCardFromString(rct.getPaymentCase(), "************####");//by NSD 26-02-2017
		String paymentCase = AppUtil.hideWTNumber(maskCC);
		rct.setPaymentCase(paymentCase);

		//Header Information
		customer.setReceiptType(rct.getType());
		//Customer Information
		customer.setCustomerNo(rct.getAccountNo());
		customer.setCustomerSubNo(rct.getAccountSubNo());
		customer.setCustomerName(rct.getName());
		customer.setAddressLine1(rct.getAddrLine1());
		customer.setAddressLine2(rct.getAddrLine2());
		customer.setTaxId(rct.getTaxNo());
		customer.setBranchPayment(rct.getAccountBranch());
		customer.setPaymentType(rct.getPaymentCase());
		customer.setBranchCode(rct.getBranchCode());
		customer.setBranchName(calculateBranchName(rct));
		customer.setDocumentNo(rct.getNo());
		customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
		customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));

		//Additional Information
		customer.setNoteTxt(rct.getRemark());
		//customer.setPromotionTxt(rct.getPromotion());
		customer.setPromotionTxt(getPromotionMessage(rct,null));
		//Summary Information
		//customer.setSummaryAmountBeforeVat(df.format(rct.getCharge())); // format
		customer.setSummaryAmountBeforeVat(df.format(rct.getAmount()));//by NSD 06-02-2017
		customer.setSummaryDiscount(df.format(rct.getDiscount()));
		//customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge().subtract(rct.getDiscount())));
		customer.setSummaryAmountSumBeforeVat(df.format(rct.getAmount().subtract(rct.getDiscount())));

		customer.setSummaryVat(df.format(rct.getVat()));
		BigDecimal afterSaleDiscount = (rct.getAfterSaleDiscount()!=null)?rct.getAfterSaleDiscount():BigDecimal.ZERO;
		customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(afterSaleDiscount)));
		customer.setAmountPaid(df.format(rct.getTotalCharge()));

		customer.setRefNo(rct.getRef1());
		
		//Detail
		int i = 1;
		List<Service> services = serviceRepo.findByReceiptId(rct.getId());
//		th.net.cat.epis.entity.Enum  serviceEnum = null;
		for(Service ser : services) {
//			serviceEnum = enumRepo.findByCategoryAndCode("payothers.service.name", ser.getProductName()).get(0);
			customerServ = (CustomerTaxReceiptForOthersPaymentTO) customer.clone();
			customerServ.setOrderNo(String.valueOf(i++));
			customerServ.setProductType(ser.getIncomeType());
			customerServ.setProductName(ser.getServiceNo());
			//customerServ.setAmount(df.format(ser.getCharge()));
			customerServ.setAmount(df.format(ser.getAmount()));//by NSD 06-02-2017
			customerServ.setVat(df.format(ser.getVat()));
			customerServ.setDiscount(df.format(ser.getDiscount()));
			customerServ.setAmountBeforeVatAfterDiscount(df.format(ser.getCharge()));
			customerServ.setWat(df.format(ser.getDeduction()));
			customerServ.setTotalSum(df.format(ser.getTotalCharge()));
			collections.add(customerServ);
		}
	}
//by NSD 28-02-2017
    private void prepareCustomerTaxReceiptForPenaltyExtendPaymentTO(List<CustomerTaxReceiptForOthersPaymentTO> collections, Receipt receipt) throws CloneNotSupportedException {
        Receipt rct = receiptRepo.findOne(receipt.getId());
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setMinimumIntegerDigits(1);
        CustomerTaxReceiptForOthersPaymentTO customer = new CustomerTaxReceiptForOthersPaymentTO();
        CustomerTaxReceiptForOthersPaymentTO customerServ;

        String maskCC = AppUtil.maskCreditCardFromString(rct.getPaymentCase(), "************####");//by NSD 26-02-2017
        String paymentCase = AppUtil.hideWTNumber(maskCC);
        rct.setPaymentCase(paymentCase);

        //Header Information
        customer.setReceiptType(rct.getType());
        //Customer Information
        customer.setCustomerNo(rct.getAccountNo());
        customer.setCustomerSubNo(rct.getAccountSubNo());
        customer.setCustomerName(rct.getName());
        customer.setAddressLine1(rct.getAddrLine1());
        customer.setAddressLine2(rct.getAddrLine2());
        customer.setTaxId(rct.getTaxNo());
        customer.setBranchPayment(rct.getAccountBranch());
        customer.setPaymentType(rct.getPaymentCase());
        customer.setBranchCode(rct.getBranchCode());
        customer.setBranchName(calculateBranchName(rct));
        customer.setDocumentNo(rct.getNo());
        customer.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_NO_SECONDS, ENGLISH).format(rct.getDocDttm()));//rct.getPayment().getDate().toString()); // format properly
        customer.setCustomerType(calculateCustomerTypeCode(rct.getAccountType()));

        //Additional Information
        customer.setNoteTxt(rct.getRemark());
        //customer.setPromotionTxt(rct.getPromotion());
        customer.setPromotionTxt(getPromotionMessage(rct,null));
        //Summary Information
        //customer.setSummaryAmountBeforeVat(df.format(rct.getCharge())); // format
       // customer.setSummaryAmountBeforeVat(df.format(rct.getAmount()));//by NSD 06-02-2017
       // customer.setSummaryDiscount(df.format(rct.getDiscount()));
        //customer.setSummaryAmountSumBeforeVat(df.format(rct.getCharge().subtract(rct.getDiscount())));
        //customer.setSummaryAmountSumBeforeVat(df.format(rct.getAmount().subtract(rct.getDiscount())));

        //customer.setSummaryVat(df.format(rct.getVat()));
        //customer.setSummaryAmountSumAfterVat(df.format(rct.getTotalCharge().subtract(rct.getAfterSaleDiscount())));
       // customer.setAmountPaid(df.format(rct.getTotalCharge()));

        //Detail
        int i = 1;
        List<Service> services = serviceRepo.findByReceiptId(rct.getId());
//		th.net.cat.epis.entity.Enum  serviceEnum = null;
		BigDecimal sumAmt = new BigDecimal(0);
        for(Service ser : services) {
			sumAmt = sumAmt.add(ser.getAmount());
//			serviceEnum = enumRepo.findByCategoryAndCode("payothers.service.name", ser.getProductName()).get(0);
            customerServ = (CustomerTaxReceiptForOthersPaymentTO) customer.clone();
            customerServ.setOrderNo(String.valueOf(i++));
            customerServ.setProductType(ser.getIncomeType());
            customerServ.setProductName(ser.getProductName());
            customerServ.setProductCode(ser.getProductCode());
            //customerServ.setAmount(df.format(ser.getCharge()));
            customerServ.setAmount(df.format(ser.getAmount()));//by NSD 06-02-2017
           // customerServ.setVat(df.format(ser.getVat()));
            //customerServ.setDiscount(df.format(ser.getDiscount()));
            //customerServ.setAmountBeforeVatAfterDiscount(df.format(ser.getCharge()));
           // customerServ.setWat(df.format(ser.getDeduction()));
            customerServ.setTotalSum(df.format(ser.getTotalCharge()));
            collections.add(customerServ);
        }
        collections.get(0).setSummaryAmountSumAfterVat(df.format(sumAmt));
    }

	private void pushReportToOutputStream(HttpServletResponse response, List<JasperPrint> jasperPrints) throws IOException, JRException  {
		OutputStream outputStream = response.getOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}
	private String calculateBranchName(Receipt rct) {
		return (rct.getBranchCode().equalsIgnoreCase("00000"))
				? "สำนักงานใหญ่ "+ rct.getBranchCode()+" "+rct.getBranchName()
				: (rct.getFlgHeader().equalsIgnoreCase("2") ? "สาขาที่ออกใบเสร็จรับเงิน คือ "+ rct.getBranchCode()+" "+rct.getBranchName() : "สาขาที่ออกใบกำกับภาษี คือ "+ rct.getBranchCode()+" "+rct.getBranchName());
	}
	private String calculateBranchNameForReceiptLang(Receipt receipt,String receiptLang) {
		Receipt rtc = receiptRepo.findOne(receipt.getId());
		logger.info("rtc.getBranchCode()["+rtc.getBranchCode()+"] receiptLang["+receiptLang+"]");
		return (rtc.getBranchCode().equals("00000")) 
				? ((!StringUtils.isEmpty(receiptLang) && receiptLang.equals(AppConstants.LANGUAGE_KEY.ENG.name()))?"Head Office ":"สำนักงานใหญ่ ")+ rtc.getBranchCode()+" "+rtc.getBranchName() 
				: ((!StringUtils.isEmpty(receiptLang) && receiptLang.equals(AppConstants.LANGUAGE_KEY.ENG.name()))?"Branch issued Tax invoice ":"สาขาที่ออกใบกำกับภาษี คือ ")+ rtc.getBranchCode()+" "+rtc.getBranchName();
	}
	
	private String calculateCustomerTypeCode(String customerType) {
		String type = stripToEmpty(customerType).toLowerCase(); 
		if (type.indexOf("individual") == 0) {
			return RECEIPT_ACCOUNT_TYPE_CODE_INDIVIDUAL;
		} else if (type.indexOf("organization") == 0) {
			return RECEIPT_ACCOUNT_TYPE_CODE_ORGANIZATION;
		} else if (type.indexOf("stateagency") == 0) {
			return RECEIPT_ACCOUNT_TYPE_CODE_GOVERNMENT;
		} else {
			throw new UnsupportedOperationException("Please specify the customer type is INDIVIDUAL or ORGANIZATION or STATEAGENCY");
		}
	}
	
	@RequestMapping(value="/printDailyPaymentReport.pdf", method=RequestMethod.POST)
	public void printDailyPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyPayment";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String accountID = request.getParameter("accountID");
		String vatRate = request.getParameter("vatRate");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String soureType = request.getParameter("soureType");
		String serviceType = request.getParameter("serviceType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		JASPER_JRXML_FILENAME = null!=serviceType || !"".equalsIgnoreCase(serviceType)? AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)?"AgentDailyPayment": AppConstants.PAYMENT_TYPE_FOREIGN.equalsIgnoreCase(serviceType)?"EpisDailyForeign":JASPER_JRXML_FILENAME:JASPER_JRXML_FILENAME;
		reportController.getDailyPaymentReportJSON(request, printPdf, startDate, endDate, accountID, vatRate, shop, officer, soureType, serviceType);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDailyPaymentReport");
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
				// pichanan 20170801
				String charge = null != reportPayment.getCharge() ? reportPayment.getCharge() : "0";
				String vat = null != reportPayment.getVat() ? reportPayment.getVat() : "0";
				String totalCharge = null != reportPayment.getTotalCharge() ? reportPayment.getTotalCharge() : "0";
				// end pichanan 20170801
				dailyPaymentTO.setDcharge(new BigDecimal(charge));
				dailyPaymentTO.setDvat(new BigDecimal(vat));
				//dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(totalCharge)));
				
				dailyPaymentTO.setRowNo(String.valueOf(i+1)); 
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setCollectionUnit(AppUtil.toString(reportPayment.getCollectionUnit()));
				dailyPaymentTO.setInvoiceNo(AppUtil.toString(reportPayment.getInvoiceNo()));
				dailyPaymentTO.setPaymentCash(reportPayment.getPaymentCash());
				dailyPaymentTO.setDocumentNo(AppUtil.toString(reportPayment.getDocumentNo()));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(charge)));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getCharge()?!reportPayment.getCharge().equalsIgnoreCase("")?reportPayment.getCharge():"0":"0")));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getVat()?!reportPayment.getVat().equalsIgnoreCase("")?reportPayment.getVat():"0":"0")));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getTotalCharge()?!reportPayment.getTotalCharge().equalsIgnoreCase("")?reportPayment.getTotalCharge():"0":"0")));
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				dailyPaymentTO.setDiscount(AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getDiscount()?!reportPayment.getDiscount().equalsIgnoreCase("")?reportPayment.getDiscount():"0":"0")));
				dailyPaymentTO.setTotalChargeForeign(reportPayment.getMsgForeign()+" "+ AppUtil.toCurrencyPrint(new BigDecimal(null!=reportPayment.getTotalCharge()?!reportPayment.getTotalCharge().equalsIgnoreCase("")?reportPayment.getTotalCharge():"0":"0")));
				dailyPaymentTO.setCurrencyRate((null!=reportPayment.getCurrencyRate()?!reportPayment.getCurrencyRate().equalsIgnoreCase("")?reportPayment.getCurrencyRate():"0":"0"));
				dailyPaymentTO.setTaxId(reportPayment.getTaxId());
				dailyPaymentTO.setAgentCode(reportPayment.getAgentCode());
				dailyPaymentTO.setAgentName(reportPayment.getAgentName());
				dailyPaymentTO.setRef1(reportPayment.getRef1());
				dailyPaymentTO.setRef2(reportPayment.getRef2());
				dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());
				dailyPaymentTO.setTotalChargeB(new BigDecimal (reportPayment.getTotalCharge()));

				
				
				if(!"".equals(reportPayment.getPaymentType())) {
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
				}
				
				collections.add(dailyPaymentTO);
			
				
                if (!AppConstants.CANCEL_TH.equalsIgnoreCase(reportPayment.getStatus())) {
                    // SUM : CollectionUnit.
                    if (AppUtil.isStringHasValue(reportPayment.getCollectionUnit())) {
                        rowCollectionUnit += 1;
                        chargeCollectionUnit += new Double(charge);
                        vatCollectionUnit += new Double(vat);
                        totalChargeCollectionUnit += new Double(totalCharge);
                    }
                    // SUM : PaymentUser.
                    if (EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
                        rowPaymentUser += 1;
                        chargePaymentUser += new Double(charge);
                        vatPaymentUser += new Double(vat);
                        totalChargePaymentUser += new Double(totalCharge);
                    }
                    // SUM : Vat 7%.
                    if ("7".equals(reportPayment.getVatRate())) {
                        row7Percent += 1;
                        charge7Percent += new Double(charge);
                        vat7Percent += new Double(vat);
                        totalCharge7Percent += new Double(totalCharge);
                    }
                    // SUM : Vat 0%.
                    if ("0".equals(reportPayment.getVatRate())) {
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
		documentObject.setSumTotalCollectionUnit(AppUtil.toCurrencyPrint(chargeCollectionUnit+vatCollectionUnit));
		// SUM : PaymentUser.
		documentObject.setSumRowPaymentUser(AppUtil.toString(rowPaymentUser));
		documentObject.setSumChargePaymentUser(AppUtil.toCurrencyPrint(chargePaymentUser));
		documentObject.setSumVatPaymentUser(AppUtil.toCurrencyPrint(vatPaymentUser));
		documentObject.setSumTotalChargePaymentUser(AppUtil.toCurrencyPrint(totalChargePaymentUser));
		documentObject.setSumTotalPaymentUser(AppUtil.toCurrencyPrint(chargePaymentUser+vatPaymentUser));
		// SUM : Vat 7%.
		documentObject.setSumRow7Percent(AppUtil.toString(AppUtil.toString(row7Percent)));
		documentObject.setSumCharge7Percent(AppUtil.toCurrencyPrint(charge7Percent));
		documentObject.setSumVat7Percent(AppUtil.toCurrencyPrint(vat7Percent));
		documentObject.setSumTotalCharge7Percent(AppUtil.toCurrencyPrint(totalCharge7Percent));
		documentObject.setSumTotal7Percent(AppUtil.toCurrencyPrint(charge7Percent+vat7Percent));
		// SUM : Vat 0%.
		documentObject.setSumRow0Percent(AppUtil.toString(AppUtil.toString(row0Percent)));
		documentObject.setSumCharge0Percent(AppUtil.toCurrencyPrint(charge0Percent));
		documentObject.setSumVat0Percent(AppUtil.toCurrencyPrint(vat0Percent));
		documentObject.setSumTotalCharge0Percent(AppUtil.toCurrencyPrint(totalCharge0Percent));
		documentObject.setSumTotal0Percent(AppUtil.toCurrencyPrint(charge0Percent+vat0Percent));
		// SUM : All.
		documentObject.setSumRowAll(AppUtil.toString(AppUtil.toString(rowAll)));
		documentObject.setSumChargeAll(AppUtil.toCurrencyPrint(chargeAll));
		documentObject.setSumVatAll(AppUtil.toCurrencyPrint(vatAll));
		documentObject.setSumTotalChargeAll(AppUtil.toCurrencyPrint(totalChargeAll));
		documentObject.setSumTotalAll(AppUtil.toCurrencyPrint(chargeAll+vatAll));

		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	@RequestMapping(value="/printChaquePaymentReport.pdf", method=RequestMethod.POST)
	public void printChaquePaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyChequePayment";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
//		String soureType = request.getParameter("soureType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		reportController.getChequePaymentReportJSON(request, printPdf, startDate, endDate, shop, officer);
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
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
		

		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	@RequestMapping(value="/printSalesFullTaxDailyReport.pdf", method=RequestMethod.POST)
	public void printSalesFullTaxDailyReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSalesFullTaxDailyPayment";
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

				String charge = null != reportPayment.getCharge() ? reportPayment.getCharge() : "0";
				String vat = null != reportPayment.getVat() ? reportPayment.getVat() : "0";
				String totalCharge = null != reportPayment.getTotalCharge() ? reportPayment.getTotalCharge() : "0";

				dailyPaymentTO.setRowNo(String.valueOf(i + 1));
				dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
				dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setTaxId(AppUtil.toString(reportPayment.getTaxId()));
				dailyPaymentTO.setBranchNo(AppUtil.toString(reportPayment.getBranchNo()));
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(charge)));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(vat)));
				/*epis7 no.44*/
				dailyPaymentTO.setDcharge(new BigDecimal(charge));
				dailyPaymentTO.setDvat(new BigDecimal(vat));
				/*end epis7 no.44*/
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(totalCharge)));
				dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				collections.add(dailyPaymentTO);

				if (!AppConstants.CANCEL_TH.equalsIgnoreCase(reportPayment.getStatus())) {
					// SUM : PaymentUser.
					if (EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
						rowPaymentUser += 1;
						chargePaymentUser += new Double(charge);
						vatPaymentUser += new Double(vat);
						totalChargePaymentUser += new Double(totalCharge);
					}
					// SUM : Vat 7%.
					if ("7".equals(reportPayment.getVatRate())) {
						row7Percent += 1;
						charge7Percent += new Double(charge);
						vat7Percent += new Double(vat);
						totalCharge7Percent += new Double(totalCharge);
					}
					// SUM : Vat 0%.
					if ("0".equals(reportPayment.getVatRate())) {
						row0Percent += 1;
						charge0Percent += new Double(charge);
						vat0Percent += new Double(vat);
						totalCharge0Percent += new Double(totalCharge);
					}
						/*ICE FIXED CODE  ข้อ 44  -  SUM TOTAL รวมทั้งสิ้น */
					// SUM ALL
					rowAll += 1; //rowTotal = rowTotal+1
					chargeAll += new Double(charge);
					vatAll += new Double(vat);
					totalChargeAll += new Double(totalCharge);
				}
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
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
		
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	@RequestMapping(value="/printSalesAbbrTaxDailyReport.pdf", method=RequestMethod.POST)
	public void printSalesAbbrTaxDailyReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSalesAbbrTaxDailyPayment";
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
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setTaxId(AppUtil.toString(reportPayment.getTaxId()));
				dailyPaymentTO.setBranchNo(AppUtil.toString(reportPayment.getBranchNo()));
				dailyPaymentTO.setDcharge(new BigDecimal(reportPayment.getCharge()));
				dailyPaymentTO.setDvat(new BigDecimal(reportPayment.getVat()));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getTotalCharge())));
				dailyPaymentTO.setStatus(reportPayment.getStatus());
				collections.add(dailyPaymentTO);

				if (!AppConstants.CANCEL_TH.equalsIgnoreCase(reportPayment.getStatus())) {
					// SUM : PaymentUser.
					if (EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
						rowPaymentUser += 1;
						chargePaymentUser += new Double(reportPayment.getCharge());
						vatPaymentUser += new Double(reportPayment.getVat());
						totalChargePaymentUser += new Double(reportPayment.getTotalCharge());
					}
					// SUM : Vat 7%.
					if ("7".equals(reportPayment.getVatRate())) {
						row7Percent += 1;
						charge7Percent += new Double(reportPayment.getCharge());
						vat7Percent += new Double(reportPayment.getVat());
						totalCharge7Percent += new Double(reportPayment.getTotalCharge());
					}
					// SUM : Vat 0%.
					if ("0".equals(reportPayment.getVatRate())) {
						row0Percent += 1;
						charge0Percent += new Double(reportPayment.getCharge());
						vat0Percent += new Double(reportPayment.getVat());
						totalCharge0Percent += new Double(reportPayment.getTotalCharge());
					}

					/*ICE FIXED CODE  ข้อ 50  -  SUM TOTAL รวมทั้งสิ้น */
					// SUM ALL
					rowAll += 1; //rowTotal = rowTotal+1
					chargeAll += new Double(reportPayment.getCharge());
					vatAll += new Double(reportPayment.getVat());
					totalChargeAll += new Double(reportPayment.getTotalCharge());
				}
			}
			DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
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
		
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	
	//pichanan 20170821

		@RequestMapping(value="/printSalesAbbrTaxMonthlyReport.pdf", method=RequestMethod.POST)
		public void printSalesAbbrTaxMonthlyReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
			String JASPER_JRXML_FILENAME = "EpisSalesAbbrTaxMonthlyPayment";
			String printPdf = stripToEmpty(request.getParameter("printPdf"));
			String period = request.getParameter("period");
			String shop = request.getParameter("shop");
			String type = request.getParameter("type");
			String officer = request.getParameter("officer");
			String serviceType = request.getParameter("serviceType");
			Map<String, Object> parameters = new HashMap<String, Object>();
			reportController.getMonthlySalesTaxReportJSON(request, printPdf, period, shop, type, officer, serviceType);
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printMonthlySalesTaxReport");
			List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
			
			//double sumCharge = 0, sumVat = 0, sumTotalCharge = 0;
			
			System.out.println(" cccc : " + reportPaymentDTO);
			
			if(reportPaymentDTO != null) {	
				
				List<ReportPayment> reportPayments = reportPaymentDTO.getData();
				for(int i=0; i<reportPayments.size(); i++) {
					ReportPayment reportPayment = reportPayments.get(i);
					DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
					dailyPaymentTO.setRowNo(String.valueOf(i+1));
					dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
					dailyPaymentTO.setCntReceipt(reportPayment.getCntReceipt());
					dailyPaymentTO.setPosNo(reportPayment.getPosNo());
					dailyPaymentTO.setReceiptFr(reportPayment.getReceiptFr());
					dailyPaymentTO.setReceiptTo(reportPayment.getReceiptTo());
					
					String charge = null != reportPayment.getCharge() ? reportPayment.getCharge() : "0";
					String vat = null != reportPayment.getVat() ? reportPayment.getVat() : "0";
					//String totalCharge = null != reportPayment.getTotalCharge() ? reportPayment.getTotalCharge() : "0";
					dailyPaymentTO.setDcharge(new BigDecimal(charge));
					dailyPaymentTO.setDvat(new BigDecimal(vat));
					
					dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getCharge())));
					dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getVat())));
					dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getTotalCharge())));
					collections.add(dailyPaymentTO);

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
//			documentObject.setPos(EpContextHolder.getContext().getPosNo());
			documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
			
			parameters.put("ReportSource", documentObject);

			response.setContentType("application/pdf");
			
			net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
			JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
	        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			pushReportToOutputStream(response, jasperPrints);
		}
	
	
	
	@RequestMapping(value="/printDailyPaymentDeductReportStore.json", method=RequestMethod.POST)
	@ResponseBody
	public String printDailyPaymentDeductReportStore(HttpServletRequest request, HttpServletResponse response, Model model,
		  @RequestBody ReportPaymentDTO	 reportPaymentDTO) throws Exception {
		String tokenPdf=AppConstants.DAILY_PAYMENT_DEDUCT_REPORT_KEY;//"DailyPaymentDeductReportStoreKey";
		model.addAttribute(tokenPdf,reportPaymentDTO);
		logger.debug("tokenPdf["+tokenPdf+"]");
		return tokenPdf;
	}
	@RequestMapping(value="/printDailyPaymentDeductReportWithUpdate.pdf", method=RequestMethod.POST)
	public void printDailyPaymentDeductReportWithUpdate(HttpServletRequest request, HttpServletResponse response, Model model
		  ) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyPaymentDeduct";
		ReportPaymentDTO	 reportPaymentDTO =(ReportPaymentDTO)model.asMap().get(AppConstants.DAILY_PAYMENT_DEDUCT_REPORT_KEY);
		String sdate=reportPaymentDTO.getSdate();
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
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
				String amount3tredecim=reportPayment.getAmount3tredecim().trim().replaceAll(",","");
				String amount69bis=reportPayment.getAmount69bis().trim().replaceAll(",","");
				String amount69tre=reportPayment.getAmount69tre().trim().replaceAll(",","");
				String remark=reportPayment.getRemark();
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

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	@RequestMapping(value="/printDailyDeductPaymentReport.pdf", method=RequestMethod.POST)
	public void printDailyDeductPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyDeductPayment";
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
			dailyPaymentTO.setRowSummary("Y");
			collections.add(dailyPaymentTO);
		}
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
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
		
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	//pichanan 20170816

	@RequestMapping(value="/printMonthlyDeductPaymentReport.pdf", method=RequestMethod.POST)
	public void printMonthlyDeductPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisMonthlyDeductPayment";
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
				dailyPaymentTO.setAmount3tredecim(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getAmount3tredecim())));
				dailyPaymentTO.setAmount69bis(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getAmount69bis())));
				dailyPaymentTO.setAmount69tre(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getAmount69tre())));
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
			dailyPaymentTO.setRowSummary("Y");
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
		
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	
	//picht 29082017
	@RequestMapping(value="/printSummaryDailyPaymentReport.pdf", method=RequestMethod.POST)
	public void printSummaryDailyPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSummaryDailyPayment";
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
				if (!AppConstants.CANCEL_TH.equalsIgnoreCase(reportPayment.getStatus())) {
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
		
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	
				
				// Report : close-endofday-report.
				@RequestMapping(value="/printCloseEndOfDayReport.pdf", method=RequestMethod.POST)
				public void printCloseEndOfDayReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
					String JASPER_JRXML_FILENAME = "EpisCloseEndOfDayPayment";
					String printPdf = stripToEmpty(request.getParameter("printPdf"));
					String startDate = stripToEmpty(request.getParameter("startDate"));
					String period = request.getParameter("period");
					String shop = request.getParameter("shop");
					String officer = request.getParameter("officer");
					Map<String, Object> parameters = new HashMap<String, Object>();
					reportController.getCloseEndOfDayReportJSON(request, printPdf, startDate, period, shop, officer);
					List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
					ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printCloseEndOfDayReport");
					List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
					
					Officer object = officerRepo.findOne(new Long(officer)); 
					officer = object == null ? "": object.getCode();
					
					int sumRowPaymentFull = 0, sumRowPaymentABVR = 0, sumRowPenalty = 0, sumRowRetention = 0, sumRowCompensation = 0;
					double sumAmountPaymentFull = 0, sumAmountPaymentABVR = 0, sumAmountPenalty = 0, sumAmountRetention = 0, sumAmountCompensation = 0;
					
					String paymentTypeBuffer = "";
					if(reportPaymentDTO != null) {
						int countReportPayments = reportPaymentDTO.getData().size();
						for(int i=0; i<countReportPayments; i++) {
							ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
							DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
							dailyPaymentTO.setRowNo(String.valueOf(i+1));
							dailyPaymentTO.setPaymentType(reportPayment.getPaymentType());
							dailyPaymentTO.setSumCancelReceipt(AppUtil.toString(reportPayment.getSumCancelReceipt()));
							dailyPaymentTO.setSumCancelAmount(AppUtil.toCurrencyPrint(reportPayment.getSumCancelAmount()));
							dailyPaymentTO.setSumReceipt(AppUtil.toString(reportPayment.getSumReceipt()));
							dailyPaymentTO.setSumAmount(AppUtil.toCurrencyPrint(reportPayment.getSumAmount()));
							dailyPaymentTO.setSumTotalReceipt(AppUtil.toString(reportPayment.getSumTotalReceipt()));
							dailyPaymentTO.setSumTotalAmount(AppUtil.toCurrencyPrint(reportPayment.getSumTotalAmount()));
							
							if(!paymentTypeBuffer.equals(reportPayment.getPaymentType())) {
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
								
								paymentTypeBuffer = reportPayment.getPaymentType();
							}
							collections.add(dailyPaymentTO);
							
							sumRowPaymentFull += (reportPayment.getSumRowPaymentFull() == null)? 0: Integer.parseInt(reportPayment.getSumRowPaymentFull()); 
							sumAmountPaymentFull += (reportPayment.getSumAmountPaymentFull() == null)? 0: new Double(reportPayment.getSumAmountPaymentFull()); 
							sumRowPaymentABVR += (reportPayment.getSumRowPaymentABVR() == null)? 0: Integer.parseInt(reportPayment.getSumRowPaymentABVR()); 
							sumAmountPaymentABVR += (reportPayment.getSumAmountPaymentABVR() == null)? 0: new Double(reportPayment.getSumAmountPaymentABVR()); 
							//sumRowPenalty += 1; 
							//sumRowRetention += 1; 
							//sumRowCompensation += 1;

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
					documentObject.setOfficer(officer);
					// SUM : Receipt Full.
					documentObject.setSumRowPaymentFull(AppUtil.toString(sumRowPaymentFull));
					documentObject.setSumAmountPaymentFull(AppUtil.toCurrencyPrint(sumAmountPaymentFull));
					// SUM : Receipt ABVR.
					documentObject.setSumRowPaymentABVR(AppUtil.toString(sumRowPaymentABVR));
					documentObject.setSumAmountPaymentABVR(AppUtil.toCurrencyPrint(sumAmountPaymentABVR));
					// SUM : Penalty.
					documentObject.setSumRowPenalty(AppUtil.toString(sumRowPenalty));
					documentObject.setSumAmountPenalty(AppUtil.toCurrencyPrint(sumAmountPenalty));
					// SUM : Retention.
					documentObject.setSumRowRetention(AppUtil.toString(sumRowRetention));
					documentObject.setSumAmountRetention(AppUtil.toCurrencyPrint(sumAmountRetention));
					// SUM : Compensation.
					documentObject.setSumRowCompensation(AppUtil.toString(sumRowCompensation));
					documentObject.setSumAmountCompensation(AppUtil.toCurrencyPrint(sumAmountCompensation));
					
					parameters.put("ReportSource", documentObject);

					response.setContentType("application/pdf");
					
					net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
					JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
			        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
					pushReportToOutputStream(response, jasperPrints);
				}
				
				// Report : unclose-endofday-report.
					@RequestMapping(value="/printUnCloseEndOfDayReport.pdf", method=RequestMethod.POST)
					public void printUnCloseEndOfDayReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
						String JASPER_JRXML_FILENAME = "EpisUnCloseEndOfDayPayment";
						String printPdf = stripToEmpty(request.getParameter("printPdf"));
						String startDate = stripToEmpty(request.getParameter("startDate"));
						String period = request.getParameter("period");
						String shop = request.getParameter("shop");
						String officer = request.getParameter("officer");
						Map<String, Object> parameters = new HashMap<String, Object>();
						reportController.getUnCloseEndOfDayReportJSON(request, printPdf, startDate, period, shop, officer);
						List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
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
								dailyPaymentTO.setShopName(reportPayment.getShopName());

								collections.add(dailyPaymentTO);

							}
						}
						
						DailyPaymentTO documentObject = new DailyPaymentTO();
						documentObject.setPaymentDate(startDate.replaceAll("-", "/"));
						documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
						documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
						documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
						documentObject.setPos(EpContextHolder.getContext().getPosNo());
						documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
						documentObject.setOfficer(officer);
						
						parameters.put("ReportSource", documentObject);

						response.setContentType("application/pdf");
						
						net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
						JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
						pushReportToOutputStream(response, jasperPrints);
					}
				
	
		@RequestMapping(value="/printOfflinePaymentReport.pdf", method=RequestMethod.POST)
		public void printOfflinePaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
			String JASPER_JRXML_FILENAME = "EpisOfflinePayment";
			String printPdf = stripToEmpty(request.getParameter("printPdf"));
			String startDate = stripToEmpty(request.getParameter("startDate"));
			String period = request.getParameter("period");
			String shop = request.getParameter("shop");
			String officer = request.getParameter("officer");
			Map<String, Object> parameters = new HashMap<String, Object>();
			reportController.getOfflinePaymentReportJSON(request, printPdf, startDate, period, shop, officer);
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printOfflinePaymentReport");
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
			documentObject.setPaymentDate(startDate.replaceAll("-", "/"));
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
			
			parameters.put("ReportSource", documentObject);

			response.setContentType("application/pdf");
			
			net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
			JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
	        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			pushReportToOutputStream(response, jasperPrints);
		}
		
		// Report : cancel-payment-report.
		@RequestMapping(value="/printCancelPaymentReport.pdf", method=RequestMethod.POST)
		public void printCancelPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
			String printPdf = stripToEmpty(request.getParameter("printPdf"));
			String startDate = stripToEmpty(request.getParameter("startDate"));
			String endDate = request.getParameter("endDate");
			String invNo = request.getParameter("invNo");
			String shop = request.getParameter("shop");
			String officer = request.getParameter("officer");
			String sourceType = request.getParameter("sourceType");
			String serviceType = request.getParameter("serviceType");
			Map<String, Object> parameters = new HashMap<String, Object>();
			String JASPER_JRXML_FILENAME = "EpisCancelPayment";
			JASPER_JRXML_FILENAME =  null!=serviceType || !"".equalsIgnoreCase(serviceType)? AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(serviceType)?"EpisMNPCancelPayment":AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)?"EpisAgentCancelPayment":JASPER_JRXML_FILENAME:JASPER_JRXML_FILENAME;
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
					dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getTotalCharge())));
					dailyPaymentTO.setTaxId(reportPayment.getTaxId());
					dailyPaymentTO.setAgentCode(reportPayment.getAgentCode());
					dailyPaymentTO.setAgentName(reportPayment.getAgentName());
					dailyPaymentTO.setRef1(reportPayment.getRef1());
					dailyPaymentTO.setRef2(reportPayment.getRef2());
					dailyPaymentTO.setDcharge(new BigDecimal(reportPayment.getCharge()));
					dailyPaymentTO.setDvat(new BigDecimal(reportPayment.getVat()));
					dailyPaymentTO.setTotalChargeB(new BigDecimal(reportPayment.getTotalCharge()));
					dailyPaymentTO.setPaymentType(reportPayment.getPaymentType());
					dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());

					
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

			response.setContentType("application/pdf");
			
			net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
			JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
	        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			pushReportToOutputStream(response, jasperPrints);
		}	
	// Report : egp-payment-report.
	@RequestMapping(value="/printEgpPaymentReport.pdf", method=RequestMethod.POST)
	public void printEgpPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisEgpPayment";
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
				dailyPaymentTO.setEgpNo(reportPayment.getEgpNo());
				dailyPaymentTO.setEgpContract(reportPayment.getEgpContract());
				dailyPaymentTO.setStatus(reportPayment.getStatus());

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
                if (!AppConstants.CANCEL_TH.equalsIgnoreCase(reportPayment.getStatus())) {
                    // SUM : CollectionUnit.
                    if (AppUtil.isStringHasValue(reportPayment.getCollectionUnit())) {
                        rowCollectionUnit += 1;
                        chargeCollectionUnit += new Double(reportPayment.getCharge());
                        vatCollectionUnit += new Double(reportPayment.getVat());
                        totalChargeCollectionUnit += new Double(reportPayment.getTotalCharge());
                    }
                    // SUM : PaymentUser.
                    if (EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
                        rowPaymentUser += 1;
                        chargePaymentUser += new Double(reportPayment.getCharge());
                        vatPaymentUser += new Double(reportPayment.getVat());
                        totalChargePaymentUser += new Double(reportPayment.getTotalCharge());
                    }
                    // SUM : Vat 7%.
                    if ("7".equals(reportPayment.getVatRate())) {
                        row7Percent += 1;
                        charge7Percent += new Double(reportPayment.getCharge());
                        vat7Percent += new Double(reportPayment.getVat());
                        totalCharge7Percent += new Double(reportPayment.getTotalCharge());
                    }
                    // SUM : Vat 0%.
                    if ("0".equals(reportPayment.getVatRate())) {
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
		
		
		/*List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
		
		if(reportPaymentDTO != null) {
			int countReportPayments = reportPaymentDTO.getData().size();
			for(int i=0; i<countReportPayments; i++) {
				ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowNo(String.valueOf(i+1));
				dailyPaymentTO.setEgpNo(reportPayment.getEgpNo());
				dailyPaymentTO.setEgpContract(reportPayment.getEgpContract());
				dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
				dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
				dailyPaymentTO.setInvoiceNo(AppUtil.toString(reportPayment.getInvoiceNo()));
				dailyPaymentTO.setIssueDate(AppUtil.toString(reportPayment.getIssueDate()));
				dailyPaymentTO.setDueDate(AppUtil.toString(reportPayment.getDueDate()));
				dailyPaymentTO.setBillCycle(reportPayment.getBillCycle());
				dailyPaymentTO.setReceiptDate(AppUtil.toString(reportPayment.getReceiptDate()));
				dailyPaymentTO.setBranchName(reportPayment.getBranchName());
				dailyPaymentTO.setCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getCharge())));
				dailyPaymentTO.setVat(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getVat())));
				dailyPaymentTO.setTotalCharge(AppUtil.toCurrencyPrint(new BigDecimal(reportPayment.getTotalCharge())));
				collections.add(dailyPaymentTO);
			}
		}
		
		DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
		dailyPaymentTO.setRowSummary("Y");
		collections.add(dailyPaymentTO);
		
		DailyPaymentTO documentObject = new DailyPaymentTO();
		documentObject.setPaymentDate(startDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getBranchName());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		*/
		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");
		
		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}
	
	//by NSD 28-02-2017
    @RequestMapping(value="/printPaymentPenaltyExtendReceipt.pdf", method=RequestMethod.POST)
    public void printPaymentPenaltyExtendReceipt(HttpServletRequest request, HttpServletResponse response, PrintReceiptDTO printReceiptDTO) throws Exception {
        String JASPER_JRXML_FILENAME = "EpisPaymentPenaltyExtendReceipt";

        Map<String, Object> parameters = new HashMap<String, Object>();
        Officer officer = null;
        String receiptLang = printReceiptDTO.getReceiptLang();
        String billingGroup = printReceiptDTO.getBillingGroup();
        String receiptFormat = printReceiptDTO.getReceiptFormat();
        boolean isSubstitute = (printReceiptDTO.getSubstitute()!=null)?Boolean.valueOf(printReceiptDTO.getSubstitute()):false;
        String serviceType = "";

        if(AppUtil.isCollectionHasValue(printReceiptDTO.getReceipts())) {
            officer = officerRepo.findByName(request.getUserPrincipal().getName());
        }
        List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
        for (Receipt rct : printReceiptDTO.getReceipts()) {
            List<CustomerTaxReceiptForOthersPaymentTO> collections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
            prepareCustomerTaxReceiptForPenaltyExtendPaymentTO(collections, rct);
            if(AppUtil.isCollectionHasValue(collections)) {
                String refNo = "";//by NSD 16-02-2017
                //List<Service> serviceList = new ArrayList<>(rct.getServices());
                CustomerTaxReceiptForOthersPaymentTO documentObject = (CustomerTaxReceiptForOthersPaymentTO)collections.get(0);
                //documentObject.setImagePathRpt(context.getRealPath("images")+File.separatorChar+"CATTelecom_Logo.png");
                documentObject.setDocumentDate(documentObject.getDocumentDate());
                documentObject.setBranchCode(documentObject.getBranchCode());
                documentObject.setBranchName(documentObject.getBranchName());
                documentObject.setDocumentNo(documentObject.getDocumentNo());
                documentObject.setUserName(officer.getGivenName() + " " + officer.getFamilyName());

                //refNo = rct.getRefNo();//serviceList.get(0).getRefTransId();
                //serviceType = rct.getServiceType();
                //if(!StringUtils.isEmpty(rct.getRefNo())){
                if(null!=rct.getServices()){
                    for(Service service: rct.getServices()){
                        serviceType = service.getServiceName();
                        refNo = service.getRefTransId();
                    }
                }
                //documentObject.setRefNo(refNo);
                //documentObject.setServiceType(serviceType);
                documentObject.setPromotionTxt(rct.getPromotion());

                // }

                List<CustomerTaxReceiptForOthersPaymentTO> printCollections = new ArrayList<CustomerTaxReceiptForOthersPaymentTO>();
                for(int i=0; i<collections.size(); i++) {
                    printCollections.add((CustomerTaxReceiptForOthersPaymentTO)collections.get(i));
                }
                // set Title for Copy
                if(printReceiptDTO.isCopy()){
                    documentObject.setTitle(messages.getMessage(AppConstants.RECEIPT_PRINT_COPY_KEY, null, null));
                    JASPER_JRXML_FILENAME = "EpisPaymentPenaltyExtendCopyReceipt";
                    //documentObject.setReceiptTitle(messages.getMessage(sb.toString(), null, null));
                }else{
                    documentObject.setTitle("");
                }
                //getReceiptTitle(documentObject,isSubstitute,receiptLang);
                documentObject.setSubstitute(isSubstitute);
                if(isSubstitute){
                    //documentObject.setReasonOfSubstitute(printReceiptDTO.getReason());
                    getReprintVersionOther(documentObject,rct.getId(),printReceiptDTO.getReason(),printReceiptDTO.getReprint(),receiptLang);
                }

				/*if(StringUtils.isNotBlank(receiptFormat))
					setReceiptFormat(rct.getId(),receiptFormat);*/

                parameters.put("ReportSource", documentObject);

				response.setContentType("application/pdf");
				net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
				jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
            }
        }
        pushReportToOutputStream(response, jasperPrints);
    }

	// W3P 15-may-2017
	@RequestMapping(value="/printDailyNewReceiptSaleReport.pdf", method=RequestMethod.POST)
	public void printDailyNewReceiptSaleReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisSalesNewReceipt";
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
		documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
		documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
		documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
		documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
		documentObject.setPos(EpContextHolder.getContext().getPosNo());
		documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
		documentObject.setOfficer("");//(officer);

		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");

		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
		jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}

	// Pichanan 7-aug-2017
		@RequestMapping(value="/printDailyReceiptSaleReport.pdf", method=RequestMethod.POST)
		public void printDailyReceiptSaleReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
			String JASPER_JRXML_FILENAME = "EpisSalesReceipt";
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
					collections.add(dailyPaymentTO);
				}
				DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
				dailyPaymentTO.setRowSummary("Y");
				collections.add(dailyPaymentTO);
				
			}

			DailyPaymentTO documentObject = new DailyPaymentTO();
			documentObject.setPaymentDate(startDate.replaceAll("-", "/") +"  ถึง  "+ endDate.replaceAll("-", "/"));
			documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
			documentObject.setBranchCode(EpContextHolder.getContext().getBranchCode());
			documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
			documentObject.setPos(EpContextHolder.getContext().getPosNo());
			documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
			documentObject.setOfficer("");//(officer);

			parameters.put("ReportSource", documentObject);

			response.setContentType("application/pdf");

			net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
			JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
			jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
			pushReportToOutputStream(response, jasperPrints);
		}
		// end Pichanan 7-aug-2017
		
	@RequestMapping(value="/printPnExtDailyPaymentReport.pdf", method=RequestMethod.POST)
	public void printPnExtDailyPaymentReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String JASPER_JRXML_FILENAME = "EpisDailyPenaltyExtend";
		String printPdf = stripToEmpty(request.getParameter("printPdf"));
		String startDate = stripToEmpty(request.getParameter("startDate"));
		String endDate = request.getParameter("endDate");
		String receiptNo = request.getParameter("receiptNo");
		String shop = request.getParameter("shop");
		String officer = request.getParameter("officer");
		String soureType = request.getParameter("soureType");
		String serviceType = request.getParameter("serviceType");
		Map<String, Object> parameters = new HashMap<String, Object>();
		JASPER_JRXML_FILENAME = null!=serviceType || !"".equalsIgnoreCase(serviceType)? AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)?"AgentDailyPayment": AppConstants.PAYMENT_TYPE_FOREIGN.equalsIgnoreCase(serviceType)?"EpisDailyForeign":JASPER_JRXML_FILENAME:JASPER_JRXML_FILENAME;
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

				if (!AppConstants.CANCEL_TH.equalsIgnoreCase(reportPayment.getStatus())) {
					// SUM : CollectionUnit.
					if (AppUtil.isStringHasValue(reportPayment.getCollectionUnit())) {
						rowCollectionUnit += 1;
						chargeCollectionUnit += new Double(charge);
						vatCollectionUnit += new Double(vat);
						totalChargeCollectionUnit += new Double(totalCharge);
					}
					// SUM : PaymentUser.
					if (EpContextHolder.getContext().getUserCode().equals(reportPayment.getPaymentUser())) {
						rowPaymentUser += 1;
						chargePaymentUser += new Double(charge);
						vatPaymentUser += new Double(vat);
						totalChargePaymentUser += new Double(totalCharge);
					}
					// SUM : Vat 7%.
					if ("7".equals(reportPayment.getVatRate())) {
						row7Percent += 1;
						charge7Percent += new Double(charge);
						vat7Percent += new Double(vat);
						totalCharge7Percent += new Double(totalCharge);
					}
					// SUM : Vat 0%.
					if ("0".equals(reportPayment.getVatRate())) {
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

		parameters.put("ReportSource", documentObject);

		response.setContentType("application/pdf");

		net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
		jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		pushReportToOutputStream(response, jasperPrints);
	}

	
	// Report : printDiscountDailyCreditReport
			@RequestMapping(value="/printDiscountDailyCreditReport.pdf", method=RequestMethod.POST)
			public void printDiscountDailyCreditReport(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
				String JASPER_JRXML_FILENAME = "EpisDiscountDailyCredit";
				String printPdf = stripToEmpty(request.getParameter("printPdf"));
				String startDate = stripToEmpty(request.getParameter("startDate"));
				String toDate = stripToEmpty(request.getParameter("toDate"));
				String period = request.getParameter("period");
				String shop = request.getParameter("shop");
				String officer = request.getParameter("officer");
				Map<String, Object> parameters = new HashMap<String, Object>();
				reportController.getDiscountDailyCreditReportJSON(request, printPdf, startDate,toDate, period, shop, officer);
				List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				ReportPaymentDTO reportPaymentDTO = (ReportPaymentDTO)request.getAttribute("printDiscountDailyCreditReport");
				List<DailyPaymentTO> collections = new ArrayList<DailyPaymentTO>();
							
				if(reportPaymentDTO != null) {
					int countReportPayments = reportPaymentDTO.getData().size();
					for(int i=0; i<countReportPayments; i++) {
						ReportPayment reportPayment = reportPaymentDTO.getData().get(i);
						DailyPaymentTO dailyPaymentTO = new DailyPaymentTO();
						dailyPaymentTO.setRowNo(String.valueOf(i+1));
//						dailyPaymentTO.setReceiptDate(reportPayment.getReceiptDate());
//						dailyPaymentTO.setPaymentUser(reportPayment.getPaymentUser());
//						dailyPaymentTO.setPayFullname(reportPayment.getPayFullname());
//						dailyPaymentTO.setShopNo(reportPayment.getShopNo());
//						dailyPaymentTO.setShopName(reportPayment.getShopName());
//						if(reportPayment.getPosName() != null && !reportPayment.getPosName().equals("")) {
//							dailyPaymentTO.setPosName(reportPayment.getPosName());
//						}else {
//							dailyPaymentTO.setPosName("-");
//						}	
						String totalCharge = null != reportPayment.getTotalCharge() ? reportPayment.getTotalCharge() : "0";
						String discount = null != reportPayment.getDiscount() ? reportPayment.getDiscount() : "0";
						String after_sale_discount  = null != reportPayment.getAfter_sale_discount() ? reportPayment.getAfter_sale_discount() : "0";
						dailyPaymentTO.setDiscountB(new BigDecimal(discount));
						dailyPaymentTO.setTotalChargeB(new BigDecimal(totalCharge));
						dailyPaymentTO.setAfter_sale_discountB(new BigDecimal(after_sale_discount));
						
						dailyPaymentTO.setCollectionUnit(AppUtil.toString(reportPayment.getCollectionUnit()));
						dailyPaymentTO.setReceiptNo(reportPayment.getReceiptNo());
						dailyPaymentTO.setAccountNo(reportPayment.getAccountNo());
						dailyPaymentTO.setReceiptName(reportPayment.getReceiptName());
						dailyPaymentTO.setInvoiceNo(reportPayment.getInvoiceNo());			
						dailyPaymentTO.setStatus(reportPayment.getAttributes().indexOf("R")>0?"ยกเลิก":"-");
						
						if(!"".equals(reportPayment.getPaymentType())) {
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
						}
						
						collections.add(dailyPaymentTO);

					}
				}
				
				DailyPaymentTO documentObject = new DailyPaymentTO();
				documentObject.setPaymentDate(startDate.replaceAll("-", "/") +" ถึง " + toDate.replaceAll("-", "/"));
				documentObject.setDocumentDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN, ENGLISH).format(new Date()));
				documentObject.setBranchArea(EpContextHolder.getContext().getBranchArea());
				documentObject.setBranchName(EpContextHolder.getContext().getDescAbvrth());
				documentObject.setPos(EpContextHolder.getContext().getPosNo());
				documentObject.setUserName(EpContextHolder.getContext().getUserName() +" : "+ EpContextHolder.getContext().getUserGivenName() +" "+ EpContextHolder.getContext().getUserFamilyName());
				
				documentObject.setOfficer(officer);
				
				parameters.put("ReportSource", documentObject);

				response.setContentType("application/pdf");
				
				net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
		        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
				pushReportToOutputStream(response, jasperPrints);
			}
   
			@RequestMapping(value="/printEmpDayEndClosing.pdf", method = RequestMethod.POST)
			public void printEmpDayEndClosing(HttpServletRequest request, HttpServletResponse response//, PrintReceiptDTO printReceiptDTO
					, PrintDayEndClosingDTO printDayEndClosingDTO
			) throws Exception {

				String JASPER_JRXML_FILENAME = "EpisEndDayClosingEmp";
		        String JASPER_JRXML_FILENAME_BACKDATE = "EpisEndDayClosingEmpBackDate";
				//String REPORT_OUTPUT_FILENAME = request.getUserPrincipal().getName()+"-EpisPaymentReceipt";

				Map<String, Object> parameters = new HashMap<String, Object>();
				Officer officer = null;
				List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();

				for(EmpClosing dtl: printDayEndClosingDTO.getEmpClosings()){
					Long empClosingId = dtl.getId();
					List<DayEndClosingTO> collections = new ArrayList<DayEndClosingTO>();
					collections = prepareEmpDayEndClosing(empClosingId);
					DayEndClosingTO documentObject = new DayEndClosingTO();
					documentObject = collections.get(0);
					//officer = officerRepo.findByCode(documentObject.getReqUser());
					//documentObject.setReqUser(officer.getGivenName() + " " + officer.getFamilyName());
					parameters.put("ReportSource", documentObject);
					response.setContentType("application/pdf");
		            net.sf.jasperreports.engine.JasperReport report;
		            if(!StringUtils.equals(documentObject.getBackDateStatus(), AppConstants.ENDDAY_CLOSE_BACKDATE)){
		                report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
		            }else{
		                report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME_BACKDATE + FILE_TYPE_JRXML);
		            }
					JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();

					jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));

				}
				pushReportToOutputStream(response, jasperPrints);
			}

			/*@RequestMapping(value="/printEmpDayEndClosingOld.pdf")
			public void printEmpDayEndClosingOld(HttpServletRequest request, HttpServletResponse response//, PrintReceiptDTO printReceiptDTO
					, @RequestParam(name = "empClosingId") Long empClosingId
			) throws Exception {

				String JASPER_JRXML_FILENAME = "EpisEndDayClosingEmp";
				//String REPORT_OUTPUT_FILENAME = request.getUserPrincipal().getName()+"-EpisPaymentReceipt";

				Map<String, Object> parameters = new HashMap<String, Object>();
				Officer officer = null;
				List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				List<DayEndClosingTO> collections = new ArrayList<DayEndClosingTO>();
				collections = prepareEmpDayEndClosing(empClosingId);
				DayEndClosingTO documentObject = new DayEndClosingTO();
				documentObject = collections.get(0);
				//officer = officerRepo.findByCode(documentObject.getReqUser());
				//documentObject.setReqUser(officer.getGivenName() + " " + officer.getFamilyName());
				parameters.put("ReportSource", documentObject);
				response.setContentType("application/pdf");

				net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();

				jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
				pushReportToOutputStream(response, jasperPrints);
			}*/

		    @RequestMapping(value="/printShopDayEndClosing.pdf")
		    public void printShopDayEndClosing(HttpServletRequest request, HttpServletResponse response//, PrintReceiptDTO printReceiptDTO
		            , @RequestParam(name = "shopClosingId") Long shopClosingId
		    ) throws Exception {

		        String JASPER_JRXML_FILENAME = "EpisEndDayClosingBranch";
				String JASPER_JRXML_FILENAME_BACKDATE = "EpisEndDayClosingBranchBackDate";
		        //String REPORT_OUTPUT_FILENAME = request.getUserPrincipal().getName()+"-EpisPaymentReceipt";

		        Map<String, Object> parameters = new HashMap<String, Object>();
		        Officer officer = null;
		        List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		        List<DayEndClosingTO> collections = new ArrayList<DayEndClosingTO>();
		        collections = prepareShopDayEndClosing(shopClosingId);
		        DayEndClosingTO documentObject = new DayEndClosingTO();
		        documentObject = collections.get(0);
		        //officer = officerRepo.findByCode(documentObject.getReqUser());
		        //documentObject.setReqUser(officer.getGivenName() + " " + officer.getFamilyName());
		        parameters.put("ReportSource", documentObject);
		        response.setContentType("application/pdf");
				net.sf.jasperreports.engine.JasperReport report;
				if(!StringUtils.equals(documentObject.getBackDateStatus(), AppConstants.ENDDAY_CLOSE_BACKDATE)){
					report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				}else{
					report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME_BACKDATE + FILE_TYPE_JRXML);
				}
		        JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();

		        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
		        pushReportToOutputStream(response, jasperPrints);
		    }

			private List<DayEndClosingTO> prepareEmpDayEndClosing(Long empClosingId){
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				df.setMinimumFractionDigits(2);
				df.setMinimumIntegerDigits(1);
				List<DayEndClosingTO> rtDayEndClosingTos = new ArrayList<DayEndClosingTO>();
				List<EndDayClosing> endDayClosingList = endDayClosingService.findEndDayClosingEmpGroup(empClosingId);
				BigDecimal totalAmount = BigDecimal.ZERO;
				for(EndDayClosing dtl: endDayClosingList){
					DayEndClosingTO rtDtl = new DayEndClosingTO();
					BigDecimal sumBackdateAmount = BigDecimal.ZERO;
					rtDtl.setEmpCode(dtl.getUserName());
					rtDtl.setEmpName(dtl.getEmpName());
					rtDtl.setBranchName(dtl.getShopName());

					rtDtl.setClosingDate(AppUtil.formatter_EN.format(dtl.getCreateDttm()));
					rtDtl.setClosingTime(AppUtil.formatter_EN_TIME.format(dtl.getCreateDttm()));

					rtDtl.setReceiptDate(AppUtil.formatter_EN.format(dtl.getReceiptDate()!=null?dtl.getReceiptDate():dtl.getClosingDate()));
					rtDtl.setReceiptTime(AppUtil.formatter_EN_TIME.format(dtl.getReceiptDate()!=null?dtl.getReceiptDate():dtl.getClosingDate()));

					sumBackdateAmount = dtl.getBackDateTotalAmount().add((dtl.getTransGfmisTotalAmount().add(dtl.getTransForeignThTotalAmount())));

					totalAmount = dtl.getTotalCash().add(dtl.getTotalCheque()).add(dtl.getTotalCredit()).add(dtl.getTotalMoneyOrder()).add(dtl.getTotalBillExchange())
							.add(dtl.getTotalMoneyTransfer()).add(dtl.getTotalOffset()).add(dtl.getTotalCoupon()).add(dtl.getTotalWt3tred()).add(dtl.getTotalWt69tre()).add(dtl.getTotalWt69bis()).add(sumBackdateAmount);
					rtDtl.setTotalReceiptAmount(df.format(totalAmount/*dtl.getExcCancelTotalAmount()*/));
					rtDtl.setTotalReceiptCount(dtl.getExcCancelTotalCount().toString());
					rtDtl.setTotalCashAmount(df.format(dtl.getTotalCash()));
					rtDtl.setTotalCashCount(dtl.getTotalCashCount().toString());
					rtDtl.setTotalChequeAmount(df.format(dtl.getTotalCheque()));
					rtDtl.setTotalChequeCount(dtl.getTotalChequeCount().toString());
					rtDtl.setTotalCreditCardAmount(df.format(dtl.getTotalCredit()));
					rtDtl.setTotalCreditCardCount(dtl.getTotalCreditCount().toString());
					rtDtl.setTotalMoneyOrderAmount(df.format(dtl.getTotalMoneyOrder()));
					rtDtl.setTotalMoneyOrderCount(dtl.getTotalMoneyOrdCnt().toString());
					rtDtl.setTotalBillExchangeAmount(df.format(dtl.getTotalBillExchange()));
					rtDtl.setTotalBillExchangeCount(dtl.getTotalBillExchCnt().toString());
					rtDtl.setTotalMoneyTransferAmount(df.format(dtl.getTotalMoneyTransfer()));
					rtDtl.setTotalMoneyTransferCount(dtl.getTotalMoneyTrnsCnt().toString());
					rtDtl.setTotalForeignTransferAmount(df.format(dtl.getTransForeignThTotalAmount()));
					rtDtl.setTotalForeignTransferCount(dtl.getTotalForeignTrnsCnt().toString());

					Long countBackdateReceipt = dtl.getBackDateTotalCount()+dtl.getTransGfmisTotalCount()+dtl.getTransForeignTotalCount();
					rtDtl.setTotalBackDateAmount(df.format(sumBackdateAmount));
					rtDtl.setTotalBackDateCount(countBackdateReceipt.toString());
					rtDtl.setTotalOffsetAmount(df.format(dtl.getTotalOffset()));
					rtDtl.setTotalOffsetCount(dtl.getTotalOffsetCount().toString());
					rtDtl.setTotalCouponAmount(df.format(dtl.getTotalCoupon()));
					rtDtl.setTotalCouponCount(dtl.getTotalCouponCount().toString());

					rtDtl.setWt3tredAmount(df.format(dtl.getTotalWt3tred()));
					rtDtl.setWt3tredCount("");
					rtDtl.setWt69treAmount(df.format(dtl.getTotalWt69tre()));
					rtDtl.setWt69treCount("");
					rtDtl.setWt69bisAmount(df.format(dtl.getTotalWt69bis()));
					rtDtl.setWt69bisCount("");

					rtDtl.setCancelReceiptAmount(df.format(dtl.getTotalCancelAmt()));
					rtDtl.setCancelReceiptCount(dtl.getTotalCancelCnt().toString());

					rtDtl.setTotalDiscount(df.format(dtl.getTotalDiscount()));
		            rtDtl.setBackDateStatus(dtl.getBackDateStatus());

					rtDtl.setReceiveWhFullCount(dtl.getReceiveWhFullCount().toString());
					rtDtl.setCancelReceiveWhFullCount(dtl.getCancelReceiveWhFullCount().toString());
					rtDtl.setReceiveWhAbvrCount(dtl.getReceiveWhAbvrCount().toString());
					rtDtl.setCancelReceiveWhAbvrCount(dtl.getCancelReceiveWhAbvrCount().toString());
					rtDtl.setWhOnlyFullCount(dtl.getWhOnlyFullCount().toString());
					rtDtl.setCancelWhOnlyFullCount(dtl.getCancelWhOnlyFullCount().toString());
					rtDtl.setReceiveOnlyCount(dtl.getReceiveOnlyCount().toString());
					rtDtl.setCancelReceiveOnlyCount(dtl.getCancelReceiveOnlyCount().toString());

					rtDtl.setReceiveWhFullAmount(dtl.getReceiveWhFullAmount().toString());
					rtDtl.setCancelReceiveWhFullAmount(dtl.getCancelReceiveWhFullAmount().toString());
					rtDtl.setReceiveWhAbvrAmount(dtl.getReceiveWhAbvrAmount().toString());
					rtDtl.setCancelReceiveWhAbvrAmount(dtl.getCancelReceiveWhAbvrAmount().toString());
					rtDtl.setWhOnlyFullAmount(dtl.getWhOnlyFullAmount().toString());
					rtDtl.setCancelWhOnlyFullAmount(dtl.getCancelWhOnlyFullAmount().toString());
					rtDtl.setReceiveOnlyAmount(dtl.getReceiveOnlyAmount().toString());
					rtDtl.setCancelReceiveOnlyAmount(dtl.getCancelReceiveOnlyAmount().toString());

					rtDayEndClosingTos.add(rtDtl);
				}
				return rtDayEndClosingTos;
			}
		    private List<DayEndClosingTO> prepareShopDayEndClosing(Long shopClosingId){
		        DecimalFormat df = new DecimalFormat();
		        df.setMaximumFractionDigits(2);
		        df.setMinimumFractionDigits(2);
		        df.setMinimumIntegerDigits(1);
		        List<DayEndClosingTO> rtDayEndClosingTos = new ArrayList<DayEndClosingTO>();
		        List<EndDayClosing> endDayClosingList = endDayClosingService.findEndDayClosingShopGroup(shopClosingId,null);
		        BigDecimal totalAmount = BigDecimal.ZERO;
		        for(EndDayClosing dtl: endDayClosingList){
		            DayEndClosingTO rtDtl = new DayEndClosingTO();
					BigDecimal sumBackdateAmount = BigDecimal.ZERO;
		            rtDtl.setEmpCode(dtl.getUserName());
		            rtDtl.setEmpName(dtl.getEmpName());
		            rtDtl.setBranchName(dtl.getShopName());

		            rtDtl.setClosingDate(AppUtil.formatter_EN.format(dtl.getCreateDttm()));
		            rtDtl.setClosingTime(AppUtil.formatter_EN_TIME.format(dtl.getCreateDttm()));

					sumBackdateAmount = dtl.getBackDateTotalAmount().add((dtl.getTransGfmisTotalAmount().add(dtl.getTransForeignThTotalAmount())));

					totalAmount = dtl.getTotalCash().add(dtl.getTotalCheque()).add(dtl.getTotalCredit()).add(dtl.getTotalMoneyOrder()).add(dtl.getTotalBillExchange())
							.add(dtl.getTotalMoneyTransfer()).add(dtl.getTotalOffset()).add(dtl.getTotalCoupon()).add(dtl.getTotalWt3tred()).add(dtl.getTotalWt69tre()).add(dtl.getTotalWt69bis()).add(sumBackdateAmount);

					rtDtl.setTotalReceiptAmount(df.format(totalAmount/*dtl.getExcCancelTotalAmount()*/));
		            rtDtl.setTotalReceiptCount(dtl.getExcCancelTotalCount().toString());
		            rtDtl.setTotalCashAmount(df.format(dtl.getTotalCash()));
		            rtDtl.setTotalCashCount(dtl.getTotalCashCount().toString());
		            rtDtl.setTotalChequeAmount(df.format(dtl.getTotalCheque()));
		            rtDtl.setTotalChequeCount(dtl.getTotalChequeCount().toString());
		            rtDtl.setTotalCreditCardAmount(df.format(dtl.getTotalCredit()));
		            rtDtl.setTotalCreditCardCount(dtl.getTotalCreditCount().toString());
		            rtDtl.setTotalMoneyOrderAmount(df.format(dtl.getTotalMoneyOrder()));
		            rtDtl.setTotalMoneyOrderCount(dtl.getTotalMoneyOrdCnt().toString());
		            rtDtl.setTotalBillExchangeAmount(df.format(dtl.getTotalBillExchange()));
		            rtDtl.setTotalBillExchangeCount(dtl.getTotalBillExchCnt().toString());
		            rtDtl.setTotalMoneyTransferAmount(df.format(dtl.getTotalMoneyTransfer()));
		            rtDtl.setTotalMoneyTransferCount(dtl.getTotalMoneyTrnsCnt().toString());
		            rtDtl.setTotalForeignTransferAmount(df.format(dtl.getTotalForeignTransfer()));
		            rtDtl.setTotalForeignTransferCount(dtl.getTotalForeignTrnsCnt().toString());

		            Long countBackdateReceipt = dtl.getBackDateTotalCount()+dtl.getTransGfmisTotalCount()+dtl.getTransForeignTotalCount();
		            rtDtl.setTotalBackDateAmount(df.format(sumBackdateAmount));
		            rtDtl.setTotalBackDateCount(countBackdateReceipt.toString());
		            rtDtl.setTotalOffsetAmount(df.format(dtl.getTotalOffset()));
		            rtDtl.setTotalOffsetCount(dtl.getTotalOffsetCount().toString());
		            rtDtl.setTotalCouponAmount(df.format(dtl.getTotalCoupon()));
		            rtDtl.setTotalCouponCount(dtl.getTotalCouponCount().toString());

					rtDtl.setWt3tredAmount(df.format(dtl.getTotalWt3tred()));
					rtDtl.setWt3tredCount("");
					rtDtl.setWt69treAmount(df.format(dtl.getTotalWt69tre()));
					rtDtl.setWt69treCount("");
					rtDtl.setWt69bisAmount(df.format(dtl.getTotalWt69bis()));
					rtDtl.setWt69bisCount("");

		            rtDtl.setCancelReceiptAmount(df.format(dtl.getTotalCancelAmt()));
		            rtDtl.setCancelReceiptCount(dtl.getTotalCancelCnt().toString());

					rtDtl.setTotalDiscount(df.format(dtl.getTotalDiscount()));
					rtDtl.setBackDateStatus(dtl.getBackDateStatus());

					rtDtl.setReceiveWhFullCount(dtl.getReceiveWhFullCount().toString());
					rtDtl.setCancelReceiveWhFullCount(dtl.getCancelReceiveWhFullCount().toString());
					rtDtl.setReceiveWhAbvrCount(dtl.getReceiveWhAbvrCount().toString());
					rtDtl.setCancelReceiveWhAbvrCount(dtl.getCancelReceiveWhAbvrCount().toString());
					rtDtl.setWhOnlyFullCount(dtl.getWhOnlyFullCount().toString());
					rtDtl.setCancelWhOnlyFullCount(dtl.getCancelWhOnlyFullCount().toString());
					rtDtl.setReceiveOnlyCount(dtl.getReceiveOnlyCount().toString());
					rtDtl.setCancelReceiveOnlyCount(dtl.getCancelReceiveOnlyCount().toString());

					rtDtl.setReceiveWhFullAmount(dtl.getReceiveWhFullAmount().toString());
					rtDtl.setCancelReceiveWhFullAmount(dtl.getCancelReceiveWhFullAmount().toString());
					rtDtl.setReceiveWhAbvrAmount(dtl.getReceiveWhAbvrAmount().toString());
					rtDtl.setCancelReceiveWhAbvrAmount(dtl.getCancelReceiveWhAbvrAmount().toString());
					rtDtl.setWhOnlyFullAmount(dtl.getWhOnlyFullAmount().toString());
					rtDtl.setCancelWhOnlyFullAmount(dtl.getCancelWhOnlyFullAmount().toString());
					rtDtl.setReceiveOnlyAmount(dtl.getReceiveOnlyAmount().toString());
					rtDtl.setCancelReceiveOnlyAmount(dtl.getCancelReceiveOnlyAmount().toString());

		            rtDayEndClosingTos.add(rtDtl);
		        }
		        return rtDayEndClosingTos;
		    }

			@RequestMapping(value="/reprintShopDayEndClosing.pdf")
			public void reprintShopDayEndClosing(HttpServletRequest request, HttpServletResponse response//, PrintReceiptDTO printReceiptDTO
					, @RequestParam(name = "shopClosingIdStr") String shopClosingIdStr
			) throws Exception {

				String JASPER_JRXML_FILENAME = "EpisEndDayClosingBranch";
				String JASPER_JRXML_FILENAME_BACKDATE = "EpisEndDayClosingBranchBackDate";
				//String REPORT_OUTPUT_FILENAME = request.getUserPrincipal().getName()+"-EpisPaymentReceipt";

				Map<String, Object> parameters = new HashMap<String, Object>();
				Officer officer = null;
				List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				List<DayEndClosingTO> collections = new ArrayList<DayEndClosingTO>();
				collections = prepareShopDayEndClosing(shopClosingIdStr);
				DayEndClosingTO documentObject = new DayEndClosingTO();
				documentObject = collections.get(0);
				//officer = officerRepo.findByCode(documentObject.getReqUser());
				//documentObject.setReqUser(officer.getGivenName() + " " + officer.getFamilyName());
				parameters.put("ReportSource", documentObject);
				response.setContentType("application/pdf");
				net.sf.jasperreports.engine.JasperReport report;
				if(!StringUtils.equals(documentObject.getBackDateStatus(), AppConstants.ENDDAY_CLOSE_BACKDATE)){
					report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
				}else{
					report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME_BACKDATE + FILE_TYPE_JRXML);
				}
				JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();

				jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
				pushReportToOutputStream(response, jasperPrints);
			}

		    private List<DayEndClosingTO> prepareShopDayEndClosing(String shopClosingIdStr){
		        DecimalFormat df = new DecimalFormat();
		        df.setMaximumFractionDigits(2);
		        df.setMinimumFractionDigits(2);
		        df.setMinimumIntegerDigits(1);
		        List<DayEndClosingTO> rtDayEndClosingTos = new ArrayList<DayEndClosingTO>();
		        List<EndDayClosing> endDayClosingList = endDayClosingService.findEndDayClosingShopGroup(null,shopClosingIdStr);
		        BigDecimal totalAmount = BigDecimal.ZERO;
		        for(EndDayClosing dtl: endDayClosingList){
		            DayEndClosingTO rtDtl = new DayEndClosingTO();
					BigDecimal sumBackdateAmount = BigDecimal.ZERO;
		            rtDtl.setEmpCode(dtl.getUserName());
		            rtDtl.setEmpName(dtl.getEmpName());
		            rtDtl.setBranchName(dtl.getShopName());

		            rtDtl.setClosingDate(AppUtil.formatter_EN.format(dtl.getCreateDttm()));
		            rtDtl.setClosingTime(AppUtil.formatter_EN_TIME.format(dtl.getCreateDttm()));

					sumBackdateAmount = dtl.getBackDateTotalAmount().add((dtl.getTransGfmisTotalAmount().add(dtl.getTransForeignThTotalAmount())));

					totalAmount = dtl.getTotalCash().add(dtl.getTotalCheque()).add(dtl.getTotalCredit()).add(dtl.getTotalMoneyOrder()).add(dtl.getTotalBillExchange())
							.add(dtl.getTotalMoneyTransfer()).add(dtl.getTotalOffset()).add(dtl.getTotalCoupon()).add(dtl.getTotalWt3tred()).add(dtl.getTotalWt69tre()).add(dtl.getTotalWt69bis()).add(sumBackdateAmount);
		            rtDtl.setTotalReceiptAmount(df.format(dtl.getExcCancelTotalAmount()));
		            rtDtl.setTotalReceiptCount(dtl.getExcCancelTotalCount().toString());
		            rtDtl.setTotalCashAmount(df.format(dtl.getTotalCash()));
		            rtDtl.setTotalCashCount(dtl.getTotalCashCount().toString());
		            rtDtl.setTotalChequeAmount(df.format(dtl.getTotalCheque()));
		            rtDtl.setTotalChequeCount(dtl.getTotalChequeCount().toString());
		            rtDtl.setTotalCreditCardAmount(df.format(dtl.getTotalCredit()));
		            rtDtl.setTotalCreditCardCount(dtl.getTotalCreditCount().toString());
		            rtDtl.setTotalMoneyOrderAmount(df.format(dtl.getTotalMoneyOrder()));
		            rtDtl.setTotalMoneyOrderCount(dtl.getTotalMoneyOrdCnt().toString());
		            rtDtl.setTotalBillExchangeAmount(df.format(dtl.getTotalBillExchange()));
		            rtDtl.setTotalBillExchangeCount(dtl.getTotalBillExchCnt().toString());
		            rtDtl.setTotalMoneyTransferAmount(df.format(dtl.getTotalMoneyTransfer()));
		            rtDtl.setTotalMoneyTransferCount(dtl.getTotalMoneyTrnsCnt().toString());
		            rtDtl.setTotalForeignTransferAmount(df.format(dtl.getTotalForeignTransfer()));
		            rtDtl.setTotalForeignTransferCount(dtl.getTotalForeignTrnsCnt().toString());

					Long countBackdateReceipt = dtl.getBackDateTotalCount()+dtl.getTransGfmisTotalCount()+dtl.getTransForeignTotalCount();
		            rtDtl.setTotalBackDateAmount(df.format(sumBackdateAmount));
		            rtDtl.setTotalBackDateCount(countBackdateReceipt.toString());
		            rtDtl.setTotalOffsetAmount(df.format(dtl.getTotalOffset()));
		            rtDtl.setTotalOffsetCount(dtl.getTotalOffsetCount().toString());
		            rtDtl.setTotalCouponAmount(df.format(dtl.getTotalCoupon()));
		            rtDtl.setTotalCouponCount(dtl.getTotalCouponCount().toString());

					rtDtl.setWt3tredAmount(df.format(dtl.getTotalWt3tred()));
					rtDtl.setWt3tredCount("");
					rtDtl.setWt69treAmount(df.format(dtl.getTotalWt69tre()));
					rtDtl.setWt69treCount("");
					rtDtl.setWt69bisAmount(df.format(dtl.getTotalWt69bis()));
					rtDtl.setWt69bisCount("");

		            rtDtl.setCancelReceiptAmount(df.format(dtl.getTotalCancelAmt()));
		            rtDtl.setCancelReceiptCount(dtl.getTotalCancelCnt().toString());

					rtDtl.setTotalDiscount(df.format(dtl.getTotalDiscount()));
					rtDtl.setBackDateStatus(dtl.getBackDateStatus());

					rtDtl.setReceiveWhFullCount(dtl.getReceiveWhFullCount().toString());
					rtDtl.setCancelReceiveWhFullCount(dtl.getCancelReceiveWhFullCount().toString());
					rtDtl.setReceiveWhAbvrCount(dtl.getReceiveWhAbvrCount().toString());
					rtDtl.setCancelReceiveWhAbvrCount(dtl.getCancelReceiveWhAbvrCount().toString());
					rtDtl.setWhOnlyFullCount(dtl.getWhOnlyFullCount().toString());
					rtDtl.setCancelWhOnlyFullCount(dtl.getCancelWhOnlyFullCount().toString());
					rtDtl.setReceiveOnlyCount(dtl.getReceiveOnlyCount().toString());
					rtDtl.setCancelReceiveOnlyCount(dtl.getCancelReceiveOnlyCount().toString());

					rtDtl.setReceiveWhFullAmount(dtl.getReceiveWhFullAmount().toString());
					rtDtl.setCancelReceiveWhFullAmount(dtl.getCancelReceiveWhFullAmount().toString());
					rtDtl.setReceiveWhAbvrAmount(dtl.getReceiveWhAbvrAmount().toString());
					rtDtl.setCancelReceiveWhAbvrAmount(dtl.getCancelReceiveWhAbvrAmount().toString());
					rtDtl.setWhOnlyFullAmount(dtl.getWhOnlyFullAmount().toString());
					rtDtl.setCancelWhOnlyFullAmount(dtl.getCancelWhOnlyFullAmount().toString());
					rtDtl.setReceiveOnlyAmount(dtl.getReceiveOnlyAmount().toString());
					rtDtl.setCancelReceiveOnlyAmount(dtl.getCancelReceiveOnlyAmount().toString());

		            rtDayEndClosingTos.add(rtDtl);
		        }
		        return rtDayEndClosingTos;
		    }

}

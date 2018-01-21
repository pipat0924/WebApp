package th.net.cat.epis.controller.payment;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.trimToEmpty;
import static org.apache.commons.lang.math.NumberUtils.toInt;
import static th.net.cat.epis.util.AppConstants.ADVANCE_PAYMENT;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_3TREDECIM;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_69BIS;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_69TRE;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_TBOSS;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_WRITEOFF;
import static th.net.cat.epis.util.AppConstants.INVOICE_OTHER_PAYMENT;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BANKTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BILLEXCHANGE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CANCEL;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CANCELTAXINVOICE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CASH;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CHEQUE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CREDITCARD;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_FOREIGNBANK;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_FOREIGNTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_MONEYORDER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_OTHER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_RECEIPT;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_RECEIPTTAXINVOICE;
import static th.net.cat.epis.util.AppConstants.TOPUP_FIELD_AUTH_TOKEN;
import static th.net.cat.epis.util.AppConstants.TOPUP_FIELD_REF_TRANSID;
import static th.net.cat.epis.util.AppConstants.TOPUP_FIELD_SERVICE_KEY;
import static th.net.cat.epis.util.AppConstants.TOPUP_FIELD_STATUS_CODE;
import static th.net.cat.epis.util.AppConstants.TOPUP_STATUS_SUCCESS;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.xml.sax.SAXException;

import com.google.common.base.Function;

import jxl.write.DateFormat;
import th.net.cat.billing.entity.PrintingInvDisplay;
import th.net.cat.billing.repo.PrintingInvDisplayRepository;
import th.net.cat.crm.dto.MasterDataDTO;
import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.BillService;
import th.net.cat.crm.entity.CustomerService;
import th.net.cat.crm.entity.Lookup;
import th.net.cat.crm.repo.BillProfileRepository;
import th.net.cat.crm.repo.CustomerGroupRepository;
import th.net.cat.crm.repo.CustomerProfileRepository;
import th.net.cat.crm.repo.CustomerSegmentRepository;
import th.net.cat.crm.repo.CustomerServiceRepository;
import th.net.cat.crm.repo.LookupRepository;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.dto.BillingInfo;
import th.net.cat.epis.dto.CancelPaymentDTO;
import th.net.cat.epis.dto.CancelPaymentResultDTO;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.CreditLimitTrans;
import th.net.cat.epis.dto.CustomerDTO;
import th.net.cat.epis.dto.CustomerInfo;
import th.net.cat.epis.dto.CustomerInfoDTO;
import th.net.cat.epis.dto.EndOfDayDTO;
import th.net.cat.epis.dto.ExchangeRateDTO;
import th.net.cat.epis.dto.HistoryPaymentDTO;
import th.net.cat.epis.dto.HistoryPaymentDetail;
import th.net.cat.epis.dto.InvoiceDTO;
import th.net.cat.epis.dto.InvoiceExistence;
import th.net.cat.epis.dto.InvoiceExistenceDTO;
import th.net.cat.epis.dto.InvoiceLock;
import th.net.cat.epis.dto.InvoiceLockDTO;
import th.net.cat.epis.dto.InvoiceProductDTO;
import th.net.cat.epis.dto.PaymentHistory;
import th.net.cat.epis.dto.PaymentHistoryDTO;
import th.net.cat.epis.dto.ProvideCustomerService;
import th.net.cat.epis.dto.ProvideCustomerServiceDTO;
import th.net.cat.epis.dto.ReportPayment;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.dto.Subscription;
import th.net.cat.epis.dto.SubscriptionDTO;
import th.net.cat.epis.dto.TopupCustomerDTO;
import th.net.cat.epis.entity.CreditLimitTransEntity;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.InvoiceVatDetail;
import th.net.cat.epis.entity.MasChangeRate;
import th.net.cat.epis.entity.Method;
import th.net.cat.epis.entity.MasterData;
import th.net.cat.epis.entity.PaymentSummary;
import th.net.cat.epis.entity.PromotionMappingEntity;
import th.net.cat.epis.entity.PromotionReceiptMappingEntity;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.ReceiptEgpMappingEntity;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Session;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.epis.repo.CreditLimitRepository;
import th.net.cat.epis.repo.CreditLimitTransRepository;
import th.net.cat.epis.repo.DeductionRepository;
import th.net.cat.epis.repo.EnumRepository;
import th.net.cat.epis.repo.ExchangeRateRepository;
import th.net.cat.epis.repo.InvoiceLockRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.MasterDataRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.PayMethodRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.PromotionBillingMappingRepository;
import th.net.cat.epis.repo.PromotionMappingRepository;
import th.net.cat.epis.repo.ReceiptEgpMappingRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.ServiceRepository;
import th.net.cat.epis.service.DWService;
import th.net.cat.epis.service.ErpService;
import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.service.TopupService;
import th.net.cat.epis.service.UserService;
import th.net.cat.epis.service.bouncecheqeue.BounceChequeService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.InvoiceHeaderBO;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.InvoiceVatDetailBO;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderRequest;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderResponse;
import th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoRequest;
import th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse;
import th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusRequest;
import th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse;
import th.net.cat.epis.ws.f05_retrieveservicestatus.ServiceStatusBO;
import th.net.cat.epis.ws.f06_reversepayment.ReversePaymentRequest;
import th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse;
import th.net.cat.epis.ws.f08_writeoffinquirypos.RetrieveWriteOffInvoiceRequest;
import th.net.cat.epis.ws.f08_writeoffinquirypos.RetrieveWriteOffInvoiceResponse;
import th.net.cat.epis.ws.f08_writeoffinquirypos.WriteOffInvoiceBO;
import th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffRequest;
import th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse;
import th.net.cat.epis.ws.f13_retrievepaymenthistory.PaymentHistoryBO;
import th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryRequest;
import th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse;
import th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrRequest;
import th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrResponse;
import th.net.cat.epis.ws.f20_retrievesubscrbyinv.SubscrNoBO;
@Controller
@SessionAttributes(value = { "service_key", "auth_token" })
public class PaymentController {
	private static Logger logger = Logger.getLogger(PaymentController.class);
	@Autowired
	PaymentService paymentService;
	@Autowired
	TopupService topupService;
	@Autowired
	ErpService erpService;
	@Autowired
	UserService userService;
	@Autowired
	OfficerRepository officerRepo;
	@Autowired
	BillProfileRepository billRepo;
	@Autowired
	CustomerProfileRepository customerRepo;
	@Autowired
	CustomerSegmentRepository customerSegmentRepo;
	@Autowired
	CustomerGroupRepository customerGroupRepo;
	@Autowired
	ReceiptRepository receiptRepo;
	@Autowired
	PaymentRepository paymentRepo;
	@Autowired
	PayMethodRepository payMethodRepo;
	@Autowired
	DeductionRepository deductRepo;
	@Autowired
	EnumRepository enumRepo;
	@Autowired
	InvoiceLockRepository invoiceLockRepository;
	@Autowired
	ExchangeRateRepository exchangeRateRepo;
	@Autowired
	PromotionMappingRepository promotionMappingRepository;
	@Autowired
	PromotionBillingMappingRepository promotionBillingMappingRepository;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F01RetrieveInvoiceHeaderService _f01RetrieveInvoiceHeaderService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F02RetrieveInvoiceAccountCodeService _f02RetrieveInvoiceAccountCodeService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F03RetrieveInvoiceChargesService _f03RetrieveInvoiceChargesService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F05RetrieveServiceStatusService _f05RetrieveServiceStatusService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F06ReversePaymentService _f06ReversePaymentService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F14RetrievePaymentService _f14RetrievePaymentService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F08RetrieveWriteOffInvoiceService _f08RetrieveWriteOffInvoiceService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F11ReverseWriteOffService _f11ReverseWriteOffService;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F13RetrievePaymentHistory _f13RetrievePaymentHistory;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F20RetrieveSubscrService _f20RetrieveSubscrService;
	@Autowired
	CreditLimitRepository creditLimitRepository;
	@Autowired
	InvoiceRepository invoiceRepo;
	@Autowired
	CreditLimitTransRepository creditLimitTransRepository;
	@Autowired
	LookupRepository lookupRepository;
	@Autowired
	PrintingInvDisplayRepository printingInvDisplayRepository;
	@Autowired
	CustomerServiceRepository customerServiceRepository;
	@Autowired
	ReceiptEgpMappingRepository egpMapRepo;
	
	@Autowired
	MasterDataRepository masterDataRepository;
	@Autowired
	DWService  dwService;

	@Autowired
	th.co.softpos.ws.mg.s001.S001MGInqGiftvoucher s001MGInqGiftvoucher;
	
	@Autowired ServiceRepository serviceRepo;
	
	

	@RequestMapping(value = "checkAuthorize.json", method = RequestMethod.POST)
	public void checkAuthorizeJSON(ModelMap modelMap, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		boolean sup = userService.selectCheckAuthenSuperwiser(username, password);
		String statusCode = "10";
		if (sup) {
			List<Map<String, Object>> supperwises = userService
					.selectSuperwiseser(SecurityContextHolder.getContext().getAuthentication().getName());
			for (Map<String, Object> supperwise : supperwises) {
				if (username.equals(supperwise.get("SUPERWISER") + "")) {
					statusCode = "0";
				}

			}

		}

		modelMap.addAttribute("statusCode", statusCode);
	}

	@RequestMapping(value = "findCustomerSegmentList.json", method = RequestMethod.GET)
	public void findCustomerSegmentListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", customerSegmentRepo.findAllOrderByOrderAsc());
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findAccountCategoryList.json", method = RequestMethod.GET)
	public void findAccountCategoryListJSON(ModelMap modelMap) {
		List<Lookup> lookupList = lookupRepository
				.findByLookupCategoryNameUpOrderByListorderAsc(AppConstants.ACCOUNT_CATGORY_LOOKUP_NAME);
		List<MasterDataDTO> dataList = new ArrayList<MasterDataDTO>();
		if (lookupList != null && lookupList.size() > 0) {
			for (Lookup lookup : lookupList) {
				MasterDataDTO dto = new MasterDataDTO();
				dto.setId(Long.valueOf(lookup.getCodeInt()));
				dto.setKey(lookup.getCodeString());
				dto.setValue(lookup.getTextString());
				dataList.add(dto);
			}
		}
		modelMap.addAttribute("data", dataList);
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findCustomerGroupList.json", method = RequestMethod.GET)
	public void findCustomerGroupListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", customerGroupRepo.findAllOrderByOrderAsc());
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findServiceCategoryList.json", method = RequestMethod.GET)
	public void findServiceCategoryListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("payothers.service.category"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findServiceReasonCategoryList.json", method = RequestMethod.GET)
	public void findServiceReasonCategoryListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("reason.service.category"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findServiceReasonByCategoryList.json", method = RequestMethod.GET)
	public void findServiceReasonByCategoryList(ModelMap modelMap, @RequestParam("category") String category) {
		modelMap.addAttribute("data", enumRepo.findByCategory(category));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findServiceNameList.json", method = RequestMethod.GET)
	public void findServiceNameListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("payothers.service.name"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findServiceDepartmentList.json", method = RequestMethod.GET)
	public void findServiceDepartmentListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByBranchCentralAndBusinessPlace("branch.central", "business.place"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findServiceUnitList.json", method = RequestMethod.GET)
	public void findServiceUnitListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("payothers.service.unit"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findBankNameList.json", method = RequestMethod.GET)
	public void findBankNameListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("bank.name"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findSourceType.json", method = RequestMethod.GET)
	public void findSourceTypeJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("source.service.name"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping("/revert_payment.html")
	public String initRevert_PaymentHandler(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		return "page-8-1";
	}

	@RequestMapping("/revert_payment_1.html")
	public String initRevert_Payment_1Handler(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		return "page-8-2-1";
	}

	@RequestMapping("/revert_payment_2.html")
	public String initRevert_Payment_2Handler(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		return "page-8-2-2";
	}

	@RequestMapping("/payment_change.html")
	public String initPayment_ChangeHandler(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		return "page-7-1";
	}

	@ResponseBody
	@RequestMapping(value = "findCustomerList.json", method = RequestMethod.GET)
	public CustomerDTO findCustomerListJSON(@RequestParam("custNo") String custNo) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setData(customerRepo.findByNoStartingWith(custNo, new PageRequest(0, 20)).getContent());
		return customerDTO;
	}

	@ResponseBody
	@RequestMapping(value = "findCurrentExchangeRate.json", method = RequestMethod.GET)
	public ExchangeRateDTO findCurrentExchangeRateJSON(
			@RequestParam(value = "dts", required = true) String dateUsedStart,
			@RequestParam(value = "dte", required = true) String dateUsedEnd,
			@RequestParam(value = "code", required = true) String rateCode) {

		ExchangeRateDTO dto = new ExchangeRateDTO();
		if (isBlank(dateUsedStart) || isBlank(dateUsedEnd) || isBlank(rateCode)) {
			dto.setStatusCode("10");
			dto.getWarningList().add(new AlertMessage("10", "Parameters are not passed correctly. (3 parameters)"));
			return dto;
		}

		// Prepare rate code
		if (rateCode != null && rateCode.indexOf("_") >= 0) {
			String[] rateCodeArr = rateCode.split("_");
			if (rateCodeArr != null && rateCodeArr.length > 0) {
				rateCode = rateCodeArr[rateCodeArr.length - 1];
			}
		}
		String queryExchgRateString = " SELECT mcr.* , to_char(mcr.DATEUSED, 'DD/MM/YYYY') as DATEUSED_SHOW  FROM MASCHANGERATE mcr WHERE mcr.RATECODE like '"
				+ rateCode + "%' " + "order by DATEUSED  DESC FETCH first 1 rows only ";
		// String queryExchgRateString = "SELECT * FROM MASCHANGERATE mcr WHERE
		// mcr.RATECODE like '"+rateCode+"%' and rownum = 1 ";
		// String queryString = "SELECT * FROM MASCHANGERATE mcr WHERE
		// mcr.RATECODE = '"+rateCode+"' AND ( mcr.DATEUSED BETWEEN
		// TO_DATE('"+dateUsedStart+"', 'YYYY-MM-DD') AND
		// TO_DATE('"+dateUsedEnd+"', 'YYYY-MM-DD') ) AND rownum < 2 ";
		// String queryExchgRateDateString = queryExchgRateString+" AND (
		// mcr.DATEUSED BETWEEN TO_DATE('"+dateUsedStart+"', 'YYYY-MM-DD') AND
		// TO_DATE('"+dateUsedEnd+"', 'YYYY-MM-DD') )";
		final MasChangeRate exchangeRate = new MasChangeRate();
		/*
		 * episJdbcTemplate.query(queryString, new RowCallbackHandler(){
		 * 
		 * @Override public void processRow(ResultSet row) throws SQLException {
		 * exchangeRate.setId(row.getLong("MASCHANGERATEID")); } });
		 */

		findExchRate(queryExchgRateString, exchangeRate);
		/*
		 * findExchRate(queryExchgRateDateString, exchangeRate);
		 * if(exchangeRate.getId() == null){ findExchRate(queryExchgRateString,
		 * exchangeRate); }
		 */
		// dto.addData((exchangeRate.getId() != null)?
		// exchangeRateRepo.findById(exchangeRate.getId()) : exchangeRate);
		dto.addData(exchangeRate);
		return dto;
	}

	// W3P get symbol by currencyCode 23-Mar-2017
	@ResponseBody
	@RequestMapping(value = "getSymbolByCurrencyCode.json", method = RequestMethod.GET)
	public ExchangeRateDTO getSymbolByCurrencyCode(@RequestParam(value = "code", required = true) String currencyCode) {

		ExchangeRateDTO dto = new ExchangeRateDTO();

		String queryStr = " SELECT * FROM MASCHANGERATE WHERE MASCHANGERATEID = '" + currencyCode + "' ";

		final MasChangeRate macChgList = new MasChangeRate();

		episJdbcTemplate.query(queryStr, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet row) throws SQLException {
				macChgList.setId(row.getLong("MASCHANGERATEID"));
				macChgList.setRateCode(row.getString("RATECODE"));
				macChgList.setMessage(row.getString("MESSAGE"));
				macChgList.setDescription(row.getString("DESCRIPTION"));
				macChgList.setRateUnit(row.getBigDecimal("RATEUNIT"));
				macChgList.setCurrencySymbol(row.getString("CURRENCYSYMBOL"));
				macChgList.setCountry(row.getString("COUNTRY"));
			}
		});

		dto.addData(macChgList);
		return dto;
	}

	private void findExchRate(String queryString, final MasChangeRate exchangeRate) {
		episJdbcTemplate.query(queryString, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet row) throws SQLException {
				exchangeRate.setId(row.getLong("MASCHANGERATEID"));
				exchangeRate.setRateCode(row.getString("RATECODE"));
				exchangeRate.setMessage(row.getString("MESSAGE"));
				exchangeRate.setDescription(row.getString("DESCRIPTION"));
				exchangeRate.setDateUsedShow(row.getString("DATEUSED_SHOW"));
				exchangeRate.setRateUnit(row.getBigDecimal("RATEUNIT"));
				exchangeRate.setCurrencySymbol(row.getString("CURRENCYSYMBOL"));
				exchangeRate.setCountry(row.getString("COUNTRY"));
			}
		});
	}

	@RequestMapping(value = "findInvoiceByNo.json", method = RequestMethod.GET)
	public void findInvoiceByNoJSON(ModelMap modelMap, @RequestParam("invNo") String invNo) throws Exception {

		RetrieveInvoiceHeaderRequest request = new RetrieveInvoiceHeaderRequest();
		request.setBillRefNo(invNo);
		request.setHasBalance(true);
		request.setTransactionLog(_f01RetrieveInvoiceHeaderService.buildTransactionLogCBO());
		RetrieveInvoiceHeaderResponse response = _f01RetrieveInvoiceHeaderService.callInterface(request);
		if (_f01RetrieveInvoiceHeaderService.isCalledSuccesful("0", response)) {
			List<InvoiceHeaderBO> invoiceHeaderList = response.getInvoiceHeaderList();
			int invoiceHeaderListSize = invoiceHeaderList != null ? invoiceHeaderList.size() : 0;
			List<Object> jsonArray = newLinkedList();
			for (int i = 0, m = invoiceHeaderListSize; i < m; i++) {
				InvoiceHeaderBO invoiceHeader = invoiceHeaderList.get(i);
				BillProfile billProfile = billRepo.findByNo(invoiceHeader.getBillingAccountNo());
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("invoiceNo", invoiceHeader.getBillRefNo());
				result.put("billingNo", billProfile != null ? billProfile.getNo() : null);
				result.put("billingName", billProfile != null ? billProfile.getCustomerAccountName() : null);
				result.put("customerType", billProfile != null ? billProfile.getType() : null);
				result.put("billGroup", billProfile != null ? StringUtils.trimToEmpty(billProfile.getBillGroup()) + " "
						+ StringUtils.trimToEmpty(billProfile.getBillGroupFull()) : null);
				jsonArray.add(result);
			}
			modelMap.addAttribute("data", jsonArray);
		} else {
			modelMap.addAttribute("errorList",
					new ArrayList<AlertMessage>().add(_f01RetrieveInvoiceHeaderService.buildErrorMessage(response)));
		}
		modelMap.addAttribute("statusCode", response.getTransactionLog().getDestinationReturnCode());
	}

	@ResponseBody
	@RequestMapping(value = "findWriteOffByNo.json", method = RequestMethod.GET)
	public void findWriteOffByNoJSON(ModelMap modelMap, @RequestParam("invNo") String invNo) throws Exception {

		RetrieveWriteOffInvoiceRequest request = new RetrieveWriteOffInvoiceRequest();
		request.setBillRefNo(invNo);
		request.setHasBalance(true);
		request.setTransactionLog(_f08RetrieveWriteOffInvoiceService.buildTransactionLogCBO());
		RetrieveWriteOffInvoiceResponse response = _f08RetrieveWriteOffInvoiceService.callInterface(request);

		if (_f08RetrieveWriteOffInvoiceService.isCalledSuccesful("0", response)) {
			if (response.getWriteOffInvoiceList() != null & response.getWriteOffInvoiceList().size() > 0) {
				List<Object> jsonArray = newLinkedList();
				for (WriteOffInvoiceBO invoiceList : response.getWriteOffInvoiceList()) {
					BillProfile billProfile = billRepo.findByNo(invoiceList.getBillingAccountNo());
					Map<String, Object> result = new HashMap<String, Object>();
					result.put("invoiceNo", invoiceList.getBillRefNo());
					result.put("billingNo", billProfile != null ? billProfile.getNo() : null);
					result.put("billingName", billProfile != null ? billProfile.getCustomerAccountName() : null);
					result.put("customerType", billProfile != null ? billProfile.getType() : null);
					result.put("billGroup", billProfile != null ? StringUtils.trimToEmpty(billProfile.getBillGroup())
							+ " " + StringUtils.trimToEmpty(billProfile.getBillGroupFull()) : null);
					jsonArray.add(result);
				}
				modelMap.addAttribute("data", jsonArray);
			} else {
				modelMap.addAttribute("errorList", new ArrayList<AlertMessage>()
						.add(_f08RetrieveWriteOffInvoiceService.buildErrorMessage(response)));
			}
			modelMap.addAttribute("statusCode", response.getTransactionLog().getDestinationReturnCode());
		}
	}

	@RequestMapping(value = "findInvoiceBillingWriteOffByNo.json", method = RequestMethod.GET)
	public void findInvoiceBillingWriteOffByNoJSON(ModelMap modelMap, @RequestParam("invNo") String invNo)
			throws Exception {

		List<Object> jsonArray = newLinkedList();

		RetrieveInvoiceHeaderRequest request = new RetrieveInvoiceHeaderRequest();
		invNo = invNo.replaceAll("\\*", "%");
		invNo = invNo.replaceAll("\\?", "_");
		request.setBillRefNo(invNo);
		request.setHasBalance(true);
		request.setTransactionLog(_f01RetrieveInvoiceHeaderService.buildTransactionLogCBO());
		RetrieveInvoiceHeaderResponse response = _f01RetrieveInvoiceHeaderService.callInterface(request);
		if (_f01RetrieveInvoiceHeaderService.isCalledSuccesful("0", response)) {
			List<InvoiceHeaderBO> invoiceHeaderList = response.getInvoiceHeaderList();
			int invoiceHeaderListSize = invoiceHeaderList != null ? invoiceHeaderList.size() : 0;

			for (int i = 0, m = invoiceHeaderListSize; i < m; i++) {
				InvoiceHeaderBO invoiceHeader = invoiceHeaderList.get(i);
				BillProfile billProfile = billRepo.findByNo(invoiceHeader.getBillingAccountNo());
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("invoiceNo", invoiceHeader.getBillRefNo());
				result.put("billingNo", billProfile != null ? billProfile.getNo() : null);
				result.put("billingName", billProfile != null ? billProfile.getCustomerAccountName() : null);
				result.put("customerType", billProfile != null ? billProfile.getType() : null);
				result.put("billGroup", billProfile != null ? StringUtils.trimToEmpty(billProfile.getBillGroup()) + " "
						+ StringUtils.trimToEmpty(billProfile.getBillGroupFull()) : null);
				jsonArray.add(result);
			}

		}
		RetrieveWriteOffInvoiceRequest requestWriteOff = new RetrieveWriteOffInvoiceRequest();
		requestWriteOff.setBillRefNo(invNo);
		requestWriteOff.setHasBalance(true);
		requestWriteOff.setTransactionLog(_f08RetrieveWriteOffInvoiceService.buildTransactionLogCBO());
		RetrieveWriteOffInvoiceResponse responseWriteOff = _f08RetrieveWriteOffInvoiceService
				.callInterface(requestWriteOff);

		if (_f08RetrieveWriteOffInvoiceService.isCalledSuccesful("0", responseWriteOff)) {
			if (responseWriteOff.getWriteOffInvoiceList() != null
					& responseWriteOff.getWriteOffInvoiceList().size() > 0) {

				for (WriteOffInvoiceBO invoiceList : responseWriteOff.getWriteOffInvoiceList()) {
					BillProfile billProfile = billRepo.findByNo(invoiceList.getBillingAccountNo());
					Map<String, Object> result = new HashMap<String, Object>();
					result.put("invoiceNo", invoiceList.getBillRefNo());
					result.put("billingNo", billProfile != null ? billProfile.getNo() : null);
					result.put("billingName", billProfile != null ? billProfile.getCustomerAccountName() : null);
					result.put("customerType", billProfile != null ? billProfile.getType() : null);
					result.put("billGroup", billProfile != null ? StringUtils.trimToEmpty(billProfile.getBillGroup())
							+ " " + StringUtils.trimToEmpty(billProfile.getBillGroupFull()) : null);
					jsonArray.add(result);
				}

			}
		}
		String responseCode = "1";
		if ("0".equals(response.getTransactionLog().getDestinationReturnCode())
				|| "0".equals(responseWriteOff.getTransactionLog().getDestinationReturnCode())) {
			responseCode = "0";
		}
		modelMap.addAttribute("data", jsonArray);
		modelMap.addAttribute("statusCode", responseCode);
	}
	
	@Autowired BounceChequeService bounceChequeService;

	@RequestMapping(value = "advanceSearchByService.json", method = RequestMethod.GET)
	public void advanceSearchByServiceJSON(ModelMap modelMap, @RequestParam("billingGroups") String billingGroupStr,
			@RequestParam("serviceNo") String serviceNo, @RequestParam("isPropertyOne") boolean isPropertyOne,
			@RequestParam("isPropertyTwo") boolean isPropertyTwo) throws Exception {

		List<CustomerService> result = null;

		if (billingGroupStr != null && billingGroupStr.trim().length() > 0) {
			List<String> billingGroups = Arrays.asList(billingGroupStr.split(","));
			if (isPropertyOne == isPropertyTwo) {
				result = customerServiceRepository.findByBillingGroupAndPropertyOneAndPropertyTwo(billingGroups,
						serviceNo);
			} else if (isPropertyOne == true) {
				result = customerServiceRepository.findByBillingGroupAndPropertyOne(billingGroups, serviceNo);
			} else if (isPropertyTwo == true) {
				result = customerServiceRepository.findByBillingGroupAndPropertyTwo(billingGroups, serviceNo);
			}
		} else if (isPropertyOne == isPropertyTwo) {
//			result = customerServiceRepository.findByPropertyOneOrPropertyTwo(serviceNo);
			result = bounceChequeService.findByPropertyOneOrPropertyTwo(serviceNo, "OneTwo");
		} else if (isPropertyOne == true) {
//			result = customerServiceRepository.findByPropertyOne(serviceNo);
			result = bounceChequeService.findByPropertyOneOrPropertyTwo(serviceNo, "One");
		} else if (isPropertyTwo == true) {
//			result = customerServiceRepository.findByPropertyTwo(serviceNo);
			result = bounceChequeService.findByPropertyOneOrPropertyTwo(serviceNo, "Two");
		}

		modelMap.addAttribute("data", result);
		modelMap.addAttribute("statusCode", "0");
	}

	@ResponseBody
	@RequestMapping(value = "findInvoiceNoInPayQueue.json", method = RequestMethod.GET)
	public InvoiceExistenceDTO findInvoiceNoInPayQueueJSON(@RequestParam("list") List<String> invoiceNoList) {

		final InvoiceExistenceDTO dto = new InvoiceExistenceDTO();
		if (invoiceNoList.size() > 0) {
			StringBuilder queryParam = new StringBuilder();
			for (String invNo : invoiceNoList) {
				queryParam.append("'").append(invNo).append("',");
			}
			queryParam.setCharAt(queryParam.length() - 1, ' ');
			episJdbcTemplate.query("SELECT inv.INVOICENO, trn.TRANSACTIONID, trn.TRACKINGID " + "FROM TMPINVOICE inv "
					+ "LEFT JOIN TRSPAYMENTREF trn ON inv.PAYMENTID = trn.PAYMENTID " + "WHERE INVOICENO in ("
					+ queryParam.toString() + ") " + "ORDER BY INVOICEID desc ", new RowCallbackHandler() {
						@Override
						public void processRow(ResultSet row) throws SQLException {
							InvoiceExistence result = new InvoiceExistence();
							result.setInvoiceNo(row.getString("INVOICENO"));
							result.setStatus(row.getString("TRACKINGID"));
							dto.addData(result);
						}
					});
		}
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findInvoiceNoDuplicatePayQueue.json", method = RequestMethod.GET)
	public InvoiceLockDTO findInvoiceNoDuplicatePayQueue(@RequestParam("list") List<String> invoiceNoList) {
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("SecurityContextHolder.getContext().getAuthentication().getName()->" + userid);
		final InvoiceLockDTO dto = new InvoiceLockDTO();
		if (invoiceNoList.size() > 0) {
			StringBuilder queryParam = new StringBuilder();
			for (String invNo : invoiceNoList) {
				queryParam.append("'").append(invNo).append("',");
			}
			queryParam.setCharAt(queryParam.length() - 1, ' ');
			episJdbcTemplate.query("SELECT inv.INVNO, inv.USER_CREATED, inv.CREATED_TIME " + "FROM INV_LOCK inv "
					+ "WHERE INVNO in (" + queryParam.toString() + ") " + " AND USER_CREATED != '" + userid + "' "
					+ "ORDER BY INVNO desc ", new RowCallbackHandler() {
						@Override
						public void processRow(ResultSet row) throws SQLException {
							InvoiceLock result = new InvoiceLock();
							result.setInvNo(row.getString("INVNO"));
							// result.setStatus(row.getString("TRACKINGID"));
							dto.addData(result);
						}
					});
		}
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "manageDuplicatePayQueue.json", method = RequestMethod.POST)
	public int manageDuplicatePayQueue(
			// List<InvoiceLock> invoiceLocks
			@RequestBody List<InvoiceLock> invoiceLocks) throws Exception {
		logger.info("invoiceLocks -->" + invoiceLocks);
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();

		int result = 0;
		for (InvoiceLock invoiceLock : invoiceLocks) {
			th.net.cat.epis.entity.InvoiceLock invLock = new th.net.cat.epis.entity.InvoiceLock();
			BeanUtils.copyProperties(invoiceLock, invLock);
			logger.info("invoiceLock.getMode()-->" + invoiceLock.getMode());
			if (invoiceLock.getMode().equals("ADD")) {
				java.util.Date today = new java.util.Date();
				logger.info("invoiceLock.getInvNo()-->" + invoiceLock.getInvNo());
				invLock.setInvNo(invoiceLock.getInvNo());
				invLock.setUserCreated(userid);
				invLock.setCreatedTime(today);
				if (invoiceLock.getInvNo() != null && invoiceLock.getInvNo().length() > 0)
					invoiceLockRepository.save(invLock);
			} else if (invoiceLock.getMode().equals("REMOVE") && invLock.getInvNo() != null) {
				logger.info("invoiceLock.getInvNo()-->" + invLock.getInvNo());
				invoiceLockRepository.delete(invLock);
			}
			// result=episJdbcTemplate.update(sql, params, types);
		}
		/*
		 * String insertSql="insert into INV_LOCK values(?,?,?)"; String
		 * updateSql="update INV_LOCK set sname=?,age=?, where sno=?"; String
		 * deleteSql="delete INV_LOCK where INVNO=?";
		 */
		// jdbcTemplate.update(insertSql,new
		// Object[]{st.getSno(),st.getSname(),st.getAge()})
		// save or delete
		// Object invoice [ invoiceNo , user pay in , datetime ]

		return result;
	}

	@ResponseBody
	@RequestMapping("findService.json") // replace Convert_json_temp_crm2.json
	public ProvideCustomerServiceDTO findServiceJSON(ProvideCustomerService input) {
		ProvideCustomerService pcs = new ProvideCustomerService();
		pcs.setServiceType(input.getServiceType());
		pcs.setServiceNo(input.getServiceNo());

		// TODO:

		ProvideCustomerServiceDTO dto = new ProvideCustomerServiceDTO();
		dto.addData(pcs);
		return dto;
	}

	@ResponseBody
	@RequestMapping("findCustomer.json") // replace Convert_json_temp_crm.json
	public CustomerInfoDTO findCustomerJSON(CustomerInfo input) {
		CustomerInfo custInfo = new CustomerInfo();
		custInfo.setCustomerNo(input.getCustomerNo());

		// TODO:

		CustomerInfoDTO dto = new CustomerInfoDTO();
		dto.addData(custInfo);
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findSubscription.json", method = RequestMethod.GET)
	public SubscriptionDTO findSubscriptionJSON(@RequestParam("no") String billingAccountNo) throws Exception {
		SubscriptionDTO dto = new SubscriptionDTO();
		// call F05 RetreiveServiceStatus
		RetrieveServiceStatusRequest request = new RetrieveServiceStatusRequest();
		request.setBillingAccountNo(billingAccountNo);
		request.setTransactionLog(_f05RetrieveServiceStatusService.buildTransactionLogCBO());
		RetrieveServiceStatusResponse response = _f05RetrieveServiceStatusService.callInterface(request);

		if (_f05RetrieveServiceStatusService.isCalledSuccesful("0", response)) {

			for (ServiceStatusBO serviceStatus : response.getServiceStatusList()) {
				dto.addData(new Subscription(serviceStatus.getServiceType(), serviceStatus.getSubscrNo(),
						serviceStatus.getStatusName()));
			}
		} else {
			dto.getWarningList().add(_f05RetrieveServiceStatusService.buildErrorMessage(response));
		}
		return dto;
	}

	@Resource(name = "sapFileChannel")
	org.springframework.messaging.MessageChannel inputChannel;

	@ResponseBody
	@RequestMapping(value = "findInvoiceList.json", method = RequestMethod.GET)
	public InvoiceDTO findInvoiceListJSON(@RequestParam("no") String billingAccountNo,
			@RequestParam(name = "hasBalance", required = false) boolean hasBalance,
			@RequestParam(name = "invNo", required = false) String invoiceNo) throws Exception {
		InvoiceDTO dto = new InvoiceDTO();

		RetrieveInvoiceHeaderRequest request = new RetrieveInvoiceHeaderRequest();
		request.setBillingAccountNo(billingAccountNo);
		request.setHasBalance(hasBalance);
		request.setTransactionLog(_f01RetrieveInvoiceHeaderService.buildTransactionLogCBO());
		RetrieveInvoiceHeaderResponse response = _f01RetrieveInvoiceHeaderService.callInterface(request);
		Date now = new Date();
		
		
		BigDecimal vatRate = new BigDecimal(masterDataRepository.findByKey(AppConstants.VAT_RATE).get(0).getValue());
		if (_f01RetrieveInvoiceHeaderService.isCalledSuccesful("0", response)) {
			if (response.getInvoiceHeaderList() != null & response.getInvoiceHeaderList().size() > 0) {
				BigDecimal ONE_HUNDRED = new BigDecimal("100");
				for (InvoiceHeaderBO invoiceList : response.getInvoiceHeaderList()) {
					RetrieveInvoiceChargeInfoRequest chargeRequest = new RetrieveInvoiceChargeInfoRequest();
					chargeRequest.setBillRefNo(invoiceList.getBillRefNo());
					chargeRequest.setTransactionLog(_f03RetrieveInvoiceChargesService.buildTransactionLogCBO());
					RetrieveInvoiceChargeInfoResponse chargeResponse = _f03RetrieveInvoiceChargesService
							.callInterface(chargeRequest);
					Date duedate = invoiceList.getDueDate() != null
							? invoiceList.getDueDate().toGregorianCalendar().getTime() : null;
					th.net.cat.epis.dto.Invoice invoice = new th.net.cat.epis.dto.Invoice();
					invoice.setCustomerNo(invoiceList.getBillingAccountNo());
					invoice.setBillNo(String.valueOf(invoiceList.getBillRefNo()));
					invoice.setAccountNo(String.valueOf(invoiceList.getAccountNo()));
					invoice.setAmountBeforeTax(invoiceList.getAmount() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getAmount()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
					invoice.setTaxAmount(invoiceList.getVat() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getVat()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
					
					BigDecimal vatData = vatRate.divide(new BigDecimal(100));
					invoice.setTaxAmount(invoice.getAmountBeforeTax() == null ? BigDecimal.ZERO : invoice.getAmountBeforeTax().multiply(vatData).setScale(2, RoundingMode.HALF_UP) ) ;
					/*invoice.setAmountAfterTax(invoiceList.getTotal() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getTotal()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));*/
					invoice.setAmountAfterTax(invoice.getAmountBeforeTax() == null ? BigDecimal.ZERO : invoice.getAmountBeforeTax().add(invoice.getTaxAmount()) ) ;
					/*invoice.setBalanceDue(invoiceList.getBalanceDue() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getBalanceDue()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));*/
					invoice.setBalanceDue(invoiceList.getBalanceDue() == null ? BigDecimal.ZERO : 
						(new BigDecimal(invoiceList.getBalanceDue()).divide( new BigDecimal(100))))  ;
						
					
					invoice.setTotalAdj(invoiceList.getTotalAdj() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getTotalAdj()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP)); // invoiceList.getTotalPaid()
					invoice.setTotalPaid(invoiceList.getTotalPaid() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getTotalPaid()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));
					invoice.setIssueDate(invoiceList.getIssueDate() != null
							? invoiceList.getIssueDate().toGregorianCalendar().getTime() : null);
					invoice.setDueDate(duedate);
					invoice.setChargeCycleFromDate(invoiceList.getChargeFromDate() != null
							? invoiceList.getChargeFromDate().toGregorianCalendar().getTime() : null);
					invoice.setChargeCycleToDate(invoiceList.getChargeToDate() != null
							? invoiceList.getChargeToDate().toGregorianCalendar().getTime() : null);
					invoice.setCurrencycode(String.valueOf(invoiceList.getCurrencyCode()));
					//invoice.setTaxRate(String.valueOf(invoiceList.getTaxRate()));
					invoice.setTaxRate(String.valueOf(vatRate));
					invoice.setTaxtypecode(invoiceList.getTaxTypeCode());
					invoice.setClosedate(invoiceList.getCloseDate() != null
							? invoiceList.getCloseDate().toGregorianCalendar().getTime() : null);
					invoice.setStatus("Active");
					invoice.setRentalCharge(chargeResponse.getRentalCharge() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(chargeResponse.getRentalCharge()));
					invoice.setUsageCharge(chargeResponse.getUsageCharge() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(chargeResponse.getUsageCharge()));
					invoice.setDiscount(invoiceList.getVolumnDiscount() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getVolumnDiscount()));
					invoice.setInvoiceDisplay(invoiceList.getInvoiceDisplay());// by
																				// NSD
																				// 24-03-2017
					if (duedate != null)
						invoice.setAging(new BigDecimal(th.net.cat.epis.util.AppUtil.calculateAging(duedate, now)));
					else
						invoice.setAging(new BigDecimal(0));

					List<String> subNoList = new ArrayList<String>(0);
					RetrieveSubscrRequest retrieveSubscRequest = new RetrieveSubscrRequest();

					retrieveSubscRequest.setBillRefNo(String.valueOf(invoiceList.getBillRefNo()));
					retrieveSubscRequest.setStatus("Active");
					retrieveSubscRequest.setTransactionLog(_f20RetrieveSubscrService.buildTransactionLogCBO());
					RetrieveSubscrResponse retrieveSubscReponse = _f20RetrieveSubscrService
							.callInterface(retrieveSubscRequest);
					if (_f20RetrieveSubscrService.isCalledSuccesful("0", retrieveSubscReponse)) {
						if (retrieveSubscReponse.getSubscrNoList() != null
								&& retrieveSubscReponse.getSubscrNoList().size() > 0) {
							int subNoListSize = retrieveSubscReponse.getSubscrNoList().size();
							subNoList = new ArrayList<String>(subNoListSize);
							for (SubscrNoBO subscrNoBO : retrieveSubscReponse.getSubscrNoList()) {
								subNoList.add(subscrNoBO.getSubscription());
							}

							// java.util.stream.Collectors
						}
					} else {

					}
					List<InvoiceVatDetail> invoiceVatDetailList = new ArrayList<InvoiceVatDetail>();
					if (!CollectionUtils.isEmpty(invoiceList.getInvoiceVatDetailList())) {
						for (InvoiceVatDetailBO dtl : invoiceList.getInvoiceVatDetailList()) {
							InvoiceVatDetail dtl1 = new InvoiceVatDetail();
							dtl1.setInvoiceNo(String.valueOf(dtl.getBillRefNo()));
							dtl1.setAccountNo(String.valueOf(dtl.getAccountNo()));
							dtl1.setAmount(dtl.getAmount() == null ? BigDecimal.ZERO
									: BigDecimal.valueOf(dtl.getAmount()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
							dtl1.setVat(dtl.getVat() == null ? BigDecimal.ZERO
									: BigDecimal.valueOf(dtl.getVat()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP));
							dtl1.setTaxTypeCode(dtl.getTaxTypeCode());
							invoiceVatDetailList.add(dtl1);
						}
					}
					invoice.setInvoiceVatDetails(invoiceVatDetailList);
					invoice.setSubNoList(subNoList);
					// find cheque Failed
					int countTrsInvoiceNoVat = paymentService.countTrsInvoiceNoVat(invoice.getCustomerNo(),
							invoice.getBillNo(), 0);
					invoice.setChequeFailed(String.valueOf(countTrsInvoiceNoVat));
					dto.addData(invoice);
				}
			}
		} else {
			dto.getWarningList().add(_f01RetrieveInvoiceHeaderService.buildErrorMessage(response));
		}

		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findWriteOffInvoiceList.json", method = RequestMethod.GET)
	public InvoiceDTO findWriteOffInvoiceListJSON(@RequestParam("no") String billingAccountNo,
			@RequestParam(name = "invNo", required = false) String invoiceNo) throws Exception {
		InvoiceDTO dto = new InvoiceDTO();

		RetrieveWriteOffInvoiceRequest request = new RetrieveWriteOffInvoiceRequest();
		request.setBillingAccountNo(billingAccountNo);
		request.setHasBalance(false);
		request.setTransactionLog(_f08RetrieveWriteOffInvoiceService.buildTransactionLogCBO());
		RetrieveWriteOffInvoiceResponse response = _f08RetrieveWriteOffInvoiceService.callInterface(request);
		Date now = new Date();
		if (_f08RetrieveWriteOffInvoiceService.isCalledSuccesful("0", response)) {
			if (response.getWriteOffInvoiceList() != null & response.getWriteOffInvoiceList().size() > 0) {
				BigDecimal ONE_HUNDRED = new BigDecimal("100");
				for (WriteOffInvoiceBO invoiceList : response.getWriteOffInvoiceList()) {
					RetrieveInvoiceChargeInfoRequest chargeRequest = new RetrieveInvoiceChargeInfoRequest();
					Date duedate = invoiceList.getWriteOffDate() != null
							? invoiceList.getWriteOffDate().toGregorianCalendar().getTime() : null;
					chargeRequest.setBillRefNo(invoiceList.getBillRefNo());
					chargeRequest.setTransactionLog(_f03RetrieveInvoiceChargesService.buildTransactionLogCBO());
					RetrieveInvoiceChargeInfoResponse chargeResponse = _f03RetrieveInvoiceChargesService
							.callInterface(chargeRequest);
					th.net.cat.epis.dto.Invoice invoice = new th.net.cat.epis.dto.Invoice();
					invoice.setCustomerNo(invoiceList.getBillingAccountNo());
					invoice.setBillNo(String.valueOf(invoiceList.getBillRefNo()));
					invoice.setAccountNo(String.valueOf(invoiceList.getAccountNo()));
					invoice.setAmountBeforeTax(invoiceList.getNewCharges() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getNewCharges()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));
					invoice.setTaxAmount(invoiceList.getVatAmount() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getVatAmount()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));
					invoice.setAmountAfterTax(invoiceList.getBalanceDue() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getBalanceDue()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));
					invoice.setBalanceDue(invoiceList.getBalanceDue() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getBalanceDue()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));
					invoice.setTotalAdj(invoiceList.getTotalAdj() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getTotalAdj()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));
					invoice.setTotalPaid(invoiceList.getTotalPaid() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getTotalPaid()).divide(ONE_HUNDRED, 2,
									RoundingMode.HALF_UP));
					invoice.setIssueDate(invoiceList.getIssueDate() != null
							? invoiceList.getIssueDate().toGregorianCalendar().getTime() : null);
					invoice.setDueDate(duedate);
					invoice.setChargeCycleFromDate(invoiceList.getPpddDate() != null
							? invoiceList.getPpddDate().toGregorianCalendar().getTime() : null);
					invoice.setChargeCycleToDate(invoiceList.getPpddDate() != null
							? invoiceList.getPpddDate().toGregorianCalendar().getTime() : null);
					invoice.setCurrencycode(String.valueOf(invoiceList.getCurrentCode()));
					invoice.setTaxRate(String.valueOf(invoiceList.getVatRate()));
					invoice.setTaxtypecode(null);
					invoice.setClosedate(null);
					invoice.setStatus("WriteOff");
					invoice.setRentalCharge(chargeResponse.getRentalCharge() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(chargeResponse.getRentalCharge()));
					invoice.setUsageCharge(chargeResponse.getUsageCharge() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(chargeResponse.getUsageCharge()));
					invoice.setDiscount(invoiceList.getVolumnDiscount() == null ? BigDecimal.ZERO
							: BigDecimal.valueOf(invoiceList.getVolumnDiscount()));
					if (duedate != null)
						invoice.setAging(new BigDecimal(th.net.cat.epis.util.AppUtil.calculateAging(duedate, now)));
					else
						invoice.setAging(new BigDecimal(0));

					List<String> subNoList = new ArrayList<String>(0);
					RetrieveSubscrRequest retrieveSubscRequest = new RetrieveSubscrRequest();

					retrieveSubscRequest.setBillRefNo(String.valueOf(invoiceList.getBillRefNo()));
					retrieveSubscRequest.setStatus("Active");
					retrieveSubscRequest.setTransactionLog(_f20RetrieveSubscrService.buildTransactionLogCBO());
					RetrieveSubscrResponse retrieveSubscReponse = _f20RetrieveSubscrService
							.callInterface(retrieveSubscRequest);
					if (_f20RetrieveSubscrService.isCalledSuccesful("0", retrieveSubscReponse)) {
						if (retrieveSubscReponse.getSubscrNoList() != null
								&& retrieveSubscReponse.getSubscrNoList().size() > 0) {
							int subNoListSize = retrieveSubscReponse.getSubscrNoList().size();
							subNoList = new ArrayList<String>(subNoListSize);
							for (SubscrNoBO subscrNoBO : retrieveSubscReponse.getSubscrNoList()) {
								subNoList.add(subscrNoBO.getSubscription());
							}

							// java.util.stream.Collectors
						}
					} else {

					}
					invoice.setSubNoList(subNoList);
					dto.addData(invoice);
				}
			}
		} else {
			dto.getWarningList().add(_f08RetrieveWriteOffInvoiceService.buildErrorMessage(response));
		}

		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findProduct.json", method = RequestMethod.GET)
	public InvoiceProductDTO findProductByInvoiceJSON(@RequestParam("no") String billRefNo,
			@RequestParam("source") String source) {
		return paymentService.findProductByInvoice(billRefNo, source);
		/*
		 * RetrieveInvoiceAccountCodeRequest request = new
		 * RetrieveInvoiceAccountCodeRequest();
		 * request.setBillRefNo(Integer.valueOf(billRefNo));
		 * request.setTransactionLog(_f02RetrieveInvoiceAccountCodeService.
		 * buildTransactionLogCBO()); RetrieveInvoiceAccountCodeResponse
		 * response =
		 * _f02RetrieveInvoiceAccountCodeService.callInterface(request);
		 * 
		 * InvoiceProductDTO dto = new InvoiceProductDTO();
		 * if(_f02RetrieveInvoiceAccountCodeService.isCalledSuccesful("0",
		 * response)) { InvoiceProduct product; if
		 * (response.getInvoiceAccountCodeList() != null &
		 * response.getInvoiceAccountCodeList().size() > 0) { for
		 * (InvoiceAccountCodeBO output : response.getInvoiceAccountCodeList())
		 * { product = new InvoiceProduct(String.valueOf(output.getBillRefNo()),
		 * output.getRevTypeCode(), output.getRevTypeName(),
		 * output.getProductCode(), output.getProductName(),
		 * output.getSubProductCode(), output.getSubProductName(),
		 * BigDecimal.valueOf(output.getAmount()).divide(new
		 * BigDecimal("100.00"))); dto.addData(product); } } } else {
		 * dto.getWarningList().add(_f02RetrieveInvoiceAccountCodeService.
		 * buildErrorMessage(response)); } return dto;
		 */
	}

	@ResponseBody
	@RequestMapping(value = "findPaymentHistoryList.json", method = RequestMethod.GET)
	public PaymentHistoryDTO findPaymentHistoryListJSON(BillingInfo input) throws Exception {
		// List<HistoryPaymentDTO> historyPaymentDetailList =
		// paymentService.findHistoryPaymentDetails(input.getBillingNo());
		// PaymentHistoryDTO dto = new PaymentHistoryDTO();
		// RetrievePaymentListRequest request = new
		// RetrievePaymentListRequest();
		// SearchCBO searchCBO = new SearchCBO();
		// searchCBO.setSearchField("AccountExternalId|Equal");
		// searchCBO.setSearchValue1(input.getBillingNo()+"|String");
		// request.getSearchOBJ().add(searchCBO);
		//
		// for(HistoryPaymentDTO history : historyPaymentDetailList) {
		//
		// }
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "findPaymentHistory.json", method = RequestMethod.GET)
	public PaymentHistoryDTO findPaymentHistoryJSON(BillingInfo input) throws SOAPException, IOException,
			UnsupportedOperationException, ParserConfigurationException, SAXException {
		// 1. Get BA from front-end
		// input.getBillingNo(); // use this to search
		// List<HistoryPaymentDTO> historyPaymentDetailList =
		// paymentService.findHistoryPaymentDetails(input.getBillingNo());
		// 2. Use this to search for following information from Tables
		// sourceAccountId; = BA number
		// destinationAccountId; = account internal id in Billing (may be BA
		// internal id)
		// billingServerId; = server id of account that billing returned when
		// CRM created the account
		// trackingId; = createPayment returned this value
		// trackingIdServ; = createPayment returned this value
		// 3. Use information to fill in ESB_F14RetrievePaymentModel
		PaymentHistoryDTO dto = new PaymentHistoryDTO();
		List<Receipt> receipts = receiptRepo.findByAccountNoOrderByUpdateDttmDesc(input.getBillingNo());
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				// Offline
				logger.info("[getUpdateDttm]=" + receipt.getUpdateDttm());
				logger.info("[getDocDttm]=" + receipt.getDocDttm());
				logger.info("[getUpdateDttm]=" + AppUtil.formatter_EN.format(receipt.getUpdateDttm()) + " "
						+ AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
				logger.info("[getDocDttm]=" + AppUtil.formatter_EN.format(receipt.getDocDttm()) + " "
						+ AppUtil.formatter_EN_TIME.format(receipt.getDocDttm()));

				PaymentHistory paymentHistory = new PaymentHistory();
				BigDecimal exchangeRate = new BigDecimal(1);// by NSD 27-04-2017
				/*
				 * paymentHistory.setUpdatePrintDate(receipt.getUpdateDttm());
				 * paymentHistory.setReceiptPrintDate((receipt.getDocDttm() !=
				 * null)? receipt.getDocDttm(): receipt.getUpdateDttm());
				 */
				// fixed Date time
				paymentHistory.setUpdatePrintDateStr(AppUtil.formatter_EN.format(receipt.getUpdateDttm()) + " "
						+ AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
				if (receipt.getDocDttm() != null) {
					paymentHistory.setReceiptPrintDateStr(AppUtil.formatter_EN.format(receipt.getDocDttm()) + " "
							+ AppUtil.formatter_EN_TIME.format(receipt.getDocDttm()));
				} else {
					paymentHistory.setReceiptPrintDateStr(AppUtil.formatter_EN.format(receipt.getUpdateDttm()) + " "
							+ AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
				}
				if (receipt.getPayment() != null && receipt.getPayment().getCurrencyRate() != null) {
					exchangeRate = receipt.getPayment().getCurrencyRate();
				}
				paymentHistory.setUpdatePrintDate(receipt.getUpdateDttm());
				paymentHistory.setReceiptPrintDate(
						(receipt.getDocDttm() != null) ? receipt.getDocDttm() : receipt.getUpdateDttm());
				paymentHistory.setReceiptNo(receipt.getNo());
				paymentHistory.setBillRefNo(invoice.getNo()); // may use
																// payment.getBillRefNo()
																// or
																// payment.getOrigBillRefNo().toString()
				paymentHistory.setShopPaymentName(receipt.getBranchName());
				paymentHistory.setPaymentReceiver(receipt.getUpdateUser());
				paymentHistory.setCycleDateFrom(null);
				paymentHistory.setCycleDateTo(null);
				paymentHistory.setBillCycle(invoice.getBillCycle());
				// paymentHistory.setBillAmount(invoice.getTotalCharge() != null
				// ? invoice.getTotalCharge() : BigDecimal.ZERO);
				paymentHistory
						.setBillAmount(invoice.getDebtAmount() != null ? invoice.getDebtAmount() : BigDecimal.ZERO);
				paymentHistory.setAfterSaleDiscount(
						invoice.getAfterSaleDiscount() != null ? invoice.getAfterSaleDiscount() : BigDecimal.ZERO);
				// Fix by PM 20/04/2017
				if (receipt.getPayment().getMethod() != null) {
					String maskCC = AppUtil.maskCreditCardFromString(receipt.getPayment().getMethod(),
							"************####");
					String paymentCase = AppUtil.hideWTNumber(maskCC);
					paymentHistory.setPaymentMethod(paymentCase);
				}

				// End Fix by PM 20/04/2017

				paymentHistory.setPaymentCategory(trimToEmpty(invoice.getAttributes()).indexOf("F") > -1
						? "ชำระเต็มจำนวน"
						: (trimToEmpty(invoice.getAttributes()).indexOf("A") > -1 ? "ชำระล่วงหน้า" : "ชำระบางส่วน"));
				BigDecimal afterSaleDiscVat = BigDecimal.ZERO;
				afterSaleDiscVat = invoice.getAfterSaleDiscVat() != null ? invoice.getAfterSaleDiscVat()
						: BigDecimal.ZERO;

				paymentHistory.setTransAmount((invoice.getReceived().setScale(2).multiply(exchangeRate)).setScale(2,
						BigDecimal.ROUND_HALF_UP));
				if (!exchangeRate.equals(new BigDecimal(1)) && invoice.getVatRate() != null) {
					BigDecimal oneHen = new BigDecimal(100);
					// BigDecimal onVar =
					// paymentHistory.getTransAmount().multiply(invoice.getVatRate());
					// BigDecimal underVar = oneHen.add(invoice.getVatRate());
					invoice.setVat((paymentHistory.getTransAmount().multiply(invoice.getVatRate()))
							.divide(oneHen.add(invoice.getVatRate()), 2, BigDecimal.ROUND_HALF_UP));
				}
				paymentHistory.setBillAmountVat(
						(invoice.getVat() != null ? invoice.getVat() : BigDecimal.ZERO).subtract(afterSaleDiscVat));// by
																													// NSD
																													// 06-04-2017
				paymentHistory.setCurrencyCode(
						!StringUtils.equals(invoice.getCurrencyCode(), "12") ? invoice.getCurrencyCode() : "12");
				//
				// BigDecimal aDecimal = new BigDecimal(0.1950);
				// BigDecimal another = aDecimal.setScale(2,
				// aDecimal.ROUND_HALF_DOWN);

				if (receipt.getAttributes() != null) {
					if (invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF)
							|| invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_TBOSS)) { // WriteOff
																							// type
																							// Invoice
						paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก"
								: (receipt.getAttributes().indexOf("C") > -1 ? "หนี้สูญรับคืน" : "รอหักล้างหนี้สูญ"));
					} else { // "N" - normal billing
						paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก"
								: (receipt.getAttributes().indexOf("C") > -1 ? "ปกติ" : "รอหักล้าง"));
					}
				}

				if (CollectionUtils.isEmpty(invoice.getInvoiceVatDetails())
						|| invoice.getInvoiceVatDetails().size() < 2) {
					dto.addData(paymentHistory);
				}
				paymentHistory.setRemark(receipt.getRemark());
				// case after sales discount
				if (invoice.getAfterSaleDiscount().compareTo(BigDecimal.ZERO) == 1) {
					PaymentHistory paymentHistory1 = new PaymentHistory();
					// paymentHistory1 = paymentHistory;
					paymentHistory1.setUpdatePrintDateStr(paymentHistory.getUpdatePrintDateStr());
					paymentHistory1.setReceiptPrintDateStr(paymentHistory.getReceiptPrintDateStr());
					paymentHistory1.setUpdatePrintDate(paymentHistory.getUpdatePrintDate());
					paymentHistory1.setReceiptPrintDate(paymentHistory.getReceiptPrintDate());
					paymentHistory1.setReceiptNo(paymentHistory.getReceiptNo());
					paymentHistory1.setBillRefNo(paymentHistory.getBillRefNo()); // may
																					// use
																					// payment.getBillRefNo()
																					// or
																					// payment.getOrigBillRefNo().toString()
					paymentHistory1.setShopPaymentName(paymentHistory.getShopPaymentName());
					paymentHistory1.setPaymentReceiver(paymentHistory.getPaymentReceiver());
					paymentHistory1.setCycleDateFrom(null);
					paymentHistory1.setCycleDateTo(null);
					paymentHistory1.setBillCycle(paymentHistory.getBillCycle());
					// paymentHistory.setBillAmount(invoice.getTotalCharge() !=
					// null ? invoice.getTotalCharge() : BigDecimal.ZERO);
					paymentHistory1.setBillAmount(paymentHistory.getBillAmount());
					// paymentHistory1.setAfterSaleDiscount();
					// paymentHistory1.setPaymentMethod(paymentHistory.getPaymentMethod());
					if (StringUtils.equals(invoice.getDiscountType(), "1")) {
						paymentHistory.setPaymentCategory("ลูกค้ารับภาระภาษีเต็มจำนวน");
						paymentHistory1.setPaymentCategory("ส่วนลดหลังการขาย");
					} else {
						paymentHistory.setPaymentCategory("ลูกค้ารับภาระภาษีบางส่วน");
						paymentHistory1.setPaymentCategory("ส่วนลดหลังการขาย");
					}
					// paymentHistory1.setTransAmount();
					// paymentHistory1.setBillAmountVat();//by NSD 06-04-2017
					paymentHistory1.setStatus(paymentHistory.getStatus());

					// paymentHistory1.setBillAmount(invoice.getAfterSaleDiscount());
					paymentHistory1.setAfterSaleDiscount(BigDecimal.ZERO);
					paymentHistory1.setTransAmount(invoice.getAfterSaleDiscount().add(
							invoice.getAfterSaleDiscVat() != null ? invoice.getAfterSaleDiscVat() : BigDecimal.ZERO));// by
																														// NSD
																														// 11-04-2017
					paymentHistory1.setBillAmountVat(
							invoice.getAfterSaleDiscVat() != null ? invoice.getAfterSaleDiscVat() : BigDecimal.ZERO);// by
																														// NSD
																														// 06-04-2017

					paymentHistory.setTransAmount(paymentHistory.getTransAmount()
							.subtract(invoice.getVat() != null ? invoice.getVat() : BigDecimal.ZERO)
							.add(paymentHistory.getBillAmountVat()));
					paymentHistory1.setPaymentMethod("อนุมัติ " + invoice.getDiscApprUser() + " "
							+ AppUtil.formatter_EN.format(receipt.getUpdateDttm()));
					paymentHistory1.setCurrencyCode(
							!StringUtils.equals(invoice.getCurrencyCode(), "12") ? invoice.getCurrencyCode() : "12");// temporary
																														// set
					dto.addData(paymentHistory1);
				}
				// case an invoice has both vat and non vat
				if (!CollectionUtils.isEmpty(invoice.getInvoiceVatDetails())
						&& invoice.getInvoiceVatDetails().size() > 1) {
					for (InvoiceVatDetail dtl : invoice.getInvoiceVatDetails()) {
						PaymentHistory paymentHistory1 = new PaymentHistory();
						// paymentHistory1 = paymentHistory;
						paymentHistory1.setUpdatePrintDateStr(paymentHistory.getUpdatePrintDateStr());
						paymentHistory1.setReceiptPrintDateStr(paymentHistory.getReceiptPrintDateStr());
						paymentHistory1.setUpdatePrintDate(paymentHistory.getUpdatePrintDate());
						paymentHistory1.setReceiptPrintDate(paymentHistory.getReceiptPrintDate());
						paymentHistory1.setReceiptNo(paymentHistory.getReceiptNo());
						paymentHistory1.setBillRefNo(paymentHistory.getBillRefNo()); // may
																						// use
																						// payment.getBillRefNo()
																						// or
																						// payment.getOrigBillRefNo().toString()
						paymentHistory1.setShopPaymentName(paymentHistory.getShopPaymentName());
						paymentHistory1.setPaymentReceiver(paymentHistory.getPaymentReceiver());
						paymentHistory1.setCycleDateFrom(paymentHistory.getCycleDateFrom());
						paymentHistory1.setCycleDateTo(paymentHistory.getCycleDateTo());
						paymentHistory1.setBillCycle(paymentHistory.getBillCycle());
						// paymentHistory.setBillAmount(invoice.getTotalCharge()
						// != null ? invoice.getTotalCharge() :
						// BigDecimal.ZERO);
						paymentHistory1.setBillAmount(invoice.getTotalCharge());
						// paymentHistory1.setAfterSaleDiscount();
						// paymentHistory1.setPaymentMethod(paymentHistory.getPaymentMethod());
						paymentHistory1.setPaymentCategory(paymentHistory.getPaymentCategory());
						// paymentHistory1.setTransAmount();
						// paymentHistory1.setBillAmountVat();//by NSD
						// 06-04-2017
						paymentHistory1.setStatus(paymentHistory.getStatus());

						// paymentHistory1.setBillAmount(invoice.getAfterSaleDiscount());
						paymentHistory1.setAfterSaleDiscount(BigDecimal.ZERO);
						paymentHistory1.setTransAmount(
								dtl.getAmount().add(dtl.getVat() != null ? dtl.getVat() : BigDecimal.ZERO));
						paymentHistory1.setBillAmountVat(dtl.getVat() != null ? dtl.getVat() : BigDecimal.ZERO);// by
																												// NSD
																												// 06-04-2017
						paymentHistory1.setPaymentMethod(paymentHistory.getPaymentMethod());
						paymentHistory1.setCurrencyCode(!StringUtils.equals(invoice.getCurrencyCode(), "12")
								? invoice.getCurrencyCode() : "12");// temporary
																	// set
						dto.addData(paymentHistory1);
					}
				}
			}
		}
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findPaymentOtherHistory.json", method = RequestMethod.GET)
	public PaymentHistoryDTO findPaymentOtherHistoryJSON(BillingInfo input) throws SOAPException, IOException,
			UnsupportedOperationException, ParserConfigurationException, SAXException {
		// 1. Get BA from front-end
		// input.getBillingNo(); // use this to search
		// List<HistoryPaymentDTO> historyPaymentDetailList =
		// paymentService.findHistoryPaymentDetails(input.getBillingNo());
		// 2. Use this to search for following information from Tables
		// sourceAccountId; = BA number
		// destinationAccountId; = account internal id in Billing (may be BA
		// internal id)
		// billingServerId; = server id of account that billing returned when
		// CRM created the account
		// trackingId; = createPayment returned this value
		// trackingIdServ; = createPayment returned this value
		// 3. Use information to fill in ESB_F14RetrievePaymentModel
		PaymentHistoryDTO dto = new PaymentHistoryDTO();

		List<Receipt> receipts = receiptRepo.findByAccountNoOrderByUpdateDttmDesc(input.getBillingNo());
		for (Receipt receipt : receipts) {
			PaymentHistory paymentHistory = new PaymentHistory();
			List<Service> servList = new ArrayList<Service>();
			paymentHistory.setUpdatePrintDateStr(AppUtil.formatter_EN.format(receipt.getUpdateDttm()) + " "
					+ AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
			if (receipt.getDocDttm() != null) {
				paymentHistory.setReceiptPrintDateStr(AppUtil.formatter_EN.format(receipt.getDocDttm()) + " "
						+ AppUtil.formatter_EN_TIME.format(receipt.getDocDttm()));
			} else {
				paymentHistory.setReceiptPrintDateStr(AppUtil.formatter_EN.format(receipt.getUpdateDttm()) + " "
						+ AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
			}
			// End Fix By EPIS8 29/12/2016 issue no 160
			paymentHistory.setUpdatePrintDate(receipt.getUpdateDttm());
			paymentHistory.setReceiptPrintDate(
					(receipt.getDocDttm() != null) ? receipt.getDocDttm() : receipt.getUpdateDttm());
			paymentHistory.setReceiptNo(receipt.getNo());
			paymentHistory.setBillRefNo(""); // may use payment.getBillRefNo()
												// or
												// payment.getOrigBillRefNo().toString()
			paymentHistory.setShopPaymentName(receipt.getBranchName());
			paymentHistory.setPaymentReceiver(receipt.getUpdateUser());
			paymentHistory.setCycleDateFrom(null);
			paymentHistory.setCycleDateTo(null);
			// paymentHistory.setBillCycle(service.getProductName());
			// paymentHistory.setBillAmount(service.getTotalCharge() != null ?
			// service.getTotalCharge() : BigDecimal.ZERO);
			// Fix by EPIS1 (PM) 20/04/2017
			String maskCC = AppUtil.maskCreditCardFromString(receipt.getPayment().getMethod(), "************####");
			String paymentCase = AppUtil.hideWTNumber(maskCC);
			// End fix by EPIS1 (PM) 20/04/2017
			paymentHistory.setPaymentMethod(paymentCase);
			// paymentHistory.setPaymentCategory("ชำระเต็มจำนวน"); Add this if
			// want to show ประเภทการรับชำระ
			paymentHistory.setTransAmount(receipt.getTotalCharge());
			paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก"
					: (receipt.getAttributes().indexOf("C") > -1 ? "ปกติ" : "รอหักล้าง"));
			paymentHistory.setRemark(receipt.getRemark());
			paymentHistory.setDiscount(receipt.getDiscount());
			paymentHistory.setExcDiscount(receipt.getExcDiscount());
			for (Service service : receipt.getServices()) {
				// Offline
				// Fix By EPIS8 29/12/2016 issue no 160
				// fixed Date time
				if (receipt.getId().equals(service.getReceiptId())) {
					Service serv = new Service();

					// add service group W3P 30-Mar_2017
					serv.setServiceName(service.getServiceName());
					serv.setProductName(service.getProductName());
					serv.setIncomeUnit(service.getIncomeUnit());
					serv.setVat(service.getVat());
					serv.setDiscount(service.getDiscount());
					serv.setDeduction(service.getDeduction());
					serv.setTotalCharge(service.getTotalCharge());
					serv.setServiceQty(service.getServiceQty());
					serv.setAmount(service.getAmount());
					serv.setTotalCharge(service.getTotalCharge());

					servList.add(serv);
				}
			}
			paymentHistory.setService(servList);
			dto.addData(paymentHistory);
		}
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findPenaltyExtendHistory.json", method = RequestMethod.GET)
	public List<Receipt> findPenaltyExtendHistoryJSON(ReportPayment input) throws Exception {
		List<Receipt> rcptList = new ArrayList<Receipt>();
		rcptList = paymentService.findByAccountNoOrReceiptNoAndPaymentType(input);
		for (Receipt rpt : rcptList) {
			String maskCC = AppUtil.maskCreditCardFromString(rpt.getPayment().getMethod(), "************####");
			String paymentCase = AppUtil.hideWTNumber(maskCC);
			rpt.setPaymentCase(paymentCase);
		}
		return rcptList;
	}

	@ResponseBody
	@RequestMapping(value = "findTopupHistory.json", method = RequestMethod.GET)
	public List<Receipt> findTopupHistoryJSON(ReportPayment input) throws Exception {
		List<Receipt> rcptList = new ArrayList<Receipt>();
		rcptList = paymentService.findByServiceNoOrReceiptNoAndPaymentType(input);
		for (Receipt rpt : rcptList) {
			String maskCC = AppUtil.maskCreditCardFromString(rpt.getPayment().getMethod(), "************####");
			String paymentCase = AppUtil.hideWTNumber(maskCC);
			rpt.setPaymentCase(paymentCase);
			rpt.setUpdateDttmStr(DateUtil.convertToString(rpt.getUpdateDttm(), DateUtil.STANDARD_DATE_TIME_PATTERN));
		}
		return rcptList;
	}

	@ResponseBody
	@RequestMapping(value = "findPaymentDetails.json", method = RequestMethod.GET)
	public HistoryPaymentDTO findPaymentDetailsJSON(BillingInfo input) throws Exception {
		HistoryPaymentDTO dto = new HistoryPaymentDTO();
		RetrievePaymentHistoryRequest request = new RetrievePaymentHistoryRequest();
		request.setTransactionLog(_f13RetrievePaymentHistory.buildTransactionLogCBO());
		request.setLimitRow(20);
		request.setBillingAccountNo(input.getBillingNo());

		RetrievePaymentHistoryResponse response = null;
		try {
			response = _f13RetrievePaymentHistory.callInterface(request);
			HistoryPaymentDetail detail;
			for (PaymentHistoryBO paymentHistory : response.getPaymentHistoryList()) {
				// if(paymentHistory.getBillRefNo().toString().equals("0"))
				// continue;

				/*
				 * <BillingAccountNo>800079218</BillingAccountNo>
				 * <BillRefNo>0</BillRefNo> <TrackingNo>14116</TrackingNo>
				 * <Amount>234</Amount> <vatAmount> <Status>ยกเลิก</Status>
				 * <ProcessDate>2011-05-03</ProcessDate>
				 * <PaymentType>Advance</PaymentType> billPeriod
				 * <LocationName>กองระบบบัญชี (ตัดหนี้สูญ)</LocationName>
				 * <PayDate>2011-05-03</PayDate>
				 */
				// ตัด Column ( ใบเสร็จรับเงิน , ภาษีมูลค่าเพิ่ม VAT ,
				// สถานที่รับชำระ ,ผู้รับชำระ , ประเภทชำระ ) ออก
				detail = new HistoryPaymentDetail();

				detail.setTrackingId(paymentHistory.getTrackingNo());
				detail.setTotalCharged(paymentHistory.getAmount() != null ? new BigDecimal(paymentHistory.getAmount())
						: BigDecimal.ZERO);
				detail.setVatAmount(paymentHistory.getVatAmount() != null
						? new BigDecimal(paymentHistory.getVatAmount()) : BigDecimal.ZERO);
				detail.setStatus(paymentHistory.getStatus() != null ? paymentHistory.getStatus().toString() : "");
				detail.setProcessDate(paymentHistory.getProcessDate() != null
						? paymentHistory.getProcessDate().toGregorianCalendar().getTime() : null);
				detail.setPaymentType(
						paymentHistory.getPaymentType() != null ? paymentHistory.getPaymentType().toString() : "");
				detail.setBillCycle(
						paymentHistory.getBillPeriod() != null ? paymentHistory.getBillPeriod().toString() : "");
				detail.setLocationName(
						paymentHistory.getLocationName() != null ? paymentHistory.getLocationName().toString() : "");
				detail.setPaymentDate(paymentHistory.getPayDate() != null
						? paymentHistory.getPayDate().toGregorianCalendar().getTime() : null);
				if (paymentHistory.getBillRefNo().toString().equals("0"))
					detail.setInvoiceNo(detail.getPaymentType());
				else
					detail.setInvoiceNo(paymentHistory.getBillRefNo().toString());
				/*
				 * detail.setInvoiceNo(paymentHistory.getOrigBillRefNo().
				 * toString());
				 * 
				 * //detail.setReceiptNo(receiptNo);
				 * //detail.setVatType(paymentHistory.getVatRate() != null ?
				 * paymentHistory.getVatRate().toString() : "");
				 * //detail.setTotalCharged(paymentHistory.getAmount() != null ?
				 * new BigDecimal(paymentHistory.getAmount() / 100.00) :
				 * BigDecimal.ZERO);
				 * 
				 * String receiptBranchName="";
				 * if(paymentHistory.getLocationBO()!=null &&
				 * paymentHistory.getLocationBO().getLocationName()!=null)
				 * receiptBranchName=paymentHistory.getLocationBO().
				 * getLocationName();
				 * detail.setReceiptBranchName(receiptBranchName );
				 * //detail.setUpdateUser(paymentHistory.getTransSource() !=
				 * null ? paymentHistory.getTransSource().toString() : "");
				 * 
				 * detail.setBillCycle(paymentHistory.getPeriodDate()!= null ?
				 * paymentHistory.getPeriodDate().toString() : "");
				 * detail.setTrackingId(paymentHistory.getTrackingId() != null ?
				 * paymentHistory.getTrackingId().toString() : "");
				 */
				// detail.setTrackingIdServ(paymentHistory.getTrackingIdServ()
				// != null ? paymentHistory.getTrackingIdServ().toString() :
				// "");
				dto.addData(detail);
			}
			dto.setStatusCode("0");
		} catch (javax.xml.ws.WebServiceException ex) {
			if (ex.getCause() instanceof java.net.SocketTimeoutException) {
				dto.getErrorList().add(new AlertMessage("10",
						"F13RetrievePaymentHistory : java.net.SocketTimeoutException: Read timed out"));
			} else {
				dto.getErrorList().add(new AlertMessage("10", "F13RetrievePaymentHistory : " + ex.toString()));
			}
		}
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "createPaymentProduct.json", method = RequestMethod.POST)
	@Transactional
	public CreatePaymentResultDTO createPaymentProductJSON(SettlePaymentDTO paymentDTO) throws Exception {

		
		List<Receipt> receipts = paymentService.createPaymentInvoice(paymentDTO);
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		// erpService.payService(receipts, "0000");
		
		// check Advance CreditLimit
		List<SettlePaymentDTO.Advanced> advances = paymentDTO.getAdvances();
		boolean isGenCreditLimit = false;
		List<CreditLimitTrans> creditLimitTransList = null;
		// List<SettlePaymentDTO.Advanced> advances = null;
		if (paymentDTO.getGenCreditLimit() != null && paymentDTO.getGenCreditLimit().equals("Y")) {
			isGenCreditLimit = true;
			creditLimitTransList = paymentDTO.getCreditLimitTransList();
			List<CreditLimitTrans> advancedCreditLimit = new ArrayList<CreditLimitTrans>();
			if (creditLimitTransList != null && creditLimitTransList.size() > 0) {
				if (advances != null && advances.size() > 0) {
					for (SettlePaymentDTO.Advanced advance : advances) {
						if (advance.getInvoiceNo() != null
								&& !advance.getInvoiceNo().equals(AppConstants.ADVANCE_PAYMENT)) {
							int creditLimitSize = creditLimitTransList.size();
							for (int i = 0; i < creditLimitSize; i++) {
								CreditLimitTrans credit = creditLimitTransList.get(i);
								if (credit.getContract().equals(advance.getCustNo())) {
									CreditLimitTrans creditLimitTrans = new CreditLimitTrans();
									if (credit.getMsisdnList().size() > 1) {// multi
										if (credit.getMsisdn() != null && !credit.getMsisdn().equals("0")) {
											creditLimitTrans.setMsisdn(credit.getMsisdn());
											creditLimitTrans.setPayType("M");
										} else {
											creditLimitTrans.setPayType("A");
											creditLimitTrans.setMsisdn("");
										}
									} else {
										creditLimitTrans.setMsisdn(credit.getMsisdn());
										creditLimitTrans.setPayType("A");
									}
									creditLimitTrans.setContract(advance.getCustNo());
									creditLimitTrans.setArRef(AppConstants.ADVANCE_PAYMENT);
									creditLimitTrans.setAccountNo(advance.getKenan());
									advancedCreditLimit.add(creditLimitTrans);

								}
							}
						}
					}
				}
				if (advancedCreditLimit.size() > 0) {
					creditLimitTransList.addAll(advancedCreditLimit);
				}

			}

		}
		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				for (Service service : invoice.getServices()) {
					for (Transaction transaction : service.getTransactions()) {
						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
						logger.info("paymentSummary[" + paymentSummary + "]");
						paymentSummary.setBalance(paymentSummary.getBalance().add(transaction.getAmount()));
					}
				}
				// Credit Limit.
				/*
				 * int dataRow = 0; String creditLimitData =
				 * paymentDTO.getCreditLimitData();
				 * if(!StringUtil.isBlank(creditLimitData) &&
				 * paymentService.findBillingGroupByCategory().indexOf(receipt.
				 * getBillingGroup()) != -1) { String[] data =
				 * creditLimitData.split("\\|"); for(String d : data) {
				 * if(dataRow > 0) { String i = d.substring(0, d.indexOf("-"));
				 * if(invoice.getNo().equals(i)) { CreditLimit creditLimit = new
				 * CreditLimit(); creditLimit.setInvoiceId(invoice.getId());
				 * creditLimit.setServiceNo(d.substring(d.indexOf("-")+1));
				 * creditLimit.setCreditMode(data[0]);
				 * creditLimit.setUpdateDttm(new Date());
				 * creditLimit.setUpdateSystem("EPS");
				 * creditLimit.setUpdateUser("CLT");
				 * creditLimit.setVersionStamp(new Long(1));
				 * creditLimitRepository.save(creditLimit); } } dataRow++;
				 * 
				 * } }
				 */
				if (isGenCreditLimit) {
					Date now = new Date();
					// formatter_yyyyMMdd formatter_EN_TIME
					String postDate = AppUtil.formatter_yyyyMMdd.format(now) + " "
							+ AppUtil.formatter_EN_TIME.format(now);
					String payDate = AppUtil.formatter_yyyyMMdd.format(now);

					if (creditLimitTransList != null && creditLimitTransList.size() > 0) {
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						for (CreditLimitTrans creditLimitTrans : creditLimitTransList) {
							CreditLimitTransEntity creditLimitTransEntity = new CreditLimitTransEntity();
							String payType = creditLimitTrans.getPayType();
							String msisdn = "";
							int msisdnSize = 0;
							if (creditLimitTrans.getMsisdnSize() != null
									&& creditLimitTrans.getMsisdnSize().length() > 0)
								msisdnSize = Integer.valueOf(creditLimitTrans.getMsisdnSize());
							if (creditLimitTrans.getMsisdn() != null && creditLimitTrans.getMsisdn().length() > 0) {
								msisdn = creditLimitTrans.getMsisdn();
							}

							// String payDate = creditLimitTrans.getPayDate();

							String amountIncVat = creditLimitTrans.getAmountIncVat();
							String received = creditLimitTrans.getReceived();
							if (amountIncVat != null && amountIncVat.length() > 0 && received != null
									&& received.length() > 0) {
								double amountIncVatD = Double.valueOf(amountIncVat);
								double receivedD = Double.valueOf(received);
								if (receivedD >= amountIncVatD && (creditLimitTrans.getArRef() != null
										&& !creditLimitTrans.getArRef().equals("-"))){
									payType = "F";
									if ("Advance Payment".equals(creditLimitTrans.getArRef())) { payType = "A"; }
								}
								else if (receivedD < amountIncVatD)
									payType = "P";
								else
									payType = "A";
							}
							String arInvdate = "";
							if (creditLimitTrans.getArInvdate() != null
									&& creditLimitTrans.getArInvdate().length() > 0) {// 22/09/2016
								String[] arInvDateArray = creditLimitTrans.getArInvdate().split("/");
								if (arInvDateArray.length == 3) {
									arInvdate = arInvDateArray[2] + arInvDateArray[1] + arInvDateArray[0];
								}
							}
							String arDuedate = "";
							if (creditLimitTrans.getArDuedate() != null
									&& creditLimitTrans.getArDuedate().length() > 0) {// 22/09/2016
								String[] arDuedateArray = creditLimitTrans.getArDuedate().split("/");
								if (arDuedateArray.length == 3) {
									arDuedate = arDuedateArray[2] + arDuedateArray[1] + arDuedateArray[0];
								}
							}
							if (payType.equals("A")) {
								creditLimitTransEntity.setArInvdate(payDate);
								creditLimitTransEntity.setArDuedate(payDate);
							} else {
								creditLimitTransEntity.setArInvdate(arInvdate);
								creditLimitTransEntity.setArDuedate(arDuedate);
							}
							if ((creditLimitTrans.getArRef() != null && !creditLimitTrans.getArRef().equals("-"))) {
								Invoice inv = invoiceRepo.findByReceiptIdAndNo(receipt.getId(),
										creditLimitTrans.getArRef());

								if (inv != null) {
									if (inv.getCharge() != null)
										creditLimitTransEntity.setAmountExVat(String.valueOf(inv.getCharge().multiply(new BigDecimal(100))));
									if (inv.getVat() != null)
										creditLimitTransEntity.setVatAmount(String.valueOf(inv.getVat().multiply(new BigDecimal(100))));
									if (inv.getReceived() != null)
										creditLimitTransEntity.setAmountIncVat(String.valueOf(inv.getReceived().multiply(new BigDecimal(100))));
								}
							} else {
								Invoice inv = invoiceRepo.findByReceiptIdAndNo(receipt.getId(), ADVANCE_PAYMENT);

								if (inv != null) {
									if (inv.getCharge() != null)
										creditLimitTransEntity.setAmountExVat(String.valueOf(inv.getCharge().multiply(new BigDecimal(100))));
									if (inv.getVat() != null)
										creditLimitTransEntity.setVatAmount(String.valueOf(inv.getVat().multiply(new BigDecimal(100))));
									if (inv.getReceived() != null)
										creditLimitTransEntity.setAmountIncVat(String.valueOf(inv.getReceived().multiply(new BigDecimal(100))));
								}
								// creditLimitTransEntity.setVatAmount(String.valueOf(new
								// BigDecimal(creditLimitTrans.getVatAmount()).setScale(2,
								// RoundingMode.HALF_UP)));
								// creditLimitTransEntity.setAmountExVat(String.valueOf(new
								// BigDecimal(creditLimitTrans.getAmountExVat()).setScale(2,
								// RoundingMode.HALF_UP)));
								// creditLimitTransEntity.setAmountIncVat(received);//amountIncVat);
							}
							if (msisdnSize > 1 && !msisdn.equals("0"))
								payType = "M";

							creditLimitTransEntity.setContract(creditLimitTrans.getContract());
							creditLimitTransEntity.setReceiptId(String.valueOf(receipt.getNo()));
							creditLimitTransEntity.setArRef(creditLimitTrans.getArRef());
							creditLimitTransEntity.setPayType(payType);
							creditLimitTransEntity.setPayDate(payDate);

							if (msisdn.equals("0"))
								msisdn = "";

							creditLimitTransEntity.setMsisdn(msisdn);
							/*
							 * if( (creditLimitTrans.getArRef()!=null &&
							 * !creditLimitTrans.getArRef().equals("-"))) {
							 * Invoice inv =
							 * invoiceRepo.findByReceiptIdAndNo(receipt.getId(),
							 * creditLimitTrans.getArRef());
							 * 
							 * if(inv!=null) { if(inv.getCharge()!=null)
							 * creditLimitTransEntity.setAmountExVat(String.
							 * valueOf(inv.getCharge())); if(inv.getVat()!=null)
							 * creditLimitTransEntity.setVatAmount(String.
							 * valueOf(inv.getVat()));
							 * if(inv.getReceived()!=null)
							 * creditLimitTransEntity.setAmountIncVat(String.
							 * valueOf(inv.getReceived())); } }else{
							 * creditLimitTransEntity.setVatAmount(String.
							 * valueOf(new
							 * BigDecimal(creditLimitTrans.getVatAmount()).
							 * setScale(2, RoundingMode.HALF_UP)));
							 * creditLimitTransEntity.setAmountExVat(String.
							 * valueOf(new
							 * BigDecimal(creditLimitTrans.getAmountExVat()).
							 * setScale(2, RoundingMode.HALF_UP)));
							 * creditLimitTransEntity.setAmountIncVat(received);
							 * //amountIncVat); }
							 */

							creditLimitTransEntity.setPostDate(postDate);
							creditLimitTransEntity.setAccountNo(creditLimitTrans.getAccountNo());

							creditLimitTransEntity.setStatus("N");
							creditLimitTransEntity.setUpdatedTime(timestamp);

							if (creditLimitTransEntity.getVatAmount() != null
									&& creditLimitTransEntity.getAmountExVat() != null)
								creditLimitTransRepository.save(creditLimitTransEntity);
						}
					}

				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			if (method.getType() != null) {
				PaymentSummary paymentSummary = session.getPayType(method.getType());
				paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
			}
		}

		userService.saveSession(session);
		paymentService.callF4CreatePayment(receipts);
		// Case use coupon will update data to insale
		AppUtil.updateCoupon(paymentDTO.getMethods());

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "createPaymentService.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO createPaymentServiceJSON(SettlePaymentDTO paymentDTO) throws Exception {
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		List<Receipt> receipts = paymentService.createPaymentService(paymentDTO);

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Service service : receipt.getServices()) {
				for (Transaction transaction : service.getTransactions()) {
					PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
					paymentSummary.setBalance(paymentSummary.getBalance().add(transaction.getAmount()));
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			PaymentSummary paymentSummary = session.getPayType(method.getType());
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}

		userService.saveSession(session);

		// Case use coupon will update data to insale
		AppUtil.updateCoupon(paymentDTO.getMethods());

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "createPaymentAgent.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO createPaymentAgentJSON(SettlePaymentDTO paymentDTO) throws Exception {
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		List<Receipt> receipts = paymentService.createPaymentAgent(paymentDTO);

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Service service : receipt.getServices()) {
				for (Transaction transaction : service.getTransactions()) {
					PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
					paymentSummary.setBalance(paymentSummary.getBalance().add(transaction.getAmount()));
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			PaymentSummary paymentSummary = session.getPayType(method.getType());
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}

		userService.saveSession(session);

		// Case use coupon will update data to insale
		AppUtil.updateCoupon(paymentDTO.getMethods());

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findTopupServiceType.json", method = RequestMethod.GET)
	public TopupCustomerDTO findTopupServiceTypeJSON(ModelMap modelMap) throws Exception {
		String serviceKey = null;
		String authTokens = null;
		// <!-- Starting: Topup Authentication. -->
		JSONObject jsonObject = topupService.login();
		modelMap.addAttribute(TOPUP_FIELD_SERVICE_KEY, serviceKey = jsonObject.getString(TOPUP_FIELD_SERVICE_KEY));
		modelMap.addAttribute(TOPUP_FIELD_AUTH_TOKEN, authTokens = jsonObject.getString(TOPUP_FIELD_AUTH_TOKEN));

		// <!-- Starting: Find Topup Service. -->
		jsonObject = topupService.findServices(authTokens);
		TopupCustomerDTO topupCustomerDTO = new TopupCustomerDTO();
		if (!TOPUP_STATUS_SUCCESS.equals(jsonObject.get(TOPUP_FIELD_STATUS_CODE))) {
			topupCustomerDTO.setStatusCode(jsonObject.getString(TOPUP_FIELD_STATUS_CODE));
			topupCustomerDTO.getErrorList().add("There is no data returned from Topup service.");
			return topupCustomerDTO;
		}
		jsonObject.remove(TOPUP_FIELD_STATUS_CODE);
		for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			TopupCustomerDTO.Service service = topupCustomerDTO.addService();
			service.setKey(key);
			service.setValue(key);
			service.setText(jsonObject.getString(key));
		}
		topupCustomerDTO.setServiceKey(serviceKey);
		topupCustomerDTO.setAuthTokens(authTokens);
		return topupCustomerDTO;
	}

	@ResponseBody
	@RequestMapping(value = "findTopupServiceNo.json", method = RequestMethod.GET)
	public TopupCustomerDTO findTopupServiceNoJSON(ModelMap modelMap, @RequestParam("serviceName") String serviceName,
			@RequestParam("serviceNo") String serviceNo) throws Exception {
		topupService.login();
		String serviceKey = (String) modelMap.get(TOPUP_FIELD_SERVICE_KEY);
		String authTokens = (String) modelMap.get(TOPUP_FIELD_AUTH_TOKEN);
		JSONObject jsonObject = topupService.findSubscribers(authTokens, serviceName, serviceNo);
		TopupCustomerDTO topupCustomerDTO = new TopupCustomerDTO();
		if (!TOPUP_STATUS_SUCCESS.equals(jsonObject.get(TOPUP_FIELD_STATUS_CODE))) {
			topupCustomerDTO.setStatusCode(jsonObject.getString(TOPUP_FIELD_STATUS_CODE));
			// topupCustomerDTO.getErrorList().add("There is no data returned
			// from Topup service.");
			topupCustomerDTO.getErrorList().add("ตรวจสอบข้อมูลผิดประเภทบริการ");// by
																				// NSD
																				// /25-01-2017
			return topupCustomerDTO;
		}
		topupCustomerDTO.setFirstName(jsonObject.getString("firstname"));
		topupCustomerDTO.setLastName(jsonObject.getString("lastname"));
		JSONArray jsonArray = jsonObject.getJSONArray("subscriberDetails");
		for (int i = 0, m = jsonArray.length(); i < m; i++) {
			JSONObject details = jsonArray.getJSONObject(i);
			JSONArray promotions = details.getJSONArray("subscriberPromotions");
			String subscriberNo = details.getString("subscriberno");
			TopupCustomerDTO.Service service = topupCustomerDTO.addService();
			service.setKey(subscriberNo);
			service.setValue(subscriberNo);
			service.setText(subscriberNo);
			TopupCustomerDTO.Amount amount = topupCustomerDTO.addAmount();
			for (int j = 0, n = promotions.length(); j < n; j++) {
				JSONObject promotion = promotions.getJSONObject(j);
				amount.addService(promotion.getString("amount"), promotion.getString("discountPercent"),
						promotion.getString("amount"));
			}
		}
		topupCustomerDTO.setServiceKey(serviceKey);
		topupCustomerDTO.setAuthTokens(authTokens);
		
		System.out.println(" hello test : "+ topupCustomerDTO.toString());
		return topupCustomerDTO;
	}

	@ResponseBody
	@RequestMapping(value = "createPaymentTopup.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO createPaymentTopupJSON(ModelMap modelMap, SettlePaymentDTO paymentDTO)
			throws Exception {
		// JSONObject jsonObject = topupService.login();
		// modelMap.addAttribute(TOPUP_FIELD_SERVICE_KEY, serviceKey =
		// jsonObject.getString(TOPUP_FIELD_SERVICE_KEY));
		String authTokens = (String) modelMap.get(TOPUP_FIELD_AUTH_TOKEN);
		for (SettlePaymentDTO.Customer customer : paymentDTO.getCustomers()) {
			String transId = "";
			for (SettlePaymentDTO.Service service : customer.getServices()) {
				String refTransId = AppUtil.generateTransactionID(9);
				service.setRefTransId(refTransId);
				// JSONObject verifyResult =
				// topupService.verifyTopup(authTokens, service.getCode(),
				// service.getNo(), service.getSubscriber(), EMPTY,
				// service.getRefTransId(),
				// service.getAmount().toPlainString());
				JSONObject verifyResult = topupService.verifyTopup(authTokens, service.getCode(), service.getNo(),
						service.getSubscriber(), EMPTY, service.getRefTransId(),
						service.getAmtIncVat().toPlainString());
				// String status_code = (String)verifyResult.get("status_code");
				// System.out.println(status_code);
				transId = (String) verifyResult.get("transid");
				JSONObject confirmResult = topupService.confirmTopup(authTokens, service.getCode(), service.getNo(),
						service.getSubscriber(), transId, verifyResult.getString(TOPUP_FIELD_REF_TRANSID),
						service.getAmtIncVat().toPlainString());
				// JSONObject confirmResult =
				// topupService.confirmTopup(authTokens, service.getCode(),
				// service.getNo(), service.getSubscriber(), EMPTY, "1234",
				// service.getAmount().toPlainString());
			}
			customer.setRef1(transId);
		}
		List<Receipt> receipts = paymentService.createPaymentTopup(paymentDTO);

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Service service : receipt.getServices()) {
				for (Transaction transaction : service.getTransactions()) {
					PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
					paymentSummary.setBalance(transaction.getAmount());
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			PaymentSummary paymentSummary = session.getPayType(method.getType());
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}

		userService.saveSession(session);

		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		dto.setData(receipts);
		dto.setStatusCode("0");		
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "createPaymentTBOSS.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO createPaymentTBOSSJSON(SettlePaymentDTO paymentDTO) throws Exception {

		List<Receipt> receipts = paymentService.createPaymentTBOSS(paymentDTO);
		paymentService.deductTBOSSBadDebt(receipts);
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				for (Service service : invoice.getServices()) {
					for (Transaction transaction : service.getTransactions()) {
						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
						paymentSummary.setBalance(paymentSummary.getBalance().add(transaction.getAmount()));
					}
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			PaymentSummary paymentSummary = session.getPayType(method.getType());
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}

		userService.saveSession(session);

		// Case use coupon will update data to insale
		AppUtil.updateCoupon(paymentDTO.getMethods());

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "cancelPaymentProduct.json", method = RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentProductJSON(CancelPaymentDTO cancelPaymentDTO) throws Exception {
		Map<Long, CancelPaymentDTO.Receipt> receiptMap = newHashMap();
		for (CancelPaymentDTO.Receipt receipt : cancelPaymentDTO.getReceipts()) {
			receiptMap.put(receipt.getId(), receipt);
		}
		CancelPaymentDTO.Receipt rcp;
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		Iterable<Receipt> receipts = receiptRepo.findAll(receiptMap.keySet());
		ReversePaymentRequest request;
		ReverseWriteOffRequest reverseWriteoff;
		boolean isCheque = false, haveError = false;
		List<Receipt> receiptList = new ArrayList<Receipt>();
		List<Receipt> receiptsList = new ArrayList<Receipt>();

		for (Receipt receipt : receipts) {

			Receipt cloneReceipt = new Receipt();
			BeanUtils.copyProperties(receipt, cloneReceipt);
			receiptList.add(cloneReceipt);

			rcp = receiptMap.get(receipt.getId());
			receipt.setCancelReason(rcp.getReasonCode() + " - " + rcp.getReasonDesc());
			dwService.sendDateWereHose(receipt.getPayment().getId());
		}

		if (!cancelPaymentDTO.isFlgNewReceipt()) {
			for (Receipt receipt : receipts) {
				for (Invoice invoice : receipt.getInvoices()) {
					if (invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF)) {
						for (Service service : invoice.getServices()) {
							for (Transaction transaction : service.getTransactions()) {
								reverseWriteoff = new ReverseWriteOffRequest();
								reverseWriteoff.setTransactionLog(_f11ReverseWriteOffService.buildTransactionLogCBO());
								reverseWriteoff.setInvoiceNo(invoice.getNo());
								reverseWriteoff.setBillingAccountNo(invoice.getCustomer().getNo());
								reverseWriteoff.setTrackingId(transaction.getTrackingId());
								reverseWriteoff.setTranId(transaction.getTransactionId());

								String exception = "";
								try {
									ReverseWriteOffResponse response = _f11ReverseWriteOffService
											.callInterface(reverseWriteoff);
									if (_f11ReverseWriteOffService.isCalledSuccesful("0", response)) {
										cancelPaymentResultDTO.addData(receipts);
										cancelPaymentResultDTO.addData(response);
									} else {
										cancelPaymentResultDTO.getErrorList()
												.add(_f11ReverseWriteOffService.buildErrorMessage(response));
										cancelPaymentResultDTO.setStatusCode("10");
										haveError = true;
									}
								} catch (javax.xml.ws.WebServiceException ex) {
									if (ex.getCause() instanceof java.net.SocketTimeoutException) {
										exception = "java.net.SocketTimeoutException: Read timed out";
									} else {
										exception = ex.toString();
									}
								} finally {
									if (isNotBlank(exception)) {
										cancelPaymentResultDTO.getErrorList().add(
												new AlertMessage("10", "F11ReverseWriteOffService : " + exception));
										cancelPaymentResultDTO.setStatusCode("10");
										haveError = true;
									}
								}
							}
						}
					}
				}

				for (Invoice invoice : receipt.getInvoices()) {
					if (invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF))
						continue;
					if (invoice.getStatus().equalsIgnoreCase(INVOICE_OTHER_PAYMENT))
						continue; // by NSD 03-04-2017
					for (Service service : invoice.getServices()) {
						for (Transaction transaction : service.getTransactions()) {
							request = new ReversePaymentRequest();
							request.setTransactionLog(_f06ReversePaymentService.buildTransactionLogCBO());
							request.setAccountNo(toInt(invoice.getKenan()));
							request.setBillingServerId(3);
							request.setBillingAccountNo(receipt.getAccountNo()); // Modified
																					// case
																					// incorrect
																					// ba
																					// data.
																					// //request.setBillingAccountNo(invoice.getNo());
							request.setIsCheque(isCheque ? 1 : 0); // do we need
																	// to send
																	// proper
																	// value
																	// from
																	// transaction???
							request.setTranId(toInt(transaction.getTrackingId()));
							request.setTranIdServe(toInt(transaction.getTrackingIdServ()));
							String exception = "";
							try {
								ReversePaymentResponse response = _f06ReversePaymentService.callInterface(request);
								if (_f06ReversePaymentService.isCalledSuccesful("0", response)) {
									cancelPaymentResultDTO.addData(receipts);
									cancelPaymentResultDTO.addData(response);
								} else {
									cancelPaymentResultDTO.getErrorList()
											.add(_f06ReversePaymentService.buildErrorMessage(response));
									cancelPaymentResultDTO.setStatusCode("10");
									haveError = true;
								}
							} catch (javax.xml.ws.WebServiceException ex) {
								if (ex.getCause() instanceof java.net.SocketTimeoutException) {
									exception = "java.net.SocketTimeoutException: Read timed out";
								} else {
									exception = ex.toString();
								}
							} finally {
								if (isNotBlank(exception)) {
									cancelPaymentResultDTO.getErrorList()
											.add(new AlertMessage("10", "F06ReversePayment : " + exception));
									cancelPaymentResultDTO.setStatusCode("10");
									haveError = true;
								}
							}
						}
					}
				}
			}
			if (!haveError) {
				paymentService.cancelPayment(receipts, cancelPaymentDTO.getUserAuthen());
				paymentService.cancelCoupon(receipts, cancelPaymentDTO.getUserAuthen());
				paymentService.cancelPromotion(receipts, "C");
			} else {
				paymentService.markToCancelPayment(receipts);
			}
		} else {

			paymentService.cancelPayment(receipts, cancelPaymentDTO.getUserAuthen());
			/* start create New Receipt */
			receiptsList = paymentService.cancelAndCreateNewReceipt(cancelPaymentDTO, receiptList);
			/* end create New Receipt */
			paymentService.cancelPromotion(receiptsList, "W");
			// egp
			for (Receipt rct : receiptsList) {
				Iterable<ReceiptEgpMappingEntity> egpMapList = egpMapRepo.findByReceiptId(rct.getId());
				for (ReceiptEgpMappingEntity egpMap : egpMapList) {
					rct.setEgpNo(egpMap.getEgpNo());
					rct.setEgpContract(egpMap.getEgpContract());
				}
			}
			cancelPaymentResultDTO.addData(receiptsList);

		}

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				for (Service service : invoice.getServices()) {
					for (Transaction transaction : service.getTransactions()) {
						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
						paymentSummary.setBalance(paymentSummary.getBalance().subtract(transaction.getAmount()));
					}
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) - 1);
			paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		// for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
		// PaymentSummary paymentSummary = session.getPayType(method.getType());
		// paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 :
		// paymentSummary.getCounter()) - 1);
		// }

		userService.saveSession(session);

		return cancelPaymentResultDTO;
	}

	@ResponseBody
	@RequestMapping(value = "cancelPaymentService.json", method = RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentServiceJSON(CancelPaymentDTO cancelPaymentDTO) throws Exception {
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		Iterable<Receipt> receipts = receiptRepo
				.findAll(transform(cancelPaymentDTO.getReceipts(), new Function<CancelPaymentDTO.Receipt, Long>() {
					@Override
					public Long apply(CancelPaymentDTO.Receipt receipt) {
						return receipt.getId();
					}
				}));
		Map<Long, CancelPaymentDTO.Receipt> receiptMap = newHashMap();
		for (CancelPaymentDTO.Receipt receipt : cancelPaymentDTO.getReceipts()) {
			receiptMap.put(receipt.getId(), receipt);
		}
		List<Receipt> newReceiptList = new ArrayList<Receipt>();
		CancelPaymentDTO.Receipt rcp;

		for (Receipt receipt : receipts) {
			Receipt newReceipt = new Receipt();
			BeanUtils.copyProperties(receipt, newReceipt);
			newReceiptList.add(newReceipt);

			rcp = receiptMap.get(receipt.getId());
			receipt.setCancelReason(rcp.getReasonCode() + " - " + rcp.getReasonDesc());

			// <!-- Starting: Mark Cancel Status. -->
			paymentService.cancelPayment(receipts, cancelPaymentDTO.getUserAuthen());
			if (cancelPaymentDTO.isFlgNewReceipt()) {
				/* start create New Receipt */
				newReceiptList = paymentService.cancelAndCreateNewReceiptOther(cancelPaymentDTO, newReceiptList);
				/* end create New Receipt */
				cancelPaymentResultDTO.addData(newReceiptList);
			} else {
				cancelPaymentResultDTO.addData(receipt);
				paymentService.cancelCoupon(receipts, cancelPaymentDTO.getUserAuthen());
			}
		}
		cancelPaymentResultDTO.setStatusCode("0");

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				for (Service service : invoice.getServices()) {
					for (Transaction transaction : service.getTransactions()) {
						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
						paymentSummary.setBalance(paymentSummary.getBalance().subtract(transaction.getAmount()));
					}
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) - 1);
			paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		// for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
		// PaymentSummary paymentSummary = session.getPayType(method.getType());
		// paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 :
		// paymentSummary.getCounter()) - 1);
		// }

		userService.saveSession(session);

		return cancelPaymentResultDTO;
	}

	@ResponseBody
	@RequestMapping(value = "cancelPaymentTBOSS.json", method = RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentTBOSSJSON(CancelPaymentDTO cancelPaymentDTO)
			throws DatatypeConfigurationException, SOAPException, IOException, UnsupportedOperationException,
			ParserConfigurationException, SAXException {
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		Iterable<Receipt> receipts = receiptRepo
				.findAll(transform(cancelPaymentDTO.getReceipts(), new Function<CancelPaymentDTO.Receipt, Long>() {
					@Override
					public Long apply(CancelPaymentDTO.Receipt receipt) {
						return receipt.getId();
					}
				}));

		// <!-- Starting: Mark Cancel Status. -->
		paymentService.reverseTBOSSBadDebt(receipts, EpContextHolder.getContext().getUserCode(),
				EpContextHolder.getContext().getBranchCode());
		for (Receipt receipt : receipts) {
			cancelPaymentResultDTO.addData(receipt);
		}
		cancelPaymentResultDTO.setStatusCode("0");

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				for (Service service : invoice.getServices()) {
					for (Transaction transaction : service.getTransactions()) {
						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
						paymentSummary.setBalance(paymentSummary.getBalance().subtract(transaction.getAmount()));
					}
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) - 1);
			paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		// for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
		// PaymentSummary paymentSummary = session.getPayType(method.getType());
		// paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 :
		// paymentSummary.getCounter()) - 1);
		// }

		userService.saveSession(session);

		return cancelPaymentResultDTO;
	}

	@ResponseBody
	@RequestMapping(value = "cancelPaymentTopup.json", method = RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentTopupJSON(CancelPaymentDTO cancelPaymentDTO) throws Exception {
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		Iterable<Receipt> receipts = receiptRepo
				.findAll(transform(cancelPaymentDTO.getReceipts(), new Function<CancelPaymentDTO.Receipt, Long>() {
					@Override
					public Long apply(CancelPaymentDTO.Receipt receipt) {
						return receipt.getId();
					}
				}));

		// <!-- Starting: Cancel Topup Service. -->
		JSONObject jsonObject, verifyResult;
		String authTokens;
		jsonObject = topupService.login();
		authTokens = jsonObject.getString(TOPUP_FIELD_AUTH_TOKEN);
		BigDecimal amtIncVatExcDisc = new BigDecimal(0);
		for (Receipt receipt : receipts) {
			for (Service service : receipt.getServices()) {
				amtIncVatExcDisc = service.getAmount().multiply(new BigDecimal(100).add(service.getVatRate()))
						.divide(new BigDecimal(100));
				amtIncVatExcDisc = amtIncVatExcDisc.setScale(0, BigDecimal.ROUND_HALF_UP);
				verifyResult = topupService.verifyCancel(authTokens, service.getServiceCode(), service.getServiceNo(),
						service.getServiceName(), EMPTY, service.getRefTransId(), amtIncVatExcDisc.toPlainString()); // change
																														// Amount
																														// to
																														// amtInc...
																														// by
																														// NSD
																														// 10-03-2017
				topupService.confirmCancel(authTokens, service.getServiceCode(), service.getServiceNo(),
						service.getServiceName(), EMPTY, verifyResult.getString(TOPUP_FIELD_REF_TRANSID),
						amtIncVatExcDisc.toPlainString());// ===||===
			}
		}

		// <!-- Starting: Mark Cancel Status. -->
		paymentService.cancelPayment(receipts, cancelPaymentDTO.getUserAuthen());
		for (Receipt receipt : receipts) {
			cancelPaymentResultDTO.addData(receipt);
		}
		cancelPaymentResultDTO.setStatusCode("0");
		return cancelPaymentResultDTO;
	}

	@ResponseBody
	@RequestMapping(value = "cancelPaymentAgent.json", method = RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentAgentJSON(CancelPaymentDTO cancelPaymentDTO)
			throws DatatypeConfigurationException, SOAPException, IOException, UnsupportedOperationException,
			ParserConfigurationException, SAXException {
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		Iterable<Receipt> receipts = receiptRepo
				.findAll(transform(cancelPaymentDTO.getReceipts(), new Function<CancelPaymentDTO.Receipt, Long>() {
					@Override
					public Long apply(CancelPaymentDTO.Receipt receipt) {
						return receipt.getId();
					}
				}));
		// <!-- Starting: Mark Cancel Status. -->
		paymentService.cancelPayment(receipts, cancelPaymentDTO.getUserAuthen());
		for (Receipt receipt : receipts) {
			cancelPaymentResultDTO.addData(receipt);
		}
		cancelPaymentResultDTO.setStatusCode("0");

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				for (Service service : invoice.getServices()) {
					for (Transaction transaction : service.getTransactions()) {
						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
						paymentSummary.setBalance(paymentSummary.getBalance().subtract(transaction.getAmount()));
					}
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) - 1);
			paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);

			receipt.setCancelReason(cancelPaymentDTO.getReasonCode() + ":" + cancelPaymentDTO.getReasonDesc());
			receipt.setCancelledBy(EpContextHolder.getContext().getUserCode());
			receipt.setCancelledDttm(new Date());
			receiptRepo.save(receipt);

		}
		// for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
		// PaymentSummary paymentSummary = session.getPayType(method.getType());
		// paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 :
		// paymentSummary.getCounter()) - 1);
		// }

		userService.saveSession(session);

		return cancelPaymentResultDTO;
	}

	@ResponseBody
	@RequestMapping(value = "closeBranch.json", method = RequestMethod.GET)
	public EndOfDayDTO closeBranchJSON(String closeDate) {
		String branchCode = EpContextHolder.getContext().getBranchCode(),
				branchName = EpContextHolder.getContext().getBranchName(),
				posName = EpContextHolder.getContext().getPosName();
		BigDecimal totalPaymentAmount = BigDecimal.ZERO;
		Session session = userService.getSessionForCloseBranch(closeDate);
		PaymentSummary paymentSummary;
		for (String payType : asList(PAY_METHOD_CASH, PAY_METHOD_CHEQUE, PAY_METHOD_CREDITCARD, PAY_METHOD_MONEYORDER,
				PAY_METHOD_BANKTRANSFER, PAY_METHOD_FOREIGNBANK, PAY_METHOD_BILLEXCHANGE, PAY_METHOD_OTHER,
				PAY_METHOD_FOREIGNTRANSFER)) {
			paymentSummary = session.getPayType(payType);
			totalPaymentAmount = totalPaymentAmount.add(paymentSummary.getBalance());
		}

		final EndOfDayDTO endOfDayDTO = new EndOfDayDTO();
		EndOfDayDTO.SummaryPaymentInfo summaryPaymentInfo = endOfDayDTO.addSummaryPaymentInfo();
		summaryPaymentInfo.setOfficerName(SecurityContextHolder.getContext().getAuthentication().getName());
		summaryPaymentInfo.setPosMachineName(posName);
		summaryPaymentInfo.setBranch(branchCode + " " + branchName);
		summaryPaymentInfo.setPageNo("1");
		summaryPaymentInfo.setCurrentDttm(new Date());
		summaryPaymentInfo.setTotalPaymentAmount(totalPaymentAmount);
		summaryPaymentInfo.setTotal3TredecimAmount(session.getPayType(DEDUCT_METHOD_3TREDECIM).getBalance());
		summaryPaymentInfo.setTotal69BisAmount(session.getPayType(DEDUCT_METHOD_69BIS).getBalance());
		summaryPaymentInfo.setTotal69TreAmount(session.getPayType(DEDUCT_METHOD_69TRE).getBalance());
		summaryPaymentInfo.setTotalCashAmount(session.getPayType(PAY_METHOD_CASH).getBalance());
		paymentSummary = session.getPayType(PAY_METHOD_CHEQUE);
		summaryPaymentInfo.setTotalChequeAmount(paymentSummary.getBalance());
		summaryPaymentInfo.setTotalCheque(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_CREDITCARD);
		summaryPaymentInfo.setTotalCreditCardAmount(paymentSummary.getBalance());
		summaryPaymentInfo.setTotalCreditCard(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_MONEYORDER);
		summaryPaymentInfo.setTotalMoneyOrderAmount(paymentSummary.getBalance());
		summaryPaymentInfo.setTotalMoneyOrder(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_BANKTRANSFER);
		summaryPaymentInfo.setTotalBankTxnfAmount(paymentSummary.getBalance());
		summaryPaymentInfo.setTotalBankTxnf(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_FOREIGNBANK);
		summaryPaymentInfo.setTotalForeignBankTxnfAmount(paymentSummary.getBalance());
		paymentSummary = session.getPayType(PAY_METHOD_FOREIGNTRANSFER);
		summaryPaymentInfo.setTotalForeignBankTxnfAmount(paymentSummary.getBalance());
		summaryPaymentInfo.setTotalForeignBankTxnf(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_BILLEXCHANGE);
		summaryPaymentInfo.setTotalBillExchangeAmount(paymentSummary.getBalance());
		summaryPaymentInfo.setTotalBillExchange(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_OTHER);
		summaryPaymentInfo.setTotalOtherAmount(paymentSummary.getBalance());
		summaryPaymentInfo.setTotalOther(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
		summaryPaymentInfo.setTotalPaidReceiptWithTaxInvoice(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
		summaryPaymentInfo.setTotalCancelledReceiptWithTaxInvoice(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_RECEIPT);
		summaryPaymentInfo.setTotalPaidReceipt(paymentSummary.getCounter());
		paymentSummary = session.getPayType(PAY_METHOD_CANCEL);
		summaryPaymentInfo.setTotalCancelledReceipt(paymentSummary.getCounter());
		return endOfDayDTO;
	}

	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;

	@ResponseBody
	@RequestMapping(value = "processEndOfDay.json", method = RequestMethod.GET)
	public void processEndOfDayJSON(String closeDate) {
		String username = EpContextHolder.getContext().getUserCode();
		System.out.println("usercode = " + username);
		System.out.println("codedate = " + closeDate);
		StringBuilder queryBuilder = new StringBuilder();
		final ArrayList<Long> receiptIdList = new ArrayList<Long>();
		queryBuilder.append(" SELECT receiptid FROM correceipt WHERE updateuser = '" + username
				+ "' AND TRUNC(receiptdttm) = TO_DATE('" + closeDate + "', 'DD-MM-YYYY')");
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet row) throws SQLException {
				receiptIdList.add(row.getLong(1));
			}
		});
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		for (Long receiptId : receiptIdList) {
			receipts.add(receiptRepo.findOne(receiptId));
		}
		updateReceiptForCloseBranch(receipts);
	}

	@Transactional
	public void updateReceiptForCloseBranch(ArrayList<Receipt> receipts) {
		for (Receipt receipt : receipts) {
			receipt.setEndOfDayDttm(DateUtil.convertToString(new Date(), DateUtil.STANDARD_DATE_PATTERN));
			receipt.setIsEndOfDay(new Long(1));
			receiptRepo.save(receipt);
		}
	}

	public static final String convertDateString(String str) {
		return str.replaceAll("([0-9]{2})/([0-9]{2})/([0-9]{4})", "$3-$2-$1");
	}
	/*
	@ResponseBody
	@RequestMapping(value = "/getCouponDetail.json", method = RequestMethod.GET)
	public void getCouponDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "couponId") String couponId) throws Exception {
		// http://10.36.10.16:8080/insaleservices/coupons/coupon/couponId
		// String jsonStringResult =
		// AppUtil.callURL("http://10.44.1.4/InsaleManagement/Services_MS?table=import_coupon&type=list&id="+couponId);
		String jsonStringResult = AppUtil
				.callURL("http://10.36.10.16:8080/insaleservices/coupons/coupon/" + couponId.trim());
		System.out.println("jsonStringResult: " + jsonStringResult);
		AppUtil.drawJson(request, response, jsonStringResult);
	}
	*/
	@ResponseBody
	@RequestMapping(value="/getCouponDetail.json", method=RequestMethod.GET)
	//public void getCouponDetail(HttpServletRequest request, HttpServletResponse response
		//	,@RequestParam(value="couponId") String couponId
	public th.co.softpos.ws.mg.s001.Response getCouponDetail(HttpServletRequest request, HttpServletResponse response
				,@RequestParam(value="couponId") String couponId
	) throws Exception {
		//http://10.36.10.16:8080/insaleservices/coupons/coupon/couponId
		//String jsonStringResult = AppUtil.callURL("http://10.44.1.4/InsaleManagement/Services_MS?table=import_coupon&type=list&id="+couponId);
		th.co.softpos.ws.mg.s001.RqHeader rqHeader = new th.co.softpos.ws.mg.s001.RqHeader();
		rqHeader.setFuncNm("S001MG");
		rqHeader.setRqAppId("POS");
		rqHeader.setUserId("9999");
		th.co.softpos.ws.mg.s001.RqBody rqBody = new th.co.softpos.ws.mg.s001.RqBody();
		rqBody.setCode(couponId);
		th.co.softpos.ws.mg.s001.Request _process_rq = new th.co.softpos.ws.mg.s001.Request();
		_process_rq.setRqHeader(rqHeader);
		_process_rq.setRqBody(rqBody);  
		
		th.co.softpos.ws.mg.s001.Response _response = null;
		try{
			_response = s001MGInqGiftvoucher.process(_process_rq);
		}catch (Exception ex) {
			 
		}
		return _response;
		// th.co.softpos.ws.mg.s001.RsHeader rsHeader = _response.getRsHeader();
		//_response.
		//String jsonStringResult = AppUtil.callURL("http://10.36.10.16:8080/insaleservices/coupons/coupon/"+couponId.trim());
		//System.out.println("jsonStringResult: " + jsonStringResult);
		//AppUtil.drawJson(request, response, jsonStringResult);
	}

	@RequestMapping(value = "findCancelMNPReasonCategoryList.json", method = RequestMethod.GET)
	public void findCancelMNPReasonCategoryListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("reason.mnpCN1.category"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findCancelMNPReasonCategoryList2.json", method = RequestMethod.GET)
	public void findCancelMNPReasonCategoryListJSON2(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("reason.mnpCN2.category"));
		modelMap.addAttribute("statusCode", "0");
	}

	// by NSD 28-02-2017
	@ResponseBody
	@RequestMapping(value = "createPaymentPenaltyExtend.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO createPaymentPenaltyExtendJSON(ModelMap modelMap, SettlePaymentDTO paymentDTO)
			throws Exception {
		// JSONObject jsonObject = topupService.login();
		// modelMap.addAttribute(TOPUP_FIELD_SERVICE_KEY, serviceKey =
		// jsonObject.getString(TOPUP_FIELD_SERVICE_KEY));
		/*
		 * String authTokens = (String) modelMap.get(TOPUP_FIELD_AUTH_TOKEN);
		 * for (SettlePaymentDTO.Customer customer : paymentDTO.getCustomers())
		 * { for (SettlePaymentDTO.Service service : customer.getServices()) {
		 * String refTransId=AppUtil.generateTransactionID(9);
		 * service.setRefTransId(refTransId); JSONObject verifyResult =
		 * topupService.verifyTopup(authTokens, service.getCode(),
		 * service.getNo(), service.getSubscriber(), EMPTY,
		 * service.getRefTransId(), service.getAmount().toPlainString()); String
		 * status_code = (String)verifyResult.get("status_code");
		 * //System.out.println(status_code); //JSONObject confirmResult =
		 * topupService.confirmTopup(authTokens, service.getCode(),
		 * service.getNo(), service.getSubscriber(), EMPTY,
		 * verifyResult.getString(TOPUP_FIELD_REF_TRANSID),
		 * service.getAmount().toPlainString()); //JSONObject confirmResult =
		 * topupService.confirmTopup(authTokens, service.getCode(),
		 * service.getNo(), service.getSubscriber(), EMPTY, "1234",
		 * service.getAmount().toPlainString()); } }
		 */
		List<Receipt> receipts = paymentService.createPaymentPenaltyExtend(paymentDTO);

		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Service service : receipt.getServices()) {
				for (Transaction transaction : service.getTransactions()) {
					PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
					paymentSummary.setBalance(transaction.getAmount());
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			PaymentSummary paymentSummary = session.getPayType(method.getType());
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}

		userService.saveSession(session);

		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@RequestMapping(value = "findAllPrintingInvDisplay.json", method = RequestMethod.GET)
	public void findAllPrintingInvDisplayJSON(ModelMap modelMap) {
		Iterable<PrintingInvDisplay> printingInvDisplayList = printingInvDisplayRepository.findAll();
		List<MasterDataDTO> dataList = new ArrayList<MasterDataDTO>();
		if (printingInvDisplayList != null) {
			Map<String, String> groupMapping = new HashMap<String, String>();
			for (PrintingInvDisplay printingInvDisplay : printingInvDisplayList) {
				String key = printingInvDisplay.getInvDisplay();
				String value = groupMapping.get(key);
				if (value != null && value.length() > 0) {
					value = value + "," + printingInvDisplay.getBillGroup();
				} else {
					value = printingInvDisplay.getBillGroup();
				}
				groupMapping.put(key, value);
			}
			for (String key : groupMapping.keySet()) {
				String value = groupMapping.get(key);
				MasterDataDTO dto = new MasterDataDTO();
				dto.setKey(value);
				dto.setValue(key);
				dataList.add(dto);
			}
		}
		modelMap.addAttribute("data", dataList);
		modelMap.addAttribute("statusCode", "0");
	}

	@ResponseBody
	@RequestMapping(value = "findReceiptList.json", method = RequestMethod.GET)
	public List<Receipt> findReceiptListJSON(@RequestParam("cusNo") String cusNo,
			@RequestParam("payType") String payType) {
		List<Receipt> rcptList = new ArrayList<Receipt>();
		rcptList = paymentService.findByCusNoAndPayType(cusNo, payType);
		for (Receipt rpt : rcptList) {
			rpt.setDocDttmStr(AppUtil.formatter_EN.format(rpt.getDocDttm()) + " "
					+ AppUtil.formatter_EN_TIME.format(rpt.getDocDttm()));
			rpt.setUpdateDttmStr(AppUtil.formatter_EN.format(rpt.getUpdateDttm()) + " "
					+ AppUtil.formatter_EN_TIME.format(rpt.getUpdateDttm()));
			String maskCC = AppUtil.maskCreditCardFromString(rpt.getPayment().getMethod(), "************####");
			String paymentCase = AppUtil.hideWTNumber(maskCC);
			rpt.setPaymentCase(paymentCase);
			for (Service serv:rpt.getServices()) {
				rpt.setRef2(serv.getRefOrderId());
			}
		}
		return rcptList;
	}

	@ResponseBody
	@RequestMapping(value = "/exchangeRateList.json", method = RequestMethod.GET)
	public ExchangeRateDTO getAllExchangeRate() {

		ExchangeRateDTO dto = new ExchangeRateDTO();

		String queryStr = " SELECT * FROM MASCHANGERATE ORDER BY MASCHANGERATEID ASC ";

		final MasChangeRate macChgList = new MasChangeRate();

		// episJdbcTemplate.query(queryStr, new RowCallbackHandler()
		List<MasChangeRate> queryList = episJdbcTemplate.query(queryStr.toString(), new Object[] {},
				BeanPropertyRowMapper.newInstance(MasChangeRate.class));
		if (!CollectionUtils.isEmpty(queryList)) {
			dto.setData(queryList);
		} else {
			dto.getWarningList().add(new AlertMessage("10", "Can not find ExchangeRate "));
		}
		return dto;
	}

	@RequestMapping(value = "findCancelReasonPaymentList.json", method = RequestMethod.GET)
	public void findCancelReasonPaymentListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("reason.cancel.payment"));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "getReasonCancelPayOther.json", method = RequestMethod.GET)
	public void getReasonCancelPayOther(ModelMap modelMap) {
		modelMap.addAttribute("data", enumRepo.findByCategory("reason.cancel.payOther"));
		modelMap.addAttribute("statusCode", "0");
	}

	@ResponseBody
	@RequestMapping(value = "findCancelPenaltyExtend.json", method = RequestMethod.POST)
	public Page<Receipt> findCancelPenaltyExtendJSON(@RequestParam("payType") String payType,
			@RequestParam("no") String no) throws Exception {
		Page<Receipt> receipt = receiptRepo.findByNoStartingWith(no, null);
		for (Receipt rpt : receipt) {
			String paymentType = rpt.getPayment().getType();
			if (paymentType.equalsIgnoreCase(AppConstants.PAYMENT_TYPE_PENALTY_CHARGE)
					|| paymentType.equalsIgnoreCase(AppConstants.PAYMENT_TYPE_EXTEND_CHARGE)) {
			} else {
				receipt = null;
			}
		}

		return receipt;
	}

	@ResponseBody
	@RequestMapping(value = "createPaymentProductManual.json", method = RequestMethod.POST)
	public void createPaymentProductManual(@RequestBody SettlePaymentDTO settlePaymentDTO) {

	}

	@ResponseBody
	@RequestMapping(value="findPaymentHistoryReceipt.json", method=RequestMethod.GET)
	public PaymentHistoryDTO findPaymentHistoryReceipt(@Param("no") String no,@Param("invNo") String invNo, @Param("payType") String payType, Pageable p) throws SOAPException, IOException, UnsupportedOperationException, ParserConfigurationException, SAXException {
		PaymentHistoryDTO dto = new PaymentHistoryDTO();
		dto = paymentService.findPaymentHistoryReceipt(no, invNo, payType, p);
		return dto;
	}
	
	// for coupon server 
	@ResponseBody 
	@RequestMapping(value="checkStatusServer.json", method=RequestMethod.GET) 
		public String checkStatusServerJSON(@RequestParam("ip") String ip) { 
		return checkStatusServer(ip); 
	} 
	// for promotion server 
	@ResponseBody 
	@RequestMapping(value = "checkPromotionServerStatus.json", method = RequestMethod.GET) 
	public String checkPromotionServerStatus(@RequestParam("accounts") String accounts,@RequestParam("ip") String ip){ 
		//String accountNo=""; 
		String status="OK"; 
		List<String> accountList = Arrays.asList( accounts.split(",")); 
		if(accounts!=null && accounts.length()>0){ 
			List<PromotionMappingEntity> promotionMappingList= promotionMappingRepository.findInActivePromotionMappingNotInAccount(accountList); 
				if(promotionMappingList!=null && promotionMappingList.size()>0){ 
					status = checkStatusServer(ip); 
				} 
			} 
			return status; 
		} 
		private String checkStatusServer(String ip) { 
		String result =""; 
		try { 
			URL siteURL = new URL("http://"+ip); 
			HttpURLConnection connection = (HttpURLConnection) siteURL .openConnection(); 
			connection.setConnectTimeout(4000); 
			connection.setRequestMethod("GET"); 
			connection.connect(); 
		
			int code = connection.getResponseCode(); 
			if (code == 200) { 
				result = "OK"; 
			} 
		} catch (Exception e) { 
			result = "ERROR"; 
		} 
		return result; 
	}
		
	@ResponseBody 
	@RequestMapping(value = "checkServerStatusForCancel.json", method = RequestMethod.GET) 
	public String checkServerStatusForCancel(@RequestParam("ip") String ip,@RequestParam("no") String no){ 
		String status="OK"; 
		boolean isCP = false; 
		List<Receipt> receipts = receiptRepo.findByNo(no); 
		if(receipts!=null && receipts.size()>0){ 
			for(Receipt receipt :receipts){ 
				if(receipt.getPayment()!=null && receipt.getPayment().getMethods()!=null){ 
					Set<Method> methods = receipt.getPayment().getMethods(); 
					for(Method method :methods){ 
						if(method.getCode()!=null && method.getCode().equals("CP")){ 
							isCP = true; 
							break;	 
						} 
					} 
				} 
			} 
		} 
		if(isCP){ 
			status = checkStatusServer(ip); 
		} 

		if(status.equals("OK")){ 
			Long receiptId= null; 
			if(receipts!=null && receipts.size()>0){ 
				StringBuilder sb =new StringBuilder(); 
				for(Receipt receipt :receipts){ 
					receiptId = receipt.getId(); 
				} 
	
				List<String> accountList = Arrays.asList( sb.toString().split(",")); 
				if(receiptId!=null){ 
					PromotionReceiptMappingEntity promotionReceiptMappingEntity = promotionBillingMappingRepository.findPromotionReceiptMappingByReceiptid(receiptId); 
					if(promotionReceiptMappingEntity!=null){ 
						status = checkStatusServer(ip); 
					} 
				} 
			} 
		}
		return status; 
	}
	
}
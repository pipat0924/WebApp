package th.net.cat.epis.service;

import static com.google.common.collect.Iterables.find;

import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.leftPad;
import static org.apache.commons.lang.StringUtils.stripToEmpty;
import static org.apache.commons.lang.StringUtils.trimToEmpty;
import static th.net.cat.epis.util.AppConstants.AFTERSALES_DISCOUNT_METHOD;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_3TREDECIM;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_69BIS;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_69TRE;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_COMPENSATION;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_FEE_OUT;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_PENALTY_OUT;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_RETENTION_OUT;
import static th.net.cat.epis.util.AppConstants.EPIS_SYSTEM_ABVR;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_1;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_2;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_3;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_TBOSS;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_WRITEOFF;
import static th.net.cat.epis.util.AppConstants.INVOICE_OTHER_PAYMENT;
import static th.net.cat.epis.util.AppConstants.PAYMENT_TYPE_AGENT;
import static th.net.cat.epis.util.AppConstants.PAYMENT_TYPE_MNP;
import static th.net.cat.epis.util.AppConstants.PAYMENT_TYPE_OTHER;
import static th.net.cat.epis.util.AppConstants.PAYMENT_TYPE_SERVICE_CHARGE;
import static th.net.cat.epis.util.AppConstants.PAYMENT_TYPE_TBOSS;
import static th.net.cat.epis.util.AppConstants.PAYMENT_TYPE_TOPUP;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BANKTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BILLEXCHANGE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CHEQUE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_COUPON;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CREDITCARD;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_FOREIGNTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_MONEYORDER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_OFFSET;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_OTHER;
import static th.net.cat.epis.util.AppConstants.RECEIPT_FORMAT_WH_ONLY;
import static th.net.cat.epis.util.AppConstants.RECEIPT_HEADER_EPO;
import static th.net.cat.epis.util.AppConstants.RECEIPT_HEADER_MNP;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_RECEIVE_ONLY;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_WH_ONLY;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
import static th.net.cat.epis.util.AppConstants.RECEIPT_TYPE_ABBREVIATION;
import static th.net.cat.epis.util.AppConstants.RECEIPT_TYPE_FULL;
import static th.net.cat.epis.util.AppConstants.TAX_CODE_NONVAT;
import static th.net.cat.epis.util.AppConstants.RECEIPT_SUBSTITUTE;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_4;
import static th.net.cat.epis.util.AppConstants.RECEIPT_SUBSTITUTE_CANCEL;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.xml.sax.SAXException;

import com.google.common.base.Predicate;

import th.co.softpos.ws.mg.s002.S002MGUpdGiftvoucher;
import th.co.softpos.ws.mg.s004.S004MGUpdFree;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.dto.BillingInfo;
import th.net.cat.epis.dto.CancelPaymentDTO;
import th.net.cat.epis.dto.InvoiceProduct;
import th.net.cat.epis.dto.InvoiceProductDTO;
import th.net.cat.epis.dto.PaymentHistory;
import th.net.cat.epis.dto.PaymentHistoryDTO;
import th.net.cat.epis.dto.ReportPayment;
import th.net.cat.epis.dto.SettlePayment;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.dto.SubscriptionDTO;
import th.net.cat.epis.entity.AccountTBOSS;
import th.net.cat.epis.entity.Customer;
import th.net.cat.epis.entity.Deduct;
import th.net.cat.epis.entity.Document;
import th.net.cat.epis.entity.Enum;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.InvoiceVatDetail;
import th.net.cat.epis.entity.MapGLServiceTpye;
import th.net.cat.epis.entity.Method;
import th.net.cat.epis.entity.MethodBillExchange;
import th.net.cat.epis.entity.MethodCheque;
import th.net.cat.epis.entity.MethodCreditCard;
import th.net.cat.epis.entity.MethodMoneyOrder;
import th.net.cat.epis.entity.MethodMoneyTransfer;
import th.net.cat.epis.entity.MethodOther;
import th.net.cat.epis.entity.Ofset;
import th.net.cat.epis.entity.Payment;
import th.net.cat.epis.entity.PromotionMappingEntity;
import th.net.cat.epis.entity.PromotionReceiptMappingEntity;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.ReceiptEgpMappingEntity;
import th.net.cat.epis.entity.ReceiptLog;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.epis.entity.TrsAccountTBOSS;
import th.net.cat.epis.repo.AccountTBOSSRepository;
import th.net.cat.epis.repo.BillOfExchangRepository;
import th.net.cat.epis.repo.ChequeRepository;
import th.net.cat.epis.repo.CreditCardRepository;
import th.net.cat.epis.repo.CustomerRepository;
import th.net.cat.epis.repo.DeductionRepository;
import th.net.cat.epis.repo.DocumentRepository;
import th.net.cat.epis.repo.EnumRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.InvoiceVatDetailRepository;
import th.net.cat.epis.repo.MapGLServiceTypeRepository;
import th.net.cat.epis.repo.MoneyOrderRepository;
import th.net.cat.epis.repo.MoneyTransferRepository;
import th.net.cat.epis.repo.OTTBossRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.OfsetRepository;
import th.net.cat.epis.repo.OtherRepository;
import th.net.cat.epis.repo.PayMethodRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.PromotionBillingMappingRepository;
import th.net.cat.epis.repo.PromotionMappingRepository;
import th.net.cat.epis.repo.ReceiptEgpMappingRepository;
import th.net.cat.epis.repo.ReceiptLogRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.ServiceRepository;
import th.net.cat.epis.repo.TransactionRepository;
import th.net.cat.epis.repo.TrsAccountTBOSSRepository;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppConstants.S002_STATUS_TYPES;
import th.net.cat.epis.util.AppConstants.S004_STATUS_TYPES;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentRequest;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse;
import th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusRequest;
import th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse;
import th.net.cat.epis.ws.f05_retrieveservicestatus.ServiceStatusBO;
import th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffRequest;
import th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse;
//import th.net.cat.epis.ws.mnp02_createorderpaymentsi.mnp02_createorderpaymentelements.CreateOrderPaymentRequest;
//import th.net.cat.epis.ws.mnp02_createorderpaymentsi.mnp02_createorderpaymentelements.CreateOrderPaymentResponse;
//import th.net.cat.epis.ws.mnp02_createorderpaymentsi.mnp02_createorderpaymentelements.MobileNumberPayment;
//import th.net.cat.epis.ws.mnp02_createorderpaymentsi.mnp02_createorderpaymentelements.MobileNumberTransaction;
//import th.net.cat.epis.ws.mnp02_createorderpaymentsi.mnp02_createorderpaymentelements.ReceiptOrderInfo;
import th.net.cat.epis.ws.m03_createreceipt.CreateReceiptRequest;
import th.net.cat.epis.ws.m03_createreceipt.CreateReceiptResponse;
import th.net.cat.epis.ws.service.ESBWS_F04CreatePaymentService;
import th.net.cat.epis.ws.service.ESBWS_F05RetrieveServiceStatusService;
import th.net.cat.epis.ws.service.ESBWS_F10CreateWriteOffService;
import th.net.cat.epis.ws.service.MNPWS_MNP03CreateOrderPaymentService;

@org.springframework.stereotype.Service
public class PaymentService {
    private static Logger logger = Logger.getLogger(PaymentService.class);
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    DocumentRepository documentRepo;
    @Autowired
    InvoiceRepository invoiceRepo;
    @Autowired
    ServiceRepository serviceRepo;
    @Autowired
    OTTBossRepository ottBossRepo;
    @Autowired
    InvoiceVatDetailRepository invoiceVatRepository;
    @Autowired
    PayMethodRepository methodRepo;
    @Autowired
    ReceiptLogRepository receiptLogRepository;
    @Autowired
    MoneyTransferRepository moneyTransferRepo;
    @Autowired
    CreditCardRepository creditCardRepo;
    @Autowired
    ChequeRepository chequeRepository;
    @Autowired
    DeductionRepository deductRepo;
    @Autowired
    PaymentRepository paymentRepo;
    @Autowired
    TransactionRepository transactionRepo;
    @Autowired
    ReceiptRepository receiptRepo;
    @Autowired
    OfficerRepository officerRepo;
    @Autowired
    AccountTBOSSRepository accountTBOSSRepo;
    @Autowired
    TrsAccountTBOSSRepository trsAccountTBOSSRepo;
    @Autowired
    ESBWS_F04CreatePaymentService _f04CreatePaymentService;
    @Autowired
    ESBWS_F10CreateWriteOffService _f10CreateWriteOffService;
    @Autowired
    MNPWS_MNP03CreateOrderPaymentService _mnp03CreateOrderPaymentService;
    @Autowired
    ESBWS_F05RetrieveServiceStatusService _f05RetrieveServiceStatusService;
    @Autowired
    EnumRepository enumRepo;
    @Autowired
    ReceiptEgpMappingRepository egpMapRepo;
    @Autowired
    DWService  dwService;
    @Autowired
    EpisService episService;
    @Autowired
    S002MGUpdGiftvoucher s002MGUpdGiftvoucher;
    @Autowired
    PromotionMappingRepository promotionMappingRepository;
    @Autowired
    PromotionBillingMappingRepository promotionBillingMappingRepository;
    @Autowired
    S004MGUpdFree s004MGUpdFree;
    @Resource(name = "episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;
    @Autowired
    MapGLServiceTypeRepository glServiceTypeRepository;
    @Autowired
    MoneyOrderRepository moneyOrderRepository;
    @Autowired
    BillOfExchangRepository  billOfExchangRepository;
    @Autowired
    OtherRepository otherRepository;
    @Autowired
    OfsetRepository ofsetRepository;
 

    private final String MNP_SERVICE = "MNP-SERVICE";
    private final List<String> MINUS_LIST = Arrays.asList(DEDUCT_METHOD_3TREDECIM, DEDUCT_METHOD_69BIS,
            DEDUCT_METHOD_69TRE, DEDUCT_METHOD_PENALTY_OUT, DEDUCT_METHOD_FEE_OUT, DEDUCT_METHOD_RETENTION_OUT,
            DEDUCT_METHOD_COMPENSATION);

    public static final BigDecimal VAT_RATE = new BigDecimal("7");

    public String getReceiptNo(String posNo, String receiptType, Date demandDate, String header) throws Exception {
        String branchArea = EpContextHolder.getContext().getBranchArea(), currentDate = FastDateFormat
                .getInstance("yyMMdd", ENGLISH).format((demandDate != null) ? demandDate : new Date());

        synchronized (branchArea.intern()) {
            Document document = null;
            try {
                document = documentRepo.findByTypeAndBranchAreaAndDateAndHeader(receiptType, branchArea, currentDate,
                        header);
            } catch (Exception e) {

            }
            if (document == null) {
                document = documentRepo.save(new Document());
                document.setHeader(header);
                document.setType(receiptType);
                document.setBranchArea(branchArea);
                document.setDate(currentDate);
                document.setCount(0);
            }
            document.setCount(document.getCount() + 1);
            // return new StringBuilder(header).append(leftPad(branchArea, 5,
            // "0")).append(leftPad(posNo, 2,
            // "0")).append(receiptType).append(currentDate).append(leftPad(document.getCount().toString(),
            // 4, "0")).toString();//by NSD 11-01-2016
            return new StringBuilder(header).append(leftPad(branchArea, 4, "0")).append(leftPad(posNo, 2, "0"))
                    .append(receiptType).append(currentDate).append(leftPad(document.getCount().toString(), 4, "0"))
                    .toString();// by NSD 21-03-2017
        }
    }

    @Transactional
    public List<Receipt> createPaymentInvoice(SettlePaymentDTO paymentDTO) throws Exception   {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        MethodCheque chequePay = new MethodCheque();
        MethodCreditCard creditCardPay = new MethodCreditCard();
        MethodMoneyTransfer moneyTransfer = new MethodMoneyTransfer();
        MethodMoneyOrder moneyOrder = new MethodMoneyOrder();
        MethodBillExchange billExchange = new MethodBillExchange();
        Ofset oFset = new Ofset();
        MethodOther otherPay = new MethodOther();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Invoice invoice;
        InvoiceVatDetail invoiceVatDetail;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Customer> episCustomers = null;
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();
        Long receiptID = null;

        ReceiptEgpMappingEntity egpMap = null;
        List<ReceiptEgpMappingEntity> egpMaps = new ArrayList<ReceiptEgpMappingEntity>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userLogin = EpContextHolder.getContext().getUserName();
        String language = paymentDTO.getLanguage();

        for (

                SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
            paids.add(new Paid(ded.getAmount(), ded.getType()));
            deduct = deductRepo.save(new Deduct());
            deduct.setUpdateDttm(date);
            deduct.setUpdateUser(userName);
            deduct.setNo(ded.getWithholdingDocNo());
            deduct.setType(ded.getType());
            deduct.setAmount(ded.getAmount());
            deduct.setDate(date);
            deduct.setPaymentId(payment.getId());
            deduct.setInvoiceNo(ded.getInvoiceNo());
            payment.getDeducts().add(deduct);
        }
        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            if (met != null && met.getType() != null) {
                Paid paid = new Paid(met.getAmount(), met.getType());
                paids.add(paid); // Preparing: To substract into invoice.
                Method method = methodRepo.save(new Method());
                method.setUpdateDttm(date);
                method.setUpdateUser(userName);
                for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
                    if ("Billing".equals(cust.getSouceType())) {
                        method.setCode("CC");
                    } else {
                        method.setCode(met.getCode());
                    }
                }
                method.setName(met.getName());
                method.setChequeNo(StringUtils.isNotEmpty(met.getChequeNo())?met.getChequeNo():met.getDocNo());
                method.setAccountNo(met.getBankAcct());
                method.setAmount(met.getAmount());
                method.setPaymentId(payment.getId());

                if (PAY_METHOD_BANKTRANSFER.equals(met.getType())
                        || PAY_METHOD_FOREIGNTRANSFER.equals(met.getType())) { // Money
                    // Transfer
                	 moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                	 moneyTransfer.setAmount(met.getAmount());
                     moneyTransfer.setUpdateDttm(date);
                     moneyTransfer.setUpdateUser(userName);
                     moneyTransfer.setDate(met.getTransferDt());
                     moneyTransfer.setPayCode(met.getCode());
                     moneyTransfer.setPayType(met.getType());
                     moneyTransfer.setBankCode(met.getBankCode());
                     moneyTransfer.setBankAcCd(met.getBankAcCd());
                     moneyTransfer.setBankName(met.getBankName());
                     moneyTransfer.setRefNo(met.getRefNo());
                     moneyTransfer.setBankBrnh(met.getBankBrnh());
                     moneyTransfer.setBankAcNo(met.getBankAcctNo());
                     moneyTransfer.setCurrencyCode(met.getCurrencyCode());
                     moneyTransfer.setCurrencyRate(met.getCurrencyRate());
                     moneyTransfer.setForeignAmount(met.getForeignAmount());
                     moneyTransfer.setPaymentId(payment.getId());
                     moneyTransfer.setMethodId(method.getId());
                     paid.setMoneyTransfer(moneyTransfer);
                     paid.setIsBackDate(met.isBackDt());
                } else if (PAY_METHOD_CHEQUE.equals(met.getType())) {
                    // TODO: complete all the saving methods and pulling them to
                    // print correctly
                	chequePay = chequeRepository.save(new MethodCheque());
                    chequePay.setAmount(met.getAmount());
                    chequePay.setBankCode(met.getPayChqBankCode());
                    chequePay.setBankName(met.getPayChqBankName());
                    chequePay.setBankBrnh(met.getPayChqBranch());
                    chequePay.setChequeDate(met.getPayChqDate());
                    chequePay.setNo(met.getChequeNo());
                    chequePay.setUpdateUser(userName);
                    chequePay.setUpdateDttm(date);
                    chequePay.setPaymentId(payment.getId());
                    chequePay.setMethodId(method.getId());
                } else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
                    // Fix by EPIS8 30/12/2016 issue no 166
                	creditCardPay = creditCardRepo.save(new MethodCreditCard());
                    creditCardPay.setPaymentId(payment.getId());
                    creditCardPay.setNo(met.getCardNo());
                    creditCardPay.setAmount(met.getAmount());
                    creditCardPay.setBankIssuer(met.getBankName());
                    creditCardPay.setType(met.getCardType());
                    creditCardPay.setUpdateDttm(date);
                    creditCardPay.setUpdateUser(userName);
                    creditCardPay.setMethodId(method.getId());
                    // End Fix by EPIS8 30/12/2016 issue no 166
                } else if (PAY_METHOD_MONEYORDER.equals(met.getType())) {
                    moneyOrder = moneyOrderRepository.save(new MethodMoneyOrder());
                    moneyOrder.setNo(met.getMnyOrderNo());
                    moneyOrder.setAmount(met.getAmount());
                    moneyOrder.setDate(met.getMnyOrderDt());
                    moneyOrder.setPostCode(met.getPostCode());
                    moneyOrder.setUpdateDttm(date);
                    moneyOrder.setUpdateUser(userName);
                    moneyOrder.setPaymentId(payment.getId());
                    moneyOrder.setMethodId(method.getId());
                } else if (PAY_METHOD_BILLEXCHANGE.equals(met.getType())) {
                    billExchange = billOfExchangRepository.save(new MethodBillExchange());
                    billExchange.setNo(met.getTrnfNo());
                    billExchange.setAmount(met.getAmount());
                    billExchange.setDate(met.getTransferDt());
                    billExchange.setPostCode(met.getPostCode());
                    billExchange.setUpdateDttm(date);
                    billExchange.setUpdateUser(userName);
                    billExchange.setPaymentId(payment.getId());
                    billExchange.setMethodId(method.getId());
                } else if (PAY_METHOD_COUPON.equals(met.getType())) {
                    th.co.softpos.ws.mg.s002.RqHeader rqHeader = new th.co.softpos.ws.mg.s002.RqHeader();
                    rqHeader.setFuncNm("S002MG");
                    rqHeader.setRqAppId("POS");
                    rqHeader.setUserId("9999");
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTime(new Date());
                    try {
                        rqHeader.setRqDt(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
                    } catch (DatatypeConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    th.co.softpos.ws.mg.s002.RqBody rqBody = new th.co.softpos.ws.mg.s002.RqBody();
                    th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher giftvoucher =
                            new th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher();
                    giftvoucher.setCode(met.getCouponNo());
                    giftvoucher.setStatus("S");
                    rqBody.setMgTempImportDetailGiftvoucher(giftvoucher);
                    th.co.softpos.ws.mg.s002.Request _process_rq = new th.co.softpos.ws.mg.s002.Request();
                    _process_rq.setRqHeader(rqHeader);
                    _process_rq.setRqBody(rqBody);
                    th.co.softpos.ws.mg.s002.Response _response = s002MGUpdGiftvoucher.process(_process_rq);
                    method.setChequeNo(met.getCouponNo());
                } else if (PAY_METHOD_OFFSET.equals(met.getType())) {
                	oFset = ofsetRepository.save(new Ofset());
                    oFset.setAmount(met.getAmount());
                    oFset.setNo(met.getOffsetDocumentNo());
                    oFset.setOfsetcode(met.getOffsetAccountCode());
                    oFset.setOfsetname(met.getOffsetAccountName());
                    oFset.setUpdateDttm(date);
                    oFset.setUpdateUser(userName);
                    oFset.setPaymentId(payment.getId());
                    oFset.setMethodId(method.getId());
                } else if (PAY_METHOD_OTHER.equals(met.getType())) {
                    otherPay = otherRepository.save(new MethodOther());
                    otherPay.setNo(met.getOtherNo());
                    otherPay.setType(met.getOtherType());
                    otherPay.setDate(met.getOtherDt());
                    otherPay.setUpdateDttm(date);
                    otherPay.setUpdateUser(userName);
                    otherPay.setPaymentId(payment.getId());
                    otherPay.setMethodId(method.getId());
                } 
                payment.getMethods().add(method);
            }
        }
        
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            episCustomers = null;
            episCustomers = customerRepo.findByNo(cust.getCustNo());
            if (CollectionUtils.isEmpty(episCustomers)) {
                customer = customerRepo.save(new Customer());
            } else {
                customer = episCustomers.get(0);
                customerRepo.save(customer);
            }
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1());
            customer.setInvoiceAddr(cust.getAddress2());
            customer.setAcctCatLkp(cust.getAcctCatLkp());// by NSD 02-03-2017
            customer.setCatCustomerSegment(cust.getCatCustomerSegment());

            // Additional Conditions for GFMIS, date and branch
            String customerType = stripToEmpty(customer.getType()).toLowerCase();
            // if (customerType.indexOf("organization") == 0 ||
            // customerType.indexOf("stateagency") == 0) {
            customer.setBranch(cust.getCustBranch());
            // }
            for (Paid paid : paids) {
                if (paid.getMoneyTransfer() != null & paid.isBackDate()) {
                    receiptDttm = paid.getMoneyTransfer().getDate();
                    break;
                }
            }
            if (paymentDTO.isBackDate()) {
            	receiptDttm = paymentDTO.getTransferDt();
                break;
            }
            
            boolean split = cust.getSplit();
            if (!split) {
                if (cust.getInvoices().size() < 1)
                    continue;
                receipt = receiptRepo.save(new Receipt());
                receiptID = receipt.getId();
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);
                receipt.setFlgHeader(FLG_HEADER_2);
                //Set posID And posNo
                receipt.setPosid(EpContextHolder.getContext().getPosId());
                receipt.setPosno(EpContextHolder.getContext().getPosNo());
                Map<String, String> accountSubNoMap = new HashMap<String, String>();
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    invoice = invoiceRepo.save(new Invoice());
                    invoice.setUpdateDttm(date);
                    invoice.setUpdateUser(userName);
                    receipt.getInvoices().add(invoice);
                    Service service = serviceRepo.save(new Service());
                    service.setUpdateDttm(date);
                    service.setReceiptId(receipt.getId());
                    service.setInvoiceId(invoice.getId());
                    service.setAccountNo("1234567890");
                    service.setProductCode("102020003");
                    service.setProductName("prod name");
                    service.setProductSubCode("sub code");
                    service.setProductSubName("sub name");
                    service.setIncomeType("1");
                    service.setAmount(inv.getReceived());
                    service.setAfterSaleDiscount(inv.getAfterSaleDiscount() != null
                            ? inv.getAfterSaleDiscount().add(
                            inv.getAfterSaleDiscVat() != null ? inv.getAfterSaleDiscVat() : BigDecimal.ZERO)
                            : BigDecimal.ZERO);
                    receipt.getServices().add(service);
                    invoice.getServices().add(service);
                    addTransactionsIntoService(paids, payment, invoice, date, userName);
                    invoice.setReceiptId(receipt.getId());
                    invoice.setCustomer(customer);
                    invoice.setPayment(payment);
                    invoice.setNo(inv.getNo());
                    invoice.setKenan(inv.getKenan());
                    invoice.setCurrencyCode(inv.getCurrencyCode());
                    invoice.setIssueDt(inv.getIssueDt());
                    invoice.setDueDt(inv.getDueDt());
                    invoice.setBillCycle(inv.getBillCycle());
                    invoice.setAmount(inv.getAmount());
                    invoice.setDiscount(inv.getDiscount());
                    invoice.setCharge(inv.getCharge());
                    if (!StringUtils.equals(inv.getStatus(), INVOICE_OTHER_PAYMENT)
                            || inv.getVat().compareTo(new BigDecimal(0)) == 1) {
                        invoice.setVat(inv.getVat());
                        invoice.setVatRate(inv.getVatRate());
                        if (!StringUtils.equals(inv.getTaxTypeCode(), TAX_CODE_NONVAT)
                                || inv.getVat().compareTo(new BigDecimal(0)) == 1) {
                            receipt.setFlgHeader(FLG_HEADER_1);
                        }
                    } else {
                        invoice.setVat(null);
                        invoice.setVatRate(null);
                    }
                    invoice.setTotalCharge(inv.getReceived());
                    invoice.setDeduction(inv.getDeduction());
                    invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
                    invoice.setBalanceDue(inv.getBalanceDue());
                    invoice.setReceived(inv.getReceived());
                    invoice.setAfterSaleDiscVat(inv.getAfterSaleDiscVat());// by
                    // NSD
                    // 05-04-2017
                    invoice.setChange(inv.getChange());
                    invoice.setAdvanced(inv.getAdvanced());
                    invoice.setDebtAmount(inv.getTotalCharge());
                    invoice.setStatus(inv.getStatus());
                    invoice.setDiscountType(inv.getDiscountType());// by NSD
                    // 04-04
                    invoice.setDiscApprUser(inv.getDiscApprUser());
                    if("Billing".equals(paymentDTO.getCustomers().get(0).getSouceType())){
                    	invoice.setUpdateUser(paymentDTO.getUpdaetBy());
                    	invoice.setUpdateDttm(paymentDTO.getUpdateDate());
                    }
                    String subNoInv = "";
                    if (!CollectionUtils.isEmpty(inv.getSubNoList())) {
                        for (String subno : inv.getSubNoList()) {
                            accountSubNoMap.put(subno, subno);
                            subNoInv += subno + "|";
                        }
                    }
                    invoice.setSubNo(subNoInv);

                    // invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO)
                    // == 0 ?
                    // (inv.getTotalCharge().compareTo(invoice.getReceived()) !=
                    // 0 ? "P" : "F") : "P");//Commented by EPIS4
                    invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0 ? "F" : "P");

                    System.out.println("# PART 1 receipt.getId() = " + receipt.getId());
                    System.out.println("# PART 1 invoice.getReceiptId() = " + invoice.getReceiptId());
                    String paymentCase = "";
                    for (SettlePaymentDTO.Invoice inv2 : cust.getInvoices()) {
                        if (receipt.getId() == invoice.getReceiptId()) {
                            if (inv2.getPaymentCase() != null && inv2.getPaymentCase().indexOf("+") > 0) {
                                String paymentCaseArr[] = inv2.getPaymentCase().split("\\+");
                                for (int i = 0; i < paymentCaseArr.length; i++) {
                                    if (paymentCaseArr[paymentCaseArr.length - 1].length() <= 1) { // Remove
                                        // "+"
                                        // last
                                        // index
                                        paymentCase = inv2.getPaymentCase().substring(0,
                                                inv2.getPaymentCase().length() - 3);
                                    } else {
                                        paymentCase = inv2.getPaymentCase();
                                    }
                                }
                                receipt.setPaymentCase(paymentCase);
                            } else {
                                receipt.setPaymentCase(inv2.getPaymentCase());
                            }
                        }
                    }
					/*
					 * if(!CollectionUtils.isEmpty(inv.getSubNoList())) {
					 * for(String subno:inv.getSubNoList()){
					 * accountSubNoMap.put(subno,subno); } }
					 */ // by NSD 23-03-2017
                    if (!CollectionUtils.isEmpty(inv.getInvoiceVatDetails())) {
                        for (SettlePaymentDTO.InvoiceVatDetail invDtl : inv.getInvoiceVatDetails()) {
                            invoiceVatDetail = invoiceVatRepository.save(new InvoiceVatDetail());
                            invoiceVatDetail.setUpdateDttm(date);
                            invoiceVatDetail.setUpdateUser(userName);
                            invoice.getInvoiceVatDetails().add(invoiceVatDetail);
                            invoiceVatDetail.setInvoiceId(invoice.getId());
                            invoiceVatDetail.setInvoiceNo(invDtl.getInvoiceNo());
                            invoiceVatDetail.setAccountNo(invDtl.getAccountNo());
                            invoiceVatDetail.setAmount(invDtl.getAmount());
                            invoiceVatDetail.setVat(invDtl.getVat());
                            invoiceVatDetail.setTaxTypeCode(invDtl.getTaxTypeCode());

                        }
                    }
                }
                BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                        rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                        rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO,
                        rcpWt = BigDecimal.ZERO, rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO,
                        rcpAfterSaleDiscVat = BigDecimal.ZERO;
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    rcpAmount = rcpAmount.add(inv.getAmount());
                    rcpDiscount = rcpDiscount.add(inv.getDiscount());
                    rcpCharge = rcpCharge.add(inv.getCharge());
                    rcpVat = rcpVat.add(inv.getVat());
                    rcpDeduction = rcpDeduction.add(inv.getDeduction());
                    rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(inv.getAfterSaleDiscount());
                    rcpBalanceDue = rcpBalanceDue.add(inv.getBalanceDue());
                }
                for (Invoice inv : receipt.getInvoices()) {
                    rcpTotalCharge = rcpTotalCharge.add(inv.getTotalCharge());
                    rcpReceived = rcpReceived.add(inv.getReceived());
                    if (null != inv.getAfterSaleDiscVat()) {// by NSD 05-04-2017
                        rcpAfterSaleDiscVat = rcpAfterSaleDiscVat.add(inv.getAfterSaleDiscVat());
                    }
                }
                rcpChange = rcpReceived.subtract(rcpTotalCharge);
                receipt.setCustomer(customer);
                receipt.setPayment(payment);
                receipt.setType(getReceiptType2(customer));
                String receiptType = "";
                if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_FORMAT_WH_ONLY)) {// by
                    // NSD
                    // 24-04-2017
                    receipt.setFlgHeader(FLG_HEADER_3);
                }
                if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_SUBSTITUTE)) {// by
                  
                    receipt.setFlgHeader(FLG_HEADER_4);
                }
                if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1)) {
                    if (RECEIPT_TYPE_FULL.equals(receipt.getType())) {
                        receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
                    } else {
                        receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
                    }
                } else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)) {
                    receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY;
                }else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_4)) {
                	   receiptType =  RECEIPT_SUBSTITUTE_CANCEL;
                } else {
                    receiptType = RECEIPT_NO_FLAG_WH_ONLY;
                }
              
                if ("Billing".equals(cust.getSouceType())) {
                    receipt.setNo(cust.getEpNameCode());
                } else {
                    receipt.setNo(getReceiptNo(posNo, receiptType, receiptDttm, RECEIPT_HEADER_EPO));
                }
                receipt.setName(cust.getCustName());
                receipt.setAccountName(cust.getCustName());
                receipt.setAccountNo(cust.getCustNo());

                // by NSD 24-04-2017
                Date dtFrom = receiptDttm;
                Calendar c = Calendar.getInstance();
                c.setTime(dtFrom);
                c.add(Calendar.DATE, 1);
                Date dtTo = c.getTime();
                // List<Receipt> rcptList =
                // receiptRepo.findByTypeAndBranchAreaAndFlgHeaderAndDocDttmOrderByNoDesc(receipt.getType(),branchArea,
                // receipt.getFlgHeader(), dtFrom, dtTo);
                List<Receipt> rcptList = receiptRepo.findBackDateReceiptList(receipt.getType(), branchArea,
                        receipt.getFlgHeader(), new java.sql.Date(dtFrom.getTime()), new java.sql.Date(dtTo.getTime()));
                BeanComparator fieldComparator = new BeanComparator("no");
                Collections.sort(rcptList, fieldComparator);

                if ("Billing".equals(cust.getSouceType())) {
                    receipt.setDocDttm(cust.getPaymentDate());
                } else {
                    if (rcptList != null && rcptList.size() > 0) {
                        receiptDttm = rcptList.get(rcptList.size() - 1).getDocDttm();
                        receipt.setDocDttm(receiptDttm);
                    }
                }
                String subNo = "";

                if (accountSubNoMap.size() > 1) {
                    subNo = String.valueOf(accountSubNoMap.size());
                } else if (accountSubNoMap.size() == 1) {
                    Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
                    subNo = entry.getValue();
                } else {
                    subNo = cust.getCustSubNo();
                }
                // receipt.setAccountSubNo(cust.getCustSubNo());
                receipt.setAccountSubNo(subNo);
                receipt.setAccountType(cust.getCustType());
                receipt.setTaxNo(cust.getTaxNo());
                // receipt.setAccountBranch(customer.getBranch());
                receipt.setAccountBranch(cust.getCustBranch());
                receipt.setAddrLine1(customer.getReceiptAddr());
                receipt.setAddrLine2(customer.getInvoiceAddr());

                // receipt.setPaymentCase(paymentDTO.getPaymentCase());
                receipt.setBranchCode(branchCode);
                receipt.setBranchArea(branchArea);
                receipt.setBranchName(branchName);
                receipt.setAmount(rcpAmount);
                receipt.setDiscount(rcpDiscount);
                receipt.setCharge(rcpCharge);
                receipt.setVatRate(VAT_RATE);
                receipt.setVat(rcpVat);
                receipt.setTotalCharge(rcpTotalCharge);
                receipt.setDeduction(rcpDeduction);
                receipt.setWtAmt(cust.getWtAmt());
                receipt.setRetentionAmt(cust.getRetentionAmt());
                receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
                receipt.setBalanceDue(rcpBalanceDue);
                receipt.setReceived(rcpReceived);
                receipt.setAfterSaleDiscVat(rcpAfterSaleDiscVat);// by NSD
                // 05-04-2017
                receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
                receipt.setAdvanced(null);
                receipt.setPromotion(null);
                receipt.setRemark(cust.getRemark());
                receipt.setReprint(0L);

                // sumAfterSaleDisc =
                // sumAfterSaleDisc.add(rcpAfterSaleDiscount!=null?rcpAfterSaleDiscount:BigDecimal.ZERO).add(rcpAfterSaleDiscVat!=null?rcpAfterSaleDiscVat:BigDecimal.ZERO);

                if (rcpReceived.doubleValue() < balanceDue.doubleValue()) {
                    receipt.setAttributes("S");
                } else {
                    receipt.setAttributes("F");
                }

                receipt.setBillingGroup(cust.getBillGroup());
                receipt.setBillingGroupFull(cust.getBillGroup());
                receipt.setBillingServiceName(cust.getInvoiceDisplay());// by
                // NSD
                // 24-03-2017
                receipt.setLanguage(language);
                for (Method method : payment.getMethods()) {
                    method.setRecieptId(receiptID);
                }
                receipts.add(receipt);
                saveLogCorReceipt(receipt, receipt.getAttributes());
                amount = amount.add(rcpAmount);
                discount = discount.add(rcpDiscount);
                charge = charge.add(rcpCharge);
                vat = vat.add(rcpVat);
                totalCharge = totalCharge.add(rcpTotalCharge);
                balanceDue = balanceDue.add(rcpBalanceDue);
                afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
                deduction = deduction.add(rcpWt);
            } else {
                BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                        rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpBalanceDue = BigDecimal.ZERO,
                        rcpAfterSaleDiscount = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                        rcpAfterSaleDiscVat = BigDecimal.ZERO;
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    // Fix by EPIS8 30/12/2016 issue no 59, 131
                    // rcpAmount = rcpAmount.add(inv.getAmount());
                    // rcpDiscount = rcpDiscount.add(inv.getDiscount());
                    // rcpCharge = rcpCharge.add(inv.getCharge());
                    // rcpVat = rcpVat.add(inv.getVat());
                    // rcpTotalCharge =
                    // rcpTotalCharge.add(inv.getTotalCharge());
                    // rcpAfterSaleDiscount =
                    // rcpAfterSaleDiscount.add(inv.getAfterSaleDiscount());
                    // rcpBalanceDue = rcpBalanceDue.add(inv.getBalanceDue());
                    // rcpDeduction = rcpDeduction.add(inv.getDeduction());
                    rcpAmount = inv.getAmount();
                    rcpDiscount = inv.getDiscount();
                    rcpCharge = inv.getCharge();
                    rcpVat = inv.getVat();
                    rcpTotalCharge = inv.getTotalCharge();
                    rcpAfterSaleDiscount = inv.getAfterSaleDiscount();
                    rcpAfterSaleDiscVat = inv.getAfterSaleDiscVat();
                    rcpBalanceDue = inv.getBalanceDue();
                    rcpDeduction = inv.getDeduction();
                    // End Fix by EPIS8 30/12/2016 issue no 59, 131
                    receipt = receiptRepo.save(new Receipt());
                    receiptID = receipt.getId();
                    receipt.setUpdateDttm(date);
                    receipt.setUpdateUser(userName);
                    receipt.setDocDttm(receiptDttm);
                    receipt.setFlgHeader(FLG_HEADER_2);
                    invoice = invoiceRepo.save(new Invoice());
                    invoice.setUpdateDttm(date);
                    invoice.setUpdateUser(userName);
                    Service service = serviceRepo.save(new Service());
                    service.setUpdateDttm(date);
                    service.setReceiptId(receipt.getId());
                    service.setInvoiceId(invoice.getId());
                    service.setAccountNo("1234567890");
                    service.setProductCode("102020003");
                    service.setProductName("prod name");
                    service.setProductSubCode("sub code");
                    service.setProductSubName("sub name");
                    service.setIncomeType("1");
                    service.setAmount(inv.getReceived());// service.setAmount(inv.getReceived().subtract(inv.getAfterSaleDiscount()));//by
                    // NSD 04-05-2017
                    service.setAfterSaleDiscount(inv.getAfterSaleDiscount() != null
                            ? inv.getAfterSaleDiscount().add(
                            inv.getAfterSaleDiscVat() != null ? inv.getAfterSaleDiscVat() : BigDecimal.ZERO)
                            : BigDecimal.ZERO);
                    receipt.getServices().add(service);
                    invoice.getServices().add(service);
                    addTransactionsIntoService(paids, payment, invoice, date, userName);
                    invoice.setReceiptId(receipt.getId());
                    invoice.setCustomer(customer);
                    invoice.setPayment(payment);
                    invoice.setNo(inv.getNo());
                    invoice.setKenan(inv.getKenan());
                    invoice.setCurrencyCode(inv.getCurrencyCode());
                    invoice.setIssueDt(inv.getIssueDt());
                    invoice.setDueDt(inv.getDueDt());
                    invoice.setBillCycle(inv.getBillCycle());
                    invoice.setAmount(inv.getAmount());
                    invoice.setDiscount(inv.getDiscount());
                    invoice.setCharge(inv.getCharge());
                    if (!StringUtils.equals(inv.getStatus(), INVOICE_OTHER_PAYMENT)
                            || inv.getVat().compareTo(new BigDecimal(0)) == 1) {// by
                        // NSD
                        // 02-04-2017
                        invoice.setVatRate(VAT_RATE);
                        invoice.setVat(inv.getVat());
                        if (!StringUtils.equals(inv.getTaxTypeCode(), TAX_CODE_NONVAT)
                                || inv.getVat().compareTo(new BigDecimal(0)) == 1) {
                            receipt.setFlgHeader(FLG_HEADER_1);
                        }
                    } else {
                        invoice.setVat(null);
                        invoice.setVatRate(null);
                    }
                    invoice.setDiscApprUser(inv.getDiscApprUser());
                    invoice.setTotalCharge(inv.getReceived());
                    invoice.setDeduction(inv.getDeduction());
                    invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
                    invoice.setAfterSaleDiscVat(inv.getAfterSaleDiscVat());// by
                    // NSD
                    // 03-05-2017
                    invoice.setBalanceDue(inv.getBalanceDue());
                    invoice.setReceived(inv.getReceived());
                    invoice.setChange(inv.getChange());
                    invoice.setAdvanced(inv.getAdvanced());
                    invoice.setStatus(inv.getStatus());
                    invoice.setDiscountType(inv.getDiscountType());// by NSD
                    // 04-04
                    invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0
                            ? (inv.getTotalCharge().compareTo(invoice.getReceived()) != 0 ? "P" : "F") : "P");
                    // by NSD 23-03-2017
                    Map<String, String> accountSubNoMap = new HashMap<String, String>();
                    String subNoInv = "";
                    if (!CollectionUtils.isEmpty(inv.getSubNoList())) {
                        for (String subno : inv.getSubNoList()) {
                            accountSubNoMap.put(subno, subno);
                            subNoInv = subno + "|";
                        }
                    }

                    if (!CollectionUtils.isEmpty(inv.getInvoiceVatDetails())) {
                        for (SettlePaymentDTO.InvoiceVatDetail invDtl : inv.getInvoiceVatDetails()) {
                            invoiceVatDetail = invoiceVatRepository.save(new InvoiceVatDetail());
                            invoiceVatDetail.setUpdateDttm(date);
                            invoiceVatDetail.setUpdateUser(userName);
                            invoice.getInvoiceVatDetails().add(invoiceVatDetail);
                            invoiceVatDetail.setInvoiceId(invoice.getId());
                            invoiceVatDetail.setInvoiceNo(invDtl.getInvoiceNo());
                            invoiceVatDetail.setAccountNo(invDtl.getAccountNo());
                            invoiceVatDetail.setAmount(invDtl.getAmount());
                            invoiceVatDetail.setVat(invDtl.getVat());
                            invoiceVatDetail.setTaxTypeCode(invDtl.getTaxTypeCode());
                        }
                    }

                    invoice.setSubNo(subNoInv);

                    receipt.getInvoices().add(invoice);
                    receipt.setCustomer(customer);
                    receipt.setPayment(payment);
                    receipt.setType(getReceiptType2(customer));
                    String receiptType = "";
                    if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_FORMAT_WH_ONLY)) {// by
                        // NSD
                        // 24-04-2017
                        receipt.setFlgHeader(FLG_HEADER_3);
                    }
                    if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_SUBSTITUTE)) {// by
                        
                        receipt.setFlgHeader(FLG_HEADER_4);
                    }
                    
                    if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1)) {
                        if (RECEIPT_TYPE_FULL.equals(receipt.getType())) {
                            receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
                        } else {
                            receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
                        }
                    } else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)) {
                        receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY;
                    } else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_4)) {
                 	   receiptType =  RECEIPT_SUBSTITUTE_CANCEL;
                    } else {
                        receiptType = RECEIPT_NO_FLAG_WH_ONLY;
                    }
                    receipt.setNo(getReceiptNo(posNo, receiptType, receiptDttm, RECEIPT_HEADER_EPO));

                    // by NSD 24-04-2017
                    Date dtFrom = receiptDttm;
                    Calendar c = Calendar.getInstance();
                    c.setTime(dtFrom);
                    c.add(Calendar.DATE, 1);
                    Date dtTo = c.getTime();
                    List<Receipt> rcptList = receiptRepo.findByTypeAndBranchAreaAndFlgHeaderAndDocDttmOrderByNoDesc(
                            receipt.getType(), branchArea, receipt.getFlgHeader(), dtFrom, dtTo);
                    BeanComparator fieldComparator = new BeanComparator("no");
                    Collections.sort(rcptList, fieldComparator);
                    if (rcptList != null && rcptList.size() > 0) {
                        receiptDttm = rcptList.get(rcptList.size() - 1).getDocDttm();
                        receipt.setDocDttm(receiptDttm);
                    }
                    receipt.setName(cust.getCustName());
                    receipt.setAccountName(cust.getCustName());
                    receipt.setAccountNo(cust.getCustNo());
					/*
					 * Map<String,String> accountSubNoMap = new
					 * HashMap<String,String>();
					 * if(!CollectionUtils.isEmpty(inv.getSubNoList())) {
					 * for(String subno:inv.getSubNoList()){
					 * accountSubNoMap.put(subno,subno); } }
					 */
                    String subNo = "";
                    if (accountSubNoMap.size() > 1) {
                        subNo = String.valueOf(accountSubNoMap.size());
                    } else if (accountSubNoMap.size() == 1) {
                        Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
                        subNo = entry.getValue();
                    } else {
                        // subNo = cust.getCustSubNo();
                    }

                    receipt.setAccountSubNo(subNo);
                    // receipt.setAccountSubNo(cust.getCustSubNo());
                    receipt.setAccountType(cust.getCustType());
                    receipt.setTaxNo(cust.getTaxNo());
                    // receipt.setAccountBranch(customer.getBranch());
                    receipt.setAccountBranch(cust.getCustBranch());
                    receipt.setAddrLine1(cust.getAddress1());
                    receipt.setAddrLine2(cust.getAddress2());
                    // receipt.setPaymentCase(paymentDTO.getPaymentCase()); ser

                    System.out.println("# PART 2 receipt.getId() = " + receipt.getId());
                    System.out.println("# PART 2 invoice.getReceiptId() = " + invoice.getReceiptId());
                    String paymentCase = "";
                    for (SettlePaymentDTO.Invoice inv2 : cust.getInvoices()) {
                        if (receipt.getId() == invoice.getReceiptId()) {
                            if (inv2.getPaymentCase() != null && inv2.getPaymentCase().indexOf("+") > 0) {
                                String paymentCaseArr[] = inv2.getPaymentCase().split("\\+");
                                for (int i = 0; i < paymentCaseArr.length; i++) {
                                    if (paymentCaseArr[paymentCaseArr.length - 1].length() <= 1) { // Remove
                                        // "+"
                                        // last
                                        // index
                                        paymentCase = inv2.getPaymentCase().substring(0,
                                                inv2.getPaymentCase().length() - 3);
                                    } else {
                                        paymentCase = inv2.getPaymentCase();
                                    }
                                }
                                receipt.setPaymentCase(paymentCase);
                            } else {
                                receipt.setPaymentCase(inv2.getPaymentCase());
                            }

                        }
                    }
                    receipt.setBranchCode(branchCode);
                    receipt.setBranchArea(branchArea);
                    receipt.setBranchName(branchName);
                    receipt.setAmount(rcpAmount);
                    receipt.setDiscount(rcpDiscount);
                    receipt.setCharge(rcpCharge);
                    receipt.setVatRate(VAT_RATE);
                    receipt.setVat(rcpVat);
                    receipt.setTotalCharge(rcpTotalCharge);
                    receipt.setDeduction(rcpDeduction);
                    receipt.setWtAmt(cust.getWtAmt());
                    receipt.setRetentionAmt(cust.getRetentionAmt());
                    receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
                    receipt.setAfterSaleDiscVat(rcpAfterSaleDiscVat);
                    receipt.setBalanceDue(rcpBalanceDue);
                    receipt.setReceived(invoice.getReceived());
                    receipt.setChange(invoice.getChange());
                    receipt.setAdvanced(null);
                    receipt.setPromotion(null);
                    receipt.setRemark(cust.getRemark());
                    receipt.setReprint(0L);

                    // sumAfterSaleDisc =
                    // sumAfterSaleDisc.add(rcpAfterSaleDiscount!=null?rcpAfterSaleDiscount:BigDecimal.ZERO).add(rcpAfterSaleDiscVat!=null?rcpAfterSaleDiscVat:BigDecimal.ZERO);

                    if (invoice.getReceived().doubleValue() < rcpAmount.doubleValue()) {
                        receipt.setAttributes("P");
                    } else {
                        receipt.setAttributes("F");
                    }

                    receipt.setBillingGroup(cust.getBillGroup());
                    receipt.setBillingGroupFull(cust.getBillGroup());
                    receipt.setBillingServiceName(cust.getInvoiceDisplay());// by
                    // NSD
                    // 24-03-2017
                    receipt.setLanguage(language);
                    for (Method method : payment.getMethods()) {
                        method.setRecieptId(receiptID);
                    }
                    
                    receipts.add(receipt);
                    saveLogCorReceipt(receipt, receipt.getAttributes());
                    amount = amount.add(rcpAmount);
                    discount = discount.add(rcpDiscount);
                    charge = charge.add(rcpCharge);
                    vat = vat.add(rcpVat);
                    totalCharge = totalCharge.add(rcpTotalCharge);
                    balanceDue = balanceDue.add(rcpBalanceDue);
                    afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
                    deduction = deduction.add(rcpDeduction);
                }
            }
        }
        BigDecimal totalAdvanced = BigDecimal.ZERO;
        Long advReceiptID = null;
        for (final SettlePaymentDTO.Advanced adv : paymentDTO.getAdvances()) {
            for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
                customer = find(customers, new Predicate<Customer>() {
                    @Override
                    public boolean apply(Customer customer) {
                        return trimToEmpty(customer.getNo()).equals(adv.getCustNo());
                    }
                });
                
                receipt = receiptRepo.save(new Receipt());
                advReceiptID = receipt.getId();
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);
                receipts.add(receipt);
                saveLogCorReceipt(receipt, receipt.getAttributes());
                invoice = invoiceRepo.save(new Invoice());
                invoice.setUpdateDttm(date);
                invoice.setUpdateUser(userName);
                Service service = serviceRepo.save(new Service());
                service.setUpdateDttm(date);
                service.setReceiptId(receipt.getId());
                service.setInvoiceId(invoice.getId());
                service.setAccountNo("1234567890");
                service.setProductCode("19201");
                service.setProductName("prod name");
                service.setProductSubCode("sub code");
                service.setProductSubName("sub name");
                service.setIncomeType("1");
                
                receipt.setCurrencyCode(adv.getType());
                boolean isMultiply = false;
                BigDecimal received = BigDecimal.ZERO; 
                if(adv.getType() == null && cust.getCurrencyRate()!=null){
                	received = adv.getReceived().multiply(cust.getCurrencyRate());
                	isMultiply = true;
                }
                else
                	received = adv.getReceived();
                
                //service.setAmount(adv.getReceived());
                service.setAmount(received);
                receipt.getServices().add(service);
                invoice.getServices().add(service);
                invoice.setNo(AppConstants.ADVANCE_PAYMENT);
                
                /*Method method = methodRepo.save(new Method()); method.setUpdateDttm(date); method.setUpdateUser(userName);
                method.setCode(adv.getPayCode());
                method.setName(adv.getPayName());
                method.setAmount(adv.getReceived());
                method.setPaymentId(payment.getId());
                method.setRecieptId(receiptID);
                payment.getMethods().add(method);
                
                paids.add(new Paid(adv.getTotalCharge(), adv.getPayType()));*/
                
                addTransactionsIntoService(paids, payment, invoice, date, userName);
                invoice.setReceiptId(receipt.getId());
                invoice.setCustomer(customer);
                invoice.setPayment(payment);
                invoice.setKenan(adv.getKenan());
                invoice.setCurrencyCode(adv.getCurrencyCode());
                invoice.setIssueDt(null);
                invoice.setDueDt(null);
                if (!StringUtils.isEmpty(adv.getBillCycle())) {
                    invoice.setBillCycle(adv.getBillCycle());
                } else {
                    invoice.setBillCycle("เริ่มตั้งแต่เดือน "
                            + FastDateFormat.getInstance("MM/yyyy", new Locale("th", "TH")).format(date));
                }
                /*
                invoice.setAmount(adv.getAmount());
                invoice.setDiscount(adv.getDiscount());
                invoice.setCharge(adv.getCharge());
                */
                invoice.setAmount((isMultiply && adv.getAmount()!=null)?(adv.getAmount().multiply(cust.getCurrencyRate())):adv.getAmount());
                invoice.setDiscount((isMultiply && adv.getDiscount()!=null)?(adv.getDiscount().multiply(cust.getCurrencyRate())):adv.getDiscount());
                invoice.setCharge((isMultiply && adv.getCharge()!=null)?(adv.getCharge().multiply(cust.getCurrencyRate())):adv.getCharge());
                
                invoice.setVatRate(VAT_RATE);
                invoice.setVat((isMultiply && adv.getVat()!=null)?(adv.getVat().multiply(cust.getCurrencyRate())):adv.getVat());
                //invoice.setTotalCharge(adv.getReceived());
                invoice.setTotalCharge(received);
                invoice.setDeduction((isMultiply && adv.getDeduction()!=null)?(adv.getDeduction().multiply(cust.getCurrencyRate())):adv.getDeduction());
                invoice.setAfterSaleDiscount(BigDecimal.ZERO);
                /*
                invoice.setBalanceDue(adv.getBalanceDue());
                invoice.setReceived(adv.getReceived());
                invoice.setChange(adv.getChange());
                invoice.setAdvanced(adv.getAdvanced());
                */
                invoice.setBalanceDue((isMultiply && adv.getBalanceDue()!=null)?(adv.getBalanceDue().multiply(cust.getCurrencyRate())):adv.getBalanceDue());
                invoice.setReceived((isMultiply && adv.getReceived()!=null)?(adv.getReceived().multiply(cust.getCurrencyRate())):adv.getReceived());
                invoice.setChange((isMultiply && adv.getChange()!=null)?(adv.getChange().multiply(cust.getCurrencyRate())):adv.getChange());
                invoice.setAdvanced((isMultiply && adv.getAdvanced()!=null)?(adv.getAdvanced().multiply(cust.getCurrencyRate())):adv.getAdvanced());
                invoice.setStatus("SUCCESS");
                invoice.setAttributes("A");
                if("Billing".equals(paymentDTO.getCustomers().get(0).getSouceType())){
                	invoice.setUpdateUser(paymentDTO.getUpdaetBy());
                	invoice.setUpdateDttm(paymentDTO.getUpdateDate());
                }
                receipt.getInvoices().add(invoice);
                receipt.setCustomer(customer);
                receipt.setPayment(payment);
                receipt.setType(getReceiptType2(customer));
                receipt.setFlgHeader(FLG_HEADER_1);
				/*
				 * String receiptType = "";
				 * if(StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1))
				 * { if(RECEIPT_TYPE_FULL.equals(receipt.getType())){
				 * receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE; }else{
				 * receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE; } }else
				 * if(StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)){
				 * receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY; }else{
				 * receiptType = RECEIPT_NO_FLAG_WH_ONLY; }
				 *//*
					 * if(cust.getReceiptFormat().toUpperCase().equals(
					 * RECEIPT_FORMAT_WH_ONLY)){//by NSD 24-04-2017
					 * receipt.setFlgHeader(FLG_HEADER_3); }
					 *//*
					 * if(StringUtils.equals(receipt.getFlgHeader(),
					 * FLG_HEADER_1)) {
					 * if(RECEIPT_TYPE_FULL.equals(receipt.getType())){
					 * receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE; }else{
					 * receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE; }
					 * }else if(StringUtils.equals(receipt.getFlgHeader(),
					 * FLG_HEADER_2)){ receiptType =
					 * RECEIPT_NO_FLAG_RECEIVE_ONLY; }else{ receiptType =
					 * RECEIPT_NO_FLAG_WH_ONLY; }
					 */

                receipt.setNo(getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(receipt.getType())
                                ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, receiptDttm,
                        RECEIPT_HEADER_EPO));

                receipt.setDocDttm(receiptDttm);

                receipt.setName(customer.getName());
                receipt.setAccountName(customer.getName());
                receipt.setAccountNo(customer.getNo());

                String subNo = "";
                Map<String, String> accountSubNoMap = new HashMap<String, String>();
                SubscriptionDTO dto = new SubscriptionDTO();
                // call F05 RetreiveServiceStatus
                RetrieveServiceStatusRequest request = new RetrieveServiceStatusRequest();
                request.setBillingAccountNo(customer.getNo());
                try {
                    request.setTransactionLog(_f05RetrieveServiceStatusService.buildTransactionLogCBO());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RetrieveServiceStatusResponse response = _f05RetrieveServiceStatusService.callInterface(request);

                if (_f05RetrieveServiceStatusService.isCalledSuccesful("0", response)) {

                    for (ServiceStatusBO serviceStatus : response.getServiceStatusList()) {
                        if (serviceStatus.getStatusName().equals("Active")) {
                            subNo = String.valueOf(serviceStatus.getSubscrNo());
                            accountSubNoMap.put(subNo, subNo);
                        }

                        // dto.addData(new
                        // Subscription(serviceStatus.getStatusReasonName(),
                        // serviceStatus.getSubscrNo(),
                        // serviceStatus.getStatusName()));
                    }
                }
                if (accountSubNoMap.size() > 1) {
                    subNo = String.valueOf(accountSubNoMap.size());
                } else if (accountSubNoMap.size() == 1) {
                    Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
                    subNo = entry.getValue();
                } else {

                }
                receipt.setAccountSubNo(subNo);
                receipt.setAccountType(adv.getCustType());
                receipt.setTaxNo(customer.getTax());
                // receipt.setAccountBranch(customer.getBranch());

                receipt.setAddrLine1(customer.getReceiptAddr());
                receipt.setAddrLine2(customer.getInvoiceAddr());
                receipt.setPaymentCase(paymentDTO.getPaymentCase());
                receipt.setBranchCode(branchCode);
                receipt.setBranchArea(branchArea);
                receipt.setBranchName(branchName);
                /*
                receipt.setAmount(adv.getAmount());
                receipt.setDiscount(adv.getDiscount());
                receipt.setCharge(adv.getCharge());
                receipt.setVatRate(VAT_RATE);
                receipt.setVat(adv.getVat());
                receipt.setTotalCharge(adv.getTotalCharge());
                receipt.setDeduction(adv.getDeduction());
                */
                receipt.setAmount((isMultiply && adv.getAmount()!=null)?(adv.getAmount().multiply(cust.getCurrencyRate())):adv.getAmount());
                receipt.setDiscount((isMultiply && adv.getDiscount()!=null)?(adv.getDiscount().multiply(cust.getCurrencyRate())):adv.getDiscount());
                receipt.setCharge((isMultiply && adv.getCharge()!=null)?(adv.getCharge().multiply(cust.getCurrencyRate())):adv.getCharge());
                receipt.setVatRate(VAT_RATE);
                receipt.setVat((isMultiply && adv.getVat()!=null)?(adv.getVat().multiply(cust.getCurrencyRate())):adv.getVat());
                receipt.setTotalCharge((isMultiply && adv.getTotalCharge()!=null)?(adv.getTotalCharge().multiply(cust.getCurrencyRate())):adv.getTotalCharge());
                receipt.setDeduction((isMultiply && adv.getDeduction()!=null)?(adv.getDeduction().multiply(cust.getCurrencyRate())):adv.getDeduction());
                
                receipt.setAfterSaleDiscount(BigDecimal.ZERO);
                receipt.setBalanceDue(invoice.getBalanceDue());
                receipt.setReceived(invoice.getReceived());
                receipt.setChange(BigDecimal.ZERO);
                receipt.setAdvanced(adv.getAdvanced());
                receipt.setPromotion(null);
                receipt.setRemark(customer.getRemark());
                receipt.setReprint(0L);
                receipt.setAttributes("A");
                receipt.setBillingGroup(customer.getBillGroup());
                receipt.setBillingGroupFull(customer.getBillGroup());
                receipt.setBillingServiceName(adv.getInvoiceDisplay());
                receipt.setLanguage(language);
                if("Billing".equals(paymentDTO.getCustomers().get(0).getSouceType())){
                	receipt.setUpdateUser(paymentDTO.getUpdaetBy());
                	receipt.setUpdateDttm(paymentDTO.getUpdateDate());
                }
                //totalAdvanced = totalAdvanced.add(adv.getAmount());
                totalAdvanced = totalAdvanced.add(((isMultiply && adv.getAmount()!=null)?(adv.getAmount().multiply(cust.getCurrencyRate())):adv.getAmount()));
            }
        }
        //Add Adv payCase
        for (SettlePaymentDTO.AdvancesCase advCase : paymentDTO.getAdvancesCase()) {
	    	Paid paid = new Paid(advCase.getPayAmount(), advCase.getPayType());
	        paids.add(paid);
			Method method = methodRepo.save(new Method()); method.setUpdateDttm(date); method.setUpdateUser(userName);
			method.setCode(advCase.getPayCode());
			method.setName(advCase.getPayName());
			method.setAmount(advCase.getPayAmount());
			method.setPaymentId(payment.getId());
	        method.setRecieptId(advReceiptID);
	        method.setChequeNo(advCase.getDocNo());
			payment.getMethods().add(method);
		}
        
        // insert Data into Table RECEIPT_EGP_MAPPING
        String currencyCode = "";
        BigDecimal currencyRate = BigDecimal.ONE;
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            for (Receipt rec : receipts) {
                if (!StringUtils.isBlank(cust.getEgpNo()) && !StringUtils.isBlank(cust.getEgpContract())) {
                    egpMap = new ReceiptEgpMappingEntity();
                    egpMap.setReceiptId(rec.getId());
                    egpMap.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                    egpMap.setCreatedDate(timestamp);
                    egpMap.setBaNo(cust.getCustNo());
                    egpMap.setEgpNo(cust.getEgpNo());
                    egpMap.setEgpContract(cust.getEgpContract());
                    egpMap.setReceiptNo(rec.getNo());
                    egpMaps.add(egpMap);
                    egpMapRepo.save(egpMap);
                }
            }
            receipt.setAccountBranch(cust.getCustBranch());
            currencyCode = cust.getCurrencyCode();
            currencyRate = cust.getCurrencyRate() != null ? cust.getCurrencyRate() : currencyRate;
        }
        payment.setOfficerId(officerId);
        payment.setShopId(EpContextHolder.getContext().getBranchId());
        payment.setPosId(EpContextHolder.getContext().getPosId());
        payment.setCollectionUnit(customer.getCollectionUnit());
        payment.setDate(date);
        payment.setType(PAYMENT_TYPE_SERVICE_CHARGE);
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);
        payment.setDiscount(discount);
        payment.setCharge(charge);
        payment.setVatRate(VAT_RATE);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);
        payment.setDeduction(deduction);
        payment.setAfterSaleDiscount(afterSaleDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(paymentDTO.getReceiveAmount());
        payment.setChange(paymentDTO.getRemainAmount());
        payment.setAdvanced(totalAdvanced);
        payment.setAttributes("S");
        payment.setCurrencyCode(currencyCode);
        payment.setCurrencyRate(currencyRate);
        int result = dwService.sendDateWereHose(receipt.getPayment().getId());
        System.out.println(result);
        return receipts;
    }

    void addTransactionsIntoService(List<Paid> paids, Payment payment, Invoice invoice, Date date, String userName) {
        BigDecimal balanceDue;
        Transaction transaction;
        boolean isEnd = false;
        for (Service service : invoice.getServices()) {
            balanceDue = service.getAmount().subtract(
                    service.getAfterSaleDiscount() != null ? service.getAfterSaleDiscount() : BigDecimal.ZERO);
            for (Paid paid : paids) {
                BigDecimal payInv = BigDecimal.ZERO;
                if (paid.getAmount().compareTo(BigDecimal.ZERO) <= 0)
                    continue;
                if (paid.getAmount().compareTo(balanceDue) >= 0) {
                    payInv = balanceDue;
                    paid.setAmount(paid.getAmount().subtract(payInv));
                    balanceDue = BigDecimal.ZERO;
                    isEnd = true;
                } else {
                    payInv = paid.getAmount();
                    paid.setAmount(BigDecimal.ZERO);
                    balanceDue = balanceDue.subtract(payInv);
                    isEnd = false;
                }
                // payInv.add(payment.getAfterSaleDiscount());
                transaction = transactionRepo.save(new Transaction());
                transaction.setUpdateDttm(date);
                transaction.setUpdateUser(userName);
                transaction.setServiceId(service.getId());
                transaction.setTransactionId(AppUtil.generateTransactionID(15));
                transaction.setTrackingDetails("EPIS is waiting for ESP response message.");
                transaction.setTrackingRetry(0);
                transaction.setPaymentDate(date);
                transaction.setPaymentType(paid.getType());
                transaction.setAmount(payInv);
                transaction.setChequeNo(paid.getChequeNo());
                transaction.setAccountNo(paid.getBankAccount());
                transaction.setStatus(null);
                transaction.setPayment(payment);
                service.getTransactions().add(transaction);
                if ("BANKTRANSFER".equals(paid.getType()) && !AppConstants.ADVANCE_PAYMENT.equalsIgnoreCase(invoice.getNo())) {
                    paid.getMoneyTransfer().setTransaction(transaction);
                }
                if (isEnd)
                    break;
            }
            // case there is aftersales discount
            if (service.getAfterSaleDiscount() != null
                    && service.getAfterSaleDiscount().compareTo(BigDecimal.ZERO) > 0) {
                transaction = transactionRepo.save(new Transaction());
                transaction.setUpdateDttm(date);
                transaction.setUpdateUser(userName);
                transaction.setServiceId(service.getId());
                transaction.setTransactionId(AppUtil.generateTransactionID(15));
                transaction.setTrackingDetails("EPIS is waiting for ESP response message.");
                transaction.setTrackingRetry(0);
                transaction.setPaymentDate(date);
                transaction.setPaymentType(AFTERSALES_DISCOUNT_METHOD);
                transaction.setAmount(service.getAfterSaleDiscount());
                // transaction.setChequeNo(paid.getChequeNo());
                // transaction.setAccountNo(paid.getBankAccount());
                transaction.setStatus(null);
                transaction.setPayment(payment);
                service.getTransactions().add(transaction);
            }
        }
    }

    String getReceiptType(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("This error is occurred when programatic has incomplete.");
        } else if (stripToEmpty(customer.getType()).toLowerCase().indexOf("individual") == 0) {
            return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) ? RECEIPT_TYPE_ABBREVIATION
                    : RECEIPT_TYPE_FULL;
        } else if (stripToEmpty(customer.getType()).toLowerCase().indexOf("organization") == 0) {
            return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) || isBlank(customer.getTax())
                    || isBlank(customer.getBranch()) ? RECEIPT_TYPE_ABBREVIATION : RECEIPT_TYPE_FULL;
        } else if (stripToEmpty(customer.getType()).toLowerCase().indexOf("stateagency") == 0) {
            return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) ? RECEIPT_TYPE_ABBREVIATION
                    : RECEIPT_TYPE_FULL;
        } else {
            throw new UnsupportedOperationException(
                    "Please specify the customer type is INDIVIDUAL or ORGANIZATION or STATEAGENCY");
        }
    }

    // by NSD 02-03-2017
    String getReceiptType2(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("This error is occurred when programatic has incomplete.");
        } else if (!StringUtils.isEmpty(customer.getAcctCatLkp())) {
            if (StringUtils.equals(customer.getAcctCatLkp(), "1")) {
                return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) || isBlank(customer.getTax())
                        || isBlank(customer.getBranch()) ? RECEIPT_TYPE_ABBREVIATION : RECEIPT_TYPE_FULL;
            } else {
                return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) ? RECEIPT_TYPE_ABBREVIATION
                        : RECEIPT_TYPE_FULL;
            }
        } else {
            return getReceiptType(customer);
        }
    }

    @Transactional
    public List<Receipt> createPaymentService(SettlePaymentDTO paymentDTO) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        MethodCheque chequePay = new MethodCheque();
        MethodCreditCard creditCardPay = new MethodCreditCard();
        MethodMoneyTransfer moneyTransfer = new MethodMoneyTransfer();
        MethodMoneyOrder moneyOrder = new MethodMoneyOrder();
        MethodBillExchange billExchange = new MethodBillExchange();
        Ofset oFset = new Ofset();
        MethodOther otherPay = new MethodOther();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                specialDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO, received = BigDecimal.ZERO, tmpDeduction = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();
        Long receiptID = null;
        String currencyCode = "th_TH";
        BigDecimal currencyRate = BigDecimal.ONE;
        // Reorder: Cheque at first.
        for (SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
            paids.add(new Paid(ded.getAmount(), ded.getType()));
            deduct = deductRepo.save(new Deduct());
            deduct.setUpdateDttm(date);
            deduct.setUpdateUser(userName);
            deduct.setNo(ded.getWithholdingDocNo());
            deduct.setType(ded.getType());
            deduct.setAmount(ded.getAmount());
            deduct.setDate(date);
            deduct.setCustomerNo(ded.getCustomerNo());
            
            if(ded.getInvoiceNo()!=null)
            	deduct.setInvoiceNo(ded.getInvoiceNo());
            if(ded.getCostCenter()!=null)
            	deduct.setCostCenter(ded.getCostCenter());
            if(ded.getWithHoldingTax()!=null)
            	deduct.setWithHoldingTax(ded.getWithHoldingTax());
            if(ded.getTaxAmt()!=null)
            	deduct.setTaxAmt(ded.getTaxAmt());
            
            deduct.setPaymentId(payment.getId());
            tmpDeduction = tmpDeduction.add(ded.getAmount());
            payment.getDeducts().add(deduct);
        }
        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            // Fix by EPIS8 issue no 166 9/1/2017
            // Paid paid = new Paid(met.getAmount(), met.getType());
            // paids.add(paid); // Preparing: To substract into invoice.
            // Method method = methodRepo.save(new Method());
            // method.setUpdateDttm(date); method.setUpdateUser(userName);
            // method.setCode(met.getCode());
            // method.setName(met.getName());
            // method.setAmount(met.getAmount());
            // method.setPaymentId(payment.getId());
            // payment.getMethods().add(method);
            // if("7".equals(met.getCode())) { // Money Transfer
            // MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new
            // MethodMoneyTransfer()); moneyTransfer.setUpdateDttm(date);
            // moneyTransfer.setUpdateUser(userName);
            // moneyTransfer.setDate(met.getTransferDt());
            // paid.setMoneyTransfer(moneyTransfer);
            // paid.setIsBackDate(met.isBackDt());
            // }
            if (met != null && met.getType() != null) {
                Paid paid = new Paid(met.getAmount(), met.getType());
                paids.add(paid); // Preparing: To substract into invoice.
                Method method = methodRepo.save(new Method());
                method.setUpdateDttm(date);
                method.setUpdateUser(userName);
                method.setCode(met.getCode());
                method.setName(met.getName());
                method.setChequeNo(StringUtils.isNotEmpty(met.getChequeNo())?met.getChequeNo():met.getDocNo());
                method.setAccountNo(met.getBankAcct());
                method.setAmount(met.getAmount());
                method.setPaymentId(payment.getId());

                if (PAY_METHOD_BANKTRANSFER.equals(met.getType())
                        || PAY_METHOD_FOREIGNTRANSFER.equals(met.getType())) { // Money
                    // Transfer
                	moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                    moneyTransfer.setUpdateDttm(date);
                    moneyTransfer.setAmount(met.getAmount());
                    moneyTransfer.setUpdateUser(userName);
                    moneyTransfer.setDate(met.getTransferDt());
                    moneyTransfer.setPayCode(met.getCode());
                    moneyTransfer.setBankCode(met.getBankCode());
                    moneyTransfer.setBankAcCd(met.getBankAcCd());
                    moneyTransfer.setBankName(met.getBankName());
                    moneyTransfer.setRefNo(met.getRefNo());
                    moneyTransfer.setBankBrnh(met.getBankBrnh());
                    moneyTransfer.setBankAcNo(met.getBankAcctNo());
                    moneyTransfer.setCurrencyCode(met.getCurrencyCode());
                    moneyTransfer.setCurrencyRate(met.getCurrencyRate());
                    moneyTransfer.setForeignAmount(met.getForeignAmount());
                    moneyTransfer.setPayType(met.getType());
                    moneyTransfer.setPaymentId(payment.getId());
                    moneyTransfer.setMethodId(method.getId());
                    paid.setMoneyTransfer(moneyTransfer);
                    paid.setIsBackDate(met.isBackDt());
                } else if (PAY_METHOD_CHEQUE.equals(met.getType())) {
                    // TODO: complete all the saving methods and pulling them to
                    // print correctly
                    chequePay = chequeRepository.save(new MethodCheque());
                    chequePay.setAmount(met.getAmount());
                    chequePay.setBankCode(met.getPayChqBankCode());
                    chequePay.setBankName(met.getPayChqBankName());
                    chequePay.setBankBrnh(met.getPayChqBranch());
                    chequePay.setChequeDate(met.getPayChqDate());
                    chequePay.setNo(met.getChequeNo());
                    chequePay.setUpdateUser(userName);
                    chequePay.setUpdateDttm(date);
                    chequePay.setPaymentId(payment.getId());
                    chequePay.setMethodId(method.getId());

                } else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
                    // Fix by EPIS8 30/12/2016 issue no 166
                    creditCardPay = creditCardRepo.save(new MethodCreditCard());
                    creditCardPay.setPaymentId(payment.getId());
                    creditCardPay.setNo(met.getCardNo());
                    creditCardPay.setAmount(met.getAmount());
                    creditCardPay.setBankIssuer(met.getBankName());
                    creditCardPay.setType(met.getCardType());
                    creditCardPay.setUpdateDttm(date);
                    creditCardPay.setUpdateUser(userName);
                    creditCardPay.setMethodId(method.getId());
                    // End Fix by EPIS8 30/12/2016 issue no 166
                } else if (PAY_METHOD_MONEYORDER.equals(met.getType())) {
                    moneyOrder = moneyOrderRepository.save(new MethodMoneyOrder());
                    moneyOrder.setNo(met.getMnyOrderNo());
                    moneyOrder.setAmount(met.getAmount());
                    moneyOrder.setDate(met.getMnyOrderDt());
                    moneyOrder.setPostCode(met.getPostCode());
                    moneyOrder.setUpdateDttm(date);
                    moneyOrder.setUpdateUser(userName);
                    moneyOrder.setPaymentId(payment.getId());
                    moneyOrder.setMethodId(method.getId());
                } else if (PAY_METHOD_BILLEXCHANGE.equals(met.getType())) {
                    billExchange = billOfExchangRepository.save(new MethodBillExchange());
                    billExchange.setNo(met.getTrnfNo());
                    billExchange.setAmount(met.getAmount());
                    billExchange.setDate(met.getTransferDt());
                    billExchange.setPostCode(met.getPostCode());
                    billExchange.setUpdateDttm(date);
                    billExchange.setUpdateUser(userName);
                    billExchange.setPaymentId(payment.getId());
                    billExchange.setMethodId(method.getId());
                } else if (PAY_METHOD_COUPON.equals(met.getType())) {
                    th.co.softpos.ws.mg.s002.RqHeader rqHeader = new th.co.softpos.ws.mg.s002.RqHeader();
                    rqHeader.setFuncNm("S002MG");
                    rqHeader.setRqAppId("POS");
                    rqHeader.setUserId("9999");
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTime(new Date());
                    try {
                        rqHeader.setRqDt(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
                    } catch (DatatypeConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    th.co.softpos.ws.mg.s002.RqBody rqBody = new th.co.softpos.ws.mg.s002.RqBody();
                    th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher giftvoucher =
                            new th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher();
                    giftvoucher.setCode(met.getCouponNo());
                    giftvoucher.setStatus("S");
                    rqBody.setMgTempImportDetailGiftvoucher(giftvoucher);
                    th.co.softpos.ws.mg.s002.Request _process_rq = new th.co.softpos.ws.mg.s002.Request();
                    _process_rq.setRqHeader(rqHeader);
                    _process_rq.setRqBody(rqBody);
                    th.co.softpos.ws.mg.s002.Response _response = s002MGUpdGiftvoucher.process(_process_rq);
                    method.setChequeNo(met.getCouponNo());
                } else if (PAY_METHOD_OFFSET.equals(met.getType())) {
                	oFset = ofsetRepository.save(new Ofset());
                    oFset.setAmount(met.getAmount());
                    oFset.setNo(met.getOffsetDocumentNo());
                    oFset.setOfsetcode(met.getOffsetAccountCode());
                    oFset.setOfsetname(met.getOffsetAccountName());
                    oFset.setUpdateDttm(date);
                    oFset.setUpdateUser(userName);
                    oFset.setPaymentId(payment.getId());
                    oFset.setMethodId(method.getId());
                } else if (PAY_METHOD_OTHER.equals(met.getType())) {
                    otherPay = otherRepository.save(new MethodOther());
                    otherPay.setNo(met.getOtherNo());
                    otherPay.setAmount(met.getAmount());
                    otherPay.setType(met.getOtherType());
                    otherPay.setDate(met.getOtherDt());
                    otherPay.setUpdateDttm(date);
                    otherPay.setUpdateUser(userName);
                    otherPay.setPaymentId(payment.getId());
                    otherPay.setMethodId(method.getId());
                } 
                payment.getMethods().add(method);
            }
            // End Fix by EPIS8 issue no 166 9/1/2017

        }
        
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            customer = customerRepo.save(new Customer());
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1());
            customer.setInvoiceAddr(cust.getAddress2());
            customer.setCatCustomerSegment(cust.getCatCustomerSegment());
            customer.setAcctCatLkp(cust.getAcctCatLkp());

            // Additional Conditions for GFMIS, date and branch
            String customerType = stripToEmpty(customer.getType()).toLowerCase();
            if (customerType.indexOf("organization") == 0 || customerType.indexOf("stateagency") == 0) {
                customer.setBranch(cust.getCustBranch());
            }
            for (Paid paid : paids) {
                if (paid.getMoneyTransfer() != null & paid.isBackDate()) {
                    receiptDttm = paid.getMoneyTransfer().getDate();
                    // by NSD 24-04-2017
                    String receiptType = getReceiptType2(customer);
                    Date dtFrom = paid.getMoneyTransfer().getDate();
                    Calendar c = Calendar.getInstance();
                    c.setTime(dtFrom);
                    c.add(Calendar.DATE, 1);
                    Date dtTo = c.getTime();
					/*
					 * List<Receipt> rcptList = receiptRepo.
					 * findByTypeAndBranchAreaAndDocDttmOrderByNoDesc(
					 * receiptType, branchArea, dtFrom, dtTo); BeanComparator
					 * fieldComparator = new BeanComparator("no");
					 * Collections.sort(rcptList, fieldComparator);
					 * if(rcptList!=null && rcptList.size()>0){ receiptDttm =
					 * rcptList.get(rcptList.size()-1).getDocDttm(); }
					 */
                    break;
                }
            }

            receipt = receiptRepo.save(new Receipt());
            receiptID = receipt.getId();
            receipt.setUpdateDttm(date);
            receipt.setUpdateUser(userName);
            receipt.setDocDttm(receiptDttm);
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                service = serviceRepo.save(new Service());
                service.setUpdateDttm(date);
                service.setUpdateUser(userName);
                addTransactionsIntoService(paids, svc, payment, service, date, userName);
                receipt.getServices().add(service);
                service.setReceiptId(receipt.getId());
                service.setServiceCode(svc.getCode());
                // service.setServiceName(svc.getName());//by NSD 30-03-2017
                service.setServiceName(svc.getName());
                service.setServiceNo(svc.getNo());
                service.setProductCode(svc.getProductCode());
                service.setProductName(svc.getProductName());
                service.setProductSubCode(svc.getSubProductCode());
                service.setProductSubName(svc.getSubProductName());
                service.setIncomeType(svc.getType());
                service.setIncomeAmount(new BigDecimal(svc.getMoreData()));
                service.setIncomeUnit(svc.getUnit());
                service.setRefTransId(svc.getRefTransId());
                service.setAmount(svc.getAmount());
                service.setDiscount(svc.getDiscount());
                service.setCharge(svc.getCharge());
                service.setVatRate(svc.getVatRate());
                service.setVat(svc.getVat());
                service.setTotalCharge(svc.getTotalCharge());
                service.setDeduction(svc.getDeduction());
                service.setServiceQty(Integer.parseInt(svc.getMoreData()));// by
                // NSD
                // 30-03-2017
                service.setCostPerUnit(svc.getCostPerUnit());
                service.setProfitCode(svc.getProfitCode());
                service.setProfitName(svc.getProfitName());
                service.setSegmentCode(svc.getSegmentCode());
                service.setSegmentName(svc.getSegmentName());
                service.setGlAccount(svc.getGlAccount());
                service.setRevenueTypeCode(svc.getRevenueTypeCode());
                service.setRevenueTypeName(svc.getRevenueTypeName());
                service.setSpecialDiscount(svc.getSpecialDiscount());
                service.setGroupCode(svc.getGroupCode());
                service.setGroupName(svc.getGroupName());
                service.setPaymentId(payment.getId());
                
                if(svc.getCurrencyCode()!=null)
                	currencyCode = svc.getCurrencyCode();
                if(svc.getCurrencyRate()!=null)
                	currencyRate = svc.getCurrencyRate();
                
               if(svc.isFlgOtherIncomeNonVat()) {
            	   for (Method method : payment.getMethods()) {
                       method.setRecieptId(receiptID);
                   }
               }
            }
            BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                    rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                    rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO, rcpWt = BigDecimal.ZERO,
                    rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO;
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                rcpAmount = rcpAmount.add(svc.getAmount());
                rcpDiscount = rcpDiscount.add(svc.getDiscount());
                if (svc.getVat() != null) {
                    rcpVat = rcpVat.add(svc.getVat());
                }
                rcpTotalCharge = rcpTotalCharge.add(svc.getTotalCharge());
                rcpDeduction = rcpDeduction.add(svc.getDeduction());
                rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(BigDecimal.ZERO); // how
                // to
                // distribute
                // 1
                // value
                // from
                // screen
                // to
                // every
                // services
                rcpBalanceDue = rcpBalanceDue.add(BigDecimal.ZERO);
            }
            rcpDeduction = tmpDeduction;
            // Add by Puthy 27-04.2017
            Boolean nonVatStatus = false;
            for (SettlePaymentDTO.Service svc : cust.getServices()) { // loop to
                // find
                // all
                // service
                // is
                // non-vat
                // or
                // not
                if (svc.getVat() != null) {
                    nonVatStatus = false;
                    break;
                } else {
                    nonVatStatus = true;
                }
            }
            if (nonVatStatus) { // set rcpVat to null if all service is non-vat
                rcpVat = null;
            }
            rcpCharge = rcpAmount.subtract(rcpDiscount);
            for (Method method : payment.getMethods()) {
                rcpReceived = rcpReceived.add(method.getAmount());
            }
            receipt.setPayment(payment);
            receipt.setCustomer(customer);
            receipt.setType(getReceiptType2(customer));
            receipt.setName(cust.getCustName());
            receipt.setAccountName(cust.getCustName());
            receipt.setAccountNo(cust.getCustNo());
            receipt.setAccountSubNo("");
            receipt.setAccountType(cust.getCustType());
            receipt.setTaxNo(cust.getTaxNo());
			/* ise no.158 */
            // receipt.setAccountBranch(customer.getBranch());
            receipt.setAccountBranch(cust.getCustBranch());
            receipt.setAddrLine1(customer.getReceiptAddr());
            receipt.setAddrLine2(customer.getInvoiceAddr());
            receipt.setPaymentCase(paymentDTO.getPaymentCase());
            receipt.setBranchCode(branchCode);
            receipt.setBranchArea(branchArea);
            receipt.setBranchName(branchName);
            receipt.setAmount(rcpAmount);
            receipt.setDiscount(rcpDiscount);
            // by NSD 29-03-2017
            receipt.setSpecialDiscount(cust.getSpecialDiscount());
            if (cust.getSpecialDiscount().compareTo(new BigDecimal(0)) == 1) {
                BigDecimal charge0 = rcpCharge.subtract(cust.getSpecialDiscount());
                BigDecimal vat0 = (charge0.multiply(VAT_RATE).divide(new BigDecimal(100))).setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                receipt.setCharge(charge0);
                rcpVat = vat0;
                receipt.setVat(rcpVat);
                rcpTotalCharge = charge0.add(vat0);
                receipt.setTotalCharge(rcpTotalCharge);
            } else {
                receipt.setCharge(rcpCharge);
                receipt.setVat(rcpVat);
                receipt.setTotalCharge(rcpTotalCharge);
            }
            receipt.setVatRate(VAT_RATE);
            receipt.setDeduction(rcpDeduction);
            receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
            // receipt.setAfterSaleDiscVat((cust.getSpecialDiscount().multiply(VAT_RATE).divide(new
            // BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP));
            receipt.setBalanceDue(rcpBalanceDue);
            receipt.setReceived(rcpReceived);
            rcpChange = rcpReceived.subtract(rcpTotalCharge.subtract(rcpDeduction));
            receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
            receipt.setAdvanced(null);
            receipt.setPromotion(null);
            receipt.setRemark(cust.getRemark());
            receipt.setReprint(0L);
            receipt.setAttributes("FC");
            
            if(cust.getWtAmt()!=null) 
                receipt.setDeduction(rcpDeduction.subtract(cust.getWtAmt()));
            receipt.setWtAmt(cust.getWtAmt());
            receipt.setRetentionAmt(cust.getRetentionAmt());
	            
            if (receipt.getVat() == null) {
                receipt.setFlgHeader(AppConstants.FLG_HEADER_2);
            } else {
                receipt.setFlgHeader(AppConstants.FLG_HEADER_1);
            }
            
            receipt.setNo(getReceiptNo(posNo,
                    RECEIPT_TYPE_FULL.equals(receipt.getType()) ? (FLG_HEADER_1.equals(receipt.getFlgHeader())
                            ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE : RECEIPT_NO_FLAG_RECEIVE_ONLY)
                            : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE,
                    receiptDttm, RECEIPT_HEADER_EPO));

            receipts.add(receipt);
            saveLogCorReceipt(receipt, receipt.getAttributes());
            amount = amount.add(rcpAmount);
            discount = discount.add(rcpDiscount);
            charge = charge.add(rcpCharge);
            if (rcpVat != null) {
                vat = vat.add(rcpVat);
            }
            totalCharge = totalCharge.add(rcpTotalCharge);
            balanceDue = balanceDue.add(rcpBalanceDue);
            specialDiscount = specialDiscount.add(cust.getSpecialDiscount());
            deduction = deduction.add(rcpDeduction);
            received = received.add(rcpReceived);
        }
        for (SettlePaymentDTO.AdvancesCase advCase : paymentDTO.getAdvancesCase()) {
	    	Paid paid = new Paid(advCase.getPayAmount(), advCase.getPayType());
	        paids.add(paid);
			Method method = methodRepo.save(new Method()); method.setUpdateDttm(date); method.setUpdateUser(userName);
			method.setCode(advCase.getPayCode());
			method.setName(advCase.getPayName());
			method.setAmount(advCase.getPayAmount());
			method.setPaymentId(payment.getId());
	        method.setRecieptId(receiptID);
			payment.getMethods().add(method);
		}
        payment.setOfficerId(officerId);
        payment.setShopId(EpContextHolder.getContext().getBranchId());

        //Set PosID
        payment.setPosId(EpContextHolder.getContext().getPosId());
        payment.setCollectionUnit(customer.getCollectionUnit());
        payment.setDate(date);
        payment.setType(PAYMENT_TYPE_OTHER);
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);
        payment.setDiscount(discount);
        payment.setCharge(charge.subtract(specialDiscount));
        payment.setVatRate(VAT_RATE);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);
        payment.setDeduction(deduction);
        payment.setSpecialDiscount(specialDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(received);
        payment.setChange(received.subtract(totalCharge.subtract(deduction)));
        payment.setAdvanced(null);
        payment.setAttributes("O");
        payment.setCurrencyCode(currencyCode);
        payment.setCurrencyRate(currencyRate);
        return receipts;
    }

    @Transactional
    public List<Receipt> createPaymentMobile(SettlePaymentDTO paymentDTO) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        MethodCheque chequePay = new MethodCheque();
        MethodCreditCard creditCardPay = new MethodCreditCard();
        MethodMoneyTransfer moneyTransfer = new MethodMoneyTransfer();
        MethodMoneyOrder moneyOrder = new MethodMoneyOrder();
        MethodBillExchange billExchange = new MethodBillExchange();
        Ofset oFset = new Ofset();
        MethodOther otherPay = new MethodOther();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO, received = BigDecimal.ZERO,
                vatRate = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();

        // Reorder: Cheque at first.
        for (SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
            paids.add(new Paid(ded.getAmount(), ded.getType()));
            deduct = deductRepo.save(new Deduct());
            deduct.setUpdateDttm(date);
            deduct.setUpdateUser(userName);
            deduct.setNo(ded.getWithholdingDocNo());
            deduct.setType(ded.getType());
            deduct.setAmount(ded.getAmount());
            deduct.setDate(date);
            deduct.setPaymentId(payment.getId());
            payment.getDeducts().add(deduct);
        }
        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            Paid paid = new Paid(met.getAmount(), met.getType());
            paids.add(paid); // Preparing: To substract into invoice.
            Method method = methodRepo.save(new Method());
            method.setUpdateDttm(date);
            method.setUpdateUser(userName);
            method.setCode(met.getCode());
            method.setName(met.getName());
            method.setAmount(met.getAmount());
            method.setChequeNo(StringUtils.isNotEmpty(met.getChequeNo())?met.getChequeNo():met.getDocNo());
            method.setPaymentId(payment.getId());

            if (PAY_METHOD_BANKTRANSFER.equals(met.getType())
                    || PAY_METHOD_FOREIGNTRANSFER.equals(met.getType())) { // Money
                // Transfer
            	moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
            	moneyTransfer.setAmount(met.getAmount());
                moneyTransfer.setUpdateDttm(date);
                moneyTransfer.setUpdateUser(userName);
                moneyTransfer.setDate(met.getTransferDt());
                moneyTransfer.setPayCode(met.getCode());
                moneyTransfer.setPayType(met.getType());
                moneyTransfer.setPaymentId(payment.getId());
                moneyTransfer.setMethodId(method.getId());
                paid.setMoneyTransfer(moneyTransfer);
                paid.setIsBackDate(met.isBackDt());
            } else if (PAY_METHOD_CHEQUE.equals(met.getType())) {
                // TODO: complete all the saving methods and pulling them to
                // print correctly
                chequePay = chequeRepository.save(new MethodCheque());
                chequePay.setAmount(met.getAmount());
                chequePay.setBankCode(met.getPayChqBankCode());
                chequePay.setBankName(met.getPayChqBankName());
                chequePay.setBankBrnh(met.getPayChqBranch());
                chequePay.setChequeDate(met.getChequeDate());
                chequePay.setNo(met.getChequeNo());
                chequePay.setUpdateUser(userName);
                chequePay.setUpdateDttm(date);
                chequePay.setPaymentId(payment.getId());
                chequePay.setMethodId(method.getId());

            } else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
                // Fix by EPIS8 30/12/2016 issue no 166
                creditCardPay = creditCardRepo.save(new MethodCreditCard());
                creditCardPay.setPaymentId(payment.getId());
                creditCardPay.setNo(met.getCardNo());
                creditCardPay.setAmount(met.getAmount());
                creditCardPay.setBankIssuer(met.getBankName());
                creditCardPay.setType(met.getCardType());
                creditCardPay.setUpdateDttm(date);
                creditCardPay.setUpdateUser(userName);
                creditCardPay.setMethodId(method.getId());
                // End Fix by EPIS8 30/12/2016 issue no 166
            } else if (PAY_METHOD_MONEYORDER.equals(met.getType())) {
                moneyOrder = moneyOrderRepository.save(new MethodMoneyOrder());
                moneyOrder.setNo(met.getMnyOrderNo());
                moneyOrder.setAmount(met.getAmount());
                moneyOrder.setDate(met.getMnyOrderDt());
                moneyOrder.setPostCode(met.getPostCode());
                moneyOrder.setUpdateDttm(date);
                moneyOrder.setUpdateUser(userName);
                moneyOrder.setPaymentId(payment.getId());
                moneyOrder.setMethodId(method.getId());
            } else if (PAY_METHOD_BILLEXCHANGE.equals(met.getType())) {
                billExchange = billOfExchangRepository.save(new MethodBillExchange());
                billExchange.setNo(met.getTrnfNo());
                billExchange.setAmount(met.getAmount());
                billExchange.setDate(met.getTransferDt());
                billExchange.setPostCode(met.getPostCode());
                billExchange.setUpdateDttm(date);
                billExchange.setUpdateUser(userName);
                billExchange.setPaymentId(payment.getId());
                billExchange.setMethodId(method.getId());
            } else if (PAY_METHOD_COUPON.equals(met.getType())) {
            	th.co.softpos.ws.mg.s002.RqHeader rqHeader = new th.co.softpos.ws.mg.s002.RqHeader();
                rqHeader.setFuncNm("S002MG");
                rqHeader.setRqAppId("POS");
                rqHeader.setUserId("9999");
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(new Date());
                try {
                    rqHeader.setRqDt(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
                } catch (DatatypeConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                th.co.softpos.ws.mg.s002.RqBody rqBody = new th.co.softpos.ws.mg.s002.RqBody();
                th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher giftvoucher = new th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher();
                giftvoucher.setCode(met.getCouponNo());
                giftvoucher.setStatus("S");
                rqBody.setMgTempImportDetailGiftvoucher(giftvoucher);
                th.co.softpos.ws.mg.s002.Request _process_rq = new th.co.softpos.ws.mg.s002.Request();
                _process_rq.setRqHeader(rqHeader);
                _process_rq.setRqBody(rqBody);
                th.co.softpos.ws.mg.s002.Response _response = s002MGUpdGiftvoucher.process(_process_rq);
                method.setChequeNo(met.getCouponNo());

            } else if (PAY_METHOD_OFFSET.equals(met.getType())) {
            	oFset = ofsetRepository.save(new Ofset());
                oFset.setAmount(met.getAmount());
                oFset.setNo(met.getOffsetDocumentNo());
                oFset.setOfsetcode(met.getOffsetAccountCode());
                oFset.setOfsetname(met.getOffsetAccountName());
                oFset.setUpdateDttm(date);
                oFset.setUpdateUser(userName);
                oFset.setPaymentId(payment.getId());
                oFset.setMethodId(method.getId());
            } else if (PAY_METHOD_OTHER.equals(met.getType())) {
                otherPay = otherRepository.save(new MethodOther());
                otherPay.setNo(met.getOtherNo());
                otherPay.setAmount(met.getAmount());
                otherPay.setType(met.getOtherType());
                otherPay.setDate(met.getOtherDt());
                otherPay.setUpdateDttm(date);
                otherPay.setUpdateUser(userName);
                otherPay.setPaymentId(payment.getId());
                otherPay.setMethodId(method.getId());
            } 

            payment.getMethods().add(method);
        }
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            customer = customerRepo.save(new Customer());
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1() + " " + cust.getAddress2());
            customer.setInvoiceAddr(cust.getAddress1() + " " + cust.getAddress2());
            customer.setBranch(cust.getCustBranch());
            customer.setAcctCatLkp(cust.getAcctCatLkp());
            customer.setCatCustomerSegment(cust.getCatCustomerSegment());
            receipt = receiptRepo.save(new Receipt());
            receipt.setUpdateDttm(date);
            receipt.setUpdateUser(userName);
            receipt.setDocDttm(date);
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                service = serviceRepo.save(new Service());
                service.setUpdateDttm(date);
                service.setUpdateUser(userName);
                addTransactionsIntoService(paids, svc, payment, service, date, userName);
                receipt.getServices().add(service);
                service.setReceiptId(receipt.getId());
                // service.setServiceCode(svc.getCode());
                // service.setServiceName(svc.getName());
                // service.setServiceNo(svc.getNo());
                // service.setProductCode(svc.getCode());
                // service.setProductName(svc.getName());
                // service.setProductSubCode(null);
                // service.setProductSubName(null);
                service.setIncomeType(svc.getType());
                // service.setIncomeAmount(new BigDecimal(svc.getMoreData()));
                // service.setIncomeUnit(svc.getUnit());
                service.setMdn(svc.getMdn());
                service.setIccid(svc.getIccid());
                service.setRefTransId(svc.getRefTransId());
                service.setAmount(svc.getAmount());
                service.setDiscount(svc.getDiscount());
                service.setCharge(svc.getCharge());
                service.setVat(svc.getVat());
                service.setTotalCharge(svc.getTotalCharge());
                service.setDeduction(svc.getDeduction());
                service.setVatRate(svc.getVatRate());
                service.setOrderId(cust.getCustNo());
            }
            BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                    rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                    rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO, rcpWt = BigDecimal.ZERO,
                    rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO;
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                rcpAmount = rcpAmount.add(svc.getAmount());
                rcpDiscount = rcpDiscount.add(svc.getDiscount());
                rcpVat = rcpVat.add(svc.getVat());
                rcpTotalCharge = rcpTotalCharge.add(svc.getTotalCharge());
                rcpDeduction = rcpDeduction.add(svc.getDeduction());
                rcpBalanceDue = rcpBalanceDue.add(BigDecimal.ZERO);
                rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(BigDecimal.ZERO);
                vatRate = svc.getVatRate();
            }
            rcpCharge = rcpAmount.subtract(rcpDiscount);
            for (Method method : payment.getMethods()) {
                rcpReceived = rcpReceived.add(method.getAmount());
            }
            rcpChange = rcpReceived.subtract(rcpTotalCharge);
            receipt.setPayment(payment);
            receipt.setType(getReceiptType(customer));
            receipt.setNo(
                    getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(receipt.getType()) ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE
                            : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, null, RECEIPT_HEADER_MNP));
            receipt.setName(cust.getCustName());
            receipt.setAccountName(cust.getCustName());
            receipt.setAccountNo(cust.getCustNo());
            receipt.setAccountSubNo("");
            receipt.setAccountBranch(cust.getCustBranch());
            receipt.setAccountType(cust.getCustType());
            receipt.setTaxNo(cust.getTaxNo());
            receipt.setAddrLine1(cust.getAddress1());
            receipt.setAddrLine2(cust.getAddress2());
            receipt.setPaymentCase(paymentDTO.getPaymentCase());
            receipt.setBranchCode(branchCode);
            receipt.setBranchArea(branchArea);
            receipt.setBranchName(branchName);
            receipt.setAmount(rcpAmount);
            receipt.setDiscount(rcpDiscount);
            receipt.setCharge(rcpCharge);
            receipt.setVatRate(vatRate);
            receipt.setVat(rcpVat);
            receipt.setTotalCharge(rcpTotalCharge);
            receipt.setDeduction(rcpDeduction);
            receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
            receipt.setBalanceDue(rcpBalanceDue);
            receipt.setReceived(rcpReceived);
            receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
            receipt.setAdvanced(null);
            receipt.setPromotion(null);
            receipt.setRemark(cust.getRemark());
            receipt.setReprint(0L);
            receipt.setAttributes("F");
            if (receipt.getVat() != null) {
                receipt.setFlgHeader(AppConstants.FLG_HEADER_1);
            } else {
                receipt.setFlgHeader(AppConstants.FLG_HEADER_2);
            }
            
            for (Method method : payment.getMethods()) {
                method.setRecieptId(receipt.getId());
            }
            receipt.setRef1(cust.getAgentAddressCode());// by NSD 01-03-2017
            receipt.setCustomer(customer);// by NSD 06-03-2017
            receipt.setCustCategoryDesc(cust.getCustCategoryDesc());// by NSD
            // 08-03-2017
            receipts.add(receipt);
            saveLogCorReceipt(receipt, receipt.getAttributes());
            amount = amount.add(rcpAmount);
            discount = discount.add(rcpDiscount);
            charge = charge.add(rcpCharge);
            vat = vat.add(rcpVat);
            totalCharge = totalCharge.add(rcpTotalCharge);
            balanceDue = balanceDue.add(rcpBalanceDue);
            afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
            deduction = deduction.add(rcpWt);
            received = received.add(rcpReceived);
        }
        payment.setOfficerId(officerId);
        payment.setShopId(EpContextHolder.getContext().getBranchId());
        payment.setPosId(EpContextHolder.getContext().getPosId());
        payment.setCollectionUnit(customer.getCollectionUnit());
        payment.setDate(date);
        payment.setType(PAYMENT_TYPE_MNP);
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);
        payment.setDiscount(discount);
        payment.setCharge(charge);
        payment.setVatRate(vatRate);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);
        payment.setDeduction(deduction);
        payment.setAfterSaleDiscount(afterSaleDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(received);
        payment.setChange(received.subtract(totalCharge));
        payment.setAdvanced(null);
        payment.setAttributes("M");

        return receipts;
    }

    @Transactional
    public List<Receipt> createPaymentTopup(SettlePaymentDTO paymentDTO) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        MethodCheque chequePay = new MethodCheque();
        MethodCreditCard creditCardPay = new MethodCreditCard();
        MethodMoneyTransfer moneyTransfer = new MethodMoneyTransfer();
        MethodMoneyOrder moneyOrder = new MethodMoneyOrder();
        MethodBillExchange billExchange = new MethodBillExchange();
        Ofset oFset = new Ofset();
        MethodOther otherPay = new MethodOther();
        Date date = new Date();
        Payment pm = new Payment();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO, received = BigDecimal.ZERO,
                vatRate = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();

        // Reorder: Cheque at first.
        for (SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
            paids.add(new Paid(ded.getAmount(), ded.getType()));
            deduct = deductRepo.save(new Deduct());
            deduct.setUpdateDttm(date);
            deduct.setUpdateUser(userName);
            deduct.setNo(ded.getWithholdingDocNo());
            deduct.setType(ded.getType());
            deduct.setAmount(ded.getAmount());
            deduct.setDate(date);
            deduct.setPaymentId(payment.getId());
            payment.getDeducts().add(deduct);
        }
        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            // paids.add(new Paid(met.getAmount(), met.getType())); //
            // Preparing: To substract into invoice.
            Paid paid = new Paid(met.getAmount(), met.getType());
            paids.add(paid); // Preparing: To substract into invoice.
            Method method = methodRepo.save(new Method());
            method.setUpdateDttm(date);
            method.setUpdateUser(userName);
            method.setCode(met.getCode());
            method.setName(met.getName());
            method.setAmount(met.getAmount());
            method.setPaymentId(payment.getId());
            method.setChequeNo(StringUtils.isNotEmpty(met.getChequeNo())?met.getChequeNo():met.getDocNo());


            if (PAY_METHOD_BANKTRANSFER.equals(met.getType())
                    || PAY_METHOD_FOREIGNTRANSFER.equals(met.getType())) { // Money
                // Transfer
            	moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
            	moneyTransfer.setAmount(met.getAmount());
                moneyTransfer.setUpdateDttm(date);
                moneyTransfer.setUpdateUser(userName);
                moneyTransfer.setDate(met.getTransferDt());
                moneyTransfer.setPayCode(met.getCode());
                moneyTransfer.setBankCode(met.getBankCode());
                moneyTransfer.setBankAcCd(met.getBankAcCd());
                moneyTransfer.setBankName(met.getBankName());
                moneyTransfer.setRefNo(met.getRefNo());
                moneyTransfer.setBankBrnh(met.getBankBrnh());
                moneyTransfer.setBankAcNo(met.getBankAcctNo());
                moneyTransfer.setCurrencyCode(met.getCurrencyCode());
                moneyTransfer.setCurrencyRate(met.getCurrencyRate());
                moneyTransfer.setForeignAmount(met.getForeignAmount());
                moneyTransfer.setPayType(met.getType());
                moneyTransfer.setPaymentId(payment.getId());
                moneyTransfer.setMethodId(method.getId());
                paid.setMoneyTransfer(moneyTransfer);
                paid.setIsBackDate(met.isBackDt());
            } else if (PAY_METHOD_CHEQUE.equals(met.getType())) {
                // TODO: complete all the saving methods and pulling them to
                // print correctly
                chequePay = chequeRepository.save(new MethodCheque());
                chequePay.setAmount(met.getAmount());
                chequePay.setBankCode(met.getPayChqBankCode());
                chequePay.setBankName(met.getPayChqBankName());
                chequePay.setBankBrnh(met.getPayChqBranch());
                chequePay.setChequeDate(met.getPayChqDate());
                chequePay.setNo(met.getChequeNo());
                chequePay.setUpdateUser(userName);
                chequePay.setUpdateDttm(date);
                chequePay.setPaymentId(payment.getId());
                chequePay.setMethodId(method.getId());

            } else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
                // Fix by EPIS8 30/12/2016 issue no 166
                creditCardPay = creditCardRepo.save(new MethodCreditCard());
                creditCardPay.setPaymentId(payment.getId());
                creditCardPay.setNo(met.getCardNo());
                creditCardPay.setAmount(met.getAmount());
                creditCardPay.setBankIssuer(met.getBankName());
                creditCardPay.setType(met.getCardType());
                creditCardPay.setUpdateDttm(date);
                creditCardPay.setUpdateUser(userName);
                creditCardPay.setMethodId(method.getId());
                // End Fix by EPIS8 30/12/2016 issue no 166
            } else if (PAY_METHOD_MONEYORDER.equals(met.getType())) {
                moneyOrder = moneyOrderRepository.save(new MethodMoneyOrder());
                moneyOrder.setNo(met.getMnyOrderNo());
                moneyOrder.setAmount(met.getAmount());
                moneyOrder.setDate(met.getMnyOrderDt());
                moneyOrder.setPostCode(met.getPostCode());
                moneyOrder.setUpdateDttm(date);
                moneyOrder.setUpdateUser(userName);
                moneyOrder.setPaymentId(payment.getId());
                moneyOrder.setMethodId(method.getId());
            } else if (PAY_METHOD_BILLEXCHANGE.equals(met.getType())) {
                billExchange = billOfExchangRepository.save(new MethodBillExchange());
                billExchange.setNo(met.getTrnfNo());
                billExchange.setAmount(met.getAmount());
                billExchange.setDate(met.getTransferDt());
                billExchange.setPostCode(met.getPostCode());
                billExchange.setUpdateDttm(date);
                billExchange.setUpdateUser(userName);
                billExchange.setPaymentId(payment.getId());
                billExchange.setMethodId(method.getId());
            } else if (PAY_METHOD_COUPON.equals(met.getType())) {
            	th.co.softpos.ws.mg.s002.RqHeader rqHeader = new th.co.softpos.ws.mg.s002.RqHeader();
                rqHeader.setFuncNm("S002MG");
                rqHeader.setRqAppId("POS");
                rqHeader.setUserId("9999");
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(new Date());
                try {
                    rqHeader.setRqDt(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
                } catch (DatatypeConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                th.co.softpos.ws.mg.s002.RqBody rqBody = new th.co.softpos.ws.mg.s002.RqBody();
                th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher giftvoucher = new th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher();
                giftvoucher.setCode(met.getCouponNo());
                giftvoucher.setStatus("S");
                rqBody.setMgTempImportDetailGiftvoucher(giftvoucher);
                th.co.softpos.ws.mg.s002.Request _process_rq = new th.co.softpos.ws.mg.s002.Request();
                _process_rq.setRqHeader(rqHeader);
                _process_rq.setRqBody(rqBody);
                th.co.softpos.ws.mg.s002.Response _response = s002MGUpdGiftvoucher.process(_process_rq);
                method.setChequeNo(met.getCouponNo());

            } else if (PAY_METHOD_OFFSET.equals(met.getType())) {
            	oFset = ofsetRepository.save(new Ofset());
                oFset.setAmount(met.getAmount());
                oFset.setNo(met.getOffsetDocumentNo());
                oFset.setOfsetcode(met.getOffsetAccountCode());
                oFset.setOfsetname(met.getOffsetAccountName());
                oFset.setUpdateDttm(date);
                oFset.setUpdateUser(userName);
                oFset.setPaymentId(payment.getId());
                oFset.setMethodId(method.getId());
            } else if (PAY_METHOD_OTHER.equals(met.getType())) {
                otherPay = otherRepository.save(new MethodOther());
                otherPay.setNo(met.getOtherNo());
                otherPay.setAmount(met.getAmount());
                otherPay.setType(met.getOtherType());
                otherPay.setDate(met.getOtherDt());
                otherPay.setUpdateDttm(date);
                otherPay.setUpdateUser(userName);
                otherPay.setPaymentId(payment.getId());
                otherPay.setMethodId(method.getId());
            } 

            payment.getMethods().add(method);
        }
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            customer = customerRepo.save(new Customer());
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1());
            customer.setInvoiceAddr(cust.getAddress2());

            customer.setBranch(cust.getCustBranch());
            customer.setAcctCatLkp(cust.getAcctCatLkp());
            customer.setCatCustomerSegment(cust.getCatCustomerSegment());

            receipt = receiptRepo.save(new Receipt());
            receipt.setUpdateDttm(date);
            receipt.setUpdateUser(userName);
            receipt.setDocDttm(date);
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                service = serviceRepo.save(new Service());
                service.setUpdateDttm(date);
                service.setUpdateUser(userName);
                addTransactionsIntoService(paids, svc, payment, service, date, userName);
                receipt.getServices().add(service);
                service.setReceiptId(receipt.getId());
                service.setServiceCode(svc.getCode());
                // service.setServiceName(svc.getName());
                service.setServiceName(svc.getServiceTypeName());// by NSD
                // 22-02-2016
                service.setServiceNo(svc.getNo());
                List<MapGLServiceTpye> listMapGLserviceTpye = new ArrayList<MapGLServiceTpye>();
                listMapGLserviceTpye = glServiceTypeRepository.MapGLServiceTpyeByServiceType(svc.getCode());
                service.setProductCode(listMapGLserviceTpye.get(0).getProductCode());
                service.setProductName(listMapGLserviceTpye.get(0).getProductName());
//				service.setProductName(svc.getServiceTypeName());// by NSD
                // 22-02-2016
                service.setPaymentId(payment.getId());
                service.setProductSubCode(listMapGLserviceTpye.get(0).getSubProductCode());
                service.setProductSubName(listMapGLserviceTpye.get(0).getSubProductName());
                service.setRevenueTypeCode(listMapGLserviceTpye.get(0).getRevenueTypeCode());
                service.setRevenueTypeName(listMapGLserviceTpye.get(0).getRevenueTypeName());
                service.setSegmentCode(Long.valueOf(listMapGLserviceTpye.get(0).getSegmentCode()));
                service.setSegmentName(listMapGLserviceTpye.get(0).getSegmentName());
                service.setIncomeType(svc.getType());
                service.setIncomeAmount(svc.getAmount());
                service.setIncomeUnit(svc.getUnit());
                service.setRefTransId(svc.getRefTransId());
                service.setAmount(svc.getAmount());
                service.setDiscount(svc.getDiscount());
                service.setCharge(svc.getCharge());
                service.setVat(svc.getVat());
                service.setTotalCharge(svc.getTotalCharge());
                service.setDeduction(svc.getDeduction());
                service.setVatRate(svc.getVatRate());
                service.setServiceTypeName(svc.getServiceTypeName());// by NSD
                // 16-02-2017
                service.setPromotionName(svc.getPromotion());
                /*FIX Code 30-10-2017 */
                service.setGlAccount(svc.getGlAccount());
                service.setProfitCode(svc.getProfitCode());
                service.setProfitName(svc.getProfitName());
                service.setGroupCode(svc.getGroupCode());
                service.setGroupName(svc.getGroupName());

            }
            BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                    rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                    rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO, rcpWt = BigDecimal.ZERO,
                    rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO;
            String rcpPro = "";
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                rcpAmount = rcpAmount.add(svc.getAmount());
                rcpDiscount = rcpDiscount.add(svc.getDiscount());
                rcpVat = rcpVat.add(svc.getVat());
                rcpTotalCharge = rcpTotalCharge.add(svc.getTotalCharge());
                rcpDeduction = rcpDeduction.add(svc.getDeduction());
                rcpBalanceDue = rcpBalanceDue.add(BigDecimal.ZERO);
                rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(BigDecimal.ZERO);
                vatRate = svc.getVatRate();
                rcpPro = svc.getPromotion();// by NSD 22-02-2017
            }
            rcpCharge = rcpAmount.subtract(rcpDiscount);
            // rcpCharge = rcpAmount.subtract(new BigDecimal(0));//now no
            // special discount
            for (Method method : payment.getMethods()) {
                rcpReceived = rcpReceived.add(method.getAmount());
                method.setRecieptId(receipt.getId());
            }

            // rcpChange = rcpReceived.subtract(rcpTotalCharge);
            rcpChange = rcpTotalCharge.subtract(rcpTotalCharge);// by NSD
            // 03-02-2017
            receipt.setPayment(payment);
            receipt.setType(getReceiptType(customer));
            receipt.setNo(
                    getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(receipt.getType()) ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE
                            : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, null, RECEIPT_HEADER_EPO));
            receipt.setName(cust.getCustName());
            receipt.setAccountName(cust.getCustName());
            receipt.setAccountNo(cust.getCustNo());
            receipt.setAccountSubNo("");
            receipt.setAccountType(cust.getCustType());

            receipt.setAccountBranch(cust.getCustBranch());

            receipt.setTaxNo(cust.getTaxNo());
            receipt.setAddrLine1(customer.getReceiptAddr());
            receipt.setAddrLine2(customer.getInvoiceAddr());
            receipt.setPaymentCase(paymentDTO.getPaymentCase());
            receipt.setBranchCode(branchCode);
            receipt.setBranchArea(branchArea);
            receipt.setBranchName(branchName);
            receipt.setAmount(rcpAmount);
            receipt.setDiscount(rcpDiscount);
            receipt.setCharge(rcpCharge);
            receipt.setVatRate(vatRate);
            receipt.setVat(rcpVat);
            receipt.setTotalCharge(rcpTotalCharge);
            receipt.setDeduction(rcpDeduction);
            receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
            receipt.setBalanceDue(rcpBalanceDue);
            // receipt.setReceived(rcpReceived);
            receipt.setReceived(rcpTotalCharge);// by NSD 03-02-2017
            receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
            receipt.setAdvanced(null);
            // receipt.setPromotion(null);
            receipt.setPromotion(rcpPro);// by NSD 22-02-2017
            receipt.setRemark(cust.getRemark());
            receipt.setReprint(0L);
            receipt.setAttributes("FC");
            receipt.setCustCategoryDesc(cust.getCustCategoryDesc());
            receipt.setRef1(cust.getRef1());// by NSD 09-03-2017
            receipt.setFlgHeader(FLG_HEADER_1);// by NSD 26-04-2017

            //Set PosID And PosNo
            receipt.setPosid(EpContextHolder.getContext().getPosId());
            receipt.setPosno(EpContextHolder.getContext().getPosNo());

            receipts.add(receipt);
            saveLogCorReceipt(receipt, receipt.getAttributes());
            amount = amount.add(rcpAmount);
            discount = discount.add(rcpDiscount);
            charge = charge.add(rcpCharge);
            vat = vat.add(rcpVat);
            totalCharge = totalCharge.add(rcpTotalCharge);
            balanceDue = balanceDue.add(rcpBalanceDue);
            afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
            deduction = deduction.add(rcpWt);
            received = received.add(rcpReceived);
        }
        payment.setOfficerId(officerId);
        payment.setShopId(EpContextHolder.getContext().getBranchId());
        payment.setPosId(EpContextHolder.getContext().getPosId());
        payment.setCollectionUnit(customer.getCollectionUnit());
        payment.setDate(date);
        payment.setType(PAYMENT_TYPE_TOPUP);
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);
        payment.setDiscount(discount);
        payment.setCharge(charge);
        payment.setVatRate(vatRate);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);
        payment.setDeduction(deduction);
        payment.setAfterSaleDiscount(afterSaleDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(received);
        payment.setChange(received.subtract(totalCharge));
        payment.setAdvanced(null);
        payment.setAttributes("T");

        return receipts;
    }

    @Transactional
    public List<Receipt> createPaymentAgent(SettlePaymentDTO paymentDTO) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        MethodCheque chequePay = new MethodCheque();
        MethodCreditCard creditCardPay = new MethodCreditCard();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO, received = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();

        // Reorder: Cheque at first.
        for (SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
            paids.add(new Paid(ded.getAmount(), ded.getType()));
            deduct = deductRepo.save(new Deduct());
            deduct.setUpdateDttm(date);
            deduct.setUpdateUser(userName);
            deduct.setNo(ded.getWithholdingDocNo());
            deduct.setType(ded.getType());
            deduct.setAmount(ded.getAmount());
            deduct.setDate(date);
            deduct.setPaymentId(payment.getId());
            payment.getDeducts().add(deduct);
        }
        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            Paid paid = new Paid(met.getAmount(), met.getType());
            paids.add(paid); // Preparing: To substract into invoice.
            Method method = methodRepo.save(new Method());
            method.setUpdateDttm(date);
            method.setUpdateUser(userName);
            method.setCode(met.getCode());
            method.setName(met.getName());
            method.setAmount(met.getAmount());
            method.setChequeNo(StringUtils.isNotEmpty(met.getChequeNo())?met.getChequeNo():met.getDocNo());
            method.setPaymentId(payment.getId());
            payment.getMethods().add(method);
             if (PAY_METHOD_CHEQUE.equals(met.getType())) {
                // TODO: complete all the saving methods and pulling them to
                // print correctly
            	chequePay = chequeRepository.save(new MethodCheque());
                chequePay.setAmount(met.getAmount());
                chequePay.setBankCode(met.getPayChqBankCode());
                chequePay.setBankName(met.getPayChqBankName());
                chequePay.setBankBrnh(met.getPayChqBranch());
                chequePay.setChequeDate(DateUtil.convertToDate(met.getChequeDt()));
                chequePay.setNo(met.getChequeNo());
                chequePay.setUpdateUser(userName);
                chequePay.setUpdateDttm(date);
                chequePay.setPaymentId(payment.getId());
                chequePay.setMethodId(method.getId());
            } else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
                // Fix by EPIS8 30/12/2016 issue no 166
            	creditCardPay = creditCardRepo.save(new MethodCreditCard());
                creditCardPay.setPaymentId(payment.getId());
                creditCardPay.setNo(met.getCardNo());
                creditCardPay.setAmount(met.getAmount());
                creditCardPay.setBankIssuer(met.getBankName());
                creditCardPay.setType(met.getCardType());
                creditCardPay.setUpdateDttm(date);
                creditCardPay.setUpdateUser(userName);
                creditCardPay.setMethodId(method.getId());
                // End Fix by EPIS8 30/12/2016 issue no 166
            }
             /*
            if ("7".equals(met.getCode())) { // Money Transfer
                MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                moneyTransfer.setUpdateDttm(date);
                moneyTransfer.setUpdateUser(userName);
                moneyTransfer.setDate(met.getTransferDt());
                paid.setMoneyTransfer(moneyTransfer);
                paid.setIsBackDate(met.isBackDt());
            }
            */
        }
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            customer = customerRepo.save(new Customer());
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1());
            customer.setInvoiceAddr(cust.getAddress2());

            // Additional Conditions for GFMIS, date and branch
            String customerType = stripToEmpty(customer.getType()).toLowerCase();
            if (customerType.indexOf("organization") == 0 || customerType.indexOf("stateagency") == 0) {
                customer.setBranch(cust.getCustBranch());
            }
            for (Paid paid : paids) {
                if (paid.getMoneyTransfer() != null & paid.isBackDate()) {
                    receiptDttm = paid.getMoneyTransfer().getDate();
                    // by NSD 24-04-2017
                    String receiptType = getReceiptType2(customer);
                    Date dtFrom = paid.getMoneyTransfer().getDate();
                    Calendar c = Calendar.getInstance();
                    c.setTime(dtFrom);
                    c.add(Calendar.DATE, 1);
                    Date dtTo = c.getTime();
					/*
					 * List<Receipt> rcptList = receiptRepo.
					 * findByTypeAndBranchAreaAndDocDttmOrderByNoDesc(
					 * receiptType, branchArea, dtFrom, dtTo); BeanComparator
					 * fieldComparator = new BeanComparator("no");
					 * Collections.sort(rcptList, fieldComparator);
					 * if(rcptList!=null && rcptList.size()>0){ receiptDttm =
					 * rcptList.get(rcptList.size()-1).getDocDttm(); }
					 */
                    break;
                }
            }

            boolean isFee = false;
            receipt = receiptRepo.save(new Receipt());
            receipt.setUpdateDttm(date);
            receipt.setUpdateUser(userName);
            receipt.setDocDttm(receiptDttm);
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                service = serviceRepo.save(new Service());
                service.setUpdateDttm(date);
                service.setUpdateUser(userName);
                addTransactionsIntoService(paids, svc, payment, service, date, userName);
                receipt.getServices().add(service);
                if (svc.getFeeFlg().equalsIgnoreCase("FEE")) {
                    isFee = true;
                    receipt.setRef2(AppConstants.DEDUCT_METHOD_FEE_IN);
                }

                // if(svc.getCode().equalsIgnoreCase("FEE")) isFee = true;
                service.setReceiptId(receipt.getId());
                service.setServiceCode(svc.getCode());
                service.setServiceName(svc.getName());
                service.setServiceNo(svc.getNo());
                service.setProductCode(svc.getCode());
                service.setProductName(svc.getName());
                service.setProductSubCode(null);
                service.setProductSubName(null);
                service.setIncomeType(svc.getType());
                service.setIncomeAmount(new BigDecimal(svc.getMoreData()));
                service.setIncomeUnit(svc.getUnit());
                service.setRefTransId(svc.getRefTransId());
                service.setAmount(svc.getAmount());
                service.setDiscount(svc.getDiscount());
                service.setCharge(svc.getCharge());
                service.setVatRate(VAT_RATE);
                service.setVat(svc.getVat());
                service.setTotalCharge(svc.getTotalCharge());
                service.setDeduction(svc.getDeduction());
                service.setPaymentId(payment.getId());
                service.setRef1(svc.getRef1());
                service.setRef2(svc.getRef2());

            }
            BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                    rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                    rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO, rcpWt = BigDecimal.ZERO,
                    rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO;
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                rcpAmount = rcpAmount.add(svc.getAmount());
                rcpDiscount = rcpDiscount.add(svc.getDiscount());
                rcpVat = rcpVat.add(svc.getVat());
                rcpTotalCharge = rcpTotalCharge.add(svc.getTotalCharge());
                rcpDeduction = rcpDeduction.add(svc.getDeduction());
                rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(BigDecimal.ZERO); // how
                // to
                // distribute
                // 1
                // value
                // from
                // screen
                // to
                // every
                // services
                rcpBalanceDue = rcpBalanceDue.add(BigDecimal.ZERO);
            }
            rcpCharge = rcpAmount.subtract(rcpDiscount);
            for (Method method : payment.getMethods()) {
                rcpReceived = rcpReceived.add(method.getAmount());
            }
            rcpChange = rcpReceived.subtract(rcpTotalCharge);
            receipt.setPayment(payment);
            receipt.setType(getReceiptType(customer));
            receipt.setNo(getReceiptNo(posNo,
                    RECEIPT_TYPE_FULL.equals(receipt.getType()) ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE
                            : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE,
                    receiptDttm, (!isFee) ? cust.getAgentAddressCode() : RECEIPT_HEADER_EPO));
            receipt.setName(cust.getCustName());
            receipt.setAccountName(cust.getCustName());
            receipt.setAccountNo(cust.getCustNo());
            receipt.setAccountSubNo("");
            receipt.setAccountType(cust.getCustType());
            receipt.setAccountBranch(cust.getCustBranch());
            receipt.setTaxNo(cust.getTaxNo());
            receipt.setTaxNoAgent(cust.getAgentTaxNo());
            receipt.setAddrLine1(customer.getReceiptAddr());
            receipt.setAddrLine2(customer.getInvoiceAddr());
            receipt.setPaymentCase(paymentDTO.getPaymentCase());
            receipt.setBranchCode(branchCode);
            receipt.setBranchArea(branchArea);
            receipt.setBranchName(branchName);
            receipt.setAmount(rcpAmount);
            receipt.setDiscount(rcpDiscount);
            receipt.setCharge(rcpCharge);
            receipt.setVatRate(VAT_RATE);
            receipt.setVat(rcpVat);
            receipt.setTotalCharge(rcpTotalCharge);
            receipt.setDeduction(rcpDeduction);
            receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
            receipt.setBalanceDue(rcpBalanceDue);
            receipt.setReceived(rcpReceived);
            receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
            receipt.setAdvanced(null);
            receipt.setPromotion(null);
            receipt.setRemark(cust.getRemark());
            receipt.setReprint(0L);
            receipt.setAttributes("FC");
            receipt.setRef1(cust.getRef1()); // Add by Puthy -- Modify by W3P
            // 04/07/2017
            receipt.setFeesIncome(cust.getFeesIncome()); // W3P 04/07/2017
            if (receipt.getVat() != null) {
                receipt.setFlgHeader(AppConstants.FLG_HEADER_1);
            } else {
                receipt.setFlgHeader(AppConstants.FLG_HEADER_2);
            }
            for (Method method : payment.getMethods()) {
                method.setRecieptId(receipt.getId());
            }
            receipts.add(receipt);
            saveLogCorReceipt(receipt, receipt.getAttributes());
            amount = amount.add(rcpAmount);
            discount = discount.add(rcpDiscount);
            charge = charge.add(rcpCharge);
            vat = vat.add(rcpVat);
            totalCharge = totalCharge.add(rcpTotalCharge);
            balanceDue = balanceDue.add(rcpBalanceDue);
            afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
            deduction = deduction.add(rcpWt);
            received = received.add(rcpReceived);
        }
        received = received.divide(new BigDecimal(2), 2);
        payment.setOfficerId(officerId);
        payment.setShopId(EpContextHolder.getContext().getBranchId());
        payment.setPosId(EpContextHolder.getContext().getPosId());
        payment.setCollectionUnit(customer.getCollectionUnit());
        payment.setDate(date);
        payment.setType(PAYMENT_TYPE_AGENT);
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);
        payment.setDiscount(discount);
        payment.setCharge(charge);
        payment.setVatRate(VAT_RATE);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);
        payment.setDeduction(deduction);
        payment.setAfterSaleDiscount(afterSaleDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(received);
        payment.setChange(received.subtract(totalCharge));
        payment.setAdvanced(null);
        payment.setAttributes("A");

        return receipts;
    }

    void addTransactionsIntoService(List<Paid> paids, SettlePaymentDTO.Service svc, Payment payment, Service service,
                                    Date date, String user) {
        Transaction transaction;
        boolean isEnd = false;
        BigDecimal balanceDue = svc.getAmount();
        for (Paid paid : paids) {
            BigDecimal payInv = BigDecimal.ZERO;
            if (paid.getAmount().compareTo(BigDecimal.ZERO) <= 0)
                continue;
            if (balanceDue!=null && paid.getAmount().compareTo(balanceDue) >= 0) {
                payInv = balanceDue;
                paid.setAmount(paid.getAmount().subtract(payInv));
                balanceDue = BigDecimal.ZERO;
                isEnd = true;
            } else {
                payInv = paid.getAmount();
                paid.setAmount(BigDecimal.ZERO);
                balanceDue = balanceDue.subtract(payInv);
                isEnd = false;
            }
            transaction = transactionRepo.save(new Transaction());
            transaction.setUpdateDttm(date);
            transaction.setUpdateUser(user);
            transaction.setServiceId(service.getId());
            transaction.setTransactionId(AppUtil.generateTransactionID(15));
            transaction.setTrackingDetails("EPIS is waiting for ESP response message.");
            transaction.setTrackingRetry(0);
            transaction.setPaymentDate(date);
            transaction.setPaymentType(paid.getType());
            transaction.setAmount(payInv);
            transaction.setChequeNo(paid.getChequeNo());
            transaction.setAccountNo(paid.getBankAccount());
            transaction.setStatus(null);
            transaction.setPayment(payment);
            service.getTransactions().add(transaction);
            if (isEnd)
                break;
        }
    }

    @Transactional
    public List<Receipt> createPaymentTBOSS(SettlePaymentDTO paymentDTO) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer;
        Invoice invoice;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();

        // Reorder: Cheque at first.
        for (SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
            paids.add(new Paid(ded.getAmount(), ded.getType()));
            deduct = deductRepo.save(new Deduct());
            deduct.setUpdateDttm(date);
            deduct.setUpdateUser(userName);
            deduct.setNo(ded.getWithholdingDocNo());
            deduct.setType(ded.getType());
            deduct.setAmount(ded.getAmount());
            deduct.setDate(date);
            deduct.setPaymentId(payment.getId());
            payment.getDeducts().add(deduct);
        }
        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            Paid paid = new Paid(met.getAmount(), met.getType());
            paids.add(paid); // Preparing: To substract into invoice.
            Method method = methodRepo.save(new Method());
            method.setUpdateDttm(date);
            method.setUpdateUser(userName);
            method.setCode(met.getCode());
            method.setName(met.getName());
            method.setChequeNo(met.getChequeNo());
            method.setAccountNo(met.getBankAcct());
            method.setAmount(met.getAmount());
            method.setPaymentId(payment.getId());
            payment.getMethods().add(method);
            if ("7".equals(met.getCode())) { // Money Transfer
                MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                moneyTransfer.setUpdateDttm(date);
                moneyTransfer.setUpdateUser(userName);
                moneyTransfer.setDate(met.getTransferDt());
                paid.setMoneyTransfer(moneyTransfer);
                paid.setIsBackDate(met.isBackDt());
            }
        }
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            customer = customerRepo.save(new Customer());
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1());
            customer.setInvoiceAddr(cust.getAddress2());

            // Additional Conditions for GFMIS, date and branch
            String customerType = stripToEmpty(customer.getType()).toLowerCase();
            if (customerType.indexOf("organization") == 0 || customerType.indexOf("stateagency") == 0) {
                customer.setBranch(cust.getCustBranch());
            }
            for (Paid paid : paids) {
                if (paid.getMoneyTransfer() != null & paid.isBackDate()) {
                    receiptDttm = paid.getMoneyTransfer().getDate();
                    break;
                }
            }
            boolean split = cust.getSplit();
            if (!split) {
                if (cust.getInvoices().size() < 1)
                    continue;
                receipt = receiptRepo.save(new Receipt());
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    invoice = invoiceRepo.save(new Invoice());
                    invoice.setUpdateDttm(date);
                    invoice.setUpdateUser(userName);
                    receipt.getInvoices().add(invoice);
                    Service service = serviceRepo.save(new Service());
                    service.setUpdateDttm(date);
                    service.setReceiptId(receipt.getId());
                    service.setInvoiceId(invoice.getId());
                    service.setAccountNo("");
                    service.setProductCode("");
                    service.setProductName("");
                    service.setProductSubCode("");
                    service.setProductSubName("");
                    service.setIncomeType("1");
                    service.setAmount(inv.getReceived());
                    receipt.getServices().add(service);
                    invoice.getServices().add(service);
                    addTransactionsIntoService(paids, payment, invoice, date, userName);
                    invoice.setReceiptId(receipt.getId());
                    invoice.setCustomer(customer);
                    invoice.setPayment(payment);
                    invoice.setNo(inv.getNo());
                    invoice.setKenan(inv.getKenan());
                    invoice.setCurrencyCode(inv.getCurrencyCode());
                    invoice.setIssueDt(inv.getIssueDt());
                    invoice.setDueDt(inv.getDueDt());
                    invoice.setBillCycle(inv.getBillCycle());
                    invoice.setAmount(inv.getAmount());
                    invoice.setDiscount(inv.getDiscount());
                    invoice.setCharge(inv.getCharge());
                    invoice.setVatRate(inv.getVatRate());
                    invoice.setVat(inv.getVat());
                    invoice.setTotalCharge(inv.getReceived());
                    invoice.setDeduction(inv.getDeduction());
                    invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
                    invoice.setBalanceDue(inv.getBalanceDue());
                    invoice.setReceived(inv.getReceived());
                    invoice.setChange(inv.getChange());
                    invoice.setAdvanced(inv.getAdvanced());
                    invoice.setStatus(inv.getStatus());
                    invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0
                            ? (inv.getTotalCharge().compareTo(invoice.getReceived()) != 0 ? "P" : "F") : "P");
                }
                BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                        rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                        rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO,
                        rcpWt = BigDecimal.ZERO, rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO;
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    rcpAmount = rcpAmount.add(inv.getAmount());
                    rcpDiscount = rcpDiscount.add(inv.getDiscount());
                    rcpCharge = rcpCharge.add(inv.getCharge());
                    rcpVat = rcpVat.add(inv.getVat());
                    rcpDeduction = rcpDeduction.add(inv.getDeduction());
                    rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(inv.getAfterSaleDiscount());
                    rcpBalanceDue = rcpBalanceDue.add(inv.getBalanceDue());
                }
                for (Invoice inv : receipt.getInvoices()) {
                    rcpTotalCharge = rcpTotalCharge.add(inv.getTotalCharge());
                    rcpReceived = rcpReceived.add(inv.getReceived());
                }
                rcpChange = rcpReceived.subtract(rcpTotalCharge);
                receipt.setCustomer(customer);
                receipt.setPayment(payment);
                receipt.setType(getReceiptType(customer));
                receipt.setNo(getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(receipt.getType())
                                ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, receiptDttm,
                        RECEIPT_HEADER_EPO));
                receipt.setName(cust.getCustName());
                receipt.setAccountName(cust.getCustName());
                receipt.setAccountNo(cust.getCustNo());
                receipt.setAccountSubNo("");
                receipt.setAccountType(cust.getCustType());
                receipt.setTaxNo(cust.getTaxNo());
				/* ise fixed no.158 */
                // receipt.setAccountBranch(customer.getBranch());
                receipt.setAccountBranch(cust.getCustBranch());
				/* ise fixed no.158 */
                receipt.setAddrLine1(customer.getReceiptAddr());
                receipt.setAddrLine2(customer.getInvoiceAddr());
                receipt.setPaymentCase(paymentDTO.getPaymentCase());
                receipt.setBranchCode(branchCode);
                receipt.setBranchArea(branchArea);
                receipt.setBranchName(branchName);
                receipt.setAmount(rcpAmount);
                receipt.setDiscount(rcpDiscount);
                receipt.setCharge(rcpCharge);
                receipt.setVatRate(VAT_RATE);
                receipt.setVat(rcpVat);
                receipt.setTotalCharge(rcpTotalCharge);
                receipt.setDeduction(rcpDeduction);
                receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
                receipt.setBalanceDue(rcpBalanceDue);
                receipt.setReceived(rcpReceived);
                receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
                receipt.setAdvanced(null);
                receipt.setPromotion(null);
                receipt.setRemark(cust.getRemark());
                receipt.setReprint(0L);
                receipt.setAttributes("F");
                receipts.add(receipt);
                saveLogCorReceipt(receipt, receipt.getAttributes());
                amount = amount.add(rcpAmount);
                discount = discount.add(rcpDiscount);
                charge = charge.add(rcpCharge);
                vat = vat.add(rcpVat);
                totalCharge = totalCharge.add(rcpTotalCharge);
                balanceDue = balanceDue.add(rcpBalanceDue);
                afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
                deduction = deduction.add(rcpWt);
            } else {
                BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                        rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpBalanceDue = BigDecimal.ZERO,
                        rcpAfterSaleDiscount = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO;
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    rcpAmount = rcpAmount.add(inv.getAmount());
                    rcpDiscount = rcpDiscount.add(inv.getDiscount());
                    rcpCharge = rcpCharge.add(inv.getCharge());
                    rcpVat = rcpVat.add(inv.getVat());
                    rcpTotalCharge = rcpTotalCharge.add(inv.getTotalCharge());
                    rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(inv.getAfterSaleDiscount());
                    rcpBalanceDue = rcpBalanceDue.add(inv.getBalanceDue());
                    rcpDeduction = rcpDeduction.add(inv.getDeduction());
                    receipt = receiptRepo.save(new Receipt());
                    receipt.setUpdateDttm(date);
                    receipt.setUpdateUser(userName);
                    receipt.setDocDttm(receiptDttm);
                    invoice = invoiceRepo.save(new Invoice());
                    invoice.setUpdateDttm(date);
                    invoice.setUpdateUser(userName);
                    Service service = serviceRepo.save(new Service());
                    service.setUpdateDttm(date);
                    service.setReceiptId(receipt.getId());
                    service.setInvoiceId(invoice.getId());
                    service.setAccountNo("");
                    service.setProductCode("");
                    service.setProductName("");
                    service.setProductSubCode("");
                    service.setProductSubName("");
                    service.setIncomeType("");
                    service.setAmount(inv.getReceived());
                    receipt.getServices().add(service);
                    invoice.getServices().add(service);
                    addTransactionsIntoService(paids, payment, invoice, date, userName);
                    invoice.setReceiptId(receipt.getId());
                    invoice.setCustomer(customer);
                    invoice.setPayment(payment);
                    invoice.setNo(inv.getNo());
                    invoice.setKenan(inv.getKenan());
                    invoice.setCurrencyCode(inv.getCurrencyCode());
                    invoice.setIssueDt(inv.getIssueDt());
                    invoice.setDueDt(inv.getDueDt());
                    invoice.setBillCycle(inv.getBillCycle());
                    invoice.setAmount(inv.getAmount());
                    invoice.setDiscount(inv.getDiscount());
                    invoice.setCharge(inv.getCharge());
                    invoice.setVatRate(VAT_RATE);
                    invoice.setVat(inv.getVat());
                    invoice.setTotalCharge(inv.getReceived());
                    invoice.setDeduction(inv.getDeduction());
                    invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
                    invoice.setBalanceDue(inv.getBalanceDue());
                    invoice.setReceived(inv.getReceived());
                    invoice.setChange(inv.getChange());
                    invoice.setAdvanced(inv.getAdvanced());
                    invoice.setStatus(inv.getStatus());
                    invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0
                            ? (inv.getTotalCharge().compareTo(invoice.getReceived()) != 0 ? "P" : "F") : "P");
                    receipt.getInvoices().add(invoice);
                    receipt.setCustomer(customer);
                    receipt.setPayment(payment);
                    receipt.setType(getReceiptType(customer));
                    receipt.setNo(
                            getReceiptNo(
                                    posNo, RECEIPT_TYPE_FULL.equals(receipt.getType())
                                            ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE,
                                    receiptDttm, RECEIPT_HEADER_EPO));
                    receipt.setName(cust.getCustName());
                    receipt.setAccountName(cust.getCustName());
                    receipt.setAccountNo(cust.getCustNo());
                    receipt.setAccountSubNo("");
                    receipt.setAccountType(cust.getCustType());
                    receipt.setTaxNo(cust.getTaxNo());
                    receipt.setAccountBranch(cust.getCustBranch());
                    // receipt.setAccountBranch(customer.getBranch());
                    receipt.setAddrLine1(cust.getAddress1());
                    receipt.setAddrLine2(cust.getAddress2());
                    receipt.setPaymentCase(paymentDTO.getPaymentCase());
                    receipt.setBranchCode(branchCode);
                    receipt.setBranchArea(branchArea);
                    receipt.setBranchName(branchName);
                    receipt.setAmount(rcpAmount);
                    receipt.setDiscount(rcpDiscount);
                    receipt.setCharge(rcpCharge);
                    receipt.setVatRate(VAT_RATE);
                    receipt.setVat(rcpVat);
                    receipt.setTotalCharge(rcpTotalCharge);
                    receipt.setDeduction(rcpDeduction);
                    receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
                    receipt.setBalanceDue(rcpBalanceDue);
                    receipt.setReceived(invoice.getReceived());
                    receipt.setChange(invoice.getChange());
                    receipt.setAdvanced(null);
                    receipt.setPromotion(null);
                    receipt.setRemark(cust.getRemark());
                    receipt.setReprint(0L);
                    receipt.setAttributes("F");
                    receipts.add(receipt);
                    saveLogCorReceipt(receipt, receipt.getAttributes());
                    amount = amount.add(rcpAmount);
                    discount = discount.add(rcpDiscount);
                    charge = charge.add(rcpCharge);
                    vat = vat.add(rcpVat);
                    totalCharge = totalCharge.add(rcpTotalCharge);
                    balanceDue = balanceDue.add(rcpBalanceDue);
                    afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
                    deduction = deduction.add(rcpDeduction);
                }
            }
        }
        payment.setOfficerId(officerId);
        payment.setDate(date);
        payment.setType(PAYMENT_TYPE_TBOSS);
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);
        payment.setDiscount(discount);
        payment.setCharge(charge);
        payment.setVatRate(VAT_RATE);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);
        payment.setDeduction(deduction);
        payment.setAfterSaleDiscount(afterSaleDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(paymentDTO.getReceiveAmount());
        payment.setChange(paymentDTO.getRemainAmount());
        payment.setAdvanced(BigDecimal.ZERO);
        payment.setAttributes("B");
        int result = dwService.sendOtbossWereHose(receipt.getPayment().getId());
		System.out.println(result);
        return receipts;
    }

    @Transactional
    public void deductTBOSSBadDebt(List<Receipt> receipts) {
        // calculate all the deduction from AccountTBOSS, ContractTBOSS and
        // write Transaction into TrsAccountTBOSS
        Set<String> contractNumbers = new HashSet<String>();
        for (Receipt receipt : receipts) {
            contractNumbers.add(receipt.getAccountNo());
        }
        List<AccountTBOSS> allInvoiceList = new ArrayList<AccountTBOSS>();
        for (String contractNo : contractNumbers) {
            allInvoiceList.addAll(accountTBOSSRepo.findByContractNo(contractNo));
        }
        AccountTBOSS accountReceivable;
        TrsAccountTBOSS transaction;
        for (final Receipt receipt : receipts) {
            for (final Invoice invoice : receipt.getInvoices()) {
                accountReceivable = find(allInvoiceList, new Predicate<AccountTBOSS>() {
                    @Override
                    public boolean apply(AccountTBOSS account) {
                        return trimToEmpty(account.getContractNo()).equals(receipt.getAccountNo())
                                && trimToEmpty(account.getInvoiceNo().toString()).equals(invoice.getNo());
                    }
                });
                accountReceivable.setUpdateUser(receipt.getUpdateUser());
                accountReceivable.setUpdateSystem(EPIS_SYSTEM_ABVR);
                accountReceivable.setReceiveDatetime(receipt.getDocDttm());
                accountReceivable.setUpdateDttm(invoice.getUpdateDttm());
                accountReceivable.setPaidAmount(accountReceivable.getPaidAmount().add(invoice.getAmount()));
                BigDecimal balanceDue = accountReceivable.getBalanceDue().subtract(invoice.getAmount());
                accountReceivable
                        .setBalanceDue((balanceDue.compareTo(BigDecimal.ZERO) >= 0) ? balanceDue : BigDecimal.ZERO);
                transaction = new TrsAccountTBOSS();
                transaction.setUpdateDttm(invoice.getUpdateDttm());
                transaction.setUpdateUser(receipt.getUpdateUser());
                transaction.setUpdateSystem(EPIS_SYSTEM_ABVR);
                transaction.setAccountTbossId(accountReceivable.getId());
                transaction.setPaydatetime(receipt.getDocDttm());
                transaction.setPaymentId(receipt.getPayment().getId());
                transaction.setAmount(invoice.getAmount());
                transaction.setVatAmount(invoice.getVat());
                transaction.setBalanceDue(balanceDue);
                transaction.setReceived(invoice.getReceived());
                accountTBOSSRepo.save(accountReceivable);
                trsAccountTBOSSRepo.save(transaction);
            }
            receipt.setAttributes(receipt.getAttributes().replaceAll("C", "") + "C");
            saveLogCorReceipt(receipt, "C");
            receiptRepo.save(receipt);
        }
    }

    @Transactional
    public void reverseTBOSSBadDebt(Iterable<Receipt> receipts, final String userCode, final String branchCode) {
        // calculate all the deduction from AccountTBOSS, ContractTBOSS and
        // reverse Transaction into TrsAccountTBOSS
        Set<String> contractNumbers = new HashSet<String>();
        for (Receipt receipt : receipts) {
            contractNumbers.add(receipt.getAccountNo());
        }
        List<AccountTBOSS> allInvoiceList = new ArrayList<AccountTBOSS>();
        for (String contractNo : contractNumbers) {
            allInvoiceList.addAll(accountTBOSSRepo.findByContractNo(contractNo));
        }
        AccountTBOSS accountReceivable;
        List<TrsAccountTBOSS> transactions;
        Date cancelledDttm = new Date();
        for (final Receipt receipt : receipts) {
            for (final Invoice invoice : receipt.getInvoices()) {
                accountReceivable = find(allInvoiceList, new Predicate<AccountTBOSS>() {
                    @Override
                    public boolean apply(AccountTBOSS account) {
                        return trimToEmpty(account.getContractNo()).equals(receipt.getAccountNo())
                                && trimToEmpty(account.getInvoiceNo().toString()).equals(invoice.getNo());
                    }
                });
                transactions = trsAccountTBOSSRepo.findByAccountTbossIdAndPaymentId(accountReceivable.getId(),
                        receipt.getPayment().getId());
                BigDecimal balanceDue = BigDecimal.ZERO;
                for (TrsAccountTBOSS transaction : transactions) {
                    balanceDue = balanceDue.add(transaction.getAmount());
                    transaction.setUpdateDttm(cancelledDttm);
                    transaction.setUpdateUser(userCode);
                    transaction.setUpdateSystem(EPIS_SYSTEM_ABVR);
                    transaction.setBalanceDue(transaction.getAmount());
                    transaction.setReceived(transaction.getReceived().subtract(invoice.getReceived()));
                    trsAccountTBOSSRepo.save(transaction);
                }
                accountReceivable.setUpdateUser(userCode);
                accountReceivable.setUpdateSystem(EPIS_SYSTEM_ABVR);
                accountReceivable.setUpdateDttm(cancelledDttm);
                accountReceivable.setBalanceDue((balanceDue.compareTo(BigDecimal.ZERO) >= 0)
                        ? accountReceivable.getBalanceDue().add(balanceDue) : accountReceivable.getBalanceDue());
                accountReceivable.setPaidAmount(accountReceivable.getPaidAmount().subtract(balanceDue));
                accountTBOSSRepo.save(accountReceivable);
            }
            receipt.setCancelledDttm(cancelledDttm);
            receipt.setAttributes(receipt.getAttributes().replaceAll("Z", "") + "R");
            receiptRepo.save(receipt);
        }
    }

    @Transactional
    public void cancelPayment(Iterable<Receipt> receipts, String userAuthen) {
        for (Receipt receipt : receipts) {
            Date cancelDate = new Date();
            receipt.setAttributes(receipt.getAttributes().replaceFirst("Z", "") + "R");
            receipt.setCancelledDttm(cancelDate);
            receipt.setCancelledBy(userAuthen);
            receiptRepo.save(receipt);
        }
    }

    @Transactional
    public void markToCancelPayment(Iterable<Receipt> receipts) {
        for (Receipt receipt : receipts) {
            receipt.setAttributes(receipt.getAttributes() + "Z");
            receiptRepo.save(receipt);
        }
    }

    /*
	 * @Async public void callMNP02CreateOrderPayment(List<Receipt> receipts,
	 * String userCode, String branchCode) throws Exception { for (Receipt
	 * receipt : receipts) { boolean isPaymentSuccess = false; String
	 * trackingDetails = null;
	 * 
	 * CreateOderPaymentRequest request = new CreateOrderPaymentRequest();
	 * request.setRqHeader(_mnp02CreateOrderPaymentService.buildRqHeader(
	 * userCode, branchCode)); ReceiptOrderInfo receiptOrderInfo = new
	 * ReceiptOrderInfo(); receiptOrderInfo.setOrderNo(receipt.getAccountNo());
	 * receiptOrderInfo.setCustomerName(receipt.getAccountName());
	 * receiptOrderInfo.setCustomerType(receipt.getAccountType());
	 * receiptOrderInfo.setCustomerTaxId(receipt.getTaxNo());
	 * receiptOrderInfo.setCustomerBranch(receipt.getAccountBranch());
	 * receiptOrderInfo.setAddrLine1(receipt.getAddrLine1());
	 * receiptOrderInfo.setAddrLine2(receipt.getAddrLine2());
	 * request.setReceiptOrderInfo(receiptOrderInfo); for(Service service :
	 * receipt.getServices()) { for(Transaction transaction :
	 * service.getTransactions()) { MobileNumberPayment payment = new
	 * MobileNumberPayment(); payment.setMDN(service.getMdn());
	 * payment.setICCID(service.getIccid());
	 * payment.setPayMethod(transaction.getPaymentType());
	 * payment.setTransAmt(transaction.getAmount().doubleValue());
	 * payment.setTransId(transaction.getTransactionId());
	 * request.getMDNPayment().add(payment); } }
	 * 
	 * CreateOrderPaymentResponse response = null; try { response =
	 * _mnp02CreateOrderPaymentService.callInterface(request); } catch
	 * (javax.xml.ws.WebServiceException ex) { if (ex.getCause() instanceof
	 * java.net.SocketTimeoutException) { trackingDetails =
	 * "java.net.SocketTimeoutException: Read timed out"; } else {
	 * trackingDetails = ex.toString(); } } finally {
	 * if(_mnp02CreateOrderPaymentService.isCalledSuccesful("00", response)) {
	 * for(MobileNumberTransaction mobileTx : response.getMDNPaymentResult()) {
	 * for(Service service : receipt.getServices()) { for(Transaction
	 * transaction : service.getTransactions()) { // This portion of code MUST
	 * be revisit when real service is implemented!!! //
	 * if(mobileTx.getTransId().equals(transaction.getTransactionId())) {
	 * transaction.setTrackingId(mobileTx.getTrackingId());
	 * transaction.setTrackingDetails("Success");
	 * transaction.setTrackingRetry(1); transactionRepo.save(transaction); // }
	 * } } } isPaymentSuccess = true; } else { for(Service service :
	 * receipt.getServices()) { for(Transaction transaction :
	 * service.getTransactions()) { transaction.setTrackingId(null);
	 * transaction.setTrackingDetails(trackingDetails);
	 * transaction.setTrackingRetry(1); transactionRepo.save(transaction); } } }
	 * }
	 * 
	 * if (isPaymentSuccess) {
	 * receipt.setAttributes(receipt.getAttributes().replaceAll("C", "") + "C");
	 * receiptRepo.save(receipt); } } }
	 */
    @Async
    public void callMNP03CreateOrderPayment(List<Receipt> receipts, String userCode, String branchCode)
            throws Exception {
        for (Receipt receipt : receipts) {
            boolean isPaymentSuccess = false;
            String trackingDetails = null;

            CreateReceiptRequest request = new CreateReceiptRequest();
            request.setTransactionLog(_mnp03CreateOrderPaymentService.buildTransactionLogCBO());
            // receiptOrderInfo = new CreateReceiptRequest();
            request.setOrderId(receipt.getAccountNo());
            request.setAccountName(receipt.getAccountName());
            request.setAccountCategory(receipt.getAccountType());
            // request.setAmountTex(receipt.getTaxNo());
            // request.setCustomerBranch(receipt.getAccountBranch());
            request.setAccountAddress(receipt.getAddrLine1());
            request.setReceiptNo(receipt.getNo());
            // by NSD 01-03-2017
            request.setReceiptId(receipt.getId().toString());
            request.setPaymentId(receipt.getPayment().getId().toString());
            request.setReceiptType(MNP_SERVICE);
            request.setPaymentType(receipt.getPaymentCase());// maybe have to
            // send
            // paymentTypeCode
            request.setPaymentShop("");
            request.setOrderId(receipt.getAccountNo());
            request.setAccountName(receipt.getAccountName());
            request.setAccountCategory("");
            request.setAccountAddress("");
            request.setVatRate(receipt.getVatRate().doubleValue());
            // request.setAmountTex();
            // request.setVatAmount();
            String repeatOrderId = "";
            for (Service sv : receipt.getServices()) {
                repeatOrderId = sv.getRefOrderId();
            }
            request.setRepeatOrderID(repeatOrderId);
            if (repeatOrderId != null) {
                request.setRepeatOrder(!repeatOrderId.equalsIgnoreCase("")?1:0);
            }

            // request.setAddrLine2(receipt.getAddrLine2());
			/*
			 * request.setReceiptOrderInfo(receiptOrderInfo); for(Service
			 * service : receipt.getServices()) { for(Transaction transaction :
			 * service.getTransactions()) { MobileNumberPayment payment = new
			 * MobileNumberPayment(); payment.setMDN(service.getMdn());
			 * payment.setICCID(service.getIccid());
			 * payment.setPayMethod(transaction.getPaymentType());
			 * payment.setTransAmt(transaction.getAmount().doubleValue());
			 * payment.setTransId(transaction.getTransactionId());
			 * request.getMDNPayment().add(payment); } }
			 */
            CreateReceiptResponse response = null;
            try {
                response = _mnp03CreateOrderPaymentService.callInterface(request);
            } catch (javax.xml.ws.WebServiceException ex) {
                if (ex.getCause() instanceof java.net.SocketTimeoutException) {
                    trackingDetails = "java.net.SocketTimeoutException: Read timed out";
                } else {
                    trackingDetails = ex.toString();
                }
            } finally {
                if (_mnp03CreateOrderPaymentService.isCalledSuccesful("00", response)) {
					/*
					 * for(MobileNumberTransaction mobileTx :
					 * response.getMDNPaymentResult()) { for(Service service :
					 * receipt.getServices()) { for(Transaction transaction :
					 * service.getTransactions()) { // This portion of code MUST
					 * be revisit when real service is implemented!!! //
					 * if(mobileTx.getTransId().equals(transaction.
					 * getTransactionId())) {
					 * transaction.setTrackingId(mobileTx.getTrackingId());
					 * transaction.setTrackingDetails("Success");
					 * transaction.setTrackingRetry(1);
					 * transactionRepo.save(transaction); // } } } }
					 */
                    isPaymentSuccess = true;
                } else {
					/*
					 * for(Service service : receipt.getServices()) {
					 * for(Transaction transaction : service.getTransactions())
					 * { transaction.setTrackingId(null);
					 * transaction.setTrackingDetails(trackingDetails);
					 * transaction.setTrackingRetry(1);
					 * transactionRepo.save(transaction); } }
					 */
                }

            }

            if (isPaymentSuccess) {
                receipt.setAttributes(receipt.getAttributes().replaceAll("C", "") + "C");
                saveLogCorReceipt(receipt, "C");
                receiptRepo.save(receipt);
            }
        }
    }

    @Async
    public List<Transaction> callF4CreatePayment(List<Receipt> receipts) throws Exception {
    	List<Transaction> listTransactionResult = new ArrayList<>();
        for (Receipt receipt : receipts) {
            boolean isWriteOffSuccess = false, isBillingSuccess = false;
            boolean hasWriteOffInvoice = false, hasBillingInvoice = false;
            String trackingId = null, trackingIdServ = null, trackingCode = null, trackingDetails = null;
            BigDecimal ONE_HUNDRED = new BigDecimal("100");
            // Create WriteOff Transactions
            for (Invoice invoice : receipt.getInvoices()) {
                if (invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF)) {
                    hasWriteOffInvoice = true;
                    for (Service service : invoice.getServices()) {
                        for (Transaction transaction : service.getTransactions()) {
                            CreateWriteOffRequest request = new CreateWriteOffRequest();
                            request.setTransactionLog(_f10CreateWriteOffService.buildTransactionLogCBO());
                            request.setInvoiceNo(invoice.getNo());
                            request.setBillingAccountNo(invoice.getCustomer().getNo());
                            request.setAmount(transaction.getAmount().multiply(ONE_HUNDRED).doubleValue());
                            request.setPayDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_DASH, ENGLISH)
                                    .format(receipt.getUpdateDttm()));
                            request.setTranType("Payment");
                            // request.setTranId("1");
                            request.setTranId(request.getTransactionLog().getTranID());

                            trackingId = null;
                            trackingIdServ = null;
                            trackingCode = null;
                            trackingDetails = null;
                            try {
                                CreateWriteOffResponse createWriteOffResult = _f10CreateWriteOffService
                                        .callInterface(request);
                                if (_f10CreateWriteOffService.isCalledSuccesful("0", createWriteOffResult)) {
                                    if (createWriteOffResult.getTrackingId() != null & transaction != null) {
                                        trackingId = createWriteOffResult.getTrackingId();
                                    }
                                    if (createWriteOffResult.getTransactionLog() != null) {
                                        trackingCode = createWriteOffResult.getTransactionLog()
                                                .getDestinationReturnCode();
                                        trackingDetails = createWriteOffResult.getTransactionLog()
                                                .getDestinationReturnDetails();
                                    }
                                }
                            } catch (javax.xml.ws.WebServiceException ex) {
                                if (ex.getCause() instanceof java.net.SocketTimeoutException) {
                                    trackingDetails = "java.net.SocketTimeoutException: Read timed out";
                                } else {
                                    trackingDetails = ex.toString();
                                }
                            } finally {
                                transaction.setTrackingId(trackingId);
                                transaction.setTrackingCode(trackingCode);
                                transaction.setTrackingDetails(trackingDetails);
                                transaction.setTrackingRetry(1);
                                transactionRepo.save(transaction);
                                listTransactionResult.add(transaction);
                                if (isNotBlank(trackingId))
                                    isWriteOffSuccess = true;
                            }
                        }
                    }
                }
            }

            // Create Billing Transaction
			for (Invoice invoice : receipt.getInvoices()) {
				if (invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF))
					continue; // Not allowing WriteOff to call F4
				boolean isAdvancedPayment = invoice.getNo().startsWith("Advance");
				boolean isOtherPayment = StringUtils.equals(invoice.getStatus(), INVOICE_OTHER_PAYMENT);
				if (!isOtherPayment) {// by NSD 03-04-2017
					hasBillingInvoice = true;
					if (invoice.getServices().size() > 0) {
						for (Service service : invoice.getServices()) {
							if(service.getTransactions().size() > 0 ) {
							for (Transaction transaction : service.getTransactions()) {
								
								/*
								 * short currencyCode=12; if(invoice.getCurrencyCode()!=null){
								 * if(invoice.getCurrencyCode().toUpperCase().equals ("USD") ||
								 * invoice.getCurrencyCode().toUpperCase().equals( "US") ||
								 * invoice.getCurrencyCode().toUpperCase().equals( "EN_US")){ currencyCode=1; }
								 * }
								 */
								// short currencyCode = (short)
								// ("USD".equals(invoice.getCurrencyCode()) ||
								// "US".equals(invoice.getCurrencyCode()) ? 1 : 12);
								short currencyCode = Short.valueOf(invoice.getCurrencyCode());
								SettlePayment settlePayment = new SettlePayment();
								settlePayment.setAccountNoFromKenan(invoice.getKenan());
								settlePayment.setBillingAccountNo(invoice.getCustomer().getNo());
								settlePayment.setAnnotation(new Date());
								settlePayment.setCurrencyCode(currencyCode);

								// settlePayment.setReceivedAmount(transaction.getAmount());
								settlePayment.setReceivedAmount(
										transaction.getAmount().add(transaction.getPayment().getAfterSaleDiscount()));
								settlePayment.setReceivedAmountCurrency(currencyCode);
								settlePayment.setPaymentChannel("");
								settlePayment.setShopCode("");
								settlePayment.setBillRefNo(isAdvancedPayment ? "0" : invoice.getNo());
								settlePayment.setOrigBillRefResets(isAdvancedPayment ? "0" : "1");
								settlePayment.setPaymentMethod("1");
								// settlePayment.setTransAmount(transaction.getAmount().add(transaction.getPayment().getAfterSaleDiscount()));
								settlePayment.setTransAmount(transaction.getAmount());// by
								// NSD
								// 04-05-2017
								/*
								 * if(StringUtils.indexOfAny(transaction. getPaymentType(),
								 * (String[])MINUS_LIST.toArray())>-1){
								 * settlePayment.setTransAmount(transaction. getAmount()); }else{
								 * settlePayment.setTransAmount(transaction.
								 * getAmount().add(invoice.getAfterSaleDiscount()!=
								 * null?invoice.getAfterSaleDiscount():BigDecimal.
								 * ZERO).add(invoice.getAfterSaleDiscVat()!=null?
								 * invoice.getAfterSaleDiscVat():BigDecimal.ZERO)); }
								 */
								// settlePayment.setTransAmount(transaction.getAmount());//(service.getAmount());//by
								// NSD 10-04-2017//NSD 03-05-2017
								settlePayment.setTransType(isAdvancedPayment ? 2 : 1);
								settlePayment.setTransaction(transaction);

								// <!-- Calling: F4_CreatePaymentRequest. -->
								trackingId = null;
								trackingIdServ = null;
								trackingCode = null;
								trackingDetails = null;
								try {
									CreatePaymentRequest request = settlePayment.transformToCreatePaymentRequest();
									request.setTransactionLog(_f04CreatePaymentService.buildTransactionLogCBO());
									request.getTransactionLog().setTranID(transaction.getTransactionId());
									CreatePaymentResponse createPaymentResult = _f04CreatePaymentService
											.callInterface(request);
									if (createPaymentResult != null & createPaymentResult.getPayment() != null
											& transaction != null) {
										if (createPaymentResult.getTransactionLog() != null) {
											trackingCode = createPaymentResult.getTransactionLog()
													.getDestinationReturnCode();
											trackingDetails = createPaymentResult.getTransactionLog()
													.getDestinationReturnDetails();
										}
										if (createPaymentResult.getPayment().getKey() != null) {
											trackingId = createPaymentResult.getPayment().getKey().getTrackingId()
													.toString();
											trackingIdServ = Short.toString(
													createPaymentResult.getPayment().getKey().getTrackingIdServ());
										}
									}
								} catch (javax.xml.ws.WebServiceException ex) {
									if (ex.getCause() instanceof java.net.SocketTimeoutException) {
										trackingDetails = "java.net.SocketTimeoutException: Read timed out";
									} else {
										ex.printStackTrace();
										trackingDetails = ex.toString();
									}
								} finally {
									transaction.setTrackingId(trackingId);
									transaction.setTrackingIdServ(trackingIdServ);
									transaction.setTrackingCode(trackingCode);
									transaction.setTrackingDetails(trackingDetails);
									transaction.setTrackingRetry(1);
									transactionRepo.save(transaction);
									listTransactionResult.add(transaction);
									if (isNotBlank(trackingId))
										isBillingSuccess = true;
								}
							}
							} else {
								Transaction transaction = new Transaction(); 
			                    transaction = transactionRepo.save(new Transaction());
			                    transaction.setUpdateDttm(new Date());
			                    transaction.setUpdateUser(invoice.getUpdateUser());
			                   // not have a service from manual 
//			                    transaction.setServiceId(service.getId());
			                    transaction.setTransactionId(AppUtil.generateTransactionID(15));
			                    transaction.setTrackingDetails("EPIS is waiting for ESP response message.");
			                    transaction.setTrackingRetry(0);
			                    transaction.setPaymentDate(receipt.getPayment().getDate());
			                    transaction.setPaymentType(receipt.getPayment().getType());
			                    transaction.setAmount(receipt.getCharge());
			                    transaction.setStatus(null);
			                    transaction.setPayment(receipt.getPayment());
			                    short currencyCode = Short.valueOf(invoice.getCurrencyCode());
								SettlePayment settlePayment = new SettlePayment();
								settlePayment.setAccountNoFromKenan(invoice.getKenan());
								settlePayment.setBillingAccountNo(invoice.getCustomer().getNo());
								settlePayment.setAnnotation(new Date());
								settlePayment.setCurrencyCode(currencyCode);

								// settlePayment.setReceivedAmount(transaction.getAmount());
								settlePayment.setReceivedAmount(
										transaction.getAmount().add(transaction.getPayment().getAfterSaleDiscount()));
								settlePayment.setReceivedAmountCurrency(currencyCode);
								settlePayment.setPaymentChannel("");
								settlePayment.setShopCode("");
								settlePayment.setBillRefNo(isAdvancedPayment ? "0" : invoice.getNo());
								settlePayment.setOrigBillRefResets(isAdvancedPayment ? "0" : "1");
								settlePayment.setPaymentMethod("1");
								// settlePayment.setTransAmount(transaction.getAmount().add(transaction.getPayment().getAfterSaleDiscount()));
								settlePayment.setTransAmount(transaction.getAmount());// by
								// NSD
								// 04-05-2017
								/*
								 * if(StringUtils.indexOfAny(transaction. getPaymentType(),
								 * (String[])MINUS_LIST.toArray())>-1){
								 * settlePayment.setTransAmount(transaction. getAmount()); }else{
								 * settlePayment.setTransAmount(transaction.
								 * getAmount().add(invoice.getAfterSaleDiscount()!=
								 * null?invoice.getAfterSaleDiscount():BigDecimal.
								 * ZERO).add(invoice.getAfterSaleDiscVat()!=null?
								 * invoice.getAfterSaleDiscVat():BigDecimal.ZERO)); }
								 */
								// settlePayment.setTransAmount(transaction.getAmount());//(service.getAmount());//by
								// NSD 10-04-2017//NSD 03-05-2017
								settlePayment.setTransType(isAdvancedPayment ? 2 : 1);
								settlePayment.setTransaction(transaction);
			                    trackingId = null;
								trackingIdServ = null;
								trackingCode = null;
								trackingDetails = null;
								try {
									CreatePaymentRequest request = settlePayment.transformToCreatePaymentRequest();
									request.setTransactionLog(_f04CreatePaymentService.buildTransactionLogCBO());
									request.getTransactionLog().setTranID(transaction.getTransactionId());
									CreatePaymentResponse createPaymentResult = _f04CreatePaymentService
											.callInterface(request);
									if (createPaymentResult != null & createPaymentResult.getPayment() != null
											& transaction != null) {
										if (createPaymentResult.getTransactionLog() != null) {
											trackingCode = createPaymentResult.getTransactionLog()
													.getDestinationReturnCode();
											trackingDetails = createPaymentResult.getTransactionLog()
													.getDestinationReturnDetails();
										}
										if (createPaymentResult.getPayment().getKey() != null) {
											trackingId = createPaymentResult.getPayment().getKey().getTrackingId()
													.toString();
											trackingIdServ = Short.toString(
													createPaymentResult.getPayment().getKey().getTrackingIdServ());
										}
									}
								} catch (javax.xml.ws.WebServiceException ex) {
									if (ex.getCause() instanceof java.net.SocketTimeoutException) {
										trackingDetails = "java.net.SocketTimeoutException: Read timed out";
									} else {
										ex.printStackTrace();
										trackingDetails = ex.toString();
									}
								} finally {
									transaction.setTrackingId(trackingId);
									transaction.setTrackingIdServ(trackingIdServ);
									transaction.setTrackingCode(trackingCode);
									transaction.setTrackingDetails(trackingDetails);
									transaction.setTrackingRetry(1);
									transactionRepo.save(transaction);
									listTransactionResult.add(transaction);
									if (isNotBlank(trackingId))
										isBillingSuccess = true;
								}
							}
						}
                    }else {
                    	Transaction transaction = new Transaction(); 
	                    transaction = transactionRepo.save(new Transaction());
	                    transaction.setUpdateDttm(new Date());
	                    transaction.setUpdateUser(invoice.getUpdateUser());
	                   // not have a service from manual 
//	                    transaction.setServiceId(service.getId());
	                    transaction.setTransactionId(AppUtil.generateTransactionID(15));
	                    transaction.setTrackingDetails("EPIS is waiting for ESP response message.");
	                    transaction.setTrackingRetry(0);
	                    transaction.setPaymentDate(receipt.getPayment().getDate());
	                    transaction.setPaymentType(receipt.getPayment().getType());
	                    transaction.setAmount(receipt.getCharge());
	                    transaction.setStatus(null);
	                    transaction.setPayment(receipt.getPayment());
	                    short currencyCode = Short.valueOf(invoice.getCurrencyCode());
						SettlePayment settlePayment = new SettlePayment();
						settlePayment.setAccountNoFromKenan(invoice.getKenan());
						settlePayment.setBillingAccountNo(invoice.getCustomer().getNo());
						settlePayment.setAnnotation(new Date());
						settlePayment.setCurrencyCode(currencyCode);

						// settlePayment.setReceivedAmount(transaction.getAmount());
						settlePayment.setReceivedAmount(
								transaction.getAmount().add(transaction.getPayment().getAfterSaleDiscount()));
						settlePayment.setReceivedAmountCurrency(currencyCode);
						settlePayment.setPaymentChannel("");
						settlePayment.setShopCode("");
						settlePayment.setBillRefNo(isAdvancedPayment ? "0" : invoice.getNo());
						settlePayment.setOrigBillRefResets(isAdvancedPayment ? "0" : "1");
						settlePayment.setPaymentMethod("1");
						// settlePayment.setTransAmount(transaction.getAmount().add(transaction.getPayment().getAfterSaleDiscount()));
						settlePayment.setTransAmount(transaction.getAmount());// by
						// NSD
						// 04-05-2017
						/*
						 * if(StringUtils.indexOfAny(transaction. getPaymentType(),
						 * (String[])MINUS_LIST.toArray())>-1){
						 * settlePayment.setTransAmount(transaction. getAmount()); }else{
						 * settlePayment.setTransAmount(transaction.
						 * getAmount().add(invoice.getAfterSaleDiscount()!=
						 * null?invoice.getAfterSaleDiscount():BigDecimal.
						 * ZERO).add(invoice.getAfterSaleDiscVat()!=null?
						 * invoice.getAfterSaleDiscVat():BigDecimal.ZERO)); }
						 */
						// settlePayment.setTransAmount(transaction.getAmount());//(service.getAmount());//by
						// NSD 10-04-2017//NSD 03-05-2017
						settlePayment.setTransType(isAdvancedPayment ? 2 : 1);
						settlePayment.setTransaction(transaction);
	                    trackingId = null;
						trackingIdServ = null;
						trackingCode = null;
						trackingDetails = null;
						try {
							CreatePaymentRequest request = settlePayment.transformToCreatePaymentRequest();
							request.setTransactionLog(_f04CreatePaymentService.buildTransactionLogCBO());
							request.getTransactionLog().setTranID(transaction.getTransactionId());
							CreatePaymentResponse createPaymentResult = _f04CreatePaymentService
									.callInterface(request);
							if (createPaymentResult != null & createPaymentResult.getPayment() != null
									& transaction != null) {
								if (createPaymentResult.getTransactionLog() != null) {
									trackingCode = createPaymentResult.getTransactionLog()
											.getDestinationReturnCode();
									trackingDetails = createPaymentResult.getTransactionLog()
											.getDestinationReturnDetails();
								}
								if (createPaymentResult.getPayment().getKey() != null) {
									trackingId = createPaymentResult.getPayment().getKey().getTrackingId()
											.toString();
									trackingIdServ = Short.toString(
											createPaymentResult.getPayment().getKey().getTrackingIdServ());
								}
							}
						} catch (javax.xml.ws.WebServiceException ex) {
							if (ex.getCause() instanceof java.net.SocketTimeoutException) {
								trackingDetails = "java.net.SocketTimeoutException: Read timed out";
							} else {
								ex.printStackTrace();
								trackingDetails = ex.toString();
							}
						} finally {
							transaction.setTrackingId(trackingId);
							transaction.setTrackingIdServ(trackingIdServ);
							transaction.setTrackingCode(trackingCode);
							transaction.setTrackingDetails(trackingDetails);
							transaction.setTrackingRetry(1);
							transactionRepo.save(transaction);
							listTransactionResult.add(transaction);
							if (isNotBlank(trackingId))
								isBillingSuccess = true;
						}
                    }
                } else {// by NSD 04-04
                    for (Service service : invoice.getServices()) {
                        for (Transaction transaction : service.getTransactions()) {
                            transaction.setTrackingId("OTHAMT");
                            transaction.setTrackingDetails("Other profit of CAT");
                            transactionRepo.save(transaction);
                        }
                    }
                }
            }

            if (canMarkFlagReceiptToComplete(hasWriteOffInvoice, hasBillingInvoice, isWriteOffSuccess,
                    isBillingSuccess)) {
                receipt.setAttributes(receipt.getAttributes().replaceAll("C", "") + "C");
                saveLogCorReceipt(receipt, "C");
                receiptRepo.save(receipt);
            }
        }
        return listTransactionResult;
    }

    private boolean canMarkFlagReceiptToComplete(boolean hasWriteOffInvoice, boolean hasBillingInvoice,
                                                 boolean isWriteOffSuccess, boolean isBillingSuccess) {
        return (hasWriteOffInvoice & hasBillingInvoice) ? isWriteOffSuccess & isBillingSuccess
                : hasBillingInvoice ? isBillingSuccess : hasWriteOffInvoice ? isWriteOffSuccess : false;
    }

    public static class Paid {
        private BigDecimal amount;
        private String type;
        private String chequeNo;
        private String bankAccount;
        private MethodMoneyTransfer moneyTransfer = null;
        private Boolean isBackDate = Boolean.FALSE;

        public Paid(BigDecimal amount, String type) {
            this.amount = amount;
            this.type = type;
        }

        public Paid(BigDecimal amount, String type, String chequeNo, String bankAccount) {
            this.amount = amount;
            this.type = type;
            this.chequeNo = chequeNo;
            this.bankAccount = bankAccount;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChequeNo() {
            return chequeNo;
        }

        public void setChequeNo(String chequeNo) {
            this.chequeNo = chequeNo;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public MethodMoneyTransfer getMoneyTransfer() {
            return moneyTransfer;
        }

        public void setMoneyTransfer(MethodMoneyTransfer moneyTransfer) {
            this.moneyTransfer = moneyTransfer;
        }

        public Boolean isBackDate() {
            return isBackDate;
        }

        public void setIsBackDate(Boolean isBackDate) {
            this.isBackDate = isBackDate;
        }
    }

    public String findBillingGroupByCategory() throws Exception {
        List<Enum> billGroupEnum = enumRepo.findByCategory(AppConstants.CREDIT_LIMIT_CATEGORY);
        StringBuilder stringBuilder = new StringBuilder();
        for (Enum enums : billGroupEnum) {
            stringBuilder.append("'" + enums.getCode() + "'").append(",");
        }
        String billGroup = stringBuilder.toString();
        return (billGroup.length() == 0 ? " IS NULL "
                : " IN ( " + billGroup.substring(0, billGroup.length() - 1) + " )");
    }

    public InvoiceProductDTO findProductByInvoice(String billRefNo, String source) {

        InvoiceProductDTO dto = new InvoiceProductDTO();
        String tableName = "INV_SUMMARY_SAP_".concat(source);
        StringBuilder subSql = new StringBuilder()
                .append(" SELECT BI_REF billRefNo, PRODUCT_CODE productCode,PRODUCT_NAME productName ,  SUB_PRODUCT_CODE subProductCode ,SUB_PRODUCT_NAME subProductName ")
                .append(" , REVENUE_TYPE_CODE revTypeCode,REV_TYPE_NAME revTypeName , AMOUNT amount , contrno FROM " + " "
                        + tableName + " ")
                .append(" WHERE BI_REF=? ");
        List<InvoiceProduct> products = episJdbcTemplate.query(subSql.toString(), new Object[] { billRefNo },
                BeanPropertyRowMapper.newInstance(InvoiceProduct.class));
        if (!CollectionUtils.isEmpty(products)) {
            dto.setData(products);
        } else {
            dto.getWarningList().add(new AlertMessage("10", "Can not find Product , Sub Product , Revenue Type "));
        }
		/*
		 * if(source.equals(AppConstants.SOURCES.IBACSS.name())){ }else
		 * if(source.equals(AppConstants.SOURCES.TBOSS.name())){ }else
		 * if(source.equals(AppConstants.SOURCES.OTBOSS.name())){ }
		 */
        return dto;
    }

    // by NSD 28-02-2017
    @Transactional
    public List<Receipt> createPaymentPenaltyExtend(SettlePaymentDTO paymentDTO) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        Date date = new Date();
        Payment pm = new Payment();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        // Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO, received = BigDecimal.ZERO,
                vatRate = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();

        // Reorder: Cheque at first.

		/*
		 * for (SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
		 * paids.add(new Paid(ded.getAmount(), ded.getType())); deduct =
		 * deductRepo.save(new Deduct()); deduct.setUpdateDttm(date);
		 * deduct.setUpdateUser(userName);
		 * deduct.setNo(ded.getWithholdingDocNo());
		 * deduct.setType(ded.getType()); deduct.setAmount(ded.getAmount());
		 * deduct.setDate(date); deduct.setPaymentId(payment.getId());
		 * payment.getDeducts().add(deduct); }
		 */

        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            paids.add(new Paid(met.getAmount(), met.getType())); // Preparing:
            // To
            // substract
            // into
            // invoice.
            Method method = methodRepo.save(new Method());
            method.setUpdateDttm(date);
            method.setUpdateUser(userName);
            method.setCode(met.getCode());
            method.setName(met.getName());
            method.setAmount(met.getAmount());
            method.setPaymentId(payment.getId());
            payment.getMethods().add(method);
        }
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            customer = customerRepo.save(new Customer());
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1());
            customer.setInvoiceAddr(cust.getAddress2());

            customer.setBranch(cust.getCustBranch());

            Receipt rcpt = new Receipt();
            receipt = receiptRepo.save(new Receipt());
            receipt.setUpdateDttm(date);
            receipt.setUpdateUser(userName);
            receipt.setDocDttm(date);
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                service = serviceRepo.save(new Service());
                service.setUpdateDttm(date);
                service.setUpdateUser(userName);
                addTransactionsIntoService(paids, svc, payment, service, date, userName);
                receipt.getServices().add(service);
                service.setReceiptId(receipt.getId());
                service.setServiceCode(svc.getCode());

                service.setServiceName(svc.getServiceName());
                // service.setServiceName(svc.getServiceTypeName());//by NSD
                // 22-02-2016

                service.setServiceNo(svc.getNo());
                service.setProductCode(svc.getCode());

                service.setProductName(svc.getName());
                // service.setProductName(svc.getServiceTypeName());//by NSD
                // 22-02-2016

                service.setProductSubCode(null);
                service.setProductSubName(null);
                service.setIncomeType(svc.getType());
                service.setIncomeAmount(svc.getAmount());
                service.setIncomeUnit(svc.getUnit());
                service.setRefTransId(svc.getRefTransId());
                service.setAmount(svc.getAmount());

                // service.setDiscount(svc.getDiscount());
                service.setDiscount(new BigDecimal(0));

                // service.setCharge(svc.getCharge());
                service.setCharge(svc.getTotalCharge());// cos no vat
                service.setVat(svc.getVat());
                service.setTotalCharge(svc.getTotalCharge());
                service.setDeduction(svc.getDeduction());
                service.setVatRate(svc.getVatRate());
                service.setServiceTypeName(svc.getServiceTypeName());// by NSD
                // 16-02-2017
                service.setPromotionName(svc.getPromotion());
                if (svc.getServiceName() != null) {
                    payment.setType(
                            svc.getServiceName().equalsIgnoreCase("CHARGE001") ? "PENALTY_CHARGE" : "EXTEND_CHARGE");
                }
            }
            BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                    rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                    rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO, rcpWt = BigDecimal.ZERO,
                    rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO;
            // String rcpPro = "";
            for (SettlePaymentDTO.Service svc : cust.getServices()) {
                rcpAmount = rcpAmount.add(svc.getAmount());
                // rcpDiscount = rcpDiscount.add(svc.getDiscount());
                // rcpVat = rcpVat.add(svc.getVat());
                rcpTotalCharge = rcpTotalCharge.add(svc.getTotalCharge());
                // rcpDeduction = rcpDeduction.add(svc.getDeduction());
                rcpBalanceDue = rcpBalanceDue.add(BigDecimal.ZERO);
                rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(BigDecimal.ZERO);
                // vatRate = svc.getVatRate();
                // rcpPro = svc.getPromotion();//by NSD 22-02-2017
            }
            // rcpCharge = rcpAmount.subtract(rcpDiscount);
            rcpCharge = rcpAmount.subtract(new BigDecimal(0));// now no special
            // discount
            for (Method method : payment.getMethods()) {
                rcpReceived = rcpReceived.add(method.getAmount());
            }

            // rcpChange = rcpReceived.subtract(rcpTotalCharge);
            rcpChange = rcpTotalCharge.subtract(rcpTotalCharge);// by NSD
            // 03-02-2017
            receipt.setPayment(payment);
            receipt.setType(getReceiptType(customer));
            receipt.setFlgHeader(FLG_HEADER_2);
            receipt.setNo(getReceiptNo(posNo, RECEIPT_NO_FLAG_RECEIVE_ONLY, null, RECEIPT_HEADER_EPO));// RECEIPT_TYPE_FULL.equals(receipt.getType())
            // ?
            // RECEIPT_NO_FLAG_WITH_TAX_INVOICE
            // :
            // RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE
            receipt.setName(cust.getCustName());
            receipt.setAccountName(cust.getCustName());
            receipt.setAccountNo(cust.getCustNo());
            receipt.setAccountSubNo("");
            receipt.setAccountType(cust.getCustType());

            receipt.setAccountBranch(cust.getCustBranch());

            receipt.setTaxNo(cust.getTaxNo());
            receipt.setAddrLine1(customer.getReceiptAddr());
            receipt.setAddrLine2(customer.getInvoiceAddr());
            receipt.setPaymentCase(paymentDTO.getPaymentCase());
            receipt.setBranchCode(branchCode);
            receipt.setBranchArea(branchArea);
            receipt.setBranchName(branchName);
            receipt.setAmount(rcpAmount);
            receipt.setCustCategoryDesc(cust.getCustCategoryDesc());

            // receipt.setDiscount(rcpDiscount);
            receipt.setDiscount(new BigDecimal(0));
            receipt.setCharge(rcpCharge);

            // receipt.setVatRate(vatRate);
            // receipt.setVat(rcpVat);
            receipt.setTotalCharge(rcpTotalCharge);
            // receipt.setDeduction(rcpDeduction);
            // receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
            receipt.setBalanceDue(rcpBalanceDue);
            // receipt.setReceived(rcpReceived);
            receipt.setReceived(rcpTotalCharge);// by NSD 03-02-2017
            receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
            receipt.setAdvanced(null);
            // receipt.setPromotion(null);
            // receipt.setPromotion(rcpPro);//by NSD 22-02-2017
            receipt.setRemark(cust.getRemark());
            receipt.setReprint(0L);
            receipt.setAttributes("FC");

            //Set PosID And PosNo
            receipt.setPosid(EpContextHolder.getContext().getPosId());
            receipt.setPosno(EpContextHolder.getContext().getPosNo());

            receipts.add(receipt);
            saveLogCorReceipt(receipt, receipt.getAttributes());
            amount = amount.add(rcpAmount);
            // discount = discount.add(rcpDiscount);
            charge = charge.add(rcpCharge);
            // vat = vat.add(rcpVat);
            totalCharge = totalCharge.add(rcpTotalCharge);
            balanceDue = balanceDue.add(rcpBalanceDue);
            // afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
            // deduction = deduction.add(rcpWt);
            received = received.add(rcpReceived);
        }
        payment.setOfficerId(officerId);
        payment.setShopId(EpContextHolder.getContext().getBranchId());
        payment.setPosId(EpContextHolder.getContext().getPosId());
        payment.setCollectionUnit(customer.getCollectionUnit());
        payment.setDate(date);
        // payment.setType("PNTEXT_CHARGE");//PAYMENT_TYPE_SERVICE_CHARGE
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);

        // payment.setDiscount(discount);
        payment.setDiscount(new BigDecimal(0));

        payment.setCharge(charge);
        // payment.setVatRate(vatRate);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);

        // payment.setDeduction(deduction);
        payment.setDeduction(new BigDecimal(0));

        payment.setAfterSaleDiscount(afterSaleDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(received);
        payment.setChange(received.subtract(totalCharge));
        payment.setAdvanced(null);
        payment.setAttributes("T");

        return receipts;
    }

    public List<Receipt> findByCusNoAndPayType(String cusNo, String payType) {
        List<Receipt> rptRst = new ArrayList<Receipt>();

        rptRst = receiptRepo.findByAccountNoStartingWithAndPayment_TypeOrderByUpdateDttmDescDocDttmDesc(cusNo, payType);

        return rptRst;
    }

    public List<Receipt> findByAccountNoOrReceiptNoAndPaymentType(ReportPayment input) {
        List<Receipt> rptRst = new ArrayList<Receipt>();
        if (!input.getAccountNo().isEmpty()) {
            rptRst = receiptRepo.findByAccountNoStartingWithAndPayment_TypeOrderByNoDesc(input.getAccountNo(),
                    input.getPaymentType());
        } else {
            rptRst = receiptRepo.findByNoStartingWithAndPayment_TypeOrderByDocDttmDesc(input.getReceiptNo(),
                    input.getPaymentType());
        }
        return rptRst;
    }

    public List<Receipt> findByServiceNoOrReceiptNoAndPaymentType(ReportPayment input) {
        List<Receipt> rptRst = new ArrayList<Receipt>();
        Pageable p = null;
        List<String> serviceCode = new ArrayList<String>();
        if(input.getServiceCode().trim().length() > 0) {
            serviceCode.add(input.getServiceCode());
        }else {
            serviceCode.add("cat2callplus");
            serviceCode.add("cnema");
            serviceCode.add("my");
        }
        if (!"".equals(input.getServiceNo()) && !"".equals(input.getReceiptNo())) {
            rptRst = receiptRepo.findByServiceNoANDReciptNoAndServiceCode_Topup(input.getServiceNo(),input.getReceiptNo(), input.getPaymentType(),serviceCode,p);
        } else if (!"".equals(input.getServiceNo()) || !"".equals(input.getReceiptNo())){
            rptRst = receiptRepo.findByServiceNoORReciptNoAndServiceCode_Topup(input.getServiceNo(),input.getReceiptNo(), input.getPaymentType(),serviceCode,p);
//			rptRst = receiptRepo.findByNoStartingWithAndPayment_TypeOrderByDocDttmDesc(input.getReceiptNo(),input.getPaymentType());
        }else {
            rptRst = receiptRepo.findInvoicesByServiceCode_Topup(input.getPaymentType(),serviceCode,p);
        }
        return rptRst;
    }

    public Integer countTrsInvoiceNoVat(String baId, String invoice, int status) {
        Integer countRow = episJdbcTemplate.queryForObject(
                "SELECT count(*) FROM TRSINVOICENOVAT WHERE BA_ID = ? AND " + "INVOICE_NO =? AND STATUS = ? ",
                new Object[] { baId, invoice, status }, Integer.class);
        return countRow;
    }

    @Transactional
    public List<Receipt> cancelAndCreateNewReceipt(CancelPaymentDTO cancleDto, List<Receipt> rcpOld) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        Date date = new Date();
        Date receiptDttm = new Date();
        Long receiptId = 0L;
        Long paymentId = 0L;
        List<Receipt> listReceipts = new ArrayList<Receipt>();
        for (Receipt rec : rcpOld) {
            for (CancelPaymentDTO.Receipt updateRec : cancleDto.getReceipts()) {

                receiptId = null != rec.getId() ? rec.getId() : receiptId;
                paymentId = null != rec.getPayment().getId() ? rec.getPayment().getId() : paymentId;
                receiptDttm = rec.getDocDttm();

                Payment payment = paymentRepo.save(new Payment());
                payment.setUpdateDttm(date);
                payment.setUpdateUser(userName);
                Deduct deduct;
                Customer customer = null;
                Invoice invoice;
                InvoiceVatDetail invoiceVatDetail;
                Receipt receipt = null;
                BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                        vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                        afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO;
                List<Customer> customers = new ArrayList<Customer>();
                List<Customer> episCustomers = null;
                List<Receipt> receipts = new ArrayList<Receipt>();
                List<Paid> paids = new ArrayList<Paid>();

                // add egp & contract
                ReceiptEgpMappingEntity egpMap = null;
                List<ReceiptEgpMappingEntity> egpMaps = new ArrayList<ReceiptEgpMappingEntity>();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                //
                List<Deduct> deductList = deductRepo.findByPaymentId(paymentId);
                for (Deduct ded : deductList) {
                    paids.add(new Paid(ded.getAmount(), ded.getType()));
                    deduct = deductRepo.save(new Deduct());
                    deduct.setUpdateDttm(date);
                    deduct.setUpdateUser(userName);
                    deduct.setNo(ded.getNo());
                    deduct.setType(ded.getType());
                    deduct.setAmount(ded.getAmount());
                    deduct.setDate(ded.getDate());
                    deduct.setPaymentId(payment.getId());
                    deduct.setInvoiceNo(ded.getInvoiceNo());
                    payment.getDeducts().add(deduct);
                }

                Iterable<Method> methodList = methodRepo.findByPaymentId(paymentId);
                for (Method met : methodList) {
                    // if(met != null && met.getType() != null) {
                    // Paid paid = new Paid(met.getAmount(), met.getType());
                    // paids.add(paid); // Preparing: To substract into invoice.
                    Method method = methodRepo.save(new Method());
                    method.setUpdateDttm(date);
                    method.setUpdateUser(userName);
                    method.setCode(met.getCode());
                    method.setName(met.getName());
                    method.setChequeNo(met.getChequeNo());
                    method.setAccountNo(met.getAccountNo());
                    method.setAmount(met.getAmount());
                    method.setPaymentId(payment.getId());

                    // if (PAY_METHOD_BANKTRANSFER.equals(met.getType())) { //
                    // Money Transfer
                    Iterable<MethodMoneyTransfer> moneyTransferList = moneyTransferRepo.findByTransaction(paymentId);
                    if (null != moneyTransferList) {
                        for (MethodMoneyTransfer moneyTrans : moneyTransferList) {
                            MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                            moneyTransfer.setUpdateDttm(date);
                            moneyTransfer.setUpdateUser(userName);
                            moneyTransfer.setDate(moneyTrans.getDate());
                            // paid.setMoneyTransfer(moneyTransfer);
                            // paid.setIsBackDate(met.isBackDt());
                        }
                    }
                    // } else if (PAY_METHOD_CHEQUE.equals(met.getType())) {
                    // TODO: complete all the saving methods and pulling them to
                    // print correctly
                    Iterable<MethodCheque> chequeList = chequeRepository.findByPaymentId(paymentId);
                    if (null != chequeList) {
                        for (MethodCheque cheque : chequeList) {
                            MethodCheque chequePay = chequeRepository.save(new MethodCheque());
                            chequePay.setAmount(cheque.getAmount());
                            chequePay.setBankCode(cheque.getBankCode());
                            chequePay.setBankName(cheque.getBankName());
                            chequePay.setBankBrnh(cheque.getBankBrnh());
                            chequePay.setChequeDate(cheque.getChequeDate());
                            chequePay.setNo(cheque.getNo());
                            chequePay.setUpdateUser(userName);
                            chequePay.setUpdateDttm(date);
                            chequePay.setPaymentId(payment.getId());
                        }
                    }
                    // } else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
                    Iterable<MethodCreditCard> creditCardList = creditCardRepo.findByPaymentId(paymentId);
                    if (null != creditCardList) {
                        for (MethodCreditCard creditCard : creditCardList) {
                            MethodCreditCard creditCardPay = creditCardRepo.save(new MethodCreditCard());
                            creditCardPay.setPaymentId(payment.getId());
                            creditCardPay.setNo(creditCard.getNo());
                            creditCardPay.setAmount(creditCard.getAmount());
                            creditCardPay.setBankIssuer(creditCard.getBankIssuer());
                            creditCardPay.setType(creditCard.getType());
                            creditCardPay.setUpdateDttm(date);
                            creditCardPay.setUpdateUser(userName);
                        }
                    }
                    // } else if (PAY_METHOD_MONEYORDER.equals(met.getType())) {
                    // MethodMoneyOrder moneuOrder = new MethodMoneyOrder();
                    // } else if (PAY_METHOD_BILLEXCHANGE.equals(met.getType()))
                    // {
                    // MethodBillExchange billExchange = new
                    // MethodBillExchange();
                    // } else if (PAY_METHOD_COUPON.equals(met.getType())) {
                    //
                    // } else if (PAY_METHOD_OFFSET.equals(met.getType())) {
                    method.setOffsetDocumentNo(met.getOffsetDocumentNo());
                    method.setOffsetAccountCode(met.getOffsetAccountCode());
                    method.setOffsetAccountName(met.getOffsetAccountName());
                    payment.getMethods().add(method);
                    // }
                }

                receipt = receiptRepo.save(new Receipt());

                for (Invoice inv : rec.getInvoices()) {
                    invoice = invoiceRepo.save(new Invoice());
                    invoice.setUpdateDttm(date);
                    invoice.setUpdateUser(userName);
                    invoice.setReceiptId(receipt.getId());
                    for (Service serv : rec.getServices()) {
                        Service service = serviceRepo.save(new Service());
                        service.setUpdateDttm(date);
                        service.setReceiptId(receipt.getId());
                        service.setInvoiceId(invoice.getId());
                        service.setAccountNo(serv.getAccountNo());
                        service.setProductCode(serv.getProductCode());
                        service.setProductName(serv.getProductName());
                        service.setProductSubCode(serv.getProductSubCode());
                        service.setProductSubName(serv.getProductSubName());
                        service.setIncomeType(serv.getIncomeType());
                        service.setAmount(serv.getAmount());

                        Transaction transaction;
                        Iterable<Transaction> transactionList = transactionRepo.findByPaymentAndServiceId(paymentId,
                                serv.getId());
                        for (Transaction transac : transactionList) {
                            transaction = transactionRepo.save(new Transaction());
                            transaction.setUpdateDttm(date);
                            transaction.setUpdateUser(userName);
                            transaction.setServiceId(service.getId());
                            transaction.setTransactionId(transac.getTransactionId());// (AppUtil.generateTransactionID(15));
                            transaction.setTrackingDetails(transac.getTrackingDetails());
                            transaction.setTrackingRetry(transac.getTrackingRetry());
                            transaction.setPaymentDate(transac.getPaymentDate());
                            transaction.setPaymentType(transac.getPaymentType());
                            transaction.setAmount(transac.getAmount());
                            transaction.setChequeNo(transac.getChequeNo());
                            transaction.setAccountNo(transac.getAccountNo());
                            transaction.setStatus(transac.getStatus());
                            transaction.setPayment(payment);
                            transaction.setTrackingCode(transac.getTrackingCode());
                            transaction.setTrackingIdServ(transac.getTrackingIdServ());
                            transaction.setTrackingId(transac.getTrackingId());
                            service.getTransactions().add(transaction);
                        }
                        receipt.getInvoices().add(invoice);
                        receipt.getServices().add(service);
                        invoice.getServices().add(service);
                    }

                    episCustomers = null;
                    episCustomers = customerRepo.findByNo(rec.getCustomer().getNo());
                    for (Customer cust : episCustomers) {
                        if (CollectionUtils.isEmpty(episCustomers)) {
                            customer = customerRepo.save(new Customer());
                        } else {
                            customer = episCustomers.get(0);
                            customerRepo.save(customer);
                        }
                        customer.setUpdateDttm(date);
                        customer.setUpdateUser(userName);
                        customers.add(customer);
                        customer.setPayment(payment);
                        customer.setType(cust.getType());
                        customer.setNo(cust.getNo());
                        customer.setName(updateRec.getAccountName());
                        customer.setTax(cust.getTax());
                        customer.setBillGroup(cust.getBillGroup());
                        customer.setCollectionUnit(cust.getCollectionUnit());
                        customer.setOutstanding(cust.getOutstanding());
                        customer.setRemark(cust.getRemark());
                        customer.setReceiptAddr(updateRec.getAddrLine1());
                        customer.setInvoiceAddr(updateRec.getAddrLine1());
                        customer.setAcctCatLkp(cust.getAcctCatLkp());
                        customer.setCatCustomerSegment(cust.getCatCustomerSegment());
                        customer.setBranch(cust.getBranch());
                        invoice.setCustomer(customer);

                    }

                    if (!CollectionUtils.isEmpty(invoice.getInvoiceVatDetails())) {
                        for (InvoiceVatDetail invDtl : invoice.getInvoiceVatDetails()) {
                            invoiceVatDetail = invoiceVatRepository.save(new InvoiceVatDetail());
                            invoiceVatDetail.setUpdateDttm(date);
                            invoiceVatDetail.setUpdateUser(userName);
                            invoice.getInvoiceVatDetails().add(invoiceVatDetail);
                            invoiceVatDetail.setInvoiceId(invoice.getId());
                            invoiceVatDetail.setInvoiceNo(invDtl.getInvoiceNo());
                            invoiceVatDetail.setAccountNo(invDtl.getAccountNo());
                            invoiceVatDetail.setAmount(invDtl.getAmount());
                            invoiceVatDetail.setVat(invDtl.getVat());
                            invoiceVatDetail.setTaxTypeCode(invDtl.getTaxTypeCode());

                        }
                    }

                    invoice.setPayment(payment);
                    invoice.setNo(inv.getNo());
                    invoice.setKenan(inv.getKenan());
                    invoice.setCurrencyCode(inv.getCurrencyCode());
                    invoice.setIssueDt(inv.getIssueDt());
                    invoice.setDueDt(inv.getDueDt());
                    invoice.setBillCycle(inv.getBillCycle());
                    invoice.setAmount(inv.getAmount());
                    invoice.setDiscount(inv.getDiscount());
                    invoice.setCharge(inv.getCharge());
                    invoice.setVat(inv.getVat());
                    invoice.setVatRate(inv.getVatRate());
                    invoice.setTotalCharge(inv.getReceived());
                    invoice.setDeduction(inv.getDeduction());
                    invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
                    invoice.setBalanceDue(inv.getBalanceDue());
                    invoice.setReceived(inv.getReceived());
                    invoice.setAfterSaleDiscVat(inv.getAfterSaleDiscVat());
                    invoice.setChange(inv.getChange());
                    invoice.setAdvanced(inv.getAdvanced());
                    invoice.setDebtAmount(inv.getTotalCharge());
                    invoice.setStatus(inv.getStatus());
                    invoice.setDiscountType(inv.getDiscountType());
                    invoice.setDiscApprUser(inv.getDiscApprUser());
                    invoice.setSubNo(inv.getSubNo());
                    invoice.setAttributes(inv.getAttributes());
                    receipt.getInvoices().add(invoice);
                    receipt.setPaymentCase(rec.getPaymentCase());
                }

                BigDecimal receivedAmount = BigDecimal.ZERO;
                receivedAmount.add(rec.getBalanceDue()).add(rec.getReceived());
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);

                receipt.setCustomer(customer);
                receipt.setPayment(payment);
                receipt.setType(rec.getType());
                receipt.setNo(
                        getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(rec.getType()) ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE
                                : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, date, RECEIPT_HEADER_EPO));
                receipt.setName(updateRec.getAccountName());
                receipt.setAccountName(updateRec.getAccountName());
                receipt.setAccountNo(rec.getAccountNo());
                receipt.setAccountSubNo(rec.getAccountSubNo());
                receipt.setAccountType(rec.getAccountType());
                receipt.setTaxNo(rec.getTaxNo());
                receipt.setAccountBranch(rec.getAccountBranch());
                receipt.setAddrLine1(updateRec.getAddrLine1());
                receipt.setAddrLine2(updateRec.getAddrLine1());

                // receipt.setPaymentCase(paymentDTO.getPaymentCase());
                receipt.setBranchCode(rec.getBranchCode());
                receipt.setBranchArea(rec.getBranchArea());
                receipt.setBranchName(rec.getBranchName());
                receipt.setAmount(rec.getAmount());
                receipt.setDiscount(rec.getDiscount());
                receipt.setCharge(rec.getCharge());
                receipt.setVatRate(rec.getVatRate());
                receipt.setVat(rec.getVat());
                receipt.setTotalCharge(rec.getTotalCharge());
                receipt.setDeduction(rec.getDeduction());
                receipt.setAfterSaleDiscount(rec.getAfterSaleDiscount());
                receipt.setBalanceDue(receivedAmount);
                //	receipt.setBalanceDue(rec.getBalanceDue());
                receipt.setReceived(rec.getReceived());
                receipt.setAfterSaleDiscVat(rec.getAfterSaleDiscVat());
                receipt.setAdvanced(rec.getAdvanced());
                receipt.setPromotion(rec.getPromotion());
                receipt.setRefRctNo(rec.getNo());
                receipt.setRemark("ยกเลิกใบเก่า ออกใบใหม่ อ้างอิงใบเสร็จรับเงินเลขที่ : "
                        + rec.getNo());/* xxxxxxxxxxxxxxxx edit */
                receipt.setReprint(rec.getReprint());
                receipt.setAttributes(rec.getAttributes());

                receipt.setBillingGroup(rec.getBillingGroup());
                receipt.setBillingGroupFull(rec.getBillingGroupFull());
                receipt.setBillingServiceName(rec.getBillingServiceName());
                receipt.setLanguage(rec.getLanguage());
                receipt.setChange(rec.getChange());
                receipt.setAdvanced(rec.getAdvanced());
                receipt.setFlgHeader(rec.getFlgHeader());
                receipts.add(receipt);
                saveLogCorReceipt(receipt, receipt.getAttributes());
                // egp
                Iterable<ReceiptEgpMappingEntity> egpMapList = egpMapRepo.findByReceiptId(rec.getId());
                if (null != egpMapList) {
                    for (ReceiptEgpMappingEntity oldEgpMap : egpMapList) {
                        egpMap = new ReceiptEgpMappingEntity();
                        egpMap.setReceiptId(receipt.getId());
                        egpMap.setCreatedBy(userName);
                        egpMap.setCreatedDate(timestamp);
                        egpMap.setBaNo(oldEgpMap.getBaNo());
                        egpMap.setEgpNo(oldEgpMap.getEgpNo());
                        egpMap.setEgpContract(oldEgpMap.getEgpContract());
                        egpMap.setReceiptNo(receipt.getNo());
                        egpMaps.add(egpMap);
                        egpMapRepo.save(egpMap);
                    }
                }

                if (rec.getFlgHeader() != null && rec.getLanguage() != null) {
                    String receiptName = episService.getReceiptName(rec.getFlgHeader(), rec.getLanguage());
                    episService.setReceiptFormat(receiptId, receiptName);
                }

                payment.setOfficerId(officerId);
                payment.setShopId(EpContextHolder.getContext().getBranchId());
                payment.setPosId(EpContextHolder.getContext().getPosId());
                payment.setCollectionUnit(rec.getPayment().getCollectionUnit());
                payment.setDate(receiptDttm);
                payment.setType(rec.getPayment().getType());
                payment.setMethod(rec.getPayment().getMethod());
                payment.setAmount(rec.getPayment().getAmount());
                payment.setDiscount(rec.getPayment().getDiscount());
                payment.setCharge(rec.getPayment().getCharge());
                payment.setVatRate(rec.getPayment().getVatRate());
                payment.setVat(rec.getPayment().getVat());
                payment.setTotalCharge(rec.getPayment().getTotalCharge());
                payment.setDeduction(rec.getPayment().getDeduction());
                payment.setAfterSaleDiscount(rec.getPayment().getAfterSaleDiscount());
                payment.setBalanceDue(rec.getPayment().getBalanceDue());
                payment.setReceived(rec.getPayment().getReceived());
                payment.setChange(rec.getPayment().getChange());
                payment.setAdvanced(rec.getPayment().getAdvanced());
                payment.setAttributes(rec.getPayment().getAttributes());
                payment.setCurrencyCode(rec.getPayment().getCurrencyCode());
                payment.setCurrencyRate(rec.getPayment().getCurrencyRate());

                listReceipts.add(receipt);
            }
        }
        return listReceipts;
    }

    @Transactional
    public List<Receipt> cancelAndCreateNewReceiptOther(CancelPaymentDTO cancleDto, List<Receipt> rcpOld) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = null;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();
        Long receiptId = 0L;
        Long paymentId = 0L;
        List<Receipt> listReceipts = new ArrayList<Receipt>();
        for (Receipt rec : rcpOld) {
            for (CancelPaymentDTO.Receipt updateRec : cancleDto.getReceipts()) {
                receiptId = rec.getId();
                paymentId = rec.getPayment().getId();

                List<Deduct> deducts = deductRepo.findByPaymentId(paymentId);
                for (Deduct ded : deducts) {
                    paids.add(new Paid(ded.getAmount(), ded.getType()));
                    deduct = deductRepo.save(new Deduct());
                    Long deductId = deduct.getId();
                    BeanUtils.copyProperties(ded, deduct);
                    deduct.setId(deductId);
                    deduct.setUpdateDttm(date);
                    deduct.setUpdateUser(userName);
                    deduct.setPaymentId(payment.getId());
                    payment.getDeducts().add(deduct);
                }

                List<Method> methods = methodRepo.findByPaymentId(paymentId);
                for (Method met : methods) {
                    Method method = methodRepo.save(new Method());
                    Long methodId = method.getId();
                    BeanUtils.copyProperties(met, method);
                    method.setId(methodId);
                    method.setUpdateDttm(date);
                    method.setUpdateUser(userName);
                    method.setPaymentId(payment.getId());

                    List<MethodMoneyTransfer> methodMoneyTransfers = moneyTransferRepo.findByTransaction(paymentId);
                    for (MethodMoneyTransfer metMonTrans : methodMoneyTransfers) {
                        MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                        Long moneyTransferId = moneyTransfer.getId();
                        BeanUtils.copyProperties(metMonTrans, moneyTransfer);
                        moneyTransfer.setId(moneyTransferId);
                        moneyTransfer.setUpdateDttm(date);
                        moneyTransfer.setUpdateUser(userName);
                    }

                    List<MethodCheque> methodCheques = chequeRepository.findByPaymentId(paymentId);
                    for (MethodCheque MetCheque : methodCheques) {
                        MethodCheque chequePay = chequeRepository.save(new MethodCheque());
                        Long chequePayId = chequePay.getId();
                        BeanUtils.copyProperties(MetCheque, chequePay);
                        chequePay.setId(chequePayId);
                        chequePay.setNo(met.getChequeNo());
                        chequePay.setUpdateUser(userName);
                        chequePay.setUpdateDttm(date);
                    }

                    try {
                        StringBuilder sql = new StringBuilder(
                                "select *  from TRSCREDITREF  WHERE PAYMENTID = ' " + paymentId + "' ");
                        List<MethodCreditCard> creditCardList = episJdbcTemplate.query(sql.toString(),
                                BeanPropertyRowMapper.newInstance(MethodCreditCard.class));
                        // Iterable<MethodCreditCard> creditCardList =
                        // creditCardRepo.findByPaymentId(paymentId);
                        if (creditCardList.size() > 0) {
                            for (MethodCreditCard creditCard : creditCardList) {
                                MethodCreditCard creditCardPay = creditCardRepo.save(new MethodCreditCard());
                                creditCardPay.setPaymentId(payment.getId());
                                creditCardPay.setNo(creditCard.getNo());
                                creditCardPay.setAmount(creditCard.getAmount());
                                creditCardPay.setBankIssuer(creditCard.getBankIssuer());
                                creditCardPay.setType(creditCard.getType());
                                creditCardPay.setUpdateDttm(date);
                                creditCardPay.setUpdateUser(userName);
                            }
                        }
                    } catch (Exception e) {

                    }

                }

                try {
                    // List<Customer> customs =
                    // customerRepo.findPayment(paymentId);
                    if (rec.getCustomer() != null) {
                        Customer custom = rec.getCustomer();
                        customer = customerRepo.save(new Customer());
                        Long customId = customer.getId();
                        BeanUtils.copyProperties(custom, customer);
                        customer.setId(customId);
                        customer.setName(updateRec.getAccountName());
                        customer.setReceiptAddr(updateRec.getAddrLine1());
                        customer.setInvoiceAddr(updateRec.getAddrLine1());
                        customer.setUpdateDttm(date);
                        customer.setUpdateUser(userName);
                        customers.add(customer);
                        customer.setPayment(payment);
                    }
                } catch (Exception e) {

                }

                receipt = receiptRepo.save(new Receipt());
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);

                try {
                    // List<Service> services =
                    // serviceRepo.findByReceiptId(receiptId);
                    if (rec.getServices() != null) {
                        for (Service serv : rec.getServices()) {
                            service = serviceRepo.save(new Service());
                            receipt.getServices().add(service);
                            service.setReceiptId(receipt.getId());
                            service.setServiceCode(serv.getServiceCode());
                            service.setServiceName(serv.getServiceName());
                            service.setServiceNo(serv.getServiceNo());
                            service.setProductCode(serv.getProductCode());
                            service.setProductName(serv.getProductName());
                            service.setProductSubCode(serv.getProductSubCode());
                            service.setProductSubName(serv.getProductSubName());
                            service.setIncomeType(serv.getIncomeType());
                            service.setIncomeAmount(serv.getIncomeAmount());
                            service.setIncomeUnit(serv.getIncomeUnit());
                            service.setRefTransId(serv.getRefTransId());
                            service.setAmount(serv.getAmount());
                            service.setDiscount(serv.getDiscount());
                            service.setCharge(serv.getCharge());
                            service.setVat(serv.getVat());
                            service.setTotalCharge(serv.getTotalCharge());
                            service.setDeduction(serv.getDeduction());

                            Transaction transaction;
                            if (serv.getTransactions() != null) {
                                for (Transaction trans : serv.getTransactions()) {
                                    transaction = transactionRepo.save(new Transaction());
                                    transaction.setUpdateDttm(date);
                                    transaction.setUpdateUser(userName);
                                    transaction.setServiceId(service.getId());
                                    transaction.setTransactionId(trans.getTransactionId());// (AppUtil.generateTransactionID(15));
                                    transaction.setTrackingDetails(trans.getTrackingDetails());
                                    transaction.setTrackingRetry(trans.getTrackingRetry());
                                    transaction.setPaymentDate(trans.getPaymentDate());
                                    transaction.setPaymentType(trans.getPaymentType());
                                    transaction.setAmount(trans.getAmount());
                                    transaction.setChequeNo(trans.getChequeNo());
                                    transaction.setAccountNo(trans.getAccountNo());
                                    transaction.setStatus(trans.getStatus());
                                    transaction.setPayment(payment);
                                    transaction.setTrackingCode(trans.getTrackingCode());
                                    transaction.setTrackingIdServ(trans.getTrackingIdServ());
                                    transaction.setTrackingId(trans.getTrackingId());
                                    receipt.getServices().add(service);
                                    service.getTransactions().add(transaction);
                                }
                            }

                        }
                    }

                } catch (Exception e) {

                }

                receipt.setPayment(payment);
                receipt.setCustomer(customer);
                receipt.setType(rec.getType());
                receipt.setNo(
                        getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(rec.getType()) ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE
                                : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, date, RECEIPT_HEADER_EPO));
                receipt.setName(updateRec.getAccountName());
                receipt.setAccountName(updateRec.getAccountName());
                receipt.setAccountNo(rec.getAccountNo());
                receipt.setAccountSubNo(rec.getAccountSubNo());
                receipt.setAccountType(rec.getAccountType());
                receipt.setTaxNo(rec.getTaxNo());
                receipt.setAccountBranch(rec.getAccountBranch());
                receipt.setAddrLine1(updateRec.getAddrLine1());
                receipt.setAddrLine2(updateRec.getAddrLine1());
                receipt.setBranchCode(rec.getBranchCode());
                receipt.setBranchArea(rec.getBranchArea());
                receipt.setBranchName(rec.getBranchName());
                receipt.setAmount(rec.getAmount());
                receipt.setDiscount(rec.getDiscount());
                receipt.setCharge(rec.getCharge());
                receipt.setVatRate(rec.getVatRate());
                receipt.setVat(rec.getVat());
                receipt.setTotalCharge(rec.getTotalCharge());
                receipt.setDeduction(rec.getDeduction());
                receipt.setAfterSaleDiscount(rec.getAfterSaleDiscount());
                receipt.setBalanceDue(rec.getBalanceDue());
                receipt.setReceived(rec.getReceived());
                receipt.setAfterSaleDiscVat(rec.getAfterSaleDiscVat());
                receipt.setAdvanced(rec.getAdvanced());
                receipt.setPromotion(rec.getPromotion());
                receipt.setRefRctNo(rec.getNo());
                receipt.setRemark("ยกเลิกใบเก่า ออกใบใหม่ อ้างอิงใบเสร็จรับเงินเลขที่ : "
                        + rec.getNo());/* xxxxxxxxxxxxxxxx edit */
                receipt.setReprint(rec.getReprint());
                receipt.setAttributes(rec.getAttributes());
                receipt.setBillingGroup(rec.getBillingGroup());
                receipt.setBillingGroupFull(rec.getBillingGroupFull());
                receipt.setBillingServiceName(rec.getBillingServiceName());
                receipt.setLanguage(rec.getLanguage());
                receipt.setChange(rec.getChange());
                receipt.setAdvanced(rec.getAdvanced());
                receipt.setFlgHeader(rec.getFlgHeader());
                receipt.setPaymentCase(rec.getPaymentCase());
                receipt.setExcDiscount(rec.getExcDiscount());
                receipts.add(receipt);
                saveLogCorReceipt(receipt, receipt.getAttributes());
                payment.setOfficerId(officerId);
                payment.setShopId(EpContextHolder.getContext().getBranchId());
                payment.setPosId(EpContextHolder.getContext().getPosId());
                payment.setCollectionUnit(rec.getPayment().getCollectionUnit());
                payment.setDate(receiptDttm);
                payment.setType(rec.getPayment().getType());
                payment.setMethod(rec.getPayment().getMethod());
                payment.setAmount(rec.getPayment().getAmount());
                payment.setDiscount(rec.getPayment().getDiscount());
                payment.setCharge(rec.getPayment().getCharge());
                payment.setVatRate(rec.getPayment().getVatRate());
                payment.setVat(rec.getPayment().getVat());
                payment.setTotalCharge(rec.getPayment().getTotalCharge());
                payment.setDeduction(rec.getPayment().getDeduction());
                payment.setAfterSaleDiscount(rec.getPayment().getAfterSaleDiscount());
                payment.setBalanceDue(rec.getPayment().getBalanceDue());
                payment.setReceived(rec.getPayment().getReceived());
                payment.setChange(rec.getPayment().getChange());
                payment.setAdvanced(rec.getPayment().getAdvanced());
                payment.setAttributes(rec.getPayment().getAttributes());
                payment.setCurrencyCode(rec.getPayment().getCurrencyCode());
                payment.setCurrencyRate(rec.getPayment().getCurrencyRate());

                listReceipts.add(receipt);

            }
        }

        return listReceipts;
    }

    public PaymentHistoryDTO findPaymentHistoryReceipt(String no,String invNo,String payType, Pageable p) throws SOAPException, IOException, UnsupportedOperationException, ParserConfigurationException, SAXException {
        logger.info("findPaymentHistoryReceipt..Start");
        PaymentHistoryDTO dto = new PaymentHistoryDTO();
        List<Receipt> receipts = null;
        if(no != null && no.trim().length() > 0){
            Page<Receipt> receiptp = receiptRepo.findByNoAndPaymentTypesOrderByNoDesc(no,p);
            if(receiptp != null){
                receipts = receiptp.getContent();
            }
        }else if(invNo != null && invNo.trim().length() > 0){
            Page<Receipt> receiptp = receiptRepo.findByInvoicesNoAndPaymentTypesOrderByNoDesc(invNo,p);
            if(receiptp != null){
                receipts = receiptp.getContent();
            }
        }
        if(receipts != null && receipts.size() > 0){
            dto = getPaymentHistory(receipts);
        }
        logger.info("findPaymentHistoryReceipt..End");
        return dto;
    }

    private PaymentHistoryDTO getPaymentHistory(List<Receipt> receipts){
        logger.info("getPaymentHistory...Start");
        PaymentHistoryDTO dto = new PaymentHistoryDTO();
        for(Receipt receipt : receipts) {
            for (Invoice invoice : receipt.getInvoices()) {
                // Offline
                logger.info("[getUpdateDttm]="+receipt.getUpdateDttm());
                logger.info("[getDocDttm]="+receipt.getDocDttm());
                logger.info("[getUpdateDttm]="+AppUtil.formatter_EN.format(receipt.getUpdateDttm())+" "+AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
                logger.info("[getDocDttm]="+AppUtil.formatter_EN.format(receipt.getDocDttm())+" "+AppUtil.formatter_EN_TIME.format(receipt.getDocDttm()));

                PaymentHistory paymentHistory = new PaymentHistory();
                BigDecimal exchangeRate = new BigDecimal(1);//by NSD 27-04-2017
				/*
				 paymentHistory.setUpdatePrintDate(receipt.getUpdateDttm());
				paymentHistory.setReceiptPrintDate((receipt.getDocDttm() != null)? receipt.getDocDttm(): receipt.getUpdateDttm());
				*/
                // fixed Date time
                paymentHistory.setUpdatePrintDateStr(AppUtil.formatter_EN.format(receipt.getUpdateDttm())+" "+AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
                if(receipt.getDocDttm() != null){
                    paymentHistory.setReceiptPrintDateStr(AppUtil.formatter_EN.format(receipt.getDocDttm())+" "+AppUtil.formatter_EN_TIME.format(receipt.getDocDttm()));
                }else{
                    paymentHistory.setReceiptPrintDateStr(AppUtil.formatter_EN.format(receipt.getUpdateDttm())+" "+AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
                }
                if(receipt.getPayment()!=null && receipt.getPayment().getCurrencyRate()!=null){
                    exchangeRate = receipt.getPayment().getCurrencyRate();
                }
                paymentHistory.setUpdatePrintDate(receipt.getUpdateDttm());
                paymentHistory.setReceiptPrintDate((receipt.getDocDttm() != null)? receipt.getDocDttm(): receipt.getUpdateDttm());
                paymentHistory.setReceiptNo(receipt.getNo());
                paymentHistory.setBillRefNo(invoice.getNo()); // may use payment.getBillRefNo() or payment.getOrigBillRefNo().toString()
                paymentHistory.setShopPaymentName(receipt.getBranchName());
                paymentHistory.setPaymentReceiver(receipt.getUpdateUser());
                paymentHistory.setCycleDateFrom(null);
                paymentHistory.setCycleDateTo(null);
                paymentHistory.setBillCycle(invoice.getBillCycle());
                //paymentHistory.setBillAmount(invoice.getTotalCharge() != null ? invoice.getTotalCharge() : BigDecimal.ZERO);
                paymentHistory.setBillAmount(invoice.getDebtAmount() != null ? invoice.getDebtAmount() : BigDecimal.ZERO);
                paymentHistory.setAfterSaleDiscount(invoice.getAfterSaleDiscount() != null ? invoice.getAfterSaleDiscount() : BigDecimal.ZERO);
                // Fix by PM 20/04/2017
                String maskCC = AppUtil.maskCreditCardFromString(receipt.getPayment().getMethod(), "************####");
                String paymentCase = AppUtil.hideWTNumber(maskCC);
                // End Fix by PM 20/04/2017
                paymentHistory.setPaymentMethod(paymentCase);
                paymentHistory.setPaymentCategory(trimToEmpty(invoice.getAttributes()).indexOf("F") > -1 ? "ชำระเต็มจำนวน" : (trimToEmpty(invoice.getAttributes()).indexOf("A") > -1 ? "ชำระล่วงหน้า" : "ชำระบางส่วน"));
                BigDecimal afterSaleDiscVat = BigDecimal.ZERO;
                afterSaleDiscVat = invoice.getAfterSaleDiscVat()!=null ? invoice.getAfterSaleDiscVat() : BigDecimal.ZERO;

                paymentHistory.setTransAmount((invoice.getReceived().setScale(2).multiply(exchangeRate)).setScale(2, BigDecimal.ROUND_HALF_UP));
                if(!exchangeRate.equals(new BigDecimal(1)) && invoice.getVatRate()!=null){
                    BigDecimal oneHen = new BigDecimal(100);
                    //BigDecimal onVar = paymentHistory.getTransAmount().multiply(invoice.getVatRate());
                    //BigDecimal underVar = oneHen.add(invoice.getVatRate());
                    invoice.setVat((paymentHistory.getTransAmount().multiply(invoice.getVatRate())).divide(oneHen.add(invoice.getVatRate()), 2, BigDecimal.ROUND_HALF_UP));
                }
                paymentHistory.setBillAmountVat((invoice.getVat()!=null?invoice.getVat():BigDecimal.ZERO).subtract(afterSaleDiscVat));//by NSD 06-04-2017
                paymentHistory.setCurrencyCode(!StringUtils.equals(invoice.getCurrencyCode(), "12")?invoice.getCurrencyCode():"12");
//
//				BigDecimal aDecimal = new BigDecimal(0.1950);
//				BigDecimal another = aDecimal.setScale(2, aDecimal.ROUND_HALF_DOWN);
                if(invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF) || invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_TBOSS)) { // WriteOff type Invoice
                    paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : (receipt.getAttributes().indexOf("C") > -1 ? "หนี้สูญรับคืน" : "รอหักล้างหนี้สูญ"));
                } else { // "N" - normal billing
                    paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก" : (receipt.getAttributes().indexOf("C") > -1 ? "ปกติ" : "รอหักล้าง"));
                }
                paymentHistory.setStatusCode(receipt.getAttributes());//Added by Sayan.T : 20170715_1421

                if(CollectionUtils.isEmpty(invoice.getInvoiceVatDetails()) || invoice.getInvoiceVatDetails().size()<2){
                    dto.addData(paymentHistory);
                }
                paymentHistory.setRemark(receipt.getRemark());

                paymentHistory.setAccountName(receipt.getAccountName());
                paymentHistory.setAccountNo(receipt.getAccountNo());
                paymentHistory.setCharge(receipt.getChange());
                paymentHistory.setVat(receipt.getVat());
                paymentHistory.setTotalCharge(receipt.getTotalCharge());
                paymentHistory.setDeduction(receipt.getDeduction());
                paymentHistory.setBalanceDue(receipt.getBalanceDue());
                paymentHistory.setDiscount(receipt.getDiscount());
                paymentHistory.setIssueDt(invoice.getIssueDt());
                paymentHistory.setDueDt(invoice.getDueDt());
                paymentHistory.setPaymentCase(receipt.getPaymentCase());
                paymentHistory.setAccountType(receipt.getAccountType());
                paymentHistory.setReceiptId(receipt.getId());
                paymentHistory.setPaymentId(receipt.getPayment().getId());
                paymentHistory.setInvoiceId(invoice.getId());
                paymentHistory.setAccountId(invoice.getCustomer().getId());
                //case after sales discount
                if(invoice.getAfterSaleDiscount().compareTo(BigDecimal.ZERO) == 1){
                    PaymentHistory paymentHistory1 = new PaymentHistory();
                    //paymentHistory1 = paymentHistory;
                    paymentHistory1.setUpdatePrintDateStr(paymentHistory.getUpdatePrintDateStr());
                    paymentHistory1.setReceiptPrintDateStr(paymentHistory.getReceiptPrintDateStr());
                    paymentHistory1.setUpdatePrintDate(paymentHistory.getUpdatePrintDate());
                    paymentHistory1.setReceiptPrintDate(paymentHistory.getReceiptPrintDate());
                    paymentHistory1.setReceiptNo(paymentHistory.getReceiptNo());
                    paymentHistory1.setBillRefNo(paymentHistory.getBillRefNo()); // may use payment.getBillRefNo() or payment.getOrigBillRefNo().toString()
                    paymentHistory1.setShopPaymentName(paymentHistory.getShopPaymentName());
                    paymentHistory1.setPaymentReceiver(paymentHistory.getPaymentReceiver());
                    paymentHistory1.setCycleDateFrom(null);
                    paymentHistory1.setCycleDateTo(null);
                    paymentHistory1.setBillCycle(paymentHistory.getBillCycle());
                    //paymentHistory.setBillAmount(invoice.getTotalCharge() != null ? invoice.getTotalCharge() : BigDecimal.ZERO);
                    paymentHistory1.setBillAmount(paymentHistory.getBillAmount());
                    //paymentHistory1.setAfterSaleDiscount();
                    //paymentHistory1.setPaymentMethod(paymentHistory.getPaymentMethod());
                    if(StringUtils.equals(invoice.getDiscountType(), "1")){
                        paymentHistory.setPaymentCategory("ลูกค้ารับภาระภาษีเต็มจำนวน");
                        paymentHistory1.setPaymentCategory("ส่วนลดหลังการขาย");
                    }else{
                        paymentHistory.setPaymentCategory("ลูกค้ารับภาระภาษีบางส่วน");
                        paymentHistory1.setPaymentCategory("ส่วนลดหลังการขาย");
                    }
                    //paymentHistory1.setTransAmount();
                    //paymentHistory1.setBillAmountVat();//by NSD 06-04-2017
                    paymentHistory1.setStatus(paymentHistory.getStatus());
                    paymentHistory1.setStatusCode(paymentHistory.getStatusCode());

                    //paymentHistory1.setBillAmount(invoice.getAfterSaleDiscount());
                    paymentHistory1.setAfterSaleDiscount(BigDecimal.ZERO);
                    paymentHistory1.setTransAmount(invoice.getAfterSaleDiscount().add(invoice.getAfterSaleDiscVat()!=null?invoice.getAfterSaleDiscVat():BigDecimal.ZERO));//by NSD 11-04-2017
                    paymentHistory1.setBillAmountVat(invoice.getAfterSaleDiscVat()!= null ? invoice.getAfterSaleDiscVat() : BigDecimal.ZERO);//by NSD 06-04-2017

                    paymentHistory.setTransAmount(paymentHistory.getTransAmount().subtract(invoice.getVat()!=null?invoice.getVat():BigDecimal.ZERO).add(paymentHistory.getBillAmountVat()));
                    paymentHistory1.setPaymentMethod("อนุมัติ "+invoice.getDiscApprUser()+" "+AppUtil.formatter_EN.format(receipt.getUpdateDttm()));
                    paymentHistory1.setCurrencyCode(!StringUtils.equals(invoice.getCurrencyCode(), "12")?invoice.getCurrencyCode():"12");//temporary set
                    paymentHistory1.setAccountName(receipt.getAccountName());
                    paymentHistory1.setAccountNo(receipt.getAccountNo());
                    paymentHistory1.setCharge(receipt.getChange());
                    paymentHistory1.setVat(receipt.getVat());
                    paymentHistory1.setTotalCharge(receipt.getTotalCharge());
                    paymentHistory1.setDeduction(receipt.getDeduction());
                    paymentHistory1.setBalanceDue(receipt.getBalanceDue());
                    paymentHistory1.setDiscount(receipt.getDiscount());
                    paymentHistory1.setIssueDt(invoice.getIssueDt());
                    paymentHistory1.setDueDt(invoice.getDueDt());
                    paymentHistory1.setAccountType(receipt.getAccountType());
                    paymentHistory1.setReceiptId(receipt.getId());
                    paymentHistory1.setPaymentId(receipt.getPayment().getId());
                    paymentHistory1.setInvoiceId(invoice.getId());
                    paymentHistory1.setAccountId(invoice.getCustomer().getId());
                    dto.addData(paymentHistory1);
                }
                //case an invoice has both vat and non vat
                if(!CollectionUtils.isEmpty(invoice.getInvoiceVatDetails()) && invoice.getInvoiceVatDetails().size()>1){
                    for(InvoiceVatDetail dtl: invoice.getInvoiceVatDetails()){
                        PaymentHistory paymentHistory1 = new PaymentHistory();
                        //paymentHistory1 = paymentHistory;
                        paymentHistory1.setUpdatePrintDateStr(paymentHistory.getUpdatePrintDateStr());
                        paymentHistory1.setReceiptPrintDateStr(paymentHistory.getReceiptPrintDateStr());
                        paymentHistory1.setUpdatePrintDate(paymentHistory.getUpdatePrintDate());
                        paymentHistory1.setReceiptPrintDate(paymentHistory.getReceiptPrintDate());
                        paymentHistory1.setReceiptNo(paymentHistory.getReceiptNo());
                        paymentHistory1.setBillRefNo(paymentHistory.getBillRefNo()); // may use payment.getBillRefNo() or payment.getOrigBillRefNo().toString()
                        paymentHistory1.setShopPaymentName(paymentHistory.getShopPaymentName());
                        paymentHistory1.setPaymentReceiver(paymentHistory.getPaymentReceiver());
                        paymentHistory1.setCycleDateFrom(paymentHistory.getCycleDateFrom());
                        paymentHistory1.setCycleDateTo(paymentHistory.getCycleDateTo());
                        paymentHistory1.setBillCycle(paymentHistory.getBillCycle());
                        //paymentHistory.setBillAmount(invoice.getTotalCharge() != null ? invoice.getTotalCharge() : BigDecimal.ZERO);
                        paymentHistory1.setBillAmount(invoice.getTotalCharge());
                        //paymentHistory1.setAfterSaleDiscount();
                        //paymentHistory1.setPaymentMethod(paymentHistory.getPaymentMethod());
                        paymentHistory1.setPaymentCategory(paymentHistory.getPaymentCategory());
                        //paymentHistory1.setTransAmount();
                        //paymentHistory1.setBillAmountVat();//by NSD 06-04-2017
                        paymentHistory1.setStatus(paymentHistory.getStatus());
                        paymentHistory1.setStatusCode(paymentHistory.getStatusCode());

                        //paymentHistory1.setBillAmount(invoice.getAfterSaleDiscount());
                        paymentHistory1.setAfterSaleDiscount(BigDecimal.ZERO);
                        paymentHistory1.setTransAmount(dtl.getAmount().add(dtl.getVat()!= null ? dtl.getVat() : BigDecimal.ZERO));
                        paymentHistory1.setBillAmountVat(dtl.getVat()!= null ? dtl.getVat() : BigDecimal.ZERO);//by NSD 06-04-2017
                        paymentHistory1.setPaymentMethod(paymentHistory.getPaymentMethod());
                        paymentHistory1.setCurrencyCode(!StringUtils.equals(invoice.getCurrencyCode(), "12")?invoice.getCurrencyCode():"12");//temporary set
                        paymentHistory1.setAccountName(receipt.getAccountName());
                        paymentHistory1.setAccountNo(receipt.getAccountNo());
                        paymentHistory1.setCharge(receipt.getChange());
                        paymentHistory1.setVat(receipt.getVat());
                        paymentHistory1.setTotalCharge(receipt.getTotalCharge());
                        paymentHistory1.setDeduction(receipt.getDeduction());
                        paymentHistory1.setBalanceDue(receipt.getBalanceDue());
                        paymentHistory1.setDiscount(receipt.getDiscount());
                        paymentHistory1.setIssueDt(invoice.getIssueDt());
                        paymentHistory1.setDueDt(invoice.getDueDt());
                        paymentHistory1.setAccountType(receipt.getAccountType());
                        paymentHistory1.setReceiptId(receipt.getId());
                        paymentHistory1.setPaymentId(receipt.getPayment().getId());
                        paymentHistory1.setInvoiceId(invoice.getId());
                        paymentHistory1.setAccountId(invoice.getCustomer().getId());
                        dto.addData(paymentHistory1);
                    }
                }
            }
        }
        logger.info("getPaymentHistory...Done");
        return dto;
    }

    public PaymentHistoryDTO findPaymentHistory(BillingInfo input){
        //1. Get BA from front-end
        // input.getBillingNo(); // use this to search
        //List<HistoryPaymentDTO> historyPaymentDetailList = paymentService.findHistoryPaymentDetails(input.getBillingNo());
        //2. Use this to search for following information from Tables
        //		sourceAccountId;  = BA number
        //		destinationAccountId; = account internal id in Billing (may be BA internal id)
        //		billingServerId; = server id of account that billing returned when CRM created the account
        //		trackingId; = createPayment returned this value
        //		trackingIdServ;  = createPayment returned this value
        //3. Use information to fill in ESB_F14RetrievePaymentModel
        PaymentHistoryDTO dto = new PaymentHistoryDTO();
        List<Receipt> receipts = receiptRepo.findByAccountNoOrderByUpdateDttmDesc(input.getBillingNo());
        if(receipts != null && receipts.size() > 0){
            dto = getPaymentHistory(receipts);
        }

        return dto;
    }
    @Transactional
    public void cancelCoupon(Iterable<Receipt> receipts, String userAuthen) {
        for (Receipt receipt : receipts) {
            Set<Method> methods = receipt.getPayment().getMethods();
            if(methods!=null && methods.size()>0){
                for(Method method :methods){
                    if(method.getCode().equals(AppConstants.couponCode)){ // CC
                        th.co.softpos.ws.mg.s002.RqHeader rqHeader = new th.co.softpos.ws.mg.s002.RqHeader();
                        rqHeader.setFuncNm("S002MG");
                        rqHeader.setRqAppId("POS");
                        rqHeader.setUserId("9999");
                        GregorianCalendar c = new GregorianCalendar();
                        c.setTime(new Date());
                        try {
                            rqHeader.setRqDt(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
                        } catch (DatatypeConfigurationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        th.co.softpos.ws.mg.s002.RqBody rqBody = new th.co.softpos.ws.mg.s002.RqBody();
                        th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher giftvoucher =
                                new th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher();
                        giftvoucher.setCode(method.getChequeNo());
                        giftvoucher.setStatus(S002_STATUS_TYPES.W.name()); // W
                        rqBody.setMgTempImportDetailGiftvoucher(giftvoucher);
                        th.co.softpos.ws.mg.s002.Request _process_rq = new th.co.softpos.ws.mg.s002.Request();
                        _process_rq.setRqHeader(rqHeader);
                        _process_rq.setRqBody(rqBody);
                        th.co.softpos.ws.mg.s002.Response _response = s002MGUpdGiftvoucher.process(_process_rq);
                    }
                }
            }
        }
    }
    @Transactional
    public void cancelPromotion(Iterable<Receipt> receipts,String status){
        for (Receipt rct : receipts) {
            if(rct.getAccountNo()!=null && rct.getAccountNo().length()>0){
                List<PromotionMappingEntity> promotionMappingList= promotionMappingRepository.findInActivePromotionMapping(rct.getAccountNo());
                if(promotionMappingList!=null && promotionMappingList.size()>0){
                    PromotionMappingEntity promotionMappingEntity  = promotionMappingList.get(0);
                    // sb.append(promotionMappingEntity.getPromotionName()+"\n");

                    // Save to PromotionReceiptMappingEntity
				 /* 
				 PromotionReceiptMappingEntity promotionReceiptMappingEntity 
							= new PromotionReceiptMappingEntity();
				 promotionReceiptMappingEntity.setReceiptid(rct.getId());
				 promotionReceiptMappingEntity.setReceiptno(rct.getNo());
				 promotionReceiptMappingEntity.setPromotionName(promotionMappingEntity.getPromotionName());
				 promotionReceiptMappingEntity.set
				*/
                    if(status.equals("C")){
                        promotionBillingMappingRepository.delete(rct.getId());
                    }else{
                        PromotionReceiptMappingEntity promotionReceiptMappingEntity
                                = new PromotionReceiptMappingEntity();
                        promotionReceiptMappingEntity.setReceiptid(rct.getId());
                        promotionReceiptMappingEntity.setReceiptno(rct.getNo());
                        promotionReceiptMappingEntity.setPromotionName(promotionMappingEntity.getPromotionName());
                        promotionBillingMappingRepository.save(promotionReceiptMappingEntity);
                    }
				 
				/* */
                    // update promotionMappingEntity to 'S'
                    th.co.softpos.ws.mg.s004.RqHeader rqHeader = new th.co.softpos.ws.mg.s004.RqHeader();
                    rqHeader.setFuncNm("S004POS");
                    rqHeader.setRqAppId("S004");
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTime(new Date());
                    try {
                        rqHeader.setRqDt(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
                    } catch (DatatypeConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    th.co.softpos.ws.mg.s004.RqBody rqBody = new th.co.softpos.ws.mg.s004.RqBody();
                    th.co.softpos.ws.mg.s004.PosFreeBillno posFreeBillno =
                            new th.co.softpos.ws.mg.s004.PosFreeBillno();
                    posFreeBillno.setPaymentType("EPIS");
                    String receiveNo ="";
                    if(!status.equals(S004_STATUS_TYPES.C.name())){ // C
                        receiveNo = rct.getNo();

                    }else{
                        posFreeBillno.setStatus(S004_STATUS_TYPES.C.name()); // C
                    }

                    posFreeBillno.setDocSendNo(promotionMappingEntity.getReceiveNo()); // SD
                    posFreeBillno.setDocRefNo(receiveNo); // Epis received ID
                    rqBody.setPosFreeBillno(posFreeBillno);
                    th.co.softpos.ws.mg.s004.Request _process_rq = new th.co.softpos.ws.mg.s004.Request();
                    _process_rq.setRqHeader(rqHeader);
                    _process_rq.setRqBody(rqBody);
                    th.co.softpos.ws.mg.s004.Response _response = s004MGUpdFree.process(_process_rq);
                    if(status.equals(S004_STATUS_TYPES.C.name())){ // C
                        promotionMappingEntity.setStatus(S004_STATUS_TYPES.A.name()); // A = Active 
                        promotionMappingRepository.save(promotionMappingEntity);
                    }
                }
            }
        }
    }

    @Transactional
    public Receipt cancelAndCreateNewMNPReceipt(CancelPaymentDTO cancleDto, List<Receipt> rcpOld) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = null;
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();
        Long receiptId = 0L;
        Long paymentId = 0L;
        for (Receipt rec : rcpOld) {
            for (CancelPaymentDTO.Receipt updateRec : cancleDto.getReceipts()) {
                receiptId = rec.getId();
                paymentId = rec.getPayment().getId();

                List<Deduct> deducts = deductRepo.findByPaymentId(paymentId);
                for (Deduct ded : deducts) {
                    paids.add(new Paid(ded.getAmount(), ded.getType()));
                    deduct = deductRepo.save(new Deduct());
                    Long deductId = deduct.getId();
                    BeanUtils.copyProperties(ded, deduct);
                    deduct.setId(deductId);
                    deduct.setUpdateDttm(date);
                    deduct.setUpdateUser(userName);
                    deduct.setPaymentId(payment.getId());
                    payment.getDeducts().add(deduct);
                }

                List<Method> methods = methodRepo.findByPaymentId(paymentId);
                for (Method met : methods) {
                    Method method = methodRepo.save(new Method());
                    Long methodId = method.getId();
                    BeanUtils.copyProperties(met, method);
                    method.setId(methodId);
                    method.setUpdateDttm(date);
                    method.setUpdateUser(userName);
                    method.setPaymentId(payment.getId());

                    List<MethodMoneyTransfer> methodMoneyTransfers = moneyTransferRepo.findByTransaction(paymentId);
                    for (MethodMoneyTransfer metMonTrans : methodMoneyTransfers) {
                        MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                        Long moneyTransferId = moneyTransfer.getId();
                        BeanUtils.copyProperties(metMonTrans, moneyTransfer);
                        moneyTransfer.setId(moneyTransferId);
                        moneyTransfer.setUpdateDttm(date);
                        moneyTransfer.setUpdateUser(userName);
                    }

                    List<MethodCheque> methodCheques = chequeRepository.findByPaymentId(paymentId);
                    for (MethodCheque MetCheque : methodCheques) {
                        MethodCheque chequePay = chequeRepository.save(new MethodCheque());
                        Long chequePayId = chequePay.getId();
                        BeanUtils.copyProperties(MetCheque, chequePay);
                        chequePay.setId(chequePayId);
                        chequePay.setNo(met.getChequeNo());
                        chequePay.setUpdateUser(userName);
                        chequePay.setUpdateDttm(date);
                    }

                    try {
                        StringBuilder sql = new StringBuilder(
                                "select *  from TRSCREDITREF  WHERE PAYMENTID = ' " + paymentId + "' ");
                        List<MethodCreditCard> creditCardList = episJdbcTemplate.query(sql.toString(),
                                BeanPropertyRowMapper.newInstance(MethodCreditCard.class));
                        // Iterable<MethodCreditCard> creditCardList =
                        // creditCardRepo.findByPaymentId(paymentId);
                        if (creditCardList.size() > 0) {
                            for (MethodCreditCard creditCard : creditCardList) {
                                MethodCreditCard creditCardPay = creditCardRepo.save(new MethodCreditCard());
                                creditCardPay.setPaymentId(payment.getId());
                                creditCardPay.setNo(creditCard.getNo());
                                creditCardPay.setAmount(creditCard.getAmount());
                                creditCardPay.setBankIssuer(creditCard.getBankIssuer());
                                creditCardPay.setType(creditCard.getType());
                                creditCardPay.setUpdateDttm(date);
                                creditCardPay.setUpdateUser(userName);
                            }
                        }
                    } catch (Exception e) {

                    }

                }

                try {
                    // List<Customer> customs =
                    // customerRepo.findPayment(paymentId);
                    if (rec.getCustomer() != null) {
                        Customer custom = rec.getCustomer();
                        customer = customerRepo.save(new Customer());
                        Long customId = customer.getId();
                        BeanUtils.copyProperties(custom, customer);
                        customer.setId(customId);
                        customer.setName(updateRec.getAccountName());
                        customer.setReceiptAddr(updateRec.getAddrLine1());
                        customer.setInvoiceAddr(updateRec.getAddrLine1());
                        customer.setUpdateDttm(date);
                        customer.setUpdateUser(userName);
                        customers.add(customer);
                        customer.setPayment(payment);
                    }
                } catch (Exception e) {

                }

                receipt = receiptRepo.save(new Receipt());
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);

                try {
                    // List<Service> services =
                    // serviceRepo.findByReceiptId(receiptId);
                    if (rec.getServices() != null) {
                        for (Service serv : rec.getServices()) {
                            service = serviceRepo.save(new Service());
                            receipt.getServices().add(service);
                            service.setReceiptId(receipt.getId());
                            service.setServiceCode(serv.getServiceCode());
                            service.setServiceName(serv.getServiceName());
                            service.setServiceNo(serv.getServiceNo());
                            service.setProductCode(serv.getProductCode());
                            service.setProductName(serv.getProductName());
                            service.setProductSubCode(serv.getProductSubCode());
                            service.setProductSubName(serv.getProductSubName());
                            service.setIncomeType(serv.getIncomeType());
                            service.setIncomeAmount(serv.getIncomeAmount());
                            service.setMdn(serv.getMdn());
                            service.setIccid(serv.getIccid());
                            service.setIncomeUnit(serv.getIncomeUnit());
                            service.setRefTransId(serv.getRefTransId());
                            service.setAmount(serv.getAmount());
                            service.setDiscount(serv.getDiscount());
                            service.setCharge(serv.getCharge());
                            service.setVat(serv.getVat());
                            service.setTotalCharge(serv.getTotalCharge());
                            service.setDeduction(serv.getDeduction());
                            service.setOrderId(serv.getOrderId());
                            service.setVatRate(serv.getVatRate());
                            service.setUpdateUser(serv.getUpdateUser());
                            service.setUpdateDttm(serv.getUpdateDttm());
                            service.setPaymentId(payment.getId());


                            Transaction transaction;
                            if (serv.getTransactions() != null) {
                                for (Transaction trans : serv.getTransactions()) {
                                    transaction = transactionRepo.save(new Transaction());
                                    transaction.setUpdateDttm(date);
                                    transaction.setUpdateUser(userName);
                                    transaction.setServiceId(service.getId());
                                    transaction.setTransactionId(trans.getTransactionId());// (AppUtil.generateTransactionID(15));
                                    transaction.setTrackingDetails(trans.getTrackingDetails());
                                    transaction.setTrackingRetry(trans.getTrackingRetry());
                                    transaction.setPaymentDate(trans.getPaymentDate());
                                    transaction.setPaymentType(trans.getPaymentType());
                                    transaction.setAmount(trans.getAmount());
                                    transaction.setChequeNo(trans.getChequeNo());
                                    transaction.setAccountNo(trans.getAccountNo());
                                    transaction.setStatus(trans.getStatus());
                                    transaction.setPayment(payment);
                                    transaction.setTrackingCode(trans.getTrackingCode());
                                    transaction.setTrackingIdServ(trans.getTrackingIdServ());
                                    transaction.setTrackingId(trans.getTrackingId());
                                    receipt.getServices().add(service);
                                    service.getTransactions().add(transaction);
                                }
                            }

                        }
                    }

                } catch (Exception e) {

                }

                receipt.setPayment(payment);
                receipt.setCustomer(customer);
                receipt.setType(rec.getType());
                receipt.setNo(
                        getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(rec.getType()) ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE
                                : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, date, RECEIPT_HEADER_MNP));
                receipt.setName(updateRec.getAccountName());
                receipt.setAccountName(updateRec.getAccountName());
                receipt.setAccountNo(rec.getAccountNo());
                receipt.setAccountSubNo(rec.getAccountSubNo());
                receipt.setAccountType(rec.getAccountType());
                receipt.setTaxNo(rec.getTaxNo());
                receipt.setAccountBranch(rec.getAccountBranch());
                receipt.setAddrLine1(updateRec.getAddrLine1());
                receipt.setAddrLine2(updateRec.getAddrLine1());
                receipt.setBranchCode(rec.getBranchCode());
                receipt.setBranchArea(rec.getBranchArea());
                receipt.setBranchName(rec.getBranchName());
                receipt.setAmount(rec.getAmount());
                receipt.setDiscount(rec.getDiscount());
                receipt.setCharge(rec.getCharge());
                receipt.setVatRate(rec.getVatRate());
                receipt.setVat(rec.getVat());
                receipt.setTotalCharge(rec.getTotalCharge());
                receipt.setDeduction(rec.getDeduction());
                receipt.setAfterSaleDiscount(rec.getAfterSaleDiscount());
                receipt.setBalanceDue(rec.getBalanceDue());
                receipt.setReceived(rec.getReceived());
                receipt.setAfterSaleDiscVat(rec.getAfterSaleDiscVat());
                receipt.setAdvanced(rec.getAdvanced());
                receipt.setPromotion(rec.getPromotion());
                receipt.setRefRctNo(rec.getNo());
                receipt.setRemark("ยกเลิกใบเก่า ออกใบใหม่ อ้างอิงใบเสร็จรับเงินเลขที่ : "
                        + rec.getNo());
                receipt.setReprint(rec.getReprint());
                receipt.setAttributes(rec.getAttributes());
                receipt.setBillingGroup(rec.getBillingGroup());
                receipt.setBillingGroupFull(rec.getBillingGroupFull());
                receipt.setBillingServiceName(rec.getBillingServiceName());
                receipt.setLanguage(rec.getLanguage());
                receipt.setChange(rec.getChange());
                receipt.setAdvanced(rec.getAdvanced());
                receipt.setFlgHeader(rec.getFlgHeader());
                receipt.setPaymentCase(rec.getPaymentCase());
                receipt.setExcDiscount(rec.getExcDiscount());
                receipt.setCustCategoryDesc(rec.getCustCategoryDesc());
                receipts.add(receipt);
                saveLogCorReceipt(receipt, receipt.getAttributes());
                payment.setOfficerId(officerId);
                payment.setShopId(EpContextHolder.getContext().getBranchId());
                payment.setPosId(EpContextHolder.getContext().getPosId());
                payment.setCollectionUnit(rec.getPayment().getCollectionUnit());
                payment.setDate(receiptDttm);
                payment.setType(rec.getPayment().getType());
                payment.setMethod(rec.getPayment().getMethod());
                payment.setAmount(rec.getPayment().getAmount());
                payment.setDiscount(rec.getPayment().getDiscount());
                payment.setCharge(rec.getPayment().getCharge());
                payment.setVatRate(rec.getPayment().getVatRate());
                payment.setVat(rec.getPayment().getVat());
                payment.setTotalCharge(rec.getPayment().getTotalCharge());
                payment.setDeduction(rec.getPayment().getDeduction());
                payment.setAfterSaleDiscount(rec.getPayment().getAfterSaleDiscount());
                payment.setBalanceDue(rec.getPayment().getBalanceDue());
                payment.setReceived(rec.getPayment().getReceived());
                payment.setChange(rec.getPayment().getChange());
                payment.setAdvanced(rec.getPayment().getAdvanced());
                payment.setAttributes(rec.getPayment().getAttributes());
                payment.setCurrencyCode(rec.getPayment().getCurrencyCode());
                payment.setCurrencyRate(rec.getPayment().getCurrencyRate());

                //listReceipts.add(receipt);

            }
        }

        return receipt;
    }
    @Transactional
    public Receipt createRecepitRepeatOrder(SettlePaymentDTO.Service paymentDTO, List<Receipt> rcpOld) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Service service;
        Receipt receipt = new Receipt();
        List<Customer> customers = new ArrayList<Customer>();
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();
        Long receiptId = 0L;
        Long paymentId = 0L;
        for (Receipt rec : rcpOld) {
            receiptId = rec.getId();
            paymentId = rec.getPayment().getId();

            List<Deduct> deducts = deductRepo.findByPaymentId(paymentId);
            for (Deduct ded : deducts) {
                paids.add(new Paid(ded.getAmount(), ded.getType()));
                deduct = deductRepo.save(new Deduct());
                Long deductId = deduct.getId();
                BeanUtils.copyProperties(ded, deduct);
                deduct.setId(deductId);
                deduct.setUpdateDttm(date);
                deduct.setUpdateUser(userName);
                deduct.setPaymentId(payment.getId());
                payment.getDeducts().add(deduct);
            }

            List<Method> methods = methodRepo.findByPaymentId(paymentId);
            for (Method met : methods) {
                Method method = methodRepo.save(new Method());
                Long methodId = method.getId();
                BeanUtils.copyProperties(met, method);
                method.setId(methodId);
                method.setUpdateDttm(date);
                method.setUpdateUser(userName);
                method.setPaymentId(payment.getId());

                List<MethodMoneyTransfer> methodMoneyTransfers = moneyTransferRepo.findByTransaction(paymentId);
                for (MethodMoneyTransfer metMonTrans : methodMoneyTransfers) {
                    MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                    Long moneyTransferId = moneyTransfer.getId();
                    BeanUtils.copyProperties(metMonTrans, moneyTransfer);
                    moneyTransfer.setId(moneyTransferId);
                    moneyTransfer.setUpdateDttm(date);
                    moneyTransfer.setUpdateUser(userName);
                }

                List<MethodCheque> methodCheques = chequeRepository.findByPaymentId(paymentId);
                for (MethodCheque MetCheque : methodCheques) {
                    MethodCheque chequePay = chequeRepository.save(new MethodCheque());
                    Long chequePayId = chequePay.getId();
                    BeanUtils.copyProperties(MetCheque, chequePay);
                    chequePay.setId(chequePayId);
                    chequePay.setNo(met.getChequeNo());
                    chequePay.setUpdateUser(userName);
                    chequePay.setUpdateDttm(date);
                }

                try {
                    StringBuilder sql = new StringBuilder(
                            "select *  from TRSCREDITREF  WHERE PAYMENTID = ' " + paymentId + "' ");
                    List<MethodCreditCard> creditCardList = episJdbcTemplate.query(sql.toString(),
                            BeanPropertyRowMapper.newInstance(MethodCreditCard.class));
                    // Iterable<MethodCreditCard> creditCardList =
                    // creditCardRepo.findByPaymentId(paymentId);
                    if (creditCardList.size() > 0) {
                        for (MethodCreditCard creditCard : creditCardList) {
                            MethodCreditCard creditCardPay = creditCardRepo.save(new MethodCreditCard());
                            creditCardPay.setPaymentId(payment.getId());
                            creditCardPay.setNo(creditCard.getNo());
                            creditCardPay.setAmount(creditCard.getAmount());
                            creditCardPay.setBankIssuer(creditCard.getBankIssuer());
                            creditCardPay.setType(creditCard.getType());
                            creditCardPay.setUpdateDttm(date);
                            creditCardPay.setUpdateUser(userName);
                        }
                    }
                } catch (Exception e) {

                }

            }

            try {
                if (rec.getCustomer() != null) {
                    Customer custom = rec.getCustomer();
                    customer = customerRepo.save(new Customer());
                    Long customId = customer.getId();
                    BeanUtils.copyProperties(custom, customer);
                    customer.setId(customId);
                    customer.setName(rec.getAccountName());
                    customer.setReceiptAddr(rec.getAddrLine1());
                    customer.setInvoiceAddr(rec.getAddrLine1());
                    customer.setUpdateDttm(date);
                    customer.setUpdateUser(userName);
                    customers.add(customer);
                    customer.setPayment(payment);
                }
            } catch (Exception e) {

            }

            receipt = receiptRepo.save(new Receipt());
            receipt.setUpdateDttm(date);
            receipt.setUpdateUser(userName);
            receipt.setDocDttm(receiptDttm);

            try {
                if (rec.getServices() != null) {
                    for (Service serv : rec.getServices()) {
                        service = serviceRepo.save(new Service());
                        receipt.getServices().add(service);
                        service.setReceiptId(receipt.getId());
                        service.setUpdateDttm(date);
                        service.setUpdateUser(userName);
                        receipt.getServices().add(service);
                        service.setReceiptId(receipt.getId());
                        service.setIncomeType(serv.getIncomeType());
                        service.setMdn(serv.getMdn());
                        service.setIccid(serv.getIccid());
                        service.setRefTransId(serv.getRefTransId());
                        service.setAmount(serv.getAmount());
                        service.setDiscount(serv.getDiscount());
                        service.setCharge(serv.getCharge());
                        service.setVat(serv.getVat());
                        service.setTotalCharge(serv.getTotalCharge());
                        service.setDeduction(serv.getDeduction());
                        service.setVatRate(serv.getVatRate());
                        service.setOrderId(paymentDTO.getOrderId());
                        service.setRefOrderId(paymentDTO.getRefOrderId());
                        service.setPaymentId(payment.getId());

                        Transaction transaction;
                        if (serv.getTransactions() != null) {
                            for (Transaction trans : serv.getTransactions()) {
                                transaction = transactionRepo.save(new Transaction());
                                transaction.setUpdateDttm(date);
                                transaction.setUpdateUser(userName);
                                transaction.setServiceId(service.getId());
                                transaction.setTransactionId(trans.getTransactionId());// (AppUtil.generateTransactionID(15));
                                transaction.setTrackingDetails(trans.getTrackingDetails());
                                transaction.setTrackingRetry(trans.getTrackingRetry());
                                transaction.setPaymentDate(trans.getPaymentDate());
                                transaction.setPaymentType(trans.getPaymentType());
                                transaction.setAmount(trans.getAmount());
                                transaction.setChequeNo(trans.getChequeNo());
                                transaction.setAccountNo(trans.getAccountNo());
                                transaction.setStatus(trans.getStatus());
                                transaction.setPayment(payment);
                                transaction.setTrackingCode(trans.getTrackingCode());
                                transaction.setTrackingIdServ(trans.getTrackingIdServ());
                                transaction.setTrackingId(trans.getTrackingId());
                                receipt.getServices().add(service);
                                service.getTransactions().add(transaction);
                            }
                        }

                    }
                }

            } catch (Exception e) {

            }

            receipt.setPayment(payment);
            receipt.setCustomer(customer);
            receipt.setType(rec.getType());
            receipt.setNo(
                    getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(rec.getType()) ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE
                            : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, date, RECEIPT_HEADER_MNP));
            receipt.setName(rec.getAccountName());
            receipt.setAccountName(rec.getAccountName());
            receipt.setAccountNo(paymentDTO.getOrderId());
            receipt.setAccountSubNo(rec.getAccountSubNo());
            receipt.setAccountType(rec.getAccountType());
            receipt.setTaxNo(rec.getTaxNo());
            receipt.setAccountBranch(rec.getAccountBranch());
            receipt.setAddrLine1(rec.getAddrLine1());
            receipt.setAddrLine2(rec.getAddrLine1());
            receipt.setBranchCode(rec.getBranchCode());
            receipt.setBranchArea(rec.getBranchArea());
            receipt.setBranchName(rec.getBranchName());
            receipt.setAmount(rec.getAmount());
            receipt.setDiscount(rec.getDiscount());
            receipt.setCharge(rec.getCharge());
            receipt.setVatRate(rec.getVatRate());
            receipt.setVat(rec.getVat());
            receipt.setTotalCharge(rec.getTotalCharge());
            receipt.setDeduction(rec.getDeduction());
            receipt.setAfterSaleDiscount(rec.getAfterSaleDiscount());
            receipt.setBalanceDue(rec.getBalanceDue());
            receipt.setReceived(rec.getReceived());
            receipt.setAfterSaleDiscVat(rec.getAfterSaleDiscVat());
            receipt.setAdvanced(rec.getAdvanced());
            receipt.setPromotion(rec.getPromotion());
            receipt.setRefRctNo(rec.getNo());
            receipt.setReprint(rec.getReprint());
            receipt.setAttributes(rec.getAttributes());
            receipt.setBillingGroup(rec.getBillingGroup());
            receipt.setBillingGroupFull(rec.getBillingGroupFull());
            receipt.setBillingServiceName(rec.getBillingServiceName());
            receipt.setLanguage(rec.getLanguage());
            receipt.setChange(rec.getChange());
            receipt.setAdvanced(rec.getAdvanced());
            receipt.setFlgHeader(rec.getFlgHeader());
            receipt.setPaymentCase(rec.getPaymentCase());
            receipt.setExcDiscount(rec.getExcDiscount());
            receipt.setRemark(paymentDTO.getRef1());
            receipt.setBranchArea(branchArea);
            receipt.setBranchCode(branchCode);
            receipt.setBranchName(branchName);
            receipt.setAttributes("FC");
            receipts.add(receipt);
            saveLogCorReceipt(receipt, receipt.getAttributes());
            payment.setOfficerId(officerId);
            payment.setShopId(EpContextHolder.getContext().getBranchId());
            payment.setPosId(EpContextHolder.getContext().getPosId());
            payment.setCollectionUnit(rec.getPayment().getCollectionUnit());
            payment.setDate(receiptDttm);
            payment.setType(rec.getPayment().getType());
            payment.setMethod(rec.getPayment().getMethod());
            payment.setAmount(rec.getPayment().getAmount());
            payment.setDiscount(rec.getPayment().getDiscount());
            payment.setCharge(rec.getPayment().getCharge());
            payment.setVatRate(rec.getPayment().getVatRate());
            payment.setVat(rec.getPayment().getVat());
            payment.setTotalCharge(rec.getPayment().getTotalCharge());
            payment.setDeduction(rec.getPayment().getDeduction());
            payment.setAfterSaleDiscount(rec.getPayment().getAfterSaleDiscount());
            payment.setBalanceDue(rec.getPayment().getBalanceDue());
            payment.setReceived(rec.getPayment().getReceived());
            payment.setChange(rec.getPayment().getChange());
            payment.setAdvanced(rec.getPayment().getAdvanced());
            payment.setAttributes(rec.getPayment().getAttributes());
            payment.setCurrencyCode(rec.getPayment().getCurrencyCode());
            payment.setCurrencyRate(rec.getPayment().getCurrencyRate());

        }

        return receipt;
    }

    
    
    @Transactional
    public List<Receipt> createPaymentInvoiceManual(SettlePaymentDTO paymentDTO) throws Exception   {
        String branchCode = EpContextHolder.getContext().getBranchCode(),
                branchArea = EpContextHolder.getContext().getBranchArea(),
                branchName = EpContextHolder.getContext().getDescAbvrth();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
                posNo = EpContextHolder.getContext().getPosNo();
        Long officerId = EpContextHolder.getContext().getOfficerId();
        MethodCheque chequePay = new MethodCheque();
        MethodCreditCard creditCardPay = new MethodCreditCard();
        MethodMoneyTransfer moneyTransfer = new MethodMoneyTransfer();
        MethodMoneyOrder moneyOrder = new MethodMoneyOrder();
        MethodBillExchange billExchange = new MethodBillExchange();
        Ofset oFset = new Ofset();
        MethodOther otherPay = new MethodOther();
        Date date = new Date();
        Payment payment = paymentRepo.save(new Payment());
        payment.setUpdateDttm(date);
        payment.setUpdateUser(userName);
        Deduct deduct;
        Customer customer = null;
        Invoice invoice;
        InvoiceVatDetail invoiceVatDetail;
        Receipt receipt = null;
        BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
                vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
                afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO;
        List<Customer> customers = new ArrayList<Customer>();
        List<Customer> episCustomers = null;
        List<Receipt> receipts = new ArrayList<Receipt>();
        List<Paid> paids = new ArrayList<Paid>();
        Date receiptDttm = new Date();
        Long receiptID = null;

        ReceiptEgpMappingEntity egpMap = null;
        List<ReceiptEgpMappingEntity> egpMaps = new ArrayList<ReceiptEgpMappingEntity>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userLogin = EpContextHolder.getContext().getUserName();
        String language = paymentDTO.getLanguage();

        for (

                SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
            paids.add(new Paid(ded.getAmount(), ded.getType()));
            deduct = deductRepo.save(new Deduct());
            deduct.setUpdateDttm(date);
            deduct.setUpdateUser(userName);
            deduct.setNo(ded.getWithholdingDocNo());
            deduct.setType(ded.getType());
            deduct.setAmount(ded.getAmount());
            deduct.setDate(date);
            deduct.setPaymentId(payment.getId());
            deduct.setInvoiceNo(ded.getInvoiceNo());
            payment.getDeducts().add(deduct);
        }
        for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
            if (met != null && met.getType() != null) {
                Paid paid = new Paid(met.getAmount(), met.getType());
                paids.add(paid); // Preparing: To substract into invoice.
                Method method = methodRepo.save(new Method());
                method.setUpdateDttm(date);
                method.setUpdateUser(userName);
                for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
                    if ("Billing".equals(cust.getSouceType())) {
                        method.setCode("CC");
                    } else {
                        method.setCode(met.getCode());
                    }
                    method.setAccountNo(cust.getCustNo());
                }
                method.setName(met.getName());
                method.setChequeNo(met.getChequeNo());
                method.setAccountNo(met.getBankAcct());
                method.setAmount(met.getAmount());
                method.setPaymentId(payment.getId());

                if (PAY_METHOD_BANKTRANSFER.equals(met.getType())
                        || PAY_METHOD_FOREIGNTRANSFER.equals(met.getType())) { // Money
                    // Transfer
                	 moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
                     moneyTransfer.setUpdateDttm(date);
                     moneyTransfer.setUpdateUser(userName);
                     moneyTransfer.setDate(met.getTransferDt());
                     moneyTransfer.setPayCode(met.getCode());
                     moneyTransfer.setPayType(met.getType());
                     moneyTransfer.setPaymentId(payment.getId());
                     moneyTransfer.setMethodId(method.getId());
                     paid.setMoneyTransfer(moneyTransfer);
                     paid.setIsBackDate(met.isBackDt());
                } else if (PAY_METHOD_CHEQUE.equals(met.getType())) {
                    // TODO: complete all the saving methods and pulling them to
                    // print correctly
                	chequePay = chequeRepository.save(new MethodCheque());
                    chequePay.setAmount(met.getAmount());
                    chequePay.setBankCode(met.getPayChqBankCode());
                    chequePay.setBankName(met.getPayChqBankName());
                    chequePay.setBankBrnh(met.getPayChqBranch());
                    chequePay.setChequeDate(DateUtil.convertToDate(met.getChequeDt()));
                    chequePay.setNo(met.getChequeNo());
                    chequePay.setUpdateUser(userName);
                    chequePay.setUpdateDttm(date);
                    chequePay.setPaymentId(payment.getId());
                    chequePay.setMethodId(method.getId());
                } else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
                    // Fix by EPIS8 30/12/2016 issue no 166
                	creditCardPay = creditCardRepo.save(new MethodCreditCard());
                    creditCardPay.setPaymentId(payment.getId());
                    creditCardPay.setNo(met.getCardNo());
                    creditCardPay.setAmount(met.getAmount());
                    creditCardPay.setBankIssuer(met.getBankName());
                    creditCardPay.setType(met.getCardType());
                    creditCardPay.setUpdateDttm(date);
                    creditCardPay.setUpdateUser(userName);
                    creditCardPay.setMethodId(method.getId());
                    // End Fix by EPIS8 30/12/2016 issue no 166
                } else if (PAY_METHOD_MONEYORDER.equals(met.getType())) {
                    moneyOrder = moneyOrderRepository.save(new MethodMoneyOrder());
                    moneyOrder.setNo(met.getMnyOrderNo());
                    moneyOrder.setDate(met.getMnyOrderDt());
                    moneyOrder.setPostCode(met.getPostCode());
                    moneyOrder.setUpdateDttm(date);
                    moneyOrder.setUpdateUser(userName);
                    moneyOrder.setPaymentId(payment.getId());
                    moneyOrder.setMethodId(method.getId());
                } else if (PAY_METHOD_BILLEXCHANGE.equals(met.getType())) {
                    billExchange = billOfExchangRepository.save(new MethodBillExchange());
                    billExchange.setNo(met.getTrnfNo());
                    billExchange.setDate(met.getTransferDt());
                    billExchange.setPostCode(met.getPostCode());
                    billExchange.setUpdateDttm(date);
                    billExchange.setUpdateUser(userName);
                    billExchange.setPaymentId(payment.getId());
                    billExchange.setMethodId(method.getId());
                } else if (PAY_METHOD_COUPON.equals(met.getType())) {
                    th.co.softpos.ws.mg.s002.RqHeader rqHeader = new th.co.softpos.ws.mg.s002.RqHeader();
                    rqHeader.setFuncNm("S002MG");
                    rqHeader.setRqAppId("POS");
                    rqHeader.setUserId("9999");
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTime(new Date());
                    try {
                        rqHeader.setRqDt(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
                    } catch (DatatypeConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    th.co.softpos.ws.mg.s002.RqBody rqBody = new th.co.softpos.ws.mg.s002.RqBody();
                    th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher giftvoucher =
                            new th.co.softpos.ws.mg.s002.MgTempImportDetailGiftvoucher();
                    giftvoucher.setCode(met.getCouponNo());
                    giftvoucher.setStatus("S");
                    rqBody.setMgTempImportDetailGiftvoucher(giftvoucher);
                    th.co.softpos.ws.mg.s002.Request _process_rq = new th.co.softpos.ws.mg.s002.Request();
                    _process_rq.setRqHeader(rqHeader);
                    _process_rq.setRqBody(rqBody);
                    th.co.softpos.ws.mg.s002.Response _response = s002MGUpdGiftvoucher.process(_process_rq);
                    method.setChequeNo(met.getCouponNo());
                } else if (PAY_METHOD_OFFSET.equals(met.getType())) {
                	oFset = ofsetRepository.save(new Ofset());
                    oFset.setAmount(met.getAmount());
                    oFset.setNo(met.getOffsetDocumentNo());
                    oFset.setOfsetcode(met.getOffsetAccountCode());
                    oFset.setOfsetname(met.getOffsetAccountName());
                    oFset.setUpdateDttm(date);
                    oFset.setUpdateUser(userName);
                    oFset.setPaymentId(payment.getId());
                    oFset.setMethodId(method.getId());
                } else if (PAY_METHOD_OTHER.equals(met.getType())) {
                    otherPay = otherRepository.save(new MethodOther());
                    otherPay.setNo(met.getOtherNo());
                    otherPay.setType(met.getOtherType());
                    otherPay.setDate(met.getOtherDt());
                    otherPay.setUpdateDttm(date);
                    otherPay.setUpdateUser(userName);
                    otherPay.setPaymentId(payment.getId());
                    otherPay.setMethodId(method.getId());
                } 
                payment.getMethods().add(method);
            }
        }
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            episCustomers = null;
            episCustomers = customerRepo.findByNo(cust.getCustNo());
            if (CollectionUtils.isEmpty(episCustomers)) {
                customer = customerRepo.save(new Customer());
            } else {
                customer = episCustomers.get(0);
                customerRepo.save(customer);
            }
            customer.setUpdateDttm(date);
            customer.setUpdateUser(userName);
            customers.add(customer);
            customer.setPayment(payment);
            customer.setType(cust.getCustType());
            customer.setNo(cust.getCustNo());
            customer.setName(cust.getCustName());
            customer.setTax(cust.getTaxNo());
            customer.setBillGroup(cust.getBillGroup());
            customer.setCollectionUnit(cust.getCollectionUnit());
            customer.setOutstanding(cust.getOutstanding());
            customer.setRemark(cust.getRemark());
            customer.setReceiptAddr(cust.getAddress1());
            customer.setInvoiceAddr(cust.getAddress2());
            customer.setAcctCatLkp(cust.getAcctCatLkp());// by NSD 02-03-2017
            customer.setCatCustomerSegment(cust.getCatCustomerSegment());

            // Additional Conditions for GFMIS, date and branch
            String customerType = stripToEmpty(customer.getType()).toLowerCase();
            // if (customerType.indexOf("organization") == 0 ||
            // customerType.indexOf("stateagency") == 0) {
            customer.setBranch(cust.getCustBranch());
            // }
            for (Paid paid : paids) {
                if (paid.getMoneyTransfer() != null & paid.isBackDate()) {
                    receiptDttm = paid.getMoneyTransfer().getDate();
                    break;
                }
            }
            boolean split = cust.getSplit();
            if (!split) {
                if (cust.getInvoices().size() < 1)
                    continue;
                receipt = receiptRepo.save(new Receipt());
                receiptID = receipt.getId();
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);
                receipt.setFlgHeader(FLG_HEADER_2);
                //Set posID And posNo
                receipt.setPosid(EpContextHolder.getContext().getPosId());
                receipt.setPosno(EpContextHolder.getContext().getPosNo());
                Map<String, String> accountSubNoMap = new HashMap<String, String>();
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    invoice = invoiceRepo.save(new Invoice());
                    invoice.setUpdateDttm(date);
                    invoice.setUpdateUser(userName);
                    receipt.getInvoices().add(invoice);
                    addTransactionsIntoService(paids, payment, invoice, date, userName);
                    invoice.setReceiptId(receipt.getId());
                    invoice.setCustomer(customer);
                    invoice.setPayment(payment);
                    invoice.setNo(inv.getNo());
                    invoice.setKenan(inv.getKenan());
                    invoice.setCurrencyCode(inv.getCurrencyCode());
                    invoice.setIssueDt(inv.getIssueDt());
                    invoice.setDueDt(inv.getDueDt());
                    invoice.setBillCycle(inv.getBillCycle());
                    invoice.setAmount(inv.getAmount());
                    invoice.setDiscount(inv.getDiscount());
                    invoice.setCharge(inv.getCharge());
                    if (!StringUtils.equals(inv.getStatus(), INVOICE_OTHER_PAYMENT)
                            || inv.getVat().compareTo(new BigDecimal(0)) == 1) {
                        invoice.setVat(inv.getVat());
                        invoice.setVatRate(inv.getVatRate());
                        if (!StringUtils.equals(inv.getTaxTypeCode(), TAX_CODE_NONVAT)
                                || inv.getVat().compareTo(new BigDecimal(0)) == 1) {
                            receipt.setFlgHeader(FLG_HEADER_1);
                        }
                    } else {
                        invoice.setVat(null);
                        invoice.setVatRate(null);
                    }
                    invoice.setTotalCharge(inv.getReceived());
                    invoice.setDeduction(inv.getDeduction());
                    invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
                    invoice.setBalanceDue(inv.getBalanceDue());
                    invoice.setReceived(inv.getReceived());
                    invoice.setAfterSaleDiscVat(inv.getAfterSaleDiscVat());// by
                    // NSD
                    // 05-04-2017
                    invoice.setChange(inv.getChange());
                    invoice.setAdvanced(inv.getAdvanced());
                    invoice.setDebtAmount(inv.getTotalCharge());
                    invoice.setStatus(inv.getStatus());
                    invoice.setDiscountType(inv.getDiscountType());// by NSD
                    // 04-04
                    invoice.setDiscApprUser(inv.getDiscApprUser());
                    if("Billing".equals(paymentDTO.getCustomers().get(0).getSouceType())){
                    	invoice.setUpdateUser(paymentDTO.getUpdaetBy());
                    	invoice.setUpdateDttm(paymentDTO.getUpdateDate());
                    }
                    String subNoInv = "";
                    if (!CollectionUtils.isEmpty(inv.getSubNoList())) {
                        for (String subno : inv.getSubNoList()) {
                            accountSubNoMap.put(subno, subno);
                            subNoInv += subno + "|";
                        }
                    }
                    invoice.setSubNo(subNoInv);

                    // invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO)
                    // == 0 ?
                    // (inv.getTotalCharge().compareTo(invoice.getReceived()) !=
                    // 0 ? "P" : "F") : "P");//Commented by EPIS4
                    invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0 ? "F" : "P");

                    System.out.println("# PART 1 receipt.getId() = " + receipt.getId());
                    System.out.println("# PART 1 invoice.getReceiptId() = " + invoice.getReceiptId());
                    String paymentCase = "";
                    for (SettlePaymentDTO.Invoice inv2 : cust.getInvoices()) {
                        if (receipt.getId() == invoice.getReceiptId()) {
                            if (inv2.getPaymentCase() != null && inv2.getPaymentCase().indexOf("+") > 0) {
                                String paymentCaseArr[] = inv2.getPaymentCase().split("\\+");
                                for (int i = 0; i < paymentCaseArr.length; i++) {
                                    if (paymentCaseArr[paymentCaseArr.length - 1].length() <= 1) { // Remove
                                        // "+"
                                        // last
                                        // index
                                        paymentCase = inv2.getPaymentCase().substring(0,
                                                inv2.getPaymentCase().length() - 3);
                                    } else {
                                        paymentCase = inv2.getPaymentCase();
                                    }
                                }
                                receipt.setPaymentCase(paymentCase);
                            } else {
                                receipt.setPaymentCase(inv2.getPaymentCase());
                            }
                        }
                    }
                    
					/*
					 * if(!CollectionUtils.isEmpty(inv.getSubNoList())) {
					 * for(String subno:inv.getSubNoList()){
					 * accountSubNoMap.put(subno,subno); } }
					 */ // by NSD 23-03-2017
                    if (!CollectionUtils.isEmpty(inv.getInvoiceVatDetails())) {
                        for (SettlePaymentDTO.InvoiceVatDetail invDtl : inv.getInvoiceVatDetails()) {
                            invoiceVatDetail = invoiceVatRepository.save(new InvoiceVatDetail());
                            invoiceVatDetail.setUpdateDttm(date);
                            invoiceVatDetail.setUpdateUser(userName);
                            invoice.getInvoiceVatDetails().add(invoiceVatDetail);
                            invoiceVatDetail.setInvoiceId(invoice.getId());
                            invoiceVatDetail.setInvoiceNo(invDtl.getInvoiceNo());
                            invoiceVatDetail.setAccountNo(invDtl.getAccountNo());
                            invoiceVatDetail.setAmount(invDtl.getAmount());
                            invoiceVatDetail.setVat(invDtl.getVat());
                            invoiceVatDetail.setTaxTypeCode(invDtl.getTaxTypeCode());

                        }
                    }
                }
                if(cust.getServices().size() >0) {
                	for (SettlePaymentDTO.Service serviceDTO : cust.getServices()) {
                    	
                        Service service = serviceRepo.save(new Service());
                        service.setPaymentId(payment.getId());
                        service.setUpdateDttm(date);
                        service.setReceiptId(receipt.getId());
                        service.setAccountNo(cust.getCustNo());
                        service.setProductCode(serviceDTO.getProductCode());
                        service.setProductName(serviceDTO.getProductName());
                        service.setProductSubCode(serviceDTO.getSubProductCode());
                        service.setProductSubName(serviceDTO.getSubProductCode());
                        service.setServiceCode(serviceDTO.getCode());
                        service.setServiceName(serviceDTO.getServiceName());
                        service.setServiceTypeName(serviceDTO.getServiceTypeName());
                        service.setIncomeType("1");
                        service.setAmount(serviceDTO.getAmount());
    			 		service.setCharge(serviceDTO.getCharge());
    			 		service.setVat(serviceDTO.getVat());
    			 		service.setDiscount(serviceDTO.getDiscount());
    			 		service.setVatRate(serviceDTO.getVatRate());
    			 		service.setDeduction(serviceDTO.getDeduction());
    			 		service.setTotalCharge(serviceDTO.getTotalCharge());
    			 		
                        receipt.getServices().add(service);
                    	
                    }
                }
                
                
                BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                        rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                        rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO,
                        rcpWt = BigDecimal.ZERO, rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO,
                        rcpAfterSaleDiscVat = BigDecimal.ZERO;
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    rcpAmount = rcpAmount.add(inv.getAmount());
                    rcpDiscount = rcpDiscount.add(inv.getDiscount());
                    rcpCharge = rcpCharge.add(inv.getCharge());
                    rcpVat = rcpVat.add(inv.getVat());
                    rcpDeduction = rcpDeduction.add(inv.getDeduction());
                    rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(inv.getAfterSaleDiscount());
                    rcpBalanceDue = rcpBalanceDue.add(inv.getBalanceDue());
                }
                for (Invoice inv : receipt.getInvoices()) {
                    rcpTotalCharge = rcpTotalCharge.add(inv.getTotalCharge());
                    rcpReceived = rcpReceived.add(inv.getReceived());
                    if (null != inv.getAfterSaleDiscVat()) {// by NSD 05-04-2017
                        rcpAfterSaleDiscVat = rcpAfterSaleDiscVat.add(inv.getAfterSaleDiscVat());
                    }
                }
                rcpChange = rcpReceived.subtract(rcpTotalCharge);
                receipt.setCustomer(customer);
                receipt.setPayment(payment);
                receipt.setType(getReceiptType2(customer));
                String receiptType = "";
                if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_FORMAT_WH_ONLY)) {// by
                    // NSD
                    // 24-04-2017
                    receipt.setFlgHeader(FLG_HEADER_3);
                }
                if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1)) {
                    if (RECEIPT_TYPE_FULL.equals(receipt.getType())) {
                        receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
                    } else {
                        receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
                    }
                } else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)) {
                    receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY;
                } else {
                    receiptType = RECEIPT_NO_FLAG_WH_ONLY;
                }

                if (!"".equals(cust.getSouceType()) || cust.getSouceType() != null) {
                    receipt.setNo(cust.getEpNameCode());
                }else {
                    receipt.setNo(getReceiptNo(posNo, receiptType, receiptDttm, RECEIPT_HEADER_EPO));
                }
                receipt.setName(cust.getCustName());
                receipt.setAccountName(cust.getCustName());
                receipt.setAccountNo(cust.getCustNo());

                // by NSD 24-04-2017
                Date dtFrom = receiptDttm;
                Calendar c = Calendar.getInstance();
                c.setTime(dtFrom);
                c.add(Calendar.DATE, 1);
                Date dtTo = c.getTime();
                // List<Receipt> rcptList =
                // receiptRepo.findByTypeAndBranchAreaAndFlgHeaderAndDocDttmOrderByNoDesc(receipt.getType(),branchArea,
                // receipt.getFlgHeader(), dtFrom, dtTo);
                List<Receipt> rcptList = receiptRepo.findBackDateReceiptList(receipt.getType(), branchArea,
                        receipt.getFlgHeader(), new java.sql.Date(dtFrom.getTime()), new java.sql.Date(dtTo.getTime()));
                BeanComparator fieldComparator = new BeanComparator("no");
                Collections.sort(rcptList, fieldComparator);

                if ("Billing".equals(cust.getSouceType())) {
                    receipt.setDocDttm(cust.getPaymentDate());
                } else {
                    if (rcptList != null && rcptList.size() > 0) {
                        receiptDttm = rcptList.get(rcptList.size() - 1).getDocDttm();
                        receipt.setDocDttm(receiptDttm);
                    }
                }
                String subNo = "";

                if (accountSubNoMap.size() > 1) {
                    subNo = String.valueOf(accountSubNoMap.size());
                } else if (accountSubNoMap.size() == 1) {
                    Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
                    subNo = entry.getValue();
                } else {
                    subNo = cust.getCustSubNo();
                }
                // receipt.setAccountSubNo(cust.getCustSubNo());
                receipt.setAccountSubNo(subNo);
                receipt.setAccountType(cust.getCustType());
                receipt.setTaxNo(cust.getTaxNo());
                // receipt.setAccountBranch(customer.getBranch());
                receipt.setAccountBranch(cust.getCustBranch());
                receipt.setAddrLine1(customer.getReceiptAddr());
                receipt.setAddrLine2(customer.getInvoiceAddr());

                // receipt.setPaymentCase(paymentDTO.getPaymentCase());
                receipt.setBranchCode(branchCode);
                receipt.setBranchArea(branchArea);
                receipt.setBranchName(branchName);
                receipt.setAmount(rcpAmount);
                receipt.setDiscount(rcpDiscount);
                receipt.setCharge(rcpCharge);
                receipt.setVatRate(VAT_RATE);
                receipt.setVat(rcpVat);
                receipt.setTotalCharge(rcpTotalCharge);
                receipt.setDeduction(rcpDeduction);
                receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
                receipt.setBalanceDue(rcpBalanceDue);
                receipt.setReceived(rcpReceived);
                receipt.setAfterSaleDiscVat(rcpAfterSaleDiscVat);// by NSD
                // 05-04-2017
                receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
                receipt.setAdvanced(null);
                receipt.setPromotion(null);
                receipt.setRemark(cust.getRemark());
                receipt.setReprint(0L);

                // sumAfterSaleDisc =
                // sumAfterSaleDisc.add(rcpAfterSaleDiscount!=null?rcpAfterSaleDiscount:BigDecimal.ZERO).add(rcpAfterSaleDiscVat!=null?rcpAfterSaleDiscVat:BigDecimal.ZERO);

                if (rcpReceived.doubleValue() < balanceDue.doubleValue()) {
                    receipt.setAttributes("S");
                } else {
                    receipt.setAttributes("F");
                }
                
                if("TOPUP".equals(cust.getSouceType()) || "OTHER".equals(cust.getSouceType())) {                	
                	receipt.setAttributes(receipt.getAttributes().replaceAll("C", "") + "C");
                }
                receipt.setBillingGroup(cust.getBillGroup());
                receipt.setBillingGroupFull(cust.getBillGroup());
                receipt.setBillingServiceName(cust.getInvoiceDisplay());// by
                // NSD
                // 24-03-2017
                receipt.setLanguage(language);
                for (Method method : payment.getMethods()) {
                    method.setRecieptId(receiptID);
                }
                receipts.add(receipt);
                amount = amount.add(rcpAmount);
                discount = discount.add(rcpDiscount);
                charge = charge.add(rcpCharge);
                vat = vat.add(rcpVat);
                totalCharge = totalCharge.add(rcpTotalCharge);
                balanceDue = balanceDue.add(rcpBalanceDue);
                afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
                deduction = deduction.add(rcpWt);
            } else {
                BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
                        rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpBalanceDue = BigDecimal.ZERO,
                        rcpAfterSaleDiscount = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
                        rcpAfterSaleDiscVat = BigDecimal.ZERO;
                for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
                    // Fix by EPIS8 30/12/2016 issue no 59, 131
                    // rcpAmount = rcpAmount.add(inv.getAmount());
                    // rcpDiscount = rcpDiscount.add(inv.getDiscount());
                    // rcpCharge = rcpCharge.add(inv.getCharge());
                    // rcpVat = rcpVat.add(inv.getVat());
                    // rcpTotalCharge =
                    // rcpTotalCharge.add(inv.getTotalCharge());
                    // rcpAfterSaleDiscount =
                    // rcpAfterSaleDiscount.add(inv.getAfterSaleDiscount());
                    // rcpBalanceDue = rcpBalanceDue.add(inv.getBalanceDue());
                    // rcpDeduction = rcpDeduction.add(inv.getDeduction());
                    rcpAmount = inv.getAmount();
                    rcpDiscount = inv.getDiscount();
                    rcpCharge = inv.getCharge();
                    rcpVat = inv.getVat();
                    rcpTotalCharge = inv.getTotalCharge();
                    rcpAfterSaleDiscount = inv.getAfterSaleDiscount();
                    rcpAfterSaleDiscVat = inv.getAfterSaleDiscVat();
                    rcpBalanceDue = inv.getBalanceDue();
                    rcpDeduction = inv.getDeduction();
                    // End Fix by EPIS8 30/12/2016 issue no 59, 131
                    receipt = receiptRepo.save(new Receipt());
                    receiptID = receipt.getId();
                    receipt.setUpdateDttm(date);
                    receipt.setUpdateUser(userName);
                    receipt.setDocDttm(receiptDttm);
                    receipt.setFlgHeader(FLG_HEADER_2);
                    invoice = invoiceRepo.save(new Invoice());
                    invoice.setUpdateDttm(date);
                    invoice.setUpdateUser(userName);
                    addTransactionsIntoService(paids, payment, invoice, date, userName);
                    invoice.setReceiptId(receipt.getId());
                    invoice.setCustomer(customer);
                    invoice.setPayment(payment);
                    invoice.setNo(inv.getNo());
                    invoice.setKenan(inv.getKenan());
                    invoice.setCurrencyCode(inv.getCurrencyCode());
                    invoice.setIssueDt(inv.getIssueDt());
                    invoice.setDueDt(inv.getDueDt());
                    invoice.setBillCycle(inv.getBillCycle());
                    invoice.setAmount(inv.getAmount());
                    invoice.setDiscount(inv.getDiscount());
                    invoice.setCharge(inv.getCharge());
      
                    if (!StringUtils.equals(inv.getStatus(), INVOICE_OTHER_PAYMENT)
                            || inv.getVat().compareTo(new BigDecimal(0)) == 1) {// by
                        // NSD
                        // 02-04-2017
                        invoice.setVatRate(VAT_RATE);
                        invoice.setVat(inv.getVat());
                        if (!StringUtils.equals(inv.getTaxTypeCode(), TAX_CODE_NONVAT)
                                || inv.getVat().compareTo(new BigDecimal(0)) == 1) {
                            receipt.setFlgHeader(FLG_HEADER_1);
                        }
                    } else {
                        invoice.setVat(null);
                        invoice.setVatRate(null);
                    }
                    invoice.setDiscApprUser(inv.getDiscApprUser());
                    invoice.setTotalCharge(inv.getReceived());
                    invoice.setDeduction(inv.getDeduction());
                    invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
                    invoice.setAfterSaleDiscVat(inv.getAfterSaleDiscVat());// by
                    // NSD
                    // 03-05-2017
                    invoice.setBalanceDue(inv.getBalanceDue());
                    invoice.setReceived(inv.getReceived());
                    invoice.setChange(inv.getChange());
                    invoice.setAdvanced(inv.getAdvanced());
                    invoice.setStatus(inv.getStatus());
                    invoice.setDiscountType(inv.getDiscountType());// by NSD
                    // 04-04
                    invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0
                            ? (inv.getTotalCharge().compareTo(invoice.getReceived()) != 0 ? "P" : "F") : "P");
                    // by NSD 23-03-2017
                    Map<String, String> accountSubNoMap = new HashMap<String, String>();
                    String subNoInv = "";
                    if (!CollectionUtils.isEmpty(inv.getSubNoList())) {
                        for (String subno : inv.getSubNoList()) {
                            accountSubNoMap.put(subno, subno);
                            subNoInv = subno + "|";
                        }
                    }

                    if (!CollectionUtils.isEmpty(inv.getInvoiceVatDetails())) {
                        for (SettlePaymentDTO.InvoiceVatDetail invDtl : inv.getInvoiceVatDetails()) {
                            invoiceVatDetail = invoiceVatRepository.save(new InvoiceVatDetail());
                            invoiceVatDetail.setUpdateDttm(date);
                            invoiceVatDetail.setUpdateUser(userName);
                            invoice.getInvoiceVatDetails().add(invoiceVatDetail);
                            invoiceVatDetail.setInvoiceId(invoice.getId());
                            invoiceVatDetail.setInvoiceNo(invDtl.getInvoiceNo());
                            invoiceVatDetail.setAccountNo(invDtl.getAccountNo());
                            invoiceVatDetail.setAmount(invDtl.getAmount());
                            invoiceVatDetail.setVat(invDtl.getVat());
                            invoiceVatDetail.setTaxTypeCode(invDtl.getTaxTypeCode());
                        }
                    }

                    invoice.setSubNo(subNoInv);

                    receipt.getInvoices().add(invoice);
                    receipt.setCustomer(customer);
                    receipt.setPayment(payment);
                    receipt.setType(getReceiptType2(customer));
                    String receiptType = "";
                    if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_FORMAT_WH_ONLY)) {// by
                        // NSD
                        // 24-04-2017
                        receipt.setFlgHeader(FLG_HEADER_3);
                    }
                    if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1)) {
                        if (RECEIPT_TYPE_FULL.equals(receipt.getType())) {
                            receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
                        } else {
                            receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
                        }
                    } else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)) {
                        receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY;
                    } else {
                        receiptType = RECEIPT_NO_FLAG_WH_ONLY;
                    }
                    receipt.setNo(getReceiptNo(posNo, receiptType, receiptDttm, RECEIPT_HEADER_EPO));

                    // by NSD 24-04-2017
                    Date dtFrom = receiptDttm;
                    Calendar c = Calendar.getInstance();
                    c.setTime(dtFrom);
                    c.add(Calendar.DATE, 1);
                    Date dtTo = c.getTime();
                    List<Receipt> rcptList = receiptRepo.findByTypeAndBranchAreaAndFlgHeaderAndDocDttmOrderByNoDesc(
                            receipt.getType(), branchArea, receipt.getFlgHeader(), dtFrom, dtTo);
                    BeanComparator fieldComparator = new BeanComparator("no");
                    Collections.sort(rcptList, fieldComparator);
                    if (rcptList != null && rcptList.size() > 0) {
                        receiptDttm = rcptList.get(rcptList.size() - 1).getDocDttm();
                        receipt.setDocDttm(receiptDttm);
                    }
                    receipt.setName(cust.getCustName());
                    receipt.setAccountName(cust.getCustName());
                    receipt.setAccountNo(cust.getCustNo());
					/*
					 * Map<String,String> accountSubNoMap = new
					 * HashMap<String,String>();
					 * if(!CollectionUtils.isEmpty(inv.getSubNoList())) {
					 * for(String subno:inv.getSubNoList()){
					 * accountSubNoMap.put(subno,subno); } }
					 */
                    String subNo = "";
                    if (accountSubNoMap.size() > 1) {
                        subNo = String.valueOf(accountSubNoMap.size());
                    } else if (accountSubNoMap.size() == 1) {
                        Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
                        subNo = entry.getValue();
                    } else {
                        // subNo = cust.getCustSubNo();
                    }

                    receipt.setAccountSubNo(subNo);
                    // receipt.setAccountSubNo(cust.getCustSubNo());
                    receipt.setAccountType(cust.getCustType());
                    receipt.setTaxNo(cust.getTaxNo());
                    // receipt.setAccountBranch(customer.getBranch());
                    receipt.setAccountBranch(cust.getCustBranch());
                    receipt.setAddrLine1(cust.getAddress1());
                    receipt.setAddrLine2(cust.getAddress2());
                    // receipt.setPaymentCase(paymentDTO.getPaymentCase()); ser

                    System.out.println("# PART 2 receipt.getId() = " + receipt.getId());
                    System.out.println("# PART 2 invoice.getReceiptId() = " + invoice.getReceiptId());
                    String paymentCase = "";
                    for (SettlePaymentDTO.Invoice inv2 : cust.getInvoices()) {
                        if (receipt.getId() == invoice.getReceiptId()) {
                            if (inv2.getPaymentCase() != null && inv2.getPaymentCase().indexOf("+") > 0) {
                                String paymentCaseArr[] = inv2.getPaymentCase().split("\\+");
                                for (int i = 0; i < paymentCaseArr.length; i++) {
                                    if (paymentCaseArr[paymentCaseArr.length - 1].length() <= 1) { // Remove
                                        // "+"
                                        // last
                                        // index
                                        paymentCase = inv2.getPaymentCase().substring(0,
                                                inv2.getPaymentCase().length() - 3);
                                    } else {
                                        paymentCase = inv2.getPaymentCase();
                                    }
                                }
                                receipt.setPaymentCase(paymentCase);
                            } else {
                                receipt.setPaymentCase(inv2.getPaymentCase());
                            }

                        }
                    }
                    receipt.setBranchCode(branchCode);
                    receipt.setBranchArea(branchArea);
                    receipt.setBranchName(branchName);
                    receipt.setAmount(rcpAmount);
                    receipt.setDiscount(rcpDiscount);
                    receipt.setCharge(rcpCharge);
                    receipt.setVatRate(VAT_RATE);
                    receipt.setVat(rcpVat);
                    receipt.setTotalCharge(rcpTotalCharge);
                    receipt.setDeduction(rcpDeduction);
                    receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
                    receipt.setAfterSaleDiscVat(rcpAfterSaleDiscVat);
                    receipt.setBalanceDue(rcpBalanceDue);
                    receipt.setReceived(invoice.getReceived());
                    receipt.setChange(invoice.getChange());
                    receipt.setAdvanced(null);
                    receipt.setPromotion(null);
                    receipt.setRemark(cust.getRemark());
                    receipt.setReprint(0L);

                    // sumAfterSaleDisc =
                    // sumAfterSaleDisc.add(rcpAfterSaleDiscount!=null?rcpAfterSaleDiscount:BigDecimal.ZERO).add(rcpAfterSaleDiscVat!=null?rcpAfterSaleDiscVat:BigDecimal.ZERO);

                    if (invoice.getReceived().doubleValue() < rcpAmount.doubleValue()) {
                        receipt.setAttributes("P");
                    } else {
                        receipt.setAttributes("F");
                    }

                    receipt.setBillingGroup(cust.getBillGroup());
                    receipt.setBillingGroupFull(cust.getBillGroup());
                    receipt.setBillingServiceName(cust.getInvoiceDisplay());// by
                    // NSD
                    // 24-03-2017
                    receipt.setLanguage(language);
                    for (Method method : payment.getMethods()) {
                        method.setRecieptId(receiptID);
                    }
                    receipts.add(receipt);
                    amount = amount.add(rcpAmount);
                    discount = discount.add(rcpDiscount);
                    charge = charge.add(rcpCharge);
                    vat = vat.add(rcpVat);
                    totalCharge = totalCharge.add(rcpTotalCharge);
                    balanceDue = balanceDue.add(rcpBalanceDue);
                    afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
                    deduction = deduction.add(rcpDeduction);
                }
                
                for (SettlePaymentDTO.Service serviceDTO : cust.getServices()) {
                	
                    Service service = serviceRepo.save(new Service());
                    service.setPaymentId(payment.getId());
                    service.setUpdateDttm(date);
                    service.setReceiptId(receipt.getId());
                    service.setAccountNo(cust.getCustNo());
                    service.setProductCode(serviceDTO.getProductCode());
                    service.setProductName(serviceDTO.getProductName());
                    service.setProductSubCode(serviceDTO.getSubProductCode());
                    service.setProductSubName(serviceDTO.getSubProductCode());
                    service.setServiceCode(serviceDTO.getCode());
                    service.setServiceName(serviceDTO.getServiceName());
                    service.setServiceTypeName(serviceDTO.getServiceTypeName());
                    service.setIncomeType("1");
                    service.setAmount(serviceDTO.getAmount());
			 		service.setCharge(serviceDTO.getCharge());
			 		service.setVat(serviceDTO.getVat());
			 		service.setDiscount(serviceDTO.getDiscount());
			 		service.setVatRate(serviceDTO.getVatRate());
			 		service.setDeduction(serviceDTO.getDeduction());
			 		service.setTotalCharge(serviceDTO.getTotalCharge());
                    receipt.getServices().add(service);
                	
                }
                
            }
        }
        BigDecimal totalAdvanced = BigDecimal.ZERO;
        for (final SettlePaymentDTO.Advanced adv : paymentDTO.getAdvances()) {
            for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
                customer = find(customers, new Predicate<Customer>() {
                    @Override
                    public boolean apply(Customer customer) {
                        return trimToEmpty(customer.getNo()).equals(adv.getCustNo());
                    }
                });
                paids.add(new Paid(adv.getTotalCharge(), adv.getPayType()));
                receipt = receiptRepo.save(new Receipt());
                receiptID = receipt.getId();
                receipt.setUpdateDttm(date);
                receipt.setUpdateUser(userName);
                receipt.setDocDttm(receiptDttm);
                receipts.add(receipt);
                invoice = invoiceRepo.save(new Invoice());
                invoice.setUpdateDttm(date);
                invoice.setUpdateUser(userName);
                Service service = serviceRepo.save(new Service());
                service.setUpdateDttm(date);
                service.setReceiptId(receipt.getId());
                service.setInvoiceId(invoice.getId());
                service.setAccountNo("1234567890");
                service.setProductCode("19201");
                service.setProductName("prod name");
                service.setProductSubCode("sub code");
                service.setProductSubName("sub name");
                service.setIncomeType("1");
                
                receipt.setCurrencyCode(adv.getType());
                boolean isMultiply = false;
                BigDecimal received = BigDecimal.ZERO; 
                if(adv.getType() == null && cust.getCurrencyRate()!=null){
                	received = adv.getReceived().multiply(cust.getCurrencyRate());
                	isMultiply = true;
                }
                else
                	received = adv.getReceived();
                
                //service.setAmount(adv.getReceived());
                service.setAmount(received);
                receipt.getServices().add(service);
                invoice.getServices().add(service);
                invoice.setNo(AppConstants.ADVANCE_PAYMENT);
                
                Method method = methodRepo.save(new Method()); method.setUpdateDttm(date); method.setUpdateUser(userName);
                method.setCode(adv.getPayCode());
                method.setName(adv.getPayName());
                
                
                method.setAmount(received);
                //method.setAmount(adv.getReceived());
                method.setPaymentId(payment.getId());
                method.setRecieptId(receiptID);
                payment.getMethods().add(method);
                
                addTransactionsIntoService(paids, payment, invoice, date, userName);
                invoice.setReceiptId(receipt.getId());
                invoice.setCustomer(customer);
                invoice.setPayment(payment);
                invoice.setKenan(adv.getKenan());
                invoice.setCurrencyCode(adv.getCurrencyCode());
                invoice.setIssueDt(null);
                invoice.setDueDt(null);
                if (!StringUtils.isEmpty(adv.getBillCycle())) {
                    invoice.setBillCycle(adv.getBillCycle());
                } else {
                    invoice.setBillCycle("เริ่มตั้งแต่เดือน "
                            + FastDateFormat.getInstance("MM/yyyy", new Locale("th", "TH")).format(date));
                }
                /*
                invoice.setAmount(adv.getAmount());
                invoice.setDiscount(adv.getDiscount());
                invoice.setCharge(adv.getCharge());
                */
                invoice.setAmount((isMultiply && adv.getAmount()!=null)?(adv.getAmount().multiply(cust.getCurrencyRate())):adv.getAmount());
                invoice.setDiscount((isMultiply && adv.getDiscount()!=null)?(adv.getDiscount().multiply(cust.getCurrencyRate())):adv.getDiscount());
                invoice.setCharge((isMultiply && adv.getCharge()!=null)?(adv.getCharge().multiply(cust.getCurrencyRate())):adv.getCharge());
                
                invoice.setVatRate(VAT_RATE);
                invoice.setVat((isMultiply && adv.getVat()!=null)?(adv.getVat().multiply(cust.getCurrencyRate())):adv.getVat());
                //invoice.setTotalCharge(adv.getReceived());
                invoice.setTotalCharge(received);
                invoice.setDeduction((isMultiply && adv.getDeduction()!=null)?(adv.getDeduction().multiply(cust.getCurrencyRate())):adv.getDeduction());
                invoice.setAfterSaleDiscount(BigDecimal.ZERO);
                /*
                invoice.setBalanceDue(adv.getBalanceDue());
                invoice.setReceived(adv.getReceived());
                invoice.setChange(adv.getChange());
                invoice.setAdvanced(adv.getAdvanced());
                */
                invoice.setBalanceDue((isMultiply && adv.getBalanceDue()!=null)?(adv.getBalanceDue().multiply(cust.getCurrencyRate())):adv.getBalanceDue());
                invoice.setReceived((isMultiply && adv.getReceived()!=null)?(adv.getReceived().multiply(cust.getCurrencyRate())):adv.getReceived());
                invoice.setChange((isMultiply && adv.getChange()!=null)?(adv.getChange().multiply(cust.getCurrencyRate())):adv.getChange());
                invoice.setAdvanced((isMultiply && adv.getAdvanced()!=null)?(adv.getAdvanced().multiply(cust.getCurrencyRate())):adv.getAdvanced());
                invoice.setStatus("SUCCESS");
                invoice.setAttributes("A");
                if("Billing".equals(paymentDTO.getCustomers().get(0).getSouceType())){
                	invoice.setUpdateUser(paymentDTO.getUpdaetBy());
                	invoice.setUpdateDttm(paymentDTO.getUpdateDate());
                }
                receipt.getInvoices().add(invoice);
                receipt.setCustomer(customer);
                receipt.setPayment(payment);
                receipt.setType(getReceiptType2(customer));
                receipt.setFlgHeader(FLG_HEADER_1);
				/*
				 * String receiptType = "";
				 * if(StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1))
				 * { if(RECEIPT_TYPE_FULL.equals(receipt.getType())){
				 * receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE; }else{
				 * receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE; } }else
				 * if(StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)){
				 * receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY; }else{
				 * receiptType = RECEIPT_NO_FLAG_WH_ONLY; }
				 *//*
					 * if(cust.getReceiptFormat().toUpperCase().equals(
					 * RECEIPT_FORMAT_WH_ONLY)){//by NSD 24-04-2017
					 * receipt.setFlgHeader(FLG_HEADER_3); }
					 *//*
					 * if(StringUtils.equals(receipt.getFlgHeader(),
					 * FLG_HEADER_1)) {
					 * if(RECEIPT_TYPE_FULL.equals(receipt.getType())){
					 * receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE; }else{
					 * receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE; }
					 * }else if(StringUtils.equals(receipt.getFlgHeader(),
					 * FLG_HEADER_2)){ receiptType =
					 * RECEIPT_NO_FLAG_RECEIVE_ONLY; }else{ receiptType =
					 * RECEIPT_NO_FLAG_WH_ONLY; }
					 */

                receipt.setNo(getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(receipt.getType())
                                ? RECEIPT_NO_FLAG_WITH_TAX_INVOICE : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, receiptDttm,
                        RECEIPT_HEADER_EPO));

                receipt.setDocDttm(receiptDttm);

                receipt.setName(customer.getName());
                receipt.setAccountName(customer.getName());
                receipt.setAccountNo(customer.getNo());

                String subNo = "";
                Map<String, String> accountSubNoMap = new HashMap<String, String>();
                SubscriptionDTO dto = new SubscriptionDTO();
                // call F05 RetreiveServiceStatus
                RetrieveServiceStatusRequest request = new RetrieveServiceStatusRequest();
                request.setBillingAccountNo(customer.getNo());
                try {
                    request.setTransactionLog(_f05RetrieveServiceStatusService.buildTransactionLogCBO());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RetrieveServiceStatusResponse response = _f05RetrieveServiceStatusService.callInterface(request);

                if (_f05RetrieveServiceStatusService.isCalledSuccesful("0", response)) {

                    for (ServiceStatusBO serviceStatus : response.getServiceStatusList()) {
                        if (serviceStatus.getStatusName().equals("Active")) {
                            subNo = String.valueOf(serviceStatus.getSubscrNo());
                            accountSubNoMap.put(subNo, subNo);
                        }

                        // dto.addData(new
                        // Subscription(serviceStatus.getStatusReasonName(),
                        // serviceStatus.getSubscrNo(),
                        // serviceStatus.getStatusName()));
                    }
                }
                if (accountSubNoMap.size() > 1) {
                    subNo = String.valueOf(accountSubNoMap.size());
                } else if (accountSubNoMap.size() == 1) {
                    Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
                    subNo = entry.getValue();
                } else {

                }
                receipt.setAccountSubNo(subNo);
                receipt.setAccountType(adv.getCustType());
                receipt.setTaxNo(customer.getTax());
                // receipt.setAccountBranch(customer.getBranch());

                receipt.setAddrLine1(customer.getReceiptAddr());
                receipt.setAddrLine2(customer.getInvoiceAddr());
                receipt.setPaymentCase(paymentDTO.getPaymentCase());
                receipt.setBranchCode(branchCode);
                receipt.setBranchArea(branchArea);
                receipt.setBranchName(branchName);
                /*
                receipt.setAmount(adv.getAmount());
                receipt.setDiscount(adv.getDiscount());
                receipt.setCharge(adv.getCharge());
                receipt.setVatRate(VAT_RATE);
                receipt.setVat(adv.getVat());
                receipt.setTotalCharge(adv.getTotalCharge());
                receipt.setDeduction(adv.getDeduction());
                */
                receipt.setAmount((isMultiply && adv.getAmount()!=null)?(adv.getAmount().multiply(cust.getCurrencyRate())):adv.getAmount());
                receipt.setDiscount((isMultiply && adv.getDiscount()!=null)?(adv.getDiscount().multiply(cust.getCurrencyRate())):adv.getDiscount());
                receipt.setCharge((isMultiply && adv.getCharge()!=null)?(adv.getCharge().multiply(cust.getCurrencyRate())):adv.getCharge());
                receipt.setVatRate(VAT_RATE);
                receipt.setVat((isMultiply && adv.getVat()!=null)?(adv.getVat().multiply(cust.getCurrencyRate())):adv.getVat());
                receipt.setTotalCharge((isMultiply && adv.getTotalCharge()!=null)?(adv.getTotalCharge().multiply(cust.getCurrencyRate())):adv.getTotalCharge());
                receipt.setDeduction((isMultiply && adv.getDeduction()!=null)?(adv.getDeduction().multiply(cust.getCurrencyRate())):adv.getDeduction());
                
                receipt.setAfterSaleDiscount(BigDecimal.ZERO);
                receipt.setBalanceDue(invoice.getBalanceDue());
                receipt.setReceived(invoice.getReceived());
                receipt.setChange(BigDecimal.ZERO);
                receipt.setAdvanced(adv.getAdvanced());
                receipt.setPromotion(null);
                receipt.setRemark(customer.getRemark());
                receipt.setReprint(0L);
                receipt.setAttributes("A");
                receipt.setBillingGroup(customer.getBillGroup());
                receipt.setBillingGroupFull(customer.getBillGroup());
                receipt.setBillingServiceName(adv.getInvoiceDisplay());
                receipt.setLanguage(language);
                if("Billing".equals(paymentDTO.getCustomers().get(0).getSouceType())){
                	receipt.setUpdateUser(paymentDTO.getUpdaetBy());
                	receipt.setUpdateDttm(paymentDTO.getUpdateDate());
                }
                //totalAdvanced = totalAdvanced.add(adv.getAmount());
                totalAdvanced = totalAdvanced.add(((isMultiply && adv.getAmount()!=null)?(adv.getAmount().multiply(cust.getCurrencyRate())):adv.getAmount()));
            }
        }
        // insert Data into Table RECEIPT_EGP_MAPPING
        String currencyCode = "";
        BigDecimal currencyRate = BigDecimal.ONE;
        for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
            for (Receipt rec : receipts) {
                if (!StringUtils.isBlank(cust.getEgpNo()) && !StringUtils.isBlank(cust.getEgpContract())) {
                    egpMap = new ReceiptEgpMappingEntity();
                    egpMap.setReceiptId(rec.getId());
                    egpMap.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                    egpMap.setCreatedDate(timestamp);
                    egpMap.setBaNo(cust.getCustNo());
                    egpMap.setEgpNo(cust.getEgpNo());
                    egpMap.setEgpContract(cust.getEgpContract());
                    egpMap.setReceiptNo(rec.getNo());
                    egpMaps.add(egpMap);
                    egpMapRepo.save(egpMap);
                }
            }
            receipt.setAccountBranch(cust.getCustBranch());
            currencyCode = cust.getCurrencyCode();
            currencyRate = cust.getCurrencyRate() != null ? cust.getCurrencyRate() : currencyRate;
        }
        payment.setOfficerId(officerId);
        payment.setShopId(EpContextHolder.getContext().getBranchId());
        payment.setPosId(EpContextHolder.getContext().getPosId());
        payment.setCollectionUnit(customer.getCollectionUnit());
        payment.setDate(date);
        payment.setType(paymentDTO.getCustomers().get(0).getSouceType());
        payment.setMethod(paymentDTO.getPaymentCase());
        payment.setAmount(amount);
        payment.setDiscount(discount);
        payment.setCharge(charge);
        payment.setVatRate(VAT_RATE);
        payment.setVat(vat);
        payment.setTotalCharge(totalCharge);
        payment.setDeduction(deduction);
        payment.setAfterSaleDiscount(afterSaleDiscount);
        payment.setBalanceDue(balanceDue);
        payment.setReceived(paymentDTO.getReceiveAmount());
        payment.setChange(paymentDTO.getRemainAmount());
        payment.setAdvanced(totalAdvanced);
        payment.setAttributes("S");
        payment.setCurrencyCode(currencyCode);
        payment.setCurrencyRate(currencyRate);
        return receipts;
    }

    
    
    private void saveLogCorReceipt(Receipt receipt ,String attributes) {
    	ReceiptLog log = new ReceiptLog();
    	log.setReceiptId(receipt.getId());
    	log.setNo(receipt.getNo());
    	log.setName(receipt.getName());
    	log.setType(receipt.getType());
    	log.setDocDttm(receipt.getDocDttm());
    	log.setAddrLine1(receipt.getAddrLine1());
    	log.setRemarkCor(receipt.getRemark());
    	log.setPromotion(receipt.getPromotion());
    	log.setReprint(receipt.getReprint());
    	log.setCancelReason(receipt.getCancelReason());
    	log.setCancelledDttm(receipt.getCancelledDttm());
    	log.setCancelledBy(receipt.getCancelledBy());
    	log.setAttributes(attributes);
    	log.setUpdateDttm(receipt.getUpdateDttm());
    	log.setUpdateSys("EPIS");
    	log.setVersionstamp(1L);
    	log.setRefNo(receipt.getRefNo());
    	log.setCreateBy(EpContextHolder.getContext().getUserName());
    	log.setCreateDate(new Date());
    	receiptLogRepository.save(log);
	}
    
    public void saveLogCorReceiptByReceiptId(Long id ,String attributes) {
    	List<Receipt> receipts = receiptRepo.findById(id);
    	saveLogCorReceipt(receipts.get(0), attributes);
	}

}
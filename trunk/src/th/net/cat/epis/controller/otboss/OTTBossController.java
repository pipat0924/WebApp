package th.net.cat.epis.controller.otboss;

import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.lang.StringUtils.trimToEmpty;
import static th.net.cat.epis.util.AppConstants.ADVANCE_PAYMENT;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_TBOSS;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_WRITEOFF;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CANCELTAXINVOICE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_RECEIPTTAXINVOICE;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.ctc.wstx.util.StringUtil;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.BillingInfo;
import th.net.cat.epis.dto.CancelPaymentOTTBossDTO;
import th.net.cat.epis.dto.CancelPaymentResultDTO;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.CreditLimitTrans;
import th.net.cat.epis.dto.InvoiceOTBossDTO;
import th.net.cat.epis.dto.OTTBossDTO;
import th.net.cat.epis.dto.PaymentHistory;
import th.net.cat.epis.dto.PaymentHistoryDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.entity.CreditLimitTransEntity;
import th.net.cat.epis.entity.DWRevernueProduct;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.InvoiceVatDetail;
import th.net.cat.epis.entity.OTTBossEntity;
import th.net.cat.epis.entity.PaymentSummary;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Session;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.epis.repo.CreditLimitTransRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.OTTBossRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.ReceiptEgpMappingRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.service.UserService;
import th.net.cat.epis.service.bouncecheqeue.BounceChequeService;
import th.net.cat.epis.service.otboss.OTBOssService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;

@Controller
public class OTTBossController {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(OTTBossController.class);
	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	@Resource(name = "viewCrmJdbcTemplate")
	JdbcTemplate viewCrmJdbcTemplate;
	@Autowired
	PaymentService paymentService;
	@Autowired
	OTBOssService otbossservice;
	@Autowired
	ReceiptRepository receiptRepo;
	@Autowired
	UserService userService;
	@Autowired
	OTTBossRepository ottbossrepository;
	@Autowired
	InvoiceRepository invoiceRepo;
	@Autowired
	CreditLimitTransRepository creditLimitTransRepository;
	@Autowired
	InvoiceRepository InvoiceRepository;
	@Autowired
	PaymentRepository paymentreporsitory;
	@Autowired
	ReceiptEgpMappingRepository egpMapRepo;

	@ResponseBody
	@RequestMapping(value = "findProductOTBOSS.json", method = RequestMethod.GET)
	public InvoiceOTBossDTO findProductByInvoiceJSONOTBoss(@RequestParam("accountNo") String account_no,
			@RequestParam("nobil") String billRefNo, @RequestParam("billCycle") String billCycleSap) {

		return otbossservice.findProductByInvoice(account_no, billRefNo, billCycleSap);

	}

	@ResponseBody
	@RequestMapping(value = "searchottbossdetails.json", method = RequestMethod.GET)
	public OTTBossDTO searchOtbossDetailsJSON(@Param("account") String account, @Param("source") String source)
			throws Exception {
		OTTBossDTO bossDTO = new OTTBossDTO();


		bossDTO = otbossservice.searchOtbossDetails(account, source);

		return bossDTO;
	}

	@ResponseBody
	@RequestMapping(value = "searchottboss.json", method = RequestMethod.POST)
	public OTTBossDTO searchCustomerProfileJSON(@RequestBody OTTBossEntity input) throws Exception {
		OTTBossDTO bossDTO = new OTTBossDTO();

		String invoice = input.getAr_ref();
		if ("".equals(invoice)) {
			invoice = null;
		}
		bossDTO = otbossservice.seachCusOTBoss(input.getAccount_no(), input.getSource(), invoice);

		return bossDTO;
	}
	
	@ResponseBody
	@RequestMapping(value = "searchInvoiceOTBOSS.json", method = RequestMethod.GET)
	public OTTBossDTO searchInvoice(@RequestParam String invNo , @RequestParam String source) throws Exception {
		OTTBossDTO bossDTO = new OTTBossDTO();

		String invoice = null;

		bossDTO = otbossservice.seachCusOTBoss(invNo, source, invoice);

		return bossDTO;
	}

	@ResponseBody
	@RequestMapping(value = "findPaymentOtbossHistory.json", method = RequestMethod.GET)
	public PaymentHistoryDTO findPaymentHistoryJSON(BillingInfo input) throws SOAPException, IOException,
			UnsupportedOperationException, ParserConfigurationException, SAXException {

		PaymentHistoryDTO dto = new PaymentHistoryDTO();
		
		List<Service> services = new ArrayList<Service>();
		List<Receipt> receipts = receiptRepo.findByAccountNoOrderByUpdateDttmDesc(input.getBillingNo());
		for (Receipt receipt : receipts) {
//			PaymentHistory paymentHistorys = new PaymentHistory();
			for(th.net.cat.epis.entity.Service service : receipt.getServices()) {
				services.add(service);
			}
			
			for (Invoice invoice : receipt.getInvoices()) {

				PaymentHistory paymentHistory = new PaymentHistory();
				paymentHistory.setService(services);
				BigDecimal exchangeRate = new BigDecimal(1);// by NSD 27-04-2017

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
				paymentHistory.setAmountBillSap(invoice.getAmount() != null ? invoice.getAmount() : BigDecimal.ZERO);
				paymentHistory.setUpdatePrintDate(receipt.getUpdateDttm());
				paymentHistory.setReceiptPrintDate(
						(receipt.getDocDttm() != null) ? receipt.getDocDttm() : receipt.getUpdateDttm());
				paymentHistory.setReceiptNo(receipt.getNo());
				paymentHistory.setBillRefNo(invoice.getNo()); 
				paymentHistory.setShopPaymentName(receipt.getBranchName());
				paymentHistory.setPaymentReceiver(receipt.getUpdateUser());
				paymentHistory.setCycleDateFrom(null);
				paymentHistory.setCycleDateTo(null);
				paymentHistory.setBillCycle(invoice.getBillCycle());
				paymentHistory.setForeignExchangeRate(invoice.getRateAr());
				paymentHistory.setCurrencyCode(receipt.getCurrencyCode());
				paymentHistory.setIssueDt(invoice.getIssueDt());
				paymentHistory.setBillAmount(invoice.getDebtAmount() != null ? invoice.getDebtAmount() : BigDecimal.ZERO);
				paymentHistory.setAfterSaleDiscount(
						invoice.getAfterSaleDiscount() != null ? invoice.getAfterSaleDiscount() : BigDecimal.ZERO);
				String maskCC = AppUtil.maskCreditCardFromString(receipt.getPayment().getMethod()==null?"เงินสด":receipt.getPayment().getMethod(), "************####");
				String paymentCase = AppUtil.hideWTNumber(maskCC);
				paymentHistory.setPaymentMethod(paymentCase);
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
					invoice.setVat((paymentHistory.getTransAmount().multiply(invoice.getVatRate()))
							.divide(oneHen.add(invoice.getVatRate()), 2, BigDecimal.ROUND_HALF_UP));
				}
				paymentHistory.setBillAmountVat(
						(invoice.getVat() != null ? invoice.getVat() : BigDecimal.ZERO).subtract(afterSaleDiscVat));
				paymentHistory.setCurrencyCode(
						!StringUtils.equals(invoice.getCurrencyCode(), "12") ? invoice.getCurrencyCode() : "12");
				if (invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF)
						|| invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_TBOSS)) {
					paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก"
							: (receipt.getAttributes().indexOf("C") > -1 ? "หนี้สูญรับคืน" : "ปกติ"));
				} else { // "N" - normal billing
					paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก"
							: (receipt.getAttributes().indexOf("C") > -1 ? "ปกติ" : "หนี้สูญรับคืน"));
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
					paymentHistory1.setBillRefNo(paymentHistory.getBillRefNo()); 
					paymentHistory1.setShopPaymentName(paymentHistory.getShopPaymentName());
					paymentHistory1.setPaymentReceiver(paymentHistory.getPaymentReceiver());
					paymentHistory1.setCycleDateFrom(null);
					paymentHistory1.setCycleDateTo(null);
					paymentHistory1.setBillCycle(paymentHistory.getBillCycle());
					paymentHistory1.setBillAmount(paymentHistory.getBillAmount());
					if (StringUtils.equals(invoice.getDiscountType(), "1")) {
						paymentHistory.setPaymentCategory("ลูกค้ารับภาระภาษีเต็มจำนวน");
						paymentHistory1.setPaymentCategory("ส่วนลดหลังการขาย");
					} else {
						paymentHistory.setPaymentCategory("ลูกค้ารับภาระภาษีบางส่วน");
						paymentHistory1.setPaymentCategory("ส่วนลดหลังการขาย");
					}
					paymentHistory1.setStatus(paymentHistory.getStatus());

					// paymentHistory1.setBillAmount(invoice.getAfterSaleDiscount());
					paymentHistory1.setAfterSaleDiscount(BigDecimal.ZERO);
					paymentHistory1.setTransAmount(invoice.getAfterSaleDiscount().add(
							invoice.getAfterSaleDiscVat() != null ? invoice.getAfterSaleDiscVat() : BigDecimal.ZERO));
					paymentHistory1.setBillAmountVat(
							invoice.getAfterSaleDiscVat() != null ? invoice.getAfterSaleDiscVat() : BigDecimal.ZERO);
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
						paymentHistory1.setCurrencyCode(
								!StringUtils.equals(invoice.getCurrencyCode(), "12") ? invoice.getCurrencyCode()
										: "12");// temporary
												// set
						dto.addData(paymentHistory1);
					}
				}
			}
		}
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "findPaymentTbossHistory.json", method = RequestMethod.GET)
	public PaymentHistoryDTO findPaymentTBOSSHistoryJSON(BillingInfo input) throws SOAPException, IOException,
			UnsupportedOperationException, ParserConfigurationException, SAXException {

		PaymentHistoryDTO dto = new PaymentHistoryDTO();
		List<Receipt> receipts = receiptRepo.findByAccountNoOrderByUpdateDttmDesc(input.getBillingNo());
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				// Offline
				logger.info("[getUpdateDttm]=" + receipt.getUpdateDttm());
				logger.info("[getDocDttm]=" + receipt.getDocDttm());
				logger.info("[getUpdateDttm]=" + AppUtil.formatter_EN.format(receipt.getUpdateDttm()) + " "
						+ AppUtil.formatter_EN_TIME.format(receipt.getUpdateDttm()));
				// logger.info("[getDocDttm]=" +
				// AppUtil.formatter_EN.format(receipt.getDocDttm()) + " "
				// + AppUtil.formatter_EN_TIME.format(receipt.getDocDttm()));

				PaymentHistory paymentHistory = new PaymentHistory();
				BigDecimal exchangeRate = new BigDecimal(1);// by NSD 27-04-2017
				/*
				 * paymentHistory.setUpdatePrintDate(receipt.getUpdateDttm());
				 * paymentHistory.setReceiptPrintDate((receipt.getDocDttm() != null)?
				 * receipt.getDocDttm(): receipt.getUpdateDttm());
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
				String maskCC = AppUtil.maskCreditCardFromString(receipt.getPayment().getMethod(), "************####");
				String paymentCase = AppUtil.hideWTNumber(maskCC);
				// End Fix by PM 20/04/2017
				paymentHistory.setPaymentMethod(paymentCase);
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
				if (invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_WRITEOFF)
						|| invoice.getStatus().equalsIgnoreCase(INVOICE_FROM_TBOSS)) { // WriteOff
																						// type
																						// Invoice
					paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก"
							: (receipt.getAttributes().indexOf("C") > -1 ? "ปกติ" : "ปกติ"));
				} else { // "N" - normal billing
					paymentHistory.setStatus(receipt.getAttributes().indexOf("R") > -1 ? "ยกเลิก"
							: (receipt.getAttributes().indexOf("C") > -1 ? "ปกติ" : "ปกติ"));
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
						paymentHistory1.setCurrencyCode(
								!StringUtils.equals(invoice.getCurrencyCode(), "12") ? invoice.getCurrencyCode()
										: "12");// temporary
												// set
						dto.addData(paymentHistory1);
					}
				}
			}
		}
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "createOTBOSSpayment.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO creatOtbossJson(SettlePaymentDTO paymentDTO) throws Exception {
		List<Receipt> receipts = null;
		BounceChequeService acService = new BounceChequeService();
		String b = "";
		for (SettlePaymentDTO.Customer cus : paymentDTO.getCustomers()) {
			int f=0;
			for(int i=0; i<cus.getInvoices().size(); i++) {
				paymentDTO.getCustomers().get(f).getInvoices().get(i).setBillCycle(acService.fdDate4Find(cus.getInvoices().get(i).getBillCycle()));
			}
			if ("Billing".equals(cus.getSouceType())) {
				receipts = paymentService.createPaymentInvoice(paymentDTO);
				b= "Billing";
			} else {
				receipts = otbossservice.createPaymentOTBOSS(paymentDTO);

			}
			f++;
		}

		try {
			otbossservice.creatPayment(paymentDTO, receipts);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
				 * int dataRow = 0; String creditLimitData = paymentDTO.getCreditLimitData();
				 * if(!StringUtil.isBlank(creditLimitData) &&
				 * paymentService.findBillingGroupByCategory().indexOf(receipt.
				 * getBillingGroup()) != -1) { String[] data = creditLimitData.split("\\|");
				 * for(String d : data) { if(dataRow > 0) { String i = d.substring(0,
				 * d.indexOf("-")); if(invoice.getNo().equals(i)) { CreditLimit creditLimit =
				 * new CreditLimit(); creditLimit.setInvoiceId(invoice.getId());
				 * creditLimit.setServiceNo(d.substring(d.indexOf("-")+1));
				 * creditLimit.setCreditMode(data[0]); creditLimit.setUpdateDttm(new Date());
				 * creditLimit.setUpdateSystem("EPS"); creditLimit.setUpdateUser("CLT");
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
										&& !creditLimitTrans.getArRef().equals("-")))
									payType = "F";
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
										creditLimitTransEntity.setAmountExVat(String.valueOf(inv.getCharge()));
									if (inv.getVat() != null)
										creditLimitTransEntity.setVatAmount(String.valueOf(inv.getVat()));
									if (inv.getReceived() != null)
										creditLimitTransEntity.setAmountIncVat(String.valueOf(inv.getReceived()));
								}
							} else {
								Invoice inv = invoiceRepo.findByReceiptIdAndNo(receipt.getId(), ADVANCE_PAYMENT);

								if (inv != null) {
									if (inv.getCharge() != null)
										creditLimitTransEntity.setAmountExVat(String.valueOf(inv.getCharge()));
									if (inv.getVat() != null)
										creditLimitTransEntity.setVatAmount(String.valueOf(inv.getVat()));
									if (inv.getReceived() != null)
										creditLimitTransEntity.setAmountIncVat(String.valueOf(inv.getReceived()));
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
							creditLimitTransEntity.setReceiptId(String.valueOf(receipt.getId()));
							creditLimitTransEntity.setArRef(creditLimitTrans.getArRef());
							creditLimitTransEntity.setPayType(payType);
							creditLimitTransEntity.setPayDate(payDate);

							if (msisdn.equals("0"))
								msisdn = "";

							creditLimitTransEntity.setMsisdn(msisdn);
							/*
							 * if( (creditLimitTrans.getArRef()!=null &&
							 * !creditLimitTrans.getArRef().equals("-"))) { Invoice inv =
							 * invoiceRepo.findByReceiptIdAndNo(receipt.getId(),
							 * creditLimitTrans.getArRef());
							 * 
							 * if(inv!=null) { if(inv.getCharge()!=null)
							 * creditLimitTransEntity.setAmountExVat(String. valueOf(inv.getCharge()));
							 * if(inv.getVat()!=null) creditLimitTransEntity.setVatAmount(String.
							 * valueOf(inv.getVat())); if(inv.getReceived()!=null)
							 * creditLimitTransEntity.setAmountIncVat(String. valueOf(inv.getReceived())); }
							 * }else{ creditLimitTransEntity.setVatAmount(String. valueOf(new
							 * BigDecimal(creditLimitTrans.getVatAmount()). setScale(2,
							 * RoundingMode.HALF_UP))); creditLimitTransEntity.setAmountExVat(String.
							 * valueOf(new BigDecimal(creditLimitTrans.getAmountExVat()). setScale(2,
							 * RoundingMode.HALF_UP))); creditLimitTransEntity.setAmountIncVat(received);
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
		if ("Billing".equals(b)) {
			paymentService.callF4CreatePayment(receipts);
		} 
	
		// Case use coupon will update data to insale
		AppUtil.updateCoupon(paymentDTO.getMethods());

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "/invNoOTTBossList.json", method = RequestMethod.GET)
	public List<OTTBossEntity> findInvOTTBossList(@RequestParam(value = "invNo") String invNo)
			throws java.lang.Exception {
		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();

		if (invNo == "") {
			invNo = null;
		}
		List<OTTBossEntity> listNew = new ArrayList<OTTBossEntity>();
		Page<OTTBossEntity> result = ottbossrepository.findByInvoiceOTTBoss(invNo, updateUser, p);
		List<OTTBossEntity> list = result.getContent();
		for (OTTBossEntity ott : list) {

			listNew.add(ott);
		}
		return listNew;
	}

	@ResponseBody
	@RequestMapping(value = "/cancelPaymentOTTBoss.json", method = RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentProductJSON(CancelPaymentOTTBossDTO cancelPaymentDTO) throws Exception {
		Map<Long, CancelPaymentOTTBossDTO.Receipt> receiptMap = newHashMap();
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		for (CancelPaymentOTTBossDTO.Receipt receipt : cancelPaymentDTO.getReceipts()) {
			receiptMap.put(receipt.getId(), receipt);
		}
		String source = Source(cancelPaymentDTO.getReceipts().get(0).getSource());
		Iterable<Receipt> receipts = receiptRepo.findAll(receiptMap.keySet());
		List<Receipt> receiptList = new ArrayList<Receipt>();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String userLogin = EpContextHolder.getContext().getUserName();
		Pageable p = null;
		String accountNo = null;
		for (Receipt receipt : receipts) {
			accountNo = receipt.getAccountNo();
			Receipt cloneReceipt = new Receipt();
			BeanUtils.copyProperties(receipt, cloneReceipt);
			receiptList.add(cloneReceipt);
			receipt.setAttributes("R");
			receipt.setCancelledDttm(new Date());
			receiptRepo.save(receipt);
			for (Invoice invoice : receipt.getInvoices()) {
				Page<OTTBossEntity> result = ottbossrepository.findByARREFAndAccountNo(invoice.getNo(), accountNo,
						invoice.getSource(), p);
				List<DWRevernueProduct> listdwRevernueProduct = new ArrayList<DWRevernueProduct>();
				listdwRevernueProduct = otbossservice.seachPayment(receipt.getPayment().getId());
				List<OTTBossEntity> listOTTBoss = new ArrayList<OTTBossEntity>();
				for (OTTBossEntity ot : result) {
					listOTTBoss.add(ot);
				}
				if (listOTTBoss != null) {
					for (OTTBossEntity oldResult : listOTTBoss) {
						if (oldResult != null) {
							for (DWRevernueProduct objDW : listdwRevernueProduct) {
								if (oldResult.getProduct_code() != null && objDW.getProductCode() != null) {
									if (oldResult.getProduct_code().equals(objDW.getProductCode())) {
										BigDecimal totalcharge = objDW.getTotalAmount();
										oldResult.setRecord_status("CANCLE");
										oldResult.setUpdate_by(userLogin);
										oldResult.setTotal_amonut_paid(totalcharge);
										oldResult.setUpdate_date(timestamp);
										ottbossrepository.save(oldResult);

										// reword inv ottboss and tboss
										OTTBossEntity outtboss = new OTTBossEntity();
										BeanUtils.copyProperties(oldResult, outtboss);
										
										BigDecimal totalTBoss = oldResult.getTotal_amount_ar();
										BigDecimal balancedue = oldResult.getBalancedue();
										BigDecimal amounttotalTboss = totalTBoss.add(totalcharge);
										BigDecimal amountbalancedue = balancedue.add(totalcharge);
										BigDecimal amount = objDW.getAmount().add(oldResult.getAmount_ar());
										BigDecimal vat = objDW.getVat().add(oldResult.getVat_amount_ar());
										
										outtboss.setAmount_ar(amount);
										outtboss.setVat_amount_ar(vat);
										outtboss.setId(0);
										outtboss.setRecord_status("A");
										outtboss.setTotal_amount_ar(amounttotalTboss);
										outtboss.setBalancedue(amountbalancedue);
										outtboss.setUpdate_by(userLogin);
										outtboss.setUpdate_date(timestamp);
										ottbossrepository.save(outtboss);
										
									}
								}
							}
						}
					}
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
						paymentSummary.setBalance(paymentSummary.getBalance().subtract(transaction.getAmount()));
					}
				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) - 1);
			paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		userService.saveSession(session);

		return cancelPaymentResultDTO;
	}

	public String Source(String souece) {
		String soueceVal = "";
		if (souece.equals("1")) {
			soueceVal = "TBOSS";
		} else if (souece.equals("2")) {
			soueceVal = "OTBOSS";
		} else {
			return null;
		}
		return soueceVal;
	}

}

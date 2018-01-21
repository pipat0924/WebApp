package th.net.cat.epis.controller.manual;

import static th.net.cat.epis.util.AppConstants.ADVANCE_PAYMENT;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_RECEIPTTAXINVOICE;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.repo.BillProfileRepository;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.CreditLimitTrans;
import th.net.cat.epis.dto.DeductionManualDTO;
import th.net.cat.epis.dto.ManualEpisDTO;
import th.net.cat.epis.dto.OTTBossDTO;
import th.net.cat.epis.dto.PayManualSetterDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.dto.SettlePaymentDTO.Customer;
import th.net.cat.epis.dto.SettlePaymentDTO.DeductTax;
import th.net.cat.epis.dto.SettlePaymentDTO.Method;
import th.net.cat.epis.entity.CreditLimitTransEntity;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.ManualEntity;
import th.net.cat.epis.entity.MapGLServiceTpye;
import th.net.cat.epis.entity.MethodChequeManual;
import th.net.cat.epis.entity.MethodCreditCardManual;
import th.net.cat.epis.entity.PayInvoiceManualEntity;
import th.net.cat.epis.entity.PaymentSummary;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Session;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.epis.entity.TrsMethodManualEntity;
import th.net.cat.epis.repo.ChequeManualRepository;
import th.net.cat.epis.repo.ChequeRepository;
import th.net.cat.epis.repo.CreditCardManualRepository;
import th.net.cat.epis.repo.CreditCardRepository;
import th.net.cat.epis.repo.CreditLimitTransRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.ManualRepository;
import th.net.cat.epis.repo.MapGLServiceTypeRepository;
import th.net.cat.epis.repo.MasterDataRepository;
import th.net.cat.epis.repo.OTTBossRepository;
import th.net.cat.epis.repo.PayInvoiceManualRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.TrsMethodManualRepository;
import th.net.cat.epis.service.DWService;
import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.service.UserService;
import th.net.cat.epis.service.bouncecheqeue.BounceChequeService;
import th.net.cat.epis.service.manual.ManualService;
import th.net.cat.epis.service.otboss.OTBOssService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderRequest;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderResponse;

@Controller
public class ManualController {
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
	ManualService manualservice;
	@Autowired
	ManualRepository manualrepo;
	@Autowired
	MasterDataRepository masterDataRepository;
	@Autowired
	BillProfileRepository billprofilerepo;
	@Autowired
	th.net.cat.epis.ws.service.ESBWS_F01RetrieveInvoiceHeaderService _f01RetrieveInvoiceHeaderService;

	@Autowired
	TrsMethodManualRepository trsMethodManualRepository;

	@Autowired
	PayInvoiceManualRepository payInvoiceMaualRepo;

	@Autowired
	MapGLServiceTypeRepository mapGLServiceTypeRepo;

	@Autowired
	ChequeRepository chequeRepository;
	@Autowired
	CreditCardRepository creditCardRepository;

	@Autowired
	BounceChequeService bounceChequeService;
	@Autowired
	ChequeManualRepository chequeManualRepository;
	@Autowired
	CreditCardManualRepository creditCardManualRepository;

	@Autowired
	DWService dwService;

	@ResponseBody
	@RequestMapping(value = "insertDataManual.json", method = RequestMethod.POST)
	public ManualEpisDTO insertManualJSON(SettlePaymentDTO paymentDTO) throws Exception {
		ManualEpisDTO manualEpisDTO = new ManualEpisDTO();
		if (paymentDTO != null) {
			manualEpisDTO = manualservice.insertDataManual(paymentDTO);
		}
		return manualEpisDTO;
	}

	@ResponseBody
	@RequestMapping(value = "searchInvoiceReceipt.json", method = RequestMethod.GET)
	public List<ManualEntity> searchInvoiceReceipt(@RequestParam("invNo") String invNo,
			@RequestParam("receipt") String receipt, @RequestParam("status") String status,
			@RequestParam("statusSAP") String statusSAP) {
		List<ManualEntity> list = new ArrayList<ManualEntity>();
		List<ManualEntity> listresult = new ArrayList<ManualEntity>();
		List<ManualEntity> resutl = new ArrayList<ManualEntity>();
		List<String> listStatuts = new ArrayList<>();
		List<String> listStatutsSAP = new ArrayList<>();
		if ("".equals(invNo)) {
			invNo = null;
		}
		if ("".equals(receipt)) {
			receipt = null;
		}

		if (invNo == null && receipt == null) {

		}
		if ("-1".equals(status)) {
			listStatuts.add("C");
			listStatuts.add("N");
		} else {
			listStatuts.add(status);
		}
		if ("-1".equals(statusSAP)) {
			listStatutsSAP.add("C");
			listStatutsSAP.add("N");
		} else {
			listStatutsSAP.add(statusSAP);
		}

		if (invNo == null && receipt == null) {
			resutl = manualrepo.findfindByRecordStatusAndClearing(listStatuts, listStatutsSAP);
		} else if (invNo != null && receipt != null) {
			resutl = manualrepo.findByInvoiceNoAndReceipt(invNo, receipt, listStatuts, listStatutsSAP);
		} else {
			resutl = manualrepo.findByInvoiceNoOrReceipt(invNo, receipt, listStatuts, listStatutsSAP);
		}

		try {
			list = resutl;

			for (ManualEntity result : list) {
				result.setBranchArea(masterDataRepository.findByKey(result.getBranchArea()).get(0).getValue());
				listresult.add(result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listresult;
	}

	@ResponseBody
	@RequestMapping(value = "createpaymentManual.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO createpaymentManual(SettlePaymentDTO paymentDTO) throws Exception {
		List<Receipt> receipts = null;
		ManualEpisDTO episDTO = new ManualEpisDTO();

		if (paymentDTO != null) {
			episDTO = manualservice.insertDataManual(paymentDTO);
		}
		for (SettlePaymentDTO.Customer cus : paymentDTO.getCustomers()) {
			cus.getSouceType();

			if ("IBASS".equals(cus.getSouceType()) || "WRITEOFF".equals(cus.getSouceType())) {
				receipts = paymentService.createPaymentInvoice(paymentDTO);

			} else {
				receipts = otbossservice.createPaymentOTBOSS(paymentDTO);

			}
		}

		try {
			otbossservice.creatPayment(paymentDTO, receipts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (receipts.size() > 0) {
			manualservice.updateStatusPayment(episDTO, receipts);
		}

		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		List<SettlePaymentDTO.Advanced> advances = paymentDTO.getAdvances();
		boolean isGenCreditLimit = false;
		List<CreditLimitTrans> creditLimitTransList = null;
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
						paymentSummary.setBalance(paymentSummary.getBalance().add(transaction.getAmount()));
					}
				}
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
		AppUtil.updateCoupon(paymentDTO.getMethods());

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "delectListManual.json", method = RequestMethod.GET)
	public ManualEpisDTO delectManual(@RequestParam("recieptManual") String reciept) {
		ManualEpisDTO dto = new ManualEpisDTO();
		if (reciept == null) {
			dto = null;
		} else {
			manualservice.delectManul(reciept);

		}

		try {
			dto = manualservice.selectByRecieptManual(reciept);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "paymentManualService.json", method = RequestMethod.POST)
	public CreatePaymentResultDTO creatPaymentManualPadding(@RequestBody List<PayManualSetterDTO> manualDTO)
			throws Exception {
		/////////////////////////////
		BigDecimal vatRate = new BigDecimal(masterDataRepository.findByKey(AppConstants.VAT_RATE).get(0).getValue());
		List<Receipt> receipts = null;
		ManualEpisDTO episDTO = new ManualEpisDTO();
		SettlePaymentDTO paymentDTO = new SettlePaymentDTO();
		DeductionManualDTO deduc = new DeductionManualDTO();
		List<PayInvoiceManualEntity> listPayInvoiceManualEntity = new ArrayList<>();
		for (PayManualSetterDTO dto : manualDTO) {
			RetrieveInvoiceHeaderRequest request = new RetrieveInvoiceHeaderRequest();
			request.setBillingAccountNo(dto.getAccountNo());
			request.setTransactionLog(_f01RetrieveInvoiceHeaderService.buildTransactionLogCBO());

			BillProfile profile = new BillProfile();
			profile = billprofilerepo.findByNo(dto.getAccountNo());
			deduc = manualservice.selectDeductionManualID(dto.getManualId());
			Customer ct = new Customer();
			List<Customer> listCt = new ArrayList<Customer>();
			List<DeductTax> listDeduc = new ArrayList<DeductTax>();
			DeductTax deductTax = new DeductTax();
			List<SettlePaymentDTO.Invoice> listInvoice = new ArrayList<SettlePaymentDTO.Invoice>();
			SettlePaymentDTO.Invoice inv = new SettlePaymentDTO.Invoice();
			List<Method> listMethod = new ArrayList<Method>();
			List<SettlePaymentDTO.Service> serviceList = new ArrayList<>();
			List<TrsMethodManualEntity> listTrsMethodManualEntity = new ArrayList<>();
			listPayInvoiceManualEntity = payInvoiceMaualRepo.findByManualID(dto.getManualId());
			listTrsMethodManualEntity = trsMethodManualRepository.findByManualId(dto.getManualId());
			List<MapGLServiceTpye> mapGLList = new ArrayList<>();
			if (listPayInvoiceManualEntity.size() > 0) {
				PayInvoiceManualEntity pay = listPayInvoiceManualEntity.get(0);
				if (!"".equals(pay.getServiceType()) && !"MANUAL".equals(pay.getServiceType())
						&& pay.getServiceType() != null) {
					mapGLList = mapGLServiceTypeRepo.findByServiceCode(pay.getServiceType());
				}
			}
			if (("IBASS".equals(dto.getSource()) || "WRITEOFF".equals(dto.getSource()))
					|| "RECEIVEGATEWAY".equals(dto.getSource())) {
				RetrieveInvoiceHeaderResponse response = _f01RetrieveInvoiceHeaderService.callInterface(request);
				if (profile == null) {
					return null;
				} else {
					try {
						// --------------------- TMPACCOUNT
						ct.setCustNo(dto.getAccountNo());
						ct.setCustType(profile.getType());
						ct.setTaxNo(profile.getTaxRegisterNo());
						ct.setCustName(profile.getCustomerAccountName());
						ct.setCustBranch(profile.getBranchId());
						ct.setCollectionUnit(profile.getCollectionUnit());
						ct.setBillGroup(profile.getBillGroupFull());
						ct.setAcctCatLkp(profile.getAccountCategoryLookup());
						ct.setEpNameCode(dto.getReceiptNoManual());
						ct.setPaymentDate(dto.getPaidDate());
						ct.setRemark(dto.getRemark());
						ct.setInputPayCashBA(dto.getBranchArea());
						ct.setInputPayCashBP(dto.getBranchCode());
						ct.setCatCustomerSegment(profile.getCatCustomerSegment());
						ct.setReceiptFormat("receive_wh");

						if (mapGLList.size() > 0) {
							ct.setSouceType(mapGLList.get(0).getSource()); // source Type
						} else {
							ct.setSouceType(dto.getSource()); // source Type
						}

						listCt.add(ct);
						// ----------------------------------- TMPINVOICE

						BigDecimal chage = dto.getPaidAmount();
						BigDecimal chagetotle = chage.subtract(dto.getVatAmount());
						BigDecimal balanceDue = new BigDecimal(response.getInvoiceHeaderList().get(0).getBalanceDue());

						inv.setNo(dto.getInvoiceNo());
						inv.setKenan(response.getInvoiceHeaderList().get(0).getAccountNo().toString());
						inv.setCurrencyCode(response.getInvoiceHeaderList().get(0).getCurrencyCode().toString());
						if (null != response.getInvoiceHeaderList().get(0).getIssueDate()) {

							inv.setIssueDt(response.getInvoiceHeaderList().get(0).getIssueDate().toGregorianCalendar()
									.getTime());
						}
						if (null != response.getInvoiceHeaderList().get(0).getDueDate()) {

							inv.setDueDt(response.getInvoiceHeaderList().get(0).getDueDate().toGregorianCalendar()
									.getTime());
						}
						inv.setAmount(chagetotle);
						inv.setCharge(chage);
						inv.setDiscount(new BigDecimal(0));
						inv.setVat(dto.getVatAmount());
						inv.setVatRate(vatRate);
						if (deduc.getData().size() > 0) {
							inv.setDeduction(deduc.getData().get(0).getAmount());
						} else {
							inv.setDeduction(new BigDecimal(0));
						}

						inv.setTotalCharge(dto.getPaidAmount());
						inv.setBalanceDue(balanceDue.divide(new BigDecimal(100)));
						inv.setReceived(dto.getPaidAmount());
						inv.setStatus("N");
						inv.setAfterSaleDiscount(new BigDecimal(0));
						inv.setBillCycle(listPayInvoiceManualEntity.get(0).getPeriod());

						if ("RECEIVEGATEWAY".equals(dto.getSource())) {
							String paymentCase = "";
							for (TrsMethodManualEntity trs : listTrsMethodManualEntity) {
								if (trs.getName() != null) {
									if (!"".equals(paymentCase)) {
										paymentCase = paymentCase + " + " + trs.getName();
									} else {
										paymentCase = trs.getName();
									}
								}
							}
							inv.setPaymentCase(paymentCase);
						} else if (("IBASS".equals(dto.getSource()) || "WRITEOFF".equals(dto.getSource()))) {
							inv.setPaymentCase("เงินสด");
						}
						listInvoice.add(inv);

						if (mapGLList.size() > 0) {
							MapGLServiceTpye mapGL = mapGLList.get(0);
							SettlePaymentDTO.Service service = new SettlePaymentDTO.Service();
							service.setGlAccount(dto.getAccountNo());
							service.setCode(mapGL.getServiceCode().toString());
							service.setName(mapGL.getServiceName());
							service.setProductCode(mapGL.getProductCode());
							service.setProductName(mapGL.getProductName());
							service.setSubProductCode(mapGL.getSubProductCode());
							service.setSubProductName(mapGL.getSubProductName());

							service.setAmount(chagetotle);
							service.setCharge(chagetotle);
							service.setVat(dto.getVatAmount());
							service.setDiscount(new BigDecimal(0));
							service.setVatRate(vatRate);
							if (deduc.getData().size() > 0) {
								service.setDeduction(deduc.getData().get(0).getAmount());
							} else {
								service.setDeduction(new BigDecimal(0));
							}
							service.setTotalCharge(dto.getPaidAmount());
							serviceList.add(service);
						}

						// ----------------- TRSDEDUCTION
						if (deduc.getData().size() > 0) {
							deductTax.setType(deduc.getData().get(0).getDeductionType());
							deductTax.setAmount(deduc.getData().get(0).getAmount());
							deductTax.setInvoiceNo(deduc.getData().get(0).getInvoiceNo());
							deductTax.setWithholdingDocNo(deduc.getData().get(0).getDeductionNo());
							listDeduc.add(deductTax);
						}
						// -------------------------- TRSMETHOD
						if ("RECEIVEGATEWAY".equals(dto.getSource())) {

							for (TrsMethodManualEntity trsMethodManualEntity : listTrsMethodManualEntity) {
								if ("CR".equals(trsMethodManualEntity.getCode())) {
									List<MethodCreditCardManual> mcm = creditCardManualRepository
											.findByMethodManualId(trsMethodManualEntity.getMethodManualId());
									for (MethodCreditCardManual m : mcm) {
										Method met = new Method();

										met.setCardNo(m.getNo());
										met.setAmount(m.getAmount());
										met.setBankName(m.getBankIssuer());
										met.setType("CREDITCARD");
										met.setUpdateDttm(m.getUpdateDttm());

										listMethod.add(met);
									}

								} else if ("CH".equals(trsMethodManualEntity.getCode())) {
									List<MethodChequeManual> mcm = chequeManualRepository
											.findByMethodId(trsMethodManualEntity.getMethodManualId());
									for (MethodChequeManual m : mcm) {
										Method met = new Method();
										met.setChequeNo(m.getNo());
										met.setAmount(m.getAmount());
										met.setType("CHEQUE");
										met.setUpdateDttm(m.getUpdateDttm());
										listMethod.add(met);
									}
								} else if ("CC".equals(trsMethodManualEntity.getCode())) {
									Method met = new Method();
									met.setCode("CC");
									met.setName("เงินสด");
									met.setType("CASH");
									met.setAmount(trsMethodManualEntity.getAmount());
									listMethod.add(met);
								}

							}
						} else if (("IBASS".equals(dto.getSource()) || "WRITEOFF".equals(dto.getSource()))) {
							Method met = new Method();
							met.setCode("CC");
							met.setName("เงินสด");
							met.setAmount(dto.getPaidAmount());
							met.setType("MANUAL");
							listMethod.add(met);
						}
						// context
						paymentDTO.setRemark(dto.getRemark());
						if ("RECEIVEGATEWAY".equals(dto.getSource())) {

						} else if ("Billing".equals(dto.getSource())) {
							paymentDTO.setPaymentCase("MANUAL");
						}
						paymentDTO.setUpdaetBy(dto.getCreateBy());
						paymentDTO.setUpdateDate(dto.getCreateDate());

						paymentDTO.setCustomers(listCt);
						paymentDTO.setMethods(listMethod);
						paymentDTO.setDeducts(listDeduc);
						paymentDTO.getCustomers().get(0).setInvoices(listInvoice);

						if (serviceList.size() > 0) {
							paymentDTO.getCustomers().get(0).setServices(serviceList);
						}

						try {
							receipts = paymentService.createPaymentInvoiceManual(paymentDTO);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (receipts.size() > 0) {
							dwService.sendDateWereHose(receipts.get(0).getPayment().getId());
						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			} else {
				OTTBossDTO beandto = new OTTBossDTO();
				beandto = otbossservice.seachCusOTBoss(dto.getAccountNo(), dto.getSource(), dto.getInvoiceNo());

				try {
					// --------------------- TMPACCOUNT
					ct.setCustNo(dto.getAccountNo());
					ct.setCustType(profile.getType());
					ct.setTaxNo(profile.getTaxRegisterNo());
					ct.setCustName(profile.getCustomerAccountName());
					ct.setCustBranch(profile.getBranchId());
					ct.setCollectionUnit(profile.getCollectionUnit());
					ct.setBillGroup(profile.getBillGroupFull());
					ct.setAcctCatLkp(profile.getAccountCategoryLookup());
					ct.setEpNameCode(dto.getReceiptNoManual());
					ct.setSouceType(dto.getSource());
					ct.setPaymentDate(dto.getPaidDate());
					ct.setRemark(dto.getRemark());
					ct.setInputPayCashBA(dto.getBranchArea());
					ct.setInputPayCashBP(dto.getBranchCode());
					ct.setCatCustomerSegment(profile.getCatCustomerSegment());
					ct.setReceiptFormat("receive_wh");
					listCt.add(ct);
					// ----------------------------------- TMPINVOICE

					BigDecimal chage = dto.getPaidAmount();
					BigDecimal chagetotle = chage.subtract(dto.getVatAmount());
					BigDecimal balanceDue = beandto.getData().get(0).getBalancedue();

					inv.setNo(dto.getInvoiceNo());
					inv.setKenan(dto.getAccountNo());
					inv.setCurrencyCode(beandto.getData().get(0).getRevenue_type_code());
					inv.setIssueDt(beandto.getData().get(0).getInvdate());
					inv.setDueDt(beandto.getData().get(0).getDuedate());
					inv.setAmount(chagetotle);
					inv.setCharge(chagetotle);
					inv.setChange(new BigDecimal(0));
					inv.setDiscount(new BigDecimal(0));
					inv.setVat(dto.getVatAmount());
					inv.setVatRate(vatRate);
					if (deduc.getData().size() > 0) {
						inv.setDeduction(deduc.getData().get(0).getAmount());
					} else {
						inv.setDeduction(new BigDecimal(0));
					}

					inv.setTotalCharge(dto.getPaidAmount());
					inv.setBalanceDue(balanceDue);
					inv.setReceived(dto.getPaidAmount());
					inv.setStatus("N");
					inv.setAfterSaleDiscount(new BigDecimal(0));
					inv.setBillCycle(beandto.getData().get(0).getSap_period());
					inv.setSource(beandto.getData().get(0).getSource());
					inv.setPaymentCase("เงินสด");
					listInvoice.add(inv);

					// ----------------- TRSDEDUCTION
					if (deduc.getData().size() > 0) {
						deductTax.setType(deduc.getData().get(0).getDeductionType());
						deductTax.setAmount(deduc.getData().get(0).getAmount());
						deductTax.setInvoiceNo(deduc.getData().get(0).getInvoiceNo());
						deductTax.setWithholdingDocNo(deduc.getData().get(0).getDeductionNo());
						listDeduc.add(deductTax);
					}
					// -------------------------- TRSMETHOD
					Method met = new Method();
					met.setCode("CC");
					met.setName("เงินสด");
					met.setAmount(dto.getPaidAmount());
					met.setType("CASH");
					listMethod.add(met);
					// context
					paymentDTO.setRemark(dto.getRemark());
					paymentDTO.setPaymentCase("เงินสด");
					paymentDTO.setUpdaetBy(dto.getCreateBy());
					paymentDTO.setUpdateDate(dto.getCreateDate());

					paymentDTO.setCustomers(listCt);
					paymentDTO.setMethods(listMethod);
					paymentDTO.setDeducts(listDeduc);
					paymentDTO.getCustomers().get(0).setInvoices(listInvoice);

					try {
						receipts = paymentService.createPaymentInvoiceManual(paymentDTO);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (receipts.size() > 0) {
						dwService.sendOtbossWereHose(receipts.get(0).getPayment().getId());
						otbossservice.creatPayment(paymentDTO, receipts);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		}
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		List<SettlePaymentDTO.Advanced> advances = paymentDTO.getAdvances();
		boolean isGenCreditLimit = false;
		List<CreditLimitTrans> creditLimitTransList = null;
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
						if (paymentSummary != null) {
							if (paymentSummary.getBalance() != null) {
								paymentSummary.setBalance(paymentSummary.getBalance().add(transaction.getAmount()));
							}
						}

					}
				}
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
			if (paymentSummary != null) {
				paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
			}
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			if (method.getType() != null) {
				PaymentSummary paymentSummary = session.getPayType(method.getType());
				if (paymentSummary != null) {
					paymentSummary
							.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
				}
			}
		}

		userService.saveSession(session);
		manualservice.updateStatusManualOffline(manualDTO.get(0).getManualId(), receipts.get(0).getPayment().getId());
		if (listPayInvoiceManualEntity.size() > 0) {

			if (!"N".equals(listPayInvoiceManualEntity.get(0).getClearing())) {

				paymentService.callF4CreatePayment(receipts);
			}

			AppUtil.updateCoupon(paymentDTO.getMethods());
		}

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	@ResponseBody
	public void creatPaymentManualOffline(SettlePaymentDTO paymentDTO) throws Exception {
		ManualEpisDTO manualEpisDTO = new ManualEpisDTO();
		if (paymentDTO != null) {
			manualEpisDTO = manualservice.insertDataManual(paymentDTO);
		}
		// if (manualEpisDTO.getData().size() > 0) {
		// // à¸¢à¹‰à¸²à¸¢à¹„à¸Ÿà¸¥à¹Œ
		// }

	}

	@ResponseBody
	@RequestMapping(value = "creatPaymentManualBatch.json", method = RequestMethod.POST)
	public List<ManualEntity> creatPaymentManualBatch() throws Exception {
		List<ManualEntity> listManual = new ArrayList<>();
		SettlePaymentDTO paymentDTO = new SettlePaymentDTO();
		listManual = manualrepo.findAllManualBySource("RECEIVEGATEWAY");
		for (ManualEntity manual : listManual) {

		}
		return listManual;

	}

	public static void main(String[] args) throws Exception {
		ManualController controller = new ManualController();
		controller.batch();

	}

	public class process extends TimerTask {
		@Override
		public void run() {
			try {
				System.out.println("==========================");
				creatPaymentManualBatch();
				System.out.println("SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void batch() {
		System.out.println("==========================");
		Timer timer = new Timer();

		Calendar today = Calendar.getInstance();

		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 00);
		today.set(Calendar.SECOND, 59);

		timer.schedule(new process(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}

	@RequestMapping(value = "findBussinessArea.json", method = RequestMethod.GET)
	public void findBussinessAreaJSON(@RequestParam(value = "property1") String property1, ModelMap modelMap) {
		modelMap.addAttribute("data", masterDataRepository.findByGroupKeyAndProperty1("BUSINESS_AREA", property1));
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "findBussinessPlace.json", method = RequestMethod.GET)
	public void findBussinessPlaceJSON(ModelMap modelMap) {
		List<Map<String, Object>> a = episJdbcTemplate.queryForList(
				"SELECT DISTINCT PROPERTY_1 as property1  FROM MASTER_DATA m WHERE Group_key = 'BUSINESS_AREA' order by PROPERTY_1 asc");
		modelMap.addAttribute("data", a);
		modelMap.addAttribute("statusCode", "0");
	}

}

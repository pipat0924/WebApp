package th.net.cat.epis.batch.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import th.net.cat.epis.entity.Customer;
import th.net.cat.epis.entity.Deduct;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.Payment;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.epis.repo.CustomerRepository;
import th.net.cat.epis.repo.DeductionRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.ServiceRepository;
import th.net.cat.epis.repo.TransactionRepository;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;

@Component("generatePaymentAndReceiptOffline")
public class GeneratePaymentAndReceiptOffline implements Tasklet {

	private static final Logger logger = Logger.getLogger(GeneratePaymentAndReceiptOffline.class.getName());
	
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@Value("${epis.task.active}") String taskActive;
	@Autowired CustomerRepository customerRepo;
	@Autowired PaymentRepository paymentRepo;
	@Autowired TransactionRepository transactionRepo;
	@Autowired DeductionRepository deductRepo;
	@Autowired ReceiptRepository receiptRepo;
	@Autowired InvoiceRepository invoiceRepo;
	@Autowired ServiceRepository serviceRepo;
	
	final static String USERNAME_OFFILINE = "BATOFF";
	
	@Value("${epis.offline.archive.directory}") String archive;
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		if(taskActive.equals("1")) {
			try {
				Transaction transaction;
				Date date = new Date();
				Payment payment;
				Deduct deduct;
				Customer customer = null;
				Invoice invoice;
				Receipt receipt = null;
				File[] fList = readFileOffline();
				if (fList != null && fList.length > 0) {
					for (File file : fList) {
						BufferedReader br = null;
						try {
							String currentLine;
							br = new BufferedReader(new FileReader(file));
							while ((currentLine = br.readLine()) != null) {
								// [0]:cr_recepitid, [1]:cr_paymentid, [2]:cr_paymentservice, [3]:cr_receiptno, [4]:cr_receiptname, [5]:cr_receitpttype, [6]:cr_accountno,
								// [7]:cr_accountsubno, [8]:cr_accounttype, [9]:cr_accountname, [10]:cr_accountbranch, [11]:cr_taxid, [12]:cr_addressline1, [13]:cr_addressline2,
								// [14]:cr_paymentcase, [15]:cr_branchcode, [16]:cr_brancharea, [17]:cr_branchname, [18]:cr_amount, [19]:cr_discount, [20]:cr_charge, [21]:cr_vatrate,
								// [22]:cr_vat, [23]:cr_totalcharge, [24]:cr_deduction, [25]:cr_aftersalediscount, [26]:cr_balancedue, [27]:cr_received, [28]:cr_advanced, [29]:cr_remark,
								// [30]:cr_promotion, [31]:cr_reprint, [32]:cr_cancelreason, [33]:cr_canceledby, [34]:cr_attributes, [35]:cr_wttaxtype, [36]:cr_wttaxno, [37]:cr_updatedttm,
								// [38]:cr_updateuser, [39]:cp_officerid, [40]:cp_shopid, [41]:cp_posid, [42]:cp_collectionid, [43]:cp_paymentdate, [44]:cp_paymenttype, [45]:cp_paymentcase,
								// [46]:cp_amount, [47]:cp_discount, [48]:cp_charge, [49]:cp_vatrate, [50]:cp_vat, [51]:cp_totalcharge, [52]:cp_deduction, [53]:cp_aftersaldiscount, [54]:cp_balancedue,
								// [55]:cp_received, [56]:cp_change, [57]:cp_advanced, [58]:cp_reduced, [59]:vcp_attributes, [60]:cp_updatedttm, [61]:cp_updateuser

								String[] paymentAndReceiptData = currentLine.split("\\|");

								payment = paymentRepo.save(new Payment());
								payment.setUpdateDttm(date);
								payment.setUpdateUser(USERNAME_OFFILINE);
								receipt = receiptRepo.save(new Receipt());
								receipt.setUpdateDttm(date);
								receipt.setUpdateUser(USERNAME_OFFILINE);
								invoice = invoiceRepo.save(new Invoice());
								invoice.setUpdateDttm(date);
								invoice.setUpdateUser(USERNAME_OFFILINE);

								// TMPINVOICESERVICE
								Service service = serviceRepo.save(new Service());
								service.setUpdateDttm(date);
								service.setReceiptId(receipt.getId());
								service.setInvoiceId(invoice.getId());
								service.setAccountNo(paymentAndReceiptData[6]);
								service.setProductCode("00000");
								service.setProductName("Product-Off");
								service.setProductSubCode("-");
								service.setProductSubName("-");
								service.setIncomeType("1");
								service.setAmount(new BigDecimal("0"));
								receipt.getServices().add(service);
								invoice.getServices().add(service);

								// TRSPAYMENTREF
								transaction = transactionRepo.save(new Transaction());
								transaction.setUpdateDttm(date);
								transaction.setUpdateUser(USERNAME_OFFILINE);
								transaction.setServiceId(service.getId());
								transaction.setTransactionId(AppUtil.generateTransactionID(15));
								transaction.setTrackingDetails("EPIS is offline data.");
								transaction.setTrackingRetry(0);
								transaction.setPaymentDate(DateUtil.convertToDate(paymentAndReceiptData[43]));
								transaction.setPaymentType("CASE");
								transaction.setAmount(new BigDecimal(paymentAndReceiptData[46]));
								transaction.setPayment(payment);

//								List<Customer> episCustomers = customerRepo.findByNo(cust.getCustNo());
//								if(CollectionUtils.isEmpty(episCustomers)) {
//									customer = customerRepo.save(new Customer());	
//								} else {
//									customer = episCustomers.get(0);
//									customerRepo.save(customer);
//								}
//								customer.setUpdateDttm(date); customer.setUpdateUser(userName); customers.add(customer);
//								customer.setPayment(payment);
//								customer.setType(cust.getCustType());
//								customer.setNo(cust.getCustNo());
//								customer.setName(cust.getCustName());
//								customer.setTax(cust.getTaxNo());
//								customer.setBillGroup(cust.getBillGroup());
//								customer.setCollectionId(null);
//								customer.setOutstanding(cust.getOutstanding());
//								customer.setRemark(cust.getRemark());
//								customer.setReceiptAddr(cust.getAddress1());
//								customer.setInvoiceAddr(cust.getAddress2());

								// TMPINVOICE
								invoice.setReceiptId(receipt.getId());
								invoice.setCustomer(customer);
								invoice.setPayment(payment);
								invoice.setNo(paymentAndReceiptData[2]);
								invoice.setKenan(null);
								invoice.setCurrencyCode("TH");
								invoice.setIssueDt(null);
								invoice.setDueDt(null);
								invoice.setBillCycle("เริ่มตั้งแต่เดือน " + FastDateFormat.getInstance("MM/yyyy", new Locale("th", "TH")).format(date));
								invoice.setAmount(new BigDecimal(paymentAndReceiptData[46]));
								invoice.setDiscount(new BigDecimal(paymentAndReceiptData[47]));
								invoice.setCharge(new BigDecimal(paymentAndReceiptData[48]));
								invoice.setVatRate(new BigDecimal(paymentAndReceiptData[49]));
								invoice.setVat(new BigDecimal(paymentAndReceiptData[50]));
								invoice.setTotalCharge(new BigDecimal(paymentAndReceiptData[51]));
								invoice.setDeduction(new BigDecimal(paymentAndReceiptData[52]));
								invoice.setAfterSaleDiscount(BigDecimal.ZERO);
								invoice.setBalanceDue(new BigDecimal(paymentAndReceiptData[54]));
								invoice.setReceived(BigDecimal.ZERO);
								invoice.setChange(BigDecimal.ZERO);
								invoice.setAdvanced(BigDecimal.ZERO);
								invoice.setStatus("SUCCESS");
								invoice.setAttributes("A");

								// CORRECEIPT
								receipt.getInvoices().add(invoice);
								receipt.setCustomer(customer);
								receipt.setPayment(payment);
								receipt.setDocDttm(DateUtil.convertToDate(paymentAndReceiptData[37], DateUtil.STANDARD_DATE_TIME_PATTERN));
								receipt.setType(paymentAndReceiptData[8]);
								receipt.setNo(paymentAndReceiptData[3]);
								receipt.setName(paymentAndReceiptData[9]);
								receipt.setAccountName(paymentAndReceiptData[9]);
								receipt.setAccountNo(paymentAndReceiptData[6]);
								receipt.setAccountSubNo(paymentAndReceiptData[7]);
								receipt.setAccountType(paymentAndReceiptData[8]);
								receipt.setTaxNo(paymentAndReceiptData[11]);
								receipt.setAccountBranch(paymentAndReceiptData[15]);
								receipt.setAddrLine1(paymentAndReceiptData[12]);
								receipt.setAddrLine2(paymentAndReceiptData[13]);
								receipt.setPaymentCase(paymentAndReceiptData[14]);
								receipt.setBranchCode(paymentAndReceiptData[15]);
								receipt.setBranchArea(paymentAndReceiptData[16]);
								receipt.setBranchName(paymentAndReceiptData[17]);
								receipt.setAmount(new BigDecimal(paymentAndReceiptData[18]));
								receipt.setDiscount(new BigDecimal(paymentAndReceiptData[19]));
								receipt.setCharge(new BigDecimal(paymentAndReceiptData[20]));
								receipt.setVatRate(new BigDecimal(paymentAndReceiptData[21]));
								receipt.setVat(new BigDecimal(paymentAndReceiptData[22]));
								receipt.setTotalCharge(new BigDecimal(paymentAndReceiptData[23]));
								receipt.setDeduction(new BigDecimal(paymentAndReceiptData[24]));
								receipt.setAfterSaleDiscount(BigDecimal.ZERO);
								receipt.setBalanceDue(new BigDecimal(paymentAndReceiptData[26]));
								receipt.setReceived(new BigDecimal(paymentAndReceiptData[27]));
								receipt.setChange(BigDecimal.ZERO);
								receipt.setAdvanced(BigDecimal.ZERO);
								receipt.setPromotion(null);
								receipt.setRemark(paymentAndReceiptData[21]);
								receipt.setReprint(0L);
								receipt.setAttributes("A");
								receipt.setBillingGroup(null);
								receipt.setBillingGroupFull(null);
								receipt.setLanguage("TH");

								// TRSDEDUCTION
								deduct = deductRepo.save(new Deduct());
								deduct.setUpdateDttm(date);
								deduct.setUpdateUser(USERNAME_OFFILINE);
								deduct.setNo(paymentAndReceiptData[36]);
								deduct.setType(paymentAndReceiptData[35]);
								deduct.setAmount(new BigDecimal(paymentAndReceiptData[24]));
								deduct.setDate(DateUtil.convertToDate(paymentAndReceiptData[43]));
								deduct.setPaymentId(payment.getId());
								payment.getDeducts().add(deduct);

								// CORPAYMENT
								payment.setOfficerId(new Long(paymentAndReceiptData[39]));
								payment.setDate(DateUtil.convertToDate(paymentAndReceiptData[43]));
								payment.setType(paymentAndReceiptData[44]);
								payment.setMethod(paymentAndReceiptData[45]);
								payment.setAmount(new BigDecimal(paymentAndReceiptData[46]));
								payment.setDiscount(new BigDecimal(paymentAndReceiptData[47]));
								payment.setCharge(new BigDecimal(paymentAndReceiptData[48]));
								payment.setVatRate(new BigDecimal(paymentAndReceiptData[49]));
								payment.setVat(new BigDecimal(paymentAndReceiptData[50]));
								payment.setTotalCharge(new BigDecimal(paymentAndReceiptData[51]));
								payment.setDeduction(new BigDecimal(paymentAndReceiptData[52]));
								payment.setAfterSaleDiscount(BigDecimal.ZERO);
								payment.setBalanceDue(new BigDecimal(paymentAndReceiptData[54]));
								payment.setReceived(BigDecimal.ZERO);
								payment.setChange(BigDecimal.ZERO);
								payment.setAdvanced(BigDecimal.ZERO);
								payment.setAttributes(paymentAndReceiptData[59]);
							}
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								if (br != null) br.close();
								FileUtils.moveFile(new File(inbound + "/" + file.getName()), new File(archive + "/" + file.getName()));
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					}
				}

			} catch (Exception e) {
				throw new Exception();
			}
		}
		return RepeatStatus.FINISHED;
	}
	
	@Value("${epis.offline.inbound.directory}") String inbound;
	public File[] readFileOffline() throws Exception {
		return new File(inbound).listFiles();
	}

}
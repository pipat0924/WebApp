package th.net.cat.epis.batch.task;

import static java.util.Locale.ENGLISH;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_WRITEOFF;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Component;

import th.net.cat.epis.dto.SettlePayment;
import th.net.cat.epis.util.DateUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentRequest;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse;
import th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffRequest;
import th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse;
import th.net.cat.epis.ws.service.ESBWS_F04CreatePaymentService;
import th.net.cat.epis.ws.service.ESBWS_F10CreateWriteOffService;

@Component("retryUnfinishCallBillingSystem")
public class RetryUnfinishCallBillingSystem implements Tasklet {

	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@Autowired ESBWS_F04CreatePaymentService _f04CreatePaymentService;
	@Autowired ESBWS_F10CreateWriteOffService _f10CreateWriteOffService;
	@Value("${epis.task.active}") String taskActive;
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
	    if(taskActive.equals("1")) {
			Date currentDate = new Date();
			final List<Transaction> transactions = new LinkedList<Transaction>();
			episJdbcTemplate.query(
					"SELECT rownum"
							+ ", rcp.receiptid"
							+ ", rcp.receiptdttm"
							+ ", rcp.updatedttm"
							+ ", inv.kenan"
							+ ", cus.accountno"
							+ ", inv.invoiceno"
							+ ", inv.status"
							+ ", inv.currencycode"
							+ ", txn.transactionid"
							+ ", txn.payamount"
							+ ", txn.trackingretry "
							+ "FROM CORRECEIPT rcp "
							+ "LEFT JOIN TMPINVOICE inv ON inv.receiptid = rcp.receiptid "
							+ "LEFT JOIN TMPINVOICESERVICE svc ON svc.invoiceid = inv.invoiceid "
							+ "LEFT JOIN TRSPAYMENTREF txn ON txn.serviceid = svc.serviceid "
							+ "LEFT JOIN TMPACCOUNT cus ON cus.accountid = inv.accountid "
							+ "WHERE rownum < 10000 "
							+ "AND inv.kenan IS NOT NULL  AND txn.TRANSACTIONID  not in ( '149183260951971','122666368775947','122666363046811') "// AND txn.TRANSACTIONID in ( '27328570029747','27328572421403') "
							+ "AND txn.trackingid IS NULL", new RowCountCallbackHandler() {
						@Override
						public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
							if (rowNum > 9999) return;
							Transaction transaction = new Transaction();
							transaction.setKenan(resultSet.getString("kenan"));
							transaction.setBillNo(resultSet.getString("accountno"));
							transaction.setInvoiceNo(resultSet.getString("invoiceno"));
							transaction.setInvoiceType(resultSet.getString("status"));
							transaction.setCurrency(resultSet.getString("currencycode"));
							transaction.setTrxnId(resultSet.getString("transactionid"));
							transaction.setAmount(resultSet.getBigDecimal("payamount"));
							transaction.setRetry(resultSet.getInt("trackingretry"));
							transaction.setReceiptId(resultSet.getLong("receiptid"));
							transaction.setReceiptPayDate(resultSet.getDate("receiptdttm") != null ? resultSet.getDate("receiptdttm") : resultSet.getDate("updatedttm")); // this is just to amend for wrong data
							transactions.add(transaction);
						}
					});

			String trackingId = null, trackingIdServ = null, trackingCode = null, trackingDetails = null;
			// Call WriteOff Transactions
			for (Transaction transaction : transactions) {
				if (transaction.getInvoiceType().equalsIgnoreCase(INVOICE_FROM_WRITEOFF)) {
					CreateWriteOffRequest request = new CreateWriteOffRequest();
					TransactionLogCBO transactionLogCBO = _f10CreateWriteOffService.buildTransactionLogCBO();
					transactionLogCBO.setTranID(transaction.getTrxnId());
					request.setTransactionLog(transactionLogCBO);
					request.setInvoiceNo(transaction.getInvoiceNo());
					request.setBillingAccountNo(transaction.getBillNo());
					request.setAmount(transaction.getAmount().doubleValue());
					request.setPayDate(new SimpleDateFormat(DateUtil.STANDARD_DATE_TIME_PATTERN_DASH, ENGLISH).format(transaction.getReceiptPayDate()));
					request.setTranType("Payment");
					request.setTranId("1");

					trackingId = null;
					trackingIdServ = null;
					trackingCode = null;
					trackingDetails = null;
					try {
						CreateWriteOffResponse createWriteOffResult = _f10CreateWriteOffService.callInterface(request);
						if (_f10CreateWriteOffService.isCalledSuccesful("0", createWriteOffResult)) {
							if (createWriteOffResult.getTrackingId() != null & transaction != null) {
								trackingId = createWriteOffResult.getTrackingId();
							}
						}
						if (createWriteOffResult.getTransactionLog() != null) {
							trackingCode = createWriteOffResult.getTransactionLog().getDestinationReturnCode();
							trackingDetails = createWriteOffResult.getTransactionLog().getDestinationReturnDetails();
						}
					} catch (javax.xml.ws.WebServiceException ex) {
						if (ex.getCause() instanceof java.net.SocketTimeoutException) {
							trackingDetails = "java.net.SocketTimeoutException: Read timed out";
						} else {
							trackingDetails = ex.toString();
						}
					} finally {
						episJdbcTemplate.update("UPDATE TRSPAYMENTREF "
								+ "SET trackingid = ?"
								+ ", trackingidserv = ?"
								+ ", trackingcode = ?"
								+ ", trackingdetails = ?"
								+ ", trackingretry = ?"
								+ ", updatedttm = ?"
								+ ", updatesystem = 'BAT'"
								+ ", updateuser = 'BATCH' "
								+ "WHERE transactionid = ?", trackingId, trackingIdServ, trackingCode, trackingDetails, transaction.getRetry() + 1, currentDate, transaction.getTrxnId());
					}
				}
			}

			for (Transaction transaction : transactions) {
				if (transaction.getInvoiceType().equalsIgnoreCase(INVOICE_FROM_WRITEOFF)) continue;
				// Call Billing Transactions
				boolean isAdvancedPayment = transaction.getInvoiceNo().startsWith("Advance");
				short currencyCode = (short) ("USD".equals(transaction.getCurrency()) || "US".equals(transaction.getCurrency()) ? 1 : 12);
				SettlePayment settlePayment = new SettlePayment();
				settlePayment.setAccountNoFromKenan(transaction.getKenan());
				settlePayment.setBillingAccountNo(transaction.getBillNo());
				settlePayment.setAnnotation(new Date());
				settlePayment.setCurrencyCode(currencyCode);
				settlePayment.setReceivedAmount(transaction.getAmount());
				settlePayment.setReceivedAmountCurrency(currencyCode);
				settlePayment.setPaymentChannel("");
				settlePayment.setShopCode("");
				settlePayment.setBillRefNo(isAdvancedPayment ? "0" : transaction.getInvoiceNo());
				settlePayment.setOrigBillRefResets(isAdvancedPayment ? "0" : "1");
				settlePayment.setPaymentMethod("1");
				settlePayment.setTransAmount(transaction.getAmount());
				settlePayment.setTransType(isAdvancedPayment ? 2 : 1);
				trackingId = null;
				trackingIdServ = null;
				trackingCode = null;
				trackingDetails = null;
				try {
					CreatePaymentRequest request = settlePayment.transformToCreatePaymentRequest();
					TransactionLogCBO transactionLogCBO = _f04CreatePaymentService.buildTransactionLogCBO();
					transactionLogCBO.setTranID(transaction.getTrxnId());
					request.setTransactionLog(transactionLogCBO);
					request.getTransactionLog().setTranID(transaction.getTrxnId());
					CreatePaymentResponse createPaymentResult = _f04CreatePaymentService.callInterface(request);
					if (createPaymentResult != null & createPaymentResult.getPayment() != null & transaction != null) {
						if (createPaymentResult.getPayment().getKey() != null) {
							trackingId = createPaymentResult.getPayment().getKey().getTrackingId().toString();
							trackingIdServ = Short.toString(createPaymentResult.getPayment().getKey().getTrackingIdServ());
						}
					}
					if (createPaymentResult.getTransactionLog() != null) {
						trackingCode = createPaymentResult.getTransactionLog().getDestinationReturnCode();
						trackingDetails = createPaymentResult.getTransactionLog().getDestinationReturnDetails();
					}
				} catch (javax.xml.ws.WebServiceException ex) {
					if (ex.getCause() instanceof java.net.SocketTimeoutException) {
						trackingDetails = "java.net.SocketTimeoutException: Read timed out";
					} else {
						trackingDetails = ex.toString();
					}
				} finally {
					episJdbcTemplate.update("UPDATE TRSPAYMENTREF "
							+ "SET trackingid = ?"
							+ ", trackingidserv = ?"
							+ ", trackingcode = ?"
							+ ", trackingdetails = ?"
							+ ", trackingretry = ?"
							+ ", updatedttm = ?"
							+ ", updatesystem = 'BAT'"
							+ ", updateuser = 'BATCH' "
							+ "WHERE transactionid = ?", trackingId, trackingIdServ, trackingCode, trackingDetails, transaction.getRetry() + 1, currentDate, transaction.getTrxnId());
				}
			}
		}
		return RepeatStatus.FINISHED;
	}

	public static class Transaction {
		private String kenan;
		private String billNo;
		private String invoiceNo;
		private String invoiceType;
		private String currency;
		private String trxnId;
		private BigDecimal amount;
		private int retry;
		private long receiptId;
		private Date receiptPayDate;
		public String getKenan() {
			return kenan;
		}
		public void setKenan(String kenan) {
			this.kenan = kenan;
		}
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public String getInvoiceNo() {
			return invoiceNo;
		}
		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}
		public String getInvoiceType() {
			return invoiceType;
		}
		public void setInvoiceType(String invoiceType) {
			this.invoiceType = invoiceType;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getTrxnId() {
			return trxnId;
		}
		public void setTrxnId(String trxnId) {
			this.trxnId = trxnId;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public int getRetry() {
			return retry;
		}
		public void setRetry(int retry) {
			this.retry = retry;
		}
		public long getReceiptId() {
			return receiptId;
		}
		public void setReceiptId(long receiptId) {
			this.receiptId = receiptId;
		}
		public Date getReceiptPayDate() {
			return receiptPayDate;
		}
		public void setReceiptPayDate(Date receiptPayDate) {
			this.receiptPayDate = receiptPayDate;
		}
	}
}
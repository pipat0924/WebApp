package th.net.cat.epis.dto;

import java.math.BigDecimal;
import java.util.Date;

public class HistoryPaymentDetail {

//	select ac.PAYMENTID as paymentId,ac.ACCOUNTNO as accountNo, inv.KENAN as kenan, trn.TRACKINGID as trackingId, trn.TRACKINGIDSERV as trackingIdServ
//	from TMPACCOUNT ac 
//	left join TMPINVOICE inv on ac.PAYMENTID = inv.PAYMENTID
//	left join TRSPAYMENTREF trn on ac.PAYMENTID = trn.PAYMENTID
//	where ac.accountNo = '1050375' and inv.KENAN is not null;
	
	private int receiptId;
	private int paymentId;
	private String accountNo;
	private String kenan;
	
	//all needed from Billing database
	private String trackingId;
	private String trackingIdServ;
	private String receiptNo;
	private String receiptBranchName;
	private String updateUser;
	private String invoiceNo;
	private String billCycle;
	private String vatType;
	private BigDecimal vatAmount;
	private BigDecimal totalCharged;
	private String paymentBy; // cash, credit, ...
	private String paymentType; // full, advance
	private String totalPay;
	private String status;
	private Date paymentDate;
	private Date processDate;
	private String locationName;
	
	public HistoryPaymentDetail(){}
	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getKenan() {
		return kenan;
	}
	public void setKenan(String kenan) {
		this.kenan = kenan;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getTrackingIdServ() {
		return trackingIdServ;
	}
	public void setTrackingIdServ(String trackingIdServ) {
		this.trackingIdServ = trackingIdServ;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getReceiptBranchName() {
		return receiptBranchName;
	}
	public void setReceiptBranchName(String receiptBranchName) {
		this.receiptBranchName = receiptBranchName;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public String getVatType() {
		return vatType;
	}
	public void setVatType(String vatType) {
		this.vatType = vatType;
	}
	public BigDecimal getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}
	public BigDecimal getTotalCharged() {
		return totalCharged;
	}
	public void setTotalCharged(BigDecimal totalCharged) {
		this.totalCharged = totalCharged;
	}
	public String getPaymentBy() {
		return paymentBy;
	}
	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}

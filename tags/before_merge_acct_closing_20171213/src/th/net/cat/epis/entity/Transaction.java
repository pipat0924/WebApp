package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRSPAYMENTREF")
public class Transaction {

	@Id
	@SequenceGenerator(name="transaction_seq", sequenceName="transaction_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transaction_seq")
	@Column(name="PAYMENTREFID") private Long id;
	@Column(name="SERVICEID") private Long serviceId;
	@Column(name="TRANSACTIONID") private String transactionId;
	@Column(name="TRACKINGID") private String trackingId;
	@Column(name="TRACKINGIDSERV") private String trackingIdServ;
	@Column(name="TRACKINGCODE") private String trackingCode;
	@Column(name="TRACKINGDETAILS") private String trackingDetails;
	@Column(name="TRACKINGRETRY") private Integer trackingRetry;
	@Column(name="PAYMENTDATE") private Date paymentDate;
	@Column(name="PAYMENTTYPE") private String paymentType;
	@Column(name="PAYMENTCASE") private String paymentCase;
	@Column(name="STATUS") private String status;
	@Column(name="CHEQUENO") private String chequeNo;
	@Column(name="ACCOUNTNO") private String accountNo;
	@Column(name="PAYAMOUNT") private BigDecimal amount;
	@Column(name="APPROVEDBY") private String approvedBy;
	@Column(name="ATTRIBUTES") private String attributes;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="PAYMENTID") private Payment payment;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	public String getTrackingDetails() {
		return trackingDetails;
	}
	public void setTrackingDetails(String trackingDetails) {
		this.trackingDetails = trackingDetails;
	}
	public Integer getTrackingRetry() {
		return trackingRetry;
	}
	public void setTrackingRetry(Integer trackingRetry) {
		this.trackingRetry = trackingRetry;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentCase() {
		return paymentCase;
	}
	public void setPaymentCase(String paymentCase) {
		this.paymentCase = paymentCase;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.net.cat.epis.util.converter.CustomDateDeserializer;
import th.net.cat.epis.util.converter.CustomDateSerializer;

@Entity
@Table(name = "PAYMENT_MANUAL")
public class ManualEntity {
	@Id
	@SequenceGenerator(name = "PAYMENT_MANUAL_SEQ", sequenceName = "PAYMENT_MANUAL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_MANUAL_SEQ")
	@Column(name = "MANUAL_ID")
	private Long manualId;
	@Column(name = "PAYMENT_ID")
	private Long paymenId;
	@Column(name = "INVOICE_NO")
	private String invoiceNo;// account_no
	@Column(name = "RECEIPT_NO_MANUAL")
	private String receiptNoManual;
	@Column(name = "PAID_DATE")
	private Date paidDate;
	@Column(name = "BRANCH_AREA")
	private String branchArea;
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	@Column(name = "PAID_AMOUNT")
	private BigDecimal paidAmount;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "SOURCE")
	private String source;
	@Column(name = "CLEARING")
	private String clearing;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "UPDATE_BY")
	private String updateBy;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	@Column(name = "RECORD_STATUS")
	private String recordStatus;
	@Column(name = "ACCOUNT_NO")
	private String accountNo;
	@Column(name = "CLEARING_SAP")
	private String clearingSap;

	@OneToMany(mappedBy="manual_id", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<PayInvoiceManualEntity> invoiceManualEntitySet = new HashSet<PayInvoiceManualEntity>();


	
	public Set<PayInvoiceManualEntity> getInvoiceManualEntitySet() {
		return invoiceManualEntitySet;
	}

	public void setInvoiceManualEntitySet(Set<PayInvoiceManualEntity> invoiceManualEntitySet) {
		this.invoiceManualEntitySet = invoiceManualEntitySet;
	}

	public String getClearingSap() {
		return clearingSap;
	}

	public void setClearingSap(String clearingSap) {
		this.clearingSap = clearingSap;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Long getManualId() {
		return manualId;
	}

	public void setManualId(Long manualId) {
		this.manualId = manualId;
	}

	public Long getPaymenId() {
		return paymenId;
	}

	public void setPaymenId(Long paymenId) {
		this.paymenId = paymenId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getReceiptNoManual() {
		return receiptNoManual;
	}

	public void setReceiptNoManual(String receiptNoManual) {
		this.receiptNoManual = receiptNoManual;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getPaidDate() {
		return paidDate;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getBranchArea() {
		return branchArea;
	}

	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getClearing() {
		return clearing;
	}

	public void setClearing(String clearing) {
		this.clearing = clearing;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUpdateDate() {
		return updateDate;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

}

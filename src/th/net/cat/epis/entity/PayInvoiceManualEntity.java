package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_INVOICE_MANUAL")
public class PayInvoiceManualEntity {
	@Id
	@SequenceGenerator(name = "PAYMENT_INVOICE_MANUAL_SEQ", sequenceName = "PAYMENT_INVOICE_MANUAL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_INVOICE_MANUAL_SEQ")
	@Column(name = "PAYMENT_INVOICE_MANUAL_ID")
	private Long PayInvID;
	@Column(name = "MANUAL_ID")
	private Long manual_id;
	@Column(name = "SOURCE")
	private String source;// account_no
	@Column(name = "INVOICE_NO")
	private String invoiceNo;
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;
	@Column(name = "UPDATE_BY")
	private String updateBy;
	@Column(name = "UPDATE_DATE")
	private Timestamp updateDate;
	@Column(name = "RECORD_STATUS")
	private String recordStatus;
	@Column(name = "SERVICE_TYPE")
	private String serviceType;
	@Column(name = "PRINT_RECEIPT")
	private String printReceipt;
	@Column(name = "CLEARING")
	private String clearing;
	@Column(name = "PERIOD")
	private String period;
	

	public Long getPayInvID() {
		return PayInvID;
	}

	public void setPayInvID(Long payInvID) {
		PayInvID = payInvID;
	}

	public Long getManual_id() {
		return manual_id;
	}

	public void setManual_id(Long manual_id) {
		this.manual_id = manual_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPrintReceipt() {
		return printReceipt;
	}

	public void setPrintReceipt(String printReceipt) {
		this.printReceipt = printReceipt;
	}

	public String getClearing() {
		return clearing;
	}

	public void setClearing(String clearing) {
		this.clearing = clearing;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}

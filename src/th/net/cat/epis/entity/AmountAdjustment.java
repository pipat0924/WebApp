package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AMOUNT_ADJUSTMENT")
public class AmountAdjustment {
	@Id
	@SequenceGenerator(name="AMOUNT_ADJUSTMENT_SEQ", sequenceName="AMOUNT_ADJUSTMENT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AMOUNT_ADJUSTMENT_SEQ")
	@Column(name="ADJUST_AMOUNT_ID") private Long id;
	@Column(name="AMOUNT_ADJMT_HEADER_ID") private Long amountAjdmtHeaderId;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="INVOICEID") private Long invoiceId;
	@Column(name="ADJUST_AMOUNT") private BigDecimal adjustAmount;
	@Column(name="ADJUST_STATUS") private String adjustStatus;
	@Column(name="CREATE_DATE") private Date createDate;
	@Column(name="UPDATE_DATE") private Date updateDate;
	@Column(name="CREATE_USER") private String createUser;
	@Column(name="UPDATE_USER") private String updateUser;
	@Column(name="BILL_CYCLE") private String billCycle;
	@Column(name="BILL_REF_NO") private Long billRefNo;
	@Column(name="APPROVE_AMOUNT") private BigDecimal approveAmount;
	@Column(name="TOTAL_CHARGE") private BigDecimal totalCharge;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public BigDecimal getAdjustAmount() {
		return adjustAmount;
	}
	public void setAdjustAmount(BigDecimal adjustAmount) {
		this.adjustAmount = adjustAmount;
	}
	
	public String getAdjustStatus() {
		return adjustStatus;
	}
	public void setAdjustStatus(String adjustStatus) {
		this.adjustStatus = adjustStatus;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Long getAmountAjdmtHeaderId() {
		return amountAjdmtHeaderId;
	}
	public void setAmountAjdmtHeaderId(Long amountAjdmtHeaderId) {
		this.amountAjdmtHeaderId = amountAjdmtHeaderId;
	}
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public Long getBillRefNo() {
		return billRefNo;
	}
	public void setBillRefNo(Long billRefNo) {
		this.billRefNo = billRefNo;
	}
	public BigDecimal getApproveAmount() {
		return approveAmount;
	}
	public void setApproveAmount(BigDecimal approveAmount) {
		this.approveAmount = approveAmount;
	}
	public BigDecimal getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}
}

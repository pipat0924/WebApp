package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DEDUCTION_MANUAL")
public class DeductionManualEntity {
	@Id  
	@SequenceGenerator(name="DEDUCTION_MANUAL_SEQ", sequenceName="DEDUCTION_MANUAL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEDUCTION_MANUAL_SEQ")
	@Column(name="DEDUCTION_MANUAL_ID") private Long deductionId;
	@Column(name="PAYMENTID") private Long paymenId;
	@Column(name="DEDUCTIONNO") private String deductionNo;
	@Column(name="DEDUCTIONTYPE") private String deductionType;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="PAYMENTDATE") private Date paymentDate;
	@Column(name="UPDATEDTTM") private Timestamp updateDttm;
	@Column(name="UPDATESYSTEM") private String updateSystem;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="VERSIONSTAMP") private Long verstionStamp;
	@Column(name="INVOICE_NO") private String invoiceNo;
	@Column(name="REMARK") private String remark;
	@Column(name="CREATE_BY") private String createBy;
	@Column(name="CREATE_DATE") private Date createDate;
	@Column(name="UPDATE_BY") private String updateBy;
	@Column(name="UPDATE_DATE") private Date updateDate;
	@Column(name="RECORD_STATUS") private String recordStatus;
	@Column(name="MANUAL_ID") private Long manualId;
	public Long getDeductionId() {
		return deductionId;
	}
	public void setDeductionId(Long deductionId) {
		this.deductionId = deductionId;
	}
	public Long getPaymenId() {
		return paymenId;
	}
	public void setPaymenId(Long paymenId) {
		this.paymenId = paymenId;
	}
	public String getDeductionNo() {
		return deductionNo;
	}
	public void setDeductionNo(String deductionNo) {
		this.deductionNo = deductionNo;
	}
	public String getDeductionType() {
		return deductionType;
	}
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Timestamp getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Timestamp updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getUpdateSystem() {
		return updateSystem;
	}
	public void setUpdateSystem(String updateSystem) {
		this.updateSystem = updateSystem;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Long getVerstionStamp() {
		return verstionStamp;
	}
	public void setVerstionStamp(Long verstionStamp) {
		this.verstionStamp = verstionStamp;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Long getManualId() {
		return manualId;
	}
	public void setManualId(Long manualId) {
		this.manualId = manualId;
	}
	
	
}

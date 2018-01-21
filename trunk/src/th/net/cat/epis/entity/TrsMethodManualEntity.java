package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TRSMETHOD_MANUAL")
public class TrsMethodManualEntity {
	@Id
	@SequenceGenerator(name="TRSMETHOD_MANUAL_SEQ", sequenceName="TRSMETHOD_MANUAL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRSMETHOD_MANUAL_SEQ")
	@Column(name="METHOD_MANUAL_ID") private Long  methodManualId;
	@Column(name="PAYMENTID") private Long  paymentid;
	@Column(name="CODE") private String  code;
	@Column(name="NAME") private String  name;
	@Column(name="CHEQUENO") private String  chequeno;
	@Column(name="ACCOUNTNO") private String  accountno;
	@Column(name="AMOUNT") private BigDecimal  amount;
	@Column(name="UPDATEDTTM") private Timestamp  updatedttm;
	@Column(name="UPDATESYSTEM") private String  updatesystem;
	@Column(name="UPDATEUSER") private String  updateuser;
	@Column(name="VERSIONSTAMP") private Long  versionstamp;
	@Column(name="OFFSET_DOCUMENT_NO") private String  offsetDocumentNo;
	@Column(name="OFFSET_ACCOUNT_CODE") private String  offsetAccountCode;
	@Column(name="OFFSET_ACCOUNT_NAME") private String  offsetAccountName;
	@Column(name="REMARK") private String  remark;
	@Column(name="CREATE_BY") private String  createBy;
	@Column(name="CREATE_DATE") private Timestamp  createDate;
	@Column(name="UPDATE_BY") private String  updateBy;
	@Column(name="UPDATE_DATE") private Timestamp  updateDate;
	@Column(name="RECORD_STATUS") private String  recordStatus;
	@Column(name = "MANUAL_ID") private Long manualId;
	
	
	

	public Long getManualId() {
		return manualId;
	}
	public void setManualId(Long manualId) {
		this.manualId = manualId;
	}
	public Long getMethodManualId() {
		return methodManualId;
	}
	public void setMethodManualId(Long methodManualId) {
		this.methodManualId = methodManualId;
	}
	public Long getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(Long paymentid) {
		this.paymentid = paymentid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChequeno() {
		return chequeno;
	}
	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Timestamp getUpdatedttm() {
		return updatedttm;
	}
	public void setUpdatedttm(Timestamp updatedttm) {
		this.updatedttm = updatedttm;
	}
	public String getUpdatesystem() {
		return updatesystem;
	}
	public void setUpdatesystem(String updatesystem) {
		this.updatesystem = updatesystem;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public Long getVersionstamp() {
		return versionstamp;
	}
	public void setVersionstamp(Long versionstamp) {
		this.versionstamp = versionstamp;
	}
	public String getOffsetDocumentNo() {
		return offsetDocumentNo;
	}
	public void setOffsetDocumentNo(String offsetDocumentNo) {
		this.offsetDocumentNo = offsetDocumentNo;
	}
	public String getOffsetAccountCode() {
		return offsetAccountCode;
	}
	public void setOffsetAccountCode(String offsetAccountCode) {
		this.offsetAccountCode = offsetAccountCode;
	}
	public String getOffsetAccountName() {
		return offsetAccountName;
	}
	public void setOffsetAccountName(String offsetAccountName) {
		this.offsetAccountName = offsetAccountName;
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
	
	
	
}

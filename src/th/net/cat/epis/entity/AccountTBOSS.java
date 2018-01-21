package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.net.cat.epis.util.AppUtil;

@Entity
@Table(name = "ACCOUNTTBOSS")
public class AccountTBOSS {

	@Id
	@SequenceGenerator(name="accounttboss_seq", sequenceName="accounttboss_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="accounttboss_seq")
	@Column(name = "ACCOUNTTBOSSID") private Long id;
	@Column(name = "AR_REF", nullable = false, precision = 9, scale = 0) private Integer invoiceNo;
	@Column(name = "CONTRNO", nullable = false, length = 10) private String contractNo;
	@Column(name = "AR_INVDATE", nullable = false) private Date invdate;
	@Column(name = "AR_DUEDATE", nullable = false) private Date invDuedate;
	@Column(name = "AR_AM_LOC", precision = 12)	private BigDecimal amount;
	@Column(name = "TAX_TYPE_CODE", length = 4) private String taxTypeCode;
	@Column(name = "AR_AM_TAX", precision = 12) private BigDecimal taxAmount;
	@Column(name = "AR_TEXT1", length = 30) private String note1;
	@Column(name = "AR_TEXT2", length = 30) private String note2;
	@Column(name = "AR_DD_STATUS", length = 1) private String arDdStatus;
	@Column(name = "DESCRIPTION", length = 61) private String description;
	@Column(name = "SUB_NO", length = 16) private String phoneNo;
	@Column(name = "CHEQUE_FLAG", length = 1) private String chequeFlag;
	@Column(name = "PAY_ADVANCE", precision = 12) private BigDecimal payAdv;
	@Column(name = "PAY_AMOUNT", precision = 12) private BigDecimal paidAmount;
	@Column(name = "AR_AM_DEBT", nullable = false, precision = 12) private BigDecimal balanceDue;
	@Column(name = "RECEIVE_DATETIME") private Date receiveDatetime;
	@Column(name = "SERVICE_CODE", length = 12) private String serviceCode;
	@Column(name = "POS_NO", length = 30) private String posNo;
	@Column(name = "RESERVE_TIME") private Date reserveTime;
	@Column(name = "UPDATEDTTM") private Date updateDttm;
	@Column(name = "UPDATESYSTEM", length = 3) private String updateSystem;
	@Column(name = "UPDATEUSER", length = 32) private String updateUser;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accountTbossId", cascade=CascadeType.PERSIST)
	private Set<TrsAccountTBOSS> trsAccountTBOSS = new HashSet<TrsAccountTBOSS>();
	public AccountTBOSS() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public Date getInvdate() {
		return invdate;
	}
	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}
	public Date getInvDuedate() {
		return invDuedate;
	}
	public void setInvDuedate(Date invDuedate) {
		this.invDuedate = invDuedate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getTaxTypeCode() {
		return taxTypeCode;
	}
	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	public String getArDdStatus() {
		return arDdStatus;
	}
	public void setArDdStatus(String arDdStatus) {
		this.arDdStatus = arDdStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getChequeFlag() {
		return chequeFlag;
	}
	public void setChequeFlag(String chequeFlag) {
		this.chequeFlag = chequeFlag;
	}
	public BigDecimal getPayAdv() {
		return payAdv;
	}
	public void setPayAdv(BigDecimal payAdv) {
		this.payAdv = payAdv;
	}
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	public Date getReceiveDatetime() {
		return receiveDatetime;
	}
	public void setReceiveDatetime(Date receiveDatetime) {
		this.receiveDatetime = receiveDatetime;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getPosNo() {
		return posNo;
	}
	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}
	public Date getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public void setUpdateDttm(java.util.Date updateDttm) {
		this.updateDttm = AppUtil.convertJavaDateToSqlDate(updateDttm);
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
	public Set<TrsAccountTBOSS> getTrsAccountTBOSS() {
		return trsAccountTBOSS;
	}
	public void setTrsAccountTBOSS(Set<TrsAccountTBOSS> trsAccountTBOSS) {
		this.trsAccountTBOSS = trsAccountTBOSS;
	}
}

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
@Table(name="SAP_AR")
public class SapAr {
	
	@Id
	@SequenceGenerator(name="SAP_AR_seq", sequenceName="SAP_AR_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SAP_AR_seq")
	@Column(name="ID") private Long id;
	///		detail Customer 	///
	@Column(name="ACCOUNT_NO")private String arAccountCode;
	@Column(name="CUSTOMER_NAME")private String arName;
	@Column(name="TAX_ID")private String taxId;
	@Column(name="RECONCILE")private String glAccount;
	@Column(name="CUSTOMER_GROUP")private String arGroup;
	@Column(name="REGION_SAP")private String regionKey1;
	@Column(name="CUSTOMER_BRANCH_CODE")private String branchAR;
	@Column(name="CUSTOMER_ADDRESS")private String address;
	///		pay bounceCheque	///
	@Column(name="SAP_PERIOD")private String refDate;
	@Column(name="FISCAL_YEAR")private Integer accountYear;
	@Column(name="AR_REF")private String docHead;
	@Column(name="SERVICE_NAME")private String serviceKey3;
	@Column(name="CURRANCY_AR")private String currency;
	@Column(name="AMOUNT_OTHER_AR")private Double amountARout;
	@Column(name="EXCH_RATE_AR")private Double rateChange;
	@Column(name="TOTAL_AMOUNT_AR")private Double amountARin;
	@Column(name="EXCH_RATE_DATE_AR")private Timestamp currencyChangeDate;
	@Column(name="DOC_NO")private String docNo;
	@Column(name="INVDATE")private Timestamp docDate;
	@Column(name="POSTING_DATE")private String passDate;
	@Column(name="TAX_CODE")private String vatCode;
	@Column(name="MESSAGE")private String massage;
	@Column(name="PRODUCT_CODE")private String prodKey2;
	@Column(name="RECORD_STATUS")private String recordStatus;
	@Column(name="BALANCE_DUE")private BigDecimal balanceDue;
	@Column(name="REGION_NAME")private String regionName;
	@Column(name="UPDATE_DATE")private Timestamp updateD;
	@Column(name="UPDATE_BY")private String updateBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArAccountCode() {
		return arAccountCode;
	}
	public void setArAccountCode(String arAccountCode) {
		this.arAccountCode = arAccountCode;
	}
	public String getArName() {
		return arName;
	}
	public void setArName(String arName) {
		this.arName = arName;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getGlAccount() {
		return glAccount;
	}
	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}
	public String getArGroup() {
		return arGroup;
	}
	public void setArGroup(String arGroup) {
		this.arGroup = arGroup;
	}
	public String getRegionKey1() {
		return regionKey1;
	}
	public void setRegionKey1(String regionKey1) {
		this.regionKey1 = regionKey1;
	}
	public String getBranchAR() {
		return branchAR;
	}
	public void setBranchAR(String branchAR) {
		this.branchAR = branchAR;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRefDate() {
		return refDate;
	}
	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}
	public Integer getAccountYear() {
		return accountYear;
	}
	public void setAccountYear(Integer accountYear) {
		this.accountYear = accountYear;
	}
	public String getDocHead() {
		return docHead;
	}
	public void setDocHead(String docHead) {
		this.docHead = docHead;
	}
	public String getServiceKey3() {
		return serviceKey3;
	}
	public void setServiceKey3(String serviceKey3) {
		this.serviceKey3 = serviceKey3;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getAmountARout() {
		return amountARout;
	}
	public void setAmountARout(Double amountARout) {
		this.amountARout = amountARout;
	}
	public Double getRateChange() {
		return rateChange;
	}
	public void setRateChange(Double rateChange) {
		this.rateChange = rateChange;
	}
	public Double getAmountARin() {
		return amountARin;
	}
	public void setAmountARin(Double amountARin) {
		this.amountARin = amountARin;
	}
	public Timestamp getCurrencyChangeDate() {
		return currencyChangeDate;
	}
	public void setCurrencyChangeDate(Timestamp currencyChangeDate) {
		this.currencyChangeDate = currencyChangeDate;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public Timestamp getDocDate() {
		return docDate;
	}
	public void setDocDate(Timestamp docDate) {
		this.docDate = docDate;
	}
	public String getPassDate() {
		return passDate;
	}
	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}
	public String getVatCode() {
		return vatCode;
	}
	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public String getProdKey2() {
		return prodKey2;
	}
	public void setProdKey2(String prodKey2) {
		this.prodKey2 = prodKey2;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Timestamp getUpdateD() {
		return updateD;
	}
	public void setUpdateD(Timestamp updateD) {
		this.updateD = updateD;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
}

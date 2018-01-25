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
@Table(name="SAP_DEBT")
public class SapDebtEntity {
	

	@Id
	@SequenceGenerator(name="SAP_DEBT_seq", sequenceName="SAP_DEBT_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SAP_DEBT_seq")
	@Column(name="ID")private Long id;
	
	
	@Column(name="ACCOUNT_NO")private String accountNo;
	@Column(name="GL_ACCOUNT")private String glAccount;                                              
	@Column(name="FISCAL_YEAR")private String fiscalYear;                                                  
	@Column(name="SAPDOC_NO")private String sapdocNo;
	@Column(name="RECONCILE_ACCOUNT")private String reconcileAccount;                                          
	@Column(name="COMPANY_CODE")private String companyCode;                                               
	@Column(name="LOCAL_AMOUNT_AR")private BigDecimal localAmountAr;                                               
	@Column(name="TRANS_AMOUNT_AR")private BigDecimal transAmountAr;                                              
	@Column(name="DOC_DATE_AR")private Timestamp docDateAr;
	@Column(name="POSTING_DATE_AR")private Timestamp postingDateAr;
	@Column(name="EXCH_RATE_DATE_AR")private Timestamp exchRateDateAr;
	@Column(name="DOC_HEADER_TEXT_INV")private String docHeaderTextInv;                                          
	@Column(name="REF_SAP_PERIOD")private String refSapPeriod;                                               
	@Column(name="AW_TEXT")private String awText;
	@Column(name="COST_CENTER_SAP")private String costCenterSap;                                            
	@Column(name="SEGMENT_CODE")private String segmentCode;                                               
	@Column(name="REF1_SAP_REGION_CODE")private String ref1SapRegionCode;                                        
	@Column(name="REF2_PRODUCT_CODE")private String ref2ProductCode;                                           
	@Column(name="SUB_PRODUCT_CODE")private String subProductCode;                                            
	@Column(name="SERVICE_PRIORITY_PRODUCT")private String servicePriorityProduct;                                 
	@Column(name="REF3_SERVICE_NAME")private String ref3ServiceName;                                        
	@Column(name="TRADING_PART")private String tradingPart;
	@Column(name="CURRENCY_CODE_AR")private String currencyCodeAr;                                                 
	@Column(name="EXCH_RATE_AR")private BigDecimal exchRateAr;                                            
	@Column(name="TAX_CODE")private String taxCode;
	@Column(name="CUSTOMER_GROUP_CODE")private String customerGroupCode;                                 
	@Column(name="CUSTOMER_NAME")private String customerName;                                               
	@Column(name="CUSTOMER_HOME_STREET")private String customerHomeStreet;                                         
	@Column(name="CUSTOMER_POSTCODE")private String customerPostcode;                                            
	@Column(name="CUSTOMER_CITY")private String customerCity;                                                
	@Column(name="CUSTOMER_COUNTRY_KEY")private String customerCountryKey;                                         
	@Column(name="CUSTOMER_TAX_ID")private String customerTaxId;                                              
	@Column(name="CUSTOMER_BRANCH_CODE")private String customerBranchCode;                                         
	@Column(name="LOCAL_BALANCE_DUE")private BigDecimal localBalanceDue;                                                
	@Column(name="TRANS_BALANCE_DUE")private BigDecimal transBalanceDue;                                                
	@Column(name="LOCAL_TOTAL_PAID")private BigDecimal localTotalPaid;                                                 
	@Column(name="LOCAL_VAT_PAID")private BigDecimal localVatPaid;                                                   
	@Column(name="TRANS_TOTAL_PAID")private BigDecimal transTotalPaid;                                                 
	@Column(name="TRANS_VAT_PAID")private BigDecimal transVatPaid;                                                  
	@Column(name="EXCH_RATE_DATE_PAID")private String exchRateDatePaid;
	@Column(name="EXCH_RATE_PAID")private String exchRatePaid;                                                   
	@Column(name="RECORD_STATUS")private String recordStatus;
	
	@Column(name="CREATED_USER")private String createdUser;
	@Column(name="CREATED_DTM")private Timestamp createdDtm;
	@Column(name="UPDATED_USER")private String updatedUser;
	@Column(name="UPDATED_DTM")private Timestamp updatedDtm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getGlAccount() {
		return glAccount;
	}
	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getSapdocNo() {
		return sapdocNo;
	}
	public void setSapdocNo(String sapdocNo) {
		this.sapdocNo = sapdocNo;
	}
	public String getReconcileAccount() {
		return reconcileAccount;
	}
	public void setReconcileAccount(String reconcileAccount) {
		this.reconcileAccount = reconcileAccount;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public BigDecimal getLocalAmountAr() {
		return localAmountAr;
	}
	public void setLocalAmountAr(BigDecimal localAmountAr) {
		this.localAmountAr = localAmountAr;
	}
	public BigDecimal getTransAmountAr() {
		return transAmountAr;
	}
	public void setTransAmountAr(BigDecimal transAmountAr) {
		this.transAmountAr = transAmountAr;
	}
	public Timestamp getDocDateAr() {
		return docDateAr;
	}
	public void setDocDateAr(Timestamp docDateAr) {
		this.docDateAr = docDateAr;
	}
	public Timestamp getPostingDateAr() {
		return postingDateAr;
	}
	public void setPostingDateAr(Timestamp postingDateAr) {
		this.postingDateAr = postingDateAr;
	}
	public Timestamp getExchRateDateAr() {
		return exchRateDateAr;
	}
	public void setExchRateDateAr(Timestamp exchRateDateAr) {
		this.exchRateDateAr = exchRateDateAr;
	}
	public String getDocHeaderTextInv() {
		return docHeaderTextInv;
	}
	public void setDocHeaderTextInv(String docHeaderTextInv) {
		this.docHeaderTextInv = docHeaderTextInv;
	}
	public String getRefSapPeriod() {
		return refSapPeriod;
	}
	public void setRefSapPeriod(String refSapPeriod) {
		this.refSapPeriod = refSapPeriod;
	}
	public String getAwText() {
		return awText;
	}
	public void setAwText(String awText) {
		this.awText = awText;
	}
	public String getCostCenterSap() {
		return costCenterSap;
	}
	public void setCostCenterSap(String costCenterSap) {
		this.costCenterSap = costCenterSap;
	}
	public String getSegmentCode() {
		return segmentCode;
	}
	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}
	public String getRef1SapRegionCode() {
		return ref1SapRegionCode;
	}
	public void setRef1SapRegionCode(String ref1SapRegionCode) {
		this.ref1SapRegionCode = ref1SapRegionCode;
	}
	public String getRef2ProductCode() {
		return ref2ProductCode;
	}
	public void setRef2ProductCode(String ref2ProductCode) {
		this.ref2ProductCode = ref2ProductCode;
	}
	public String getSubProductCode() {
		return subProductCode;
	}
	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}
	public String getServicePriorityProduct() {
		return servicePriorityProduct;
	}
	public void setServicePriorityProduct(String servicePriorityProduct) {
		this.servicePriorityProduct = servicePriorityProduct;
	}
	public String getRef3ServiceName() {
		return ref3ServiceName;
	}
	public void setRef3ServiceName(String ref3ServiceName) {
		this.ref3ServiceName = ref3ServiceName;
	}
	public String getTradingPart() {
		return tradingPart;
	}
	public void setTradingPart(String tradingPart) {
		this.tradingPart = tradingPart;
	}
	public String getCurrencyCodeAr() {
		return currencyCodeAr;
	}
	public void setCurrencyCodeAr(String currencyCodeAr) {
		this.currencyCodeAr = currencyCodeAr;
	}
	public BigDecimal getExchRateAr() {
		return exchRateAr;
	}
	public void setExchRateAr(BigDecimal exchRateAr) {
		this.exchRateAr = exchRateAr;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getCustomerGroupCode() {
		return customerGroupCode;
	}
	public void setCustomerGroupCode(String customerGroupCode) {
		this.customerGroupCode = customerGroupCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerHomeStreet() {
		return customerHomeStreet;
	}
	public void setCustomerHomeStreet(String customerHomeStreet) {
		this.customerHomeStreet = customerHomeStreet;
	}
	public String getCustomerPostcode() {
		return customerPostcode;
	}
	public void setCustomerPostcode(String customerPostcode) {
		this.customerPostcode = customerPostcode;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	public String getCustomerCountryKey() {
		return customerCountryKey;
	}
	public void setCustomerCountryKey(String customerCountryKey) {
		this.customerCountryKey = customerCountryKey;
	}
	public String getCustomerTaxId() {
		return customerTaxId;
	}
	public void setCustomerTaxId(String customerTaxId) {
		this.customerTaxId = customerTaxId;
	}
	public String getCustomerBranchCode() {
		return customerBranchCode;
	}
	public void setCustomerBranchCode(String customerBranchCode) {
		this.customerBranchCode = customerBranchCode;
	}
	public BigDecimal getLocalBalanceDue() {
		return localBalanceDue;
	}
	public void setLocalBalanceDue(BigDecimal localBalanceDue) {
		this.localBalanceDue = localBalanceDue;
	}
	public BigDecimal getTransBalanceDue() {
		return transBalanceDue;
	}
	public void setTransBalanceDue(BigDecimal transBalanceDue) {
		this.transBalanceDue = transBalanceDue;
	}
	public BigDecimal getLocalTotalPaid() {
		return localTotalPaid;
	}
	public void setLocalTotalPaid(BigDecimal localTotalPaid) {
		this.localTotalPaid = localTotalPaid;
	}
	public BigDecimal getLocalVatPaid() {
		return localVatPaid;
	}
	public void setLocalVatPaid(BigDecimal localVatPaid) {
		this.localVatPaid = localVatPaid;
	}
	public BigDecimal getTransTotalPaid() {
		return transTotalPaid;
	}
	public void setTransTotalPaid(BigDecimal transTotalPaid) {
		this.transTotalPaid = transTotalPaid;
	}
	public BigDecimal getTransVatPaid() {
		return transVatPaid;
	}
	public void setTransVatPaid(BigDecimal transVatPaid) {
		this.transVatPaid = transVatPaid;
	}
	public String getExchRateDatePaid() {
		return exchRateDatePaid;
	}
	public void setExchRateDatePaid(String exchRateDatePaid) {
		this.exchRateDatePaid = exchRateDatePaid;
	}
	public String getExchRatePaid() {
		return exchRatePaid;
	}
	public void setExchRatePaid(String exchRatePaid) {
		this.exchRatePaid = exchRatePaid;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Timestamp getCreatedDtm() {
		return createdDtm;
	}
	public void setCreatedDtm(Timestamp createdDtm) {
		this.createdDtm = createdDtm;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public Timestamp getUpdatedDtm() {
		return updatedDtm;
	}
	public void setUpdatedDtm(Timestamp updatedDtm) {
		this.updatedDtm = updatedDtm;
	}
	
}

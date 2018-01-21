package th.net.cat.erp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRSERPPAYSERVICE")
public class PayService {

	@Id
	@SequenceGenerator(name="erp_payservice_seq", sequenceName="erp_payservice_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="erp_payservice_seq")
	@Column(name="PAYSERVICEID") private Long id;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="ATTRIBUTES") private String attributes;
	@Column(name="RECORDSEQ") private String recordSequence;
	@Column(name="BKPF_BLDAT") private String documentDate;
	@Column(name="BKPF_BLART") private String documentType;
	@Column(name="BKPF_BUKRS") private String companyCode;
	@Column(name="BKPF_BUDAT") private String postingDate;
	@Column(name="BKPF_MONAT") private String fiscalPeriod;
	@Column(name="BKPF_WAERS") private String currency;
	@Column(name="BKPF_KURSF") private String exchangeRate;
	@Column(name="BKPF_WWERT") private String translationDate;
	@Column(name="BKPF_XBLNR") private String reference;
	@Column(name="BKPF_BKTXT") private String documentHeaderText;
	@Column(name="BKPF_BRNCH") private String branch;
	@Column(name="BKPF_GLVOR") private String businessTransaction;
	@Column(name="BKPF_GJAHR") private String fiscalYear;
	@Column(name="BKPF_USNAM") private String userName;
	@Column(name="BKPF_AWTYP") private String referenceProcedure;
	@Column(name="BKPF_AWREF") private String referenceDocumentNo;
	@Column(name="BSEG_BUZEI") private String numberOfLineItem1;
	@Column(name="BSEG_SHKZG") private String debitCredit;
	@Column(name="BSEG_BSCHL") private String postingKey;
	@Column(name="BSEG_KOART") private String accountType;
	@Column(name="BSEG_HKONT_KUNNR_LIFNR") private String account;
	@Column(name="BSEG_HKONT") private String alternativeReconcileAccount;
	@Column(name="BSEG_WRBTR") private String amountTransaction;
	@Column(name="BSEG_DMBTR") private String amountLocal;
	@Column(name="BSEG_FWBAS") private String taxBaseTransaction;
	@Column(name="BSEG_HWBAS") private String taxBaseLocal;
	@Column(name="BSEG_MWSKZ") private String taxCode;
	@Column(name="BSEG_BUPLA") private String businessPlace;
	@Column(name="BSEG_GSBER") private String businessArea;
	@Column(name="BSEG_VALUT") private String valueDate;
	@Column(name="BSEG_ZTERM") private String paymentTerm;
	@Column(name="BSEG_ZFBDT") private String baselineDate;
	@Column(name="BSEG_SKFBT") private String amountEligibleForCashDiscountInDocumentCurrency;
	@Column(name="BSEG_KOSTL") private String costCenter;
	@Column(name="BSEG_FKBER") private String functionalArea;
	@Column(name="BSEG_PROJK") private String wbs;
	@Column(name="BSEG_LSTAR") private String activityType;
	@Column(name="BSEG_SEGMENT") private String segment;
	@Column(name="BSEG_ZZWWPRD") private String product;
	@Column(name="BSEG_ZZWWSUB") private String subProduct;
	@Column(name="BSEG_ZZWWREV") private String revenueType;
	@Column(name="BSEG_MATNR") private String material;
	@Column(name="BSEG_WERKS") private String plant;
	@Column(name="BSEG_PRZNR") private String businessProcess;
	@Column(name="CE11000_WWCUS") private String customerGroup;
	@Column(name="BSEG_KIDNO") private String paymentReference;
	@Column(name="BSEG_ZUONR") private String assignment;
	@Column(name="BSEG_SGTXT") private String text;
	@Column(name="BSEG_XREF1") private String ref1;
	@Column(name="BSEG_XREF2") private String ref2;
	@Column(name="BSEG_XREF3") private String ref3;
	@Column(name="BSEG_VBUND") private String tradingPartner;
	@Column(name="BSEC_BUZEI") private String numberOfLineItem2;
	@Column(name="BSEC_ANRED") private String title;
	@Column(name="BSEC_NAME1") private String name1;
	@Column(name="BSEC_NAME2") private String name2;
	@Column(name="BSEC_NAME3") private String name3;
	@Column(name="BSEC_NAME4") private String name4;
	@Column(name="BSEC_STRAS") private String houseNoAndStreet;
	@Column(name="BSEC_ORT01") private String city;
	@Column(name="BSEC_PSTLZ") private String postalCode;
	@Column(name="BSEC_LAND1") private String countryKey;
	@Column(name="BSEC_STCD1") private String taxNo1;
	@Column(name="BSEC_STCD2") private String taxNo2;
	@Column(name="BSEC_EMPFG") private String payeeCode;
	@Column(name="BSEC_BANKL") private String bankKey;
	@Column(name="BSEC_BANKN") private String bankAccount;
	@Column(name="BSEC_BANKS") private String bankCountry;
	@Column(name="WITH_ITEM_BUZEI") private String numberOfLineItem3;
	@Column(name="WITH_ITEM_WITHT1") private String withholdingTaxType1;
	@Column(name="WITH_ITEM_WT_WITHCD1") private String withholdingTaxCode1;
	@Column(name="WITH_ITEM_WT_QSSHB1") private String withholdingTaxBaseDoc1;
	@Column(name="WITH_ITEM_WT_QSSHH1") private String withholdingTaxBaseLocal1;
	@Column(name="WITH_ITEM_WT_QBSHB1") private String withholdingTaxAmtDoc1;
	@Column(name="WITH_ITEM_WT_QBSHH1") private String withholdingTaxAmtLocal1;
	@Column(name="WITH_ITEM_WITHT2") private String withholdingTaxType2;
	@Column(name="WITH_ITEM_WT_WITHCD2") private String withholdingTaxCode2;
	@Column(name="WITH_ITEM_WT_QSSHB2") private String withholdingTaxBaseDoc2;
	@Column(name="WITH_ITEM_WT_QSSHH2") private String withholdingTaxBaseLocal2;
	@Column(name="WITH_ITEM_WT_QBSHB2") private String withholdingTaxAmtDoc2;
	@Column(name="WITH_ITEM_WT_QBSHH2") private String withholdingTaxAmtLocal2;
	@Column(name="BSET_BUZEI") private String numberOfLineItem4;
	@Column(name="BSET_MWSKZ") private String taxNoSalesCode;
	@Column(name="BSET_HKONT") private String generalLedgerAccount;
	@Column(name="BSET_SHKZG") private String debitCreditIndicator;
	@Column(name="BSET_HWBAS") private String localTaxBaseAmount;
	@Column(name="BSET_FWBAS") private String taxBaseAmount;
	@Column(name="BSET_HWSTE") private String localTaxAmount;
	@Column(name="BSET_FWSTE") private String taxAmount;
	@Column(name="BSET_KTOSL") private String transactionKey;
	@Column(name="BSET_KSCHL") private String conditionType;
	@Column(name="BSET_BUPLA") private String businessPlace2;
	@Column(name="CE11000_WWCOC") private String carrierOperator;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getRecordSequence() {
		return recordSequence;
	}
	public void setRecordSequence(String recordSequence) {
		this.recordSequence = recordSequence;
	}
	public String getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public String getFiscalPeriod() {
		return fiscalPeriod;
	}
	public void setFiscalPeriod(String fiscalPeriod) {
		this.fiscalPeriod = fiscalPeriod;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getTranslationDate() {
		return translationDate;
	}
	public void setTranslationDate(String translationDate) {
		this.translationDate = translationDate;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDocumentHeaderText() {
		return documentHeaderText;
	}
	public void setDocumentHeaderText(String documentHeaderText) {
		this.documentHeaderText = documentHeaderText;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBusinessTransaction() {
		return businessTransaction;
	}
	public void setBusinessTransaction(String businessTransaction) {
		this.businessTransaction = businessTransaction;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReferenceProcedure() {
		return referenceProcedure;
	}
	public void setReferenceProcedure(String referenceProcedure) {
		this.referenceProcedure = referenceProcedure;
	}
	public String getReferenceDocumentNo() {
		return referenceDocumentNo;
	}
	public void setReferenceDocumentNo(String referenceDocumentNo) {
		this.referenceDocumentNo = referenceDocumentNo;
	}
	public String getNumberOfLineItem1() {
		return numberOfLineItem1;
	}
	public void setNumberOfLineItem1(String numberOfLineItem1) {
		this.numberOfLineItem1 = numberOfLineItem1;
	}
	public String getDebitCredit() {
		return debitCredit;
	}
	public void setDebitCredit(String debitCredit) {
		this.debitCredit = debitCredit;
	}
	public String getPostingKey() {
		return postingKey;
	}
	public void setPostingKey(String postingKey) {
		this.postingKey = postingKey;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAlternativeReconcileAccount() {
		return alternativeReconcileAccount;
	}
	public void setAlternativeReconcileAccount(String alternativeReconcileAccount) {
		this.alternativeReconcileAccount = alternativeReconcileAccount;
	}
	public String getAmountTransaction() {
		return amountTransaction;
	}
	public void setAmountTransaction(String amountTransaction) {
		this.amountTransaction = amountTransaction;
	}
	public String getAmountLocal() {
		return amountLocal;
	}
	public void setAmountLocal(String amountLocal) {
		this.amountLocal = amountLocal;
	}
	public String getTaxBaseTransaction() {
		return taxBaseTransaction;
	}
	public void setTaxBaseTransaction(String taxBaseTransaction) {
		this.taxBaseTransaction = taxBaseTransaction;
	}
	public String getTaxBaseLocal() {
		return taxBaseLocal;
	}
	public void setTaxBaseLocal(String taxBaseLocal) {
		this.taxBaseLocal = taxBaseLocal;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getBusinessPlace() {
		return businessPlace;
	}
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public String getBaselineDate() {
		return baselineDate;
	}
	public void setBaselineDate(String baselineDate) {
		this.baselineDate = baselineDate;
	}
	public String getAmountEligibleForCashDiscountInDocumentCurrency() {
		return amountEligibleForCashDiscountInDocumentCurrency;
	}
	public void setAmountEligibleForCashDiscountInDocumentCurrency(String amountEligibleForCashDiscountInDocumentCurrency) {
		this.amountEligibleForCashDiscountInDocumentCurrency = amountEligibleForCashDiscountInDocumentCurrency;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getFunctionalArea() {
		return functionalArea;
	}
	public void setFunctionalArea(String functionalArea) {
		this.functionalArea = functionalArea;
	}
	public String getWbs() {
		return wbs;
	}
	public void setWbs(String wbs) {
		this.wbs = wbs;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSubProduct() {
		return subProduct;
	}
	public void setSubProduct(String subProduct) {
		this.subProduct = subProduct;
	}
	public String getRevenueType() {
		return revenueType;
	}
	public void setRevenueType(String revenueType) {
		this.revenueType = revenueType;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getBusinessProcess() {
		return businessProcess;
	}
	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public String getRef3() {
		return ref3;
	}
	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}
	public String getTradingPartner() {
		return tradingPartner;
	}
	public void setTradingPartner(String tradingPartner) {
		this.tradingPartner = tradingPartner;
	}
	public String getNumberOfLineItem2() {
		return numberOfLineItem2;
	}
	public void setNumberOfLineItem2(String numberOfLineItem2) {
		this.numberOfLineItem2 = numberOfLineItem2;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	public String getHouseNoAndStreet() {
		return houseNoAndStreet;
	}
	public void setHouseNoAndStreet(String houseNoAndStreet) {
		this.houseNoAndStreet = houseNoAndStreet;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountryKey() {
		return countryKey;
	}
	public void setCountryKey(String countryKey) {
		this.countryKey = countryKey;
	}
	public String getTaxNo1() {
		return taxNo1;
	}
	public void setTaxNo1(String taxNo1) {
		this.taxNo1 = taxNo1;
	}
	public String getTaxNo2() {
		return taxNo2;
	}
	public void setTaxNo2(String taxNo2) {
		this.taxNo2 = taxNo2;
	}
	public String getPayeeCode() {
		return payeeCode;
	}
	public void setPayeeCode(String payeeCode) {
		this.payeeCode = payeeCode;
	}
	public String getBankKey() {
		return bankKey;
	}
	public void setBankKey(String bankKey) {
		this.bankKey = bankKey;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankCountry() {
		return bankCountry;
	}
	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}
	public String getNumberOfLineItem3() {
		return numberOfLineItem3;
	}
	public void setNumberOfLineItem3(String numberOfLineItem3) {
		this.numberOfLineItem3 = numberOfLineItem3;
	}
	public String getWithholdingTaxType1() {
		return withholdingTaxType1;
	}
	public void setWithholdingTaxType1(String withholdingTaxType1) {
		this.withholdingTaxType1 = withholdingTaxType1;
	}
	public String getWithholdingTaxCode1() {
		return withholdingTaxCode1;
	}
	public void setWithholdingTaxCode1(String withholdingTaxCode1) {
		this.withholdingTaxCode1 = withholdingTaxCode1;
	}
	public String getWithholdingTaxBaseDoc1() {
		return withholdingTaxBaseDoc1;
	}
	public void setWithholdingTaxBaseDoc1(String withholdingTaxBaseDoc1) {
		this.withholdingTaxBaseDoc1 = withholdingTaxBaseDoc1;
	}
	public String getWithholdingTaxBaseLocal1() {
		return withholdingTaxBaseLocal1;
	}
	public void setWithholdingTaxBaseLocal1(String withholdingTaxBaseLocal1) {
		this.withholdingTaxBaseLocal1 = withholdingTaxBaseLocal1;
	}
	public String getWithholdingTaxAmtDoc1() {
		return withholdingTaxAmtDoc1;
	}
	public void setWithholdingTaxAmtDoc1(String withholdingTaxAmtDoc1) {
		this.withholdingTaxAmtDoc1 = withholdingTaxAmtDoc1;
	}
	public String getWithholdingTaxAmtLocal1() {
		return withholdingTaxAmtLocal1;
	}
	public void setWithholdingTaxAmtLocal1(String withholdingTaxAmtLocal1) {
		this.withholdingTaxAmtLocal1 = withholdingTaxAmtLocal1;
	}
	public String getWithholdingTaxType2() {
		return withholdingTaxType2;
	}
	public void setWithholdingTaxType2(String withholdingTaxType2) {
		this.withholdingTaxType2 = withholdingTaxType2;
	}
	public String getWithholdingTaxCode2() {
		return withholdingTaxCode2;
	}
	public void setWithholdingTaxCode2(String withholdingTaxCode2) {
		this.withholdingTaxCode2 = withholdingTaxCode2;
	}
	public String getWithholdingTaxBaseDoc2() {
		return withholdingTaxBaseDoc2;
	}
	public void setWithholdingTaxBaseDoc2(String withholdingTaxBaseDoc2) {
		this.withholdingTaxBaseDoc2 = withholdingTaxBaseDoc2;
	}
	public String getWithholdingTaxBaseLocal2() {
		return withholdingTaxBaseLocal2;
	}
	public void setWithholdingTaxBaseLocal2(String withholdingTaxBaseLocal2) {
		this.withholdingTaxBaseLocal2 = withholdingTaxBaseLocal2;
	}
	public String getWithholdingTaxAmtDoc2() {
		return withholdingTaxAmtDoc2;
	}
	public void setWithholdingTaxAmtDoc2(String withholdingTaxAmtDoc2) {
		this.withholdingTaxAmtDoc2 = withholdingTaxAmtDoc2;
	}
	public String getWithholdingTaxAmtLocal2() {
		return withholdingTaxAmtLocal2;
	}
	public void setWithholdingTaxAmtLocal2(String withholdingTaxAmtLocal2) {
		this.withholdingTaxAmtLocal2 = withholdingTaxAmtLocal2;
	}
	public String getNumberOfLineItem4() {
		return numberOfLineItem4;
	}
	public void setNumberOfLineItem4(String numberOfLineItem4) {
		this.numberOfLineItem4 = numberOfLineItem4;
	}
	public String getTaxNoSalesCode() {
		return taxNoSalesCode;
	}
	public void setTaxNoSalesCode(String taxNoSalesCode) {
		this.taxNoSalesCode = taxNoSalesCode;
	}
	public String getGeneralLedgerAccount() {
		return generalLedgerAccount;
	}
	public void setGeneralLedgerAccount(String generalLedgerAccount) {
		this.generalLedgerAccount = generalLedgerAccount;
	}
	public String getDebitCreditIndicator() {
		return debitCreditIndicator;
	}
	public void setDebitCreditIndicator(String debitCreditIndicator) {
		this.debitCreditIndicator = debitCreditIndicator;
	}
	public String getLocalTaxBaseAmount() {
		return localTaxBaseAmount;
	}
	public void setLocalTaxBaseAmount(String localTaxBaseAmount) {
		this.localTaxBaseAmount = localTaxBaseAmount;
	}
	public String getTaxBaseAmount() {
		return taxBaseAmount;
	}
	public void setTaxBaseAmount(String taxBaseAmount) {
		this.taxBaseAmount = taxBaseAmount;
	}
	public String getLocalTaxAmount() {
		return localTaxAmount;
	}
	public void setLocalTaxAmount(String localTaxAmount) {
		this.localTaxAmount = localTaxAmount;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getTransactionKey() {
		return transactionKey;
	}
	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public String getBusinessPlace2() {
		return businessPlace2;
	}
	public void setBusinessPlace2(String businessPlace2) {
		this.businessPlace2 = businessPlace2;
	}
	public String getCarrierOperator() {
		return carrierOperator;
	}
	public void setCarrierOperator(String carrierOperator) {
		this.carrierOperator = carrierOperator;
	}
}
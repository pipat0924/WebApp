package th.net.cat.epis.dto;

import java.io.Serializable;

/**
 * Created by imake on 29/12/2016.
 */
public class InvSummarySapIbacss implements Serializable{
    private Long id;
    private String contrno;
    private Long biRef;
    private String billgroup;
    private String accountCodeOld;
    private String accountCodeNew;
    private String segmentCode;
    private String productCode;
    private String subProductCode;
    private String revenueTypeCode;
    private String invdate;
    private String duedate;
    private String paydate;
    private String period;
    private Long amount;
    private Long vatAmount;
    private Long totalAmount;
    private String tradingPart;
    private String text;
    private String region;
    private String newCode;
    private String customerGroup;
    private String cctr;
    private String curr;
    private String taxCode;
    private Integer subscrNo;
    private String loadDate;
    private String billOrderNumber;
    private String servicePriorityProduct;
    private Long rentalCharge;
    private Long usesageCharge;
    private String processDate;
    private String serviceCode;
    private String regionSap;
    private String cctrSap;
    private String servicePriority;
    private String category;
    private String postingDate;
    private Integer calls;
    private Long ratedUnits;
    private Long primaryUnits;
    private Long secondaryUnits;
    private Long thirdUnits;
    private String property1;
    private String property2;
    private String carrierCode;
    private String carrierName;

    private String productName;
    private String subProductName;
    private String revTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContrno() {
        return contrno;
    }

    public void setContrno(String contrno) {
        this.contrno = contrno;
    }

    public Long getBiRef() {
        return biRef;
    }

    public void setBiRef(Long biRef) {
        this.biRef = biRef;
    }

    public String getBillgroup() {
        return billgroup;
    }

    public void setBillgroup(String billgroup) {
        this.billgroup = billgroup;
    }

    public String getAccountCodeOld() {
        return accountCodeOld;
    }

    public void setAccountCodeOld(String accountCodeOld) {
        this.accountCodeOld = accountCodeOld;
    }

    public String getAccountCodeNew() {
        return accountCodeNew;
    }

    public void setAccountCodeNew(String accountCodeNew) {
        this.accountCodeNew = accountCodeNew;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSubProductCode() {
        return subProductCode;
    }

    public void setSubProductCode(String subProductCode) {
        this.subProductCode = subProductCode;
    }

    public String getRevenueTypeCode() {
        return revenueTypeCode;
    }

    public void setRevenueTypeCode(String revenueTypeCode) {
        this.revenueTypeCode = revenueTypeCode;
    }

    public String getInvdate() {
        return invdate;
    }

    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Long vatAmount) {
        this.vatAmount = vatAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradingPart() {
        return tradingPart;
    }

    public void setTradingPart(String tradingPart) {
        this.tradingPart = tradingPart;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getCctr() {
        return cctr;
    }

    public void setCctr(String cctr) {
        this.cctr = cctr;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Integer getSubscrNo() {
        return subscrNo;
    }

    public void setSubscrNo(Integer subscrNo) {
        this.subscrNo = subscrNo;
    }

    public String getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    public String getBillOrderNumber() {
        return billOrderNumber;
    }

    public void setBillOrderNumber(String billOrderNumber) {
        this.billOrderNumber = billOrderNumber;
    }

    public String getServicePriorityProduct() {
        return servicePriorityProduct;
    }

    public void setServicePriorityProduct(String servicePriorityProduct) {
        this.servicePriorityProduct = servicePriorityProduct;
    }

    public Long getRentalCharge() {
        return rentalCharge;
    }

    public void setRentalCharge(Long rentalCharge) {
        this.rentalCharge = rentalCharge;
    }

    public Long getUsesageCharge() {
        return usesageCharge;
    }

    public void setUsesageCharge(Long usesageCharge) {
        this.usesageCharge = usesageCharge;
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getRegionSap() {
        return regionSap;
    }

    public void setRegionSap(String regionSap) {
        this.regionSap = regionSap;
    }

    public String getCctrSap() {
        return cctrSap;
    }

    public void setCctrSap(String cctrSap) {
        this.cctrSap = cctrSap;
    }

    public String getServicePriority() {
        return servicePriority;
    }

    public void setServicePriority(String servicePriority) {
        this.servicePriority = servicePriority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public Integer getCalls() {
        return calls;
    }

    public void setCalls(Integer calls) {
        this.calls = calls;
    }

    public Long getRatedUnits() {
        return ratedUnits;
    }

    public void setRatedUnits(Long ratedUnits) {
        this.ratedUnits = ratedUnits;
    }

    public Long getPrimaryUnits() {
        return primaryUnits;
    }

    public void setPrimaryUnits(Long primaryUnits) {
        this.primaryUnits = primaryUnits;
    }

    public Long getSecondaryUnits() {
        return secondaryUnits;
    }

    public void setSecondaryUnits(Long secondaryUnits) {
        this.secondaryUnits = secondaryUnits;
    }

    public Long getThirdUnits() {
        return thirdUnits;
    }

    public void setThirdUnits(Long thirdUnits) {
        this.thirdUnits = thirdUnits;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubProductName() {
        return subProductName;
    }

    public void setSubProductName(String subProductName) {
        this.subProductName = subProductName;
    }

    public String getRevTypeName() {
        return revTypeName;
    }

    public void setRevTypeName(String revTypeName) {
        this.revTypeName = revTypeName;
    }
}

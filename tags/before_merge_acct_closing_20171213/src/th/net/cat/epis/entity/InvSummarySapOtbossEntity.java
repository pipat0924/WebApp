package th.net.cat.epis.entity;

import javax.persistence.*;

/**
 * Created by imake on 28/12/2016.
 */
@Entity
@Table(name = "INV_SUMMARY_SAP_OTBOSS")
public class InvSummarySapOtbossEntity {
    private Long id;
    private String contrno;
    private String biRef;
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
    private String createdDate;
    private String createdBy;
    private String property1;
    private String property2;
    private String carrierCode;
    private String carrierName;
    private Integer calls;
    private Long ratedUnits;
    private Long primaryUnits;
    private Long secondaryUnits;
    private Long thirdUnits;
    private String keyRef;
    private String updateDatetime;
    @Basic
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Basic
    @Column(name = "SUB_PRODUCT_NAME")
    private String subProductName;
    @Basic
    @Column(name = "REV_TYPE_NAME")
    private String revTypeName;
    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CONTRNO")
    public String getContrno() {
        return contrno;
    }

    public void setContrno(String contrno) {
        this.contrno = contrno;
    }

    @Basic
    @Column(name = "BI_REF")
    public String getBiRef() {
        return biRef;
    }

    public void setBiRef(String biRef) {
        this.biRef = biRef;
    }

    @Basic
    @Column(name = "BILLGROUP")
    public String getBillgroup() {
        return billgroup;
    }

    public void setBillgroup(String billgroup) {
        this.billgroup = billgroup;
    }

    @Basic
    @Column(name = "ACCOUNT_CODE_OLD")
    public String getAccountCodeOld() {
        return accountCodeOld;
    }

    public void setAccountCodeOld(String accountCodeOld) {
        this.accountCodeOld = accountCodeOld;
    }

    @Basic
    @Column(name = "ACCOUNT_CODE_NEW")
    public String getAccountCodeNew() {
        return accountCodeNew;
    }

    public void setAccountCodeNew(String accountCodeNew) {
        this.accountCodeNew = accountCodeNew;
    }

    @Basic
    @Column(name = "SEGMENT_CODE")
    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    @Basic
    @Column(name = "PRODUCT_CODE")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Basic
    @Column(name = "SUB_PRODUCT_CODE")
    public String getSubProductCode() {
        return subProductCode;
    }

    public void setSubProductCode(String subProductCode) {
        this.subProductCode = subProductCode;
    }

    @Basic
    @Column(name = "REVENUE_TYPE_CODE")
    public String getRevenueTypeCode() {
        return revenueTypeCode;
    }

    public void setRevenueTypeCode(String revenueTypeCode) {
        this.revenueTypeCode = revenueTypeCode;
    }

    @Basic
    @Column(name = "INVDATE")
    public String getInvdate() {
        return invdate;
    }

    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    @Basic
    @Column(name = "DUEDATE")
    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    @Basic
    @Column(name = "PAYDATE")
    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    @Basic
    @Column(name = "PERIOD")
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Basic
    @Column(name = "AMOUNT")
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "VAT_AMOUNT")
    public Long getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Long vatAmount) {
        this.vatAmount = vatAmount;
    }

    @Basic
    @Column(name = "TOTAL_AMOUNT")
    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "TRADING_PART")
    public String getTradingPart() {
        return tradingPart;
    }

    public void setTradingPart(String tradingPart) {
        this.tradingPart = tradingPart;
    }

    @Basic
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "REGION")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "NEW_CODE")
    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    @Basic
    @Column(name = "CUSTOMER_GROUP")
    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    @Basic
    @Column(name = "CCTR")
    public String getCctr() {
        return cctr;
    }

    public void setCctr(String cctr) {
        this.cctr = cctr;
    }

    @Basic
    @Column(name = "CURR")
    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    @Basic
    @Column(name = "TAX_CODE")
    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Basic
    @Column(name = "SUBSCR_NO")
    public Integer getSubscrNo() {
        return subscrNo;
    }

    public void setSubscrNo(Integer subscrNo) {
        this.subscrNo = subscrNo;
    }

    @Basic
    @Column(name = "LOAD_DATE")
    public String getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    @Basic
    @Column(name = "BILL_ORDER_NUMBER")
    public String getBillOrderNumber() {
        return billOrderNumber;
    }

    public void setBillOrderNumber(String billOrderNumber) {
        this.billOrderNumber = billOrderNumber;
    }

    @Basic
    @Column(name = "SERVICE_PRIORITY_PRODUCT")
    public String getServicePriorityProduct() {
        return servicePriorityProduct;
    }

    public void setServicePriorityProduct(String servicePriorityProduct) {
        this.servicePriorityProduct = servicePriorityProduct;
    }

    @Basic
    @Column(name = "RENTAL_CHARGE")
    public Long getRentalCharge() {
        return rentalCharge;
    }

    public void setRentalCharge(Long rentalCharge) {
        this.rentalCharge = rentalCharge;
    }

    @Basic
    @Column(name = "USESAGE_CHARGE")
    public Long getUsesageCharge() {
        return usesageCharge;
    }

    public void setUsesageCharge(Long usesageCharge) {
        this.usesageCharge = usesageCharge;
    }

    @Basic
    @Column(name = "PROCESS_DATE")
    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    @Basic
    @Column(name = "SERVICE_CODE")
    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Basic
    @Column(name = "REGION_SAP")
    public String getRegionSap() {
        return regionSap;
    }

    public void setRegionSap(String regionSap) {
        this.regionSap = regionSap;
    }

    @Basic
    @Column(name = "CCTR_SAP")
    public String getCctrSap() {
        return cctrSap;
    }

    public void setCctrSap(String cctrSap) {
        this.cctrSap = cctrSap;
    }

    @Basic
    @Column(name = "SERVICE_PRIORITY")
    public String getServicePriority() {
        return servicePriority;
    }

    public void setServicePriority(String servicePriority) {
        this.servicePriority = servicePriority;
    }

    @Basic
    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "POSTING_DATE")
    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    @Basic
    @Column(name = "CREATED_DATE")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "CREATED_BY")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "PROPERTY1")
    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    @Basic
    @Column(name = "PROPERTY2")
    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    @Basic
    @Column(name = "CARRIER_CODE")
    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    @Basic
    @Column(name = "CARRIER_NAME")
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    @Basic
    @Column(name = "CALLS")
    public Integer getCalls() {
        return calls;
    }

    public void setCalls(Integer calls) {
        this.calls = calls;
    }

    @Basic
    @Column(name = "RATED_UNITS")
    public Long getRatedUnits() {
        return ratedUnits;
    }

    public void setRatedUnits(Long ratedUnits) {
        this.ratedUnits = ratedUnits;
    }

    @Basic
    @Column(name = "PRIMARY_UNITS")
    public Long getPrimaryUnits() {
        return primaryUnits;
    }

    public void setPrimaryUnits(Long primaryUnits) {
        this.primaryUnits = primaryUnits;
    }

    @Basic
    @Column(name = "SECONDARY_UNITS")
    public Long getSecondaryUnits() {
        return secondaryUnits;
    }

    public void setSecondaryUnits(Long secondaryUnits) {
        this.secondaryUnits = secondaryUnits;
    }

    @Basic
    @Column(name = "THIRD_UNITS")
    public Long getThirdUnits() {
        return thirdUnits;
    }

    public void setThirdUnits(Long thirdUnits) {
        this.thirdUnits = thirdUnits;
    }

    @Basic
    @Column(name = "KEY_REF")
    public String getKeyRef() {
        return keyRef;
    }

    public void setKeyRef(String keyRef) {
        this.keyRef = keyRef;
    }

    @Basic
    @Column(name = "UPDATE_DATETIME")
    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvSummarySapOtbossEntity that = (InvSummarySapOtbossEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (contrno != null ? !contrno.equals(that.contrno) : that.contrno != null) return false;
        if (biRef != null ? !biRef.equals(that.biRef) : that.biRef != null) return false;
        if (billgroup != null ? !billgroup.equals(that.billgroup) : that.billgroup != null) return false;
        if (accountCodeOld != null ? !accountCodeOld.equals(that.accountCodeOld) : that.accountCodeOld != null)
            return false;
        if (accountCodeNew != null ? !accountCodeNew.equals(that.accountCodeNew) : that.accountCodeNew != null)
            return false;
        if (segmentCode != null ? !segmentCode.equals(that.segmentCode) : that.segmentCode != null) return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (subProductCode != null ? !subProductCode.equals(that.subProductCode) : that.subProductCode != null)
            return false;
        if (revenueTypeCode != null ? !revenueTypeCode.equals(that.revenueTypeCode) : that.revenueTypeCode != null)
            return false;
        if (invdate != null ? !invdate.equals(that.invdate) : that.invdate != null) return false;
        if (duedate != null ? !duedate.equals(that.duedate) : that.duedate != null) return false;
        if (paydate != null ? !paydate.equals(that.paydate) : that.paydate != null) return false;
        if (period != null ? !period.equals(that.period) : that.period != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (vatAmount != null ? !vatAmount.equals(that.vatAmount) : that.vatAmount != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (tradingPart != null ? !tradingPart.equals(that.tradingPart) : that.tradingPart != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (newCode != null ? !newCode.equals(that.newCode) : that.newCode != null) return false;
        if (customerGroup != null ? !customerGroup.equals(that.customerGroup) : that.customerGroup != null)
            return false;
        if (cctr != null ? !cctr.equals(that.cctr) : that.cctr != null) return false;
        if (curr != null ? !curr.equals(that.curr) : that.curr != null) return false;
        if (taxCode != null ? !taxCode.equals(that.taxCode) : that.taxCode != null) return false;
        if (subscrNo != null ? !subscrNo.equals(that.subscrNo) : that.subscrNo != null) return false;
        if (loadDate != null ? !loadDate.equals(that.loadDate) : that.loadDate != null) return false;
        if (billOrderNumber != null ? !billOrderNumber.equals(that.billOrderNumber) : that.billOrderNumber != null)
            return false;
        if (servicePriorityProduct != null ? !servicePriorityProduct.equals(that.servicePriorityProduct) : that.servicePriorityProduct != null)
            return false;
        if (rentalCharge != null ? !rentalCharge.equals(that.rentalCharge) : that.rentalCharge != null) return false;
        if (usesageCharge != null ? !usesageCharge.equals(that.usesageCharge) : that.usesageCharge != null)
            return false;
        if (processDate != null ? !processDate.equals(that.processDate) : that.processDate != null) return false;
        if (serviceCode != null ? !serviceCode.equals(that.serviceCode) : that.serviceCode != null) return false;
        if (regionSap != null ? !regionSap.equals(that.regionSap) : that.regionSap != null) return false;
        if (cctrSap != null ? !cctrSap.equals(that.cctrSap) : that.cctrSap != null) return false;
        if (servicePriority != null ? !servicePriority.equals(that.servicePriority) : that.servicePriority != null)
            return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (postingDate != null ? !postingDate.equals(that.postingDate) : that.postingDate != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (property1 != null ? !property1.equals(that.property1) : that.property1 != null) return false;
        if (property2 != null ? !property2.equals(that.property2) : that.property2 != null) return false;
        if (carrierCode != null ? !carrierCode.equals(that.carrierCode) : that.carrierCode != null) return false;
        if (carrierName != null ? !carrierName.equals(that.carrierName) : that.carrierName != null) return false;
        if (calls != null ? !calls.equals(that.calls) : that.calls != null) return false;
        if (ratedUnits != null ? !ratedUnits.equals(that.ratedUnits) : that.ratedUnits != null) return false;
        if (primaryUnits != null ? !primaryUnits.equals(that.primaryUnits) : that.primaryUnits != null) return false;
        if (secondaryUnits != null ? !secondaryUnits.equals(that.secondaryUnits) : that.secondaryUnits != null)
            return false;
        if (thirdUnits != null ? !thirdUnits.equals(that.thirdUnits) : that.thirdUnits != null) return false;
        if (keyRef != null ? !keyRef.equals(that.keyRef) : that.keyRef != null) return false;
        if (updateDatetime != null ? !updateDatetime.equals(that.updateDatetime) : that.updateDatetime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contrno != null ? contrno.hashCode() : 0);
        result = 31 * result + (biRef != null ? biRef.hashCode() : 0);
        result = 31 * result + (billgroup != null ? billgroup.hashCode() : 0);
        result = 31 * result + (accountCodeOld != null ? accountCodeOld.hashCode() : 0);
        result = 31 * result + (accountCodeNew != null ? accountCodeNew.hashCode() : 0);
        result = 31 * result + (segmentCode != null ? segmentCode.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (subProductCode != null ? subProductCode.hashCode() : 0);
        result = 31 * result + (revenueTypeCode != null ? revenueTypeCode.hashCode() : 0);
        result = 31 * result + (invdate != null ? invdate.hashCode() : 0);
        result = 31 * result + (duedate != null ? duedate.hashCode() : 0);
        result = 31 * result + (paydate != null ? paydate.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (vatAmount != null ? vatAmount.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (tradingPart != null ? tradingPart.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (newCode != null ? newCode.hashCode() : 0);
        result = 31 * result + (customerGroup != null ? customerGroup.hashCode() : 0);
        result = 31 * result + (cctr != null ? cctr.hashCode() : 0);
        result = 31 * result + (curr != null ? curr.hashCode() : 0);
        result = 31 * result + (taxCode != null ? taxCode.hashCode() : 0);
        result = 31 * result + (subscrNo != null ? subscrNo.hashCode() : 0);
        result = 31 * result + (loadDate != null ? loadDate.hashCode() : 0);
        result = 31 * result + (billOrderNumber != null ? billOrderNumber.hashCode() : 0);
        result = 31 * result + (servicePriorityProduct != null ? servicePriorityProduct.hashCode() : 0);
        result = 31 * result + (rentalCharge != null ? rentalCharge.hashCode() : 0);
        result = 31 * result + (usesageCharge != null ? usesageCharge.hashCode() : 0);
        result = 31 * result + (processDate != null ? processDate.hashCode() : 0);
        result = 31 * result + (serviceCode != null ? serviceCode.hashCode() : 0);
        result = 31 * result + (regionSap != null ? regionSap.hashCode() : 0);
        result = 31 * result + (cctrSap != null ? cctrSap.hashCode() : 0);
        result = 31 * result + (servicePriority != null ? servicePriority.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (postingDate != null ? postingDate.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (property1 != null ? property1.hashCode() : 0);
        result = 31 * result + (property2 != null ? property2.hashCode() : 0);
        result = 31 * result + (carrierCode != null ? carrierCode.hashCode() : 0);
        result = 31 * result + (carrierName != null ? carrierName.hashCode() : 0);
        result = 31 * result + (calls != null ? calls.hashCode() : 0);
        result = 31 * result + (ratedUnits != null ? ratedUnits.hashCode() : 0);
        result = 31 * result + (primaryUnits != null ? primaryUnits.hashCode() : 0);
        result = 31 * result + (secondaryUnits != null ? secondaryUnits.hashCode() : 0);
        result = 31 * result + (thirdUnits != null ? thirdUnits.hashCode() : 0);
        result = 31 * result + (keyRef != null ? keyRef.hashCode() : 0);
        result = 31 * result + (updateDatetime != null ? updateDatetime.hashCode() : 0);
        return result;
    }
}

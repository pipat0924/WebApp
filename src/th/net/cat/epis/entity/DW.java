package th.net.cat.epis.entity;

import javax.persistence.*;


@Entity
@Table(name = "PAYMENT_SAP_EPIS_CR_DTL")
public class DW {
    @Id
    @Column (name = "CONTRNO") private String contrno;
    @Column (name = "AR_REF") private String arRef;
    @Column (name = "PAY_LOCATION") private String payLocation;
    @Column (name = "PAY_DATE") private String payDate;
    @Column (name = "PAY_AMOUNT") private Double payAmount;
    @Column (name = "PAY_VAT") private Double payVat;
    @Column (name = "PAY_WT") private Double payWt;
    @Column (name = "GL_ACCOUNT") private String glAccount;
    @Column (name = "TRADING_PART") private String tradingPart;
    @Column (name = "BUSINESS_AREA") private String businessArea;
    @Column (name = "BUSINESS_PLACE") private String businessPlace;
    @Column (name = "REGION") private String region;
    @Column (name = "PROCESS_DATETIME") private String processDatetime;
    @Column (name = "PRODUCT_CODE") private String productCode;
    @Column (name = "LOCATION_NAME") private String locationName;
    @Column (name = "BILL_GROUP") private String billGroup;
    @Column (name = "CCTR") private String cctr;
    @Column (name = "POST_DATE") private String postDate;
    @Column (name = "RECEIPT_NO") private String receiptNo;
    @Column (name = "PAY_TOTALAMOUNT") private Double payTotalamount;
    @Column (name = "TYPE") private String type;
    @Column (name = "SUB_PRODUCT_CODE") private String subProductCode;
    @Column (name = "REVENUE_TYPE_CODE") private String revenueTypeCode;
    @Column (name = "CUSTOMER_GROUP") private String customerGroup;
    @Column (name = "REMARK") private String remark;
    @Column (name = "BILL_TYPE") private String billType;
    @Column (name = "SERVICE_PRIORITY_PRODUCT") private String servicePriorityProduct;
    @Column (name = "REVERSE_ID") private String reverseId;
    @Column (name = "REGION_DW") private String regionDw;
    @Column (name = "INV_DATE") private String invDate;
    @Column (name = "DUE_DATE") private String dueDate;
    @Column (name = "PAY_TYPE") private String payType;
    @Column (name = "DEFAULT_PROD") private String defaultProd;  
    @Column (name = "USAGE_PERIOD") private String usagePeriod;
    @Column (name = "COLLECTION_UNIT") private String collectionUnit;
    @Column (name = "COLLECTION_CODE") private String collectionCode;
    @Column (name = "SEGMENT_CODE") private String segmentCode;
    @Column (name = "STATUS_FLAG") private String statusFlag;
	public String getContrno() {
		return contrno;
	}
	public void setContrno(String contrno) {
		this.contrno = contrno;
	}
	public String getArRef() {
		return arRef;
	}
	public void setArRef(String arRef) {
		this.arRef = arRef;
	}
	public String getPayLocation() {
		return payLocation;
	}
	public void setPayLocation(String payLocation) {
		this.payLocation = payLocation;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	public Double getPayVat() {
		return payVat;
	}
	public void setPayVat(Double payVat) {
		this.payVat = payVat;
	}
	public Double getPayWt() {
		return payWt;
	}
	public void setPayWt(Double payWt) {
		this.payWt = payWt;
	}
	public String getGlAccount() {
		return glAccount;
	}
	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}
	public String getTradingPart() {
		return tradingPart;
	}
	public void setTradingPart(String tradingPart) {
		this.tradingPart = tradingPart;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	public String getBusinessPlace() {
		return businessPlace;
	}
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProcessDatetime() {
		return processDatetime;
	}
	public void setProcessDatetime(String processDatetime) {
		this.processDatetime = processDatetime;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public String getCctr() {
		return cctr;
	}
	public void setCctr(String cctr) {
		this.cctr = cctr;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public Double getPayTotalamount() {
		return payTotalamount;
	}
	public void setPayTotalamount(Double payTotalamount) {
		this.payTotalamount = payTotalamount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getServicePriorityProduct() {
		return servicePriorityProduct;
	}
	public void setServicePriorityProduct(String servicePriorityProduct) {
		this.servicePriorityProduct = servicePriorityProduct;
	}
	public String getReverseId() {
		return reverseId;
	}
	public void setReverseId(String reverseId) {
		this.reverseId = reverseId;
	}
	public String getRegionDw() {
		return regionDw;
	}
	public void setRegionDw(String regionDw) {
		this.regionDw = regionDw;
	}
	public String getInvDate() {
		return invDate;
	}
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getDefaultProd() {
		return defaultProd;
	}
	public void setDefaultProd(String defaultProd) {
		this.defaultProd = defaultProd;
	}
	public String getUsagePeriod() {
		return usagePeriod;
	}
	public void setUsagePeriod(String usagePeriod) {
		this.usagePeriod = usagePeriod;
	}
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	public String getSegmentCode() {
		return segmentCode;
	}
	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	@Override
	public String toString() {
		return "DW [contrno=" + contrno + ", arRef=" + arRef + ", payLocation=" + payLocation + ", payDate=" + payDate
				+ ", payAmount=" + payAmount + ", payVat=" + payVat + ", payWt=" + payWt + ", glAccount=" + glAccount
				+ ", tradingPart=" + tradingPart + ", businessArea=" + businessArea + ", businessPlace=" + businessPlace
				+ ", region=" + region + ", processDatetime=" + processDatetime + ", productCode=" + productCode
				+ ", locationName=" + locationName + ", billGroup=" + billGroup + ", cctr=" + cctr + ", postDate="
				+ postDate + ", receiptNo=" + receiptNo + ", payTotalamount=" + payTotalamount + ", type=" + type
				+ ", subProductCode=" + subProductCode + ", revenueTypeCode=" + revenueTypeCode + ", customerGroup="
				+ customerGroup + ", remark=" + remark + ", billType=" + billType + ", servicePriorityProduct="
				+ servicePriorityProduct + ", reverseId=" + reverseId + ", regionDw=" + regionDw + ", invDate="
				+ invDate + ", dueDate=" + dueDate + ", payType=" + payType + ", defaultProd=" + defaultProd
				+ ", usagePeriod=" + usagePeriod + ", collectionUnit=" + collectionUnit + ", collectionCode="
				+ collectionCode + ", segmentCode=" + segmentCode + ", statusFlag=" + statusFlag  +"]";
	}
    
    
}

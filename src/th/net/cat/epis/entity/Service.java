package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="TMPINVOICESERVICE")
public class Service {

    @Id
    @SequenceGenerator(name="service_seq", sequenceName="service_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="service_seq")
    @Column(name="SERVICEID") private Long id;
    @Column(name="RECEIPTID") private Long receiptId;
    @Column(name="INVOICEID") private Long invoiceId;
    @Column(name="ACCOUNTNO") private String accountNo;
    @Column(name="SERVICENO") private String serviceNo;
    @Column(name="SERVICECODE") private String serviceCode;
    @Column(name="SERVICENAME") private String serviceName;
    @Column(name="SERVICEGROUP") private String serviceGroup;
    @Column(name="PRODUCTCODE") private String productCode;
    @Column(name="PRODUCTNAME") private String productName;
    @Column(name="PRODUCTSUBNO") private String productSubCode;
    @Column(name="PRODUCTSUBNAME") private String productSubName;
    @Column(name="INCOMETYPE") private String incomeType;
    @Column(name="INCOMEUNIT") private String incomeUnit;
    @Column(name="INCOMEAMOUNT") private BigDecimal incomeAmount;
    @Column(name="PROMOTIONCODE") private String promotionCode;
    @Column(name="PROMOTIONNAME") private String promotionName;
    @Column(name="REFTRANSID") private String refTransId;
    @Column(name="ORDERID") private String orderId;
    @Column(name="MDN") private String mdn;
    @Column(name="ICCID") private String iccid;
    @Column(name="AMOUNT") private BigDecimal amount;
    @Column(name="DISCOUNT") private BigDecimal discount;
    @Column(name="AFTERSALEDISCOUNT") private BigDecimal afterSaleDiscount;
    @Column(name="CHARGE") private BigDecimal charge;
    @Column(name="VATRATE") private BigDecimal vatRate;
    @Column(name="VAT") private BigDecimal vat;
    @Column(name="TOTALCHARGE") private BigDecimal totalCharge;
    @Column(name="DEDUCTION") private BigDecimal deduction;
    @Column(name="ATTRIBUTES") private String attributes;
    @Column(name="UPDATEDTTM") private Date updateDttm;
    @Column(name="UPDATEUSER") private String updateUser;
    @Column(name="SERVICE_QTY") private Integer serviceQty;
    @Column(name="PAYMENTID") private Long paymentId;  // Add by Puthy 25-05-2017
    @Column(name="REF1") private String ref1;  // Add by Puthy 25-05-2017
    @Column(name="REF2") private String ref2; // Add by Puthy 25-05-2017
    @Column(name="COST_PER_UNIT") private BigDecimal costPerUnit;
    @Column(name="PROFIT_CENTER_CODE") private String profitCode;
    @Column(name="PROFIT_CENTER_NAME") private String profitName;
    @Column(name="GL_ACCOUNT") private String glAccount;
    @Column(name="SEGMENT_CODE")private Long segmentCode;
    @Column(name="SEGMENT_NAME")private String segmentName;
    @Column(name="REVENUE_TYPE_CODE")private String revenueTypeCode;
    @Column(name="REVENUE_TYPE_NAME")private String revenueTypeName;
    @Column(name="SPECIAL_DISCOUNT") private BigDecimal specialDiscount;
    @Column(name="REF_ORDERID") private String refOrderId;
    @Column(name="CUSTOMER_GROUP_CODE") private String groupCode;
    @Column(name="CUSTOMER_GROUP_NAME") private String groupName;
    @OneToMany(mappedBy="serviceId", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Set<Transaction> transactions = new HashSet<Transaction>();

    @Transient private String serviceTypeName;//by NSD 16-02-2017

    public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getReceiptId() {
        return receiptId;
    }
    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }
    public Long getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getServiceNo() {
        return serviceNo;
    }
    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }
    public String getServiceCode() {
        return serviceCode;
    }
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceGroup() {
        return serviceGroup;
    }
    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductSubCode() {
        return productSubCode;
    }
    public void setProductSubCode(String productSubCode) {
        this.productSubCode = productSubCode;
    }
    public String getProductSubName() {
        return productSubName;
    }
    public void setProductSubName(String productSubName) {
        this.productSubName = productSubName;
    }
    public String getIncomeType() {
        return incomeType;
    }
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
    public String getIncomeUnit() {
        return incomeUnit;
    }
    public void setIncomeUnit(String incomeUnit) {
        this.incomeUnit = incomeUnit;
    }
    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }
    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }
    public String getPromotionCode() {
        return promotionCode;
    }
    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }
    public String getPromotionName() {
        return promotionName;
    }
    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }
    public String getRefTransId() {
        return refTransId;
    }
    public void setRefTransId(String refTransId) {
        this.refTransId = refTransId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getMdn() {
        return mdn;
    }
    public void setMdn(String mdn) {
        this.mdn = mdn;
    }
    public String getIccid() {
        return iccid;
    }
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public BigDecimal getCharge() {
        return charge;
    }
    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }
    public BigDecimal getVatRate() {
        return vatRate;
    }
    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }
    public BigDecimal getVat() {
        return vat;
    }
    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
    public BigDecimal getTotalCharge() {
        return totalCharge;
    }
    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }
    public BigDecimal getDeduction() {
        return deduction;
    }
    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }
    public String getAttributes() {
        return attributes;
    }
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    public Date getUpdateDttm() {
        return updateDttm;
    }
    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public Set<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public Integer getServiceQty() {
        return serviceQty;
    }

    public void setServiceQty(Integer serviceQty) {
        this.serviceQty = serviceQty;
    }

    public BigDecimal getAfterSaleDiscount() {
        return afterSaleDiscount;
    }

    public void setAfterSaleDiscount(BigDecimal afterSaleDiscount) {
        this.afterSaleDiscount = afterSaleDiscount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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
    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(BigDecimal costPerUnit) { this.costPerUnit = costPerUnit; }

    public String getProfitCode() { return profitCode; }

    public void setProfitCode(String profitCode) { this.profitCode = profitCode; }

    public String getProfitName() { return profitName; }

    public void setProfitName(String profitName) { this.profitName = profitName; }

    public String getGlAccount() { return glAccount; }

    public void setGlAccount(String glAccount) { this.glAccount = glAccount; }

    public Long getSegmentCode() { return segmentCode; }

    public void setSegmentCode(Long segmentCode) { this.segmentCode = segmentCode; }

    public String getSegmentName() { return segmentName; }

    public void setSegmentName(String segmentName) { this.segmentName = segmentName; }

    public String getRevenueTypeCode() { return revenueTypeCode; }

    public void setRevenueTypeCode(String revenueTypeCode) { this.revenueTypeCode = revenueTypeCode; }

    public String getRevenueTypeName() { return revenueTypeName; }

    public void setRevenueTypeName(String revenueTypeName) { this.revenueTypeName = revenueTypeName; }

    public BigDecimal getSpecialDiscount() { return specialDiscount; }

    public void setSpecialDiscount(BigDecimal specialDiscount) { this.specialDiscount = specialDiscount; }
    
	public String getRefOrderId() {
		return refOrderId;
	}
	public void setRefOrderId(String refOrderId) {
		this.refOrderId = refOrderId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
    
    
}




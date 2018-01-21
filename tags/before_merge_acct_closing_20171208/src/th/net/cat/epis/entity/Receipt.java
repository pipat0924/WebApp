package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import th.net.cat.epis.util.AppUtil;

@Entity
@Table(name="CORRECEIPT")
public class Receipt {

	@Id
	@SequenceGenerator(name="receipt_seq", sequenceName="receipt_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="receipt_seq")
	@Column(name="RECEIPTID") private Long id;
	@Column(name="RECEIPTNO") private String no;
	@Column(name="RECEIPTNAME") private String name;
	@Column(name="RECEIPTTYPE") private String type;
	@Column(name="RECEIPTDTTM") private Date docDttm;
	@Column(name="ACCOUNTNO") private String accountNo;
	@Column(name="ACCOUNTSUBNO") private String accountSubNo;
	@Column(name="ACCOUNTTYPE") private String accountType;
	@Column(name="ACCOUNTNAME") private String accountName;
	@Column(name="ACCOUNTBRANCH") private String accountBranch;
	@Column(name="ADDRESSLINE1") private String addrLine1;
	@Column(name="ADDRESSLINE2") private String addrLine2;
	@Column(name="PAYMENTCASE") private String paymentCase;
	@Column(name="BRANCHCODE") private String branchCode;
	@Column(name="BRANCHAREA") private String branchArea;
	@Column(name="BRANCHNAME") private String branchName;
	@Column(name="REMARK") private String remark;
	@Column(name="PROMOTION") private String promotion;
	@Column(name="REPRINT") private Long reprint;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="DISCOUNT") private BigDecimal discount;
	@Column(name="CHARGE") private BigDecimal charge;
	@Column(name="VATRATE") private BigDecimal vatRate;
	@Column(name="VAT") private BigDecimal vat;
	@Column(name="TOTALCHARGE") private BigDecimal totalCharge;
	@Column(name="DEDUCTION") private BigDecimal deduction;
	@Column(name="AFTERSALEDISCOUNT") private BigDecimal afterSaleDiscount;
	@Column(name="BALANCEDUE") private BigDecimal balanceDue;
	@Column(name="RECEIVED") private BigDecimal received;
	@Column(name="CHANGE") private BigDecimal change;
	@Column(name="ADVANCED") private BigDecimal advanced;
	@Column(name="TAXID") private String taxNo;
	@Column(name="CANCELREASON") private String cancelReason;
	@Column(name="CANCELDTTM") private Date cancelledDttm;
	@Column(name="CANCELEDBY") private String cancelledBy;
	@Column(name="ATTRIBUTES") private String attributes;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="DOCNO_SAP") private String docNo;
	@Column(name="YEAR_ACC") private Integer year;
	@Column(name="MESSAGE") private String message;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ACCOUNTID") private Customer customer;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="PAYMENTID") private Payment payment;
	@OneToMany(mappedBy="receiptId", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Invoice> invoices = new HashSet<Invoice>();
	@OneToMany(mappedBy="receiptId", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<Service> services = new HashSet<Service>();
	@Column(name="BILLING_GROUP") private String billingGroup;
	@Column(name="BILLING_GROUP_FULL") private String billingGroupFull;
	@Column(name="LANGUAGE") private String language;
	@Column(name="ENDOFDAYDTTM") private String endOfDayDttm;
	@Column(name="ISENDOFDAY") private Long isEndOfDay;
	@Transient private String refNo;//by NSD 16-02-2017
	@Transient private String serviceType;//by NSD 22-02-2017
	@Column(name="REF1") private String ref1;//by NSD 01-03-2017 to store angent code of mnp and ref_code of topup
	@Column(name="CUST_CATEGORY_DESC") private String custCategoryDesc;//by NSD 08-03-2017
    @Column(name="BILLING_SERVICE_NAME") private String billingServiceName;//by NSD 24-03-2017
	@Transient private String docDttmStr;
	@Transient private String updateDttmStr;
	@Column(name = "EXC_DISCOUNT")  private BigDecimal excDiscount;
	@Column(name = "AFTERSALEDISC_VAT") private BigDecimal afterSaleDiscVat;
	@Column(name = "FLG_HEADER") private String flgHeader;
	@Column(name = "REF_RECEIPTNO") private String refRctNo;
	@Transient private String egpNo;
	@Transient private String egpContract;
	@Column(name = "SHOP_CLOSING_ID") private Long shopClosingId;
	@Column(name = "FEES_INCOME") private BigDecimal feesIncome;
	@Column(name = "TAXID_AGENT") private String taxNoAgent;
	@Column(name = "REF2") private String ref2;
	@Column(name = "POSID") private Long posid;
	@Column(name = "POSNO") private String posno;
	@Column(name = "EMP_CLOSING_ID") private Long empClosingId;
    //@Column(name = "SHOP_CLOSING_ID") private Long shopClosingId;
    @Column(name = "CLOSING_ID") private Long closingId;
    @Column(name = "SPECIAL_DISCOUNT") private BigDecimal specialDiscount;
    @Column(name = "CURRENCYCODE") private String currencyCode;
    @Column(name="WT_AMT") private BigDecimal wtAmt;
	@Column(name="RETENTION_AMT") private BigDecimal retentionAmt;

	public Long getId() {
		return id;
	}
	public void setId(Long id) { 
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDocDttm() {
		return docDttm;
	}
	public void setDocDttm(Date docDttm) {
		this.docDttm = docDttm;
	}
	public void setDocDttm(java.util.Date docDttm) {
		this.docDttm = AppUtil.convertJavaDateToSqlDate(docDttm);
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountSubNo() {
		return accountSubNo;
	}
	public void setAccountSubNo(String accountSubNo) {
		this.accountSubNo = accountSubNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getAddrLine1() {
		return addrLine1;
	}
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}
	public String getAddrLine2() {
		return addrLine2;
	}
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}
	public String getPaymentCase() {
		return paymentCase;
	}
	public void setPaymentCase(String paymentCase) {
		this.paymentCase = paymentCase;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public String getBranchArea() {
		return branchArea;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public Long getReprint() {
		return reprint;
	}
	public void setReprint(Long reprint) {
		this.reprint = reprint;
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
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	public BigDecimal getAfterSaleDiscount() {
		return afterSaleDiscount;
	}
	public void setAfterSaleDiscount(BigDecimal afterSaleDiscount) {
		this.afterSaleDiscount = afterSaleDiscount;
	}
	public BigDecimal getReceived() {
		return received;
	}
	public void setReceived(BigDecimal received) {
		this.received = received;
	}
	public BigDecimal getChange() {
		return change;
	}
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	public BigDecimal getAdvanced() {
		return advanced;
	}
	public void setAdvanced(BigDecimal advanced) {
		this.advanced = advanced;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public Date getCancelledDttm() {
		return cancelledDttm;
	}
	public void setCancelledDttm(Date cancelledDttm) {
		this.cancelledDttm = cancelledDttm;
	}
	public void setCancelledDttm(java.util.Date cancelledDttm) {
		this.cancelledDttm = AppUtil.convertJavaDateToSqlDate(cancelledDttm);
	}
	public String getCancelledBy() {
		return cancelledBy;
	}
	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
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
	public void setUpdateDttm(java.util.Date updateDttm) {
		this.updateDttm = AppUtil.convertJavaDateToSqlDate(updateDttm);
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Payment getPayment() {
		return payment;
	}
	
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Set<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	public Set<Service> getServices() {
		return services;
	}
	public void setServices(Set<Service> services) {
		this.services = services;
	}
	public String getBillingGroup() {
		return billingGroup;
	}
	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}
	public String getBillingGroupFull() {
		return billingGroupFull;
	}
	public void setBillingGroupFull(String billingGroupFull) {
		this.billingGroupFull = billingGroupFull;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEndOfDayDttm() {
		return endOfDayDttm;
	}
	public void setEndOfDayDttm(String endOfDayDttm) {
		this.endOfDayDttm = endOfDayDttm;
	}
	public Long getIsEndOfDay() {
		return isEndOfDay;
	}
	public void setIsEndOfDay(Long isEndOfDay) {
		this.isEndOfDay = isEndOfDay;
	}

	public String getRefNo() {
		return refNo;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getCustCategoryDesc() {
		return custCategoryDesc;
	}

	public void setCustCategoryDesc(String custCategoryDesc) {
		this.custCategoryDesc = custCategoryDesc;
	}

	public String getDocDttmStr() {
		return docDttmStr;
	}

	public void setDocDttmStr(String docDttmStr) {
		this.docDttmStr = docDttmStr;
	}

	public String getUpdateDttmStr() {
		return updateDttmStr;
	}

	public void setUpdateDttmStr(String updateDttmStr) {
		this.updateDttmStr = updateDttmStr;
	}

    public String getBillingServiceName() {
        return billingServiceName;
    }

    public void setBillingServiceName(String billingServiceName) {
        this.billingServiceName = billingServiceName;
    }

	public BigDecimal getExcDiscount() {
		return excDiscount;
	}

	public void setExcDiscount(BigDecimal excDiscount) {
		this.excDiscount = excDiscount;
	}

	public BigDecimal getAfterSaleDiscVat() {
		return afterSaleDiscVat;
	}

	public void setAfterSaleDiscVat(BigDecimal afterSaleDiscVat) {
		this.afterSaleDiscVat = afterSaleDiscVat;
	}

	public String getFlgHeader() {
		return flgHeader;
	}

	public void setFlgHeader(String flgHeader) {
		this.flgHeader = flgHeader;
	}

	public String getRefRctNo() {
		return refRctNo;
	}

	public void setRefRctNo(String refRctNo) {
		this.refRctNo = refRctNo;
	}

	public String getEgpNo() { 
		return egpNo; 
	}

	public void setEgpNo(String egpNo) { 
		this.egpNo = egpNo; 
	}

	public String getEgpContract() { 
		return egpContract; 
	}

	public void setEgpContract(String egpContract) { 
		this.egpContract = egpContract; 
	}

	public Long getShopClosingId() { 
		return shopClosingId; 
	}

	public void setShopClosingId(Long shopClosingId) { 
		this.shopClosingId = shopClosingId; 
	}

	public BigDecimal getFeesIncome() {
		return feesIncome;
	}

	public void setFeesIncome(BigDecimal feesIncome) {
		this.feesIncome = feesIncome;
	}
	public String getTaxNoAgent() {
		return taxNoAgent;
	}
	public void setTaxNoAgent(String taxNoAgent) {
		this.taxNoAgent = taxNoAgent;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public Long getPosid() {
		return posid;
	}
	public void setPosid(Long posid) {
		this.posid = posid;
	}
	public String getPosno() {
		return posno;
	}
	public void setPosno(String posno) {
		this.posno = posno;
	}
	public Long getEmpClosingId() {
		return empClosingId;
	}
	public void setEmpClosingId(Long empClosingId) {
		this.empClosingId = empClosingId;
	}
	public Long getClosingId() {
		return closingId;
	}
	public void setClosingId(Long closingId) {
		this.closingId = closingId;
	}
	public BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}
	public void setSpecialDiscount(BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getWtAmt() {
		return wtAmt;
	}
	public void setWtAmt(BigDecimal wtAmt) {
		this.wtAmt = wtAmt;
	}
	public BigDecimal getRetentionAmt() {
		return retentionAmt;
	}
	public void setRetentionAmt(BigDecimal retentionAmt) {
		this.retentionAmt = retentionAmt;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
/*
	public Long getShopClosingId() { return shopClosingId; }
	public void setShopClosingId(Long shopClosingId) { this.shopClosingId = shopClosingId; }
*/

	
	
}
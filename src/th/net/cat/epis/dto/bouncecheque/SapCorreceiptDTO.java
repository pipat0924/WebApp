package th.net.cat.epis.dto.bouncecheque;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class SapCorreceiptDTO {
	private String receiptid;                              
	private String paymentid;                             
	private String accountid;                              
	private String receiptno;                              
	private String receiptname;                            
	private String receipttype;                            
	private Timestamp receiptdttm;                         
	private String accountno;                              
	private String accountsubno;                           
	private String accounttype;                            
	private String accountname;                            
	private String accountbranch;                          
	private String taxid;                                  
	private String addressline1;                           
	private String addressline2;                           
	private String paymentcase;                            
	private String branchcode;                             
	private String brancharea;                             
	private String branchname;                             
	private BigDecimal amount;                             
	private BigDecimal discount;                           
	private Double charge;                             
	private BigDecimal vatrate;                            
	private Double vat;                                
	private Double totalcharge;                        
	private BigDecimal deduction;                          
	private BigDecimal aftersalediscount;                  
	private BigDecimal balancedue;                         
	private BigDecimal received;                           
	private BigDecimal change;                             
	private BigDecimal advanced;            
	private String remark;                  
	private String promotion;               
	private BigDecimal reprint;             
	private String cancelreason;            
	private String canceldttm;              
	private String canceledby;              
	private String attributes;              
	private String endofdaydttm;            
	private String isendofday;              
	private Timestamp updatedttm;           
	private String updatesystem;            
	private String updateuser;              
	private BigDecimal versionstamp;        
	private String billingGroup;           
	private String billingGroupFull;      
	private String language;                
	private String ref1;                    
	private String custCategoryDesc;      
	private String billingServiceName;    
	private BigDecimal excDiscount;        
	private BigDecimal aftersalediscVat;   
	private String flgHeader;              
	private String refReceiptno;           
	private BigDecimal shopClosingId;     
	private Double exchangeRate;       
	private String currencycode;            
	private BigDecimal feesIncome;         
	private String taxidAgent;             
	private String ref2;                    
	private String closingId;              
	private String empClosingId;          
	private String posid;                   
	private String posno;                  
	private String fullPayment;            
	private String specialDiscount;        
	private String wtAmt;                  
	private String retentionAmt;           
	private String docnoSap;               
	private String yearAcc;                
	private String message;                 
	private String cancelCode;
	private String currencyCodeAr;   
	private Double transBalanceDue; 
	private String ref3ServiceName;
	private Double exchRatePaid;
	private Date exchRateDatePaid;
	private String refSapPeriod;
	private String invoiceid;           
	private String invoiceno;        
	private String paymenttype;      
	private String kenan;             
	private String issuedate;        
	private String duedate;                
	private Double debtamount;       
	private String status;           
	private String billcycle;           
	private String subno;            
	private String discType;        
	private String discApprUser;   
	private String source;           
	private String currancyAr;      
	private String exchRateAr;     
	private String exchRateDateAr;
	private Double vattm; 
	
	public String getReceiptid() {
		return receiptid;
	}
	public void setReceiptid(String receiptid) {
		this.receiptid = receiptid;
	}
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	public String getReceiptname() {
		return receiptname;
	}
	public void setReceiptname(String receiptname) {
		this.receiptname = receiptname;
	}
	public String getReceipttype() {
		return receipttype;
	}
	public void setReceipttype(String receipttype) {
		this.receipttype = receipttype;
	}
	public Timestamp getReceiptdttm() {
		return receiptdttm;
	}
	public void setReceiptdttm(Timestamp receiptdttm) {
		this.receiptdttm = receiptdttm;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getAccountsubno() {
		return accountsubno;
	}
	public void setAccountsubno(String accountsubno) {
		this.accountsubno = accountsubno;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getAccountbranch() {
		return accountbranch;
	}
	public void setAccountbranch(String accountbranch) {
		this.accountbranch = accountbranch;
	}
	public String getTaxid() {
		return taxid;
	}
	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getPaymentcase() {
		return paymentcase;
	}
	public void setPaymentcase(String paymentcase) {
		this.paymentcase = paymentcase;
	}
	public String getBranchcode() {
		return branchcode;
	}
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}
	public String getBrancharea() {
		return brancharea;
	}
	public void setBrancharea(String brancharea) {
		this.brancharea = brancharea;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
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
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	public BigDecimal getVatrate() {
		return vatrate;
	}
	public void setVatrate(BigDecimal vatrate) {
		this.vatrate = vatrate;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getTotalcharge() {
		return totalcharge;
	}
	public void setTotalcharge(Double totalcharge) {
		this.totalcharge = totalcharge;
	}
	public BigDecimal getDeduction() {
		return deduction;
	}
	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}
	public BigDecimal getAftersalediscount() {
		return aftersalediscount;
	}
	public void setAftersalediscount(BigDecimal aftersalediscount) {
		this.aftersalediscount = aftersalediscount;
	}
	public BigDecimal getBalancedue() {
		return balancedue;
	}
	public void setBalancedue(BigDecimal balancedue) {
		this.balancedue = balancedue;
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
	public BigDecimal getReprint() {
		return reprint;
	}
	public void setReprint(BigDecimal reprint) {
		this.reprint = reprint;
	}
	public String getCancelreason() {
		return cancelreason;
	}
	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}
	public String getCanceldttm() {
		return canceldttm;
	}
	public void setCanceldttm(String canceldttm) {
		this.canceldttm = canceldttm;
	}
	public String getCanceledby() {
		return canceledby;
	}
	public void setCanceledby(String canceledby) {
		this.canceledby = canceledby;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getEndofdaydttm() {
		return endofdaydttm;
	}
	public void setEndofdaydttm(String endofdaydttm) {
		this.endofdaydttm = endofdaydttm;
	}
	public String getIsendofday() {
		return isendofday;
	}
	public void setIsendofday(String isendofday) {
		this.isendofday = isendofday;
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
	public BigDecimal getVersionstamp() {
		return versionstamp;
	}
	public void setVersionstamp(BigDecimal versionstamp) {
		this.versionstamp = versionstamp;
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
	public BigDecimal getAftersalediscVat() {
		return aftersalediscVat;
	}
	public void setAftersalediscVat(BigDecimal aftersalediscVat) {
		this.aftersalediscVat = aftersalediscVat;
	}
	public String getFlgHeader() {
		return flgHeader;
	}
	public void setFlgHeader(String flgHeader) {
		this.flgHeader = flgHeader;
	}
	public String getRefReceiptno() {
		return refReceiptno;
	}
	public void setRefReceiptno(String refReceiptno) {
		this.refReceiptno = refReceiptno;
	}
	public BigDecimal getShopClosingId() {
		return shopClosingId;
	}
	public void setShopClosingId(BigDecimal shopClosingId) {
		this.shopClosingId = shopClosingId;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public BigDecimal getFeesIncome() {
		return feesIncome;
	}
	public void setFeesIncome(BigDecimal feesIncome) {
		this.feesIncome = feesIncome;
	}
	public String getTaxidAgent() {
		return taxidAgent;
	}
	public void setTaxidAgent(String taxidAgent) {
		this.taxidAgent = taxidAgent;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public String getClosingId() {
		return closingId;
	}
	public void setClosingId(String closingId) {
		this.closingId = closingId;
	}
	public String getEmpClosingId() {
		return empClosingId;
	}
	public void setEmpClosingId(String empClosingId) {
		this.empClosingId = empClosingId;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getPosno() {
		return posno;
	}
	public void setPosno(String posno) {
		this.posno = posno;
	}
	public String getFullPayment() {
		return fullPayment;
	}
	public void setFullPayment(String fullPayment) {
		this.fullPayment = fullPayment;
	}
	public String getSpecialDiscount() {
		return specialDiscount;
	}
	public void setSpecialDiscount(String specialDiscount) {
		this.specialDiscount = specialDiscount;
	}
	public String getWtAmt() {
		return wtAmt;
	}
	public void setWtAmt(String wtAmt) {
		this.wtAmt = wtAmt;
	}
	public String getRetentionAmt() {
		return retentionAmt;
	}
	public void setRetentionAmt(String retentionAmt) {
		this.retentionAmt = retentionAmt;
	}
	public String getDocnoSap() {
		return docnoSap;
	}
	public void setDocnoSap(String docnoSap) {
		this.docnoSap = docnoSap;
	}
	public String getYearAcc() {
		return yearAcc;
	}
	public void setYearAcc(String yearAcc) {
		this.yearAcc = yearAcc;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCancelCode() {
		return cancelCode;
	}
	public void setCancelCode(String cancelCode) {
		this.cancelCode = cancelCode;
	}
	public String getCurrencyCodeAr() {
		return currencyCodeAr;
	}
	public void setCurrencyCodeAr(String currencyCodeAr) {
		this.currencyCodeAr = currencyCodeAr;
	}
	public Double getTransBalanceDue() {
		return transBalanceDue;
	}
	public void setTransBalanceDue(Double transBalanceDue) {
		this.transBalanceDue = transBalanceDue;
	}
	public String getRef3ServiceName() {
		return ref3ServiceName;
	}
	public void setRef3ServiceName(String ref3ServiceName) {
		this.ref3ServiceName = ref3ServiceName;
	}
	public Double getExchRatePaid() {
		return exchRatePaid;
	}
	public void setExchRatePaid(Double exchRatePaid) {
		this.exchRatePaid = exchRatePaid;
	}
	public Date getExchRateDatePaid() {
		return exchRateDatePaid;
	}
	public void setExchRateDatePaid(Date exchRateDatePaid) {
		this.exchRateDatePaid = exchRateDatePaid;
	}
	public String getRefSapPeriod() {
		return refSapPeriod;
	}
	public void setRefSapPeriod(String refSapPeriod) {
		this.refSapPeriod = refSapPeriod;
	}
	public String getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getKenan() {
		return kenan;
	}
	public void setKenan(String kenan) {
		this.kenan = kenan;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public Double getDebtamount() {
		return debtamount;
	}
	public void setDebtamount(Double debtamount) {
		this.debtamount = debtamount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBillcycle() {
		return billcycle;
	}
	public void setBillcycle(String billcycle) {
		this.billcycle = billcycle;
	}
	public String getSubno() {
		return subno;
	}
	public void setSubno(String subno) {
		this.subno = subno;
	}
	public String getDiscType() {
		return discType;
	}
	public void setDiscType(String discType) {
		this.discType = discType;
	}
	public String getDiscApprUser() {
		return discApprUser;
	}
	public void setDiscApprUser(String discApprUser) {
		this.discApprUser = discApprUser;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCurrancyAr() {
		return currancyAr;
	}
	public void setCurrancyAr(String currancyAr) {
		this.currancyAr = currancyAr;
	}
	public String getExchRateAr() {
		return exchRateAr;
	}
	public void setExchRateAr(String exchRateAr) {
		this.exchRateAr = exchRateAr;
	}
	public String getExchRateDateAr() {
		return exchRateDateAr;
	}
	public void setExchRateDateAr(String exchRateDateAr) {
		this.exchRateDateAr = exchRateDateAr;
	}
	public Double getVattm() {
		return vattm;
	}
	public void setVattm(Double vattm) {
		this.vattm = vattm;
	}
	
	
	
	
}

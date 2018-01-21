package th.net.cat.epis.dto;

import th.net.cat.epis.entity.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentHistory {
	private Date updatePrintDate;
	private Date receiptPrintDate;
	private String receiptNo;
	private String shopPaymentName;
	private String paymentReceiver;
	private String billRefNo;
	private Date cycleDateFrom;
	private Date cycleDateTo;
	private String billCycle;
	private BigDecimal billAmount;
	private BigDecimal billAmountVat;
	private String paymentMethod;
	private String paymentCategory;
	private BigDecimal afterSaleDiscount;
	private BigDecimal transAmount;
	private String status;
	private String remark;
	
	// sap ar
	private String serviceName;
	private BigDecimal amountBillSap;
	private BigDecimal foreignExchangeRate;

	private String updatePrintDateStr;
	private String receiptPrintDateStr;
	private String currencyCode;//by NSD 02-05-2017
	public PaymentHistory() {}
	public PaymentHistory(Date updatePrintDate, Date receiptPrintDate, String receiptNo,
			String shopPaymentName, String paymentReceiver, String billRefNo,
			Date cycleDateFrom, Date cycleDateTo, String billCycle, BigDecimal billAmount,
			String paymentMethod, String paymentCategory,
			BigDecimal transAmount, String serviceName, BigDecimal foreignExchangeRate) {
		super();
		this.updatePrintDate = updatePrintDate;
		this.receiptPrintDate = receiptPrintDate;
		this.receiptNo = receiptNo;
		this.shopPaymentName = shopPaymentName;
		this.paymentReceiver = paymentReceiver;
		this.billRefNo = billRefNo;
		this.cycleDateFrom = cycleDateFrom;
		this.cycleDateTo = cycleDateTo;
		this.billCycle = billCycle;
		this.billAmount = billAmount;
		this.paymentMethod = paymentMethod;
		this.paymentCategory = paymentCategory;
		this.transAmount = transAmount;
		this.serviceName = serviceName;
		this.foreignExchangeRate = foreignExchangeRate;
	}
	private BigDecimal discount;
	private BigDecimal excDiscount;
	private Long receiptId;
	private List<Service> service;
	private String statusCode;
	private String accountName;
	private String accountNo;
	private BigDecimal charge;
	private BigDecimal vat;
	private BigDecimal totalCharge;
	private BigDecimal deduction;
	private BigDecimal balanceDue;
	private Date issueDt;
	private Date dueDt;
	private String paymentCase;
	private String accountType;
	private BigDecimal adjustAmount;
	private Long paymentId;
	private Long invoiceId;
	private Long accountId;
	public PaymentHistory(BigDecimal discount, BigDecimal excDiscount, Long receiptId, List<Service> service) {this.discount = discount; this.excDiscount = excDiscount; this.receiptId = receiptId; this.service = service;}

	public Date getUpdatePrintDate() {
		return updatePrintDate;
	}
	public void setUpdatePrintDate(Date updatePrintDate) {
		this.updatePrintDate = updatePrintDate;
	}
	public Date getReceiptPrintDate() {
		return receiptPrintDate;
	}
	public void setReceiptPrintDate(Date receiptPrintDate) {
		this.receiptPrintDate = receiptPrintDate;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getShopPaymentName() {
		return shopPaymentName;
	}
	public void setShopPaymentName(String shopPaymentName) {
		this.shopPaymentName = shopPaymentName;
	}
	public String getPaymentReceiver() {
		return paymentReceiver;
	}
	public void setPaymentReceiver(String paymentReceiver) {
		this.paymentReceiver = paymentReceiver;
	}
	public String getBillRefNo() {
		return billRefNo;
	}
	public void setBillRefNo(String billRefNo) {
		this.billRefNo = billRefNo;
	}
	public Date getCycleDateFrom() {
		return cycleDateFrom;
	}
	public void setCycleDateFrom(Date cycleDateFrom) {
		this.cycleDateFrom = cycleDateFrom;
	}
	public Date getCycleDateTo() {
		return cycleDateTo;
	}
	public void setCycleDateTo(Date cycleDateTo) {
		this.cycleDateTo = cycleDateTo;
	}
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentCategory() {
		return paymentCategory;
	}
	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdatePrintDateStr() {
		return updatePrintDateStr;
	}
	public void setUpdatePrintDateStr(String updatePrintDateStr) {
		this.updatePrintDateStr = updatePrintDateStr;
	}
	public String getReceiptPrintDateStr() {
		return receiptPrintDateStr;
	}
	public void setReceiptPrintDateStr(String receiptPrintDateStr) {
		this.receiptPrintDateStr = receiptPrintDateStr;
	}

	public BigDecimal getAfterSaleDiscount() {
		return afterSaleDiscount;
	}

	public void setAfterSaleDiscount(BigDecimal afterSaleDiscount) {
		this.afterSaleDiscount = afterSaleDiscount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getExcDiscount() { return excDiscount; }

	public void setExcDiscount(BigDecimal excDiscount) { this.excDiscount = excDiscount; }

	public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public List<Service> getService() {
		return service;
	}

	public void setService(List<Service> service) {
		this.service = service;
	}


	public BigDecimal getBillAmountVat() {
		return billAmountVat;
	}

	public void setBillAmountVat(BigDecimal billAmountVat) {
		this.billAmountVat = billAmountVat;
	}

	public String getRemark() {return remark;}

	public void setRemark(String remark) {this.remark = remark;}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
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
	public Date getIssueDt() {
		return issueDt;
	}
	public void setIssueDt(Date issueDt) {
		this.issueDt = issueDt;
	}
	public Date getDueDt() {
		return dueDt;
	}
	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}
	public String getPaymentCase() {
		return paymentCase;
	}
	public void setPaymentCase(String paymentCase) {
		this.paymentCase = paymentCase;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getRequestAmount() {
		return adjustAmount;
	}
	public void setRequestAmount(BigDecimal requestAmount) {
		this.adjustAmount = requestAmount;
	}
	public BigDecimal getAdjustAmount() {
		return adjustAmount;
	}
	public void setAdjustAmount(BigDecimal adjustAmount) {
		this.adjustAmount = adjustAmount;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public BigDecimal getAmountBillSap() {
		return amountBillSap;
	}
	public void setAmountBillSap(BigDecimal amountBillSap) {
		this.amountBillSap = amountBillSap;
	}
	public BigDecimal getForeignExchangeRate() {
		return foreignExchangeRate;
	}
	public void setForeignExchangeRate(BigDecimal foreignExchangeRate) {
		this.foreignExchangeRate = foreignExchangeRate;
	}
	
}

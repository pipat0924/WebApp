package th.net.cat.epis.dto;

import th.net.cat.epis.entity.InvoiceVatDetail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Invoice {

	protected String invoiceNo;
	protected String customerNo;
	protected String customerName;
	protected String serviceType;
	protected String billingGroup;
	
	// fields to display bills
	protected String billNo;
	protected Date issueDate;
	protected Date dueDate;
	protected BigDecimal amountBeforeTax;
	protected BigDecimal discount;
	protected BigDecimal taxAmount;
	protected BigDecimal amountAfterTax;
	protected BigDecimal balanceDue;
	protected BigDecimal totalAdj;
	protected BigDecimal totalPaid;
	protected BigDecimal withholdingTax;
	protected Date chargeCycleFromDate;
	protected Date chargeCycleToDate;
	protected String status;
	protected BigDecimal rentalCharge;
	protected BigDecimal usageCharge;
	// extra fields
	protected String accountNo;
	protected String currencycode;
	protected String taxRate;
	protected String taxtypecode;
	protected Date closedate;
	protected BigDecimal aging;
	protected List<String> subNoList;
	protected String invoiceDisplay;//by NSD 23-03-2017
	protected String chequeFailed; // 0= no , 1= yes
	protected List<InvoiceVatDetail> invoiceVatDetails;
	
	public Invoice() {}
	public Invoice(String invoiceNo, String customerNo, String customerName,
			String serviceType, String billingGroup) {
		super();
		this.invoiceNo = invoiceNo;
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.serviceType = serviceType;
		this.billingGroup = billingGroup;
	}
	public Invoice(String billNo, Date issueDate, Date dueDate,
			BigDecimal amountBeforeTax, BigDecimal discount,
			BigDecimal taxAmount, BigDecimal amountAfterTax,
			BigDecimal balanceDue, BigDecimal totalDue,
			BigDecimal withholdingTax, Date chargeCycleFromDate,
			Date chargeCycleToDate, String status, String accountNo,
			String currencycode, String taxRate, String taxtypecode,
			Date closedate) {
		super();
		this.billNo = billNo;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.amountBeforeTax = amountBeforeTax;
		this.discount = discount;
		this.taxAmount = taxAmount;
		this.amountAfterTax = amountAfterTax;
		this.balanceDue = balanceDue;
		this.totalAdj = totalDue;
		this.withholdingTax = withholdingTax;
		this.chargeCycleFromDate = chargeCycleFromDate;
		this.chargeCycleToDate = chargeCycleToDate;
		this.status = status;
		this.accountNo = accountNo;
		this.currencycode = currencycode;
		this.taxRate = taxRate;
		this.taxtypecode = taxtypecode;
		this.closedate = closedate;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getBillingGroup() {
		return billingGroup;
	}
	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public BigDecimal getAmountBeforeTax() {
		return amountBeforeTax;
	}
	public void setAmountBeforeTax(BigDecimal amountBeforeTax) {
		this.amountBeforeTax = amountBeforeTax;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getAmountAfterTax() {
		return amountAfterTax;
	}
	public void setAmountAfterTax(BigDecimal amountAfterTax) {
		this.amountAfterTax = amountAfterTax;
	}
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	public BigDecimal getTotalAdj() {
		return totalAdj;
	}
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}
	public void setTotalAdj(BigDecimal totalAdj) {
		this.totalAdj = totalAdj;
	}
	public void setTotalPaid(BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}
	public BigDecimal getWithholdingTax() {
		return withholdingTax;
	}
	public void setWithholdingTax(BigDecimal withholdingTax) {
		this.withholdingTax = withholdingTax;
	}
	public Date getChargeCycleFromDate() {
		return chargeCycleFromDate;
	}
	public void setChargeCycleFromDate(Date chargeCycleFromDate) {
		this.chargeCycleFromDate = chargeCycleFromDate;
	}
	public Date getChargeCycleToDate() {
		return chargeCycleToDate;
	}
	public void setChargeCycleToDate(Date chargeCycleToDate) {
		this.chargeCycleToDate = chargeCycleToDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getRentalCharge() {
		return rentalCharge;
	}
	public void setRentalCharge(BigDecimal rentalCharge) {
		this.rentalCharge = rentalCharge;
	}
	public BigDecimal getUsageCharge() {
		return usageCharge;
	}
	public void setUsageCharge(BigDecimal usageCharge) {
		this.usageCharge = usageCharge;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getTaxtypecode() {
		return taxtypecode;
	}
	public void setTaxtypecode(String taxtypecode) {
		this.taxtypecode = taxtypecode;
	}
	public Date getClosedate() {
		return closedate;
	}
	public void setClosedate(Date closedate) {
		this.closedate = closedate;
	}

	public BigDecimal getAging() {
		return aging;
	}

	public List<String> getSubNoList() {
		return subNoList;
	}

	public void setSubNoList(List<String> subNoList) {
		this.subNoList = subNoList;
	}

	public void setAging(BigDecimal aging) {
		this.aging = aging;
	}

	public String getInvoiceDisplay() {
		return invoiceDisplay;
	}

	public void setInvoiceDisplay(String invoiceDisplay) {
		this.invoiceDisplay = invoiceDisplay;
	}

	public List<InvoiceVatDetail> getInvoiceVatDetails() {
		return invoiceVatDetails;
	}

	public void setInvoiceVatDetails(List<InvoiceVatDetail> invoiceVatDetails) {
		this.invoiceVatDetails = invoiceVatDetails;
	}

	public String getChequeFailed() {
		return chequeFailed;
	}

	public void setChequeFailed(String chequeFailed) {
		this.chequeFailed = chequeFailed;
	}
	
}

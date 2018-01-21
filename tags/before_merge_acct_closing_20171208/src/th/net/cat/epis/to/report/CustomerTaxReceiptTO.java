package th.net.cat.epis.to.report;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerTaxReceiptTO extends EpisReportTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 8796768192436830121L;
	// Header Information
	private String receiptType;
	private String receiptFormat; // receive Only , WH Only
	private String billingGroup;
	private boolean substitute;
	private String title;
	private String receiptTitle;

	// Customer Information
	private String customerNo;
	private String customerSubNo;
	private String customerName;
	private String addressLine1;
	private String addressLine2;
	private String taxId;
	private String branchPayment;
	private String paymentType;
	private String customerType;
	
	private String egpNo;
	private String egpContract;
	
	
	//Summary Information
	private String summaryAmountBeforeVat;
	private String summaryDiscount;
	private String summaryAmountSumBeforeVat;
	private String summaryVat;
	private String summaryAmountSumAfterVat;
	private String additionalDiscountAfterVat;
	private String amountPaid;
	
	private BigDecimal summaryAmountBeforeVatTH;
	private BigDecimal summaryDiscountTH;
	private BigDecimal summaryAmountSumBeforeVatTH;
	private BigDecimal summaryVatTH;
	private BigDecimal summaryAmountSumAfterVatTH;
	private BigDecimal additionalDiscountAfterVatTH;
	
	//Addtional Information
	private String noteTxt;
	private String promotionTxt;
	
	//Detail
	private String orderNo;
	private String invoiceNo;
	private String paymentCycle;
	private String amountBeforeVat;
	private String discount;
	private String amountSumBeforeVat;
	private String vat;
	private String amountSumAfterVat;
	
	private BigDecimal amountBeforeVatTH;
	private BigDecimal amountSumBeforeVatTH;
	private BigDecimal amountSumAfterVatTH;
	//for substitute Receipt
	private String numberOfPrint;
	private String reasonOfSubstitute;

	//for offsset
	private String offsetDocumentNo;
	
//	note
	private String note;
	private String checkSpecial;
	private String amountBeforeVatTax;
	private String discountTax;
	private String amountSumBeforeVatTax;
	private String vatTax;
	private String amountSumAfterVatTax;
	private BigDecimal currencyRate;

	private BigDecimal vatRate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReceiptTitle() {
		return receiptTitle;
	}

	public void setReceiptTitle(String receiptTitle) {
		this.receiptTitle = receiptTitle;
	}	
	public boolean isSubstitute() {
		return substitute;
	}

	public void setSubstitute(boolean substitute) {
		this.substitute = substitute;
	}

	public CustomerTaxReceiptTO() {}

	public CustomerTaxReceiptTO(String receiptType, String customerNo, String customerSubNo,
			String customerName, String addressLine1, String addressLine2,
			String taxId, String branchNo, String paymentType,
			String summaryAmountBeforeVat, String summaryDiscount,
			String summaryAmountSumBeforeVat, String summaryVat,
			String summaryAmountSumAfterVat, String additionalDiscountAfterVat,
			String amountPaid, String noteTxt, String promotionTxt,
			String orderNo, String invoiceNo, String paymentCycle,
			String amountBeforeVat, String discount, String amountSumBeforeVat,
			String vat, String amountSumAfterVat) {
		super();
		this.receiptType = receiptType;
		this.customerNo = customerNo;
		this.customerSubNo = customerSubNo;
		this.customerName = customerName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.taxId = taxId;
		this.branchPayment = branchNo;
		this.paymentType = paymentType;
		this.summaryAmountBeforeVat = summaryAmountBeforeVat;
		this.summaryDiscount = summaryDiscount;
		this.summaryAmountSumBeforeVat = summaryAmountSumBeforeVat;
		this.summaryVat = summaryVat;
		this.summaryAmountSumAfterVat = summaryAmountSumAfterVat;
		this.additionalDiscountAfterVat = additionalDiscountAfterVat;
		this.amountPaid = amountPaid;
		this.noteTxt = noteTxt;
		this.promotionTxt = promotionTxt;
		this.orderNo = orderNo;
		this.invoiceNo = invoiceNo;
		this.paymentCycle = paymentCycle;
		this.amountBeforeVat = amountBeforeVat;
		this.discount = discount;
		this.amountSumBeforeVat = amountSumBeforeVat;
		this.vat = vat;
		this.amountSumAfterVat = amountSumAfterVat;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerSubNo() {
		return customerSubNo;
	}

	public void setCustomerSubNo(String customerSubNo) {
		this.customerSubNo = customerSubNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getBranchPayment() {
		return branchPayment;
	}

	public void setBranchPayment(String branchPayment) {
		this.branchPayment = branchPayment;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getSummaryAmountBeforeVat() {
		return summaryAmountBeforeVat;
	}

	public void setSummaryAmountBeforeVat(String summaryAmountBeforeVat) {
		this.summaryAmountBeforeVat = summaryAmountBeforeVat;
	}

	public String getSummaryDiscount() {
		return summaryDiscount;
	}

	public void setSummaryDiscount(String summaryDiscount) {
		this.summaryDiscount = summaryDiscount;
	}

	public String getSummaryAmountSumBeforeVat() {
		return summaryAmountSumBeforeVat;
	}

	public void setSummaryAmountSumBeforeVat(String summaryAmountSumBeforeVat) {
		this.summaryAmountSumBeforeVat = summaryAmountSumBeforeVat;
	}

	public String getSummaryVat() {
		return summaryVat;
	}

	public void setSummaryVat(String summaryVat) {
		this.summaryVat = summaryVat;
	}

	public String getSummaryAmountSumAfterVat() {
		return summaryAmountSumAfterVat;
	}

	public void setSummaryAmountSumAfterVat(String summaryAmountSumAfterVat) {
		this.summaryAmountSumAfterVat = summaryAmountSumAfterVat;
	}

	public String getAdditionalDiscountAfterVat() {
		return additionalDiscountAfterVat;
	}

	public void setAdditionalDiscountAfterVat(String additionalDiscountAfterVat) {
		this.additionalDiscountAfterVat = additionalDiscountAfterVat;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getNoteTxt() {
		return noteTxt;
	}

	public void setNoteTxt(String noteTxt) {
		this.noteTxt = noteTxt;
	}

	public String getPromotionTxt() {
		return promotionTxt;
	}

	public void setPromotionTxt(String promotionTxt) {
		this.promotionTxt = promotionTxt;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public String getAmountBeforeVat() {
		return amountBeforeVat;
	}

	public void setAmountBeforeVat(String amountBeforeVat) {
		this.amountBeforeVat = amountBeforeVat;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAmountSumBeforeVat() {
		return amountSumBeforeVat;
	}

	public void setAmountSumBeforeVat(String amountSumBeforeVat) {
		this.amountSumBeforeVat = amountSumBeforeVat;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getAmountSumAfterVat() {
		return amountSumAfterVat;
	}

	public void setAmountSumAfterVat(String amountSumAfterVat) {
		this.amountSumAfterVat = amountSumAfterVat;
	}

	public String getReceiptFormat() {
		return receiptFormat;
	}

	public void setReceiptFormat(String receiptFormat) {
		this.receiptFormat = receiptFormat;
	}

	public String getBillingGroup() {
		return billingGroup;
	}

	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}

	public String getNumberOfPrint() {
		return numberOfPrint;
	}

	public void setNumberOfPrint(String numberOfPrint) {
		this.numberOfPrint = numberOfPrint;
	}

	public String getReasonOfSubstitute() {
		return reasonOfSubstitute;
	}

	public void setReasonOfSubstitute(String reasonOfSubstitute) {
		this.reasonOfSubstitute = reasonOfSubstitute;
	}

	public String getOffsetDocumentNo() {
		return offsetDocumentNo;
	}

	public void setOffsetDocumentNo(String offsetDocumentNo) {
		this.offsetDocumentNo = offsetDocumentNo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCheckSpecial() {
		return checkSpecial;
	}

	public void setCheckSpecial(String checkSpecial) {
		this.checkSpecial = checkSpecial;
	}

	public String getAmountBeforeVatTax() {
		return amountBeforeVatTax;
	}

	public void setAmountBeforeVatTax(String amountBeforeVatTax) {
		this.amountBeforeVatTax = amountBeforeVatTax;
	}

	public String getDiscountTax() {
		return discountTax;
	}

	public void setDiscountTax(String discountTax) {
		this.discountTax = discountTax;
	}

	public String getAmountSumBeforeVatTax() {
		return amountSumBeforeVatTax;
	}

	public void setAmountSumBeforeVatTax(String amountSumBeforeVatTax) {
		this.amountSumBeforeVatTax = amountSumBeforeVatTax;
	}

	public String getVatTax() {
		return vatTax;
	}

	public void setVatTax(String vatTax) {
		this.vatTax = vatTax;
	}

	public String getAmountSumAfterVatTax() {
		return amountSumAfterVatTax;
	}

	public void setAmountSumAfterVatTax(String amountSumAfterVatTax) {
		this.amountSumAfterVatTax = amountSumAfterVatTax;
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

	public BigDecimal getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(BigDecimal currencyRate) {
		this.currencyRate = currencyRate;
	}

	public BigDecimal getSummaryAmountBeforeVatTH() {
		return summaryAmountBeforeVatTH;
	}

	public void setSummaryAmountBeforeVatTH(BigDecimal summaryAmountBeforeVatTH) {
		this.summaryAmountBeforeVatTH = summaryAmountBeforeVatTH;
	}

	public BigDecimal getSummaryDiscountTH() {
		return summaryDiscountTH;
	}

	public void setSummaryDiscountTH(BigDecimal summaryDiscountTH) {
		this.summaryDiscountTH = summaryDiscountTH;
	}

	public BigDecimal getSummaryAmountSumBeforeVatTH() {
		return summaryAmountSumBeforeVatTH;
	}

	public void setSummaryAmountSumBeforeVatTH(BigDecimal summaryAmountSumBeforeVatTH) {
		this.summaryAmountSumBeforeVatTH = summaryAmountSumBeforeVatTH;
	}

	public BigDecimal getSummaryVatTH() {
		return summaryVatTH;
	}

	public void setSummaryVatTH(BigDecimal summaryVatTH) {
		this.summaryVatTH = summaryVatTH;
	}

	public BigDecimal getSummaryAmountSumAfterVatTH() {
		return summaryAmountSumAfterVatTH;
	}

	public void setSummaryAmountSumAfterVatTH(BigDecimal summaryAmountSumAfterVatTH) {
		this.summaryAmountSumAfterVatTH = summaryAmountSumAfterVatTH;
	}

	public BigDecimal getAdditionalDiscountAfterVatTH() {
		return additionalDiscountAfterVatTH;
	}

	public void setAdditionalDiscountAfterVatTH(BigDecimal additionalDiscountAfterVatTH) {
		this.additionalDiscountAfterVatTH = additionalDiscountAfterVatTH;
	}

	public BigDecimal getAmountSumBeforeVatTH() {
		return amountSumBeforeVatTH;
	}

	public void setAmountSumBeforeVatTH(BigDecimal amountSumBeforeVatTH) {
		this.amountSumBeforeVatTH = amountSumBeforeVatTH;
	}

	public BigDecimal getAmountSumAfterVatTH() {
		return amountSumAfterVatTH;
	}

	public void setAmountSumAfterVatTH(BigDecimal amountSumAfterVatTH) {
		this.amountSumAfterVatTH = amountSumAfterVatTH;
	}

	public BigDecimal getAmountBeforeVatTH() {
		return amountBeforeVatTH;
	}

	public void setAmountBeforeVatTH(BigDecimal amountBeforeVatTH) {
		this.amountBeforeVatTH = amountBeforeVatTH;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
}

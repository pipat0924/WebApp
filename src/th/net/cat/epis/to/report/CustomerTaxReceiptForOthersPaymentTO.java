package th.net.cat.epis.to.report;

import java.io.Serializable;

import com.ibm.icu.math.BigDecimal;

public class CustomerTaxReceiptForOthersPaymentTO  extends EpisReportTO implements Serializable, Cloneable {

	private static final long serialVersionUID = -4835027764107034716L;
	// Header Information
	private String receiptType;
	private String receiptFormat;// by Puthy 08-04-2017
	private String sourceAddress;//by NSD 12-01-2017
	private String refNo;//by NSD 16-02-2017
	private String serviceType;//by NSD 22-02-2017
	private String flgHeader; //by Puthy 27-04-2017
	//by NSD 23-02-2017
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
	private String customerBranch; // AGENT OR MNP by Puthy 24-05-2017
	private String payType; // AGENT OR MNP by Puthy 24-05-2017
	
	//Summary Information
	private String summaryAmountBeforeVat;
	private String summaryDiscount;
	private String excDiscount;//specialDiscount
	private String summaryAmountSumBeforeVat;
	private String summaryVat;
	private String summaryAmountSumAfterVat;
	private String additionalDiscountAfterVat;
	private String amountPaid;
	private String stringVatRate;
	
	//Addtional Information
	private String noteTxt;
	private String promotionTxt;
	
	//Detail
	private String orderNo;
	private String productType;
	private String productName;
	private String amount;
	private String vat;
	private String discount;
	private String wat; 
	private String amountBeforeVatAfterDiscount;
	private String totalSum;
	private String productCode; //by NSD 28-02-2017
	private String currencyName;
	private String currencyCode;
	private String currencyAmount;
	private String currencyRate;
	

	//for substitute Receipt
	private String numberOfPrint;
	private String reasonOfSubstitute;
	
	public CustomerTaxReceiptForOthersPaymentTO() {}

	public CustomerTaxReceiptForOthersPaymentTO(String receiptType, String receiptFormat,
			String flgHeader, String customerNo,
			String customerSubNo, String customerName, String addressLine1,
			String addressLine2, String taxId, String branchPayment,
			String paymentType, String summaryAmountBeforeVat, String payType,
			String summaryDiscount, String summaryAmountSumBeforeVat,
			String summaryVat, String summaryAmountSumAfterVat,
			String additionalDiscountAfterVat, String amountPaid, String stringVatRate,
			String noteTxt, String promotionTxt, String orderNo,
			String productType, String productName, String amount, String vat,
			String discount, String wat, String totalSum, String customerBranch) {
		super();
		this.receiptType = receiptType;
		this.receiptFormat = receiptFormat;
		this.flgHeader = flgHeader;
		this.customerNo = customerNo;
		this.customerSubNo = customerSubNo;
		this.customerName = customerName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.taxId = taxId;
		this.branchPayment = branchPayment;
		this.paymentType = paymentType;
		this.payType = payType;
		this.summaryAmountBeforeVat = summaryAmountBeforeVat;
		this.summaryDiscount = summaryDiscount;
		this.summaryAmountSumBeforeVat = summaryAmountSumBeforeVat;
		this.summaryVat = summaryVat;
		this.summaryAmountSumAfterVat = summaryAmountSumAfterVat;
		this.additionalDiscountAfterVat = additionalDiscountAfterVat;
		this.amountPaid = amountPaid;
		this.stringVatRate = stringVatRate;
		this.noteTxt = noteTxt;
		this.promotionTxt = promotionTxt;
		this.orderNo = orderNo;
		this.productType = productType;
		this.productName = productName;
		this.amount = amount;
		this.vat = vat;
		this.discount = discount;
		this.wat = wat;
		this.totalSum = totalSum;
		this.customerBranch = customerBranch;
	}
	
	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public String getReceiptFormat() { return receiptFormat;}

	public void setReceiptFormat(String receiptFormat) { this.receiptFormat = receiptFormat;}

	public String getFlgHeader() {return flgHeader;}

	public void setFlgHeader(String flgHeader) {this.flgHeader = flgHeader;}

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

	public String getStringVatRate() {
		return stringVatRate;
	}

	public void setStringVatRate(String stringVatRate) {
		this.stringVatRate = stringVatRate;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getWat() {
		return wat;
	}

	public void setWat(String wat) {
		this.wat = wat;
	}

	public String getAmountBeforeVatAfterDiscount() {
		return amountBeforeVatAfterDiscount;
	}

	public void setAmountBeforeVatAfterDiscount(String amountBeforeVatAfterDiscount) {
		this.amountBeforeVatAfterDiscount = amountBeforeVatAfterDiscount;
	}

	public String getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(String totalSum) {
		this.totalSum = totalSum;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isSubstitute() {
		return substitute;
	}

	public void setSubstitute(boolean substitute) {
		this.substitute = substitute;
	}

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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getExcDiscount() {
		return excDiscount;
	}

	public void setExcDiscount(String excDiscount) {
		this.excDiscount = excDiscount;
	}

	public String getPayType() {return payType;}

	public void setPayType(String payType) {this.payType = payType;}

	public String getCustomerBranch() {
		return customerBranch;
	}

	public void setCustomerBranch(String customerBranch) {
		this.customerBranch = customerBranch;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(String currencyRate) {
		this.currencyRate = currencyRate;
	}

	
}

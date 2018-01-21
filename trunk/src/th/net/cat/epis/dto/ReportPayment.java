package th.net.cat.epis.dto;

import java.math.BigDecimal;

public class ReportPayment {

	private String paymentId;
	private String paymentType;
	private String receiptDate;
	private Long receiptId;
	private String receiptNo;
	private String accountNo;
	private String receiptName;
	private String receiptType;
	private String receiptCount;
	private String collectionUnit;
	private String invoiceNo;
	private String paymentCash;
	private String documentNo;
	private String charge;
	private String vat;
	private String vatRate;
	private String totalCharge;
	private String attributes;
	private String taxId;
	private String branchNo;
	private String branchName;
	
	private String productCode;
	private String deductionNo;
	private String deductionType;
	private String amount3tredecim;
	private String amount69bis;
	private String amount69tre;
	private String remark;
	private String deductionId;
	
	private String sumRowPaymentFull; 
	private String sumRowPaymentABVR; 
	private String sumRowPenalty; 
	private String sumRowRetention; 
	private String sumRowCompensation;
	private String sumAmountPaymentFull; 
	private String sumAmountPaymentABVR; 
	private String sumAmountPenalty; 
	private String sumAmountRetention; 
	private String sumAmountCompensation;
	
	private int sumCancelReceipt;
	private double sumCancelAmount;
	private int sumReceipt;
	private double sumAmount;
	private int sumTotalReceipt;
	private double sumTotalAmount;
	
	private String cancelByUser;
	private String paymentUser;
	private String payFullname;
	private String shopNo;
	private String shopName;
	
	private String egpNo;
	private String egpContract;
	private String issueDate;
	private String dueDate;
	private String billCycle;
	private String status;

	private String discount;
	private String payTypeName;
	
	private String cancelDate;

	private String serviceNo;
	private String serviceCode;

	private String refRecNo;

	private String paymentDate;
	private String updateDate;
	private String totalChargeForeign;
	private String msgForeign;
	private String refCode;
	
	
	// pichanan 20170817
	private String currencyCode;
	private String currencyRate;	
	private BigDecimal currencyRateR;
	private BigDecimal chargeR;
	private BigDecimal totalChargeR;

	private String reason;
	private int cntReceipt;
	private int cntCancleReceipt;
	private String PosNo;
	private String posName;
	private String receiptFr;
	private String receiptTo;
	private String after_sale_discount;
	private String paymentTime;
	private String chequeno;
	private String publisherid;
	private String publisher;
	private String agentCode;
	private String agentName;
	private String ref1;
	private String ref2;
	private String startDate;
	private String endDate;
	private String egpValue;
	private String billGroup;
	private String beginCharge;
	private String beginVat;
	private String beginTotalCharge;
	private String posting_date;
	private String adjCharge;
	private String adjVat;
	private String adjTotalCharge;
	private String adjPostingDate;
	private String netCharge;
	private String netVat;
	private String netTotalCharge;
	
	public String getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(String currencyRate) {
		this.currencyRate = currencyRate;
	}
	public BigDecimal getCurrencyRateR() {
		return currencyRateR;
	}
	public void setCurrencyRateR(BigDecimal currencyRateR) {
		this.currencyRateR = currencyRateR;
	}
	public BigDecimal getChargeR() {
		return chargeR;
	}
	public void setChargeR(BigDecimal chargeR) {
		this.chargeR = chargeR;
	}
	public BigDecimal getTotalChargeR() {
		return totalChargeR;
	}
	public void setTotalChargeR(BigDecimal totalChargeR) {
		this.totalChargeR = totalChargeR;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int getCntReceipt() {
		return cntReceipt;
	}
	public void setCntReceipt(int cntReceipt) {
		this.cntReceipt = cntReceipt;
	}
	public int getCntCancleReceipt() {
		return cntCancleReceipt;
	}
	public void setCntCancleReceipt(int cntCancleReceipt) {
		this.cntCancleReceipt = cntCancleReceipt;
	}
	public String getPosNo() {
		return PosNo;
	}
	public void setPosNo(String posNo) {
		PosNo = posNo;
	}
	public String getReceiptFr() {
		return receiptFr;
	}
	public void setReceiptFr(String receiptFr) {
		this.receiptFr = receiptFr;
	}
	public String getReceiptTo() {
		return receiptTo;
	}
	public void setReceiptTo(String receiptTo) {
		this.receiptTo = receiptTo;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getChequeno() {
		return chequeno;
	}
	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}
	public String getPublisherid() {
		return publisherid;
	}
	public void setPublisherid(String publisherid) {
		this.publisherid = publisherid;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	// end pichanan 20170817 
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getDeductionId() {
		return deductionId;
	}
	public void setDeductionId(String deductionId) {
		this.deductionId = deductionId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	public Long getReceiptId() {return receiptId;}
	public void setReceiptId(Long receiptId) {this.receiptId = receiptId;}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getReceiptName() {
		return receiptName;
	}
	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getPaymentCash() {
		return paymentCash;
	}
	public void setPaymentCash(String paymentCash) {
		this.paymentCash = paymentCash;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getVatRate() {
		return vatRate;
	}
	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}
	public String getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getDeductionNo() {
		return deductionNo;
	}
	public void setDeductionNo(String deductionNo) {
		this.deductionNo = deductionNo;
	}
	public String getDeductionType() {
		return deductionType;
	}
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
	public String getAmount3tredecim() {
		return amount3tredecim;
	}
	public void setAmount3tredecim(String amount3tredecim) {
		this.amount3tredecim = amount3tredecim;
	}
	public String getAmount69bis() {
		return amount69bis;
	}
	public void setAmount69bis(String amount69bis) {
		this.amount69bis = amount69bis;
	}
	public String getAmount69tre() {
		return amount69tre;
	}
	public void setAmount69tre(String amount69tre) {
		this.amount69tre = amount69tre;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSumCancelReceipt() {
		return sumCancelReceipt;
	}
	public void setSumCancelReceipt(int sumCancelReceipt) {
		this.sumCancelReceipt = sumCancelReceipt;
	}
	public double getSumCancelAmount() {
		return sumCancelAmount;
	}
	public void setSumCancelAmount(double sumCancelAmount) {
		this.sumCancelAmount = sumCancelAmount;
	}
	public int getSumReceipt() {
		return sumReceipt;
	}
	public void setSumReceipt(int sumReceipt) {
		this.sumReceipt = sumReceipt;
	}
	public double getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(double sumAmount) {
		this.sumAmount = sumAmount;
	}
	public int getSumTotalReceipt() {
		return sumTotalReceipt;
	}
	public void setSumTotalReceipt(int sumTotalReceipt) {
		this.sumTotalReceipt = sumTotalReceipt;
	}
	public double getSumTotalAmount() {
		return sumTotalAmount;
	}
	public void setSumTotalAmount(double sumTotalAmount) {
		this.sumTotalAmount = sumTotalAmount;
	}
	public String getCancelByUser() {
		return cancelByUser;
	}
	public void setCancelByUser(String cancelByUser) {
		this.cancelByUser = cancelByUser;
	}
	public String getPaymentUser() {
		return paymentUser;
	}
	public void setPaymentUser(String paymentUser) {
		this.paymentUser = paymentUser;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getPayFullname() {
		return payFullname;
	}
	public void setPayFullname(String payFullname) {
		this.payFullname = payFullname;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getReceiptCount() {
		return receiptCount;
	}
	public void setReceiptCount(String receiptCount) {
		this.receiptCount = receiptCount;
	}
	public String getSumRowPaymentFull() {
		return sumRowPaymentFull;
	}
	public void setSumRowPaymentFull(String sumRowPaymentFull) {
		this.sumRowPaymentFull = sumRowPaymentFull;
	}
	public String getSumRowPaymentABVR() {
		return sumRowPaymentABVR;
	}
	public void setSumRowPaymentABVR(String sumRowPaymentABVR) {
		this.sumRowPaymentABVR = sumRowPaymentABVR;
	}
	public String getSumRowPenalty() {
		return sumRowPenalty;
	}
	public void setSumRowPenalty(String sumRowPenalty) {
		this.sumRowPenalty = sumRowPenalty;
	}
	public String getSumRowRetention() {
		return sumRowRetention;
	}
	public void setSumRowRetention(String sumRowRetention) {
		this.sumRowRetention = sumRowRetention;
	}
	public String getSumRowCompensation() {
		return sumRowCompensation;
	}
	public void setSumRowCompensation(String sumRowCompensation) {
		this.sumRowCompensation = sumRowCompensation;
	}
	public String getSumAmountPaymentFull() {
		return sumAmountPaymentFull;
	}
	public void setSumAmountPaymentFull(String sumAmountPaymentFull) {
		this.sumAmountPaymentFull = sumAmountPaymentFull;
	}
	public String getSumAmountPaymentABVR() {
		return sumAmountPaymentABVR;
	}
	public void setSumAmountPaymentABVR(String sumAmountPaymentABVR) {
		this.sumAmountPaymentABVR = sumAmountPaymentABVR;
	}
	public String getSumAmountPenalty() {
		return sumAmountPenalty;
	}
	public void setSumAmountPenalty(String sumAmountPenalty) {
		this.sumAmountPenalty = sumAmountPenalty;
	}
	public String getSumAmountRetention() {
		return sumAmountRetention;
	}
	public void setSumAmountRetention(String sumAmountRetention) {
		this.sumAmountRetention = sumAmountRetention;
	}
	public String getSumAmountCompensation() {
		return sumAmountCompensation;
	}
	public void setSumAmountCompensation(String sumAmountCompensation) {
		this.sumAmountCompensation = sumAmountCompensation;
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
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getServiceNo() {return serviceNo;}
	public void setServiceNo(String serviceNo) {this.serviceNo = serviceNo;}

	public String getRefRecNo() {
		return refRecNo;
	}

	public void setRefRecNo(String refRecNo) {
		this.refRecNo = refRecNo;
	}

	public String getMsgForeign() {
		return msgForeign;
	}

	public void setMsgForeign(String msgForeign) {
		this.msgForeign = msgForeign;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getTotalChargeForeign() {
		return totalChargeForeign;
	}

	public void setTotalChargeForeign(String totalChargeForeign) {
		this.totalChargeForeign = totalChargeForeign;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	
	public String getAfter_sale_discount() {
		return after_sale_discount;
	}
	public void setAfter_sale_discount(String after_sale_discount) {
		this.after_sale_discount = after_sale_discount;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEgpValue() {
		return egpValue;
	}
	public void setEgpValue(String egpValue) {
		this.egpValue = egpValue;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public String getBeginCharge() {
		return beginCharge;
	}
	public void setBeginCharge(String beginCharge) {
		this.beginCharge = beginCharge;
	}
	public String getBeginVat() {
		return beginVat;
	}
	public void setBeginVat(String beginVat) {
		this.beginVat = beginVat;
	}
	public String getBeginTotalCharge() {
		return beginTotalCharge;
	}
	public void setBeginTotalCharge(String beginTotalCharge) {
		this.beginTotalCharge = beginTotalCharge;
	}
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	public String getAdjCharge() {
		return adjCharge;
	}
	public void setAdjCharge(String adjCharge) {
		this.adjCharge = adjCharge;
	}
	public String getAdjVat() {
		return adjVat;
	}
	public void setAdjVat(String adjVat) {
		this.adjVat = adjVat;
	}
	public String getAdjTotalCharge() {
		return adjTotalCharge;
	}
	public void setAdjTotalCharge(String adjTotalCharge) {
		this.adjTotalCharge = adjTotalCharge;
	}
	public String getAdjPostingDate() {
		return adjPostingDate;
	}
	public void setAdjPostingDate(String adjPostingDate) {
		this.adjPostingDate = adjPostingDate;
	}
	public String getNetCharge() {
		return netCharge;
	}
	public void setNetCharge(String netCharge) {
		this.netCharge = netCharge;
	}
	public String getNetVat() {
		return netVat;
	}
	public void setNetVat(String netVat) {
		this.netVat = netVat;
	}
	public String getNetTotalCharge() {
		return netTotalCharge;
	}
	public void setNetTotalCharge(String netTotalCharge) {
		this.netTotalCharge = netTotalCharge;
	}
	
}

package th.net.cat.epis.to.report;

import java.io.Serializable;
import java.math.BigDecimal;

public class DailyPaymentTO extends EpisReportTO implements Serializable, Cloneable {

	private static long serialVersionUID = 8796768192436830121L;
	
	private String rowNo;
	private String pos;
	private String paymentDate;
	private String paymentType;
	private String receiptDate;
	private String receiptNo;
	private String accountNo;
	private String receiptName;
	private String receiptCount;
	private String collectionUnit;
	private String invoiceNo;
	private String paymentCash;
	private String chequeNo;
	private String charge;
	private String vat;
	private String totalCharge;
	
	private BigDecimal chargeB;
	private BigDecimal vatB;
	private BigDecimal totalChargeB;
	private String totalChargeFomular;
	
	private String taxId;
	private String branchNo;
	private String branchName;
	private String branchArea;
	
	private String deductionNo;
	private String deductionType;
	private String amount3tredecim;
	private String amount69bis;
	private String amount69tre;
	private String remark;
	
	private String rowSummary;
	
	private String sumRowCollectionUnit;
	private String sumChargeCollectionUnit;
	private String sumVatCollectionUnit;
	private String sumTotalChargeCollectionUnit;

	private String sumRowPaymentUser;
	private String sumChargePaymentUser;
	private String sumVatPaymentUser;
	private String sumTotalChargePaymentUser;

	private String sumRow7Percent;
	private String sumCharge7Percent;
	private String sumVat7Percent;
	private String sumTotalCharge7Percent;

	private String sumRow0Percent;
	private String sumCharge0Percent;
	private String sumVat0Percent;
	private String sumTotalCharge0Percent;

	private String sumAmount3tredecim;
	private String sumAmount69bis;
	private String sumAmount69tre;
	
	private String sumRowAll;
	private String sumChargeAll;
	private String sumVatAll;
	private String sumTotalChargeAll;

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
	
	private String total;
	
	private String paymentMessage;
	private String officer;
	
	private String sumCancelReceipt;
	private String sumCancelAmount;
	private String sumReceipt;
	private String sumAmount;
	private String sumTotalReceipt;
	private String sumTotalAmount;
	
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
	private BigDecimal discountB;
	private String payTypeName;
	
	private String cancelDate;

	private String refRecNo;
	private String totalChargeForeign;

	/*epis7 no.44*/
	private BigDecimal dcharge;
	private BigDecimal dvat;

	private String sumTotalCollectionUnit;
	private String sumTotalAll;
	private String sumTotalPaymentUser;
	private String sumTotal7Percent;
	private String sumTotal0Percent;
	
	// pichanan 20170906
		private String currencyCode;
		private String currencyRate;
		private String updateDate;
		private int cntReceipt;
		private String period;
		private String receiptFr;
		private String receiptTo;
		private String posNo;
		private String posName;
		private String paymentTime;
		
		private BigDecimal sumAmt; 
		private BigDecimal sumRowExtend; 
		private BigDecimal sumAmountExtend; 
		private BigDecimal sumRowPayPartial; 
		private BigDecimal sumAmountPayPartial; 
		private BigDecimal sumRowPayFull; 
		private BigDecimal sumAmountPayFull; 
		private BigDecimal sumRowPayAdvance; 
		private BigDecimal sumAmountPayAdvance; 
		private BigDecimal sumRowPayPenalty; 
		private BigDecimal sumAmountPayPenalty; 
		
		private String after_sale_discount;
		private BigDecimal after_sale_discountB;
		private BigDecimal sum3tredecim; 
		private BigDecimal sum69bis; 
		private BigDecimal sum69tre;
		private String agentCode;
		private String agentName;
		private String ref1;
		private String ref2;
		private String paymentId;

		
		private String startDate;
		private String endDate;
		private BigDecimal EgpValue;
		private String billGroup;
		private BigDecimal beginCharge;
		private BigDecimal beginVat;
		private BigDecimal beginTotalCharge;
		private String posting_date;
		private BigDecimal adjCharge;
		private BigDecimal adjVat;
		private BigDecimal adjTotalCharge;
		private String adjPostingDate;
		private BigDecimal netCharge;
		private BigDecimal netVat;
		private BigDecimal netTotalCharge;
		
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
		public int getCntReceipt() {
			return cntReceipt;
		}
		public void setCntReceipt(int cntReceipt) {
			this.cntReceipt = cntReceipt;
		}
	public String getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}
	
	public BigDecimal getSumRowPayPartial() {
			return sumRowPayPartial;
		}
		public void setSumRowPayPartial(BigDecimal sumRowPayPartial) {
			this.sumRowPayPartial = sumRowPayPartial;
		}
		public BigDecimal getSumAmountPayPartial() {
			return sumAmountPayPartial;
		}
		public void setSumAmountPayPartial(BigDecimal sumAmountPayPartial) {
			this.sumAmountPayPartial = sumAmountPayPartial;
		}
		public BigDecimal getSumRowPayFull() {
			return sumRowPayFull;
		}
		public void setSumRowPayFull(BigDecimal sumRowPayFull) {
			this.sumRowPayFull = sumRowPayFull;
		}
		public BigDecimal getSumAmountPayFull() {
			return sumAmountPayFull;
		}
		public void setSumAmountPayFull(BigDecimal sumAmountPayFull) {
			this.sumAmountPayFull = sumAmountPayFull;
		}
		public BigDecimal getSumRowPayAdvance() {
			return sumRowPayAdvance;
		}
		public void setSumRowPayAdvance(BigDecimal sumRowPayAdvance) {
			this.sumRowPayAdvance = sumRowPayAdvance;
		}
		public BigDecimal getSumAmountPayAdvance() {
			return sumAmountPayAdvance;
		}
		public void setSumAmountPayAdvance(BigDecimal sumAmountPayAdvance) {
			this.sumAmountPayAdvance = sumAmountPayAdvance;
		}
		public BigDecimal getSumRowPayPenalty() {
			return sumRowPayPenalty;
		}
		public void setSumRowPayPenalty(BigDecimal sumRowPayPenalty) {
			this.sumRowPayPenalty = sumRowPayPenalty;
		}
		public BigDecimal getSumAmountPayPenalty() {
			return sumAmountPayPenalty;
		}
		public void setSumAmountPayPenalty(BigDecimal sumAmountPayPenalty) {
			this.sumAmountPayPenalty = sumAmountPayPenalty;
		}
	public BigDecimal getSumRowExtend() {
			return sumRowExtend;
		}
		public void setSumRowExtend(BigDecimal sumRowExtend) {
			this.sumRowExtend = sumRowExtend;
		}
		public BigDecimal getSumAmountExtend() {
			return sumAmountExtend;
		}
		public void setSumAmountExtend(BigDecimal sumAmountExtend) {
			this.sumAmountExtend = sumAmountExtend;
		}
	public String getPosName() {
			return posName;
		}
		public void setPosName(String posName) {
			this.posName = posName;
		}
	public String getPosNo() {
			return posNo;
		}
		public void setPosNo(String posNo) {
			this.posNo = posNo;
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
	public String getPeriod() {
			return period;
		}
		public void setPeriod(String period) {
			this.period = period;
		}
	public BigDecimal getDcharge() {
		return dcharge;
	}
	public void setDcharge(BigDecimal dcharge) {
		this.dcharge = dcharge;
	}
	public BigDecimal getDvat() {
		return dvat;
	}
	public void setDvat(BigDecimal dvat) {
		this.dvat = dvat;
	}
	
	// end pichanan 20170906
	
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public BigDecimal getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}
	public BigDecimal getSum3tredecim() {
		return sum3tredecim;
	}
	public void setSum3tredecim(BigDecimal sum3tredecim) {
		this.sum3tredecim = sum3tredecim;
	}
	public BigDecimal getSum69bis() {
		return sum69bis;
	}
	public void setSum69bis(BigDecimal sum69bis) {
		this.sum69bis = sum69bis;
	}
	public BigDecimal getSum69tre() {
		return sum69tre;
	}
	public void setSum69tre(BigDecimal sum69tre) {
		this.sum69tre = sum69tre;
	}
	public String getRefRecNo() { return refRecNo; }
	public void setRefRecNo(String refRecNo) { this.refRecNo = refRecNo; }
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
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
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void CollectionUnit(String collectionUnit) {
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
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
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
	public String getRowSummary() {
		return rowSummary;
	}
	public void setRowSummary(String rowSummary) {
		this.rowSummary = rowSummary;
	}
	public String getSumRowCollectionUnit() {
		return sumRowCollectionUnit;
	}
	public void setSumRowCollectionUnit(String sumRowCollectionUnit) {
		this.sumRowCollectionUnit = sumRowCollectionUnit;
	}
	public String getSumChargeCollectionUnit() {
		return sumChargeCollectionUnit;
	}
	public void setSumChargeCollectionUnit(String sumChargeCollectionUnit) {
		this.sumChargeCollectionUnit = sumChargeCollectionUnit;
	}
	public String getSumVatCollectionUnit() {
		return sumVatCollectionUnit;
	}
	public void setSumVatCollectionUnit(String sumVatCollectionUnit) {
		this.sumVatCollectionUnit = sumVatCollectionUnit;
	}
	public String getSumTotalChargeCollectionUnit() {
		return sumTotalChargeCollectionUnit;
	}
	public void setSumTotalChargeCollectionUnit(String sumTotalChargeCollectionUnit) {
		this.sumTotalChargeCollectionUnit = sumTotalChargeCollectionUnit;
	}
	public String getSumRow7Percent() {
		return sumRow7Percent;
	}
	public void setSumRow7Percent(String sumRow7Percent) {
		this.sumRow7Percent = sumRow7Percent;
	}
	public String getSumCharge7Percent() {
		return sumCharge7Percent;
	}
	public void setSumCharge7Percent(String sumCharge7Percent) {
		this.sumCharge7Percent = sumCharge7Percent;
	}
	public String getSumVat7Percent() {
		return sumVat7Percent;
	}
	public void setSumVat7Percent(String sumVat7Percent) {
		this.sumVat7Percent = sumVat7Percent;
	}
	public String getSumTotalCharge7Percent() {
		return sumTotalCharge7Percent;
	}
	public void setSumTotalCharge7Percent(String sumTotalCharge7Percent) {
		this.sumTotalCharge7Percent = sumTotalCharge7Percent;
	}
	public String getSumRow0Percent() {
		return sumRow0Percent;
	}
	public void setSumRow0Percent(String sumRow0Percent) {
		this.sumRow0Percent = sumRow0Percent;
	}
	public String getSumCharge0Percent() {
		return sumCharge0Percent;
	}
	public void setSumCharge0Percent(String sumCharge0Percent) {
		this.sumCharge0Percent = sumCharge0Percent;
	}
	public String getSumVat0Percent() {
		return sumVat0Percent;
	}
	public void setSumVat0Percent(String sumVat0Percent) {
		this.sumVat0Percent = sumVat0Percent;
	}
	public String getSumTotalCharge0Percent() {
		return sumTotalCharge0Percent;
	}
	public void setSumTotalCharge0Percent(String sumTotalCharge0Percent) {
		this.sumTotalCharge0Percent = sumTotalCharge0Percent;
	}
	public String getSumRowAll() {
		return sumRowAll;
	}
	public void setSumRowAll(String sumRowAll) {
		this.sumRowAll = sumRowAll;
	}
	public String getSumChargeAll() {
		return sumChargeAll;
	}
	public void setSumChargeAll(String sumChargeAll) {
		this.sumChargeAll = sumChargeAll;
	}
	public String getSumVatAll() {
		return sumVatAll;
	}
	public void setSumVatAll(String sumVatAll) {
		this.sumVatAll = sumVatAll;
	}
	public String getSumTotalChargeAll() {
		return sumTotalChargeAll;
	}
	public void setSumTotalChargeAll(String sumTotalChargeAll) {
		this.sumTotalChargeAll = sumTotalChargeAll;
	}
	public String getPaymentMessage() {
		return paymentMessage;
	}
	public void setPaymentMessage(String paymentMessage) {
		this.paymentMessage = paymentMessage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String officer) {
		this.officer = officer;
	}
	public String getSumCancelReceipt() {
		return sumCancelReceipt;
	}
	public void setSumCancelReceipt(String sumCancelReceipt) {
		this.sumCancelReceipt = sumCancelReceipt;
	}
	public String getSumCancelAmount() {
		return sumCancelAmount;
	}
	public void setSumCancelAmount(String sumCancelAmount) {
		this.sumCancelAmount = sumCancelAmount;
	}
	public String getSumReceipt() {
		return sumReceipt;
	}
	public void setSumReceipt(String sumReceipt) {
		this.sumReceipt = sumReceipt;
	}
	public String getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}
	public String getSumTotalReceipt() {
		return sumTotalReceipt;
	}
	public void setSumTotalReceipt(String sumTotalReceipt) {
		this.sumTotalReceipt = sumTotalReceipt;
	}
	public String getSumTotalAmount() {
		return sumTotalAmount;
	}
	public void setSumTotalAmount(String sumTotalAmount) {
		this.sumTotalAmount = sumTotalAmount;
	}
	public String getPaymentUser() {
		return paymentUser;
	}
	public void setPaymentUser(String paymentUser) {
		this.paymentUser = paymentUser;
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
	public String getSumRowPaymentUser() {
		return sumRowPaymentUser;
	}
	public void setSumRowPaymentUser(String sumRowPaymentUser) {
		this.sumRowPaymentUser = sumRowPaymentUser;
	}
	public String getSumChargePaymentUser() {
		return sumChargePaymentUser;
	}
	public void setSumChargePaymentUser(String sumChargePaymentUser) {
		this.sumChargePaymentUser = sumChargePaymentUser;
	}
	public String getSumVatPaymentUser() {
		return sumVatPaymentUser;
	}
	public void setSumVatPaymentUser(String sumVatPaymentUser) {
		this.sumVatPaymentUser = sumVatPaymentUser;
	}
	public String getSumTotalChargePaymentUser() {
		return sumTotalChargePaymentUser;
	}
	public void setSumTotalChargePaymentUser(String sumTotalChargePaymentUser) {
		this.sumTotalChargePaymentUser = sumTotalChargePaymentUser;
	}
	public String getSumAmount3tredecim() {
		return sumAmount3tredecim;
	}
	public void setSumAmount3tredecim(String sumAmount3tredecim) {
		this.sumAmount3tredecim = sumAmount3tredecim;
	}
	public String getSumAmount69bis() {
		return sumAmount69bis;
	}
	public void setSumAmount69bis(String sumAmount69bis) {
		this.sumAmount69bis = sumAmount69bis;
	}
	public String getSumAmount69tre() {
		return sumAmount69tre;
	}
	public void setSumAmount69tre(String sumAmount69tre) {
		this.sumAmount69tre = sumAmount69tre;
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTotalChargeForeign() {
		return totalChargeForeign;
	}

	public void setTotalChargeForeign(String totalChargeForeign) {
		this.totalChargeForeign = totalChargeForeign;
	}

	public String getSumTotalCollectionUnit() {
		return sumTotalCollectionUnit;
	}

	public void setSumTotalCollectionUnit(String sumTotalCollectionUnit) {
		this.sumTotalCollectionUnit = sumTotalCollectionUnit;
	}

	public String getSumTotalAll() {
		return sumTotalAll;
	}

	public void setSumTotalAll(String sumTotalAll) {
		this.sumTotalAll = sumTotalAll;
	}

	public String getSumTotalPaymentUser() {
		return sumTotalPaymentUser;
	}

	public void setSumTotalPaymentUser(String sumTotalPaymentUser) {
		this.sumTotalPaymentUser = sumTotalPaymentUser;
	}

	public String getSumTotal7Percent() {
		return sumTotal7Percent;
	}

	public void setSumTotal7Percent(String sumTotal7Percent) {
		this.sumTotal7Percent = sumTotal7Percent;
	}

	public String getSumTotal0Percent() {
		return sumTotal0Percent;
	}

	public void setSumTotal0Percent(String sumTotal0Percent) {
		this.sumTotal0Percent = sumTotal0Percent;
	}
	public BigDecimal getChargeB() {
		return chargeB;
	}
	public void setChargeB(BigDecimal chargeB) {
		this.chargeB = chargeB;
	}
	public BigDecimal getVatB() {
		return vatB;
	}
	public void setVatB(BigDecimal vatB) {
		this.vatB = vatB;
	}
	public BigDecimal getTotalChargeB() {
		return totalChargeB;
	}
	public void setTotalChargeB(BigDecimal totalChargeB) {
		this.totalChargeB = totalChargeB;
	}
	public String getTotalChargeFomular() {
		return totalChargeFomular;
	}
	public void setTotalChargeFomular(String totalChargeFomular) {
		this.totalChargeFomular = totalChargeFomular;
	}
	public String getAfter_sale_discount() {
		return after_sale_discount;
	}
	public void setAfter_sale_discount(String after_sale_discount) {
		this.after_sale_discount = after_sale_discount;
	}
	public BigDecimal getDiscountB() {
		return discountB;
	}
	public void setDiscountB(BigDecimal discountB) {
		this.discountB = discountB;
	}
	public BigDecimal getAfter_sale_discountB() {
		return after_sale_discountB;
	}
	public void setAfter_sale_discountB(BigDecimal after_sale_discountB) {
		this.after_sale_discountB = after_sale_discountB;
	}
	public String getBranchArea() {
		return branchArea;
	}
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
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
	public BigDecimal getEgpValue() {
		return EgpValue;
	}
	public void setEgpValue(BigDecimal egpValue) {
		EgpValue = egpValue;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public BigDecimal getBeginCharge() {
		return beginCharge;
	}
	public void setBeginCharge(BigDecimal beginCharge) {
		this.beginCharge = beginCharge;
	}
	public BigDecimal getBeginVat() {
		return beginVat;
	}
	public void setBeginVat(BigDecimal beginVat) {
		this.beginVat = beginVat;
	}
	public BigDecimal getBeginTotalCharge() {
		return beginTotalCharge;
	}
	public void setBeginTotalCharge(BigDecimal beginTotalCharge) {
		this.beginTotalCharge = beginTotalCharge;
	}
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	public BigDecimal getAdjCharge() {
		return adjCharge;
	}
	public void setAdjCharge(BigDecimal adjCharge) {
		this.adjCharge = adjCharge;
	}
	public BigDecimal getAdjVat() {
		return adjVat;
	}
	public void setAdjVat(BigDecimal adjVat) {
		this.adjVat = adjVat;
	}
	public BigDecimal getAdjTotalCharge() {
		return adjTotalCharge;
	}
	public void setAdjTotalCharge(BigDecimal adjTotalCharge) {
		this.adjTotalCharge = adjTotalCharge;
	}
	public String getAdjPostingDate() {
		return adjPostingDate;
	}
	public void setAdjPostingDate(String adjPostingDate) {
		this.adjPostingDate = adjPostingDate;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}	
	public BigDecimal getNetCharge() {
		return netCharge;
	}
	public void setNetCharge(BigDecimal netCharge) {
		this.netCharge = netCharge;
	}
	public BigDecimal getNetVat() {
		return netVat;
	}
	public void setNetVat(BigDecimal netVat) {
		this.netVat = netVat;
	}
	public BigDecimal getNetTotalCharge() {
		return netTotalCharge;
	}
	public void setNetTotalCharge(BigDecimal netTotalCharge) {
		this.netTotalCharge = netTotalCharge;
	}
	
	
}

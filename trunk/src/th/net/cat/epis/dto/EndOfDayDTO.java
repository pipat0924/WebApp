package th.net.cat.epis.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EndOfDayDTO extends CommonStatus<Object> {

	public SummaryPaymentInfo addSummaryPaymentInfo() {
		SummaryPaymentInfo summaryPaymentInfo = new SummaryPaymentInfo();
		getData().add(summaryPaymentInfo);
		return summaryPaymentInfo;
	}

	public static class SummaryPaymentInfo {
		private String officerName;
		private String pageNo;
		private String branch;
		private String posMachineName;
		private Date currentDttm;
		private BigDecimal totalPaymentAmount;
		private BigDecimal total3TredecimAmount;
		private BigDecimal total69BisAmount;
		private BigDecimal total69TreAmount;
		private BigDecimal totalCashAmount;
		private BigDecimal totalChequeAmount;
		private BigDecimal totalCreditCardAmount;
		private BigDecimal totalMoneyOrderAmount;
		private BigDecimal totalBankTxnfAmount;
		private BigDecimal totalForeignBankTxnfAmount;
		private BigDecimal totalBillExchangeAmount;
		private BigDecimal totalOtherAmount;
		private Integer totalCheque;
		private Integer totalCreditCard;
		private Integer totalMoneyOrder;
		private Integer totalBankTxnf;
		private Integer totalForeignBankTxnf;
		private Integer totalBillExchange;
		private Integer totalOther;
		private Integer totalPaidReceiptWithTaxInvoice;
		private Integer totalCancelledReceiptWithTaxInvoice;
		private Integer totalPaidReceipt;
		private Integer totalCancelledReceipt;

		public String getOfficerName() {
			return officerName;
		}
		public String getPageNo() {
			return pageNo;
		}
		public String getBranch() {
			return branch;
		}
		public String getPosMachineName() {
			return posMachineName;
		}
		public Date getCurrentDttm() {
			return currentDttm;
		}
		public BigDecimal getTotalPaymentAmount() {
			return totalPaymentAmount;
		}
		public BigDecimal getTotal3TredecimAmount() {
			return total3TredecimAmount;
		}
		public BigDecimal getTotal69BisAmount() {
			return total69BisAmount;
		}
		public BigDecimal getTotal69TreAmount() {
			return total69TreAmount;
		}
		public void setTotal69TreAmount(BigDecimal total69TreAmount) {
			this.total69TreAmount = total69TreAmount;
		}
		public BigDecimal getTotalCashAmount() {
			return totalCashAmount;
		}
		public BigDecimal getTotalChequeAmount() {
			return totalChequeAmount;
		}
		public BigDecimal getTotalCreditCardAmount() {
			return totalCreditCardAmount;
		}
		public BigDecimal getTotalMoneyOrderAmount() {
			return totalMoneyOrderAmount;
		}
		public BigDecimal getTotalBankTxnfAmount() {
			return totalBankTxnfAmount;
		}
		public BigDecimal getTotalForeignBankTxnfAmount() {
			return totalForeignBankTxnfAmount;
		}
		public BigDecimal getTotalBillExchangeAmount() {
			return totalBillExchangeAmount;
		}
		public BigDecimal getTotalOtherAmount() {
			return totalOtherAmount;
		}
		public Integer getTotalCheque() {
			return totalCheque;
		}
		public Integer getTotalCreditCard() {
			return totalCreditCard;
		}
		public Integer getTotalMoneyOrder() {
			return totalMoneyOrder;
		}
		public Integer getTotalBankTxnf() {
			return totalBankTxnf;
		}
		public Integer getTotalForeignBankTxnf() {
			return totalForeignBankTxnf;
		}
		public Integer getTotalBillExchange() {
			return totalBillExchange;
		}
		public Integer getTotalOther() {
			return totalOther;
		}
		public Integer getTotalPaidReceiptWithTaxInvoice() {
			return totalPaidReceiptWithTaxInvoice;
		}
		public Integer getTotalCancelledReceiptWithTaxInvoice() {
			return totalCancelledReceiptWithTaxInvoice;
		}
		public Integer getTotalPaidReceipt() {
			return totalPaidReceipt;
		}
		public Integer getTotalCancelledReceipt() {
			return totalCancelledReceipt;
		}
		public void setOfficerName(String officerName) {
			this.officerName = officerName;
		}
		public void setPageNo(String pageNo) {
			this.pageNo = pageNo;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public void setPosMachineName(String posMachineName) {
			this.posMachineName = posMachineName;
		}
		public void setCurrentDttm(Date currentDttm) {
			this.currentDttm = currentDttm;
		}
		public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
			this.totalPaymentAmount = totalPaymentAmount;
		}
		public void setTotal3TredecimAmount(BigDecimal total3TredecimAmount) {
			this.total3TredecimAmount = total3TredecimAmount;
		}
		public void setTotal69BisAmount(BigDecimal total69BisAmount) {
			this.total69BisAmount = total69BisAmount;
		}
		public void setTotalCashAmount(BigDecimal totalCashAmount) {
			this.totalCashAmount = totalCashAmount;
		}
		public void setTotalChequeAmount(BigDecimal totalChequeAmount) {
			this.totalChequeAmount = totalChequeAmount;
		}
		public void setTotalCreditCardAmount(BigDecimal totalCreditCardAmount) {
			this.totalCreditCardAmount = totalCreditCardAmount;
		}
		public void setTotalMoneyOrderAmount(BigDecimal totalMoneyOrderAmount) {
			this.totalMoneyOrderAmount = totalMoneyOrderAmount;
		}
		public void setTotalBankTxnfAmount(BigDecimal totalBankTxnfAmount) {
			this.totalBankTxnfAmount = totalBankTxnfAmount;
		}
		public void setTotalForeignBankTxnfAmount(BigDecimal totalForeignBankTxnfAmount) {
			this.totalForeignBankTxnfAmount = totalForeignBankTxnfAmount;
		}
		public void setTotalBillExchangeAmount(BigDecimal totalBillExchangeAmount) {
			this.totalBillExchangeAmount = totalBillExchangeAmount;
		}
		public void setTotalOtherAmount(BigDecimal totalOtherAmount) {
			this.totalOtherAmount = totalOtherAmount;
		}
		public void setTotalCheque(Integer totalCheque) {
			this.totalCheque = totalCheque;
		}
		public void setTotalCreditCard(Integer totalCreditCard) {
			this.totalCreditCard = totalCreditCard;
		}
		public void setTotalMoneyOrder(Integer totalMoneyOrder) {
			this.totalMoneyOrder = totalMoneyOrder;
		}
		public void setTotalBankTxnf(Integer totalBankTxnf) {
			this.totalBankTxnf = totalBankTxnf;
		}
		public void setTotalForeignBankTxnf(Integer totalForeignBankTxnf) {
			this.totalForeignBankTxnf = totalForeignBankTxnf;
		}
		public void setTotalBillExchange(Integer totalBillExchange) {
			this.totalBillExchange = totalBillExchange;
		}
		public void setTotalOther(Integer totalOther) {
			this.totalOther = totalOther;
		}
		public void setTotalPaidReceiptWithTaxInvoice(Integer totalPaidReceiptWithTaxInvoice) {
			this.totalPaidReceiptWithTaxInvoice = totalPaidReceiptWithTaxInvoice;
		}
		public void setTotalCancelledReceiptWithTaxInvoice(Integer totalCancelledReceiptWithTaxInvoice) {
			this.totalCancelledReceiptWithTaxInvoice = totalCancelledReceiptWithTaxInvoice;
		}
		public void setTotalPaidReceipt(Integer totalPaidReceipt) {
			this.totalPaidReceipt = totalPaidReceipt;
		}
		public void setTotalCancelledReceipt(Integer totalCancelledReceipt) {
			this.totalCancelledReceipt = totalCancelledReceipt;
		}
	}
}
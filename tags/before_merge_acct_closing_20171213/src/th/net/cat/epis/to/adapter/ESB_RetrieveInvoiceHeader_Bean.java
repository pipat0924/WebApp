/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.net.cat.epis.to.adapter;

/**
 * 
 * @author softpos2013
 */
public class ESB_RetrieveInvoiceHeader_Bean {

	private String BillRefNo = null;
	private String AccountNo = null;
	private double Amount = 0.00;
	private double VAT = 0.00;
	private double Total = 0.00;
	private double BalanceDue = 0.00;
	private double TotalPaid = 0.00;
	private double TotalAdj = 0.00;
	private String IssueDate = null;
	private String DueDate = null;
	private String ChargeFromDate = null;
	private String ChargeToDate = null;
	private String CurrencyCode = null;
	private String TaxRate = null;
	private String TaxTypeCode = null;
	private String CloseDate = null;
	private double VolumnDiscount = 0.00;

	public void ESB_RetrieveInvoiceHeader_Bean1(String BillRefNo, String AccountNo,
			double Amount, double VAT, double Total, double BalanceDue,
			double TotalPaid, double TotalAdj, String IssueDate,
			String DueDate, String ChargeFromDate, String ChargeToDate,
			String CurrencyCode, String TaxRate, String TaxTypeCode, String CloseDate,
			double VolumnDiscount) {

		this.BillRefNo = BillRefNo;
		this.AccountNo = AccountNo;
		this.Amount = Amount;
		this.VAT = VAT;
		this.Total = Total;
		this.BalanceDue = BalanceDue;
		this.TotalPaid = TotalPaid;
		this.TotalAdj = TotalAdj;
		this.IssueDate = IssueDate;
		this.DueDate = DueDate;
		this.ChargeFromDate = ChargeFromDate;
		this.ChargeToDate = ChargeToDate;
		this.CurrencyCode = CurrencyCode;
		this.TaxRate = TaxRate;
		this.TaxTypeCode = TaxTypeCode;
		this.CloseDate = CloseDate;
		this.VolumnDiscount = VolumnDiscount;
	}

	public String getBillRefNo() {
		return BillRefNo;
	}

	public void setBillRefNo(String BillRefNo) {
		this.BillRefNo = BillRefNo;
	}

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String AccountNo) {
		this.AccountNo = AccountNo;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double Amount) {
		this.Amount = Amount;
	}

	public double getVAT() {
		return VAT;
	}

	public void setVAT(double VAT) {
		this.VAT = VAT;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double Total) {
		this.Total = Total;
	}

	public double getBalanceDue() {
		return BalanceDue;
	}

	public void setBalanceDue(double BalanceDue) {
		this.BalanceDue = BalanceDue;
	}

	public double getTotalPaid() {
		return TotalPaid;
	}

	public void setTotalPaid(double TotalPaid) {
		this.TotalPaid = TotalPaid;
	}

	public double getTotalAdj() {
		return TotalAdj;
	}

	public void setTotalAdj(double TotalAdj) {
		this.TotalAdj = TotalAdj;
	}

	public String getIssueDate() {
		return IssueDate;
	}

	public void setIssueDate(String IssueDate) {
		this.IssueDate = IssueDate;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String DueDate) {
		this.DueDate = DueDate;
	}

	public String getChargeFromDate() {
		return ChargeFromDate;
	}

	public void setChargeFromDate(String ChargeFromDate) {
		this.ChargeFromDate = ChargeFromDate;
	}

	public String getChargeToDate() {
		return ChargeToDate;
	}

	public void setChargeToDate(String ChargeToDate) {
		this.ChargeToDate = ChargeToDate;
	}

	public String getCurrencyCode() {
		return CurrencyCode;
	}

	public void setCurrencyCode(String CurrencyCode) {
		this.CurrencyCode = CurrencyCode;
	}

	public String getTaxRate() {
		return TaxRate;
	}

	public void setTaxRate(String TaxRate) {
		this.TaxRate = TaxRate;
	}

	public String getTaxTypeCode() {
		return TaxTypeCode;
	}

	public void setTaxTypeCode(String TaxTypeCode) {
		this.TaxTypeCode = TaxTypeCode;
	}
	
	public String getCloseDate() {
		return CloseDate;
	}

	public void setCloseDate(String CloseDate) {
		this.CloseDate = CloseDate;
	}

	public double getVolumnDiscount() {
		return VolumnDiscount;
	}

	public void setVolumnDiscount(double VolumnDiscount) {
		this.VolumnDiscount = VolumnDiscount;
	}

}

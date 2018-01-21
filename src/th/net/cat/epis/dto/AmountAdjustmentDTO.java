package th.net.cat.epis.dto;

public class AmountAdjustmentDTO {
	private String amountAdjustNo;
	private String billRefNo;
	private String receiptNo;
	private String custNo;
	private String custName;
	private String payMethod;
	private String totalAmt;
	private String adjustAmt;
	private String branch;
	private String userName;
	private String status;
	public String getAmountAdjustNo() {
		return amountAdjustNo;
	}
	public void setAmountAdjustNo(String amountAdjustNo) {
		this.amountAdjustNo = amountAdjustNo;
	}
	public String getBillRefNo() {
		return billRefNo;
	}
	public void setBillRefNo(String billRefNo) {
		this.billRefNo = billRefNo;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getAdjustAmt() {
		return adjustAmt;
	}
	public void setAdjustAmt(String adjustAmt) {
		this.adjustAmt = adjustAmt;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

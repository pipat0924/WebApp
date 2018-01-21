package th.net.cat.epis.dto;

import java.math.BigDecimal;

public class ReportPaymentSummary {

	private String userCode;
	private String userName;
	private String userFamilyname;
	private Integer receiptCount;
	private BigDecimal summaryAmount;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserFamilyname() {
		return userFamilyname;
	}
	public void setUserFamilyname(String userFamilyname) {
		this.userFamilyname = userFamilyname;
	}
	public Integer getReceiptCount() {
		return receiptCount;
	}
	public void setReceiptCount(Integer receiptCount) {
		this.receiptCount = receiptCount;
	}
	public BigDecimal getSummaryAmount() {
		return summaryAmount;
	}
	public void setSummaryAmount(BigDecimal summaryAmount) {
		this.summaryAmount = summaryAmount;
	}
}

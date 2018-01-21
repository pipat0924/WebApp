package th.net.cat.epis.dto.bouncecheque;

import java.math.BigDecimal;

public class SapForReportDTO {
	
	private String orderNo;
	private Double totalChargeBath;
	private Double totalCharge;
	private Integer vat;
	private Double vatBath;
	private String remark;
	private String paidBy;
	private String rateChange;
	private String rateChangeAmount;
	private String address;
	private String arName;
	private String arAccountCode;
	private String subNo;
	private String date;
	private String no;
	private String vatNo;
	private String serviceKey3;
	private String currency;
	private BigDecimal vatRD;
	private BigDecimal chargeBath;
	private BigDecimal amountBath;
	private BigDecimal amount;
	
	public Double getTotalChargeBath() {
		return totalChargeBath;
	}
	public void setTotalChargeBath(Double totalChargeBath) {
		this.totalChargeBath = totalChargeBath;
	}
	public Double getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}
	public Double getVatBath() {
		return vatBath;
	}
	public void setVatBath(Double vatBath) {
		this.vatBath = vatBath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	public String getRateChange() {
		return rateChange;
	}
	public void setRateChange(String rateChange) {
		this.rateChange = rateChange;
	}
	public String getRateChangeAmount() {
		return rateChangeAmount;
	}
	public void setRateChangeAmount(String rateChangeAmount) {
		this.rateChangeAmount = rateChangeAmount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArName() {
		return arName;
	}
	public void setArName(String arName) {
		this.arName = arName;
	}
	public String getArAccountCode() {
		return arAccountCode;
	}
	public void setArAccountCode(String arAccountCode) {
		this.arAccountCode = arAccountCode;
	}
	public String getSubNo() {
		return subNo;
	}
	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getVatNo() {
		return vatNo;
	}
	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}
	public String getServiceKey3() {
		return serviceKey3;
	}
	public void setServiceKey3(String serviceKey3) {
		this.serviceKey3 = serviceKey3;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getVatRD() {
		return vatRD;
	}
	public void setVatRD(BigDecimal vatRD) {
		this.vatRD = vatRD;
	}
	public BigDecimal getChargeBath() {
		return chargeBath;
	}
	public void setChargeBath(BigDecimal chargeBath) {
		this.chargeBath = chargeBath;
	}
	public BigDecimal getAmountBath() {
		return amountBath;
	}
	public void setAmountBath(BigDecimal amountBath) {
		this.amountBath = amountBath;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getVat() {
		return vat;
	}
	public void setVat(Integer vat) {
		this.vat = vat;
	}
	
}

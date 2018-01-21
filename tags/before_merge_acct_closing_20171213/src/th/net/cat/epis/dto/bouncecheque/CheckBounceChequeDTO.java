package th.net.cat.epis.dto.bouncecheque;

import java.util.List;

import th.net.cat.epis.dto.MasterDataDTO;

public class CheckBounceChequeDTO {
	private String bounceDate;
	private String creBillDate;
	private String billNo;
	private String chequeNo;
	private String payLocation;
	private String user;
	private String dueDate;
	private String useDate;
	private Double balanceDue;
	private String payLog;
	private String payType;
	private Double payAmount;
	private Double vat;
	private String status;
	private String remark;
	private Boolean bounceCheque;
	private String bounceStatus;
	private MasterDataDTO masterDataDTO;
	private MasterDataDTO masterDataBounceStatus;
	
	public MasterDataDTO getMasterDataBounceStatus() {
		return masterDataBounceStatus;
	}
	public void setMasterDataBounceStatus(MasterDataDTO masterDataBounceStatus) {
		this.masterDataBounceStatus = masterDataBounceStatus;
	}
	public String getBounceStatus() {
		return bounceStatus;
	}
	public void setBounceStatus(String bounceStatus) {
		this.bounceStatus = bounceStatus;
	}
	public MasterDataDTO getMasterDataDTO() {
		return masterDataDTO;
	}
	public void setMasterDataDTO(MasterDataDTO masterDataDTO) {
		this.masterDataDTO = masterDataDTO;
	}
	public String getBounceDate() {
		return bounceDate;
	}
	public void setBounceDate(String bounceDate) {
		this.bounceDate = bounceDate;
	}
	public String getCreBillDate() {
		return creBillDate;
	}
	public void setCreBillDate(String creBillDate) {
		this.creBillDate = creBillDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getPayLog() {
		return payLog;
	}
	public void setPayLog(String payLog) {
		this.payLog = payLog;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getBounceCheque() {
		return bounceCheque;
	}
	public void setBounceCheque(Boolean bounceCheque) {
		this.bounceCheque = bounceCheque;
	}
	public Double getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(Double balanceDue) {
		this.balanceDue = balanceDue;
	}
	public String getPayLocation() {
		return payLocation;
	}
	public void setPayLocation(String payLocation) {
		this.payLocation = payLocation;
	}
	
}

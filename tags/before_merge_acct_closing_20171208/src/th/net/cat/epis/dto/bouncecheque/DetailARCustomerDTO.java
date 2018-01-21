package th.net.cat.epis.dto.bouncecheque;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.net.cat.epis.util.converter.CustomDateTimeDeserializer;
import th.net.cat.epis.util.converter.CustomDateTimeSerializer;

public class DetailARCustomerDTO {
	
	private String arAccountCode;
	private String arName;
	private String taxId;
	private String glAccount;
	private String arGroup;
	private String regionKey1;
	private String branchAR;
	private String address;
	private String remark;
	
	private String reportNo;
	private Date upDDate;
	private String branchName;
	private String branchCode;
	
	private List<PayBounceChequeDTO> payBounceChequeDTOList;
	
	public String getBranchAR() {
		return branchAR;
	}
	public void setBranchAR(String branchAR) {
		this.branchAR = branchAR;
	}
	public String getArName() {
		return arName;
	}
	public void setArName(String arName) {
		this.arName = arName;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getGlAccount() {
		return glAccount;
	}
	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}
	public String getArGroup() {
		return arGroup;
	}
	public void setArGroup(String arGroup) {
		this.arGroup = arGroup;
	}
	public String getRegionKey1() {
		return regionKey1;
	}
	public void setRegionKey1(String regionKey1) {
		this.regionKey1 = regionKey1;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArAccountCode() {
		return arAccountCode;
	}
	public void setArAccountCode(String arAccountCode) {
		this.arAccountCode = arAccountCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getUpDDate() {
		return upDDate;
	}
	
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	public void setUpDDate(Date upDDate) {
		this.upDDate = upDDate;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public List<PayBounceChequeDTO> getPayBounceChequeDTOList() {
		return payBounceChequeDTOList;
	}
	public void setPayBounceChequeDTOList(List<PayBounceChequeDTO> payBounceChequeDTOList) {
		this.payBounceChequeDTOList = payBounceChequeDTOList;
	}
	
}

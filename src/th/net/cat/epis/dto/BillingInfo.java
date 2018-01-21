package th.net.cat.epis.dto;

public class BillingInfo {
	
	protected String billingNo; // customerNo; 1 CA can have multiple BA
	protected String billingId; // used to find Address
	protected String customerName;
	protected String taxNo;
	protected String branchName;
	protected String deptFollowupDepartment;
	protected String userGroup;
	protected String totalAmountIncludeVat;
	protected String billingGroup;
	protected String advancePay;
	protected String customerStatus;
	protected String currency;
	protected String remark;
	protected String vatRate;
	
	public BillingInfo() {}
	public BillingInfo(String billingNo) {
		super();
		this.billingNo = billingNo; // 		this.sourceAccountId = sourceAccountId;
	}
	public BillingInfo(String billingNo, String billingId, String customerName,
			String taxNo, String branchName, String deptFollowupDepartment,
			String userGroup, String totalAmountIncludeVat,
			String billingGroup, String advancePay, String customerStatus,
			String currency, String remark, String vatRate) {
		super();
		this.billingNo = billingNo;
		this.billingId = billingId;
		this.customerName = customerName;
		this.taxNo = taxNo;
		this.branchName = branchName;
		this.deptFollowupDepartment = deptFollowupDepartment;
		this.userGroup = userGroup;
		this.totalAmountIncludeVat = totalAmountIncludeVat;
		this.billingGroup = billingGroup;
		this.advancePay = advancePay;
		this.customerStatus = customerStatus;
		this.currency = currency;
		this.remark = remark;
		this.vatRate = vatRate;
	}

	public String getBillingNo() {
		return billingNo;
	}
	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}
	public String getBillingId() {
		return billingId;
	}
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDeptFollowupDepartment() {
		return deptFollowupDepartment;
	}
	public void setDeptFollowupDepartment(String deptFollowupDepartment) {
		this.deptFollowupDepartment = deptFollowupDepartment;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getTotalAmountIncludeVat() {
		return totalAmountIncludeVat;
	}
	public void setTotalAmountIncludeVat(String totalAmountIncludeVat) {
		this.totalAmountIncludeVat = totalAmountIncludeVat;
	}
	public String getBillingGroup() {
		return billingGroup;
	}
	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}
	public String getAdvancePay() {
		return advancePay;
	}
	public void setAdvancePay(String advancePay) {
		this.advancePay = advancePay;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getVatRate() {
		return vatRate;
	}
	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}
	
}

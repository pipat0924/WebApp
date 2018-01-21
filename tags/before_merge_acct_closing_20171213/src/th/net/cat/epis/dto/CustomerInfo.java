package th.net.cat.epis.dto;

public class CustomerInfo {

	// Service Input fields
	protected String customerNo;
	protected String taxNo;
	protected String personFirstName;
	protected String personLastName;
	protected String organizationName;
	
	// Results with search parameters
	protected String serviceType;
	protected String billingGroup;
	
	public CustomerInfo() {
		this.customerNo = "";
		this.taxNo = "";
		this.personFirstName = "";
		this.personLastName = "";
		this.organizationName = "";
		this.serviceType = "";
		this.billingGroup = "";
	}
	
	public CustomerInfo(String customerNo, String taxNo,
			String personFirstName, String personLastName,
			String organizationName, String serviceType, String billingGroup) {
		this.customerNo = customerNo;
		this.taxNo = taxNo;
		this.personFirstName = personFirstName;
		this.personLastName = personLastName;
		this.organizationName = organizationName;
		this.serviceType = serviceType;
		this.billingGroup = billingGroup;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getPersonFirstName() {
		return personFirstName;
	}
	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}
	public String getPersonLastName() {
		return personLastName;
	}
	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getBillingGroup() {
		return billingGroup;
	}
	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}
	
}

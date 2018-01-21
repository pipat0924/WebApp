package th.net.cat.epis.dto;

public class ProvideCustomerService {
	// Search Parameter
	protected String serviceType;
	protected String serviceNo;	
	protected boolean isProperty1Checked;
	protected boolean isProperty2Checked;
	
	// Results with search parameters
	protected String customerNo;
	protected String customerName;
	protected String billingGroup;
	
	public ProvideCustomerService() {
		this.serviceType = "";
		this.serviceNo = "";
		this.isProperty1Checked = false;
		this.isProperty2Checked = false;
		this.customerNo = "";
		this.customerName = "";
		this.billingGroup = "";
	}
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBillingGroup() {
		return billingGroup;
	}
	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}
}

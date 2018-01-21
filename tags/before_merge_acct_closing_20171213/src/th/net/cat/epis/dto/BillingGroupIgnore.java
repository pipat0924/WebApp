package th.net.cat.epis.dto;

public class BillingGroupIgnore {
	private Long id;
	private String billingGroup;
	private String billingGroupDesc;
	private String fullName;
	public String getBillingGroup() {
		return billingGroup;
	}
	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}
	public String getBillingGroupDesc() {
		return billingGroupDesc;
	}
	public void setBillingGroupDesc(String billingGroupDesc) {
		this.billingGroupDesc = billingGroupDesc;
	}
	public BillingGroupIgnore(Long id, String billingGroup, String billingGroupDesc) {
		super();
		this.id = id;
		this.billingGroup = billingGroup;
		this.billingGroupDesc = billingGroupDesc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BillingGroupIgnore(String billingGroup, String billingGroupDesc,String fullName) {
		super();
		this.billingGroup = billingGroup;
		this.billingGroupDesc = billingGroupDesc;
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public BillingGroupIgnore() {
		super();
	}
	
}

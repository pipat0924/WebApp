package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MASBILLING_GROUP_IGNORE")
public class BillingGroupIgnore {
	
	

	@Id
	@SequenceGenerator(name="BILLING_GROUP_IGNORE_SEQ", sequenceName="BILLING_GROUP_IGNORE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILLING_GROUP_IGNORE_SEQ")
	@Column(name="ID") private Long id;
	@Column(name="BILLING_GROUP") private String billingGroup;
	@Column(name="BILLING_GROUP_DESC") private String billingGroupDesc;
	@Column(name="FULL_NAME") private String fullName;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BillingGroupIgnore() {
		super();
	}
	public BillingGroupIgnore(String billingGroup, String billingGroupDesc) {
		super();
		this.billingGroup = billingGroup;
		this.billingGroupDesc = billingGroupDesc;
	}
	
	public BillingGroupIgnore(Long id, String billingGroup, String billingGroupDesc,String fullName) {
		super();
		this.id = id;
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
}

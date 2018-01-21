package th.net.cat.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @IdClass(ServiceId.class)
@Table(name="V_CATCRM_SVC_PROPERTY",schema = "CRMDATA")
public class BillService {

	@Id
	@Column(name="CAT_SVC_ID") private String id;
	@Id
	@Column(name="PROPERTY_LABEL") private String propLabel;
	@Column(name="PROPERTY_ONE") private String propOne;
	@Column(name="PROPERTY_TWO") private String propTwo;
	@Column(name="PROPERTY_VALUE") private String propValue;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CAT_BILL_ACCT_ID") private BillProfile profile;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPropOne() {
		return propOne;
	}
	public void setPropOne(String propOne) {
		this.propOne = propOne;
	}
	public String getPropTwo() {
		return propTwo;
	}
	public void setPropTwo(String propTwo) {
		this.propTwo = propTwo;
	}
	public String getPropLabel() {
		return propLabel;
	}
	public void setPropLabel(String propLabel) {
		this.propLabel = propLabel;
	}
	public String getPropValue() {
		return propValue;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	public BillProfile getProfile() {
		return profile;
	}
	public void setProfile(BillProfile profile) {
		this.profile = profile;
	}
}
class ServiceId implements Serializable {
	private static final long serialVersionUID = -1643283769032009703L;
	String id;
	String propLabel;
}
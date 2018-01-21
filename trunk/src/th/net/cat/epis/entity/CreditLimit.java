package th.net.cat.epis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MASCREDITLIMITS")
public class CreditLimit {

	@Id
	@SequenceGenerator(name="creditlimit_seq", sequenceName="creditlimit_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="creditlimit_seq")
	@Column(name="SERVICEID") private Long id;
	@Column(name="INVOICEID") private Long invoiceId;
	@Column(name="SERVICENO") private String serviceNo;
	@Column(name="CREDITMODE") private String creditMode;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATESYSTEM") private String updateSystem;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="VERSIONSTAMP") private Long versionStamp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getCreditMode() {
		return creditMode;
	}
	public void setCreditMode(String creditMode) {
		this.creditMode = creditMode;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getUpdateSystem() {
		return updateSystem;
	}
	public void setUpdateSystem(String updateSystem) {
		this.updateSystem = updateSystem;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Long getVersionStamp() {
		return versionStamp;
	}
	public void setVersionStamp(Long versionStamp) {
		this.versionStamp = versionStamp;
	}
	
	
}
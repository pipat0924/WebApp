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
@Table(name="ARCPRINCIPAL")
public class Principal {

	@Id
	@SequenceGenerator(name="principal_seq", sequenceName="principal_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="principal_seq")
	@Column(name="PRINCIPALID") private Long id;
	@Column(name="NAME") private String name;
	@Column(name="DESCRIPTION") private String desc;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATESYSTEM") private String updateSystem;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="VERSIONSTAMP") private Long version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateSystem() {
		return updateSystem;
	}
	public void setUpdateSystem(String updateSystem) {
		this.updateSystem = updateSystem;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
}
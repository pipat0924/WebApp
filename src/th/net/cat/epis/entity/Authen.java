package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ARCUSERAUTHNTICN")
public class Authen {

	@SequenceGenerator(name="authen_seq", sequenceName="authen_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="authen_seq")
	@Id
	@Column(name="USERAUTHNTICNID") private Long id;
	@Column(name="OFFICERID") private Long officerId;
	@Column(name="PASSWORD") private String password;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOfficerId() {
		return officerId;
	}
	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
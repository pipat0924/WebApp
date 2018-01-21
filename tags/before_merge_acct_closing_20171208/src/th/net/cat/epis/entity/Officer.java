package th.net.cat.epis.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MASOFFICER")
public class Officer {

	@Id
	@SequenceGenerator(name="officer_seq", sequenceName="officer_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="officer_seq")
	@Column(name="OFFICERID") private Long id;
	@Column(name="SESSIONID") private Long sessionId;
	@Column(name="USERNAME") private String name;
	@Column(name="OFFICERCODE") private String code;
	@Column(name="OFFICERGIVENNAME") private String givenName;
	@Column(name="OFFICERFAMILYNAME") private String familyName;
	@Column(name="DESCRIPTION") private String description;
	@Column(name="PERMISSION") private String permission;
	@Column(name="ISPOSITIVE") private Integer isPositive;
	@Column(name="VERIFY_FLAG") private String verifyFlag;
	@Column(name="VERIFY_KEY") private String verifyKey;
	@Column(name="PASSWORD") private String password;
	@ManyToOne(targetEntity=Principal.class)
	@JoinColumn(name="PRINCIPALID", nullable=false) private Principal principal;
	/*@OneToOne(cascade=CascadeType.ALL, targetEntity=Authen.class)
	@JoinColumn(name="OFFICERID", nullable=false) private Authen authen;*/
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="COROFFICERPOS",
		joinColumns=@JoinColumn(name="OFFICERID", referencedColumnName="OFFICERID"),
		inverseJoinColumns=@JoinColumn(name="POSID", referencedColumnName="POSID"))
	private List<Pos> machines = new ArrayList<Pos>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Integer getIsPositive() {
		return isPositive;
	}
	public void setIsPositive(Integer isPositive) {
		this.isPositive = isPositive;
	}
	public String getVerifyFlag() {
		return verifyFlag;
	}
	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}
	public String getVerifyKey() {
		return verifyKey;
	}
	public void setVerifyKey(String verifyKey) {
		this.verifyKey = verifyKey;
	}
	public Principal getPrincipal() {
		return principal;
	}
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	/*public Authen getAuthen() {
		return authen;
	}
	public void setAuthen(Authen authen) {
		this.authen = authen;
	}*/
	public List<Pos> getMachines() {
		return machines;
	}
	public void setMachines(List<Pos> machines) {
		this.machines = machines;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
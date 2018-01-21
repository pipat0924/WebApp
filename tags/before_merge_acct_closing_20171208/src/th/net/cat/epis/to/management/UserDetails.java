package th.net.cat.epis.to.management;

import java.util.LinkedList;
import java.util.List;

public class UserDetails {

	private int arcIdentityKy;
	private int corPersonKy;
	private String password;
	private String username;
	private String titlename;
	private String givenname;
	private String familyname;
	private String telephone;
	private String description;
	private String role;
	List<UserRole> roles = new LinkedList<UserRole>();

	public int getArcIdentityKy() {
		return arcIdentityKy;
	}

	public void setArcIdentityKy(int arcIdentityKy) {
		this.arcIdentityKy = arcIdentityKy;
	}

	public int getCorPersonKy() {
		return corPersonKy;
	}

	public void setCorPersonKy(int corPersonKy) {
		this.corPersonKy = corPersonKy;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGivenname() {
		return givenname;
	}
	
	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}
	
	public String getFamilyname() {
		return familyname;
	}
	
	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UserRole> getRoles() {
		return roles;
	}
	
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

}
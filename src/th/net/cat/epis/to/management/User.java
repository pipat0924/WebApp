package th.net.cat.epis.to.management;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import th.net.cat.epis.to.AbstractTO;
import th.net.cat.epis.util.AppUtil;

public class User extends AbstractTO implements Serializable {

	private static final long serialVersionUID = 379202859831058851L;

	List<String> groups = new LinkedList<String>();
	private int arcPrincipalKy;
	private int arcIdentityKy;
	private int arcUserAuthnticnKy;
	private int corPersonKy;
	private String username;
	private String password;
	private String confirmpassword;
	private String titlename;
	private String givenname;
	private String familyname;
	private String telephone;
	private String role;
	private String description;
	private String contextPath;
	private String resetPassword;
	
	public User() {}
	public User(String username, String titlename, String givenname, String familyname, String password, String telephone, String role, String description) {
		this.username = username;
		this.titlename = titlename;
		this.givenname = givenname;
		this.familyname = familyname;
		this.password = password;
		this.telephone = telephone;
		this.role = role;
		this.description = description;
	}
	
	public User(int arcIdentityKy, int corPersonKy, String username, String titlename, String givenname, String familyname, String telephone, String role, String description) {
		this.corPersonKy = corPersonKy;
		this.arcIdentityKy = arcIdentityKy;
		this.username = username;
		this.titlename = titlename;
		this.givenname = givenname;
		this.familyname = familyname;
		this.telephone = telephone;
		this.role = role;
		this.description = description;
	}

	public int getArcPrincipalKy() {
		return arcPrincipalKy;
	}
	
	public void setArcPrincipalKy(int arcPrincipalKy) {
		this.arcPrincipalKy = arcPrincipalKy;
	}
	
	public int getArcIdentityKy() {
		return arcIdentityKy;
	}
	
	public void setArcIdentityKy(int arcIdentityKy) {
		this.arcIdentityKy = arcIdentityKy;
	}
	
	public int getArcUserAuthnticnKy() {
		return arcUserAuthnticnKy;
	}
	
	public void setArcUserAuthnticnKy(int arcUserAuthnticnKy) {
		this.arcUserAuthnticnKy = arcUserAuthnticnKy;
	}
	
	public int getCorPersonKy() {
		return corPersonKy;
	}
	
	public void setCorPersonKy(int corPersonKy) {
		this.corPersonKy = corPersonKy;
	}
	
	public List<String> getGroups() {
		return groups;
	}
	
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public String toString() {
		throw new UnsupportedOperationException();
	}

	public String toJson() {
		throw new UnsupportedOperationException();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getTitlename() {
		return titlename;
	}
	public void setTitlename(String titlename) {
		this.titlename = titlename;
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
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstnameStr() {
		return AppUtil.toThaiString(getGivenname() +" "+ getFamilyname());
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getResetPassword() {
		return resetPassword;
	}
	
	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}

}
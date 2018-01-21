package th.net.cat.epis.to.management;

import java.util.LinkedList;
import java.util.List;

public class UserRole {

	String id;
	String code;
	List<UserPermission> permissions = new LinkedList<UserPermission>();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<UserPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<UserPermission> permissions) {
		this.permissions = permissions;
	}

}
package th.net.cat.epis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INV_LOCK")
public class InvoiceLock implements Serializable {
	private static final long serialVersionUID = -2737191784096506250L;
	@Id
	@Column(name="INVNO") private String invNo;
	@Column(name="USER_CREATED") private String userCreated;
	@Column(name="CREATED_TIME") private Date createdTime;
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}

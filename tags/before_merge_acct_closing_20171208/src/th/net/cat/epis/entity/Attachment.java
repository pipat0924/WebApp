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
@Table(name = "ATTACHMENT")
public class Attachment {
	@Id
	@SequenceGenerator(name="ATTACHMENT_SEQ", sequenceName="ATTACHMENT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATTACHMENT_SEQ")
	@Column(name="ATTACH_ID") private Long id;
	@Column(name="PARENT_ID") private Long parentId;
	@Column(name="PARENT_TYPE") private String parentType;
	@Column(name="ATTACH_CODE") private String attachCode;
	@Column(name="ATTACH_TITLE") private String attachTitle;
	@Column(name="ATTACH_DESCRIPTION") private String attachDescription;
	@Column(name="ATTACH_PATH") private String attachPath;
	@Column(name="ATTACH_NAME") private String attachName;
	@Column(name="CREATE_DATE") private Date createDate;
	@Column(name="UPDATE_DATE") private Date updateDate;
	@Column(name="CREATE_USER") private String createUser;
	@Column(name="UPDATE_USER") private String updateUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public String getAttachCode() {
		return attachCode;
	}
	public void setAttachCode(String attachCode) {
		this.attachCode = attachCode;
	}
	public String getAttachTitle() {
		return attachTitle;
	}
	public void setAttachTitle(String attachTitle) {
		this.attachTitle = attachTitle;
	}
	public String getAttachDescription() {
		return attachDescription;
	}
	public void setAttachDescription(String attachDescription) {
		this.attachDescription = attachDescription;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}

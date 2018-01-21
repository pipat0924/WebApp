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
@Table(name="TRSREPRINT")
public class ReprintReceipt {

	@Id
	@SequenceGenerator(name="TRSREPRINT_SEQ", sequenceName="TRSREPRINT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRSREPRINT_SEQ")
	@Column(name="REPRINTID") private Long id;
	@Column(name="RECEIPTID") private Long receiptId;
	@Column(name="CATEGORY") private Long category;
	@Column(name="REASON") private String reason;
	@Column(name="APPROVEDBY") private String approvedBy;
	@Column(name="UPDATEDTTM") private Date updatedDttm;
	@Column(name="UPDATESYSTEM") private String updateSystem;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="VERSIONSTAMP") private Long versionStamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getUpdatedDttm() {
		return updatedDttm;
	}
	public void setUpdatedDttm(Date updatedDttm) {
		this.updatedDttm = updatedDttm;
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

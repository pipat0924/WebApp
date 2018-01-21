package th.net.cat.epis.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="PRINC_PERMISSION_MAPPING",schema = "EPIS")
public class PrincPermissionMapping implements Cloneable{
	@Id
	@SequenceGenerator(name="PRINC_PERMISSION_MAPPING_seq", sequenceName="PRINC_PERMISSION_MAPPING_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRINC_PERMISSION_MAPPING_seq")
	@Column(name="ID") private Long id;
	//@Column(name="PRINCIPALID") private Long principleId;
	//@Column(name="ROLEID") private Long rowId;
	@Column(name="PRINCIPAL_ID") private Long principalId;
	@Column(name="PERMISSION_ID") private Long permissionId;
	@Column(name="CREATED_BY") private String createBy;
	@Column(name="CREATED_DTM") private Timestamp createDate;
	@Column(name="UPDATED_BY") private String updateBy;
	@Column(name="UPDATED_DTM") private Timestamp updateDate;
	
	@Transient private String principalName;
	@Transient private String principalDetail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	public String getPrincipalDetail() {
		return principalDetail;
	}
	public void setPrincipalDetail(String principalDetail) {
		this.principalDetail = principalDetail;
	}
	public PrincPermissionMapping clone() {
        try {
            return (PrincPermissionMapping) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
	
}

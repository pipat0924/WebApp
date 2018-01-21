/**
 * 
 */
package th.net.cat.epis.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author IEC
 *
 */
@Entity
@Table(name="MENU")
public class Menu implements Cloneable,Serializable,Comparable<Menu> {
	@Id
	@SequenceGenerator(name="MENU_seq", sequenceName="MENU_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MENU_seq")
	@Column(name="ID") private Long id;
	@Column(name="NAME") private String name;
	@Column(name="`DESC`") private String desc;
//    @Column(name="TYPE") private String type;

	//@Column(name="`open`")
	@Column(name="PARENT_ID") private Long parrentId;
	@Column(name="`ORDERING`") private Long ordering;
	@Column(name="STATUS") private String status;
	@Column(name="CREATED_BY") private String createBy;
	@Column(name="CREATED_DTM") private Timestamp createDate;
	@Column(name="UPDATED_BY") private String updateBy;
	@Column(name="UPDATED_DTM") private Timestamp updateDate;
	@Column(name="URL") private String url;
	@Transient private int isEnabled;
	@Transient private int principalId;
	@Transient private String principalName;
	@Transient private String principalDetail;
	@Transient private String mainMenuName;
	
	
	
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
	public Long getParrentId() {
		return parrentId;
	}
	public void setParrentId(Long parrentId) {
		this.parrentId = parrentId;
	}
	public Long getOrdering() {
		return ordering;
	}
	public void setOrdering(Long ordering) {
		this.ordering = ordering;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public int getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
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
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createBy == null) ? 0 : createBy.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + isEnabled;
		result = prime * result + ((mainMenuName == null) ? 0 : mainMenuName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ordering == null) ? 0 : ordering.hashCode());
		result = prime * result + ((parrentId == null) ? 0 : parrentId.hashCode());
		result = prime * result + ((principalDetail == null) ? 0 : principalDetail.hashCode());
		result = prime * result + principalId;
		result = prime * result + ((principalName == null) ? 0 : principalName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updateBy == null) ? 0 : updateBy.hashCode());
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (createBy == null) {
			if (other.createBy != null)
				return false;
		} else if (!createBy.equals(other.createBy))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEnabled != other.isEnabled)
			return false;
		if (mainMenuName == null) {
			if (other.mainMenuName != null)
				return false;
		} else if (!mainMenuName.equals(other.mainMenuName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ordering == null) {
			if (other.ordering != null)
				return false;
		} else if (!ordering.equals(other.ordering))
			return false;
		if (parrentId == null) {
			if (other.parrentId != null)
				return false;
		} else if (!parrentId.equals(other.parrentId))
			return false;
		if (principalDetail == null) {
			if (other.principalDetail != null)
				return false;
		} else if (!principalDetail.equals(other.principalDetail))
			return false;
		if (principalId != other.principalId)
			return false;
		if (principalName == null) {
			if (other.principalName != null)
				return false;
		} else if (!principalName.equals(other.principalName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updateBy == null) {
			if (other.updateBy != null)
				return false;
		} else if (!updateBy.equals(other.updateBy))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	public String getMainMenuName() {
		return mainMenuName;
	}
	public void setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
	}
	@Override
	public int compareTo(Menu arg0) {
		return new CompareToBuilder()
		.append(getOrdering(), arg0.getOrdering())
        .toComparison();
	}
	
	 public Menu clone() {
	        try {
	            return (Menu) super.clone();
	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	            throw new RuntimeException();
	        }
	    }
	
	
}

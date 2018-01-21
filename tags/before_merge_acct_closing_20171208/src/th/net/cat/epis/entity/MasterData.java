package th.net.cat.epis.entity;

        import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MASTER_DATA")
public class MasterData {

    @Id
    @SequenceGenerator(name="MASTER_DATA_SEQ", sequenceName="MASTER_DATA_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_DATA_SEQ")
    @Column(name="ID") private Long id;
    @Column(name="KEY") private String key;
    @Column(name="VALUE") private String value;
    @Column(name="GROUP_KEY") private String groupKey;
    @Column(name="TYPE") private String type;
    @Column(name="STATUS") private String status;
    @Column(name="ORDERED") private Integer ordered;
    //	@Column(name="GROUP_ID") private Long groupId;
    @Column(name="PARENT_ID") private Long parentId;
    @Column(name="REF_ID") private Long refId;
    @Column(name="PROPERTY_1") private String property1;
    @Column(name="PROPERTY_2") private String property2;
    @Column(name="PROPERTY_3") private String property3;
    @Column(name="PROPERTY_4") private String property4;
    @Column(name="PROPERTY_5") private String property5;
    @Column(name="CREATED_BY") private String createBy;
    @Column(name="CREATED_DTM") private Timestamp createDate;
    @Column(name="UPDATED_BY") private String updateBy;
    @Column(name="UPDATED_DTM") private Timestamp updateDate;
    @Column(name="INITIAL_VALUE") private String initialValue;
    
    
    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getOrdered() {
        return ordered;
    }
    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Long getRefId() {
        return refId;
    }
    public void setRefId(Long refId) {
        this.refId = refId;
    }
    public String getGroupKey() {
        return groupKey;
    }
    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }
    public String getProperty1() {
        return property1;
    }
    public void setProperty1(String property1) {
        this.property1 = property1;
    }
    public String getProperty2() {
        return property2;
    }
    public void setProperty2(String property2) {
        this.property2 = property2;
    }
    public String getProperty3() {
        return property3;
    }
    public void setProperty3(String property3) {
        this.property3 = property3;
    }
    public String getProperty4() {
        return property4;
    }
    public void setProperty4(String property4) {
        this.property4 = property4;
    }
    public String getProperty5() {
        return property5;
    }
    public void setProperty5(String property5) {
        this.property5 = property5;
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
	public String getInitialValue() {
		return initialValue;
	}
	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}
}

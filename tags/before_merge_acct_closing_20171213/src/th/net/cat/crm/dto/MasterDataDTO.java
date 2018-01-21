package th.net.cat.crm.dto;

import java.io.Serializable;
//import th.net.cat.epis.entity.MasterData;

public class MasterDataDTO implements Serializable{
	
	private static final long serialVersionUID = -3266854470873548092L;
	
	private Long id;
	private String key;
	private String value;
	private String groupKey;
	private String type;
	private String status;
	private Integer ordered;
//	private Long groupId;
	private Long parentId;
	private Long refId;
	private String property1;
	private String property2;
	private String property3;
	private String property4;
	private String property5;
	
	public MasterDataDTO(){
		
	}
/*	
	public MasterDataDTO(MasterData entity){
		if(entity != null){
			this.id = entity.getId();
			this.key = entity.getKey();
			this.value = entity.getValue();
			this.groupKey = entity.getGroupKey();
			this.type = entity.getType();
			this.status = entity.getStatus();
			this.ordered = entity.getOrdered();
//			this.groupId = entity.getGroupId();
			this.parentId = entity.getParentId();
			this.refId = entity.getRefId();
			this.property1 = entity.getProperty1();
			this.property2 = entity.getProperty2();
			this.property3 = entity.getProperty3();
			this.property4 = entity.getProperty4();
			this.property5 = entity.getProperty5();
		}
	}
*/	
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
}

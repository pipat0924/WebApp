package th.net.cat.crm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="V_CATCRM_CUSTOMER_PROFILE",schema = "CRMDATA")
public class CustomerProfile {

	@Id
	@Column(name="CUSTOMER_ID") private String id;
	@Column(name="CUSTOMER_NUMBER") private String no;
	@Column(name="CUSTOMER_TYPE") private String type;
	@Column(name="CAT_CARD_TYPE_CODE") private Long typeCode;
	@Column(name="CAT_CARD_TYPE_DESC") private String typeDesc;
	@Column(name="CAT_CARD_NUMBER") private String cardNo;
	@Column(name="CUSTOMER_FULL_NAME") private String fullName;
	@Column(name="EMPLOYEE_ID") private String employeeId;
	@Column(name="CUSTOMER_GROUP_CODE") private String groupCode;
	@Column(name="CUSTOMER_GROUP_NAME") private String groupName;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CAT_CUSTOMER_SEGMENT") private CustomerSegment segment;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CAT_CUSTOMER_GROUP") private CustomerGroup group;
//	@ManyToOne(fetch=FetchType.EAGER, targetEntity=CustomerType.class)
//	@JoinColumn(name="CAT_CUSTOMER_TYPE", nullable=true) private CustomerType category;
	@OneToMany(mappedBy="customerId", fetch=FetchType.EAGER,cascade= CascadeType.PERSIST)
	private List<CustomerAddress> addresses = new ArrayList<CustomerAddress>();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(Long typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public CustomerSegment getSegment() {
		return segment;
	}
	public void setSegment(CustomerSegment segment) {
		this.segment = segment;
	}
	public CustomerGroup getGroup() {
		return group;
	}
	public void setGroup(CustomerGroup group) {
		this.group = group;
	}
//	public CustomerType getCategory() {
//		return category;
//	}
//	public void setCategory(CustomerType category) {
//		this.category = category;
//	}
	public List<CustomerAddress> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<CustomerAddress> addresses) {
		this.addresses = addresses;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
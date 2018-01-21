package th.net.cat.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_CATCRM_ADDRESS_PROFILE",schema = "CRMDATA")
public class CustomerAddress {

	@Id
	@Column(name="ADDRESS_ID") private String id;
	@Column(name="CUSTOMER_ID") private String customerId;
	@Column(name="ID_REGISTER_NUMBER") private String registerNo;
	@Column(name="CUSTOMER_NUMBER") private String customerNo;
	@Column(name="CUSTOMER_FULL_NAME") private String customerFullName;
	@Column(name="CAT_CARD_NUMBER") private String cardNo;
	@Column(name="CAT_CARD_TYPE_DESC") private String cardType;
	@Column(name="PRIMARY_FLAG") private String flag;
	@Column(name="HOUSE_NUMBER") private String no;
	@Column(name="ADDRESS_TYPE") private String type;
	@Column(name="MOO") private String moo;
	@Column(name="VILLAGE") private String village;
	@Column(name="BUILDING_FLOOR_ROOM") private String soi;
	@Column(name="ROAD") private String road;
	@Column(name="KWANG_TAMBON") private String tumbon;
	@Column(name="KHET_AMPHUR") private String amphur;
	@Column(name="PROVINCE") private String province;
	@Column(name="POSTAL_CODE") private String postCode;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerFullName() {
		return customerFullName;
	}
	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getMoo() {
		return moo;
	}
	public void setMoo(String moo) {
		this.moo = moo;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getSoi() {
		return soi;
	}
	public void setSoi(String soi) {
		this.soi = soi;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getTumbon() {
		return tumbon;
	}
	public void setTumbon(String tumbon) {
		this.tumbon = tumbon;
	}
	public String getAmphur() {
		return amphur;
	}
	public void setAmphur(String amphur) {
		this.amphur = amphur;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
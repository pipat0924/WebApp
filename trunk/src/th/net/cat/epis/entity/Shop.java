package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MASSHOP")
public class Shop {

	@Id
	@SequenceGenerator(name="shop_seq", sequenceName="shop_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shop_seq")

	@Column(name="SHOPID") private Long id;
	@Column(name="SHOPNO") private String no;
	@Column(name="SHOPNAME") private String name;
	@Column(name="BUPLACE") private String businessPlace;
	@Column(name="BUPLACENAME") private String buPlaceName;
	@Column(name="BUAREA") private String businessArea;
	@Column(name="BUAREANAME") private String buAreaName;
	@Column(name="COSTCENTER") private String costCenter;
	@Column(name="COSTCENTERNAME") private String costCenterName;
	@Column(name="DESCABVRTH") private String descAbvrTh;
	@Column(name="ISPOSITIVE") private Integer isPositive;
	@Column(name="DESCRIPTION") private String description;
    @Column(name="BUILDING") private String building;
    @Column(name="FLOOR") private String floor;
    @Column(name="ROOM") private String room;
    @Column(name="ADDRESS") private String address;
    @Column(name="STREET") private String street;
    @Column(name="SUBDISTRICT") private String subdistrict;
    @Column(name="DISTRICT") private String district;
    @Column(name="PROVINCE") private String province;
    @Column(name="ZIPCODE") private String zipCode;
    @Column(name="PHONE") private String phone;
    @Column(name="FAX") private String fax;
    @Column(name="EMAIL") private String email;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBusinessPlace() {
		return businessPlace;
	}
	
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}
	
	public String getBusinessArea() {
		return businessArea;
	}
	
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	public String getCostCenter() {
		return costCenter;
	}
	
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	
	public String getDescAbvrTh() {
		return descAbvrTh;
	}
	
	public void setDescAbvrTh(String descAbvrTh) {
		this.descAbvrTh = descAbvrTh;
	}
	
	public Integer getIsPositive() {
		return isPositive;
	}
	
	public void setIsPositive(Integer isPositive) {
		this.isPositive = isPositive;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBuPlaceName() {
		return buPlaceName;
	}

	public void setBuPlaceName(String buPlaceName) {
		this.buPlaceName = buPlaceName;
	}

	public String getBuAreaName() {
		return buAreaName;
	}

	public void setBuAreaName(String buAreaName) {
		this.buAreaName = buAreaName;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	
}
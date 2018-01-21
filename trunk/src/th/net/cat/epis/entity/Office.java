package th.net.cat.epis.entity;

import javax.persistence.*;

/**
 * Created by T'Tee Puthy on 1/31/2017.
 */
@Entity
@Table(name="MASOFFICE")
public class Office {

    @Id
    @SequenceGenerator(name="office_seq", sequenceName="office_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="office_seq")
    @Column(name="OFFICEID") private Long id;
    @Column(name="BUPLACECODE") private String buPlaceCode;
    @Column(name="BUPLACENAME") private String buPlaceName;
    @Column(name="BUAREACODE") private String buAreaCode;
    @Column(name="BUAREANAME") private String buAreaName;
    @Column(name="BUAREANAMEABBR") private String buAreaNameAbbr;
    @Column(name="COSTCENTERCODE") private String costCenterCode;
    @Column(name="COSTCENTERNAME") private String costCenterName;
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
    @Column(name="ISPOSITIVE") private Integer isPositive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuPlaceCode() {
        return buPlaceCode;
    }

    public void setBuPlaceCode(String buPlaceCode) {
        this.buPlaceCode = buPlaceCode;
    }

    public String getBuPlaceName() {
        return buPlaceName;
    }

    public void setBuPlaceName(String buPlaceName) {
        this.buPlaceName = buPlaceName;
    }

    public String getBuAreaCode() {
        return buAreaCode;
    }

    public void setBuAreaCode(String buAreaCode) {
        this.buAreaCode = buAreaCode;
    }

    public String getBuAreaName() {
        return buAreaName;
    }

    public void setBuAreaName(String buAreaName) {
        this.buAreaName = buAreaName;
    }

    public String getBuAreaNameAbbr() {
        return buAreaNameAbbr;
    }

    public void setBuAreaNameAbbr(String buAreaNameAbbr) {
        this.buAreaNameAbbr = buAreaNameAbbr;
    }

    public String getCostCenterCode() {
        return costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
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

    public Integer getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Integer isPositive) {
        this.isPositive = isPositive;
    }
}


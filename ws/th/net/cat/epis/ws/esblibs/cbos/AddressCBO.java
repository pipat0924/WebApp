
package th.net.cat.epis.ws.esblibs.cbos;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AddressId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SourceAddressId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DestinationAddressId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="HouseNo" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Moo" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Village" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BuildingInformation" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="200"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TrokSoi" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Street" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Tambon" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="City" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="State" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PostalCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Fax" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="50"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BillLanguage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Telephone" type="{http://ESBLibs/CBOs}TelephoneCBO" minOccurs="0"/&gt;
 *         &lt;element name="AddressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AddressLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AddressLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatTitle" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="VatFName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="60"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="VatLName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="60"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="VatCompanyName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="60"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustCity" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustState" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="28"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustZipCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="16"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustTambon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustHouseNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustMoo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustVillage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustBuildingInformation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustTrokSoi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustGeocode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GeoCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatCompanyNameExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressCBO", propOrder = {
    "addressId",
    "sourceAddressId",
    "destinationAddressId",
    "houseNo",
    "moo",
    "village",
    "buildingInformation",
    "trokSoi",
    "street",
    "tambon",
    "city",
    "state",
    "postalCode",
    "fax",
    "billLanguage",
    "telephone",
    "addressLine1",
    "addressLine2",
    "addressLine3",
    "custAddress1",
    "custAddress2",
    "custAddress3",
    "vatTitle",
    "vatFName",
    "vatLName",
    "vatCompanyName",
    "custCity",
    "custState",
    "custZipCode",
    "custTambon",
    "custCountryCode",
    "custHouseNo",
    "custMoo",
    "custVillage",
    "custBuildingInformation",
    "custTrokSoi",
    "custFax",
    "custGeocode",
    "countryCode",
    "geoCode",
    "vatCompanyNameExt"
})
public class AddressCBO {

    @XmlElement(name = "AddressId")
    protected String addressId;
    @XmlElement(name = "SourceAddressId")
    protected String sourceAddressId;
    @XmlElement(name = "DestinationAddressId")
    protected String destinationAddressId;
    @XmlElement(name = "HouseNo")
    protected String houseNo;
    @XmlElement(name = "Moo")
    protected String moo;
    @XmlElement(name = "Village")
    protected String village;
    @XmlElement(name = "BuildingInformation")
    protected String buildingInformation;
    @XmlElement(name = "TrokSoi")
    protected String trokSoi;
    @XmlElement(name = "Street")
    protected String street;
    @XmlElement(name = "Tambon")
    protected String tambon;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "PostalCode")
    protected String postalCode;
    @XmlElement(name = "Fax")
    protected String fax;
    @XmlElement(name = "BillLanguage")
    protected BigInteger billLanguage;
    @XmlElement(name = "Telephone")
    protected TelephoneCBO telephone;
    @XmlElement(name = "AddressLine1")
    protected String addressLine1;
    @XmlElement(name = "AddressLine2")
    protected String addressLine2;
    @XmlElement(name = "AddressLine3")
    protected String addressLine3;
    @XmlElement(name = "CustAddress1")
    protected String custAddress1;
    @XmlElement(name = "CustAddress2")
    protected String custAddress2;
    @XmlElement(name = "CustAddress3")
    protected String custAddress3;
    @XmlElement(name = "VatTitle")
    protected String vatTitle;
    @XmlElement(name = "VatFName")
    protected String vatFName;
    @XmlElement(name = "VatLName")
    protected String vatLName;
    @XmlElement(name = "VatCompanyName")
    protected String vatCompanyName;
    @XmlElement(name = "CustCity")
    protected String custCity;
    @XmlElement(name = "CustState")
    protected String custState;
    @XmlElement(name = "CustZipCode")
    protected String custZipCode;
    @XmlElement(name = "CustTambon")
    protected String custTambon;
    @XmlElement(name = "CustCountryCode")
    protected String custCountryCode;
    @XmlElement(name = "CustHouseNo")
    protected String custHouseNo;
    @XmlElement(name = "CustMoo")
    protected String custMoo;
    @XmlElement(name = "CustVillage")
    protected String custVillage;
    @XmlElement(name = "CustBuildingInformation")
    protected String custBuildingInformation;
    @XmlElement(name = "CustTrokSoi")
    protected String custTrokSoi;
    @XmlElement(name = "CustFax")
    protected String custFax;
    @XmlElement(name = "CustGeocode")
    protected String custGeocode;
    @XmlElement(name = "CountryCode")
    protected String countryCode;
    @XmlElement(name = "GeoCode")
    protected String geoCode;
    @XmlElement(name = "VatCompanyNameExt")
    protected String vatCompanyNameExt;

    /**
     * Gets the value of the addressId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * Sets the value of the addressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressId(String value) {
        this.addressId = value;
    }

    /**
     * Gets the value of the sourceAddressId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceAddressId() {
        return sourceAddressId;
    }

    /**
     * Sets the value of the sourceAddressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceAddressId(String value) {
        this.sourceAddressId = value;
    }

    /**
     * Gets the value of the destinationAddressId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationAddressId() {
        return destinationAddressId;
    }

    /**
     * Sets the value of the destinationAddressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationAddressId(String value) {
        this.destinationAddressId = value;
    }

    /**
     * Gets the value of the houseNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * Sets the value of the houseNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNo(String value) {
        this.houseNo = value;
    }

    /**
     * Gets the value of the moo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoo() {
        return moo;
    }

    /**
     * Sets the value of the moo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoo(String value) {
        this.moo = value;
    }

    /**
     * Gets the value of the village property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVillage() {
        return village;
    }

    /**
     * Sets the value of the village property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVillage(String value) {
        this.village = value;
    }

    /**
     * Gets the value of the buildingInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingInformation() {
        return buildingInformation;
    }

    /**
     * Sets the value of the buildingInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingInformation(String value) {
        this.buildingInformation = value;
    }

    /**
     * Gets the value of the trokSoi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrokSoi() {
        return trokSoi;
    }

    /**
     * Sets the value of the trokSoi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrokSoi(String value) {
        this.trokSoi = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the tambon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTambon() {
        return tambon;
    }

    /**
     * Sets the value of the tambon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTambon(String value) {
        this.tambon = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the billLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillLanguage() {
        return billLanguage;
    }

    /**
     * Sets the value of the billLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillLanguage(BigInteger value) {
        this.billLanguage = value;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * @return
     *     possible object is
     *     {@link TelephoneCBO }
     *     
     */
    public TelephoneCBO getTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelephoneCBO }
     *     
     */
    public void setTelephone(TelephoneCBO value) {
        this.telephone = value;
    }

    /**
     * Gets the value of the addressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the addressLine3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * Sets the value of the addressLine3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine3(String value) {
        this.addressLine3 = value;
    }

    /**
     * Gets the value of the custAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustAddress1() {
        return custAddress1;
    }

    /**
     * Sets the value of the custAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustAddress1(String value) {
        this.custAddress1 = value;
    }

    /**
     * Gets the value of the custAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustAddress2() {
        return custAddress2;
    }

    /**
     * Sets the value of the custAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustAddress2(String value) {
        this.custAddress2 = value;
    }

    /**
     * Gets the value of the custAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustAddress3() {
        return custAddress3;
    }

    /**
     * Sets the value of the custAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustAddress3(String value) {
        this.custAddress3 = value;
    }

    /**
     * Gets the value of the vatTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatTitle() {
        return vatTitle;
    }

    /**
     * Sets the value of the vatTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatTitle(String value) {
        this.vatTitle = value;
    }

    /**
     * Gets the value of the vatFName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatFName() {
        return vatFName;
    }

    /**
     * Sets the value of the vatFName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatFName(String value) {
        this.vatFName = value;
    }

    /**
     * Gets the value of the vatLName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatLName() {
        return vatLName;
    }

    /**
     * Sets the value of the vatLName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatLName(String value) {
        this.vatLName = value;
    }

    /**
     * Gets the value of the vatCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatCompanyName() {
        return vatCompanyName;
    }

    /**
     * Sets the value of the vatCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatCompanyName(String value) {
        this.vatCompanyName = value;
    }

    /**
     * Gets the value of the custCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustCity() {
        return custCity;
    }

    /**
     * Sets the value of the custCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustCity(String value) {
        this.custCity = value;
    }

    /**
     * Gets the value of the custState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustState() {
        return custState;
    }

    /**
     * Sets the value of the custState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustState(String value) {
        this.custState = value;
    }

    /**
     * Gets the value of the custZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustZipCode() {
        return custZipCode;
    }

    /**
     * Sets the value of the custZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustZipCode(String value) {
        this.custZipCode = value;
    }

    /**
     * Gets the value of the custTambon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTambon() {
        return custTambon;
    }

    /**
     * Sets the value of the custTambon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTambon(String value) {
        this.custTambon = value;
    }

    /**
     * Gets the value of the custCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustCountryCode() {
        return custCountryCode;
    }

    /**
     * Sets the value of the custCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustCountryCode(String value) {
        this.custCountryCode = value;
    }

    /**
     * Gets the value of the custHouseNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustHouseNo() {
        return custHouseNo;
    }

    /**
     * Sets the value of the custHouseNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustHouseNo(String value) {
        this.custHouseNo = value;
    }

    /**
     * Gets the value of the custMoo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustMoo() {
        return custMoo;
    }

    /**
     * Sets the value of the custMoo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustMoo(String value) {
        this.custMoo = value;
    }

    /**
     * Gets the value of the custVillage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustVillage() {
        return custVillage;
    }

    /**
     * Sets the value of the custVillage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustVillage(String value) {
        this.custVillage = value;
    }

    /**
     * Gets the value of the custBuildingInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBuildingInformation() {
        return custBuildingInformation;
    }

    /**
     * Sets the value of the custBuildingInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBuildingInformation(String value) {
        this.custBuildingInformation = value;
    }

    /**
     * Gets the value of the custTrokSoi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTrokSoi() {
        return custTrokSoi;
    }

    /**
     * Sets the value of the custTrokSoi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTrokSoi(String value) {
        this.custTrokSoi = value;
    }

    /**
     * Gets the value of the custFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustFax() {
        return custFax;
    }

    /**
     * Sets the value of the custFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustFax(String value) {
        this.custFax = value;
    }

    /**
     * Gets the value of the custGeocode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustGeocode() {
        return custGeocode;
    }

    /**
     * Sets the value of the custGeocode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustGeocode(String value) {
        this.custGeocode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the geoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeoCode() {
        return geoCode;
    }

    /**
     * Sets the value of the geoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeoCode(String value) {
        this.geoCode = value;
    }

    /**
     * Gets the value of the vatCompanyNameExt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatCompanyNameExt() {
        return vatCompanyNameExt;
    }

    /**
     * Sets the value of the vatCompanyNameExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatCompanyNameExt(String value) {
        this.vatCompanyNameExt = value;
    }

}

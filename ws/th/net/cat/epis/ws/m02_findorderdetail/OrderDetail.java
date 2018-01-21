
package th.net.cat.epis.ws.m02_findorderdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Ttile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Zipcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CategoryId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatRate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="PortFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="QtyPort" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="TotalFeeAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="VatAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TaxId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BranchCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="BranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MDN" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ICCD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderDetail", propOrder = {
    "orderId",
    "ttile",
    "name",
    "surname",
    "address",
    "zipcode",
    "categoryId",
    "categoryName",
    "vatRate",
    "portFee",
    "qtyPort",
    "totalFeeAmount",
    "vatAddress1",
    "vatAddress2",
    "vatAddress3",
    "taxId",
    "branchCode",
    "branchName",
    "mdn",
    "iccd",
    "totalAmount"
})
public class OrderDetail {

    @XmlElement(name = "OrderId")
    protected String orderId;
    @XmlElement(name = "Ttile")
    protected String ttile;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Surname")
    protected String surname;
    @XmlElement(name = "Address")
    protected String address;
    @XmlElement(name = "Zipcode")
    protected String zipcode;
    @XmlElement(name = "CategoryId")
    protected Integer categoryId;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "VatRate")
    protected Integer vatRate;
    @XmlElement(name = "PortFee")
    protected Double portFee;
    @XmlElement(name = "QtyPort")
    protected Double qtyPort;
    @XmlElement(name = "TotalFeeAmount")
    protected Double totalFeeAmount;
    @XmlElement(name = "VatAddress1")
    protected String vatAddress1;
    @XmlElement(name = "VatAddress2")
    protected String vatAddress2;
    @XmlElement(name = "VatAddress3")
    protected String vatAddress3;
    @XmlElement(name = "TaxId")
    protected String taxId;
    @XmlElement(name = "BranchCode")
    protected Integer branchCode;
    @XmlElement(name = "BranchName")
    protected String branchName;
    @XmlElement(name = "MDN")
    protected Integer mdn;
    @XmlElement(name = "ICCD")
    protected String iccd;
    @XmlElement(name = "TotalAmount")
    protected Double totalAmount;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the ttile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTtile() {
        return ttile;
    }

    /**
     * Sets the value of the ttile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTtile(String value) {
        this.ttile = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the zipcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets the value of the zipcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipcode(String value) {
        this.zipcode = value;
    }

    /**
     * Gets the value of the categoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the vatRate property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVatRate() {
        return vatRate;
    }

    /**
     * Sets the value of the vatRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVatRate(Integer value) {
        this.vatRate = value;
    }

    /**
     * Gets the value of the portFee property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPortFee() {
        return portFee;
    }

    /**
     * Sets the value of the portFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPortFee(Double value) {
        this.portFee = value;
    }

    /**
     * Gets the value of the qtyPort property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQtyPort() {
        return qtyPort;
    }

    /**
     * Sets the value of the qtyPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQtyPort(Double value) {
        this.qtyPort = value;
    }

    /**
     * Gets the value of the totalFeeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalFeeAmount() {
        return totalFeeAmount;
    }

    /**
     * Sets the value of the totalFeeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalFeeAmount(Double value) {
        this.totalFeeAmount = value;
    }

    /**
     * Gets the value of the vatAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatAddress1() {
        return vatAddress1;
    }

    /**
     * Sets the value of the vatAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatAddress1(String value) {
        this.vatAddress1 = value;
    }

    /**
     * Gets the value of the vatAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatAddress2() {
        return vatAddress2;
    }

    /**
     * Sets the value of the vatAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatAddress2(String value) {
        this.vatAddress2 = value;
    }

    /**
     * Gets the value of the vatAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatAddress3() {
        return vatAddress3;
    }

    /**
     * Sets the value of the vatAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatAddress3(String value) {
        this.vatAddress3 = value;
    }

    /**
     * Gets the value of the taxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Sets the value of the taxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxId(String value) {
        this.taxId = value;
    }

    /**
     * Gets the value of the branchCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBranchCode() {
        return branchCode;
    }

    /**
     * Sets the value of the branchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBranchCode(Integer value) {
        this.branchCode = value;
    }

    /**
     * Gets the value of the branchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Sets the value of the branchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchName(String value) {
        this.branchName = value;
    }

    /**
     * Gets the value of the mdn property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMDN() {
        return mdn;
    }

    /**
     * Sets the value of the mdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMDN(Integer value) {
        this.mdn = value;
    }

    /**
     * Gets the value of the iccd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICCD() {
        return iccd;
    }

    /**
     * Sets the value of the iccd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICCD(String value) {
        this.iccd = value;
    }

    /**
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalAmount(Double value) {
        this.totalAmount = value;
    }

}

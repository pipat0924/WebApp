
package th.net.cat.epis.ws.f19_retrieveaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PropertyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PropertyOne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PropertyTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillingAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustomerFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillingGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TaxRegisterNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BranchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillAddrLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillAddrLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillAddrLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillAddrLine4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatAddrLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatAddrLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatAddrLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VatAddrLine4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountBO", propOrder = {
    "propertyLabel",
    "propertyOne",
    "propertyTwo",
    "billingAccountNo",
    "customerFullName",
    "billingGroup",
    "taxRegisterNo",
    "branchId",
    "billAddrLine1",
    "billAddrLine2",
    "billAddrLine3",
    "billAddrLine4",
    "vatAddrLine1",
    "vatAddrLine2",
    "vatAddrLine3",
    "vatAddrLine4"
})
public class AccountBO {

    @XmlElement(name = "PropertyLabel")
    protected String propertyLabel;
    @XmlElement(name = "PropertyOne")
    protected String propertyOne;
    @XmlElement(name = "PropertyTwo")
    protected String propertyTwo;
    @XmlElement(name = "BillingAccountNo")
    protected String billingAccountNo;
    @XmlElement(name = "CustomerFullName")
    protected String customerFullName;
    @XmlElement(name = "BillingGroup")
    protected String billingGroup;
    @XmlElement(name = "TaxRegisterNo")
    protected String taxRegisterNo;
    @XmlElement(name = "BranchId")
    protected String branchId;
    @XmlElement(name = "BillAddrLine1")
    protected String billAddrLine1;
    @XmlElement(name = "BillAddrLine2")
    protected String billAddrLine2;
    @XmlElement(name = "BillAddrLine3")
    protected String billAddrLine3;
    @XmlElement(name = "BillAddrLine4")
    protected String billAddrLine4;
    @XmlElement(name = "VatAddrLine1")
    protected String vatAddrLine1;
    @XmlElement(name = "VatAddrLine2")
    protected String vatAddrLine2;
    @XmlElement(name = "VatAddrLine3")
    protected String vatAddrLine3;
    @XmlElement(name = "VatAddrLine4")
    protected String vatAddrLine4;

    /**
     * Gets the value of the propertyLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyLabel() {
        return propertyLabel;
    }

    /**
     * Sets the value of the propertyLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyLabel(String value) {
        this.propertyLabel = value;
    }

    /**
     * Gets the value of the propertyOne property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyOne() {
        return propertyOne;
    }

    /**
     * Sets the value of the propertyOne property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyOne(String value) {
        this.propertyOne = value;
    }

    /**
     * Gets the value of the propertyTwo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyTwo() {
        return propertyTwo;
    }

    /**
     * Sets the value of the propertyTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyTwo(String value) {
        this.propertyTwo = value;
    }

    /**
     * Gets the value of the billingAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNo() {
        return billingAccountNo;
    }

    /**
     * Sets the value of the billingAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNo(String value) {
        this.billingAccountNo = value;
    }

    /**
     * Gets the value of the customerFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerFullName() {
        return customerFullName;
    }

    /**
     * Sets the value of the customerFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerFullName(String value) {
        this.customerFullName = value;
    }

    /**
     * Gets the value of the billingGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingGroup() {
        return billingGroup;
    }

    /**
     * Sets the value of the billingGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingGroup(String value) {
        this.billingGroup = value;
    }

    /**
     * Gets the value of the taxRegisterNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxRegisterNo() {
        return taxRegisterNo;
    }

    /**
     * Sets the value of the taxRegisterNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxRegisterNo(String value) {
        this.taxRegisterNo = value;
    }

    /**
     * Gets the value of the branchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * Sets the value of the branchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchId(String value) {
        this.branchId = value;
    }

    /**
     * Gets the value of the billAddrLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddrLine1() {
        return billAddrLine1;
    }

    /**
     * Sets the value of the billAddrLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddrLine1(String value) {
        this.billAddrLine1 = value;
    }

    /**
     * Gets the value of the billAddrLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddrLine2() {
        return billAddrLine2;
    }

    /**
     * Sets the value of the billAddrLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddrLine2(String value) {
        this.billAddrLine2 = value;
    }

    /**
     * Gets the value of the billAddrLine3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddrLine3() {
        return billAddrLine3;
    }

    /**
     * Sets the value of the billAddrLine3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddrLine3(String value) {
        this.billAddrLine3 = value;
    }

    /**
     * Gets the value of the billAddrLine4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddrLine4() {
        return billAddrLine4;
    }

    /**
     * Sets the value of the billAddrLine4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddrLine4(String value) {
        this.billAddrLine4 = value;
    }

    /**
     * Gets the value of the vatAddrLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatAddrLine1() {
        return vatAddrLine1;
    }

    /**
     * Sets the value of the vatAddrLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatAddrLine1(String value) {
        this.vatAddrLine1 = value;
    }

    /**
     * Gets the value of the vatAddrLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatAddrLine2() {
        return vatAddrLine2;
    }

    /**
     * Sets the value of the vatAddrLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatAddrLine2(String value) {
        this.vatAddrLine2 = value;
    }

    /**
     * Gets the value of the vatAddrLine3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatAddrLine3() {
        return vatAddrLine3;
    }

    /**
     * Sets the value of the vatAddrLine3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatAddrLine3(String value) {
        this.vatAddrLine3 = value;
    }

    /**
     * Gets the value of the vatAddrLine4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatAddrLine4() {
        return vatAddrLine4;
    }

    /**
     * Sets the value of the vatAddrLine4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatAddrLine4(String value) {
        this.vatAddrLine4 = value;
    }

}

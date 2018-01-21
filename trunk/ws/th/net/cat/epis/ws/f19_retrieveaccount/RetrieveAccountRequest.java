
package th.net.cat.epis.ws.f19_retrieveaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;


/**
 * <p>Java class for RetrieveAccountRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetrieveAccountRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionLog" type="{http://ESBLibs/CBOs}TransactionLogCBO" minOccurs="0"/&gt;
 *         &lt;element name="BillingAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TaxRegisterNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PropertyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PropertyOne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PropertyTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LimitRow" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveAccountRequest", propOrder = {
    "transactionLog",
    "billingAccountNo",
    "taxRegisterNo",
    "billFirstName",
    "billLastName",
    "propertyLabel",
    "propertyOne",
    "propertyTwo",
    "billCompName",
    "limitRow"
})
public class RetrieveAccountRequest {

    @XmlElement(name = "TransactionLog")
    protected TransactionLogCBO transactionLog;
    @XmlElement(name = "BillingAccountNo")
    protected String billingAccountNo;
    @XmlElement(name = "TaxRegisterNo")
    protected String taxRegisterNo;
    @XmlElement(name = "BillFirstName")
    protected String billFirstName;
    @XmlElement(name = "BillLastName")
    protected String billLastName;
    @XmlElement(name = "PropertyLabel")
    protected String propertyLabel;
    @XmlElement(name = "PropertyOne")
    protected String propertyOne;
    @XmlElement(name = "PropertyTwo")
    protected String propertyTwo;
    @XmlElement(name = "BillCompName")
    protected String billCompName;
    @XmlElement(name = "LimitRow")
    protected Integer limitRow;

    /**
     * Gets the value of the transactionLog property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionLogCBO }
     *     
     */
    public TransactionLogCBO getTransactionLog() {
        return transactionLog;
    }

    /**
     * Sets the value of the transactionLog property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionLogCBO }
     *     
     */
    public void setTransactionLog(TransactionLogCBO value) {
        this.transactionLog = value;
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
     * Gets the value of the billFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillFirstName() {
        return billFirstName;
    }

    /**
     * Sets the value of the billFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillFirstName(String value) {
        this.billFirstName = value;
    }

    /**
     * Gets the value of the billLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillLastName() {
        return billLastName;
    }

    /**
     * Sets the value of the billLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillLastName(String value) {
        this.billLastName = value;
    }

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
     * Gets the value of the billCompName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCompName() {
        return billCompName;
    }

    /**
     * Sets the value of the billCompName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCompName(String value) {
        this.billCompName = value;
    }

    /**
     * Gets the value of the limitRow property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLimitRow() {
        return limitRow;
    }

    /**
     * Sets the value of the limitRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLimitRow(Integer value) {
        this.limitRow = value;
    }

}

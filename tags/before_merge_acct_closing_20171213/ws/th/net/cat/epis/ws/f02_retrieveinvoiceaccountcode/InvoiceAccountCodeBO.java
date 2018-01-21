
package th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvoiceAccountCodeBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceAccountCodeBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BillRefNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="AccountCodeNew" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AccountCodeNewDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RevTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RevTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SeqmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SeqmentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SubProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SubProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceAccountCodeBO", propOrder = {
    "billRefNo",
    "accountCodeNew",
    "accountCodeNewDesc",
    "revTypeCode",
    "revTypeName",
    "seqmentCode",
    "seqmentName",
    "productCode",
    "productName",
    "subProductCode",
    "subProductName",
    "amount"
})
public class InvoiceAccountCodeBO {

    @XmlElement(name = "BillRefNo")
    protected Integer billRefNo;
    @XmlElement(name = "AccountCodeNew")
    protected String accountCodeNew;
    @XmlElement(name = "AccountCodeNewDesc")
    protected String accountCodeNewDesc;
    @XmlElement(name = "RevTypeCode")
    protected String revTypeCode;
    @XmlElement(name = "RevTypeName")
    protected String revTypeName;
    @XmlElement(name = "SeqmentCode")
    protected String seqmentCode;
    @XmlElement(name = "SeqmentName")
    protected String seqmentName;
    @XmlElement(name = "ProductCode")
    protected String productCode;
    @XmlElement(name = "ProductName")
    protected String productName;
    @XmlElement(name = "SubProductCode")
    protected String subProductCode;
    @XmlElement(name = "SubProductName")
    protected String subProductName;
    @XmlElement(name = "Amount")
    protected Double amount;

    /**
     * Gets the value of the billRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBillRefNo() {
        return billRefNo;
    }

    /**
     * Sets the value of the billRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBillRefNo(Integer value) {
        this.billRefNo = value;
    }

    /**
     * Gets the value of the accountCodeNew property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCodeNew() {
        return accountCodeNew;
    }

    /**
     * Sets the value of the accountCodeNew property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCodeNew(String value) {
        this.accountCodeNew = value;
    }

    /**
     * Gets the value of the accountCodeNewDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCodeNewDesc() {
        return accountCodeNewDesc;
    }

    /**
     * Sets the value of the accountCodeNewDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCodeNewDesc(String value) {
        this.accountCodeNewDesc = value;
    }

    /**
     * Gets the value of the revTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevTypeCode() {
        return revTypeCode;
    }

    /**
     * Sets the value of the revTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevTypeCode(String value) {
        this.revTypeCode = value;
    }

    /**
     * Gets the value of the revTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevTypeName() {
        return revTypeName;
    }

    /**
     * Sets the value of the revTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevTypeName(String value) {
        this.revTypeName = value;
    }

    /**
     * Gets the value of the seqmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqmentCode() {
        return seqmentCode;
    }

    /**
     * Sets the value of the seqmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqmentCode(String value) {
        this.seqmentCode = value;
    }

    /**
     * Gets the value of the seqmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqmentName() {
        return seqmentName;
    }

    /**
     * Sets the value of the seqmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqmentName(String value) {
        this.seqmentName = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the subProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubProductCode() {
        return subProductCode;
    }

    /**
     * Sets the value of the subProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubProductCode(String value) {
        this.subProductCode = value;
    }

    /**
     * Gets the value of the subProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubProductName() {
        return subProductName;
    }

    /**
     * Sets the value of the subProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubProductName(String value) {
        this.subProductName = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

}

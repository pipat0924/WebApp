
package th.net.cat.epis.ws.esblibs.cbos;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CustomerServiceCenterCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerServiceCenterCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Key" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AccountInternalId" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="ServiceCenterType" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ChgDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ChgWho" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ServiceCenterId" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="ScName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ScAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ScAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ScAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerServiceCenterCBO", propOrder = {
    "key",
    "chgDate",
    "chgWho",
    "serviceCenterId",
    "scName",
    "scAddress1",
    "scAddress2",
    "scAddress3"
})
public class CustomerServiceCenterCBO {

    @XmlElement(name = "Key")
    protected CustomerServiceCenterCBO.Key key;
    @XmlElement(name = "ChgDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar chgDate;
    @XmlElement(name = "ChgWho")
    protected String chgWho;
    @XmlElement(name = "ServiceCenterId")
    protected Short serviceCenterId;
    @XmlElement(name = "ScName")
    protected String scName;
    @XmlElement(name = "ScAddress1")
    protected String scAddress1;
    @XmlElement(name = "ScAddress2")
    protected String scAddress2;
    @XmlElement(name = "ScAddress3")
    protected String scAddress3;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerServiceCenterCBO.Key }
     *     
     */
    public CustomerServiceCenterCBO.Key getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerServiceCenterCBO.Key }
     *     
     */
    public void setKey(CustomerServiceCenterCBO.Key value) {
        this.key = value;
    }

    /**
     * Gets the value of the chgDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChgDate() {
        return chgDate;
    }

    /**
     * Sets the value of the chgDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChgDate(XMLGregorianCalendar value) {
        this.chgDate = value;
    }

    /**
     * Gets the value of the chgWho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChgWho() {
        return chgWho;
    }

    /**
     * Sets the value of the chgWho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChgWho(String value) {
        this.chgWho = value;
    }

    /**
     * Gets the value of the serviceCenterId property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getServiceCenterId() {
        return serviceCenterId;
    }

    /**
     * Sets the value of the serviceCenterId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setServiceCenterId(Short value) {
        this.serviceCenterId = value;
    }

    /**
     * Gets the value of the scName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScName() {
        return scName;
    }

    /**
     * Sets the value of the scName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScName(String value) {
        this.scName = value;
    }

    /**
     * Gets the value of the scAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScAddress1() {
        return scAddress1;
    }

    /**
     * Sets the value of the scAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScAddress1(String value) {
        this.scAddress1 = value;
    }

    /**
     * Gets the value of the scAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScAddress2() {
        return scAddress2;
    }

    /**
     * Sets the value of the scAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScAddress2(String value) {
        this.scAddress2 = value;
    }

    /**
     * Gets the value of the scAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScAddress3() {
        return scAddress3;
    }

    /**
     * Sets the value of the scAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScAddress3(String value) {
        this.scAddress3 = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="AccountInternalId" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="ServiceCenterType" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "accountInternalId",
        "serviceCenterType"
    })
    public static class Key {

        @XmlElement(name = "AccountInternalId", required = true)
        protected BigInteger accountInternalId;
        @XmlElement(name = "ServiceCenterType")
        protected short serviceCenterType;

        /**
         * Gets the value of the accountInternalId property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getAccountInternalId() {
            return accountInternalId;
        }

        /**
         * Sets the value of the accountInternalId property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setAccountInternalId(BigInteger value) {
            this.accountInternalId = value;
        }

        /**
         * Gets the value of the serviceCenterType property.
         * 
         */
        public short getServiceCenterType() {
            return serviceCenterType;
        }

        /**
         * Sets the value of the serviceCenterType property.
         * 
         */
        public void setServiceCenterType(short value) {
            this.serviceCenterType = value;
        }

    }

}

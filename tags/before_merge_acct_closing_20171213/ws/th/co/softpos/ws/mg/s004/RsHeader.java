
package th.co.softpos.ws.mg.s004;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rsHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rsHeader"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="funcNm" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rqUID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rsAppId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rsUID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rsDt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="error" type="{http://s004.mg.ws.softpos.co.th/}error"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rsHeader", propOrder = {
    "funcNm",
    "rqUID",
    "rsAppId",
    "rsUID",
    "rsDt",
    "statusCode",
    "error"
})
public class RsHeader {

    @XmlElement(required = true)
    protected String funcNm;
    @XmlElement(required = true)
    protected String rqUID;
    @XmlElement(required = true)
    protected String rsAppId;
    @XmlElement(required = true)
    protected String rsUID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rsDt;
    @XmlElement(required = true)
    protected String statusCode;
    @XmlElement(required = true, nillable = true)
    protected Error error;

    /**
     * Gets the value of the funcNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncNm() {
        return funcNm;
    }

    /**
     * Sets the value of the funcNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncNm(String value) {
        this.funcNm = value;
    }

    /**
     * Gets the value of the rqUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqUID() {
        return rqUID;
    }

    /**
     * Sets the value of the rqUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqUID(String value) {
        this.rqUID = value;
    }

    /**
     * Gets the value of the rsAppId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRsAppId() {
        return rsAppId;
    }

    /**
     * Sets the value of the rsAppId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRsAppId(String value) {
        this.rsAppId = value;
    }

    /**
     * Gets the value of the rsUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRsUID() {
        return rsUID;
    }

    /**
     * Sets the value of the rsUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRsUID(String value) {
        this.rsUID = value;
    }

    /**
     * Gets the value of the rsDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRsDt() {
        return rsDt;
    }

    /**
     * Sets the value of the rsDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRsDt(XMLGregorianCalendar value) {
        this.rsDt = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link Error }
     *     
     */
    public Error getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link Error }
     *     
     */
    public void setError(Error value) {
        this.error = value;
    }

}

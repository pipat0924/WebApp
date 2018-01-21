
package th.net.cat.epis.ws.mnp.libs.header;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RsHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RsHeader"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FuncNm" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RqUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RsAppId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RsUID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RsDt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ErrorAppId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrorDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrorSeverity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinationUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RsHeader", propOrder = {
    "funcNm",
    "rqUID",
    "rsAppId",
    "rsUID",
    "rsDt",
    "statusCode",
    "errorAppId",
    "errorCode",
    "errorDesc",
    "errorSeverity",
    "destinationUsername"
})
public class RsHeader {

    @XmlElement(name = "FuncNm", required = true)
    protected String funcNm;
    @XmlElement(name = "RqUID")
    protected String rqUID;
    @XmlElement(name = "RsAppId", required = true)
    protected String rsAppId;
    @XmlElement(name = "RsUID", required = true)
    protected String rsUID;
    @XmlElement(name = "RsDt", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rsDt;
    @XmlElement(name = "StatusCode", required = true)
    protected String statusCode;
    @XmlElement(name = "ErrorAppId")
    protected String errorAppId;
    @XmlElement(name = "ErrorCode")
    protected String errorCode;
    @XmlElement(name = "ErrorDesc")
    protected String errorDesc;
    @XmlElement(name = "ErrorSeverity")
    protected String errorSeverity;
    @XmlElement(name = "DestinationUsername")
    protected String destinationUsername;

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
     * Gets the value of the errorAppId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorAppId() {
        return errorAppId;
    }

    /**
     * Sets the value of the errorAppId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorAppId(String value) {
        this.errorAppId = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * Sets the value of the errorDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDesc(String value) {
        this.errorDesc = value;
    }

    /**
     * Gets the value of the errorSeverity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorSeverity() {
        return errorSeverity;
    }

    /**
     * Sets the value of the errorSeverity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorSeverity(String value) {
        this.errorSeverity = value;
    }

    /**
     * Gets the value of the destinationUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationUsername() {
        return destinationUsername;
    }

    /**
     * Sets the value of the destinationUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationUsername(String value) {
        this.destinationUsername = value;
    }

}

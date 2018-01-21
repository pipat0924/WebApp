
package th.co.softpos.ws.mg.s001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rqHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rqHeader"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="funcNm" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rqUID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rqDt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="rqAppId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rqHeader", propOrder = {
    "funcNm",
    "rqUID",
    "rqDt",
    "rqAppId",
    "userId",
    "terminalId"
})
public class RqHeader {

    @XmlElement(required = true)
    protected String funcNm;
    @XmlElement(required = true)
    protected String rqUID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rqDt;
    @XmlElement(required = true)
    protected String rqAppId;
    @XmlElement(required = true)
    protected String userId;
    @XmlElement(required = true)
    protected String terminalId;

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
     * Gets the value of the rqDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRqDt() {
        return rqDt;
    }

    /**
     * Sets the value of the rqDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRqDt(XMLGregorianCalendar value) {
        this.rqDt = value;
    }

    /**
     * Gets the value of the rqAppId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqAppId() {
        return rqAppId;
    }

    /**
     * Sets the value of the rqAppId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqAppId(String value) {
        this.rqAppId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the terminalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Sets the value of the terminalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

}

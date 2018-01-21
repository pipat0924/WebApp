
package th.co.softpos.ws.mg.s001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="request"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rqHeader" type="{http://s001.mg.ws.softpos.co.th/}rqHeader"/&gt;
 *         &lt;element name="rqBody" type="{http://s001.mg.ws.softpos.co.th/}rqBody"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "request", propOrder = {
    "rqHeader",
    "rqBody"
})
public class Request {

    @XmlElement(required = true)
    protected RqHeader rqHeader;
    @XmlElement(required = true)
    protected RqBody rqBody;

    /**
     * Gets the value of the rqHeader property.
     * 
     * @return
     *     possible object is
     *     {@link RqHeader }
     *     
     */
    public RqHeader getRqHeader() {
        return rqHeader;
    }

    /**
     * Sets the value of the rqHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link RqHeader }
     *     
     */
    public void setRqHeader(RqHeader value) {
        this.rqHeader = value;
    }

    /**
     * Gets the value of the rqBody property.
     * 
     * @return
     *     possible object is
     *     {@link RqBody }
     *     
     */
    public RqBody getRqBody() {
        return rqBody;
    }

    /**
     * Sets the value of the rqBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link RqBody }
     *     
     */
    public void setRqBody(RqBody value) {
        this.rqBody = value;
    }

}


package th.co.softpos.ws.mg.s001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rsHeader" type="{http://s001.mg.ws.softpos.co.th/}rsHeader"/&gt;
 *         &lt;element name="rsBody" type="{http://s001.mg.ws.softpos.co.th/}rsBody"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
    "rsHeader",
    "rsBody"
})
public class Response {

    @XmlElement(required = true)
    protected RsHeader rsHeader;
    @XmlElement(required = true)
    protected RsBody rsBody;

    /**
     * Gets the value of the rsHeader property.
     * 
     * @return
     *     possible object is
     *     {@link RsHeader }
     *     
     */
    public RsHeader getRsHeader() {
        return rsHeader;
    }

    /**
     * Sets the value of the rsHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link RsHeader }
     *     
     */
    public void setRsHeader(RsHeader value) {
        this.rsHeader = value;
    }

    /**
     * Gets the value of the rsBody property.
     * 
     * @return
     *     possible object is
     *     {@link RsBody }
     *     
     */
    public RsBody getRsBody() {
        return rsBody;
    }

    /**
     * Sets the value of the rsBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link RsBody }
     *     
     */
    public void setRsBody(RsBody value) {
        this.rsBody = value;
    }

}
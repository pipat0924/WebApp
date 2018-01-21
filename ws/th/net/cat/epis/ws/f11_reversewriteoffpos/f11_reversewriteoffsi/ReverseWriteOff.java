
package th.net.cat.epis.ws.f11_reversewriteoffpos.f11_reversewriteoffsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffRequest;


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
 *         &lt;element name="reverseWriteOffRequest" type="{http://F11_ReverseWriteOffPOS}ReverseWriteOffRequest"/&gt;
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
    "reverseWriteOffRequest"
})
@XmlRootElement(name = "reverseWriteOff")
public class ReverseWriteOff {

    @XmlElement(required = true, nillable = true)
    protected ReverseWriteOffRequest reverseWriteOffRequest;

    /**
     * Gets the value of the reverseWriteOffRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ReverseWriteOffRequest }
     *     
     */
    public ReverseWriteOffRequest getReverseWriteOffRequest() {
        return reverseWriteOffRequest;
    }

    /**
     * Sets the value of the reverseWriteOffRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReverseWriteOffRequest }
     *     
     */
    public void setReverseWriteOffRequest(ReverseWriteOffRequest value) {
        this.reverseWriteOffRequest = value;
    }

}

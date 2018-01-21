
package th.net.cat.epis.ws.f11_reversewriteoffpos.f11_reversewriteoffsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="reverseWriteOffResponse" type="{http://F11_ReverseWriteOffPOS}ReverseWriteOffResponse"/&gt;
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
    "reverseWriteOffResponse"
})
@XmlRootElement(name = "reverseWriteOffResponse")
public class ReverseWriteOffResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse reverseWriteOffResponse;

    /**
     * Gets the value of the reverseWriteOffResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse }
     *     
     */
    public th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse getReverseWriteOffResponse() {
        return reverseWriteOffResponse;
    }

    /**
     * Sets the value of the reverseWriteOffResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse }
     *     
     */
    public void setReverseWriteOffResponse(th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse value) {
        this.reverseWriteOffResponse = value;
    }

}

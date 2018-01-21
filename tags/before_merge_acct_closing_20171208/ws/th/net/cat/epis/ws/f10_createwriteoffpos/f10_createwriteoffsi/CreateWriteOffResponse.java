
package th.net.cat.epis.ws.f10_createwriteoffpos.f10_createwriteoffsi;

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
 *         &lt;element name="createWriteOffResponse" type="{http://F10_CreateWriteOffPOS}CreateWriteOffResponse"/&gt;
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
    "createWriteOffResponse"
})
@XmlRootElement(name = "createWriteOffResponse")
public class CreateWriteOffResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse createWriteOffResponse;

    /**
     * Gets the value of the createWriteOffResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse }
     *     
     */
    public th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse getCreateWriteOffResponse() {
        return createWriteOffResponse;
    }

    /**
     * Sets the value of the createWriteOffResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse }
     *     
     */
    public void setCreateWriteOffResponse(th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse value) {
        this.createWriteOffResponse = value;
    }

}

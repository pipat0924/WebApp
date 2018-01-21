
package th.net.cat.epis.ws.f10_createwriteoffpos.f10_createwriteoffsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffRequest;


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
 *         &lt;element name="createWriteOffRequest" type="{http://F10_CreateWriteOffPOS}CreateWriteOffRequest"/&gt;
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
    "createWriteOffRequest"
})
@XmlRootElement(name = "createWriteOff")
public class CreateWriteOff {

    @XmlElement(required = true, nillable = true)
    protected CreateWriteOffRequest createWriteOffRequest;

    /**
     * Gets the value of the createWriteOffRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CreateWriteOffRequest }
     *     
     */
    public CreateWriteOffRequest getCreateWriteOffRequest() {
        return createWriteOffRequest;
    }

    /**
     * Sets the value of the createWriteOffRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateWriteOffRequest }
     *     
     */
    public void setCreateWriteOffRequest(CreateWriteOffRequest value) {
        this.createWriteOffRequest = value;
    }

}

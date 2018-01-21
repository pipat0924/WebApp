
package th.net.cat.epis.ws.f18_createrefund.f18_createrefundsi;

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
 *         &lt;element name="createRefundResponse" type="{http://F18_CreateRefund}CreateRefundResponse"/&gt;
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
    "createRefundResponse"
})
@XmlRootElement(name = "createRefundResponse")
public class CreateRefundResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f18_createrefund.CreateRefundResponse createRefundResponse;

    /**
     * Gets the value of the createRefundResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f18_createrefund.CreateRefundResponse }
     *     
     */
    public th.net.cat.epis.ws.f18_createrefund.CreateRefundResponse getCreateRefundResponse() {
        return createRefundResponse;
    }

    /**
     * Sets the value of the createRefundResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f18_createrefund.CreateRefundResponse }
     *     
     */
    public void setCreateRefundResponse(th.net.cat.epis.ws.f18_createrefund.CreateRefundResponse value) {
        this.createRefundResponse = value;
    }

}

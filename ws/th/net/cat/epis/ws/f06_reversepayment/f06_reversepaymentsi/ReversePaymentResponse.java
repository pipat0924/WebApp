
package th.net.cat.epis.ws.f06_reversepayment.f06_reversepaymentsi;

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
 *         &lt;element name="reversePaymentResponse" type="{http://F06_ReversePayment}ReversePaymentResponse"/&gt;
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
    "reversePaymentResponse"
})
@XmlRootElement(name = "reversePaymentResponse")
public class ReversePaymentResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse reversePaymentResponse;

    /**
     * Gets the value of the reversePaymentResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse }
     *     
     */
    public th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse getReversePaymentResponse() {
        return reversePaymentResponse;
    }

    /**
     * Sets the value of the reversePaymentResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse }
     *     
     */
    public void setReversePaymentResponse(th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse value) {
        this.reversePaymentResponse = value;
    }

}

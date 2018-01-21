
package th.net.cat.epis.ws.f06_reversepayment.f06_reversepaymentsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f06_reversepayment.ReversePaymentRequest;


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
 *         &lt;element name="reversePaymentRequest" type="{http://F06_ReversePayment}ReversePaymentRequest"/&gt;
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
    "reversePaymentRequest"
})
@XmlRootElement(name = "reversePayment")
public class ReversePayment {

    @XmlElement(required = true, nillable = true)
    protected ReversePaymentRequest reversePaymentRequest;

    /**
     * Gets the value of the reversePaymentRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ReversePaymentRequest }
     *     
     */
    public ReversePaymentRequest getReversePaymentRequest() {
        return reversePaymentRequest;
    }

    /**
     * Sets the value of the reversePaymentRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReversePaymentRequest }
     *     
     */
    public void setReversePaymentRequest(ReversePaymentRequest value) {
        this.reversePaymentRequest = value;
    }

}

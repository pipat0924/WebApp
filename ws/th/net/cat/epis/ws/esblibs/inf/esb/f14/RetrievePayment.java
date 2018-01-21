
package th.net.cat.epis.ws.esblibs.inf.esb.f14;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentRequest;


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
 *         &lt;element name="retrievePaymentRequest" type="{http://ESBLibs/RequestResponseCBO/F14}RetrievePaymentRequest"/&gt;
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
    "retrievePaymentRequest"
})
@XmlRootElement(name = "retrievePayment")
public class RetrievePayment {

    @XmlElement(required = true, nillable = true)
    protected RetrievePaymentRequest retrievePaymentRequest;

    /**
     * Gets the value of the retrievePaymentRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrievePaymentRequest }
     *     
     */
    public RetrievePaymentRequest getRetrievePaymentRequest() {
        return retrievePaymentRequest;
    }

    /**
     * Sets the value of the retrievePaymentRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrievePaymentRequest }
     *     
     */
    public void setRetrievePaymentRequest(RetrievePaymentRequest value) {
        this.retrievePaymentRequest = value;
    }

}

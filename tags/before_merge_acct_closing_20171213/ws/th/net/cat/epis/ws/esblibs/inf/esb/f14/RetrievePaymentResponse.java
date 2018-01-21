
package th.net.cat.epis.ws.esblibs.inf.esb.f14;

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
 *         &lt;element name="retrievePaymentResponse" type="{http://ESBLibs/RequestResponseCBO/F14}RetrievePaymentResponse"/&gt;
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
    "retrievePaymentResponse"
})
@XmlRootElement(name = "retrievePaymentResponse")
public class RetrievePaymentResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentResponse retrievePaymentResponse;

    /**
     * Gets the value of the retrievePaymentResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentResponse }
     *     
     */
    public th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentResponse getRetrievePaymentResponse() {
        return retrievePaymentResponse;
    }

    /**
     * Sets the value of the retrievePaymentResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentResponse }
     *     
     */
    public void setRetrievePaymentResponse(th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentResponse value) {
        this.retrievePaymentResponse = value;
    }

}

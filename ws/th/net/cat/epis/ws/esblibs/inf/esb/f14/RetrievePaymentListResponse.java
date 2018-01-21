
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
 *         &lt;element name="retrievePaymentListResponse" type="{http://ESBLibs/RequestResponseCBO/F14}RetrievePaymentListResponse"/&gt;
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
    "retrievePaymentListResponse"
})
@XmlRootElement(name = "retrievePaymentListResponse")
public class RetrievePaymentListResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListResponse retrievePaymentListResponse;

    /**
     * Gets the value of the retrievePaymentListResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListResponse }
     *     
     */
    public th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListResponse getRetrievePaymentListResponse() {
        return retrievePaymentListResponse;
    }

    /**
     * Sets the value of the retrievePaymentListResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListResponse }
     *     
     */
    public void setRetrievePaymentListResponse(th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListResponse value) {
        this.retrievePaymentListResponse = value;
    }

}

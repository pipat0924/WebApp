
package th.net.cat.epis.ws.esblibs.inf.esb.f4;

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
 *         &lt;element name="createPaymentResponse" type="{http://ESBLibs/RequestResponseCBO/F4}CreatePaymentResponse"/&gt;
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
    "createPaymentResponse"
})
@XmlRootElement(name = "createPaymentResponse")
public class CreatePaymentResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse createPaymentResponse;

    /**
     * Gets the value of the createPaymentResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse }
     *     
     */
    public th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse getCreatePaymentResponse() {
        return createPaymentResponse;
    }

    /**
     * Sets the value of the createPaymentResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse }
     *     
     */
    public void setCreatePaymentResponse(th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse value) {
        this.createPaymentResponse = value;
    }

}

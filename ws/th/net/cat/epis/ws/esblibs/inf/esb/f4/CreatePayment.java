
package th.net.cat.epis.ws.esblibs.inf.esb.f4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentRequest;


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
 *         &lt;element name="createPaymentRequest" type="{http://ESBLibs/RequestResponseCBO/F4}CreatePaymentRequest"/&gt;
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
    "createPaymentRequest"
})
@XmlRootElement(name = "createPayment")
public class CreatePayment {

    @XmlElement(required = true, nillable = true)
    protected CreatePaymentRequest createPaymentRequest;

    /**
     * Gets the value of the createPaymentRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CreatePaymentRequest }
     *     
     */
    public CreatePaymentRequest getCreatePaymentRequest() {
        return createPaymentRequest;
    }

    /**
     * Sets the value of the createPaymentRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatePaymentRequest }
     *     
     */
    public void setCreatePaymentRequest(CreatePaymentRequest value) {
        this.createPaymentRequest = value;
    }

}


package th.net.cat.epis.ws.esblibs.inf.esb.f14;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListRequest;


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
 *         &lt;element name="retrievePaymentListRequest" type="{http://ESBLibs/RequestResponseCBO/F14}RetrievePaymentListRequest"/&gt;
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
    "retrievePaymentListRequest"
})
@XmlRootElement(name = "retrievePaymentList")
public class RetrievePaymentList {

    @XmlElement(required = true, nillable = true)
    protected RetrievePaymentListRequest retrievePaymentListRequest;

    /**
     * Gets the value of the retrievePaymentListRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrievePaymentListRequest }
     *     
     */
    public RetrievePaymentListRequest getRetrievePaymentListRequest() {
        return retrievePaymentListRequest;
    }

    /**
     * Sets the value of the retrievePaymentListRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrievePaymentListRequest }
     *     
     */
    public void setRetrievePaymentListRequest(RetrievePaymentListRequest value) {
        this.retrievePaymentListRequest = value;
    }

}

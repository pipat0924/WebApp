
package th.net.cat.epis.ws.f13_retrievepaymenthistory.f13_retrievepaymenthistorysi;

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
 *         &lt;element name="retrievePaymentHistoryResponse" type="{http://F13_RetrievePaymentHistory}RetrievePaymentHistoryResponse"/&gt;
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
    "retrievePaymentHistoryResponse"
})
@XmlRootElement(name = "retrievePaymentHistoryResponse")
public class RetrievePaymentHistoryResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse retrievePaymentHistoryResponse;

    /**
     * Gets the value of the retrievePaymentHistoryResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse }
     *     
     */
    public th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse getRetrievePaymentHistoryResponse() {
        return retrievePaymentHistoryResponse;
    }

    /**
     * Sets the value of the retrievePaymentHistoryResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse }
     *     
     */
    public void setRetrievePaymentHistoryResponse(th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse value) {
        this.retrievePaymentHistoryResponse = value;
    }

}


package th.net.cat.epis.ws.f13_retrievepaymenthistory.f13_retrievepaymenthistorysi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryRequest;


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
 *         &lt;element name="retrievePaymentHistoryRequest" type="{http://F13_RetrievePaymentHistory}RetrievePaymentHistoryRequest"/&gt;
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
    "retrievePaymentHistoryRequest"
})
@XmlRootElement(name = "retrievePaymentHistory")
public class RetrievePaymentHistory {

    @XmlElement(required = true, nillable = true)
    protected RetrievePaymentHistoryRequest retrievePaymentHistoryRequest;

    /**
     * Gets the value of the retrievePaymentHistoryRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrievePaymentHistoryRequest }
     *     
     */
    public RetrievePaymentHistoryRequest getRetrievePaymentHistoryRequest() {
        return retrievePaymentHistoryRequest;
    }

    /**
     * Sets the value of the retrievePaymentHistoryRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrievePaymentHistoryRequest }
     *     
     */
    public void setRetrievePaymentHistoryRequest(RetrievePaymentHistoryRequest value) {
        this.retrievePaymentHistoryRequest = value;
    }

}

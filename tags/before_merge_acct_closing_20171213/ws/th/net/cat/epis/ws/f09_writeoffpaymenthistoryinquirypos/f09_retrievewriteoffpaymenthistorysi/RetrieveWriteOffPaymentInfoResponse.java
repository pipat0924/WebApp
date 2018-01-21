
package th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.f09_retrievewriteoffpaymenthistorysi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.RetrieveWriteOffPaymentResponse;


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
 *         &lt;element name="retrieveWriteOffPaymentResponse" type="{http://F09_WriteOffPaymentHistoryInquiryPOS}RetrieveWriteOffPaymentResponse"/&gt;
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
    "retrieveWriteOffPaymentResponse"
})
@XmlRootElement(name = "retrieveWriteOffPaymentInfoResponse")
public class RetrieveWriteOffPaymentInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected RetrieveWriteOffPaymentResponse retrieveWriteOffPaymentResponse;

    /**
     * Gets the value of the retrieveWriteOffPaymentResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveWriteOffPaymentResponse }
     *     
     */
    public RetrieveWriteOffPaymentResponse getRetrieveWriteOffPaymentResponse() {
        return retrieveWriteOffPaymentResponse;
    }

    /**
     * Sets the value of the retrieveWriteOffPaymentResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveWriteOffPaymentResponse }
     *     
     */
    public void setRetrieveWriteOffPaymentResponse(RetrieveWriteOffPaymentResponse value) {
        this.retrieveWriteOffPaymentResponse = value;
    }

}


package th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.f09_retrievewriteoffpaymenthistorysi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.RetrieveWriteOffPaymentRequest;


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
 *         &lt;element name="retrieveWriteOffPaymentRequest" type="{http://F09_WriteOffPaymentHistoryInquiryPOS}RetrieveWriteOffPaymentRequest"/&gt;
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
    "retrieveWriteOffPaymentRequest"
})
@XmlRootElement(name = "retrieveWriteOffPaymentInfo")
public class RetrieveWriteOffPaymentInfo {

    @XmlElement(required = true, nillable = true)
    protected RetrieveWriteOffPaymentRequest retrieveWriteOffPaymentRequest;

    /**
     * Gets the value of the retrieveWriteOffPaymentRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveWriteOffPaymentRequest }
     *     
     */
    public RetrieveWriteOffPaymentRequest getRetrieveWriteOffPaymentRequest() {
        return retrieveWriteOffPaymentRequest;
    }

    /**
     * Sets the value of the retrieveWriteOffPaymentRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveWriteOffPaymentRequest }
     *     
     */
    public void setRetrieveWriteOffPaymentRequest(RetrieveWriteOffPaymentRequest value) {
        this.retrieveWriteOffPaymentRequest = value;
    }

}

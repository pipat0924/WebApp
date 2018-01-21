
package th.net.cat.epis.ws.f08_writeoffinquirypos.f08_writeoffinquirysi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f08_writeoffinquirypos.RetrieveWriteOffInvoiceRequest;


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
 *         &lt;element name="retrieveWriteOffInvoiceRequest" type="{http://F08_WriteOffInquiryPOS}RetrieveWriteOffInvoiceRequest"/&gt;
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
    "retrieveWriteOffInvoiceRequest"
})
@XmlRootElement(name = "retrieveWriteOffInvoice")
public class RetrieveWriteOffInvoice {

    @XmlElement(required = true, nillable = true)
    protected RetrieveWriteOffInvoiceRequest retrieveWriteOffInvoiceRequest;

    /**
     * Gets the value of the retrieveWriteOffInvoiceRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveWriteOffInvoiceRequest }
     *     
     */
    public RetrieveWriteOffInvoiceRequest getRetrieveWriteOffInvoiceRequest() {
        return retrieveWriteOffInvoiceRequest;
    }

    /**
     * Sets the value of the retrieveWriteOffInvoiceRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveWriteOffInvoiceRequest }
     *     
     */
    public void setRetrieveWriteOffInvoiceRequest(RetrieveWriteOffInvoiceRequest value) {
        this.retrieveWriteOffInvoiceRequest = value;
    }

}

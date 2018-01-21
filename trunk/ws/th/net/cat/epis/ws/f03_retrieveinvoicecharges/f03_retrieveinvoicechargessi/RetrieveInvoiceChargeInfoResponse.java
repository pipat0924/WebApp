
package th.net.cat.epis.ws.f03_retrieveinvoicecharges.f03_retrieveinvoicechargessi;

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
 *         &lt;element name="retrieveInvoiceChargeInfoResponse" type="{http://F03_RetrieveInvoiceCharges}RetrieveInvoiceChargeInfoResponse"/&gt;
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
    "retrieveInvoiceChargeInfoResponse"
})
@XmlRootElement(name = "retrieveInvoiceChargeInfoResponse")
public class RetrieveInvoiceChargeInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse retrieveInvoiceChargeInfoResponse;

    /**
     * Gets the value of the retrieveInvoiceChargeInfoResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse }
     *     
     */
    public th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse getRetrieveInvoiceChargeInfoResponse() {
        return retrieveInvoiceChargeInfoResponse;
    }

    /**
     * Sets the value of the retrieveInvoiceChargeInfoResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse }
     *     
     */
    public void setRetrieveInvoiceChargeInfoResponse(th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse value) {
        this.retrieveInvoiceChargeInfoResponse = value;
    }

}

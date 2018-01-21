
package th.net.cat.epis.ws.f03_retrieveinvoicecharges.f03_retrieveinvoicechargessi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoRequest;


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
 *         &lt;element name="retrieveInvoiceChargeInfoRequest" type="{http://F03_RetrieveInvoiceCharges}RetrieveInvoiceChargeInfoRequest"/&gt;
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
    "retrieveInvoiceChargeInfoRequest"
})
@XmlRootElement(name = "retrieveInvoiceChargeInfo")
public class RetrieveInvoiceChargeInfo {

    @XmlElement(required = true, nillable = true)
    protected RetrieveInvoiceChargeInfoRequest retrieveInvoiceChargeInfoRequest;

    /**
     * Gets the value of the retrieveInvoiceChargeInfoRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveInvoiceChargeInfoRequest }
     *     
     */
    public RetrieveInvoiceChargeInfoRequest getRetrieveInvoiceChargeInfoRequest() {
        return retrieveInvoiceChargeInfoRequest;
    }

    /**
     * Sets the value of the retrieveInvoiceChargeInfoRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveInvoiceChargeInfoRequest }
     *     
     */
    public void setRetrieveInvoiceChargeInfoRequest(RetrieveInvoiceChargeInfoRequest value) {
        this.retrieveInvoiceChargeInfoRequest = value;
    }

}

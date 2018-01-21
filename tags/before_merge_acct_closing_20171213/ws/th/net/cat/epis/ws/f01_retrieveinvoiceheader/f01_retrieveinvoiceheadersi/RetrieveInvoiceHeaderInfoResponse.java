
package th.net.cat.epis.ws.f01_retrieveinvoiceheader.f01_retrieveinvoiceheadersi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderResponse;


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
 *         &lt;element name="retrieveInvoiceHeaderResponse" type="{http://F01_RetrieveInvoiceHeader}RetrieveInvoiceHeaderResponse"/&gt;
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
    "retrieveInvoiceHeaderResponse"
})
@XmlRootElement(name = "retrieveInvoiceHeaderInfoResponse")
public class RetrieveInvoiceHeaderInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected RetrieveInvoiceHeaderResponse retrieveInvoiceHeaderResponse;

    /**
     * Gets the value of the retrieveInvoiceHeaderResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveInvoiceHeaderResponse }
     *     
     */
    public RetrieveInvoiceHeaderResponse getRetrieveInvoiceHeaderResponse() {
        return retrieveInvoiceHeaderResponse;
    }

    /**
     * Sets the value of the retrieveInvoiceHeaderResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveInvoiceHeaderResponse }
     *     
     */
    public void setRetrieveInvoiceHeaderResponse(RetrieveInvoiceHeaderResponse value) {
        this.retrieveInvoiceHeaderResponse = value;
    }

}


package th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.f02_retrieveinvoiceaccountcodesi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.RetrieveInvoiceAccountCodeResponse;


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
 *         &lt;element name="retrieveInvoiceAccountCodeResponse" type="{http://F02_RetrieveInvoiceAccountCode}RetrieveInvoiceAccountCodeResponse"/&gt;
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
    "retrieveInvoiceAccountCodeResponse"
})
@XmlRootElement(name = "retrieveInvoiceAccountCodeInfoResponse")
public class RetrieveInvoiceAccountCodeInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected RetrieveInvoiceAccountCodeResponse retrieveInvoiceAccountCodeResponse;

    /**
     * Gets the value of the retrieveInvoiceAccountCodeResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveInvoiceAccountCodeResponse }
     *     
     */
    public RetrieveInvoiceAccountCodeResponse getRetrieveInvoiceAccountCodeResponse() {
        return retrieveInvoiceAccountCodeResponse;
    }

    /**
     * Sets the value of the retrieveInvoiceAccountCodeResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveInvoiceAccountCodeResponse }
     *     
     */
    public void setRetrieveInvoiceAccountCodeResponse(RetrieveInvoiceAccountCodeResponse value) {
        this.retrieveInvoiceAccountCodeResponse = value;
    }

}

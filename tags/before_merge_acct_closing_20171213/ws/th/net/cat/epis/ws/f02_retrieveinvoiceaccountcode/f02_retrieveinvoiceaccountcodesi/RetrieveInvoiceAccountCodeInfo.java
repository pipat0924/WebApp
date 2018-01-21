
package th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.f02_retrieveinvoiceaccountcodesi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.RetrieveInvoiceAccountCodeRequest;


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
 *         &lt;element name="retrieveInvoiceAccountCodeRequest" type="{http://F02_RetrieveInvoiceAccountCode}RetrieveInvoiceAccountCodeRequest"/&gt;
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
    "retrieveInvoiceAccountCodeRequest"
})
@XmlRootElement(name = "retrieveInvoiceAccountCodeInfo")
public class RetrieveInvoiceAccountCodeInfo {

    @XmlElement(required = true, nillable = true)
    protected RetrieveInvoiceAccountCodeRequest retrieveInvoiceAccountCodeRequest;

    /**
     * Gets the value of the retrieveInvoiceAccountCodeRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveInvoiceAccountCodeRequest }
     *     
     */
    public RetrieveInvoiceAccountCodeRequest getRetrieveInvoiceAccountCodeRequest() {
        return retrieveInvoiceAccountCodeRequest;
    }

    /**
     * Sets the value of the retrieveInvoiceAccountCodeRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveInvoiceAccountCodeRequest }
     *     
     */
    public void setRetrieveInvoiceAccountCodeRequest(RetrieveInvoiceAccountCodeRequest value) {
        this.retrieveInvoiceAccountCodeRequest = value;
    }

}

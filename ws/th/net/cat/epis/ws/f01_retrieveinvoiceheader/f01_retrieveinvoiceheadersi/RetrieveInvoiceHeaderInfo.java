
package th.net.cat.epis.ws.f01_retrieveinvoiceheader.f01_retrieveinvoiceheadersi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderRequest;


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
 *         &lt;element name="retrieveInvoiceHeaderRequest" type="{http://F01_RetrieveInvoiceHeader}RetrieveInvoiceHeaderRequest"/&gt;
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
    "retrieveInvoiceHeaderRequest"
})
@XmlRootElement(name = "retrieveInvoiceHeaderInfo")
public class RetrieveInvoiceHeaderInfo {

    @XmlElement(required = true, nillable = true)
    protected RetrieveInvoiceHeaderRequest retrieveInvoiceHeaderRequest;

    /**
     * Gets the value of the retrieveInvoiceHeaderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveInvoiceHeaderRequest }
     *     
     */
    public RetrieveInvoiceHeaderRequest getRetrieveInvoiceHeaderRequest() {
        return retrieveInvoiceHeaderRequest;
    }

    /**
     * Sets the value of the retrieveInvoiceHeaderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveInvoiceHeaderRequest }
     *     
     */
    public void setRetrieveInvoiceHeaderRequest(RetrieveInvoiceHeaderRequest value) {
        this.retrieveInvoiceHeaderRequest = value;
    }

}

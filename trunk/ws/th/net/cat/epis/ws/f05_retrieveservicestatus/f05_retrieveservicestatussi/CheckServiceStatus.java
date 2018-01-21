
package th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusRequest;


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
 *         &lt;element name="retrieveServiceStatusRequest" type="{http://F05_RetrieveServiceStatus}RetrieveServiceStatusRequest"/&gt;
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
    "retrieveServiceStatusRequest"
})
@XmlRootElement(name = "checkServiceStatus")
public class CheckServiceStatus {

    @XmlElement(required = true, nillable = true)
    protected RetrieveServiceStatusRequest retrieveServiceStatusRequest;

    /**
     * Gets the value of the retrieveServiceStatusRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveServiceStatusRequest }
     *     
     */
    public RetrieveServiceStatusRequest getRetrieveServiceStatusRequest() {
        return retrieveServiceStatusRequest;
    }

    /**
     * Sets the value of the retrieveServiceStatusRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveServiceStatusRequest }
     *     
     */
    public void setRetrieveServiceStatusRequest(RetrieveServiceStatusRequest value) {
        this.retrieveServiceStatusRequest = value;
    }

}

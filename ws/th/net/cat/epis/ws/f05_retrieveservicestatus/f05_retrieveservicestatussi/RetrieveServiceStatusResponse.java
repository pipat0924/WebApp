
package th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi;

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
 *         &lt;element name="retrieveServiceStatusResponse" type="{http://F05_RetrieveServiceStatus}RetrieveServiceStatusResponse"/&gt;
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
    "retrieveServiceStatusResponse"
})
@XmlRootElement(name = "retrieveServiceStatusResponse")
public class RetrieveServiceStatusResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse retrieveServiceStatusResponse;

    /**
     * Gets the value of the retrieveServiceStatusResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse }
     *     
     */
    public th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse getRetrieveServiceStatusResponse() {
        return retrieveServiceStatusResponse;
    }

    /**
     * Sets the value of the retrieveServiceStatusResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse }
     *     
     */
    public void setRetrieveServiceStatusResponse(th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse value) {
        this.retrieveServiceStatusResponse = value;
    }

}

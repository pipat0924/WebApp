
package th.net.cat.epis.ws.f19_retrieveaccount.f19_retrieveaccountsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountResponse;


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
 *         &lt;element name="retrieveAccountResponse" type="{http://F19_RetrieveAccount}RetrieveAccountResponse"/&gt;
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
    "retrieveAccountResponse"
})
@XmlRootElement(name = "retrieveAccountInfoResponse")
public class RetrieveAccountInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected RetrieveAccountResponse retrieveAccountResponse;

    /**
     * Gets the value of the retrieveAccountResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveAccountResponse }
     *     
     */
    public RetrieveAccountResponse getRetrieveAccountResponse() {
        return retrieveAccountResponse;
    }

    /**
     * Sets the value of the retrieveAccountResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveAccountResponse }
     *     
     */
    public void setRetrieveAccountResponse(RetrieveAccountResponse value) {
        this.retrieveAccountResponse = value;
    }

}


package th.net.cat.epis.ws.f19_retrieveaccount.f19_retrieveaccountsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountRequest;


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
 *         &lt;element name="retrieveAccountRequest" type="{http://F19_RetrieveAccount}RetrieveAccountRequest"/&gt;
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
    "retrieveAccountRequest"
})
@XmlRootElement(name = "retrieveAccountInfo")
public class RetrieveAccountInfo {

    @XmlElement(required = true, nillable = true)
    protected RetrieveAccountRequest retrieveAccountRequest;

    /**
     * Gets the value of the retrieveAccountRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveAccountRequest }
     *     
     */
    public RetrieveAccountRequest getRetrieveAccountRequest() {
        return retrieveAccountRequest;
    }

    /**
     * Sets the value of the retrieveAccountRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveAccountRequest }
     *     
     */
    public void setRetrieveAccountRequest(RetrieveAccountRequest value) {
        this.retrieveAccountRequest = value;
    }

}

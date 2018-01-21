
package th.net.cat.epis.ws.f20_retrievesubscrbyinv.f20_retrievesubscrbyinvsi;

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
 *         &lt;element name="retrieveSubscrResponse" type="{http://F20_RetrieveSubscrByInv}RetrieveSubscrResponse"/&gt;
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
    "retrieveSubscrResponse"
})
@XmlRootElement(name = "retrieveSubscrResponse")
public class RetrieveSubscrResponse {

    @XmlElement(required = true, nillable = true)
    protected th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrResponse retrieveSubscrResponse;

    /**
     * Gets the value of the retrieveSubscrResponse property.
     * 
     * @return
     *     possible object is
     *     {@link th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrResponse }
     *     
     */
    public th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrResponse getRetrieveSubscrResponse() {
        return retrieveSubscrResponse;
    }

    /**
     * Sets the value of the retrieveSubscrResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrResponse }
     *     
     */
    public void setRetrieveSubscrResponse(th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrResponse value) {
        this.retrieveSubscrResponse = value;
    }

}

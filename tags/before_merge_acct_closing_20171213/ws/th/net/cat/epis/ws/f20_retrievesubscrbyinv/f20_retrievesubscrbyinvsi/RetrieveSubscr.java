
package th.net.cat.epis.ws.f20_retrievesubscrbyinv.f20_retrievesubscrbyinvsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrRequest;


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
 *         &lt;element name="retrieveSubscrRequest" type="{http://F20_RetrieveSubscrByInv}RetrieveSubscrRequest"/&gt;
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
    "retrieveSubscrRequest"
})
@XmlRootElement(name = "retrieveSubscr")
public class RetrieveSubscr {

    @XmlElement(required = true, nillable = true)
    protected RetrieveSubscrRequest retrieveSubscrRequest;

    /**
     * Gets the value of the retrieveSubscrRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveSubscrRequest }
     *     
     */
    public RetrieveSubscrRequest getRetrieveSubscrRequest() {
        return retrieveSubscrRequest;
    }

    /**
     * Sets the value of the retrieveSubscrRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveSubscrRequest }
     *     
     */
    public void setRetrieveSubscrRequest(RetrieveSubscrRequest value) {
        this.retrieveSubscrRequest = value;
    }

}

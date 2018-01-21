
package th.net.cat.epis.ws.f18_createrefund.f18_createrefundsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.f18_createrefund.CreateRefundRequest;


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
 *         &lt;element name="createRefundRequest" type="{http://F18_CreateRefund}CreateRefundRequest"/&gt;
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
    "createRefundRequest"
})
@XmlRootElement(name = "createRefund")
public class CreateRefund {

    @XmlElement(required = true, nillable = true)
    protected CreateRefundRequest createRefundRequest;

    /**
     * Gets the value of the createRefundRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CreateRefundRequest }
     *     
     */
    public CreateRefundRequest getCreateRefundRequest() {
        return createRefundRequest;
    }

    /**
     * Sets the value of the createRefundRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateRefundRequest }
     *     
     */
    public void setCreateRefundRequest(CreateRefundRequest value) {
        this.createRefundRequest = value;
    }

}

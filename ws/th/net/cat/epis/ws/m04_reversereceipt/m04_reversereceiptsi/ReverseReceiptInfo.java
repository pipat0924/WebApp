
package th.net.cat.epis.ws.m04_reversereceipt.m04_reversereceiptsi;

import th.net.cat.epis.ws.m04_reversereceipt.ReverseReceiptRequest;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="reverseReceiptRequest" type="{http://M04_ReverseReceipt}ReverseReceiptRequest"/&gt;
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
    "reverseReceiptRequest"
})
@XmlRootElement(name = "reverseReceiptInfo")
public class ReverseReceiptInfo {

    @XmlElement(required = true, nillable = true)
    protected ReverseReceiptRequest reverseReceiptRequest;

    /**
     * Gets the value of the reverseReceiptRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ReverseReceiptRequest }
     *     
     */
    public ReverseReceiptRequest getReverseReceiptRequest() {
        return reverseReceiptRequest;
    }

    /**
     * Sets the value of the reverseReceiptRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReverseReceiptRequest }
     *     
     */
    public void setReverseReceiptRequest(ReverseReceiptRequest value) {
        this.reverseReceiptRequest = value;
    }

}

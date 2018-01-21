
package th.net.cat.epis.ws.m04_reversereceipt.m04_reversereceiptsi;

import th.net.cat.epis.ws.m04_reversereceipt.ReverseReceiptResponse;

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
 *         &lt;element name="reverseReceiptResponse" type="{http://M04_ReverseReceipt}ReverseReceiptResponse"/&gt;
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
    "reverseReceiptResponse"
})
@XmlRootElement(name = "reverseReceiptInfoResponse")
public class ReverseReceiptInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected ReverseReceiptResponse reverseReceiptResponse;

    /**
     * Gets the value of the reverseReceiptResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ReverseReceiptResponse }
     *     
     */
    public ReverseReceiptResponse getReverseReceiptResponse() {
        return reverseReceiptResponse;
    }

    /**
     * Sets the value of the reverseReceiptResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReverseReceiptResponse }
     *     
     */
    public void setReverseReceiptResponse(ReverseReceiptResponse value) {
        this.reverseReceiptResponse = value;
    }

}

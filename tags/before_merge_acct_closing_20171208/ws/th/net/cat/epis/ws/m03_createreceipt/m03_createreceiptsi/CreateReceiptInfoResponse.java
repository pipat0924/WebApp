
package th.net.cat.epis.ws.m03_createreceipt.m03_createreceiptsi;

import th.net.cat.epis.ws.m03_createreceipt.CreateReceiptResponse;

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
 *         &lt;element name="createReceiptResponse" type="{http://M03_CreateReceipt}CreateReceiptResponse"/&gt;
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
    "createReceiptResponse"
})
@XmlRootElement(name = "createReceiptInfoResponse")
public class CreateReceiptInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected CreateReceiptResponse createReceiptResponse;

    /**
     * Gets the value of the createReceiptResponse property.
     * 
     * @return
     *     possible object is
     *     {@link CreateReceiptResponse }
     *     
     */
    public CreateReceiptResponse getCreateReceiptResponse() {
        return createReceiptResponse;
    }

    /**
     * Sets the value of the createReceiptResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateReceiptResponse }
     *     
     */
    public void setCreateReceiptResponse(CreateReceiptResponse value) {
        this.createReceiptResponse = value;
    }

}

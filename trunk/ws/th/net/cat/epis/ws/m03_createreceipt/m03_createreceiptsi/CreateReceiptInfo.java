
package th.net.cat.epis.ws.m03_createreceipt.m03_createreceiptsi;

import th.net.cat.epis.ws.m03_createreceipt.CreateReceiptRequest;

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
 *         &lt;element name="createReceiptRequest" type="{http://M03_CreateReceipt}CreateReceiptRequest"/&gt;
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
    "createReceiptRequest"
})
@XmlRootElement(name = "createReceiptInfo")
public class CreateReceiptInfo {

    @XmlElement(required = true, nillable = true)
    protected CreateReceiptRequest createReceiptRequest;

    /**
     * Gets the value of the createReceiptRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CreateReceiptRequest }
     *     
     */
    public CreateReceiptRequest getCreateReceiptRequest() {
        return createReceiptRequest;
    }

    /**
     * Sets the value of the createReceiptRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateReceiptRequest }
     *     
     */
    public void setCreateReceiptRequest(CreateReceiptRequest value) {
        this.createReceiptRequest = value;
    }

}

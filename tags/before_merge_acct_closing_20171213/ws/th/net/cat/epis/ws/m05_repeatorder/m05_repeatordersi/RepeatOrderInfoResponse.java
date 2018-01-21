
package th.net.cat.epis.ws.m05_repeatorder.m05_repeatordersi;

import th.net.cat.epis.ws.m05_repeatorder.RepeatOrderResponse;

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
 *         &lt;element name="repeatOrderResponse" type="{http://M05_RepeatOrder}RepeatOrderResponse"/&gt;
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
    "repeatOrderResponse"
})
@XmlRootElement(name = "repeatOrderInfoResponse")
public class RepeatOrderInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected RepeatOrderResponse repeatOrderResponse;

    /**
     * Gets the value of the repeatOrderResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RepeatOrderResponse }
     *     
     */
    public RepeatOrderResponse getRepeatOrderResponse() {
        return repeatOrderResponse;
    }

    /**
     * Sets the value of the repeatOrderResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepeatOrderResponse }
     *     
     */
    public void setRepeatOrderResponse(RepeatOrderResponse value) {
        this.repeatOrderResponse = value;
    }

}

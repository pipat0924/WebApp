
package th.net.cat.epis.ws.m05_repeatorder.m05_repeatordersi;

import th.net.cat.epis.ws.m05_repeatorder.RepeatOrderRequest;

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
 *         &lt;element name="repeatOrderRequest" type="{http://M05_RepeatOrder}RepeatOrderRequest"/&gt;
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
    "repeatOrderRequest"
})
@XmlRootElement(name = "repeatOrderInfo")
public class RepeatOrderInfo {

    @XmlElement(required = true, nillable = true)
    protected RepeatOrderRequest repeatOrderRequest;

    /**
     * Gets the value of the repeatOrderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RepeatOrderRequest }
     *     
     */
    public RepeatOrderRequest getRepeatOrderRequest() {
        return repeatOrderRequest;
    }

    /**
     * Sets the value of the repeatOrderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepeatOrderRequest }
     *     
     */
    public void setRepeatOrderRequest(RepeatOrderRequest value) {
        this.repeatOrderRequest = value;
    }

}

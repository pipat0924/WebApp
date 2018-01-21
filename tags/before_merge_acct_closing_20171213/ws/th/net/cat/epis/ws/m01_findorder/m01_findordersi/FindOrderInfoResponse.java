
package th.net.cat.epis.ws.m01_findorder.m01_findordersi;

import th.net.cat.epis.ws.m01_findorder.FindOrderResponse;

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
 *         &lt;element name="findOrderDetailResponse" type="{http://M01_FindOrder}FindOrderResponse"/&gt;
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
    "findOrderDetailResponse"
})
@XmlRootElement(name = "findOrderInfoResponse")
public class FindOrderInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected FindOrderResponse findOrderDetailResponse;

    /**
     * Gets the value of the findOrderDetailResponse property.
     * 
     * @return
     *     possible object is
     *     {@link FindOrderResponse }
     *     
     */
    public FindOrderResponse getFindOrderDetailResponse() {
        return findOrderDetailResponse;
    }

    /**
     * Sets the value of the findOrderDetailResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindOrderResponse }
     *     
     */
    public void setFindOrderDetailResponse(FindOrderResponse value) {
        this.findOrderDetailResponse = value;
    }

}

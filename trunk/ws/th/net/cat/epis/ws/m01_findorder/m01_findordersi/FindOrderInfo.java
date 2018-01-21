
package th.net.cat.epis.ws.m01_findorder.m01_findordersi;

import th.net.cat.epis.ws.m01_findorder.FindOrderRequest;

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
 *         &lt;element name="findOrderDetailRequest" type="{http://M01_FindOrder}FindOrderRequest"/&gt;
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
    "findOrderDetailRequest"
})
@XmlRootElement(name = "findOrderInfo")
public class FindOrderInfo {

    @XmlElement(required = true, nillable = true)
    protected FindOrderRequest findOrderDetailRequest;

    /**
     * Gets the value of the findOrderDetailRequest property.
     * 
     * @return
     *     possible object is
     *     {@link FindOrderRequest }
     *     
     */
    public FindOrderRequest getFindOrderDetailRequest() {
        return findOrderDetailRequest;
    }

    /**
     * Sets the value of the findOrderDetailRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindOrderRequest }
     *     
     */
    public void setFindOrderDetailRequest(FindOrderRequest value) {
        this.findOrderDetailRequest = value;
    }

}

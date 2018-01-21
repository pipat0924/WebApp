
package th.net.cat.epis.ws.m02_findorderdetail.m02_findorderdetailsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailResponse;


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
 *         &lt;element name="findOrderDetailResponse" type="{http://M02_FindOrderDetail}FindOrderDetailResponse"/&gt;
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
@XmlRootElement(name = "findOrderDetailInfoResponse")
public class FindOrderDetailInfoResponse {

    @XmlElement(required = true, nillable = true)
    protected FindOrderDetailResponse findOrderDetailResponse;

    /**
     * Gets the value of the findOrderDetailResponse property.
     * 
     * @return
     *     possible object is
     *     {@link FindOrderDetailResponse }
     *     
     */
    public FindOrderDetailResponse getFindOrderDetailResponse() {
        return findOrderDetailResponse;
    }

    /**
     * Sets the value of the findOrderDetailResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindOrderDetailResponse }
     *     
     */
    public void setFindOrderDetailResponse(FindOrderDetailResponse value) {
        this.findOrderDetailResponse = value;
    }

}

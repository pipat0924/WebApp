
package th.net.cat.epis.ws.m02_findorderdetail.m02_findorderdetailsi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailRequest;


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
 *         &lt;element name="findOrderDetailRequest" type="{http://M02_FindOrderDetail}FindOrderDetailRequest"/&gt;
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
@XmlRootElement(name = "findOrderDetailInfo")
public class FindOrderDetailInfo {

    @XmlElement(required = true, nillable = true)
    protected FindOrderDetailRequest findOrderDetailRequest;

    /**
     * Gets the value of the findOrderDetailRequest property.
     * 
     * @return
     *     possible object is
     *     {@link FindOrderDetailRequest }
     *     
     */
    public FindOrderDetailRequest getFindOrderDetailRequest() {
        return findOrderDetailRequest;
    }

    /**
     * Sets the value of the findOrderDetailRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindOrderDetailRequest }
     *     
     */
    public void setFindOrderDetailRequest(FindOrderDetailRequest value) {
        this.findOrderDetailRequest = value;
    }

}

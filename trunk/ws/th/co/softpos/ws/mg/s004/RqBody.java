
package th.co.softpos.ws.mg.s004;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rqBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rqBody"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="posFreeBillno" type="{http://s004.mg.ws.softpos.co.th/}posFreeBillno"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rqBody", propOrder = {
    "posFreeBillno"
})
public class RqBody {

    @XmlElement(required = true, nillable = true)
    protected PosFreeBillno posFreeBillno;

    /**
     * Gets the value of the posFreeBillno property.
     * 
     * @return
     *     possible object is
     *     {@link PosFreeBillno }
     *     
     */
    public PosFreeBillno getPosFreeBillno() {
        return posFreeBillno;
    }

    /**
     * Sets the value of the posFreeBillno property.
     * 
     * @param value
     *     allowed object is
     *     {@link PosFreeBillno }
     *     
     */
    public void setPosFreeBillno(PosFreeBillno value) {
        this.posFreeBillno = value;
    }

}

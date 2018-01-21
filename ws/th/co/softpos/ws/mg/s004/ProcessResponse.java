
package th.co.softpos.ws.mg.s004;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for processResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="processResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rs" type="{http://s004.mg.ws.softpos.co.th/}response"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processResponse", propOrder = {
    "rs"
})
public class ProcessResponse {

    @XmlElement(required = true)
    protected Response rs;

    /**
     * Gets the value of the rs property.
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getRs() {
        return rs;
    }

    /**
     * Sets the value of the rs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setRs(Response value) {
        this.rs = value;
    }

}

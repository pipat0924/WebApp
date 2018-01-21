
package th.co.softpos.ws.mg.s004;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for posFreeBillno complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="posFreeBillno"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="docRefNo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="docSendNo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "posFreeBillno", propOrder = {
    "paymentType",
    "docRefNo",
    "docSendNo",
    "status"
})
public class PosFreeBillno {

    @XmlElement(required = true, nillable = true)
    protected String paymentType;
    @XmlElement(required = true, nillable = true)
    protected String docRefNo;
    @XmlElement(required = true, nillable = true)
    protected String docSendNo;
    @XmlElement(required = true, nillable = true)
    protected String status;

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentType(String value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the docRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocRefNo() {
        return docRefNo;
    }

    /**
     * Sets the value of the docRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocRefNo(String value) {
        this.docRefNo = value;
    }

    /**
     * Gets the value of the docSendNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocSendNo() {
        return docSendNo;
    }

    /**
     * Sets the value of the docSendNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocSendNo(String value) {
        this.docSendNo = value;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    

}

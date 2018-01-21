
package th.net.cat.epis.ws.m05_repeatorder;

import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RepeatOrderRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RepeatOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionLog" type="{http://ESBLibs/CBOs}TransactionLogCBO" minOccurs="0"/&gt;
 *         &lt;element name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RepeatOrderRequest", propOrder = {
    "transactionLog",
    "orderId"
})
public class RepeatOrderRequest {

    @XmlElement(name = "TransactionLog")
    protected TransactionLogCBO transactionLog;
    @XmlElement(name = "OrderId")
    protected String orderId;

    /**
     * Gets the value of the transactionLog property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionLogCBO }
     *     
     */
    public TransactionLogCBO getTransactionLog() {
        return transactionLog;
    }

    /**
     * Sets the value of the transactionLog property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionLogCBO }
     *     
     */
    public void setTransactionLog(TransactionLogCBO value) {
        this.transactionLog = value;
    }

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

}

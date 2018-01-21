
package th.net.cat.epis.ws.esblibs.requestresponsecbo.f4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import th.net.cat.epis.ws.esblibs.cbos.PaymentCBO;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;


/**
 * <p>Java class for CreatePaymentResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreatePaymentResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionLog" type="{http://ESBLibs/CBOs}TransactionLogCBO" minOccurs="0"/&gt;
 *         &lt;element name="Payment" type="{http://ESBLibs/CBOs}PaymentCBO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreatePaymentResponse", propOrder = {
    "transactionLog",
    "payment"
})
public class CreatePaymentResponse {

    @XmlElement(name = "TransactionLog")
    protected TransactionLogCBO transactionLog;
    @XmlElement(name = "Payment")
    protected PaymentCBO payment;

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
     * Gets the value of the payment property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentCBO }
     *     
     */
    public PaymentCBO getPayment() {
        return payment;
    }

    /**
     * Sets the value of the payment property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentCBO }
     *     
     */
    public void setPayment(PaymentCBO value) {
        this.payment = value;
    }

}


package th.net.cat.epis.ws.esblibs.requestresponsecbo.f4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import th.net.cat.epis.ws.esblibs.cbos.AccountCBO;
import th.net.cat.epis.ws.esblibs.cbos.PaymentCBO;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;


/**
 * <p>Java class for CreatePaymentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreatePaymentRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionLog" type="{http://ESBLibs/CBOs}TransactionLogCBO" minOccurs="0"/&gt;
 *         &lt;element name="Account" type="{http://ESBLibs/CBOs}AccountCBO" minOccurs="0"/&gt;
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
@XmlType(name = "CreatePaymentRequest", propOrder = {
    "transactionLog",
    "account",
    "payment"
})
public class CreatePaymentRequest {

    @XmlElement(name = "TransactionLog")
    protected TransactionLogCBO transactionLog;
    @XmlElement(name = "Account")
    protected AccountCBO account;
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
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link AccountCBO }
     *     
     */
    public AccountCBO getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountCBO }
     *     
     */
    public void setAccount(AccountCBO value) {
        this.account = value;
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

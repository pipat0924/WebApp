
package th.net.cat.epis.ws.f03_retrieveinvoicecharges;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;


/**
 * <p>Java class for RetrieveInvoiceChargeInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetrieveInvoiceChargeInfoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionLog" type="{http://ESBLibs/CBOs}TransactionLogCBO" minOccurs="0"/&gt;
 *         &lt;element name="RentalCharge" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="UsageCharge" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveInvoiceChargeInfoResponse", propOrder = {
    "transactionLog",
    "rentalCharge",
    "usageCharge"
})
public class RetrieveInvoiceChargeInfoResponse {

    @XmlElement(name = "TransactionLog")
    protected TransactionLogCBO transactionLog;
    @XmlElement(name = "RentalCharge")
    protected Double rentalCharge;
    @XmlElement(name = "UsageCharge")
    protected Double usageCharge;

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
     * Gets the value of the rentalCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRentalCharge() {
        return rentalCharge;
    }

    /**
     * Sets the value of the rentalCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRentalCharge(Double value) {
        this.rentalCharge = value;
    }

    /**
     * Gets the value of the usageCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUsageCharge() {
        return usageCharge;
    }

    /**
     * Sets the value of the usageCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUsageCharge(Double value) {
        this.usageCharge = value;
    }

}

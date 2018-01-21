
package th.net.cat.epis.ws.f06_reversepayment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;


/**
 * <p>Java class for ReversePaymentResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReversePaymentResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionLog" type="{http://ESBLibs/CBOs}TransactionLogCBO" minOccurs="0"/&gt;
 *         &lt;element name="CancelType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrackingId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="TrackingIdServ" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="BillReturnCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="BillRefMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReversePaymentResponse", propOrder = {
    "transactionLog",
    "cancelType",
    "trackingId",
    "trackingIdServ",
    "billReturnCode",
    "billRefMsg"
})
public class ReversePaymentResponse {

    @XmlElement(name = "TransactionLog")
    protected TransactionLogCBO transactionLog;
    @XmlElement(name = "CancelType")
    protected String cancelType;
    @XmlElement(name = "TrackingId")
    protected Integer trackingId;
    @XmlElement(name = "TrackingIdServ")
    protected Integer trackingIdServ;
    @XmlElement(name = "BillReturnCode")
    protected Integer billReturnCode;
    @XmlElement(name = "BillRefMsg")
    protected String billRefMsg;

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
     * Gets the value of the cancelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelType() {
        return cancelType;
    }

    /**
     * Sets the value of the cancelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelType(String value) {
        this.cancelType = value;
    }

    /**
     * Gets the value of the trackingId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTrackingId() {
        return trackingId;
    }

    /**
     * Sets the value of the trackingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTrackingId(Integer value) {
        this.trackingId = value;
    }

    /**
     * Gets the value of the trackingIdServ property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTrackingIdServ() {
        return trackingIdServ;
    }

    /**
     * Sets the value of the trackingIdServ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTrackingIdServ(Integer value) {
        this.trackingIdServ = value;
    }

    /**
     * Gets the value of the billReturnCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBillReturnCode() {
        return billReturnCode;
    }

    /**
     * Sets the value of the billReturnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBillReturnCode(Integer value) {
        this.billReturnCode = value;
    }

    /**
     * Gets the value of the billRefMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillRefMsg() {
        return billRefMsg;
    }

    /**
     * Sets the value of the billRefMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillRefMsg(String value) {
        this.billRefMsg = value;
    }

}

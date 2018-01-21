
package th.net.cat.epis.ws.f13_retrievepaymenthistory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;


/**
 * <p>Java class for RetrievePaymentHistoryRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetrievePaymentHistoryRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransactionLog" type="{http://ESBLibs/CBOs}TransactionLogCBO" minOccurs="0"/&gt;
 *         &lt;element name="BillingAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillRefNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrackingNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StartProcessDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EndProcessDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StartPayDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EndPayDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LimitRow" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrievePaymentHistoryRequest", propOrder = {
    "transactionLog",
    "billingAccountNo",
    "billRefNo",
    "trackingNo",
    "startProcessDate",
    "endProcessDate",
    "startPayDate",
    "endPayDate",
    "limitRow"
})
public class RetrievePaymentHistoryRequest {

    @XmlElement(name = "TransactionLog")
    protected th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO transactionLog;
    @XmlElement(name = "BillingAccountNo")
    protected String billingAccountNo;
    @XmlElement(name = "BillRefNo")
    protected String billRefNo;
    @XmlElement(name = "TrackingNo")
    protected String trackingNo;
    @XmlElement(name = "StartProcessDate")
    protected String startProcessDate;
    @XmlElement(name = "EndProcessDate")
    protected String endProcessDate;
    @XmlElement(name = "StartPayDate")
    protected String startPayDate;
    @XmlElement(name = "EndPayDate")
    protected String endPayDate;
    @XmlElement(name = "LimitRow")
    protected Integer limitRow;

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
     * Gets the value of the billingAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNo() {
        return billingAccountNo;
    }

    /**
     * Sets the value of the billingAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNo(String value) {
        this.billingAccountNo = value;
    }

    /**
     * Gets the value of the billRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillRefNo() {
        return billRefNo;
    }

    /**
     * Sets the value of the billRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillRefNo(String value) {
        this.billRefNo = value;
    }

    /**
     * Gets the value of the trackingNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    /**
     * Sets the value of the trackingNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNo(String value) {
        this.trackingNo = value;
    }

    /**
     * Gets the value of the startProcessDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartProcessDate() {
        return startProcessDate;
    }

    /**
     * Sets the value of the startProcessDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartProcessDate(String value) {
        this.startProcessDate = value;
    }

    /**
     * Gets the value of the endProcessDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndProcessDate() {
        return endProcessDate;
    }

    /**
     * Sets the value of the endProcessDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndProcessDate(String value) {
        this.endProcessDate = value;
    }

    /**
     * Gets the value of the startPayDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartPayDate() {
        return startPayDate;
    }

    /**
     * Sets the value of the startPayDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartPayDate(String value) {
        this.startPayDate = value;
    }

    /**
     * Gets the value of the endPayDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndPayDate() {
        return endPayDate;
    }

    /**
     * Sets the value of the endPayDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndPayDate(String value) {
        this.endPayDate = value;
    }

    /**
     * Gets the value of the limitRow property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLimitRow() {
        return limitRow;
    }

    /**
     * Sets the value of the limitRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLimitRow(Integer value) {
        this.limitRow = value;
    }

}

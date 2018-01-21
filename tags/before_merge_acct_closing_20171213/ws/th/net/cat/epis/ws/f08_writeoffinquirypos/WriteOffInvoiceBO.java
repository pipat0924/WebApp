
package th.net.cat.epis.ws.f08_writeoffinquirypos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WriteOffInvoiceBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WriteOffInvoiceBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AccountNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="BillRefNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="BillRefResets" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="PpddDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="NewCharges" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="BalanceDue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="TotalPaid" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="TotalAdj" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="WriteOffDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="RewriteOffAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="CurrentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IsTax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PayDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="VatAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="VatRate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="BillingAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InvStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FeeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PayVatAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="BillPeriod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="VolumnDiscount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="SubscriptionBO" type="{http://F08_WriteOffInquiryPOS}SubscriptionBO" minOccurs="0"/&gt;
 *         &lt;element name="InvoiceVatDetailList" type="{http://F08_WriteOffInquiryPOS}InvoiceVatDetailBO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WriteOffInvoiceBO", propOrder = {
    "accountNo",
    "billRefNo",
    "billRefResets",
    "ppddDate",
    "newCharges",
    "balanceDue",
    "totalPaid",
    "totalAdj",
    "writeOffDate",
    "rewriteOffAmount",
    "currentCode",
    "isTax",
    "payDate",
    "vatAmount",
    "vatRate",
    "billingAccountNo",
    "invStatus",
    "feeId",
    "payVatAmount",
    "issueDate",
    "billPeriod",
    "createDate",
    "volumnDiscount",
    "subscriptionBO",
    "invoiceVatDetailList"
})
public class WriteOffInvoiceBO {

    @XmlElement(name = "AccountNo")
    protected Integer accountNo;
    @XmlElement(name = "BillRefNo")
    protected Integer billRefNo;
    @XmlElement(name = "BillRefResets")
    protected Integer billRefResets;
    @XmlElement(name = "PpddDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ppddDate;
    @XmlElement(name = "NewCharges")
    protected Double newCharges;
    @XmlElement(name = "BalanceDue")
    protected Double balanceDue;
    @XmlElement(name = "TotalPaid")
    protected Double totalPaid;
    @XmlElement(name = "TotalAdj")
    protected Double totalAdj;
    @XmlElement(name = "WriteOffDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar writeOffDate;
    @XmlElement(name = "RewriteOffAmount")
    protected Double rewriteOffAmount;
    @XmlElement(name = "CurrentCode")
    protected String currentCode;
    @XmlElement(name = "IsTax")
    protected String isTax;
    @XmlElement(name = "PayDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar payDate;
    @XmlElement(name = "VatAmount")
    protected Double vatAmount;
    @XmlElement(name = "VatRate")
    protected Integer vatRate;
    @XmlElement(name = "BillingAccountNo")
    protected String billingAccountNo;
    @XmlElement(name = "InvStatus")
    protected String invStatus;
    @XmlElement(name = "FeeId")
    protected String feeId;
    @XmlElement(name = "PayVatAmount")
    protected Double payVatAmount;
    @XmlElement(name = "IssueDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar issueDate;
    @XmlElement(name = "BillPeriod")
    protected String billPeriod;
    @XmlElement(name = "CreateDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    @XmlElement(name = "VolumnDiscount")
    protected Double volumnDiscount;
    @XmlElement(name = "SubscriptionBO")
    protected SubscriptionBO subscriptionBO;
    @XmlElement(name = "InvoiceVatDetailList")
    protected List<InvoiceVatDetailBO> invoiceVatDetailList;

    /**
     * Gets the value of the accountNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the value of the accountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccountNo(Integer value) {
        this.accountNo = value;
    }

    /**
     * Gets the value of the billRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBillRefNo() {
        return billRefNo;
    }

    /**
     * Sets the value of the billRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBillRefNo(Integer value) {
        this.billRefNo = value;
    }

    /**
     * Gets the value of the billRefResets property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBillRefResets() {
        return billRefResets;
    }

    /**
     * Sets the value of the billRefResets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBillRefResets(Integer value) {
        this.billRefResets = value;
    }

    /**
     * Gets the value of the ppddDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPpddDate() {
        return ppddDate;
    }

    /**
     * Sets the value of the ppddDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPpddDate(XMLGregorianCalendar value) {
        this.ppddDate = value;
    }

    /**
     * Gets the value of the newCharges property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNewCharges() {
        return newCharges;
    }

    /**
     * Sets the value of the newCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNewCharges(Double value) {
        this.newCharges = value;
    }

    /**
     * Gets the value of the balanceDue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBalanceDue() {
        return balanceDue;
    }

    /**
     * Sets the value of the balanceDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBalanceDue(Double value) {
        this.balanceDue = value;
    }

    /**
     * Gets the value of the totalPaid property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPaid() {
        return totalPaid;
    }

    /**
     * Sets the value of the totalPaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPaid(Double value) {
        this.totalPaid = value;
    }

    /**
     * Gets the value of the totalAdj property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalAdj() {
        return totalAdj;
    }

    /**
     * Sets the value of the totalAdj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalAdj(Double value) {
        this.totalAdj = value;
    }

    /**
     * Gets the value of the writeOffDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWriteOffDate() {
        return writeOffDate;
    }

    /**
     * Sets the value of the writeOffDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWriteOffDate(XMLGregorianCalendar value) {
        this.writeOffDate = value;
    }

    /**
     * Gets the value of the rewriteOffAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRewriteOffAmount() {
        return rewriteOffAmount;
    }

    /**
     * Sets the value of the rewriteOffAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRewriteOffAmount(Double value) {
        this.rewriteOffAmount = value;
    }

    /**
     * Gets the value of the currentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentCode() {
        return currentCode;
    }

    /**
     * Sets the value of the currentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentCode(String value) {
        this.currentCode = value;
    }

    /**
     * Gets the value of the isTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTax() {
        return isTax;
    }

    /**
     * Sets the value of the isTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTax(String value) {
        this.isTax = value;
    }

    /**
     * Gets the value of the payDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPayDate() {
        return payDate;
    }

    /**
     * Sets the value of the payDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPayDate(XMLGregorianCalendar value) {
        this.payDate = value;
    }

    /**
     * Gets the value of the vatAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVatAmount() {
        return vatAmount;
    }

    /**
     * Sets the value of the vatAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVatAmount(Double value) {
        this.vatAmount = value;
    }

    /**
     * Gets the value of the vatRate property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVatRate() {
        return vatRate;
    }

    /**
     * Sets the value of the vatRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVatRate(Integer value) {
        this.vatRate = value;
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
     * Gets the value of the invStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvStatus() {
        return invStatus;
    }

    /**
     * Sets the value of the invStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvStatus(String value) {
        this.invStatus = value;
    }

    /**
     * Gets the value of the feeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * Sets the value of the feeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeId(String value) {
        this.feeId = value;
    }

    /**
     * Gets the value of the payVatAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPayVatAmount() {
        return payVatAmount;
    }

    /**
     * Sets the value of the payVatAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPayVatAmount(Double value) {
        this.payVatAmount = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIssueDate(XMLGregorianCalendar value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the billPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillPeriod() {
        return billPeriod;
    }

    /**
     * Sets the value of the billPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillPeriod(String value) {
        this.billPeriod = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the volumnDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVolumnDiscount() {
        return volumnDiscount;
    }

    /**
     * Sets the value of the volumnDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVolumnDiscount(Double value) {
        this.volumnDiscount = value;
    }

    /**
     * Gets the value of the subscriptionBO property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionBO }
     *     
     */
    public SubscriptionBO getSubscriptionBO() {
        return subscriptionBO;
    }

    /**
     * Sets the value of the subscriptionBO property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionBO }
     *     
     */
    public void setSubscriptionBO(SubscriptionBO value) {
        this.subscriptionBO = value;
    }

    /**
     * Gets the value of the invoiceVatDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceVatDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceVatDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvoiceVatDetailBO }
     * 
     * 
     */
    public List<InvoiceVatDetailBO> getInvoiceVatDetailList() {
        if (invoiceVatDetailList == null) {
            invoiceVatDetailList = new ArrayList<InvoiceVatDetailBO>();
        }
        return this.invoiceVatDetailList;
    }

}

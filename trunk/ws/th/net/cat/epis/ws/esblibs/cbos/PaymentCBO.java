
package th.net.cat.epis.ws.esblibs.cbos;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PaymentCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Key" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TrackingId" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="TrackingIdServ" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AccountInternalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AccountExternalId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="144"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AccountExternalIdType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ActionCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="5"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Annotation" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ArchFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="BatchId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="15"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BatchIdServ" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="BillOrderNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="25"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BillRefNo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="BillRefResets" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="CcardAccount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="22"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CcardCarrier" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CcardExpire" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CcardIdServ" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="CcardOwnrName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="40"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ChgDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustBillCountryCode" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DiscountId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DistributedAmount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DistributedGlAmount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ExternalAmount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ExternalCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FileId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="GlAmount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IsCurrent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ManualCcauthCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ManualCcauthDate" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MicrBankId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MicrCheckNum" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="15"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MicrDdaNum" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NoBill" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="OpenItemId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="OrigBillRefNo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="OrigBillRefResets" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrigTrackingId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="OrigTrackingIdServ" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="PayMethod" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="PostDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="RealtimeIndicator" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ResponseCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SourceId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="SourceIdServ" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="SourceType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="TaxTypeCode" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="TransAmount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TransCategory" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="TransSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TransSubmitter" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TransType" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentCBO", propOrder = {
    "key",
    "accountInternalId",
    "accountExternalId",
    "accountExternalIdType",
    "actionCode",
    "annotation",
    "archFlag",
    "batchId",
    "batchIdServ",
    "billOrderNumber",
    "billRefNo",
    "billRefResets",
    "ccardAccount",
    "ccardCarrier",
    "ccardExpire",
    "ccardIdServ",
    "ccardOwnrName",
    "chgDate",
    "currencyCode",
    "custBillCountryCode",
    "discountId",
    "distributedAmount",
    "distributedGlAmount",
    "externalAmount",
    "externalCurrency",
    "fileId",
    "glAmount",
    "isCurrent",
    "manualCcauthCode",
    "manualCcauthDate",
    "micrBankId",
    "micrCheckNum",
    "micrDdaNum",
    "noBill",
    "openItemId",
    "origBillRefNo",
    "origBillRefResets",
    "origTrackingId",
    "origTrackingIdServ",
    "payMethod",
    "postDate",
    "realtimeIndicator",
    "responseCode",
    "sourceId",
    "sourceIdServ",
    "sourceType",
    "taxTypeCode",
    "transAmount",
    "transCategory",
    "transDate",
    "transSource",
    "transSubmitter",
    "transType"
})
public class PaymentCBO {

    @XmlElement(name = "Key")
    protected PaymentCBO.Key key;
    @XmlElement(name = "AccountInternalId")
    protected String accountInternalId;
    @XmlElement(name = "AccountExternalId")
    protected String accountExternalId;
    @XmlElement(name = "AccountExternalIdType")
    protected String accountExternalIdType;
    @XmlElement(name = "ActionCode")
    protected String actionCode;
    @XmlElement(name = "Annotation")
    protected String annotation;
    @XmlElement(name = "ArchFlag")
    protected boolean archFlag;
    @XmlElement(name = "BatchId")
    protected String batchId;
    @XmlElement(name = "BatchIdServ")
    protected Short batchIdServ;
    @XmlElement(name = "BillOrderNumber")
    protected String billOrderNumber;
    @XmlElement(name = "BillRefNo")
    protected BigInteger billRefNo;
    @XmlElement(name = "BillRefResets")
    protected Short billRefResets;
    @XmlElement(name = "CcardAccount")
    protected String ccardAccount;
    @XmlElement(name = "CcardCarrier")
    protected String ccardCarrier;
    @XmlElement(name = "CcardExpire")
    protected String ccardExpire;
    @XmlElement(name = "CcardIdServ")
    protected Short ccardIdServ;
    @XmlElement(name = "CcardOwnrName")
    protected String ccardOwnrName;
    @XmlElement(name = "ChgDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar chgDate;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "CustBillCountryCode")
    protected BigInteger custBillCountryCode;
    @XmlElement(name = "DiscountId")
    protected BigInteger discountId;
    @XmlElement(name = "DistributedAmount")
    protected String distributedAmount;
    @XmlElement(name = "DistributedGlAmount")
    protected String distributedGlAmount;
    @XmlElement(name = "ExternalAmount")
    protected String externalAmount;
    @XmlElement(name = "ExternalCurrency")
    protected String externalCurrency;
    @XmlElement(name = "FileId")
    protected BigInteger fileId;
    @XmlElement(name = "GlAmount")
    protected String glAmount;
    @XmlElement(name = "IsCurrent")
    protected Boolean isCurrent;
    @XmlElement(name = "ManualCcauthCode")
    protected String manualCcauthCode;
    @XmlElement(name = "ManualCcauthDate")
    protected String manualCcauthDate;
    @XmlElement(name = "MicrBankId")
    protected String micrBankId;
    @XmlElement(name = "MicrCheckNum")
    protected String micrCheckNum;
    @XmlElement(name = "MicrDdaNum")
    protected String micrDdaNum;
    @XmlElement(name = "NoBill")
    protected Boolean noBill;
    @XmlElement(name = "OpenItemId")
    protected BigInteger openItemId;
    @XmlElement(name = "OrigBillRefNo")
    protected BigInteger origBillRefNo;
    @XmlElement(name = "OrigBillRefResets")
    protected String origBillRefResets;
    @XmlElement(name = "OrigTrackingId")
    protected BigInteger origTrackingId;
    @XmlElement(name = "OrigTrackingIdServ")
    protected Short origTrackingIdServ;
    @XmlElement(name = "PayMethod")
    protected BigInteger payMethod;
    @XmlElement(name = "PostDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postDate;
    @XmlElement(name = "RealtimeIndicator")
    protected BigInteger realtimeIndicator;
    @XmlElement(name = "ResponseCode")
    protected String responseCode;
    @XmlElement(name = "SourceId")
    protected BigInteger sourceId;
    @XmlElement(name = "SourceIdServ")
    protected Short sourceIdServ;
    @XmlElement(name = "SourceType")
    protected Short sourceType;
    @XmlElement(name = "TaxTypeCode")
    protected BigInteger taxTypeCode;
    @XmlElement(name = "TransAmount")
    protected String transAmount;
    @XmlElement(name = "TransCategory")
    protected BigInteger transCategory;
    @XmlElement(name = "TransDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar transDate;
    @XmlElement(name = "TransSource")
    protected String transSource;
    @XmlElement(name = "TransSubmitter")
    protected String transSubmitter;
    @XmlElement(name = "TransType")
    protected BigInteger transType;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentCBO.Key }
     *     
     */
    public PaymentCBO.Key getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentCBO.Key }
     *     
     */
    public void setKey(PaymentCBO.Key value) {
        this.key = value;
    }

    /**
     * Gets the value of the accountInternalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountInternalId() {
        return accountInternalId;
    }

    /**
     * Sets the value of the accountInternalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountInternalId(String value) {
        this.accountInternalId = value;
    }

    /**
     * Gets the value of the accountExternalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountExternalId() {
        return accountExternalId;
    }

    /**
     * Sets the value of the accountExternalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountExternalId(String value) {
        this.accountExternalId = value;
    }

    /**
     * Gets the value of the accountExternalIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountExternalIdType() {
        return accountExternalIdType;
    }

    /**
     * Sets the value of the accountExternalIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountExternalIdType(String value) {
        this.accountExternalIdType = value;
    }

    /**
     * Gets the value of the actionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Sets the value of the actionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

    /**
     * Gets the value of the annotation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * Sets the value of the annotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnotation(String value) {
        this.annotation = value;
    }

    /**
     * Gets the value of the archFlag property.
     * 
     */
    public boolean isArchFlag() {
        return archFlag;
    }

    /**
     * Sets the value of the archFlag property.
     * 
     */
    public void setArchFlag(boolean value) {
        this.archFlag = value;
    }

    /**
     * Gets the value of the batchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Sets the value of the batchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchId(String value) {
        this.batchId = value;
    }

    /**
     * Gets the value of the batchIdServ property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getBatchIdServ() {
        return batchIdServ;
    }

    /**
     * Sets the value of the batchIdServ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setBatchIdServ(Short value) {
        this.batchIdServ = value;
    }

    /**
     * Gets the value of the billOrderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillOrderNumber() {
        return billOrderNumber;
    }

    /**
     * Sets the value of the billOrderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillOrderNumber(String value) {
        this.billOrderNumber = value;
    }

    /**
     * Gets the value of the billRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillRefNo() {
        return billRefNo;
    }

    /**
     * Sets the value of the billRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillRefNo(BigInteger value) {
        this.billRefNo = value;
    }

    /**
     * Gets the value of the billRefResets property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getBillRefResets() {
        return billRefResets;
    }

    /**
     * Sets the value of the billRefResets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setBillRefResets(Short value) {
        this.billRefResets = value;
    }

    /**
     * Gets the value of the ccardAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcardAccount() {
        return ccardAccount;
    }

    /**
     * Sets the value of the ccardAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcardAccount(String value) {
        this.ccardAccount = value;
    }

    /**
     * Gets the value of the ccardCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcardCarrier() {
        return ccardCarrier;
    }

    /**
     * Sets the value of the ccardCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcardCarrier(String value) {
        this.ccardCarrier = value;
    }

    /**
     * Gets the value of the ccardExpire property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcardExpire() {
        return ccardExpire;
    }

    /**
     * Sets the value of the ccardExpire property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcardExpire(String value) {
        this.ccardExpire = value;
    }

    /**
     * Gets the value of the ccardIdServ property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCcardIdServ() {
        return ccardIdServ;
    }

    /**
     * Sets the value of the ccardIdServ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCcardIdServ(Short value) {
        this.ccardIdServ = value;
    }

    /**
     * Gets the value of the ccardOwnrName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcardOwnrName() {
        return ccardOwnrName;
    }

    /**
     * Sets the value of the ccardOwnrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcardOwnrName(String value) {
        this.ccardOwnrName = value;
    }

    /**
     * Gets the value of the chgDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChgDate() {
        return chgDate;
    }

    /**
     * Sets the value of the chgDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChgDate(XMLGregorianCalendar value) {
        this.chgDate = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the custBillCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCustBillCountryCode() {
        return custBillCountryCode;
    }

    /**
     * Sets the value of the custBillCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCustBillCountryCode(BigInteger value) {
        this.custBillCountryCode = value;
    }

    /**
     * Gets the value of the discountId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDiscountId() {
        return discountId;
    }

    /**
     * Sets the value of the discountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDiscountId(BigInteger value) {
        this.discountId = value;
    }

    /**
     * Gets the value of the distributedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistributedAmount() {
        return distributedAmount;
    }

    /**
     * Sets the value of the distributedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistributedAmount(String value) {
        this.distributedAmount = value;
    }

    /**
     * Gets the value of the distributedGlAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistributedGlAmount() {
        return distributedGlAmount;
    }

    /**
     * Sets the value of the distributedGlAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistributedGlAmount(String value) {
        this.distributedGlAmount = value;
    }

    /**
     * Gets the value of the externalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalAmount() {
        return externalAmount;
    }

    /**
     * Sets the value of the externalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalAmount(String value) {
        this.externalAmount = value;
    }

    /**
     * Gets the value of the externalCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalCurrency() {
        return externalCurrency;
    }

    /**
     * Sets the value of the externalCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalCurrency(String value) {
        this.externalCurrency = value;
    }

    /**
     * Gets the value of the fileId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFileId() {
        return fileId;
    }

    /**
     * Sets the value of the fileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFileId(BigInteger value) {
        this.fileId = value;
    }

    /**
     * Gets the value of the glAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlAmount() {
        return glAmount;
    }

    /**
     * Sets the value of the glAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlAmount(String value) {
        this.glAmount = value;
    }

    /**
     * Gets the value of the isCurrent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCurrent() {
        return isCurrent;
    }

    /**
     * Sets the value of the isCurrent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCurrent(Boolean value) {
        this.isCurrent = value;
    }

    /**
     * Gets the value of the manualCcauthCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManualCcauthCode() {
        return manualCcauthCode;
    }

    /**
     * Sets the value of the manualCcauthCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManualCcauthCode(String value) {
        this.manualCcauthCode = value;
    }

    /**
     * Gets the value of the manualCcauthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManualCcauthDate() {
        return manualCcauthDate;
    }

    /**
     * Sets the value of the manualCcauthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManualCcauthDate(String value) {
        this.manualCcauthDate = value;
    }

    /**
     * Gets the value of the micrBankId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicrBankId() {
        return micrBankId;
    }

    /**
     * Sets the value of the micrBankId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicrBankId(String value) {
        this.micrBankId = value;
    }

    /**
     * Gets the value of the micrCheckNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicrCheckNum() {
        return micrCheckNum;
    }

    /**
     * Sets the value of the micrCheckNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicrCheckNum(String value) {
        this.micrCheckNum = value;
    }

    /**
     * Gets the value of the micrDdaNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicrDdaNum() {
        return micrDdaNum;
    }

    /**
     * Sets the value of the micrDdaNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicrDdaNum(String value) {
        this.micrDdaNum = value;
    }

    /**
     * Gets the value of the noBill property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoBill() {
        return noBill;
    }

    /**
     * Sets the value of the noBill property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoBill(Boolean value) {
        this.noBill = value;
    }

    /**
     * Gets the value of the openItemId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOpenItemId() {
        return openItemId;
    }

    /**
     * Sets the value of the openItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOpenItemId(BigInteger value) {
        this.openItemId = value;
    }

    /**
     * Gets the value of the origBillRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrigBillRefNo() {
        return origBillRefNo;
    }

    /**
     * Sets the value of the origBillRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrigBillRefNo(BigInteger value) {
        this.origBillRefNo = value;
    }

    /**
     * Gets the value of the origBillRefResets property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigBillRefResets() {
        return origBillRefResets;
    }

    /**
     * Sets the value of the origBillRefResets property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigBillRefResets(String value) {
        this.origBillRefResets = value;
    }

    /**
     * Gets the value of the origTrackingId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrigTrackingId() {
        return origTrackingId;
    }

    /**
     * Sets the value of the origTrackingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrigTrackingId(BigInteger value) {
        this.origTrackingId = value;
    }

    /**
     * Gets the value of the origTrackingIdServ property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getOrigTrackingIdServ() {
        return origTrackingIdServ;
    }

    /**
     * Sets the value of the origTrackingIdServ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setOrigTrackingIdServ(Short value) {
        this.origTrackingIdServ = value;
    }

    /**
     * Gets the value of the payMethod property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPayMethod() {
        return payMethod;
    }

    /**
     * Sets the value of the payMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPayMethod(BigInteger value) {
        this.payMethod = value;
    }

    /**
     * Gets the value of the postDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostDate() {
        return postDate;
    }

    /**
     * Sets the value of the postDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostDate(XMLGregorianCalendar value) {
        this.postDate = value;
    }

    /**
     * Gets the value of the realtimeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRealtimeIndicator() {
        return realtimeIndicator;
    }

    /**
     * Sets the value of the realtimeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRealtimeIndicator(BigInteger value) {
        this.realtimeIndicator = value;
    }

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the sourceId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSourceId() {
        return sourceId;
    }

    /**
     * Sets the value of the sourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSourceId(BigInteger value) {
        this.sourceId = value;
    }

    /**
     * Gets the value of the sourceIdServ property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getSourceIdServ() {
        return sourceIdServ;
    }

    /**
     * Sets the value of the sourceIdServ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setSourceIdServ(Short value) {
        this.sourceIdServ = value;
    }

    /**
     * Gets the value of the sourceType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getSourceType() {
        return sourceType;
    }

    /**
     * Sets the value of the sourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setSourceType(Short value) {
        this.sourceType = value;
    }

    /**
     * Gets the value of the taxTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTaxTypeCode() {
        return taxTypeCode;
    }

    /**
     * Sets the value of the taxTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTaxTypeCode(BigInteger value) {
        this.taxTypeCode = value;
    }

    /**
     * Gets the value of the transAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransAmount() {
        return transAmount;
    }

    /**
     * Sets the value of the transAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransAmount(String value) {
        this.transAmount = value;
    }

    /**
     * Gets the value of the transCategory property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTransCategory() {
        return transCategory;
    }

    /**
     * Sets the value of the transCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTransCategory(BigInteger value) {
        this.transCategory = value;
    }

    /**
     * Gets the value of the transDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransDate() {
        return transDate;
    }

    /**
     * Sets the value of the transDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransDate(XMLGregorianCalendar value) {
        this.transDate = value;
    }

    /**
     * Gets the value of the transSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransSource() {
        return transSource;
    }

    /**
     * Sets the value of the transSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransSource(String value) {
        this.transSource = value;
    }

    /**
     * Gets the value of the transSubmitter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransSubmitter() {
        return transSubmitter;
    }

    /**
     * Sets the value of the transSubmitter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransSubmitter(String value) {
        this.transSubmitter = value;
    }

    /**
     * Gets the value of the transType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTransType() {
        return transType;
    }

    /**
     * Sets the value of the transType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTransType(BigInteger value) {
        this.transType = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="TrackingId" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="TrackingIdServ" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "trackingId",
        "trackingIdServ"
    })
    public static class Key {

        @XmlElement(name = "TrackingId", required = true)
        protected BigInteger trackingId;
        @XmlElement(name = "TrackingIdServ")
        protected short trackingIdServ;

        /**
         * Gets the value of the trackingId property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTrackingId() {
            return trackingId;
        }

        /**
         * Sets the value of the trackingId property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTrackingId(BigInteger value) {
            this.trackingId = value;
        }

        /**
         * Gets the value of the trackingIdServ property.
         * 
         */
        public short getTrackingIdServ() {
            return trackingIdServ;
        }

        /**
         * Sets the value of the trackingIdServ property.
         * 
         */
        public void setTrackingIdServ(short value) {
            this.trackingIdServ = value;
        }

    }

}

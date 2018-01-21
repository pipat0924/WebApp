
package th.net.cat.epis.ws.esblibs.cbos;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentProfileCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentProfileCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Key" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ProfileId"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *                         &lt;maxLength value="18"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AccountInternalId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="AltBankAccNum" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AvsAddressId" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="AvsResponseCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BankAccountType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="BankAgencyCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="5"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BankAgencyName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="24"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BankCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BillCompanyTaxId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BranchCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BranchName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CardAccount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="22"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CardCarrier" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CardExpire" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ClearingHouseId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustBankAccNum" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustBankAcctType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustBankSortCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="9"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustBillAddress" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="75"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustBillCity" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustBillCountryCode" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="CustBillState" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="28"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustBillZip" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="16"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustCompanyName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="40"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustEmail" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="144"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustPhone" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="15"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CyclicalBillUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DriverLicenseNum" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DriverLicenseState" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AccountExternalId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="144"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AccountExternalIdType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="IsDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="IsTemporary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="NewCustBankSortCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="9"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NonRealtimeOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="OwnrFname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OwnrLname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PayMethod" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="TransFlag" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="WTFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentProfileCBO", propOrder = {
    "key",
    "accountInternalId",
    "altBankAccNum",
    "avsAddressId",
    "avsResponseCode",
    "bankAccountType",
    "bankAgencyCode",
    "bankAgencyName",
    "bankCode",
    "billCompanyTaxId",
    "branchCode",
    "branchName",
    "cardAccount",
    "cardCarrier",
    "cardExpire",
    "clearingHouseId",
    "custBankAccNum",
    "custBankAcctType",
    "custBankSortCode",
    "custBillAddress",
    "custBillCity",
    "custBillCountryCode",
    "custBillState",
    "custBillZip",
    "custCompanyName",
    "custEmail",
    "custPhone",
    "cyclicalBillUsed",
    "driverLicenseNum",
    "driverLicenseState",
    "accountExternalId",
    "accountExternalIdType",
    "isDefault",
    "isTemporary",
    "newCustBankSortCode",
    "nonRealtimeOnly",
    "ownrFname",
    "ownrLname",
    "payMethod",
    "status",
    "transFlag",
    "wtFlag",
    "createDate"
})
public class PaymentProfileCBO {

    @XmlElement(name = "Key")
    protected PaymentProfileCBO.Key key;
    @XmlElement(name = "AccountInternalId")
    protected BigInteger accountInternalId;
    @XmlElement(name = "AltBankAccNum", nillable = true)
    protected String altBankAccNum;
    @XmlElement(name = "AvsAddressId", nillable = true)
    protected Short avsAddressId;
    @XmlElement(name = "AvsResponseCode", nillable = true)
    protected String avsResponseCode;
    @XmlElement(name = "BankAccountType", nillable = true)
    protected Short bankAccountType;
    @XmlElement(name = "BankAgencyCode", nillable = true)
    protected String bankAgencyCode;
    @XmlElement(name = "BankAgencyName", nillable = true)
    protected String bankAgencyName;
    @XmlElement(name = "BankCode", nillable = true)
    protected String bankCode;
    @XmlElement(name = "BillCompanyTaxId", nillable = true)
    protected String billCompanyTaxId;
    @XmlElement(name = "BranchCode", nillable = true)
    protected String branchCode;
    @XmlElement(name = "BranchName", nillable = true)
    protected String branchName;
    @XmlElement(name = "CardAccount", nillable = true)
    protected String cardAccount;
    @XmlElement(name = "CardCarrier", nillable = true)
    protected String cardCarrier;
    @XmlElement(name = "CardExpire", nillable = true)
    protected String cardExpire;
    @XmlElement(name = "ClearingHouseId", nillable = true)
    protected String clearingHouseId;
    @XmlElement(name = "CustBankAccNum", nillable = true)
    protected String custBankAccNum;
    @XmlElement(name = "CustBankAcctType", nillable = true)
    protected String custBankAcctType;
    @XmlElement(name = "CustBankSortCode", nillable = true)
    protected String custBankSortCode;
    @XmlElement(name = "CustBillAddress", nillable = true)
    protected String custBillAddress;
    @XmlElement(name = "CustBillCity", nillable = true)
    protected String custBillCity;
    @XmlElement(name = "CustBillCountryCode", nillable = true)
    protected Short custBillCountryCode;
    @XmlElement(name = "CustBillState", nillable = true)
    protected String custBillState;
    @XmlElement(name = "CustBillZip", nillable = true)
    protected String custBillZip;
    @XmlElement(name = "CustCompanyName", nillable = true)
    protected String custCompanyName;
    @XmlElement(name = "CustEmail", nillable = true)
    protected String custEmail;
    @XmlElement(name = "CustPhone", nillable = true)
    protected String custPhone;
    @XmlElement(name = "CyclicalBillUsed")
    protected Boolean cyclicalBillUsed;
    @XmlElement(name = "DriverLicenseNum", nillable = true)
    protected String driverLicenseNum;
    @XmlElement(name = "DriverLicenseState", nillable = true)
    protected String driverLicenseState;
    @XmlElement(name = "AccountExternalId")
    protected String accountExternalId;
    @XmlElement(name = "AccountExternalIdType")
    protected Short accountExternalIdType;
    @XmlElement(name = "IsDefault")
    protected Boolean isDefault;
    @XmlElement(name = "IsTemporary")
    protected Boolean isTemporary;
    @XmlElement(name = "NewCustBankSortCode", nillable = true)
    protected String newCustBankSortCode;
    @XmlElement(name = "NonRealtimeOnly")
    protected Boolean nonRealtimeOnly;
    @XmlElement(name = "OwnrFname", nillable = true)
    protected String ownrFname;
    @XmlElement(name = "OwnrLname", nillable = true)
    protected String ownrLname;
    @XmlElement(name = "PayMethod")
    protected Short payMethod;
    @XmlElement(name = "Status")
    protected Short status;
    @XmlElement(name = "TransFlag", nillable = true)
    protected String transFlag;
    @XmlElement(name = "WTFlag")
    protected String wtFlag;
    @XmlElement(name = "CreateDate")
    protected String createDate;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentProfileCBO.Key }
     *     
     */
    public PaymentProfileCBO.Key getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentProfileCBO.Key }
     *     
     */
    public void setKey(PaymentProfileCBO.Key value) {
        this.key = value;
    }

    /**
     * Gets the value of the accountInternalId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAccountInternalId() {
        return accountInternalId;
    }

    /**
     * Sets the value of the accountInternalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAccountInternalId(BigInteger value) {
        this.accountInternalId = value;
    }

    /**
     * Gets the value of the altBankAccNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltBankAccNum() {
        return altBankAccNum;
    }

    /**
     * Sets the value of the altBankAccNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltBankAccNum(String value) {
        this.altBankAccNum = value;
    }

    /**
     * Gets the value of the avsAddressId property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getAvsAddressId() {
        return avsAddressId;
    }

    /**
     * Sets the value of the avsAddressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setAvsAddressId(Short value) {
        this.avsAddressId = value;
    }

    /**
     * Gets the value of the avsResponseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvsResponseCode() {
        return avsResponseCode;
    }

    /**
     * Sets the value of the avsResponseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvsResponseCode(String value) {
        this.avsResponseCode = value;
    }

    /**
     * Gets the value of the bankAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getBankAccountType() {
        return bankAccountType;
    }

    /**
     * Sets the value of the bankAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setBankAccountType(Short value) {
        this.bankAccountType = value;
    }

    /**
     * Gets the value of the bankAgencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAgencyCode() {
        return bankAgencyCode;
    }

    /**
     * Sets the value of the bankAgencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAgencyCode(String value) {
        this.bankAgencyCode = value;
    }

    /**
     * Gets the value of the bankAgencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAgencyName() {
        return bankAgencyName;
    }

    /**
     * Sets the value of the bankAgencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAgencyName(String value) {
        this.bankAgencyName = value;
    }

    /**
     * Gets the value of the bankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Sets the value of the bankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode(String value) {
        this.bankCode = value;
    }

    /**
     * Gets the value of the billCompanyTaxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCompanyTaxId() {
        return billCompanyTaxId;
    }

    /**
     * Sets the value of the billCompanyTaxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCompanyTaxId(String value) {
        this.billCompanyTaxId = value;
    }

    /**
     * Gets the value of the branchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * Sets the value of the branchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchCode(String value) {
        this.branchCode = value;
    }

    /**
     * Gets the value of the branchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Sets the value of the branchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchName(String value) {
        this.branchName = value;
    }

    /**
     * Gets the value of the cardAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardAccount() {
        return cardAccount;
    }

    /**
     * Sets the value of the cardAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardAccount(String value) {
        this.cardAccount = value;
    }

    /**
     * Gets the value of the cardCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardCarrier() {
        return cardCarrier;
    }

    /**
     * Sets the value of the cardCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardCarrier(String value) {
        this.cardCarrier = value;
    }

    /**
     * Gets the value of the cardExpire property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardExpire() {
        return cardExpire;
    }

    /**
     * Sets the value of the cardExpire property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardExpire(String value) {
        this.cardExpire = value;
    }

    /**
     * Gets the value of the clearingHouseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearingHouseId() {
        return clearingHouseId;
    }

    /**
     * Sets the value of the clearingHouseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearingHouseId(String value) {
        this.clearingHouseId = value;
    }

    /**
     * Gets the value of the custBankAccNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBankAccNum() {
        return custBankAccNum;
    }

    /**
     * Sets the value of the custBankAccNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBankAccNum(String value) {
        this.custBankAccNum = value;
    }

    /**
     * Gets the value of the custBankAcctType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBankAcctType() {
        return custBankAcctType;
    }

    /**
     * Sets the value of the custBankAcctType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBankAcctType(String value) {
        this.custBankAcctType = value;
    }

    /**
     * Gets the value of the custBankSortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBankSortCode() {
        return custBankSortCode;
    }

    /**
     * Sets the value of the custBankSortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBankSortCode(String value) {
        this.custBankSortCode = value;
    }

    /**
     * Gets the value of the custBillAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBillAddress() {
        return custBillAddress;
    }

    /**
     * Sets the value of the custBillAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBillAddress(String value) {
        this.custBillAddress = value;
    }

    /**
     * Gets the value of the custBillCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBillCity() {
        return custBillCity;
    }

    /**
     * Sets the value of the custBillCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBillCity(String value) {
        this.custBillCity = value;
    }

    /**
     * Gets the value of the custBillCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCustBillCountryCode() {
        return custBillCountryCode;
    }

    /**
     * Sets the value of the custBillCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCustBillCountryCode(Short value) {
        this.custBillCountryCode = value;
    }

    /**
     * Gets the value of the custBillState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBillState() {
        return custBillState;
    }

    /**
     * Sets the value of the custBillState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBillState(String value) {
        this.custBillState = value;
    }

    /**
     * Gets the value of the custBillZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustBillZip() {
        return custBillZip;
    }

    /**
     * Sets the value of the custBillZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustBillZip(String value) {
        this.custBillZip = value;
    }

    /**
     * Gets the value of the custCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustCompanyName() {
        return custCompanyName;
    }

    /**
     * Sets the value of the custCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustCompanyName(String value) {
        this.custCompanyName = value;
    }

    /**
     * Gets the value of the custEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustEmail() {
        return custEmail;
    }

    /**
     * Sets the value of the custEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustEmail(String value) {
        this.custEmail = value;
    }

    /**
     * Gets the value of the custPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustPhone() {
        return custPhone;
    }

    /**
     * Sets the value of the custPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustPhone(String value) {
        this.custPhone = value;
    }

    /**
     * Gets the value of the cyclicalBillUsed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCyclicalBillUsed() {
        return cyclicalBillUsed;
    }

    /**
     * Sets the value of the cyclicalBillUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCyclicalBillUsed(Boolean value) {
        this.cyclicalBillUsed = value;
    }

    /**
     * Gets the value of the driverLicenseNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverLicenseNum() {
        return driverLicenseNum;
    }

    /**
     * Sets the value of the driverLicenseNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverLicenseNum(String value) {
        this.driverLicenseNum = value;
    }

    /**
     * Gets the value of the driverLicenseState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverLicenseState() {
        return driverLicenseState;
    }

    /**
     * Sets the value of the driverLicenseState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverLicenseState(String value) {
        this.driverLicenseState = value;
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
     *     {@link Short }
     *     
     */
    public Short getAccountExternalIdType() {
        return accountExternalIdType;
    }

    /**
     * Sets the value of the accountExternalIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setAccountExternalIdType(Short value) {
        this.accountExternalIdType = value;
    }

    /**
     * Gets the value of the isDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDefault(Boolean value) {
        this.isDefault = value;
    }

    /**
     * Gets the value of the isTemporary property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsTemporary() {
        return isTemporary;
    }

    /**
     * Sets the value of the isTemporary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsTemporary(Boolean value) {
        this.isTemporary = value;
    }

    /**
     * Gets the value of the newCustBankSortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewCustBankSortCode() {
        return newCustBankSortCode;
    }

    /**
     * Sets the value of the newCustBankSortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewCustBankSortCode(String value) {
        this.newCustBankSortCode = value;
    }

    /**
     * Gets the value of the nonRealtimeOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonRealtimeOnly() {
        return nonRealtimeOnly;
    }

    /**
     * Sets the value of the nonRealtimeOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonRealtimeOnly(Boolean value) {
        this.nonRealtimeOnly = value;
    }

    /**
     * Gets the value of the ownrFname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnrFname() {
        return ownrFname;
    }

    /**
     * Sets the value of the ownrFname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnrFname(String value) {
        this.ownrFname = value;
    }

    /**
     * Gets the value of the ownrLname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnrLname() {
        return ownrLname;
    }

    /**
     * Sets the value of the ownrLname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnrLname(String value) {
        this.ownrLname = value;
    }

    /**
     * Gets the value of the payMethod property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getPayMethod() {
        return payMethod;
    }

    /**
     * Sets the value of the payMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setPayMethod(Short value) {
        this.payMethod = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setStatus(Short value) {
        this.status = value;
    }

    /**
     * Gets the value of the transFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransFlag() {
        return transFlag;
    }

    /**
     * Sets the value of the transFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransFlag(String value) {
        this.transFlag = value;
    }

    /**
     * Gets the value of the wtFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWTFlag() {
        return wtFlag;
    }

    /**
     * Sets the value of the wtFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWTFlag(String value) {
        this.wtFlag = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateDate(String value) {
        this.createDate = value;
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
     *         &lt;element name="ProfileId"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
     *               &lt;maxLength value="18"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
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
        "profileId"
    })
    public static class Key {

        @XmlElement(name = "ProfileId", required = true)
        protected String profileId;

        /**
         * Gets the value of the profileId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProfileId() {
            return profileId;
        }

        /**
         * Sets the value of the profileId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProfileId(String value) {
            this.profileId = value;
        }

    }

}

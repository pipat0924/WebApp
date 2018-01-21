
package th.net.cat.epis.ws.esblibs.cbos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AccountCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AccountType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="1"/&gt;
 *               &lt;enumeration value="C"/&gt;
 *               &lt;enumeration value="S"/&gt;
 *               &lt;enumeration value="B"/&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SourceAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SourceAccountIdType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="SourceParentAccountId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="SourceParentAccountIdType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="DestinationAccountId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DestinationAccountIdType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="144"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DestinationParentAccountId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DestinationParentAccountIdType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="AccountCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ThaiTitle" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ThaiFirstName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ThaiLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EngTitle" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EngFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EngLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RegistrationType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RegistrationNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="250"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RegistrationNumberIssueBy" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="250"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RegistrationNumberExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="BirthDate" type="{http://ESBLibs/CBOs}BirthDateCBO" minOccurs="0"/&gt;
 *         &lt;element name="Nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="eMailAddress" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Education" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Occupation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Salary" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="BillCompany" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BusinessType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BusinessRegistrationNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CompanyTaxId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="50"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BusinessRegistrationNumberIssuedBy" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BusinessRegistrationNumberIssuedDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="ContactPerson" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="250"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ContactPersonPosition" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ContactTelephoneNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="WebSite" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Address" type="{http://ESBLibs/CBOs}AddressCBO" minOccurs="0"/&gt;
 *         &lt;element name="PaymentProfile" type="{http://ESBLibs/CBOs}PaymentProfileCBO" minOccurs="0"/&gt;
 *         &lt;element name="ThaiMiddleName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BillLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillPeriod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VipCode" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="RateClassDefault" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="CollectionStatus" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="SalesCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillingServerId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="TAXExemp" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="MarketCode" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="CustomerServiceCenter" type="{http://ESBLibs/CBOs}CustomerServiceCenterCBO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AccountStatus" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="DateActive" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ExrateClass" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="BillFmtOpt" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="BillDispMeth" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="AccountStatusDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="AccountBalanceSummary" type="{http://ESBLibs/CBOs}AccountBalanceSummaryCBO" minOccurs="0"/&gt;
 *         &lt;element name="GovernmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GovernmentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Billable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DealerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreditLimit" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Attention" type="{http://ESBLibs/CBOs}AttentionCBO" minOccurs="0"/&gt;
 *         &lt;element name="BankName" type="{http://ESBLibs/CBOs}BankNameCBO" minOccurs="0"/&gt;
 *         &lt;element name="BankAccountNo" type="{http://ESBLibs/CBOs}BankAccountNoCBO" minOccurs="0"/&gt;
 *         &lt;element name="BankAccountName" type="{http://ESBLibs/CBOs}BankAccountNameCBO" minOccurs="0"/&gt;
 *         &lt;element name="BankAddress" type="{http://ESBLibs/CBOs}BankAddressCBO" minOccurs="0"/&gt;
 *         &lt;element name="OwningCostCtr" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InvoiceType" type="{http://ESBLibs/CBOs}InvoiceTypeCBO" minOccurs="0"/&gt;
 *         &lt;element name="DetailOfInvoice" type="{http://ESBLibs/CBOs}DetailOfInvoiceCBO" minOccurs="0"/&gt;
 *         &lt;element name="AccountCode" type="{http://ESBLibs/CBOs}AccountCodeCBO" minOccurs="0"/&gt;
 *         &lt;element name="DZipCode" type="{http://ESBLibs/CBOs}DZipCodeCBO" minOccurs="0"/&gt;
 *         &lt;element name="DState" type="{http://ESBLibs/CBOs}DStateCBO" minOccurs="0"/&gt;
 *         &lt;element name="DCity" type="{http://ESBLibs/CBOs}DCityCBO" minOccurs="0"/&gt;
 *         &lt;element name="DCounty" type="{http://ESBLibs/CBOs}DCountyCBO" minOccurs="0"/&gt;
 *         &lt;element name="DAddress3" type="{http://ESBLibs/CBOs}DAddress3CBO" minOccurs="0"/&gt;
 *         &lt;element name="DAddress2" type="{http://ESBLibs/CBOs}DAddress2CBO" minOccurs="0"/&gt;
 *         &lt;element name="DAddress1" type="{http://ESBLibs/CBOs}DAddress1CBO" minOccurs="0"/&gt;
 *         &lt;element name="DCompanyName" type="{http://ESBLibs/CBOs}DCompanyNameCBO" minOccurs="0"/&gt;
 *         &lt;element name="DLastName" type="{http://ESBLibs/CBOs}DLastNameCBO" minOccurs="0"/&gt;
 *         &lt;element name="DFirstName" type="{http://ESBLibs/CBOs}DFirstNameCBO" minOccurs="0"/&gt;
 *         &lt;element name="DTitle" type="{http://ESBLibs/CBOs}DTitleCBO" minOccurs="0"/&gt;
 *         &lt;element name="DCountry" type="{http://ESBLibs/CBOs}DCountryCBO" minOccurs="0"/&gt;
 *         &lt;element name="VatDisplay" type="{http://ESBLibs/CBOs}VatDisplayCBO" minOccurs="0"/&gt;
 *         &lt;element name="StatementToEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GsoNo" type="{http://ESBLibs/CBOs}GsoNoCBO" minOccurs="0"/&gt;
 *         &lt;element name="PoNo" type="{http://ESBLibs/CBOs}PoNoCBO" minOccurs="0"/&gt;
 *         &lt;element name="BillRemark" type="{http://ESBLibs/CBOs}BillRemarkCBO" minOccurs="0"/&gt;
 *         &lt;element name="CreditTerm" type="{http://ESBLibs/CBOs}CreditTermCBO" minOccurs="0"/&gt;
 *         &lt;element name="CarrierType" type="{http://ESBLibs/CBOs}CarrierTypeCBO" minOccurs="0"/&gt;
 *         &lt;element name="ServiceType" type="{http://ESBLibs/CBOs}ServiceTypeCBO" minOccurs="0"/&gt;
 *         &lt;element name="StartBillDate" type="{http://ESBLibs/CBOs}StartBillDateCBO" minOccurs="0"/&gt;
 *         &lt;element name="StatementParent" type="{http://ESBLibs/CBOs}StatementParentCBO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountCBO", propOrder = {
    "accountType",
    "sourceAccountId",
    "sourceAccountIdType",
    "sourceParentAccountId",
    "sourceParentAccountIdType",
    "destinationAccountId",
    "destinationAccountIdType",
    "destinationParentAccountId",
    "destinationParentAccountIdType",
    "accountCategory",
    "thaiTitle",
    "thaiFirstName",
    "thaiLastName",
    "engTitle",
    "engFirstName",
    "engLastName",
    "registrationType",
    "registrationNumber",
    "registrationNumberIssueBy",
    "registrationNumberExpiryDate",
    "birthDate",
    "nationality",
    "gender",
    "eMailAddress",
    "education",
    "occupation",
    "salary",
    "billCompany",
    "businessType",
    "businessRegistrationNumber",
    "companyTaxId",
    "businessRegistrationNumberIssuedBy",
    "businessRegistrationNumberIssuedDate",
    "contactPerson",
    "contactPersonPosition",
    "contactTelephoneNumber",
    "webSite",
    "address",
    "paymentProfile",
    "thaiMiddleName",
    "billLanguage",
    "currencyCode",
    "billPeriod",
    "vipCode",
    "rateClassDefault",
    "collectionStatus",
    "salesCode",
    "billingServerId",
    "taxExemp",
    "marketCode",
    "customerServiceCenter",
    "accountStatus",
    "dateActive",
    "exrateClass",
    "billFmtOpt",
    "billDispMeth",
    "accountStatusDt",
    "accountBalanceSummary",
    "governmentId",
    "governmentType",
    "cardId",
    "cardType",
    "billable",
    "dealerCode",
    "creditLimit",
    "companyName",
    "attention",
    "bankName",
    "bankAccountNo",
    "bankAccountName",
    "bankAddress",
    "owningCostCtr",
    "employeeID",
    "invoiceType",
    "detailOfInvoice",
    "accountCode",
    "dZipCode",
    "dState",
    "dCity",
    "dCounty",
    "dAddress3",
    "dAddress2",
    "dAddress1",
    "dCompanyName",
    "dLastName",
    "dFirstName",
    "dTitle",
    "dCountry",
    "vatDisplay",
    "statementToEmail",
    "gsoNo",
    "poNo",
    "billRemark",
    "creditTerm",
    "carrierType",
    "serviceType",
    "startBillDate",
    "statementParent"
})
public class AccountCBO {

    @XmlElement(name = "AccountType")
    protected String accountType;
    @XmlElement(name = "SourceAccountId")
    protected String sourceAccountId;
    @XmlElement(name = "SourceAccountIdType")
    protected Short sourceAccountIdType;
    @XmlElement(name = "SourceParentAccountId")
    protected BigInteger sourceParentAccountId;
    @XmlElement(name = "SourceParentAccountIdType")
    protected Short sourceParentAccountIdType;
    @XmlElement(name = "DestinationAccountId")
    protected BigInteger destinationAccountId;
    @XmlElement(name = "DestinationAccountIdType")
    protected String destinationAccountIdType;
    @XmlElement(name = "DestinationParentAccountId")
    protected BigInteger destinationParentAccountId;
    @XmlElement(name = "DestinationParentAccountIdType")
    protected Short destinationParentAccountIdType;
    @XmlElement(name = "AccountCategory")
    protected String accountCategory;
    @XmlElement(name = "ThaiTitle")
    protected String thaiTitle;
    @XmlElement(name = "ThaiFirstName")
    protected String thaiFirstName;
    @XmlElement(name = "ThaiLastName")
    protected String thaiLastName;
    @XmlElement(name = "EngTitle")
    protected String engTitle;
    @XmlElement(name = "EngFirstName")
    protected String engFirstName;
    @XmlElement(name = "EngLastName")
    protected String engLastName;
    @XmlElement(name = "RegistrationType")
    protected String registrationType;
    @XmlElement(name = "RegistrationNumber")
    protected String registrationNumber;
    @XmlElement(name = "RegistrationNumberIssueBy")
    protected String registrationNumberIssueBy;
    @XmlElement(name = "RegistrationNumberExpiryDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar registrationNumberExpiryDate;
    @XmlElement(name = "BirthDate")
    protected BirthDateCBO birthDate;
    @XmlElement(name = "Nationality")
    protected String nationality;
    @XmlElement(name = "Gender")
    protected Short gender;
    protected String eMailAddress;
    @XmlElement(name = "Education")
    protected String education;
    @XmlElement(name = "Occupation")
    protected String occupation;
    @XmlElement(name = "Salary")
    protected BigInteger salary;
    @XmlElement(name = "BillCompany")
    protected String billCompany;
    @XmlElement(name = "BusinessType")
    protected String businessType;
    @XmlElement(name = "BusinessRegistrationNumber")
    protected String businessRegistrationNumber;
    @XmlElement(name = "CompanyTaxId")
    protected String companyTaxId;
    @XmlElement(name = "BusinessRegistrationNumberIssuedBy")
    protected String businessRegistrationNumberIssuedBy;
    @XmlElement(name = "BusinessRegistrationNumberIssuedDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar businessRegistrationNumberIssuedDate;
    @XmlElement(name = "ContactPerson")
    protected String contactPerson;
    @XmlElement(name = "ContactPersonPosition")
    protected String contactPersonPosition;
    @XmlElement(name = "ContactTelephoneNumber")
    protected String contactTelephoneNumber;
    @XmlElement(name = "WebSite")
    protected String webSite;
    @XmlElement(name = "Address")
    protected AddressCBO address;
    @XmlElement(name = "PaymentProfile")
    protected PaymentProfileCBO paymentProfile;
    @XmlElement(name = "ThaiMiddleName")
    protected String thaiMiddleName;
    @XmlElement(name = "BillLanguage")
    protected String billLanguage;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "BillPeriod")
    protected String billPeriod;
    @XmlElement(name = "VipCode")
    protected Short vipCode;
    @XmlElement(name = "RateClassDefault")
    protected Short rateClassDefault;
    @XmlElement(name = "CollectionStatus")
    protected Short collectionStatus;
    @XmlElement(name = "SalesCode")
    protected String salesCode;
    @XmlElement(name = "BillingServerId")
    protected BigInteger billingServerId;
    @XmlElement(name = "TAXExemp")
    protected Short taxExemp;
    @XmlElement(name = "MarketCode")
    protected Short marketCode;
    @XmlElement(name = "CustomerServiceCenter")
    protected List<CustomerServiceCenterCBO> customerServiceCenter;
    @XmlElement(name = "AccountStatus")
    protected Short accountStatus;
    @XmlElement(name = "DateActive")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateActive;
    @XmlElement(name = "ExrateClass")
    protected Short exrateClass;
    @XmlElement(name = "BillFmtOpt")
    protected BigInteger billFmtOpt;
    @XmlElement(name = "BillDispMeth")
    protected Short billDispMeth;
    @XmlElement(name = "AccountStatusDt")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar accountStatusDt;
    @XmlElement(name = "AccountBalanceSummary")
    protected AccountBalanceSummaryCBO accountBalanceSummary;
    @XmlElement(name = "GovernmentId")
    protected String governmentId;
    @XmlElement(name = "GovernmentType")
    protected String governmentType;
    @XmlElement(name = "CardId")
    protected String cardId;
    @XmlElement(name = "CardType")
    protected String cardType;
    @XmlElement(name = "Billable")
    protected Boolean billable;
    @XmlElement(name = "DealerCode")
    protected String dealerCode;
    @XmlElement(name = "CreditLimit")
    protected String creditLimit;
    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "Attention")
    protected AttentionCBO attention;
    @XmlElement(name = "BankName")
    protected BankNameCBO bankName;
    @XmlElement(name = "BankAccountNo")
    protected BankAccountNoCBO bankAccountNo;
    @XmlElement(name = "BankAccountName")
    protected BankAccountNameCBO bankAccountName;
    @XmlElement(name = "BankAddress")
    protected BankAddressCBO bankAddress;
    @XmlElement(name = "OwningCostCtr")
    protected Short owningCostCtr;
    @XmlElement(name = "EmployeeID")
    protected String employeeID;
    @XmlElement(name = "InvoiceType")
    protected InvoiceTypeCBO invoiceType;
    @XmlElement(name = "DetailOfInvoice")
    protected DetailOfInvoiceCBO detailOfInvoice;
    @XmlElement(name = "AccountCode")
    protected AccountCodeCBO accountCode;
    @XmlElement(name = "DZipCode")
    protected DZipCodeCBO dZipCode;
    @XmlElement(name = "DState")
    protected DStateCBO dState;
    @XmlElement(name = "DCity")
    protected DCityCBO dCity;
    @XmlElement(name = "DCounty")
    protected DCountyCBO dCounty;
    @XmlElement(name = "DAddress3")
    protected DAddress3CBO dAddress3;
    @XmlElement(name = "DAddress2")
    protected DAddress2CBO dAddress2;
    @XmlElement(name = "DAddress1")
    protected DAddress1CBO dAddress1;
    @XmlElement(name = "DCompanyName")
    protected DCompanyNameCBO dCompanyName;
    @XmlElement(name = "DLastName")
    protected DLastNameCBO dLastName;
    @XmlElement(name = "DFirstName")
    protected DFirstNameCBO dFirstName;
    @XmlElement(name = "DTitle")
    protected DTitleCBO dTitle;
    @XmlElement(name = "DCountry")
    protected DCountryCBO dCountry;
    @XmlElement(name = "VatDisplay")
    protected VatDisplayCBO vatDisplay;
    @XmlElement(name = "StatementToEmail")
    protected String statementToEmail;
    @XmlElement(name = "GsoNo")
    protected GsoNoCBO gsoNo;
    @XmlElement(name = "PoNo")
    protected PoNoCBO poNo;
    @XmlElement(name = "BillRemark")
    protected BillRemarkCBO billRemark;
    @XmlElement(name = "CreditTerm")
    protected CreditTermCBO creditTerm;
    @XmlElement(name = "CarrierType")
    protected CarrierTypeCBO carrierType;
    @XmlElement(name = "ServiceType")
    protected ServiceTypeCBO serviceType;
    @XmlElement(name = "StartBillDate")
    protected StartBillDateCBO startBillDate;
    @XmlElement(name = "StatementParent")
    protected StatementParentCBO statementParent;

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the sourceAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceAccountId() {
        return sourceAccountId;
    }

    /**
     * Sets the value of the sourceAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceAccountId(String value) {
        this.sourceAccountId = value;
    }

    /**
     * Gets the value of the sourceAccountIdType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getSourceAccountIdType() {
        return sourceAccountIdType;
    }

    /**
     * Sets the value of the sourceAccountIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setSourceAccountIdType(Short value) {
        this.sourceAccountIdType = value;
    }

    /**
     * Gets the value of the sourceParentAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSourceParentAccountId() {
        return sourceParentAccountId;
    }

    /**
     * Sets the value of the sourceParentAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSourceParentAccountId(BigInteger value) {
        this.sourceParentAccountId = value;
    }

    /**
     * Gets the value of the sourceParentAccountIdType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getSourceParentAccountIdType() {
        return sourceParentAccountIdType;
    }

    /**
     * Sets the value of the sourceParentAccountIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setSourceParentAccountIdType(Short value) {
        this.sourceParentAccountIdType = value;
    }

    /**
     * Gets the value of the destinationAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDestinationAccountId() {
        return destinationAccountId;
    }

    /**
     * Sets the value of the destinationAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDestinationAccountId(BigInteger value) {
        this.destinationAccountId = value;
    }

    /**
     * Gets the value of the destinationAccountIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationAccountIdType() {
        return destinationAccountIdType;
    }

    /**
     * Sets the value of the destinationAccountIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationAccountIdType(String value) {
        this.destinationAccountIdType = value;
    }

    /**
     * Gets the value of the destinationParentAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDestinationParentAccountId() {
        return destinationParentAccountId;
    }

    /**
     * Sets the value of the destinationParentAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDestinationParentAccountId(BigInteger value) {
        this.destinationParentAccountId = value;
    }

    /**
     * Gets the value of the destinationParentAccountIdType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getDestinationParentAccountIdType() {
        return destinationParentAccountIdType;
    }

    /**
     * Sets the value of the destinationParentAccountIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setDestinationParentAccountIdType(Short value) {
        this.destinationParentAccountIdType = value;
    }

    /**
     * Gets the value of the accountCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCategory() {
        return accountCategory;
    }

    /**
     * Sets the value of the accountCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCategory(String value) {
        this.accountCategory = value;
    }

    /**
     * Gets the value of the thaiTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThaiTitle() {
        return thaiTitle;
    }

    /**
     * Sets the value of the thaiTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThaiTitle(String value) {
        this.thaiTitle = value;
    }

    /**
     * Gets the value of the thaiFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThaiFirstName() {
        return thaiFirstName;
    }

    /**
     * Sets the value of the thaiFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThaiFirstName(String value) {
        this.thaiFirstName = value;
    }

    /**
     * Gets the value of the thaiLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThaiLastName() {
        return thaiLastName;
    }

    /**
     * Sets the value of the thaiLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThaiLastName(String value) {
        this.thaiLastName = value;
    }

    /**
     * Gets the value of the engTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEngTitle() {
        return engTitle;
    }

    /**
     * Sets the value of the engTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEngTitle(String value) {
        this.engTitle = value;
    }

    /**
     * Gets the value of the engFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEngFirstName() {
        return engFirstName;
    }

    /**
     * Sets the value of the engFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEngFirstName(String value) {
        this.engFirstName = value;
    }

    /**
     * Gets the value of the engLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEngLastName() {
        return engLastName;
    }

    /**
     * Sets the value of the engLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEngLastName(String value) {
        this.engLastName = value;
    }

    /**
     * Gets the value of the registrationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationType() {
        return registrationType;
    }

    /**
     * Sets the value of the registrationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationType(String value) {
        this.registrationType = value;
    }

    /**
     * Gets the value of the registrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the value of the registrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationNumber(String value) {
        this.registrationNumber = value;
    }

    /**
     * Gets the value of the registrationNumberIssueBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationNumberIssueBy() {
        return registrationNumberIssueBy;
    }

    /**
     * Sets the value of the registrationNumberIssueBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationNumberIssueBy(String value) {
        this.registrationNumberIssueBy = value;
    }

    /**
     * Gets the value of the registrationNumberExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegistrationNumberExpiryDate() {
        return registrationNumberExpiryDate;
    }

    /**
     * Sets the value of the registrationNumberExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegistrationNumberExpiryDate(XMLGregorianCalendar value) {
        this.registrationNumberExpiryDate = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link BirthDateCBO }
     *     
     */
    public BirthDateCBO getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BirthDateCBO }
     *     
     */
    public void setBirthDate(BirthDateCBO value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setGender(Short value) {
        this.gender = value;
    }

    /**
     * Gets the value of the eMailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMailAddress() {
        return eMailAddress;
    }

    /**
     * Sets the value of the eMailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMailAddress(String value) {
        this.eMailAddress = value;
    }

    /**
     * Gets the value of the education property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEducation() {
        return education;
    }

    /**
     * Sets the value of the education property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEducation(String value) {
        this.education = value;
    }

    /**
     * Gets the value of the occupation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the value of the occupation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupation(String value) {
        this.occupation = value;
    }

    /**
     * Gets the value of the salary property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSalary() {
        return salary;
    }

    /**
     * Sets the value of the salary property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSalary(BigInteger value) {
        this.salary = value;
    }

    /**
     * Gets the value of the billCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCompany() {
        return billCompany;
    }

    /**
     * Sets the value of the billCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCompany(String value) {
        this.billCompany = value;
    }

    /**
     * Gets the value of the businessType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * Sets the value of the businessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessType(String value) {
        this.businessType = value;
    }

    /**
     * Gets the value of the businessRegistrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    /**
     * Sets the value of the businessRegistrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessRegistrationNumber(String value) {
        this.businessRegistrationNumber = value;
    }

    /**
     * Gets the value of the companyTaxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyTaxId() {
        return companyTaxId;
    }

    /**
     * Sets the value of the companyTaxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyTaxId(String value) {
        this.companyTaxId = value;
    }

    /**
     * Gets the value of the businessRegistrationNumberIssuedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessRegistrationNumberIssuedBy() {
        return businessRegistrationNumberIssuedBy;
    }

    /**
     * Sets the value of the businessRegistrationNumberIssuedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessRegistrationNumberIssuedBy(String value) {
        this.businessRegistrationNumberIssuedBy = value;
    }

    /**
     * Gets the value of the businessRegistrationNumberIssuedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBusinessRegistrationNumberIssuedDate() {
        return businessRegistrationNumberIssuedDate;
    }

    /**
     * Sets the value of the businessRegistrationNumberIssuedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBusinessRegistrationNumberIssuedDate(XMLGregorianCalendar value) {
        this.businessRegistrationNumberIssuedDate = value;
    }

    /**
     * Gets the value of the contactPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * Sets the value of the contactPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPerson(String value) {
        this.contactPerson = value;
    }

    /**
     * Gets the value of the contactPersonPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPersonPosition() {
        return contactPersonPosition;
    }

    /**
     * Sets the value of the contactPersonPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPersonPosition(String value) {
        this.contactPersonPosition = value;
    }

    /**
     * Gets the value of the contactTelephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactTelephoneNumber() {
        return contactTelephoneNumber;
    }

    /**
     * Sets the value of the contactTelephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactTelephoneNumber(String value) {
        this.contactTelephoneNumber = value;
    }

    /**
     * Gets the value of the webSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Sets the value of the webSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebSite(String value) {
        this.webSite = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressCBO }
     *     
     */
    public AddressCBO getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressCBO }
     *     
     */
    public void setAddress(AddressCBO value) {
        this.address = value;
    }

    /**
     * Gets the value of the paymentProfile property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentProfileCBO }
     *     
     */
    public PaymentProfileCBO getPaymentProfile() {
        return paymentProfile;
    }

    /**
     * Sets the value of the paymentProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentProfileCBO }
     *     
     */
    public void setPaymentProfile(PaymentProfileCBO value) {
        this.paymentProfile = value;
    }

    /**
     * Gets the value of the thaiMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThaiMiddleName() {
        return thaiMiddleName;
    }

    /**
     * Sets the value of the thaiMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThaiMiddleName(String value) {
        this.thaiMiddleName = value;
    }

    /**
     * Gets the value of the billLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillLanguage() {
        return billLanguage;
    }

    /**
     * Sets the value of the billLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillLanguage(String value) {
        this.billLanguage = value;
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
     * Gets the value of the vipCode property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getVipCode() {
        return vipCode;
    }

    /**
     * Sets the value of the vipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setVipCode(Short value) {
        this.vipCode = value;
    }

    /**
     * Gets the value of the rateClassDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getRateClassDefault() {
        return rateClassDefault;
    }

    /**
     * Sets the value of the rateClassDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setRateClassDefault(Short value) {
        this.rateClassDefault = value;
    }

    /**
     * Gets the value of the collectionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCollectionStatus() {
        return collectionStatus;
    }

    /**
     * Sets the value of the collectionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCollectionStatus(Short value) {
        this.collectionStatus = value;
    }

    /**
     * Gets the value of the salesCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesCode() {
        return salesCode;
    }

    /**
     * Sets the value of the salesCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesCode(String value) {
        this.salesCode = value;
    }

    /**
     * Gets the value of the billingServerId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillingServerId() {
        return billingServerId;
    }

    /**
     * Sets the value of the billingServerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillingServerId(BigInteger value) {
        this.billingServerId = value;
    }

    /**
     * Gets the value of the taxExemp property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTAXExemp() {
        return taxExemp;
    }

    /**
     * Sets the value of the taxExemp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTAXExemp(Short value) {
        this.taxExemp = value;
    }

    /**
     * Gets the value of the marketCode property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMarketCode() {
        return marketCode;
    }

    /**
     * Sets the value of the marketCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMarketCode(Short value) {
        this.marketCode = value;
    }

    /**
     * Gets the value of the customerServiceCenter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerServiceCenter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerServiceCenter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerServiceCenterCBO }
     * 
     * 
     */
    public List<CustomerServiceCenterCBO> getCustomerServiceCenter() {
        if (customerServiceCenter == null) {
            customerServiceCenter = new ArrayList<CustomerServiceCenterCBO>();
        }
        return this.customerServiceCenter;
    }

    /**
     * Gets the value of the accountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the value of the accountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setAccountStatus(Short value) {
        this.accountStatus = value;
    }

    /**
     * Gets the value of the dateActive property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateActive() {
        return dateActive;
    }

    /**
     * Sets the value of the dateActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateActive(XMLGregorianCalendar value) {
        this.dateActive = value;
    }

    /**
     * Gets the value of the exrateClass property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getExrateClass() {
        return exrateClass;
    }

    /**
     * Sets the value of the exrateClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setExrateClass(Short value) {
        this.exrateClass = value;
    }

    /**
     * Gets the value of the billFmtOpt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillFmtOpt() {
        return billFmtOpt;
    }

    /**
     * Sets the value of the billFmtOpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillFmtOpt(BigInteger value) {
        this.billFmtOpt = value;
    }

    /**
     * Gets the value of the billDispMeth property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getBillDispMeth() {
        return billDispMeth;
    }

    /**
     * Sets the value of the billDispMeth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setBillDispMeth(Short value) {
        this.billDispMeth = value;
    }

    /**
     * Gets the value of the accountStatusDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAccountStatusDt() {
        return accountStatusDt;
    }

    /**
     * Sets the value of the accountStatusDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAccountStatusDt(XMLGregorianCalendar value) {
        this.accountStatusDt = value;
    }

    /**
     * Gets the value of the accountBalanceSummary property.
     * 
     * @return
     *     possible object is
     *     {@link AccountBalanceSummaryCBO }
     *     
     */
    public AccountBalanceSummaryCBO getAccountBalanceSummary() {
        return accountBalanceSummary;
    }

    /**
     * Sets the value of the accountBalanceSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountBalanceSummaryCBO }
     *     
     */
    public void setAccountBalanceSummary(AccountBalanceSummaryCBO value) {
        this.accountBalanceSummary = value;
    }

    /**
     * Gets the value of the governmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovernmentId() {
        return governmentId;
    }

    /**
     * Sets the value of the governmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovernmentId(String value) {
        this.governmentId = value;
    }

    /**
     * Gets the value of the governmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovernmentType() {
        return governmentType;
    }

    /**
     * Sets the value of the governmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovernmentType(String value) {
        this.governmentType = value;
    }

    /**
     * Gets the value of the cardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Sets the value of the cardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardId(String value) {
        this.cardId = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the billable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBillable() {
        return billable;
    }

    /**
     * Sets the value of the billable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBillable(Boolean value) {
        this.billable = value;
    }

    /**
     * Gets the value of the dealerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealerCode() {
        return dealerCode;
    }

    /**
     * Sets the value of the dealerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealerCode(String value) {
        this.dealerCode = value;
    }

    /**
     * Gets the value of the creditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditLimit() {
        return creditLimit;
    }

    /**
     * Sets the value of the creditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditLimit(String value) {
        this.creditLimit = value;
    }

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the attention property.
     * 
     * @return
     *     possible object is
     *     {@link AttentionCBO }
     *     
     */
    public AttentionCBO getAttention() {
        return attention;
    }

    /**
     * Sets the value of the attention property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttentionCBO }
     *     
     */
    public void setAttention(AttentionCBO value) {
        this.attention = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link BankNameCBO }
     *     
     */
    public BankNameCBO getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankNameCBO }
     *     
     */
    public void setBankName(BankNameCBO value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the bankAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link BankAccountNoCBO }
     *     
     */
    public BankAccountNoCBO getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * Sets the value of the bankAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAccountNoCBO }
     *     
     */
    public void setBankAccountNo(BankAccountNoCBO value) {
        this.bankAccountNo = value;
    }

    /**
     * Gets the value of the bankAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link BankAccountNameCBO }
     *     
     */
    public BankAccountNameCBO getBankAccountName() {
        return bankAccountName;
    }

    /**
     * Sets the value of the bankAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAccountNameCBO }
     *     
     */
    public void setBankAccountName(BankAccountNameCBO value) {
        this.bankAccountName = value;
    }

    /**
     * Gets the value of the bankAddress property.
     * 
     * @return
     *     possible object is
     *     {@link BankAddressCBO }
     *     
     */
    public BankAddressCBO getBankAddress() {
        return bankAddress;
    }

    /**
     * Sets the value of the bankAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAddressCBO }
     *     
     */
    public void setBankAddress(BankAddressCBO value) {
        this.bankAddress = value;
    }

    /**
     * Gets the value of the owningCostCtr property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getOwningCostCtr() {
        return owningCostCtr;
    }

    /**
     * Sets the value of the owningCostCtr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setOwningCostCtr(Short value) {
        this.owningCostCtr = value;
    }

    /**
     * Gets the value of the employeeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeID(String value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the invoiceType property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceTypeCBO }
     *     
     */
    public InvoiceTypeCBO getInvoiceType() {
        return invoiceType;
    }

    /**
     * Sets the value of the invoiceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceTypeCBO }
     *     
     */
    public void setInvoiceType(InvoiceTypeCBO value) {
        this.invoiceType = value;
    }

    /**
     * Gets the value of the detailOfInvoice property.
     * 
     * @return
     *     possible object is
     *     {@link DetailOfInvoiceCBO }
     *     
     */
    public DetailOfInvoiceCBO getDetailOfInvoice() {
        return detailOfInvoice;
    }

    /**
     * Sets the value of the detailOfInvoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailOfInvoiceCBO }
     *     
     */
    public void setDetailOfInvoice(DetailOfInvoiceCBO value) {
        this.detailOfInvoice = value;
    }

    /**
     * Gets the value of the accountCode property.
     * 
     * @return
     *     possible object is
     *     {@link AccountCodeCBO }
     *     
     */
    public AccountCodeCBO getAccountCode() {
        return accountCode;
    }

    /**
     * Sets the value of the accountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountCodeCBO }
     *     
     */
    public void setAccountCode(AccountCodeCBO value) {
        this.accountCode = value;
    }

    /**
     * Gets the value of the dZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link DZipCodeCBO }
     *     
     */
    public DZipCodeCBO getDZipCode() {
        return dZipCode;
    }

    /**
     * Sets the value of the dZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DZipCodeCBO }
     *     
     */
    public void setDZipCode(DZipCodeCBO value) {
        this.dZipCode = value;
    }

    /**
     * Gets the value of the dState property.
     * 
     * @return
     *     possible object is
     *     {@link DStateCBO }
     *     
     */
    public DStateCBO getDState() {
        return dState;
    }

    /**
     * Sets the value of the dState property.
     * 
     * @param value
     *     allowed object is
     *     {@link DStateCBO }
     *     
     */
    public void setDState(DStateCBO value) {
        this.dState = value;
    }

    /**
     * Gets the value of the dCity property.
     * 
     * @return
     *     possible object is
     *     {@link DCityCBO }
     *     
     */
    public DCityCBO getDCity() {
        return dCity;
    }

    /**
     * Sets the value of the dCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link DCityCBO }
     *     
     */
    public void setDCity(DCityCBO value) {
        this.dCity = value;
    }

    /**
     * Gets the value of the dCounty property.
     * 
     * @return
     *     possible object is
     *     {@link DCountyCBO }
     *     
     */
    public DCountyCBO getDCounty() {
        return dCounty;
    }

    /**
     * Sets the value of the dCounty property.
     * 
     * @param value
     *     allowed object is
     *     {@link DCountyCBO }
     *     
     */
    public void setDCounty(DCountyCBO value) {
        this.dCounty = value;
    }

    /**
     * Gets the value of the dAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link DAddress3CBO }
     *     
     */
    public DAddress3CBO getDAddress3() {
        return dAddress3;
    }

    /**
     * Sets the value of the dAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DAddress3CBO }
     *     
     */
    public void setDAddress3(DAddress3CBO value) {
        this.dAddress3 = value;
    }

    /**
     * Gets the value of the dAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link DAddress2CBO }
     *     
     */
    public DAddress2CBO getDAddress2() {
        return dAddress2;
    }

    /**
     * Sets the value of the dAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DAddress2CBO }
     *     
     */
    public void setDAddress2(DAddress2CBO value) {
        this.dAddress2 = value;
    }

    /**
     * Gets the value of the dAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link DAddress1CBO }
     *     
     */
    public DAddress1CBO getDAddress1() {
        return dAddress1;
    }

    /**
     * Sets the value of the dAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DAddress1CBO }
     *     
     */
    public void setDAddress1(DAddress1CBO value) {
        this.dAddress1 = value;
    }

    /**
     * Gets the value of the dCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link DCompanyNameCBO }
     *     
     */
    public DCompanyNameCBO getDCompanyName() {
        return dCompanyName;
    }

    /**
     * Sets the value of the dCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link DCompanyNameCBO }
     *     
     */
    public void setDCompanyName(DCompanyNameCBO value) {
        this.dCompanyName = value;
    }

    /**
     * Gets the value of the dLastName property.
     * 
     * @return
     *     possible object is
     *     {@link DLastNameCBO }
     *     
     */
    public DLastNameCBO getDLastName() {
        return dLastName;
    }

    /**
     * Sets the value of the dLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link DLastNameCBO }
     *     
     */
    public void setDLastName(DLastNameCBO value) {
        this.dLastName = value;
    }

    /**
     * Gets the value of the dFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link DFirstNameCBO }
     *     
     */
    public DFirstNameCBO getDFirstName() {
        return dFirstName;
    }

    /**
     * Sets the value of the dFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link DFirstNameCBO }
     *     
     */
    public void setDFirstName(DFirstNameCBO value) {
        this.dFirstName = value;
    }

    /**
     * Gets the value of the dTitle property.
     * 
     * @return
     *     possible object is
     *     {@link DTitleCBO }
     *     
     */
    public DTitleCBO getDTitle() {
        return dTitle;
    }

    /**
     * Sets the value of the dTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link DTitleCBO }
     *     
     */
    public void setDTitle(DTitleCBO value) {
        this.dTitle = value;
    }

    /**
     * Gets the value of the dCountry property.
     * 
     * @return
     *     possible object is
     *     {@link DCountryCBO }
     *     
     */
    public DCountryCBO getDCountry() {
        return dCountry;
    }

    /**
     * Sets the value of the dCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link DCountryCBO }
     *     
     */
    public void setDCountry(DCountryCBO value) {
        this.dCountry = value;
    }

    /**
     * Gets the value of the vatDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link VatDisplayCBO }
     *     
     */
    public VatDisplayCBO getVatDisplay() {
        return vatDisplay;
    }

    /**
     * Sets the value of the vatDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link VatDisplayCBO }
     *     
     */
    public void setVatDisplay(VatDisplayCBO value) {
        this.vatDisplay = value;
    }

    /**
     * Gets the value of the statementToEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatementToEmail() {
        return statementToEmail;
    }

    /**
     * Sets the value of the statementToEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatementToEmail(String value) {
        this.statementToEmail = value;
    }

    /**
     * Gets the value of the gsoNo property.
     * 
     * @return
     *     possible object is
     *     {@link GsoNoCBO }
     *     
     */
    public GsoNoCBO getGsoNo() {
        return gsoNo;
    }

    /**
     * Sets the value of the gsoNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GsoNoCBO }
     *     
     */
    public void setGsoNo(GsoNoCBO value) {
        this.gsoNo = value;
    }

    /**
     * Gets the value of the poNo property.
     * 
     * @return
     *     possible object is
     *     {@link PoNoCBO }
     *     
     */
    public PoNoCBO getPoNo() {
        return poNo;
    }

    /**
     * Sets the value of the poNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoNoCBO }
     *     
     */
    public void setPoNo(PoNoCBO value) {
        this.poNo = value;
    }

    /**
     * Gets the value of the billRemark property.
     * 
     * @return
     *     possible object is
     *     {@link BillRemarkCBO }
     *     
     */
    public BillRemarkCBO getBillRemark() {
        return billRemark;
    }

    /**
     * Sets the value of the billRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillRemarkCBO }
     *     
     */
    public void setBillRemark(BillRemarkCBO value) {
        this.billRemark = value;
    }

    /**
     * Gets the value of the creditTerm property.
     * 
     * @return
     *     possible object is
     *     {@link CreditTermCBO }
     *     
     */
    public CreditTermCBO getCreditTerm() {
        return creditTerm;
    }

    /**
     * Sets the value of the creditTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditTermCBO }
     *     
     */
    public void setCreditTerm(CreditTermCBO value) {
        this.creditTerm = value;
    }

    /**
     * Gets the value of the carrierType property.
     * 
     * @return
     *     possible object is
     *     {@link CarrierTypeCBO }
     *     
     */
    public CarrierTypeCBO getCarrierType() {
        return carrierType;
    }

    /**
     * Sets the value of the carrierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarrierTypeCBO }
     *     
     */
    public void setCarrierType(CarrierTypeCBO value) {
        this.carrierType = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTypeCBO }
     *     
     */
    public ServiceTypeCBO getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTypeCBO }
     *     
     */
    public void setServiceType(ServiceTypeCBO value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the startBillDate property.
     * 
     * @return
     *     possible object is
     *     {@link StartBillDateCBO }
     *     
     */
    public StartBillDateCBO getStartBillDate() {
        return startBillDate;
    }

    /**
     * Sets the value of the startBillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link StartBillDateCBO }
     *     
     */
    public void setStartBillDate(StartBillDateCBO value) {
        this.startBillDate = value;
    }

    /**
     * Gets the value of the statementParent property.
     * 
     * @return
     *     possible object is
     *     {@link StatementParentCBO }
     *     
     */
    public StatementParentCBO getStatementParent() {
        return statementParent;
    }

    /**
     * Sets the value of the statementParent property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatementParentCBO }
     *     
     */
    public void setStatementParent(StatementParentCBO value) {
        this.statementParent = value;
    }

}

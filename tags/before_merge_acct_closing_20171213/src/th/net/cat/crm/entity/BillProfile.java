package th.net.cat.crm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="V_CATCRM_BILLING_PROFILE" ,schema = "CRMDATA")
public class BillProfile {

	@Id
	@Column(name="CAT_BILL_ACCT_ID") private String id;
	@Column(name="CAT_BILL_ACCT_NUMBER") private String no;
	@Column(name="TAX_REGISTER_NUMBER") private String taxRegisterNo;
	@Column(name="BRANCH_ID") private String branchId;
	@Column(name="BILL_FIRST_NAME") private String billFirstName;
	@Column(name="BILL_LAST_NAME") private String billLastName;
	@Column(name="BILL_COMP_NAME") private String billCompName;
	@Column(name="BILLING_GROUP") private String billGroup;
	@Column(name="BILLING_GROUP_FULL") private String billGroupFull;
	@Column(name="COLLECTION_UNIT_DESC") private String collectionUnit;
	@Column(name="CUSTOMER_ACCOUNT_NAME") private String customerAccountName;
	@Column(name="CUSTOMER_TYPE") private String type;
	@Column(name="VAT_TYPE") private String vatType;
	@Column(name="CURRENCY") private String currency;
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="CUSTOMER_ID") private CustomerProfile customer;
	@Column(name="ACCT_CAT_LKP") private String accountCategoryLookup;
	@Column(name="EGP_NUMBER") private String egpNumber;
	@Column(name="EGP_CONTACT") private String egpContact;
	@Column(name="CAT_CUSTOMER_SEGMENT") private String catCustomerSegment;//by  NSD 02-03-2017
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTaxRegisterNo() {
		return taxRegisterNo;
	}
	public void setTaxRegisterNo(String taxRegisterNo) {
		this.taxRegisterNo = taxRegisterNo;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBillFirstName() {
		return billFirstName;
	}
	public void setBillFirstName(String billFirstName) {
		this.billFirstName = billFirstName;
	}
	public String getBillLastName() {
		return billLastName;
	}
	public void setBillLastName(String billLastName) {
		this.billLastName = billLastName;
	}
	public String getBillCompName() {
		return billCompName;
	}
	public void setBillCompName(String billCompName) {
		this.billCompName = billCompName;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public String getBillGroupFull() {
		return billGroupFull;
	}
	public void setBillGroupFull(String billGroupFull) {
		this.billGroupFull = billGroupFull;
	}
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}
	public String getCustomerAccountName() {
		return customerAccountName;
	}
	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVatType() {
		return vatType;
	}
	public void setVatType(String vatType) {
		this.vatType = vatType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public CustomerProfile getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerProfile customer) {
		this.customer = customer;
	}
	public String getAccountCategoryLookup() {
		return accountCategoryLookup;
	}
	public void setAccountCategoryLookup(String accountCategoryLookup) {
		this.accountCategoryLookup = accountCategoryLookup;
	}
	public String getEgpNumber() {
		return egpNumber;
	}
	public void setEgpNumber(String egpNumber) {
		this.egpNumber = egpNumber;
	}
	public String getEgpContact() {
		return egpContact;
	}
	public void setEgpContact(String egpContact) {
		this.egpContact = egpContact;
	}

	public String getCatCustomerSegment() {
		return catCustomerSegment;
	}

	public void setCatCustomerSegment(String catCustomerSegment) {
		this.catCustomerSegment = catCustomerSegment;
	}
}
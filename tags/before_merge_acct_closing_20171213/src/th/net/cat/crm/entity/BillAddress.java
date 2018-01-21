package th.net.cat.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_CATCRM_BILLING_ADDRESS",schema = "CRMDATA")
public class BillAddress {

	@Id
	@Column(name="CAT_BILL_ACCT_ID") private String id;
	@Column(name="CAT_BILL_ACCT_NUMBER") private String no;
	@Column(name="TAX_REGISTER_NUMBER") private String taxRegisterNo;
	@Column(name="BRANCH_ID") private String branchId;
	@Column(name="BILL_SALUTATION") private String billSalutation;
	@Column(name="BILL_FIRST_NAME") private String billFirstName;
	@Column(name="BILL_LAST_NAME") private String billLastName;
	@Column(name="BILL_COMP_SALUTATION") private String billCompSalutation;
	@Column(name="BILL_COMP_NAME") private String billCompName;
	@Column(name="BILL_ADDR_LINE1") private String billAddrLine1;
	@Column(name="BILL_ADDR_LINE2") private String billAddrLine2;
	@Column(name="BILL_ADDR_LINE3") private String billAddrLine3;
	@Column(name="BILL_ADDR_LINE4") private String billAddrLine4;
	@Column(name="BILL_ADDR_KHET_AMPHUR") private String billKhetAmphur;
	@Column(name="BILL_ADDR_PROVINCE_LKP") private String billProvince;
	@Column(name="BILL_ADDR_POST_CODE") private String billPostCode;
	@Column(name="BILL_ADDR_COUNTRY_LKP") private String billCountry;
	@Column(name="VAT_SALUTATION") private String vatSalutation;
	@Column(name="VAT_FIRST_NAME") private String vatFirstName;
	@Column(name="VAT_LAST_NAME") private String vatLastName;
	@Column(name="VAT_COMP_SALUTATION") private String vatCompSalutation;
	@Column(name="VAT_COMP_NAME") private String vatCompName;
	@Column(name="VAT_ADDR_LINE1") private String vatAddrLine1;
	@Column(name="VAT_ADDR_LINE2") private String vatAddrLine2;
	@Column(name="VAT_ADDR_LINE3") private String vatAddrLine3;
	@Column(name="VAT_ADDR_LINE4") private String vatAddrLine4;
	@Column(name="VAT_ADDR_KHET_AMPHUR") private String vatKhetAmphur;
	@Column(name="VAT_ADDR_PROVINCE_LKP") private String vatProvince;
	@Column(name="VAT_ADDR_POST_CODE") private String vatPostCode;
	@Column(name="VAT_ADDR_COUNTRY_LKP") private String vatCountry;
	@Column(name="DELI_SALUTATION") private String deliSalutation;
	@Column(name="DELI_FIRST_NAME") private String deliFirstName;
	@Column(name="DELI_LAST_NAME") private String deliLastName;
	@Column(name="DELI_COMP_SALUTATION") private String deliCompSalutation;
	@Column(name="DELI_COMP_NAME") private String deliCompName;
	@Column(name="DELI_ADDR_LINE1") private String deliAddrLine1;
	@Column(name="DELI_ADDR_LINE2") private String deliAddrLine2;
	@Column(name="DELI_ADDR_LINE3") private String deliAddrLine3;
	@Column(name="DELI_ADDR_LINE4") private String deliAddrLine4;
	@Column(name="DELI_ADDR_KHET_AMPHUR") private String deliKhetAmphur;
	@Column(name="DELI_ADDR_PROVINCE_LKP") private String deliProvince;
	@Column(name="DELI_ADDR_POST_CODE") private String deliPostCode;
	@Column(name="DELI_ADDR_COUNTRY_LKP") private String deliCountry;
	@Column(name="DELI_VAT_SALUTATION") private String deliVatSalutation;
	@Column(name="DELI_VAT_FIRST_NAME") private String deliVatFirstName;
	@Column(name="DELI_VAT_LAST_NAME") private String deliVatLastName;
	@Column(name="DELI_VAT_COMP_SALUTATION") private String deliVatCompSalutation;
	@Column(name="DELI_VAT_COMP_NAME") private String deliVatCompName;
	@Column(name="DELI_VAT_ADDR_LINE1") private String deliVatAddrLine1;
	@Column(name="DELI_VAT_ADDR_LINE2") private String deliVatAddrLine2;
	@Column(name="DELI_VAT_ADDR_LINE3") private String deliVatAddrLine3;
	@Column(name="DELI_VAT_ADDR_LINE4") private String deliVatAddrLine4;
	@Column(name="DELI_VAT_ADDR_KHET_AMPHUR") private String deliVatKhetAmphur;
	@Column(name="DELI_VAT_ADDR_PROVINCE_LKP") private String deliVatProvince;
	@Column(name="DELI_VAT_ADDR_POST_CODE") private String deliVatPostCode;
	@Column(name="DELI_VAT_ADDR_COUNTRY_LKP") private String deliVatCountry;
	@Column(name="CUSTOMER_ACCOUNT_NAME") private String customerAccountName;
	@Column(name="CUSTOMER_TYPE") private String customerType;
	@Column(name="CUSTOMER_ID") private String customerId;
	@Column(name="PAY_CREDIT_CARD_NUMBER") private String payCreditCardNo;
	@Column(name="PAY_CREDIT_CARD_CARRIER") private String payCreditCardCarrier;
	@Column(name="PAY_BANK_NUMBER") private String payBankNo;
	@Column(name="PAY_BANK_NAME") private String payBankName;
	@Column(name="EMPLOYEE_ID") private String employeeId;
	@Column(name="EMPLOYEE_NAME") private String employeeName;

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
	public String getBillSalutation() {
		return billSalutation;
	}
	public void setBillSalutation(String billSalutation) {
		this.billSalutation = billSalutation;
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
	public String getBillCompSalutation() {
		return billCompSalutation;
	}
	public void setBillCompSalutation(String billCompSalutation) {
		this.billCompSalutation = billCompSalutation;
	}
	public String getBillCompName() {
		return billCompName;
	}
	public void setBillCompName(String billCompName) {
		this.billCompName = billCompName;
	}
	public String getBillAddrLine1() {
		return billAddrLine1;
	}
	public void setBillAddrLine1(String billAddrLine1) {
		this.billAddrLine1 = billAddrLine1;
	}
	public String getBillAddrLine2() {
		return billAddrLine2;
	}
	public void setBillAddrLine2(String billAddrLine2) {
		this.billAddrLine2 = billAddrLine2;
	}
	public String getBillAddrLine3() {
		return billAddrLine3;
	}
	public void setBillAddrLine3(String billAddrLine3) {
		this.billAddrLine3 = billAddrLine3;
	}
	public String getBillAddrLine4() {
		return billAddrLine4;
	}
	public void setBillAddrLine4(String billAddrLine4) {
		this.billAddrLine4 = billAddrLine4;
	}
	public String getBillKhetAmphur() {
		return billKhetAmphur;
	}
	public void setBillKhetAmphur(String billKhetAmphur) {
		this.billKhetAmphur = billKhetAmphur;
	}
	public String getBillProvince() {
		return billProvince;
	}
	public void setBillProvince(String billProvince) {
		this.billProvince = billProvince;
	}
	public String getBillPostCode() {
		return billPostCode;
	}
	public void setBillPostCode(String billPostCode) {
		this.billPostCode = billPostCode;
	}
	public String getBillCountry() {
		return billCountry;
	}
	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}
	public String getVatSalutation() {
		return vatSalutation;
	}
	public void setVatSalutation(String vatSalutation) {
		this.vatSalutation = vatSalutation;
	}
	public String getVatFirstName() {
		return vatFirstName;
	}
	public void setVatFirstName(String vatFirstName) {
		this.vatFirstName = vatFirstName;
	}
	public String getVatLastName() {
		return vatLastName;
	}
	public void setVatLastName(String vatLastName) {
		this.vatLastName = vatLastName;
	}
	public String getVatCompSalutation() {
		return vatCompSalutation;
	}
	public void setVatCompSalutation(String vatCompSalutation) {
		this.vatCompSalutation = vatCompSalutation;
	}
	public String getVatCompName() {
		return vatCompName;
	}
	public void setVatCompName(String vatCompName) {
		this.vatCompName = vatCompName;
	}
	public String getVatAddrLine1() {
		return vatAddrLine1;
	}
	public void setVatAddrLine1(String vatAddrLine1) {
		this.vatAddrLine1 = vatAddrLine1;
	}
	public String getVatAddrLine2() {
		return vatAddrLine2;
	}
	public void setVatAddrLine2(String vatAddrLine2) {
		this.vatAddrLine2 = vatAddrLine2;
	}
	public String getVatAddrLine3() {
		return vatAddrLine3;
	}
	public void setVatAddrLine3(String vatAddrLine3) {
		this.vatAddrLine3 = vatAddrLine3;
	}
	public String getVatAddrLine4() {
		return vatAddrLine4;
	}
	public void setVatAddrLine4(String vatAddrLine4) {
		this.vatAddrLine4 = vatAddrLine4;
	}
	public String getVatKhetAmphur() {
		return vatKhetAmphur;
	}
	public void setVatKhetAmphur(String vatKhetAmphur) {
		this.vatKhetAmphur = vatKhetAmphur;
	}
	public String getVatProvince() {
		return vatProvince;
	}
	public void setVatProvince(String vatProvince) {
		this.vatProvince = vatProvince;
	}
	public String getVatPostCode() {
		return vatPostCode;
	}
	public void setVatPostCode(String vatPostCode) {
		this.vatPostCode = vatPostCode;
	}
	public String getVatCountry() {
		return vatCountry;
	}
	public void setVatCountry(String vatCountry) {
		this.vatCountry = vatCountry;
	}
	public String getDeliSalutation() {
		return deliSalutation;
	}
	public void setDeliSalutation(String deliSalutation) {
		this.deliSalutation = deliSalutation;
	}
	public String getDeliFirstName() {
		return deliFirstName;
	}
	public void setDeliFirstName(String deliFirstName) {
		this.deliFirstName = deliFirstName;
	}
	public String getDeliLastName() {
		return deliLastName;
	}
	public void setDeliLastName(String deliLastName) {
		this.deliLastName = deliLastName;
	}
	public String getDeliCompSalutation() {
		return deliCompSalutation;
	}
	public void setDeliCompSalutation(String deliCompSalutation) {
		this.deliCompSalutation = deliCompSalutation;
	}
	public String getDeliCompName() {
		return deliCompName;
	}
	public void setDeliCompName(String deliCompName) {
		this.deliCompName = deliCompName;
	}
	public String getDeliAddrLine1() {
		return deliAddrLine1;
	}
	public void setDeliAddrLine1(String deliAddrLine1) {
		this.deliAddrLine1 = deliAddrLine1;
	}
	public String getDeliAddrLine2() {
		return deliAddrLine2;
	}
	public void setDeliAddrLine2(String deliAddrLine2) {
		this.deliAddrLine2 = deliAddrLine2;
	}
	public String getDeliAddrLine3() {
		return deliAddrLine3;
	}
	public void setDeliAddrLine3(String deliAddrLine3) {
		this.deliAddrLine3 = deliAddrLine3;
	}
	public String getDeliAddrLine4() {
		return deliAddrLine4;
	}
	public void setDeliAddrLine4(String deliAddrLine4) {
		this.deliAddrLine4 = deliAddrLine4;
	}
	public String getDeliKhetAmphur() {
		return deliKhetAmphur;
	}
	public void setDeliKhetAmphur(String deliKhetAmphur) {
		this.deliKhetAmphur = deliKhetAmphur;
	}
	public String getDeliProvince() {
		return deliProvince;
	}
	public void setDeliProvince(String deliProvince) {
		this.deliProvince = deliProvince;
	}
	public String getDeliPostCode() {
		return deliPostCode;
	}
	public void setDeliPostCode(String deliPostCode) {
		this.deliPostCode = deliPostCode;
	}
	public String getDeliCountry() {
		return deliCountry;
	}
	public void setDeliCountry(String deliCountry) {
		this.deliCountry = deliCountry;
	}
	public String getDeliVatSalutation() {
		return deliVatSalutation;
	}
	public void setDeliVatSalutation(String deliVatSalutation) {
		this.deliVatSalutation = deliVatSalutation;
	}
	public String getDeliVatFirstName() {
		return deliVatFirstName;
	}
	public void setDeliVatFirstName(String deliVatFirstName) {
		this.deliVatFirstName = deliVatFirstName;
	}
	public String getDeliVatLastName() {
		return deliVatLastName;
	}
	public void setDeliVatLastName(String deliVatLastName) {
		this.deliVatLastName = deliVatLastName;
	}
	public String getDeliVatCompSalutation() {
		return deliVatCompSalutation;
	}
	public void setDeliVatCompSalutation(String deliVatCompSalutation) {
		this.deliVatCompSalutation = deliVatCompSalutation;
	}
	public String getDeliVatCompName() {
		return deliVatCompName;
	}
	public void setDeliVatCompName(String deliVatCompName) {
		this.deliVatCompName = deliVatCompName;
	}
	public String getDeliVatAddrLine1() {
		return deliVatAddrLine1;
	}
	public void setDeliVatAddrLine1(String deliVatAddrLine1) {
		this.deliVatAddrLine1 = deliVatAddrLine1;
	}
	public String getDeliVatAddrLine2() {
		return deliVatAddrLine2;
	}
	public void setDeliVatAddrLine2(String deliVatAddrLine2) {
		this.deliVatAddrLine2 = deliVatAddrLine2;
	}
	public String getDeliVatAddrLine3() {
		return deliVatAddrLine3;
	}
	public void setDeliVatAddrLine3(String deliVatAddrLine3) {
		this.deliVatAddrLine3 = deliVatAddrLine3;
	}
	public String getDeliVatAddrLine4() {
		return deliVatAddrLine4;
	}
	public void setDeliVatAddrLine4(String deliVatAddrLine4) {
		this.deliVatAddrLine4 = deliVatAddrLine4;
	}
	public String getDeliVatKhetAmphur() {
		return deliVatKhetAmphur;
	}
	public void setDeliVatKhetAmphur(String deliVatKhetAmphur) {
		this.deliVatKhetAmphur = deliVatKhetAmphur;
	}
	public String getDeliVatProvince() {
		return deliVatProvince;
	}
	public void setDeliVatProvince(String deliVatProvince) {
		this.deliVatProvince = deliVatProvince;
	}
	public String getDeliVatPostCode() {
		return deliVatPostCode;
	}
	public void setDeliVatPostCode(String deliVatPostCode) {
		this.deliVatPostCode = deliVatPostCode;
	}
	public String getDeliVatCountry() {
		return deliVatCountry;
	}
	public void setDeliVatCountry(String deliVatCountry) {
		this.deliVatCountry = deliVatCountry;
	}
	public String getCustomerAccountName() {
		return customerAccountName;
	}
	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPayCreditCardNo() {
		return payCreditCardNo;
	}
	public void setPayCreditCardNo(String payCreditCardNo) {
		this.payCreditCardNo = payCreditCardNo;
	}
	public String getPayCreditCardCarrier() {
		return payCreditCardCarrier;
	}
	public void setPayCreditCardCarrier(String payCreditCardCarrier) {
		this.payCreditCardCarrier = payCreditCardCarrier;
	}
	public String getPayBankNo() {
		return payBankNo;
	}
	public void setPayBankNo(String payBankNo) {
		this.payBankNo = payBankNo;
	}
	public String getPayBankName() {
		return payBankName;
	}
	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
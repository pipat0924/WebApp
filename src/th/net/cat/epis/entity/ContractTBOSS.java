package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "CONTRACTTBOSS", uniqueConstraints = @UniqueConstraint(columnNames = "CONTRNO"))
public class ContractTBOSS {

	@Id
	@SequenceGenerator(name="contracttboss_seq", sequenceName="contracttboss_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contracttboss_seq")
	@Column(name = "CONTRACTTBOSSID") private Long id;
	@Column(name = "CONTRNO", unique = true, nullable = false, length = 10) private String contractNo;
	@Column(name = "FIRSTNAME", nullable = false, length = 50) private String firstname;
	@Column(name = "LASTNAME", nullable = false, length = 50) private String lastname;
	@Column(name = "ADDRESS_1", nullable = false, length = 50) private String address1;
	@Column(name = "ADDRESS_2", length = 50) private String address2;
	@Column(name = "ADDRESS_3", length = 50) private String address3;
	@Column(name = "ADDRESS_4", length = 50) private String address4;
	@Column(name = "ADDRESS_5", length = 50) private String address5;
	@Column(name = "POSTCODE", length = 5) private String postcode;
	@Column(name = "REGION_NAME", length = 50) private String regionName;
	@Column(name = "CUSTOMER_TYPE", length = 12) private String customerType;
	@Column(name = "BILL_GROUP", length = 10) private String billGroup;
	@Column(name = "CREDIT_LIMIT", precision = 12) private BigDecimal creditLimit;
	@Column(name = "DEPOSIT", precision = 12) private BigDecimal deposit;
	@Column(name = "DEBT_AMOUNT", precision = 12) private BigDecimal debtAmount;
	@Column(name = "INVOICE_AMOUNT", precision = 12)	private BigDecimal invoiceAmount;
	@Column(name = "VAT_AMOUNT", precision = 10)	private BigDecimal vatAmount;
	@Column(name = "VOL_DISCOUNT", precision = 10)	private BigDecimal volDiscount;
	@Column(name = "USAGE_PERIOD", length = 21)	private String usagePeriod;
	@Column(name = "UPDATEDTTM")	private Date updateDttm;
	@Column(name = "UPDATESYSTEM", length = 3) private String updateSystem;
	@Column(name = "UPDATEUSER", length = 32) private String updateUser;
	public ContractTBOSS() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contrno) {
		this.contractNo = contrno;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getAddress4() {
		return address4;
	}
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	public String getAddress5() {
		return address5;
	}
	public void setAddress5(String address5) {
		this.address5 = address5;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public BigDecimal getDebtAmount() {
		return debtAmount;
	}
	public void setDebtAmount(BigDecimal debtAmount) {
		this.debtAmount = debtAmount;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public BigDecimal getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}
	public BigDecimal getVolDiscount() {
		return volDiscount;
	}
	public void setVolDiscount(BigDecimal volDiscount) {
		this.volDiscount = volDiscount;
	}
	public String getUsagePeriod() {
		return usagePeriod;
	}
	public void setUsagePeriod(String usagePeriod) {
		this.usagePeriod = usagePeriod;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updatedttm) {
		this.updateDttm = updatedttm;
	}
	public String getUpdateSystem() {
		return updateSystem;
	}
	public void setUpdateSystem(String updateSystem) {
		this.updateSystem = updateSystem;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}

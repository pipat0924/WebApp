package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TMPACCOUNT")
public class Customer {

	@Id
	@SequenceGenerator(name="account_seq", sequenceName="account_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="account_seq")
	@Column(name="ACCOUNTID") private Long id;
	@Column(name="ACCOUNTNO") private String no;
	@Column(name="ACCOUNTTYPE") private String type;
	@Column(name="TAXID") private String tax;
	@Column(name="NAME") private String name;
	@Column(name="BRANCH") private String branch;
	@Column(name="STATUS") private String status;
	@Column(name="TELEPHONE") private String tel;
	@Column(name="OUTSTANDING") private BigDecimal outstanding;
	@Column(name="ADVANCED") private BigDecimal advanced;
	@Column(name="RECEIPTADDRESS") private String receiptAddr;
	@Column(name="INVOICEADDRESS") private String invoiceAddr;
	@Column(name="BILLINGSERVERID") private String billServerId;
	@Column(name="CURRENCYCODE") private String currencyCode;
	@Column(name="VATRATE") private String vatRate;
	@Column(name="BILLGROUP") private String billGroup;
	@Column(name="REMARK") private String remark;
	@Column(name="COLLECTIONUNIT") private String collectionUnit;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PAYMENTID") private Payment payment;

	@Column(name="ACCT_CAT_LKP") private String acctCatLkp;//by NSD 02-03-2017
	@Column(name="CAT_CUSTOMER_SEGMENT") private String catCustomerSegment;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public BigDecimal getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(BigDecimal outstanding) {
		this.outstanding = outstanding;
	}
	public BigDecimal getAdvanced() {
		return advanced;
	}
	public void setAdvanced(BigDecimal advanced) {
		this.advanced = advanced;
	}
	public String getReceiptAddr() {
		return receiptAddr;
	}
	public void setReceiptAddr(String receiptAddr) {
		this.receiptAddr = receiptAddr;
	}
	public String getInvoiceAddr() {
		return invoiceAddr;
	}
	public void setInvoiceAddr(String invoiceAddr) {
		this.invoiceAddr = invoiceAddr;
	}
	public String getBillServerId() {
		return billServerId;
	}
	public void setBillServerId(String billServerId) {
		this.billServerId = billServerId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getVatRate() {
		return vatRate;
	}
	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getAcctCatLkp() {
		return acctCatLkp;
	}

	public void setAcctCatLkp(String acctCatLkp) {
		this.acctCatLkp = acctCatLkp;
	}

	public String getCatCustomerSegment() {
		return catCustomerSegment;
	}

	public void setCatCustomerSegment(String catCustomerSegment) {
		this.catCustomerSegment = catCustomerSegment;
	}
}
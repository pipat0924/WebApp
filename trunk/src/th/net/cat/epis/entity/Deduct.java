package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRSDEDUCTION")
public class Deduct {

	@Id
	@SequenceGenerator(name="withholding_seq", sequenceName="withholding_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="withholding_seq")
	@Column(name="DEDUCTIONID") private Long id;
	@Column(name="DEDUCTIONNO") private String no;
	@Column(name="DEDUCTIONTYPE") private String type;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="PAYMENTDATE") private Date date;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="INVOICE_NO") private String invoiceNo;
	@Column(name="CUSTOMER_NO") private String customerNo;
	@Column(name="COST_CENTER") private String costCenter;
	@Column(name="WITH_HOLDING_TAX") private BigDecimal withHoldingTax;
	@Column(name="TAX_AMT") private BigDecimal taxAmt;
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
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public BigDecimal getWithHoldingTax() {
		return withHoldingTax;
	}
	public void setWithHoldingTax(BigDecimal withHoldingTax) {
		this.withHoldingTax = withHoldingTax;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	
	
}
package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TMPINVOICE")
public class Invoice {

	@Id
	@SequenceGenerator(name="invoice_seq", sequenceName="invoice_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoice_seq")
	@Column(name="INVOICEID") private Long id;
	@Column(name="RECEIPTID") private Long receiptId;
	@Column(name="INVOICENO") private String no;
	@Column(name="PAYMENTTYPE") private String type;
	@Column(name="KENAN") private String kenan;
	@Column(name="CURRENCYCODE") private String currencyCode;
	@Column(name="ISSUEDATE") private Date issueDt;
	@Column(name="DUEDATE") private Date dueDt;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="DISCOUNT") private BigDecimal discount;
	@Column(name="CHARGE") private BigDecimal charge;
	@Column(name="VATRATE") private BigDecimal vatRate;
	@Column(name="VAT") private BigDecimal vat;
	@Column(name="TOTALCHARGE") private BigDecimal totalCharge;
	@Column(name="DEDUCTION") private BigDecimal deduction;
	@Column(name="BALANCEDUE") private BigDecimal balanceDue;
	@Column(name="AFTERSALEDISCOUNT") private BigDecimal afterSaleDiscount;
	@Column(name="RECEIVED") private BigDecimal received;
	@Column(name="CHANGE") private BigDecimal change;
	@Column(name="ADVANCED") private BigDecimal advanced;
	@Column(name="BILLCYCLE") private String billCycle;
	@Column(name="DEBTAMOUNT") private BigDecimal debtAmount;
	@Column(name="STATUS") private String status;
	@Column(name="CANCELDTTM") private Date cancelledDttm;
	@Column(name="CANCELEDBY") private String cancelledBy;
	@Column(name="ATTRIBUTES") private String attributes;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="SUBNO") private String subNo;//by NSD 23-03-2017
	@Column(name="DISC_TYPE") private String discountType;//by NSD 23-03-2017
	@Column(name="SOURCE") private String source;
	@Column(name="EXCH_RATE_AR") private BigDecimal rateAr;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ACCOUNTID") private Customer customer;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="PAYMENTID") private Payment payment;
	@OneToMany(mappedBy="invoiceId", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<Service> services = new HashSet<Service>();
	@Column(name = "AFTERSALEDISC_VAT") private BigDecimal afterSaleDiscVat;
	@Column(name="DISC_APPR_USER") private String discApprUser;
	@OneToMany(mappedBy="invoiceId", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<InvoiceVatDetail> invoiceVatDetails = new HashSet<InvoiceVatDetail>();

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
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
	public String getKenan() {
		return kenan;
	}
	public void setKenan(String kenan) {
		this.kenan = kenan;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public BigDecimal getVatRate() {
		return vatRate;
	}
	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public BigDecimal getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}
	public BigDecimal getDeduction() {
		return deduction;
	}
	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	public BigDecimal getAfterSaleDiscount() {
		return afterSaleDiscount;
	}
	public void setAfterSaleDiscount(BigDecimal afterSaleDiscount) {
		this.afterSaleDiscount = afterSaleDiscount;
	}
	public BigDecimal getReceived() {
		return received;
	}
	public void setReceived(BigDecimal received) {
		this.received = received;
	}
	public BigDecimal getChange() {
		return change;
	}
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	public BigDecimal getAdvanced() {
		return advanced;
	}
	public void setAdvanced(BigDecimal advanced) {
		this.advanced = advanced;
	}
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCancelledBy() {
		return cancelledBy;
	}
	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	
	public Date getIssueDt() {
		return issueDt;
	}
	public void setIssueDt(Date issueDt) {
		this.issueDt = issueDt;
	}
	public Date getDueDt() {
		return dueDt;
	}
	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}
	public Date getCancelledDttm() {
		return cancelledDttm;
	}
	public void setCancelledDttm(Date cancelledDttm) {
		this.cancelledDttm = cancelledDttm;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Set<Service> getServices() {
		return services;
	}
	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Set<InvoiceVatDetail> getInvoiceVatDetails() {
		return invoiceVatDetails;
	}

	public void setInvoiceVatDetails(Set<InvoiceVatDetail> invoiceVatDetails) {
		this.invoiceVatDetails = invoiceVatDetails;
	}

	public BigDecimal getDebtAmount() {
		return debtAmount;
	}

	public void setDebtAmount(BigDecimal debtAmount) {
		this.debtAmount = debtAmount;
	}

	public String getSubNo() {
		return subNo;
	}

	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public BigDecimal getAfterSaleDiscVat() {
		return afterSaleDiscVat;
	}

	public void setAfterSaleDiscVat(BigDecimal afterSaleDiscVat) {
		this.afterSaleDiscVat = afterSaleDiscVat;
	}

	public String getDiscApprUser() {
		return discApprUser;
	}

	public void setDiscApprUser(String discApprUser) {
		this.discApprUser = discApprUser;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public BigDecimal getRateAr() {
		return rateAr;
	}
	public void setRateAr(BigDecimal rateAr) {
		this.rateAr = rateAr;
	}
	
	
}
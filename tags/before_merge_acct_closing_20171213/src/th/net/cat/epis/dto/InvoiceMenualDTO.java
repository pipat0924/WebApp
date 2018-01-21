package th.net.cat.epis.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.net.cat.epis.dto.SettlePaymentDTO.InvoiceVatDetail;
import th.net.cat.epis.util.converter.CustomDateTimeDeserializer;
import th.net.cat.epis.util.converter.CustomDateTimeSerializer;
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceMenualDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2817736959528943720L;
	private String no;
	private String currencyCode;
	private BigDecimal amount;
	private BigDecimal discount;
	private BigDecimal charge;
	private BigDecimal vatRate;
	private BigDecimal vat;
	private BigDecimal totalCharge;
	private BigDecimal deduction;
	private BigDecimal afterSaleDiscount;
	private BigDecimal balanceDue;
	private BigDecimal received;
	private BigDecimal change;
	private BigDecimal advanced;
	private BigDecimal calculatedVat;
	private String billCycle;
	private Date issueDate;
	private Date dueDate;
	private String kenan;//
	private String status;
	private String paymentCase;
	private List<String> subNoList;
	private List<InvoiceVatDetail> invoiceVatDetails;
	private String discountType;//by NSD 04-04-2017
	private BigDecimal afterSaleDiscVat;
	private String discApprUser;
	private String taxTypeCode;
	private boolean checked;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public BigDecimal getAfterSaleDiscount() {
		return afterSaleDiscount;
	}
	public void setAfterSaleDiscount(BigDecimal afterSaleDiscount) {
		this.afterSaleDiscount = afterSaleDiscount;
	}
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
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
	
	public BigDecimal getCalculatedVat() {
		return calculatedVat;
	}
	public void setCalculatedVat(BigDecimal calculatedVat) {
		this.calculatedVat = calculatedVat;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getIssueDate() {
		return issueDate;
	}
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getDueDate() {
		return dueDate;
	}
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getKenan() {
		return kenan;
	}
	public void setKenan(String kenan) {
		this.kenan = kenan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentCase() {
		return paymentCase;
	}
	public void setPaymentCase(String paymentCase) {
		this.paymentCase = paymentCase;
	}
	public List<String> getSubNoList() {
		return subNoList;
	}
	public void setSubNoList(List<String> subNoList) {
		this.subNoList = subNoList;
	}
	public List<InvoiceVatDetail> getInvoiceVatDetails() {
		return invoiceVatDetails;
	}
	public void setInvoiceVatDetails(List<InvoiceVatDetail> invoiceVatDetails) {
		this.invoiceVatDetails = invoiceVatDetails;
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
	public String getTaxTypeCode() {
		return taxTypeCode;
	}
	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	
}

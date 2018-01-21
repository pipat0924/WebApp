package th.net.cat.epis.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import th.net.cat.epis.dto.SettlePaymentDTO.Customer;
import th.net.cat.epis.entity.Receipt;

public class PrintReceiptDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3930763189815139904L;
	private boolean isCopy = false;
	private String receiptType;
	private List<Receipt> receipts = new ArrayList<Receipt>();
	private List<Customer> customers = new ArrayList<Customer>();

	private String receiptFormat;
	private String receiptLang;
	private String billingGroup;
	private String substitute;
	private String reason;
	private String note;
	private String amountBeforeVatTax;
	private String discountTax;
	private String amountSumBeforeVatTax;
	private String vatTax;
	private String amountSumAfterVatTax;
	private String checkSpecial;
	private String reprint;//W3P add repair print 21-Mar-2017

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	private String reasonOfSubstitute;
	
	public String getReasonOfSubstitute() {
		return reasonOfSubstitute;
	}
	public void setReasonOfSubstitute(String reasonOfSubstitute) {
		this.reasonOfSubstitute = reasonOfSubstitute;
	}
	private Long id;
	
	private th.net.cat.epis.dto.Trsreprint trsreprintDTO;
	public boolean isCopy() {
		return isCopy;
	}
	public void setCopy(boolean isCopy) {
		this.isCopy = isCopy;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public List<Receipt> getReceipts() {
		return receipts;
	}
	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}
	public String getReceiptFormat() {
		return receiptFormat;
	}
	public void setReceiptFormat(String receiptFormat) {
		this.receiptFormat = receiptFormat;
	}
	public String getReceiptLang() {
		return receiptLang;
	}
	public void setReceiptLang(String receiptLang) {
		this.receiptLang = receiptLang;
	}
	public String getBillingGroup() {
		return billingGroup;
	}
	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}
	
	
	public String getSubstitute() {
		return substitute;
	}
	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public th.net.cat.epis.dto.Trsreprint getTrsreprintDTO() {
		return trsreprintDTO;
	}
	public void setTrsreprintDTO(th.net.cat.epis.dto.Trsreprint trsreprintDTO) {
		this.trsreprintDTO = trsreprintDTO;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCheckSpecial() {
		return checkSpecial;
	}
	public void setCheckSpecial(String checkSpecial) {
		this.checkSpecial = checkSpecial;
	}
	public String getAmountBeforeVatTax() {
		return amountBeforeVatTax;
	}
	public void setAmountBeforeVatTax(String amountBeforeVatTax) {
		this.amountBeforeVatTax = amountBeforeVatTax;
	}
	public String getDiscountTax() {
		return discountTax;
	}
	public void setDiscountTax(String discountTax) {
		this.discountTax = discountTax;
	}
	public String getAmountSumBeforeVatTax() {
		return amountSumBeforeVatTax;
	}
	public void setAmountSumBeforeVatTax(String amountSumBeforeVatTax) {
		this.amountSumBeforeVatTax = amountSumBeforeVatTax;
	}
	public String getVatTax() {
		return vatTax;
	}
	public void setVatTax(String vatTax) {
		this.vatTax = vatTax;
	}
	public String getAmountSumAfterVatTax() {
		return amountSumAfterVatTax;
	}
	public void setAmountSumAfterVatTax(String amountSumAfterVatTax) {
		this.amountSumAfterVatTax = amountSumAfterVatTax;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String getReprint() {
		return reprint;
	}

	public void setReprint(String reprint) {
		this.reprint = reprint;
	}
}
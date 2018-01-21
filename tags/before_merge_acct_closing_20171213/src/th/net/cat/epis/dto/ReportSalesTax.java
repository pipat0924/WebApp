package th.net.cat.epis.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReportSalesTax {

	private Date receiptDate;
	private String receiptNo;
	private String receiptName;
	private String taxId;
	private String branchNo;
	private BigDecimal productCost;
	private BigDecimal vat;
	private BigDecimal totalAmount;
	private Long officialId;
	private String officialCode;
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getReceiptName() {
		return receiptName;
	}
	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public BigDecimal getProductCost() {
		return productCost;
	}
	public void setProductCost(BigDecimal productCost) {
		this.productCost = productCost;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getOfficialId() {
		return officialId;
	}
	public void setOfficialId(Long officialId) {
		this.officialId = officialId;
	}
	public String getOfficialCode() {
		return officialCode;
	}
	public void setOfficialCode(String officialCode) {
		this.officialCode = officialCode;
	}
}

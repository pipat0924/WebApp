package th.net.cat.epis.dto;

import java.math.BigDecimal;

public class InvoiceProduct {

	protected String billRefNo;
	protected String revTypeCode;
	protected String revTypeName;
	protected String productCode;
	protected String productName;
	protected String subProductCode;
	protected String subProductName;
	protected BigDecimal amount;
	protected String contrno;
	// might not use for display
//	protected String accountCode;
//	protected String accountCodeDesc;

	public InvoiceProduct() {
	}

	public InvoiceProduct(String billRefNo, String revTypeCode,
						  String revTypeName, String productCode, String productName,
						  String subProductCode, String subProductName, BigDecimal amount,String contrno) {
		super();
		this.billRefNo = billRefNo;
		this.revTypeCode = revTypeCode;
		this.revTypeName = revTypeName;
		this.productCode = productCode;
		this.productName = productName;
		this.subProductCode = subProductCode;
		this.subProductName = subProductName;
		this.amount = amount;
		this.contrno = contrno;
	}
	public String getBillRefNo() {
		return billRefNo;
	}
	public void setBillRefNo(String billRefNo) {
		this.billRefNo = billRefNo;
	}
	public String getRevTypeCode() {
		return revTypeCode;
	}
	public void setRevTypeCode(String revTypeCode) {
		this.revTypeCode = revTypeCode;
	}
	public String getRevTypeName() {
		return revTypeName;
	}
	public void setRevTypeName(String revTypeName) {
		this.revTypeName = revTypeName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSubProductCode() {
		return subProductCode;
	}
	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}
	public String getSubProductName() {
		return subProductName;
	}
	public void setSubProductName(String subProductName) {
		this.subProductName = subProductName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getContrno() {
		return contrno;
	}

	public void setContrno(String contrno) {
		this.contrno = contrno;
	}
}

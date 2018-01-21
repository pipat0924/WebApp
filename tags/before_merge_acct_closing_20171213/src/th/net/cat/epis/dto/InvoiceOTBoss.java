package th.net.cat.epis.dto;

import java.math.BigDecimal;

public class InvoiceOTBoss {
	protected String account_no;
	protected String billRefNo;
	protected String revTypeCode;
	protected String revTypeName;
	protected String productCode;
	protected String productName;
	protected String subProductCode;
	protected String subProductName;
	protected BigDecimal amount;
	public InvoiceOTBoss() {
	}

	public InvoiceOTBoss(String account_no, String billRefNo, String revTypeCode, String revTypeName, String productCode, String productName,
			String subProductCode, String subProductName, BigDecimal amount) {
		super();
		this.account_no = account_no;
		this.billRefNo = billRefNo;
		this.revTypeCode = revTypeCode;
		this.revTypeName = revTypeName;
		this.productCode = productCode;
		this.productName = productName;
		this.subProductCode = subProductCode;
		this.subProductName = subProductName;
		this.amount = amount;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
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
	
	
}

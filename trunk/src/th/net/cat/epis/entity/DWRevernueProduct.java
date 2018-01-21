package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DW_REVERNUE_PRODUCT")
public class DWRevernueProduct {
	@Id  
	@SequenceGenerator(name="DW_REVERNUE_PRODUCT_SEQ", sequenceName="DW_REVERNUE_PRODUCT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DW_REVERNUE_PRODUCT_SEQ")
	@Column(name="ID") private Long id;
	@Column(name="PAYMENT_ID") private Long paymentId;
	@Column(name="INVOICEID") private Long invoiceId;//account_no
	@Column(name="SOURCE_ID") private Long sourceId;
	@Column(name="SOURCE_TABLE") private String sourceTable;
	@Column(name="PRODUCT_CODE") private String productCode;
	@Column(name="SUB_PRODUCT_CODE") private String subProductCode;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="DISCOUNT") private BigDecimal discount;
	@Column(name="VAT") private BigDecimal vat;
	@Column(name="TOTAL_AMOUNT") private BigDecimal totalAmount;
	@Column(name="DEDUCTION") private BigDecimal deduction;
	@Column(name="REMARK") private String remark;
	@Column(name="CREATE_BY") private String createBy;
	@Column(name="CREATE_DATE") private Timestamp createDate;
	@Column(name="UPDATE_BY") private String updateBy;
	@Column(name="UPDATE_DATE") private Timestamp updateDate;
	@Column(name="RECORD_STATUS") private String recordStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceTable() {
		return sourceTable;
	}
	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getSubProductCode() {
		return subProductCode;
	}
	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
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
	public BigDecimal getDeduction() {
		return deduction;
	}
	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	
	

}

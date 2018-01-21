package th.net.cat.epis.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BILLINVOICEPRODUCT")
public class BillInvoiceProduct {

	@Id
	@SequenceGenerator(name="billinvoiceproduct_seq", sequenceName="billinvoiceproduct_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="billinvoiceproduct_seq")
	@Column(name="ID") private Long id;
	@Column(name="INVOICEID") private Long invoiceId;
	@Column(name="PRODUCTPRIORITY") private Integer priority;
	@Column(name="PRODUCTCODE") private String productCode;
	@Column(name="SUBPRODUCTCODE") private String subProductCode;
	@Column(name="REVENUETYPE") private String revenueType;
	@Column(name="AMOUNT") private BigDecimal amount;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	public String getRevenueType() {
		return revenueType;
	}
	public void setRevenueType(String revenueType) {
		this.revenueType = revenueType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
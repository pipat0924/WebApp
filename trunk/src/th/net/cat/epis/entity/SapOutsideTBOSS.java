package th.net.cat.epis.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INV_SUMMARY_SAP_OTBOSS")
public class SapOutsideTBOSS {

	@Id
	@Column(name="ID") private Long Id;
	@Column(name="BI_REF") private String invoiceNo;
	@Column(name="CONTRNO") private String billAccount;
	@Column(name="BILLGROUP") private String billGroup;
	@Column(name="SEGMENT_CODE") private String segmentCode;
	@Column(name="PRODUCT_CODE") private String productCode;
	@Column(name="SUB_PRODUCT_CODE") private String subProductCode;
	@Column(name="REVENUE_TYPE_CODE") private String revenueTypeCode;
	@Column(name="SERVICE_PRIORITY") private String servicePriority;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="VAT_AMOUNT") private BigDecimal vat;
	@Column(name="TOTAL_AMOUNT") private BigDecimal totalCharge;

	public Long getId() {
		return Id;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public String getBillAccount() {
		return billAccount;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public String getSegmentCode() {
		return segmentCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public String getSubProductCode() {
		return subProductCode;
	}
	public String getRevenueTypeCode() {
		return revenueTypeCode;
	}
	public String getServicePriority() {
		return servicePriority;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public BigDecimal getTotalCharge() {
		return totalCharge;
	}
	public void setId(Long id) {
		Id = id;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public void setBillAccount(String billAccount) {
		this.billAccount = billAccount;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}
	public void setRevenueTypeCode(String revenueTypeCode) {
		this.revenueTypeCode = revenueTypeCode;
	}
	public void setServicePriority(String servicePriority) {
		this.servicePriority = servicePriority;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}
}
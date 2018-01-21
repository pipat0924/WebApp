package th.net.cat.epis.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SettenManualDTO {

		private Date createDate;
		private Date paidDate;
		private String clearing;
		private BigDecimal paidAmount;
		private String invoiceNo;
		private String receiptNoManual;
		private String branchArea;
		private String remark;
		private String createBy;
		private String updateBy;
		private BigDecimal vatAmount;
		private String payType;
		private String source;
		private Date updateDate;
		private String recordStatus;
		private Long paymenId;
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getPaidDate() {
			return paidDate;
		}
		public void setPaidDate(Date paidDate) {
			this.paidDate = paidDate;
		}
		public String getClearing() {
			return clearing;
		}
		public void setClearing(String clearing) {
			this.clearing = clearing;
		}
		public BigDecimal getPaidAmount() {
			return paidAmount;
		}
		public void setPaidAmount(BigDecimal paidAmount) {
			this.paidAmount = paidAmount;
		}
		public String getInvoiceNo() {
			return invoiceNo;
		}
		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}
		public String getReceiptNoManual() {
			return receiptNoManual;
		}
		public void setReceiptNoManual(String receiptNoManual) {
			this.receiptNoManual = receiptNoManual;
		}
		public String getBranchArea() {
			return branchArea;
		}
		public void setBranchArea(String branchArea) {
			this.branchArea = branchArea;
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
		public String getUpdateBy() {
			return updateBy;
		}
		public void setUpdateBy(String updateBy) {
			this.updateBy = updateBy;
		}
		public BigDecimal getVatAmount() {
			return vatAmount;
		}
		public void setVatAmount(BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
		}
		public String getPayType() {
			return payType;
		}
		public void setPayType(String payType) {
			this.payType = payType;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
		public String getRecordStatus() {
			return recordStatus;
		}
		public void setRecordStatus(String recordStatus) {
			this.recordStatus = recordStatus;
		}
		public Long getPaymenId() {
			return paymenId;
		}
		public void setPaymenId(Long paymenId) {
			this.paymenId = paymenId;
		}
	
}

package th.net.cat.epis.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.net.cat.epis.util.converter.CustomDateDeserializer;
import th.net.cat.epis.util.converter.CustomDateSerializer;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayManualSetterDTO {
		private Long paymenId;
		private String invoiceNo;
		private String receiptNoManual;
		private BigDecimal vatAmount;
		private Date paidDate;
		private String branchArea;
		private String branchCode;
		private BigDecimal paidAmount;
		private String remark;
		private String source;
		private String clearing;
		private String createBy;
		private Date createDate;
		private String updateBy;
		private Date updateDate;
		private String recordStatus;
		private String accountNo;
		private Long ManualId;
		

		public Long getManualId() {
			return ManualId;
		}
		public void setManualId(Long manualId) {
			ManualId = manualId;
		}
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		public BigDecimal getVatAmount() {
			return vatAmount;
		}
		public void setVatAmount(BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
		}
		public Long getPaymenId() {
			return paymenId;
		}
		public void setPaymenId(Long paymenId) {
			this.paymenId = paymenId;
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
		@JsonSerialize(using = CustomDateSerializer.class)
		public Date getPaidDate() {
			return paidDate;
		}
		@JsonDeserialize(using = CustomDateDeserializer.class)
		public void setPaidDate(Date paidDate) {
			this.paidDate = paidDate;
		}
		public String getBranchArea() {
			return branchArea;
		}
		public void setBranchArea(String branchArea) {
			this.branchArea = branchArea;
		}
		public String getBranchCode() {
			return branchCode;
		}
		public void setBranchCode(String branchCode) {
			this.branchCode = branchCode;
		}
		public BigDecimal getPaidAmount() {
			return paidAmount;
		}
		public void setPaidAmount(BigDecimal paidAmount) {
			this.paidAmount = paidAmount;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getClearing() {
			return clearing;
		}
		public void setClearing(String clearing) {
			this.clearing = clearing;
		}
		public String getCreateBy() {
			return createBy;
		}
		public void setCreateBy(String createBy) {
			this.createBy = createBy;
		}
		@JsonSerialize(using = CustomDateSerializer.class)
		public Date getCreateDate() {
			return createDate;
		}
		@JsonDeserialize(using = CustomDateDeserializer.class)
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public String getUpdateBy() {
			return updateBy;
		}
		public void setUpdateBy(String updateBy) {
			this.updateBy = updateBy;
		}
		@JsonSerialize(using = CustomDateSerializer.class)
		public Date getUpdateDate() {
			return updateDate;
		}
		@JsonDeserialize(using = CustomDateDeserializer.class)
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
		public String getRecordStatus() {
			return recordStatus;
		}
		public void setRecordStatus(String recordStatus) {
			this.recordStatus = recordStatus;
		}
			
}

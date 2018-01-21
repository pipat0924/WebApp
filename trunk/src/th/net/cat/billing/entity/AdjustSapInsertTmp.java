package th.net.cat.billing.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CAT_ADJ_SAP_INSERT_TMP" , schema = "ARBOR")
public class AdjustSapInsertTmp {
	@Id
	//@SequenceGenerator(name="ARBOR.KEE_ADJ_SAP_IBACSS_SEQ", sequenceName="ARBOR.KEE_ADJ_SAP_IBACSS_SEQ", allocationSize=1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARBOR.KEE_ADJ_SAP_IBACSS_SEQ")
	@Column(name="SAP_ID") private Long id;
	
	@Column(name="CONTRACT_NO") private String contractNo;
	@Column(name="BILL_REF_NO") private Long billRefNo;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="ANNOTATION") private String annotation;
	@Column(name="ADJ_REASON_CODE") private String adjReasonCode;
	@Column(name="ACCOUNT_CODE_NEW") private String accountCodeNew;
	@Column(name="PRODUCT_CODE") private String productCode;
	@Column(name="SUB_PRODUCT_CODE") private String subProductCode;
	@Column(name="REV_TYPE_CODE") private String revTypeCode;
	@Column(name="COST_CENTER") private String costCenter;
	@Column(name="REQ_USER") private String reqUser;
	@Column(name="FORCE_ADJ") private String forceAdj;
	@Column(name="TRADING_PARTNER") private String tradingPartner;
	@Column(name="PROCESS_DATE") private Date processDate;
	@Column(name="APP_DATE") private Date appDate;
	@Column(name="FILE_NAME") private String fileName;
	@Column(name="DOCDATE") private String docDate;
	@Column(name="PERIOD") private String period;
	@Column(name="NEW_BILLING_ACCOUNT") private String newBillingAccount;
	//@SequenceGenerator(name="ARBOR.CAT_ADJ_TMP_SEQ", sequenceName="ARBOR.CAT_ADJ_TMP_SEQ", allocationSize=1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARBOR.CAT_ADJ_TMP_SEQ")
	@Column(name="BATCH_ID") private Long batchId;
	@Column(name="PROCESS_STATUS") private Long processStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public Long getBillRefNo() {
		return billRefNo;
	}
	public void setBillRefNo(Long billRefNo) {
		this.billRefNo = billRefNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getAdjReasonCode() {
		return adjReasonCode;
	}
	public void setAdjReasonCode(String adjReasonCode) {
		this.adjReasonCode = adjReasonCode;
	}
	public String getAccountCodeNew() {
		return accountCodeNew;
	}
	public void setAccountCodeNew(String accountCodeNew) {
		this.accountCodeNew = accountCodeNew;
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
	public String getRevTypeCode() {
		return revTypeCode;
	}
	public void setRevTypeCode(String revTypeCode) {
		this.revTypeCode = revTypeCode;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getReqUser() {
		return reqUser;
	}
	public void setReqUser(String reqUser) {
		this.reqUser = reqUser;
	}
	public String getForceAdj() {
		return forceAdj;
	}
	public void setForceAdj(String forceAdj) {
		this.forceAdj = forceAdj;
	}
	public String getTradingPartner() {
		return tradingPartner;
	}
	public void setTradingPartner(String tradingPartner) {
		this.tradingPartner = tradingPartner;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getNewBillingAccount() {
		return newBillingAccount;
	}
	public void setNewBillingAccount(String newBillingAccount) {
		this.newBillingAccount = newBillingAccount;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(Long processStatus) {
		this.processStatus = processStatus;
	}
}

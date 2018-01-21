package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AMOUNT_ADJMT_HEADER")
public class AmountAdjustmentHeader {
	@Id
	@SequenceGenerator(name="AMOUNT_ADJUSTMENT_HEADER_SEQ", sequenceName="AMOUNT_ADJUSTMENT_HEADER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AMOUNT_ADJUSTMENT_HEADER_SEQ")
	@Column(name="AMOUNT_ADJMT_HEADER_ID") private Long id;
	@Column(name="AMOUNT_ADJMT_HEADER_NO") private String no;
	@Column(name="RECEIPTID") private Long receiptId;
	@Column(name="ACCOUNTID") private Long accountId;
	@Column(name="ACCOUNT_NO") private String accountNo;
	@Column(name="ADJUST_TYPE") private String adjustType;
	@Column(name="ADJUST_REASON") private String adjustReason;
	@Column(name="RECEIPT_AMOUNT") private BigDecimal receiptAmount;//Amount in issued receipt/Tax invoice
	@Column(name="ADJUST_AMOUNT_TOTAL_CORRECT") private BigDecimal adjustAmountTotalCorrect;//Correct Amount
	@Column(name="ADJUST_AMOUNT_TOTAL_DIF") private BigDecimal adjustAmountTotalDif;//Difference
	@Column(name="ADJUST_AMOUNT_TOTAL_DIF_VAT") private BigDecimal adjustAmountTotalDifVat;//Difference*vat
	@Column(name="ADJUST_AMOUNT_TOTAL") private BigDecimal adjustAmountTotal;//Total Amount = Difference + Difference*vat
	@Column(name="CREATE_DATE") private Date createDate;
	@Column(name="UPDATE_DATE") private Date updateDate;
	@Column(name="CREATE_USER") private String createUser;
	@Column(name="UPDATE_USER") private String updateUser;
	
	@Column(name="ADJUST_STATUS") private String adjustStatus;
	@Column(name="ADJUST_APPROVE_REASON") private String adjustApvReason;	
	@Column(name="REMARK") private String remark;
	@Column(name="ANNOTATION") private String annotation;
	@Column(name="ADJUST_AREA_TYPE") private String adjustAreaType;
	@Column(name="FORCE_ADJ_TYPE") private String forceAdjType;
	@Column(name="EXPORT_FILE_ADJUST_TYPE") private String exportFileAdjustType;
	@Column(name="ADJUST_AREA_NO") private Long adjustAreaNo;
	@Column(name="PERIOD") private String period;
	
	@Column(name="AMOUNT_ADJMT_HEADER_NO_APV") private String apvNo;
	@Column(name="ADJMT_AMOUNT_TOTAL_CORRECT_APV") private BigDecimal adjustAmountTotalCorrectApv;//Correct Amount
	@Column(name="ADJMT_AMOUNT_TOTAL_DIF_APV") private BigDecimal adjustAmountTotalDifApv;//Difference
	@Column(name="ADJMT_AMOUNT_TOTAL_DIF_VAT_APV") private BigDecimal adjustAmountTotalDifVatApv;//Difference*vat
	@Column(name="ADJMT_AMOUNT_TOTAL_APV") private BigDecimal adjustAmountTotalApv;//Total Amount = Difference + Difference*vat
	
	@Column(name="ADJUST_CONDITION_TYPE") private String adjustConditionType;
	@Column(name="ADJUST_REFUND_TYPE") private String adjustRefundType;
	@Column(name="BANK_TYPE") private String bankType;
	@Column(name="BANK_ACCT_NAME") private String bankAcctName;
	@Column(name="BANK_ACCT_NO") private String bankAcctNo;
	@Column(name="ADJUST_SHOP_ID") private Long adjustShopId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Long getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public String getAdjustType() {
		return adjustType;
	}
	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}
	
	public String getAdjustReason() {
		return adjustReason;
	}
	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public BigDecimal getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public BigDecimal getAdjustAmountTotalCorrect() {
		return adjustAmountTotalCorrect;
	}
	public void setAdjustAmountTotalCorrect(BigDecimal adjustAmountTotalCorrect) {
		this.adjustAmountTotalCorrect = adjustAmountTotalCorrect;
	}
	public BigDecimal getAdjustAmountTotalDif() {
		return adjustAmountTotalDif;
	}
	public void setAdjustAmountTotalDif(BigDecimal adjustAmountTotalDif) {
		this.adjustAmountTotalDif = adjustAmountTotalDif;
	}
	public BigDecimal getAdjustAmountTotal() {
		return adjustAmountTotal;
	}
	public void setAdjustAmountTotal(BigDecimal adjustAmountTotal) {
		this.adjustAmountTotal = adjustAmountTotal;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public BigDecimal getAdjustAmountTotalDifVat() {
		return adjustAmountTotalDifVat;
	}
	public void setAdjustAmountTotalDifVat(BigDecimal adjustAmountTotalDifVat) {
		this.adjustAmountTotalDifVat = adjustAmountTotalDifVat;
	}
	public String getAdjustStatus() {
		return adjustStatus;
	}
	public void setAdjustStatus(String adjustStatus) {
		this.adjustStatus = adjustStatus;
	}
	public String getAdjustApvReason() {
		return adjustApvReason;
	}
	public void setAdjustApvReason(String adjustApvReason) {
		this.adjustApvReason = adjustApvReason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getExportFileAdjustType() {
		return exportFileAdjustType;
	}
	public void setExportFileAdjustType(String exportFileAdjustType) {
		this.exportFileAdjustType = exportFileAdjustType;
	}
	public Long getAdjustAreaNo() {
		return adjustAreaNo;
	}
	public void setAdjustAreaNo(Long adjustAreaNo) {
		this.adjustAreaNo = adjustAreaNo;
	}
	public String getAdjustAreaType() {
		return adjustAreaType;
	}
	public void setAdjustAreaType(String adjustAreaType) {
		this.adjustAreaType = adjustAreaType;
	}
	public String getForceAdjType() {
		return forceAdjType;
	}
	public void setForceAdjType(String forceAdjType) {
		this.forceAdjType = forceAdjType;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getApvNo() {
		return apvNo;
	}
	public void setApvNo(String apvNo) {
		this.apvNo = apvNo;
	}
	public BigDecimal getAdjustAmountTotalCorrectApv() {
		return adjustAmountTotalCorrectApv;
	}
	public void setAdjustAmountTotalCorrectApv(BigDecimal adjustAmountTotalCorrectApv) {
		this.adjustAmountTotalCorrectApv = adjustAmountTotalCorrectApv;
	}
	public BigDecimal getAdjustAmountTotalDifApv() {
		return adjustAmountTotalDifApv;
	}
	public void setAdjustAmountTotalDifApv(BigDecimal adjustAmountTotalDifApv) {
		this.adjustAmountTotalDifApv = adjustAmountTotalDifApv;
	}
	public BigDecimal getAdjustAmountTotalDifVatApv() {
		return adjustAmountTotalDifVatApv;
	}
	public void setAdjustAmountTotalDifVatApv(BigDecimal adjustAmountTotalDifVatApv) {
		this.adjustAmountTotalDifVatApv = adjustAmountTotalDifVatApv;
	}
	public BigDecimal getAdjustAmountTotalApv() {
		return adjustAmountTotalApv;
	}
	public void setAdjustAmountTotalApv(BigDecimal adjustAmountTotalApv) {
		this.adjustAmountTotalApv = adjustAmountTotalApv;
	}
	public String getAdjustConditionType() {
		return adjustConditionType;
	}
	public void setAdjustConditionType(String adjustConditionType) {
		this.adjustConditionType = adjustConditionType;
	}
	public String getAdjustRefundType() {
		return adjustRefundType;
	}
	public void setAdjustRefundType(String adjustRefundType) {
		this.adjustRefundType = adjustRefundType;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankAcctName() {
		return bankAcctName;
	}
	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	public Long getAdjustShopId() {
		return adjustShopId;
	}
	public void setAdjustShopId(Long adjustShopId) {
		this.adjustShopId = adjustShopId;
	}
}

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
@Table(name = "AMOUNT_ADJMT_DTL")
public class AmountAdjustmentDetail {
	@Id
	@SequenceGenerator(name="AMOUNT_ADJMT_DTL_SEQ", sequenceName="AMOUNT_ADJMT_DTL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AMOUNT_ADJMT_DTL_SEQ")
	@Column(name="AMOUNT_ADJMT_DTL_ID") private Long id;
	@Column(name="ADJUST_AMOUNT_ID") private Long adjustAmtId;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="INVOICEID") private Long invoiceId;
	@Column(name="ADJUST_AMOUNT") private BigDecimal adjustAmount;
	@Column(name="CONTRNO") private String contrno;
	@Column(name="BI_REF") private Long biRef;
	@Column(name="PRODUCT_CODE") private String prodCd;
	@Column(name="SUB_PRODUCT_CODE") private String subProdCd;
	@Column(name="CREATE_DATE") private Date createDate;
	@Column(name="UPDATE_DATE") private Date updateDate;
	@Column(name="CREATE_USER") private String createUser;
	@Column(name="UPDATE_USER") private String updateUser;
	@Column(name="APPROVE_AMOUNT") private BigDecimal approveAmount;
	@Column(name="ACCOUNT_CODE_NEW") private String accountCodeNew;
	@Column(name="REV_TYPE_CODE") private String revTypeCode;
	@Column(name="COST_CENTER") private String costCenter;
	@Column(name="TRADING_PART") private String tradingPart;
	@Column(name="POSTING_DATE") private String postingDate;
	@Column(name="SAP_ID") private Long sapId;
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
	
	public BigDecimal getAdjustAmount() {
		return adjustAmount;
	}
	public void setAdjustAmount(BigDecimal adjustAmount) {
		this.adjustAmount = adjustAmount;
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
	public String getContrno() {
		return contrno;
	}
	public void setContrno(String contrno) {
		this.contrno = contrno;
	}
	public Long getBiRef() {
		return biRef;
	}
	public void setBiRef(Long biRef) {
		this.biRef = biRef;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getSubProdCd() {
		return subProdCd;
	}
	public void setSubProdCd(String subProdCd) {
		this.subProdCd = subProdCd;
	}
	public Long getAdjustAmtId() {
		return adjustAmtId;
	}
	public void setAdjustAmtId(Long adjustAmtId) {
		this.adjustAmtId = adjustAmtId;
	}
	public BigDecimal getApproveAmount() {
		return approveAmount;
	}
	public void setApproveAmount(BigDecimal approveAmount) {
		this.approveAmount = approveAmount;
	}
	public String getAccountCodeNew() {
		return accountCodeNew;
	}
	public void setAccountCodeNew(String accountCodeNew) {
		this.accountCodeNew = accountCodeNew;
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
	public String getTradingPart() {
		return tradingPart;
	}
	public void setTradingPart(String tradingPart) {
		this.tradingPart = tradingPart;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public Long getSapId() {
		return sapId;
	}
	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}
}

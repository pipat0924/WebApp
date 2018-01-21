package th.net.cat.epis.dto.bouncecheque;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.net.cat.epis.util.converter.CustomDateDeserializer;
import th.net.cat.epis.util.converter.CustomDateSerializer;

public class Method {
	private String code;
	private String name;
	private String type;
	private BigDecimal amount;
	private String bankCode;
	private String bankName;
	private String bankBrnh;
	private String bankAcct;
	private Date transferDt;
	private String bankAcCd;
	private String chequeNo;
	private String chequeDt;
	private String cardNo;
	private String cardType;
	private String mnyOrderNo;
	private String mnyOrderCd;
	private Date mnyOrderDt;
	private String billExNo;
	private String billExCd;
	private String billExDt;
	private String couponNo;
	private String trnfNo;
	private Boolean isBackDt;
	private String offsetDocumentNo;
	private String offsetAccountCode;
	private String offsetAccountName;
	private String payChqBankCode;
	private String payChqBankName;
	private String payChqBranch;
	private Date payChqDate;
	private Date chequeDate;
	private String postCode;
	private Date updateDttm;
	private Date updateUser;
	private String otherType;
	private String otherNo;
	private Date otherDt;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBrnh() {
		return bankBrnh;
	}

	public void setBankBrnh(String bankBrnh) {
		this.bankBrnh = bankBrnh;
	}

	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getTransferDt() {
		return transferDt;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setTransferDt(Date transferDt) {
		this.transferDt = transferDt;
	}

	public String getBankAcCd() {
		return bankAcCd;
	}

	public void setBankAcCd(String bankAcCd) {
		this.bankAcCd = bankAcCd;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getChequeDt() {
		return chequeDt;
	}

	public void setChequeDt(String chequeDt) {
		this.chequeDt = chequeDt;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getMnyOrderNo() {
		return mnyOrderNo;
	}

	public void setMnyOrderNo(String mnyOrderNo) {
		this.mnyOrderNo = mnyOrderNo;
	}

	public String getMnyOrderCd() {
		return mnyOrderCd;
	}

	public void setMnyOrderCd(String mnyOrderCd) {
		this.mnyOrderCd = mnyOrderCd;
	}

	public Date getMnyOrderDt() {
		return mnyOrderDt;
	}

	public void setMnyOrderDt(Date mnyOrderDt) {
		this.mnyOrderDt = mnyOrderDt;
	}

	public String getBillExNo() {
		return billExNo;
	}

	public void setBillExNo(String billExNo) {
		this.billExNo = billExNo;
	}

	public String getBillExCd() {
		return billExCd;
	}

	public void setBillExCd(String billExCd) {
		this.billExCd = billExCd;
	}

	public String getBillExDt() {
		return billExDt;
	}

	public void setBillExDt(String billExDt) {
		this.billExDt = billExDt;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getTrnfNo() {
		return trnfNo;
	}

	public void setTrnfNo(String trnfNo) {
		this.trnfNo = trnfNo;
	}

	public Boolean getIsBackDt() {
		return isBackDt;
	}

	public void setIsBackDt(Boolean isBackDt) {
		this.isBackDt = isBackDt;
	}

	public String getOffsetDocumentNo() {
		return offsetDocumentNo;
	}

	public void setOffsetDocumentNo(String offsetDocumentNo) {
		this.offsetDocumentNo = offsetDocumentNo;
	}

	public String getOffsetAccountCode() {
		return offsetAccountCode;
	}

	public void setOffsetAccountCode(String offsetAccountCode) {
		this.offsetAccountCode = offsetAccountCode;
	}

	public String getOffsetAccountName() {
		return offsetAccountName;
	}

	public void setOffsetAccountName(String offsetAccountName) {
		this.offsetAccountName = offsetAccountName;
	}

	public String getPayChqBankCode() {
		return payChqBankCode;
	}

	public void setPayChqBankCode(String payChqBankCode) {
		this.payChqBankCode = payChqBankCode;
	}

	public String getPayChqBankName() {
		return payChqBankName;
	}

	public void setPayChqBankName(String payChqBankName) {
		this.payChqBankName = payChqBankName;
	}

	public String getPayChqBranch() {
		return payChqBranch;
	}

	public void setPayChqBranch(String payChqBranch) {
		this.payChqBranch = payChqBranch;
	}

	public Date getPayChqDate() {
		return payChqDate;
	}

	public void setPayChqDate(Date payChqDate) {
		this.payChqDate = payChqDate;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Date getUpdateDttm() {
		return updateDttm;
	}

	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}

	public Date getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Date updateUser) {
		this.updateUser = updateUser;
	}

	public String getOtherType() {
		return otherType;
	}

	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

	public Date getOtherDt() {
		return otherDt;
	}

	public void setOtherDt(Date otherDt) {
		this.otherDt = otherDt;
	}

}

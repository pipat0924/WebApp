package th.net.cat.epis.dto.bouncecheque;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.net.cat.epis.util.converter.CustomDateDeserializer;
import th.net.cat.epis.util.converter.CustomDateSerializer;

public class PayBounceChequeDTO {
	
	private String docHead;
	private String refDate;
	private String serviceKey3;
	private String currency;
	private String currencyCode;
	private Double amountARin;
	private Double rateChange;
	private Double amountARout;
	private Timestamp currencyChangeDateTimestamp;
	private Date currencyChangeDate;
	private Date currencyChangeDateDate;
	private String docNo;
	private Integer accountYear;
	private Timestamp docDateTimestamp;
	private Date docDate;
	private Date docDateDate;
	private String passDate;
	private String vatCode;
	private String tradingPartner;
	private String message;
	private String orgCode;
	private String docType;
	private String secment;
	private String prodKey2;
	private String arAccountCode;
	private String arName;
	private String glAccount;
	private String taxId;
	private String arGroup;
	private String regionKey1;
	private String branchAR;
	private String address;
	private String recordStatus;
	private String paymentCase;
	private BigDecimal amountTotalPay;
	private BigDecimal amountPay;
	
	private BigDecimal balanceDueDB;
	private BigDecimal vatRD;
	private BigDecimal amount;
	
	//			for report			//
	private String orderNo;
	private String no;
	private String brName;
	private Date upDDate;
	
	//			summarySapAr		//
	private Double foreignExchangeRate;
	private Double preItemsDiscount;
	private Double itemsDiscount;
	private Double charge;
	private Double vat;
	private Double totalCharge;
	private Double deduct;
	private Double fee;
	private Double penalty;
	private Double compensation;
	private Double discount;
	private Double balanceDue;
	private Double inputReceived;
	private Double change;
	private Double inputAdvanced;
	
	//			Bath			//
	private Double foreignExchangeRateBath;
	private Double preItemsDiscountBath;
	private Double itemsDiscountBath;
	private Double chargeBath;
	private Double vatBath;
	private Double totalChargeBath;
	private Double deductBath;
	private Double feeBath;
	private Double penaltyBath;
	private Double compensationBath;
	private Double discountBath;
	private Double balanceDueBath;
	private Double inputReceivedBath;
	private Double changeBath;
	private Double inputAdvancedBath;
//	sap_dept
	private Double localBalDueSap;
	private Double transBalDueSap;
	
	private List<SubPayBounceChequeDTO> subPayBounceChequeDTOList;
	
	public String getDocHead() {
		return docHead;
	}
	public void setDocHead(String docHead) {
		this.docHead = docHead;
	}
	public String getServiceKey3() {
		return serviceKey3;
	}
	public void setServiceKey3(String serviceKey3) {
		this.serviceKey3 = serviceKey3;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getAmountARin() {
		return amountARin;
	}
	public void setAmountARin(Double amountARin) {
		this.amountARin = amountARin;
	}
	public Double getRateChange() {
		return rateChange;
	}
	public void setRateChange(Double rateChange) {
		this.rateChange = rateChange;
	}
	public Double getAmountARout() {
		return amountARout;
	}
	public void setAmountARout(Double amountARout) {
		this.amountARout = amountARout;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getVatCode() {
		return vatCode;
	}
	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}
	public String getTradingPartner() {
		return tradingPartner;
	}
	public void setTradingPartner(String tradingPartner) {
		this.tradingPartner = tradingPartner;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getSecment() {
		return secment;
	}
	public void setSecment(String secment) {
		this.secment = secment;
	}
	public String getProdKey2() {
		return prodKey2;
	}
	public void setProdKey2(String prodKey2) {
		this.prodKey2 = prodKey2;
	}
	public List<SubPayBounceChequeDTO> getSubPayBounceChequeDTOList() {
		return subPayBounceChequeDTOList;
	}
	public void setSubPayBounceChequeDTOList(List<SubPayBounceChequeDTO> subPayBounceChequeDTOList) {
		this.subPayBounceChequeDTOList = subPayBounceChequeDTOList;
	}
	public String getRefDate() {
		return refDate;
	}
	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}
	public Integer getAccountYear() {
		return accountYear;
	}
	public void setAccountYear(Integer accountYear) {
		this.accountYear = accountYear;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCurrencyChangeDate() {
		return currencyChangeDate;
	}
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setCurrencyChangeDate(Date currencyChangeDate) {
		this.currencyChangeDate = currencyChangeDate;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDocDate() {
		return docDate;
	}
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}
	public String getPassDate() {
		return passDate;
	}
	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}
	public String getGlAccount() {
		return glAccount;
	}
	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getArGroup() {
		return arGroup;
	}
	public void setArGroup(String arGroup) {
		this.arGroup = arGroup;
	}
	public String getRegionKey1() {
		return regionKey1;
	}
	public void setRegionKey1(String regionKey1) {
		this.regionKey1 = regionKey1;
	}
	public String getBranchAR() {
		return branchAR;
	}
	public void setBranchAR(String branchAR) {
		this.branchAR = branchAR;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArAccountCode() {
		return arAccountCode;
	}
	public void setArAccountCode(String arAccountCode) {
		this.arAccountCode = arAccountCode;
	}
	public String getArName() {
		return arName;
	}
	public void setArName(String arName) {
		this.arName = arName;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCurrencyChangeDateDate() {
		return currencyChangeDateDate;
	}
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setCurrencyChangeDateDate(Date currencyChangeDateDate) {
		this.currencyChangeDateDate = currencyChangeDateDate;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDocDateDate() {
		return docDateDate;
	}
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDocDateDate(Date docDateDate) {
		this.docDateDate = docDateDate;
	}
	public Double getForeignExchangeRate() {
		return foreignExchangeRate;
	}
	public void setForeignExchangeRate(Double foreignExchangeRate) {
		this.foreignExchangeRate = foreignExchangeRate;
	}
	public Double getPreItemsDiscount() {
		return preItemsDiscount;
	}
	public void setPreItemsDiscount(Double preItemsDiscount) {
		this.preItemsDiscount = preItemsDiscount;
	}
	public Double getItemsDiscount() {
		return itemsDiscount;
	}
	public void setItemsDiscount(Double itemsDiscount) {
		this.itemsDiscount = itemsDiscount;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}
	public Double getDeduct() {
		return deduct;
	}
	public void setDeduct(Double deduct) {
		this.deduct = deduct;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getPenalty() {
		return penalty;
	}
	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}
	public Double getCompensation() {
		return compensation;
	}
	public void setCompensation(Double compensation) {
		this.compensation = compensation;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(Double balanceDue) {
		this.balanceDue = balanceDue;
	}
	public Double getInputReceived() {
		return inputReceived;
	}
	public void setInputReceived(Double inputReceived) {
		this.inputReceived = inputReceived;
	}
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
	}
	public Double getInputAdvanced() {
		return inputAdvanced;
	}
	public void setInputAdvanced(Double inputAdvanced) {
		this.inputAdvanced = inputAdvanced;
	}
	public Double getForeignExchangeRateBath() {
		return foreignExchangeRateBath;
	}
	public void setForeignExchangeRateBath(Double foreignExchangeRateBath) {
		this.foreignExchangeRateBath = foreignExchangeRateBath;
	}
	public Double getPreItemsDiscountBath() {
		return preItemsDiscountBath;
	}
	public void setPreItemsDiscountBath(Double preItemsDiscountBath) {
		this.preItemsDiscountBath = preItemsDiscountBath;
	}
	public Double getItemsDiscountBath() {
		return itemsDiscountBath;
	}
	public void setItemsDiscountBath(Double itemsDiscountBath) {
		this.itemsDiscountBath = itemsDiscountBath;
	}
	public Double getChargeBath() {
		return chargeBath;
	}
	public void setChargeBath(Double chargeBath) {
		this.chargeBath = chargeBath;
	}
	public Double getTotalChargeBath() {
		return totalChargeBath;
	}
	public void setTotalChargeBath(Double totalChargeBath) {
		this.totalChargeBath = totalChargeBath;
	}
	public Double getDeductBath() {
		return deductBath;
	}
	public void setDeductBath(Double deductBath) {
		this.deductBath = deductBath;
	}
	public Double getFeeBath() {
		return feeBath;
	}
	public void setFeeBath(Double feeBath) {
		this.feeBath = feeBath;
	}
	public Double getPenaltyBath() {
		return penaltyBath;
	}
	public void setPenaltyBath(Double penaltyBath) {
		this.penaltyBath = penaltyBath;
	}
	public Double getCompensationBath() {
		return compensationBath;
	}
	public void setCompensationBath(Double compensationBath) {
		this.compensationBath = compensationBath;
	}
	public Double getDiscountBath() {
		return discountBath;
	}
	public void setDiscountBath(Double discountBath) {
		this.discountBath = discountBath;
	}
	public Double getBalanceDueBath() {
		return balanceDueBath;
	}
	public void setBalanceDueBath(Double balanceDueBath) {
		this.balanceDueBath = balanceDueBath;
	}
	public Double getInputReceivedBath() {
		return inputReceivedBath;
	}
	public void setInputReceivedBath(Double inputReceivedBath) {
		this.inputReceivedBath = inputReceivedBath;
	}
	public Double getChangeBath() {
		return changeBath;
	}
	public void setChangeBath(Double changeBath) {
		this.changeBath = changeBath;
	}
	public Double getInputAdvancedBath() {
		return inputAdvancedBath;
	}
	public void setInputAdvancedBath(Double inputAdvancedBath) {
		this.inputAdvancedBath = inputAdvancedBath;
	}
	public Double getVatBath() {
		return vatBath;
	}
	public void setVatBath(Double vatBath) {
		this.vatBath = vatBath;
	}
	public Timestamp getCurrencyChangeDateTimestamp() {
		return currencyChangeDateTimestamp;
	}
	public void setCurrencyChangeDateTimestamp(Timestamp currencyChangeDateTimestamp) {
		this.currencyChangeDateTimestamp = currencyChangeDateTimestamp;
	}
	public Timestamp getDocDateTimestamp() {
		return docDateTimestamp;
	}
	public void setDocDateTimestamp(Timestamp docDateTimestamp) {
		this.docDateTimestamp = docDateTimestamp;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getPaymentCase() {
		return paymentCase;
	}
	public void setPaymentCase(String paymentCase) {
		this.paymentCase = paymentCase;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public Date getUpDDate() {
		return upDDate;
	}
	public void setUpDDate(Date upDDate) {
		this.upDDate = upDDate;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getBalanceDueDB() {
		return balanceDueDB;
	}
	public void setBalanceDueDB(BigDecimal balanceDueDB) {
		this.balanceDueDB = balanceDueDB;
	}
	public BigDecimal getAmountTotalPay() {
		return amountTotalPay;
	}
	public void setAmountTotalPay(BigDecimal amountTotalPay) {
		this.amountTotalPay = amountTotalPay;
	}
	public BigDecimal getAmountPay() {
		return amountPay;
	}
	public void setAmountPay(BigDecimal amountPay) {
		this.amountPay = amountPay;
	}
	public BigDecimal getVatRD() {
		return vatRD;
	}
	public void setVatRD(BigDecimal vatRD) {
		this.vatRD = vatRD;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Double getLocalBalDueSap() {
		return localBalDueSap;
	}
	public void setLocalBalDueSap(Double localBalDueSap) {
		this.localBalDueSap = localBalDueSap;
	}
	public Double getTransBalDueSap() {
		return transBalDueSap;
	}
	public void setTransBalDueSap(Double transBalDueSap) {
		this.transBalDueSap = transBalDueSap;
	}
	
	
}

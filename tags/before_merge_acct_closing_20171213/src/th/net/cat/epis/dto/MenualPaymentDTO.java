package th.net.cat.epis.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenualPaymentDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7515510718797462797L;
	private String custNo;
	private String custName;
	private String souceType;
	private String custType;
	private String taxId;
	private String custBranch;
	private String billGroup;
	private String collectionUnit;
	private BigDecimal outstanding;
	private String address1;
	private String address2;
	private String remark;
	private Boolean split = false;
	private List<InvoiceMenualDTO> invoiceList = new ArrayList<InvoiceMenualDTO>();
	private ManualDTO manual = new ManualDTO();
	private String custSubNo; 
	private String additionalRemark;
	private String branch;
	private BigDecimal  prepaid;
	private String egpNo;
	private String egpContract;
	private String currencyCode;
	private BigDecimal currencyRate;
	private String agentAddressCode; //by NSD 01-03-2017
	private String acctCatLkp;//by NSD 02-03-2017
	private String catCustomerSegment;
	private String custCategoryDesc;//by NSD 08-03-2017
	private String ref1;
	private String invoiceDisplay;//by NSD 24-03-2017
	private BigDecimal excDiscount;
	private BigDecimal afterSaleDiscVat;
	private String receiptFormat;
	private String agentTaxNo; // by W3P 03-07-2017
	private BigDecimal feesIncome; // by W3P 04-07-2017
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getSouceType() {
		return souceType;
	}
	public void setSouceType(String souceType) {
		this.souceType = souceType;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getCustBranch() {
		return custBranch;
	}
	public void setCustBranch(String custBranch) {
		this.custBranch = custBranch;
	}
	public String getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}
	public BigDecimal getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(BigDecimal outstanding) {
		this.outstanding = outstanding;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getSplit() {
		return split;
	}
	public void setSplit(Boolean split) {
		this.split = split;
	}
	
	
	public List<InvoiceMenualDTO> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(List<InvoiceMenualDTO> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public ManualDTO getManual() {
		return manual;
	}
	public void setManual(ManualDTO manual) {
		this.manual = manual;
	}
	public String getCustSubNo() {
		return custSubNo;
	}
	public void setCustSubNo(String custSubNo) {
		this.custSubNo = custSubNo;
	}
	public String getAdditionalRemark() {
		return additionalRemark;
	}
	public void setAdditionalRemark(String additionalRemark) {
		this.additionalRemark = additionalRemark;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public BigDecimal getPrepaid() {
		return prepaid;
	}
	public void setPrepaid(BigDecimal prepaid) {
		this.prepaid = prepaid;
	}
	public String getEgpNo() {
		return egpNo;
	}
	public void setEgpNo(String egpNo) {
		this.egpNo = egpNo;
	}
	public String getEgpContract() {
		return egpContract;
	}
	public void setEgpContract(String egpContract) {
		this.egpContract = egpContract;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(BigDecimal currencyRate) {
		this.currencyRate = currencyRate;
	}
	public String getAgentAddressCode() {
		return agentAddressCode;
	}
	public void setAgentAddressCode(String agentAddressCode) {
		this.agentAddressCode = agentAddressCode;
	}
	public String getAcctCatLkp() {
		return acctCatLkp;
	}
	public void setAcctCatLkp(String acctCatLkp) {
		this.acctCatLkp = acctCatLkp;
	}
	public String getCatCustomerSegment() {
		return catCustomerSegment;
	}
	public void setCatCustomerSegment(String catCustomerSegment) {
		this.catCustomerSegment = catCustomerSegment;
	}
	public String getCustCategoryDesc() {
		return custCategoryDesc;
	}
	public void setCustCategoryDesc(String custCategoryDesc) {
		this.custCategoryDesc = custCategoryDesc;
	}
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	public String getInvoiceDisplay() {
		return invoiceDisplay;
	}
	public void setInvoiceDisplay(String invoiceDisplay) {
		this.invoiceDisplay = invoiceDisplay;
	}
	public BigDecimal getExcDiscount() {
		return excDiscount;
	}
	public void setExcDiscount(BigDecimal excDiscount) {
		this.excDiscount = excDiscount;
	}
	public BigDecimal getAfterSaleDiscVat() {
		return afterSaleDiscVat;
	}
	public void setAfterSaleDiscVat(BigDecimal afterSaleDiscVat) {
		this.afterSaleDiscVat = afterSaleDiscVat;
	}
	public String getReceiptFormat() {
		return receiptFormat;
	}
	public void setReceiptFormat(String receiptFormat) {
		this.receiptFormat = receiptFormat;
	}
	public String getAgentTaxNo() {
		return agentTaxNo;
	}
	public void setAgentTaxNo(String agentTaxNo) {
		this.agentTaxNo = agentTaxNo;
	}
	public BigDecimal getFeesIncome() {
		return feesIncome;
	}
	public void setFeesIncome(BigDecimal feesIncome) {
		this.feesIncome = feesIncome;
	}
	
	
}

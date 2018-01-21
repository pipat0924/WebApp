package th.net.cat.epis.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Na Sanfeng on 10/24/2016.
 */
public class PaidInvoiceDTO extends CommonStatus<Invoice>{

    private String invoiceno;
    private String receiptno;
    private Date receiptdttm;//Date
    private String accountno;
    private String accountname;
    private String paymentcase;
    private String branchname;
    private BigDecimal totalcharge;//BigDecimal
    private String updateuser;

    private String issuedate;//update 24-11-2016
    private Date duedate;
    private BigDecimal charge;
    private BigDecimal discount;
    private BigDecimal balancedue;
    private BigDecimal deduct;
    private String billcycle;
    private BigDecimal received;
    private BigDecimal vat;
    private String accounttype;
    private BigDecimal revertAmt = new BigDecimal(0);// revert amount each invoice


    private String receiptdttmStr;
    private String totalchargeStr;
    private String duedateStr;

    private List<RevertInvProDto> productList;

    private String reqReason;
    private String docStatus;
    private String revertReqNo;
    private BigDecimal revertAmount;//total revert amount of receipt
    private String reqDate;
    private String customerAddress;
    private String taxId;
    private String reqUser;
    private String branchCode;// request branch code
    private String customerBranch;
    private Long revertInvDtlId;

    private String apprUser;
    private String apprReason;
    private BigDecimal apprRevertAmt=new BigDecimal(0); // approve revert amount of invoice
    private BigDecimal apprRevertAmount;// total approve revert amount of receipt
    private Date issuedt;
    private String revertApprNo;

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getReceiptno() {
        return receiptno;
    }

    public void setReceiptno(String receiptno) {
        this.receiptno = receiptno;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getPaymentcase() {
        return paymentcase;
    }

    public void setPaymentcase(String paymentcase) {
        this.paymentcase = paymentcase;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Date getReceiptdttm() {
        return receiptdttm;
    }

    public void setReceiptdttm(Date receiptdttm) {
        this.receiptdttm = receiptdttm;
    }

    public BigDecimal getTotalcharge() {
        return totalcharge;
    }

    public void setTotalcharge(BigDecimal totalcharge) {
        this.totalcharge = totalcharge;
    }

    public String getReceiptdttmStr() {
        return receiptdttmStr;
    }

    public void setReceiptdttmStr(String receiptdttmStr) {
        this.receiptdttmStr = receiptdttmStr;
    }

    public String getTotalchargeStr() {
        return totalchargeStr;
    }

    public void setTotalchargeStr(String totalchargeStr) {
        this.totalchargeStr = totalchargeStr;
    }

    public List<RevertInvProDto> getProductList() {
        return productList;
    }

    public void setProductList(List<RevertInvProDto> productList) {
        this.productList = productList;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getBalancedue() {
        return balancedue;
    }

    public void setBalancedue(BigDecimal balancedue) {
        this.balancedue = balancedue;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public String getBillcycle() {
        return billcycle;
    }

    public void setBillcycle(String billcycle) {
        this.billcycle = billcycle;
    }

    public BigDecimal getReceived() {
        return received;
    }

    public void setReceived(BigDecimal received) {
        this.received = received;
    }

    public String getDuedateStr() {
        return duedateStr;
    }

    public void setDuedateStr(String duedateStr) {
        this.duedateStr = duedateStr;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public BigDecimal getRevertAmt() {
        return revertAmt;
    }

    public void setRevertAmt(BigDecimal revertAmt) {
        this.revertAmt = revertAmt;
    }

    public String getReqReason() {
        return reqReason;
    }

    public void setReqReason(String reqReason) {
        this.reqReason = reqReason;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getRevertReqNo() {
        return revertReqNo;
    }

    public void setRevertReqNo(String revertReqNo) {
        this.revertReqNo = revertReqNo;
    }

    public BigDecimal getRevertAmount() {
        return revertAmount;
    }

    public void setRevertAmount(BigDecimal revertAmount) {
        this.revertAmount = revertAmount;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getReqUser() {
        return reqUser;
    }

    public void setReqUser(String reqUser) {
        this.reqUser = reqUser;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCustomerBranch() {
        return customerBranch;
    }

    public void setCustomerBranch(String customerBranch) {
        this.customerBranch = customerBranch;
    }

    public Long getRevertInvDtlId() {
        return revertInvDtlId;
    }

    public void setRevertInvDtlId(Long revertInvDtlId) {
        this.revertInvDtlId = revertInvDtlId;
    }

    public String getApprUser() {
        return apprUser;
    }

    public void setApprUser(String apprUser) {
        this.apprUser = apprUser;
    }

    public String getApprReason() {
        return apprReason;
    }

    public void setApprReason(String apprReason) {
        this.apprReason = apprReason;
    }

    public BigDecimal getApprRevertAmount() {
        return apprRevertAmount;
    }

    public void setApprRevertAmount(BigDecimal apprRevertAmount) {
        this.apprRevertAmount = apprRevertAmount;
    }

    public Date getIssuedt() {
        return issuedt;
    }

    public void setIssuedt(Date issuedt) {
        this.issuedt = issuedt;
    }

    public BigDecimal getApprRevertAmt() {
        return apprRevertAmt;
    }

    public void setApprRevertAmt(BigDecimal apprRevertAmt) {
        this.apprRevertAmt = apprRevertAmt;
    }

    public String getRevertApprNo() {
        return revertApprNo;
    }

    public void setRevertApprNo(String revertApprNo) {
        this.revertApprNo = revertApprNo;
    }
}

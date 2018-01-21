package th.net.cat.epis.to.report;

import java.io.Serializable;

/**
 * Created by nastanda on 12/2/2016 AD.
 */
public class RevertPaymentReqDTO implements Serializable {

    private static final long serialVersionUID = 6031163194081461661L;

    // header part
        // customer part
    private String accountNo;
    private String accountName; // don't know yet.[dn]
    private String customerAddress; // dn
    private String taxId; // dn
    private String customerBranch; // dn

    private String printDate;
    private String branchCode;
    private String branchName;
    private String revertReqNo;
    private String receiptNo;
    private String reqDate;
    private String apprDate;

    private String ttRevertAmount;
    private String reqReason;
    private String reqUser;
    private String checkUser; // dn
    private String apprUser;
    private String ttRevertAmountThDesc;
    private String ttApprRevertAmount;
    private String ttApprRevertAmountThDesc;

    // detail part
    private String invoiceNo;
    private String billcycle; // haven't stored
    private String totalCharge;
    private String revertAmount;
    private String apprRevertAmount;
    private String apprReason;
    private String revertApprNo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getCustomerBranch() {
        return customerBranch;
    }

    public void setCustomerBranch(String customerBranch) {
        this.customerBranch = customerBranch;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRevertReqNo() {
        return revertReqNo;
    }

    public void setRevertReqNo(String revertReqNo) {
        this.revertReqNo = revertReqNo;
    }

    public String getTtRevertAmount() {
        return ttRevertAmount;
    }

    public void setTtRevertAmount(String ttRevertAmount) {
        this.ttRevertAmount = ttRevertAmount;
    }

    public String getReqReason() {
        return reqReason;
    }

    public void setReqReason(String reqReason) {
        this.reqReason = reqReason;
    }

    public String getReqUser() {
        return reqUser;
    }

    public void setReqUser(String reqUser) {
        this.reqUser = reqUser;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getApprUser() {
        return apprUser;
    }

    public void setApprUser(String apprUser) {
        this.apprUser = apprUser;
    }

    public String getTtRevertAmountThDesc() {
        return ttRevertAmountThDesc;
    }

    public void setTtRevertAmountThDesc(String ttRevertAmountThDesc) {
        this.ttRevertAmountThDesc = ttRevertAmountThDesc;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getBillcycle() {
        return billcycle;
    }

    public void setBillcycle(String billcycle) {
        this.billcycle = billcycle;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getRevertAmount() {
        return revertAmount;
    }

    public void setRevertAmount(String revertAmount) {
        this.revertAmount = revertAmount;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getTtApprRevertAmount() {
        return ttApprRevertAmount;
    }

    public void setTtApprRevertAmount(String ttApprRevertAmount) {
        this.ttApprRevertAmount = ttApprRevertAmount;
    }

    public String getApprRevertAmount() {
        return apprRevertAmount;
    }

    public void setApprRevertAmount(String apprRevertAmount) {
        this.apprRevertAmount = apprRevertAmount;
    }

    public String getApprReason() {
        return apprReason;
    }

    public void setApprReason(String apprReason) {
        this.apprReason = apprReason;
    }

    public String getApprDate() {
        return apprDate;
    }

    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    public String getTtApprRevertAmountThDesc() {
        return ttApprRevertAmountThDesc;
    }

    public void setTtApprRevertAmountThDesc(String ttApprRevertAmountThDesc) {
        this.ttApprRevertAmountThDesc = ttApprRevertAmountThDesc;
    }

    public String getRevertApprNo() {
        return revertApprNo;
    }

    public void setRevertApprNo(String revertApprNo) {
        this.revertApprNo = revertApprNo;
    }
}

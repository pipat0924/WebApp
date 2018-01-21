package th.net.cat.epis.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by nastanda on 11/29/2016 AD.
 */
@Entity
@Table(name = "REVERT_PAYMENT_MST")
public class RevertPaymentMst {
    @EmbeddedId
    private RevertPaymentPK revertPaymentPK;
    @Column(name = "REVERT_APPR_NO")
    private String revertApprNo;
    @Column(name = "ACCOUNT_NO")
    private String accountNo;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "PAYMENT_CASE")
    private String paymentCase;
    @Column(name = "TOTAL_CHARGE")
    private BigDecimal totalCharge;
    @Column(name = "REVERT_AMOUNT")
    private BigDecimal revertAmount;
    @Column(name = "BRANCH_CODE")
    private String branchCode;
    @Column(name = "BRANCH_NAME")
    private String branchName;
    @Column(name = "REVERT_METHOD")
    private String revertMethod;
    @Column(name = "REQ_REASON")
    private String reqReason;
    @Column(name = "REQ_USER")
    private String reqUser;
    @Column(name = "REQ_DTTM")
    private Date reqDttm;
    @Column(name = "APPR_USER")
    private String apprUser;
    @Column(name = "APPR_DTTM")
    private Date apprDttm;
    @Column(name = "DOC_STATUS")
    private String docStatus;
    @Column(name = "RECEIVE_BRANCH_CODE")
    private String receiveBranchCode;
    @Column(name = "RECEIVE_BRANCH_NAME")
    private String receiveBranchName;
    @Column(name = "RECEIVE_BANK")
    private String receiveBank;
    @Column(name = "RECEIVE_BANK_BRANCH_CODE")
    private String receiveBankBranchCode;
    @Column(name = "RECEIVE_BANK_BRANCH_NAME")
    private String receiveBankBranchName;
    @Column(name = "RECEIVE_ACCOUNT_ID")
    private String receiveAccountId;
    @Column(name = "RECEIVE_ACCOUNT_NAME")
    private String receiveAccountName;
    @Column(name = "CREATED_DTM")
    private Date createdDtm;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPD_DTM")
    private Date lastUpdDtm;
    @Column(name = "LAST_UPD_BY")
    private String lastUpdBy;

    //update 15-12-2016
    @Column(name = "APPR_REVERT_AMOUNT")
    private BigDecimal apprRevertAmount;
    @Column(name = "APPR_REASON")
    private String apprReason;


    public String getRevertApprNo() {
        return revertApprNo;
    }

    public void setRevertApprNo(String revertApprNo) {
        this.revertApprNo = revertApprNo;
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

    public String getPaymentCase() {
        return paymentCase;
    }

    public void setPaymentCase(String paymentCase) {
        this.paymentCase = paymentCase;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public BigDecimal getRevertAmount() {
        return revertAmount;
    }

    public void setRevertAmount(BigDecimal revertAmount) {
        this.revertAmount = revertAmount;
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

    public String getRevertMethod() {
        return revertMethod;
    }

    public void setRevertMethod(String revertMethod) {
        this.revertMethod = revertMethod;
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

    public Date getReqDttm() {
        return reqDttm;
    }

    public void setReqDttm(Date reqDttm) {
        this.reqDttm = reqDttm;
    }

    public String getApprUser() {
        return apprUser;
    }

    public void setApprUser(String apprUser) {
        this.apprUser = apprUser;
    }

    public Date getApprDttm() {
        return apprDttm;
    }

    public void setApprDttm(Date apprDttm) {
        this.apprDttm = apprDttm;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getReceiveBranchCode() {
        return receiveBranchCode;
    }

    public void setReceiveBranchCode(String receiveBranchCode) {
        this.receiveBranchCode = receiveBranchCode;
    }

    public String getReceiveBranchName() {
        return receiveBranchName;
    }

    public void setReceiveBranchName(String receiveBranchName) {
        this.receiveBranchName = receiveBranchName;
    }

    public String getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    public String getReceiveBankBranchCode() {
        return receiveBankBranchCode;
    }

    public void setReceiveBankBranchCode(String receiveBankBranchCode) {
        this.receiveBankBranchCode = receiveBankBranchCode;
    }

    public String getReceiveBankBranchName() {
        return receiveBankBranchName;
    }

    public void setReceiveBankBranchName(String receiveBankBranchName) {
        this.receiveBankBranchName = receiveBankBranchName;
    }

    public String getReceiveAccountId() {
        return receiveAccountId;
    }

    public void setReceiveAccountId(String receiveAccountId) {
        this.receiveAccountId = receiveAccountId;
    }

    public String getReceiveAccountName() {
        return receiveAccountName;
    }

    public void setReceiveAccountName(String receiveAccountName) {
        this.receiveAccountName = receiveAccountName;
    }

    public Date getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(Date createdDtm) {
        this.createdDtm = createdDtm;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdDtm() {
        return lastUpdDtm;
    }

    public void setLastUpdDtm(Date lastUpdDtm) {
        this.lastUpdDtm = lastUpdDtm;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public RevertPaymentPK getRevertPaymentPK() {
        return revertPaymentPK;
    }

    public void setRevertPaymentPK(RevertPaymentPK revertPaymentPK) {
        this.revertPaymentPK = revertPaymentPK;
    }

    public BigDecimal getApprRevertAmount() {
        return apprRevertAmount;
    }

    public void setApprRevertAmount(BigDecimal apprRevertAmount) {
        this.apprRevertAmount = apprRevertAmount;
    }

    public String getApprReason() {
        return apprReason;
    }

    public void setApprReason(String apprReason) {
        this.apprReason = apprReason;
    }
}


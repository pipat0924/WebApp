package th.net.cat.epis.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by nastanda on 11/29/2016 AD.
 */
@Entity
@Table(name = "REVERT_PAYMENT_INVOICE_DTL")
public class RevertPaymentInvoiceDtl {
    @Id
    @SequenceGenerator(name="REVERT_INV_DTL_SEQ", sequenceName="REVERT_INV_DTL_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVERT_INV_DTL_SEQ")
    @Column(name = "REVERT_INV_DTL_ID")
    private Long revertInvDtlId;

    @Column(name = "INVOICE_NO")
    private String invoiceNo;

    @Column(name = "TOTAL_CHARGE")
    private BigDecimal totalCharge;

    @Column(name = "RECEIVED_AMOUNT")
    private BigDecimal receivedAmount;

    @Column(name = "BILLCYCLE")
    private String billcycle;

    @Column(name = "REVERT_AMOUNT")
    private BigDecimal revertAmount;

    @Column(name = "REVERT_REQ_NO")
    private String revertReqNo;

    @Column(name = "RECEIPT_NO")
    private String receiptNo;

    @Column(name = "CREATED_DTM")
    private Date createdDtm;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPD_DTM")
    private Date lastUpdDtm;
    @Column(name = "LAST_UPD_BY")
    private String lastUpdBy;
    //update 15-12-2016
    @Column(name = "ISSUEDATE")
    private Date issuedate;
    @Column(name = "DUEDATE")
    private Date duedate;
    @Column(name = "CHARGE")
    private BigDecimal charge;
    @Column(name = "DISCOUNT")
    private BigDecimal discount;
    @Column(name = "VAT")
    private BigDecimal vat;
    @Column(name = "BALANCEDUE")
    private BigDecimal balancedue;
    @Column(name = "DEDUCT")
    private BigDecimal deduct;
    @Column(name = "APPR_REVERT_AMOUNT")
    private BigDecimal apprRevertAmount;

    @Transient
    private List<RevertPaymentProductDtl> revertPaymentProductDtls;

    public Long getRevertInvDtlId() {
        return revertInvDtlId;
    }

    public void setRevertInvDtlId(Long revertInvDtlId) {
        this.revertInvDtlId = revertInvDtlId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public String getRevertReqNo() {
        return revertReqNo;
    }

    public void setRevertReqNo(String revertReqNo) {
        this.revertReqNo = revertReqNo;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
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

    public List<RevertPaymentProductDtl> getRevertPaymentProductDtls() {
        return revertPaymentProductDtls;
    }

    public void setRevertPaymentProductDtls(List<RevertPaymentProductDtl> revertPaymentProductDtls) {
        this.revertPaymentProductDtls = revertPaymentProductDtls;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getBillcycle() {
        return billcycle;
    }

    public void setBillcycle(String billcycle) {
        this.billcycle = billcycle;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
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

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
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

    public BigDecimal getApprRevertAmount() {
        return apprRevertAmount;
    }

    public void setApprRevertAmount(BigDecimal apprRevertAmount) {
        this.apprRevertAmount = apprRevertAmount;
    }
}

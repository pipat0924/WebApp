package th.net.cat.epis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by imake on 13/02/2017.
 */
@Entity
@Table(name = "CREDIT_LIMIT_TRANS")
@IdClass(CreditLimitTransEntityPK.class)
public class CreditLimitTransEntity implements Serializable{
    private static final long serialVersionUID = 2034316658299808517L;
    private String contract;
    private String arRef;
    private String payType;
    private String amountIncVat;
    private String payDate;
    private String msisdn;
    private String receiptId;
    private String vatAmount;
    private String amountExVat;
    private String postDate;
    private String accountNo;
    private String arInvdate;
    private String arDuedate;
    private String status;
    private Timestamp updatedTime;

    @Id
    @Column(name = "CONTRACT")
    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    @Id
    @Column(name = "AR_REF")
    public String getArRef() {
        return arRef;
    }

    public void setArRef(String arRef) {
        this.arRef = arRef;
    }

    @Basic
    @Column(name = "PAY_TYPE")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "AMOUNT_INC_VAT")
    public String getAmountIncVat() {
        return amountIncVat;
    }

    public void setAmountIncVat(String amountIncVat) {
        this.amountIncVat = amountIncVat;
    }

    @Basic
    @Column(name = "PAY_DATE")
    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    @Basic
    @Column(name = "MSISDN")
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Id
    @Column(name = "RECEIPT_ID")
    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    @Basic
    @Column(name = "VAT_AMOUNT")
    public String getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(String vatAmount) {
        this.vatAmount = vatAmount;
    }

    @Basic
    @Column(name = "AMOUNT_EX_VAT")
    public String getAmountExVat() {
        return amountExVat;
    }

    public void setAmountExVat(String amountExVat) {
        this.amountExVat = amountExVat;
    }

    @Basic
    @Column(name = "POST_DATE")
    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    @Basic
    @Column(name = "ACCOUNT_NO")
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Basic
    @Column(name = "AR_INVDATE")
    public String getArInvdate() {
        return arInvdate;
    }

    public void setArInvdate(String arInvdate) {
        this.arInvdate = arInvdate;
    }

    @Basic
    @Column(name = "AR_DUEDATE")
    public String getArDuedate() {
        return arDuedate;
    }

    public void setArDuedate(String arDuedate) {
        this.arDuedate = arDuedate;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "UPDATED_TIME")
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditLimitTransEntity that = (CreditLimitTransEntity) o;

        if (contract != null ? !contract.equals(that.contract) : that.contract != null) return false;
        if (arRef != null ? !arRef.equals(that.arRef) : that.arRef != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (amountIncVat != null ? !amountIncVat.equals(that.amountIncVat) : that.amountIncVat != null) return false;
        if (payDate != null ? !payDate.equals(that.payDate) : that.payDate != null) return false;
        if (msisdn != null ? !msisdn.equals(that.msisdn) : that.msisdn != null) return false;
        if (receiptId != null ? !receiptId.equals(that.receiptId) : that.receiptId != null) return false;
        if (vatAmount != null ? !vatAmount.equals(that.vatAmount) : that.vatAmount != null) return false;
        if (amountExVat != null ? !amountExVat.equals(that.amountExVat) : that.amountExVat != null) return false;
        if (postDate != null ? !postDate.equals(that.postDate) : that.postDate != null) return false;
        if (accountNo != null ? !accountNo.equals(that.accountNo) : that.accountNo != null) return false;
        if (arInvdate != null ? !arInvdate.equals(that.arInvdate) : that.arInvdate != null) return false;
        if (arDuedate != null ? !arDuedate.equals(that.arDuedate) : that.arDuedate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contract != null ? contract.hashCode() : 0;
        result = 31 * result + (arRef != null ? arRef.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (amountIncVat != null ? amountIncVat.hashCode() : 0);
        result = 31 * result + (payDate != null ? payDate.hashCode() : 0);
        result = 31 * result + (msisdn != null ? msisdn.hashCode() : 0);
        result = 31 * result + (receiptId != null ? receiptId.hashCode() : 0);
        result = 31 * result + (vatAmount != null ? vatAmount.hashCode() : 0);
        result = 31 * result + (amountExVat != null ? amountExVat.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (accountNo != null ? accountNo.hashCode() : 0);
        result = 31 * result + (arInvdate != null ? arInvdate.hashCode() : 0);
        result = 31 * result + (arDuedate != null ? arDuedate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        return result;
    }
}

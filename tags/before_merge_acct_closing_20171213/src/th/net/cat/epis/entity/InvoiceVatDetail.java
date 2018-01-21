package th.net.cat.epis.entity;

import th.net.cat.epis.util.AppUtil;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by nastanda on 4/8/2017 AD.
 */
@Entity
@Table(name="TMPINVOICEVATDETAIL")
public class InvoiceVatDetail {
    @Id
    @SequenceGenerator(name="invoice_dtl_vat_seq", sequenceName="invoice_dtl_vat_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoice_dtl_vat_seq")
    @Column(name="INVOICEVATDTLID") private Long id;

    @Column(name="INVOICEID") private Long invoiceId;
    @Column(name="INVOICENO") private String invoiceNo;
    @Column(name="ACCOUNTNO") private String accountNo;
    @Column(name="AMOUNT") private BigDecimal amount;
    @Column(name="VAT") private BigDecimal vat;
    @Column(name="ISSUEDATE") private Date issueDt;
    @Column(name="DUEDATE") private Date dueDt;
    @Column(name="CHARGEFROMDATE") private Date chargeFromDt;
    @Column(name="CHARGETODATE") private Date chargeToDt;
    @Column(name="TAXTYPECODE") private String taxTypeCode;
    @Column(name="UPDATEDTTM") private Date updateDttm;
    @Column(name="UPDATEUSER") private String updateUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public Date getIssueDt() {
        return issueDt;
    }

    public void setIssueDt(java.util.Date issueDt) {
        this.issueDt = AppUtil.convertJavaDateToSqlDate(issueDt);
    }

    public Date getDueDt() {
        return dueDt;
    }

    public void setDueDt(java.util.Date dueDt) {
        this.dueDt = AppUtil.convertJavaDateToSqlDate(dueDt);
    }

    public Date getChargeFromDt() {
        return chargeFromDt;
    }

    public void setChargeFromDt(java.util.Date chargeFromDt) {
        this.chargeFromDt = AppUtil.convertJavaDateToSqlDate(chargeFromDt);
    }

    public Date getChargeToDt() {
        return chargeToDt;
    }

    public void setChargeToDt(java.util.Date chargeToDt) {
        this.chargeToDt = AppUtil.convertJavaDateToSqlDate(chargeToDt);
    }

    public String getTaxTypeCode() {
        return taxTypeCode;
    }

    public void setTaxTypeCode(String taxTypeCode) {
        this.taxTypeCode = taxTypeCode;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(java.util.Date updateDttm) {
        this.updateDttm = AppUtil.convertJavaDateToSqlDate(updateDttm);
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}

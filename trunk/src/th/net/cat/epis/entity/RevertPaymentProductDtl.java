package th.net.cat.epis.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by nastanda on 11/29/2016 AD.
 */
@Entity
@Table(name = "REVERT_PAYMENT_PRODUCT_DTL")
public class RevertPaymentProductDtl {
    @Id
    @SequenceGenerator(name="REVERT_PRO_DTL_SEQ", sequenceName="REVERT_PRO_DTL_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVERT_PRO_DTL_SEQ")
    @Column(name = "REVERT_PRO_DTL_ID")
    private Long revertProDtlId;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "SUB_PRODUCT_CODE")
    private String subProductCode;

    @Column(name = "SUB_PRODUCT_NAME")
    private String subProductName;

    @Column(name = "REVENUE_TYPE_CODE")
    private String revenueTypeCode;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "REVERT_AMOUNT")
    private BigDecimal revertAmount;

    @Column(name = "REVERT_INV_DTL_ID")
    private Long revertInvDtlId;

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

    public Long getRevertProDtlId() {
        return revertProDtlId;
    }

    public void setRevertProDtlId(Long revertProDtlId) {
        this.revertProDtlId = revertProDtlId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubProductCode() {
        return subProductCode;
    }

    public void setSubProductCode(String subProductCode) {
        this.subProductCode = subProductCode;
    }

    public String getSubProductName() {
        return subProductName;
    }

    public void setSubProductName(String subProductName) {
        this.subProductName = subProductName;
    }

    public String getRevenueTypeCode() {
        return revenueTypeCode;
    }

    public void setRevenueTypeCode(String revenueTypeCode) {
        this.revenueTypeCode = revenueTypeCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRevertAmount() {
        return revertAmount;
    }

    public void setRevertAmount(BigDecimal revertAmount) {
        this.revertAmount = revertAmount;
    }

    public Long getRevertInvDtlId() {
        return revertInvDtlId;
    }

    public void setRevertInvDtlId(Long revertInvDtlId) {
        this.revertInvDtlId = revertInvDtlId;
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

    public BigDecimal getApprRevertAmount() {
        return apprRevertAmount;
    }

    public void setApprRevertAmount(BigDecimal apprRevertAmount) {
        this.apprRevertAmount = apprRevertAmount;
    }
}

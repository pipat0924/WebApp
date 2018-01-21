package th.net.cat.epis.dto;

import java.math.BigDecimal;

/**
 * Created by Na Sanfeng on 11/1/2016.
 */
public class RevertInvProDto {
    private String billRefNo;
    private String revTypeCode;
    private String revTypeName;
    private String productCode;
    private String productName;
    private String subProductCode;
    private String subProductName;
    private BigDecimal amount;
    private BigDecimal revertAmt=new BigDecimal(0);
    private BigDecimal apprRevertAmount = new BigDecimal(0);
    private Long revertProDtlId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBillRefNo() {
        return billRefNo;
    }

    public void setBillRefNo(String billRefNo) {
        this.billRefNo = billRefNo;
    }

    public String getRevTypeCode() {
        return revTypeCode;
    }

    public void setRevTypeCode(String revTypeCode) {
        this.revTypeCode = revTypeCode;
    }

    public String getRevTypeName() {
        return revTypeName;
    }

    public void setRevTypeName(String revTypeName) {
        this.revTypeName = revTypeName;
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

    public BigDecimal getRevertAmt() {
        return revertAmt;
    }

    public void setRevertAmt(BigDecimal revertAmt) {
        this.revertAmt = revertAmt;
    }

    public BigDecimal getApprRevertAmount() {
        return apprRevertAmount;
    }

    public void setApprRevertAmount(BigDecimal apprRevertAmount) {
        this.apprRevertAmount = apprRevertAmount;
    }

    public Long getRevertProDtlId() {
        return revertProDtlId;
    }

    public void setRevertProDtlId(Long revertProDtlId) {
        this.revertProDtlId = revertProDtlId;
    }
}

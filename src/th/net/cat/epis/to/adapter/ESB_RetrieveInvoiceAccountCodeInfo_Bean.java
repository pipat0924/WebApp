/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.net.cat.epis.to.adapter;

/**
 *
 * @author softpos2013
 */
public class ESB_RetrieveInvoiceAccountCodeInfo_Bean {

    private int BillRefNo; //int
    private String AccountCodeNew;
    private String AccountCodeNewDesc;
    private String RevTypeCode;
    private String RevTypeName;
    private String SeqmentCode;
    private String SeqmentName;
    private String ProductCode;
    private String ProductName;
    private String SubProductCode;
    private String SubProductName;
    private double Amount;//double

    public int getBillRefNo() {
        return BillRefNo;
    }

    public void setBillRefNo(int BillRefNo) {
        this.BillRefNo = BillRefNo;
    }

    public String getAccountCodeNew() {
        return AccountCodeNew;
    }

    public void setAccountCodeNew(String AccountCodeNew) {
        this.AccountCodeNew = AccountCodeNew;
    }

    public String getAccountCodeNewDesc() {
        return AccountCodeNewDesc;
    }

    public void setAccountCodeNewDesc(String AccountCodeNewDesc) {
        this.AccountCodeNewDesc = AccountCodeNewDesc;
    }

    public String getRevTypeCode() {
        return RevTypeCode;
    }

    public void setRevTypeCode(String RevTypeCode) {
        this.RevTypeCode = RevTypeCode;
    }

    public String getRevTypeName() {
        return RevTypeName;
    }

    public void setRevTypeName(String RevTypeName) {
        this.RevTypeName = RevTypeName;
    }

    public String getSeqmentCode() {
        return SeqmentCode;
    }

    public void setSeqmentCode(String SeqmentCode) {
        this.SeqmentCode = SeqmentCode;
    }

    public String getSeqmentName() {
        return SeqmentName;
    }

    public void setSeqmentName(String SeqmentName) {
        this.SeqmentName = SeqmentName;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getSubProductCode() {
        return SubProductCode;
    }

    public void setSubProductCode(String SubProductCode) {
        this.SubProductCode = SubProductCode;
    }

    public String getSubProductName() {
        return SubProductName;
    }

    public void setSubProductName(String SubProductName) {
        this.SubProductName = SubProductName;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }
    
    
}

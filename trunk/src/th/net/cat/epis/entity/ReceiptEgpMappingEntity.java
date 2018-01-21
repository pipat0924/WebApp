package th.net.cat.epis.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by imake on 08/02/2017.
 */
@Entity
@Table(name = "RECEIPT_EGP_MAPPING")
public class ReceiptEgpMappingEntity {
    private Long receiptId;
    private String receiptNo;
    private String baNo;
    private String egpNo;
    private String egpContract;
    private String createdBy;
    private Timestamp createdDate;

    @Id
    @Column(name = "RECEIPT_ID")
    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    @Basic
    @Column(name = "RECEIPT_NO")
    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    @Basic
    @Column(name = "BA_NO")
    public String getBaNo() {
        return baNo;
    }

    public void setBaNo(String baNo) {
        this.baNo = baNo;
    }

    @Basic
    @Column(name = "EGP_NO")
    public String getEgpNo() {
        return egpNo;
    }

    public void setEgpNo(String egpNo) {
        this.egpNo = egpNo;
    }

    @Basic
    @Column(name = "EGP_CONTRACT")
    public String getEgpContract() {
        return egpContract;
    }

    public void setEgpContract(String egpContract) {
        this.egpContract = egpContract;
    }

    @Basic
    @Column(name = "CREATED_BY")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "CREATED_DATE")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiptEgpMappingEntity that = (ReceiptEgpMappingEntity) o;

        if (receiptId != null ? !receiptId.equals(that.receiptId) : that.receiptId != null) return false;
        if (receiptNo != null ? !receiptNo.equals(that.receiptNo) : that.receiptNo != null) return false;
        if (baNo != null ? !baNo.equals(that.baNo) : that.baNo != null) return false;
        if (egpNo != null ? !egpNo.equals(that.egpNo) : that.egpNo != null) return false;
        if (egpContract != null ? !egpContract.equals(that.egpContract) : that.egpContract != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = receiptId != null ? receiptId.hashCode() : 0;
        result = 31 * result + (receiptNo != null ? receiptNo.hashCode() : 0);
        result = 31 * result + (baNo != null ? baNo.hashCode() : 0);
        result = 31 * result + (egpNo != null ? egpNo.hashCode() : 0);
        result = 31 * result + (egpContract != null ? egpContract.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}

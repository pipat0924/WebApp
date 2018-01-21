package th.net.cat.epis.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by imake on 02/08/2017
 */
@Entity
@Table(name = "PROMOTION_RECEIPT_MAPPING")
public class PromotionReceiptMappingEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1817713322752914769L;
	private Long receiptid;
    private String receiptno;
    private String promotionName;
    private Timestamp createDate;
    private String createBy;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @Column(name = "RECEIPTID")
    public Long getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(Long receiptid) {
        this.receiptid = receiptid;
    }

    @Basic
    @Column(name = "RECEIPTNO")
    public String getReceiptno() {
        return receiptno;
    }

    public void setReceiptno(String receiptno) {
        this.receiptno = receiptno;
    }

    @Basic
    @Column(name = "PROMOTION_NAME")
    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_BY")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "UPDATE_DATE")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "UPDATE_BY")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionReceiptMappingEntity that = (PromotionReceiptMappingEntity) o;

        if (receiptid != that.receiptid) return false;
        if (receiptno != null ? !receiptno.equals(that.receiptno) : that.receiptno != null) return false;
        if (promotionName != null ? !promotionName.equals(that.promotionName) : that.promotionName != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (receiptid ^ (receiptid >>> 32));
        result = 31 * result + (receiptno != null ? receiptno.hashCode() : 0);
        result = 31 * result + (promotionName != null ? promotionName.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}

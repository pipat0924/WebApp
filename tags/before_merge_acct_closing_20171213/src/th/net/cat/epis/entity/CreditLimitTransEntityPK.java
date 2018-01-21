package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by imake on 13/02/2017.
 */
public class CreditLimitTransEntityPK implements Serializable {
    private static final long serialVersionUID = 2029183320943168448L;
    private String contract;
    private String arRef;
    private String receiptId;

    @Column(name = "CONTRACT")
    @Id
    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    @Column(name = "AR_REF")
    @Id
    public String getArRef() {
        return arRef;
    }

    public void setArRef(String arRef) {
        this.arRef = arRef;
    }

    @Column(name = "RECEIPT_ID")
    @Id
    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }


}

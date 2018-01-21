package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by nastanda on 11/29/2016 AD.
 */
@Embeddable
public class RevertPaymentPK implements Serializable{
    @Column(name = "REVERT_REQ_NO")
    private String revertReqNo;

    @Column(name = "RECEIPT_NO")
    private String receiptNo;

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
}

package th.net.cat.epis.entity;

import org.apache.commons.logging.Log;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.stream.config.StreamNamespaceHandler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by nastanda on 11/29/2016 AD.
 */
@Entity
@Table(name = "REVERT_PAYMENT_DOC_COMPONENT")
public class RevertPaymentDocComponent {
    @Id
    @Column(name = "DOC_COMP_ID")
    private Long docCompId;

    @Column(name = "DOC_COMP_NAME")
    private String docCompName;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

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

    public Long getDocCompId() {
        return docCompId;
    }

    public void setDocCompId(Long docCompId) {
        this.docCompId = docCompId;
    }

    public String getDocCompName() {
        return docCompName;
    }

    public void setDocCompName(String docCompName) {
        this.docCompName = docCompName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
}

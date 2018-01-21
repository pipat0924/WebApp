package th.net.cat.epis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nastanda on 12/1/2016 AD.
 */
@Entity
@Table(name = "KEY_GENERATOR")
public class KeyGenerator {
    @Id
    @SequenceGenerator(name="DOC_SEQ", sequenceName="DOC_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOC_SEQ")
    @Column(name = "DOC_ID") private Long docId;
    @Column(name = "BRANCH_CODE") private String branchCode;
    @Column(name = "DOC_CODE") private String docCode;
    @Column(name = "DOC_TYPE") private String docType;
    @Column(name = "DOC_DESC") private String docDesc;
    @Column(name = "DOC_HEADER") private String docHeader;
    @Column(name = "DOC_DATE") private Date docDate;
    @Column(name = "RUNNING_NUMBER") private Long runningNumber;

    @Column(name = "CREATED_DTM")
    private Date createdDtm;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPD_DTM")
    private Date lastUpdDtm;
    @Column(name = "LAST_UPD_BY")
    private String lastUpdBy;

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocDesc() {
        return docDesc;
    }

    public void setDocDesc(String docDesc) {
        this.docDesc = docDesc;
    }

    public String getDocHeader() {
        return docHeader;
    }

    public void setDocHeader(String docHeader) {
        this.docHeader = docHeader;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Long getRunningNumber() {
        return runningNumber;
    }

    public void setRunningNumber(Long runningNumber) {
        this.runningNumber = runningNumber;
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

package th.net.cat.epis.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by nastanda on 5/27/2017 AD.
 */
@Entity
@Table(name="EMP_CLOSING")
public class EmpClosing {
    @Id
    @SequenceGenerator(name="EMP_CLOSINGID_SEQ", sequenceName="EMP_CLOSINGID_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMP_CLOSINGID_SEQ")
    @Column(name="EMP_CLOSING_ID") private Long id;
    @Column(name = "EMP_CODE") private String empCode;
    @Column(name = "CLOSING_DATE") private Date closingDate;
    @Column(name = "BRANCH_CODE") private String branchCode;
    @Column(name = "CLOSING_POS") private String closingPos;
    @Column(name = "DOC_STATUS") private String docStatus;
    @Column(name = "BACKDATE_STATUS") private String backdateStatus;

    @Column(name="CREATEDTTM") private java.sql.Date createDttm;
    @Column(name="CREATEUSER") private String createUser;
    @Column(name="UPDATEDTTM") private java.sql.Date updateDttm;
    @Column(name="UPDATEUSER") private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getClosingPos() {
        return closingPos;
    }

    public void setClosingPos(String closingPos) {
        this.closingPos = closingPos;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public Date getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(Date createDttm) {
        this.createDttm = createDttm;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getBackdateStatus() {
        return backdateStatus;
    }

    public void setBackdateStatus(String backdateStatus) {
        this.backdateStatus = backdateStatus;
    }
}

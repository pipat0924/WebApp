package th.net.cat.epis.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by nastanda on 5/22/2017 AD.
 */
@Entity
@Table(name="SHOP_CLOSING")
public class ShopClosing {
    @Id
    @SequenceGenerator(name="SHOP_CLOSINGID_SEQ", sequenceName="SHOP_CLOSINGID_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SHOP_CLOSINGID_SEQ")
    @Column(name="SHOP_CLOSING_ID") private Long id;
    @Column(name = "CLOSING_DATE") private Date closingDate;
    @Column(name = "BRANCH_CODE") private String branchCode;
    @Column(name = "DOC_STATUS") private String docStatus;
    @Column(name = "CLOSING_EMP_CODE") private String closingEmpCode;
    @Column(name = "CLOSING_EMP_NAME") private String closingEmpName;
    @Column(name = "BACKDATE_STATUS") private String backdateStatus;
    @Column(name = "OPEN_SHOP_ID") private Long openShopId;
    
    @Column(name="CREATEDTTM") private java.sql.Date createDttm;
    @Column(name="CREATEUSER") private String createUser;
    @Column(name="UPDATEDTTM") private java.sql.Date updateDttm;
    @Column(name="UPDATEUSER") private String updateUser;
    @Column(name="USERNAME") private String userName;

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

    public String getClosingEmpCode() {
        return closingEmpCode;
    }

    public void setClosingEmpCode(String closingEmpCode) {
        this.closingEmpCode = closingEmpCode;
    }

    public String getClosingEmpName() {
        return closingEmpName;
    }

    public void setClosingEmpName(String closingEmpName) {
        this.closingEmpName = closingEmpName;
    }

    public String getBackdateStatus() {
        return backdateStatus;
    }

    public void setBackdateStatus(String backdateStatus) {
        this.backdateStatus = backdateStatus;
    }

	public Long getOpenShopId() {
		return openShopId;
	}

	public void setOpenShopId(Long openShopId) {
		this.openShopId = openShopId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}

package th.net.cat.epis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EPIS_SHOP")
public class EpisShop {
	@Id
	@SequenceGenerator(name="SEQ_EPIS_SHOP", sequenceName="SEQ_EPIS_SHOP", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_EPIS_SHOP")
	@Column(name="SHOP_ID") private Long id;
	@Column(name="SHOP_OPEN_DTTM") private Date shopOpenDttm;
	@Column(name="BRANCH_AREA") private String branchArea;
	@Column(name="BRANCH_NAME") private String branchName;
	@Column(name="CREATEDTTM") private Date createDttm;
	@Column(name="CREATEBY") private String createBy;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEBY") private String updateBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getShopOpenDttm() {
		return shopOpenDttm;
	}
	public void setShopOpenDttm(Date shopOpenDttm) {
		this.shopOpenDttm = shopOpenDttm;
	}
	public String getBranchArea() {
		return branchArea;
	}
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getCreateDttm() {
		return createDttm;
	}
	public void setCreateDttm(Date createDttm) {
		this.createDttm = createDttm;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	
	
}

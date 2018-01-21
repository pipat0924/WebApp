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
@Table(name="REVERT_RECEIPT")
public class RevertRecipt {
	
	@Id
	@SequenceGenerator(name="REVERT_RECEIPT_SEQ", sequenceName="REVERT_RECEIPT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVERT_RECEIPT_SEQ")
	@Column(name="CANCELREASONID") private Long id;
	@Column(name="BANKACCOUNTNO") private String bankaccountNo;
	@Column(name="BANKACCOUNTNAME") private String bankaccountName;
	@Column(name="REASONDESC") private String reasonDesc;
	@Column(name="BANKNAME") private String bankName;
	@Column(name="BRANCH") private String branch;
	@Column(name="SERVICENO") private String serviceNo;
	@Column(name="REVERSETYPE") private Integer revertType;
	@Column(name="ODERID") private String oderId;
	@Column(name="RECEIPTID") private String receiptId;
	@Column(name="STATUS") private String status;
	@Column(name="SOURCETYPE") private String sourceType;
	@Column(name="CREATE_DATE") private Date createDate;
	@Column(name="CREATE_BY") private String createBy;
	@Column(name="UPDATE_DATE") private Date updateDate;
	@Column(name="UPDATE_BY") private String updateBy;
	@Column(name="REMARK") private String remark;
	
	
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the bankaccountNo
	 */
	public String getBankaccountNo() {
		return bankaccountNo;
	}
	/**
	 * @param bankaccountNo the bankaccountNo to set
	 */
	public void setBankaccountNo(String bankaccountNo) {
		this.bankaccountNo = bankaccountNo;
	}
	/**
	 * @return the bankaccountName
	 */
	public String getBankaccountName() {
		return bankaccountName;
	}
	/**
	 * @param bankaccountName the bankaccountName to set
	 */
	public void setBankaccountName(String bankaccountName) {
		this.bankaccountName = bankaccountName;
	}
	/**
	 * @return the reasonDesc
	 */
	public String getReasonDesc() {
		return reasonDesc;
	}
	/**
	 * @param reasonDesc the reasonDesc to set
	 */
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the serviceNo
	 */
	public String getServiceNo() {
		return serviceNo;
	}
	/**
	 * @param serviceNo the serviceNo to set
	 */
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	/**
	 * @return the revertType
	 */
	public Integer getRevertType() {
		return revertType;
	}
	/**
	 * @param revertType the revertType to set
	 */
	public void setRevertType(Integer revertType) {
		this.revertType = revertType;
	}
	/**
	 * @return the oderId
	 */
	public String getOderId() {
		return oderId;
	}
	/**
	 * @param oderId the oderId to set
	 */
	public void setOderId(String oderId) {
		this.oderId = oderId;
	}
	/**
	 * @return the receiptId
	 */
	public String getReceiptId() {
		return receiptId;
	}
	/**
	 * @param receiptId the receiptId to set
	 */
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}
	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package th.net.cat.epis.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class Trsreprint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 711664184745238606L;

	private Long reprintid;
	private Long receiptid;
	private String password;
	private String approvedby;
	private BigDecimal category;
	private String categoryName;
	private String reason;
	private Date updatedttm;
	private String updatedttmStr;
	private String updatesystem;
	private String updateuser;
	private BigDecimal versionstamp;
	private String receiptno;
	private String searchStartDate;
	private String searchEndDate;

	private String reprintflg;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSearchStartDate() {
		return searchStartDate;
	}
	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}
	public String getSearchEndDate() {
		return searchEndDate;
	}
	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	public Long getReceiptid() {
		return receiptid;
	}
	public void setReceiptid(Long receiptid) {
		this.receiptid = receiptid;
	}
	public Long getReprintid() {
		return reprintid;
	}
	public void setReprintid(Long reprintid) {
		this.reprintid = reprintid;
	}
	public String getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public BigDecimal getCategory() {
		return category;
	}
	public void setCategory(BigDecimal category) {
		this.category = category;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getUpdatedttm() {
		return updatedttm;
	}
	public void setUpdatedttm(Date updatedttm) {
		this.updatedttm = updatedttm;
	}
	public String getUpdatesystem() {
		return updatesystem;
	}
	public void setUpdatesystem(String updatesystem) {
		this.updatesystem = updatesystem;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public BigDecimal getVersionstamp() {
		return versionstamp;
	}
	public void setVersionstamp(BigDecimal versionstamp) {
		this.versionstamp = versionstamp;
	}
	public String getUpdatedttmStr() {
		return updatedttmStr;
	}
	public void setUpdatedttmStr(String updatedttmStr) {
		this.updatedttmStr = updatedttmStr;
	}

	public String getReprintflg() {
		return reprintflg;
	}

	public void setReprintflg(String reprintflg) {
		this.reprintflg = reprintflg;
	}
}

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
@Table(name = "CORRECEIPT_LOG")
public class ReceiptLog {

	@Id
	@SequenceGenerator(name = "CORRECEIPT_LOG_SEQ", sequenceName = "CORRECEIPT_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CORRECEIPT_LOG_SEQ")
	@Column(name = "ID")
	private Long id;
	@Column(name = "RECEIPTID")
	private Long receiptId;
	@Column(name = "RECEIPTNO")
	private String no;
	@Column(name = "RECEIPTNAME")
	private String name;
	@Column(name = "RECEIPTTYPE")
	private String type;
	@Column(name = "RECEIPTDTTM")
	private Date docDttm;
	@Column(name = "ADDRESSLINE1")
	private String addrLine1;
	@Column(name = "REMARK_CORRECEIPT")
	private String remarkCor;
	@Column(name = "PROMOTION")
	private String promotion;
	@Column(name = "REPRINT")
	private Long reprint;
	@Column(name = "CANCELREASON")
	private String cancelReason;
	@Column(name = "CANCELDTTM")
	private Date cancelledDttm;
	@Column(name = "CANCELEDBY")
	private String cancelledBy;
	@Column(name = "ATTRIBUTES")
	private String attributes;
	@Column(name = "UPDATEDTTM")
	private Date updateDttm;
	@Column(name = "UPDATESYSTEM")
	private String updateSys;
	@Column(name = "UPDATEUSER")
	private String updateUser;
	@Column(name = "VERSIONSTAMP")
	private Long versionstamp;
	@Column(name = "REF_RECEIPTNO")
	private String refNo;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	@Column(name = "UPDATE_BY")
	private String updateBy;
	@Column(name = "RECORD_STATUS")
	private String recordStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDocDttm() {
		return docDttm;
	}
	public void setDocDttm(Date docDttm) {
		this.docDttm = docDttm;
	}
	public String getAddrLine1() {
		return addrLine1;
	}
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}
	public String getRemarkCor() {
		return remarkCor;
	}
	public void setRemarkCor(String remarkCor) {
		this.remarkCor = remarkCor;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public Long getReprint() {
		return reprint;
	}
	public void setReprint(Long reprint) {
		this.reprint = reprint;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Date getCancelledDttm() {
		return cancelledDttm;
	}
	public void setCancelledDttm(Date cancelledDttm) {
		this.cancelledDttm = cancelledDttm;
	}
	public String getCancelledBy() {
		return cancelledBy;
	}
	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getUpdateSys() {
		return updateSys;
	}
	public void setUpdateSys(String updateSys) {
		this.updateSys = updateSys;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date date) {
		this.createDate = date;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Long getVersionstamp() {
		return versionstamp;
	}
	public void setVersionstamp(Long versionstamp) {
		this.versionstamp = versionstamp;
	}
	
	

}
package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Entity
@Table(name="DOC_NO_CONF")
public class DocNoConf {

	@Id
	@SequenceGenerator(name="docnoconf_seq", sequenceName="docnoconf_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="docnoconf_seq")
	@Column(name="ID") private Long id;
	@Column(name="DOC_NO") private String docNo;
	@Column(name="DOC_NAME") private String docName;
	@Column(name="BRANCH_CODE") private String branchCode;
	@Column(name="POS_NO") private String posNo;
	@Column(name="DOC_TYPE") private String docType;
	@Column(name="DATE_FORMAT") private String dateFormat;
	@Column(name="BD_ERA_FLAG") private String bdEraFlag;
	@Column(name="RUNNING_NO") private String runningNo;
	@Column(name="SYSTEM_TYPE") private String systemType;
	@Column(name="SYSTEM_CODE") private String systemCode;
	@Column(name="FORMAT") private String format;
	@Column(name="STATUS") private String status;
	@Column(name="CREATED_BY") private String createBy;
	@Column(name="CREATED_DTM") private Timestamp createDate;
	@Column(name="UPDATED_BY") private String updateBy;
	@Column(name="UPDATED_DTM") private Timestamp updateDate;
	@Transient private String key; 
	@Transient private String value; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getPosNo() {
		return posNo;
	}
	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getBdEraFlag() {
		return bdEraFlag;
	}
	public void setBdEraFlag(String bdEraFlag) {
		this.bdEraFlag = bdEraFlag;
	}
	public String getRunningNo() {
		return runningNo;
	}
	public void setRunningNo(String runningNo) {
		this.runningNo = runningNo;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}


}
package th.net.cat.crm.entity;

import javax.persistence.*;

@Entity
@Table(name="LOOKUP",schema = "CRMDATA")
public class Lookup {

	@Id
	@Column(name="LOOKUP_ID")
	private String lookupId;

	@Column(name="CODE_INT")
	private Integer codeInt;

	@Column(name="CODE_STRING")
	private String codeString;

	@Column(name="CODE_STRING_UP")
	private String codeStringUp;

	@Column(name="DCSS_CODE")
	private String dcssCode;

	@Column
	private Integer listorder;

	@Column(name="LOOKUP_CATEGORY_NAME")
	private String lookupCategoryName;

	@Column(name="LOOKUP_CATEGORY_NAME_UP")
	private String lookupCategoryNameUp;

	@Column(name="OBSOLETE_FLAG")
	private Integer obsoleteFlag;

	@Column(name="PARENT_LOOKUP_CAT_NAME")
	private String parentLookupCatName;

	@Column(name="PARENT_LOOKUP_ID")
	private byte[] parentLookupId;

	@Column(name="REVISION_NUMBER")
	private Integer revisionNumber;

	@Column(name="SYSTEM_MANAGED_FLAG")
	private Integer systemManagedFlag;

	@Column(name="TEXT_STRING")
	private String textString;

	@Column(name="TEXT_STRING_UP")
	private String textStringUp;

	@Column(name="UAT_FLAG")
	private Integer uatFlag;

	public String getLookupId() {
		return this.lookupId;
	}

	public void setLookupId(String lookupId) {
		this.lookupId = lookupId;
	}

	public Integer getCodeInt() {
		return this.codeInt;
	}

	public void setCodeInt(Integer codeInt) {
		this.codeInt = codeInt;
	}

	public String getCodeString() {
		return this.codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}

	public String getCodeStringUp() {
		return this.codeStringUp;
	}

	public void setCodeStringUp(String codeStringUp) {
		this.codeStringUp = codeStringUp;
	}

	public String getDcssCode() {
		return this.dcssCode;
	}

	public void setDcssCode(String dcssCode) {
		this.dcssCode = dcssCode;
	}

	public Integer getListorder() {
		return this.listorder;
	}

	public void setListorder(Integer listorder) {
		this.listorder = listorder;
	}

	public String getLookupCategoryName() {
		return this.lookupCategoryName;
	}

	public void setLookupCategoryName(String lookupCategoryName) {
		this.lookupCategoryName = lookupCategoryName;
	}

	public String getLookupCategoryNameUp() {
		return this.lookupCategoryNameUp;
	}

	public void setLookupCategoryNameUp(String lookupCategoryNameUp) {
		this.lookupCategoryNameUp = lookupCategoryNameUp;
	}

	public Integer getObsoleteFlag() {
		return this.obsoleteFlag;
	}

	public void setObsoleteFlag(Integer obsoleteFlag) {
		this.obsoleteFlag = obsoleteFlag;
	}

	public String getParentLookupCatName() {
		return this.parentLookupCatName;
	}

	public void setParentLookupCatName(String parentLookupCatName) {
		this.parentLookupCatName = parentLookupCatName;
	}

	public byte[] getParentLookupId() {
		return this.parentLookupId;
	}

	public void setParentLookupId(byte[] parentLookupId) {
		this.parentLookupId = parentLookupId;
	}

	public Integer getRevisionNumber() {
		return this.revisionNumber;
	}

	public void setRevisionNumber(Integer revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public Integer getSystemManagedFlag() {
		return this.systemManagedFlag;
	}

	public void setSystemManagedFlag(Integer systemManagedFlag) {
		this.systemManagedFlag = systemManagedFlag;
	}

	public String getTextString() {
		return this.textString;
	}

	public void setTextString(String textString) {
		this.textString = textString;
	}

	public String getTextStringUp() {
		return this.textStringUp;
	}

	public void setTextStringUp(String textStringUp) {
		this.textStringUp = textStringUp;
	}

	public Integer getUatFlag() {
		return this.uatFlag;
	}

	public void setUatFlag(Integer uatFlag) {
		this.uatFlag = uatFlag;
	}

}
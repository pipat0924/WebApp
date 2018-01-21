package th.net.cat.epis.to.report;

import java.io.Serializable;

public class EpisReportTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 7096681747592398733L;

	private String imagePathRpt;

	private String branchCode;
	private String branchName;
	private String documentNo;
	private String documentDate;
    private String userName;

	public String getImagePathRpt() {
		return imagePathRpt;
	}

	public void setImagePathRpt(String imagePathRpt) {
		this.imagePathRpt = imagePathRpt;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

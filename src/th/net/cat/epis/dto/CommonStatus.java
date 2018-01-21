package th.net.cat.epis.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonStatus <T> {

	protected String statusCode; // 0 = SUCCESS, != 0 FAIL
	protected List<AlertMessage> successList;
	protected List<AlertMessage> warningList;
	protected List<AlertMessage> errorList;
	protected List<T> data;
	protected Map<String,String> param;
	
	public CommonStatus() {
		this.statusCode = "0";
		this.successList = new ArrayList<AlertMessage>();
		this.warningList = new ArrayList<AlertMessage>();
		this.errorList = new ArrayList<AlertMessage>();
		this.data = new ArrayList<T>();
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public List<AlertMessage> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<AlertMessage> successList) {
		this.successList = successList;
	}
	public List<AlertMessage> getWarningList() {
		return warningList;
	}
	public void setWarningList(List<AlertMessage> warningList) {
		this.warningList = warningList;
	}
	public List<AlertMessage> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<AlertMessage> errorList) {
		this.errorList = errorList;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public void addData(T tObj) {
		data.add(tObj);
	}

	public Map<String, String> getParam() {
		return param;
	}

	public void setParam(Map<String, String> param) {
		this.param = param;
	}
}

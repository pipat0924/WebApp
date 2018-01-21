package th.net.cat.epis.dto;

import java.util.List;
import java.util.Map;

public class AmountAdjustmentSummaryDTO {
	private List<Map<String,String>> records;
	
	private Map<String,String> header;
	
	private List<Map<String,String>> recordDetailList;
	
	private List<Map<String,String>> attMapList;
	
	public List<Map<String, String>> getRecords() {
		return records;
	}

	public void setRecords(List<Map<String, String>> records) {
		this.records = records;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public List<Map<String, String>> getRecordDetailList() {
		return recordDetailList;
	}

	public void setRecordDetailList(List<Map<String, String>> recordDetailList) {
		this.recordDetailList = recordDetailList;
	}

	public List<Map<String, String>> getAttMapList() {
		return attMapList;
	}

	public void setAttMapList(List<Map<String, String>> attMapList) {
		this.attMapList = attMapList;
	}
}

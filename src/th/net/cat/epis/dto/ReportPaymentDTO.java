package th.net.cat.epis.dto;

public class ReportPaymentDTO extends CommonStatus<ReportPayment> {
	private String rowNo;
	private String sdate;

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	
}

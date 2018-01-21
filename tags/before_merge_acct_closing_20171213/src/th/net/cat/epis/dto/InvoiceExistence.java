package th.net.cat.epis.dto;


public class InvoiceExistence {

	protected String invoiceNo;
	protected String status;
	
	public InvoiceExistence() {}
	public InvoiceExistence(String invoiceNo, String status) {
		super();
		this.invoiceNo = invoiceNo;
		this.status = status;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

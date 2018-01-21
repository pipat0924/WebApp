package th.net.cat.epis.dto;

public class AlertMessage {
	protected String messageCode;
	protected String messageDesc;

	public AlertMessage(String messageCode, String messageDesc) {
		this.messageCode = messageCode;
		this.messageDesc = messageDesc;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageDesc() {
		return messageDesc;
	}
	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}
	
}

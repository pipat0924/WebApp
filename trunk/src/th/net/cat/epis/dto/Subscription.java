package th.net.cat.epis.dto;


public class Subscription {

	private String subscrType;
	private Integer subscrNo;
	private String statusName;
	public Subscription() {}
	public Subscription(String subscrType, Integer subscrNo, String statusName) {
		super();
		this.subscrType = subscrType;
		this.subscrNo = subscrNo;
		this.statusName = statusName;
	}
	public String getSubscrType() {
		return subscrType;
	}
	public void setSubscrType(String subscrType) {
		this.subscrType = subscrType;
	}
	public Integer getSubscrNo() {
		return subscrNo;
	}
	public void setSubscrNo(Integer subscrNo) {
		this.subscrNo = subscrNo;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}

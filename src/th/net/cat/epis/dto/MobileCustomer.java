package th.net.cat.epis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MobileCustomer {

//	private String statusCode = "0";
	private String orderId;
	private String no;
	private String name;
	private String taxId;
	private String group;
	private String type;
	private String address;
	private String address2;
	private String branch;
	private String mobile;
	private String orderIdRef;//by NSD 01-03-2017
	private String agentAddressCode;

	private List<MobileCustomer.Service> data = new ArrayList<MobileCustomer.Service>();
//	public String getStatusCode() {
//		return statusCode;
//	}
//	public void setStatusCode(String statusCode) {
//		this.statusCode = statusCode;
//	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<Service> getData() {
		return data;
	}
	public void setData(List<Service> data) {
		this.data = data;
	}
	public Service addService() {
		Service service = new Service();
		getData().add(service);
		return service;
	}
	public static class Service {
		private String mdn;
		private String iccid;
		private BigDecimal amount;
		public String getMdn() {
			return mdn;
		}
		public void setMdn(String mdn) {
			this.mdn = mdn;
		}
		public String getIccid() {
			return iccid;
		}
		public void setIccid(String iccid) {
			this.iccid = iccid;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
	}

	public String getOrderIdRef() {
		return orderIdRef;
	}

	public void setOrderIdRef(String orderIdRef) {
		this.orderIdRef = orderIdRef;
	}

	public String getAgentAddressCode() {
		return agentAddressCode;
	}

	public void setAgentAddressCode(String agentAddressCode) {
		this.agentAddressCode = agentAddressCode;
	}
}
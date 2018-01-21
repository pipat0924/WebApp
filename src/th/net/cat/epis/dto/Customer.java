package th.net.cat.epis.dto;

import java.math.BigDecimal;

public class Customer {
	private String orderId;
	private String no;
	private String name;
	private String taxId;
	private String group;
	private String address;
	private String branch;
	private String mobile;
	private String promotion;
	private BigDecimal serviceCharge;
	private BigDecimal serviceDiscount;
	public String getOrderId() {
		return orderId;
	}
	public String getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getTaxId() {
		return taxId;
	}
	public String getGroup() {
		return group;
	}
	public String getAddress() {
		return address;
	}
	public String getBranch() {
		return branch;
	}
	public String getMobile() {
		return mobile;
	}
	public String getPromotion() {
		return promotion;
	}
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}
	public BigDecimal getServiceDiscount() {
		return serviceDiscount;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public void setServiceDiscount(BigDecimal serviceDiscount) {
		this.serviceDiscount = serviceDiscount;
	}
}
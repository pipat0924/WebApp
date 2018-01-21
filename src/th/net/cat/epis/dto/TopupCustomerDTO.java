package th.net.cat.epis.dto;

import java.util.ArrayList;
import java.util.List;

public class TopupCustomerDTO {

	private String statusCode = "0";
	private String serviceKey;
	private String authTokens;
	private String firstName;
	private String lastName;
	private List<String> errorList = new ArrayList<String>();
	private List<Object> data = new ArrayList<Object>();
	private List<Amount> amounts = new ArrayList<Amount>();
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public String getAuthTokens() {
		return authTokens;
	}
	public void setAuthTokens(String authTokens) {
		this.authTokens = authTokens;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public List<Amount> getAmounts() {
		return amounts;
	}
	public void setAmountList(List<Amount> amounts) {
		this.amounts = amounts;
	}
	public Amount addAmount() {
		Amount amount = new Amount();
		getAmounts().add(amount);
		return amount;
	}
	public Service addService() {
		Service service = new Service();
		getData().add(service);
		return service;
	}
	public Customer addCustomer() {
		Customer customer = new Customer();
		getData().add(customer);
		return customer;
	}
	public static class Service {
		private String key;
		private String value;
		private String text;
		private String discount;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getDiscount() {
			return discount;
		}

		public void setDiscount(String discount) {
			this.discount = discount;
		}
	}
	public static class Amount {
		private List<Service> list = new ArrayList<Service>();
		public List<Service> getList() {
			return list;
		}
		public void setList(List<Service> list) {
			this.list = list;
		}
		public void addService(String key, String value, String text) {
			Service service = new Service();
			service.setKey(key);
			service.setValue(value);
			service.setText(text);
			this.getList().add(service);
		}
	}

	
}
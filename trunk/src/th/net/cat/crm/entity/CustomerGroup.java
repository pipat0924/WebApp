package th.net.cat.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATCRM_CUSTOMER_GROUP",schema = "CRMDATA")
public class CustomerGroup {

	@Id
	@Column(name="CODE") private String id;
	@Column(name="TEXT") private String text;
	@Column(name="LISTORDER") private Integer order;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
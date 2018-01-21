package th.net.cat.epis.dto.sap;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name ="SA_SAP_TEMPLATE")
public class SaSapTemplateEntity {
	
	@Id  
	@SequenceGenerator(name="SA_SAP_TEMPLATE_SEQ", sequenceName="SA_SAP_TEMPLATE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SA_SAP_TEMPLATE_SEQ")
	@Column(name="ID") private int id;
	@Column(name="ATTRIBUTE_NAME") private String attribute_name;
	@Column(name="LENGTH") private String length;
	@Column(name="REMARK") private String remark;
	@Column(name="CREATE_BY") private String create_by;
	@Column(name="CREATE_DATE") private Timestamp create_date;
	@Column(name="UPDATE_BY") private String update_by;
	@Column(name="UPDATE_DATE") private Timestamp update_date;
	@Column(name="RECORD_STATUS") private String record_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttribute_name() {
		return attribute_name;
	}
	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	public String getRecord_status() {
		return record_status;
	}
	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}
	
	
	
}

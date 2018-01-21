package th.net.cat.epis.to.management;

import java.io.Serializable;

public class Predefine implements Serializable{

	private static final long serialVersionUID = 64774346811500555L;
	
	private String id;
	private String type;
	private String label;
	private Integer porder;
	
	private String value;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPorder() {
		return porder;
	}
	public void setPorder(Integer porder) {
		this.porder = porder;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}

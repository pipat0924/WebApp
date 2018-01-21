package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CORRECEIPTDOCUMENT")
public class Document {

	@Id
	@SequenceGenerator(name="receiptdocument_seq", sequenceName="receiptdocument_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="receiptdocument_seq")
	@Column(name="RECEIPTDOCUMENTID") private Long id;
	@Column(name="RECEIPTDOCUMENTTYPE") private String type;
	@Column(name="RECEIPTHEADER") private String header;
	@Column(name="BRANCHAREA") private String branchArea;
	@Column(name="DATETEXT") private String date;
	@Column(name="DOCUMENTCOUNT") private Integer count;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBranchArea() {
		return branchArea;
	}
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="MASPOS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pos {

	@Id
	@SequenceGenerator(name="pos_seq", sequenceName="pos_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pos_seq")
	@Column(name="POSID") private Long id;
	@Column(name="POSNO") private String no;
	@Column(name="POSNAME") private String name;
	@Column(name="MAC") private String mac;
	@Column(name="ISPOSITIVE") private Integer isPositive;
	@Column(name="DESCRIPTION") private String description;
	@ManyToOne(targetEntity=Shop.class)
	@JoinColumn(name="SHOPID") private Shop shop;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public String getMac() {
		return mac;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public Integer getIsPositive() {
		return isPositive;
	}
	
	public void setIsPositive(Integer isPositive) {
		this.isPositive = isPositive;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Shop getShop() {
		return shop;
	}
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
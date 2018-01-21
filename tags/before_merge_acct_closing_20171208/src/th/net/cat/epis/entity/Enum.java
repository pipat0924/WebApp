package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ARCENUMS")
public class Enum {

	@Id
	@SequenceGenerator(name="enum_seq", sequenceName="enum_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="enum_seq")
	@Column(name="ENUMID") private Long id;
	@Column(name="CATEGORY") private String category;
	@Column(name="MESSAGECODE") private String code;
	@Column(name="MAPCODE1") private String mapCode1;
	@Column(name="MAPCODE2") private String mapCode2;
	@Column(name="MAPCODE3") private String mapCode3;
	@Column(name="MAPCODE4") private String mapCode4;
	@Column(name="DESCFULLEN") private String descFullEn;
	@Column(name="DESCFULLTH") private String descFullTh;
	@Column(name="DESCABVREN") private String descAbvrEn;
	@Column(name="DESCABVRTH") private String descAbvrTh;
	@Column(name="ISPOSITIVE") private Integer isPositive;

	public Long getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	public String getCode() {
		return code;
	}
	public String getMapCode1() {
		return mapCode1;
	}
	public String getMapCode2() {
		return mapCode2;
	}
	public String getMapCode3() {
		return mapCode3;
	}
	public String getMapCode4() {
		return mapCode4;
	}
	public String getDescFullEn() {
		return descFullEn;
	}
	public String getDescFullTh() {
		return descFullTh;
	}
	public String getDescAbvrEn() {
		return descAbvrEn;
	}
	public String getDescAbvrTh() {
		return descAbvrTh;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMapCode1(String mapCode1) {
		this.mapCode1 = mapCode1;
	}
	public void setMapCode2(String mapCode2) {
		this.mapCode2 = mapCode2;
	}
	public void setMapCode3(String mapCode3) {
		this.mapCode3 = mapCode3;
	}
	public void setMapCode4(String mapCode4) {
		this.mapCode4 = mapCode4;
	}
	public void setDescFullEn(String descFullEn) {
		this.descFullEn = descFullEn;
	}
	public void setDescFullTh(String descFullTh) {
		this.descFullTh = descFullTh;
	}
	public void setDescAbvrEn(String descAbvrEn) {
		this.descAbvrEn = descAbvrEn;
	}
	public void setDescAbvrTh(String descAbvrTh) {
		this.descAbvrTh = descAbvrTh;
	}
	public Integer getIsPositive() {
		return isPositive;
	}
	public void setIsPositive(Integer isPositive) {
		this.isPositive = isPositive;
	}
	
}
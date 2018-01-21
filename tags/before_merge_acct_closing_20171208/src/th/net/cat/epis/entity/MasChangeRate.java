package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
import th.net.cat.epis.util.AppUtil;

@Entity
@Table(name = "MASCHANGERATE")
public class MasChangeRate {

	@Id
	@SequenceGenerator(name="changerate_seq", sequenceName="changerate_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="changerate_seq")
	@Column(name = "MASCHANGERATEID") private Long id;
	@Column(name = "RATECODE") private String rateCode;
	@Column(name = "MESSAGE") private String message;
	@Column(name = "COUNTRY") private String country;
	@Column(name = "DATEUSED") private Date dateUsed;
	@Column(name = "RATEUNIT") private BigDecimal rateUnit;
	@Column(name = "DESCRIPTION") private String description;
	@Column(name = "CURRENCYSYMBOL") private String currencySymbol;
	@Column(name = "UPDATEDTTM") private Date updateDttm;
	@Column(name = "UPDATESYSTEM") private String updateSystem;
	@Column(name = "UPDATEUSER") private String updateUser;
	@Column(name = "CURRENCYCODE") private String currencyCode;
	@Column(name = "CODE") private String code;
	@Transient
	@Column(name = "DATEUSED_SHOW")
	private String dateUsedShow;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getDateUsed() {
		return dateUsed;
	}
	public void setDateUsed(Date dateUsed) {
		this.dateUsed = dateUsed;
	}
	public void setDateUsed(java.util.Date dateUsed) {
		this.dateUsed = (dateUsed != null) ? AppUtil.convertJavaDateToSqlDate(dateUsed) :null;
	}
	public BigDecimal getRateUnit() {
		return rateUnit;
	}
	public void setRateUnit(BigDecimal rateUnit) {
		this.rateUnit = rateUnit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public void setUpdateDttm(java.util.Date updateDttm) {
		this.updateDttm = (updateDttm != null) ? AppUtil.convertJavaDateToSqlDate(updateDttm) :null;
	}
	public String getUpdateSystem() {
		return updateSystem;
	}
	public void setUpdateSystem(String updateSystem) {
		this.updateSystem = updateSystem;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getDateUsedShow() {
		return dateUsedShow;
	}

	public void setDateUsedShow(String dateUsedShow) {
		this.dateUsedShow = dateUsedShow;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

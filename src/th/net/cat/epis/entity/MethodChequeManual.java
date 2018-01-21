package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRSCHEQUEREF_MANUAL")
public class MethodChequeManual {

	@Id
	@SequenceGenerator(name="TRSCHEQUEREF_MANUAL_SEQ", sequenceName="TRSCHEQUEREF_MANUAL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRSCHEQUEREF_MANUAL_SEQ")
	@Column(name="ID") private Long id;
	@Column(name="CHEQUENO") private String no;
	@Column(name="PUBLISHERID") private String bankCode;
	@Column(name="PUBLISHER") private String bankName;
	@Column(name="BRANCH") private String bankBrnh;
//	@Column(name="ISSUEDATE") private Date date;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="CHEQUEDATE") private Date chequeDate;
	@Column(name="VERSIONSTAMP") private Long version;
	@Column(name="METHOD_MANUAL_ID") private Long methodId;
	
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
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBrnh() {
		return bankBrnh;
	}
	public void setBankBrnh(String bankBrnh) {
		this.bankBrnh = bankBrnh;
	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Date getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}
	/**
	 * @return the methodId
	 */
	public Long getMethodId() {
		return methodId;
	}
	/**
	 * @param methodId the methodId to set
	 */
	public void setMethodId(Long methodId) {
		this.methodId = methodId;
	}
	
	
	
}
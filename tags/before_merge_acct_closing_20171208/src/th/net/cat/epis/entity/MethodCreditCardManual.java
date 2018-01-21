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
@Table(name="TRSCREDITREF_MANUAL")
public class MethodCreditCardManual {

	@Id
	@SequenceGenerator(name="TRSCREDITREF_MANUAL_SEQ", sequenceName="TRSCREDITREF_MANUAL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRSCREDITREF_MANUAL_SEQ")
	@Column(name="ID") private Long id;
	@Column(name="CREDITNO") private String no;
	@Column(name="CARDTYPE") private String type;
	@Column(name="PUBLISHERDEC") private String bankIssuer;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="VERSIONSTAMP") private Long version;
	@Column(name="METHOD_MANUAL_ID") private Long methodManualId;
	

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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBankIssuer() {
		return bankIssuer;
	}
	public void setBankIssuer(String bankIssuer) {
		this.bankIssuer = bankIssuer;
	}
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
	public Long getMethodManualId() {
		return methodManualId;
	}
	public void setMethodManualId(Long methodManualId) {
		this.methodManualId = methodManualId;
	}
		
}
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

import th.net.cat.epis.util.AppUtil;

@Entity
@Table(name = "TRSACCOUNTTBOSS")
public class TrsAccountTBOSS{

	@Id
	@SequenceGenerator(name="trsaccounttboss_seq", sequenceName="trsaccounttboss_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trsaccounttboss_seq")
	@Column(name = "TRSACCOUNTTBOSSID") private Long id;
	@Column(name = "ACCOUNTTBOSSID") private Long accountTbossId;
	@Column(name = "PAYMENTID") private Long paymentId;
	@Column(name = "PAYDATETIME", nullable = false) private Date paydatetime;
	@Column(name = "AMOUNT", nullable = false, precision = 12) private BigDecimal amount;
	@Column(name = "TAX", nullable = false, precision = 12) private BigDecimal vatAmount;
	@Column(name = "BALANCEDUE", nullable = false, precision = 12) private BigDecimal balanceDue;
	@Column(name = "RECEIVED", nullable = false, precision = 12) private BigDecimal received;
	@Column(name = "UPDATEDTTM") private Date updateDttm;
	@Column(name = "UPDATESYSTEM", length = 3) private String updateSystem;
	@Column(name = "UPDATEUSER", length = 32) private String updateUser;
//	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
//	@JoinColumn(name="PAYMENTID") private Payment payment;
	public TrsAccountTBOSS() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountTbossId() {
		return accountTbossId;
	}
	public void setAccountTbossId(Long accountTbossId) {
		this.accountTbossId = accountTbossId;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Date getPaydatetime() {
		return paydatetime;
	}
	public void setPaydatetime(Date paydatetime) {
		this.paydatetime = paydatetime;
	}
	public void setPaydatetime(java.util.Date paydatetime) {
		this.paydatetime = AppUtil.convertJavaDateToSqlDate(paydatetime);
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	public BigDecimal getReceived() {
		return received;
	}
	public void setReceived(BigDecimal received) {
		this.received = received;
	}
	public Date getUpdateDttm() {
		return updateDttm;
	}
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	public void setUpdateDttm(java.util.Date updateDttm) {
		this.updateDttm = AppUtil.convertJavaDateToSqlDate(updateDttm);
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
}

package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRSCREDITREF")
public class MethodCreditCard {

	@Id
	@SequenceGenerator(name="creditcard_seq", sequenceName="creditcard_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="creditcard_seq")
	@Column(name="CREDITREFID") private Long id;
	@Column(name="CREDITNO") private String no;
	@Column(name="CARDTYPE") private String type;
	@Column(name="PUBLISHERDEC") private String bankIssuer;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="METHODID") private Long methodId;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="PAYMENTREFID") private Transaction transaction;

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
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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
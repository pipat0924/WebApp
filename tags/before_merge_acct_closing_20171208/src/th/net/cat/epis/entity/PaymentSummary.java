package th.net.cat.epis.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRSPAYMENTSUMMARY")
public class PaymentSummary {

	@Id
	@SequenceGenerator(name="paymentsummary_seq", sequenceName="paymentsummary_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="paymentsummary_seq")
	@Column(name="SUMMARYID") private Long id;
	@Column(name="SESSIONID") private Long sessionId;
	@Column(name="PAYTYPE") private String payType;
	@Column(name="PAYDESC") private String payDesc;
	@Column(name="BALANCE") private BigDecimal balance = BigDecimal.ZERO;
	@Column(name="COUNTER") private Integer counter = 0;
	public PaymentSummary() {}
	public PaymentSummary(Long sessionId, String payType) { this.sessionId = sessionId; this.payType = payType; }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayDesc() {
		return payDesc;
	}
	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
}
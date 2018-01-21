package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRSCHEQUEREF")
public class MethodCheque {

	@Id
	@SequenceGenerator(name="cheque_seq", sequenceName="cheque_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cheque_seq")
	@Column(name="CHEQUEREFID") private Long id;
	@Column(name="CHEQUENO") private String no;
	@Column(name="PUBLISHERID") private String bankCode;
	@Column(name="PUBLISHER") private String bankName;
	@Column(name="BRANCH") private String bankBrnh;
//	@Column(name="ISSUEDATE") private Date date;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="CHEQUEDATE") private Date chequeDate;
	@Column(name="METHODID") private Long methodId;
	@OneToMany(mappedBy="id", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Transaction> transactions = new ArrayList<Transaction>();

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
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
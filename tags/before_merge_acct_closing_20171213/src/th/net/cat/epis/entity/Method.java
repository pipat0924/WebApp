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
@Table(name="TRSMETHOD")
public class Method {

	@Id
	@SequenceGenerator(name="method_seq", sequenceName="method_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="method_seq")
	@Column(name="METHODID") private Long id;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="CODE") private String code;
	@Column(name="NAME") private String name;
	@Column(name="CHEQUENO") private String chequeNo;
	@Column(name="ACCOUNTNO") private String accountNo;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="RECEIPTID") private Long recieptId;
	// add for Offset
	@Column(name="OFFSET_DOCUMENT_NO") private String offsetDocumentNo;
	@Column(name="OFFSET_ACCOUNT_CODE") private String offsetAccountCode;
	@Column(name="OFFSET_ACCOUNT_NAME") private String offsetAccountName;
	
	

	
	public Long getRecieptId() {
		return recieptId;
	}
	public void setRecieptId(Long recieptId) {
		this.recieptId = recieptId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
	public String getOffsetDocumentNo() {
		return offsetDocumentNo;
	}
	public void setOffsetDocumentNo(String offsetDocumentNo) {
		this.offsetDocumentNo = offsetDocumentNo;
	}
	public String getOffsetAccountCode() {
		return offsetAccountCode;
	}
	public void setOffsetAccountCode(String offsetAccountCode) {
		this.offsetAccountCode = offsetAccountCode;
	}
	public String getOffsetAccountName() {
		return offsetAccountName;
	}
	public void setOffsetAccountName(String offsetAccountName) {
		this.offsetAccountName = offsetAccountName;
	}
	
}
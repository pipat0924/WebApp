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
@Table(name="TRSMONEYTRANSFERREF")
public class MethodMoneyTransfer {

	@Id
	@SequenceGenerator(name="mnytxnf_seq", sequenceName="mnytxnf_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mnytxnf_seq")
	@Column(name="MONEYTRANSFERREFID") private Long id;
	@Column(name="MONEYTRANSFERNO") private String no;
	@Column(name="PUBLISHER") private String bankCode;
	@Column(name="PUBLISHERNAME") private String bankName;
	@Column(name="BRANCH") private String bankBrnh;
	@Column(name="DECIMALBANKACCT") private String bankAcNo;
	@Column(name="CODEBANKACCT") private String bankAcCd;
	@Column(name="ISSUEDATE") private Date date;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="PAY_TYPE") private String payType;
	@Column(name="PAY_CODE") private String payCode;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="METHODID") private Long methodId;
	@Column(name="CURRENCYCODE") private String currencyCode;
    @Column(name="CURRENCYRATE") private BigDecimal currencyRate;
    @Column(name="FOREIGNAMOUNT") private BigDecimal foreignAmount;
    @Column(name="REFNO") private String refNo;
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
	public String getBankAcNo() {
		return bankAcNo;
	}
	public void setBankAcNo(String bankAcNo) {
		this.bankAcNo = bankAcNo;
	}
	public String getBankAcCd() {
		return bankAcCd;
	}
	public void setBankAcCd(String bankAcCd) {
		this.bankAcCd = bankAcCd;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * @param payType the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * @return the payCode
	 */
	public String getPayCode() {
		return payCode;
	}
	/**
	 * @param payCode the payCode to set
	 */
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	/**
	 * @return the paymentId
	 */
	public Long getPaymentId() {
		return paymentId;
	}
	/**
	 * @param paymentId the paymentId to set
	 */
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

	public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }
    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }
    public BigDecimal getForeignAmount() {
        return foreignAmount;
    }
    public void setForeignAmount(BigDecimal foreignAmount) {
        this.foreignAmount = foreignAmount;
    }
    public String getRefNo() {
        return refNo;
    }
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
	
	
}
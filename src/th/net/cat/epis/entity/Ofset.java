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
@Table(name="TRSOFSETREF")
public class Ofset {
	
	@Id
	@SequenceGenerator(name="TRSOFSETREF_SEQ", sequenceName="TRSOFSETREF_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRSOFSETREF_SEQ")
	@Column(name="OFSETREFID") private Long id;
	@Column(name="OFSETNO") private String no;
	@Column(name="OFSETACCOUNTCODE") private String ofsetcode;
	@Column(name="OFSETACCOUNTNAME") private String ofsetname;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@Column(name="PAYMENTID") private Long paymentId;
	@Column(name="METHODID") private Long methodId;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="PAYMENTREFID") private Transaction transaction;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the ofsetcode
	 */
	public String getOfsetcode() {
		return ofsetcode;
	}
	/**
	 * @param ofsetcode the ofsetcode to set
	 */
	public void setOfsetcode(String ofsetcode) {
		this.ofsetcode = ofsetcode;
	}
	/**
	 * @return the ofsetname
	 */
	public String getOfsetname() {
		return ofsetname;
	}
	/**
	 * @param ofsetname the ofsetname to set
	 */
	public void setOfsetname(String ofsetname) {
		this.ofsetname = ofsetname;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the updateDttm
	 */
	public Date getUpdateDttm() {
		return updateDttm;
	}
	/**
	 * @param updateDttm the updateDttm to set
	 */
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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
	
	
	
	
	
	
}

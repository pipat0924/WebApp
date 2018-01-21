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
@Table(name="TRSDISCOUNTREF")
public class MethodDiscount {

	@Id
	@SequenceGenerator(name="deduct_seq", sequenceName="deduct_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="deduct_seq")
	@Column(name="DISCOUNTREFID") private Long id;
	@Column(name="PAYMENTID") private Long transactionId;
	@Column(name="DISCOUNTTYPE") private String type;
	@Column(name="INVOICENO") private String invoiceNo;
	@Column(name="PRODUCTNO") private String productNo;
	@Column(name="FEEID") private String feeId;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private Date updateUser;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="PAYMENTREFID") private Transaction transaction;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getFeeId() {
		return feeId;
	}
	public void setFeeId(String feeId) {
		this.feeId = feeId;
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
	public Date getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Date updateUser) {
		this.updateUser = updateUser;
	}
}
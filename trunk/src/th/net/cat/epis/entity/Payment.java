package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name="CORPAYMENT")
public class Payment {

	@Id
	@SequenceGenerator(name="payment_seq", sequenceName="payment_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="payment_seq")
	@Column(name="PAYMENTID") private Long id;
	@Column(name="PAYMENTTYPE") private String type;
	@Column(name="PAYMENTCASE") private String method;
	@Column(name="OFFICERID") private Long officerId;
	@Column(name="SHOPID") private Long shopId;
	@Column(name="POSID") private Long posId;
	@Column(name="COLLECTIONUNIT") private String collectionUnit;
	@Column(name="PAYMENTDATE") private Date date;
	@Column(name="AMOUNT") private BigDecimal amount;
	@Column(name="DISCOUNT") private BigDecimal discount;
	@Column(name="CHARGE") private BigDecimal charge;
	@Column(name="VATRATE") private BigDecimal vatRate;
	@Column(name="VAT") private BigDecimal vat;
	@Column(name="TOTALCHARGE") private BigDecimal totalCharge;
	@Column(name="DEDUCTION") private BigDecimal deduction;
	@Column(name="AFTERSALEDISCOUNT") private BigDecimal afterSaleDiscount;
	@Column(name="BALANCEDUE") private BigDecimal balanceDue;
	@Column(name="RECEIVED") private BigDecimal received;
	@Column(name="CHANGE") private BigDecimal change;
	@Column(name="ADVANCED") private BigDecimal advanced;
	@Column(name="ATTRIBUTES") private String attributes;
	@Column(name="CURRENCYCODE") private String currencyCode;
	@Column(name="CURRENCYRATE") private BigDecimal currencyRate;
	@Column(name="UPDATEDTTM") private Date updateDttm;
	@Column(name="UPDATEUSER") private String updateUser;
	@OneToMany(mappedBy="paymentId", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<Method> methods = new HashSet<Method>();
	@OneToMany(mappedBy="paymentId", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<Deduct> deducts = new HashSet<Deduct>();
	@Column(name = "SPECIAL_DISCOUNT") private BigDecimal specialDiscount;

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
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Long getOfficerId() {
		return officerId;
	}
	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getPosId() {
		return posId;
	}
	public void setPosId(Long posId) {
		this.posId = posId;
	}
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
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
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public BigDecimal getVatRate() {
		return vatRate;
	}
	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public BigDecimal getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}
	public BigDecimal getDeduction() {
		return deduction;
	}
	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	public BigDecimal getAfterSaleDiscount() {
		return afterSaleDiscount;
	}
	public void setAfterSaleDiscount(BigDecimal afterSaleDiscount) {
		this.afterSaleDiscount = afterSaleDiscount;
	}
	public BigDecimal getReceived() {
		return received;
	}
	public void setReceived(BigDecimal received) {
		this.received = received;
	}
	public BigDecimal getChange() {
		return change;
	}
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	public BigDecimal getAdvanced() {
		return advanced;
	}
	public void setAdvanced(BigDecimal advanced) {
		this.advanced = advanced;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
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
	public Set<Method> getMethods() {
		return methods;
	}
	public void setMethods(Set<Method> methods) {
		this.methods = methods;
	}
	public Set<Deduct> getDeducts() {
		return deducts;
	}
	public void setDeducts(Set<Deduct> deducts) {
		this.deducts = deducts;
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
	public BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}
	public void setSpecialDiscount(BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}
	
}
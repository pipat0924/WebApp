package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
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
@Table(name="BILLINVOICE")
public class BillInvoice {

	@Id
	@SequenceGenerator(name="billinvoice_seq", sequenceName="billinvoice_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="billinvoice_seq")
	@Column(name="ID") private Long id;
	@Column(name="INVOICENO") private String invoiceNo;
	@Column(name="TOTALCHARGE") private BigDecimal totalCharge;
	@OneToMany(mappedBy="invoiceId", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<BillInvoiceProduct> products = new ArrayList<BillInvoiceProduct>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public BigDecimal getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}
	public List<BillInvoiceProduct> getProducts() {
		return products;
	}
	public void setProducts(List<BillInvoiceProduct> products) {
		this.products = products;
	}
}
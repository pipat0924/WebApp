package th.net.cat.epis.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RECEIPT_PRINTTYPE_MAPPING database table.
 * 
 */
@Entity
@Table(name="RECEIPT_PRINT_TYPE_MAPPING")
public class ReceiptPrinttypeMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReceiptPrinttypeMappingPK id;

	//bi-directional many-to-one association to Correceipt
	
	@Column(insertable=false, updatable=false)
	private Long receiptid;
	
	@Column(name="PRINT_TYPE",insertable=false, updatable=false)
	private String printType;
	
	public ReceiptPrinttypeMapping() {
	}

	public ReceiptPrinttypeMappingPK getId() {
		return this.id;
	}

	public void setId(ReceiptPrinttypeMappingPK id) {
		this.id = id;
	}

	public Long getReceiptid() {
		return receiptid;
	}

	public void setReceiptid(Long receiptid) {
		this.receiptid = receiptid;
	}

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	
}
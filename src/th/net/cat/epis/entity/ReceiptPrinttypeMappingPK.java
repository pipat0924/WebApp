package th.net.cat.epis.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RECEIPT_PRINTTYPE_MAPPING database table.
 * 
 */
@Embeddable
public class ReceiptPrinttypeMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Long receiptid;

	@Column(name="PRINT_TYPE")
	private String printType;
/*
	public ReceiptPrinttypeMappingPK(Long receiptid, String printType) {
		super();
		this.receiptid = receiptid;
		this.printType = printType;
	}
	*/
	public ReceiptPrinttypeMappingPK() {
	}
	public Long getReceiptid() {
		return this.receiptid;
	}
	public void setReceiptid(Long receiptid) {
		this.receiptid = receiptid;
	}
	public String getPrintType() {
		return this.printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReceiptPrinttypeMappingPK)) {
			return false;
		}
		ReceiptPrinttypeMappingPK castOther = (ReceiptPrinttypeMappingPK)other;
		return 
			(this.receiptid == castOther.receiptid)
			&& this.printType.equals(castOther.printType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.receiptid ^ (this.receiptid >>> 32)));
		hash = hash * prime + this.printType.hashCode();
		
		return hash;
	}
}
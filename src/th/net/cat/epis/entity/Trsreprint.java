package th.net.cat.epis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TRSREPRINT database table.
 * 
 */
@Entity
@Table(name = "TRSREPRINT")
public class Trsreprint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRSREPRINT_SEQ", sequenceName="TRSREPRINT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRSREPRINT_SEQ")
	private Long reprintid;

	private String approvedby;

	private BigDecimal category;

	private String reason;

	private Timestamp updatedttm;

	private String updatesystem;

	private String updateuser;

	private BigDecimal versionstamp;

	private String reprintflg;

	//bi-directional many-to-one association to Correceipt
	@ManyToOne
	@JoinColumn(name="RECEIPTID")
	private Receipt receipt;

	public Trsreprint() {
	}

	public Long getReprintid() {
		return this.reprintid;
	}

	public void setReprintid(Long reprintid) {
		this.reprintid = reprintid;
	}

	public String getApprovedby() {
		return this.approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public BigDecimal getCategory() {
		return this.category;
	}

	public void setCategory(BigDecimal category) {
		this.category = category;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getUpdatedttm() {
		return this.updatedttm;
	}

	public void setUpdatedttm(Timestamp updatedttm) {
		this.updatedttm = updatedttm;
	}

	public String getUpdatesystem() {
		return this.updatesystem;
	}

	public void setUpdatesystem(String updatesystem) {
		this.updatesystem = updatesystem;
	}

	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public BigDecimal getVersionstamp() {
		return this.versionstamp;
	}

	public void setVersionstamp(BigDecimal versionstamp) {
		this.versionstamp = versionstamp;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public String getReprintflg() {
		return reprintflg;
	}

	public void setReprintflg(String reprintflg) {
		this.reprintflg = reprintflg;
	}
}
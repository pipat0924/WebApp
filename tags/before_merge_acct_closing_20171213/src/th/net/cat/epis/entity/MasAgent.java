package th.net.cat.epis.entity;

import javax.persistence.*;

/**
 * Created by puthy on 5/28/17.
 */
@Entity
@Table(name = "MAS_AGENT")
public class MasAgent {

    @Id
    @SequenceGenerator(name="masagent_seq", sequenceName="masagent_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="masagent_seq")
    @Column(name="AGENTID") private Long id;
    @Column(name="AGENT_CODE") private String code;
    @Column(name="AGENT_NAME") private String name;
    @Column(name="COMPANY_NAME") private String companyName;
    @Column(name="TAXID") private String taxId;
    @Column(name="ADDRESS") private String address;
    @Column(name="TAXID_START") private String taxIdSt;
    @Column(name="TAXID_END") private String taxIdEnd;
    @Column(name="INVOICE_NO_START") private String invoiceNoSt;
    @Column(name="INVOICE_NO_END") private String invoiceNoEnd;
    @Column(name="REF1_START") private String ref1St;
    @Column(name="REF1_END") private String ref1End;
    @Column(name="REF2_START") private String ref2St;
    @Column(name="REF2_END") private String ref2End;
    @Column(name="DUE_DATE_START") private String dueDateSt;
    @Column(name="DUE_DATE_END") private String dueDateEnd;
    //@Column(name="AMOUNT_POSITION") private String amountPos;
    @Column(name="AMOUNT_START") private String amountSt;
    @Column(name="AMOUNT_END") private String amountEnd;
    @Column(name="ISPOSITIVE") private Long isPositive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

    public String getTaxIdSt() {
		return taxIdSt;
	}

	public void setTaxIdSt(String taxIdSt) {
		this.taxIdSt = taxIdSt;
	}

	

	public String getTaxIdEnd() {
		return taxIdEnd;
	}

	public void setTaxIdEnd(String taxIdEnd) {
		this.taxIdEnd = taxIdEnd;
	}

	public String getInvoiceNoSt() {
		return invoiceNoSt;
	}

	public void setInvoiceNoSt(String invoiceNoSt) {
		this.invoiceNoSt = invoiceNoSt;
	}

	public String getInvoiceNoEnd() {
		return invoiceNoEnd;
	}

	public void setInvoiceNoEnd(String invoiceNoEnd) {
		this.invoiceNoEnd = invoiceNoEnd;
	}

	public String getRef1St() {
		return ref1St;
	}

	public void setRef1St(String ref1St) {
		this.ref1St = ref1St;
	}

	public String getRef1End() {
		return ref1End;
	}

	public void setRef1End(String ref1End) {
		this.ref1End = ref1End;
	}

	public String getRef2St() {
		return ref2St;
	}

	public void setRef2St(String ref2St) {
		this.ref2St = ref2St;
	}

	public String getRef2End() {
		return ref2End;
	}

	public void setRef2End(String ref2End) {
		this.ref2End = ref2End;
	}

	public String getDueDateSt() {
		return dueDateSt;
	}

	public void setDueDateSt(String dueDateSt) {
		this.dueDateSt = dueDateSt;
	}

	public String getDueDateEnd() {
		return dueDateEnd;
	}

	public void setDueDateEnd(String dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}

	

    public String getAmountSt() {
		return amountSt;
	}

	public void setAmountSt(String amountSt) {
		this.amountSt = amountSt;
	}

	public String getAmountEnd() {
		return amountEnd;
	}

	public void setAmountEnd(String amountEnd) {
		this.amountEnd = amountEnd;
	}

	public Long getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Long isPositive) {
        this.isPositive = isPositive;
    }
}

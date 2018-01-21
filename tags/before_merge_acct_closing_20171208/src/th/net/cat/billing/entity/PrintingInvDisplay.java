package th.net.cat.billing.entity;

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.Table;

@Entity
@Table(name="CAT_PRINTING_INV_DISPLAY")
public class PrintingInvDisplay {
    @Id
    @Column(name="MKT_CODE") private Long mktCode;
    @Column(name="BILL_GROUP") private String billGroup;
    @Column(name="INV_DISPLAY") private String invDisplay;

    public Long getMktCode() {
        return mktCode;
    }
    public void setMktCode(Long mktCode) {
        this.mktCode = mktCode;
    }
    public String getBillGroup() {
        return billGroup;
    }
    public void setBillGroup(String billGroup) {
        this.billGroup = billGroup;
    }
    public String getInvDisplay() {
        return invDisplay;
    }
    public void setInvDisplay(String invDisplay) {
        this.invDisplay = invDisplay;
    }


}


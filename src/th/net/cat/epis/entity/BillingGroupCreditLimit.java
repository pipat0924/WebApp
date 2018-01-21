package th.net.cat.epis.entity;

import javax.persistence.*;

/**
 * Created by T'Tee Puthy on 2/6/2017.
 */
@Entity
@Table(name = "MASBILLING_GROUP_CREDIT_LIMIT")
public class BillingGroupCreditLimit {
    @Id
    @SequenceGenerator(name="BILLING_GROUP_CREDIT_LIMIT_SEQ", sequenceName="BILLING_GROUP_CREDIT_LIMIT_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILLING_GROUP_CREDIT_LIMIT_SEQ")
    @Column (name = "ID") private Long id;
    @Column (name = "BILLING_GROUP_CODE") private String billingGroupCode;
    @Column (name = "BILLING_GROUP_FULL_NAME") private String billingGroupFullName;
    @Column (name = "BILLING_GROUP_DESC") private String billingGroupDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillingGroupCode() {
        return billingGroupCode;
    }

    public void setBillingGroupCode(String billingGroupCode) {
        this.billingGroupCode = billingGroupCode;
    }

    public String getBillingGroupFullName() {
        return billingGroupFullName;
    }

    public void setBillingGroupFullName(String billingGroupFullName) {
        this.billingGroupFullName = billingGroupFullName;
    }

    public String getBillingGroupDesc() {
        return billingGroupDesc;
    }

    public void setBillingGroupDesc(String billingGroupDesc) {
        this.billingGroupDesc = billingGroupDesc;
    }
}

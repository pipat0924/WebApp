package th.net.cat.epis.dto;

import java.io.Serializable;

/**
 * Created by T'Tee Puthy on 2/6/2017.
 */
public class BillingGroupCreditLimit implements Serializable {

    private static final long serialVersionUID = 3196387094568659122L;
    private Long id;
    private String billingGroupCode;
    private String billingGroupFullName;
    private String billingGroupDesc;

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

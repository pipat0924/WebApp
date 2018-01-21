package th.net.cat.crm.entity;

        import java.util.Date;

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.Table;

@Entity
@Table(name="V_CATCRM_SERVICE",schema = "CRMDATA")
public class CustomerService {

    @Id
    @Column(name="CAT_SVC_ID") private String catSvcId;
    @Column(name="CUSTOMER_ID") private String customerId;
    @Column(name="CAT_BILL_ACCT_ID") private String catBillAcctId;
    @Column(name="CUSTOMER_NAME") private String customerName;
    @Column(name="CUSTOMER_NUMBER") private String customerNo;
    @Column(name="CAT_CARD_NUMBER") private String catCardNo;
    @Column(name="CAT_BILL_ACCT_NUMBER") private String catBillAcctNo;
    @Column(name="CAT_BILL_ACCT_NAME") private String catBillAcctName;
    @Column(name="PROPERTY_ONE") private String propertyOne;
    @Column(name="PROPERTY_TWO") private String propertyTwo;
    //	@Column(name="START_DATE") private Date startDate;
//	@Column(name="END_DATE") private Date endDate;
    @Column(name="SERVICE_TYPE") private String serviceType;
    @Column(name="STATUS") private String status;
    @Column(name="STATUS_LKP") private Long statusLkp;
    @Column(name="CAT_SVC_TYPE_LKP") private Long catSvcTypeLkp;
    @Column(name="BILLING_GROUP_CODE") private String billingGroupCode;
    @Column(name="BILLING_GROUP") private String billingGroup;
    @Column(name="BILLING_GROUP_FULL") private String billingGroupFull;

    public String getCatSvcId() {
        return catSvcId;
    }
    public void setCatSvcId(String catSvcId) {
        this.catSvcId = catSvcId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCatBillAcctId() {
        return catBillAcctId;
    }
    public void setCatBillAcctId(String catBillAcctId) {
        this.catBillAcctId = catBillAcctId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    public String getCatCardNo() {
        return catCardNo;
    }
    public void setCatCardNo(String catCardNo) {
        this.catCardNo = catCardNo;
    }
    public String getCatBillAcctNo() {
        return catBillAcctNo;
    }
    public void setCatBillAcctNo(String catBillAcctNo) {
        this.catBillAcctNo = catBillAcctNo;
    }
    public String getCatBillAcctName() {
        return catBillAcctName;
    }
    public void setCatBillAcctName(String catBillAcctName) {
        this.catBillAcctName = catBillAcctName;
    }
    public String getPropertyOne() {
        return propertyOne;
    }
    public void setPropertyOne(String propertyOne) {
        this.propertyOne = propertyOne;
    }
    public String getPropertyTwo() {
        return propertyTwo;
    }
    public void setPropertyTwo(String propertyTwo) {
        this.propertyTwo = propertyTwo;
    }
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getStatusLkp() {
        return statusLkp;
    }
    public void setStatusLkp(Long statusLkp) {
        this.statusLkp = statusLkp;
    }
    public Long getCatSvcTypeLkp() {
        return catSvcTypeLkp;
    }
    public void setCatSvcTypeLkp(Long catSvcTypeLkp) {
        this.catSvcTypeLkp = catSvcTypeLkp;
    }
    public String getBillingGroupCode() {
        return billingGroupCode;
    }
    public void setBillingGroupCode(String billingGroupCode) {
        this.billingGroupCode = billingGroupCode;
    }
    public String getBillingGroup() {
        return billingGroup;
    }
    public void setBillingGroup(String billingGroup) {
        this.billingGroup = billingGroup;
    }
    public String getBillingGroupFull() {
        return billingGroupFull;
    }
    public void setBillingGroupFull(String billingGroupFull) {
        this.billingGroupFull = billingGroupFull;
    }
}

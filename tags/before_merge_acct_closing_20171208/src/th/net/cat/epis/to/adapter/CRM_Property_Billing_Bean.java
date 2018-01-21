package th.net.cat.epis.to.adapter;

public class CRM_Property_Billing_Bean {
	private String property1 ="";
	private String property2 = "";
	private String cat_bill_acct_number = "";
	private String bill_first_name = "";
	private String bill_last_name = "";
	private String bill_comp_name = "";
	private String property_label = "";
	private String billing_group = "";

	
	public CRM_Property_Billing_Bean(String property1,
			String property2, String cat_bill_acct_number,
			String bill_first_name,String bill_last_name,String bill_comp_name , String property_label ,String billing_group ) {
		
		this.property1 = property1;
		this.property2 = property2;
		this.cat_bill_acct_number =cat_bill_acct_number;
		this.bill_first_name = bill_first_name;
		this.bill_last_name = bill_last_name;
		this.bill_comp_name = bill_comp_name;
		this.property_label = property_label;
		this.billing_group = billing_group;

	}

	
	public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getCat_bill_acct_number() {
        return cat_bill_acct_number;
    }

    public void setCat_bill_acct_number(String cat_bill_acct_number) {
        this.cat_bill_acct_number = cat_bill_acct_number;
    }

    public String getBill_first_name() {
        return bill_first_name;
    }

    public void setBill_first_name(String bill_first_name) {
        this.bill_first_name = bill_first_name;
    }

    public String getBill_last_name() {
        return bill_last_name;
    }

    public void setBill_last_name(String bill_last_name) {
        this.bill_last_name = bill_last_name;
    }

    public String getBill_comp_name() {
        return bill_comp_name;
    }

    public void setBill_comp_name(String bill_comp_name) {
        this.bill_comp_name = bill_comp_name;
    }

    public String getProperty_label() {
        return property_label;
    }

    public void setProperty_label(String property_label) {
        this.property_label = property_label;
    }

    public String getBilling_group() {
        return billing_group;
    }

    public void setBilling_group(String billing_group) {
        this.billing_group = billing_group;
    }
}

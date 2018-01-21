/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.net.cat.epis.to.adapter;

/**
 * 
 * @author softpos2013
 */
public class CRM_Customer_Billing_Bean {

	private String cat_bill_acct_id = "";
	private String cat_bill_acct_number = "";
	private String bill_first_name = "";
	private String bill_last_name = "";
	private String customer_account_name = "";
	private String tax_register_number = "";
	private String customer_id = "";
	private String customer_type = "";
	private String branch_id = "";
	private String bill_addr_line1 = "";
	private String bill_addr_line2 = "";
	private String bill_addr_line3 = "";
	private String bill_addr_line4 = "";
	private String bill_addr_khet_amphur = "";
	private String bill_addr_province_lkp = "";
	private String bill_addr_post_code = "";
	private String vat_addr_line1 = "";
	private String vat_addr_line2 = "";
	private String vat_addr_line3 = "";
	private String vat_addr_line4 = "";
	private String vat_addr_khet_amphur = "";
	private String vat_addr_province_lkp = "";
	private String vat_addr_post_code = "";
	private String deli_addr_line1 = "";
	private String deli_addr_line2 = "";
	private String deli_addr_line3 = "";
	private String deli_addr_line4 = "";
	private String deli_addr_khet_amphur = "";
	private String deli_addr_province_lkp = "";
	private String deli_addr_post_code = "";
	private String deli_vat_addr_line1 = "";
	private String deli_vat_addr_line2 = "";
	private String deli_vat_addr_line3 = "";
	private String deli_vat_addr_line4 = "";
	private String deli_vat_addr_khet_amphur = "";
	private String deli_vat_addr_province_lkp = "";
	private String deli_vat_addr_post_code = "";
	private String customer_full_name = "";
	private String customer_number = "";
	private String billing_group = "";
	private String bill_salutation = "";
	private String bill_comp_salutation = "";
	private String pay_credit_card_number = "";
	private String pay_credit_card_carrier = "";
	private String pay_bank_number = "";
	private String pay_bank_name = "";
	private String employee_name = "";
	private String customer_number_ca = "";
	private String cat_customer_segment = "";
	private String cat_customer_group = "";
	private String cat_customer_type = "";
	private String employee_id = "";
	private String bill_comp_name = "";
	private String property_label = "";;

	public CRM_Customer_Billing_Bean(String cat_bill_acct_number,
			String tax_register_number, String bill_first_name,
			String bill_last_name, String bill_comp_name ,String property_label , String billing_group ) {
		
		this.cat_bill_acct_number = cat_bill_acct_number;
		this.tax_register_number = tax_register_number;
		this.bill_first_name = bill_first_name;
		this.bill_last_name = bill_last_name;
		this.bill_comp_name = bill_comp_name;
		this.property_label = property_label;
		this.billing_group = billing_group;

	}

	public String getCat_bill_acct_id() {
		return cat_bill_acct_id;
	}

	public void setCat_bill_acct_id(String cat_bill_acct_id) {
		this.cat_bill_acct_id = cat_bill_acct_id;
	}

	public String getCat_bill_acct_number() {
		return cat_bill_acct_number;
	}

	public void setCat_bill_acct_number(String cat_bill_acct_number) {
		this.cat_bill_acct_number = cat_bill_acct_number;
	}

	public String getCustomer_account_name() {
		return customer_account_name;
	}

	public void setCustomer_account_name(String customer_account_name) {
		this.customer_account_name = customer_account_name;
	}

	public String getTax_register_number() {
		return tax_register_number;
	}

	public void setTax_register_number(String tax_register_number) {
		this.tax_register_number = tax_register_number;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getBill_addr_line1() {
		return bill_addr_line1;
	}

	public void setBill_addr_line1(String bill_addr_line1) {
		this.bill_addr_line1 = bill_addr_line1;
	}

	public String getBill_addr_line2() {
		return bill_addr_line2;
	}

	public void setBill_addr_line2(String bill_addr_line2) {
		this.bill_addr_line2 = bill_addr_line2;
	}

	public String getBill_addr_line3() {
		return bill_addr_line3;
	}

	public void setBill_addr_line3(String bill_addr_line3) {
		this.bill_addr_line3 = bill_addr_line3;
	}

	public String getBill_addr_line4() {
		return bill_addr_line4;
	}

	public void setBill_addr_line4(String bill_addr_line4) {
		this.bill_addr_line4 = bill_addr_line4;
	}

	public String getBill_addr_khet_amphur() {
		return bill_addr_khet_amphur;
	}

	public void setBill_addr_khet_amphur(String bill_addr_khet_amphur) {
		this.bill_addr_khet_amphur = bill_addr_khet_amphur;
	}

	public String getBill_addr_province_lkp() {
		return bill_addr_province_lkp;
	}

	public void setBill_addr_province_lkp(String bill_addr_province_lkp) {
		this.bill_addr_province_lkp = bill_addr_province_lkp;
	}

	public String getBill_addr_post_code() {
		return bill_addr_post_code;
	}

	public void setBill_addr_post_code(String bill_addr_post_code) {
		this.bill_addr_post_code = bill_addr_post_code;
	}

	public String getVat_addr_line1() {
		return vat_addr_line1;
	}

	public void setVat_addr_line1(String vat_addr_line1) {
		this.vat_addr_line1 = vat_addr_line1;
	}

	public String getVat_addr_line2() {
		return vat_addr_line2;
	}

	public void setVat_addr_line2(String vat_addr_line2) {
		this.vat_addr_line2 = vat_addr_line2;
	}

	public String getVat_addr_line3() {
		return vat_addr_line3;
	}

	public void setVat_addr_line3(String vat_addr_line3) {
		this.vat_addr_line3 = vat_addr_line3;
	}

	public String getVat_addr_line4() {
		return vat_addr_line4;
	}

	public void setVat_addr_line4(String vat_addr_line4) {
		this.vat_addr_line4 = vat_addr_line4;
	}

	public String getVat_addr_khet_amphur() {
		return vat_addr_khet_amphur;
	}

	public void setVat_addr_khet_amphur(String vat_addr_khet_amphur) {
		this.vat_addr_khet_amphur = vat_addr_khet_amphur;
	}

	public String getVat_addr_province_lkp() {
		return vat_addr_province_lkp;
	}

	public void setVat_addr_province_lkp(String vat_addr_province_lkp) {
		this.vat_addr_province_lkp = vat_addr_province_lkp;
	}

	public String getVat_addr_post_code() {
		return vat_addr_post_code;
	}

	public void setVat_addr_post_code(String vat_addr_post_code) {
		this.vat_addr_post_code = vat_addr_post_code;
	}

	public String getDeli_addr_line1() {
		return deli_addr_line1;
	}

	public void setDeli_addr_line1(String deli_addr_line1) {
		this.deli_addr_line1 = deli_addr_line1;
	}

	public String getDeli_addr_line2() {
		return deli_addr_line2;
	}

	public void setDeli_addr_line2(String deli_addr_line2) {
		this.deli_addr_line2 = deli_addr_line2;
	}

	public String getDeli_addr_line3() {
		return deli_addr_line3;
	}

	public void setDeli_addr_line3(String deli_addr_line3) {
		this.deli_addr_line3 = deli_addr_line3;
	}

	public String getDeli_addr_line4() {
		return deli_addr_line4;
	}

	public void setDeli_addr_line4(String deli_addr_line4) {
		this.deli_addr_line4 = deli_addr_line4;
	}

	public String getDeli_addr_khet_amphur() {
		return deli_addr_khet_amphur;
	}

	public void setDeli_addr_khet_amphur(String deli_addr_khet_amphur) {
		this.deli_addr_khet_amphur = deli_addr_khet_amphur;
	}

	public String getDeli_addr_province_lkp() {
		return deli_addr_province_lkp;
	}

	public void setDeli_addr_province_lkp(String deli_addr_province_lkp) {
		this.deli_addr_province_lkp = deli_addr_province_lkp;
	}

	public String getDeli_addr_post_code() {
		return deli_addr_post_code;
	}

	public void setDeli_addr_post_code(String deli_addr_post_code) {
		this.deli_addr_post_code = deli_addr_post_code;
	}

	public String getDeli_vat_addr_line1() {
		return deli_vat_addr_line1;
	}

	public void setDeli_vat_addr_line1(String deli_vat_addr_line1) {
		this.deli_vat_addr_line1 = deli_vat_addr_line1;
	}

	public String getDeli_vat_addr_line2() {
		return deli_vat_addr_line2;
	}

	public void setDeli_vat_addr_line2(String deli_vat_addr_line2) {
		this.deli_vat_addr_line2 = deli_vat_addr_line2;
	}

	public String getDeli_vat_addr_line3() {
		return deli_vat_addr_line3;
	}

	public void setDeli_vat_addr_line3(String deli_vat_addr_line3) {
		this.deli_vat_addr_line3 = deli_vat_addr_line3;
	}

	public String getDeli_vat_addr_line4() {
		return deli_vat_addr_line4;
	}

	public void setDeli_vat_addr_line4(String deli_vat_addr_line4) {
		this.deli_vat_addr_line4 = deli_vat_addr_line4;
	}

	public String getDeli_vat_addr_khet_amphur() {
		return deli_vat_addr_khet_amphur;
	}

	public void setDeli_vat_addr_khet_amphur(String deli_vat_addr_khet_amphur) {
		this.deli_vat_addr_khet_amphur = deli_vat_addr_khet_amphur;
	}

	public String getDeli_vat_addr_province_lkp() {
		return deli_vat_addr_province_lkp;
	}

	public void setDeli_vat_addr_province_lkp(String deli_vat_addr_province_lkp) {
		this.deli_vat_addr_province_lkp = deli_vat_addr_province_lkp;
	}

	public String getDeli_vat_addr_post_code() {
		return deli_vat_addr_post_code;
	}

	public void setDeli_vat_addr_post_code(String deli_vat_addr_post_code) {
		this.deli_vat_addr_post_code = deli_vat_addr_post_code;
	}

	public String getCustomer_full_name() {
		return customer_full_name;
	}

	public void setCustomer_full_name(String customer_full_name) {
		this.customer_full_name = customer_full_name;
	}

	public String getCustomer_number() {
		return customer_number;
	}

	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}

	public String getBilling_group() {
		return billing_group;
	}

	public void setBilling_group(String billing_group) {
		this.billing_group = billing_group;
	}

	public String getBill_salutation() {
		return bill_salutation;
	}

	public void setBill_salutation(String bill_salutation) {
		this.bill_salutation = bill_salutation;
	}

	public String getBill_comp_salutation() {
		return bill_comp_salutation;
	}

	public void setBill_comp_salutation(String bill_comp_salutation) {
		this.bill_comp_salutation = bill_comp_salutation;
	}

	public String getPay_credit_card_number() {
		return pay_credit_card_number;
	}

	public void setPay_credit_card_number(String pay_credit_card_number) {
		this.pay_credit_card_number = pay_credit_card_number;
	}

	public String getPay_credit_card_carrier() {
		return pay_credit_card_carrier;
	}

	public void setPay_credit_card_carrier(String pay_credit_card_carrier) {
		this.pay_credit_card_carrier = pay_credit_card_carrier;
	}

	public String getPay_bank_number() {
		return pay_bank_number;
	}

	public void setPay_bank_number(String pay_bank_number) {
		this.pay_bank_number = pay_bank_number;
	}

	public String getPay_bank_name() {
		return pay_bank_name;
	}

	public void setPay_bank_name(String pay_bank_name) {
		this.pay_bank_name = pay_bank_name;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getCustomer_number_ca() {
		return customer_number_ca;
	}

	public void setCustomer_number_ca(String customer_number_ca) {
		this.customer_number_ca = customer_number_ca;
	}

	public String getCat_customer_segment() {
		return cat_customer_segment;
	}

	public void setCat_customer_segment(String cat_customer_segment) {
		this.cat_customer_segment = cat_customer_segment;
	}

	public String getCat_customer_group() {
		return cat_customer_group;
	}

	public void setCat_customer_group(String cat_customer_group) {
		this.cat_customer_group = cat_customer_group;
	}

	public String getCat_customer_type() {
		return cat_customer_type;
	}

	public void setCat_customer_type(String cat_customer_type) {
		this.cat_customer_type = cat_customer_type;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
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

}

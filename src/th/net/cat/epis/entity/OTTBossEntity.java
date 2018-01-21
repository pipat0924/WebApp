package th.net.cat.epis.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="INV_SOURCE")
public class OTTBossEntity {

//	@SequenceGenerator(name="ID", sequenceName="ID", allocationSize=1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID")
	@Id  
	@SequenceGenerator(name="INV_SOURCE_SEQ", sequenceName="INV_SOURCE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_SOURCE_SEQ")
	@Column(name="ID") private int id;
	@Column(name="SOURCE") private String source;
	@Column(name="AR_REF") private String ar_ref;//account_no
	@Column(name="BILLGROUP") private String billgroup;
	@Column(name="ACCOUNT_CODE_OLD") private String account_code_old;
	@Column(name="ACCOUNT_NO") private String account_no;
	@Column(name="TAX_ID") private String tax_id;
	@Column(name="CUSTOMER_NAME") private String customer_name;
	@Column(name="CUSTOMER_LASTNAME") private String customer_lastname;
	@Column(name="CUSTOMER_ADDRESS") private String customer_address;
	@Column(name="CUSTOMER_BRANCH_CODE") private String customer_branch_code;
	@Column(name="E_GP") private String e_gp;
	@Column(name="CUSTOMER_STAUS") private String customer_status;
	@Column(name="REGION") private String region;
	@Column(name="REGION_NAME") private String region_name;
	@Column(name="NEW_CODE") private String new_code;
	@Column(name="CUSTOMER_GROUP") private String cus_group;
	@Column(name="CUS_CATEGORY_CODE") private String cus_category_code;
	@Column(name="CUS_CATEGORY_NAME") private String cus_category_name;
	@Column(name="CUSTOMER_TYPE_CODE") private String customer_type_code;
	@Column(name="CUSTOMER_TYPE_NAME") private String customer_type_name;
	@Column(name="SEGMENT_CODE") private String segment_code;
	@Column(name="PRODUCT_CODE") private String product_code;
	@Column(name="SUB_PRODUCT_CODE") private String sub_product_code;
	@Column(name="REVENUE_TYPE_CODE") private String revenue_type_code;
	@Column(name="INVDATE") private Timestamp invdate;
	@Column(name="DUEDATE") private Timestamp duedate;
	@Column(name="PAYDATE") private Timestamp paydate;
	@Column(name="INV_PERIOD") private String inv_period;
	@Column(name="SAP_PERIOD") private String  sap_period;
	@Column(name="AMOUNT_AR") private BigDecimal  amount_ar;
	@Column(name="VAT_AMOUNT_AR") private BigDecimal  vat_amount_ar;
	@Column(name="TOTAL_AMOUNT_AR") private BigDecimal  total_amount_ar;
	@Column(name="CURRANCY_AR") private String  currancy_ar;
	@Column(name="EXCH_RATE_AR") private BigDecimal  exch_rate_ar;
	@Column(name="EXCH_RATE_DATE_AR") private Timestamp  exch_rate_date_ar;
	@Column(name="VAT_RATE_AR") private String  vat_rate_ar;
	@Column(name="WT_RATE") private BigDecimal  wt_rate;
	@Column(name="DISCOUNT") private BigDecimal  discount;
	@Column(name="TAX_INCLUDE") private String  tax_include;
	@Column(name="TOTAL_AMOUNT_PAID") private BigDecimal  total_amonut_paid;
	@Column(name="CURRENCY_PAID") private String  currency_paid;
	@Column(name="EXCH_RATE_PAID") private BigDecimal  exch_rate_paid;
	@Column(name="EXCH_RATE_DATE_PAID") private Timestamp  exch_rate_date_paid;
	@Column(name="PAY_ADVANCE") private BigDecimal  pay_advance;
	@Column(name="TRADING_PART") private String  trading_part;
	@Column(name="TEXT") private String  text;
	@Column(name="CCTR") private String  cctr;
	@Column(name="SUBSCR_NO") private String  subscr_no;
	@Column(name="LOAD_DATE") private String  load_date;
	@Column(name="SERVICE_PRIORITY_PRODUCT") private String  service_priority_product;
	@Column(name="RENTAL_CHARGE") private String  rental_charge;
	@Column(name="USESAGE_CHARGE") private String  usesage_charge;
	@Column(name="PROCESS_DATE") private Timestamp  process_date;
	@Column(name="SERV_TYPE_CODE") private String  serv_type_code;
	@Column(name="SERV_DESC") private String  serv_desc;
	@Column(name="STD_CODE") private String  std_code;
	@Column(name="SERVICE_CODE") private String  service_code;
	@Column(name="SERVICE_NAME") private String  service_name;
	@Column(name="REGION_SAP") private String  region_sap;
	@Column(name="CCTR_SAP") private String  cctr_sap;
	@Column(name="SERVICE_PRIORITY") private String  service_priority;
	@Column(name="CATEGORY") private String  category;
	@Column(name="POSTING_DATE") private String  posting_date;
	@Column(name="CALLS") private String  calls;
	@Column(name="RATED_UNITS") private String  rated_units;
	@Column(name="PRIMARY_UNITS") private String  primary_units;
	@Column(name="SECONDARY_UNITS") private String  secondary_units;
	@Column(name="THIRD_UNITS") private String  third_units;
	@Column(name="PROPERTY1") private String  property_1;
	@Column(name="PROPERTY2") private String  property_2;
	@Column(name="CARRIER_CODE") private String  carrier_code;
	@Column(name="CARRIER_NAME") private String  carrier_name;
	@Column(name="PRODUCT_NAME") private String  product_name;
	@Column(name="SUB_PRODUCT_NAME") private String  sub_product_name;
	@Column(name="REV_TYPE_NAME") private String  rev_type_name;
	@Column(name="GL_ACCOUNT") private String  gl_account;
	@Column(name="REMARK") private String  remark;
	@Column(name="CREATE_DATE") private Timestamp  create_date;
	@Column(name="CREATE_BY") private String  create_by;
	@Column(name="UPDATE_DATE") private Timestamp  update_date;
	@Column(name="UPDATE_BY") private String  update_by;
	@Column(name="RECORD_STATUS") private String  record_status;
	@Column(name="BALANCE_DUE") private BigDecimal  balancedue;
	@Column(name="FISCAL_YEAR") private Integer  fiscalYear;
	@Column(name="AMOUNT_OTHER_AR") private BigDecimal  amountBAR;
	@Column(name="TAX_CODE") private String  taxCode;
	@Column(name="MESSAGE") private String  message;
	@Column(name="HEADER_TEXT") private String  headerText;
	@Column(name="DOC_NO") private String  docNo;
	@Column(name="RECONCILE") private String  reconcile;
	@Column(name="COMPANY_CODE") private String companyCode;
	
	public String getCurrancy_ar() {
		return currancy_ar;
	}
	public void setCurrancy_ar(String currancy_ar) {
		this.currancy_ar = currancy_ar;
	}
	public BigDecimal getBalancedue() {
		return balancedue;
	}
	public void setBalancedue(BigDecimal balancedue) {
		this.balancedue = balancedue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAr_ref() {
		return ar_ref;
	}
	public void setAr_ref(String ar_ref) {
		this.ar_ref = ar_ref;
	}
	public String getBillgroup() {
		return billgroup;
	}
	public void setBillgroup(String billgroup) {
		this.billgroup = billgroup;
	}
	public String getAccount_code_old() {
		return account_code_old;
	}
	public void setAccount_code_old(String account_code_old) {
		this.account_code_old = account_code_old;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getTax_id() {
		return tax_id;
	}
	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_lastname() {
		return customer_lastname;
	}
	public void setCustomer_lastname(String customer_lastname) {
		this.customer_lastname = customer_lastname;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_branch_code() {
		return customer_branch_code;
	}
	public void setCustomer_branch_code(String customer_branch_code) {
		this.customer_branch_code = customer_branch_code;
	}
	public String getE_gp() {
		return e_gp;
	}
	public void setE_gp(String e_gp) {
		this.e_gp = e_gp;
	}
	public String getCustomer_status() {
		return customer_status;
	}
	public void setCustomer_status(String customer_status) {
		this.customer_status = customer_status;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getNew_code() {
		return new_code;
	}
	public void setNew_code(String new_code) {
		this.new_code = new_code;
	}
	public String getCus_group() {
		return cus_group;
	}
	public void setCus_group(String cus_group) {
		this.cus_group = cus_group;
	}
	public String getCus_category_code() {
		return cus_category_code;
	}
	public void setCus_category_code(String cus_category_code) {
		this.cus_category_code = cus_category_code;
	}
	public String getCus_category_name() {
		return cus_category_name;
	}
	public void setCus_category_name(String cus_category_name) {
		this.cus_category_name = cus_category_name;
	}
	public String getCustomer_type_code() {
		return customer_type_code;
	}
	public void setCustomer_type_code(String customer_type_code) {
		this.customer_type_code = customer_type_code;
	}
	public String getCustomer_type_name() {
		return customer_type_name;
	}
	public void setCustomer_type_name(String customer_type_name) {
		this.customer_type_name = customer_type_name;
	}
	public String getSegment_code() {
		return segment_code;
	}
	public void setSegment_code(String segment_code) {
		this.segment_code = segment_code;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getSub_product_code() {
		return sub_product_code;
	}
	public void setSub_product_code(String sub_product_code) {
		this.sub_product_code = sub_product_code;
	}
	public String getRevenue_type_code() {
		return revenue_type_code;
	}
	public void setRevenue_type_code(String revenue_type_code) {
		this.revenue_type_code = revenue_type_code;
	}
	public Timestamp getInvdate() {
		return invdate;
	}
	public void setInvdate(Timestamp invdate) {
		this.invdate = invdate;
	}
	public Timestamp getDuedate() {
		return duedate;
	}
	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}
	public Timestamp getPaydate() {
		return paydate;
	}
	public void setPaydate(Timestamp paydate) {
		this.paydate = paydate;
	}
	public String getInv_period() {
		return inv_period;
	}
	public void setInv_period(String inv_period) {
		this.inv_period = inv_period;
	}
	public String getSap_period() {
		return sap_period;
	}
	public void setSap_period(String sap_period) {
		this.sap_period = sap_period;
	}
	public BigDecimal getAmount_ar() {
		return amount_ar;
	}
	public void setAmount_ar(BigDecimal amount_ar) {
		this.amount_ar = amount_ar;
	}
	public BigDecimal getVat_amount_ar() {
		return vat_amount_ar;
	}
	public void setVat_amount_ar(BigDecimal vat_amount_ar) {
		this.vat_amount_ar = vat_amount_ar;
	}
	public BigDecimal getTotal_amount_ar() {
		return total_amount_ar;
	}
	public void setTotal_amount_ar(BigDecimal total_amount_ar) {
		this.total_amount_ar = total_amount_ar;
	}
	
	public BigDecimal getExch_rate_ar() {
		return exch_rate_ar;
	}
	public void setExch_rate_ar(BigDecimal exch_rate_ar) {
		this.exch_rate_ar = exch_rate_ar;
	}
	public Timestamp getExch_rate_date_ar() {
		return exch_rate_date_ar;
	}
	public void setExch_rate_date_ar(Timestamp exch_rate_date_ar) {
		this.exch_rate_date_ar = exch_rate_date_ar;
	}
	public String getVat_rate_ar() {
		return vat_rate_ar;
	}
	public void setVat_rate_ar(String vat_rate_ar) {
		this.vat_rate_ar = vat_rate_ar;
	}
	public BigDecimal getWt_rate() {
		return wt_rate;
	}
	public void setWt_rate(BigDecimal wt_rate) {
		this.wt_rate = wt_rate;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public String getTax_include() {
		return tax_include;
	}
	public void setTax_include(String tax_include) {
		this.tax_include = tax_include;
	}
	
	public BigDecimal getTotal_amonut_paid() {
		return total_amonut_paid;
	}
	public void setTotal_amonut_paid(BigDecimal total_amonut_paid) {
		this.total_amonut_paid = total_amonut_paid;
	}
	public String getCurrency_paid() {
		return currency_paid;
	}
	public void setCurrency_paid(String currency_paid) {
		this.currency_paid = currency_paid;
	}
	public BigDecimal getExch_rate_paid() {
		return exch_rate_paid;
	}
	public void setExch_rate_paid(BigDecimal exch_rate_paid) {
		this.exch_rate_paid = exch_rate_paid;
	}
	public Timestamp getExch_rate_date_paid() {
		return exch_rate_date_paid;
	}
	public void setExch_rate_date_paid(Timestamp exch_rate_date_paid) {
		this.exch_rate_date_paid = exch_rate_date_paid;
	}
	public BigDecimal getPay_advance() {
		return pay_advance;
	}
	public void setPay_advance(BigDecimal pay_advance) {
		this.pay_advance = pay_advance;
	}
	public String getTrading_part() {
		return trading_part;
	}
	public void setTrading_part(String trading_part) {
		this.trading_part = trading_part;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCctr() {
		return cctr;
	}
	public void setCctr(String cctr) {
		this.cctr = cctr;
	}
	public String getSubscr_no() {
		return subscr_no;
	}
	public void setSubscr_no(String subscr_no) {
		this.subscr_no = subscr_no;
	}
	public String getLoad_date() {
		return load_date;
	}
	public void setLoad_date(String load_date) {
		this.load_date = load_date;
	}
	public String getService_priority_product() {
		return service_priority_product;
	}
	public void setService_priority_product(String service_priority_product) {
		this.service_priority_product = service_priority_product;
	}
	public String getRental_charge() {
		return rental_charge;
	}
	public void setRental_charge(String rental_charge) {
		this.rental_charge = rental_charge;
	}
	public String getUsesage_charge() {
		return usesage_charge;
	}
	public void setUsesage_charge(String usesage_charge) {
		this.usesage_charge = usesage_charge;
	}
	public Timestamp getProcess_date() {
		return process_date;
	}
	public void setProcess_date(Timestamp process_date) {
		this.process_date = process_date;
	}
	public String getServ_type_code() {
		return serv_type_code;
	}
	public void setServ_type_code(String serv_type_code) {
		this.serv_type_code = serv_type_code;
	}
	public String getServ_desc() {
		return serv_desc;
	}
	public void setServ_desc(String serv_desc) {
		this.serv_desc = serv_desc;
	}
	public String getStd_code() {
		return std_code;
	}
	public void setStd_code(String std_code) {
		this.std_code = std_code;
	}
	public String getService_code() {
		return service_code;
	}
	public void setService_code(String service_code) {
		this.service_code = service_code;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getRegion_sap() {
		return region_sap;
	}
	public void setRegion_sap(String region_sap) {
		this.region_sap = region_sap;
	}
	public String getCctr_sap() {
		return cctr_sap;
	}
	public void setCctr_sap(String cctr_sap) {
		this.cctr_sap = cctr_sap;
	}
	public String getService_priority() {
		return service_priority;
	}
	public void setService_priority(String service_priority) {
		this.service_priority = service_priority;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	public String getCalls() {
		return calls;
	}
	public void setCalls(String calls) {
		this.calls = calls;
	}
	public String getRated_units() {
		return rated_units;
	}
	public void setRated_units(String rated_units) {
		this.rated_units = rated_units;
	}
	public String getPrimary_units() {
		return primary_units;
	}
	public void setPrimary_units(String primary_units) {
		this.primary_units = primary_units;
	}
	public String getSecondary_units() {
		return secondary_units;
	}
	public void setSecondary_units(String secondary_units) {
		this.secondary_units = secondary_units;
	}
	public String getThird_units() {
		return third_units;
	}
	public void setThird_units(String third_units) {
		this.third_units = third_units;
	}
	public String getProperty_1() {
		return property_1;
	}
	public void setProperty_1(String property_1) {
		this.property_1 = property_1;
	}
	public String getProperty_2() {
		return property_2;
	}
	public void setProperty_2(String property_2) {
		this.property_2 = property_2;
	}
	public String getCarrier_code() {
		return carrier_code;
	}
	public void setCarrier_code(String carrier_code) {
		this.carrier_code = carrier_code;
	}
	public String getCarrier_name() {
		return carrier_name;
	}
	public void setCarrier_name(String carrier_name) {
		this.carrier_name = carrier_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getSub_product_name() {
		return sub_product_name;
	}
	public void setSub_product_name(String sub_product_name) {
		this.sub_product_name = sub_product_name;
	}
	public String getRev_type_name() {
		return rev_type_name;
	}
	public void setRev_type_name(String rev_type_name) {
		this.rev_type_name = rev_type_name;
	}
	public String getGl_account() {
		return gl_account;
	}
	public void setGl_account(String gl_account) {
		this.gl_account = gl_account;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getRecord_status() {
		return record_status;
	}
	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}
	public Integer getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(Integer fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getReconcile() {
		return reconcile;
	}
	public void setReconcile(String reconcile) {
		this.reconcile = reconcile;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;	
	}
	public BigDecimal getAmountBAR() {
		return amountBAR;
	}
	public void setAmountBAR(BigDecimal amountBAR) {
		this.amountBAR = amountBAR;
	}
	
}

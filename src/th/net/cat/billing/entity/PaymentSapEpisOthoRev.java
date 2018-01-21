package th.net.cat.billing.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PAYMENT_SAP_EPIS_OTHO_REV" , schema = "ARBOR")
public class PaymentSapEpisOthoRev {

	@Id
	@SequenceGenerator(name="ARBOR.PAYMENT_SAP_EPIS_REVERSE_SEQ", sequenceName="ARBOR.PAYMENT_SAP_EPIS_REVERSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARBOR.PAYMENT_SAP_EPIS_REVERSE_SEQ")
	@Column(name="ID") private Long id;
	@Column(name="CONTRNO") private String  contrno;
	@Column(name="AR_REF") private String ar_ref;
	@Column(name="PAY_LOCATION") private String pay_location;
	@Column(name="PAY_DATE") private String pay_date;
	@Column(name="PAY_AMOUNT") private BigDecimal pay_amount;
	@Column(name="PAY_VAT") private BigDecimal pay_vat;
	@Column(name="PAY_WT") private BigDecimal pay_wt;
	@Column(name="GL_ACCOUNT") private String gl_account;
	@Column(name="TRADING_PART") private String trading_part;
	@Column(name="BUSINESS_AREA") private String business_area;
	@Column(name="BUSINESS_PLACE") private String business_place;
	@Column(name="REGION") private String region;
	@Column(name="PROCESS_DATETIME") private String process_datetime;
	@Column(name="PRODUCT_CODE") private String product_code;
	@Column(name="LOCATION_NAME") private String location_name;
	@Column(name="BILL_GROUP") private String bill_group;
	@Column(name="POST_DATE") private String post_date;
	@Column(name="CCTR") private String cctr;
	@Column(name="PAY_TOTALAMOUNT") private BigDecimal pay_totalamount;
	@Column(name="REF") private String ref;
	@Column(name="TYPE") private String type;
	@Column(name="SUB_PRODUCT_CODE") private String sub_product_code;
	@Column(name="REVENUE_TYPE_CODE") private String revenue_type_code;
	@Column(name="CUSTOMER_GROUP") private String customer_group;
	@Column(name="CAT_SERVICE") private String cat_service;
	@Column(name="PERIOD") private String period;
	@Column(name="CHANNEL") private String channel;
	@Column(name="RECEIPT_NO") private String receipt_no;
	@Column(name="SERVICE_PRIORITY_PRODUCT") private String service_priority_product;
	@Column(name="REGION_DW") private String region_dw;
	@Column(name="INV_DATE") private String inv_date;
	@Column(name="DUE_DATE") private String due_date;
	@Column(name="PAY_TYPE") private String pay_type;
	@Column(name="DEFAULT_PROD") private String default_prod;
	@Column(name="USAGE_PERIOD") private String usage_period;
	@Column(name="REVERSE_DATE") private String reverse_date;
	@Column(name="COLLECTION_UNIT") private String collection_unit;
	@Column(name="COLLECTION_CODE") private String collection_code;
	
	public String getContrno() {
		return contrno;
	}
	public void setContrno(String contrno) {
		this.contrno = contrno;
	}
	public String getAr_ref() {
		return ar_ref;
	}
	public void setAr_ref(String ar_ref) {
		this.ar_ref = ar_ref;
	}
	public String getPay_location() {
		return pay_location;
	}
	public void setPay_location(String pay_location) {
		this.pay_location = pay_location;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public BigDecimal getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(BigDecimal pay_amount) {
		this.pay_amount = pay_amount;
	}
	public BigDecimal getPay_vat() {
		return pay_vat;
	}
	public void setPay_vat(BigDecimal pay_vat) {
		this.pay_vat = pay_vat;
	}
	public BigDecimal getPay_wt() {
		return pay_wt;
	}
	public void setPay_wt(BigDecimal pay_wt) {
		this.pay_wt = pay_wt;
	}
	public String getGl_account() {
		return gl_account;
	}
	public void setGl_account(String gl_account) {
		this.gl_account = gl_account;
	}
	public String getTrading_part() {
		return trading_part;
	}
	public void setTrading_part(String trading_part) {
		this.trading_part = trading_part;
	}
	public String getBusiness_area() {
		return business_area;
	}
	public void setBusiness_area(String business_area) {
		this.business_area = business_area;
	}
	public String getBusiness_place() {
		return business_place;
	}
	public void setBusiness_place(String business_place) {
		this.business_place = business_place;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProcess_datetime() {
		return process_datetime;
	}
	public void setProcess_datetime(String process_datetime) {
		this.process_datetime = process_datetime;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getBill_group() {
		return bill_group;
	}
	public void setBill_group(String bill_group) {
		this.bill_group = bill_group;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	public String getCctr() {
		return cctr;
	}
	public void setCctr(String cctr) {
		this.cctr = cctr;
	}
	public BigDecimal getPay_totalamount() {
		return pay_totalamount;
	}
	public void setPay_totalamount(BigDecimal pay_totalamount) {
		this.pay_totalamount = pay_totalamount;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getCustomer_group() {
		return customer_group;
	}
	public void setCustomer_group(String customer_group) {
		this.customer_group = customer_group;
	}
	public String getCat_service() {
		return cat_service;
	}
	public void setCat_service(String cat_service) {
		this.cat_service = cat_service;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getService_priority_product() {
		return service_priority_product;
	}
	public void setService_priority_product(String service_priority_product) {
		this.service_priority_product = service_priority_product;
	}
	public String getRegion_dw() {
		return region_dw;
	}
	public void setRegion_dw(String region_dw) {
		this.region_dw = region_dw;
	}
	public String getInv_date() {
		return inv_date;
	}
	public void setInv_date(String inv_date) {
		this.inv_date = inv_date;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getDefault_prod() {
		return default_prod;
	}
	public void setDefault_prod(String default_prod) {
		this.default_prod = default_prod;
	}
	public String getUsage_period() {
		return usage_period;
	}
	public void setUsage_period(String usage_period) {
		this.usage_period = usage_period;
	}
	public String getReverse_date() {
		return reverse_date;
	}
	public void setReverse_date(String reverse_date) {
		this.reverse_date = reverse_date;
	}
	public String getCollection_unit() {
		return collection_unit;
	}
	public void setCollection_unit(String collection_unit) {
		this.collection_unit = collection_unit;
	}
	public String getCollection_code() {
		return collection_code;
	}
	public void setCollection_code(String collection_code) {
		this.collection_code = collection_code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}

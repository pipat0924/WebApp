package th.net.cat.epis.service.otboss;

import static com.google.common.collect.Iterables.find;
import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.leftPad;
import static org.apache.commons.lang.StringUtils.stripToEmpty;
import static org.apache.commons.lang.StringUtils.trimToEmpty;
import static th.net.cat.epis.util.AppConstants.AFTERSALES_DISCOUNT_METHOD;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_1;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_2;
import static th.net.cat.epis.util.AppConstants.FLG_HEADER_3;
import static th.net.cat.epis.util.AppConstants.INVOICE_OTHER_PAYMENT;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BANKTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BILLEXCHANGE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CHEQUE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_COUPON;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CREDITCARD;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_FOREIGNTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_MONEYORDER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_OFFSET;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_OTHER;
import static th.net.cat.epis.util.AppConstants.RECEIPT_FORMAT_WH_ONLY;
import static th.net.cat.epis.util.AppConstants.RECEIPT_HEADER_EPO;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_RECEIVE_ONLY;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_WH_ONLY;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
import static th.net.cat.epis.util.AppConstants.RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
import static th.net.cat.epis.util.AppConstants.RECEIPT_TYPE_ABBREVIATION;
import static th.net.cat.epis.util.AppConstants.RECEIPT_TYPE_FULL;
import static th.net.cat.epis.util.AppConstants.TAX_CODE_NONVAT;
import static th.net.cat.epis.util.AppConstants.INVOICE_FROM_BILLING;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ctc.wstx.util.StringUtil;
import com.google.common.base.Predicate;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.dto.DWRevernueProductDTO;
import th.net.cat.epis.dto.InvoiceOTBoss;
import th.net.cat.epis.dto.InvoiceOTBossDTO;
import th.net.cat.epis.dto.OTTBossDTO;
import th.net.cat.epis.dto.SearchOTBOSSDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.dto.SubscriptionDTO;
import th.net.cat.epis.entity.Customer;
import th.net.cat.epis.entity.DWRevernueProduct;
import th.net.cat.epis.entity.Deduct;
import th.net.cat.epis.entity.Document;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.InvoiceVatDetail;
import th.net.cat.epis.entity.Method;
import th.net.cat.epis.entity.MethodBillExchange;
import th.net.cat.epis.entity.MethodCheque;
import th.net.cat.epis.entity.MethodCreditCard;
import th.net.cat.epis.entity.MethodMoneyOrder;
import th.net.cat.epis.entity.MethodMoneyTransfer;
import th.net.cat.epis.entity.MethodOther;
import th.net.cat.epis.entity.OTTBossEntity;
import th.net.cat.epis.entity.Payment;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.ReceiptEgpMappingEntity;
import th.net.cat.epis.entity.SearchOTBOSS;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.epis.repo.ChequeRepository;
import th.net.cat.epis.repo.CreditCardRepository;
import th.net.cat.epis.repo.CustomerRepository;
import th.net.cat.epis.repo.DeductionRepository;
import th.net.cat.epis.repo.DocumentRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.InvoiceVatDetailRepository;
import th.net.cat.epis.repo.MoneyTransferRepository;
import th.net.cat.epis.repo.OTTBossRepository;
import th.net.cat.epis.repo.PayMethodRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.ReceiptEgpMappingRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.ServiceRepository;
import th.net.cat.epis.repo.TransactionRepository;
import th.net.cat.epis.service.DWService;
import th.net.cat.epis.service.PaymentService.Paid;
import th.net.cat.epis.service.bouncecheqeue.BounceChequeService;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;

@org.springframework.stereotype.Service
public class OTBOssService {
	@Autowired
	OTTBossRepository otbossRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	DocumentRepository documentRepo;
	@Autowired
	InvoiceRepository invoiceRepo;
	@Autowired
	ServiceRepository serviceRepo;
	@Autowired
	InvoiceVatDetailRepository invoiceVatRepository;
	@Autowired
	PayMethodRepository methodRepo;
	@Autowired
	MoneyTransferRepository moneyTransferRepo;
	@Autowired
	CreditCardRepository creditCardRepo;
	@Autowired
	ChequeRepository chequeRepository;
	@Autowired
	DeductionRepository deductRepo;
	@Autowired
	PaymentRepository paymentRepo;
	@Autowired
	TransactionRepository transactionRepo;
	@Autowired
	ReceiptRepository receiptRepo;
	@Autowired
	DWService dwService;
	@Autowired
	BounceChequeService bounceChS;

	@Autowired
	ReceiptEgpMappingRepository egpMapRepo;

	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;

	OTTBossEntity profile = new OTTBossEntity();
	public static final BigDecimal VAT_RATE = new BigDecimal("7");

	public synchronized String getReceiptNo(String posNo, String receiptType, Date demandDate, String header) {
		String branchArea = EpContextHolder.getContext().getBranchArea(), currentDate = FastDateFormat
				.getInstance("yyMMdd", ENGLISH).format((demandDate != null) ? demandDate : new Date());
		synchronized (branchArea.intern()) {
			Document document = null;
			try {
				document = documentRepo.findByTypeAndBranchAreaAndDateAndHeader(receiptType, branchArea, currentDate,
						header);
			} catch (Exception e) {

			}
			if (document == null) {
				document = documentRepo.save(new Document());
				document.setHeader(header);
				document.setType(receiptType);
				document.setBranchArea(branchArea);
				document.setDate(currentDate);
				document.setCount(0);
			}
			document.setCount(document.getCount() + 1);
			// return new StringBuilder(header).append(leftPad(branchArea, 5,
			// "0")).append(leftPad(posNo, 2,
			// "0")).append(receiptType).append(currentDate).append(leftPad(document.getCount().toString(),
			// 4, "0")).toString();//by NSD 11-01-2016
			return new StringBuilder(header).append(leftPad(branchArea, 4, "0")).append(leftPad(posNo, 2, "0"))
					.append(receiptType).append(currentDate).append(leftPad(document.getCount().toString(), 4, "0"))
					.toString();// by NSD 21-03-2017
		}
	}

	@Transactional
	public InvoiceOTBossDTO findProductByInvoice(String account_no, String billRefNo, String billCycleSap) {

		InvoiceOTBossDTO dto = new InvoiceOTBossDTO();
		String tableName = "INV_SOURCE";
		StringBuilder subSql = new StringBuilder()
				.append(" SELECT ACCOUNT_NO account_no,AR_REF billRefNo,  PRODUCT_CODE productCode,PRODUCT_NAME productName ,  SUB_PRODUCT_CODE subProductCode ,SUB_PRODUCT_NAME subProductName ")
				.append(" , REVENUE_TYPE_CODE revTypeCode,REV_TYPE_NAME revTypeName , AMOUNT_AR amount FROM " + " "
						+ tableName + " ")
				.append(" WHERE ACCOUNT_NO=? and AR_REF=? and SAP_PERIOD = ? and RECORD_STATUS = 'A' ");
		List<InvoiceOTBoss> products = episJdbcTemplate.query(subSql.toString(),
				new Object[] { account_no, billRefNo, billCycleSap },
				BeanPropertyRowMapper.newInstance(InvoiceOTBoss.class));
		if (!CollectionUtils.isEmpty(products)) {
			dto.setData(products);
		} else {
			dto.getWarningList().add(new AlertMessage("10", "Can not find Product , Sub Product , Revenue Type "));
		}

		return dto;
	}

	public OTTBossDTO seachCusOTBoss(String accoutNo, String typeSource, String ar_ref) throws Exception {

		OTTBossDTO ottbossDto = new OTTBossDTO();
		String sql = "SELECT * FROM INV_SOURCE WHERE (ACCOUNT_NO = ? or AR_REF = ? ) and SOURCE = ? and RECORD_STATUS = 'A' ORDER BY AR_REF DESC ";
//		String sql = "SELECT * FROM SAP_DEBT WHERE ACCOUNT_NO = ?  and RECORD_STATUS = 'A' ORDER BY ID ";
		List<OTTBossEntity> ottList = new ArrayList<OTTBossEntity>();

		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, accoutNo);
				preparedStatement.setString(2, ar_ref);
				preparedStatement.setString(3, typeSource);
			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				OTTBossEntity profile = new OTTBossEntity();
				profile.setId(row.getInt("ID"));
				profile.setSource(row.getString("SOURCE"));
				profile.setAr_ref(row.getString("AR_REF"));
				profile.setBillgroup(row.getString("BILLGROUP"));
				profile.setAccount_code_old(row.getString("ACCOUNT_CODE_OLD"));
				profile.setAccount_no(row.getString("ACCOUNT_NO"));
				profile.setTax_id(row.getString("TAX_ID"));
				profile.setCustomer_name(row.getString("CUSTOMER_NAME"));
				profile.setCustomer_lastname(row.getString("CUSTOMER_LASTNAME"));
				profile.setCustomer_address(row.getString("CUSTOMER_ADDRESS"));
				profile.setCustomer_branch_code(row.getString("CUSTOMER_BRANCH_CODE"));
				profile.setE_gp(row.getString("E_GP"));
				profile.setCustomer_status(row.getString("CUSTOMER_STAUS"));
				profile.setRegion(row.getString("REGION"));
				profile.setRegion_name(row.getString("REGION_NAME"));
				profile.setNew_code(row.getString("NEW_CODE"));
				profile.setCus_category_code(row.getString("CUS_CATEGORY_CODE"));
				profile.setCus_category_name(row.getString("CUS_CATEGORY_NAME"));
				profile.setCustomer_type_code(row.getString("CUSTOMER_TYPE_CODE"));
				profile.setCustomer_type_name(row.getString("CUSTOMER_TYPE_NAME"));
				profile.setSegment_code(row.getString("SEGMENT_CODE"));
				profile.setProduct_code(row.getString("PRODUCT_CODE"));
				profile.setSub_product_code(row.getString("SUB_PRODUCT_CODE"));
				profile.setRevenue_type_code(row.getString("REVENUE_TYPE_CODE"));
				profile.setInvdate(row.getTimestamp("INVDATE"));
				profile.setDuedate(row.getTimestamp("DUEDATE"));
				profile.setPaydate(row.getTimestamp("PAYDATE"));
				profile.setInv_period(row.getString("INV_PERIOD"));
				profile.setSap_period(row.getString("SAP_PERIOD"));
				profile.setAmount_ar(row.getBigDecimal("AMOUNT_AR"));
				profile.setVat_amount_ar(row.getBigDecimal("VAT_AMOUNT_AR"));
				profile.setTotal_amount_ar(row.getBigDecimal("TOTAL_AMOUNT_AR"));
				profile.setCurrancy_ar(row.getString("CURRANCY_AR"));
				profile.setExch_rate_ar(row.getBigDecimal("EXCH_RATE_AR"));
				profile.setExch_rate_date_ar(row.getTimestamp("EXCH_RATE_DATE_AR"));
				profile.setVat_rate_ar(row.getString("VAT_RATE_AR"));
				profile.setWt_rate(row.getBigDecimal("WT_RATE"));
				profile.setDiscount(row.getBigDecimal("DISCOUNT"));
				profile.setTax_include(row.getString("TAX_INCLUDE"));
				profile.setTotal_amonut_paid(row.getBigDecimal("TOTAL_AMOUNT_PAID"));
				profile.setCurrency_paid(row.getString("CURRENCY_PAID"));
				profile.setExch_rate_paid(row.getBigDecimal("EXCH_RATE_PAID"));
				profile.setExch_rate_date_paid(row.getTimestamp("EXCH_RATE_DATE_PAID"));
				profile.setPay_advance(row.getBigDecimal("PAY_ADVANCE"));
				profile.setTrading_part(row.getString("TRADING_PART"));
				profile.setText(row.getString("TEXT"));
				profile.setCctr(row.getString("CCTR"));
				profile.setSubscr_no(row.getString("SUBSCR_NO"));
				profile.setLoad_date(row.getString("LOAD_DATE"));
				profile.setService_priority_product(row.getString("SERVICE_PRIORITY_PRODUCT"));
				profile.setRental_charge(row.getString("RENTAL_CHARGE"));
				profile.setUsesage_charge(row.getString("USESAGE_CHARGE"));
				profile.setProcess_date(row.getTimestamp("PROCESS_DATE"));
				profile.setServ_type_code(row.getString("SERV_TYPE_CODE"));
				profile.setServ_desc(row.getString("SERV_DESC"));
				profile.setStd_code(row.getString("STD_CODE"));
				profile.setService_code(row.getString("SERVICE_CODE"));
				profile.setService_name(row.getString("SERVICE_NAME"));
				profile.setRegion_sap(row.getString("REGION_SAP"));
				profile.setCctr_sap(row.getString("CCTR_SAP"));
				profile.setService_priority(row.getString("SERVICE_PRIORITY"));
				profile.setCategory(row.getString("CATEGORY"));
				profile.setPosting_date(row.getString("POSTING_DATE"));
				profile.setCalls(row.getString("CALLS"));
				profile.setRated_units(row.getString("RATED_UNITS"));
				profile.setPrimary_units(row.getString("PRIMARY_UNITS"));
				profile.setSecondary_units(row.getString("SECONDARY_UNITS"));
				profile.setThird_units(row.getString("THIRD_UNITS"));
				profile.setProperty_1(row.getString("PROPERTY1"));
				profile.setProperty_2(row.getString("PROPERTY2"));
				profile.setCarrier_code(row.getString("CARRIER_CODE"));
				profile.setCarrier_name(row.getString("CARRIER_NAME"));
				profile.setProduct_name(row.getString("PRODUCT_NAME"));
				profile.setSub_product_name(row.getString("SUB_PRODUCT_NAME"));
				profile.setRev_type_name(row.getString("REV_TYPE_NAME"));
				profile.setGl_account(row.getString("GL_ACCOUNT"));
				profile.setRemark(row.getString("REMARK"));
				profile.setCreate_date(row.getTimestamp("CREATE_DATE"));
				profile.setCreate_by(row.getString("CREATE_BY"));
				profile.setUpdate_date(row.getTimestamp("UPDATE_DATE"));
				profile.setUpdate_by(row.getString("UPDATE_BY"));
				profile.setRecord_status(row.getString("RECORD_STATUS"));
				profile.setBalancedue(row.getBigDecimal("BALANCE_DUE"));
				profile.setCus_group(row.getString("CUSTOMER_GROUP"));
				profile.setAmountBAR(row.getBigDecimal("AMOUNT_OTHER_AR"));
				profile.setMessage(row.getString("MESSAGE"));
				profile.setTaxCode(row.getString("TAX_CODE"));
				profile.setFiscalYear(row.getInt("FISCAL_YEAR"));
				profile.setDocNo(row.getString("DOC_NO"));
				ottList.add(profile);
			}

		});

		OTTBossEntity temp = null;
		List<OTTBossEntity> result = new ArrayList<OTTBossEntity>();
		for (int i = 0; i < ottList.size(); i++) {
			if (temp == null) {
				temp = ottList.get(i);
				continue;
			}
			if (temp.getAr_ref().equals(ottList.get(i).getAr_ref())) {
				if (temp.getSap_period().equals(ottList.get(i).getSap_period())) {

					OTTBossEntity newResult = new OTTBossEntity();
					newResult = ottList.get(i);
					newResult.setBalancedue(newResult.getBalancedue().add(temp.getBalancedue()));
					result.add(newResult);
					temp = null;
				} else {
					result.add(ottList.get(i));
					result.add(temp);
					temp = null;

				}

			} else {
				result.add(ottList.get(i));
				result.add(temp);
				temp = null;

			}
		}
		if (temp != null) {
			result.add(temp);
		}
		ottbossDto.setData(result);

		return ottbossDto;
	}

	public OTTBossDTO searchOtbossDetails(String inv, String source) throws Exception {

		OTTBossDTO ottbossDto = new OTTBossDTO();
		String sql = "SELECT * FROM INV_SOURCE WHERE ACCOUNT_NO = ?  and SOURCE = ? and ((RECORD_STATUS = 'R') or (RECORD_STATUS = 'CANCLE')) ORDER BY UPDATE_DATE DESC";
		List<OTTBossEntity> ottList = new ArrayList<OTTBossEntity>();

		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, inv);
				preparedStatement.setString(2, source);
			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				OTTBossEntity profile = new OTTBossEntity();
				profile.setId(row.getInt("ID"));
				profile.setSource(row.getString("SOURCE"));
				profile.setAr_ref(row.getString("AR_REF"));
				profile.setBillgroup(row.getString("BILLGROUP"));
				profile.setAccount_code_old(row.getString("ACCOUNT_CODE_OLD"));
				profile.setAccount_no(row.getString("ACCOUNT_NO"));
				profile.setTax_id(row.getString("TAX_ID"));
				profile.setCustomer_name(row.getString("CUSTOMER_NAME"));
				profile.setCustomer_lastname(row.getString("CUSTOMER_LASTNAME"));
				profile.setCustomer_address(row.getString("CUSTOMER_ADDRESS"));
				profile.setCustomer_branch_code(row.getString("CUSTOMER_BRANCH_CODE"));
				profile.setE_gp(row.getString("E_GP"));
				profile.setCustomer_status(row.getString("CUSTOMER_STAUS"));
				profile.setRegion(row.getString("REGION"));
				profile.setRegion_name(row.getString("REGION_NAME"));
				profile.setNew_code(row.getString("NEW_CODE"));
				profile.setCus_category_code(row.getString("CUS_CATEGORY_CODE"));
				profile.setCus_category_name(row.getString("CUS_CATEGORY_NAME"));
				profile.setCustomer_type_code(row.getString("CUSTOMER_TYPE_CODE"));
				profile.setCustomer_type_name(row.getString("CUSTOMER_TYPE_NAME"));
				profile.setSegment_code(row.getString("SEGMENT_CODE"));
				profile.setProduct_code(row.getString("PRODUCT_CODE"));
				profile.setSub_product_code(row.getString("SUB_PRODUCT_CODE"));
				profile.setRevenue_type_code(row.getString("REVENUE_TYPE_CODE"));
				profile.setInvdate(row.getTimestamp("INVDATE"));
				profile.setDuedate(row.getTimestamp("DUEDATE"));
				profile.setPaydate(row.getTimestamp("PAYDATE"));
				profile.setInv_period(row.getString("INV_PERIOD"));
				profile.setSap_period(row.getString("SAP_PERIOD"));
				profile.setAmount_ar(row.getBigDecimal("AMOUNT_AR"));
				profile.setVat_amount_ar(row.getBigDecimal("VAT_AMOUNT_AR"));
				profile.setTotal_amount_ar(row.getBigDecimal("TOTAL_AMOUNT_AR"));
				profile.setCurrancy_ar(row.getString("CURRANCY_AR"));
				profile.setExch_rate_ar(row.getBigDecimal("EXCH_RATE_AR"));
				profile.setExch_rate_date_ar(row.getTimestamp("EXCH_RATE_DATE_AR"));
				profile.setVat_rate_ar(row.getString("VAT_RATE_AR"));
				profile.setWt_rate(row.getBigDecimal("WT_RATE"));
				profile.setDiscount(row.getBigDecimal("DISCOUNT"));
				profile.setTax_include(row.getString("TAX_INCLUDE"));
				profile.setTotal_amonut_paid(row.getBigDecimal("TOTAL_AMOUNT_PAID"));
				profile.setCurrency_paid(row.getString("CURRENCY_PAID"));
				profile.setExch_rate_paid(row.getBigDecimal("EXCH_RATE_PAID"));
				profile.setExch_rate_date_paid(row.getTimestamp("EXCH_RATE_DATE_PAID"));
				profile.setPay_advance(row.getBigDecimal("PAY_ADVANCE"));
				profile.setTrading_part(row.getString("TRADING_PART"));
				profile.setText(row.getString("TEXT"));
				profile.setCctr(row.getString("CCTR"));
				profile.setSubscr_no(row.getString("SUBSCR_NO"));
				profile.setLoad_date(row.getString("LOAD_DATE"));
				profile.setService_priority_product(row.getString("SERVICE_PRIORITY_PRODUCT"));
				profile.setRental_charge(row.getString("RENTAL_CHARGE"));
				profile.setUsesage_charge(row.getString("USESAGE_CHARGE"));
				profile.setProcess_date(row.getTimestamp("PROCESS_DATE"));
				profile.setServ_type_code(row.getString("SERV_TYPE_CODE"));
				profile.setServ_desc(row.getString("SERV_DESC"));
				profile.setStd_code(row.getString("STD_CODE"));
				profile.setService_code(row.getString("SERVICE_CODE"));
				profile.setService_name(row.getString("SERVICE_NAME"));
				profile.setRegion_sap(row.getString("REGION_SAP"));
				profile.setCctr_sap(row.getString("CCTR_SAP"));
				profile.setService_priority(row.getString("SERVICE_PRIORITY"));
				profile.setCategory(row.getString("CATEGORY"));
				profile.setPosting_date(row.getString("POSTING_DATE"));
				profile.setCalls(row.getString("CALLS"));
				profile.setRated_units(row.getString("RATED_UNITS"));
				profile.setPrimary_units(row.getString("PRIMARY_UNITS"));
				profile.setSecondary_units(row.getString("SECONDARY_UNITS"));
				profile.setThird_units(row.getString("THIRD_UNITS"));
				profile.setProperty_1(row.getString("PROPERTY1"));
				profile.setProperty_2(row.getString("PROPERTY2"));
				profile.setCarrier_code(row.getString("CARRIER_CODE"));
				profile.setCarrier_name(row.getString("CARRIER_NAME"));
				profile.setProduct_name(row.getString("PRODUCT_NAME"));
				profile.setSub_product_name(row.getString("SUB_PRODUCT_NAME"));
				profile.setRev_type_name(row.getString("REV_TYPE_NAME"));
				profile.setGl_account(row.getString("GL_ACCOUNT"));
				profile.setRemark(row.getString("REMARK"));
				profile.setCreate_date(row.getTimestamp("CREATE_DATE"));
				profile.setCreate_by(row.getString("CREATE_BY"));
				profile.setUpdate_date(row.getTimestamp("UPDATE_DATE"));
				profile.setUpdate_by(row.getString("UPDATE_BY"));
				profile.setRecord_status(row.getString("RECORD_STATUS"));
				profile.setBalancedue(row.getBigDecimal("BALANCE_DUE"));
				ottList.add(profile);
			}

		});
		OTTBossEntity temp = null;
		List<OTTBossEntity> result = new ArrayList<OTTBossEntity>();
		for (int i = 0; i < ottList.size(); i++) {
			if (temp == null) {
				temp = ottList.get(i);
				continue;
			}
			if (temp.getAr_ref().equals(ottList.get(i).getAr_ref())) {
				if (temp.getSap_period().equals(ottList.get(i).getSap_period())) {
					OTTBossEntity newResult = new OTTBossEntity();
					newResult = ottList.get(i);
						newResult.setTotal_amonut_paid(
								newResult.getTotal_amonut_paid().add(temp.getTotal_amonut_paid()));
						result.add(newResult);
						temp = null;
					}
				

			} else {
				result.add(ottList.get(i));
				result.add(temp);
				temp = null;

			}

		}
		if (temp != null) {
			result.add(temp);
		}
		ottbossDto.setData(result);

		return ottbossDto;
	}

	public SearchOTBOSSDTO seachCusOTBossByInvoice(String accoutNo, String typeSource, String period, String invoice,
			Long paymentID) throws Exception {

		SearchOTBOSSDTO ottbossDto = new SearchOTBOSSDTO();
		String sql = "SELECT TMP.INVOICEID,INV.* FROM INV_SOURCE INV inner join TMPINVOICE  TMP on TMP.INVOICENO = INV.AR_REF WHERE INV.ACCOUNT_NO = ? and INV.SOURCE = ? and INV.AR_REF = ? and INV.SAP_PERIOD= ? and TMP.paymentID = ? and INV.RECORD_STATUS = 'A' ORDER BY INV.SERVICE_PRIORITY_PRODUCT   ";
		List<SearchOTBOSS> ottList = new ArrayList<SearchOTBOSS>();

		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, accoutNo);
				preparedStatement.setString(2, typeSource);
				preparedStatement.setString(3, invoice);
				preparedStatement.setString(4, period);
				preparedStatement.setLong(5, paymentID);

			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				SearchOTBOSS profile = new SearchOTBOSS();

				profile.setId(row.getInt("ID"));
				profile.setSource(row.getString("SOURCE"));
				profile.setAr_ref(row.getString("AR_REF"));
				profile.setBillgroup(row.getString("BILLGROUP"));
				profile.setAccount_code_old(row.getString("ACCOUNT_CODE_OLD"));
				profile.setAccount_no(row.getString("ACCOUNT_NO"));
				profile.setTax_id(row.getString("TAX_ID"));
				profile.setCustomer_name(row.getString("CUSTOMER_NAME"));
				profile.setCustomer_lastname(row.getString("CUSTOMER_LASTNAME"));
				profile.setCustomer_address(row.getString("CUSTOMER_ADDRESS"));
				profile.setCustomer_branch_code(row.getString("CUSTOMER_BRANCH_CODE"));
				profile.setE_gp(row.getString("E_GP"));
				profile.setCustomer_status(row.getString("CUSTOMER_STAUS"));
				profile.setRegion(row.getString("REGION"));
				profile.setRegion_name(row.getString("REGION_NAME"));
				profile.setNew_code(row.getString("NEW_CODE"));
				profile.setCus_group(row.getString("CUSTOMER_GROUP"));
				profile.setCus_category_code(row.getString("CUS_CATEGORY_CODE"));
				profile.setCus_category_name(row.getString("CUS_CATEGORY_NAME"));
				profile.setCustomer_type_code(row.getString("CUSTOMER_TYPE_CODE"));
				profile.setCustomer_type_name(row.getString("CUSTOMER_TYPE_NAME"));
				profile.setSegment_code(row.getString("SEGMENT_CODE"));
				profile.setProduct_code(row.getString("PRODUCT_CODE"));
				profile.setSub_product_code(row.getString("SUB_PRODUCT_CODE"));
				profile.setRevenue_type_code(row.getString("REVENUE_TYPE_CODE"));
				profile.setInvdate(row.getTimestamp("INVDATE"));
				profile.setDuedate(row.getTimestamp("DUEDATE"));
				profile.setPaydate(row.getTimestamp("PAYDATE"));
				profile.setInv_period(row.getString("INV_PERIOD"));
				profile.setSap_period(row.getString("SAP_PERIOD"));
				profile.setAmount_ar(row.getBigDecimal("AMOUNT_AR"));
				profile.setVat_amount_ar(row.getBigDecimal("VAT_AMOUNT_AR"));
				profile.setTotal_amount_ar(row.getBigDecimal("TOTAL_AMOUNT_AR"));
				profile.setCurrancy_ar(row.getString("CURRANCY_AR"));
				profile.setExch_rate_ar(row.getBigDecimal("EXCH_RATE_AR"));
				profile.setExch_rate_date_ar(row.getTimestamp("EXCH_RATE_DATE_AR"));
				profile.setVat_rate_ar(row.getString("VAT_RATE_AR"));
				profile.setWt_rate(row.getBigDecimal("WT_RATE"));
				profile.setDiscount(row.getBigDecimal("DISCOUNT"));
				profile.setTax_include(row.getString("TAX_INCLUDE"));
				profile.setTotal_amonut_paid(row.getBigDecimal("TOTAL_AMOUNT_PAID"));
				profile.setCurrency_paid(row.getString("CURRENCY_PAID"));
				profile.setExch_rate_paid(row.getBigDecimal("EXCH_RATE_PAID"));
				profile.setExch_rate_date_paid(row.getTimestamp("EXCH_RATE_DATE_PAID"));
				profile.setPay_advance(row.getBigDecimal("PAY_ADVANCE"));
				profile.setTrading_part(row.getString("TRADING_PART"));
				profile.setText(row.getString("TEXT"));
				profile.setCctr(row.getString("CCTR"));
				profile.setSubscr_no(row.getString("SUBSCR_NO"));
				profile.setLoad_date(row.getString("LOAD_DATE"));
				profile.setService_priority_product(row.getString("SERVICE_PRIORITY_PRODUCT"));
				profile.setRental_charge(row.getString("RENTAL_CHARGE"));
				profile.setUsesage_charge(row.getString("USESAGE_CHARGE"));
				profile.setProcess_date(row.getTimestamp("PROCESS_DATE"));
				profile.setServ_type_code(row.getString("SERV_TYPE_CODE"));
				profile.setServ_desc(row.getString("SERV_DESC"));
				profile.setStd_code(row.getString("STD_CODE"));
				profile.setService_code(row.getString("SERVICE_CODE"));
				profile.setService_name(row.getString("SERVICE_NAME"));
				profile.setRegion_sap(row.getString("REGION_SAP"));
				profile.setCctr_sap(row.getString("CCTR_SAP"));
				profile.setService_priority(row.getString("SERVICE_PRIORITY"));
				profile.setCategory(row.getString("CATEGORY"));
				profile.setPosting_date(row.getString("POSTING_DATE"));
				profile.setCalls(row.getString("CALLS"));
				profile.setRated_units(row.getString("RATED_UNITS"));
				profile.setPrimary_units(row.getString("PRIMARY_UNITS"));
				profile.setSecondary_units(row.getString("SECONDARY_UNITS"));
				profile.setThird_units(row.getString("THIRD_UNITS"));
				profile.setProperty_1(row.getString("PROPERTY1"));
				profile.setProperty_2(row.getString("PROPERTY2"));
				profile.setCarrier_code(row.getString("CARRIER_CODE"));
				profile.setCarrier_name(row.getString("CARRIER_NAME"));
				profile.setProduct_name(row.getString("PRODUCT_NAME"));
				profile.setSub_product_name(row.getString("SUB_PRODUCT_NAME"));
				profile.setRev_type_name(row.getString("REV_TYPE_NAME"));
				profile.setGl_account(row.getString("GL_ACCOUNT"));
				profile.setRemark(row.getString("REMARK"));
				profile.setCreate_date(row.getTimestamp("CREATE_DATE"));
				profile.setCreate_by(row.getString("CREATE_BY"));
				profile.setUpdate_date(row.getTimestamp("UPDATE_DATE"));
				profile.setUpdate_by(row.getString("UPDATE_BY"));
				profile.setRecord_status(row.getString("RECORD_STATUS"));
				profile.setBalancedue(row.getBigDecimal("BALANCE_DUE"));
				profile.setInvoiceId(row.getLong("INVOICEID"));
				
				profile.setFiscalYear(row.getInt("FISCAL_YEAR"));
				profile.setMessage(row.getString("MESSAGE"));
				profile.setTaxCode(row.getString("TAX_CODE"));
				profile.setReconcile(row.getString("RECONCILE"));
				profile.setCompanyCode(row.getString("COMPANY_CODE"));
				profile.setAmountBathAr(row.getBigDecimal("AMOUNT_OTHER_AR"));
				
				ottList.add(profile);
			}
		});
		ottbossDto.setData(ottList);

		return ottbossDto;
	}

	public List<DWRevernueProduct> seachPayment(Long payment) throws Exception {

		DWRevernueProductDTO ottbossDto = new DWRevernueProductDTO();
		String sql = "SELECT * FROM DW_REVERNUE_PRODUCT WHERE PAYMENT_ID = ? and  RECORD_STATUS= 'A' ORDER BY INVOICEID ASC  ";
		List<DWRevernueProduct> ottList = new ArrayList<DWRevernueProduct>();

		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setLong(1, payment);

			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				DWRevernueProduct profile = new DWRevernueProduct();
				;
				profile.setId(row.getLong("ID"));
				profile.setPaymentId(row.getLong("PAYMENT_ID"));
				profile.setInvoiceId(row.getLong("INVOICEID"));
				profile.setSourceId(row.getLong("SOURCE_ID"));
				profile.setSourceTable(row.getString("SOURCE_TABLE"));
				profile.setProductCode(row.getString("PRODUCT_CODE"));
				profile.setSubProductCode(row.getString("SUB_PRODUCT_CODE"));
				profile.setAmount(row.getBigDecimal("AMOUNT"));
				profile.setDiscount(row.getBigDecimal("DISCOUNT"));
				profile.setVat(row.getBigDecimal("VAT"));
				profile.setTotalAmount(row.getBigDecimal("TOTAL_AMOUNT"));
				profile.setDeduction(row.getBigDecimal("DEDUCTION"));
				profile.setRemark(row.getString("REMARK"));
				profile.setCreateBy(row.getString("CREATE_BY"));
				profile.setCreateDate(row.getTimestamp("CREATE_DATE"));
				profile.setUpdateBy(row.getString("UPDATE_BY"));
				profile.setUpdateDate(row.getTimestamp("UPDATE_DATE"));
				profile.setRecordStatus(row.getString("RECORD_STATUS"));

				ottList.add(profile);
			}
		});

		return ottList;
	}

	@Transactional
	public List<Receipt> createPaymentOTBOSS(SettlePaymentDTO paymentDTO) throws Exception {

		String userLogin = EpContextHolder.getContext().getUserName();
		String branchCode = EpContextHolder.getContext().getBranchCode(),
				branchArea = EpContextHolder.getContext().getBranchArea(),
				branchName = EpContextHolder.getContext().getDescAbvrth();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
				posNo = EpContextHolder.getContext().getPosNo();
		Long officerId = EpContextHolder.getContext().getOfficerId();
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		Payment payment = paymentRepo.save(new Payment());
		payment.setUpdateDttm(date);
		payment.setUpdateUser(userName);
		Deduct deduct;
		Customer customer = null;
		Invoice invoice;
		InvoiceVatDetail invoiceVatDetail;
		Receipt receipt = null;
		List<Customer> customers = new ArrayList<Customer>();
		List<Customer> episCustomers = null;
		List<Receipt> receipts = new ArrayList<Receipt>();
		List<Paid> paids = new ArrayList<Paid>();
		Date receiptDttm = new Date();
		BigDecimal amount = BigDecimal.ZERO, discount = BigDecimal.ZERO, charge = BigDecimal.ZERO,
				vat = BigDecimal.ZERO, totalCharge = BigDecimal.ZERO, balanceDue = BigDecimal.ZERO,
				afterSaleDiscount = BigDecimal.ZERO, deduction = BigDecimal.ZERO;

		ReceiptEgpMappingEntity egpMap = null;
		List<ReceiptEgpMappingEntity> egpMaps = new ArrayList<ReceiptEgpMappingEntity>();
		String language = paymentDTO.getLanguage();

		for (

		SettlePaymentDTO.DeductTax ded : paymentDTO.getDeducts()) {
			for (SettlePaymentDTO.Customer cus : paymentDTO.getCustomers()) {
				for (SettlePaymentDTO.Invoice inv : cus.getInvoices()) {
					paids.add(new Paid(ded.getAmount(), ded.getType()));
					deduct = deductRepo.save(new Deduct());
					deduct.setUpdateDttm(date);
					deduct.setUpdateUser(userName);
					deduct.setNo(inv.getWithholdingTaxNo());
					deduct.setType(ded.getType());
					deduct.setAmount(ded.getAmount());
					deduct.setDate(date);
					deduct.setPaymentId(payment.getId());
					deduct.setInvoiceNo(ded.getInvoiceNo());
					payment.getDeducts().add(deduct);
				}
			}
		}
		for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {

			if (met != null && met.getType() != null) {
				Paid paid = new Paid(met.getAmount(), met.getType());
				paids.add(paid); // Preparing: To substract into invoice.
				Method method = methodRepo.save(new Method());
				method.setUpdateDttm(date);
				method.setUpdateUser(userName);
				method.setCode(met.getCode());
				method.setName(met.getName());
				method.setChequeNo(met.getChequeNo());
				method.setAccountNo(met.getBankAcct());
				for (SettlePaymentDTO.Invoice inv : paymentDTO.getCustomers().get(0).getInvoices()) {
					BigDecimal total = met.getAmount().subtract(inv.getChange());
					method.setAmount(total);
				}
				method.setPaymentId(payment.getId());

				if (PAY_METHOD_BANKTRANSFER.equals(met.getType())) { // Money
																		// Transfer
					MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
					moneyTransfer.setUpdateDttm(date);
					moneyTransfer.setUpdateUser(userName);
					moneyTransfer.setDate(met.getTransferDt());
					paid.setMoneyTransfer(moneyTransfer);
					paid.setIsBackDate(met.isBackDt());
				} else if (PAY_METHOD_CHEQUE.equals(met.getType())) {
					// TODO: complete all the saving methods and pulling them to
					// print correctly
					MethodCheque chequePay = chequeRepository.save(new MethodCheque());
					chequePay.setAmount(met.getAmount());
					chequePay.setBankCode(met.getPayChqBankCode());
					chequePay.setBankName(met.getPayChqBankName());
					chequePay.setBankBrnh(met.getPayChqBranch());
					chequePay.setChequeDate(DateUtil.convertToDate(met.getChequeDt()));
					chequePay.setNo(met.getChequeNo());
					chequePay.setUpdateUser(userName);
					chequePay.setUpdateDttm(date);
					chequePay.setPaymentId(payment.getId());
				} else if (PAY_METHOD_CREDITCARD.equals(met.getType())) {
					// Fix by EPIS8 30/12/2016 issue no 166
					MethodCreditCard creditCardPay = creditCardRepo.save(new MethodCreditCard());
					creditCardPay.setPaymentId(payment.getId());
					creditCardPay.setNo(met.getCardNo());
					creditCardPay.setAmount(met.getAmount());
					creditCardPay.setBankIssuer(met.getBankName());
					creditCardPay.setType(met.getCardType());
					creditCardPay.setUpdateDttm(date);
					creditCardPay.setUpdateUser(userName);
					// End Fix by EPIS8 30/12/2016 issue no 166
				} else if (PAY_METHOD_MONEYORDER.equals(met.getType())) {
					MethodMoneyOrder moneuOrder = new MethodMoneyOrder();
				} else if (PAY_METHOD_BILLEXCHANGE.equals(met.getType())) {
					MethodBillExchange billExchange = new MethodBillExchange();
				} else if (PAY_METHOD_COUPON.equals(met.getType())) {

				} else if (PAY_METHOD_OFFSET.equals(met.getType())) {
					method.setOffsetDocumentNo(met.getOffsetDocumentNo());
					method.setOffsetAccountCode(met.getOffsetAccountCode());
					method.setOffsetAccountName(met.getOffsetAccountName());
				} else if (PAY_METHOD_OTHER.equals(met.getType())) {
					MethodOther otherPay = new MethodOther();
				} else if (PAY_METHOD_FOREIGNTRANSFER.equals(met.getType())) { // Money
																				// Transfer
					MethodMoneyTransfer moneyTransfer = moneyTransferRepo.save(new MethodMoneyTransfer());
					moneyTransfer.setUpdateDttm(date);
					moneyTransfer.setUpdateUser(userName);
					moneyTransfer.setDate(met.getTransferDt());
					paid.setMoneyTransfer(moneyTransfer);
					paid.setIsBackDate(met.isBackDt());
				}
				payment.getMethods().add(method);
			}
		}
		
		for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
			episCustomers = null;
			episCustomers = customerRepo.findByNo(cust.getCustNo());
			if (CollectionUtils.isEmpty(episCustomers)) {
				customer = customerRepo.save(new Customer());
			} else {
				customer = episCustomers.get(0);
				customerRepo.save(customer);
			}
			customer.setUpdateDttm(date);
			customer.setUpdateUser(userName);
			customers.add(customer);
			customer.setPayment(payment);
			customer.setType(cust.getCustType());
			customer.setNo(cust.getCustNo());
			customer.setName(cust.getCustName());
			customer.setTax(cust.getTaxNo());
			customer.setBillGroup(cust.getBillGroup());
			customer.setCollectionUnit(cust.getCollectionUnit());
			customer.setOutstanding(cust.getOutstanding());
			customer.setRemark(cust.getRemark());
			customer.setReceiptAddr(cust.getAddress1());
			customer.setInvoiceAddr(cust.getAddress2());
			customer.setAcctCatLkp(cust.getAcctCatLkp());// by NSD 02-03-2017
			customer.setCatCustomerSegment(cust.getCatCustomerSegment());

			// Additional Conditions for GFMIS, date and branch
			String customerType = stripToEmpty(customer.getType()).toLowerCase();
			// if (customerType.indexOf("organization") == 0 ||
			// customerType.indexOf("stateagency") == 0) {
			customer.setBranch(cust.getCustBranch());
			// }
			for (Paid paid : paids) {
				if (paid.getMoneyTransfer() != null & paid.isBackDate()) {
					receiptDttm = paid.getMoneyTransfer().getDate();
					break;
				}
			}
			boolean split = cust.getSplit();
			if (!split) {
				if (cust.getInvoices().size() < 1)
					continue;
				receipt = receiptRepo.save(new Receipt());
				receipt.setUpdateDttm(date);
				receipt.setUpdateUser(userName);
				receipt.setDocDttm(receiptDttm);
				receipt.setFlgHeader(FLG_HEADER_2);
				receipt.setYear(cust.getInvoices().get(0).getYear());
				receipt.setDocNo(cust.getInvoices().get(0).getDocNo());
				receipt.setMessage(cust.getInvoices().get(0).getMessage());
				receipt.setExchangeRate(cust.getExchangeRate());
				Map<String, String> accountSubNoMap = new HashMap<String, String>();
				for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
					invoice = invoiceRepo.save(new Invoice());
					invoice.setUpdateDttm(date);
					invoice.setUpdateUser(userName);
					receipt.getInvoices().add(invoice);
					Service service = serviceRepo.save(new Service());
					service.setUpdateDttm(date);
					service.setReceiptId(receipt.getId());
					service.setInvoiceId(invoice.getId());
					service.setAccountNo("1234567890");
					service.setProductCode("102020003");
					service.setProductName("prod name");
					service.setProductSubCode("sub code");
					service.setProductSubName("sub name");
					service.setIncomeType("1");
					service.setServiceName(inv.getServiceName());//mainxx
					service.setAmount(inv.getReceived());
					service.setAfterSaleDiscount(inv.getAfterSaleDiscount() != null
							? inv.getAfterSaleDiscount().add(
									inv.getAfterSaleDiscVat() != null ? inv.getAfterSaleDiscVat() : BigDecimal.ZERO)
							: BigDecimal.ZERO);
					receipt.getServices().add(service);
					invoice.getServices().add(service);
					addTransactionsIntoService(paids, payment, invoice, date, userName);
					invoice.setReceiptId(receipt.getId());
					invoice.setCustomer(customer);
					invoice.setPayment(payment);
					invoice.setNo(inv.getNo());
					invoice.setKenan(inv.getKenan());
					invoice.setCurrencyCode(inv.getCurrencyCode());
					invoice.setIssueDt(inv.getIssueDt());
					invoice.setDueDt(inv.getDueDt());
					invoice.setBillCycle(inv.getBillCycle()); // 
					invoice.setAmount(inv.getAmount());
					invoice.setDiscount(inv.getDiscount());
					invoice.setCharge(inv.getCharge());
					invoice.setSource(cust.getSouceType());
					invoice.setRateAr(inv.getForeignExchangeRate());
					if (!StringUtils.equals(inv.getStatus(), INVOICE_OTHER_PAYMENT)
							|| inv.getVat().compareTo(new BigDecimal(0)) == 1) {
						invoice.setVat(inv.getVat());
						invoice.setVatRate(inv.getVatRate());
						if (!StringUtils.equals(inv.getTaxTypeCode(), TAX_CODE_NONVAT)
								|| inv.getVat().compareTo(new BigDecimal(0)) == 1) {
							receipt.setFlgHeader(FLG_HEADER_1);
						}
					} else {
						invoice.setVat(null);
						invoice.setVatRate(null);
					}
					invoice.setTotalCharge(inv.getReceived());
					invoice.setDeduction(inv.getDeduction());
					invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
					invoice.setBalanceDue(inv.getBalanceDue());
					invoice.setReceived(inv.getReceived());
					invoice.setAfterSaleDiscVat(inv.getAfterSaleDiscVat());// by
																			// NSD
																			// 05-04-2017
					invoice.setChange(inv.getChange());
					invoice.setAdvanced(inv.getAdvanced());
					invoice.setDebtAmount(inv.getTotalCharge());
					invoice.setStatus(INVOICE_FROM_BILLING);
					invoice.setDiscountType(inv.getDiscountType());// by NSD
																	// 04-04
					invoice.setDiscApprUser(inv.getDiscApprUser());
					invoice.setSource(cust.getSouceType());
					String subNoInv = "";
					if (!CollectionUtils.isEmpty(inv.getSubNoList())) {
						for (String subno : inv.getSubNoList()) {
							accountSubNoMap.put(subno, subno);
							subNoInv += subno + "|";
						}
					}
					invoice.setSubNo(subNoInv);

					// invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO)
					// == 0 ?
					// (inv.getTotalCharge().compareTo(invoice.getReceived()) !=
					// 0 ? "P" : "F") : "P");//Commented by EPIS4
					invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0 ? "F" : "P");

					System.out.println("# PART 1 receipt.getId() = " + receipt.getId());
					System.out.println("# PART 1 invoice.getReceiptId() = " + invoice.getReceiptId());
					String paymentCase = "";
					for (SettlePaymentDTO.Invoice inv2 : cust.getInvoices()) {
						if (receipt.getId() == invoice.getReceiptId()) {
							if (inv2.getPaymentCase() != null && inv2.getPaymentCase().indexOf("+") > 0) {
								String paymentCaseArr[] = inv2.getPaymentCase().split("\\+");
								for (int i = 0; i < paymentCaseArr.length; i++) {
									if (paymentCaseArr[paymentCaseArr.length - 1].length() <= 1) { // Remove
																									// "+"
																									// last
																									// index
										paymentCase = inv2.getPaymentCase().substring(0,
												inv2.getPaymentCase().length() - 3);
									} else {
										paymentCase = inv2.getPaymentCase();
									}
								}
								receipt.setPaymentCase(paymentCase);
							} else {
								receipt.setPaymentCase(inv2.getPaymentCase());
							}
						}
					}
					/*
					 * if(!CollectionUtils.isEmpty(inv.getSubNoList())) {
					 * for(String subno:inv.getSubNoList()){
					 * accountSubNoMap.put(subno,subno); } }
					 */ // by NSD 23-03-2017
					if (!CollectionUtils.isEmpty(inv.getInvoiceVatDetails())) {
						for (SettlePaymentDTO.InvoiceVatDetail invDtl : inv.getInvoiceVatDetails()) {
							invoiceVatDetail = invoiceVatRepository.save(new InvoiceVatDetail());
							invoiceVatDetail.setUpdateDttm(date);
							invoiceVatDetail.setUpdateUser(userName);
							invoice.getInvoiceVatDetails().add(invoiceVatDetail);
							invoiceVatDetail.setInvoiceId(invoice.getId());
							invoiceVatDetail.setInvoiceNo(invDtl.getInvoiceNo());
							invoiceVatDetail.setAccountNo(invDtl.getAccountNo());
							invoiceVatDetail.setAmount(invDtl.getAmount());
							invoiceVatDetail.setVat(invDtl.getVat());
							invoiceVatDetail.setTaxTypeCode(invDtl.getTaxTypeCode());

						}
					}
				}
				BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
						rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
						rcpBalanceDue = BigDecimal.ZERO, rcpAfterSaleDiscount = BigDecimal.ZERO,
						rcpWt = BigDecimal.ZERO, rcpReceived = BigDecimal.ZERO, rcpChange = BigDecimal.ZERO,
						rcpAfterSaleDiscVat = BigDecimal.ZERO;
				for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
					rcpAmount = rcpAmount.add(inv.getAmount());
					rcpDiscount = rcpDiscount.add(inv.getDiscount());
					rcpCharge = rcpCharge.add(inv.getCharge());
					rcpVat = rcpVat.add(inv.getVat());
					rcpDeduction = rcpDeduction.add(inv.getDeduction());
					rcpAfterSaleDiscount = rcpAfterSaleDiscount.add(inv.getAfterSaleDiscount());
					rcpBalanceDue = rcpBalanceDue.add(inv.getBalanceDue());
				}
				for (Invoice inv : receipt.getInvoices()) {
					rcpTotalCharge = rcpTotalCharge.add(inv.getTotalCharge());
					rcpReceived = rcpReceived.add(inv.getReceived());
					if (null != inv.getAfterSaleDiscVat()) {// by NSD 05-04-2017
						rcpAfterSaleDiscVat = rcpAfterSaleDiscVat.add(inv.getAfterSaleDiscVat());
					}
				}
				rcpChange = rcpReceived.subtract(rcpTotalCharge);
				receipt.setCustomer(customer);
				receipt.setPayment(payment);
				receipt.setType(getReceiptType2(customer));
				String receiptType = "";
				if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_FORMAT_WH_ONLY)) {// by
																							// NSD
																							// 24-04-2017
					receipt.setFlgHeader(FLG_HEADER_3);
				}
				if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1)) {
					if (RECEIPT_TYPE_FULL.equals(receipt.getType())) {
						receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
					} else {
						receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
					}
				} else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)) {
					receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY;
				} else {
					receiptType = RECEIPT_NO_FLAG_WH_ONLY;
				}
				if (cust.getEpNameCode() == null) {
					receipt.setNo(getReceiptNo(posNo, receiptType, receiptDttm, RECEIPT_HEADER_EPO));

				} else {
					receipt.setNo(cust.getEpNameCode());
				}

				receipt.setName(cust.getCustNameEdit());
				receipt.setAccountName(cust.getCustName());
				receipt.setAccountNo(cust.getCustNo());

				// by NSD 24-04-2017
				Date dtFrom = receiptDttm;
				Calendar c = Calendar.getInstance();
				c.setTime(dtFrom);
				c.add(Calendar.DATE, 1);
				Date dtTo = c.getTime();
				// List<Receipt> rcptList =
				// receiptRepo.findByTypeAndBranchAreaAndFlgHeaderAndDocDttmOrderByNoDesc(receipt.getType(),branchArea,
				// receipt.getFlgHeader(), dtFrom, dtTo);
				List<Receipt> rcptList = receiptRepo.findBackDateReceiptList(receipt.getType(), branchArea,
						receipt.getFlgHeader(), new java.sql.Date(dtFrom.getTime()), new java.sql.Date(dtTo.getTime()));
				BeanComparator fieldComparator = new BeanComparator("no");
				Collections.sort(rcptList, fieldComparator);

				if (cust.getPaymentDate() == null) {

					if (rcptList != null && rcptList.size() > 0) {
						receiptDttm = rcptList.get(rcptList.size() - 1).getDocDttm();
						receipt.setDocDttm(receiptDttm);
					}
				} else {
					receipt.setDocDttm(cust.getPaymentDate());
				}
				String subNo = "";

				if (accountSubNoMap.size() > 1) {
					subNo = String.valueOf(accountSubNoMap.size());
				} else if (accountSubNoMap.size() == 1) {
					Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
					subNo = entry.getValue();
				} else {
					subNo = cust.getCustSubNo();
				}
				// receipt.setAccountSubNo(cust.getCustSubNo());
				receipt.setAccountSubNo(subNo);
				receipt.setAccountType(cust.getCustType());
				receipt.setTaxNo(cust.getTaxNo());
				// receipt.setAccountBranch(customer.getBranch());
				receipt.setAccountBranch(cust.getCustBranch());
				receipt.setAddrLine1(customer.getReceiptAddr());
				receipt.setAddrLine2(customer.getInvoiceAddr());
				
				receipt.setCurrencyCode(cust.getCurrencyCode());

				// receipt.setPaymentCase(paymentDTO.getPaymentCase());
				receipt.setBranchCode(branchCode);
				receipt.setBranchArea(branchArea);
				receipt.setBranchName(branchName);
				receipt.setAmount(rcpAmount);
				receipt.setDiscount(rcpDiscount);
				receipt.setCharge(rcpCharge);
				receipt.setVatRate(VAT_RATE);
				receipt.setVat(rcpVat);
				receipt.setTotalCharge(rcpTotalCharge);
				receipt.setDeduction(rcpDeduction);
				receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
				receipt.setBalanceDue(rcpBalanceDue);
				receipt.setReceived(rcpReceived);
				receipt.setAfterSaleDiscVat(rcpAfterSaleDiscVat);// by NSD
																	// 05-04-2017
				receipt.setChange(rcpChange.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rcpChange);
				receipt.setAdvanced(null);
				receipt.setPromotion(null);
				receipt.setRemark(cust.getRemark());
				receipt.setReprint(0L);

				// sumAfterSaleDisc =
				// sumAfterSaleDisc.add(rcpAfterSaleDiscount!=null?rcpAfterSaleDiscount:BigDecimal.ZERO).add(rcpAfterSaleDiscVat!=null?rcpAfterSaleDiscVat:BigDecimal.ZERO);

				if (rcpReceived.doubleValue() < balanceDue.doubleValue()) {
					receipt.setAttributes("S");
				} else {
					receipt.setAttributes("F");
				}

				receipt.setBillingGroup(cust.getBillGroup());
				receipt.setBillingGroupFull(cust.getBillGroup());
				receipt.setBillingServiceName(cust.getInvoiceDisplay());// by
																		// NSD
																		// 24-03-2017
				receipt.setLanguage(language);
				receipts.add(receipt);
				amount = amount.add(rcpAmount);
				discount = discount.add(rcpDiscount);
				charge = charge.add(rcpCharge);
				vat = vat.add(rcpVat);
				totalCharge = totalCharge.add(rcpTotalCharge);
				balanceDue = balanceDue.add(rcpBalanceDue);
				afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
				deduction = deduction.add(rcpWt);
			} else {
				BigDecimal rcpAmount = BigDecimal.ZERO, rcpDiscount = BigDecimal.ZERO, rcpCharge = BigDecimal.ZERO,
						rcpVat = BigDecimal.ZERO, rcpTotalCharge = BigDecimal.ZERO, rcpBalanceDue = BigDecimal.ZERO,
						rcpAfterSaleDiscount = BigDecimal.ZERO, rcpDeduction = BigDecimal.ZERO,
						rcpAfterSaleDiscVat = BigDecimal.ZERO;
				for (SettlePaymentDTO.Invoice inv : cust.getInvoices()) {
					rcpAmount = inv.getAmount();
					rcpDiscount = inv.getDiscount();
					rcpCharge = inv.getCharge();
					rcpVat = inv.getVat();
					rcpTotalCharge = inv.getTotalCharge();
					rcpAfterSaleDiscount = inv.getAfterSaleDiscount();
					rcpAfterSaleDiscVat = inv.getAfterSaleDiscVat();
					rcpBalanceDue = inv.getBalanceDue();
					rcpDeduction = inv.getDeduction();
					// End Fix by EPIS8 30/12/2016 issue no 59, 131
					receipt = receiptRepo.save(new Receipt());
					receipt.setUpdateDttm(date);
					receipt.setUpdateUser(userName);
					receipt.setDocDttm(receiptDttm);
					receipt.setFlgHeader(FLG_HEADER_2);
					invoice = invoiceRepo.save(new Invoice());
					invoice.setUpdateDttm(date);
					invoice.setUpdateUser(userName);
					Service service = serviceRepo.save(new Service());
					service.setUpdateDttm(date);
					service.setReceiptId(receipt.getId());
					service.setInvoiceId(invoice.getId());
					service.setAccountNo("1234567890");
					service.setProductCode("102020003");
					service.setProductName("prod name");
					service.setProductSubCode("sub code");
					service.setProductSubName("sub name");
					service.setIncomeType("1");
					service.setAmount(inv.getReceived());// service.setAmount(inv.getReceived().subtract(inv.getAfterSaleDiscount()));//by
															// NSD 04-05-2017
					service.setAfterSaleDiscount(inv.getAfterSaleDiscount() != null
							? inv.getAfterSaleDiscount().add(
									inv.getAfterSaleDiscVat() != null ? inv.getAfterSaleDiscVat() : BigDecimal.ZERO)
							: BigDecimal.ZERO);
					receipt.getServices().add(service);
					invoice.getServices().add(service);
					addTransactionsIntoService(paids, payment, invoice, date, userName);
					invoice.setReceiptId(receipt.getId());
					invoice.setCustomer(customer);
					invoice.setPayment(payment);
					invoice.setNo(inv.getNo());
					invoice.setKenan(inv.getKenan());
					invoice.setCurrencyCode(inv.getCurrencyCode());
					invoice.setIssueDt(inv.getIssueDt());
					invoice.setDueDt(inv.getDueDt());
					invoice.setBillCycle(inv.getBillCycle());
					invoice.setAmount(inv.getAmount());
					invoice.setDiscount(inv.getDiscount());
					invoice.setCharge(inv.getCharge());
					invoice.setSource(cust.getSouceType());
					if (!StringUtils.equals(inv.getStatus(), INVOICE_OTHER_PAYMENT)
							|| inv.getVat().compareTo(new BigDecimal(0)) == 1) {// by
																				// NSD
																				// 02-04-2017
						invoice.setVatRate(VAT_RATE);
						invoice.setVat(inv.getVat());
						if (!StringUtils.equals(inv.getTaxTypeCode(), TAX_CODE_NONVAT)
								|| inv.getVat().compareTo(new BigDecimal(0)) == 1) {
							receipt.setFlgHeader(FLG_HEADER_1);
						}
					} else {
						invoice.setVat(null);
						invoice.setVatRate(null);
					}
					invoice.setDiscApprUser(inv.getDiscApprUser());
					invoice.setTotalCharge(inv.getReceived());
					invoice.setDeduction(inv.getDeduction());
					invoice.setAfterSaleDiscount(inv.getAfterSaleDiscount());
					invoice.setAfterSaleDiscVat(inv.getAfterSaleDiscVat());// by
																			// NSD
																			// 03-05-2017
					invoice.setBalanceDue(inv.getBalanceDue());
					invoice.setReceived(inv.getReceived());
					invoice.setChange(inv.getChange());
					invoice.setAdvanced(inv.getAdvanced());
					invoice.setStatus(inv.getStatus());
					invoice.setDiscountType(inv.getDiscountType());// by NSD
																	// 04-04
					invoice.setAttributes(invoice.getBalanceDue().compareTo(BigDecimal.ZERO) == 0
							? (inv.getTotalCharge().compareTo(invoice.getReceived()) != 0 ? "P" : "F") : "P");
					// by NSD 23-03-2017
					Map<String, String> accountSubNoMap = new HashMap<String, String>();
					String subNoInv = "";
					if (!CollectionUtils.isEmpty(inv.getSubNoList())) {
						for (String subno : inv.getSubNoList()) {
							accountSubNoMap.put(subno, subno);
							subNoInv = subno + "|";
						}
					}

					if (!CollectionUtils.isEmpty(inv.getInvoiceVatDetails())) {
						for (SettlePaymentDTO.InvoiceVatDetail invDtl : inv.getInvoiceVatDetails()) {
							invoiceVatDetail = invoiceVatRepository.save(new InvoiceVatDetail());
							invoiceVatDetail.setUpdateDttm(date);
							invoiceVatDetail.setUpdateUser(userName);
							invoice.getInvoiceVatDetails().add(invoiceVatDetail);
							invoiceVatDetail.setInvoiceId(invoice.getId());
							invoiceVatDetail.setInvoiceNo(invDtl.getInvoiceNo());
							invoiceVatDetail.setAccountNo(invDtl.getAccountNo());
							invoiceVatDetail.setAmount(invDtl.getAmount());
							invoiceVatDetail.setVat(invDtl.getVat());
							invoiceVatDetail.setTaxTypeCode(invDtl.getTaxTypeCode());
						}
					}

					invoice.setSubNo(subNoInv);

					receipt.getInvoices().add(invoice);
					receipt.setCustomer(customer);
					receipt.setPayment(payment);
					receipt.setType(getReceiptType2(customer));
					String receiptType = "";
					if (cust.getReceiptFormat().toUpperCase().equals(RECEIPT_FORMAT_WH_ONLY)) {// by
																								// NSD
																								// 24-04-2017
						receipt.setFlgHeader(FLG_HEADER_3);
					}
					if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_1)) {
						if (RECEIPT_TYPE_FULL.equals(receipt.getType())) {
							receiptType = RECEIPT_NO_FLAG_WITH_TAX_INVOICE;
						} else {
							receiptType = RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE;
						}
					} else if (StringUtils.equals(receipt.getFlgHeader(), FLG_HEADER_2)) {
						receiptType = RECEIPT_NO_FLAG_RECEIVE_ONLY;
					} else {
						receiptType = RECEIPT_NO_FLAG_WH_ONLY;
					}
					receipt.setNo(getReceiptNo(posNo, receiptType, receiptDttm, RECEIPT_HEADER_EPO));

					// by NSD 24-04-2017
					Date dtFrom = receiptDttm;
					Calendar c = Calendar.getInstance();
					c.setTime(dtFrom);
					c.add(Calendar.DATE, 1);
					Date dtTo = c.getTime();
					List<Receipt> rcptList = receiptRepo.findByTypeAndBranchAreaAndFlgHeaderAndDocDttmOrderByNoDesc(
							receipt.getType(), branchArea, receipt.getFlgHeader(), dtFrom, dtTo);
					BeanComparator fieldComparator = new BeanComparator("no");
					Collections.sort(rcptList, fieldComparator);
					if (rcptList != null && rcptList.size() > 0) {
						receiptDttm = rcptList.get(rcptList.size() - 1).getDocDttm();
						receipt.setDocDttm(receiptDttm);
					}
					receipt.setName(cust.getCustName());
					receipt.setAccountName(cust.getCustName());
					receipt.setAccountNo(cust.getCustNo());
					/*
					 * Map<String,String> accountSubNoMap = new
					 * HashMap<String,String>();
					 * if(!CollectionUtils.isEmpty(inv.getSubNoList())) {
					 * for(String subno:inv.getSubNoList()){
					 * accountSubNoMap.put(subno,subno); } }
					 */
					String subNo = "";
					if (accountSubNoMap.size() > 1) {
						subNo = String.valueOf(accountSubNoMap.size());
					} else if (accountSubNoMap.size() == 1) {
						Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
						subNo = entry.getValue();
					} else {
						// subNo = cust.getCustSubNo();
					}

					receipt.setAccountSubNo(subNo);
					// receipt.setAccountSubNo(cust.getCustSubNo());
					receipt.setAccountType(cust.getCustType());
					receipt.setTaxNo(cust.getTaxNo());
					// receipt.setAccountBranch(customer.getBranch());
					receipt.setAccountBranch(cust.getCustBranch());
					receipt.setAddrLine1(cust.getAddress1());
					receipt.setAddrLine2(cust.getAddress2());
					// receipt.setPaymentCase(paymentDTO.getPaymentCase()); ser

					System.out.println("# PART 2 receipt.getId() = " + receipt.getId());
					System.out.println("# PART 2 invoice.getReceiptId() = " + invoice.getReceiptId());
					String paymentCase = "";
					for (SettlePaymentDTO.Invoice inv2 : cust.getInvoices()) {
						if (receipt.getId() == invoice.getReceiptId()) {
							if (inv2.getPaymentCase() != null && inv2.getPaymentCase().indexOf("+") > 0) {
								String paymentCaseArr[] = inv2.getPaymentCase().split("\\+");
								for (int i = 0; i < paymentCaseArr.length; i++) {
									if (paymentCaseArr[paymentCaseArr.length - 1].length() <= 1) { // Remove
																									// "+"
																									// last
																									// index
										paymentCase = inv2.getPaymentCase().substring(0,
												inv2.getPaymentCase().length() - 3);
									} else {
										paymentCase = inv2.getPaymentCase();
									}
								}
								receipt.setPaymentCase(paymentCase);
							} else {
								receipt.setPaymentCase(inv2.getPaymentCase());
							}

						}
					}
					receipt.setBranchCode(branchCode);
					receipt.setBranchArea(branchArea);
					receipt.setBranchName(branchName);
					receipt.setAmount(rcpAmount);
					receipt.setDiscount(rcpDiscount);
					receipt.setCharge(rcpCharge);
					receipt.setVatRate(VAT_RATE);
					receipt.setVat(rcpVat);
					receipt.setTotalCharge(rcpTotalCharge);
					receipt.setDeduction(rcpDeduction);
					receipt.setAfterSaleDiscount(rcpAfterSaleDiscount);
					receipt.setAfterSaleDiscVat(rcpAfterSaleDiscVat);
					receipt.setBalanceDue(rcpBalanceDue);
					receipt.setReceived(invoice.getReceived());
					receipt.setChange(invoice.getChange());
					receipt.setAdvanced(null);
					receipt.setPromotion(null);
					receipt.setRemark(cust.getRemark());
					receipt.setReprint(0L);

					// sumAfterSaleDisc =
					// sumAfterSaleDisc.add(rcpAfterSaleDiscount!=null?rcpAfterSaleDiscount:BigDecimal.ZERO).add(rcpAfterSaleDiscVat!=null?rcpAfterSaleDiscVat:BigDecimal.ZERO);

					if (invoice.getReceived().doubleValue() < rcpAmount.doubleValue()) {
						receipt.setAttributes("P");
					} else {
						receipt.setAttributes("F");
					}

					receipt.setBillingGroup(cust.getBillGroup());
					receipt.setBillingGroupFull(cust.getBillGroup());
					receipt.setBillingServiceName(cust.getInvoiceDisplay());// by
																			// NSD
																			// 24-03-2017
					receipt.setLanguage(language);
					receipts.add(receipt);
					amount = amount.add(rcpAmount);
					discount = discount.add(rcpDiscount);
					charge = charge.add(rcpCharge);
					vat = vat.add(rcpVat);
					totalCharge = totalCharge.add(rcpTotalCharge);
					balanceDue = balanceDue.add(rcpBalanceDue);
					afterSaleDiscount = afterSaleDiscount.add(rcpAfterSaleDiscount);
					deduction = deduction.add(rcpDeduction);
				}
			}
		}
		BigDecimal totalAdvanced = BigDecimal.ZERO;
		for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
			for (final SettlePaymentDTO.Advanced adv : paymentDTO.getAdvances()) {
				customer = find(customers, new Predicate<Customer>() {
					@Override
					public boolean apply(Customer customer) {
						return trimToEmpty(customer.getNo()).equals(adv.getCustNo());
					}
				});
				receipt = receiptRepo.save(new Receipt());
				receipt.setUpdateDttm(date);
				receipt.setUpdateUser(userName);
				receipt.setDocDttm(receiptDttm);
				receipts.add(receipt);
				invoice = invoiceRepo.save(new Invoice());
				invoice.setUpdateDttm(date);
				invoice.setUpdateUser(userName);
				Service service = serviceRepo.save(new Service());
				service.setUpdateDttm(date);
				service.setReceiptId(receipt.getId());
				service.setInvoiceId(invoice.getId());
				service.setAccountNo("1234567890");
				service.setProductCode("19201");
				service.setProductName("prod name");
				service.setProductSubCode("sub code");
				service.setProductSubName("sub name");
				service.setIncomeType("1");
				service.setAmount(adv.getReceived());
				receipt.getServices().add(service);
				invoice.getServices().add(service);
				addTransactionsIntoService(paids, payment, invoice, date, userName);
				invoice.setReceiptId(receipt.getId());
				invoice.setCustomer(customer);
				invoice.setPayment(payment);
				invoice.setNo("Advance Payment");
				invoice.setKenan(adv.getKenan());
				invoice.setCurrencyCode(adv.getCurrencyCode());
				invoice.setIssueDt(null);
				invoice.setDueDt(null);
				if (!StringUtils.isEmpty(adv.getBillCycle())) {
					invoice.setBillCycle(adv.getBillCycle());
				} else {
					invoice.setBillCycle("เริ่มตั้งแต่เดือน"
							+ FastDateFormat.getInstance("MM/yyyy", new Locale("th", "TH")).format(date));
				}

				invoice.setAmount(adv.getAmount());
				invoice.setDiscount(adv.getDiscount());
				invoice.setCharge(adv.getCharge());
				invoice.setVatRate(VAT_RATE);
				invoice.setVat(adv.getVat());
				invoice.setTotalCharge(adv.getReceived());
				invoice.setDeduction(adv.getDeduction());
				invoice.setAfterSaleDiscount(BigDecimal.ZERO);
				invoice.setBalanceDue(adv.getBalanceDue());
				invoice.setReceived(adv.getReceived());
				invoice.setChange(adv.getChange());
				invoice.setAdvanced(adv.getAdvanced());
				invoice.setStatus("SUCCESS");
				invoice.setAttributes("A");
				receipt.getInvoices().add(invoice);
				receipt.setCustomer(customer);
				receipt.setPayment(payment);
				receipt.setType(getReceiptType2(customer));
				receipt.setFlgHeader(FLG_HEADER_1);

				receipt.setNo(getReceiptNo(posNo, RECEIPT_TYPE_FULL.equals(receipt.getType())
						? RECEIPT_NO_FLAG_WITH_TAX_INVOICE : RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE, receiptDttm,
						RECEIPT_HEADER_EPO));
				receipt.setName(customer.getName());
				receipt.setAccountName(customer.getName());
				receipt.setAccountNo(customer.getNo());

				String subNo = "";
				Map<String, String> accountSubNoMap = new HashMap<String, String>();
				SubscriptionDTO dto = new SubscriptionDTO();
				// call F05 RetreiveServiceStatus
				// RetrieveServiceStatusRequest request = new
				// RetrieveServiceStatusRequest();
				// request.setBillingAccountNo(customer.getNo());

				if (accountSubNoMap.size() > 1) {
					subNo = String.valueOf(accountSubNoMap.size());
				} else if (accountSubNoMap.size() == 1) {
					Map.Entry<String, String> entry = accountSubNoMap.entrySet().iterator().next();
					subNo = entry.getValue();
				} else {

				}
				receipt.setAccountSubNo(subNo);
				receipt.setAccountType(adv.getCustType());
				receipt.setTaxNo(customer.getTax());
				// receipt.setAccountBranch(customer.getBranch());

				receipt.setAddrLine1(customer.getReceiptAddr());
				receipt.setAddrLine2(customer.getInvoiceAddr());
				receipt.setPaymentCase(paymentDTO.getPaymentCase());
				receipt.setBranchCode(branchCode);
				receipt.setBranchArea(branchArea);
				receipt.setBranchName(branchName);
				receipt.setAmount(adv.getAmount());
				receipt.setDiscount(adv.getDiscount());
				receipt.setCharge(adv.getCharge());
				receipt.setVatRate(VAT_RATE);
				receipt.setVat(adv.getVat());
				receipt.setTotalCharge(adv.getTotalCharge());
				receipt.setDeduction(adv.getDeduction());
				receipt.setAfterSaleDiscount(BigDecimal.ZERO);
				receipt.setBalanceDue(invoice.getBalanceDue());
				receipt.setReceived(invoice.getReceived());
				receipt.setChange(BigDecimal.ZERO);
				receipt.setAdvanced(adv.getAdvanced());
				receipt.setPromotion(null);
				receipt.setRemark(customer.getRemark());
				receipt.setReprint(0L);
				receipt.setAttributes("A");
				receipt.setBillingGroup(customer.getBillGroup());
				receipt.setBillingGroupFull(customer.getBillGroup());
				receipt.setBillingServiceName(adv.getInvoiceDisplay());
				receipt.setLanguage(language);
				// Set Posno and PosId
				receipt.setPosid(EpContextHolder.getContext().getPosId());
				receipt.setPosno(EpContextHolder.getContext().getPosNo());
				totalAdvanced = totalAdvanced.add(adv.getAmount());
			}
		}
		if (receipt.getId() != null) {
			for (Method met : payment.getMethods()) {
				met.setRecieptId(receipt.getId());
				met.setId(met.getId());
				methodRepo.save(met);
			}

		}

		// insert Data into Table RECEIPT_EGP_MAPPING
		String currencyCode = "";
		BigDecimal currencyRate = BigDecimal.ONE;
		for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
			for (Receipt rec : receipts) {
				if (!StringUtils.isBlank(cust.getEgpNo()) && !StringUtils.isBlank(cust.getEgpContract())) {
					egpMap = new ReceiptEgpMappingEntity();
					egpMap.setReceiptId(rec.getId());
					egpMap.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
					egpMap.setCreatedDate(timestamp);
					egpMap.setBaNo(cust.getCustNo());
					egpMap.setEgpNo(cust.getEgpNo());
					egpMap.setEgpContract(cust.getEgpContract());
					egpMap.setReceiptNo(rec.getNo());
					egpMaps.add(egpMap);
					egpMapRepo.save(egpMap);
				}
			}
			receipt.setAccountBranch(cust.getCustBranch());
			currencyCode = cust.getCurrencyCode();
			currencyRate = cust.getCurrencyRate() != null ? cust.getCurrencyRate() : currencyRate;
		}
		for (SettlePaymentDTO.Customer cust : paymentDTO.getCustomers()) {
			payment.setOfficerId(officerId);
			payment.setShopId(EpContextHolder.getContext().getBranchId());
			payment.setPosId(EpContextHolder.getContext().getPosId());
			payment.setCollectionUnit(customer.getCollectionUnit());
			payment.setDate(date);
			payment.setType(cust.getSouceType());
			payment.setMethod(paymentDTO.getPaymentCase());
			payment.setAmount(amount);
			payment.setDiscount(discount);
			for (SettlePaymentDTO.Method met : paymentDTO.getMethods()) {
				if ("CC".equals(met.getCode())) {
					payment.setCharge(paymentDTO.getReceiveAmount());
				}
			}
			payment.setVatRate(VAT_RATE);
			payment.setVat(vat);
			payment.setTotalCharge(totalCharge);
			payment.setDeduction(deduction);
			payment.setAfterSaleDiscount(afterSaleDiscount);
			payment.setBalanceDue(balanceDue);
			payment.setReceived(paymentDTO.getReceiveAmount());
			payment.setChange(paymentDTO.getRemainAmount());
			payment.setAdvanced(totalAdvanced);
			payment.setAttributes("S");
			payment.setCurrencyCode(currencyCode);
			payment.setCurrencyRate(currencyRate);
			int result = dwService.sendOtbossWereHose(receipt.getPayment().getId());
			System.out.println(result);
		}

		return receipts;
	}

	public void creatPayment(SettlePaymentDTO paymentDTO, List<Receipt> receipt) throws Exception {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String userLogin = EpContextHolder.getContext().getUserName();
		for (SettlePaymentDTO.Customer cus : paymentDTO.getCustomers()) {
			for (SettlePaymentDTO.Invoice invoice2 : cus.getInvoices()) {
				SearchOTBOSSDTO bossDTO = new SearchOTBOSSDTO();
				List<DWRevernueProduct> dto = new ArrayList<DWRevernueProduct>();
				bossDTO = seachCusOTBossByInvoice(cus.getCustNo(), cus.getSouceType(), invoice2.getBillCycle(),
						invoice2.getNo(), receipt.get(0).getPayment().getId());

				OTTBossEntity ottBossEntity = new OTTBossEntity();
				dto = seachPayment(receipt.get(0).getPayment().getId());

				for (int i = 0; i < bossDTO.getData().size(); i++) {
					SearchOTBOSS searchOTBOSS = bossDTO.getData().get(i);
					for (Iterator<DWRevernueProduct> iterator = dto.iterator(); iterator.hasNext();) {
						DWRevernueProduct product = iterator.next();
						try {

							System.out.println(product.getInvoiceId());
							System.out.println(receipt.get(0).getInvoices().iterator().next().getId());
							if (!product.getInvoiceId().equals(searchOTBOSS.getInvoiceId())) {
								continue;
							} else {
								if (!product.getProductCode().equals(searchOTBOSS.getProduct_code())) {
									continue;
								}
							}

							BigDecimal aount = searchOTBOSS.getBalancedue().subtract(product.getTotalAmount());
							OTTBossEntity oldResult = otbossRepo.findOne(searchOTBOSS.getId());
							oldResult.setRecord_status("R");
							oldResult.setUpdate_by(userLogin);
							oldResult.setPaydate(timestamp);
							oldResult.setUpdate_date(timestamp);
							oldResult.setTotal_amonut_paid(product.getTotalAmount());
							oldResult.setRemark(cus.getRemark());

							otbossRepo.save(oldResult);
							BigDecimal received = product.getAmount();
							BigDecimal vat = product.getVat();
							BigDecimal totalAmount = searchOTBOSS.getAmount_ar().subtract(received);
							BigDecimal totalVatAmount = searchOTBOSS.getVat_amount_ar().subtract(vat);
							
							if(StringUtils.equals("SAP_AR",cus.getSouceType())) {
//								BigDecimal totalAmountBath = searchOTBOSS.getAmountBathAr().subtract(invoice2.getAmountBath());
								oldResult.setRegion_name(searchOTBOSS.getRegion_name());
								oldResult.setAmount_ar(totalAmount); // 0 totalAmount = 0
								oldResult.setVat_amount_ar(totalVatAmount); // 0 totalVatAmount = 0
								oldResult.setTotal_amount_ar(aount); // 0 aount = 0
								oldResult.setTotal_amonut_paid(product.getTotalAmount()); // total_amont_ar
								oldResult.setBalancedue(aount); // 0 aount = 0
								
								oldResult.setFiscalYear(searchOTBOSS.getFiscalYear()); // not set
								//oldResult.setAmountBAR(BigDecimal.ZERO); //product.getTotalAmount();  not set????
								oldResult.setTaxCode(searchOTBOSS.getTaxCode()); // not set
								oldResult.setMessage(searchOTBOSS.getMessage()); // not set
								oldResult.setHeaderText(invoice2.getNo());
								oldResult.setDocNo(invoice2.getWithholdingTaxNo());
								oldResult.setReconcile(searchOTBOSS.getReconcile()); // not set
								oldResult.setCompanyCode(searchOTBOSS.getCompanyCode()); // not set
							}else {
								oldResult.setRegion_name(cus.getCollectionUnit());
								oldResult.setAmount_ar(totalAmount);
								oldResult.setVat_amount_ar(totalVatAmount);
								oldResult.setTotal_amount_ar(aount);
								oldResult.setTotal_amonut_paid(product.getTotalAmount());
								oldResult.setBalancedue(aount);
							}
							
							oldResult.setDiscount(invoice2.getDiscount());
							oldResult.setSap_period(invoice2.getBillCycle());
							oldResult.setE_gp(cus.getEgpNo());
							oldResult.setCustomer_address(cus.getAddress1());
							oldResult.setTax_id(cus.getTaxNo());
							oldResult.setAccount_no(cus.getCustNo());
							oldResult.setRemark(cus.getRemark());
							oldResult.setPaydate(timestamp);
							oldResult.setCreate_by(userLogin);
							oldResult.setCreate_date(timestamp);
							oldResult.setUpdate_by(userLogin);
							oldResult.setUpdate_date(timestamp);
							oldResult.setId(0);
							oldResult.setRecord_status("A");
							
							profile = otbossRepo.save(oldResult);
							iterator.remove();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		}

	}

	void addTransactionsIntoService(List<Paid> paids, Payment payment, Invoice invoice, Date date, String userName) {
		BigDecimal balanceDue;
		Transaction transaction;
		boolean isEnd = false;
		for (Service service : invoice.getServices()) {
			balanceDue = service.getAmount().subtract(
					service.getAfterSaleDiscount() != null ? service.getAfterSaleDiscount() : BigDecimal.ZERO);
			for (Paid paid : paids) {
				BigDecimal payInv = BigDecimal.ZERO;
				if (paid.getAmount().compareTo(BigDecimal.ZERO) <= 0)
					continue;
				if (paid.getAmount().compareTo(balanceDue) >= 0) {
					payInv = balanceDue;
					paid.setAmount(paid.getAmount().subtract(payInv));
					balanceDue = BigDecimal.ZERO;
					isEnd = true;
				} else {
					payInv = paid.getAmount();
					paid.setAmount(BigDecimal.ZERO);
					balanceDue = balanceDue.subtract(payInv);
					isEnd = false;
				}
				// payInv.add(payment.getAfterSaleDiscount());
				transaction = transactionRepo.save(new Transaction());
				transaction.setUpdateDttm(date);
				transaction.setUpdateUser(userName);
				transaction.setServiceId(service.getId());
				transaction.setTransactionId(AppUtil.generateTransactionID(15));
				transaction.setTrackingDetails("EPIS is waiting for ESP response message.");
				transaction.setTrackingRetry(0);
				transaction.setPaymentDate(date);
				transaction.setPaymentType(paid.getType());
				transaction.setAmount(payInv);
				transaction.setChequeNo(paid.getChequeNo());
				transaction.setAccountNo(paid.getBankAccount());
				transaction.setStatus(null);
				transaction.setPayment(payment);
				service.getTransactions().add(transaction);
				if ("BANKTRANSFER".equals(paid.getType())) {
					paid.getMoneyTransfer().setTransaction(transaction);
				}
				if (isEnd)
					break;
			}
			// case there is aftersales discount
			if (service.getAfterSaleDiscount() != null
					&& service.getAfterSaleDiscount().compareTo(BigDecimal.ZERO) > 0) {
				transaction = transactionRepo.save(new Transaction());
				transaction.setUpdateDttm(date);
				transaction.setUpdateUser(userName);
				transaction.setServiceId(service.getId());
				transaction.setTransactionId(AppUtil.generateTransactionID(15));
				transaction.setTrackingDetails("EPIS is waiting for ESP response message.");
				transaction.setTrackingRetry(0);
				transaction.setPaymentDate(date);
				transaction.setPaymentType(AFTERSALES_DISCOUNT_METHOD);
				transaction.setAmount(service.getAfterSaleDiscount());
				// transaction.setChequeNo(paid.getChequeNo());
				// transaction.setAccountNo(paid.getBankAccount());
				transaction.setStatus(null);
				transaction.setPayment(payment);
				service.getTransactions().add(transaction);
			}
		}
	}

	String getReceiptType(Customer customer) {
		if (customer == null) {
			throw new NullPointerException("This error is occurred when programatic has incomplete.");
		} else if (stripToEmpty(customer.getType()).toUpperCase().indexOf("INDIVIDUAL") == 0) {
			return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) ? RECEIPT_TYPE_ABBREVIATION
					: RECEIPT_TYPE_FULL;
		} else if (stripToEmpty(customer.getType()).toUpperCase().indexOf("ORGANIZATION") == 0) {
			return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) || isBlank(customer.getTax())
					|| isBlank(customer.getBranch()) ? RECEIPT_TYPE_ABBREVIATION : RECEIPT_TYPE_FULL;
		} else if (stripToEmpty(customer.getType()).toUpperCase().indexOf("STATEAGENCY") == 0) {
			return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) ? RECEIPT_TYPE_ABBREVIATION
					: RECEIPT_TYPE_FULL;
		} else {
			throw new UnsupportedOperationException(
					"Please specify the customer type is INDIVIDUAL or ORGANIZATION or STATEAGENCY");
		}
	}

	// by NSD 02-03-2017
	String getReceiptType2(Customer customer) {
		if (customer == null) {
			throw new NullPointerException("This error is occurred when programatic has incomplete.");
		} else if (!StringUtils.isEmpty(customer.getAcctCatLkp())) {
			if (StringUtils.equals(customer.getAcctCatLkp(), "1")) {
				return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) || isBlank(customer.getTax())
						|| isBlank(customer.getBranch()) ? RECEIPT_TYPE_ABBREVIATION : RECEIPT_TYPE_FULL;
			} else {
				return isBlank(customer.getName()) || isBlank(customer.getReceiptAddr()) ? RECEIPT_TYPE_ABBREVIATION
						: RECEIPT_TYPE_FULL;
			}
		} else {
			return getReceiptType(customer);
		}
	}
	
}

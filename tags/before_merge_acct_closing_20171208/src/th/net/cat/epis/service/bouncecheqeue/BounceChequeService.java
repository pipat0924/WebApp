package th.net.cat.epis.service.bouncecheqeue;

import static th.net.cat.epis.util.AppConstants.ADVANCE_PAYMENT;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_RECEIPTTAXINVOICE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.transaction.annotation.Transactional;

import th.net.cat.crm.entity.CustomerService;
import th.net.cat.epis.controller.otboss.OTTBossController;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.CreditLimitTrans;
import th.net.cat.epis.dto.MasterDataDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.dto.SettlePaymentDTO.Customer;
import th.net.cat.epis.dto.bouncecheque.BounceChequeDTO;
import th.net.cat.epis.dto.bouncecheque.CheckBounceChequeDTO;
import th.net.cat.epis.dto.bouncecheque.DetailARCustomerDTO;
import th.net.cat.epis.dto.bouncecheque.HistoryARDTO;
import th.net.cat.epis.dto.bouncecheque.PayBounceChequeDTO;
import th.net.cat.epis.dto.bouncecheque.SubPayBounceChequeDTO;
import th.net.cat.epis.entity.BounceCheque;
import th.net.cat.epis.entity.CreditLimitTransEntity;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.PaymentSummary;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Session;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.epis.repo.CreditLimitTransRepository;
import th.net.cat.epis.repo.InvoiceRepository;
import th.net.cat.epis.repo.MasterDataRepository;
import th.net.cat.epis.repo.PayBounceChequeRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.service.UserService;
import th.net.cat.epis.service.otboss.OTBOssService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;

import static th.net.cat.epis.util.AppConstants.RECEIPT_FORMAT_WH_ONLY;

@org.springframework.stereotype.Service
public class BounceChequeService {
	private static Logger logger = Logger.getLogger(OTTBossController.class);
	@Autowired
	PayBounceChequeRepository payBounceChequeRepository;
	@Autowired
	PaymentRepository paymentRepo;
	@Autowired
	OTBOssService otbossservice;
	@Autowired
	UserService userService;
	@Autowired
	InvoiceRepository invoiceRepo;
	@Autowired
	CreditLimitTransRepository creditLimitTransRepository;
	@Autowired
	PaymentService paymentService;
	@Autowired
	private MasterDataRepository masterDataRepository;

	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	@Resource(name = "viewCrmJdbcTemplate")
	JdbcTemplate viewCrmJdbcTemplate;
	private static final String BOUNCE_CHEQUE_STATUS = "BOUNCE_CHEQUE_STATUS";
	private BounceChequeDTO bounceChequeDTO;
	private PayBounceChequeDTO payBounceChequeDTO;
	private DetailARCustomerDTO detailARCustomerDTO;
	private HistoryARDTO historyARDTO;
	private SubPayBounceChequeDTO subPayBounceChequeDTO;

	@Transactional
	public BounceChequeDTO searchPaySAP(String arCode, String docHead) {
		List<BounceCheque> bounceChequeList = new ArrayList<BounceCheque>();
		bounceChequeList = payBounceChequeRepository.searchPaySAP(arCode, docHead);
		BigDecimal vatRate = new BigDecimal(masterDataRepository.findByKey(AppConstants.VAT_RATE).get(0).getValue());

		bounceChequeDTO = new BounceChequeDTO();
		detailARCustomerDTO = new DetailARCustomerDTO();
		payBounceChequeDTO = new PayBounceChequeDTO();
		historyARDTO = new HistoryARDTO();
		HistoryARDTO historyARDTO2 = new HistoryARDTO();
		List<PayBounceChequeDTO> list = new ArrayList<PayBounceChequeDTO>();
		List<HistoryARDTO> list2 = new ArrayList<HistoryARDTO>();

		if (!bounceChequeList.isEmpty()) {
			// if("160000026".equals(arCode) ||
			// "01/CN/C&W/2011".equals(docHead)) {
			detailARCustomerDTO.setAddress(
					bounceChequeList.get(0).getAddress() == null ? "" : bounceChequeList.get(0).getAddress());
			detailARCustomerDTO.setArAccountCode(bounceChequeList.get(0).getArAccountCode() == null ? ""
					: bounceChequeList.get(0).getArAccountCode());
			detailARCustomerDTO.setArGroup(
					bounceChequeList.get(0).getArGroup() == null ? "" : bounceChequeList.get(0).getArGroup());
			detailARCustomerDTO
					.setArName(bounceChequeList.get(0).getArName() == null ? "" : bounceChequeList.get(0).getArName());
			detailARCustomerDTO.setBranchAR(
					bounceChequeList.get(0).getBranchAR() == null ? "" : bounceChequeList.get(0).getBranchAR());
			detailARCustomerDTO.setGlAccount(
					bounceChequeList.get(0).getGlAccount() == null ? "" : bounceChequeList.get(0).getGlAccount());
			detailARCustomerDTO.setRegionKey1(
					bounceChequeList.get(0).getRegionKey1() == null ? "" : bounceChequeList.get(0).getRegionKey1());
			detailARCustomerDTO
					.setTaxId(bounceChequeList.get(0).getTaxId() == null ? "" : bounceChequeList.get(0).getTaxId());
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			for (int i = 0; i < bounceChequeList.size(); i++) {
				payBounceChequeDTO = new PayBounceChequeDTO();
				if (bounceChequeList.get(i).getBalanceDue() != BigDecimal.ZERO) {
					payBounceChequeDTO.setRefDate(formatDateDoc(bounceChequeList.get(i).getRefDate()));
					payBounceChequeDTO.setDocHead(bounceChequeList.get(i).getDocHead());
					payBounceChequeDTO.setServiceKey3(bounceChequeList.get(i).getServiceKey3());
					payBounceChequeDTO.setCurrency(bounceChequeList.get(i).getCurrency());
					payBounceChequeDTO.setAmountARin(bounceChequeList.get(i).getAmountARin());
					payBounceChequeDTO.setRateChange(bounceChequeList.get(i).getRateChange());
					payBounceChequeDTO.setAmountARout(bounceChequeList.get(i).getAmountARout());
					payBounceChequeDTO.setCurrencyChangeDate((bounceChequeList.get(i).getCurrencyChangeDate())); // formatDatePass
					payBounceChequeDTO.setDocNo(bounceChequeList.get(i).getDocNo());
					payBounceChequeDTO.setAccountYear(bounceChequeList.get(i).getAccountYear());
					payBounceChequeDTO.setDocDate(bounceChequeList.get(i).getDocDate()); // formatDatePass
					payBounceChequeDTO.setPassDate(formatDatePass(bounceChequeList.get(i).getPassDate()));
					payBounceChequeDTO.setVatCode(bounceChequeList.get(i).getVatCode());
					payBounceChequeDTO.setMessage(bounceChequeList.get(i).getMessage());
					payBounceChequeDTO.setArAccountCode(bounceChequeList.get(i).getArAccountCode());
					payBounceChequeDTO.setArName(bounceChequeList.get(i).getArName());
					payBounceChequeDTO.setGlAccount(bounceChequeList.get(i).getGlAccount());
					payBounceChequeDTO.setTaxId(bounceChequeList.get(i).getTaxId());
					payBounceChequeDTO.setArGroup(bounceChequeList.get(i).getArGroup());
					payBounceChequeDTO.setRegionKey1(bounceChequeList.get(i).getRegionKey1());
					payBounceChequeDTO.setBranchAR(bounceChequeList.get(i).getBranchAR());
					payBounceChequeDTO.setAddress(bounceChequeList.get(i).getAddress());
					payBounceChequeDTO.setRecordStatus(bounceChequeList.get(i).getRecordStatus());
					payBounceChequeDTO.setAmountPay(bounceChequeList.get(i).getBalanceDue());
					payBounceChequeDTO.setAmountTotalPay(bounceChequeList.get(i).getBalanceDue());

					list.add(payBounceChequeDTO);
				}

			}

			// payBounceChequeDTO2.setRefDate(formatDateDoc("2017060120170630"));
			// payBounceChequeDTO2.setAccountYear("");
			// payBounceChequeDTO2.setDocHead("269760598");
			// payBounceChequeDTO2.setServiceKey3("MPLS");
			// payBounceChequeDTO2.setCurrency("USD");
			// payBounceChequeDTO2.setAmountARin(730.81);
			// payBounceChequeDTO2.setRateChange(33.83591);
			// payBounceChequeDTO2.setAmountARout(24727.62);
			// payBounceChequeDTO2.setCurrencyChangeDate(formatDatePass("30.06.2017"));
			// payBounceChequeDTO2.setDocNo("1120002218");
			// payBounceChequeDTO2.setAccountYear("2017");
			// payBounceChequeDTO2.setDocDate(formatDatePass("30.06.2017"));
			// payBounceChequeDTO2.setPassDate(formatDatePass("30.06.2017"));
			// payBounceChequeDTO2.setVatCode("DU");
			// payBounceChequeDTO2.setMassage("MPLS รายตัวต่างประเทศ 01/59");
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Date date = new Date();
			historyARDTO.setAccountYeah("1234");
			historyARDTO.setAmountCurrency(200.00);
			historyARDTO.setAmountCurrencyin(100.00);
			historyARDTO.setArNo("เลขที่ใบแจ้งหนี้");
			historyARDTO.setBranch("สาขาลูกหนี้");
			historyARDTO.setChangeDate(date);
			historyARDTO.setCurrency("USD");
			historyARDTO.setDocNo("เลขที่เอกสาร");
			historyARDTO.setLogPay("วิธีการชำระ");
			historyARDTO.setMassage("Msss");
			historyARDTO.setPayAmountAR(500.00);
			historyARDTO.setPayDate(date);
			historyARDTO.setPayType("ประเภทการชำระ");
			historyARDTO.setRateChange(32.22);
			historyARDTO.setReceiptDate(date);
			historyARDTO.setReceiptNo("เลขที่ใบเสณ้จ");
			historyARDTO.setRefDate("รอบการใช้งาน");
			historyARDTO.setServiceKey3("Service");
			historyARDTO.setStatus("Status");
			historyARDTO.setUser("ผู้รับชำระ");
			historyARDTO.setVat(20.00);
			historyARDTO.setRemark("remark");
			historyARDTO.setVatIn(10.00);

			historyARDTO2.setAccountYeah("1234");
			historyARDTO2.setAmountCurrency(200.00);
			historyARDTO2.setAmountCurrencyin(100.00);
			historyARDTO2.setArNo("เลขที่ใบแจ้งหนี้");
			historyARDTO2.setBranch("สาขาลูกหนี้");
			historyARDTO2.setChangeDate(date);
			historyARDTO2.setCurrency("USD");
			historyARDTO2.setDocNo("เลขที่เอกสาร");
			historyARDTO2.setLogPay("วิธีการชำระ");
			historyARDTO2.setMassage("Msss");
			historyARDTO2.setPayAmountAR(500.00);
			historyARDTO2.setPayDate(date);
			historyARDTO2.setPayType("ประเภทการชำระ");
			historyARDTO2.setRateChange(32.22);
			historyARDTO2.setReceiptDate(date);
			historyARDTO2.setReceiptNo("เลขที่ใบเสณ้จ");
			historyARDTO2.setRefDate("รอบการใช้งาน");
			historyARDTO2.setServiceKey3("Service");
			historyARDTO2.setStatus("Status");
			historyARDTO2.setUser("ผู้รับชำระ");
			historyARDTO2.setVat(20.00);
			historyARDTO2.setRemark("remark");
			historyARDTO2.setVatIn(10.00);
			list2.add(historyARDTO2);
			list2.add(historyARDTO);

			bounceChequeDTO.setDetailARCustomerDTO(detailARCustomerDTO);
			bounceChequeDTO.setPayBounceChequeDTOList(list);
			bounceChequeDTO.setHistoryARDTOList(list2);
			bounceChequeDTO.setVatRD(vatRate);
		}
		return bounceChequeDTO;
	}

	public PayBounceChequeDTO findSubProduct(String docHead) {
		subPayBounceChequeDTO = new SubPayBounceChequeDTO();
		SubPayBounceChequeDTO subPayBounceChequeDTO2 = new SubPayBounceChequeDTO();
		List<SubPayBounceChequeDTO> list3 = new ArrayList<SubPayBounceChequeDTO>();
		payBounceChequeDTO = new PayBounceChequeDTO();

		subPayBounceChequeDTO2.setAmount("10101010100001");
		subPayBounceChequeDTO2.setProductCode("บริการ cat 01");
		subPayBounceChequeDTO2.setProductName("01");
		subPayBounceChequeDTO2.setRevTypeCode("บริการ CAT 01");
		subPayBounceChequeDTO2.setRevTypeName("03");
		subPayBounceChequeDTO2.setSubProductCode("0001");
		subPayBounceChequeDTO2.setSubProductName("บริการโน่นนั่นนี่");

		subPayBounceChequeDTO.setAmount("x1");
		subPayBounceChequeDTO.setProductCode("x2");
		subPayBounceChequeDTO.setProductName("x3");
		subPayBounceChequeDTO.setRevTypeCode("x4");
		subPayBounceChequeDTO.setRevTypeName("x5");
		subPayBounceChequeDTO.setSubProductCode("x6");
		subPayBounceChequeDTO.setSubProductName("100.00");
		list3.add(subPayBounceChequeDTO);
		list3.add(subPayBounceChequeDTO2);
		payBounceChequeDTO.setSubPayBounceChequeDTOList(list3);

		return payBounceChequeDTO;
	}

	public BounceChequeDTO searchCheckBounceCheque(String invoiceNo, String billNo, String chequeNo) {
		bounceChequeDTO = new BounceChequeDTO();
		List<CheckBounceChequeDTO> bounceList = new ArrayList<CheckBounceChequeDTO>();

		// -------------------------------------------------------------------------------------------------------------------
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT");
		sql.append(
				" CPM.PAYMENTDATE AS PAYMENTDATE,COR.RECEIPTDTTM AS RECEIPTDTTM,COR.RECEIPTNO AS RECEIPTNO,TRQ.CHEQUENO AS CHEQUENO,");
		sql.append(
				" CPM.COLLECTIONUNIT AS COLLECTIONUNIT,CPM.UPDATEUSER AS UPDATEUSER,TMP.INVOICENO AS INVOICENO,INV.INV_PERIOD AS INV_PERIOD,");
		sql.append(
				" INV.AMOUNT_BAHT_AR AS AMOUNT_BAHT_AR,CPM.PAYMENTCASE AS PAYMENTCASE,COR.ATTRIBUTES AS ATTRIBUTES,COR.TOTALCHARGE AS TOTALCHARGE,");
		sql.append(" COR.VAT AS VAT,TMP.STATUS AS STATUS,COR.REMARK AS REMARK,TRQ.BOUNCE_STATUS AS BOUNCE_STATUS");
		sql.append(" FROM  CORPAYMENT CPM ");
		sql.append(" INNER JOIN TMPINVOICE TMP ON CPM.PAYMENTID = TMP.PAYMENTID");
		sql.append(" INNER JOIN CORRECEIPT COR ON CPM.PAYMENTID = COR.PAYMENTID");
		sql.append(" INNER JOIN TRSCHEQUEREF TRQ ON CPM.PAYMENTID = TRQ.PAYMENTID");
		sql.append(" INNER JOIN INV_SOURCE INV ON TMP.INVOICENO = TO_CHAR(INV.AR_REF)");
		sql.append(" WHERE TMP.INVOICENO = ? OR COR.RECEIPTNO = ? OR TRQ.CHEQUENO = ?");

		episJdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				int i = 1;
				preparedStatement.setString(i++, invoiceNo);
				preparedStatement.setString(i++, billNo);
				preparedStatement.setString(i++, chequeNo);

			}
		}

				, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet row) throws SQLException {
						CheckBounceChequeDTO bean = new CheckBounceChequeDTO();

						bean.setBounceDate(row.getString("PAYMENTDATE"));
						bean.setCreBillDate(row.getString("RECEIPTDTTM"));
						bean.setBillNo(row.getString("RECEIPTNO"));
						bean.setChequeNo(row.getString("CHEQUENO"));
						bean.setPayLocation(row.getString("COLLECTIONUNIT"));
						bean.setUser(row.getString("UPDATEUSER"));
						bean.setDueDate(row.getString("INVOICENO"));
						bean.setUseDate(formatDateDoc(row.getString("INV_PERIOD")));
						bean.setBalanceDue(row.getDouble("AMOUNT_BAHT_AR"));
						bean.setPayLog(row.getString("PAYMENTCASE"));
						bean.setPayType(PaidType(row.getString("ATTRIBUTES")));
						bean.setPayAmount(row.getDouble("TOTALCHARGE"));
						bean.setVat(row.getDouble("VAT"));
						bean.setStatus(row.getString("STATUS"));
						bean.setRemark(row.getString("REMARK"));
						bean.setBounceStatus(row.getString("BOUNCE_STATUS"));

						bounceList.add(bean);
					}
				});
		// -------------------------------------------------------------------------------------------------------------------

		// CheckBounceChequeDTO bean2 = new CheckBounceChequeDTO();
		// bean2.setBalanceDue(8877.54);
		// bean2.setBillNo("EPO170401F1708180008");
		//// bean.setBounceCheque(false);
		// bean2.setBounceDate("18/08/2560 13:55:38");
		// bean2.setChequeNo("1111111");
		// bean2.setCreBillDate("18/08/2560 13:55:38");
		// bean2.setDueDate("220547342");
		// bean2.setPayAmount(8877.54);
		// bean2.setPayLog("เช็ค กรุงเทพ เลขที่ : 1111111 + เงินสด");
		// bean2.setPayLocation("ศบล. นนทบุรี");
		// bean2.setPayType("ชำระบางส่วน");
		// bean2.setRemark("Test Remark ::: ");
		// bean2.setStatus("ปกติ");
		// bean2.setUseDate("16/03/2016 - 15/04/2016");
		// bean2.setUser("EPIS100");
		// bean2.setVat(888.77);
		// bounceList.add(bean2);

		List<MasterDataDTO> listMaster = new ArrayList<MasterDataDTO>();
		try {
			listMaster = AppUtil.getGroupMasterData(BOUNCE_CHEQUE_STATUS);
			for (int i = 0; i < bounceList.size(); i++) {
				for (int a = 0; a < listMaster.size(); a++) {
					if (bounceList.get(i).getStatus().equals(listMaster.get(a).getKey())) {
						bounceList.get(i).setMasterDataDTO(listMaster.get(a));

					}
					if (bounceList.get(i).getBounceStatus() != null) {
						if (bounceList.get(i).getBounceStatus().equals(listMaster.get(a).getKey())) {
							bounceList.get(i).setMasterDataBounceStatus(listMaster.get(a));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		bounceChequeDTO.setCheckBounceChequeDTOList(bounceList);

		return bounceChequeDTO;
	}

	public String UpdateBounceCheque(BounceChequeDTO bounceChequeDTO) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TABLE SET FILDE = ? WHERE FILDE = ?");
		for (int i = 0; i < bounceChequeDTO.getCheckBounceChequeDTOList().size(); i++) {
			episJdbcTemplate.update(sql.toString(), bounceChequeDTO.getCheckBounceChequeDTOList().get(i).getBillNo());
		}
		return "test";
	}

	public BounceChequeDTO avdSearchPayBounceCheque(String docHead) {
		List<BounceCheque> bounceChequeList = new ArrayList<BounceCheque>();
		List<PayBounceChequeDTO> list = new ArrayList<PayBounceChequeDTO>();
		detailARCustomerDTO = new DetailARCustomerDTO();
		bounceChequeDTO = new BounceChequeDTO();
		historyARDTO = new HistoryARDTO();
		HistoryARDTO historyARDTO2 = new HistoryARDTO();
		List<HistoryARDTO> list2 = new ArrayList<HistoryARDTO>();
		bounceChequeList = payBounceChequeRepository.searchPaySAP(null, docHead);

		detailARCustomerDTO
				.setAddress(bounceChequeList.get(0).getAddress() == null ? "" : bounceChequeList.get(0).getAddress());
		detailARCustomerDTO.setArAccountCode(
				bounceChequeList.get(0).getArAccountCode() == null ? "" : bounceChequeList.get(0).getArAccountCode());
		detailARCustomerDTO
				.setArGroup(bounceChequeList.get(0).getArGroup() == null ? "" : bounceChequeList.get(0).getArGroup());
		detailARCustomerDTO
				.setArName(bounceChequeList.get(0).getArName() == null ? "" : bounceChequeList.get(0).getArName());
		detailARCustomerDTO.setBranchAR(
				bounceChequeList.get(0).getBranchAR() == null ? "" : bounceChequeList.get(0).getBranchAR());
		detailARCustomerDTO.setGlAccount(
				bounceChequeList.get(0).getGlAccount() == null ? "" : bounceChequeList.get(0).getGlAccount());
		detailARCustomerDTO.setRegionKey1(
				bounceChequeList.get(0).getRegionKey1() == null ? "" : bounceChequeList.get(0).getRegionKey1());
		detailARCustomerDTO
				.setTaxId(bounceChequeList.get(0).getTaxId() == null ? "" : bounceChequeList.get(0).getTaxId());

		for (int i = 0; i < bounceChequeList.size(); i++) {
			payBounceChequeDTO = new PayBounceChequeDTO();

			payBounceChequeDTO.setRefDate(formatDateDoc(bounceChequeList.get(i).getRefDate()));
			payBounceChequeDTO.setDocHead(bounceChequeList.get(i).getDocHead());
			payBounceChequeDTO.setServiceKey3(bounceChequeList.get(i).getServiceKey3());
			payBounceChequeDTO.setCurrency(bounceChequeList.get(i).getCurrency());
			payBounceChequeDTO.setAmountARin(bounceChequeList.get(i).getAmountARin());
			payBounceChequeDTO.setRateChange(bounceChequeList.get(i).getRateChange());
			payBounceChequeDTO.setAmountARout(bounceChequeList.get(i).getAmountARout());
			payBounceChequeDTO.setCurrencyChangeDate((bounceChequeList.get(i).getCurrencyChangeDate())); // formatDatePass
			payBounceChequeDTO.setDocNo(bounceChequeList.get(i).getDocNo());
			payBounceChequeDTO.setAccountYear(bounceChequeList.get(i).getAccountYear());
			payBounceChequeDTO.setDocDate(bounceChequeList.get(i).getDocDate()); // formatDatePass
			payBounceChequeDTO.setPassDate(formatDatePass(bounceChequeList.get(i).getPassDate()));
			payBounceChequeDTO.setVatCode(bounceChequeList.get(i).getVatCode());
			payBounceChequeDTO.setMessage(bounceChequeList.get(i).getMessage());
			payBounceChequeDTO.setArAccountCode(bounceChequeList.get(i).getArAccountCode());
			payBounceChequeDTO.setArName(bounceChequeList.get(i).getArName());
			payBounceChequeDTO.setGlAccount(bounceChequeList.get(i).getGlAccount());
			payBounceChequeDTO.setTaxId(bounceChequeList.get(i).getTaxId());
			payBounceChequeDTO.setArGroup(bounceChequeList.get(i).getArGroup());
			payBounceChequeDTO.setRegionKey1(bounceChequeList.get(i).getRegionKey1());
			payBounceChequeDTO.setBranchAR(bounceChequeList.get(i).getBranchAR());
			payBounceChequeDTO.setAddress(bounceChequeList.get(i).getAddress());
			payBounceChequeDTO.setRecordStatus(bounceChequeList.get(i).getRecordStatus());

			list.add(payBounceChequeDTO);
		}

		Date date = new Date();
		historyARDTO.setAccountYeah("1234");
		historyARDTO.setAmountCurrency(200.00);
		historyARDTO.setAmountCurrencyin(100.00);
		historyARDTO.setArNo("เลขที่ใบแจ้งหนี้");
		historyARDTO.setBranch("สาขาลูกหนี้");
		historyARDTO.setChangeDate(date);
		historyARDTO.setCurrency("USD");
		historyARDTO.setDocNo("เลขที่เอกสาร");
		historyARDTO.setLogPay("วิธีการชำระ");
		historyARDTO.setMassage("Msss");
		historyARDTO.setPayAmountAR(500.00);
		historyARDTO.setPayDate(date);
		historyARDTO.setPayType("ประเภทการชำระ");
		historyARDTO.setRateChange(32.22);
		historyARDTO.setReceiptDate(date);
		historyARDTO.setReceiptNo("เลขที่ใบเสณ้จ");
		historyARDTO.setRefDate("รอบการใช้งาน");
		historyARDTO.setServiceKey3("Service");
		historyARDTO.setStatus("Status");
		historyARDTO.setUser("ผู้รับชำระ");
		historyARDTO.setVat(20.00);
		historyARDTO.setRemark("remark");
		historyARDTO.setVatIn(10.00);

		historyARDTO2.setAccountYeah("1234");
		historyARDTO2.setAmountCurrency(200.00);
		historyARDTO2.setAmountCurrencyin(100.00);
		historyARDTO2.setArNo("เลขที่ใบแจ้งหนี้");
		historyARDTO2.setBranch("สาขาลูกหนี้");
		historyARDTO2.setChangeDate(date);
		historyARDTO2.setCurrency("USD");
		historyARDTO2.setDocNo("เลขที่เอกสาร");
		historyARDTO2.setLogPay("วิธีการชำระ");
		historyARDTO2.setMassage("Msss");
		historyARDTO2.setPayAmountAR(500.00);
		historyARDTO2.setPayDate(date);
		historyARDTO2.setPayType("ประเภทการชำระ");
		historyARDTO2.setRateChange(32.22);
		historyARDTO2.setReceiptDate(date);
		historyARDTO2.setReceiptNo("เลขที่ใบเสณ้จ");
		historyARDTO2.setRefDate("รอบการใช้งาน");
		historyARDTO2.setServiceKey3("Service");
		historyARDTO2.setStatus("Status");
		historyARDTO2.setUser("ผู้รับชำระ");
		historyARDTO2.setVat(20.00);
		historyARDTO2.setRemark("remark");
		historyARDTO2.setVatIn(10.00);
		list2.add(historyARDTO2);
		list2.add(historyARDTO);

		bounceChequeDTO.setDetailARCustomerDTO(detailARCustomerDTO);
		bounceChequeDTO.setPayBounceChequeDTOList(list);
		bounceChequeDTO.setHistoryARDTOList(list2);

		return bounceChequeDTO;
	}

	public BounceChequeDTO searchPayAdvTab(String accountNo, String cusName, String avdRegionKey1) {
		bounceChequeDTO = new BounceChequeDTO();
		List<PayBounceChequeDTO> list = new ArrayList<PayBounceChequeDTO>();
		detailARCustomerDTO = new DetailARCustomerDTO();
		List<BounceCheque> bounceChequeList = new ArrayList<BounceCheque>();
		historyARDTO = new HistoryARDTO();
		HistoryARDTO historyARDTO2 = new HistoryARDTO();
		List<HistoryARDTO> list2 = new ArrayList<HistoryARDTO>();
		bounceChequeList = payBounceChequeRepository.searchPayAdvTab(accountNo, cusName, avdRegionKey1);

		detailARCustomerDTO
				.setAddress(bounceChequeList.get(0).getAddress() == null ? "" : bounceChequeList.get(0).getAddress());
		detailARCustomerDTO.setArAccountCode(
				bounceChequeList.get(0).getArAccountCode() == null ? "" : bounceChequeList.get(0).getArAccountCode());
		detailARCustomerDTO
				.setArGroup(bounceChequeList.get(0).getArGroup() == null ? "" : bounceChequeList.get(0).getArGroup());
		detailARCustomerDTO
				.setArName(bounceChequeList.get(0).getArName() == null ? "" : bounceChequeList.get(0).getArName());
		detailARCustomerDTO.setBranchAR(
				bounceChequeList.get(0).getBranchAR() == null ? "" : bounceChequeList.get(0).getBranchAR());
		detailARCustomerDTO.setGlAccount(
				bounceChequeList.get(0).getGlAccount() == null ? "" : bounceChequeList.get(0).getGlAccount());
		detailARCustomerDTO.setRegionKey1(
				bounceChequeList.get(0).getRegionKey1() == null ? "" : bounceChequeList.get(0).getRegionKey1());
		detailARCustomerDTO
				.setTaxId(bounceChequeList.get(0).getTaxId() == null ? "" : bounceChequeList.get(0).getTaxId());

		for (int i = 0; i < bounceChequeList.size(); i++) {
			payBounceChequeDTO = new PayBounceChequeDTO();

			payBounceChequeDTO.setRefDate(formatDateDoc(bounceChequeList.get(i).getRefDate()));
			payBounceChequeDTO.setDocHead(bounceChequeList.get(i).getDocHead());
			payBounceChequeDTO.setServiceKey3(bounceChequeList.get(i).getServiceKey3());
			payBounceChequeDTO.setCurrency(bounceChequeList.get(i).getCurrency());
			payBounceChequeDTO.setAmountARin(bounceChequeList.get(i).getAmountARin());
			payBounceChequeDTO.setRateChange(bounceChequeList.get(i).getRateChange());
			payBounceChequeDTO.setAmountARout(bounceChequeList.get(i).getAmountARout());
			payBounceChequeDTO.setCurrencyChangeDate((bounceChequeList.get(i).getCurrencyChangeDate())); // formatDatePass
			payBounceChequeDTO.setDocNo(bounceChequeList.get(i).getDocNo());
			payBounceChequeDTO.setAccountYear(bounceChequeList.get(i).getAccountYear());
			payBounceChequeDTO.setDocDate(bounceChequeList.get(i).getDocDate()); // formatDatePass
			payBounceChequeDTO.setPassDate(formatDatePass(bounceChequeList.get(i).getPassDate()));
			payBounceChequeDTO.setVatCode(bounceChequeList.get(i).getVatCode());
			payBounceChequeDTO.setMessage(bounceChequeList.get(i).getMessage());
			payBounceChequeDTO.setArAccountCode(bounceChequeList.get(i).getArAccountCode());
			payBounceChequeDTO.setArName(bounceChequeList.get(i).getArName());
			payBounceChequeDTO.setGlAccount(bounceChequeList.get(i).getGlAccount());
			payBounceChequeDTO.setTaxId(bounceChequeList.get(i).getTaxId());
			payBounceChequeDTO.setArGroup(bounceChequeList.get(i).getArGroup());
			payBounceChequeDTO.setRegionKey1(bounceChequeList.get(i).getRegionKey1());
			payBounceChequeDTO.setBranchAR(bounceChequeList.get(i).getBranchAR());
			payBounceChequeDTO.setAddress(bounceChequeList.get(i).getAddress());
			payBounceChequeDTO.setRecordStatus(bounceChequeList.get(i).getRecordStatus());

			list.add(payBounceChequeDTO);
		}

		Date date = new Date();
		historyARDTO.setAccountYeah("1234");
		historyARDTO.setAmountCurrency(200.00);
		historyARDTO.setAmountCurrencyin(100.00);
		historyARDTO.setArNo("เลขที่ใบแจ้งหนี้");
		historyARDTO.setBranch("สาขาลูกหนี้");
		historyARDTO.setChangeDate(date);
		historyARDTO.setCurrency("USD");
		historyARDTO.setDocNo("เลขที่เอกสาร");
		historyARDTO.setLogPay("วิธีการชำระ");
		historyARDTO.setMassage("Msss");
		historyARDTO.setPayAmountAR(500.00);
		historyARDTO.setPayDate(date);
		historyARDTO.setPayType("ประเภทการชำระ");
		historyARDTO.setRateChange(32.22);
		historyARDTO.setReceiptDate(date);
		historyARDTO.setReceiptNo("เลขที่ใบเสณ้จ");
		historyARDTO.setRefDate("รอบการใช้งาน");
		historyARDTO.setServiceKey3("Service");
		historyARDTO.setStatus("Status");
		historyARDTO.setUser("ผู้รับชำระ");
		historyARDTO.setVat(20.00);
		historyARDTO.setRemark("remark");
		historyARDTO.setVatIn(10.00);

		historyARDTO2.setAccountYeah("1234");
		historyARDTO2.setAmountCurrency(200.00);
		historyARDTO2.setAmountCurrencyin(100.00);
		historyARDTO2.setArNo("เลขที่ใบแจ้งหนี้");
		historyARDTO2.setBranch("สาขาลูกหนี้");
		historyARDTO2.setChangeDate(date);
		historyARDTO2.setCurrency("USD");
		historyARDTO2.setDocNo("เลขที่เอกสาร");
		historyARDTO2.setLogPay("วิธีการชำระ");
		historyARDTO2.setMassage("Msss");
		historyARDTO2.setPayAmountAR(500.00);
		historyARDTO2.setPayDate(date);
		historyARDTO2.setPayType("ประเภทการชำระ");
		historyARDTO2.setRateChange(32.22);
		historyARDTO2.setReceiptDate(date);
		historyARDTO2.setReceiptNo("เลขที่ใบเสณ้จ");
		historyARDTO2.setRefDate("รอบการใช้งาน");
		historyARDTO2.setServiceKey3("Service");
		historyARDTO2.setStatus("Status");
		historyARDTO2.setUser("ผู้รับชำระ");
		historyARDTO2.setVat(20.00);
		historyARDTO2.setRemark("remark");
		historyARDTO2.setVatIn(10.00);
		list2.add(historyARDTO2);
		list2.add(historyARDTO);

		bounceChequeDTO.setDetailARCustomerDTO(detailARCustomerDTO);
		bounceChequeDTO.setPayBounceChequeDTOList(list);
		bounceChequeDTO.setHistoryARDTOList(list2);

		return bounceChequeDTO;
	}

	public void saveBounceChequeSet(List<CheckBounceChequeDTO> reqList) {
		for (CheckBounceChequeDTO dto : reqList) {
			System.out.println("Test Remark ::: " + dto.getRemark());
		}
	}

	public void testCal(List<PayBounceChequeDTO> bean) {
		PayBounceChequeDTO beans = new PayBounceChequeDTO();
		PayBounceChequeDTO result = new PayBounceChequeDTO();
		Double beforeSet = 0.00;
		Double beforeSet2 = 0.00;
		if (bean != null) {
			for (int i = 0; i < bean.size(); i++) {
				beans = bean.get(i);
				beforeSet = (beans.getAmountARin() * 100) / 107;
				result.setAmountARin(
						i == 0 ? fmDouble2P(beforeSet) : fmDouble2P(result.getAmountARin()) + fmDouble2P(beforeSet));
				Double sumVat = beans.getAmountARin() - result.getAmountARin();
				System.out.println(fmDouble2P(sumVat));

				beforeSet2 = (beans.getAmountARout() * 100) / 107;
				result.setAmountARout(
						i == 0 ? fmDouble2P(beforeSet2) : fmDouble2P(result.getAmountARout()) + fmDouble2P(beforeSet2));
				Double sumVat2 = beans.getAmountARout() - result.getAmountARout();
				System.out.println(fmDouble2P(sumVat2));

			}
		}
		System.out.println("Test Result ::: " + result.getAmountARin());
		System.out.println("Test Result ::: " + result.getAmountARout());

	}

	public Double fmDouble2P(Double req) {
		Double result = 0.00;
		String varF = new DecimalFormat("0.00").format(req);
		result = Double.parseDouble(varF);
		return result;
	}

	static SimpleDateFormat dateFM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws ParseException {
		BounceChequeService service = new BounceChequeService();
		// service.testCal(service.searchPaySAP("","").getPayBounceChequeDTOList());
		// System.out.println(sv.sumAmount(12.00,1.00));
		// String x = service.fdDate4Find(xx);
		// boolean x = false;
		// if(x!=null){}if(!("").equals(x)){}if(x>10){}
		// if(StringUtils.equals("x","xx")) {
		// x = true;
		// }
		// System.out.println(x);
		// System.out.println(service.fmString2Date("30/06/2017"));
	}

	public Date beforeDate() throws ParseException {
		Date now = new Date();
		String date = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		date = dateFM.format(calendar.getTime());
		now = dateFM.parse(date);
		return now;
	}

	public Date afterDate() throws ParseException {
		Date now = new Date();
		String date = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		date = dateFM.format(calendar.getTime());
		now = dateFM.parse(date);
		return now;
	}

	public String fdDate4Date(Date date1, Date date2) {
		String result = "";
		SimpleDateFormat fdd = new SimpleDateFormat("YYYYMMDD", Locale.ENGLISH);
		result = fdd.format(date1) + fdd.format(date2);
		return result;
	}

	// dd/mm/yyyy-dd/mm/yyyy
	public String fdDate4Find(String date) {
		SimpleDateFormat fd = new SimpleDateFormat("ddmmyyyy");
		SimpleDateFormat fdd = new SimpleDateFormat("yyyymmdd");

		String d = date.replace("/", "");
		String[] list = d.split("-");
		String result = "";
		for (int i = 0; i < list.length; i++) {
			String dateR = list[i];
			Date dateD = new Date();
			try {
				dateD = fd.parse(dateR);
				result += fdd.format(dateD);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public String formatDateDoc(String date) {
		String dateResult = "";
		if (date.length() == 16) {
			String docDatess = date.substring(8);
			String docDates = date.substring(0, 8);
			dateResult = docDates.substring(6) + "/" + docDates.substring(4, 6) + "/" + docDates.substring(0, 4) + "-"
					+ docDatess.substring(6) + "/" + docDatess.substring(4, 6) + "/" + docDatess.substring(0, 4);
		}
		return dateResult;
	}

	public String formatDatePass(String date) {
		String passDate = date.substring(0, 2) + "/" + date.substring(3, 5) + "/" + date.substring(6);
		System.out.println(passDate);
		return passDate;
	}

	public String PaidType(String PayType) {
		String Result = "";
		if (PayType.indexOf(AppConstants.R) > -1) {
			Result = AppConstants.STATUS_R;
		} else if (PayType.equals("A")) {
			Result = AppConstants.STATUS_A;
		} else if (PayType.equals("F")) {
			Result = AppConstants.STATUS_F;
		} else if (PayType.equals("P")) {
			Result = AppConstants.STATUS_P;
		} else if (PayType.equals("AC")) {
			Result = AppConstants.STATUS_AC;
		} else if (PayType.equals("FC")) {
			Result = AppConstants.STATUS_FC;
		} else if (PayType.equals("PC")) {
			Result = AppConstants.STATUS_PC;
		}
		return Result;
	}

	public List<CustomerService> findByPropertyOneOrPropertyTwo(String serviceNo, String chk) {
		List<CustomerService> result = new ArrayList<CustomerService>();

		String append = "SELECT "
				+ " VCS.CAT_SVC_ID AS CAT_SVC_ID,VCS.CUSTOMER_ID AS CUSTOMER_ID,VCS.CAT_BILL_ACCT_ID AS CAT_BILL_ACCT_ID,VCS.CUSTOMER_NAME AS CUSTOMER_NAME,VCS.BILLING_GROUP_FULL AS BILLING_GROUP_FULL, "
				+ " VCS.CUSTOMER_NUMBER AS CUSTOMER_NUMBER,VCS.CAT_CARD_NUMBER AS CAT_CARD_NUMBER,VCS.CAT_BILL_ACCT_NUMBER AS CAT_BILL_ACCT_NUMBER,VCS.CAT_BILL_ACCT_NAME AS CAT_BILL_ACCT_NAME, "
				+ " VCS.PROPERTY_ONE AS PROPERTY_ONE,VCS.PROPERTY_TWO AS PROPERTY_TWO,VCS.START_DATE AS START_DATE,VCS.END_DATE AS END_DATE,VCS.SERVICE_TYPE AS SERVICE_TYPE, "
				+ " VCS.STATUS AS STATUS,VCS.STATUS_LKP AS STATUS_LKP,VCS.CAT_SVC_TYPE_LKP AS CAT_SVC_TYPE_LKP,VCS.BILLING_GROUP_CODE AS BILLING_GROUP_CODE,VCS.BILLING_GROUP AS BILLING_GROUP "
				+ " FROM  CRMDATA.V_CATCRM_SERVICE VCS ";

		String append1 = "";
		if ("OneTwo".equals(chk)) {
			append1 = " WHERE VCS.PROPERTY_ONE like  " + "'" + serviceNo + "'" + "OR VCS.PROPERTY_TWO like " + "'"
					+ serviceNo + "'";
		} else if ("One".equals(chk)) {
			append1 = " WHERE VCS.PROPERTY_ONE like  " + "'" + serviceNo + "'";
		} else {
			append1 = " WHERE VCS.PROPERTY_TWO like " + "'" + serviceNo + "'";
		}

		String append2 = " GROUP BY  VCS.CAT_SVC_ID ,VCS.CUSTOMER_ID ,VCS.CAT_BILL_ACCT_ID ,VCS.CUSTOMER_NAME ,VCS.BILLING_GROUP_FULL , VCS.CUSTOMER_NUMBER ,VCS.CAT_CARD_NUMBER ,VCS.CAT_BILL_ACCT_NUMBER ,VCS.CAT_BILL_ACCT_NAME , VCS.PROPERTY_ONE ,VCS.PROPERTY_TWO ,VCS.START_DATE ,VCS.END_DATE ,VCS.SERVICE_TYPE , VCS.STATUS ,VCS.STATUS_LKP ,VCS.CAT_SVC_TYPE_LKP ,VCS.BILLING_GROUP_CODE ,VCS.BILLING_GROUP";

		String sql = append + append1 + append2;// new StringBuffer();TEST431
		viewCrmJdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				CustomerService bean = new CustomerService();

				bean.setBillingGroup(row.getString("BILLING_GROUP"));//
				bean.setBillingGroupCode(row.getString("BILLING_GROUP_CODE"));//
				bean.setBillingGroupFull(row.getString("BILLING_GROUP_FULL"));//
				bean.setCatBillAcctId(row.getString("CAT_BILL_ACCT_ID"));//
				bean.setCatBillAcctName(row.getString("CAT_BILL_ACCT_NAME"));//
				bean.setCatBillAcctNo(row.getString("CAT_BILL_ACCT_NUMBER"));//
				bean.setCatCardNo(row.getString("CAT_CARD_NUMBER"));//
				bean.setCatSvcId(row.getString("CAT_SVC_ID"));//
				bean.setCatSvcTypeLkp(row.getLong("CAT_SVC_TYPE_LKP"));//
				bean.setCustomerId(row.getString("CUSTOMER_ID"));//
				bean.setCustomerName(row.getString("CUSTOMER_NAME"));//
				bean.setCustomerNo(row.getString("CUSTOMER_NUMBER"));
				bean.setPropertyOne(row.getString("PROPERTY_ONE"));//
				bean.setPropertyTwo(row.getString("PROPERTY_TWO"));//
				bean.setServiceType(row.getString("SERVICE_TYPE"));//
				bean.setStatus(row.getString("STATUS"));//
				bean.setStatusLkp(row.getLong("STATUS_LKP"));//

				result.add(bean);
			}
		});

		return result;

	}

	public BounceChequeDTO sapPaymentSummr(BounceChequeDTO bean) throws Exception {
		BounceChequeDTO result = new BounceChequeDTO();
		DetailARCustomerDTO DCus;
		List<DetailARCustomerDTO> DCusList = new ArrayList<DetailARCustomerDTO>();
		PayBounceChequeDTO PBCheque = new PayBounceChequeDTO();
		List<PayBounceChequeDTO> PBChqeueList = new ArrayList<PayBounceChequeDTO>();
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();

		boolean chk = true;
		if (chk) {

			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setGroupingSeparator(',');
			symbols.setDecimalSeparator('.');
			String pattern = "#,##0.0#";
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);
			SettlePaymentDTO settlePaymentDTO = new SettlePaymentDTO();
			Customer customer = new Customer();
			List<Customer> customerList = new ArrayList<Customer>();
			List<th.net.cat.epis.dto.SettlePaymentDTO.Invoice> invoices = new ArrayList<th.net.cat.epis.dto.SettlePaymentDTO.Invoice>();

			// settlePaymentDTO.setAdvances(advances);
			settlePaymentDTO.setAmount(
					(BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTO().getPreItemsDiscount().toString()));
			settlePaymentDTO.setCheckSpecial("");
			settlePaymentDTO.setCreditLimitData("M|");
			// settlePaymentDTO.setCreditLimitTransList(creditLimitTransList);
			// settlePaymentDTO.setDeducts(deducts);
			settlePaymentDTO.setDiscount(BigDecimal.ZERO);
			settlePaymentDTO.setGenCreditLimit("N");
			settlePaymentDTO.setLanguage("TH");
			settlePaymentDTO.setPayAmount(
					(BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTO().getPreItemsDiscount().toString()));
			// settlePaymentDTO.setPaymentCase(bean.getPayBounceChequeDTO().getPaymentCase());
			settlePaymentDTO.setReceiveAmount(
					(BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTO().getInputReceived().toString()));
			settlePaymentDTO.setRemainAmount(BigDecimal.ZERO);
			// settlePaymentDTO.setRemark(remark);
			settlePaymentDTO.setTotalPaid(
					(BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTO().getTotalCharge().toString()));
			settlePaymentDTO
					.setVatAmount((BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTO().getVat().toString()));
			settlePaymentDTO.setWtAmount(BigDecimal.ZERO);
			settlePaymentDTO.setPaymentCase(bean.getPaymentCase());

			customer.setCustName(bean.getDetailARCustomerDTO().getArName());
			customer.setCustNo(bean.getDetailARCustomerDTO().getArAccountCode());
			customer.setTaxNo(bean.getDetailARCustomerDTO().getTaxId());
			customer.setRemark(bean.getDetailARCustomerDTO().getRemark());
			customer.setAddress1(bean.getDetailARCustomerDTO().getAddress());
			customer.setCustBranch(bean.getDetailARCustomerDTO().getBranchAR());
			customer.setCollectionUnit(bean.getDetailARCustomerDTO().getRegionKey1());
			customer.setCatCustomerSegment(bean.getDetailARCustomerDTO().getArGroup());
			customer.setCustType(
					bean.getDetailARCustomerDTO().getArGroup().equals("001") ? "INDIVIDUAL" : "ORGANIZATION"); // "INDIVIDUAL"
																												// mock
			customer.setReceiptFormat(RECEIPT_FORMAT_WH_ONLY); // mock
			customer.setSouceType("SAP_AR");
			customer.setCurrencyCode(bean.getPayBounceChequeDTOList().get(0).getCurrencyCode());
			customer.setSplit(false);

			for (int i = 0; i < bean.getPayBounceChequeDTOList().size(); i++) {
				th.net.cat.epis.dto.SettlePaymentDTO.Invoice invoice = new th.net.cat.epis.dto.SettlePaymentDTO.Invoice();
				invoice.setNo(bean.getPayBounceChequeDTOList().get(i).getDocHead());
				invoice.setBillCycle(fdDate4Find(bean.getPayBounceChequeDTOList().get(i).getRefDate()));
				invoice.setAmount(bean.getPayBounceChequeDTOList().get(i).getAmount()); //
				invoice.setAmountBath((BigDecimal) decimalFormat.parse(invoice.getAmount()
						.multiply((BigDecimal) decimalFormat
								.parse(bean.getPayBounceChequeDTOList().get(i).getRateChange().toString()))
						.toString()));
				invoice.setCurrencyCode(bean.getPayBounceChequeDTOList().get(i).getCurrency());
				invoice.setCharge((BigDecimal) decimalFormat
						.parse(bean.getPayBounceChequeDTOList().get(i).getCharge().toString()));
				invoice.setWithholdingTaxNo(bean.getPayBounceChequeDTOList().get(i).getDocNo());
				invoice.setVat(
						(BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTOList().get(i).getVat().toString()));
				invoice.setVatRate((BigDecimal) decimalFormat
						.parse(bean.getPayBounceChequeDTOList().get(i).getVatRD().toString()));
				invoice.setTotalCharge((BigDecimal) decimalFormat
						.parse(bean.getPayBounceChequeDTOList().get(i).getAmountTotalPay().toString()));
				invoice.setDeduction(BigDecimal.ZERO);
				invoice.setAfterSaleDiscount(BigDecimal.ZERO);
				invoice.setBalanceDue(
						(BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTOList().get(i).getAmountTotalPay()
								.subtract(bean.getPayBounceChequeDTOList().get(i).getAmountPay()).toString()));
				invoice.setReceived((BigDecimal) decimalFormat
						.parse(bean.getPayBounceChequeDTOList().get(i).getAmountPay().toString()));
				invoice.setChange(BigDecimal.ZERO);
				invoice.setIssueDt(fmString2Date(bean.getPayBounceChequeDTOList().get(i).getPassDate()));
				invoice.setDueDt(null);
				invoice.setKenan(bean.getPayBounceChequeDTOList().get(i).getArAccountCode());
				invoice.setStatus(bean.getPayBounceChequeDTOList().get(i).getRecordStatus());
				invoice.setDiscountType("");
				invoice.setAfterSaleDiscVat(BigDecimal.ZERO);
				invoice.setDiscApprUser("");
				invoice.setTaxTypeCode("");
				invoice.setDiscount(BigDecimal.ZERO);
				invoice.setStatus("N"); // ???
				invoice.setCurrencyCode(bean.getPayBounceChequeDTOList().get(i).getCurrencyCode());
				invoice.setPaymentCase(bean.getPayBounceChequeDTOList().get(i).getPaymentCase()); // ???
				invoice.setServiceName(bean.getPayBounceChequeDTOList().get(i).getServiceKey3()); // foreignExchangeRate
				invoice.setForeignExchangeRate((BigDecimal) decimalFormat.parse(bean.getPayBounceChequeDTO().getForeignExchangeRateBath().toString()));
				invoice.setDocNo(bean.getPayBounceChequeDTOList().get(i).getDocNo());
				invoice.setYear(bean.getPayBounceChequeDTOList().get(i).getAccountYear());
				invoice.setMessage(bean.getPayBounceChequeDTOList().get(i).getMessage());
				invoices.add(invoice);
			}
			List<th.net.cat.epis.dto.SettlePaymentDTO.Method> methods = new ArrayList<th.net.cat.epis.dto.SettlePaymentDTO.Method>();
			for (int i = 0; i < bean.getMethods().size(); i++) {
				th.net.cat.epis.dto.SettlePaymentDTO.Method method = new th.net.cat.epis.dto.SettlePaymentDTO.Method();
				method.setType(bean.getMethods().get(i).getType());
				method.setCode(bean.getMethods().get(i).getCode());
				method.setName(bean.getMethods().get(i).getName());
				method.setChequeNo(bean.getMethods().get(i).getChequeNo());
				method.setAmount(bean.getMethods().get(i).getAmount());
				method.setPayChqBankCode(bean.getMethods().get(i).getPayChqBankCode());
				method.setPayChqBankName(bean.getMethods().get(i).getPayChqBankName());
				method.setPayChqBranch(bean.getMethods().get(i).getPayChqBranch());
				method.setPayChqDate(bean.getMethods().get(i).getPayChqDate());
				method.setCardType(bean.getMethods().get(i).getCardType());
				method.setCardNo(bean.getMethods().get(i).getCardNo());
				method.setBankName(bean.getMethods().get(i).getBankName());
				method.setBankAcct(bean.getMethods().get(i).getBankAcct());
				method.setTransferDt(bean.getMethods().get(i).getTransferDt());
				method.setIsBackDt(bean.getMethods().get(i).getIsBackDt());

				methods.add(method);
			}

			customer.setInvoices(invoices);
			customerList.add(customer);

			settlePaymentDTO.setCustomers(customerList);
			settlePaymentDTO.setMethods(methods);

			// creditLimitTransList
			dto = creatOtbossJson(settlePaymentDTO);
			dto.getData().get(0).getNo(); // à¹€à¸¥à¸‚à¸—à¸µà¹ˆ :
											// EPO170402F1710310004
			dto.getData().get(0).getBranchName(); // à¸ªà¸²à¸‚à¸²à¸—à¸µà¹ˆà¸­à¸­à¸�à¹ƒà¸šà¸�à¸³à¸�à¸±à¸šà¸ à¸²à¸©à¸µ
													// à¸„à¸·à¸­ : à¸¨à¸šà¸¥.
													// à¸™à¸™à¸—à¸šà¸¸à¸£à¸µ
			dto.getData().get(0).getUpdateDttm(); // à¸§à¸±à¸™à¸—à¸µà¹ˆ :
													// 2017-10-31

			for (int i = 0; i < dto.getData().size(); i++) {
				Receipt rt = new Receipt();
				DCus = new DetailARCustomerDTO();
				PBCheque = new PayBounceChequeDTO();
				rt = dto.getData().get(i);

				DCus.setAddress(rt.getAddrLine1());
				DCus.setArAccountCode(rt.getAccountNo());
				DCus.setArGroup("");
				DCus.setArName(rt.getAccountName());
				DCus.setBranchAR(rt.getAccountBranch());
				DCus.setRegionKey1("");
				DCus.setGlAccount("");

				DCus.setReportNo(rt.getNo());
				DCus.setBranchName(rt.getBranchName());
				DCus.setBranchCode(rt.getBranchCode());
				DCus.setUpDDate(rt.getCustomer().getUpdateDttm());

				PBCheque = bean.getPayBounceChequeDTOList().get(i);
				PBCheque.setBalanceDueDB(dto.getData().get(i).getBalanceDue());

				DCusList.add(DCus);
				PBChqeueList.add(PBCheque);

				result.setPayBounceChequeDTO(bean.getPayBounceChequeDTO());
			}

			result.setDetailARCustomerDTOList(DCusList);
			result.setPayBounceChequeDTOList(PBChqeueList);

		}
		return result;
	}

	public BigDecimal sumAmount(Double amount, Double vat) {
		// BigDecimal result = BigDecimal.ZERO;
		Double resultDB = amount + vat;
		BigDecimal result = new BigDecimal(resultDB);
		return result;
	}

	public Date fmString2Date(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateResult = new Date();
		try {
			dateResult = formatter.parse(date + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateResult;
	}

	public CreatePaymentResultDTO creatOtbossJson(SettlePaymentDTO paymentDTO) throws Exception {
		List<Receipt> receipts = null;

		for (int i = 0; i < paymentDTO.getCustomers().size(); i++) {
			receipts = otbossservice.createPaymentOTBOSS(paymentDTO);
		}

		try {
			otbossservice.creatPayment(paymentDTO, receipts);
		} catch (Exception e) {
			e.printStackTrace();
		}

		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		// erpService.payService(receipts, "0000");

		// check Advance CreditLimit
		List<SettlePaymentDTO.Advanced> advances = paymentDTO.getAdvances();
		boolean isGenCreditLimit = false;
		List<CreditLimitTrans> creditLimitTransList = null;
		// List<SettlePaymentDTO.Advanced> advances = null;
		if (paymentDTO.getGenCreditLimit() != null && paymentDTO.getGenCreditLimit().equals("Y")) {
			isGenCreditLimit = true;
			creditLimitTransList = paymentDTO.getCreditLimitTransList();
			List<CreditLimitTrans> advancedCreditLimit = new ArrayList<CreditLimitTrans>();
			if (creditLimitTransList != null && creditLimitTransList.size() > 0) {
				if (advances != null && advances.size() > 0) {
					for (SettlePaymentDTO.Advanced advance : advances) {
						if (advance.getInvoiceNo() != null
								&& !advance.getInvoiceNo().equals(AppConstants.ADVANCE_PAYMENT)) {
							int creditLimitSize = creditLimitTransList.size();
							for (int i = 0; i < creditLimitSize; i++) {
								CreditLimitTrans credit = creditLimitTransList.get(i);
								if (credit.getContract().equals(advance.getCustNo())) {
									CreditLimitTrans creditLimitTrans = new CreditLimitTrans();
									if (credit.getMsisdnList().size() > 1) {// multi
										if (credit.getMsisdn() != null && !credit.getMsisdn().equals("0")) {
											creditLimitTrans.setMsisdn(credit.getMsisdn());
											creditLimitTrans.setPayType("M");
										} else {
											creditLimitTrans.setPayType("A");
											creditLimitTrans.setMsisdn("");
										}
									} else {
										creditLimitTrans.setMsisdn(credit.getMsisdn());
										creditLimitTrans.setPayType("A");
									}
									creditLimitTrans.setContract(advance.getCustNo());
									creditLimitTrans.setArRef(AppConstants.ADVANCE_PAYMENT);
									creditLimitTrans.setAccountNo(advance.getKenan());
									advancedCreditLimit.add(creditLimitTrans);

								}
							}
						}
					}
				}
				if (advancedCreditLimit.size() > 0) {
					creditLimitTransList.addAll(advancedCreditLimit);
				}

			}

		}
		// <!-- Updating: User Session. -->
		Session session = userService.getSession();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) {
				for (Service service : invoice.getServices()) {
					for (Transaction transaction : service.getTransactions()) {
						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
						logger.info("paymentSummary[" + paymentSummary + "]");
						paymentSummary.setBalance(paymentSummary.getBalance().add(transaction.getAmount()));
					}
				}
				// Credit Limit.
				if (isGenCreditLimit) {
					Date now = new Date();
					// formatter_yyyyMMdd formatter_EN_TIME
					String postDate = AppUtil.formatter_yyyyMMdd.format(now) + " "
							+ AppUtil.formatter_EN_TIME.format(now);
					String payDate = AppUtil.formatter_yyyyMMdd.format(now);

					if (creditLimitTransList != null && creditLimitTransList.size() > 0) {
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						for (CreditLimitTrans creditLimitTrans : creditLimitTransList) {
							CreditLimitTransEntity creditLimitTransEntity = new CreditLimitTransEntity();
							String payType = creditLimitTrans.getPayType();
							String msisdn = "";
							int msisdnSize = 0;
							if (creditLimitTrans.getMsisdnSize() != null
									&& creditLimitTrans.getMsisdnSize().length() > 0)
								msisdnSize = Integer.valueOf(creditLimitTrans.getMsisdnSize());
							if (creditLimitTrans.getMsisdn() != null && creditLimitTrans.getMsisdn().length() > 0) {
								msisdn = creditLimitTrans.getMsisdn();
							}

							// String payDate = creditLimitTrans.getPayDate();

							String amountIncVat = creditLimitTrans.getAmountIncVat();
							String received = creditLimitTrans.getReceived();
							if (amountIncVat != null && amountIncVat.length() > 0 && received != null
									&& received.length() > 0) {
								double amountIncVatD = Double.valueOf(amountIncVat);
								double receivedD = Double.valueOf(received);
								if (receivedD >= amountIncVatD && (creditLimitTrans.getArRef() != null
										&& !creditLimitTrans.getArRef().equals("-")))
									payType = "F";
								else if (receivedD < amountIncVatD)
									payType = "P";
								else
									payType = "A";
							}
							String arInvdate = "";
							if (creditLimitTrans.getArInvdate() != null
									&& creditLimitTrans.getArInvdate().length() > 0) {// 22/09/2016
								String[] arInvDateArray = creditLimitTrans.getArInvdate().split("/");
								if (arInvDateArray.length == 3) {
									arInvdate = arInvDateArray[2] + arInvDateArray[1] + arInvDateArray[0];
								}
							}
							String arDuedate = "";
							if (creditLimitTrans.getArDuedate() != null
									&& creditLimitTrans.getArDuedate().length() > 0) {// 22/09/2016
								String[] arDuedateArray = creditLimitTrans.getArDuedate().split("/");
								if (arDuedateArray.length == 3) {
									arDuedate = arDuedateArray[2] + arDuedateArray[1] + arDuedateArray[0];
								}
							}
							if (payType.equals("A")) {
								creditLimitTransEntity.setArInvdate(payDate);
								creditLimitTransEntity.setArDuedate(payDate);
							} else {
								creditLimitTransEntity.setArInvdate(arInvdate);
								creditLimitTransEntity.setArDuedate(arDuedate);
							}
							if ((creditLimitTrans.getArRef() != null && !creditLimitTrans.getArRef().equals("-"))) {
								Invoice inv = invoiceRepo.findByReceiptIdAndNo(receipt.getId(),
										creditLimitTrans.getArRef());

								if (inv != null) {
									if (inv.getCharge() != null)
										creditLimitTransEntity.setAmountExVat(String.valueOf(inv.getCharge()));
									if (inv.getVat() != null)
										creditLimitTransEntity.setVatAmount(String.valueOf(inv.getVat()));
									if (inv.getReceived() != null)
										creditLimitTransEntity.setAmountIncVat(String.valueOf(inv.getReceived()));
								}
							} else {
								Invoice inv = invoiceRepo.findByReceiptIdAndNo(receipt.getId(), ADVANCE_PAYMENT);

								if (inv != null) {
									if (inv.getCharge() != null)
										creditLimitTransEntity.setAmountExVat(String.valueOf(inv.getCharge()));
									if (inv.getVat() != null)
										creditLimitTransEntity.setVatAmount(String.valueOf(inv.getVat()));
									if (inv.getReceived() != null)
										creditLimitTransEntity.setAmountIncVat(String.valueOf(inv.getReceived()));
								}
							}
							if (msisdnSize > 1 && !msisdn.equals("0"))
								payType = "M";

							creditLimitTransEntity.setContract(creditLimitTrans.getContract());
							creditLimitTransEntity.setReceiptId(String.valueOf(receipt.getId()));
							creditLimitTransEntity.setArRef(creditLimitTrans.getArRef());
							creditLimitTransEntity.setPayType(payType);
							creditLimitTransEntity.setPayDate(payDate);

							if (msisdn.equals("0"))
								msisdn = "";

							creditLimitTransEntity.setMsisdn(msisdn);

							creditLimitTransEntity.setPostDate(postDate);
							creditLimitTransEntity.setAccountNo(creditLimitTrans.getAccountNo());

							creditLimitTransEntity.setStatus("N");
							creditLimitTransEntity.setUpdatedTime(timestamp);

							if (creditLimitTransEntity.getVatAmount() != null
									&& creditLimitTransEntity.getAmountExVat() != null)
								creditLimitTransRepository.save(creditLimitTransEntity);
						}
					}

				}
			}
			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
		}
		for (SettlePaymentDTO.Method method : paymentDTO.getMethods()) {
			if (method.getType() != null) {
				PaymentSummary paymentSummary = session.getPayType(method.getType());
				paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
			}
		}

		userService.saveSession(session);
		paymentService.callF4CreatePayment(receipts);
		// Case use coupon will update data to insale
		AppUtil.updateCoupon(paymentDTO.getMethods());

		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	public BigDecimal subVat(BigDecimal amount, BigDecimal vat) {
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		result = (amount.multiply(hundred));
		return result.divide(vat.add(hundred), 2, RoundingMode.HALF_UP);
	}
	
	public Double changeOut4In(BigDecimal amount, BigDecimal vat){
		return amount.multiply(vat).setScale(2,  RoundingMode.HALF_UP).doubleValue();
	}

}

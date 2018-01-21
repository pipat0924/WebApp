package th.net.cat.epis.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.ReportPayment;
import th.net.cat.epis.dto.ReportPaymentDTO;
import th.net.cat.epis.dto.ReportPaymentSummary;
import th.net.cat.epis.dto.ReportPaymentSummaryDTO;
import th.net.cat.epis.dto.ReportSalesTax;
import th.net.cat.epis.dto.ReportSalesTaxDTO;
import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.entity.Payment;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.PaymentRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.util.AppUtil;

@org.springframework.stereotype.Service
public class ReportService {

	@Autowired PaymentRepository paymentRepo;
	@Autowired ReceiptRepository receiptRepo;
	@Autowired OfficerRepository officerRepo;
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;

	@Transactional
	public ReportPaymentSummaryDTO getPaymentsByUserCode(String userCode, Date searchDateFrom, Date searchDateTo) {
		Officer officer = ((List<Officer>) officerRepo.findByCode(userCode)).get(0);
		ReportPaymentSummaryDTO dto = new ReportPaymentSummaryDTO();
		findPaymentsSummaryByUserCode(dto, officer, searchDateFrom, searchDateTo);
		return dto;
	}
	@Transactional
	public ReportPaymentSummaryDTO getAllPayments(Date searchDateFrom, Date searchDateTo) {
		List<Officer> officerList = (ArrayList<Officer>) officerRepo.findAll();
		ReportPaymentSummaryDTO dto = new ReportPaymentSummaryDTO();
		for(Officer officer : officerList) {
			findPaymentsSummaryByUserCode(dto, officer, searchDateFrom, searchDateTo);
		}
		return dto;
	}
	@Transactional
	private void findPaymentsSummaryByUserCode(ReportPaymentSummaryDTO dto, Officer officer, Date searchDateFrom, Date searchDateTo) {
		List<Payment> paymentList = paymentRepo.findByOfficerIdAndUpdateDttmBetween(officer.getId(), searchDateFrom, searchDateTo);
		if(AppUtil.isCollectionHasValue(paymentList)) {
			ReportPaymentSummary report = new ReportPaymentSummary();
			BigDecimal summaryAmount = BigDecimal.ZERO;
			for(Payment payment : paymentList) {
				summaryAmount = summaryAmount.add(payment.getReceived());
				summaryAmount = summaryAmount.subtract(payment.getChange());
			}
			report.setSummaryAmount(summaryAmount);
			report.setReceiptCount(paymentList.size());
			report.setUserCode(officer.getCode());
			report.setUserName(officer.getGivenName());
			report.setUserFamilyname(officer.getFamilyName());
			dto.addData(report);
		}
	}
	
	@Transactional
	public ReportSalesTaxDTO getReceiptsByUserCode(String userCode, Date searchDateFrom, Date searchDateTo, String receiptType) {
		//Officer officer = ((List<Officer>) officerRepo.findByCode(userCode)).get(0);
		Officer officer =  officerRepo.findByCode(userCode);
		ReportSalesTaxDTO dto = new ReportSalesTaxDTO();
		findReceiptsByOfficerId(dto, officer, searchDateFrom, searchDateTo, receiptType);
		return dto; 
	}
	@Transactional
	public ReportSalesTaxDTO getAllReceipts(Date searchDateFrom, Date searchDateTo, String receiptType) {
		List<Officer> officerList = (ArrayList<Officer>) officerRepo.findAll();
		ReportSalesTaxDTO dto = new ReportSalesTaxDTO();
		for(Officer officer : officerList) {
			findReceiptsByOfficerId(dto, officer, searchDateFrom, searchDateTo, receiptType);
		}
		return dto;
	}
	@Transactional
	private void findReceiptsByOfficerId(ReportSalesTaxDTO dto, Officer officer, Date searchDateFrom, Date searchDateTo, String receiptType) {
		List<Receipt> receiptList = receiptRepo.findByPayment(officer.getId(), searchDateFrom, searchDateTo, receiptType);
		for(Receipt receipt : receiptList) {
			ReportSalesTax report = new ReportSalesTax();
			report.setReceiptDate(receipt.getUpdateDttm());
			report.setReceiptNo(receipt.getNo());
			report.setReceiptName(receipt.getName());
			report.setTaxId(receipt.getTaxNo());
			report.setBranchNo(receipt.getAccountSubNo());
			report.setProductCost(receipt.getAmount());
			report.setVat(receipt.getVat());
			report.setTotalAmount(receipt.getTotalCharge());
			report.setOfficialId(officer.getId());
			report.setOfficialCode(officer.getCode());
			dto.addData(report);
		}
	}
	
	@Transactional
	public String findPosNameGetByPosID(Long posid) {
		String posnoresule = null; 
		if(posid > 0) {	
		Map<String, Object> posno = episJdbcTemplate.queryForMap("Select POSNAME FROM MASPOS WHERE POSID="+posid);
		if(posno != null) {
			posnoresule = posno.get("POSNAME").toString();
			}	
		}
	return posnoresule;	
	}
	
	
	@Transactional
	public ReportPaymentDTO getDiscountDailyCreditReportService(HttpServletRequest request, 
			String searchDate,String searchDateTo,String searchPeriod,String searchShop,
			String searchOfficer) throws Exception {
		
		StringBuilder whereBuilder = new StringBuilder();
		
		if(!StringUtil.isBlank(searchDate)&&!StringUtil.isBlank(searchDateTo)) {
			whereBuilder.append(" AND cr.receiptdttm BETWEEN TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY HH24:MI') AND TO_TIMESTAMP('"+searchDateTo+"', 'DD-MM-YYYY HH24:MI')");
		}
		
		if(!"all".equals(searchOfficer)) {
			whereBuilder.append(" AND cp.updateuser = '" + searchOfficer +"'");
		}else{
			List<String> username = EpContextHolder.getContext().getOwners();
			StringBuilder user = new StringBuilder();
			int i = 0;
			while (i < username.size()) {
				user.append("'" + username.get(i) + "'");
				if(i == (username.size() -1)) {
					break;
				}else {
					user.append(",");
				}
				i++;			
			}
			whereBuilder.append(" AND cp.updateuser IN (" + user +")");
		}
		
		final ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select CR.RECEIPTNO , CR.ACCOUNTNO , CR.RECEIPTNAME,  ti.invoiceno , TI.TOTALCHARGE, ti.DISCOUNT ,  ti.AFTERSALEDISCOUNT ,ti.vatrate, ti.vat,CR.ATTRIBUTES ,cr.updateuser,cp.paymenttype,sap.TB_CODETEXT as collectionunit");
		queryBuilder.append(" from tmpinvoice ti");
		queryBuilder.append(" inner join corpayment cp on TI.PAYMENTID = cp.PAYMENTID ");
		queryBuilder.append(" inner join correceipt cr on TI.PAYMENTID = cr.PAYMENTID  ");
		queryBuilder.append(" LEFT JOIN SAP_REVENUE_DEPT@CATPCU1_DEV sap ON substr(cp.collectionunit,1,1)||'000' = sap.TB_REGION_CODE ");
		queryBuilder.append(" LEFT JOIN masshop ms ON ms.shopid = cp.shopid ");
		queryBuilder.append(" WHERE cp.paymenttype in ('SERVICE_CHARGE', 'TBOSS','OTBOSS') ");
		queryBuilder.append( whereBuilder.toString() );
		
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				ReportPayment reportPayment = new ReportPayment();
				reportPayment.setReceiptNo(row.getString(1));
				reportPayment.setAccountNo(row.getString(2));				
				reportPayment.setReceiptName(row.getString(3));
				reportPayment.setInvoiceNo(row.getString(4));
				reportPayment.setTotalCharge(row.getString(5));
				reportPayment.setDiscount(row.getString(6));
				reportPayment.setAfter_sale_discount(row.getString(7));
				reportPayment.setVatRate(row.getString(8));
				reportPayment.setVat(row.getString(9));
				reportPayment.setAttributes(row.getString(10));
				reportPayment.setUpdateDate(row.getString(11));
				reportPayment.setPaymentType(row.getString(12));
				reportPayment.setCollectionUnit(row.getString(13));
				reportPaymentDTO.addData(reportPayment);
			}
		});
		return reportPaymentDTO;
	}
}
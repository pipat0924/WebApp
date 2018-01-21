package th.net.cat.epis.service;

import static com.google.common.collect.Iterables.*;
import static org.apache.commons.lang.StringUtils.*;
import static org.apache.commons.lang.math.NumberUtils.*;
import static th.net.cat.epis.util.AppConstants.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Predicate;

import th.net.cat.epis.entity.Deduct;
import th.net.cat.epis.entity.Invoice;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.Service;
import th.net.cat.epis.entity.Transaction;
import th.net.cat.erp.entity.PayService;
import th.net.cat.erp.repo.PayNoServiceRepository;
import th.net.cat.erp.repo.PayServiceRepository;

@org.springframework.stereotype.Service
public class ErpService {

	@Autowired PayServiceRepository payServiceRepo;
	@Autowired PayNoServiceRepository payNoServiceRepo;

	public static class RecordInfo {
		String account;
		String amountTrasaction;
		String amountLocal;
		String taxBaseTransaction;
		String taxBaseLocal;
		String taxCode;
		String businessPlace;
		String businessArea;
		String valueDate;
		String segment;
		String product;
		String chequeNo;
		String text;
		String ref1;
		String tradingPartner;
	}

	@Transactional
	public void payService(List<Receipt> receipts, final String branchCode) {
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
		Date currentDate = new Date();
		String currentyyyyMMdd = yyyyMMdd.format(currentDate);
		List<PayService> debitCashDeposits = new ArrayList<PayService>();
		List<PayService> creditTBOSSProducts = new ArrayList<PayService>();
		List<PayService> debitDeductionTaxs = new ArrayList<PayService>();
		List<PayService> debitSuspenseTaxs = new ArrayList<PayService>();
		List<PayService> creditSalesTaxs = new ArrayList<PayService>();
		List<RecordInfo> payServices = new ArrayList<RecordInfo>();
		List<RecordInfo> prodDebtors = new ArrayList<RecordInfo>();
		List<RecordInfo> deductTaxs = new ArrayList<RecordInfo>();
		List<RecordInfo> outputTaxs = new ArrayList<RecordInfo>();
		List<RecordInfo> salesTaxs = new ArrayList<RecordInfo>();
		for (Receipt receipt : receipts) {
			for (Invoice invoice : receipt.getInvoices()) { // Deduction
				RecordInfo deductTax = findByAccount(deductTaxs, ERP_ACCOUNT_NO_DEBIT_TAX_DEDUCTION);
				deductTax.amountTrasaction = invoice.getDeduction() == null ? "0" : invoice.getDeduction().toPlainString();
				deductTax.amountLocal = invoice.getDeduction() == null ? "0" : invoice.getDeduction().toPlainString();
				deductTax.taxBaseTransaction = "0";
				deductTax.taxBaseLocal = "0";
			}
			for (Deduct deduct : receipt.getPayment().getDeducts()) { // Deduction
				RecordInfo deductTax = findByAccount(deductTaxs, ERP_ACCOUNT_NO_DEBIT_TAX_DEDUCTION);
				deductTax.amountTrasaction = deduct.getAmount() == null ? "0" : deduct.getAmount().toPlainString();
				deductTax.amountLocal = deduct.getAmount() == null ? "0" : deduct.getAmount().toPlainString();
				deductTax.taxBaseTransaction = "0";
				deductTax.taxBaseLocal = "0";
			}
			for (Invoice invoice : receipt.getInvoices()) {
				for (final Service service : invoice.getServices()) {
					for (final Transaction transaction : service.getTransactions()) {
						RecordInfo payService = new RecordInfo(); payServices.add(payService);
						payService.account = isNotBlank(transaction.getAccountNo()) ? transaction.getAccountNo() : (isBlank(transaction.getChequeNo()) ? ERP_ACCOUNT_NO_DEBIT_CASH : "");
						payService.amountTrasaction = transaction.getAmount() == null ? "0" : transaction.getAmount().toPlainString();
						payService.amountLocal = transaction.getAmount() == null ? "0" : transaction.getAmount().toPlainString();
						payService.taxBaseTransaction = "0";
						payService.taxBaseLocal = "0";
						payService.chequeNo = isBlank(transaction.getChequeNo()) ? "" : transaction.getChequeNo();
						RecordInfo prodDebtor = findByAccountAndProduct(prodDebtors, ERP_ACCOUNT_NO_CREDIT_TBOSS, service.getProductCode());
						prodDebtor.amountTrasaction = transaction.getAmount() == null ? "0" : transaction.getAmount().toPlainString();
						prodDebtor.amountLocal = transaction.getAmount() == null ? "0" : transaction.getAmount().toPlainString();
						prodDebtor.taxBaseTransaction = "0";
						prodDebtor.taxBaseLocal = "0";
						prodDebtor.segment = "";
						prodDebtor.product = service.getProductCode();
						prodDebtor.text = "PM:"+ currentyyyyMMdd;
						prodDebtor.ref1 = "1J30200"; // Collection Unit
					}
					RecordInfo outputTax = findByAccountAndProduct(outputTaxs, ERP_ACCOUNT_NO_DEBIT_SUSPENSE_OUTPUT_TAX, service.getProductCode());
					outputTax.amountTrasaction = invoice.getVat() == null ? "0" : invoice.getVat().toPlainString();
					outputTax.amountLocal = invoice.getVat() == null ? "0" : invoice.getVat().toPlainString();
					outputTax.taxBaseTransaction = invoice.getAmount() == null ? "0" : invoice.getAmount().toPlainString();
					outputTax.taxBaseLocal = invoice.getAmount() == null ? "0" : invoice.getAmount().toPlainString();
					outputTax.segment = "";
					outputTax.product = service.getProductCode();
					outputTax.text = "PM:"+ currentyyyyMMdd;
					outputTax.ref1 = "1J30200"; // Collection Unit
					RecordInfo salesTax = findByAccountAndProduct(salesTaxs, ERP_ACCOUNT_NO_CREDIT_SALES_TAX, service.getProductCode());
					salesTax.amountTrasaction = invoice.getVat() == null ? "0" : invoice.getVat().toPlainString();
					salesTax.amountLocal = invoice.getVat() == null ? "0" : invoice.getVat().toPlainString();
					salesTax.taxBaseTransaction = invoice.getAmount() == null ? "0" : invoice.getAmount().toPlainString();
					salesTax.taxBaseLocal = invoice.getAmount() == null ? "0" : invoice.getAmount().toPlainString();
					salesTax.segment = "";
					salesTax.product = service.getProductCode();
					salesTax.text = "PM:"+ currentyyyyMMdd;
					salesTax.ref1 = "1J30200"; // Collection Unit
				}
			}
			
		}
		for (RecordInfo recordInfo : payServices) { // By Account
			String accountNo = leftPad(stripToEmpty(recordInfo.account), 10, isBlank(recordInfo.account) ? " " : "0");
			PayService debitCashDeposit = isNotBlank(accountNo) ? findByDateAndBranchAndAccount(debitCashDeposits, currentyyyyMMdd, branchCode, accountNo) : createPayService("001");
			debitCashDeposit.setUpdateDttm(currentDate);
			debitCashDeposit.setRecordSequence("001");
			debitCashDeposit.setDocumentDate(currentyyyyMMdd);
			debitCashDeposit.setDocumentType("RV");
			debitCashDeposit.setCompanyCode("1000");
			debitCashDeposit.setPostingDate(currentyyyyMMdd);
			debitCashDeposit.setFiscalPeriod(substring(currentyyyyMMdd, 4, 6));
			debitCashDeposit.setCurrency("THB");
			debitCashDeposit.setExchangeRate(leftPad("1", 11, " "));
			debitCashDeposit.setTranslationDate(currentyyyyMMdd);
			debitCashDeposit.setReference(rightPad(substring("HeadQuater", 0, 16), 16, " "));
			debitCashDeposit.setDocumentHeaderText(rightPad("Payment-EPIS", 25, " "));
			debitCashDeposit.setBranch(leftPad("0", 4, "0"));
			debitCashDeposit.setBusinessTransaction("RFBU");
			debitCashDeposit.setFiscalYear(substring(currentyyyyMMdd, 0, 4));
			debitCashDeposit.setUserName(rightPad("username", 12, " "));
			debitCashDeposit.setReferenceProcedure(rightPad("", 5, " "));
			debitCashDeposit.setReferenceDocumentNo(rightPad("", 10, " "));
//			debitCashDeposit.setNumberOfLineItem1("001");
			debitCashDeposit.setDebitCredit(ERP_ACCOUNT_DEBIT);
			debitCashDeposit.setPostingKey("40");
			debitCashDeposit.setAccountType(ERP_ACCOUNT_TYPE_GENERAL_LEDGER);
			debitCashDeposit.setAccount(accountNo);
			debitCashDeposit.setAlternativeReconcileAccount(leftPad("", 10, " "));
			debitCashDeposit.setAmountTransaction(rightPad(BigDecimal.valueOf(toDouble(debitCashDeposit.getAmountTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountTrasaction), "0"))).toPlainString(), 13, " "));
			debitCashDeposit.setAmountLocal(rightPad(BigDecimal.valueOf(toDouble(debitCashDeposit.getAmountLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountLocal), "0"))).toPlainString(), 13, " "));
			debitCashDeposit.setTaxBaseTransaction(rightPad(BigDecimal.valueOf(toDouble(debitCashDeposit.getTaxBaseTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseTransaction), "0"))).toPlainString(), 13, " "));
			debitCashDeposit.setTaxBaseLocal(rightPad(BigDecimal.valueOf(toDouble(debitCashDeposit.getTaxBaseLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseLocal), "0"))).toPlainString(), 13, " "));
			debitCashDeposit.setTaxCode("  ");
			debitCashDeposit.setBusinessPlace(rightPad("", 4, " "));
			debitCashDeposit.setBusinessArea("1717");
			debitCashDeposit.setValueDate(currentyyyyMMdd);
			debitCashDeposit.setPaymentTerm(rightPad("", 4, " "));
			debitCashDeposit.setBaselineDate(rightPad("", 8, " "));
			debitCashDeposit.setAmountEligibleForCashDiscountInDocumentCurrency(rightPad("", 13, " "));
			debitCashDeposit.setCostCenter(rightPad("", 10, " "));
			debitCashDeposit.setFunctionalArea(rightPad("", 16, " "));
			debitCashDeposit.setWbs(rightPad("", 24, " "));
			debitCashDeposit.setActivityType(rightPad("", 6, " "));
			debitCashDeposit.setSegment(leftPad("", 10, " "));
			debitCashDeposit.setProduct(leftPad("", 18, " "));
			debitCashDeposit.setSubProduct(rightPad("", 2, " "));
			debitCashDeposit.setRevenueType(rightPad("", 10, " "));
			debitCashDeposit.setMaterial(rightPad("", 18, " "));
			debitCashDeposit.setPlant(rightPad("", 4, " "));
			debitCashDeposit.setBusinessProcess(rightPad("", 12, " "));
			debitCashDeposit.setCustomerGroup(rightPad("", 10, " "));
			debitCashDeposit.setPaymentReference(rightPad("", 30, " "));
			debitCashDeposit.setAssignment(rightPad(stripToEmpty(recordInfo.chequeNo), 18, " "));
			debitCashDeposit.setText(rightPad("", 50, " "));
			debitCashDeposit.setRef1(rightPad("", 12, " "));
			debitCashDeposit.setRef2(leftPad("", 12, " "));
			debitCashDeposit.setRef3(rightPad("", 20, " "));
			debitCashDeposit.setTradingPartner(rightPad("ZZZZ", 6, " "));
//			debitCashDeposit.setNumberOfLineItem2("001");
			debitCashDeposit.setTitle(rightPad("", 15, " "));
			debitCashDeposit.setName1(rightPad("", 35, " "));
			debitCashDeposit.setName2(rightPad("", 35, " "));
			debitCashDeposit.setName3(rightPad("", 35, " "));
			debitCashDeposit.setName4(rightPad("", 35, " "));
			debitCashDeposit.setHouseNoAndStreet(rightPad("", 35, " "));
			debitCashDeposit.setCity(rightPad("", 35, " "));
			debitCashDeposit.setPostalCode(rightPad("", 10, " "));
			debitCashDeposit.setCountryKey(rightPad("", 3, " "));
			debitCashDeposit.setTaxNo1(rightPad("", 16, " "));
			debitCashDeposit.setTaxNo2(rightPad("", 11, " "));
			debitCashDeposit.setPayeeCode(rightPad("", 16, " "));
			debitCashDeposit.setBankKey(rightPad("", 15, " "));
			debitCashDeposit.setBankAccount(rightPad("", 18, " "));
			debitCashDeposit.setBankCountry(rightPad("", 3, " "));
//			debitCashDeposit.setNumberOfLineItem3("001");
			debitCashDeposit.setWithholdingTaxType1(rightPad("", 2, " "));
			debitCashDeposit.setWithholdingTaxCode1(rightPad("", 2, " "));
			debitCashDeposit.setWithholdingTaxBaseDoc1(rightPad("", 13, " "));
			debitCashDeposit.setWithholdingTaxBaseLocal1(rightPad("", 13, " "));
			debitCashDeposit.setWithholdingTaxAmtDoc1(rightPad("", 13, " "));
			debitCashDeposit.setWithholdingTaxAmtLocal1(rightPad("", 13, " "));
			debitCashDeposit.setWithholdingTaxType2(rightPad("", 2, " "));
			debitCashDeposit.setWithholdingTaxCode2(rightPad("", 2, " "));
			debitCashDeposit.setWithholdingTaxBaseDoc2(rightPad("", 13, " "));
			debitCashDeposit.setWithholdingTaxBaseLocal2(rightPad("", 13, " "));
			debitCashDeposit.setWithholdingTaxAmtDoc2(rightPad("", 13, " "));
			debitCashDeposit.setWithholdingTaxAmtLocal2(rightPad("", 13, " "));
//			debitCashDeposit.setNumberOfLineItem4("001");
			debitCashDeposit.setTaxCode(rightPad("", 2, " "));
			debitCashDeposit.setGeneralLedgerAccount(rightPad("", 10, " "));
			debitCashDeposit.setDebitCreditIndicator(rightPad("", 1, " "));
			debitCashDeposit.setLocalTaxBaseAmount(rightPad("", 15, " "));
			debitCashDeposit.setTaxBaseAmount(rightPad("", 15, " "));
			debitCashDeposit.setLocalTaxAmount(rightPad("", 13, " "));
			debitCashDeposit.setTaxAmount(rightPad("", 13, " "));
			debitCashDeposit.setTransactionKey(rightPad("", 3, " "));
			debitCashDeposit.setConditionType(rightPad("", 4, " "));
			debitCashDeposit.setBusinessPlace2(debitCashDeposit.getBranch());
//			debitCashDeposit.setCarrierOperator(rightPad("", 4, " "));
		}
		for (RecordInfo recordInfo : prodDebtors) { // By Product
			String accountNo = leftPad(ERP_ACCOUNT_NO_CREDIT_TBOSS, 10, "0");
			PayService creditTBOSSProduct = findByDateAndBranchAndAccountAndProduct(creditTBOSSProducts, currentyyyyMMdd, branchCode, accountNo, recordInfo.product);
			creditTBOSSProduct.setUpdateDttm(currentDate);
			creditTBOSSProduct.setRecordSequence("001");
			creditTBOSSProduct.setDocumentDate(currentyyyyMMdd);
			creditTBOSSProduct.setDocumentType("RV");
			creditTBOSSProduct.setCompanyCode("1000");
			creditTBOSSProduct.setPostingDate(currentyyyyMMdd);
			creditTBOSSProduct.setFiscalPeriod(substring(currentyyyyMMdd, 4, 6));
			creditTBOSSProduct.setCurrency("THB");
			creditTBOSSProduct.setExchangeRate(leftPad("1", 11, " "));
			creditTBOSSProduct.setTranslationDate(currentyyyyMMdd);
			creditTBOSSProduct.setReference(rightPad(substring("HeadQuater", 0, 16), 16, " "));
			creditTBOSSProduct.setDocumentHeaderText(rightPad("Payment-EPIS", 25, " "));
			creditTBOSSProduct.setBranch(leftPad("0", 4, "0"));
			creditTBOSSProduct.setBusinessTransaction("RFBU");
			creditTBOSSProduct.setFiscalYear(substring(currentyyyyMMdd, 0, 4));
			creditTBOSSProduct.setUserName(rightPad("username", 12, " "));
			creditTBOSSProduct.setReferenceProcedure(rightPad("", 5, " "));
			creditTBOSSProduct.setReferenceDocumentNo(rightPad("", 10, " "));
//			creditTBOSSProduct.setNumberOfLineItem1("001");
			creditTBOSSProduct.setDebitCredit(ERP_ACCOUNT_CREDIT);
			creditTBOSSProduct.setPostingKey("40");
			creditTBOSSProduct.setAccountType(ERP_ACCOUNT_TYPE_GENERAL_LEDGER);
			creditTBOSSProduct.setAccount(accountNo);
			creditTBOSSProduct.setAlternativeReconcileAccount(leftPad("", 10, " "));
			creditTBOSSProduct.setAmountTransaction(rightPad(BigDecimal.valueOf(toDouble(creditTBOSSProduct.getAmountTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountTrasaction), "0"))).toPlainString(), 13, " "));
			creditTBOSSProduct.setAmountLocal(rightPad(BigDecimal.valueOf(toDouble(creditTBOSSProduct.getAmountLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountLocal), "0"))).toPlainString(), 13, " "));
			creditTBOSSProduct.setTaxBaseTransaction(rightPad(BigDecimal.valueOf(toDouble(creditTBOSSProduct.getTaxBaseTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseTransaction), "0"))).toPlainString(), 13, " "));
			creditTBOSSProduct.setTaxBaseLocal(rightPad(BigDecimal.valueOf(toDouble(creditTBOSSProduct.getTaxBaseLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseLocal), "0"))).toPlainString(), 13, " "));
			creditTBOSSProduct.setTaxCode("  ");
			creditTBOSSProduct.setBusinessPlace(rightPad("", 4, " "));
			creditTBOSSProduct.setBusinessArea(rightPad("", 4, " "));
			creditTBOSSProduct.setValueDate(currentyyyyMMdd);
			creditTBOSSProduct.setPaymentTerm(rightPad("", 4, " "));
			creditTBOSSProduct.setBaselineDate(rightPad("", 8, " "));
			creditTBOSSProduct.setAmountEligibleForCashDiscountInDocumentCurrency(rightPad("", 13, " "));
			creditTBOSSProduct.setCostCenter(rightPad("", 10, " "));
			creditTBOSSProduct.setFunctionalArea(rightPad("", 16, " "));
			creditTBOSSProduct.setWbs(rightPad("", 24, " "));
			creditTBOSSProduct.setActivityType(rightPad("", 6, " "));
			creditTBOSSProduct.setSegment(leftPad(stripToEmpty(recordInfo.segment), 10, isBlank(recordInfo.segment) ? " " : "0"));
			creditTBOSSProduct.setProduct(leftPad(stripToEmpty(recordInfo.product), 18, isBlank(recordInfo.product) ? " " : "0"));
			creditTBOSSProduct.setSubProduct(rightPad("", 2, " "));
			creditTBOSSProduct.setRevenueType(rightPad("", 10, " "));
			creditTBOSSProduct.setMaterial(rightPad("", 18, " "));
			creditTBOSSProduct.setPlant(rightPad("", 4, " "));
			creditTBOSSProduct.setBusinessProcess(rightPad("", 12, " "));
			creditTBOSSProduct.setCustomerGroup(rightPad("", 10, " "));
			creditTBOSSProduct.setPaymentReference(rightPad("", 30, " "));
			creditTBOSSProduct.setAssignment(rightPad("", 18, " "));
			creditTBOSSProduct.setText(rightPad(stripToEmpty(recordInfo.text), 50, " "));
			creditTBOSSProduct.setRef1(rightPad(stripToEmpty(recordInfo.ref1), 12, " "));
			creditTBOSSProduct.setRef2(leftPad("", 12, " "));
			creditTBOSSProduct.setRef3(rightPad("", 20, " "));
			creditTBOSSProduct.setTradingPartner(rightPad("ZZZZ", 6, " "));
//			creditTBOSSProduct.setNumberOfLineItem2("001");
			creditTBOSSProduct.setTitle(rightPad("", 15, " "));
			creditTBOSSProduct.setName1(rightPad("", 35, " "));
			creditTBOSSProduct.setName2(rightPad("", 35, " "));
			creditTBOSSProduct.setName3(rightPad("", 35, " "));
			creditTBOSSProduct.setName4(rightPad("", 35, " "));
			creditTBOSSProduct.setHouseNoAndStreet(rightPad("", 35, " "));
			creditTBOSSProduct.setCity(rightPad("", 35, " "));
			creditTBOSSProduct.setPostalCode(rightPad("", 10, " "));
			creditTBOSSProduct.setCountryKey(rightPad("", 3, " "));
			creditTBOSSProduct.setTaxNo1(rightPad("", 16, " "));
			creditTBOSSProduct.setTaxNo2(rightPad("", 11, " "));
			creditTBOSSProduct.setPayeeCode(rightPad("", 16, " "));
			creditTBOSSProduct.setBankKey(rightPad("", 15, " "));
			creditTBOSSProduct.setBankAccount(rightPad("", 18, " "));
			creditTBOSSProduct.setBankCountry(rightPad("", 3, " "));
//			creditTBOSSProduct.setNumberOfLineItem3("001");
			creditTBOSSProduct.setWithholdingTaxType1(rightPad("", 2, " "));
			creditTBOSSProduct.setWithholdingTaxCode1(rightPad("", 2, " "));
			creditTBOSSProduct.setWithholdingTaxBaseDoc1(rightPad("", 13, " "));
			creditTBOSSProduct.setWithholdingTaxBaseLocal1(rightPad("", 13, " "));
			creditTBOSSProduct.setWithholdingTaxAmtDoc1(rightPad("", 13, " "));
			creditTBOSSProduct.setWithholdingTaxAmtLocal1(rightPad("", 13, " "));
			creditTBOSSProduct.setWithholdingTaxType2(rightPad("", 2, " "));
			creditTBOSSProduct.setWithholdingTaxCode2(rightPad("", 2, " "));
			creditTBOSSProduct.setWithholdingTaxBaseDoc2(rightPad("", 13, " "));
			creditTBOSSProduct.setWithholdingTaxBaseLocal2(rightPad("", 13, " "));
			creditTBOSSProduct.setWithholdingTaxAmtDoc2(rightPad("", 13, " "));
			creditTBOSSProduct.setWithholdingTaxAmtLocal2(rightPad("", 13, " "));
//			creditTBOSSProduct.setNumberOfLineItem4("001");
			creditTBOSSProduct.setTaxCode(rightPad("", 2, " "));
			creditTBOSSProduct.setGeneralLedgerAccount(rightPad("", 10, " "));
			creditTBOSSProduct.setDebitCreditIndicator(rightPad("", 1, " "));
			creditTBOSSProduct.setLocalTaxBaseAmount(rightPad("", 15, " "));
			creditTBOSSProduct.setTaxBaseAmount(rightPad("", 15, " "));
			creditTBOSSProduct.setLocalTaxAmount(rightPad("", 13, " "));
			creditTBOSSProduct.setTaxAmount(rightPad("", 13, " "));
			creditTBOSSProduct.setTransactionKey(rightPad("", 3, " "));
			creditTBOSSProduct.setConditionType(rightPad("", 4, " "));
			creditTBOSSProduct.setBusinessPlace2(creditTBOSSProduct.getBranch());
//			creditTBOSSProduct.setCarrierOperator(rightPad("", 4, " "));
		}
		for (RecordInfo recordInfo : deductTaxs) { // By Account
			String accountNo = leftPad(ERP_ACCOUNT_NO_DEBIT_TAX_DEDUCTION, 10, "0");
			PayService debitDeductionTax = findByDateAndBranchAndAccount(debitDeductionTaxs, currentyyyyMMdd, branchCode, accountNo);
			debitDeductionTax.setUpdateDttm(currentDate);
			debitDeductionTax.setRecordSequence("001");
			debitDeductionTax.setDocumentDate(currentyyyyMMdd);
			debitDeductionTax.setDocumentType("RV");
			debitDeductionTax.setCompanyCode("1000");
			debitDeductionTax.setPostingDate(currentyyyyMMdd);
			debitDeductionTax.setFiscalPeriod(substring(currentyyyyMMdd, 4, 6));
			debitDeductionTax.setCurrency("THB");
			debitDeductionTax.setExchangeRate(leftPad("1", 11, " "));
			debitDeductionTax.setTranslationDate(currentyyyyMMdd);
			debitDeductionTax.setReference(rightPad(substring("HeadQuater", 0, 16), 16, " "));
			debitDeductionTax.setDocumentHeaderText(rightPad("Payment-EPIS", 25, " "));
			debitDeductionTax.setBranch(leftPad("0", 4, "0"));
			debitDeductionTax.setBusinessTransaction("RFBU");
			debitDeductionTax.setFiscalYear(substring(currentyyyyMMdd, 0, 4));
			debitDeductionTax.setUserName(rightPad("username", 12, " "));
			debitDeductionTax.setReferenceProcedure(rightPad("", 5, " "));
			debitDeductionTax.setReferenceDocumentNo(rightPad("", 10, " "));
//			debitDeductionTax.setNumberOfLineItem1("001");
			debitDeductionTax.setDebitCredit(ERP_ACCOUNT_DEBIT);
			debitDeductionTax.setPostingKey("40");
			debitDeductionTax.setAccountType(ERP_ACCOUNT_TYPE_GENERAL_LEDGER);
			debitDeductionTax.setAccount(accountNo);
			debitDeductionTax.setAlternativeReconcileAccount(leftPad("", 10, " "));
			debitDeductionTax.setAmountTransaction(rightPad(BigDecimal.valueOf(toDouble(debitDeductionTax.getAmountTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountTrasaction), "0"))).toPlainString(), 13, " "));
			debitDeductionTax.setAmountLocal(rightPad(BigDecimal.valueOf(toDouble(debitDeductionTax.getAmountLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountLocal), "0"))).toPlainString(), 13, " "));
			debitDeductionTax.setTaxBaseTransaction(rightPad(BigDecimal.valueOf(toDouble(debitDeductionTax.getTaxBaseTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseTransaction), "0"))).toPlainString(), 13, " "));
			debitDeductionTax.setTaxBaseLocal(rightPad(BigDecimal.valueOf(toDouble(debitDeductionTax.getTaxBaseLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseLocal), "0"))).toPlainString(), 13, " "));
			debitDeductionTax.setTaxCode("  ");
			debitDeductionTax.setBusinessPlace(rightPad("", 4, " "));
			debitDeductionTax.setBusinessArea(rightPad("", 4, " "));
			debitDeductionTax.setValueDate(currentyyyyMMdd);
			debitDeductionTax.setPaymentTerm(rightPad("", 4, " "));
			debitDeductionTax.setBaselineDate(rightPad("", 8, " "));
			debitDeductionTax.setAmountEligibleForCashDiscountInDocumentCurrency(rightPad("", 13, " "));
			debitDeductionTax.setCostCenter(rightPad("", 10, " "));
			debitDeductionTax.setFunctionalArea(rightPad("", 16, " "));
			debitDeductionTax.setWbs(rightPad("", 24, " "));
			debitDeductionTax.setActivityType(rightPad("", 6, " "));
			debitDeductionTax.setSegment(leftPad("", 10, " "));
			debitDeductionTax.setProduct(leftPad("", 18, " "));
			debitDeductionTax.setSubProduct(rightPad("", 2, " "));
			debitDeductionTax.setRevenueType(rightPad("", 10, " "));
			debitDeductionTax.setMaterial(rightPad("", 18, " "));
			debitDeductionTax.setPlant(rightPad("", 4, " "));
			debitDeductionTax.setBusinessProcess(rightPad("", 12, " "));
			debitDeductionTax.setCustomerGroup(rightPad("", 10, " "));
			debitDeductionTax.setPaymentReference(rightPad("", 30, " "));
			debitDeductionTax.setAssignment(rightPad("", 18, " "));
			debitDeductionTax.setText(rightPad("", 50, " "));
			debitDeductionTax.setRef1(rightPad("", 12, " "));
			debitDeductionTax.setRef2(leftPad("", 12, " "));
			debitDeductionTax.setRef3(rightPad("", 20, " "));
			debitDeductionTax.setTradingPartner(rightPad("ZZZZ", 6, " "));
//			debitDeductionTax.setNumberOfLineItem2("001");
			debitDeductionTax.setTitle(rightPad("", 15, " "));
			debitDeductionTax.setName1(rightPad("", 35, " "));
			debitDeductionTax.setName2(rightPad("", 35, " "));
			debitDeductionTax.setName3(rightPad("", 35, " "));
			debitDeductionTax.setName4(rightPad("", 35, " "));
			debitDeductionTax.setHouseNoAndStreet(rightPad("", 35, " "));
			debitDeductionTax.setCity(rightPad("", 35, " "));
			debitDeductionTax.setPostalCode(rightPad("", 10, " "));
			debitDeductionTax.setCountryKey(rightPad("", 3, " "));
			debitDeductionTax.setTaxNo1(rightPad("", 16, " "));
			debitDeductionTax.setTaxNo2(rightPad("", 11, " "));
			debitDeductionTax.setPayeeCode(rightPad("", 16, " "));
			debitDeductionTax.setBankKey(rightPad("", 15, " "));
			debitDeductionTax.setBankAccount(rightPad("", 18, " "));
			debitDeductionTax.setBankCountry(rightPad("", 3, " "));
//			debitDeductionTax.setNumberOfLineItem3("001");
			debitDeductionTax.setWithholdingTaxType1(rightPad("", 2, " "));
			debitDeductionTax.setWithholdingTaxCode1(rightPad("", 2, " "));
			debitDeductionTax.setWithholdingTaxBaseDoc1(rightPad("", 13, " "));
			debitDeductionTax.setWithholdingTaxBaseLocal1(rightPad("", 13, " "));
			debitDeductionTax.setWithholdingTaxAmtDoc1(rightPad("", 13, " "));
			debitDeductionTax.setWithholdingTaxAmtLocal1(rightPad("", 13, " "));
			debitDeductionTax.setWithholdingTaxType2(rightPad("", 2, " "));
			debitDeductionTax.setWithholdingTaxCode2(rightPad("", 2, " "));
			debitDeductionTax.setWithholdingTaxBaseDoc2(rightPad("", 13, " "));
			debitDeductionTax.setWithholdingTaxBaseLocal2(rightPad("", 13, " "));
			debitDeductionTax.setWithholdingTaxAmtDoc2(rightPad("", 13, " "));
			debitDeductionTax.setWithholdingTaxAmtLocal2(rightPad("", 13, " "));
//			debitDeductionTax.setNumberOfLineItem4("001");
			debitDeductionTax.setTaxCode(rightPad("", 2, " "));
			debitDeductionTax.setGeneralLedgerAccount(rightPad("", 10, " "));
			debitDeductionTax.setDebitCreditIndicator(rightPad("", 1, " "));
			debitDeductionTax.setLocalTaxBaseAmount(rightPad("", 15, " "));
			debitDeductionTax.setTaxBaseAmount(rightPad("", 15, " "));
			debitDeductionTax.setLocalTaxAmount(rightPad("", 13, " "));
			debitDeductionTax.setTaxAmount(rightPad("", 13, " "));
			debitDeductionTax.setTransactionKey(rightPad("", 3, " "));
			debitDeductionTax.setConditionType(rightPad("", 4, " "));
			debitDeductionTax.setBusinessPlace2(debitDeductionTax.getBranch());
//			debitDeductionTax.setCarrierOperator(rightPad("", 4, " "));
		}
		for (RecordInfo recordInfo : outputTaxs) { // By Product
			String accountNo = leftPad(ERP_ACCOUNT_NO_DEBIT_SUSPENSE_OUTPUT_TAX, 10, "0");
			PayService debitSuspenseTax = findByDateAndBranchAndAccountAndRef2(debitSuspenseTaxs, currentyyyyMMdd, branchCode, accountNo, recordInfo.product);
			debitSuspenseTax.setUpdateDttm(currentDate);
			debitSuspenseTax.setRecordSequence("002");
			debitSuspenseTax.setDocumentDate(currentyyyyMMdd);
			debitSuspenseTax.setDocumentType("RV");
			debitSuspenseTax.setCompanyCode("1000");
			debitSuspenseTax.setPostingDate(currentyyyyMMdd);
			debitSuspenseTax.setFiscalPeriod(substring(currentyyyyMMdd, 4, 6));
			debitSuspenseTax.setCurrency("THB");
			debitSuspenseTax.setExchangeRate(leftPad("1", 11, " "));
			debitSuspenseTax.setTranslationDate(currentyyyyMMdd);
			debitSuspenseTax.setReference(rightPad(substring("HeadQuater", 0, 16), 16, " "));
			debitSuspenseTax.setDocumentHeaderText(rightPad("Payment-EPIS", 25, " "));
			debitSuspenseTax.setBranch(leftPad("0", 4, "0"));
			debitSuspenseTax.setBusinessTransaction("RFBU");
			debitSuspenseTax.setFiscalYear(substring(currentyyyyMMdd, 0, 4));
			debitSuspenseTax.setUserName(rightPad("username", 12, " "));
			debitSuspenseTax.setReferenceProcedure(rightPad("", 5, " "));
			debitSuspenseTax.setReferenceDocumentNo(rightPad("", 10, " "));
//			debitSuspenseTax.setNumberOfLineItem1("001");
			debitSuspenseTax.setDebitCredit(ERP_ACCOUNT_DEBIT);
			debitSuspenseTax.setPostingKey("40");
			debitSuspenseTax.setAccountType(ERP_ACCOUNT_TYPE_GENERAL_LEDGER);
			debitSuspenseTax.setAccount(accountNo);
			debitSuspenseTax.setAlternativeReconcileAccount(leftPad("", 10, " "));
			debitSuspenseTax.setAmountTransaction(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getAmountTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountTrasaction), "0"))).toPlainString(), 13, " "));
			debitSuspenseTax.setAmountLocal(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getAmountLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountLocal), "0"))).toPlainString(), 13, " "));
			debitSuspenseTax.setTaxBaseTransaction(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getTaxBaseTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseTransaction), "0"))).toPlainString(), 13, " "));
			debitSuspenseTax.setTaxBaseLocal(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getTaxBaseLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseLocal), "0"))).toPlainString(), 13, " "));
			debitSuspenseTax.setTaxCode(ERP_TAX_CODE_DEBIT);
			debitSuspenseTax.setBusinessPlace("0000");
			debitSuspenseTax.setBusinessArea(rightPad("", 4, " "));
			debitSuspenseTax.setValueDate(currentyyyyMMdd);
			debitSuspenseTax.setPaymentTerm(rightPad("", 4, " "));
			debitSuspenseTax.setBaselineDate(rightPad("", 8, " "));
			debitSuspenseTax.setAmountEligibleForCashDiscountInDocumentCurrency(rightPad("", 13, " "));
			debitSuspenseTax.setCostCenter(rightPad("", 10, " "));
			debitSuspenseTax.setFunctionalArea(rightPad("", 16, " "));
			debitSuspenseTax.setWbs(rightPad("", 24, " "));
			debitSuspenseTax.setActivityType(rightPad("", 6, " "));
			debitSuspenseTax.setSegment(leftPad("", 10, " "));
			debitSuspenseTax.setProduct(leftPad("", 18, " "));
			debitSuspenseTax.setSubProduct(rightPad("", 2, " "));
			debitSuspenseTax.setRevenueType(rightPad("", 10, " "));
			debitSuspenseTax.setMaterial(rightPad("", 18, " "));
			debitSuspenseTax.setPlant(rightPad("", 4, " "));
			debitSuspenseTax.setBusinessProcess(rightPad("", 12, " "));
			debitSuspenseTax.setCustomerGroup(rightPad("", 10, " "));
			debitSuspenseTax.setPaymentReference(rightPad("", 30, " "));
			debitSuspenseTax.setAssignment(rightPad("", 18, " "));
			debitSuspenseTax.setText(rightPad(stripToEmpty(recordInfo.text), 50, " "));
			debitSuspenseTax.setRef1(rightPad(stripToEmpty(recordInfo.ref1), 12, " "));
			debitSuspenseTax.setRef2(leftPad(stripToEmpty(recordInfo.product), 12, isBlank(recordInfo.product) ? " " : "0"));
			debitSuspenseTax.setRef3(rightPad("", 20, " "));
			debitSuspenseTax.setTradingPartner(rightPad("", 6, " "));
//			debitSuspenseTax.setNumberOfLineItem2("001");
			debitSuspenseTax.setTitle(rightPad("", 15, " "));
			debitSuspenseTax.setName1(rightPad("", 35, " "));
			debitSuspenseTax.setName2(rightPad("", 35, " "));
			debitSuspenseTax.setName3(rightPad("", 35, " "));
			debitSuspenseTax.setName4(rightPad("", 35, " "));
			debitSuspenseTax.setHouseNoAndStreet(rightPad("", 35, " "));
			debitSuspenseTax.setCity(rightPad("", 35, " "));
			debitSuspenseTax.setPostalCode(rightPad("", 10, " "));
			debitSuspenseTax.setCountryKey(rightPad("", 3, " "));
			debitSuspenseTax.setTaxNo1(rightPad("", 16, " "));
			debitSuspenseTax.setTaxNo2(rightPad("", 11, " "));
			debitSuspenseTax.setPayeeCode(rightPad("", 16, " "));
			debitSuspenseTax.setBankKey(rightPad("", 15, " "));
			debitSuspenseTax.setBankAccount(rightPad("", 18, " "));
			debitSuspenseTax.setBankCountry(rightPad("", 3, " "));
//			debitSuspenseTax.setNumberOfLineItem3("001");
			debitSuspenseTax.setWithholdingTaxType1(rightPad("", 2, " "));
			debitSuspenseTax.setWithholdingTaxCode1(rightPad("", 2, " "));
			debitSuspenseTax.setWithholdingTaxBaseDoc1(rightPad("", 13, " "));
			debitSuspenseTax.setWithholdingTaxBaseLocal1(rightPad("", 13, " "));
			debitSuspenseTax.setWithholdingTaxAmtDoc1(rightPad("", 13, " "));
			debitSuspenseTax.setWithholdingTaxAmtLocal1(rightPad("", 13, " "));
			debitSuspenseTax.setWithholdingTaxType2(rightPad("", 2, " "));
			debitSuspenseTax.setWithholdingTaxCode2(rightPad("", 2, " "));
			debitSuspenseTax.setWithholdingTaxBaseDoc2(rightPad("", 13, " "));
			debitSuspenseTax.setWithholdingTaxBaseLocal2(rightPad("", 13, " "));
			debitSuspenseTax.setWithholdingTaxAmtDoc2(rightPad("", 13, " "));
			debitSuspenseTax.setWithholdingTaxAmtLocal2(rightPad("", 13, " "));
//			debitSuspenseTax.setNumberOfLineItem4("001");
			debitSuspenseTax.setTaxCode(ERP_TAX_CODE_DEBIT);
			debitSuspenseTax.setGeneralLedgerAccount(accountNo);
			debitSuspenseTax.setDebitCreditIndicator(ERP_ACCOUNT_DEBIT);
			debitSuspenseTax.setLocalTaxBaseAmount(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getTaxBaseLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseLocal), "0"))).toPlainString(), 15, " "));
			debitSuspenseTax.setTaxBaseAmount(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getTaxBaseTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseTransaction), "0"))).toPlainString(), 15, " "));
			debitSuspenseTax.setLocalTaxAmount(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getLocalTaxAmount(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountLocal), "0"))).toPlainString(), 13, " "));
			debitSuspenseTax.setTaxAmount(rightPad(BigDecimal.valueOf(toDouble(debitSuspenseTax.getTaxAmount(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountTrasaction), "0"))).toPlainString(), 13, " "));
			debitSuspenseTax.setTransactionKey("MWS");
			debitSuspenseTax.setConditionType("MWAS");
			debitSuspenseTax.setBusinessPlace2(debitSuspenseTax.getBranch());
//			debitSuspenseTax.setCarrierOperator(rightPad("", 4, " "));
		}
		for (RecordInfo recordInfo : salesTaxs) { // By Product
			String accountNo = leftPad(ERP_ACCOUNT_NO_CREDIT_SALES_TAX, 10, "0");
			PayService creditSalesTax = findByDateAndBranchAndAccountAndRef2(creditSalesTaxs, currentyyyyMMdd, branchCode, accountNo, recordInfo.product);
			creditSalesTax.setUpdateDttm(currentDate);
			creditSalesTax.setRecordSequence("002");
			creditSalesTax.setDocumentDate(currentyyyyMMdd);
			creditSalesTax.setDocumentType("RV");
			creditSalesTax.setCompanyCode("1000");
			creditSalesTax.setPostingDate(currentyyyyMMdd);
			creditSalesTax.setFiscalPeriod(substring(currentyyyyMMdd, 4, 6));
			creditSalesTax.setCurrency("THB");
			creditSalesTax.setExchangeRate(leftPad("1", 11, " "));
			creditSalesTax.setTranslationDate(currentyyyyMMdd);
			creditSalesTax.setReference(rightPad(substring("HeadQuater", 0, 16), 16, " "));
			creditSalesTax.setDocumentHeaderText(rightPad("Payment-EPIS", 25, " "));
			creditSalesTax.setBranch(leftPad("0", 4, "0"));
			creditSalesTax.setBusinessTransaction("RFBU");
			creditSalesTax.setFiscalYear(substring(currentyyyyMMdd, 0, 4));
			creditSalesTax.setUserName(rightPad("username", 12, " "));
			creditSalesTax.setReferenceProcedure(rightPad("", 5, " "));
			creditSalesTax.setReferenceDocumentNo(rightPad("", 10, " "));
//			creditSalesTax.setNumberOfLineItem1("001");
			creditSalesTax.setDebitCredit(ERP_ACCOUNT_CREDIT);
			creditSalesTax.setPostingKey("40");
			creditSalesTax.setAccountType(ERP_ACCOUNT_TYPE_GENERAL_LEDGER);
			creditSalesTax.setAccount(accountNo);
			creditSalesTax.setAlternativeReconcileAccount(leftPad("", 10, " "));
			creditSalesTax.setAmountTransaction(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getAmountTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountTrasaction), "0"))).toPlainString(), 13, " "));
			creditSalesTax.setAmountLocal(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getAmountLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountLocal), "0"))).toPlainString(), 13, " "));
			creditSalesTax.setTaxBaseTransaction(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getTaxBaseTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseTransaction), "0"))).toPlainString(), 13, " "));
			creditSalesTax.setTaxBaseLocal(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getTaxBaseLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseLocal), "0"))).toPlainString(), 13, " "));
			creditSalesTax.setTaxCode(ERP_TAX_CODE_CREDIT);
			creditSalesTax.setBusinessPlace("0000");
			creditSalesTax.setBusinessArea(rightPad("", 4, " "));
			creditSalesTax.setValueDate(currentyyyyMMdd);
			creditSalesTax.setPaymentTerm(rightPad("", 4, " "));
			creditSalesTax.setBaselineDate(rightPad("", 8, " "));
			creditSalesTax.setAmountEligibleForCashDiscountInDocumentCurrency(rightPad("", 13, " "));
			creditSalesTax.setCostCenter(rightPad("", 10, " "));
			creditSalesTax.setFunctionalArea(rightPad("", 16, " "));
			creditSalesTax.setWbs(rightPad("", 24, " "));
			creditSalesTax.setActivityType(rightPad("", 6, " "));
			creditSalesTax.setSegment(leftPad("", 10, " "));
			creditSalesTax.setProduct(leftPad("", 18, " "));
			creditSalesTax.setSubProduct(rightPad("", 2, " "));
			creditSalesTax.setRevenueType(rightPad("", 10, " "));
			creditSalesTax.setMaterial(rightPad("", 18, " "));
			creditSalesTax.setPlant(rightPad("", 4, " "));
			creditSalesTax.setBusinessProcess(rightPad("", 12, " "));
			creditSalesTax.setCustomerGroup(rightPad("", 10, " "));
			creditSalesTax.setPaymentReference(rightPad("", 30, " "));
			creditSalesTax.setAssignment(rightPad("", 18, " "));
			creditSalesTax.setText(rightPad("", 50, " "));
			creditSalesTax.setRef1(rightPad(stripToEmpty(recordInfo.ref1), 12, " "));
			creditSalesTax.setRef2(leftPad(stripToEmpty(recordInfo.product), 12, isBlank(recordInfo.product) ? " " : "0"));
			creditSalesTax.setRef3(rightPad("", 20, " "));
			creditSalesTax.setTradingPartner(rightPad("", 6, " "));
//			creditSalesTax.setNumberOfLineItem2("001");
			creditSalesTax.setTitle(rightPad("", 15, " "));
			creditSalesTax.setName1(rightPad("", 35, " "));
			creditSalesTax.setName2(rightPad("", 35, " "));
			creditSalesTax.setName3(rightPad("", 35, " "));
			creditSalesTax.setName4(rightPad("", 35, " "));
			creditSalesTax.setHouseNoAndStreet(rightPad("", 35, " "));
			creditSalesTax.setCity(rightPad("", 35, " "));
			creditSalesTax.setPostalCode(rightPad("", 10, " "));
			creditSalesTax.setCountryKey(rightPad("", 3, " "));
			creditSalesTax.setTaxNo1(rightPad("", 16, " "));
			creditSalesTax.setTaxNo2(rightPad("", 11, " "));
			creditSalesTax.setPayeeCode(rightPad("", 16, " "));
			creditSalesTax.setBankKey(rightPad("", 15, " "));
			creditSalesTax.setBankAccount(rightPad("", 18, " "));
			creditSalesTax.setBankCountry(rightPad("", 3, " "));
//			creditSalesTax.setNumberOfLineItem3("001");
			creditSalesTax.setWithholdingTaxType1(rightPad("", 2, " "));
			creditSalesTax.setWithholdingTaxCode1(rightPad("", 2, " "));
			creditSalesTax.setWithholdingTaxBaseDoc1(rightPad("", 13, " "));
			creditSalesTax.setWithholdingTaxBaseLocal1(rightPad("", 13, " "));
			creditSalesTax.setWithholdingTaxAmtDoc1(rightPad("", 13, " "));
			creditSalesTax.setWithholdingTaxAmtLocal1(rightPad("", 13, " "));
			creditSalesTax.setWithholdingTaxType2(rightPad("", 2, " "));
			creditSalesTax.setWithholdingTaxCode2(rightPad("", 2, " "));
			creditSalesTax.setWithholdingTaxBaseDoc2(rightPad("", 13, " "));
			creditSalesTax.setWithholdingTaxBaseLocal2(rightPad("", 13, " "));
			creditSalesTax.setWithholdingTaxAmtDoc2(rightPad("", 13, " "));
			creditSalesTax.setWithholdingTaxAmtLocal2(rightPad("", 13, " "));
//			creditSalesTax.setNumberOfLineItem4("001");
			creditSalesTax.setTaxCode(ERP_TAX_CODE_CREDIT);
			creditSalesTax.setGeneralLedgerAccount(accountNo);
			creditSalesTax.setDebitCreditIndicator(ERP_ACCOUNT_CREDIT);
			creditSalesTax.setLocalTaxBaseAmount(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getTaxBaseLocal(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseLocal), "0"))).toPlainString(), 15, " "));
			creditSalesTax.setTaxBaseAmount(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getTaxBaseTransaction(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.taxBaseTransaction), "0"))).toPlainString(), 15, " "));
			creditSalesTax.setLocalTaxAmount(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getLocalTaxAmount(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountLocal), "0"))).toPlainString(), 13, " "));
			creditSalesTax.setTaxAmount(rightPad(BigDecimal.valueOf(toDouble(creditSalesTax.getTaxAmount(), 0)).add(new BigDecimal(defaultIfEmpty(stripToEmpty(recordInfo.amountTrasaction), "0"))).toPlainString(), 13, " "));
			creditSalesTax.setTransactionKey("MWS");
			creditSalesTax.setConditionType("MWAS");
			creditSalesTax.setBusinessPlace2(creditSalesTax.getBranch());
//			creditSalesTax.setCarrierOperator(rightPad("", 4, " "));
		}
	}

	RecordInfo findByAccount(List<RecordInfo> payServices, final String account) {
		RecordInfo recordInfo = find(payServices, new Predicate<RecordInfo>() {
			@Override
			public boolean apply(RecordInfo recordInfo) {
				return stripToEmpty(account).equals(recordInfo.account);
			}
		}, null);
		if (recordInfo == null) { recordInfo = new RecordInfo(); payServices.add(recordInfo); }
		return recordInfo;
	}
	RecordInfo findByAccountAndProduct(List<RecordInfo> payServices, final String account, final String product) {
		RecordInfo recordInfo = find(payServices, new Predicate<RecordInfo>() {
			@Override
			public boolean apply(RecordInfo recordInfo) {
				return stripToEmpty(account).equals(recordInfo.account) && stripToEmpty(product).equals(recordInfo.product);
			}
		}, null);
		if (recordInfo == null) { recordInfo = new RecordInfo(); payServices.add(recordInfo); }
		return recordInfo;
	}
	PayService findByDateAndBranchAndAccount(List<PayService> payServices, String date, final String branch, final String account) {
		PayService payService = find(payServices, new Predicate<PayService>() {
			@Override
			public boolean apply(PayService payService) {
				return stripToEmpty(branch).equals(payService.getBranch()) && stripToEmpty(account).equals(payService.getAccount());
			}
		}, null);
		if (payService == null) {
			payService = payServiceRepo.findTopByDocumentDateAndBranchAndAccount(date, branch, account);
		}
		if (payService == null) {
			payService = createPayService("001");
		}
		payServices.add(payService);
		return payService;
	}
	PayService findByDateAndBranchAndAccountAndProduct(List<PayService> payServices, String date, final String branch, final String account, final String product) {
		PayService payService = find(payServices, new Predicate<PayService>() {
			@Override
			public boolean apply(PayService payService) {
				return stripToEmpty(branch).equals(payService.getBranch()) && stripToEmpty(account).equals(payService.getAccount()) && stripToEmpty(payService.getProduct()).endsWith(product);
			}
		}, null);
		if (payService == null) {
			payService = payServiceRepo.findTopByDocumentDateAndBranchAndAccountAndProductEndingWith(date, branch, account, product);
		}
		if (payService == null) {
			payService = createPayService("001");
		}
		payServices.add(payService);
		return payService;
	}
	PayService findByDateAndBranchAndAccountAndRef2(List<PayService> payServices, String date, final String branch, final String account, final String ref2) {
		PayService payService = find(payServices, new Predicate<PayService>() {
			@Override
			public boolean apply(PayService payService) {
				return stripToEmpty(branch).equals(payService.getBranch()) && stripToEmpty(account).equals(payService.getAccount()) && stripToEmpty(payService.getRef2()).endsWith(ref2);
			}
		}, null);
		if (payService == null) {
			payService = payServiceRepo.findTopByDocumentDateAndBranchAndAccountAndRef2EndingWith(date, branch, account, ref2);
		}
		if (payService == null) {
			payService = createPayService("002");
		}
		payServices.add(payService);
		return payService;
	}
	PayService createPayService(String documentSequence) {
		PayService lastRecord = payServiceRepo.findTopByRecordSequenceOrderByNumberOfLineItem1Desc(documentSequence);
		String itemNo = leftPad(Integer.toString(lastRecord == null ? 1 : (toInt(lastRecord.getNumberOfLineItem1())) + 1), 3, "0");
		PayService payService = payServiceRepo.save(new PayService());
		payService.setNumberOfLineItem1(itemNo);
		payService.setNumberOfLineItem2(itemNo);
		payService.setNumberOfLineItem3(itemNo);
		payService.setNumberOfLineItem4(itemNo);
		return payService;
	}
}
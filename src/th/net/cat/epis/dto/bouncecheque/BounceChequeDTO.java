package th.net.cat.epis.dto.bouncecheque;

import java.math.BigDecimal;
import java.util.List;

import th.net.cat.epis.dto.CreatePaymentResultDTO;


public class BounceChequeDTO {
	private BigDecimal test;
	private String account;
	private String docHead;
	
	//			checkBounce			//
	private String invoiceNo;
	private String billNo;
	private String chequeNo;
	
	//			after pay			//
	private String cusNo;
	private String paymentCase;
	
	private List<BounceChequeDTO> bounceChequeDTOList;
	
	private List<CheckBounceChequeDTO> checkBounceChequeDTOList;
	
//				vat				//
	private Double sumBalance;
	private Double sumBalance2;
	private Double sumVat;
	private Double sumVat2;
	private Double sumTotalCharge;
	private Double sumTotalCharge2;
	private Double deduct;
	private Double deduct2;
	
	private BigDecimal vatRD;
	
	private DetailARCustomerDTO detailARCustomerDTO;
	private List<DetailARCustomerDTO> detailARCustomerDTOList;
	
	private PayBounceChequeDTO payBounceChequeDTO;
	private List<PayBounceChequeDTO> payBounceChequeDTOList;
	
	private HistoryARDTO historyARDTO;
	private List<HistoryARDTO> historyARDTOList;
	
	private Method method;
	private List<Method> methods;
	private CreatePaymentResultDTO createPaymentResultDTO;
	
	public CreatePaymentResultDTO getCreatePaymentResultDTO() {
		return createPaymentResultDTO;
	}
	public void setCreatePaymentResultDTO(CreatePaymentResultDTO createPaymentResultDTO) {
		this.createPaymentResultDTO = createPaymentResultDTO;
	}
	public List<PayBounceChequeDTO> getPayBounceChequeDTOList() {
		return payBounceChequeDTOList;
	}
	public void setPayBounceChequeDTOList(List<PayBounceChequeDTO> payBounceChequeDTOList) {
		this.payBounceChequeDTOList = payBounceChequeDTOList;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDocHead() {
		return docHead;
	}
	public void setDocHead(String docHead) {
		this.docHead = docHead;
	}
	public PayBounceChequeDTO getPayBounceChequeDTO() {
		return payBounceChequeDTO;
	}
	public void setPayBounceChequeDTO(PayBounceChequeDTO payBounceChequeDTO) {
		this.payBounceChequeDTO = payBounceChequeDTO;
	}
	public DetailARCustomerDTO getDetailARCustomerDTO() {
		return detailARCustomerDTO;
	}
	public void setDetailARCustomerDTO(DetailARCustomerDTO detailARCustomerDTO) {
		this.detailARCustomerDTO = detailARCustomerDTO;
	}
	public List<DetailARCustomerDTO> getDetailARCustomerDTOList() {
		return detailARCustomerDTOList;
	}
	public void setDetailARCustomerDTOList(List<DetailARCustomerDTO> detailARCustomerDTOList) {
		this.detailARCustomerDTOList = detailARCustomerDTOList;
	}
	public Double getSumBalance() {
		return sumBalance;
	}
	public void setSumBalance(Double sumBalance) {
		this.sumBalance = sumBalance;
	}
	public Double getSumBalance2() {
		return sumBalance2;
	}
	public void setSumBalance2(Double sumBalance2) {
		this.sumBalance2 = sumBalance2;
	}
	public Double getSumVat() {
		return sumVat;
	}
	public void setSumVat(Double sumVat) {
		this.sumVat = sumVat;
	}
	public Double getSumVat2() {
		return sumVat2;
	}
	public void setSumVat2(Double sumVat2) {
		this.sumVat2 = sumVat2;
	}
	public Double getSumTotalCharge() {
		return sumTotalCharge;
	}
	public void setSumTotalCharge(Double sumTotalCharge) {
		this.sumTotalCharge = sumTotalCharge;
	}
	public Double getSumTotalCharge2() {
		return sumTotalCharge2;
	}
	public void setSumTotalCharge2(Double sumTotalCharge2) {
		this.sumTotalCharge2 = sumTotalCharge2;
	}
	public Double getDeduct() {
		return deduct;
	}
	public void setDeduct(Double deduct) {
		this.deduct = deduct;
	}
	public Double getDeduct2() {
		return deduct2;
	}
	public void setDeduct2(Double deduct2) {
		this.deduct2 = deduct2;
	}
	public HistoryARDTO getHistoryARDTO() {
		return historyARDTO;
	}
	public void setHistoryARDTO(HistoryARDTO historyARDTO) {
		this.historyARDTO = historyARDTO;
	}
	public List<HistoryARDTO> getHistoryARDTOList() {
		return historyARDTOList;
	}
	public void setHistoryARDTOList(List<HistoryARDTO> historyARDTOList) {
		this.historyARDTOList = historyARDTOList;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public List<CheckBounceChequeDTO> getCheckBounceChequeDTOList() {
		return checkBounceChequeDTOList;
	}
	public void setCheckBounceChequeDTOList(List<CheckBounceChequeDTO> checkBounceChequeDTOList) {
		this.checkBounceChequeDTOList = checkBounceChequeDTOList;
	}
	public BigDecimal getTest() {
		return test;
	}
	public void setTest(BigDecimal test) {
		this.test = test;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public List<BounceChequeDTO> getBounceChequeDTOList() {
		return bounceChequeDTOList;
	}
	public void setBounceChequeDTOList(List<BounceChequeDTO> bounceChequeDTOList) {
		this.bounceChequeDTOList = bounceChequeDTOList;
	}
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	public String getPaymentCase() {
		return paymentCase;
	}
	public void setPaymentCase(String paymentCase) {
		this.paymentCase = paymentCase;
	}
	public BigDecimal getVatRD() {
		return vatRD;
	}
	public void setVatRD(BigDecimal vatRD) {
		this.vatRD = vatRD;
	}
	
}

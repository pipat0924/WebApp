package th.net.cat.epis.controller.payment;

import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/*
import th.net.cat.epis.ws.mnp01_retrieveordersi.mnp01_retrieveorderelements.MobileNumber;
import th.net.cat.epis.ws.mnp01_retrieveordersi.mnp01_retrieveorderelements.RetrieveOrderRequest;
import th.net.cat.epis.ws.mnp01_retrieveordersi.mnp01_retrieveorderelements.RetrieveOrderResponse;
import th.net.cat.epis.ws.mnp02_createorderpaymentsi.mnp02_createorderpaymentelements.MobileNumberTransaction;
import th.net.cat.epis.ws.mnp03_reverseorderpaymentsi.mnp03_reverseorderpaymentelements.ReverseOrderPaymentRequest;
import th.net.cat.epis.ws.mnp03_reverseorderpaymentsi.mnp03_reverseorderpaymentelements.ReverseOrderPaymentResponse;
*/
import com.google.common.base.Function;

import th.net.cat.crm.repo.BillProfileRepository;
import th.net.cat.crm.repo.CustomerGroupRepository;
import th.net.cat.crm.repo.CustomerProfileRepository;
import th.net.cat.crm.repo.CustomerSegmentRepository;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.dto.CancelPaymentDTO;
import th.net.cat.epis.dto.CancelPaymentResultDTO;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.MobileCustomer;
import th.net.cat.epis.dto.MobileCustomerDTO;
import th.net.cat.epis.dto.OrderRepeatDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.RevertRecipt;
import th.net.cat.epis.repo.EnumRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.RevertReceiptRopository;
import th.net.cat.epis.service.ErpService;
import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.service.TopupService;
import th.net.cat.epis.service.UserService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.ws.m01_findorder.FindOrderRequest;
import th.net.cat.epis.ws.m01_findorder.FindOrderResponse;
import th.net.cat.epis.ws.m01_findorder.Order;
import th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailRequest;
import th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailResponse;
import th.net.cat.epis.ws.m02_findorderdetail.OrderDetail;
import th.net.cat.epis.ws.m04_reversereceipt.ReverseReceiptRequest;
import th.net.cat.epis.ws.m04_reversereceipt.ReverseReceiptResponse;
import th.net.cat.epis.ws.m05_repeatorder.RepeatOrderRequest;
import th.net.cat.epis.ws.m05_repeatorder.RepeatOrderResponse;


@Controller
@SessionAttributes(value={ "service_key", "auth_token" })
public class PaymentMNPController {
	@Autowired PaymentService paymentService;
	@Autowired TopupService topupService;
	@Autowired ErpService erpService;
	@Autowired UserService userService;
	@Autowired OfficerRepository officerRepo;
	@Autowired BillProfileRepository billRepo;
	@Autowired CustomerProfileRepository customerRepo;
	@Autowired CustomerSegmentRepository customerSegmentRepo;
	@Autowired CustomerGroupRepository customerGroupRepo;
	@Autowired ReceiptRepository receiptRepo;
	@Autowired EnumRepository enumRepo;
	@Autowired RevertReceiptRopository revertReceiptRopository;
    /*
	@Autowired th.net.cat.epis.ws.service.MNPWS_MNP01RetrieveOrderService _mnp01RetrieveOrderService;
	@Autowired th.net.cat.epis.ws.service.MNPWS_MNP03ReverseOrderPaymentService _mnp03ReverseOrderPaymentService;
	*/
	@Autowired th.net.cat.epis.ws.service.MNPWS_MNP01FindOrderService _mnp01RetrieveOrderService;
	@Autowired th.net.cat.epis.ws.service.MNPWS_MNP02FindOrderDetailService _mnp02FindOrderDetailService;
	@Autowired th.net.cat.epis.ws.service.MNPWS_MNP04ReverseOrderPaymentService _mnp04ReverseOrderPaymentService;
	@Autowired th.net.cat.epis.ws.service.MNPWS_MNP05RepeatOrderPaymentService _mnp05RepeatOrderPaymentService;
	@Resource(name = "episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	private static Logger logger = Logger.getLogger(PaymentMNPController.class);
	
	/*
	@ResponseBody
	@RequestMapping(value="findMobileNumberInfo.json", method=RequestMethod.GET)
	public MobileCustomerDTO findMobileNumberInfoJSON(@RequestParam("orderId") String orderId, @RequestParam("mdn") String mdn, @RequestParam("name") String name, @RequestParam("surname") String surname) throws Exception {
		
		RetrieveOrderRequest request = new RetrieveOrderRequest();
		request.setRqHeader(_mnp01RetrieveOrderService.buildRqHeader(EpContextHolder.getContext().getUserCode(), EpContextHolder.getContext().getBranchCode()));
		request.setOrderNo(orderId);
		request.setMDN(mdn);
		request.setHasBalance(Boolean.TRUE);
        //nsd 10-01-2017
        request.setCustomerName(name);
        request.setCustomerSurname(surname);
		
		RetrieveOrderResponse response = _mnp01RetrieveOrderService.callInterface(request);
		MobileCustomerDTO mobileCustomerDTO = new MobileCustomerDTO();
		MobileCustomer data = new MobileCustomer();
		if(_mnp01RetrieveOrderService.isCalledSuccesful("00", response)) {
			data.setOrderId(response.getOrderInfo().getOrderNo());
			data.setNo(response.getOrderInfo().getOrderNo());
			data.setName(StringUtils.trim(response.getOrderInfo().getCustomerName() + " " + response.getOrderInfo().getCustomerSurname()));
			data.setTaxId(response.getOrderInfo().getCustomerTaxId());
			data.setBranch(response.getOrderInfo().getCustomerBranch());
			data.setGroup(response.getOrderInfo().getCustomerGroup());
			data.setType(response.getOrderInfo().getCustomerType());
			data.setAddress(StringUtils.trim(response.getOrderInfo().getAddrLine1()));
			data.setAddress2(StringUtils.trim(response.getOrderInfo().getAddrLine2()));
			for(MobileNumber mobile : response.getMDNList()) {
				MobileCustomer.Service service = data.addService();
				service.setMdn(mobile.getMDN());
				service.setIccid(mobile.getICCID());
				service.setAmount(new BigDecimal(mobile.getTotalBalance()));
			}
			mobileCustomerDTO.addData(data);
			mobileCustomerDTO.setStatusCode("0");
		} else {
			mobileCustomerDTO.setStatusCode("1");
			mobileCustomerDTO.getErrorList().add(_mnp01RetrieveOrderService.buildErrorMessage(response));
		}
		
		return mobileCustomerDTO;
	}
    */
	@ResponseBody
	@RequestMapping(value="findMobileNumberInfo.json", method=RequestMethod.GET)
	public MobileCustomerDTO findMobileNumberInfoJSON(@RequestParam("orderId") String orderId, @RequestParam("mdn") String mdn, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("repeatOrderFlag") String repeatOrderFlag) throws Exception {
		
		FindOrderRequest request = new FindOrderRequest();
		request.setTransactionLog(_mnp01RetrieveOrderService.buildTransactionLogCBO());
		request.setOrderId(orderId);
		request.setMDN(mdn);
		//request.setHasBalance(Boolean.TRUE);
		//nsd 10-01-2017
		request.setCustomerName(name);
		request.setCustomerSurname(surname);
		request.setRepeateOrderFlag(repeatOrderFlag);

		FindOrderResponse response = _mnp01RetrieveOrderService.callInterface(request);
		MobileCustomerDTO mobileCustomerDTO = new MobileCustomerDTO();

		if(_mnp01RetrieveOrderService.isCalledSuccesful("00", response)) {
			List<Order> orders = response.getOrderList();
			for(Order order : orders){
				/*if(StringUtils.equals(repeatOrderFlag, "N")){
					orderId = "021312091117188";
				}else{
					orderId = "021312091117189";
				}*/
				MobileCustomer data = new MobileCustomer();
				if(StringUtils.isEmpty(order.getOrderId())){
					data.setOrderId(orderId);;
					data.setNo(orderId);
				}else{
					data.setOrderId(order.getOrderId());
					data.setNo(order.getOrderId());
				}
				data.setName(StringUtils.trim(order.getTitle()+" "+order.getCustomerName() + " " + order.getCustomerSurname()));
				data.setType(order.getAccountType());

				FindOrderDetailRequest findOrderDetailRequest = new FindOrderDetailRequest();
				findOrderDetailRequest.setTransactionLog(_mnp02FindOrderDetailService.buildTransactionLogCBO());
				findOrderDetailRequest.setOrderId(order.getOrderId());
				FindOrderDetailResponse findOrderDetailResponse = _mnp02FindOrderDetailService.callInterface(findOrderDetailRequest);
				List<OrderDetail> orderDetailList = findOrderDetailResponse.getOrderDetailList();
				
				 OrderRepeatDTO repeatDto = repeatOrder(order.getOrderId());
				if(repeatDto.getStatusCode().equals("0")) {
					data.setOrderIdRef(repeatDto.getOrderRepeat().getOrderId());//by NSD 01-03-2017
				} else {
					data.setOrderIdRef("");
				}
				
				if(orderDetailList!=null){
					//List<MobileCustomer.Service> services =new ArrayList<>(orderDetailList.size());
					for(OrderDetail orderDetail: orderDetailList) {
						data.setTaxId(orderDetail.getTaxId());
						data.setAddress(orderDetail.getAddress());
						data.setBranch(orderDetail.getBranchName());
						if(repeatDto.getStatusCode().equals("0")) {
							data.setOrderIdRef(repeatDto.getOrderRepeat().getOrderId());//by NSD 01-03-2017
						} else {
							data.setOrderIdRef("");
						}
						
						data.setAgentAddressCode(response.getOrderList().get(0).getAgentAddressCode());
						//List<MobileCustomer.Service> service =new ArrayList<>()
						MobileCustomer.Service service  = data.addService();
						//MobileCustomer.Service service = new MobileCustomer.Service();

						service.setMdn(String.valueOf(orderDetail.getMDN()));
						service.setIccid(orderDetail.getICCD());
						service.setAmount(new BigDecimal(orderDetail.getTotalAmount()));
						//services.add(service);
					}
					//data.setData(services);
				}
				mobileCustomerDTO.addData(data);
			}


			mobileCustomerDTO.setStatusCode("0");
		} else {
			mobileCustomerDTO.setStatusCode("1");
			mobileCustomerDTO.getErrorList().add(_mnp01RetrieveOrderService.buildErrorMessage(response));
		}

		return mobileCustomerDTO;
	}
 	@ResponseBody
	@RequestMapping(value="createPaymentMobile.json", method=RequestMethod.POST)
	public CreatePaymentResultDTO createPaymentMobileJSON(SettlePaymentDTO paymentDTO) throws Exception {
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		List<Receipt> receipts = paymentService.createPaymentMobile(paymentDTO);
		paymentService.callMNP03CreateOrderPayment(receipts, EpContextHolder.getContext().getUserCode(), EpContextHolder.getContext().getBranchCode());
		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

	/*@ResponseBody
	@RequestMapping(value="cancelPaymentMobile.json", method=RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentMobileJSON(CancelPaymentDTO cancelPaymentDTO) throws Exception {
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		String cancelReasonCode = cancelPaymentDTO.getReceipts().get(0).getReasonCode();
		Iterable<Receipt> receipts = receiptRepo.findAll(transform(cancelPaymentDTO.getReceipts(), new Function<CancelPaymentDTO.Receipt, Long>() {
			@Override
			public Long apply(CancelPaymentDTO.Receipt receipt) {
				return receipt.getId();
			}
		}));
		ReverseOrderPaymentRequest request;
		ReverseOrderPaymentResponse response;
		MobileNumberTransaction mdnTx;
		String exception;
		boolean haveError = false;
		for(Receipt receipt : receipts) {
			request = new ReverseOrderPaymentRequest();
			request.setRqHeader(_mnp03ReverseOrderPaymentService.buildRqHeader(EpContextHolder.getContext().getUserCode(), EpContextHolder.getContext().getBranchCode()));
			request.setOrderNo(receipt.getAccountNo());
			for(Service service : receipt.getServices()) {
				for(Transaction transaction : service.getTransactions()) {
					mdnTx = new MobileNumberTransaction();
					mdnTx.setMDN(service.getMdn());
					mdnTx.setICCID(service.getIccid());
					mdnTx.setTransId(transaction.getTransactionId());
					mdnTx.setTrackingId(transaction.getTrackingId());
					request.getMDNPayment().add(mdnTx);
				}
			}
			exception = "";
			try {
				response = _mnp03ReverseOrderPaymentService.callInterface(request);
				if(_mnp03ReverseOrderPaymentService.isCalledSuccesful("00", response)) {
					cancelPaymentResultDTO.addData(response);
				} else {
					cancelPaymentResultDTO.getErrorList().add(_mnp03ReverseOrderPaymentService.buildErrorMessage(response));
					cancelPaymentResultDTO.setStatusCode("10");
					haveError = true;
				}
			} catch (javax.xml.ws.WebServiceException ex) {
				if (ex.getCause() instanceof java.net.SocketTimeoutException) {
					exception = "java.net.SocketTimeoutException: Read timed out";
				} else {
					exception = ex.toString();
				}
			} finally {
				if(isNotBlank(exception)) {
					cancelPaymentResultDTO.getErrorList().add(new AlertMessage("10","MNP03ReverseOrderPayment : "+exception));
					cancelPaymentResultDTO.setStatusCode("10");
					haveError = true;
				}
			}
		}
		// <!-- Starting: Mark Cancel Status. -->
//		if (!haveError) {
//			paymentService.cancelPayment(receipts);
//		} else {
//			paymentService.markToCancelPayment(receipts);
//		}
		for (Receipt receipt : receipts) {
			receipt.setCancelledBy(EpContextHolder.getContext().getUserCode());
			receipt.setCancelledDttm(new Date());
			receipt.setUpdateDttm(new Date());
			receipt.setCancelReason(cancelReasonCode);
			cancelPaymentResultDTO.addData(receipt);
		}

		paymentService.cancelPayment(receipts);
		
		// <!-- Updating: User Session. -->

//		Session session = userService.getSession();
//		for (Receipt receipt : receipts) {
//			for (Invoice invoice : receipt.getInvoices()) {
//				for (Service service : invoice.getServices()) {
//					for (Transaction transaction : service.getTransactions()) {
//						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
//						paymentSummary.setBalance(paymentSummary.getBalance().subtract(transaction.getAmount()));
//					}
//				}
//			}
//			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
//			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) - 1);
//			paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
//			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
//		}
//		userService.saveSession(session);
//
//

		cancelPaymentResultDTO.setStatusCode("0");
		return cancelPaymentResultDTO;
	}*/
	@ResponseBody
	@RequestMapping(value="cancelPaymentMobile.json", method=RequestMethod.POST)
	public CancelPaymentResultDTO cancelPaymentMobileJSON(CancelPaymentDTO cancelPaymentDTO) throws Exception {
		CancelPaymentResultDTO cancelPaymentResultDTO = new CancelPaymentResultDTO();
		String cancelReasonCode = cancelPaymentDTO.getReceipts().get(0).getReasonCode();
		String cancelReasonDesc = cancelPaymentDTO.getReceipts().get(0).getReasonDesc();
		Iterable<Receipt> receipts = receiptRepo.findAll(transform(cancelPaymentDTO.getReceipts(), new Function<CancelPaymentDTO.Receipt, Long>() {
		
			@Override
			public Long apply(CancelPaymentDTO.Receipt receipt) {
				return receipt.getId();
			}
		}));
		
		Map<Long, CancelPaymentDTO.Receipt> receiptMap = newHashMap();
		for (CancelPaymentDTO.Receipt receipt : cancelPaymentDTO.getReceipts()) {
			receiptMap.put(receipt.getId(), receipt);
		}
		List<Receipt> newReceiptList = new ArrayList<Receipt>();
		Receipt newReceipt = new Receipt();
		CancelPaymentDTO.Receipt rcp;
		
		ReverseReceiptRequest request;
		ReverseReceiptResponse response;
		//MobileNumberTransaction mdnTx;
		
		String exception;
		boolean haveError = false;
		for(Receipt receipt : receipts) {
			request = new ReverseReceiptRequest();
			request.setTransactionLog(_mnp04ReverseOrderPaymentService.buildTransactionLogCBO());
			request.setReceiptId(String.valueOf(receipt.getId()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date receiptdate = receipt.getUpdateDttm();
			Date newdate = new Date();
			String newDate = sdf.format(newdate);
			
			if(AppConstants.REVERT_REPAY_CODE.equalsIgnoreCase(cancelReasonCode)) {
				if(receiptdate.toString().compareTo(newDate) == 0) {
					request.setReverseType(0);
					request.setAccountNo("");
					request.setAccountName("");
					request.setBankName("");
					request.setBranch("");
					request.setServiceNo("");
					request.setCancelReasonId(Integer.parseInt(cancelReasonCode));
					request.setCancelReason(cancelReasonDesc);
				}else {
					request.setAccountNo(cancelPaymentDTO.getBankaccountNo());
					request.setAccountName(cancelPaymentDTO.getBankaccountName());
					request.setBankName(cancelPaymentDTO.getBankName());
					request.setBranch(cancelPaymentDTO.getBranch());
					request.setServiceNo(cancelPaymentDTO.getServiceNo());
					request.setCancelReasonId(Integer.parseInt(cancelReasonCode));
					request.setCancelReason(cancelReasonDesc);
					request.setReverseType(1);
				}
			}
			
			/*receipt.setCancelledBy(EpContextHolder.getContext().getUserCode());
			receipt.setCancelledDttm(new Date());
			receipt.setUpdateDttm(new Date());
			receipt.setCancelReason(cancelReasonCode);
			cancelPaymentResultDTO.addData(receipt);
			
			//Receipt newReceipt = new Receipt();
			BeanUtils.copyProperties(receipt, newReceipt);
			newReceiptList.add(newReceipt);
			rcp = receiptMap.get(receipt.getId());
			receipt.setCancelReason(rcp.getReasonCode() + " - " + rcp.getReasonDesc());*/

			/*
			for(Service service : receipt.getServices()) {
				for(Transaction transaction : service.getTransactions()) {
					mdnTx = new MobileNumberTransaction();
					mdnTx.setMDN(service.getMdn());
					mdnTx.setICCID(service.getIccid());
					mdnTx.setTransId(transaction.getTransactionId());
					mdnTx.setTrackingId(transaction.getTrackingId());
					request.getMDNPayment().add(mdnTx);
				}
			}
			*/
			String status = "", remark = "";
			/*
			if(cancelPaymentDTO.isFlgNewReceipt()) {
				 start create New Receipt 
				newReceipt = paymentService.cancelAndCreateNewMNPReceipt(cancelPaymentDTO, newReceiptList);
				 end create New Receipt 
				cancelPaymentResultDTO.addData(newReceipt);
			}else {
				*/
			
			exception = "";
			try {
				response = _mnp04ReverseOrderPaymentService.callInterface(request);
				if(_mnp04ReverseOrderPaymentService.isCalledSuccesful("00", response)) {
					cancelPaymentResultDTO.addData(response);
					cancelPaymentResultDTO.setStatusCode("0");
					status=response.getTransactionLog().getDestinationReturnCode();
					receipt.setAttributes(receipt.getAttributes().replaceFirst("Z", "") + "R");
		            receipt.setCancelledDttm(newdate);
		            receipt.setCancelledBy(cancelPaymentDTO.getUserAuthen());
		            receiptRepo.save(receipt);
				} else {
					cancelPaymentResultDTO.getErrorList().add(_mnp04ReverseOrderPaymentService.buildErrorMessage(response));
					cancelPaymentResultDTO.setStatusCode("10");
					haveError = true;
					status=cancelPaymentResultDTO.getStatusCode();
				}
				
			} catch (javax.xml.ws.WebServiceException ex) {
				if (ex.getCause() instanceof java.net.SocketTimeoutException) {
					exception = "java.net.SocketTimeoutException: Read timed out";
					remark=exception;
					cancelPaymentResultDTO.setStatusCode("15");
					status=cancelPaymentResultDTO.getStatusCode();
				} else {
					exception = ex.toString();
					remark=exception;
					cancelPaymentResultDTO.setStatusCode("13");
					status=cancelPaymentResultDTO.getStatusCode();
				}
			} finally {
				if(isNotBlank(exception)) {
					cancelPaymentResultDTO.getErrorList().add(new AlertMessage("10","MNP03ReverseOrderPayment : "+exception));
					cancelPaymentResultDTO.setStatusCode("13");
					haveError = true;
					status=cancelPaymentResultDTO.getStatusCode();
				}
			}
		//}
			RevertRecipt revertRecipt = new RevertRecipt();
			revertRecipt.setBankaccountNo(request.getAccountNo());
			revertRecipt.setBankaccountName(request.getAccountName());
			revertRecipt.setReceiptId(request.getReceiptId());
			revertRecipt.setBankName(request.getBankName());
			revertRecipt.setBranch(request.getBranch());
			revertRecipt.setReasonDesc(cancelReasonDesc);
			revertRecipt.setServiceNo(request.getServiceNo());
			revertRecipt.setRevertType(request.getReverseType());
			revertRecipt.setOderId(receipt.getAccountNo());
			revertRecipt.setSourceType(AppConstants.PAYMENT_TYPE_MNP);
			revertRecipt.setCreateDate(newdate);
			revertRecipt.setStatus(status);
			revertRecipt.setRemark(remark);
			//revertRecipt.setCreateBy(createBy);
			revertReceiptRopository.save(revertRecipt);
		}
		// <!-- Starting: Mark Cancel Status. -->
//		if (!haveError) {
//			paymentService.cancelPayment(receipts);
//		} else {
//			paymentService.markToCancelPayment(receipts);
//		}
		/*for (Receipt receipt : receipts) {
			receipt.setCancelledBy(EpContextHolder.getContext().getUserCode());
			receipt.setCancelledDttm(new Date());
			receipt.setUpdateDttm(new Date());
			receipt.setCancelReason(cancelReasonCode);
			cancelPaymentResultDTO.addData(receipt);
		}*/

		//paymentService.cancelPayment(receipts, cancelPaymentDTO.getUserAuthen());

		// <!-- Updating: User Session. -->

//		Session session = userService.getSession();
//		for (Receipt receipt : receipts) {
//			for (Invoice invoice : receipt.getInvoices()) {
//				for (Service service : invoice.getServices()) {
//					for (Transaction transaction : service.getTransactions()) {
//						PaymentSummary paymentSummary = session.getPayType(transaction.getPaymentType());
//						paymentSummary.setBalance(paymentSummary.getBalance().subtract(transaction.getAmount()));
//					}
//				}
//			}
//			PaymentSummary paymentSummary = session.getPayType(PAY_METHOD_RECEIPTTAXINVOICE);
//			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) - 1);
//			paymentSummary = session.getPayType(PAY_METHOD_CANCELTAXINVOICE);
//			paymentSummary.setCounter((paymentSummary.getCounter() == null ? 0 : paymentSummary.getCounter()) + 1);
//		}
//		userService.saveSession(session);

		return cancelPaymentResultDTO;
	}
	@ResponseBody
	@RequestMapping(value="repeatOrder.json", method=RequestMethod.GET)
	public OrderRepeatDTO repeatOrder(@RequestParam("orderId") String orderId) throws Exception {

		RepeatOrderRequest request = new RepeatOrderRequest();
		request.setTransactionLog(_mnp05RepeatOrderPaymentService.buildTransactionLogCBO());
		request.setOrderId(orderId);
		RepeatOrderResponse response = _mnp05RepeatOrderPaymentService.callInterface(request);
		OrderRepeatDTO orderRepeatDTO = new OrderRepeatDTO();

		if(_mnp05RepeatOrderPaymentService.isCalledSuccesful("00", response)) {
			th.net.cat.epis.ws.m05_repeatorder.OrderRepeat orderRepeat = response.getOrderRepeat();
			th.net.cat.epis.dto.OrderRepeat data = new th.net.cat.epis.dto.OrderRepeat();
			data.setOrderId(orderRepeat.getOrderId());
			orderRepeatDTO.setOrderRepeat(data);
			orderRepeatDTO.setStatusCode("0");
		} else {
			orderRepeatDTO.setStatusCode("1");
			orderRepeatDTO.getErrorList().add(_mnp05RepeatOrderPaymentService.buildErrorMessage(response));
		}
		
		logger.info("=================== "+response.toString());
		
		
		return orderRepeatDTO;
	}
	public static final String convertDateString(String str) { 
		return str.replaceAll("([0-9]{2})/([0-9]{2})/([0-9]{4})", "$3-$2-$1"); 
	}
	
	@ResponseBody
	@RequestMapping(value="findMNPPaymentByOrderId.json", method=RequestMethod.POST)
	public CreatePaymentResultDTO findMNPPaymentByOrderIdJSON(SettlePaymentDTO.Service paymentDTO) throws Exception {
		Receipt newReceipt = new Receipt();
		List<Receipt> receipts = new ArrayList<Receipt>();
		CreatePaymentResultDTO dto = new CreatePaymentResultDTO();
		Long receiptId = episJdbcTemplate.queryForObject("SELECT max(RECEIPTID) from TMPINVOICESERVICE where orderId = ? ", new Object[] { paymentDTO.getRefOrderId() }, Long.class);
		if (receiptId != null) {
			List<Receipt> recp = receiptRepo.findById(receiptId);
			newReceipt = paymentService.createRecepitRepeatOrder(paymentDTO, recp);
		}
		receipts.add(newReceipt);
		paymentService.callMNP03CreateOrderPayment(receipts, EpContextHolder.getContext().getUserCode(), EpContextHolder.getContext().getBranchCode());
		dto.setData(receipts);
		dto.setStatusCode("0");
		return dto;
	}

}
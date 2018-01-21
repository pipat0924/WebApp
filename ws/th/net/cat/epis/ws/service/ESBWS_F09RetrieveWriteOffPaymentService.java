package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F09_RetrieveWriteOffPayment_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.RetrieveWriteOffPaymentRequest;
import th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.RetrieveWriteOffPaymentResponse;

@Service
public class ESBWS_F09RetrieveWriteOffPaymentService implements IESBService<RetrieveWriteOffPaymentRequest, RetrieveWriteOffPaymentResponse> {

	@Autowired
	th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.f09_retrievewriteoffpaymenthistorysi.F09RetrieveWriteOffPaymentHistorySI
	_service;
	
	@Override
	public boolean validateRequest(RetrieveWriteOffPaymentRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F09_RetrieveWriteOffPayment_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}

	@Override
	public RetrieveWriteOffPaymentResponse callInterface(RetrieveWriteOffPaymentRequest request) {
		if(!validateRequest(request)) return null;
		return _service.retrieveWriteOffPaymentInfo(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveWriteOffPaymentResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(RetrieveWriteOffPaymentResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F09RetrieveWriteOffPayment either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F09RetrieveWriteOffPayment didn't get TransactionLog response.");
		}
		return message;
	}

}

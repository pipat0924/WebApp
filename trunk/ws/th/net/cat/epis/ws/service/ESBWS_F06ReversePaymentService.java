package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.DESTINATION_SYSTEM;
import static th.net.cat.epis.service.adapter.AdapterConstants.F06_ReversePayment_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f06_reversepayment.ReversePaymentRequest;
import th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse;

@Service
public class ESBWS_F06ReversePaymentService implements IESBService<ReversePaymentRequest, ReversePaymentResponse> {

	@Autowired
	th.net.cat.epis.ws.f06_reversepayment.f06_reversepaymentsi.F06ReversePaymentSI
	_service;
	
	@Override
	public boolean validateRequest(ReversePaymentRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F06_ReversePayment_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		transaction.setDestinationSystem(DESTINATION_SYSTEM);
		transaction.setRequestDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		transaction.setLogStatus(Boolean.TRUE);
		transaction.setDataLog(Boolean.TRUE);
		transaction.setTransactionType("Request");
		return transaction;
	}

	@Override
	public ReversePaymentResponse callInterface(ReversePaymentRequest request) {
		if(!validateRequest(request)) return null;
		return _service.reversePayment(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, ReversePaymentResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(ReversePaymentResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F06ReversePayment either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F06ReversePayment didn't get TransactionLog response.");
		}
		return message;
	}

}

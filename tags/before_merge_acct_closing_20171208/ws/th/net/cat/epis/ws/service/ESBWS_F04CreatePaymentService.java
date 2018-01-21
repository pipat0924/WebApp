package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.DESTINATION_SYSTEM;
import static th.net.cat.epis.service.adapter.AdapterConstants.F04_CreatePayment_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentRequest;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f4.CreatePaymentResponse;

@Service
public class ESBWS_F04CreatePaymentService implements IESBService<CreatePaymentRequest,CreatePaymentResponse> {

	@Autowired
	th.net.cat.epis.ws.esblibs.inf.esb.f4.F4CreatePaymentSI
	_f04service;
	
	@Override
	public boolean validateRequest(CreatePaymentRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F04_CreatePayment_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		transaction.setDestinationSystem(DESTINATION_SYSTEM);
		transaction.setSourceInterfaceType("W");
		transaction.setDestinationInterfaceType("K");
		transaction.setRequestDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		transaction.setLogStatus(true);
		transaction.setTransactionType("Request");
		transaction.setDataLog(true);
		return transaction;
	}

	@Override
	public CreatePaymentResponse callInterface(CreatePaymentRequest request) {
		if(!validateRequest(request)) return null;
		return _f04service.createPayment(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, CreatePaymentResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(CreatePaymentResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F4CreatePayment either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F4CreatePayment didn't get TransactionLog response.");
		}
		return message;
	}

}

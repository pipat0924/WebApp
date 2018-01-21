package th.net.cat.epis.ws.service;

import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryRequest;
import th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse;

import static th.net.cat.epis.service.adapter.AdapterConstants.*;

@Service
public class ESBWS_F13RetrievePaymentHistory implements IESBService<RetrievePaymentHistoryRequest, RetrievePaymentHistoryResponse> {

	@Resource(name="_f13webservice")
	th.net.cat.epis.ws.f13_retrievepaymenthistory.f13_retrievepaymenthistorysi.F13RetrievePaymentHistorySI _service;
	
	@Override
	public boolean validateRequest(RetrievePaymentHistoryRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F13_RetrievePaymentHistory_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
	/*
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F13_RetrievePaymentHistory_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		transaction.setDestinationSystem(DESTINATION_SYSTEM);
		transaction.setRequestDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		transaction.setLogStatus(Boolean.TRUE);
		transaction.setDataLog(Boolean.TRUE);
		transaction.setTransactionType("Request");
		*/
		return transaction;
	}

	@Override
	public RetrievePaymentHistoryResponse callInterface(RetrievePaymentHistoryRequest request) {
		if(!validateRequest(request)) return null;
		return _service.retrievePaymentHistory(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrievePaymentHistoryResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}
	
	@Override
	public AlertMessage buildErrorMessage(RetrievePaymentHistoryResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F13RetrievePaymentHistory either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F13RetrievePaymentHistory didn't get TransactionLog response.");
		}
		return message;
	}

}

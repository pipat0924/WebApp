package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.DESTINATION_SYSTEM;
import static th.net.cat.epis.service.adapter.AdapterConstants.F14_RetrievePayment_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListRequest;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentListResponse;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentRequest;
import th.net.cat.epis.ws.esblibs.requestresponsecbo.f14.RetrievePaymentResponse;

@Service
public class ESBWS_F14RetrievePaymentService implements IESBService<RetrievePaymentRequest, RetrievePaymentResponse> {

	@Resource(name="_f14webservice")
	th.net.cat.epis.ws.esblibs.inf.esb.f14.F14RetrievePaymentSI _service;
	@Resource(name="_f14webservicelist")
	th.net.cat.epis.ws.esblibs.inf.esb.f14.F14RetrievePaymentSI _serviceList;
	
	
	
	@Override
	public boolean validateRequest(RetrievePaymentRequest model) {
		return true;
	}
	
	public boolean validateRequest(RetrievePaymentListRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F14_RetrievePayment_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		transaction.setDestinationSystem(DESTINATION_SYSTEM);
		transaction.setRequestDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		transaction.setLogStatus(Boolean.TRUE);
		transaction.setDataLog(Boolean.TRUE);
		transaction.setTransactionType("Request");
		return transaction;
	}

	@Override
	public RetrievePaymentResponse callInterface(RetrievePaymentRequest request) {
		if(!validateRequest(request)) return null;
		return _service.retrievePayment(request);
	}
	
	public RetrievePaymentListResponse callInterface(RetrievePaymentListRequest request) {
		if(!validateRequest(request)) return null;
		return _serviceList.retrievePaymentList(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrievePaymentResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}
	
	public boolean isCalledSuccesful(String successCode, RetrievePaymentListResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(RetrievePaymentResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F14RetrievePayment either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F14RetrievePayment didn't get TransactionLog response.");
		}
		return message;
	}
	
	public AlertMessage buildErrorMessage(RetrievePaymentListResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F14RetrievePaymentList either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F14RetrievePaymentList didn't get TransactionLog response.");
		}
		return message;
	}

}

package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F05_RetrieveServiceStatus_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusRequest;
import th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse;

@Service
public class ESBWS_F05RetrieveServiceStatusService implements IESBService<RetrieveServiceStatusRequest, RetrieveServiceStatusResponse> {

	@Autowired
	th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi.F05RetrieveServiceStatusSI
	_service;
	
	@Override
	public boolean validateRequest(RetrieveServiceStatusRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F05_RetrieveServiceStatus_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}

	@Override
	public RetrieveServiceStatusResponse callInterface(RetrieveServiceStatusRequest request) {
		if(!validateRequest(request)) return null;
		return _service.checkServiceStatus(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveServiceStatusResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(RetrieveServiceStatusResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F05RetrieveServiceStatus either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F05RetrieveServiceStatus didn't get TransactionLog response.");
		}
		return message;
	}

}

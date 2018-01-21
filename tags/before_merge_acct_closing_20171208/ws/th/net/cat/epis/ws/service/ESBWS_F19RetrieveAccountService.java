package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F19_RetrieveAccount_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountRequest;
import th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountResponse;

@Service
public class ESBWS_F19RetrieveAccountService implements IESBService<RetrieveAccountRequest, RetrieveAccountResponse> {

	@Autowired
	th.net.cat.epis.ws.f19_retrieveaccount.f19_retrieveaccountsi.F19RetrieveAccountSI
	_service;
	
	@Override
	public boolean validateRequest(RetrieveAccountRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F19_RetrieveAccount_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}

	@Override
	public RetrieveAccountResponse callInterface(RetrieveAccountRequest request) {
		if(!validateRequest(request)) return null;
		return _service.retrieveAccountInfo(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveAccountResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(RetrieveAccountResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F19RetrieveAccount either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F19RetrieveAccount didn't get TransactionLog response.");
		}
		return message;
	}

}

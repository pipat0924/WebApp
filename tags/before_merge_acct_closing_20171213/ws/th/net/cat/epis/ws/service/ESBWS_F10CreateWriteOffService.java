package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F10_CreateWriteOff_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffRequest;
import th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse;

@Service
public class ESBWS_F10CreateWriteOffService implements IESBService<CreateWriteOffRequest, CreateWriteOffResponse> {

	@Autowired
	th.net.cat.epis.ws.f10_createwriteoffpos.f10_createwriteoffsi.F10CreateWriteOffSI
	_service;
	
	@Override
	public boolean validateRequest(CreateWriteOffRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F10_CreateWriteOff_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}

	@Override
	public CreateWriteOffResponse callInterface(CreateWriteOffRequest request) {
		if(!validateRequest(request)) return null;
		return _service.createWriteOff(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, CreateWriteOffResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(CreateWriteOffResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F10CreateWriteOff either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F10CreateWriteOff didn't get TransactionLog response.");
		}
		return message;
	}

}

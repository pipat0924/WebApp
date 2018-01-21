package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F11_ReverseWriteOff_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffRequest;
import th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse;

@Service
public class ESBWS_F11ReverseWriteOffService implements IESBService<ReverseWriteOffRequest, ReverseWriteOffResponse> {

	@Autowired
	th.net.cat.epis.ws.f11_reversewriteoffpos.f11_reversewriteoffsi.F11ReverseWriteOffSI
	_service;
	
	@Override
	public boolean validateRequest(ReverseWriteOffRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F11_ReverseWriteOff_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}

	@Override
	public ReverseWriteOffResponse callInterface(ReverseWriteOffRequest request) {
		if(!validateRequest(request)) return null;
		return _service.reverseWriteOff(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, ReverseWriteOffResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(ReverseWriteOffResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F11ReverseWriteOffService either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F11ReverseWriteOffService didn't get TransactionLog response.");
		}
		return message;
	}

}

package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F18_CreateRefund_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f18_createrefund.CreateRefundRequest;
import th.net.cat.epis.ws.f18_createrefund.CreateRefundResponse;

@Service
public class ESBWS_F18CreateRefundService implements IESBService<CreateRefundRequest, CreateRefundResponse> {

	@Autowired
	th.net.cat.epis.ws.f18_createrefund.f18_createrefundsi.F18CreateRefundSI
	_service;
	
	@Override
	public boolean validateRequest(CreateRefundRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F18_CreateRefund_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}

	@Override
	public CreateRefundResponse callInterface(CreateRefundRequest request) {
		if(!validateRequest(request)) return null;
		return _service.createRefund(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, CreateRefundResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(CreateRefundResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F18CreateRefund either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F18CreateRefund didn't get TransactionLog response.");
		}
		return message;
	}

}

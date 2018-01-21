package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F02_RetrieveInvoiceAccountCode_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.RetrieveInvoiceAccountCodeRequest;
import th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.RetrieveInvoiceAccountCodeResponse;

@Service
public class ESBWS_F02RetrieveInvoiceAccountCodeService implements IESBService<RetrieveInvoiceAccountCodeRequest, RetrieveInvoiceAccountCodeResponse> {
	
	@Autowired  
	th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.f02_retrieveinvoiceaccountcodesi.F02RetrieveInvoiceAccountCodeSI
	_f02service;

	@Override
	public TransactionLogCBO buildTransactionLogCBO() {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F02_RetrieveInvoiceAccountCode_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		transaction.setLogStatus(true);
		transaction.setTransactionType("Request");
		transaction.setDataLog(true);
		return transaction;
	}
	
	@Override
	public boolean validateRequest(RetrieveInvoiceAccountCodeRequest model) {
		return true;
	}

	@Override
	public RetrieveInvoiceAccountCodeResponse callInterface(RetrieveInvoiceAccountCodeRequest request) {
		if(!validateRequest(request)) return null;
		return _f02service.retrieveInvoiceAccountCodeInfo(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveInvoiceAccountCodeResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}
	
	@Override
	public AlertMessage buildErrorMessage(RetrieveInvoiceAccountCodeResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F02RetrieveInvoiceAccountCode either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F02RetrieveInvoiceAccountCode didn't get TransactionLog response.");
		}
		return message;
	}
	
}

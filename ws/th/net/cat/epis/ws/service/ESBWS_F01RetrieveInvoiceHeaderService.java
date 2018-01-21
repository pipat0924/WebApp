package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F01_RetrieveInvoice_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderRequest;
import th.net.cat.epis.ws.f01_retrieveinvoiceheader.RetrieveInvoiceHeaderResponse;

@Service
public class ESBWS_F01RetrieveInvoiceHeaderService implements IESBService<RetrieveInvoiceHeaderRequest, RetrieveInvoiceHeaderResponse> {
	
	@Autowired  
	th.net.cat.epis.ws.f01_retrieveinvoiceheader.f01_retrieveinvoiceheadersi.F01RetrieveInvoiceHeaderSI
	_f01service;

	@Override
	public TransactionLogCBO buildTransactionLogCBO() {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F01_RetrieveInvoice_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}
	
	@Override
	public boolean validateRequest(RetrieveInvoiceHeaderRequest model) {
		return true;
	}

	@Override
	public RetrieveInvoiceHeaderResponse callInterface(RetrieveInvoiceHeaderRequest request) {
		if(!validateRequest(request)) return null;
		return _f01service.retrieveInvoiceHeaderInfo(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveInvoiceHeaderResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}
	
	@Override
	public AlertMessage buildErrorMessage(RetrieveInvoiceHeaderResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F01RetrieveInvoiceHeader either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F01RetrieveInvoiceHeader didn't get TransactionLog response.");
		}
		return message;
	}
	
}

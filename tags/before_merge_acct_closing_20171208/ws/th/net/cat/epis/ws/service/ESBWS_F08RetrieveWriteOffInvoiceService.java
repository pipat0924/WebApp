package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.F08_RetrieveWriteOffInvoice_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f08_writeoffinquirypos.RetrieveWriteOffInvoiceRequest;
import th.net.cat.epis.ws.f08_writeoffinquirypos.RetrieveWriteOffInvoiceResponse;

@Service
public class ESBWS_F08RetrieveWriteOffInvoiceService implements IESBService<RetrieveWriteOffInvoiceRequest, RetrieveWriteOffInvoiceResponse> {

	@Autowired
	th.net.cat.epis.ws.f08_writeoffinquirypos.f08_writeoffinquirysi.F08WriteOffInquirySI
	_service;
	
	@Override
	public boolean validateRequest(RetrieveWriteOffInvoiceRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F08_RetrieveWriteOffInvoice_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		return transaction;
	}

	@Override
	public RetrieveWriteOffInvoiceResponse callInterface(RetrieveWriteOffInvoiceRequest request) {
		if(!validateRequest(request)) return null;
		return _service.retrieveWriteOffInvoice(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveWriteOffInvoiceResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(RetrieveWriteOffInvoiceResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F08RetrieveWriteOffInvoice either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F08RetrieveWriteOffInvoice didn't get TransactionLog response.");
		}
		return message;
	}

}

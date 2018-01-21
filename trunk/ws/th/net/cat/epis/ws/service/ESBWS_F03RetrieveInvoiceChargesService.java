package th.net.cat.epis.ws.service;

import static th.net.cat.epis.service.adapter.AdapterConstants.DESTINATION_SYSTEM;
import static th.net.cat.epis.service.adapter.AdapterConstants.F03_RetrieveInvoiceCharges_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoRequest;
import th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse;

@Service
public class ESBWS_F03RetrieveInvoiceChargesService implements IESBService<RetrieveInvoiceChargeInfoRequest, RetrieveInvoiceChargeInfoResponse> {

	@Autowired
	th.net.cat.epis.ws.f03_retrieveinvoicecharges.f03_retrieveinvoicechargessi.F03RetrieveInvoiceChargesSI
	_f03service;
	
	@Override
	public boolean validateRequest(RetrieveInvoiceChargeInfoRequest model) {
		return true;
	}

	@Override
	public TransactionLogCBO buildTransactionLogCBO() throws Exception {
		TransactionLogCBO transaction = new TransactionLogCBO();
		transaction.setTranID(AppUtil.generateTransactionID(15));
		transaction.setInterfaceNo(F03_RetrieveInvoiceCharges_INTERFACE_NO);
		transaction.setSourceSystem(SOURCE_SYSTEM);
		transaction.setDestinationSystem(DESTINATION_SYSTEM);
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		transaction.setRequestDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		transaction.setLogStatus(true);
		transaction.setTransactionType("Request");
		transaction.setDataLog(true);
		return transaction;
	}

	@Override
	public RetrieveInvoiceChargeInfoResponse callInterface(RetrieveInvoiceChargeInfoRequest request) {
		if(!validateRequest(request)) return null;
		return _f03service.retrieveInvoiceChargeInfo(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveInvoiceChargeInfoResponse response) {
		return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(RetrieveInvoiceChargeInfoResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("F03RetrieveInvoiceCharges either didn't get response or technical exception occurred. ");
		} else if(model.getTransactionLog() != null) {
			message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
			message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
		} else {
			message.setMessageDesc("F03RetrieveInvoiceCharges didn't get TransactionLog response.");
		}
		return message;
	}
}

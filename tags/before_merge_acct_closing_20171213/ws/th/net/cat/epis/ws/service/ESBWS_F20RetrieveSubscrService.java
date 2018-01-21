package th.net.cat.epis.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountRequest;
import th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountResponse;
import th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrRequest;
import th.net.cat.epis.ws.f20_retrievesubscrbyinv.RetrieveSubscrResponse;

import static th.net.cat.epis.service.adapter.AdapterConstants.F19_RetrieveAccount_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.F20_F20RetrieveSubscr_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

/**
 * Created by imake on 10/01/2017.
 */
@Service
public class ESBWS_F20RetrieveSubscrService implements IESBService<RetrieveSubscrRequest, RetrieveSubscrResponse> {

    @Autowired
    th.net.cat.epis.ws.f20_retrievesubscrbyinv.f20_retrievesubscrbyinvsi.F20RetrieveSubscrByInvSI
            _service;

    @Override
    public boolean validateRequest(RetrieveSubscrRequest model) {
        return true;
    }

    @Override
    public TransactionLogCBO buildTransactionLogCBO() throws Exception {
        TransactionLogCBO transaction = new TransactionLogCBO();
        transaction.setTranID(AppUtil.generateTransactionID(15));
        transaction.setInterfaceNo(F20_F20RetrieveSubscr_INTERFACE_NO);
        transaction.setSourceSystem(SOURCE_SYSTEM);
        return transaction;
    }

    @Override
    public RetrieveSubscrResponse callInterface(RetrieveSubscrRequest request) {
        if(!validateRequest(request)) return null;
        return _service.retrieveSubscr(request);
    }

    @Override
    public boolean isCalledSuccesful(String successCode, RetrieveSubscrResponse response) {
        return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
    }

    @Override
    public AlertMessage buildErrorMessage(RetrieveSubscrResponse model) {
        AlertMessage message = new AlertMessage("10","");
        if(model == null) {
            message.setMessageDesc("F20RetrieveSubscr either didn't get response or technical exception occurred. ");
        } else if(model.getTransactionLog() != null) {
            message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
            message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
        } else {
            message.setMessageDesc("F20RetrieveSubscr didn't get TransactionLog response.");
        }
        return message;
    }
}

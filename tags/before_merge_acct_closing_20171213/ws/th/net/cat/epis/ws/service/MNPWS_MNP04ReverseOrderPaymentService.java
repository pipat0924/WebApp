package th.net.cat.epis.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.m04_reversereceipt.ReverseReceiptRequest;
import th.net.cat.epis.ws.m04_reversereceipt.ReverseReceiptResponse;

import static th.net.cat.epis.service.adapter.AdapterConstants.MNP04_ReverseOrderPayment_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

/**
 * Created by imake on 26/02/2017
 */
@Service
public class MNPWS_MNP04ReverseOrderPaymentService
        implements IMNPService<ReverseReceiptRequest, ReverseReceiptResponse> {

    @Autowired
    th.net.cat.epis.ws.m04_reversereceipt.m04_reversereceiptsi.M04ReverseReceiptSI _mnp04service;

    @Override
    public TransactionLogCBO buildTransactionLogCBO() throws Exception {
        TransactionLogCBO transaction = new TransactionLogCBO();
        transaction.setTranID(AppUtil.generateTransactionID(15));
        transaction.setInterfaceNo(MNP04_ReverseOrderPayment_INTERFACE_NO);
        transaction.setSourceSystem(SOURCE_SYSTEM);
        return transaction;
    }


    @Override
    public boolean validateRequest(ReverseReceiptRequest model) {
        return true;
    }

    @Override
    public ReverseReceiptResponse callInterface(ReverseReceiptRequest request) {
        if(!validateRequest(request)) return null;
        return _mnp04service.reverseReceiptInfo(request);
    }

    @Override
    public boolean isCalledSuccesful(String successCode, ReverseReceiptResponse response) {
        return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
    }

    @Override
    public AlertMessage buildErrorMessage(ReverseReceiptResponse model) {
        AlertMessage message = new AlertMessage("10","");
        if(model == null) {
            message.setMessageDesc("M04ReverseOrderPayment either didn't get response or technical exception occurred. ");
        } else if(model.getTransactionLog() != null) {
            message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
            message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
        } else {
            message.setMessageDesc("M04ReverseOrderPayment didn't get TransactionLog response.");
        }
        return message;
    }
}

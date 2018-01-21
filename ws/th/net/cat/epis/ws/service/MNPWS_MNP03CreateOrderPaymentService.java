package th.net.cat.epis.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.m03_createreceipt.CreateReceiptRequest;
import th.net.cat.epis.ws.m03_createreceipt.CreateReceiptResponse;

import static th.net.cat.epis.service.adapter.AdapterConstants.MNP03_CreateOrderPayment_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

/**
 * Created by imake on 26/02/2017
 */
@Service
public class MNPWS_MNP03CreateOrderPaymentService implements IMNPService<CreateReceiptRequest, CreateReceiptResponse> {

    @Autowired
    th.net.cat.epis.ws.m03_createreceipt.m03_createreceiptsi.M03CreateReceiptSI _mnp03service;

    @Override
    public TransactionLogCBO buildTransactionLogCBO() throws Exception {
        TransactionLogCBO transaction = new TransactionLogCBO();
        transaction.setTranID(AppUtil.generateTransactionID(15));
        transaction.setInterfaceNo(MNP03_CreateOrderPayment_INTERFACE_NO);
        transaction.setSourceSystem(SOURCE_SYSTEM);
        return transaction;
    }


    @Override
    public boolean validateRequest(CreateReceiptRequest model) {
        return true;
    }

    @Override
    public CreateReceiptResponse callInterface(CreateReceiptRequest request) {
        if(!validateRequest(request)) return null;
        return _mnp03service.createReceiptInfo(request);
    }

    @Override
    public boolean isCalledSuccesful(String successCode, CreateReceiptResponse response) {
        return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
    }

    @Override
    public AlertMessage buildErrorMessage(CreateReceiptResponse model) {
        AlertMessage message = new AlertMessage("10","");
        if(model == null) {
            message.setMessageDesc("M03CreateOrderPayment either didn't get response or technical exception occurred. ");
        } else if(model.getTransactionLog() != null) {
            message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
            message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
        } else {
            message.setMessageDesc("M03CreateOrderPayment didn't get TransactionLog response.");
        }
        return message;
    }
}

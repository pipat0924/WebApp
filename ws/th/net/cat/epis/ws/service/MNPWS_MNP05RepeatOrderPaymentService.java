package th.net.cat.epis.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.m05_repeatorder.RepeatOrderRequest;
import th.net.cat.epis.ws.m05_repeatorder.RepeatOrderResponse;

import static th.net.cat.epis.service.adapter.AdapterConstants.MNP05_RepeatOrder_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

/**
 * Created by imake on 26/02/2017
 */
@Service
public class MNPWS_MNP05RepeatOrderPaymentService implements IMNPService<RepeatOrderRequest, RepeatOrderResponse> {

    @Autowired
    th.net.cat.epis.ws.m05_repeatorder.m05_repeatordersi.M05RepeatOrderSI _mnp05service;

    @Override
    public TransactionLogCBO buildTransactionLogCBO() throws Exception {
        TransactionLogCBO transaction = new TransactionLogCBO();
        transaction.setTranID(AppUtil.generateTransactionID(15));
        transaction.setInterfaceNo(MNP05_RepeatOrder_INTERFACE_NO);
        transaction.setSourceSystem(SOURCE_SYSTEM);
        return transaction;
    }


    @Override
    public boolean validateRequest(RepeatOrderRequest model) {
        return true;
    }

    @Override
    public RepeatOrderResponse callInterface(RepeatOrderRequest request) {
        if(!validateRequest(request)) return null;
        return _mnp05service.repeatOrderInfo(request);
    }

    @Override
    public boolean isCalledSuccesful(String successCode, RepeatOrderResponse response) {
        return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
    }

    @Override
    public AlertMessage buildErrorMessage(RepeatOrderResponse model) {
        AlertMessage message = new AlertMessage("10","");
        if(model == null) {
            message.setMessageDesc("M05RepeatOrderPayment either didn't get response or technical exception occurred. ");
        } else if(model.getTransactionLog() != null) {
            message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
            message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
        } else {
            message.setMessageDesc("M05RepeatOrderPayment didn't get TransactionLog response.");
        }
        return message;
    }
}

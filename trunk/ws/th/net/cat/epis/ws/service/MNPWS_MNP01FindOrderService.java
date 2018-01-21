package th.net.cat.epis.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.m01_findorder.FindOrderRequest;
import th.net.cat.epis.ws.m01_findorder.FindOrderResponse;
import static th.net.cat.epis.service.adapter.AdapterConstants.MNP01_FindOrder_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

/**
 * Created by imake on 26/02/2017
 */
@Service
public class MNPWS_MNP01FindOrderService  implements IMNPService<FindOrderRequest, FindOrderResponse> {

    @Autowired
    th.net.cat.epis.ws.m01_findorder.m01_findordersi.M01FindOrderSI _mnp01service;

    @Override
    public TransactionLogCBO buildTransactionLogCBO() throws Exception {
        TransactionLogCBO transaction = new TransactionLogCBO();
        transaction.setTranID(AppUtil.generateTransactionID(15));
        transaction.setInterfaceNo(MNP01_FindOrder_INTERFACE_NO);
        transaction.setSourceSystem(SOURCE_SYSTEM);
        return transaction;
    }


    @Override
    public boolean validateRequest(FindOrderRequest model) {
        return true;
    }

    @Override
    public FindOrderResponse callInterface(FindOrderRequest request) {
        if(!validateRequest(request)) return null;
        return _mnp01service.findOrderInfo(request);
    }

    @Override
    public boolean isCalledSuccesful(String successCode, FindOrderResponse response) {
        return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
    }

    @Override
    public AlertMessage buildErrorMessage(FindOrderResponse model) {
        AlertMessage message = new AlertMessage("10","");
        if(model == null) {
            message.setMessageDesc("M01FindOrder either didn't get response or technical exception occurred. ");
        } else if(model.getTransactionLog() != null) {
            message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
            message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
        } else {
            message.setMessageDesc("M01FindOrder didn't get TransactionLog response.");
        }
        return message;
    }
}

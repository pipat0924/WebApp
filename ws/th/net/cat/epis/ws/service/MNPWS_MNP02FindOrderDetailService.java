package th.net.cat.epis.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailRequest;
import th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailResponse;

import static th.net.cat.epis.service.adapter.AdapterConstants.MNP02_FindOrderDetail_INTERFACE_NO;
import static th.net.cat.epis.service.adapter.AdapterConstants.SOURCE_SYSTEM;

/**
 * Created by imake on 26/02/2017
 */
@Service
public class MNPWS_MNP02FindOrderDetailService implements IMNPService<FindOrderDetailRequest, FindOrderDetailResponse> {

    @Autowired
    th.net.cat.epis.ws.m02_findorderdetail.m02_findorderdetailsi.M02FindOrderDetailSI _mnp02service;

    @Override
    public TransactionLogCBO buildTransactionLogCBO() throws Exception {
        TransactionLogCBO transaction = new TransactionLogCBO();
        transaction.setTranID(AppUtil.generateTransactionID(15));
        transaction.setInterfaceNo(MNP02_FindOrderDetail_INTERFACE_NO);
        transaction.setSourceSystem(SOURCE_SYSTEM);
        return transaction;
    }


    @Override
    public boolean validateRequest(FindOrderDetailRequest model) {
        return true;
    }

    @Override
    public FindOrderDetailResponse callInterface(FindOrderDetailRequest request) {
        if(!validateRequest(request)) return null;
        return _mnp02service.findOrderDetailInfo(request);
    }

    @Override
    public boolean isCalledSuccesful(String successCode, FindOrderDetailResponse response) {
        return (response == null) ? false : (response.getTransactionLog() == null) ? false : response.getTransactionLog().getDestinationReturnCode().equals(successCode) ;
    }

    @Override
    public AlertMessage buildErrorMessage(FindOrderDetailResponse model) {
        AlertMessage message = new AlertMessage("10","");
        if(model == null) {
            message.setMessageDesc("M2FindOrderDetail either didn't get response or technical exception occurred. ");
        } else if(model.getTransactionLog() != null) {
            message.setMessageCode(model.getTransactionLog().getDestinationReturnCode());
            message.setMessageDesc(model.getTransactionLog().getDestinationReturnDetails());
        } else {
            message.setMessageDesc("M2FindOrderDetail didn't get TransactionLog response.");
        }
        return message;
    }
}

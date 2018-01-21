package th.net.cat.epis.ws.service;

import org.springframework.stereotype.Service;

@Service
public class MNPWS_MNP01RetrieveOrderService {
	/*

	implements
    IMNPService<FindOrderDetailRequest, RetrieveOrderResponse> {
	
	@Autowired
	th.net.cat.epis.ws.mnp01_retrieveordersi.MNP01RetrieveOrderSI _mnp01service;

	@Override
	public RqHeader buildRqHeader(String loginUsername, String loginBranchCode)
			throws Exception {
		RqHeader rqHead = new RqHeader();
		rqHead.setFuncNm(MNP01_RetrieveOrderInfo_INTERFACE_NO);
		rqHead.setRqAppId(SOURCE_SYSTEM);
		rqHead.setRqDt(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		rqHead.setRqUID(AppUtil.generateTransactionID(15));
		rqHead.setTerminalId(loginBranchCode);
		rqHead.setUserId(loginUsername);
		return rqHead;
	}

	@Override
	public boolean validateRequest(RetrieveOrderRequest model) {
		return true;
	}

	@Override
	public RetrieveOrderResponse callInterface(RetrieveOrderRequest request) {
		if(!validateRequest(request)) return null;
		return _mnp01service.retrieveOrderInfo(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, RetrieveOrderResponse response) {
		return (response == null) ? false : (response.getRsHeader() == null) ? false : response.getRsHeader().getStatusCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(RetrieveOrderResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("MNP01RetrieveOrder either didn't get response or technical exception occurred. ");
		} else if(model.getRsHeader() != null) {
			message.setMessageCode(model.getRsHeader().getErrorCode());
			message.setMessageDesc(model.getRsHeader().getErrorDesc());
		} else {
			message.setMessageDesc("MNP01RetrieveOrder didn't get RsHeader response.");
		}
		return message;
	}
	*/
}

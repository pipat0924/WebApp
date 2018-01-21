package th.net.cat.epis.ws.service;

import org.springframework.stereotype.Service;

@Service
public class MNPWS_MNP02CreateOrderPaymentService {

   /*
		implements IMNPService<Creato, CreateOrderPaymentResponse> {

	@Autowired
	th.net.cat.epis.ws.mnp02_createorderpaymentsi.MNP02CreateOrderPaymentSI _mnp02service;

	@Override
	public RqHeader buildRqHeader(String loginUsername, String loginBranchCode)
			throws Exception {
		RqHeader rqHead = new RqHeader();
		rqHead.setFuncNm(MNP02_CreateOrderPayment_INTERFACE_NO);
		rqHead.setRqAppId(SOURCE_SYSTEM);
		rqHead.setRqDt(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		rqHead.setRqUID(AppUtil.generateTransactionID(15));
		rqHead.setTerminalId(loginBranchCode);
		rqHead.setUserId(loginUsername);
		return rqHead;
	}

	@Override
	public boolean validateRequest(CreateOrderPaymentRequest model) {
		return true;
	}

	@Override
	public CreateOrderPaymentResponse callInterface(CreateOrderPaymentRequest request) {
		if(!validateRequest(request)) return null;
		return _mnp02service.createOrderPayment(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, CreateOrderPaymentResponse response) {
		return (response == null) ? false : (response.getRsHeader() == null) ? false : response.getRsHeader().getStatusCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(CreateOrderPaymentResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("MNP02CreateOrderPayment either didn't get response or technical exception occurred. ");
		} else if(model.getRsHeader() != null) {
			message.setMessageCode(model.getRsHeader().getErrorCode());
			message.setMessageDesc(model.getRsHeader().getErrorDesc());
		} else {
			message.setMessageDesc("MNP02CreateOrderPayment didn't get RsHeader response.");
		}
		return message;
	}
	*/
}

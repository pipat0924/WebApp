package th.net.cat.epis.ws.service;

import org.springframework.stereotype.Service;

@Service
public class MNPWS_MNP03ReverseOrderPaymentService {
	/*

		implements IMNPService<ReverseOrderPaymentRequest, ReverseOrderPaymentResponse> {

	@Autowired
	th.net.cat.epis.ws.mnp03_reverseorderpaymentsi.MNP03ReverseOrderPaymentSI _mnp03service;

	@Override
	public RqHeader buildRqHeader(String loginUsername, String loginBranchCode)
			throws Exception {
		RqHeader rqHead = new RqHeader();
		rqHead.setFuncNm(MNP03_ReverseOrderPayment_INTERFACE_NO);
		rqHead.setRqAppId(SOURCE_SYSTEM);
		rqHead.setRqDt(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		rqHead.setRqUID(AppUtil.generateTransactionID(15));
		rqHead.setTerminalId(loginBranchCode);
		rqHead.setUserId(loginUsername);
		return rqHead;
	}

	@Override
	public boolean validateRequest(ReverseOrderPaymentRequest model) {
		return true;
	}

	@Override
	public ReverseOrderPaymentResponse callInterface(ReverseOrderPaymentRequest request) {
		if(!validateRequest(request)) return null;
		return _mnp03service.reverseOrderPayment(request);
	}

	@Override
	public boolean isCalledSuccesful(String successCode, ReverseOrderPaymentResponse response) {
		return (response == null) ? false : (response.getRsHeader() == null) ? false : response.getRsHeader().getStatusCode().equals(successCode) ;
	}

	@Override
	public AlertMessage buildErrorMessage(ReverseOrderPaymentResponse model) {
		AlertMessage message = new AlertMessage("10","");
		if(model == null) {
			message.setMessageDesc("MNP03ReverseOrderPayment either didn't get response or technical exception occurred. ");
		} else if(model.getRsHeader() != null) {
			message.setMessageCode(model.getRsHeader().getErrorCode());
			message.setMessageDesc(model.getRsHeader().getErrorDesc());
		} else {
			message.setMessageDesc("MNP03ReverseOrderPayment didn't get RsHeader response.");
		}
		return message;
	}
*/
}

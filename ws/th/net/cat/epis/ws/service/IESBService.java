package th.net.cat.epis.ws.service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;

/**
 * @param <REQ> - Request Stub class
 * @param <RES> - Response Stub class
 */
public interface IESBService <REQ, RES> {
	
	public boolean validateRequest(REQ model);
	public TransactionLogCBO buildTransactionLogCBO() throws Exception; 
	public RES callInterface(REQ request);
	public boolean isCalledSuccesful(String successCode, RES response);
	public AlertMessage buildErrorMessage(RES model);
	
}

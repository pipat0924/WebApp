package th.net.cat.epis.ws.service;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.ws.esblibs.cbos.TransactionLogCBO;
import th.net.cat.epis.ws.mnp.libs.header.RqHeader;

/**
 * @param <REQ> - Request Stub class
 * @param <RES> - Response Stub class
 */
public interface IMNPService <REQ, RES> {
	
	//public RqHeader buildRqHeader(String loginUsername, String loginBranchCode) throws Exception;
	public TransactionLogCBO buildTransactionLogCBO() throws Exception;
	public boolean validateRequest(REQ model);
	public RES callInterface(REQ request);
	public boolean isCalledSuccesful(String successCode, RES response);
	public AlertMessage buildErrorMessage(RES model);
	
}

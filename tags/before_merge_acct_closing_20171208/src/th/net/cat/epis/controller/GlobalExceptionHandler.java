package th.net.cat.epis.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		logger.info("SQLException Occured:: URL=" + request.getRequestURL());
		return "database_error";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
	@ExceptionHandler(Exception.class)
	public void handleIOException() {
		logger.error("IOException handler executed");
		// returning 404 error code
	}
	*/

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setStatusCode("10");
		Throwable throwable = ex;
		ex.printStackTrace();
		while (throwable != null) {
			errorInfo.getErrorList().add(new AlertMessage("", throwable.toString()));
			if (throwable instanceof NullPointerException) {
				for (StackTraceElement stackTrace : throwable.getStackTrace()) {
					String clsName = stackTrace.getClassName();
					if (!clsName.startsWith("java") && !clsName.startsWith("sun") && !clsName.startsWith("org.apache") && !clsName.startsWith("org.spring"))
						errorInfo.getErrorList().add(new AlertMessage("", clsName +" - Method: "+ stackTrace.getMethodName() +"(Line:"+ stackTrace.getLineNumber() +")"));
				}
			}
			throwable = throwable.getCause();
		}
		return errorInfo;
	}

	public static class ErrorInfo {
		private String statusCode;
		private List<AlertMessage> successList = new LinkedList<AlertMessage>();
		private List<AlertMessage> warningList = new LinkedList<AlertMessage>();
		private List<AlertMessage> errorList = new LinkedList<AlertMessage>();
		public String getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}
		public List<AlertMessage> getSuccessList() {
			return successList;
		}
		public void setSuccessList(List<AlertMessage> successList) {
			this.successList = successList;
		}
		public List<AlertMessage> getWarningList() {
			return warningList;
		}
		public void setWarningList(List<AlertMessage> warningList) {
			this.warningList = warningList;
		}
		public List<AlertMessage> getErrorList() {
			return errorList;
		}
		public void setErrorList(List<AlertMessage> errorList) {
			this.errorList = errorList;
		}
	}
	public static class AlertMessage {
		private String messageCode;
		private String messageDesc;
		public AlertMessage(String messageCode, String messageDesc) {
			this.messageCode = messageCode;
			this.messageDesc = messageDesc;
		}
		public String getMessageCode() {
			return messageCode;
		}
		public void setMessageCode(String messageCode) {
			this.messageCode = messageCode;
		}
		public String getMessageDesc() {
			return messageDesc;
		}
		public void setMessageDesc(String messageDesc) {
			this.messageDesc = messageDesc;
		}
	}
}
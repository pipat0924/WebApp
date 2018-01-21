package th.net.cat.epis.service.adapter;

public class AdapterConstants {

	public static final String F01_SERVER_URI = "http://F01_RetrieveInvoiceHeader/F01_RetrieveInvoiceHeaderSI";
	public static final String F01_WS_API = "http://10.32.23.134:7800/F01_RetrieveInvoiceHeaderSIExport1_F01_RetrieveInvoiceHeaderSIHttpService?wsdl";
	public static final String F01_NS_PREFIX = "f01";
	public static final String F01_RetrieveInvoice_INTERFACE_NO = "F01";
	
	public static final String F02_SERVER_URI = "http://F02_RetrieveInvoiceAccountCode/F02_RetrieveInvoiceAccountCodeSI";
	public static final String F02_WS_API = "http://10.32.23.134:7800/F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpService?wsdl";
	public static final String F02_NS_PREFIX = "f02";
	public static final String F02_RetrieveInvoiceAccountCode_INTERFACE_NO = "F02";
	
	public static final String F03_SERVER_URI = "http://F03_RetrieveInvoiceCharges/F03_RetrieveInvoiceChargesSI";
	public static final String F03_WS_API = "http://10.32.23.134:7800/F03_RetrieveInvoiceChargesSIExport1_F03_RetrieveInvoiceChargesSIHttpService?wsdl";
	public static final String F03_NS_PREFIX = "f03";
	public static final String F03_RetrieveInvoiceCharges_INTERFACE_NO = "F03";
	
	public static final String F04_SERVER_URI = "http://ESBLibs/INF/ESB/F4";
	public static final String F04_WS_API = "http://10.32.23.134:7800/F4_CreatePaymentSIExport1_F4_CreatePaymentSIHttpService?wsdl";
	public static final String F04_NS_PREFIX = "f4";
	public static final String F04_CreatePayment_INTERFACE_NO = "F4";

	public static final String F05_SERVER_URI = "http://F05_RetrieveServiceStatus/F05_RetrieveServiceStatusSI";
	public static final String F05_WS_API = "http://10.32.23.134:7800/F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpService?wsdl";
	public static final String F05_NS_PREFIX = "f05";
	public static final String F05_RetrieveServiceStatus_INTERFACE_NO = "F05";
	
	// F06_ReversePayment
	public static final String F06_SERVER_URI = "http://F06_ReversePayment/F06_ReversePaymentSI";
	public static final String F06_WS_API = "http://10.32.23.134:7800/F06_ReversePaymentSIExport1_F06_ReversePaymentSIHttpService?wsdl";
	public static final String F06_NS_PREFIX = "f06";
	public static final String F06_ReversePayment_INTERFACE_NO = "F06";
	public static final String F06_LOCAL_PART = "reversePayment";
	public static final String F06_LOCAL_PART_RESPONSE = "reversePaymentResponse";
	public static final String F06_MIME_HEADER_ACTION = F06_SERVER_URI+F06_LOCAL_PART;
	public static final String F06_ResponseSuccessCode = "0";
	
	public static final String F08_RetrieveWriteOffInvoice_INTERFACE_NO = "F08"; //th.net.cat.epis.ws.f08_writeoffinquirypos
	public static final String F09_RetrieveWriteOffPayment_INTERFACE_NO = "F09"; //th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos
	public static final String F10_CreateWriteOff_INTERFACE_NO = "F10"; //th.net.cat.epis.ws.f10_createwriteoffpos
	public static final String F11_ReverseWriteOff_INTERFACE_NO = "F11"; //th.net.cat.epis.ws.f11_reversewriteoffpos
	public static final String F13_RetrievePaymentHistory_INTERFACE_NO = "F13"; //th.net.cat.epis.ws.f13_retrievepaymenthistory
	public static final String F18_CreateRefund_INTERFACE_NO = "F18"; //th.net.cat.epis.ws.f18_createrefund
	public static final String F19_RetrieveAccount_INTERFACE_NO = "F19"; //th.net.cat.epis.ws.f19_retrieveaccount
	public static final String F20_F20RetrieveSubscr_INTERFACE_NO = "F20"; //th.net.cat.epis.ws.f20_retrievesubscrbyinv
	
	public static final String SERVER_URI = "http://ESBLibs/INF/ESB/F14";
	public static final String F14_WS_API = "http://10.32.23.134:7800/F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpService?wsdl";
	public static final String F14_NS_PREFIX = "f14";
	public static final String F14_RetrievePayment_INTERFACE_NO = "F14";
	
	//public static final String MNP01_RetrieveOrderInfo_INTERFACE_NO = "M01";
	//public static final String MNP02_CreateOrderPayment_INTERFACE_NO = "M02";
    //public static final String MNP03_ReverseOrderPayment_INTERFACE_NO = "M03";

	public static final String MNP01_FindOrder_INTERFACE_NO = "M01";
	public static final String MNP02_FindOrderDetail_INTERFACE_NO = "M02";
	public static final String MNP03_CreateOrderPayment_INTERFACE_NO = "M03";
	public static final String MNP04_ReverseOrderPayment_INTERFACE_NO = "M04";
	public static final String MNP05_RepeatOrder_INTERFACE_NO = "M05";

	public static final String SOURCE_SYSTEM = "EPIS";
	public static final String DESTINATION_SYSTEM = "Billing";
	
}

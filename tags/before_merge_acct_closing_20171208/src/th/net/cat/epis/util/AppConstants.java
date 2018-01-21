package th.net.cat.epis.util;

public class AppConstants {

	// set this flag as "false" when working offline (no api connection)
	public static boolean _IS_ONLINE_MODE = true;

	// Jasper Report
	public static final String JASPER_JRXML_PATH = "/report/jasper/template";
	public static final String JETT_EXCEL_PATH = "/report/excel/template";
	public static final String JASPER_LOGO_PATH = "/images/";
	public static final String JASPER_LOGO_FILENAME = "logo.gif";
	public static final String JASPER_NOIMG_FILENAME = "no_image.jpg";
	public static final String JASPER_REPORT_URL = "/downloadReport";
	public static final String JASPER_OUTPUT_PATH_PDF = "/report/jasper/pdf";
	public static final String JASPER_OUTPUT_PATH_XLS = "/report/jasper/xls";
	public static final String EXPORT_OUTPUT_PATH_XLS = "/output/export";
	public static final String EXPORT_OUTPUT_PATH_TXT = "C://cat//epis//online//";
	public static final String REPORT_TYPE_PDF = "PDF";
	public static final String REPORT_TYPE_XLS = "XLS";
	public static final String REPORT_TYPE_TXT = ".TXT";
	public static final String FILE_TYPE_PDF = ".pdf";
	public static final String FILE_TYPE_XLSX = ".xlsx";
	public static final String FILE_TYPE_XLS = ".xls";
	public static final String FILE_TYPE_ZIP = ".zip";
	public static final String FILE_TYPE_JRXML = ".jrxml";
	public static final String FILE_TYPE_JASPER = ".jasper";
	public static final String PDF = "pdf";

	public static final String DF = "0.00";
	public static final String EPIS_SYSTEM_ABVR = "EPS";

	public static final String ADMIN_ROLE = "PAY_Admin";
	public static final String ADMIN_TEXT = "Administrator";
	public static final String USER_TEXT = "User";

	public static final String TOPUP_FIELD_SERVICE_KEY = "service_key";
	public static final String TOPUP_FIELD_AUTH_TOKEN = "auth_token";
	public static final String TOPUP_FIELD_STATUS_CODE = "status_code";
	public static final String TOPUP_FIELD_TRANSID = "transid";
	public static final String TOPUP_FIELD_REF_TRANSID = "reftransid";
	public static final String TOPUP_STATUS_SUCCESS = "200";

	public static final String PAYMENT_TYPE_SERVICE_CHARGE = "SERVICE_CHARGE";
	public static final String PAYMENT_TYPE_WRITEOFF_SERVICE_CHARGE = "WRITEOFF_SERVICE_CHARGE";
	public static final String PAYMENT_TYPE_MNP = "MNP";
	public static final String PAYMENT_TYPE_TOPUP = "TOPUP";
	public static final String PAYMENT_TYPE_GFMIS = "GFMIS";
	public static final String PAYMENT_TYPE_OTHER = "OTHER";
	public static final String PAYMENT_TYPE_TBOSS = "TBOSS";
	public static final String PAYMENT_TYPE_OTBOSS = "OTBOSS";
	public static final String PAYMENT_TYPE_AGENT = "AGENT";
	public static final String PAYMENT_TYPE_PENALTY_CHARGE = "PENALTY_CHARGE";
	public static final String PAYMENT_TYPE_EXTEND_CHARGE = "EXTEND_CHARGE";
	public static final String PAYMENT_TYPE_FOREIGN = "foreign";

	public static final String DEDUCT_METHOD_3TREDECIM = "3TREDECIM"; // -
	public static final String DEDUCT_METHOD_69BIS = "69BIS"; // -
	public static final String DEDUCT_METHOD_69TRE = "69TRE"; // -
	public static final String DEDUCT_METHOD_PENALTY_IN = "PTY-IN"; // +
	public static final String DEDUCT_METHOD_PENALTY_OUT = "PTY-OUT"; // -
	public static final String DEDUCT_METHOD_FEE_IN = "FEE-IN"; // +
	public static final String DEDUCT_METHOD_FEE_OUT = "FEE-OUT"; // -
	public static final String DEDUCT_METHOD_RETENTION_IN = "RTTN-IN"; // +
	public static final String DEDUCT_METHOD_RETENTION_OUT = "RTTN-OUT"; // -
	public static final String DEDUCT_METHOD_COMPENSATION = "CMPST"; // -
	public static final String AFTERSALES_DISCOUNT_METHOD = "AFTS-DISC";

	public static final String PAY_METHOD_CASH = "CASH";
	public static final String PAY_METHOD_CHEQUE = "CHEQUE";
	public static final String PAY_METHOD_CREDITCARD = "CREDITCARD";
	public static final String PAY_METHOD_COUPON = "COUPON";
	public static final String PAY_METHOD_MONEYORDER = "MONEYORDER";
	public static final String PAY_METHOD_BANKTRANSFER = "BANKTRANSFER";
	public static final String PAY_METHOD_FOREIGNTRANSFER = "FOREIGNTRANSFER";
	public static final String PAY_METHOD_FOREIGNBANK = "FOREIGNBANK";
	public static final String PAY_METHOD_BILLEXCHANGE = "BILLEXCHANGE";
	public static final String PAY_METHOD_OFFSET = "OFFSET";
	public static final String PAY_METHOD_OTHER = "OTHER";
	public static final String PAY_METHOD_RECEIPTTAXINVOICE = "RECEIPTTAXINVOICE";
	public static final String PAY_METHOD_CANCELTAXINVOICE = "CANCELTAXINVOICE";
	public static final String PAY_METHOD_RECEIPT = "RECEIPT";
	public static final String PAY_METHOD_CANCEL = "CANCEL";

	public static final String ERP_ACCOUNT_DEBIT = "S";
	public static final String ERP_ACCOUNT_CREDIT = "H";
	public static final String ERP_TAX_CODE_DEBIT = "DB";
	public static final String ERP_TAX_CODE_CREDIT = "OB";
	public static final String ERP_ACCOUNT_TYPE_GENERAL_LEDGER = "S";
	public static final String ERP_ACCOUNT_TYPE_CUSTOMER = "D";
	public static final String ERP_ACCOUNT_TYPE_VENDOR = "K";

	public static final String ERP_ACCOUNT_NO_DEBIT_CASH = "11010102";
	public static final String ERP_ACCOUNT_NO_DEBIT_BANK_ACCOUNT = "CASH_SERVICE";
	public static final String ERP_ACCOUNT_NO_CREDIT_TBOSS = "11149102";
	public static final String ERP_ACCOUNT_NO_DEBIT_TAX_DEDUCTION = "11903102";
	public static final String ERP_ACCOUNT_NO_DEBIT_SUSPENSE_OUTPUT_TAX = "21115102";
	public static final String ERP_ACCOUNT_NO_CREDIT_SALES_TAX = "21110102";

	public static final String FULL_WORKING_PERIOD = "-1";
	public static final String MORNING_WORKING_PERIOD = "1";
	public static final String AFTERNOON_WORKING_PERIOD = "2";

	public static final String RECEIPT_NO_FLAG_WITH_TAX_INVOICE = "F";
	public static final String RECEIPT_NO_FLAG_WITHOUT_TAX_INVOICE = "S";
	public static final String RECEIPT_NO_FLAG_RECEIVE_ONLY = "R";
	public static final String RECEIPT_NO_FLAG_WH_ONLY = "T";

	public static final String RECEIPT_TYPE_FULL = "FULL";
	public static final String RECEIPT_TYPE_ABBREVIATION = "ABVR";
	public static final String RECEIPT_TYPE_GET_PARAM_FULL = "F";
	public static final String RECEIPT_TYPE_GET_PARAM_ABBREVIATE = "A";

	public static final String RECEIPT_ACCOUNT_TYPE_CODE_INDIVIDUAL = "I";
	public static final String RECEIPT_ACCOUNT_TYPE_CODE_ORGANIZATION = "O";
	public static final String RECEIPT_ACCOUNT_TYPE_CODE_GOVERNMENT = "G";

	public static final String RECEIPT_FORMAT_RECEIVE_ONLY = "RECEIVE_ONLY";
	public static final String RECEIPT_FORMAT_RECEIVE_WH = "RECEIVE_WH";
	public static final String RECEIPT_FORMAT_WH_ONLY = "WH_ONLY";

	public static final String INVOICE_FROM_BILLING = "N";
	public static final String INVOICE_FROM_WRITEOFF = "W";
	public static final String INVOICE_FROM_TBOSS = "T";
	public static final String INVOICE_OTHER_PAYMENT = "OTH";
	
	public static final String DAILY_PAYMENT_DEDUCT_REPORT_KEY ="DailyPaymentDeductReportStoreKey";
	// Enum Category
	public static final String ENUM_CATEGORY_AUTHEN = "authen.forcelogin";
	public static final String ENUM_PAYMENT_GL_CATEGORY_GROUP = "payment.gl.category.group";
	
	public static final String RECEIPT_PRINT_COPY_KEY ="RECEIPT_PRINT_COPY";
	public static enum LANGUAGE_KEY {
		THAI, ENG
	}
	
	public static final String RECEIPT_APPROVE_COMPLETED = "AC";
	
	public static final String CREDIT_LIMIT_CATEGORY = "creditlimit.category.code";

	public static final String RECEIPT_HEADER_EPO = "EPO";
	public static final String RECEIPT_HEADER_MNP = "MNP";
	public static final String CANCEL_TH = "ยกเลิก";

	public static enum RECEIPT_PRINT_TYPES {
		COPY, SUBSTITUE , REPRINT     // 1=COPY,2=SUBSTITUE,3=REPRINT
	}
	
	public static final String ACCOUNT_CATGORY_LOOKUP_NAME = "CAT_BILL_ACCOUNT_CATEGORY_LKP";
	public static final String MASTER_DATA_LOOKUP = "MASTER_DATA_LOOKUP";

	public static enum SOURCES {
		IBACSS, TBOSS , OTBOSS
	}

	public static final String ADVANCE_PAYMENT = "Advance Payment";

	public static final String FLG_HEADER_1 = "1"; //ใบเสร็จรับเงิน/ใบกำกับภาษี
	public static final String FLG_HEADER_2 = "2"; //ใบเสร็จรับเงิน
	public static final String FLG_HEADER_3 = "3"; //ใบกำกับภาษี
	public static final String TAX_CODE_NONVAT = "NO";

	public static final String ENDDAY_CLOSE_STATUS_W = "W";
	public static final String ENDDAY_CLOSE_STATUS_S = "S";
	public static final String ENDDAY_CLOSE_BACKDATE = "Y";
	
	public static final String BRANCH_DEFAULT = "00000";
	public static final String BRANCH_ADJUST_AMOUNT = "00001";
	
	public static final String VAT_RATE = "VAT_RATE";
	
	public static final String WT_GOLV_KEY = "2";
	public static final String WT_STR = "WT";
	public static final String WT_USAGE = "USAGE_CHARGE";
	public static final String WT_RENTAL = "RENTAL_CHARGE";
	public static final String WT_AVG = "AVERAGE_CHARGE";

	public static final String BIS_USAGE_CHARGE = "69BIS_USAGE_CHARGE";
	public static final String BIS_RENTAL_CHARGE = "69BIS_RENTAL_CHARGE";
	public static final String BIS_AVERAGE_CHARGE = "69BIS_AVERAGE_CHARGE";
	
	public static final String TRE_USAGE_CHARGE = "3TRE_USAGE_CHARGE";
	public static final String TRE_RENTAL_CHARGE = "3TRE_RENTAL_CHARGE";
	public static final String TRE_AVERAGE_CHARGE = "3TRE_AVERAGE_CHARGE";
	
    //----------------------for check paid status 27/09/2017---------------------
    public static final String R = "R";
    public static final String STATUS_R = "ยกเลิก";
    public static final String STATUS_A = "จ่ายล่วงหน้า";
    public static final String STATUS_F = "จ่ายเต็มจำนวน";
    public static final String STATUS_P = "จ่ายบางส่วน";
    public static final String STATUS_AC = "จ่ายล่วงหน้าหักล้างเรียบร้อย";
    public static final String STATUS_FC = "จ่ายเต็มจำนวนหักล้างเรียบร้อย";
	public static final String STATUS_PC = "จ่ายบางส่วนหักล้างเรียบร้อย";
	//----------------------for check paid status 27/09/2017---------------------
	
	 /*pay type*/
    public static final String cashCode = "CC";
    public static final String cashNameTH = "เงินสด";

    public static final String FLG_TRUE = "1";
    public static final String FLG_FALSE = "0";
    public static final String repairPrint = "พิมพ์ซ่อม";
    public static final String couponCode = "CP";
    public static final String chequeCode = "CH";
    public static final String otherCode = "OT";
    public static final String oFsetCode = "OF";
    public static final String billExchangeCode = "BX";
    public static final String moneyOrderCode = "MO";
    public static final String creditCardCode = "CR";
    public static final String moneyTransferTrCode = "TR";
    public static final String moneyTransferGfmisCode = "GF";
    public static final String moneyTransferTfCode = "TF";
    //---cancelReasonCode---
    public static final String REVERT_REPAY_CODE = "003";
    
    public static enum S004_STATUS_TYPES {
		S,A,C    // S = Sale , A = Active , C = Cancel 
	}
    public static enum S002_STATUS_TYPES {
		W,C    // W = Cancel and re new , C = Cancel 
	}

    //Added by Sayan.T : Start : 20171005_1453 , attachment
  	public static final String ATT_COMMON_PATH = "ATT_COMMON_PATH";
  	public static final String ATT_ADJUST_PATH = "ATT_ADJUST_PATH";
  	public static final String YES = "Y";
  	public static final String NO = "N";
  	
  	public static final String ADVANCE_CASE = "advance";
  	public static final String NORMAL_CASE = "normal";
  	
    //Added by Sayan.T : Start : 20171015_1552 , amount adjustment
  	public static final String SEQ_AMOUNT_ADJUSTMENT_NO = "AMOUNT_ADJUSTMENT_NO_SEQ";
  	public static final String SEQ_AMOUNT_ADJUSTMENT_NO_APV = "AMOUNT_ADJUSTMENT_NO_APV_SEQ";
  	public static final String ADJUST_APPROVE = "02";
  	public static final String ADJUST_REJECT = "03";
    //Added by Sayan.T : End
  	
  	public static final String CURRENCY_CODE_TH = "th_TH"; 
  	public static final String[] CURRENCY_CODE_EXCHANGE = {"en_EN"};
}
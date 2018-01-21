package th.net.cat.epis.controller.payment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.util.DateUtil;

@Controller
public class ExportingController {

	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	
	int headerRowCount = 1;
	
	String receiptUpdatedDateStr;
	String receiptDateStr;
	String currencyStr;
	String currencyRateStr;
	String branchNameStr;
	String branchCodeStr;
	String totalChargeStr;
	String chargeStr13;
	String chargeStr15;
//	String totalAmtStr;
	String vatStr;
	String wtStr;
	String transAmtStr;
	String taxBaseAmtStr13;
	String taxBaseAmtStr15;
	String branchAreaStr;
	String productCodeStr12;
	String productCodeStr18;
	String methodCodeStr;
	String chequeNoStr;
	String regionSapStr;
	String tradingPartStr;
    
    @ResponseBody
	@RequestMapping(value="genBillingIdoc.json", method=RequestMethod.GET)
	public void genBillingIdoc(@RequestParam("date") String dateParam) {
		
    	StringBuffer strBuff = new StringBuffer();
    	strBuff.setLength(0);
    	Date date = new Date();	//DateUtil.convertToDate(dateParam, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);	//
    	String dateStr = DateUtil.convertToString(date, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
    	
//    	int headerRowCount = 1;
//    	
//    	String receiptUpdatedDateStr;
//    	String receiptDateStr;
//    	String currencyStr;
//    	String currencyRateStr;
//    	String branchNameStr;
//    	String branchCodeStr;
//    	String totalChargeStr;
//    	String totalAmtStr;
//    	String vatStr;
//    	String wtStr;
//    	String branchAreaStr;
//    	String productCodeStr;
//    	String methodCodeStr;
//    	String chequeNoStr;
//    	String regionSapStr;
//    	String tradingPartStr;
    	
		episJdbcTemplate.query(
			" select "
			+"	r.UPDATEDTTM  "
			+"	,r.RECEIPTDTTM "
			+"	,inv_sum_ibacss.CURR , r.CURRENCYCODE, curr.MESSAGE "
			+"	,p.CURRENCYRATE "
			+"	,r.BRANCHNAME "
			+"	,r.BRANCHCODE "
			+"	,i.TOTALCHARGE "
			+"  ,i.CHARGE "
			+"	,inv_sum_ibacss.TOTAL_AMOUNT "
			+"	,i.VAT "
			+"	,d.WT, i.DEDUCTION "
			+"	,r.BRANCHAREA "
			+"	,inv_sum_ibacss.PRODUCT_CODE, tmp_inv_svc.PRODUCTCODE "
			+"	,m.CODE ,m.CHEQUENO "
			+"	,inv_sum_ibacss.REGION_SAP "
//			+"	--,PM:yyyymmdd(วันที่หักล้าง(posting date)) "
			+"	,inv_sum_ibacss.TRADING_PART "
				
			+" from "
			+" shop_closing shop_close "
//			+" --inner join tmpinvoiceservice tmp_inv_svc on shop_close.SHOP_CLOSING_ID = tmp_inv_svc.SHOP_CLOSING_ID "
			+" inner join correceipt r on shop_close.SHOP_CLOSING_ID = r.SHOP_CLOSING_ID "
			+" inner join corpayment p on p.PAYMENTID = r.PAYMENTID "
			+" inner join tmpinvoice i on p.PAYMENTID = i.PAYMENTID and r.RECEIPTID = i.RECEIPTID "
			+" inner join tmpinvoiceservice tmp_inv_svc on tmp_inv_svc.RECEIPTID = r.RECEIPTID and tmp_inv_svc.INVOICEID = i.INVOICEID "
			+" left join inv_summary_sap_ibacss inv_sum_ibacss on (''||inv_sum_ibacss.BI_REF) = i.INVOICENO "
			+" left join (select PAYMENTID, sum(AMOUNT) as WT from trsdeduction where DEDUCTIONTYPE in ('3TREDECIM','69BIS','69TRE') group by PAYMENTID) d on p.PAYMENTID = d.PAYMENTID "
			+" left join trsmethod m on m.PAYMENTID = p.PAYMENTID "
			+" left join maschangerate curr on curr.CURRENCYCODE = i.CURRENCYCODE "
			+" where "
			+" trunc(shop_close.CREATEDTTM) =  to_date('"+dateStr+"', 'YYYY-MM-DD') "
			+" and (r.ATTRIBUTES not LIKE '%R%' or (trunc(p.PAYMENTDATE) = trunc(r.CANCELDTTM))) "
			
			, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					
			    	receiptUpdatedDateStr = toLeftPaddingString(DateUtil.convertToString(row.getDate("UPDATEDTTM"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	receiptDateStr = toLeftPaddingString(DateUtil.convertToString(row.getDate("RECEIPTDTTM"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	currencyStr = toLeftPaddingString(row.getString("MESSAGE"), 5, ' ');
			    	currencyRateStr = toLeftPaddingString((row.getBigDecimal("CURRENCYRATE") != null) ? row.getBigDecimal("CURRENCYRATE").toString() : "", 11, ' ');
			    	branchNameStr = toLeftPaddingString(row.getString("BRANCHNAME"), 16, ' ');
			    	branchNameStr = branchNameStr.replace("ศูนย์บริการลูกค้า", "ศบล. ");
			    	if(branchNameStr.length() > 16){
			    		branchNameStr.substring(0,16);
			    	}
			    	branchCodeStr = toLeftPaddingString(row.getString("BRANCHCODE"), 4, ' ');
			    	if(branchCodeStr.length() > 4) {
			    		branchCodeStr = branchCodeStr.substring(1, 5);
			    	}
			    	BigDecimal totalCharge = (row.getBigDecimal("TOTALCHARGE") != null) ? row.getBigDecimal("TOTALCHARGE") : BigDecimal.ZERO;
			    	BigDecimal vat = (row.getBigDecimal("VAT") != null) ? row.getBigDecimal("VAT") : BigDecimal.ZERO;
			    	BigDecimal wt = (row.getBigDecimal("WT") != null) ? row.getBigDecimal("WT") : BigDecimal.ZERO;
			    	BigDecimal transAmt = totalCharge.subtract(wt);
			    	BigDecimal taxBaseAmt = totalCharge.subtract(vat);
			    	
			    	totalChargeStr = toLeftPaddingString( toNumberFormat(totalCharge, "0.00") , 13, ' ');
			    	chargeStr13 = toLeftPaddingString( toNumberFormat(row.getBigDecimal("CHARGE"), "0.00") , 13, ' ');
			    	chargeStr15 = toLeftPaddingString( toNumberFormat(row.getBigDecimal("CHARGE"), "0.00") , 15, ' ');
//			    	totalAmtStr = toLeftPaddingString( toNumberFormat(row.getBigDecimal("TOTAL_AMOUNT"), "0.00") , 13, ' ');
			    	vatStr = toLeftPaddingString( toNumberFormat(vat, "0.00") , 13, ' ');
			    	wtStr = toLeftPaddingString( toNumberFormat(wt, "0.00") , 13, ' ');
			    	transAmtStr = toLeftPaddingString( toNumberFormat(transAmt, "0.00") , 13, ' ');
			    	taxBaseAmtStr13 = toLeftPaddingString( toNumberFormat(taxBaseAmt, "0.00") , 13, ' ');
			    	taxBaseAmtStr15 = toLeftPaddingString( toNumberFormat(taxBaseAmt, "0.00") , 15, ' ');
			    	branchAreaStr = toLeftPaddingString(row.getString("BRANCHAREA"), 4, ' ');
			    	productCodeStr12 = toLeftPaddingString(row.getString("PRODUCT_CODE"), 12, ' ');
			    	productCodeStr18 = toLeftPaddingString(row.getString("PRODUCT_CODE"), 18, '0');
//			    	methodCodeStr = row.getInt("CODE");
			    	chequeNoStr = toLeftPaddingString(row.getString("CHEQUENO"), 18, ' ');
			    	regionSapStr = toLeftPaddingString(row.getString("REGION_SAP"), 12, ' ');
			    	tradingPartStr = toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	
					// Header Loop
					for (int i = 1; i <= 2; i++) {
						
						// Detail Loop
						if(headerRowCount % 2 > 0){	//Header row 1
							for (int detailRowCount = 1; detailRowCount <= 3; detailRowCount++) {
								// Header
								strBuff.append( toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( receiptDateStr );//B
								strBuff.append("RV");//C							
								strBuff.append("1000");//D
								strBuff.append( receiptDateStr ); //E
								strBuff.append( receiptDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( receiptUpdatedDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( receiptDateStr.substring(0,4) ); //N
								strBuff.append( toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":(detailRowCount==3)?"S":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":(detailRowCount==3)?"40":"  " );//T
								strBuff.append( "S" );//U
								if(detailRowCount == 1){
									strBuff.append( toRightPaddingString("11010102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( toRightPaddingString("11149102", 10, '0') );//V
								}else if(detailRowCount == 3){
									strBuff.append( toRightPaddingString("11903102", 10, '0') );//V
								}
								strBuff.append( toLeftPaddingString("", 10, ' ') );//W
								if(detailRowCount == 1){
									strBuff.append( transAmtStr );//X
									strBuff.append( transAmtStr );//Y
								}else if(detailRowCount == 2){	
									strBuff.append( totalChargeStr );//X
									strBuff.append( totalChargeStr );//Y
								}else if(detailRowCount == 3){
									strBuff.append( wtStr );//X
									strBuff.append( wtStr );//Y
								}
								strBuff.append( toLeftPaddingString("", 13, ' ') );//Z
								strBuff.append( toLeftPaddingString("", 13, ' ') );//AA
								strBuff.append( toLeftPaddingString("", 2, ' ') );//AB
								if(detailRowCount == 1 || detailRowCount == 2){
									strBuff.append( toLeftPaddingString("", 4, ' ') );//AC
								}else if(detailRowCount == 3){
									strBuff.append( branchCodeStr );//AC
								}
								strBuff.append( (detailRowCount == 1) ? branchAreaStr : toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( receiptDateStr );//AE
								strBuff.append( toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( (detailRowCount == 2) ? productCodeStr18 : toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}else if(detailRowCount == 2 || detailRowCount == 3){
									strBuff.append( toLeftPaddingString("PM:"+receiptDateStr, 50, ' ') );//AW
								}
								strBuff.append( (detailRowCount == 2) ? regionSapStr : toLeftPaddingString("", 12, ' ') );//AX
								strBuff.append( toLeftPaddingString("", 12, ' ') );//AY
								strBuff.append( toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( (detailRowCount == 1 || detailRowCount == 2) ? tradingPartStr : toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( toLeftPaddingString("", 3, ' ') );//CE
								strBuff.append( toLeftPaddingString("", 2, ' ') );//CF
								strBuff.append( toLeftPaddingString("", 10, ' ') );//CG
								strBuff.append( toLeftPaddingString("", 1, ' ') );//CH
								strBuff.append( toLeftPaddingString("", 15, ' ') );//CI
								strBuff.append( toLeftPaddingString("", 15, ' ') );//CJ
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CK
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CL
								strBuff.append( toLeftPaddingString("", 3, ' ') );//CM
								strBuff.append( toLeftPaddingString("", 4, ' ') );//CN
								strBuff.append( toLeftPaddingString("", 4, ' ') );//CO
//								strBuff.append( toLeftPaddingString("", 4, ' ') );//CP
								
								
								strBuff.append("\n");
							}
						}else{	//Header row 2
							for (int detailRowCount = 1; detailRowCount <= 2; detailRowCount++) {
								// Header
								strBuff.append( toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( receiptUpdatedDateStr );//B
								strBuff.append("DW");//C
								strBuff.append("1000");//D
								strBuff.append( receiptUpdatedDateStr ); //E
								strBuff.append( receiptDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( receiptUpdatedDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( receiptDateStr.substring(0,4) ); //N
								strBuff.append( toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":"  " );//T
								strBuff.append( "S" );//U
								if(detailRowCount == 1){
									strBuff.append( toRightPaddingString("21115102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( toRightPaddingString("21110102", 10, '0') );//V
								}
								strBuff.append( toLeftPaddingString("", 10, ' ') );//W
								strBuff.append( vatStr );//X
								strBuff.append( vatStr );//Y
								strBuff.append( taxBaseAmtStr13 );//Z
								strBuff.append( taxBaseAmtStr13 );//AA
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//AB
								strBuff.append( branchCodeStr );//AC
								strBuff.append( toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( toLeftPaddingString("", 8, ' ') );//AE								
								strBuff.append( toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( toLeftPaddingString("PM:"+receiptDateStr, 50, ' ') );//AW
								}else{
									strBuff.append( toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}
								strBuff.append( regionSapStr );//AX
								strBuff.append( productCodeStr12 );//AY
								strBuff.append( toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( toRightPaddingString(""+detailRowCount, 3, '0') ); //CE
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//CF
								if(detailRowCount == 1){
									strBuff.append( toRightPaddingString("21115102", 10, '0') );//CG
									strBuff.append( "S" );//CH
								}else if(detailRowCount == 2){
									strBuff.append( toRightPaddingString("21110102", 10, '0') );//CG
									strBuff.append( "H" );//CH
								}
								strBuff.append( taxBaseAmtStr15 );//CI 
								strBuff.append( taxBaseAmtStr15 );//CJ 
								strBuff.append( vatStr );//CK
								strBuff.append( vatStr );//CL
								strBuff.append( "MWS" );//CM
								strBuff.append( "MWAS" );//CN
								strBuff.append( branchCodeStr );//CO
//								strBuff.append( toLeftPaddingString("", 4, ' ') );//CP
								
								
								strBuff.append("\n");
							}
						}
						headerRowCount++;
					}
				}
			}
		);
		
		String path = "/home/epis_user/upload/";//"D:\\temp\\";	//
		writeFileData(path+"EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(new Date(), "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC", strBuff);
	}
    
    
    
    
    private String toFixLengthString(String str, int length){
    	if(str == null){
    		str = "";
    	}
    	return String.format("%-"+length+"s", str);
    }
    
    private String toLeftPaddingString(String str, int length, Character fillingChar){
    	if(str == null){
    		str = "";
    	}
    	return String.format("%-"+length+"s", str).replace(' ', fillingChar);
    }
    
    private String toRightPaddingString(String str, int length, Character fillingChar){
    	if(str == null){
    		str = "";
    	}
    	return String.format("%"+length+"s", str).replace(' ', fillingChar);
    }
    
    private String toCurrencyFormat(BigDecimal amt, String format){
    	String str = "";
    	if(amt != null){
    		DecimalFormat formatter = new DecimalFormat(format);
    		str = formatter.format(amt).replace(".", "");
    	}
    	return str;
    }
    
    private String toNumberFormat(BigDecimal amt, String format){
    	if(amt == null){
    		amt = BigDecimal.ZERO;
    	}
		DecimalFormat formatter = new DecimalFormat(format);
    	return formatter.format(amt);
    }
    
    private void writeFileData(String filePath, StringBuffer data){
//    	PrintWriter writer = null;
    	OutputStreamWriter writer = null;
		try{
//		    writer = new PrintWriter(filePath, "UTF-8"); 
//			writer = new PrintWriter(filePath, "tis-620"); //Unix Ansi
			/*writer = new PrintWriter(filePath, "windows-1252");
		    writer.print(data);*/
		    
		    
		    writer = new OutputStreamWriter(new FileOutputStream(filePath, true), "Cp1252");
			writer.append(data);
		    
		} catch (IOException e) {
		   e.printStackTrace();
		}finally{
			try{
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
	
}

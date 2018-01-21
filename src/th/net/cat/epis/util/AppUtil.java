package th.net.cat.epis.util;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_COUPON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import org.joda.time.DateTime;
import org.joda.time.Days;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.MasterDataDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.initial.AfterInitialConfiguration;

public class AppUtil {

	public static String DB_SCHEMA = "inventory.";

	public static final Locale LOCALE_THAI = new Locale("th", "TH");
	public static final Locale LOCALE_ENG = new Locale("en", "EN");
	public static SimpleDateFormat formatter_thai_full = new SimpleDateFormat("dd/MM/yyyy", LOCALE_THAI);
	public static SimpleDateFormat formatter_time = new SimpleDateFormat("k:mm:ss ", LOCALE_ENG);
	public static SimpleDateFormat formatter_datetime = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a", LOCALE_THAI);
	public static SimpleDateFormat formatter_yyyyMMdd = new SimpleDateFormat("yyyyMMdd", LOCALE_ENG);
	
	public static SimpleDateFormat formatter_skin = new SimpleDateFormat("dd/MM/yyyy h:mm", LOCALE_ENG);
	
	public static SimpleDateFormat formatter_TH_SHORT = new SimpleDateFormat("dd MMM yyyy", LOCALE_THAI);
	//public static SimpleDateFormat formatter_TH_DATE = new SimpleDateFormat("dd MMMMM yyyy", LOCALE_THAI);
	public static SimpleDateFormat formatter_TH_DATE = new SimpleDateFormat("dd MMM yyyy", LOCALE_THAI);
	public static SimpleDateFormat formatter_TH_TIME = new SimpleDateFormat("HH:mm นน.", LOCALE_THAI);
	
	public static SimpleDateFormat formatter_EN = new SimpleDateFormat("dd/MM/yyyy", LOCALE_ENG);
	public static SimpleDateFormat formatter_EN_XML = new SimpleDateFormat("yyyy/MM/dd", LOCALE_ENG);
	public static SimpleDateFormat formatter_EN_TIME = new SimpleDateFormat("HH:mm:ss", LOCALE_ENG);
	public static SimpleDateFormat formatter_EN_TIME2 = new SimpleDateFormat("HH:mm", LOCALE_ENG);
	public static SimpleDateFormat formatter_EN_FULL = new SimpleDateFormat("ddMMyyyy HH:mm:ss", LOCALE_ENG);
	public static SimpleDateFormat formatter_EN_FULL2 = new SimpleDateFormat("dd/MM/yyyyHH:mm", LOCALE_ENG);	
	
	public static DecimalFormat formatter_PRICE = new DecimalFormat("###,##0.##");
	public static DecimalFormat formatter_PRICE2 = new DecimalFormat("###,##0.00");
	public static DecimalFormat formatter_PRICE_NOTINDECATE = new DecimalFormat("######.##");
	
	public static final String[] monthThai = new String[]{"มกราคม","กุมภาพันธ์","มีนาคม","เมษายน","พฤษภาคม","มิถุนายน","กรกฏาคม","สิงหาคม","กันยายน","ตุลาคม","พฤศจิกายน","ธันวาคม"};
	public static final String [] ENG_CHARs = new String [] {"A", "B", "C" , "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	
	public static final String TYK = "tyk";
	public static final Character  ACTIVE_CASE = 'Y';
	public static final Character  NONE_ACTIVE_CASE = 'N';
	public static final String  ACTIVE_CASE_STR = "Y";
	public static final String  NONE_ACTIVE_CASE_STR = "N";
	
	public static final String MEMBER_USERS_PROFILE_ALIAS = "usersMapGroup";
	
	public static final String KEY_PASS_GEN = "444";
    
    public static final int SQL_LIKE_BEGIN = 1;
    public static final int SQL_LIKE_END = 2;
    public static final int SQL_LIKE_CONTAIN = 3;
    
    public static final String MATERIAL_TYPE = "MAT_TYPE";
    public static final String CURRENCY_TYPE = "CUR_TYPE";
    public static final String CURRENCY_TYPE_ASSET = "CUR_TYPE_ASSET";
    
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    private static DateFormat formatter_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    
	public static Integer parseInteger(String val,Integer defalultVal){
		try{
			val = StringUtils.remove(val, ",");
			return Integer.parseInt(val);
		}catch(NumberFormatException ex){}
		return defalultVal;
	}
	
	public static BigDecimal parseBigDecimal(String val,BigDecimal defalultVal){
		try{
			return new BigDecimal(val);
		}catch(NumberFormatException ex){}
		return defalultVal;
	}
	
	public static String toString(Object val){
		String vl = "";
		if (null == val) return "";
		try{
			vl = val.toString();
		}catch (Exception e) {
			vl = "";
		}
		return vl;
	}
	
	public static String toString(String val, String removetext, String replacetext){
		String vl = "";
		if (null == val) return "";
		try{
			vl = StringUtils.replace(val.toString(), removetext, replacetext);
		}catch (Exception e) {
			vl = "";
		}
		return vl;
	}
	
	public static String toThaiString(String val) {
		if (!StringUtils.isEmpty(val))
			return new String(val.getBytes(), Charset.forName("TIS-620"));
		else
			return "";
	}
	
	public static String toCurrencyString(BigDecimal value) {
		if (value == null) return "";
		return AppUtil.formatter_PRICE.format(value);
	}
	
	public static String toCurrencyPrint(BigDecimal value) {
		if (value == null) return "";
		return AppUtil.formatter_PRICE2.format(value);
	}
	
	public static String toCurrencyPrint(Double value) {
		if (value == null) return "";
		return AppUtil.formatter_PRICE2.format(value);
	}
	
	public static String toNumberPrint(Integer value) {
		return NumberFormat.getNumberInstance(Locale.US).format((value));
	}
	
	public static Float parseFloat(String val,float defaultVal){
		try{
			return Float.parseFloat(val);
		}catch(NumberFormatException ex){}
		return defaultVal;
	}
	
	public static boolean isStringHasValue(String value){
		if(value!=null&&!"".equals(value))
			return true;
		return false;
	}
	
	public static boolean isCollectionHasValue(List<?> values) {
		return null != values && ! values.isEmpty() ;		
	}
	
	public static String formattDateThai(Date input) {
        String returnFormatt = new String("");
        try {
            returnFormatt = formatter_thai_full.format(input);

        } catch (Exception error) {

        }

        return returnFormatt;
    }
	
	public static String formattTime(Date input){
		String returnVal = "";
		try{
			returnVal = formatter_time.format(input);
		}catch(Exception error){}
		return returnVal;
	}
	
	public static Date  convertDateThai(String input){
		Date d = null;
		try{
			d = formatter_thai_full.parse(input);
		}catch(Exception ex){
			ex.printStackTrace();
			d = new Date();
		}
		return d;
	}
	
	public static String genSQLConcat(String[] vals){
		String valGen = "CONCAT(";
		if(vals!=null){
			int i =0;
			for(String val:vals){
				if(i!=0){
					valGen += ",";
				}
				valGen += val;
				i++;
			}
		}
		valGen += ")";
		return valGen;
	}

	public static String getJSONSaveResult(boolean result, String detail){
		return "{\"result\":" + Boolean.toString(result) + ",\"detail\":\"" + detail + "\"}";
	}
	
	public static void drawJson(HttpServletRequest request, HttpServletResponse response, String jsonText) throws IOException{
    	jsonText = jsonText.replaceAll("(\\r|\\n)+", "");
    	response.setContentType("application/json"); 
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
		out.println(jsonText);
		out.close();
	}
	
	public static void drawHTML(HttpServletRequest request, HttpServletResponse response, String htmlText)throws IOException{
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter  out = response.getWriter();
		out.write(htmlText);
		out.close();
	}

	 public static String getTempFileName(String fileName){
		 String fName = "";
		 String suffix = "";
	     if (fileName == null)
	     {
	        throw new IllegalArgumentException("file name == null");
	     }
	     int pos = fileName.lastIndexOf('.');
	     if (pos > 0 && pos < fileName.length() - 1)
	     {
	    	 suffix = fileName.substring(pos + 1);
	    	 fName = fileName.substring(0, pos);
	     }
	     
	     if(! suffix.trim().equals("")){
	    	 fileName = fName + "_" + new Date().getTime() + "." + suffix;
	     }else{
	    	 fileName = fName + "_" + new Date().getTime();
	     }
	     return fileName;
	 }

	public static int calculatePageSize(int allsize, int limitsize) {
		/*int sizepagesRowTotal = allsize / limitsize;
		int mod = allsize % limitsize;
		
		if(mod >= (limitsize / 2)){
			sizepagesRowTotal += 1;
		}*/
		
		if (allsize <= limitsize) return 1;
		int sizepagesRowTotal = allsize / limitsize;
		int mod = allsize % limitsize;
		if (mod > 0)
			sizepagesRowTotal ++;
		return sizepagesRowTotal;
	}
	
	public static String formatNotEnd(String val, String chk) {
		if(val.endsWith(chk)){
			val = val.substring(0, val.length() - 1);
		}
		return val;
	}
	
	public static BigDecimal calculateVat(BigDecimal amount, BigDecimal vat) {
		BigDecimal r1 = amount.multiply(vat);
		BigDecimal r2 = new BigDecimal(vat.floatValue() + 100f);
		return r1.divide(r2,2, RoundingMode.HALF_UP);
	}
	
	 public static String Unicode2ASCII(String unicode) {
		 if (isEmpty(unicode)) {
			 return "";
		 }
		 StringBuffer ascii = new StringBuffer(unicode);
		 int code;
		 for (int i = 0; i < unicode.length(); i++) {
			 code = (int) unicode.charAt(i);
			 if ((0xE01 <= code) && (code <= 0xE5B))
				 ascii.setCharAt(i, (char) (code - 0xD60));
		 }
		 return ascii.toString();
	 }
	 
	 public static String getActiveFlag(boolean flag) {
		 return flag ? ACTIVE_CASE.toString() : NONE_ACTIVE_CASE.toString(); 
	 }
	 
	 public static Boolean parseActiveFlag(String flag) {
		 if ( null == flag ) return false;
		 return  ACTIVE_CASE.toString().equals(flag);
	 }
	 
	 public static boolean unZip(InputStream zipFile, String outputFolder){
		 
	     byte[] buffer = new byte[1024];
	 
	     try{
	 
	    	//create output directory is not exists
	    	File folder = new File(outputFolder);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	 
	    	//get the zip file content
	    	ZipInputStream zis = new ZipInputStream(zipFile);
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();
	 
	    	while(ze!=null){
	 
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);
	 
	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();	 
	            FileOutputStream fos = new FileOutputStream(newFile);             	 
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	 
	        zis.closeEntry();
	    	zis.close();	 
	 
	    	return true;
	    }catch(IOException ex){
	       ex.printStackTrace(); 
	    }
	     return false;
	   } 
	 
	 public static String getActiveText(String val) {
		 if ( null == val ) return "";
		 if (ACTIVE_CASE.toString().equals(val))
			 return "Yes";
		 else if (NONE_ACTIVE_CASE.toString().equals(val))
			 return "No";
		 return "";
	 }
	 
		
	 public static String formatYYYYMMDD(String dateString) {
		 if(StringUtils.isNotEmpty(dateString) && dateString.length() == 10) {
			return dateString.substring(6, 10) + dateString.substring(3, 5) + dateString.substring(0, 2);
		 }
    	 return StringUtils.EMPTY;
    }
	 
	public static String buildBranchIndex(String dataString, int index) {
		if(StringUtils.isNotEmpty(dataString)) {
			switch(index) {
				case 0 : return (dataString.length() > 0) ? dataString.substring(0, 1): "-";
				case 1 : return (dataString.length() > 1) ? dataString.substring(1, 2): "-";
				case 2 : return (dataString.length() > 2) ? dataString.substring(2, 3): "-";
				case 3 : return (dataString.length() > 3) ? dataString.substring(3, 4): "-";
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static Date localizeDate(String input) { 
		return getDate(input, "dd/MM/yyyy HH:mm");
	}

	public static Date getDate(String strDateTime, String pattern) {
        try {
            if (strDateTime != null && strDateTime.length() > 0) {
                SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
                Date dtDate = format.parse(strDateTime);

                return dtDate;

            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
	
	 public static String format(Date dt, String pattern) {
        String strDate = "";
        try {
            if (dt != null) {
                java.text.Format formatter = new java.text.SimpleDateFormat(pattern, Locale.US);
                strDate = formatter.format(dt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return strDate.toUpperCase();
    }
	
	public static String format_yyyyMMdds(Date dt) {
		return format(dt, "yyyyMMdd");
	}
	
	public static String format_ddMMyyyyHHmmss(Date dt) {
        return format(dt, "dd-MM-yyyy HH:mm:ss");
    }
	
	public static String format_ddMMyyyyHHmm(Date dt) {
        return format(dt, "dd/MM/yyyy HH:mm");
    }

	/**
	 * Set time as 00:00:00 for given date
	 * @param dt
	 * @return Date with time set 00:00:00
	 */
	public static Date setStartOfDayTime(Date dt) {
		return setTimeToDate(dt,0,0,0);
	}
	/**
	 * Set time as 23:59:59 for given date  
	 * @param dt - Date
	 * @return Date with time set 23:59:59
	 */
	public static Date setEndOfDayTime(Date dt) {
		return setTimeToDate(dt, 23, 59, 59);
	}
	public static Date setBeforeNoonTime(Date dt) {
		return setTimeToDate(dt, 11, 59, 59);
	}
	public static Date setNoonTime(Date dt) {
		return setTimeToDate(dt, 12, 0, 0);
	}
	private static Date setTimeToDate(Date dt, int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), hourOfDay, minute, second);
		return cal.getTime();
	}
	
	public static String generatedPassword(String passwordToHash) {
//        String passwordToHash = "password";
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } return generatedPassword;
    }
	
	public synchronized static String generateTransactionID (int length) {
		String id = "EP"+String.valueOf(System.nanoTime());
		String idS;
		if(id.length() >= length) {
			idS = id.substring(0, length);
		} else {
			idS = id.toString();
		}
		return idS;
	}
	
	public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
		if(date == null) return null;
	    return new java.sql.Date(date.getTime());
	}
	
	public static void updateCoupon(List<SettlePaymentDTO.Method> methods) throws Exception{
		String branchCode = EpContextHolder.getContext().getBranchCode();
		String currentDate = DateUtil.convertToString(new Date());
		for (SettlePaymentDTO.Method method : methods) {
			if (PAY_METHOD_COUPON.equals(method.getType())) {
				String jsonStringResult = callURL("http://10.44.1.4:80/InsaleManagement/Services_MS?table=import_coupon&type=update"
													+"&id="+method.getCouponNo()
													+"&branchupdate="+branchCode
													+"&expireday="+currentDate
													+"&status=S");
				System.out.println("Coupon No : "+method.getCouponNo()+", jsonStringResult: " + jsonStringResult);
				
//				try {  
					JSONArray jsonArray = new JSONArray(jsonStringResult);
					System.out.println("\n\njsonArray: " + jsonArray);
					if(jsonArray != null && jsonArray.length() == 1){
						JSONObject jsonObject = jsonArray.getJSONObject(0);
						String status = jsonObject.has("status")?jsonObject.getString("status"):null;
						String error = jsonObject.has("Error")?jsonObject.getString("Error"):null;
						if(status != null && status.equals("S")){
							//Success
						}else if(error != null){
							if(error.equals("N")){
								//No data found
							}else if(error.equals("U")){
								//Coupon used
							}
						}
					}
					
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
				
			}
		}
	}

	public static String callURL(String myURL) throws Exception{
		System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		HttpURLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = (HttpURLConnection)url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
//		in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
//			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if(urlConn != null){
				urlConn = null;
			}
		}

		return sb.toString();
	}
	
	/**
	 *  Method for mask credit card number
	 *  input 1 : credit card number
	 *  input 2 : format mask eq ****-****-****-####, ####
	 *  // Fix By EPIS8 29/12/2016 issue no 57, 159
	 */
	public static String maskCreditCardFromString(String cardNumber, String mask) {
    	StringBuilder resultBd = new StringBuilder();
    	String cardNumberArr[] = cardNumber.split("\\+");
    	for (int i = 0; i < cardNumberArr.length; i++) {
    		boolean isCreditCard = false;
    		String tmp = extractNumber(cardNumberArr[i]);
			if (tmp.length() == 16) {
				isCreditCard = true;
				String rCreditNoArr[] = cardNumberArr[i].split("\\:");
				
				// format the number
		        int index = 0;
		        StringBuilder maskedNumber = new StringBuilder();
		        for (int x = 0; x < mask.length(); x++) {
		            char c = mask.charAt(x);
		            if (c == '#') {
		                maskedNumber.append(rCreditNoArr[1].trim().charAt(index));
		                index++;
		            } else if (c == '*') {
		                maskedNumber.append(c);
		                index++;
		            } else {
		                maskedNumber.append(c);
		            }
		        }
		        rCreditNoArr[0] = rCreditNoArr[0].trim().concat(": ").concat(maskedNumber.toString());
		        cardNumberArr[i] = rCreditNoArr[0];
			}
			resultBd.append(cardNumberArr[i]+ " + ");
		}
        return resultBd.substring(0, resultBd.length()-2);
    }
	
	/**
	 *  Method for hideWTNumber
	 *  input 1 : paymentCase
	 *  // Fix By EPIS8 29/12/2016
	 */
	public static String hideWTNumber(String paymentCase) {
    	StringBuilder resultBd = new StringBuilder();
    	String paymentCaseAr[] = paymentCase.split("\\+");
    	for (int i = 0; i < paymentCaseAr.length; i++) {
    		if(paymentCaseAr[i].contains("WT")){
    			paymentCaseAr[i] = " WT ";
    		}
    		if(resultBd.length() > 0)
    			resultBd.append("+");
    		resultBd.append(paymentCaseAr[i]);
		}
    	return resultBd.toString();
    }
	
	/**
	 *  Method for mask credit card number
	 *  input 1 : credit card number
	 *  input 2 : format mask eq ****-****-****-####, ####
	 *  Use '^' Escape character to display
	 *  // Fix By EPIS8 29/12/2016 issue no 57, 159
	 */
	public static String maskCreditCardNumber(String cardNumber, String mask) {
		// format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int x = 0; x < mask.length(); x++) {
            char c = mask.charAt(x);
            if (c == '#') {
                maskedNumber.append(cardNumber.trim().charAt(index));
                index++;
            } else if (c == '*') {
                maskedNumber.append(c);
                index++;
            }else if (c == '^') {	// Escape character to display
                index++;
            } else {
                maskedNumber.append(c);
            }
        }
        return maskedNumber.toString();
    }
	
	/**
	 * Extract Number from String
	 * @param str
	 * @return
	 * // Fix By EPIS8 29/12/2016 issue no 57, 159
	 */
	public static String extractNumber(final String str) { 
        if(str == null || str.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;                
            }
        }

        return sb.toString();
    }
	/**
	 * Calculate aging from two Date
	 */
	public static  int calculateAging(Date fromDate , Date toDate) {
		DateTime dtForm = new DateTime(fromDate.getTime());
		DateTime dtTo = new DateTime(toDate.getTime());
		return Days.daysBetween(dtForm, dtTo).getDays();
	}
	
	public static String removeLastChar(String input, String chac){
		String paymentCase = input;
			if(input != null && input.indexOf("+") > 0){
			String paymentCaseArr[] = input.split(chac);
			for (int i = 0; i < paymentCaseArr.length; i++) {
				if(paymentCaseArr[paymentCaseArr.length-1].length() <= 1){	// Remove "+" last index
					paymentCase = input.substring(0, input.length()-3);
				}else{
					paymentCase = input;
				}
			}
		}
		return paymentCase;
	}
	
	public static String toLeftPaddingString(String str, int length, Character fillingChar){
    	if(str == null){
    		str = "";
    	}
    	return String.format("%-"+length+"s", str).replace(' ', fillingChar);
    }
    
	public static String toRightPaddingString(String str, int length, Character fillingChar){
    	if(str == null){
    		str = "";
    	}
    	return String.format("%"+length+"s", str).replace(' ', fillingChar);
    }
	
	public static String toNumberFormat(BigDecimal amt, String format){
    	if(amt == null){
    		amt = BigDecimal.ZERO;
    	}
		DecimalFormat formatter = new DecimalFormat(format);
    	return formatter.format(amt);
    }
	
	public static List<MasterDataDTO> getGroupMasterData(String key) throws Exception {
        return AfterInitialConfiguration.masterDataLookup.get(key);
    }
	
	public static MasterDataDTO getSingleMasterData(String key) throws Exception {
        return AfterInitialConfiguration.masterDataLookup.get(key).get(0);
    }
	/*
	 * Usage e.g. group = "SYSTEM_CONFIG" , keyGroup = "ATT_PATH,ATT_ADJUST_PATH"
	 */
	public static List<MasterDataDTO> getSpecificGroupData(String group,String keyGroup){
		String[] keyGroupArr = keyGroup.split(",");
    	List<MasterDataDTO> mdList = AfterInitialConfiguration.masterDataLookup.get(group);
    	List<MasterDataDTO> results = new ArrayList<MasterDataDTO>();
    	for(String key : keyGroupArr){
    		for(MasterDataDTO dto : mdList){
    			if(dto.getKey().equals(key)){
    				results.add(dto);
    				break;
        		}
    		}
    	}
        return results;
	}
	
	public static void pushReportToOutputStream(HttpServletResponse response, List<JasperPrint> jasperPrints) throws IOException, JRException  {
		OutputStream outputStream = response.getOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}
	
	public static boolean isWindows(){
		return (OS.indexOf("win") >= 0);
	}
	
	public static String getDateYYYYMMDDHHMMSS(){
		Date today = Calendar.getInstance().getTime();
		return formatter_YYYYMMDDHHMMSS.format(today);
		
	}

	public static void main(String[] args){
		System.out.println(getDateYYYYMMDDHHMMSS());
	}
	
}

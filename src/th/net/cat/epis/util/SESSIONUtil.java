package th.net.cat.epis.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SESSIONUtil {

	public static final String STRUCTUREPAGE = "MUNS_PAGESTRUCTURE";
	public static final String MAINPAGE = "MAIN_PAGE";
	public static final String MAINPAGEPRINT = "MAIN_PAGE_PRINT";
	public static final String MAINMENU = "MUNS_MENUMAIN";
	
	public static final String AUTHENOBJ = "AUTHENOBJ";	
	
	public static final String KEYGROUP_ACCESS_BY_BLOG = "ACCESSKEYGROUP_BY_MANAGEBLOG"; 
	public static final String HOLD_KEY_BLOG = "HOLD_KEY_BLOG";
	
	
	public static final String KEYACCESS_ACTIVATED = "KEYACCESS_ACTIVATED";
	public static final String ACCESS_ACTIVATED_ENABLE = "6uyiugggrtuuyuikhgkrtikykyuliyli";
	
	public static final String MENU_SESSION = "menuSession";
	public static final String USER_SEESION = "userSession";
	
	public static final String EVENT_SESSION = "eventSession";
	
	public static final String ROUNDS_SESSION = "roundsSession";
	public static final String RESERV_SESSION = "reserveSession";
	public static final String POS_PARAM_SESSION = "posParamSession";
	public static final String RESERV_REQUEST_SESSION = "reserveRequestSession";
	public static final String RESERV_RESPONSE_SESSION = "reserveResponseSession";

	public static final String DISCOUNT_RESPONSE_SESSION = "discountResponseSession";
	public static final String CANCEL_RESPONSE_SESSION = "cancelResponseSession";
	
	public static final String CAPT_RESETPASS = "capthaSession";
	
    public static final String CUST_SEESION = "custSession";
	
	public static final String ACTIVE_MENUSESSION = "activeMenuSession";
	public static final String REPORT_MENU_SESSION = "reportMenuSession";
	public static final String RESERVE_MENU_SESSION = "reserveMenuSession";
	public static final String MASTER_EVENT_MENU_SESSION = "mastereventMenuSession";
	public static final String MASTER_USER_MENU_SESSION = "masteruserMenuSession";
	public static final String MASTER_COUNTRY_MENU_SESSION = "mastercountryMenuSession";
	
	public static final Map<String, String> IGNORE_PAGE = new HashMap<String, String>();
	
	static{
		IGNORE_PAGE.put("/se/pa/confachtm.html", "1");
		IGNORE_PAGE.put("/se/pa/canfachtm.html", "2");
	}
	
	public static void setStructurePageNotAuthen(HttpServletRequest request, String mainpage){
		/*HttpSession session = request.getSession(false);
		session.setAttribute(SESSIONUtil.STRUCTUREPAGE, request.getContextPath() + "/pub/main.html?t=" + AppUtil.getRandomParam());
		if(null != mainpage)
			session.setAttribute(SESSIONUtil.MAINPAGE, request.getContextPath() + mainpage);
			*/
	}
	
}

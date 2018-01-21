package th.net.cat.epis.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.codec.binary.StringUtils;
//import static org.apache.commons.lang.StringUtils.equals;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import th.net.cat.epis.entity.MasAgent;
import th.net.cat.epis.entity.Menu;
import th.net.cat.epis.repo.AgentRepository;
import th.net.cat.epis.repo.MenuRepository;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private MenuRepository menuRepository;

    @Autowired AgentRepository agentRepository;

	static final String AUTHORITY_ADMIN = "ADMIN";
	static final String AUTHORITY_AGENT = "AGENT";
	static final String AUTHORITY_BASIC = "BASIC";
	static final String AUTHORITY_GFMISAGENT = "GFMISAGENT";
	static final String PAGE_LOGIN = "/pages/login.jsp";
	static final String PAGE_MAIN_ADMIN = "/admin/main.jsp";
	static final String PAGE_MAIN_AGENT = "/pages/main.jsp";
	static final String PAGE_ERROR = "/pages/error500.jsp";

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String Home(HttpSession session, RedirectAttributes redirect, HttpServletResponse response,HttpServletRequest request,
			Authentication auth) {
		// public ModelAndView Home(HttpSession session,RedirectAttributes
		// redirect,HttpServletRequest request){
		String username = "";
		logger.info("============= start home controller");
		String ip = getIpAddr(request);
		String mac = getMACAddress(ip);
		System.out.println("==========IP=============" + ip + "===================");
		System.out.println("==========Mac=============" + mac + "===================");
		System.out.println("===== MacOLD=======" + EpContextHolder.getContext().getMacAddress() + "==============");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
// ======================== authen ดับเครื่อง =========================
//		if (mac != null) {              
//			if(!EpContextHolder.getContext().getMacAddress().equals(mac)) {
//				 session.removeAttribute("session");
//				 session.removeAttribute("role");
//			  
//			     if (authentication != null){    
//			         new SecurityContextLogoutHandler().logout(request, response, authentication);
//			     }
//			     return "redirect:"+PAGE_LOGIN;
//			}
//		}
		List<Long> listIdMenu = new ArrayList<Long>();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			username = authentication.getName();
		}
	 	
       	
		logger.debug("=== query role param:" + username);
		List<Menu> menuPrincipalModel = menuRepository.findAllByPrincipalUsername("MENU", username);
		List<Menu> menuList = menuRepository.findAllOrderByOrderingAsc();

		Long agentMenuId = 222L;
		Long agentIndex = -1L;
		// start group menu
		List<Menu> headMenu = new ArrayList<Menu>();
		List<Menu> listMenu = new ArrayList<Menu>();
		for (Menu tmpMenu : menuList) {// ENABLED SUBMENU

			if (tmpMenu.getParrentId() == 0) {
				headMenu.add(tmpMenu);

			} else {
				listMenu.add(tmpMenu);
			}
			
			
			//find max index for agent pay service
			if(tmpMenu.getId()> agentIndex){
				agentIndex = tmpMenu.getId();
			}
		}
		
		//start add agent payment
	//	if(listIdMenu.contains(agentMenuId)){
			agentIndex++;//prevent id in menuList
			List<MasAgent> masAgentList = agentRepository.findByIsPositive(Long.valueOf(1));
			
			for (MasAgent tmpAgent : masAgentList) {
				//Id()==222){//รับชำระเงินจากตัวแทน
				
				Menu agentMenuModel = new Menu();
				
				String url = "pay-agent_1.jsp?id="+tmpAgent.getId();
				String name = "รับชำระ"+tmpAgent.getName();
				
				agentMenuModel.setId(agentIndex);
				agentMenuModel.setParrentId(agentMenuId);
				agentMenuModel.setUrl(url);
				agentMenuModel.setName(name);
				agentMenuModel.setIsEnabled(1);
				listMenu.add(agentMenuModel);
				
				agentIndex++;
				//		   	    <c:when test="${not empty menuUser.url && menuUser.parrentId != 0 && menuUser.isEnabled ==1}">
		
			}
	//	}

		menuList = new ArrayList<Menu>();

		Collections.sort(headMenu);
		Collections.sort(listMenu);

		int headFlag = 0;
		for (Menu tmpMenu : headMenu) {
			logger.debug("query menu:" + tmpMenu.getName());
			for (Menu tmpListMenu : listMenu) {
				if (tmpMenu.getId() != null && tmpListMenu.getParrentId() != null
						&& tmpMenu.getId().equals(tmpListMenu.getParrentId())) {

					if (headFlag == 0) {
						menuList.add(tmpMenu);
						headFlag = 1;
					}

					menuList.add(tmpListMenu);
				}

			}
			if (headFlag == 0 && !StringUtils.isEmpty(tmpMenu.getUrl()) && tmpMenu.getParrentId() == 0) {
				menuList.add(tmpMenu);
			}
			headFlag = 0;

		}
		

		
		
		for (Menu tmpMenu : menuList) {// ENABLED SUBMENU
			for (Menu tmpPermisstion : menuPrincipalModel) {

				if (tmpPermisstion.getId() != null && tmpMenu.getId() != null && tmpPermisstion.getId().equals(tmpMenu.getId())) {
					tmpMenu.setIsEnabled(1);
					logger.debug("sub menu enable menu:" + tmpMenu.getName());

					listIdMenu.add(tmpMenu.getParrentId());
					break;
				}
			}
		}

		// set head menu enabled -> submenu enabled
		for (Menu tmpMenu : menuList) {// ENABLED head menu
			if(tmpMenu.getParrentId() == 0){
				tmpMenu.setIsEnabled(0);
			}
			
			if (tmpMenu.getParrentId() == 0 && listIdMenu.contains(tmpMenu.getId())) {//recheck
				logger.debug("head enable menu:" + tmpMenu.getName());
				tmpMenu.setIsEnabled(1);
			}
		}

		
		// test
		for (Menu tmpMenu : menuList) {
			logger.debug("menu:" + tmpMenu.getName() + " id :" + tmpMenu.getId() + " parrent :" + tmpMenu.getParrentId()+ " isenabled:" + tmpMenu.getIsEnabled());
		}
		String page = "/pages/main.jsp";
		org.springframework.security.core.GrantedAuthority[] grantedAuthorities = auth.getAuthorities()
				.toArray(new org.springframework.security.core.GrantedAuthority[0]);
		if (grantedAuthorities == null || grantedAuthorities.length < 1) {
			page = PAGE_LOGIN;

		}/* else if ("ADMIN".equals(grantedAuthorities[0].getAuthority())) {
			page = PAGE_MAIN_ADMIN;

		}*/

		session.setAttribute("user", username);
		// session.setAttribute("menuUserList", permissionModel);
		session.setAttribute("menuMainData", menuList);
		ModelAndView model = new ModelAndView();
		model.setViewName("main.jsp");
		logger.debug("========== home controller view " + page);

		System.out.println("vie wname");
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("main");
		// return modelview;
		return "redirect:" + page;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}
}
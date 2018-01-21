package th.net.cat.epis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import th.net.cat.epis.to.management.Predefine;
import th.net.cat.epis.to.management.User;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.SESSIONUtil;

public abstract class AbstractController {
	
	protected static final String EDIT_MODE = "2";
	protected static final String ADD_MODE = "1";
	
	protected static final String STATE_PARAM = "state";
	protected static final String USERGROUP_PARAM = "userGroups";

	@Autowired
	protected MessageSource messages;
	
	public abstract String iniHandler(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception;
	
	protected boolean isAddMode(HttpServletRequest request) {
		boolean result = request.getParameter(STATE_PARAM) != null && ADD_MODE.equals(request.getParameter(STATE_PARAM));
		return result;
	}
	
	protected boolean isEditMode(HttpServletRequest request) {
		boolean result = request.getParameter(STATE_PARAM) != null && EDIT_MODE.equals(request.getParameter(STATE_PARAM));
		return result;
	}
	
	protected void executeState(HttpServletRequest request, Model model) {
		model.addAttribute(STATE_PARAM, request.getParameter(STATE_PARAM));
		model.addAttribute("ISADDMODE", isAddMode(request));
		model.addAttribute("activestatus", getActiveItem());
	}
	
	protected List<Predefine> getActiveItem() {
		List<Predefine> predefines = new ArrayList<Predefine>();
		Predefine pre = new Predefine();
		pre.setId("Y");
		pre.setLabel("Yes");
		predefines.add(pre);
		
		pre = new Predefine();
		pre.setId("N");
		pre.setLabel("No");
		predefines.add(pre);
		
		return predefines;		
	}
	
	protected User getUserLoggedIn(HttpServletRequest request) {
		User user =  (User)request.getSession().getAttribute(SESSIONUtil.AUTHENOBJ);
		return user;
	}
	
	protected String getJsonSaveErrorMessage(){		
		return AppUtil.getJSONSaveResult(false, messages.getMessage("label.saveerror", null, null));
	}
	
	protected void executeJsonSaveError(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AppUtil.drawJson(request, response, getJsonSaveErrorMessage());
	}
	
	protected void executeSaveResult(HttpServletRequest request, HttpServletResponse response, boolean result) throws IOException {
		if ( result )
			AppUtil.drawJson(request, response, AppUtil.getJSONSaveResult(true, messages.getMessage("label.savesuccess", null, null)));
		else
			executeJsonSaveError(request, response);
	}

}

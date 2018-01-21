package th.net.cat.epis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.dto.sap.SaSapTemplateDTO;
import th.net.cat.epis.dto.sap.SapViewDTO;
import th.net.cat.epis.service.SapTemplateServiceImp;

@Controller
public class SapTemplateController {
	
	@Autowired SapTemplateServiceImp saptemplate;
	
//	for test sap template-------------------------------------------------------
	
	@ResponseBody
	@RequestMapping(value="findtest_sap.json", method=RequestMethod.POST)
	public SaSapTemplateDTO findtest_sapFuntion() {
		SaSapTemplateDTO sapTemplateDTO = new SaSapTemplateDTO();
		sapTemplateDTO = saptemplate.SelectSaSapTemplate();
		return sapTemplateDTO;
		
	}
	
	@ResponseBody
	@RequestMapping(value="findtest_view.json", method=RequestMethod.POST)
	public SapViewDTO findtest_viewFuntion() {
		SapViewDTO viewDTO = new SapViewDTO();
		viewDTO = saptemplate.SelectSapView();
		return viewDTO;
		
	}
//	for test sap template-------------------------------------------------------

}

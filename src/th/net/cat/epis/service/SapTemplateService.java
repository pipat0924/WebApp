package th.net.cat.epis.service;


import th.net.cat.epis.dto.sap.SaSapTemplateDTO;
import th.net.cat.epis.dto.sap.SapViewDTO;
@org.springframework.stereotype.Service
public interface SapTemplateService {
	
	public SaSapTemplateDTO SelectSaSapTemplate() throws Exception;
	public SapViewDTO SelectSapView() throws Exception;

}

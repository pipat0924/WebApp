package th.net.cat.epis.controller.management;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.entity.PosOffline;
import th.net.cat.epis.repo.PosOfflineRepository;
import th.net.cat.epis.repo.ShopRepository;

@Controller
public class PosOfflineController {

	@Autowired PosOfflineRepository posOfflineRepository;
	@Autowired ShopRepository shopRepository;

	@ResponseBody
	@RequestMapping(value="/findPosOfflineByBranchAttribute.json", method=RequestMethod.GET)
	public Iterable<PosOffline> findPosByBranchAttributeJSON(ModelMap modelMap,
			@RequestParam(value="no") String no, 
			@RequestParam(value="name") String name,
			@RequestParam(value="shop") String shop) throws Exception {
		if(isNotBlank(no) || isNotBlank(name) || isNotBlank(shop)) {
			return posOfflineRepository.findByNoIsOrNameIsOrShop_No(no, name, shop);
		} return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/savePosOfflineByBranchAttribute.json", method=RequestMethod.POST)
	public Long savePosByBranchAttribute(HttpServletRequest request, @RequestBody PosOffline posOffline) throws Exception {
		posOfflineRepository.save(posOffline);
		return posOffline.getId();
	}
	
	@ResponseBody
	@RequestMapping(value="/findPosOfflineDetailById.json", method=RequestMethod.GET)
	public PosOffline findPosDetailByIdJSON(ModelMap modelMap,
			@RequestParam(value="id") String id) throws Exception {
		if(isNotBlank(id)) {
			return posOfflineRepository.findOne(new Long(id));
		} return null;
	}
}

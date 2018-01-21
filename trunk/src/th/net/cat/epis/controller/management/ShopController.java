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

import antlr.collections.List;
import th.net.cat.epis.entity.Office;
import th.net.cat.epis.entity.Shop;
import th.net.cat.epis.repo.ShopRepository;
import th.net.cat.epis.service.OfficerService;

@Controller
public class ShopController {

	@Autowired ShopRepository shopRepository;
	@Autowired OfficerService officerService;
	@ResponseBody
	@RequestMapping(value="/findShopByBranchAttribute.json", method=RequestMethod.POST)
	public Iterable<Shop> findShopByBranchAttributeJSON(ModelMap modelMap, @RequestBody Shop shop) throws Exception {
		if(isNotBlank(shop.getBusinessPlace()) || isNotBlank(shop.getBusinessArea()) || isNotBlank(shop.getName())) {
	
			return shopRepository.findByBusinessPlaceIsOrBusinessAreaIsOrName(shop.getBusinessPlace(), shop.getBusinessArea(), shop.getName());
	
		} return null;
	}

	@ResponseBody
	@RequestMapping(value="/saveShopByBranchAttribute.json", method=RequestMethod.POST)
	public Long saveShopByBranchAttribute(HttpServletRequest request, @RequestBody Shop shop) throws Exception {

		return officerService.saveShopByBranchAttribute(request, shop);
	       
	}

	@ResponseBody
	@RequestMapping(value="/findShopDetailById.json", method=RequestMethod.GET)
	public Shop findShopDetailByIdJSON(ModelMap modelMap,
			@RequestParam(value="id") String id) throws Exception {
		if(isNotBlank(id)) {
			return shopRepository.findOne(new Long(id));
		} return null;
	}
	

	@RequestMapping(value="/findShopList.json", method=RequestMethod.GET)
	public void findShopListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", shopRepository.findAll());
		modelMap.addAttribute("statusCode", "0");
	}
}

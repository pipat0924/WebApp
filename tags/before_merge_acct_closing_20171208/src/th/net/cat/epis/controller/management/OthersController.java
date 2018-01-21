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

import th.net.cat.epis.entity.Enum;
import th.net.cat.epis.repo.EnumRepository;

@Controller
public class OthersController {

	@Autowired EnumRepository enumRepository;

	@ResponseBody
	@RequestMapping(value="/findOthersByAttribute.json", method=RequestMethod.GET)
	public Iterable<Enum> findOthersByAttributeJSON(ModelMap modelMap,
			@RequestParam(value="category") String category, 
			@RequestParam(value="code") String code,
			@RequestParam(value="descFullTh") String descFullTh) throws Exception {
		if(isNotBlank(category) || isNotBlank(code) || isNotBlank(descFullTh)) {
			return enumRepository.findByCategoryIsOrCodeIsOrDescFullThStartingWith(category, code, descFullTh);
		} return null;
	}

	@ResponseBody
	@RequestMapping(value="/saveOthersByAttribute.json", method=RequestMethod.POST)
	public Long saveOthersByAttribute(HttpServletRequest request, @RequestBody Enum enums) throws Exception {
		enumRepository.save(enums);
		return enums.getId();
	}
	
	@ResponseBody
	@RequestMapping(value="/findOthersDetailById.json", method=RequestMethod.GET)
	public Enum findOthersDetailByIdJSON(ModelMap modelMap,
			@RequestParam(value="id") String id) throws Exception {
		if(isNotBlank(id)) {
			return enumRepository.findOne(new Long(id));
		} return null;
	}
}

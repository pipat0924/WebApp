package th.net.cat.epis.controller.management;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.repo.AuthenRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.PrincipalRepository;
import th.net.cat.epis.service.OfficerService;
import th.net.cat.epis.util.AppUtil;

@Controller
public class OfficerController {

	@Autowired
	OfficerRepository officerRepository;
	@Autowired
	AuthenRepository authenRepo;
	@Autowired
	PrincipalRepository principalRepository;
	@Autowired
	OfficerService officerService;
	@ResponseBody
	@RequestMapping(value = "/findOfficerByBranchAttribute.json", method = RequestMethod.POST)
	public Iterable<Officer> findOfficerByBranchAttributeJSON(ModelMap modelMap, @RequestBody Officer officer)
			throws Exception {
		if (isNotBlank(officer.getCode()) || isNotBlank(officer.getName()) || isNotBlank(officer.getGivenName())
				|| isNotBlank(officer.getFamilyName())) {
			return officerRepository
					.findByCodeContainingIgnoreCaseAndNameContainingIgnoreCaseAndGivenNameContainingIgnoreCaseAndFamilyNameContainingIgnoreCase(
							officer.getCode(), officer.getName(), officer.getGivenName(), officer.getFamilyName());
		}
		return null;
	}

	@RequestMapping(value = "/findPrincipalList.json", method = RequestMethod.GET)
	public void findPrincipalListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", principalRepository.findAll());
		modelMap.addAttribute("statusCode", "0");
	}

	@RequestMapping(value = "/findOfficerList.json", method = RequestMethod.GET)
	public void findOfficerListJSON(ModelMap modelMap) {

		modelMap.addAttribute("data", officerRepository.findAll());
		modelMap.addAttribute("statusCode", "0");
	}

	@ResponseBody
	@RequestMapping(value = "/saveOfficerByBranchAttribute.json", method = RequestMethod.POST)
	public Long saveOfficerByBranchAttribute(HttpServletRequest request, @RequestBody Officer officer)
			throws Exception {
		return officerService.saveOfficerByBranchAttribute(request, officer);
	}

	@ResponseBody
	@RequestMapping(value = "/findOfficerDetailById.json", method = RequestMethod.GET)
	public Officer findOfficerDetailById(ModelMap modelMap, @RequestParam(value = "id") String id) throws Exception {
		if (isNotBlank(id)) {
			return officerRepository.findOne(new Long(id));
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getEncodedKey.json", method = RequestMethod.GET)
	public String getEncodedKey(ModelMap modelMap, @RequestParam(value = "verifyKey") String verifyKey)
			throws Exception {
		if (isNotBlank(verifyKey)) {
			return AppUtil.generatedPassword(verifyKey);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/loadCurrentOfficer.json", method = RequestMethod.GET)
	public Officer loadCurrentOfficer(ModelMap modelMap) throws Exception {
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		if (isNotBlank(userid)) {
			return officerRepository.findByName(userid);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/updateVerifyUser.json", method = RequestMethod.POST)
	public Officer updateVerifyUser(ModelMap modelMap, @RequestBody Officer officerParam) throws Exception {

		Officer officer = officerRepository.findByName(officerParam.getName());
		officer.setVerifyFlag(officerParam.getVerifyFlag());
		if ("Y".equals(officerParam.getVerifyFlag())) {
			officer.setVerifyKey(AppUtil.generatedPassword(officerParam.getVerifyKey()));
		}
		Officer updatedOfficer = (Officer) officerRepository.save(officer);
		return updatedOfficer;
	}
}

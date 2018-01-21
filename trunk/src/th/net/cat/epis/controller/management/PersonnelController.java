package th.net.cat.epis.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.repo.AuthenRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.to.management.User;
import th.net.cat.epis.util.AppUtil;

@Controller
public class PersonnelController {

	@Autowired OfficerRepository officerRepo;
	@Autowired AuthenRepository authenRepo;
	
	@ResponseBody
	@RequestMapping(value="/resetPassword.json", method=RequestMethod.POST)
	public Long getResetPasswordJSON(HttpServletRequest request, @RequestBody User user) throws Exception {
		Officer officer = officerRepo.findByName(user.getUsername());
		//Authen authen = authenRepo.findOne(officer.getAuthen().getId());
		if(AppUtil.generatedPassword(user.getPassword()).equals(officer.getPassword())) {
			officer.setPassword(AppUtil.generatedPassword(user.getConfirmpassword()));
			officerRepo.save(officer);
			return officer.getId();
		} return 0l;
	}

	public static final String convertDateString(String str) { 
		return str.replaceAll("([0-9]{2})-([0-9]{2})-([0-9]{4})", "$1/$2/$3"); 
	}
}

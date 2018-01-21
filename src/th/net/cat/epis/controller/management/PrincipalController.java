package th.net.cat.epis.controller.management;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import th.net.cat.epis.entity.Principal;
import th.net.cat.epis.repo.PrincipalRepository;

@Controller

public class PrincipalController {
	@Autowired PrincipalRepository principalRepository;
	//start add by kao 25600724 1003
	/*@RequestMapping(value="/findPrincipalList.json", method=RequestMethod.GET)
	public void findPrincipalListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", principalRepository.findAll());
		modelMap.addAttribute("statusCode", "0");
	}
	*/
	@RequestMapping(value="/findPrincipalListOrderById.json", method=RequestMethod.GET)//add by kao 25600724 1003
	public void findPrincipalListJSONOrderById(ModelMap modelMap) {
		modelMap.addAttribute("data", principalRepository.findAllByOrderByIdAsc());
		modelMap.addAttribute("statusCode", "0");
	}
	
	@RequestMapping(value="/findPrincipalListByNameAndDescOrderById.json", method=RequestMethod.GET)//add by kao 25600724 1003
	public void findPrincipalListByNameAndDescOrderById(ModelMap modelMap,
			@RequestParam(value="name",required=true,defaultValue="") String name,
			@RequestParam(value="desc",required=true,defaultValue="") String desc) {
		List<Principal> principalList = null;
		principalList = principalRepository.findByNameLikeIgnoreCaseAndDescLikeIgnoreCase("%"+name+"%","%"+desc+"%"  );
		modelMap.addAttribute("data", principalList );
		modelMap.addAttribute("statusCode", "0");
	}
	
	
	
	@RequestMapping(value="/updatePrincipal.json", method=RequestMethod.POST)
	public void updatePrincipal(ModelMap modelMap, @RequestBody Principal model) {
		

		int statusCode = -1;
		String dataMsg = "error";
		String username = "";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		if(!model.equals(new Principal())){
			
			 
			 try{
				 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				 if (!(authentication instanceof AnonymousAuthenticationToken)) 
				     username = authentication.getName();
				 else throw new Exception("user expire");
				 
				 
				 List<Principal> principalList = principalRepository.findById(model.getId());
				 
				 if(model.getId()==0 && principalList.size() != 0){
					 dataMsg = "error repeat";
					 statusCode = 1;
				 }else{
					 model.setUpdateUser(username);
					 model.setUpdateDttm(ts);
					 principalRepository.save(model);
					 statusCode = 0;
				 }
				 
				 
				 
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			
		}
		modelMap.addAttribute("data", dataMsg);
		modelMap.addAttribute("statusCode", statusCode);
		
	}
	
	//end add by kao 25600724 1003
}

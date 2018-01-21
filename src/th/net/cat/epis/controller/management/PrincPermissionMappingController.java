package th.net.cat.epis.controller.management;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import th.net.cat.epis.entity.Permission;
import th.net.cat.epis.entity.PrincPermissionMapping;
import th.net.cat.epis.entity.Principal;
import th.net.cat.epis.repo.MenuRepository;
import th.net.cat.epis.repo.PermissionRepository;
import th.net.cat.epis.repo.PrincipalRepository;
import th.net.cat.epis.repo.PrincpermissionmappingRepository;

@Controller
public class PrincPermissionMappingController {

	private static Logger logger = Logger.getLogger(BillingGroupIgnoreController.class);
	//@Autowired BillingGroupIgnoreRepository billingGroupIgnoreRepository;
	/*@Autowired RoleRepository roleRepository;
	@Autowired RolenameRepository rolenameRepository;
	@Autowired PrincipalRepository principalRepository;*/
	
	@Autowired PrincpermissionmappingRepository princpermissionmappingRepository;
	@Autowired PermissionRepository permissionRepository;
	@Autowired MenuRepository menuRepository;
	@Autowired PrincipalRepository principalRepository;
	
	
	@Resource(name="episJdbcTemplate") 
	JdbcTemplate episJdbcTemplate;

	
	
	@RequestMapping(value="/searchprinciple.json", method=RequestMethod.GET)
	public void getPrinciple(ModelMap modelMap,
			@RequestParam(value="principalId",required=true,defaultValue="0") int principalId,
			HttpSession session
			) throws Exception {
		
		String username = "";
		List<Permission> permissionList = null;
		int statusCode = -1;
		//if(principalId != 0) 
		
		try{
			 permissionList = permissionRepository.findAllOrderByOrderingAsc();
			 List<PrincPermissionMapping> principalPermissionList = principalId == 0 ? null :princpermissionmappingRepository.findByPrincipalId(new Long(principalId));
			 
			 if(principalPermissionList!= null)
			 for(Permission tmpMenu: permissionList)
				 for(PrincPermissionMapping tmpPrinc : principalPermissionList)
					 if(tmpMenu.getId().equals(tmpPrinc.getPermissionId()) ) tmpMenu.setIsEnabled(1);
				 
			 
			 statusCode = 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		modelMap.addAttribute("data", permissionList);
		modelMap.addAttribute("statusCode", statusCode);
		
	}
	
	
	@RequestMapping(value="/update-principal.json", method=RequestMethod.POST)
	public void updatePrincipal(ModelMap modelMap,@RequestBody List<Permission> principalPermissionList,HttpSession session) throws Exception {

		int statusCode = -1;
	
		Long principalId = null;
		String principalName = "";
		String principalDetail = "";
		String userName = "";
		try{
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 if (!(authentication instanceof AnonymousAuthenticationToken)) 
				 userName = authentication.getName();
			 else throw new Exception("user expire");
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			List<PrincPermissionMapping> principalPermissionSave = new ArrayList<PrincPermissionMapping>();
			Principal principalModel = new Principal();
			
			 principalId = new Long(principalPermissionList.get(0).getPrincipalId());
			 principalName = principalPermissionList.get(0).getPrincipalName();
			 principalDetail = principalPermissionList.get(0).getPrincipalDetail();
			 
			 //start set principal Model
			 principalModel.setId(principalId == 0 ? null : principalId);
			 principalModel.setName(principalName);
			 principalModel.setDesc(principalDetail);
			 //principalModel.setUpdateUser(userName);//test
			 //principalModel.setUpdateDttm(ts);//test
			 
			Principal principalSave = principalRepository.save(principalModel);//arcprincipal name
			
 		    principalId = principalSave.getId();
 		   List<PrincPermissionMapping> princOridata = princpermissionmappingRepository.findByPrincipalId(principalId);
			// if(principalPermissionList.get(0).getId() != null){ //delete all role
			 //start set princPermission
			List<Long> principalIdArr = new ArrayList<Long>();
			for(Permission tmpPermission : principalPermissionList){
					PrincPermissionMapping tmpPrincPermission = new PrincPermissionMapping();
					
					tmpPrincPermission.setPrincipalId(principalId);
					tmpPrincPermission.setPermissionId(tmpPermission.getId());
					tmpPrincPermission.setCreateBy(userName);
					tmpPrincPermission.setCreateDate(ts);
					tmpPrincPermission.setUpdateBy(userName);
					tmpPrincPermission.setUpdateDate(ts);
					
					principalPermissionSave.add(tmpPrincPermission);
						
						
				//	}
					
			
					 
					 //start set table permission
					 
					//Set<Long> principalIdList = new HashSet<Long>();
					// List<PrincPermissionMapping> princOridata = princpermissionmappingRepository.findByPrincipalId(principalId);
					 for(PrincPermissionMapping tmpOri : princOridata){
						 for(PrincPermissionMapping tmpSave : principalPermissionSave){
							 
							 if(tmpOri.getPermissionId().equals(tmpSave.getPermissionId()) && tmpOri.getPrincipalId().equals(tmpSave.getPrincipalId()) ){
								 tmpSave.setId(tmpOri.getId());
								 tmpSave.setCreateBy(tmpOri.getCreateBy());
								 tmpSave.setCreateDate(tmpOri.getCreateDate());
								 
							 }
						 }
						 
						// principalIdList.add(tmpOri.getId());
					 }
			 }
			
			 /*List<PrincPermissionMapping> princOridata = princpermissionmappingRepository.findByPrincipalId(principalId);
			 for(PrincPermissionMapping tmpOri : princOridata){
				 for(PrincPermissionMapping tmpSave : principalPermissionSave){
					 
					 if(tmpOri.getPermissionId().equals(tmpSave.getPermissionId()) && tmpOri.getPrincipalId().equals(tmpSave.getPrincipalId()) ){
						 tmpSave.setId(tmpOri.getId());
						 tmpSave.setCreateBy(tmpOri.getCreateBy());
						 tmpSave.setCreateDate(tmpOri.getCreateDate());
						 
					 }
				 }
				 
				// principalIdList.add(tmpOri.getId());
			 }*/
			 
			
			princpermissionmappingRepository.deleteByPrincipalId(principalId); //delete all
			
			 //if(principalPermissionList.get(0).getId() != null) princpermissionmappingRepository.save(principalPermissionSave); //new data
			if(principalId != 0) princpermissionmappingRepository.save(principalPermissionSave); //new data
			statusCode = 0;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		modelMap.addAttribute("data", "");
		modelMap.addAttribute("statusCode", statusCode);
		//return update;
	}
	
}

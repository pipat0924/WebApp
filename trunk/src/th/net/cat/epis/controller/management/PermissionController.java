package th.net.cat.epis.controller.management;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import th.net.cat.epis.entity.Menu;
import th.net.cat.epis.entity.Permission;
import th.net.cat.epis.repo.MenuRepository;
import th.net.cat.epis.repo.PermissionRepository;
import th.net.cat.epis.repo.PrincipalRepository;
import th.net.cat.epis.repo.PrincpermissionmappingRepository;

@Controller
public class PermissionController {
	private static Logger logger = Logger.getLogger(PermissionController.class);

	@Autowired PrincpermissionmappingRepository princpermissionmappingRepository;
	@Autowired PermissionRepository permissionRepository;
	@Autowired MenuRepository menuRepository;
	@Autowired PrincipalRepository principalRepository;
	
	
	@Resource(name="episJdbcTemplate") 
	JdbcTemplate episJdbcTemplate;

	
	
	@RequestMapping(value="/findPermissionListByNameAndDescAndType.json", method=RequestMethod.POST)
	public void findPermissionListByNameAndDescAndType(ModelMap modelMap,
			@RequestParam(value="name",required=true,defaultValue="") String name,
			@RequestParam(value="desc",required=true,defaultValue="") String desc,
			@RequestParam(value="type",required=true,defaultValue="") String type			) throws Exception {
		
		String username = "";
		int statusCode = -1;
		List<Permission> permissionList = null;
		
		try{
			Set<Long> menuId = new HashSet<Long>();
			
			name = "%"+name+"%";
			desc = "%"+desc+"%";
			logger.info("============= name:"+name);
			logger.info("============= desc:"+desc);
			logger.info("============= type:"+type);
			permissionList = isEmpty(type)
					? permissionRepository.findByNameLikeIgnoreCaseAndDescLikeIgnoreCase(name,desc)
					: permissionRepository.findByNameLikeIgnoreCaseAndDescLikeIgnoreCaseAndType(name,desc,type);
			for(Permission tmp : permissionList){
				menuId.add(tmp.getRefId());
			}
			
			
			//start get ref menu name
			if(menuId.size() > 0){
				List<Menu> menuList = menuRepository.findAllByIdIn(menuId);
				
				for(Menu tmpMenu : menuList)
					for(Permission tmp : permissionList)
						if(tmpMenu.getId().equals(tmp.getRefId()))
							tmp.setRefName(tmpMenu.getName());
			}
			
					
			
			if(permissionList.size() > 0)
			 statusCode = 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		modelMap.addAttribute("data", permissionList);
		modelMap.addAttribute("statusCode", statusCode);
		//return permissionList;

	}
	
	@RequestMapping(value="/deletePermissionById.json", method=RequestMethod.POST)
	public void deleteMenuList(ModelMap modelMap,			@RequestParam(value="id",required=true,defaultValue="0") Long id) {
		
		int isDelete = -1;
		StringBuilder idChildArr = new StringBuilder("");
		if(id != 0)
		try{
			

			permissionRepository.delete(id);
			isDelete = 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		modelMap.addAttribute("data", idChildArr.toString());
		modelMap.addAttribute("statusCode", isDelete);
	}
	
	@RequestMapping(value="/updatePermissionById.json", method=RequestMethod.POST)
	public void findPermissionListByNameAndDescAndType(ModelMap modelMap,
			@RequestBody Permission permissionData,
			HttpSession session
			) throws Exception {
		
		String username = "";
		int statusCode = -1;
		List<Permission> permissionList = null;
		
		String dataMsg = "error";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		if(!permissionData.equals(new Permission())){
			
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 
			 try{
				 
				 if (!(authentication instanceof AnonymousAuthenticationToken)) 
				     username = authentication.getName();
				 else{
					 throw new Exception("session expire");
				 }
				 
				 
				 permissionData.setCreateBy(username);
				 permissionData.setCreateDate(ts);
				 permissionData.setUpdateBy(username);
				 permissionData.setUpdateDate(ts);
				 
				 if(permissionData.getId() != null){
					 permissionList=  permissionRepository.findById(permissionData.getId());
					 for(Permission tmpMenu : permissionList){
						 if( permissionData.getId().equals(tmpMenu.getId())){
							 
							 permissionData.setId(tmpMenu.getId());
							 permissionData.setCreateBy(tmpMenu.getCreateBy());
							 permissionData.setCreateDate(tmpMenu.getCreateDate());
						 }
					 }
				 }
			
				 permissionRepository.save(permissionData);
				 statusCode = 0;
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			
		}
		
		
		
		modelMap.addAttribute("data", permissionList);
		modelMap.addAttribute("statusCode", statusCode);
		//return permissionList;

	}
}

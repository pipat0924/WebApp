package th.net.cat.epis.controller.management;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import th.net.cat.epis.repo.MenuRepository;
import th.net.cat.epis.repo.PermissionRepository;
import th.net.cat.epis.repo.PrincipalRepository;
import th.net.cat.epis.repo.PrincpermissionmappingRepository;

@Controller
public class MenuContoller {

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


	@RequestMapping(value="/findMenuListByNameAndDesc.json", method=RequestMethod.GET)
	public void findMenuListByNameAndDesc(ModelMap modelMap,
			@RequestParam(value="name",required=true,defaultValue="") String name,
			@RequestParam(value="desc",required=true,defaultValue="") String desc) {
		
		 List<Menu> menuList =  menuRepository.findByNameContainingIgnoreCaseAndDescContainingIgnoreCaseOrderByOrderingAsc(name,desc);

		 //start group menu
		 List<Menu> headMenu = null;
		 List<Menu> listMenu = null;
		 
		 
		 Set<Long> parrentId = new HashSet<Long>(); ;
		 Set<Long> menuId = new HashSet<Long>(); ;

		 //menuRepository.findByParrentIdList();
		 for(Menu tmpMenu : menuList){//ENABLED SUBMENU
		 	 if(!parrentId.contains(tmpMenu.getParrentId())){
				 parrentId.add(tmpMenu.getParrentId());
			 }
			
		 }
		 listMenu = menuList;
		 menuList = new ArrayList<Menu>();
		 if(parrentId.size() == 0){
			 modelMap.addAttribute("data", "");
			modelMap.addAttribute("statusCode", 1);
		 }
		 headMenu = menuRepository.findByIdList(parrentId);
		 
		 //sub menu
		 for(Menu headTmp : headMenu){//menu name
			 for(Menu tmpMenu : listMenu){//ENABLED SUBMENU
			 	 if(tmpMenu.getParrentId().equals(headTmp.getId())){
			 		 //tmpMenu.setMainMenuName(headTmp.getName());
			 		Menu tmpData = tmpMenu;
			 		tmpData.setMainMenuName(headTmp.getName());
			 		menuList.add(tmpData);
			 		 
			 	 }
			 	
				
			 }
			
		 }
		 
		 //head menu
		 for(Menu tmpMenu : listMenu){
			 if(!menuId.contains(tmpMenu.getId()) && tmpMenu.getParrentId() == 0 ) {//menu without parrent Id
		 		menuId.add(tmpMenu.getId());
		 		menuList.add(tmpMenu);
		 	 }
		 }
		 
		/* menuList = new ArrayList<Menu>();
		 
		 Collections.sort(headMenu);
		 Collections.sort(listMenu);
		 */
		/* int headFlag = 0;
		 for(Menu tmpMenu : headMenu){
			 
			 for(Menu tmpListMenu : listMenu){
				 
				 if(tmpMenu.getId() != null && tmpListMenu.getParrentId() != null && tmpMenu.getId().equals(tmpListMenu.getParrentId())){
					 
					 if(headFlag==0){
						 menuList.add(tmpMenu);
						 headFlag=1;
					 }
					 
					 tmpListMenu.setMainMenuName(tmpMenu.getName());
					 menuList.add(tmpListMenu);
				 }
				 
			 }
			 if(headFlag==0 && !StringUtils.isEmpty(tmpMenu.getUrl())){
				 menuList.add(tmpMenu);
			 }
			 headFlag = 0;
		 }*/
	 
	 	modelMap.addAttribute("data", menuList);
		modelMap.addAttribute("statusCode", "0");
	 
	}
	
	@RequestMapping(value="/findMenuWithHeadNameList.json", method=RequestMethod.GET)
	public void findMenuWithHeadNameList(ModelMap modelMap) {
		
		List<Menu> menuList = new ArrayList<Menu>();
		List<Menu> subMenuList = menuRepository.findAll();
		
		 Set<Long> parrentId = new HashSet<Long>(); ;
		
		 for(Menu tmpMenu : subMenuList){//ENABLED SUBMENU
		 	 if(!parrentId.contains(tmpMenu.getParrentId())){
				 parrentId.add(tmpMenu.getParrentId());
			 }
			
		 }
		 
		List<Menu> headMenu = menuRepository.findByIdList(parrentId);
		 
		 
		 for(Menu headTmp : headMenu){//ENABLED SUBMENU
			 for(Menu tmpMenu : subMenuList){//ENABLED SUBMENU
			 	 if(tmpMenu.getParrentId().equals(headTmp.getId())){
			 		 //tmpMenu.setMainMenuName(headTmp.getName());
			 		 tmpMenu.setName(headTmp.getName()+" > "+tmpMenu.getName());
			 		menuList.add(tmpMenu);
			 	 }
				
			 }
			
		 }
		modelMap.addAttribute("data", menuList);
		modelMap.addAttribute("statusCode", "0");
	}
	
	@RequestMapping(value="/findMenuList.json", method=RequestMethod.GET)
	public void findPrincipalListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", menuRepository.findAll());
		modelMap.addAttribute("statusCode", "0");
	}
	
	@RequestMapping(value="/deleteMenuList.json", method=RequestMethod.POST)
	public void deleteMenuList(ModelMap modelMap,			@RequestParam(value="id",required=true,defaultValue="0") Long id) {
		
		int isDelete = -1;
		StringBuilder idChildArr = new StringBuilder("");
		if(id != 0)
		try{
			
			List<Menu> menuList = menuRepository.findById(id);
			idChildArr.append(String.valueOf(id));
			if(menuList.size()>0 && menuList.get(0).getParrentId() == 0){
				
				List<Menu> menuChild = menuRepository.findByParrentId(id);
				
				
				for(Menu tmpMenu : menuChild){
					
					if(!StringUtils.isEmpty(idChildArr.toString())){
						idChildArr.append("||");
					}
					idChildArr.append(String.valueOf(tmpMenu.getId()));
				}
				
				menuRepository.deleteByParrentId(id);
				
				
				
			}
			menuRepository.delete(id);
			isDelete = 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		modelMap.addAttribute("data", idChildArr.toString());
		modelMap.addAttribute("statusCode", isDelete);
	}
	@RequestMapping(value="/findHeadMenuList.json", method=RequestMethod.GET)
	public void findHeadMenuList(ModelMap modelMap) {
		
		List<Menu> menuList = menuRepository.findAll();
		
		List<Menu> headMenu = new ArrayList<Menu>();
		for(Menu tmpMenu : menuList){
			if(tmpMenu.getParrentId() ==0 ){
				headMenu.add(tmpMenu);
			}
		}
		
		modelMap.addAttribute("data", headMenu);
		modelMap.addAttribute("statusCode", "0");
	}
	
	@RequestMapping(value="/update-menu.json", method=RequestMethod.POST)
	public void updateMenu(ModelMap modelMap,@RequestBody Menu menuData) {
		
		int statusCode = -1;
		String dataMsg = "error";
		String username = "";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		if(!menuData.equals(new Menu())){
			
			 
			 try{
				 
				 
				 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				 if (!(authentication instanceof AnonymousAuthenticationToken)) 
				     username = authentication.getName();
				 else throw new Exception("user expire");
				 
				 
				 
				 menuData.setCreateBy(username);
				 menuData.setCreateDate(ts);
				 menuData.setUpdateBy(username);
				 menuData.setUpdateDate(ts);
				 
				 
				 if(!menuData.getId().equals(0)){//update data
					 List<Menu> menuList=  menuRepository.findById(menuData.getId());
					 for(Menu tmpMenu : menuList){
						 if( menuData.getId().equals(tmpMenu.getId())){
							 
							 menuData.setId(tmpMenu.getId());
							 menuData.setCreateBy(tmpMenu.getCreateBy());
							 menuData.setCreateDate(tmpMenu.getCreateDate());
						 }
					 }
				 }
				
			 
			
				 menuRepository.save(menuData);
				 statusCode = 0;
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			
		}
		modelMap.addAttribute("data", dataMsg);
		modelMap.addAttribute("statusCode", statusCode);
	}
	
	@RequestMapping(value="/epis-user-role/getMenu.json", method=RequestMethod.GET)
	public void getPrinciple(ModelMap modelMap,
			@RequestParam(value="principalId",required=true,defaultValue="0") int principalId,
			HttpSession session
			) throws Exception {
		
		String username = "";
		List<Menu> menuList = null;
		if(principalId != 0) 
		
		try{
			
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 if (!(authentication instanceof AnonymousAuthenticationToken)) 
			     username = authentication.getName();
			 
			 //menuList = permissionRepository.findByTypeOrderByIdAsc("MENU");
			 
			 
			 //List<Permission> permissionModel = permissionRepository.findMenuByPrincipalIdOrderByIdAsc(principalId);
			 
			 menuList = menuRepository.findAllOrderByOrderingAsc();
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		modelMap.addAttribute("data", menuList);
		modelMap.addAttribute("statusCode", "0");

	}
	
}

package th.net.cat.epis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.entity.Pos;
import th.net.cat.epis.entity.Principal;
import th.net.cat.epis.entity.Shop;
import th.net.cat.epis.repo.AuthenRepository;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.PrincipalRepository;
import th.net.cat.epis.repo.ShopRepository;
import th.net.cat.epis.util.AppUtil;

@org.springframework.stereotype.Service
public class OfficerService {

	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	@Autowired
	UserService userService;
	@Autowired
	OfficerRepository officerRepository;
	@Autowired
	AuthenRepository authenRepo;
	@Autowired
	PrincipalRepository principalRepository;
	
	@Autowired ShopRepository shopRepository;

	public Long saveOfficerByBranchAttribute(HttpServletRequest request,Officer officer)
			throws Exception {

		Officer tempOfficer;
		//Authen authen;
		String spitPassword = officer.getDescription().substring(0, officer.getDescription().indexOf(":#1"));
		String spitShopAndPos = officer.getDescription().substring(officer.getDescription().indexOf(":#1") + 3,
				officer.getDescription().indexOf(":#2"));
		String spitDescription = officer.getDescription().substring(officer.getDescription().indexOf(":#2") + 3,
				officer.getDescription().length());
		
		String[] shopAndPos = spitShopAndPos.split("\\|");
		List<Pos> machines = new ArrayList<Pos>();
		for (int i = 1; i < shopAndPos.length; i++) {
			Pos pos = new Pos();
			pos.setId(new Long(shopAndPos[i]));
			machines.add(pos);
		}
		
		if(officer.getId() == null) {
			boolean isDuplicateUserName = userService.isDuplicateUserName(officer.getName());
			if(isDuplicateUserName) {
				return null;
			}
			tempOfficer = new Officer();
			//authen = new Authen();
		}else{
			
			tempOfficer = officerRepository.findOne(officer.getId());
			//List<Authen> authenList = authenRepo.findByOfficerId(officer.getId());
			
			//tempOfficer.setAuthen(authenList.size() > 0 ? authenList.get(0) : new Authen());
			
			//authen = tempOfficer.getAuthen();
		}

	
		tempOfficer.setPassword(AppUtil.generatedPassword(spitPassword));

		Principal principal = principalRepository.findOne(officer.getPrincipal().getId());
		
//		tempOfficer.setAuthen(authen);
		tempOfficer.setPrincipal(principal);
		tempOfficer.setMachines(machines);
		tempOfficer.setDescription(spitDescription);
		tempOfficer.setCode(officer.getCode());
		tempOfficer.setName(officer.getName());
		tempOfficer.setGivenName(officer.getGivenName());
		tempOfficer.setFamilyName(officer.getFamilyName());
		tempOfficer.setIsPositive(officer.getIsPositive());
		tempOfficer.setVerifyFlag(officer.getVerifyFlag());
		tempOfficer = officerRepository.save(tempOfficer);
		//authen.setOfficerId(tempOfficer.getId());
		//authen = authenRepo.save(authen);
		return tempOfficer.getId();
	}
	public Long saveShopByBranchAttribute(HttpServletRequest request, @RequestBody Shop shop) throws Exception {
//		System.out.println("getId " + shop.getId());
		if(shop.getId() == null) {
			boolean dup = checkBaDuplicate(shop.getBusinessArea());
			
			if(dup){
				shop.setNo(shop.getBusinessPlace());
				shop.setName(shop.getBuAreaName());
				shopRepository.save(shop);
				return shop.getId();
			}else {
				return 0L;
			}
		}else {
			Shop update = shopRepository.findOne(shop.getId());
			shop.setNo(update.getNo());
			shop.setName(update.getName());
			BeanUtils.copyProperties(shop, update);
			return shopRepository.save(update).getId();
		}	
	       
	}
	
	private boolean checkBaDuplicate(String ba) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select * from MASSHOP where BUAREA  = ?");
		List<Map<String, Object>> result = episJdbcTemplate.queryForList(queryBuilder.toString(),new Object[]{ba});
		if(result.size() > 0 ) {
			return false;
		}else {
			return true;
		}
	
	} 
}

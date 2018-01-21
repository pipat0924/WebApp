package th.net.cat.epis.controller.management;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.dto.PackagePromotionDTO;
import th.net.cat.epis.repo.PackagePromotionRepository;

@Controller
public class PackagePromotionController {
	private static Logger logger = Logger.getLogger(PackagePromotionController.class);
	@Autowired PackagePromotionRepository packagePromotionRepository;
		
	@Resource(name="episJdbcTemplate") 
	JdbcTemplate episJdbcTemplate;
	@ResponseBody
	@RequestMapping(value="/getPackagePromotion.json", method=RequestMethod.GET)
	public PackagePromotionDTO getPackagePromotion(HttpServletRequest request, 
			@RequestParam(value="packageId", required=true) String packageId
			) throws Exception {
		final PackagePromotionDTO packagePromotionDTO = new PackagePromotionDTO();
		List<th.net.cat.epis.entity.PackagePromotion> packagePromotionList =null;
			if(packageId!=null && packageId.trim().length()>0)
				packagePromotionList=packagePromotionRepository.findCountPackagePromotion(packageId.trim().toLowerCase());
			else
				packagePromotionList=(List<th.net.cat.epis.entity.PackagePromotion>) packagePromotionRepository.findAll();
		List<th.net.cat.epis.dto.PackagePromotion> dtoList=new ArrayList<th.net.cat.epis.dto.PackagePromotion>();
		for(th.net.cat.epis.entity.PackagePromotion obj: packagePromotionList){
			th.net.cat.epis.dto.PackagePromotion dto=new th.net.cat.epis.dto.PackagePromotion();
			BeanUtils.copyProperties(obj, dto);
			dtoList.add(dto);
		}
		packagePromotionDTO.setData(dtoList);
		return packagePromotionDTO;
	}
	@ResponseBody
	@RequestMapping(value="/savePackagePromotion.json", method=RequestMethod.POST)
	public Long savePackagePromotion(HttpServletRequest request,
			@RequestBody th.net.cat.epis.dto.PackagePromotion packagePromotion
			) throws Exception {
		 th.net.cat.epis.entity.PackagePromotion target=new th.net.cat.epis.entity.PackagePromotion();
		 BeanUtils.copyProperties(packagePromotion, target);
		 String userid=SecurityContextHolder.getContext().getAuthentication().getName();	
		 target.setCreatedBy(userid);
		 target.setCreatedDate(new Date());
		logger.debug(" savePackagePromotion["+target.getId()+"]");	
		 packagePromotionRepository.save(target);
		return target.getId();
	}
}

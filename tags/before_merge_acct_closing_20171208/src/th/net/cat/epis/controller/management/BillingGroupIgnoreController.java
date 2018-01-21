package th.net.cat.epis.controller.management;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.dto.BillingGroupIgnoreDTO;
import th.net.cat.epis.entity.BillingGroupIgnore;
import th.net.cat.epis.repo.BillingGroupIgnoreRepository;
@Controller
public class BillingGroupIgnoreController {
	private static Logger logger = Logger.getLogger(BillingGroupIgnoreController.class);
	@Autowired BillingGroupIgnoreRepository billingGroupIgnoreRepository;
	
	@Resource(name="episJdbcTemplate") 
	JdbcTemplate episJdbcTemplate;

	@ResponseBody
	@RequestMapping(value="/countBillingGroupIgnore.json", method=RequestMethod.POST)
	public Integer countBillingGroupIgnore(
			ModelMap modelMap,@RequestBody th.net.cat.epis.dto.BillingGroupIgnore billingGroupParam
			) throws Exception {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("billingGroups", billingGroupParam);
	      String sql = "SELECT count(*) FROM MASBILLING_GROUP_IGNORE WHERE BILLING_GROUP  in ( ? ) ";
	      int count = episJdbcTemplate.queryForObject(
	                              sql, new Object[] { billingGroupParam.getBillingGroup() }, Integer.class);
	      
		return count;
	}
	
	@ResponseBody
	@RequestMapping(value="/getBillingGroupIgnore.json", method={RequestMethod.GET,RequestMethod.POST})
	public BillingGroupIgnoreDTO getBillingGroupIgnore(HttpServletRequest request, 
			@RequestParam(value="billingGroup", required=false) String billingGroupParam
			) throws Exception {
		final BillingGroupIgnoreDTO billingGroupIgnoreDTO = new BillingGroupIgnoreDTO();
		System.out.println(" into save Billing Group");
		List<th.net.cat.epis.entity.BillingGroupIgnore> billingGroupIgnoreList =null;
			if(billingGroupParam!=null && billingGroupParam.trim().length()>0)
				billingGroupIgnoreList=billingGroupIgnoreRepository.findCountBillingGroupIgnore(billingGroupParam.trim().toLowerCase());
			else
				billingGroupIgnoreList=(List<BillingGroupIgnore>) billingGroupIgnoreRepository.findAll();
		List<th.net.cat.epis.dto.BillingGroupIgnore> dtoList=new ArrayList<th.net.cat.epis.dto.BillingGroupIgnore>();
		for(th.net.cat.epis.entity.BillingGroupIgnore obj: billingGroupIgnoreList){
			th.net.cat.epis.dto.BillingGroupIgnore dto=new th.net.cat.epis.dto.BillingGroupIgnore();
			BeanUtils.copyProperties(obj, dto);
			dtoList.add(dto);
		}
		billingGroupIgnoreDTO.setData(dtoList);
		return billingGroupIgnoreDTO;
	}
	@ResponseBody
	@RequestMapping(value="/getBillingGroupIgnorePOST.json", method=RequestMethod.POST)
	public BillingGroupIgnoreDTO getBillingGroupIgnorePOST(HttpServletRequest request, 
			@RequestBody th.net.cat.epis.dto.BillingGroupIgnore billingGroupParam
			) throws Exception {
		logger.info("billingGroup["+billingGroupParam.getBillingGroup()+"]");
		final BillingGroupIgnoreDTO billingGroupIgnoreDTO = new BillingGroupIgnoreDTO();
		List<th.net.cat.epis.entity.BillingGroupIgnore> billingGroupIgnoreList =null;
			if(billingGroupParam.getBillingGroup()!=null && billingGroupParam.getBillingGroup().trim().length()>0)
				billingGroupIgnoreList=billingGroupIgnoreRepository.findCountBillingGroupIgnore(billingGroupParam.getBillingGroup().trim().toLowerCase());
			else
				billingGroupIgnoreList=(List<BillingGroupIgnore>) billingGroupIgnoreRepository.findAll();
		List<th.net.cat.epis.dto.BillingGroupIgnore> dtoList=new ArrayList<th.net.cat.epis.dto.BillingGroupIgnore>();
		for(th.net.cat.epis.entity.BillingGroupIgnore obj: billingGroupIgnoreList){
			th.net.cat.epis.dto.BillingGroupIgnore dto=new th.net.cat.epis.dto.BillingGroupIgnore();
			BeanUtils.copyProperties(obj, dto);
			dtoList.add(dto);
		}
		billingGroupIgnoreDTO.setData(dtoList);
		return billingGroupIgnoreDTO;
	}
	@ResponseBody
	@RequestMapping(value="/saveBillingGroupIgnore.json", method=RequestMethod.POST)
	public Long saveBillingGroupIgnore(HttpServletRequest request,
			@RequestBody th.net.cat.epis.dto.BillingGroupIgnore billingGroupParam
			) throws Exception {
		 th.net.cat.epis.entity.BillingGroupIgnore target=new th.net.cat.epis.entity.BillingGroupIgnore();
		 BeanUtils.copyProperties(billingGroupParam, target);
		 logger.debug(" saveBillingGroupIgnore["+target.getId()+"]");	
			 billingGroupIgnoreRepository.save(target);
		return target.getId();
	}
}

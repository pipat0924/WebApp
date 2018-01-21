package th.net.cat.epis.controller.management;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import th.net.cat.epis.dto.BillingGroupCreditLimitDTO;
import th.net.cat.epis.entity.BillingGroupCreditLimit;
import th.net.cat.epis.repo.BillingGroupCreditLimitRepository;
import th.net.cat.epis.repo.InvoiceRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by T'Tee Puthy on 2/6/2017.
 */
@Controller
public class BillingGroupCreditLimitController {
    private static Logger logger = Logger.getLogger(BillingGroupCreditLimitController.class);
    @Autowired
    BillingGroupCreditLimitRepository billingGroupCreditLimitRepository;

    @Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;

    @ResponseBody
    @RequestMapping(value="/countBillingGroupCreditLimit.json", method= RequestMethod.POST)
    public Integer countBillingGroupCreditLimit(
            ModelMap modelMap, @RequestBody th.net.cat.epis.dto.BillingGroupCreditLimit billingGroupParam
    ) throws Exception {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("billingGroups", billingGroupParam);
        String sql = "SELECT count(*) FROM MASBILLING_GROUP_CREDIT_LIMIT WHERE BILLING_GROUP_CODE  in ( ? ) ";
        int count = episJdbcTemplate.queryForObject(
                sql, new Object[] { billingGroupParam.getBillingGroupCode() }, Integer.class);

        return count;
    }

    @ResponseBody
    @RequestMapping(value="/getBillingGroupCreditLimit.json", method={RequestMethod.GET,RequestMethod.POST})
    public BillingGroupCreditLimitDTO getBillingGroupCreditLimit(HttpServletRequest request,
                                                                 @RequestParam(value="billingGroupCode", required=false) String billingGroupParam
    ) throws Exception {
        final BillingGroupCreditLimitDTO billingGroupCreditLimitDTO = new BillingGroupCreditLimitDTO();
        List<BillingGroupCreditLimit> billingGroupCreditLimitList =null;
        if(billingGroupParam!=null && billingGroupParam.trim().length()>0)
            billingGroupCreditLimitList=billingGroupCreditLimitRepository.findCountBillingGroupCreditLimit(billingGroupParam.trim().toLowerCase());
        else
            billingGroupCreditLimitList=(List<BillingGroupCreditLimit>) billingGroupCreditLimitRepository.findAll();
        List<th.net.cat.epis.dto.BillingGroupCreditLimit> dtoList=new ArrayList<th.net.cat.epis.dto.BillingGroupCreditLimit>();
        for(th.net.cat.epis.entity.BillingGroupCreditLimit obj: billingGroupCreditLimitList){
            th.net.cat.epis.dto.BillingGroupCreditLimit dto=new th.net.cat.epis.dto.BillingGroupCreditLimit();
            BeanUtils.copyProperties(obj, dto);
            dtoList.add(dto);
        }
        billingGroupCreditLimitDTO.setData(dtoList);
        return billingGroupCreditLimitDTO;
    }
    @ResponseBody
    @RequestMapping(value="/getBillingGroupCreditLimitPOST.json", method=RequestMethod.POST)
    public BillingGroupCreditLimitDTO getBillingGroupCreditLimitPOST(HttpServletRequest request,
                                                                     @RequestBody th.net.cat.epis.dto.BillingGroupCreditLimit billingGroupParam
    ) throws Exception {
        logger.info("billingGroupCode["+billingGroupParam.getBillingGroupCode()+"]");
        final BillingGroupCreditLimitDTO billingGroupCreditLimitDTO = new BillingGroupCreditLimitDTO();
        List<th.net.cat.epis.entity.BillingGroupCreditLimit> billingGroupCreditLimitList =null;
        if(billingGroupParam.getBillingGroupCode()!=null && billingGroupParam.getBillingGroupCode().trim().length()>0)
            billingGroupCreditLimitList=billingGroupCreditLimitRepository.findCountBillingGroupCreditLimit(billingGroupParam.getBillingGroupCode().trim().toLowerCase());
        else
            billingGroupCreditLimitList=(List<BillingGroupCreditLimit>) billingGroupCreditLimitRepository.findAll();
        List<th.net.cat.epis.dto.BillingGroupCreditLimit> dtoList=new ArrayList<th.net.cat.epis.dto.BillingGroupCreditLimit>();
        for(th.net.cat.epis.entity.BillingGroupCreditLimit obj: billingGroupCreditLimitList){
            th.net.cat.epis.dto.BillingGroupCreditLimit dto=new th.net.cat.epis.dto.BillingGroupCreditLimit();
            BeanUtils.copyProperties(obj, dto);
            dtoList.add(dto);
        }
        billingGroupCreditLimitDTO.setData(dtoList);
        return billingGroupCreditLimitDTO;
    }
    @ResponseBody
    @RequestMapping(value="/saveBillingGroupCreditLimit.json", method=RequestMethod.POST)
    public Long saveBillingGroupCreditLimit(HttpServletRequest request,
                                            @RequestBody th.net.cat.epis.dto.BillingGroupCreditLimit billingGroupParam
    ) throws Exception {
        th.net.cat.epis.entity.BillingGroupCreditLimit target=new th.net.cat.epis.entity.BillingGroupCreditLimit();
        BeanUtils.copyProperties(billingGroupParam, target);
        logger.debug(" saveBillingGroupCreditLimit["+target.getId()+"]");
        billingGroupCreditLimitRepository.save(target);
        return target.getId();
    }

    @ResponseBody
    @RequestMapping(value="/checkBillingGroupCreditLimit.json", method=RequestMethod.GET)
    public Integer checkBillingGroupCreditLimitJSON(@RequestParam("billingGroupCode") String billingGroupCode)
            throws Exception {
    	  // MapSqlParameterSource parameters = new MapSqlParameterSource();
        // parameters.addValue("billingGroups", billingGroupParam);
         int  count;
         
      
         String sql = "SELECT count(*) FROM MASBILLING_GROUP_CREDIT_LIMIT WHERE BILLING_GROUP_CODE  = '"+billingGroupCode.trim()+"' ";

          count = episJdbcTemplate.queryForObject(sql, Integer.class);
         //billingGroupCode.trim()
         
         return count;


    }
}

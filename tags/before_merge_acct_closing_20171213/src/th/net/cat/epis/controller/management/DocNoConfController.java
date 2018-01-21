package th.net.cat.epis.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.entity.DocNoConf;
import th.net.cat.epis.entity.MapGLServiceTpye;
import th.net.cat.epis.repo.DocNoConfRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;



/**
 * Created by Administrator on 23/11/2560.
 **/

@Controller
@RequestMapping("/DocNoConf")
public class DocNoConfController {
    @Autowired
    DocNoConfRepository docNoConfRepository;
    @Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
    

    @RequestMapping(value="loadDocNoConf.json", method=RequestMethod.GET)
    public void loadDocNoConf(ModelMap modelMap) {
       // modelMap.addAttribute("data", docNoConfRepository.loadDocNoConfList());
        modelMap.addAttribute("statusCode", "0");
    }
    @ResponseBody
    @RequestMapping(value="/saveDocNoConf.json", method = RequestMethod.POST)
    public Long saveDocNoConf(HttpServletRequest request, @RequestBody DocNoConf docNoConf) throws java.lang.Exception {
    	
    	String updateBy ="";
    	Timestamp ts = new Timestamp(System.currentTimeMillis());
    	Timestamp updateDate = ts;
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    		updateBy = authentication.getName(); 
		 }
    	docNoConf.setUpdateBy(updateBy);
    	docNoConf.setUpdateDate(updateDate);
    	docNoConf.setId(docNoConf.getId());
        docNoConfRepository.save(docNoConf);
        return docNoConf.getId();
    }
    
    @RequestMapping(value="/findByDocType.json", method = RequestMethod.GET)
    public void findByDocType(HttpServletRequest request, 
    		@RequestParam(value="docType") String docType
    		,ModelMap modelMap) throws Exception {
        modelMap.addAttribute("data", docNoConfRepository.findByDocType(docType));
  		modelMap.addAttribute("statusCode", "0");
    	
    }
    
    @RequestMapping(value="findSystemType.json", method=RequestMethod.GET)
    public void findSystemType(ModelMap modelMap) {
    	
    	final CommonStatus<DocNoConf> CmnStatus = new CommonStatus<DocNoConf>();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT DISTINCT(SYSTEM_TYPE) ,CASE WHEN SYSTEM_TYPE = 'AGENT' THEN 'หน่วยงานอื่น' ELSE 'EPIS' END FROM DOC_NO_CONF ");
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					DocNoConf conf = new DocNoConf();
					conf.setKey(row.getString(1));
					conf.setValue(row.getString(2));
					CmnStatus.addData(conf);
				}
			}); 			
			modelMap.addAttribute("data", CmnStatus.getData());
			modelMap.addAttribute("statusCode", "0");  
    }
    
    @RequestMapping(value="/findBySystemType.json", method = RequestMethod.GET)
    public void findBySystemType(HttpServletRequest request, 
    		@RequestParam(value="systemType") String systemType
    		,ModelMap modelMap) throws Exception {
        modelMap.addAttribute("data", docNoConfRepository.findBySystemType(systemType));
  		modelMap.addAttribute("statusCode", "0");
    	
    }
   
    @RequestMapping(value="/findBySystemCode.json", method = RequestMethod.GET)
    public void findBySystemCode(HttpServletRequest request, 
    		@RequestParam(value="systemCode") String systemCode
    		,ModelMap modelMap) throws Exception {
    	
        modelMap.addAttribute("data", docNoConfRepository.findBySystemCode(systemCode));
  		modelMap.addAttribute("statusCode", "0");
    	
    }

    @ResponseBody
    @RequestMapping(value="/findById.json", method=RequestMethod.GET)
    public List<DocNoConf> findById(ModelMap modelMap,
     @RequestParam(value="id") Long id) throws Exception {
        if(id != null) {
            return docNoConfRepository.findById(id);
        } return null;
    }

    @RequestMapping(value="searchDocNoCof.json", method=RequestMethod.GET)
    public void searchDocNoCof(ModelMap modelMap,
    		@RequestParam(value="systemType") String systemType,
    		@RequestParam(value="systemCode") String systemCode,
    		@RequestParam(value="docName") String docName)throws Exception {
			
    			if(systemType.equalsIgnoreCase("All")){ systemType = "";}
    	    	if(systemCode.equalsIgnoreCase("All")){ systemCode = "";}
    	        if(docName.equalsIgnoreCase("All")){ docName = "";}
    	        
  			final CommonStatus<DocNoConf> CmnStatus = new CommonStatus<DocNoConf>();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT *   ");
			queryBuilder.append(" FROM  DOC_NO_CONF ");
			queryBuilder.append(" WHERE SYSTEM_TYPE LIKE '%"+ systemType +"%'");
			queryBuilder.append(" AND SYSTEM_CODE LIKE '%"+ systemCode +"%'");
			queryBuilder.append(" AND DOC_NAME LIKE '%"+ docName +"%'");
			queryBuilder.append(" ORDER BY ID ASC ");
			System.out.println(" sql : "+queryBuilder.toString());
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
  				@Override
  				public void processRow(ResultSet row) throws SQLException {
  					DocNoConf conf = new DocNoConf();
  					conf.setId(row.getLong(1));
  					conf.setDocNo(row.getString(2));
  					conf.setDocName(row.getString(3));
  					conf.setBranchCode(row.getString(4));
  					conf.setPosNo(row.getString(5));
  					conf.setDocType(row.getString(6));
  					conf.setDateFormat(row.getString(7));
  					conf.setBdEraFlag(row.getString(8));
  					conf.setRunningNo(row.getString(9));
  					conf.setSystemType(row.getString(10));
  					conf.setFormat(row.getString(11));
  					conf.setStatus(row.getString(12));
  					conf.setSystemCode(row.getString(17));
  					CmnStatus.addData(conf);
  				}
  			}); 			
  			modelMap.addAttribute("data", CmnStatus.getData());
  			modelMap.addAttribute("statusCode", "0");  			
  		}
}







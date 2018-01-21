package th.net.cat.epis.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.entity.MapGLServiceTpye;
import th.net.cat.epis.repo.MapGLServiceTypeRepository;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;


/**
 * Created by Administrator on 25/5/2560.
 */
@Controller
public class MapGLServiceTypeController {
    @Autowired
    MapGLServiceTypeRepository mapGLServiceTypeRepository;
    @Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
    
/*
    @ResponseBody
    @RequestMapping(value="/saveMapGLServiceType.json", method= RequestMethod.POST)

    public String save(HttpServletRequest request, @RequestBody MapGLServiceTpye mapGLServiceTpye) throws Exception {
        mapGLServiceTpye.setGlCode(mapGLServiceTpye.getGlCode());
        *//*mapGLServiceTpye.setServiceCode(mapGLServiceTpye.getId());*//*
        mapGLServiceTypeRepository.save(mapGLServiceTpye);
        return mapGLServiceTpye.getGlCode();
    }*/
//ad edited
    @ResponseBody
    @RequestMapping(value="/saveMapGLServiceType.json", method = RequestMethod.POST)
    public Long saveUnit(HttpServletRequest request, @RequestBody MapGLServiceTpye mapGLServiceTpye) throws Exception {
        mapGLServiceTpye.setServId(mapGLServiceTpye.getServId());
        mapGLServiceTypeRepository.save(mapGLServiceTpye);
        return mapGLServiceTpye.getServId();
    }
//ad
    @ResponseBody
    @RequestMapping(value="/editMapGLServiceType.json", method = RequestMethod.POST)
    public Long editService(HttpServletRequest request, @RequestBody MapGLServiceTpye mapGLServiceTpye) throws java.lang.Exception {
        mapGLServiceTpye.setServId(mapGLServiceTpye.getServId());
        mapGLServiceTypeRepository.save(mapGLServiceTpye);
        return mapGLServiceTpye.getServId();
    }
//pay
    @RequestMapping(value="loadServiceNameMapGl.json", method=RequestMethod.GET)
    public void loadServiceNameMapGl(ModelMap modelMap) {
        modelMap.addAttribute("data", mapGLServiceTypeRepository.loadServiceNameList());
        modelMap.addAttribute("statusCode", "0");
    }
//ad
    @ResponseBody
    @RequestMapping(value="/findServiceNameMapGl.json", method=RequestMethod.POST)
    public List<MapGLServiceTpye> findServiceNameMapGlJSON(@RequestBody MapGLServiceTpye mapGLServiceTpye) throws java.lang.Exception {
        List<MapGLServiceTpye> mapGLServiceTpyeList = mapGLServiceTypeRepository.findByRevenueTypeCodeOrProductCode(mapGLServiceTpye.getRevenueTypeCode(), mapGLServiceTpye.getProductCode());
        return mapGLServiceTpyeList;
    }
//ad edited
    @ResponseBody
    @RequestMapping(value="/findServiceNameMapGlByServiceCode.json", method=RequestMethod.GET)
    public List<MapGLServiceTpye> findServiceNameMapGlByServiceCodeJSON(ModelMap modelMap,
     @RequestParam(value="servId") Long servId) throws Exception {
        if(servId != null) {
            return mapGLServiceTypeRepository.findByServId(servId);
        } return null;
    }
    //edited
    @ResponseBody
    @RequestMapping(value="/findByServiceCode.json", method=RequestMethod.POST)
    public List<MapGLServiceTpye> findByServiceCodeJSON(@RequestParam(value="serviceCode") String serviceCode) throws java.lang.Exception {
        List<MapGLServiceTpye> mapGLServiceTpyeList = mapGLServiceTypeRepository.findByServiceCode(serviceCode);
        return mapGLServiceTpyeList;
    }
    
    @RequestMapping(value = "findBySource.json", method = RequestMethod.GET)
  	public void findListByServiceType(@RequestParam("source") String source, ModelMap modelMap) {
  		modelMap.addAttribute("data", mapGLServiceTypeRepository.findBySource(source));
  		modelMap.addAttribute("statusCode", "0");
  	}
  	//edited
  	@RequestMapping(value="/findSeviceByRevCode.json", method=RequestMethod.GET)
	public void findSeviceByRevCode(ModelMap modelMap,
			@RequestParam(value="revenueCode") String revenueCode) throws Exception {
		if(isNotBlank(revenueCode)) {
			
  			final CommonStatus<MapGLServiceTpye> CmnStatus = new CommonStatus<MapGLServiceTpye>();

			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT DISTINCT SERVICE_CODE, SERVICE_NAME ");
			queryBuilder.append(" FROM MAP_GL_SERVICE_TYPE ");
			queryBuilder.append(" WHERE  REVENUE_TYPE_CODE = '"+revenueCode+"'");
			queryBuilder.append(" ORDER BY SERVICE_NAME ");
			
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
  				@Override
  				public void processRow(ResultSet row) throws SQLException {
  					MapGLServiceTpye mapGl = new MapGLServiceTpye();
  					mapGl.setServId(row.getLong(1));
  					mapGl.setServiceName(row.getString(2));
  					CmnStatus.addData(mapGl);
  				}
  			});
  			
  			modelMap.addAttribute("data", CmnStatus.getData());
  			modelMap.addAttribute("statusCode", "0");
  			
  		}
		}
		//pay edited
	  	@RequestMapping(value="/findGlByServiceCode.json", method=RequestMethod.GET)
		public void findGlByServiceCode(ModelMap modelMap,
				@RequestParam(value="serviceCode") String serviceCode) throws Exception {
			if(isNotBlank(serviceCode)) {
				
	  			final CommonStatus<MapGLServiceTpye> CmnStatus = new CommonStatus<MapGLServiceTpye>();

				StringBuilder queryBuilder = new StringBuilder();
				queryBuilder.append(" SELECT GL_CODE");
				queryBuilder.append(" FROM MAP_GL_SERVICE_TYPE ");
				queryBuilder.append(" WHERE  SERVICE_CODE = '"+serviceCode+"'");
				
				episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
	  				@Override
	  				public void processRow(ResultSet row) throws SQLException {
	  					MapGLServiceTpye mapGl = new MapGLServiceTpye();
	  					mapGl.setGlCode(row.getString(1));
	  					CmnStatus.addData(mapGl);
	  				}
	  			});
	  			
	  			modelMap.addAttribute("data", CmnStatus.getData());
	  			modelMap.addAttribute("statusCode", "0");
	  			
	  		} 
	}
}
  	







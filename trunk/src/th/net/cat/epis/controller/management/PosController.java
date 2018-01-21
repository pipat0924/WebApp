package th.net.cat.epis.controller.management;

import static org.apache.commons.lang.StringUtils.isNotBlank;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.entity.Pos;
import th.net.cat.epis.repo.PosRepository;
import th.net.cat.epis.repo.ShopRepository;

@Controller
public class PosController {

	@Autowired PosRepository posRepository;
	@Autowired ShopRepository shopRepository;
	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	@ResponseBody
	@RequestMapping(value="/findPosByBranchAttribute.json", method=RequestMethod.GET)
	public Iterable<Pos> findPosByBranchAttributeJSON(ModelMap modelMap,
			@RequestParam(value="no") String no, 
			@RequestParam(value="name") String name,
			@RequestParam(value="shop") String shop) throws Exception {
		
		Iterable<Pos> responseObj = null;
		if(isNotBlank(no) || isNotBlank(name) || isNotBlank(shop)) {
			if(isNotBlank(shop)){
				responseObj = posRepository.findByNoContainingIgnoreCaseAndNameContainingIgnoreCaseAndShop_No(no, name, shop);
			}else{
				responseObj = posRepository.findByNoContainingIgnoreCaseAndNameContainingIgnoreCase(no, name);
			}
		} return responseObj;
	}
	
	@ResponseBody
	@RequestMapping(value="/findPosByShopAttribute.json", method=RequestMethod.GET)
	public Iterable<Pos> findPosByShopAttributeJSON(ModelMap modelMap,
			@RequestParam(value="shop") String shop) throws Exception {
		if(isNotBlank(shop)) {
			return posRepository.findByShop_Id(new Long(shop));
		} return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/savePosByBranchAttribute.json", method=RequestMethod.POST)
	public Long savePosByBranchAttribute(HttpServletRequest request, @RequestBody Pos pos) throws Exception {
			
		if(pos.getId() == null) {

			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select * from MASPOS where POSNO  = ? and SHOPID = ? ");
			List<Map<String, Object>> result = episJdbcTemplate.queryForList(queryBuilder.toString(),new Object[]{pos.getNo(),pos.getShop().getId()});
			
			
			if(result.size() > 0 ) {
				return 0L;
			}else {
				posRepository.save(pos);
				return pos.getId();
			}
		}else {
			Pos update = posRepository.findOne(pos.getId());
			BeanUtils.copyProperties(pos, update);
			return posRepository.save(update).getId();
		}
	
	}
	
	@ResponseBody
	@RequestMapping(value="/findPosDetailById.json", method=RequestMethod.GET)
	public Pos findPosDetailByIdJSON(ModelMap modelMap,
			@RequestParam(value="id") String id) throws Exception {
		if(isNotBlank(id)) {
			return posRepository.findOne(new Long(id));
		} return null;
	}
	
	@RequestMapping(value="/findPosList.json", method=RequestMethod.GET)
	public void findPosListJSON(ModelMap modelMap) {
		modelMap.addAttribute("data", posRepository.findAll());
		modelMap.addAttribute("statusCode", "0");
	}

	//picht 06/09/17
	@RequestMapping(value="/findPosListByShopId.json", method=RequestMethod.GET)
	public void findPosListByShopId(ModelMap modelMap,
			@RequestParam(value="shop") String shop) throws Exception {
		if(isNotBlank(shop)) {
			
			final CommonStatus<Pos> CmnStatus = new CommonStatus<Pos>();

			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT MP.POSNO ,MS.SHOPNAME || '/' || MP.POSNO AS POSDESC, MP.POSNAME, MS.SHOPNAME ");
			queryBuilder.append(" FROM MASPOS MP  ");
			queryBuilder.append(" INNER JOIN MASSHOP MS ON MP.SHOPID = MS.SHOPID ");
			queryBuilder.append(" WHERE MS.SHOPID = '"+shop+"'");
			queryBuilder.append(" ORDER BY MP.POSNO ");
			
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					Pos pos = new Pos();
					pos.setNo(row.getString(1));
					pos.setName(row.getString(2));
					//pos.setDescription(row.getString(3));
					//pos.setMac(row.getString(4));
					CmnStatus.addData(pos);
				}
			});
			
			modelMap.addAttribute("data", CmnStatus.getData());
			modelMap.addAttribute("statusCode", "0");
		} 
	}

    
}

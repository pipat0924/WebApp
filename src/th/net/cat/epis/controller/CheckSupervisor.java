package th.net.cat.epis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.controller.otboss.OTTBossController;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;

@Service
public class CheckSupervisor {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(OTTBossController.class);
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@Resource(name = "viewCrmJdbcTemplate") JdbcTemplate viewCrmJdbcTemplate;
	public Map getUserCodeLogin(String id) throws Exception {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++ " +id);
		String sql = "select us.USERNAME as superwiser "+
				" from masofficer us, corofficerpos uspos, "+
				" maspos pos "+
				" where us.OFFICERID = uspos.OFFICERID "+
				" and pos.posid = uspos.posid "+
				" and us.PRINCIPALID = 8 "+
				" and pos.shopid in "+
				" ( "+
				" select pos2.shopid "+
				" from masofficer us2, corofficerpos uspos2, "+
				" maspos pos2 "+
				" where us2.OFFICERID = uspos2.OFFICERID "+
				" and pos2.posid = uspos2.posid "+
				" and us2.USERNAME ='"+id+"'"+
				" ) GROUP BY us.USERNAME";
		logger.info(sql);
		return episJdbcTemplate.queryForMap(sql);		

	}

	
}

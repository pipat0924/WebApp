package th.net.cat.epis.service;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service

@Transactional
public class DWService {

	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;

	public int sendDateWereHose(Long id) {

		int result = episJdbcTemplate.update("call  DW_PD_IBACSS_DEV(" + id + ")");
		result = episJdbcTemplate.update("call  DW_PT_IBACSS_DEV(" + id + ")");
		result = episJdbcTemplate.update("call  DW_PT_IBACSS_WT_DEV(" + id + ")");
		return result;

	}

	public int sendOtbossWereHose(Long id) {

		int result = episJdbcTemplate.update("call  DW_PD_INV_SOURCE_DEV(" + id + ")");
		result = episJdbcTemplate.update("call  DW_PT_IBACSS_DEV(" + id + ")");
		result = episJdbcTemplate.update("call  DW_PT_SOURCE_WT_DEV(" + id + ")");
		return result;

	}

}

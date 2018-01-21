package th.net.cat.epis.service;


import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@org.springframework.stereotype.Service
public class UtilityService {
	private static Logger logger = Logger.getLogger(UtilityService.class);
	
	@Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;
	
	@Resource(name="billingJdbcTemplate")
    JdbcTemplate billingJdbcTemplate;
	
	@Transactional
	public BigDecimal getNextSequence(String seqName) {
		logger.info("getNextSequence...Start");	
		BigDecimal nextValue= episJdbcTemplate.queryForObject("select " + seqName +".nextval from dual", BigDecimal.class);
		logger.info("getNextSequence...Done >> sequence >>"+nextValue);
		return nextValue;
	}
	
	public String getValue(String code , String groupKey) {
		List<String> args = new  ArrayList<String>();
		args.add(code);
		args.add(groupKey);
		String value= episJdbcTemplate.queryForObject("select value from epis.MASTER_DATA where key = ? and group_key = ? and status = 'Y' ", String.class,args );
		logger.info("getValue...Done >> value = "+value);
		return value;
		
	}

	@Transactional
	public BigDecimal getNextSequenceBilling(String seqName) {
		logger.info("getNextSequenceBilling...Start");	
		BigDecimal nextValue= billingJdbcTemplate.queryForObject("select " + seqName +".nextval from dual", BigDecimal.class);
		logger.info("getNextSequenceBilling...Done >> sequence >>"+nextValue);
		return nextValue;
	}
	
}
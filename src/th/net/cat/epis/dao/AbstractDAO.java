package th.net.cat.epis.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDAO<T> {
	
	protected Logger log;
	
	protected JdbcTemplate jdbcTemplate;
	
	
	public AbstractDAO(Class<T> classz){
		log = Logger.getLogger(classz);
	}
	
	public AbstractDAO(){};

	@Resource(name="episDataSource")
	public void setDataSource( DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	protected void closeStatement(ResultSet rs, PreparedStatement pstmt){
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException ec) {
		    log.error(log.toString());
		} 				
	}

}

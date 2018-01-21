package th.net.cat.epis.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.dto.sap.SaSapTemplateDTO;
import th.net.cat.epis.dto.sap.SaSapTemplateEntity;
import th.net.cat.epis.dto.sap.SapViewDTO;
import th.net.cat.epis.dto.sap.SapViewEntity;
import th.net.cat.epis.dto.sap.ViewDTO;

import com.sun.istack.logging.Logger;

@org.springframework.stereotype.Service
public class SapTemplateServiceImp implements SapTemplateService {

	private static Logger logger = Logger.getLogger(SapTemplateServiceImp.class);

	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;

	@Override
	public SaSapTemplateDTO SelectSaSapTemplate() {

		ArrayList<SaSapTemplateEntity> list = new ArrayList<SaSapTemplateEntity>();
		SaSapTemplateDTO sapTemplateDTO = new SaSapTemplateDTO();
		String sql = "SELECT * FROM SA_SAP_TEMPLATE";

		logger.info("sql######################>>" + sql);
		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {

			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				SaSapTemplateEntity saSap = new SaSapTemplateEntity();

				saSap.setId(row.getInt("ID"));
				saSap.setAttribute_name(row.getString("ATTRIBUTE_NAME"));
				saSap.setLength(row.getString("LENGTH"));
				saSap.setRemark(row.getString("REMARK"));
				saSap.setCreate_by(row.getString("CREATE_BY"));
				saSap.setCreate_date(row.getTimestamp("CREATE_DATE"));
				saSap.setUpdate_by(row.getString("UPDATE_BY"));
				saSap.setUpdate_date(row.getTimestamp("UPDATE_DATE"));
				saSap.setRecord_status(row.getString("RECORD_STATUS"));

				list.add(saSap);
			}

		});
		sapTemplateDTO.setData(list);

		return sapTemplateDTO;
	}

	@Override
	public SapViewDTO SelectSapView() {
		ArrayList<SapViewEntity> list = new ArrayList<SapViewEntity>();
		SapViewDTO sapViewDTO = new SapViewDTO();
		 String sql = "SELECT * FROM V_SAP3";
		logger.info("###queryView###>>" + sql);
		List<SapViewEntity> vlist = episJdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(SapViewEntity.class));
		if (!CollectionUtils.isEmpty(vlist)) {
			sapViewDTO.setData(vlist);
		} else {
			logger.info("####################Data Empty########################");
		}
		return sapViewDTO;
	}

}

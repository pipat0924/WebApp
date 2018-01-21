package th.net.cat.epis.dao.sap;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import th.net.cat.epis.dto.bouncecheque.SapCorreceiptDTO;
import th.net.cat.epis.dto.bouncecheque.SapDebtDTO;

public class SapDebtDao {
	
	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	
	
	public List<SapCorreceiptDTO> getDataAccountCorreCeipt(String accuntNo) {
		List<SapCorreceiptDTO> listSapCor = new ArrayList<SapCorreceiptDTO>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CO.* ,SA.* FROM CORRECEIPT CO WHERE CO.ACCOUNTNO =? ");
			sql.append("INNER JOIN SAP_DEBT SA ON CO.ACCOUNTNO = SA.ACCOUNT_NO");
			sql.append("ORDER BY CO.RECEIPTDTTM DESC");
			listSapCor = (List<SapCorreceiptDTO>) episJdbcTemplate.query(sql.toString(), new Object[]{accuntNo}, new BeanPropertyRowMapper<SapCorreceiptDTO>(SapCorreceiptDTO.class));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listSapCor;
	}
	
	
	public List<SapDebtDTO> getDataAccountSapDebt(String arCode, String docHead ) {
		List<SapDebtDTO> listSap = new ArrayList<SapDebtDTO>();
		List<Object> list =  new ArrayList<Object>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT  SA.* from SAP_DEBT SA ");
			sql.append("WHERE  1=1 ");
			if(!arCode.isEmpty() && !"".equals(arCode)) {
				sql.append("AND SA.ACCOUNT_NO =? ");
				list.add(arCode);
			}
			if(!docHead.isEmpty() && !"".equals(docHead)) {
				sql.append("AND SA.DOC_HEADER_TEXT_INV = ? ");
				list.add(docHead);
			}
			sql.append("ORDER BY SA.DOC_HEADER_TEXT_INV ");
			Object[] obj = null;
			if(list != null && list.size() > 0){	
				obj = list.toArray(new Object[list.size()]);
			}else{
				obj = new Object[]{};
			}
			
			listSap = (List<SapDebtDTO>) episJdbcTemplate.query(sql.toString(), obj, new BeanPropertyRowMapper<SapDebtDTO>(SapDebtDTO.class));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listSap;
	}
}

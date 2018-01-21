package th.net.cat.epis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import th.net.cat.epis.dto.ReportPayment;
import th.net.cat.epis.entity.ReceiptPrinttypeMapping;
import th.net.cat.epis.entity.ReceiptPrinttypeMappingPK;
import th.net.cat.epis.entity.Trsreprint;
import th.net.cat.epis.repo.ReceiptPrintTypeMappingRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.TsrReprintRepository;

@Service("episService")
public class EpisService {
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;	
	@Autowired ReceiptRepository receiptRepo;
	@Autowired TsrReprintRepository tsrReprintRepo;
	@Autowired ReceiptPrintTypeMappingRepository receiptPrintTypeMappingRepo;
	@Transactional
	public void reprintReceipt(Trsreprint trsreprint) {
		Long receiptid=trsreprint.getReceipt().getId();
		BigDecimal category=trsreprint.getCategory();
		String reprint =trsreprint.getReprintflg();
		trsreprint.setVersionstamp(BigDecimal.valueOf(getNextVersionOfTrsReprint(receiptid,category,reprint)));
		 tsrReprintRepo.save(trsreprint);
		
	}
	public Integer getNextVersionOfTrsReprint(Long receiptid ,BigDecimal category ,String reprint) {
		// find next
		Integer count= episJdbcTemplate.queryForObject("SELECT max(count) FROM ( "
				+ " SELECT VERSIONSTAMP AS count "
				+ " FROM TRSREPRINT "
				+ " WHERE RECEIPTID = ? and CATEGORY = ? order by UPDATEDTTM desc) WHERE ROWNUM=1 ", new Object[] { receiptid,
				category }, Integer.class);
		/*Integer count= episJdbcTemplate.queryForObject("SELECT "
				//+ " COUNT(1) AS count "
				+ " max(VERSIONSTAMP) AS count "
				+ " FROM TRSREPRINT "
				+ " WHERE RECEIPTID = ? and CATEGORY = ?  ", new Object[] { receiptid,
						category }, Integer.class);*/
		if (count == null )
			return 1;
		else
			if ("1".equalsIgnoreCase(reprint))
				return count;
			else
				return count+1;
	}
	public th.net.cat.epis.dto.Trsreprint getTrsReprintByVersion(Long receiptid ,BigDecimal category,BigDecimal version) {
		StringBuilder sql = new StringBuilder("SELECT * from ( select * from TRSREPRINT  WHERE RECEIPTID = ? and CATEGORY = ? and VERSIONSTAMP = ? ORDER BY UPDATEDTTM desc ) where rownum=1 ");
		return episJdbcTemplate.queryForObject(sql.toString(),new Object[] { receiptid , category , version }, BeanPropertyRowMapper.newInstance(th.net.cat.epis.dto.Trsreprint.class));
	}
	public List<th.net.cat.epis.dto.Trsreprint> getReprintHistory(th.net.cat.epis.dto.Trsreprint rq) {
			StringBuilder sql = new StringBuilder("select tsreprint.* , receipt.RECEIPTNO from TRSREPRINT tsreprint left join CORRECEIPT receipt on tsreprint.RECEIPTID = receipt.RECEIPTID WHERE 1 = 1 ");
			List<Object> args = new ArrayList<Object>();
			if(StringUtils.isNotBlank(rq.getReceiptno())){
				sql.append(" 	 AND receipt.RECEIPTNO = ? ");
				args.add(rq.getReceiptno());
			}
			if(StringUtils.isNotBlank(rq.getUpdateuser())){
				sql.append(" 	 AND lower(tsreprint.UPDATEUSER) = ? ");
				args.add(rq.getUpdateuser().toLowerCase());
			}
			if(StringUtils.isNotBlank(rq.getSearchStartDate())){
				sql.append(" 	 AND TO_CHAR(tsreprint.UPDATEDTTM, 'DD/MM/YYYY') >= ? ");
				args.add(rq.getSearchStartDate());
			}
			if(StringUtils.isNotBlank(rq.getSearchEndDate())){
				sql.append(" 	 AND TO_CHAR(tsreprint.UPDATEDTTM, 'DD/MM/YYYY') <= ? ");
				args.add(rq.getSearchEndDate());
			}
			 
			if(StringUtils.isNotBlank(rq.getApprovedby())){
		            sql.append(" 	 AND tsreprint.APPROVEDBY = ? ");
		            args.add(rq.getApprovedby());
		    }
			
			sql.append(" order by tsreprint.UPDATEDTTM  DESC ");
			if(CollectionUtils.isEmpty(args)){
				return episJdbcTemplate.query(sql.toString(), BeanPropertyRowMapper.newInstance(th.net.cat.epis.dto.Trsreprint.class));
			}else{
				return episJdbcTemplate.query(sql.toString(), args.toArray(), BeanPropertyRowMapper.newInstance(th.net.cat.epis.dto.Trsreprint.class));
			}	
	}
	

	@Transactional
	public void updateDeductionNo(List<ReportPayment> reportPayments) {
		for (ReportPayment reportPayment : reportPayments) {
				episJdbcTemplate.update("UPDATE TRSDEDUCTION "
						+ "SET DEDUCTIONNO = ? "
						+ "WHERE DEDUCTIONID = ?", reportPayment.getDeductionNo(), reportPayment.getDeductionId() );
		}
		
	}
	@Transactional
	public void setReceiptFormat(Long receiptid ,String receiptFormat) {
		Integer count= episJdbcTemplate.queryForObject("SELECT "
				+ " COUNT(1) AS count "
				+ " FROM RECEIPT_PRINT_TYPE_MAPPING "
				+ " WHERE RECEIPT_ID = ? and PRINT_TYPE = ?  ", new Object[] { receiptid,
						receiptFormat.toUpperCase() }, Integer.class);
		if(count==0){
			episJdbcTemplate.update("INSERT INTO RECEIPT_PRINT_TYPE_MAPPING (RECEIPT_ID , PRINT_TYPE ) "
					+ " VALUES (? , ? )",
					new Object[] { 
							receiptid,
							receiptFormat.toUpperCase()
					});
		}
	}
	public List<ReceiptPrinttypeMapping> getReceiptPrinttypeMapping(ReceiptPrinttypeMapping rq) {
		// find next version
		StringBuilder sql = new StringBuilder("select *  from RECEIPT_PRINT_TYPE_MAPPING  WHERE 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(rq.getReceiptid() != null ){
			sql.append(" 	 AND RECEIPT_ID = ? ");
			args.add(rq.getReceiptid());
		}
		if(StringUtils.isNotBlank(rq.getPrintType())){
			sql.append(" 	 AND PRINT_TYPE = ? ");
			args.add(rq.getPrintType());
		}
		
		if(CollectionUtils.isEmpty(args)){
			return episJdbcTemplate.query(sql.toString(), BeanPropertyRowMapper.newInstance(th.net.cat.epis.entity.ReceiptPrinttypeMapping.class));
		}else{
			return episJdbcTemplate.query(sql.toString(), args.toArray(), BeanPropertyRowMapper.newInstance(th.net.cat.epis.entity.ReceiptPrinttypeMapping.class));
		}	
}

	public Integer findReprintByReceiptID(Long recriptID,Long category) {
		Integer countRow = episJdbcTemplate.queryForObject("SELECT count(RECEIPTID) FROM TRSREPRINT WHERE RECEIPTID = ? AND CATEGORY = ? AND REPRINTFLG IS NOT NULL ", new Object[] { recriptID,category }, Integer.class);
		return countRow;
	}

	@Transactional
	public String getReceiptName(String FlgHeader,String language) {
		if(FlgHeader.equals("4")) {
			FlgHeader= "1";
		}
			String receiptName = episJdbcTemplate.queryForObject("SELECT "
					+ " DESCABVREN"
					+ " FROM ARCENUMS "
					+ " WHERE MAPCODE1 = ? and CATEGORY = 'TITLE.RECEIPT.NAME' ", new Object[]{FlgHeader}, String.class);
			return receiptName;
	}

	@Transactional
	public String getReceiptNameByreceiptId(String receiptId) {
		String receiptName = episJdbcTemplate.queryForObject("SELECT "
				+ " PRINT_TYPE"
				+ " FROM RECEIPT_PRINT_TYPE_MAPPING "
				+ " WHERE RECEIPT_ID = ? ", new Object[]{receiptId}, String.class);
		return receiptName;
	}

}

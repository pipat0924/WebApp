package th.net.cat.epis.service;

import static th.net.cat.epis.util.AppConstants.AFTERSALES_DISCOUNT_METHOD;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_3TREDECIM;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_69BIS;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_69TRE;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_COMPENSATION;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_FEE_IN;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_FEE_OUT;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_PENALTY_IN;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_PENALTY_OUT;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_RETENTION_IN;
import static th.net.cat.epis.util.AppConstants.DEDUCT_METHOD_RETENTION_OUT;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BANKTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_BILLEXCHANGE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CANCEL;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CANCELTAXINVOICE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CASH;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CHEQUE;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_COUPON;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_CREDITCARD;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_FOREIGNBANK;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_FOREIGNTRANSFER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_MONEYORDER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_OFFSET;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_OTHER;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_RECEIPT;
import static th.net.cat.epis.util.AppConstants.PAY_METHOD_RECEIPTTAXINVOICE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.entity.PaymentSummary;
import th.net.cat.epis.entity.Session;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.repo.PaymentSummaryRepository;
import th.net.cat.epis.repo.SessionRepository;

@org.springframework.stereotype.Service
public class UserService {

	@Autowired OfficerRepository officerRepo;
	@Autowired SessionRepository sessionRepo;
	@Autowired PaymentSummaryRepository paySummaryRepo;

	public Session getSession() {
		Long sessionId = EpContextHolder.getContext().getSessionId();
		Session session = sessionRepo.findOne(sessionId);
		if (session == null) {
			session = new Session();
			session.setId(sessionId);
			session.setUserName(EpContextHolder.getContext().getUserName());
			session.setFirstTime(new java.util.Date());
			session.setLastUpdated(new java.util.Date());
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_3TREDECIM));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_69BIS));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_69TRE));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_FEE_IN));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_FEE_OUT));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_PENALTY_IN));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_PENALTY_OUT));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_RETENTION_IN));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_RETENTION_OUT));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_COMPENSATION));
			session.getSummaries().add(new PaymentSummary(sessionId, AFTERSALES_DISCOUNT_METHOD));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CASH));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CHEQUE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CREDITCARD));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_COUPON));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_MONEYORDER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_BANKTRANSFER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_FOREIGNTRANSFER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_FOREIGNBANK));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_BILLEXCHANGE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_OTHER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_RECEIPTTAXINVOICE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CANCELTAXINVOICE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_RECEIPT));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CANCEL));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_OFFSET));

		}
		return session;
	}
	@Transactional
	public void saveSession(Session session) {
		Officer officer = officerRepo.findByName(session.getUserName());
		officer.setSessionId(session.getId());
		sessionRepo.save(session);
		for (PaymentSummary paymentSummary : session.getSummaries()) {
			paySummaryRepo.save(paymentSummary);
		}
	}
	@Transactional
	public void removeSession() {
		Officer officer = officerRepo.findByName(EpContextHolder.getContext().getUserName());
		officer.setSessionId(null);
	}

	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	public Session getSessionForCloseBranch(String closeDate) {
		String username = EpContextHolder.getContext().getUserCode();
		System.out.println("usercode = "+ username);
		System.out.println("codedate = "+ closeDate);
		StringBuilder queryBuilder = new StringBuilder();
		final ArrayList<Long> sessionIdList = new ArrayList<Long>();
		queryBuilder.append(" SELECT MAX(sessionid) FROM corsession WHERE username = '"+ username +"' AND TRUNC(firsttime) = TO_DATE('"+ closeDate +"', 'DD-MM-YYYY')");
		episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet row) throws SQLException {
				sessionIdList.add(row.getLong(1));
			}
		});
		final ArrayList<Long> receiptIdList = new ArrayList<Long>();
		if(sessionIdList.size() > 0) {
			queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT receiptid FROM correceipt WHERE isendofday IS NULL AND updateuser = '"+ username +"' AND TRUNC(receiptdttm) = TO_DATE('"+ closeDate +"', 'DD-MM-YYYY')");
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
					receiptIdList.add(row.getLong(1));
				}
			});
		}
		Session session = sessionRepo.findOne(receiptIdList.size() == 0 ? 0L : (Long)sessionIdList.get(0));
		if(session == null) {
			Long sessionId = EpContextHolder.getContext().getSessionId();
			session = new Session();
			session.setId(sessionId);
			session.setUserName(EpContextHolder.getContext().getUserName());
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_3TREDECIM));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_69BIS));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_69TRE));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_FEE_IN));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_FEE_OUT));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_PENALTY_IN));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_PENALTY_OUT));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_RETENTION_IN));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_RETENTION_OUT));
			session.getSummaries().add(new PaymentSummary(sessionId, DEDUCT_METHOD_COMPENSATION));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CASH));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CHEQUE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CREDITCARD));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_COUPON));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_MONEYORDER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_BANKTRANSFER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_FOREIGNTRANSFER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_FOREIGNBANK));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_BILLEXCHANGE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_OTHER));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_RECEIPTTAXINVOICE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CANCELTAXINVOICE));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_RECEIPT));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_CANCEL));
			session.getSummaries().add(new PaymentSummary(sessionId, PAY_METHOD_OFFSET));
		}
		return session;
	}
	
	@Transactional
	public List<Map<String, Object>> selectSuperwiseser(String user) {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select us.USERNAME as SUPERWISER from masofficer us, corofficerpos uspos, maspos pos ");
		queryBuilder.append("where us.OFFICERID = uspos.OFFICERID and pos.posid = uspos.posid ");
		queryBuilder.append("and us.PRINCIPALID = 8 and pos.shopid in ( ");
		queryBuilder.append("select pos2.shopid from masofficer us2, corofficerpos uspos2, ");
		queryBuilder.append("maspos pos2 where us2.OFFICERID = uspos2.OFFICERID ");
		queryBuilder.append("and pos2.posid = uspos2.posid and us2.USERNAME = ? ) Group by USERNAME ");
		List<Map<String, Object>> result = episJdbcTemplate.queryForList(queryBuilder.toString(),new Object[]{user});
		return result;
	}
	@Transactional
	public boolean selectCheckAuthenSuperwiser(String user,String pass) {
		Officer officer = officerRepo.findByName(user);
//		List<Map<String, Object>> result  = episJdbcTemplate.queryForList("select PASSWORD from ARCUSERAUTHNTICN where OFFICERID = ? ",new Object[]{officer.getId()});
		
		if(officer!=null
				&& new Md5PasswordEncoder().isPasswordValid(officer.getPassword()+"", pass, null)
				&& officer.getPrincipal()!=null && officer.getPrincipal().getName().equals("Suppervisor")) {
				return true;
			}
		return false;
	}
	
	public boolean isDuplicateUserName(String user) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select * from MASOFFICER where USERNAME  = ?");
		List<Map<String, Object>> result = episJdbcTemplate.queryForList(queryBuilder.toString(),new Object[]{user});
		if(result.size() > 0 ) {
			return true;
		}else {
			return false;
		}
	
	}
}
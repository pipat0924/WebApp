package th.net.cat.epis.controller;

import static java.util.Locale.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import th.net.cat.epis.EpWebApplicationInitializer;

public class EpContextHolder {

	static final ThreadLocal<EpContext> contextHolder = new InheritableThreadLocal<EpContext>();

	public static EpContext getContext() {
		return contextHolder.get();
	}

	public static class EpContext implements Serializable {
		
		private static final long serialVersionUID = 7535161242912111843L;
		private final Long sessionId;
		private final Long officerId;
		private final Long branchId;
		private final Long posId;
		private final String userName;
		private final String userCode;
		private final String userGivenName;
		private final String userFamilyName;
		private final Long roleId;
		private final String roleName;
		private final String roleDesc;
		private final String branchCode;
		private final String branchArea;
		private final String branchName;
		private final String costCenter;
		private final String descAbvrth;
		private final String posNo;
		private final String posName;
		private final String macAddress;
		private final Integer isPositive;
		private List<String> owners;//new ArrayList<String>();
//		private final Map<Long, String> permissions;
		
		public Long getSessionId() {
			return sessionId;
		}
		public Long getOfficerId() {
			return officerId;
		}
		public Long getBranchId() {
			return branchId;
		}
		public Long getPosId() {
			return posId;
		}
		public String getUserName() {
			return userName;
		}
		public String getUserCode() {
			return userCode;
		}
		public String getUserGivenName() {
			return userGivenName;
		}
		public String getUserFamilyName() {
			return userFamilyName;
		}
		public String getRoleName() {
			return roleName;
		}
		public String getRoleDesc() {
			return roleDesc;
		}
		public String getBranchCode() {
			return branchCode;
		}
		public String getBranchArea() {
			return branchArea;
		}
		public String getBranchName() {
			return branchName;
		}
		public String getCostCenter() {
			return costCenter;
		}
		public String getDescAbvrth() {
			return descAbvrth;
		}
		public String getPosNo() {
			return posNo;
		}
		public String getPosName() {
			return posName;
		}
		public String getMacAddress() {
			return macAddress;
		}
		public Integer getIsPositive() {
			return isPositive;
		}
		public Long getRoleId() {
			return roleId;
		}
		public List<String> getOwners() {
			return owners;
		}
		
		EpContext(JdbcTemplate jdbcTemplate) {
			this.userName = SecurityContextHolder.getContext().getAuthentication().getName();
			Map<String, Object> userInfo = jdbcTemplate.queryForMap("SELECT o.officerid AS officeid, o.username AS username, o.officercode AS usercode, o.officerfamilyname AS familyname, o.officergivenname AS givenname, o.sessionid, o.ispositive AS ispositive, p.principalid AS roleid, p.name AS rolename, p.description AS roledesc FROM MASOFFICER o INNER JOIN ARCPRINCIPAL p ON o.principalid = p.principalid WHERE o.username = ?", userName);
			this.officerId = ((BigDecimal) userInfo.get("officeid")).longValue();
			Long sessId = userInfo.get("sessionid") == null ? null : ((BigDecimal) userInfo.get("sessionid")).longValue();
			List<Long> posIds = jdbcTemplate.queryForList("SELECT posid FROM COROFFICERPOS WHERE officerid = ?", new Object[] { this.officerId }, Long.class); 
			Map<String, Object> posInfo = posIds.size() < 1 ? new java.util.HashMap<String, Object>() : jdbcTemplate.queryForMap("SELECT p.posid, p.posno, p.posname, p.mac, p.shopid, s.buplace, s.buarea, s.shopno, s.shopname, s.costcenter, s.descabvrth FROM MASPOS p INNER JOIN MASSHOP s ON s.shopid = p.shopid WHERE p.posid = ?", posIds.get(0));
			Map<String, Object> sessionInfo = sessId == null ? null : jdbcTemplate.queryForMap("SELECT sessionid, username, firsttime, lastupdated FROM CORSESSION WHERE sessionid = ?", sessId);
			Calendar calendar = new GregorianCalendar(ENGLISH);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			if (sessId == null || sessionInfo == null || (sessionInfo.containsKey("lastupdated") && calendar.getTime().after((java.util.Date) sessionInfo.get("lastupdated")))) {
				sessId = jdbcTemplate.queryForObject("SELECT session_seq.nextval FROM dual", Long.class);
			}

			this.sessionId = sessId;
			this.posId = !posInfo.containsKey("posid") ? -1L : ((BigDecimal) posInfo.get("posid")).longValue();
			this.branchId = !posInfo.containsKey("shopid") ? -1L : ((BigDecimal) posInfo.get("shopid")).longValue();
			this.userCode = (String) userInfo.get("usercode");
			this.userGivenName = (String) userInfo.get("givenname");
			this.userFamilyName = (String) userInfo.get("familyname");
			this.roleId = (userInfo.get("roleid") == null) ? -1L : ((BigDecimal) userInfo.get("roleid")).longValue();
			this.roleName = (String) userInfo.get("rolename");
			this.roleDesc = (String) userInfo.get("roledesc");
			this.branchCode = (String) posInfo.get("buplace");
			this.branchArea = (String) posInfo.get("buarea");
			this.branchName = (String) posInfo.get("shopname");
			this.costCenter = (String) posInfo.get("costcenter");
			this.descAbvrth = (String) posInfo.get("descabvrth");
			this.posNo = (String) posInfo.get("posno");
			this.posName = (String) posInfo.get("posname");
			this.macAddress = (String) posInfo.get("mac");
			this.isPositive = !userInfo.containsKey("ispositive") ? -1 : ((BigDecimal) userInfo.get("ispositive")).intValue();

			
			 if("Suppervisor".equalsIgnoreCase(roleName)){
				if(this.branchId != null){
					
					System.out.println("branchId : " + branchId);
					List<String> allShopUsers = jdbcTemplate.queryForList(
					// 13092017 PICHT
					"select distinct(o.username) "
					+"from maspos p "
					+"inner join corofficerpos op on p.POSID = op.POSID "
					+"inner join masofficer o on op.OFFICERID = o.OFFICERID AND O.PRINCIPALID NOT IN ('8') "
				    // +"inner join masofficer o on op.OFFICERID = o.OFFICERID "
					+"where p.SHOPID = ?"
				    +" order by o.username asc "
					// END 13092017 PICHT
					,new Object[] { this.branchId }, String.class);
					if(allShopUsers != null && allShopUsers.size() > 0){
						owners = new ArrayList<String>();
						owners.addAll(allShopUsers);
					}
				}
			}else {
				owners = new ArrayList<String>();
				owners.add(this.userName);
			}
		}
	}

	public static class Filter extends EpWebApplicationInitializer.ContextHolderFilter<EpContext> {

		static { FILTER = new Filter(); }

		@Override
		public void setContext(EpContext epContext) {
			contextHolder.set(epContext);
		}

		@Override
		public EpContext newContext() {
			return new EpContext(jdbcTemplate);
		}

	}
}
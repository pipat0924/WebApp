package th.net.cat.epis.dao.adapter;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import th.net.cat.epis.dao.AbstractDAO;
import th.net.cat.epis.to.adapter.ExportErpTO;
import th.net.cat.epis.to.management.User;
import th.net.cat.epis.util.AppUtil;

@Repository
public class ErpDAO extends AbstractDAO<ErpDAO> {
	private static Logger logger = Logger.getLogger(ErpDAO.class);


	public ExportErpTO findExportErp(Date date) {
		List<ExportErpTO> ExportErpList = jdbcTemplate
				.query("SELECT BKPF_BLRES,BKPF_BLDAT,BKPF_BLART,BKPF_BUKRS,BKPF_BUDAT,BKPF_MONAT,BKPF_WAERS,BKPF_KURSF,BKPF_WWERT,BKPF_XBLNR,BKPF_BKTXT,BKPF_BRNCH,BKPF_GLVOR,BKPF_GJAHR,BKPF_USNAM,BKPF_AWTYP,BKPF_AWREF,BSEG_BUZEI,BSEG_SHKZG,BSEG_BSCHL,BSEG_KOART,BSEG_HKONT_KUNNR_LIFNR,BSEG_HKONT,BSEG_WRBTR,BSEG_DMBTR,BSEG_FWBAS,BSEG_HWBAS,BSEG_MWSKZ,BSEG_BUPLA,BSEG_GSBER,BSEG_VALUT,BSEG_ZTERM,BSEG_ZFBDT,BSEG_SKFBT,BSEG_KOSTL,BSEG_FKBER,BSEG_PROJK,BSEG_LSTAR,BSEG_SEGMENT,BSEG_ZZWWPRD,BSEG_ZZWWSUB,BSEG_ZZWWREV,BSEG_MATNR,BSEG_WERKS,BSEG_PRZNR,CE11000_WWCUS,BSEG_KIDNO,BSEG_ZOUNR,BSEG_SGTXT,BSEG_XREF1,BSEG_XREF2,BSEG_XREF3,BSEG_VBUND,BSEC_BUZEI,BSEC_ANRED,BSEC_NAME1,BSEC_NAME2,BSEC_NAME3,BSEC_NAME4,BSEC_STRAS,BSEC_ORT01,BSEC_PSTLZ,BSEC_LAND1,BSEC_STCD1,BSEC_STCD2,BSEC_EMPFG,BSEC_BANKL,BSEC_BANKN,BSEC_BANKS,WITH_ITEM_BUZEI,WITH_ITEM_WITHT,WITH_ITEM_WT_WITHCD,WITH_ITEM_WT_QSSHB,WITH_ITEM_WT_QSSHH,WITH_ITEM_WT_QBSHB,WITH_ITEM_WT_QBSHH,WITH_ITEM_WITHT84,WITH_ITEM_WT_WITHCD85,WITH_ITEM_WT_QSSHB86,WITH_ITEM_WT_QSSHH87,WITH_ITEM_WT_QBSHB88,WITH_ITEM_WT_QBSHH89,BSET_BUZEI,BSET_MWSKZ,BSET_HKONT,BSET_SHKZG,BSET_HWBAS,BSET_FWBAS,BSET_HWSTE,BSET_FWSTE,BSET_KTOSL,BSET_KSCHL,BSET_BUPLA,DATE FROM EPIS_ERP_TEMPLATE_PAYMENT",
						BeanPropertyRowMapper.newInstance(ExportErpTO.class));
		return ExportErpList.size() < 1 ? null : ExportErpList.get(0);
	}

	public ExportErpTO findExportErpDate(Date date) {
		List<ExportErpTO> ExportErpList = jdbcTemplate
				.query("SELECT BKPF_BLRES,BKPF_BLDAT,BKPF_BLART,BKPF_BUKRS,BKPF_BUDAT,BKPF_MONAT,BKPF_WAERS,BKPF_KURSF,BKPF_WWERT,BKPF_XBLNR,BKPF_BKTXT,BKPF_BRNCH,BKPF_GLVOR,BKPF_GJAHR,BKPF_USNAM,BKPF_AWTYP,BKPF_AWREF,BSEG_BUZEI,BSEG_SHKZG,BSEG_BSCHL,BSEG_KOART,BSEG_HKONT_KUNNR_LIFNR,BSEG_HKONT,BSEG_WRBTR,BSEG_DMBTR,BSEG_FWBAS,BSEG_HWBAS,BSEG_MWSKZ,BSEG_BUPLA,BSEG_GSBER,BSEG_VALUT,BSEG_ZTERM,BSEG_ZFBDT,BSEG_SKFBT,BSEG_KOSTL,BSEG_FKBER,BSEG_PROJK,BSEG_LSTAR,BSEG_SEGMENT,BSEG_ZZWWPRD,BSEG_ZZWWSUB,BSEG_ZZWWREV,BSEG_MATNR,BSEG_WERKS,BSEG_PRZNR,CE11000_WWCUS,BSEG_KIDNO,BSEG_ZOUNR,BSEG_SGTXT,BSEG_XREF1,BSEG_XREF2,BSEG_XREF3,BSEG_VBUND,BSEC_BUZEI,BSEC_ANRED,BSEC_NAME1,BSEC_NAME2,BSEC_NAME3,BSEC_NAME4,BSEC_STRAS,BSEC_ORT01,BSEC_PSTLZ,BSEC_LAND1,BSEC_STCD1,BSEC_STCD2,BSEC_EMPFG,BSEC_BANKL,BSEC_BANKN,BSEC_BANKS,WITH_ITEM_BUZEI,WITH_ITEM_WITHT,WITH_ITEM_WT_WITHCD,WITH_ITEM_WT_QSSHB,WITH_ITEM_WT_QSSHH,WITH_ITEM_WT_QBSHB,WITH_ITEM_WT_QBSHH,WITH_ITEM_WITHT84,WITH_ITEM_WT_WITHCD85,WITH_ITEM_WT_QSSHB86,WITH_ITEM_WT_QSSHH87,WITH_ITEM_WT_QBSHB88,WITH_ITEM_WT_QBSHH89,BSET_BUZEI,BSET_MWSKZ,BSET_HKONT,BSET_SHKZG,BSET_HWBAS,BSET_FWBAS,BSET_HWSTE,BSET_FWSTE,BSET_KTOSL,BSET_KSCHL,BSET_BUPLA,DATE FROM EPIS_ERP_TEMPLATE_PAYMENT where DATE = ?",
						new Object[] { date },
						BeanPropertyRowMapper.newInstance(ExportErpTO.class));
		return ExportErpList.size() < 1 ? null : ExportErpList.get(0);
	}
	
	

	public void insertExportErp(final ExportErpTO exportErp) throws Exception {
		try {
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO ARCIDENTITY (BKPF_BLRES,BKPF_BLDAT,BKPF_BLART,BKPF_BUKRS,BKPF_BUDAT,BKPF_MONAT,BKPF_WAERS,BKPF_KURSF,BKPF_WWERT,BKPF_XBLNR,BKPF_BKTXT,BKPF_BRNCH,BKPF_GLVOR,BKPF_GJAHR,BKPF_USNAM,BKPF_AWTYP,BKPF_AWREF,BSEG_BUZEI,BSEG_SHKZG,BSEG_BSCHL,BSEG_KOART,BSEG_HKONT_KUNNR_LIFNR,BSEG_HKONT,BSEG_WRBTR,BSEG_DMBTR,BSEG_FWBAS,BSEG_HWBAS,BSEG_MWSKZ,BSEG_BUPLA,BSEG_GSBER,BSEG_VALUT,BSEG_ZTERM,BSEG_ZFBDT,BSEG_SKFBT,BSEG_KOSTL,BSEG_FKBER,BSEG_PROJK,BSEG_LSTAR,BSEG_SEGMENT,BSEG_ZZWWPRD,BSEG_ZZWWSUB,BSEG_ZZWWREV,BSEG_MATNR,BSEG_WERKS,BSEG_PRZNR,CE11000_WWCUS,BSEG_KIDNO,BSEG_ZOUNR,BSEG_SGTXT,BSEG_XREF1,BSEG_XREF2,BSEG_XREF3,BSEG_VBUND,BSEC_BUZEI,BSEC_ANRED,BSEC_NAME1,BSEC_NAME2,BSEC_NAME3,BSEC_NAME4,BSEC_STRAS,BSEC_ORT01,BSEC_PSTLZ,BSEC_LAND1,BSEC_STCD1,BSEC_STCD2,BSEC_EMPFG,BSEC_BANKL,BSEC_BANKN,BSEC_BANKS,WITH_ITEM_BUZEI,WITH_ITEM_WITHT,WITH_ITEM_WT_WITHCD,WITH_ITEM_WT_QSSHB,WITH_ITEM_WT_QSSHH,WITH_ITEM_WT_QBSHB,WITH_ITEM_WT_QBSHH,WITH_ITEM_WITHT84,WITH_ITEM_WT_WITHCD85,WITH_ITEM_WT_QSSHB86,WITH_ITEM_WT_QSSHH87,WITH_ITEM_WT_QBSHB88,WITH_ITEM_WT_QBSHH89,BSET_BUZEI,BSET_MWSKZ,BSET_HKONT,BSET_SHKZG,BSET_HWBAS,BSET_FWBAS,BSET_HWSTE,BSET_FWSTE,BSET_KTOSL,BSET_KSCHL,BSET_BUPLA,CE11000_WWCOC,DATE) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, CURDATE())", Statement.RETURN_GENERATED_KEYS);
					
					preparedStatement.setString(1, parseTextZero(exportErp.getBKPF_BLRES(), 3, false) );
					preparedStatement.setString(2, exportErp.getBKPF_BLDAT());
					preparedStatement.setString(3, exportErp.getBKPF_BLART());
					preparedStatement.setString(4, exportErp.getBKPF_BUKRS());
					preparedStatement.setString(5, exportErp.getBKPF_BUDAT());
					preparedStatement.setString(6, exportErp.getBKPF_MONAT());
					preparedStatement.setString(7, exportErp.getBKPF_WAERS());
					preparedStatement.setString(8, exportErp.getBKPF_KURSF());
					preparedStatement.setString(9, exportErp.getBKPF_WWERT());
					preparedStatement.setString(10, exportErp.getBKPF_XBLNR());
					preparedStatement.setString(11, exportErp.getBKPF_BKTXT());
					preparedStatement.setString(12, exportErp.getBKPF_BRNCH());
					preparedStatement.setString(13, exportErp.getBKPF_GLVOR());
					preparedStatement.setString(14, exportErp.getBKPF_GJAHR());
					preparedStatement.setString(15, exportErp.getBKPF_USNAM());
					preparedStatement.setString(16, exportErp.getBKPF_AWTYP());
					preparedStatement.setString(17, exportErp.getBKPF_AWREF());
					preparedStatement.setString(18, parseTextZero(exportErp.getBSEG_BUZEI(), 3, false));
					preparedStatement.setString(19, exportErp.getBSEG_SHKZG());
					preparedStatement.setString(20, exportErp.getBSEG_BSCHL());
					preparedStatement.setString(21, exportErp.getBSEG_KOART());
					preparedStatement.setString(22, parseTextZero(exportErp.getBSEG_HKONT_KUNNR_LIFNR(), 10, false));
					preparedStatement.setString(23, exportErp.getBSEG_HKONT());
					preparedStatement.setString(24, parseIfZero(exportErp.getBSEG_WRBTR()));
					preparedStatement.setString(25, parseIfZero(exportErp.getBSEG_DMBTR()));
					preparedStatement.setString(26, parseIfZero(exportErp.getBSEG_FWBAS()));
					preparedStatement.setString(27, parseIfZero(exportErp.getBSEG_HWBAS()));
					preparedStatement.setString(28, exportErp.getBSEG_MWSKZ());
					preparedStatement.setString(29, exportErp.getBSEG_BUPLA());
					preparedStatement.setString(30, exportErp.getBSEG_GSBER());
					preparedStatement.setString(31, exportErp.getBSEG_VALUT());
					preparedStatement.setString(32, exportErp.getBSEG_ZTERM());
					preparedStatement.setString(33, exportErp.getBSEG_ZFBDT());
					preparedStatement.setString(34, parseIfZero(exportErp.getBSEG_SKFBT()));
					preparedStatement.setString(35, exportErp.getBSEG_KOSTL());
					preparedStatement.setString(36, exportErp.getBSEG_FKBER());
					preparedStatement.setString(37, exportErp.getBSEG_PROJK());
					preparedStatement.setString(38, exportErp.getBSEG_LSTAR());
					preparedStatement.setString(39, exportErp.getBSEG_SEGMENT());
					preparedStatement.setString(40, parseTextZero(exportErp.getBSEG_ZZWWPRD(), 18, false));
					preparedStatement.setString(41, parseTextZero(exportErp.getBSEG_ZZWWSUB(), 2, false));
					preparedStatement.setString(42, parseTextZero(exportErp.getBSEG_ZZWWREV(), 10, false));
					preparedStatement.setString(43, parseTextZero(exportErp.getBSEG_MATNR(), 18, false));
					preparedStatement.setString(44, exportErp.getBSEG_WERKS());
					preparedStatement.setString(45, exportErp.getBSEG_PRZNR());
					preparedStatement.setString(46, parseTextZero(exportErp.getCE11000_WWCUS(), 10, false));
					preparedStatement.setString(47, exportErp.getBSEG_KIDNO());
					preparedStatement.setString(48, exportErp.getBSEG_ZOUNR());
					preparedStatement.setString(49, exportErp.getBSEG_SGTXT());
					preparedStatement.setString(50, exportErp.getBSEG_XREF1());
					preparedStatement.setString(51, exportErp.getBSEG_XREF2());
					preparedStatement.setString(52, exportErp.getBSEG_XREF3());
					preparedStatement.setString(53, exportErp.getBSEG_VBUND());
					preparedStatement.setString(54, exportErp.getBSEC_BUZEI());
					preparedStatement.setString(55, exportErp.getBSEC_ANRED());
					preparedStatement.setString(56, exportErp.getBSEC_NAME1());
					preparedStatement.setString(57, exportErp.getBSEC_NAME2());
					preparedStatement.setString(58, exportErp.getBSEC_NAME3());
					preparedStatement.setString(59, exportErp.getBSEC_NAME4());
					preparedStatement.setString(60, exportErp.getBSEC_STRAS());
					preparedStatement.setString(61, exportErp.getBSEC_ORT01());
					preparedStatement.setString(62, exportErp.getBSEC_PSTLZ());
					preparedStatement.setString(63, exportErp.getBSEC_LAND1());
					preparedStatement.setString(64, exportErp.getBSEC_STCD1());
					preparedStatement.setString(65, exportErp.getBSEC_STCD2());
					preparedStatement.setString(66, exportErp.getBSEC_EMPFG());
					preparedStatement.setString(67, exportErp.getBSEC_BANKL());
					preparedStatement.setString(68, exportErp.getBSEC_BANKN());
					preparedStatement.setString(69, exportErp.getBSEC_BANKS());
					preparedStatement.setString(70, exportErp.getWITH_ITEM_BUZEI());
					preparedStatement.setString(71, exportErp.getWITH_ITEM_WITHT());
					preparedStatement.setString(72, exportErp.getWITH_ITEM_WT_WITHCD());
					preparedStatement.setString(73, parseIfZero(exportErp.getWITH_ITEM_WT_QSSHB()));
					preparedStatement.setString(74, parseIfZero(exportErp.getWITH_ITEM_WT_QSSHH()));
					preparedStatement.setString(75, parseIfZero(exportErp.getWITH_ITEM_WT_QBSHB()));
					preparedStatement.setString(76, parseIfZero(exportErp.getWITH_ITEM_WT_QBSHH()));
					preparedStatement.setString(77, exportErp.getWITH_ITEM_WITHT84());
					preparedStatement.setString(78, exportErp.getWITH_ITEM_WT_WITHCD85());
					preparedStatement.setString(79, parseIfZero(exportErp.getWITH_ITEM_WT_QSSHB86()));
					preparedStatement.setString(80, parseIfZero(exportErp.getWITH_ITEM_WT_QSSHH87()));
					preparedStatement.setString(81, parseIfZero(exportErp.getWITH_ITEM_WT_QBSHB88()));
					preparedStatement.setString(82, parseIfZero(exportErp.getWITH_ITEM_WT_QBSHH89()));
					preparedStatement.setString(83, parseTextZero(exportErp.getBSET_BUZEI(), 3, false));
					preparedStatement.setString(84, exportErp.getBSET_MWSKZ());
					preparedStatement.setString(85, exportErp.getBSET_HKONT());
					preparedStatement.setString(86, exportErp.getBSET_SHKZG());
					preparedStatement.setString(87,  parseIfZero(exportErp.getBSET_HWBAS()));
					preparedStatement.setString(88,  parseIfZero(exportErp.getBSET_FWBAS()));
					preparedStatement.setString(89,  parseIfZero(exportErp.getBSET_HWSTE()));
					preparedStatement.setString(90,  parseIfZero(exportErp.getBSET_FWSTE()));
					preparedStatement.setString(91, exportErp.getBSET_KTOSL());
					preparedStatement.setString(92, exportErp.getBSET_KSCHL());
					preparedStatement.setString(93, exportErp.getBSET_BUPLA());
					preparedStatement.setString(94, exportErp.getCE11000_WWCOC());
				
					return preparedStatement;
				}
			}, keyHolder);
		} catch(Exception sqex) {
			logger.error(sqex.getMessage());
			throw new Exception(sqex);
		}
	}

	//Format 1
	public String parseText(String data, int size, boolean isString) {
		String str = "";

		if (data == null) {
			data = "";
		}

		data = data.trim();
		int amt = size - data.length();
		for (int i = 0; i < amt; i++) {
			str += " ";
		}
		data += str;
		return data;
	}

	//Format 2
	public String parseTextZero(String data, int size, boolean isString) {
		String str = "";

		if (data == null) {
			data = "";
		}

		data = data.trim();
		int amt = size - data.length();

		for (int i = 0; i < amt; i++) {
			if (isString) {
				str += " ";
			} else {
				str += "0";
			}
		}
		str += data;

		if (str.equals("0000000000") || str.equals("000000000000000000")
				|| str.equals("000") || str.equals("00")) {
			str = "";
		}
		return str;
	}

	//Format 3
	public String parseIfZero(String data) {

		if (data.equals("0.00") || data.equals(0) || data.equals("0")
				|| data.equals("0.0")) {
			data = null;
		} else {
			data = "'" + data + "'";
		}

		return data;
	}

	//Format 4
	public String parseTextRight(String data, int size, boolean isString) {
		String str = "";

		if (data == null) {
			data = "";
		}
		data = data.trim();
		int amt = size - data.length();
		for (int i = 0; i < amt; i++) {
			str += " ";
		}
		data += str;
		return data;
	}
}

package th.net.cat.epis.service.manual;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.security.core.context.SecurityContextHolder;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.DeductionManualDTO;
import th.net.cat.epis.dto.ManualEpisDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.entity.DeductionManualEntity;
import th.net.cat.epis.entity.ManualEntity;
import th.net.cat.epis.entity.PayInvoiceManualEntity;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.TrsMethodManualEntity;
import th.net.cat.epis.repo.DeductionManualRepository;
import th.net.cat.epis.repo.ManualRepository;
import th.net.cat.epis.repo.PayInvoiceManualRepository;

@org.springframework.stereotype.Service
public class ManualService {
	@Autowired
	ManualRepository manualRepo;
	@Autowired
	DeductionManualRepository deductionRepo;
	@Autowired
	PayInvoiceManualRepository payInvoiceManual;
	ManualEntity manualEntityReturn = new ManualEntity();
	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;

	public ManualEpisDTO insertDataManual(SettlePaymentDTO paymentDTO) {
		String userLogin = EpContextHolder.getContext().getUserName();
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		ManualEpisDTO manualDTO = new ManualEpisDTO();
		List<DeductionManualEntity> deductionManualList = new ArrayList<DeductionManualEntity>();
		List<ManualEntity> list = new ArrayList<ManualEntity>();
		List<TrsMethodManualEntity> trsMethodList = new ArrayList<TrsMethodManualEntity>();
		for (SettlePaymentDTO.Invoice inv : paymentDTO.getCustomers().get(0).getInvoices()) {
			ManualEntity manualEntity = new ManualEntity();
			ManualEntity manualEntityResult = new ManualEntity();
			DeductionManualEntity deductionManualEntity = new DeductionManualEntity();
			TrsMethodManualEntity trsMethodManualEntity = new TrsMethodManualEntity();
			PayInvoiceManualEntity invoiceManualEntity = new PayInvoiceManualEntity();

			List<ManualEntity> result = manualRepo.findByReceipt(paymentDTO.getCustomers().get(0).getEpNameCode());
			if (result.size() != 0) {
				if (result.get(0).getReceiptNoManual().equals(paymentDTO.getCustomers().get(0).getEpNameCode())) {
					return manualDTO;
				}
			}
			try {
				Date paid_date = new Date(paymentDTO.getCustomers().get(0).getPaymentDate().getTime());
				manualEntity.setInvoiceNo(inv.getNo());
				manualEntity.setReceiptNoManual(paymentDTO.getCustomers().get(0).getEpNameCode());
				manualEntity.setPaidDate(paid_date);
				manualEntity.setBranchArea(paymentDTO.getCustomers().get(0).getInputPayCashBA());
				manualEntity.setBranchCode(paymentDTO.getCustomers().get(0).getInputPayCashBP());
				manualEntity.setAccountNo(paymentDTO.getCustomers().get(0).getCustNo());
//				manualEntity.setPaidDate(paidDate);(inv.getTotalCharge());
				manualEntity.setPaidAmount(paymentDTO.getTotalPaid());
				manualEntity.setRemark(paymentDTO.getRemark());
				manualEntity.setSource(paymentDTO.getCustomers().get(0).getSouceType());
				manualEntity.setClearing("N");
				manualEntity.setClearingSap("N");
				manualEntity.setCreateBy(userLogin);
				manualEntity.setCreateDate(date);
				manualEntity.setUpdateBy(userLogin);
				manualEntity.setUpdateDate(date);
				manualEntity.setRecordStatus("A");

				// -------------------------------------------------------------------------
				manualEntityResult = manualRepo.save(manualEntity);

				if (manualEntityResult.getRecordStatus() != null) {
					invoiceManualEntity.setManual_id(manualEntityResult.getManualId());
					invoiceManualEntity.setSource(manualEntityResult.getSource());
					invoiceManualEntity.setInvoiceNo(manualEntity.getInvoiceNo());
					invoiceManualEntity.setAmount(manualEntity.getPaidAmount());
					invoiceManualEntity.setRemark(manualEntity.getRemark());
//					invoiceManualEntity.setPeriod(period);
					invoiceManualEntity.setCreateBy(userLogin);
					invoiceManualEntity.setCreateDate(time);
					invoiceManualEntity.setUpdateBy(userLogin);
					invoiceManualEntity.setUpdateDate(time);
					invoiceManualEntity.setRecordStatus("A");
					invoiceManualEntity.setServiceType("MANUAL");
					invoiceManualEntity.setPrintReceipt("Y");
					invoiceManualEntity.setPeriod(StringToPeriodFormat(inv.getBillCycle()));
					if("TBOSS".equals(manualEntityResult.getSource()) || "OTBOSS".equals(manualEntityResult.getSource())) {
						invoiceManualEntity.setClearing("N");
					}else {
						invoiceManualEntity.setClearing("Y");
					}
					
					payInvoiceManual.save(invoiceManualEntity);
					for (SettlePaymentDTO.DeductTax deduc : paymentDTO.getDeducts()) {
						deductionManualEntity.setDeductionNo(inv.getWithholdingTaxNo());
						deductionManualEntity.setDeductionType(deduc.getType());
						deductionManualEntity.setAmount(deduc.getAmount());
						deductionManualEntity.setPaymentDate(date);
						deductionManualEntity.setUpdateDttm(time);
						deductionManualEntity.setUpdateUser(userLogin);
						deductionManualEntity.setInvoiceNo(inv.getNo());
						deductionManualEntity.setRemark(paymentDTO.getRemark());
						deductionManualEntity.setCreateBy(userLogin);
						deductionManualEntity.setCreateDate(time);
						deductionManualEntity.setUpdateBy(userLogin);
						deductionManualEntity.setUpdateDate(time);
						deductionManualEntity.setRecordStatus("A");
						deductionManualEntity.setManualId(manualEntityResult.getManualId());

					}

				}
				DeductionManualEntity deductionManualResutl = deductionRepo.save(deductionManualEntity);
				deductionManualList.add(deductionManualResutl);

				list.add(manualEntity);

				if (manualEntityResult.getRecordStatus() != null) {
	
					trsMethodManualEntity.setCode("MU");
					trsMethodManualEntity.setName("Manual");
					trsMethodManualEntity.setChequeno("");
					trsMethodManualEntity.setAccountno(manualEntityResult.getAccountNo());
					trsMethodManualEntity.setAmount(manualEntityResult.getPaidAmount());
					trsMethodManualEntity.setUpdatedttm(time);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		manualDTO.setData(list);
		manualDTO.setDeductionManual(deductionManualList);
		return manualDTO;
	}

	public void updateStatusPayment(ManualEpisDTO episDTO, List<Receipt> receipts) {

		ManualEpisDTO entitydto = new ManualEpisDTO();
		DeductionManualDTO deductionManualdto = new DeductionManualDTO();
		deductionManualdto = selectDeductionManual(episDTO.getDeductionManual().get(0).getDeductionId());
		entitydto = selectByRecieptManual(episDTO.getData().get(0).getReceiptNoManual());

		for (int i = 0; i < entitydto.getData().size(); i++) {
			ManualEntity manualEntity = new ManualEntity();
			manualEntity = entitydto.getData().get(i);
			manualEntity.setClearing("C");
			manualEntity.setPaymenId(receipts.get(0).getPayment().getId());
			manualEntity.setManualId(episDTO.getData().get(0).getManualId());
			manualRepo.save(manualEntity);
		}
		if (deductionManualdto.getData().size() > 0) {
			for (int i = 0; i < deductionManualdto.getData().size(); i++) {

				DeductionManualEntity manualEntity = new DeductionManualEntity();
				manualEntity = deductionManualdto.getData().get(i);

				manualEntity.setPaymenId(receipts.get(0).getPayment().getId());
				manualEntity.setDeductionId(manualEntity.getDeductionId());
				deductionRepo.save(manualEntity);
			}

		}

	}

	public ManualEpisDTO selectByRecieptManual(String recieptNo) {

		List<ManualEntity> list = new ArrayList<ManualEntity>();
		ManualEpisDTO dto = new ManualEpisDTO();
		String sql = "select * from PAYMENT_MANUAL where RECEIPT_NO_MANUAL  = ?";
		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, recieptNo);
			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				ManualEntity entity = new ManualEntity();
				entity.setManualId(row.getLong("MANUAL_ID"));
				entity.setPaymenId(row.getLong("PAYMENT_ID"));
				entity.setInvoiceNo(row.getString("INVOICE_NO"));
				entity.setReceiptNoManual(row.getString("RECEIPT_NO_MANUAL"));
				entity.setPaidDate(row.getDate("PAID_DATE"));
				entity.setBranchArea(row.getString("BRANCH_AREA"));
				entity.setBranchCode(row.getString("BRANCH_CODE"));
				entity.setPaidAmount(row.getBigDecimal("PAID_AMOUNT"));
				entity.setSource(row.getString("SOURCE"));
				entity.setClearing(row.getString("CLEARING"));
				entity.setClearingSap(row.getString("CLEARING_SAP"));
				entity.setClearingSap(row.getString("CLEARING_SAP"));
				entity.setRemark(row.getString("REMARK"));
				entity.setCreateBy(row.getString("CREATE_BY"));
				entity.setCreateDate(row.getDate("CREATE_DATE"));
				entity.setUpdateBy(row.getString("UPDATE_BY"));
				entity.setUpdateDate(row.getDate("UPDATE_DATE"));
				entity.setRecordStatus(row.getString("RECORD_STATUS"));
				entity.setAccountNo(row.getString("ACCOUNT_NO"));
				list.add(entity);

			}

		});
		dto.setData(list);
		return dto;
	}

	public DeductionManualDTO selectDeductionManual(Long deducID) {
		List<DeductionManualEntity> list = new ArrayList<DeductionManualEntity>();
		DeductionManualDTO dto = new DeductionManualDTO();
		String sql = "select * from DEDUCTION_MANUAL where DEDUCTION_MANUAL_ID  = ?";
		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setLong(1, deducID);
			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				DeductionManualEntity entity = new DeductionManualEntity();
				entity.setDeductionId(row.getLong("DEDUCTION_MANUAL_ID"));
				entity.setPaymenId(row.getLong("PAYMENTID"));
				entity.setDeductionNo(row.getString("DEDUCTIONNO"));
				entity.setDeductionType(row.getString("DEDUCTIONTYPE"));
				entity.setAmount(row.getBigDecimal("AMOUNT"));
				entity.setPaymentDate(row.getDate("PAYMENTDATE"));
				entity.setUpdateDttm(row.getTimestamp("UPDATEDTTM"));
				entity.setUpdateSystem(row.getString("UPDATESYSTEM"));
				entity.setUpdateUser(row.getString("UPDATEUSER"));
				entity.setVerstionStamp(row.getLong("VERSIONSTAMP"));
				entity.setInvoiceNo(row.getString("INVOICE_NO"));
				entity.setRemark(row.getString("REMARK"));
				entity.setCreateBy(row.getString("CREATE_BY"));
				entity.setCreateDate(row.getDate("CREATE_DATE"));
				entity.setUpdateBy(row.getString("UPDATE_BY"));
				entity.setUpdateDate(row.getDate("UPDATE_DATE"));
				entity.setRecordStatus(row.getString("RECORD_STATUS"));
				entity.setManualId(row.getLong("MANUAL_ID"));

				list.add(entity);

			}
		});
		dto.setData(list);
		return dto;
	}
	public DeductionManualDTO selectDeductionManualID(Long manualId) {
		List<DeductionManualEntity> list = new ArrayList<DeductionManualEntity>();
		DeductionManualDTO dto = new DeductionManualDTO();
		String sql = "select * from DEDUCTION_MANUAL where MANUAL_ID  = ? and RECORD_STATUS = 'A' ";
		episJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setLong(1, manualId);
			}
		}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				DeductionManualEntity entity = new DeductionManualEntity();
				entity.setDeductionId(row.getLong("DEDUCTION_MANUAL_ID"));
				entity.setPaymenId(row.getLong("PAYMENTID"));
				entity.setDeductionNo(row.getString("DEDUCTIONNO"));
				entity.setDeductionType(row.getString("DEDUCTIONTYPE"));
				entity.setAmount(row.getBigDecimal("AMOUNT"));
				entity.setPaymentDate(row.getDate("PAYMENTDATE"));
				entity.setUpdateDttm(row.getTimestamp("UPDATEDTTM"));
				entity.setUpdateSystem(row.getString("UPDATESYSTEM"));
				entity.setUpdateUser(row.getString("UPDATEUSER"));
				entity.setVerstionStamp(row.getLong("VERSIONSTAMP"));
				entity.setInvoiceNo(row.getString("INVOICE_NO"));
				entity.setRemark(row.getString("REMARK"));
				entity.setCreateBy(row.getString("CREATE_BY"));
				entity.setCreateDate(row.getDate("CREATE_DATE"));
				entity.setUpdateBy(row.getString("UPDATE_BY"));
				entity.setUpdateDate(row.getDate("UPDATE_DATE"));
				entity.setRecordStatus(row.getString("RECORD_STATUS"));
				entity.setManualId(row.getLong("MANUAL_ID"));

				list.add(entity);

			}
		});
		dto.setData(list);
		return dto;
	}
	public void updateStatusManualOffline(Long manualId,Long paymentId){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName(),
				posNo = EpContextHolder.getContext().getPosNo();
		ManualEntity manualEntity = manualRepo.findByManualId(manualId);
		if(manualEntity != null) {
			manualEntity.setClearing("C");
			manualEntity.setPaymenId(paymentId);
			manualEntity.setUpdateBy(userName);
			manualEntity.setUpdateDate(new Date());
			manualRepo.save(manualEntity);
		}
	}
	public void delectManul(String reciept) {
		ManualEpisDTO entityDTO = new ManualEpisDTO();
		ManualEntity entity = new ManualEntity();
		entityDTO = 	selectByRecieptManual(reciept);
		
		for(int i = 0; i<entityDTO.getData().size() ; i++){
			entity = entityDTO.getData().get(i);
			entity.setRecordStatus("S");
			entity.setManualId(entity.getManualId());
			manualRepo.save(entity);
			
		}
		
		
	}
	

	//intput 16/03/2016 - 15/04/2016 to 2016031620160415
	public static String StringToPeriodFormat(String date) {
		int a = date.indexOf("-");
		StringBuilder result = new StringBuilder();
//		date.replace(" ", "");
		String[] parts = date.split("-");
		if(parts.length > 0) {
			for (String d : parts) {
				String[] c = d.split("/");
				if(c.length == 3) {
					result.append(c[2]).append(c[1]).append(c[0]);
				}				
			}
			
		}
		
		return result.toString().replaceAll(" ", "");
	}
}

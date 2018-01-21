package th.net.cat.epis.service;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.EndDayClosingDTO;
import th.net.cat.epis.entity.EmpClosing;
import th.net.cat.epis.entity.EndDayClosing;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.entity.ShopClosing;
import th.net.cat.epis.repo.EmpClosingRepository;
import th.net.cat.epis.repo.EndDayClosingRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.repo.ShopClosingRepository;
import th.net.cat.epis.util.AppConstants;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by nastanda on 5/11/2017 AD.
 */
@Service
public class EndDayClosingService {
    @Autowired
    EndDayClosingRepository endDayClosingRepository;
    @Autowired
    ShopClosingRepository shopClosingRepository;
    @Autowired
    EmpClosingRepository empClosingRepository;
    @Autowired
    ReceiptRepository receiptRepository;

    @Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;

    @Transactional
    public List<EmpClosing> createEndDayClosing(EndDayClosingDTO dto) throws Exception{
    	List<EmpClosing> empClosingList = new ArrayList<EmpClosing>();
    	SimpleDateFormat textFormat = new SimpleDateFormat("dd/MM/yyyy");
    	java.sql.Date closingDate = null;
        java.sql.Date transDate = null;
        if (StringUtils.isNotBlank(dto.getSearchDate())) {
            closingDate = new java.sql.Date(textFormat.parse(dto.getSearchDate()).getTime());
        }
        if (StringUtils.isNotBlank(dto.getSearchTransDate())) {
            transDate = new java.sql.Date(textFormat.parse(dto.getSearchTransDate()).getTime());
        }
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        EndDayClosing endDayClosing = new EndDayClosing();
        if(!StringUtils.equals(dto.getBackDateStatus(), AppConstants.ENDDAY_CLOSE_BACKDATE)){
            if (dto.getEmployees().size()>0){
                for (EndDayClosingDTO.Employee emp : dto.getEmployees()) {
                    if (emp.getClosingId() != null) {
                        endDayClosing = endDayClosingRepository.findById(emp.getClosingId());
                        endDayClosingList.add(endDayClosing);
                    } else {
                        endDayClosingList = endDayClosingRepository.findByUserNameAndClosingDate(dto.getUserName(), closingDate);
                        break;
                    }
                }
            } else {
                endDayClosingList = endDayClosingRepository.findByUserNameAndClosingDate(dto.getUserName(), closingDate);
            }
        }else{
            if (StringUtils.isNotBlank(dto.getSearchDate())) {
                endDayClosingList = endDayClosingRepository.findByUserNameAndReceiptDate(dto.getUserName(), closingDate);
            } else if (StringUtils.isNotBlank(dto.getSearchTransDate()) && StringUtils.isBlank(dto.getSearchDate())) {
                endDayClosingList = endDayClosingRepository.findByUserNameAndClosingDateAndBackDateStatusOrderByIdAsc(dto.getUserName(), transDate, AppConstants.ENDDAY_CLOSE_BACKDATE);
            }
        }
        EmpClosing rtEmpClosing = new EmpClosing();
        for(EndDayClosing dtl: endDayClosingList){
            if(!StringUtils.equals(dtl.getDocStatus(), AppConstants.ENDDAY_CLOSE_STATUS_S)){
                EmpClosing empClosing = new EmpClosing();
                empClosing.setBranchCode(dtl.getBranchCode()!=null?dtl.getBranchCode():dto.getBranchCode());
                empClosing.setClosingPos(dtl.getPosNo()!=null?dtl.getPosNo():dto.getPosNo());
                empClosing.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
                empClosing.setClosingDate(closingDate!=null?closingDate:transDate);
                empClosing.setCreateDttm(new java.sql.Date(new Date().getTime()));
                empClosing.setCreateUser(EpContextHolder.getContext().getUserCode());
                empClosing.setUserName(dtl.getUserName()!=null?dtl.getUserName():dto.getUserName());
                empClosing.setEmpCode(dtl.getEmpCode()!=null?dtl.getEmpCode():dto.getEmpCode());
                empClosing.setUpdateDttm(new java.sql.Date(new Date().getTime()));
                empClosing.setUpdateUser(EpContextHolder.getContext().getUserCode());
                if (StringUtils.equals(dto.getBackDateStatus(), AppConstants.ENDDAY_CLOSE_BACKDATE)){empClosing.setBackdateStatus(AppConstants.ENDDAY_CLOSE_BACKDATE);}
                rtEmpClosing = empClosingRepository.save(empClosing);

                dtl.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
                dtl.setUpdateDttm(new java.sql.Date(new Date().getTime()));
                dtl.setUpdateUser(EpContextHolder.getContext().getUserCode());
                dtl.setEmpClosing(rtEmpClosing);
                endDayClosingRepository.save(dtl);

                empClosingList.add(rtEmpClosing);
            }
        }
        /*
        EmpClosing empClosing = new EmpClosing();
        empClosing.setBranchCode(dto.getBranchCode());
        empClosing.setClosingPos(dto.getPosNo());
        empClosing.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
        empClosing.setClosingDate(closingDate);
        empClosing.setCreateDttm(new java.sql.Date(new Date().getTime()));
        empClosing.setCreateUser(dto.getEmpCode());
        empClosing.setEmpCode(dto.getEmpCode());
        empClosing.setUpdateDttm(new java.sql.Date(new Date().getTime()));
        empClosing.setUpdateUser(dto.getClosingEmpCode());
        //EndDayClosing dtl = endDayClosingRepository.findById(dto.getClosingId());
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        if(!StringUtils.equals(dto.getBackDateStatus(), AppConstants.ENDDAY_CLOSE_BACKDATE)){
            endDayClosingList = endDayClosingRepository.findByEmpCodeAndClosingDate(dto.getEmpCode(), closingDate);
        }else{
            empClosing.setBackdateStatus(AppConstants.ENDDAY_CLOSE_BACKDATE);
            endDayClosingList = endDayClosingRepository.findByEmpCodeAndReceiptDate(dto.getEmpCode(), closingDate);
        }
        EmpClosing rtEmpClosing = empClosingRepository.save(empClosing);
        for(EndDayClosing dtl: endDayClosingList){
            if(!StringUtils.equals(dtl.getDocStatus(), AppConstants.ENDDAY_CLOSE_STATUS_S)){
                dtl.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
                dtl.setUpdateDttm(new java.sql.Date(new Date().getTime()));
                dtl.setUpdateUser(dto.getClosingEmpCode());
                dtl.setEmpClosing(rtEmpClosing);
                endDayClosingRepository.save(dtl);
            }
        }
        */
        return empClosingList;
    }

    public List<EndDayClosing> findEndDayClosingPosGroup(String searchDate, String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CLOSING_DATE, BRANCH_CODE, MAC_NO, POS_NO, SHOP_CLOSING_ID shopClosingId1 ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        query.append(" , nvl(sum(TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount ");
        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount ");
        query.append(" , nvl(sum(TRANS_FOREIGN_TH_TOTAL_AMOUNT), 0) transForeignThTotalAmount ");
        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CREDIT), 0) totalCredit ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_OFFSET), 0) totalOffset ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? group by CLOSING_DATE, BRANCH_CODE, MAC_NO, POS_NO, SHOP_CLOSING_ID ");
        endDayClosingList = episJdbcTemplate.query(query.toString(),new Object[]{searchDate, branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroup(String searchDate, String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CLOSING_ID id, CLOSING_DATE, BRANCH_CODE, EMP_CODE, DOC_STATUS, SHOP_CLOSING_ID shopClosingId1 , USERNAME userName ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        query.append(" , nvl(sum(TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount ");
        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount ");
        query.append(" , nvl(sum(TRANS_FOREIGN_TH_TOTAL_AMOUNT), 0) transForeignThTotalAmount ");
        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CREDIT), 0) totalCredit ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_OFFSET), 0) totalOffset ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and BACKDATE_STATUS is null group by CLOSING_ID, CLOSING_DATE, BRANCH_CODE, EMP_CODE, DOC_STATUS, SHOP_CLOSING_ID, USERNAME ");
        query.append("order by regexp_substr(USERNAME, '^\\D*') nulls first, to_number(regexp_substr(USERNAME, '\\d+')), DOC_STATUS desc");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{searchDate, branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroupBackDate(String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT * FROM ");
        query.append(" ( SELECT CLOSING_ID id, RECEIPT_DATE, to_char(RECEIPT_DATE, 'dd/mm/yyyy') receiptDtStr,CLOSING_DATE closingDate, to_char(CLOSING_DATE, 'dd/mm/yyyy') closingDtStr, BRANCH_CODE, EMP_CODE, DOC_STATUS, SHOP_CLOSING_ID shopClosingId1 , USERNAME userName ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        query.append(" , nvl(sum(TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount ");
        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount ");
        query.append(" , nvl(sum(TRANS_FOREIGN_TH_TOTAL_AMOUNT), 0) transForeignThTotalAmount ");
        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CREDIT), 0) totalCredit ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_OFFSET), 0) totalOffset ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" FROM DAY_END_CLOSING WHERE BRANCH_CODE = ? AND BACKDATE_STATUS = 'Y' AND SHOP_CLOSING_ID IS NULL GROUP BY CLOSING_ID, RECEIPT_DATE, BRANCH_CODE, EMP_CODE, DOC_STATUS, CLOSING_DATE, SHOP_CLOSING_ID, USERNAME ");
        query.append(" ) aa WHERE aa.totalWt3tred > 0 OR aa.totalWt69Bis > 0 OR aa.totalWt69Tre > 0 OR aa.transGfmisTotalAmount > 0 OR aa.transForeignThTotalAmount > 0 ");
        query.append(" order by RECEIPT_DATE asc, regexp_substr(USERNAME, '^\\D*') nulls first, to_number(regexp_substr(USERNAME, '\\d+')) ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findShopIdBackDate(String closingDt, String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
//        query.append(" SELECT SHOP_CLOSING_ID shopClosingId1, CLOSING_ID id, RECEIPT_DATE, to_char(RECEIPT_DATE, 'dd/mm/yyyy') receiptDtStr,CLOSING_DATE closingDate, to_char(CLOSING_DATE, 'dd/mm/yyyy') closingDtStr, BRANCH_CODE, EMP_CODE, DOC_STATUS ");
//        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and BACKDATE_STATUS = 'Y' and SHOP_CLOSING_ID is not null group by SHOP_CLOSING_ID, CLOSING_ID, RECEIPT_DATE, BRANCH_CODE, EMP_CODE, DOC_STATUS, CLOSING_DATE ");

        query.append(" SELECT dc.SHOP_CLOSING_ID shopClosingId1 ");
        query.append(" from DAY_END_CLOSING dc inner JOIN SHOP_CLOSING sc on sc.SHOP_CLOSING_ID = dc.SHOP_CLOSING_ID ");
        query.append(" where trunc(sc.CLOSING_DATE) = trunc(to_date(?,'dd/mm/yyyy')) and dc.BRANCH_CODE = ? and dc.BACKDATE_STATUS = 'Y' and dc.SHOP_CLOSING_ID is not null ");
        query.append(" group by dc.SHOP_CLOSING_ID ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{closingDt, branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }

    public List<EndDayClosing> findShopClosingDate(String closingDt, String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT to_char(sc.CLOSING_DATE, 'dd/mm/yyyy') shopClosingDtStr, dc.RECEIPT_DATE, to_char(dc.RECEIPT_DATE, 'dd/mm/yyyy') receiptDtStr,dc.CLOSING_DATE closingDate, to_char(dc.CLOSING_DATE, 'dd/mm/yyyy') closingDtStr, dc.BRANCH_CODE, dc.EMP_CODE, dc.DOC_STATUS, dc.SHOP_CLOSING_ID shopClosingId1, dc.USERNAME userName ");
        query.append(" , sum(dc.TOTAL_EXC_AMOUNT) totalExcAmount, sum(dc.TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(dc.TOTAL_CHAEGE) totalCharge, sum(dc.TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        query.append(" , nvl(sum(dc.TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount ");
        query.append(" , nvl(sum(dc.TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount ");
        query.append(" , nvl(sum(dc.TRANS_FOREIGN_TH_TOTAL_AMOUNT), 0) transForeignThTotalAmount ");
        query.append(" , nvl(sum(dc.TOTAL_CASH), 0) totalCash, nvl(sum(dc.TOTAL_CHEQUE), 0) totalCheque, nvl(sum(dc.TOTAL_CREDIT), 0) totalCredit ");
        query.append(" , nvl(sum(dc.TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(dc.TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(dc.TOTAL_BILL_EXCHANGE), 0) totalBillExchange ");
        query.append(" , nvl(sum(dc.TOTAL_COUPON), 0)  totalCoupon, nvl(sum(dc.TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(dc.TOTAL_OFFSET), 0) totalOffset ");
        query.append(" , nvl(sum(dc.TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(dc.TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(dc.TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING dc ");
        query.append(" inner JOIN SHOP_CLOSING sc ");
        query.append(" on sc.SHOP_CLOSING_ID = dc.SHOP_CLOSING_ID ");
        query.append(" where trunc(sc.CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and dc.BRANCH_CODE = ? and dc.BACKDATE_STATUS = 'Y' and dc.SHOP_CLOSING_ID is not null ");
        query.append(" group by sc.CLOSING_DATE , dc.RECEIPT_DATE, dc.BRANCH_CODE, dc.EMP_CODE, dc.DOC_STATUS, dc.CLOSING_DATE, dc.SHOP_CLOSING_ID, dc.USERNAME ");
        query.append(" order by RECEIPT_DATE asc, regexp_substr(USERNAME, '^\\D*') nulls first, to_number(regexp_substr(USERNAME, '\\d+')) ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{closingDt, branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroup(String searchDate, String branchCode, String empCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CLOSING_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, EMP_NAME, DOC_STATUS docStatus, EMP_CLOSING_ID empClosingId1 , USERNAME userName ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        //28-05-2017
        query.append(" , nvl(sum(EXC_CANCEL_TOTAL_AMOUNT), 0) excCancelTotalAmount, nvl(sum(EXC_CANCEL_TOTAL_COUNT), 0) excCancelTotalCount ");
        query.append(" , nvl(sum(RECEIVE_WH_FULL_AMOUNT), 0) receiveWhFullAmount, nvl(sum(RECEIVE_WH_FULL_COUNT), 0) receiveWhFullCount ");
        query.append(" , nvl(sum(RECEIVE_WH_ABVR_AMOUNT), 0) receiveWhAbvrAmount, nvl(sum(RECEIVE_WH_ABVR_COUNT), 0) receiveWhAbvrCount ");
        query.append(" , nvl(sum(RECEIVE_ONLY_AMOUNT), 0) receiveOnlyAmount, nvl(sum(RECEIVE_ONLY_COUNT), 0) receiveOnlyCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_WH_FULL_AMOUNT), 0) cancelReceiveWhFullAmount, nvl(sum(CANCEL_RECEIVE_WH_FULL_COUNT), 0) cancelReceiveWhFullCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_WH_ABVR_AMOUNT), 0) cancelReceiveWhAbvrAmount, nvl(sum(CANCEL_RECEIVE_WH_ABVR_COUNT), 0) cancelReceiveWhAbvrCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_ONLY_AMOUNT), 0) cancelReceiveOnlyAmount, nvl(sum(CANCEL_RECEIVE_ONLY_COUNT), 0) cancelReceiveOnlyCount ");
        query.append(" , nvl(sum(TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(TRANS_BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount ");

        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount, nvl(sum(TRANSFER_GFMIS_TOTAL_COUNT), 0) transGfmisTotalCount ");
        query.append(" , nvl(sum(FOREIGN_US_TOTAL_AMOUNT), 0) foreignUsTotalAmount, nvl(sum(FOREIGN_US_TO_TH_TOTAL_AMOUNT), 0) foreignUsToThTotalAmount, nvl(sum(FOREIGN_US_TOTAL_COUNT), 0) foreignUsTotalCount ");
        query.append(" , nvl(sum(FOREIGN_EU_TOTAL_AMOUNT), 0) foreignEuTotalAmount, nvl(sum(FOREIGN_EU_TO_TH_TOTAL_AMOUNT), 0) foreignEuToThTotalAmount, nvl(sum(FOREIGN_EU_TOTAL_COUNT), 0) foreignEuTotalCount ");

        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(TOTAL_CREDIT), 0) totalCredit, nvl(sum(TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(TOTAL_OFFSET), 0) totalOffset, nvl(sum(TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and EMP_CODE = ? and BACKDATE_STATUS is null group by CLOSING_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, DOC_STATUS ");
        query.append(" , EMP_NAME, EMP_CLOSING_ID,USERNAME ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{searchDate, branchCode, empCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));

        endDayClosingList = compareEmpCloseList(endDayClosingList);

        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroupBackDate(String searchDate, String branchCode, String empCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT RECEIPT_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, EMP_NAME, DOC_STATUS docStatus, EMP_CLOSING_ID empClosingId1 ,USERNAME userName");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        //28-05-2017
        query.append(" , nvl(sum(EXC_CANCEL_TOTAL_AMOUNT), 0) excCancelTotalAmount, nvl(sum(EXC_CANCEL_TOTAL_COUNT), 0) excCancelTotalCount ");
        query.append(" , nvl(sum(RECEIVE_WH_FULL_AMOUNT), 0) receiveWhFullAmount, nvl(sum(RECEIVE_WH_FULL_COUNT), 0) receiveWhFullCount ");
        query.append(" , nvl(sum(RECEIVE_WH_ABVR_AMOUNT), 0) receiveWhAbvrAmount, nvl(sum(RECEIVE_WH_ABVR_COUNT), 0) receiveWhAbvrCount ");
        query.append(" , nvl(sum(RECEIVE_ONLY_AMOUNT), 0) receiveOnlyAmount, nvl(sum(RECEIVE_ONLY_COUNT), 0) receiveOnlyCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_WH_FULL_AMOUNT), 0) cancelReceiveWhFullAmount, nvl(sum(CANCEL_RECEIVE_WH_FULL_COUNT), 0) cancelReceiveWhFullCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_WH_ABVR_AMOUNT), 0) cancelReceiveWhAbvrAmount, nvl(sum(CANCEL_RECEIVE_WH_ABVR_COUNT), 0) cancelReceiveWhAbvrCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_ONLY_AMOUNT), 0) cancelReceiveOnlyAmount, nvl(sum(CANCEL_RECEIVE_ONLY_COUNT), 0) cancelReceiveOnlyCount ");
        query.append(" , nvl(sum(TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(TRANS_BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount ");

        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount, nvl(sum(TRANSFER_GFMIS_TOTAL_COUNT), 0) transGfmisTotalCount ");
        query.append(" , nvl(sum(FOREIGN_US_TOTAL_AMOUNT), 0) foreignUsTotalAmount, nvl(sum(FOREIGN_US_TO_TH_TOTAL_AMOUNT), 0) foreignUsToThTotalAmount, nvl(sum(FOREIGN_US_TOTAL_COUNT), 0) foreignUsTotalCount ");
        query.append(" , nvl(sum(FOREIGN_EU_TOTAL_AMOUNT), 0) foreignEuTotalAmount, nvl(sum(FOREIGN_EU_TO_TH_TOTAL_AMOUNT), 0) foreignEuToThTotalAmount, nvl(sum(FOREIGN_EU_TOTAL_COUNT), 0) foreignEuTotalCount ");

        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(TOTAL_CREDIT), 0) totalCredit, nvl(sum(TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(TOTAL_OFFSET), 0) totalOffset, nvl(sum(TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING where trunc(RECEIPT_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and EMP_CODE = ? group by RECEIPT_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, DOC_STATUS ");
        query.append(" , EMP_NAME, EMP_CLOSING_ID , USERNAME ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{searchDate, branchCode, empCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        /*if(!CollectionUtils.isEmpty(endDayClosingList) && endDayClosingList.size()>1){
            for(EndDayClosing dtl: endDayClosingList){
                if(dtl.getDocStatus().equals(AppConstants.ENDDAY_CLOSE_STATUS_S)){
                    endDayClosingList.remove(dtl);
                }
            }
        }*/
        endDayClosingList = compareEmpCloseList(endDayClosingList);

        return endDayClosingList;
    }


    public List<EndDayClosing> findEndDayClosingEmpGroupBackDateByTransDate(String searchTransDate, String branchCode, String empCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();

        query.append(" SELECT BRANCH_CODE, SHOP_NAME, EMP_CODE, EMP_NAME, DOC_STATUS  docStatus,USERNAME userName ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        //28-05-2017
        query.append(" , nvl(sum(EXC_CANCEL_TOTAL_AMOUNT), 0) excCancelTotalAmount, nvl(sum(EXC_CANCEL_TOTAL_COUNT), 0) excCancelTotalCount ");
        query.append(" , nvl(sum(RECEIVE_WH_FULL_AMOUNT), 0) receiveWhFullAmount, nvl(sum(RECEIVE_WH_FULL_COUNT), 0) receiveWhFullCount ");
        query.append(" , nvl(sum(RECEIVE_WH_ABVR_AMOUNT), 0) receiveWhAbvrAmount, nvl(sum(RECEIVE_WH_ABVR_COUNT), 0) receiveWhAbvrCount ");
        query.append(" , nvl(sum(RECEIVE_ONLY_AMOUNT), 0) receiveOnlyAmount, nvl(sum(RECEIVE_ONLY_COUNT), 0) receiveOnlyCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_WH_FULL_AMOUNT), 0) cancelReceiveWhFullAmount, nvl(sum(CANCEL_RECEIVE_WH_FULL_COUNT), 0) cancelReceiveWhFullCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_WH_ABVR_AMOUNT), 0) cancelReceiveWhAbvrAmount, nvl(sum(CANCEL_RECEIVE_WH_ABVR_COUNT), 0) cancelReceiveWhAbvrCount ");
        query.append(" , nvl(sum(CANCEL_RECEIVE_ONLY_AMOUNT), 0) cancelReceiveOnlyAmount, nvl(sum(CANCEL_RECEIVE_ONLY_COUNT), 0) cancelReceiveOnlyCount ");
        query.append(" , nvl(sum(TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(TRANS_BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount ");

        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount, nvl(sum(TRANSFER_GFMIS_TOTAL_COUNT), 0) transGfmisTotalCount ");
        query.append(" , nvl(sum(FOREIGN_US_TOTAL_AMOUNT), 0) foreignUsTotalAmount, nvl(sum(FOREIGN_US_TO_TH_TOTAL_AMOUNT), 0) foreignUsToThTotalAmount, nvl(sum(FOREIGN_US_TOTAL_COUNT), 0) foreignUsTotalCount ");
        query.append(" , nvl(sum(FOREIGN_EU_TOTAL_AMOUNT), 0) foreignEuTotalAmount, nvl(sum(FOREIGN_EU_TO_TH_TOTAL_AMOUNT), 0) foreignEuToThTotalAmount, nvl(sum(FOREIGN_EU_TOTAL_COUNT), 0) foreignEuTotalCount ");

        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(TOTAL_CREDIT), 0) totalCredit, nvl(sum(TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(TOTAL_OFFSET), 0) totalOffset, nvl(sum(TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and EMP_CODE = ? ");/*and BACKDATE_STATUS = '"+AppConstants.ENDDAY_CLOSE_BACKDATE+"'*/
        query.append(" group by BRANCH_CODE, SHOP_NAME, EMP_CODE, DOC_STATUS, EMP_NAME, USERNAME ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{searchTransDate, branchCode, empCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));

        endDayClosingList = compareEmpCloseList(endDayClosingList);

        return endDayClosingList;
    }

    public List<EndDayClosing> findEndDayClosingEmpGroup(Long id){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT a.CLOSING_DATE closingDate, a.RECEIPT_DATE receiptDate, a.BRANCH_CODE, a.SHOP_NAME, a.EMP_CODE, a.EMP_NAME, a.DOC_STATUS, a.EMP_CLOSING_ID empClosingId1, b.CREATEDTTM createDttm, b.BACKDATE_STATUS backDateStatus, a.USERNAME userName ");
        query.append(" , sum(a.TOTAL_EXC_AMOUNT) totalExcAmount, sum(a.TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(a.TOTAL_CHAEGE) totalCharge, sum(a.TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        //28-05-2017
        query.append(" , nvl(sum(a.EXC_CANCEL_TOTAL_AMOUNT), 0) excCancelTotalAmount, nvl(sum(a.EXC_CANCEL_TOTAL_COUNT), 0) excCancelTotalCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_FULL_AMOUNT), 0) receiveWhFullAmount, nvl(sum(a.RECEIVE_WH_FULL_COUNT), 0) receiveWhFullCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_ABVR_AMOUNT), 0) receiveWhAbvrAmount, nvl(sum(a.RECEIVE_WH_ABVR_COUNT), 0) receiveWhAbvrCount ");
        query.append(" , nvl(sum(a.WH_ONLY_FULL_AMOUNT), 0) whOnlyFullAmount, nvl(sum(a.WH_ONLY_FULL_COUNT), 0) whOnlyFullCount  ");
        query.append(" , nvl(sum(a.RECEIVE_ONLY_AMOUNT), 0) receiveOnlyAmount, nvl(sum(a.RECEIVE_ONLY_COUNT), 0) receiveOnlyCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_FULL_AMOUNT), 0) cancelReceiveWhFullAmount, nvl(sum(a.CANCEL_RECEIVE_WH_FULL_COUNT), 0) cancelReceiveWhFullCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_AMOUNT), 0) cancelReceiveWhAbvrAmount, nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_COUNT), 0) cancelReceiveWhAbvrCount ");
        query.append(" , nvl(sum(a.CANCEL_WH_ONLY_FULL_AMOUNT), 0) cancelWhOnlyFullAmount, nvl(sum(a.CANCEL_WH_ONLY_FULL_COUNT), 0) cancelWhOnlyFullCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_ONLY_AMOUNT), 0) cancelReceiveOnlyAmount, nvl(sum(a.CANCEL_RECEIVE_ONLY_COUNT), 0) cancelReceiveOnlyCount ");
        query.append(" , nvl(sum(a.TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(a.TRANS_BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount, nvl(sum(a.TOTAL_DISCOUNT), 0) totalDiscount ");
        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount , nvl(sum(TRANSFER_GFMIS_TOTAL_COUNT), 0) transGfmisTotalCount ");
        query.append(" , nvl(sum(TRANS_FOREIGN_TH_TOTAL_AMOUNT), 0) transForeignThTotalAmount , nvl(sum(TRANS_FOREIGN_TOTAL_COUNT), 0) transForeignTotalCount ");
        query.append(" , nvl(sum(a.TOTAL_CANCEL_AMT), 0) totalCancelAmt, nvl(sum(a.TOTAL_CANCEL_CNT), 0) totalCancelCnt ");

        query.append(" , nvl(sum(a.TOTAL_CASH), 0) totalCash, nvl(sum(a.TOTAL_CASH_COUNT), 0) totalCashCount, nvl(sum(a.TOTAL_CHEQUE), 0) totalCheque, nvl(sum(a.TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(a.TOTAL_CREDIT), 0) totalCredit, nvl(sum(a.TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(a.TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(a.TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(a.TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(a.TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(a.TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(a.TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(a.TOTAL_COUPON), 0)  totalCoupon, nvl(sum(a.TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(a.TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(a.TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(a.TOTAL_OFFSET), 0) totalOffset, nvl(sum(a.TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(a.TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(a.TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(a.TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING a inner join EMP_CLOSING b on a.EMP_CLOSING_ID = b.EMP_CLOSING_ID where a.EMP_CLOSING_ID = ? group by a.CLOSING_DATE, a.RECEIPT_DATE, a.BRANCH_CODE, a.SHOP_NAME, a.EMP_CODE, a.DOC_STATUS ");
        query.append(" , a.EMP_NAME, a.EMP_CLOSING_ID, b.CREATEDTTM, b.BACKDATE_STATUS, a.USERNAME");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{id}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingShopGroup(Long id, String shopClosingIdStr){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT a.BRANCH_CODE, a.SHOP_NAME, b.CLOSING_EMP_CODE empCode, b.CLOSING_EMP_NAME empName, a.SHOP_CLOSING_ID shopClosingId1, b.CREATEDTTM createDttm, b.BACKDATE_STATUS backDateStatus ");//a.CLOSING_DATE,
        query.append(" , sum(a.TOTAL_EXC_AMOUNT) totalExcAmount, sum(a.TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(a.TOTAL_CHAEGE) totalCharge, sum(a.TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        //28-05-2017
        query.append(" , nvl(sum(a.EXC_CANCEL_TOTAL_AMOUNT), 0) excCancelTotalAmount, nvl(sum(a.EXC_CANCEL_TOTAL_COUNT), 0) excCancelTotalCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_FULL_AMOUNT), 0) receiveWhFullAmount, nvl(sum(a.RECEIVE_WH_FULL_COUNT), 0) receiveWhFullCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_ABVR_AMOUNT), 0) receiveWhAbvrAmount, nvl(sum(a.RECEIVE_WH_ABVR_COUNT), 0) receiveWhAbvrCount ");
        query.append(" , nvl(sum(a.WH_ONLY_FULL_AMOUNT), 0) whOnlyFullAmount, nvl(sum(a.WH_ONLY_FULL_COUNT), 0) whOnlyFullCount  ");
        query.append(" , nvl(sum(a.RECEIVE_ONLY_AMOUNT), 0) receiveOnlyAmount, nvl(sum(a.RECEIVE_ONLY_COUNT), 0) receiveOnlyCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_FULL_AMOUNT), 0) cancelReceiveWhFullAmount, nvl(sum(a.CANCEL_RECEIVE_WH_FULL_COUNT), 0) cancelReceiveWhFullCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_AMOUNT), 0) cancelReceiveWhAbvrAmount, nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_COUNT), 0) cancelReceiveWhAbvrCount ");
        query.append(" , nvl(sum(a.CANCEL_WH_ONLY_FULL_AMOUNT), 0) cancelWhOnlyFullAmount, nvl(sum(a.CANCEL_WH_ONLY_FULL_COUNT), 0) cancelWhOnlyFullCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_ONLY_AMOUNT), 0) cancelReceiveOnlyAmount, nvl(sum(a.CANCEL_RECEIVE_ONLY_COUNT), 0) cancelReceiveOnlyCount ");
        query.append(" , nvl(sum(a.TRANS_BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(a.TRANS_BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount, nvl(sum(a.TOTAL_DISCOUNT), 0) totalDiscount ");
        query.append(" , nvl(sum(TRANSFER_GFMIS_TOTAL_AMOUNT), 0) transGfmisTotalAmount , nvl(sum(TRANSFER_GFMIS_TOTAL_COUNT), 0) transGfmisTotalCount ");
        query.append(" , nvl(sum(TRANS_FOREIGN_TH_TOTAL_AMOUNT), 0) transForeignThTotalAmount , nvl(sum(TRANS_FOREIGN_TOTAL_COUNT), 0) transForeignTotalCount ");
        query.append(" , nvl(sum(a.TOTAL_CANCEL_AMT), 0) totalCancelAmt, nvl(sum(a.TOTAL_CANCEL_CNT), 0) totalCancelCnt ");

        query.append(" , nvl(sum(a.TOTAL_CASH), 0) totalCash, nvl(sum(a.TOTAL_CASH_COUNT), 0) totalCashCount, nvl(sum(a.TOTAL_CHEQUE), 0) totalCheque, nvl(sum(a.TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(a.TOTAL_CREDIT), 0) totalCredit, nvl(sum(a.TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(a.TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(a.TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(a.TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(a.TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(a.TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(a.TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(a.TOTAL_COUPON), 0)  totalCoupon, nvl(sum(a.TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(a.TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(a.TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(a.TOTAL_OFFSET), 0) totalOffset, nvl(sum(a.TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(a.TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(a.TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(a.TOTAL_WT_69TRE), 0) totalWt69Tre ");

        query.append(" , nvl(sum(AGENT_TOTAL_AMOUNT), 0) agentTotalAmount, nvl(sum(AGENT_RECEIVE_FULL_AGENT_COUNT), 0) agentReceiveFullAgentCount, nvl(sum(AGENT_RECEIVE_ABVR_COUNT), 0) agentReceiveAbvrCount ");

        query.append(" from DAY_END_CLOSING a inner join SHOP_CLOSING b on a.SHOP_CLOSING_ID = b.SHOP_CLOSING_ID where 1=1");
        if (id!=null) {
            query.append(" and a.SHOP_CLOSING_ID = "+id+" ");
        } else if (shopClosingIdStr!=null) {
            query.append(" and a.SHOP_CLOSING_ID = "+shopClosingIdStr+" ");
        }
        query.append(" group by a.BRANCH_CODE, a.SHOP_NAME, b.CLOSING_EMP_CODE, b.CLOSING_EMP_NAME, a.SHOP_CLOSING_ID ");//a.CLOSING_DATE,
        query.append(" , b.CREATEDTTM, b.BACKDATE_STATUS ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public ShopClosing createShopClosing(ShopClosing dtl){
        ShopClosing shopClosing = new ShopClosing();
        shopClosing = shopClosingRepository.findByBranchCodeAndClosingDateAndBackdateStatus(dtl.getBranchCode(), dtl.getClosingDate(), dtl.getBackdateStatus());
        if(shopClosing!=null && shopClosing.getId()!=null){
            dtl.setId(shopClosing.getId());
        }
        shopClosing = shopClosingRepository.save(dtl);
        //List<EndDayClosing> endDayClosingList = endDayClosingRepository.findByBranchCodeAndClosingDate(dtl.getBranchCode(), new Date(dtl.getClosingDate().getTime()));
        //List<EndDayClosing> endDayClosingList = endDayClosingRepository.findByEmpCodeAndClosingDate("", dtl.getClosingDate());
        //EndDayClosing endDayClosing = endDayClosingRepository.findById(new Long(47));
//        List<EndDayClosing> endDayClosingList = endDayClosingRepository.findByBranchCodeAndClosingDateAndBackDateStatus(dtl.getBranchCode(), new Date(dtl.getClosingDate().getTime()), dtl.getBackdateStatus());
        List<EndDayClosing> endDayClosingList = endDayClosingRepository.findByBranchCodeAndClosingDateAndBackDateStatusAndShopClosing_IdIsNull(dtl.getBranchCode(), new Date(dtl.getClosingDate().getTime()), dtl.getBackdateStatus());
        for(EndDayClosing dtl0: endDayClosingList){
            dtl0.setShopClosing(shopClosing);
            endDayClosingRepository.save(dtl0);
        }
        return shopClosing;
    }
    public ShopClosing createShopClosingBackDate(ShopClosing dtl,EndDayClosingDTO closingDTO){
        ShopClosing shopClosing = new ShopClosing();
        shopClosing = shopClosingRepository.findByBranchCodeAndClosingDateAndBackdateStatus(dtl.getBranchCode(), dtl.getClosingDate(), dtl.getBackdateStatus());
        if(shopClosing!=null && shopClosing.getId()!=null){
            dtl.setId(shopClosing.getId());
        }
        shopClosing = shopClosingRepository.save(dtl);
//      List<EndDayClosing> endDayClosingList = endDayClosingRepository.findByBranchCodeAndDocStatusAndBackDateStatusAndShopClosing_IdIsNull(dtl.getBranchCode(), dtl.getDocStatus(), dtl.getBackdateStatus());
        /*for(EndDayClosing dtl0: endDayClosingList){
            dtl0.setShopClosing(shopClosing);
            endDayClosingRepository.save(dtl0);
        }*/
        for(EndDayClosingDTO.Employee endDayClosing : closingDTO.getEmployees()){
            if (endDayClosing.getChecked().equals(true)) {
                EndDayClosing result = endDayClosingRepository.findById(endDayClosing.getClosingId());
                result.setShopClosing(shopClosing);
                endDayClosingRepository.save(result);
            }
        }
        return shopClosing;
    }
    public EmpClosing findEmpClosingById(Long id){
        EmpClosing rtEmpClosing = empClosingRepository.findById(id);
        return rtEmpClosing;
    }
    public ShopClosing findShopClosingById(Long id){
        ShopClosing shopClosing = shopClosingRepository.findById(id);
        return  shopClosing;
    }

    public List<EndDayClosing> findEndDayClosingEmp(String searchDate, String searchTransDate, String branchCode, String empCode, String backdateStatus){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CLOSING_DATE closingDate, BRANCH_CODE branchCode, SHOP_NAME shopName, EMP_CODE empCode, EMP_NAME empName, DOC_STATUS docStatus, EMP_CLOSING_ID empClosingId1");
        query.append(" from DAY_END_CLOSING ");
        query.append(" WHERE 1=1 ");
        if (!StringUtils.equals(backdateStatus, AppConstants.ENDDAY_CLOSE_BACKDATE)) {
            query.append(" and trunc(CLOSING_DATE) = TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY') ");
            query.append(" and BACKDATE_STATUS is null ");
        } else {
            if (StringUtils.isNotBlank(searchDate)) {
                query.append(" and trunc(RECEIPT_DATE) = TO_TIMESTAMP('"+searchDate+"', 'DD-MM-YYYY') ");
                query.append(" and BACKDATE_STATUS = 'Y' ");
            } else if (StringUtils.isNotBlank(searchTransDate) && StringUtils.isBlank(searchDate)) {
                query.append(" and trunc(CLOSING_DATE) = TO_TIMESTAMP('"+searchTransDate+"', 'DD-MM-YYYY') ");
                query.append(" and BACKDATE_STATUS = 'Y' ");
            }

        }
        if (StringUtils.isNotBlank(branchCode)) {
            query.append(" and BRANCH_CODE = '" + branchCode + "' ");
        }
        if (StringUtils.isNotBlank(empCode)) {
            query.append(" and EMP_CODE = '"+empCode+"' ");
        }

        query.append(" and EMP_CLOSING_ID is not null ");
        query.append(" order by RECEIPT_DATE ASC ");
        //query.append(" group by CLOSING_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, DOC_STATUS, EMP_NAME, EMP_CLOSING_ID ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), BeanPropertyRowMapper.newInstance(EndDayClosing.class));

        return endDayClosingList;
    }

    public List<EndDayClosing> compareEmpCloseList(List<EndDayClosing> endDayClosingList){

        List<EndDayClosing> openEndDayClosingList = new ArrayList<EndDayClosing>();
        List<EndDayClosing> closeEndDayClosingList = new ArrayList<EndDayClosing>();

        if(!CollectionUtils.isEmpty(endDayClosingList) && endDayClosingList.size()>1){

            for (EndDayClosing dtl : endDayClosingList) {

                if (dtl.getDocStatus().equalsIgnoreCase(AppConstants.ENDDAY_CLOSE_STATUS_W)) {
                    EndDayClosing openEndDayClosing = new EndDayClosing();
                    BeanUtils.copyProperties(dtl, openEndDayClosing);
                    openEndDayClosingList.add(openEndDayClosing);
                }/* else if (dtl.getDocStatus().equalsIgnoreCase(AppConstants.ENDDAY_CLOSE_STATUS_S)) {
                    EndDayClosing closeEndDayClosing = new EndDayClosing();
                    BeanUtils.copyProperties(dtl, closeEndDayClosing);
                    closeEndDayClosingList.add(closeEndDayClosing);
                }*/
            }

            if (!CollectionUtils.isEmpty(openEndDayClosingList) && openEndDayClosingList.size()>0) {
                endDayClosingList = openEndDayClosingList;
            }/* else {
                endDayClosingList = closeEndDayClosingList;
            }*/

        }
        return endDayClosingList;

    }

    public Boolean updateEmpClosingId(List<EmpClosing> empClosingList){
        //update emp_closing_id
        Boolean updateFlg = false;
        for (EmpClosing emp:empClosingList) {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" select cr.RECEIPTID id, cr.RECEIPTNO no, cr.BRANCHAREA, cr.UPDATEUSER, cr.ATTRIBUTES, trunc(cr.UPDATEDTTM), trunc(cr.RECEIPTDTTM) from CORRECEIPT cr ");
            queryBuilder.append(" inner join DAY_END_CLOSING dc ");
            queryBuilder.append(" on cr.CLOSING_ID = dc.CLOSING_ID ");
            queryBuilder.append(" where dc.EMP_CLOSING_ID = " + emp.getId() + " and cr.BRANCHAREA = " + emp.getBranchCode() + " and cr.UPDATEUSER = '" + emp.getUserName() + "' ");
            queryBuilder.append(" GROUP BY cr.RECEIPTID, cr.RECEIPTNO, cr.BRANCHAREA, cr.UPDATEUSER, cr.ATTRIBUTES, trunc(cr.UPDATEDTTM), trunc(cr.RECEIPTDTTM) ");

            List<Receipt> receiptsList = episJdbcTemplate.query(queryBuilder.toString(), BeanPropertyRowMapper.newInstance(Receipt.class));

            if (receiptsList != null && receiptsList.size() > 0) {
                updateFlg = true;

                Map<Long, Receipt> receiptMap = newHashMap();
                for (Receipt rec : receiptsList) {

                /*episJdbcTemplate.update("UPDATE CORRECEIPT "
                        + "SET EMP_CLOSING_ID = ? "
                        + "WHERE receiptid = ?", emp.getId(), rec.getId() );*/
                    receiptMap.put(rec.getId(), rec);
                    Iterable<Receipt> recResults = receiptRepository.findAll(receiptMap.keySet());
                    if (recResults != null) {
                        for (Receipt recResult : recResults) {
                            recResult.setEmpClosingId(emp.getId());
                            receiptRepository.save(recResult);
                        }
                    }

                }
            }
        }
        return updateFlg;
    }

    public Boolean updateShopClosingId(EndDayClosingDTO closingDTO){
        closingDTO.setEmpCode(EpContextHolder.getContext().getUserCode()!=null?StringUtils.isNotBlank(EpContextHolder.getContext().getUserCode())?EpContextHolder.getContext().getUserCode():null:null);
        ShopClosing shopClosing = new ShopClosing();
        //update shop_closing_id
        StringBuilder queryBuilder = new StringBuilder();
        Boolean updateFlg = false;

        queryBuilder.append(" select cr.RECEIPTID id, cr.RECEIPTNO no, cr.BRANCHAREA, cr.UPDATEUSER, cr.ATTRIBUTES, trunc(cr.UPDATEDTTM), trunc(cr.RECEIPTDTTM) from CORRECEIPT cr ");
        queryBuilder.append(" inner join DAY_END_CLOSING dc ");
        queryBuilder.append(" on cr.CLOSING_ID = dc.CLOSING_ID ");
        queryBuilder.append(" where dc.SHOP_CLOSING_ID = "+closingDTO.getShopClosingId()+" and cr.BRANCHAREA = "+closingDTO.getBranchCode()+" and cr.SHOP_CLOSING_ID is null ");
        /*if (closingDTO.getEmpCode()!=null) {
            queryBuilder.append("  and cr.UPDATEUSER = '"+closingDTO.getEmpCode()+"' ");
        }*/
        queryBuilder.append(" GROUP BY cr.RECEIPTID, cr.RECEIPTNO, cr.BRANCHAREA, cr.UPDATEUSER, cr.ATTRIBUTES, trunc(cr.UPDATEDTTM), trunc(cr.RECEIPTDTTM) ");

        List<Receipt> receiptsList = episJdbcTemplate.query(queryBuilder.toString(), BeanPropertyRowMapper.newInstance(Receipt.class));

        if (receiptsList != null && receiptsList.size()>0 ) {
            updateFlg = true;

            Map<Long, Receipt> receiptMap = newHashMap();
            for (Receipt rec : receiptsList) {

                /*episJdbcTemplate.update("UPDATE CORRECEIPT "
                        + "SET SHOP_CLOSING_ID = ? "
                        + "WHERE receiptid = ?", closingDTO.getShopClosingId(), rec.getId() );
*/
                receiptMap.put(rec.getId(), rec);
                Iterable<Receipt> recResults = receiptRepository.findAll(receiptMap.keySet());
                if (recResults != null) {
                    for (Receipt recResult : recResults) {
                        recResult.setShopClosingId(Long.parseLong(closingDTO.getShopClosingId()));
                        receiptRepository.save(recResult);
                    }
                }

            }
        }

        return updateFlg;
    }

}

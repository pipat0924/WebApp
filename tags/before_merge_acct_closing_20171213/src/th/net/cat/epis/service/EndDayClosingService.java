package th.net.cat.epis.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import th.net.cat.epis.dto.EndDayClosingDTO;
import th.net.cat.epis.entity.EmpClosing;
import th.net.cat.epis.entity.EndDayClosing;
import th.net.cat.epis.entity.ShopClosing;
import th.net.cat.epis.repo.EmpClosingRepository;
import th.net.cat.epis.repo.EndDayClosingRepository;
import th.net.cat.epis.repo.ShopClosingRepository;
import th.net.cat.epis.util.AppConstants;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

    @Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;

    @Transactional
    public EmpClosing createEndDayClosing(EndDayClosingDTO dto) throws Exception{
        SimpleDateFormat textFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date closingDate = new java.sql.Date(textFormat.parse(dto.getSearchDate()).getTime());

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

        return rtEmpClosing;
    }

    public List<EndDayClosing> findEndDayClosingPosGroup(String searchDate, String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CLOSING_DATE, BRANCH_CODE, MAC_NO, POS_NO, SHOP_CLOSING_ID shopClosingId1 ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        query.append(" , nvl(sum(BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount ");
        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CREDIT), 0) totalCredit ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_OFFSET), 0) totalOffset ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");
        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? group by CLOSING_DATE, BRANCH_CODE, MAC_NO, POS_NO, SHOP_CLOSING_ID ");
        endDayClosingList = episJdbcTemplate.query(query.toString(),new Object[]{searchDate, branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroup(String searchDate, String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CLOSING_DATE, BRANCH_CODE, EMP_CODE, DOC_STATUS, SHOP_CLOSING_ID shopClosingId1 ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        query.append(" , nvl(sum(BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount ");
        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CREDIT), 0) totalCredit ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_OFFSET), 0) totalOffset ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");
        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and BACKDATE_STATUS is null group by CLOSING_DATE, BRANCH_CODE, EMP_CODE, DOC_STATUS, SHOP_CLOSING_ID ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{searchDate, branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroupBackDate(String branchCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT  RECEIPT_DATE, to_char(RECEIPT_DATE, 'dd/mm/yyyy') receiptDtStr, BRANCH_CODE, EMP_CODE, DOC_STATUS ");
        query.append(" , sum(TOTAL_EXC_AMOUNT) totalExcAmount, sum(TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(TOTAL_CHAEGE) totalCharge, sum(TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        query.append(" , nvl(sum(BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount ");
        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CREDIT), 0) totalCredit ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_OFFSET), 0) totalOffset ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");
        query.append(" from DAY_END_CLOSING where BRANCH_CODE = ? and BACKDATE_STATUS = 'Y' and SHOP_CLOSING_ID is null group by RECEIPT_DATE, BRANCH_CODE, EMP_CODE, DOC_STATUS ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{branchCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroup(String searchDate, String branchCode, String empCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CLOSING_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, EMP_NAME, DOC_STATUS, EMP_CLOSING_ID empClosingId1 ");
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
        query.append(" , nvl(sum(BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount ");

        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(TOTAL_CREDIT), 0) totalCredit, nvl(sum(TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(TOTAL_OFFSET), 0) totalOffset, nvl(sum(TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");
        query.append(" from DAY_END_CLOSING where trunc(CLOSING_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and EMP_CODE = ? and BACKDATE_STATUS is null group by CLOSING_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, DOC_STATUS ");
        query.append(" , EMP_NAME, EMP_CLOSING_ID ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{searchDate, branchCode, empCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingEmpGroupBackDate(String searchDate, String branchCode, String empCode){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT RECEIPT_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, EMP_NAME, DOC_STATUS, EMP_CLOSING_ID empClosingId1 ");
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
        query.append(" , nvl(sum(BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount ");

        query.append(" , nvl(sum(TOTAL_CASH), 0) totalCash, nvl(sum(TOTAL_CHEQUE), 0) totalCheque, nvl(sum(TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(TOTAL_CREDIT), 0) totalCredit, nvl(sum(TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(TOTAL_COUPON), 0)  totalCoupon, nvl(sum(TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(TOTAL_OFFSET), 0) totalOffset, nvl(sum(TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(TOTAL_WT_69TRE), 0) totalWt69Tre ");
        query.append(" from DAY_END_CLOSING where trunc(RECEIPT_DATE) = TO_TIMESTAMP(?, 'DD-MM-YYYY') and BRANCH_CODE = ? and EMP_CODE = ? and BACKDATE_STATUS = 'Y' group by RECEIPT_DATE, BRANCH_CODE, SHOP_NAME, EMP_CODE, DOC_STATUS ");
        query.append(" , EMP_NAME, EMP_CLOSING_ID ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{searchDate, branchCode, empCode}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        if(!CollectionUtils.isEmpty(endDayClosingList) && endDayClosingList.size()>1){
            for(EndDayClosing dtl: endDayClosingList){
                if(dtl.getDocStatus().equals(AppConstants.ENDDAY_CLOSE_STATUS_S)){
                    endDayClosingList.remove(dtl);
                }
            }
        }
        return endDayClosingList;
    }

    public List<EndDayClosing> findEndDayClosingEmpGroup(Long id){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT a.CLOSING_DATE, a.BRANCH_CODE, a.SHOP_NAME, a.EMP_CODE, a.EMP_NAME, a.DOC_STATUS, a.EMP_CLOSING_ID empClosingId1, b.CREATEDTTM createDttm, b.BACKDATE_STATUS backDateStatus ");
        query.append(" , sum(a.TOTAL_EXC_AMOUNT) totalExcAmount, sum(a.TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(a.TOTAL_CHAEGE) totalCharge, sum(a.TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        //28-05-2017
        query.append(" , nvl(sum(a.EXC_CANCEL_TOTAL_AMOUNT), 0) excCancelTotalAmount, nvl(sum(a.EXC_CANCEL_TOTAL_COUNT), 0) excCancelTotalCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_FULL_AMOUNT), 0) receiveWhFullAmount, nvl(sum(a.RECEIVE_WH_FULL_COUNT), 0) receiveWhFullCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_ABVR_AMOUNT), 0) receiveWhAbvrAmount, nvl(sum(a.RECEIVE_WH_ABVR_COUNT), 0) receiveWhAbvrCount ");
        query.append(" , nvl(sum(a.RECEIVE_ONLY_AMOUNT), 0) receiveOnlyAmount, nvl(sum(a.RECEIVE_ONLY_COUNT), 0) receiveOnlyCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_FULL_AMOUNT), 0) cancelReceiveWhFullAmount, nvl(sum(a.CANCEL_RECEIVE_WH_FULL_COUNT), 0) cancelReceiveWhFullCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_AMOUNT), 0) cancelReceiveWhAbvrAmount, nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_COUNT), 0) cancelReceiveWhAbvrCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_ONLY_AMOUNT), 0) cancelReceiveOnlyAmount, nvl(sum(a.CANCEL_RECEIVE_ONLY_COUNT), 0) cancelReceiveOnlyCount ");
        query.append(" , nvl(sum(a.BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(a.BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount, nvl(sum(a.TOTAL_DISCOUNT), 0) totalDiscount ");
        query.append(" , nvl(sum(a.TOTAL_CANCEL_AMT), 0) totalCancelAmt, nvl(sum(a.TOTAL_CANCEL_CNT), 0) totalCancelCnt ");

        query.append(" , nvl(sum(a.TOTAL_CASH), 0) totalCash, nvl(sum(a.TOTAL_CASH_COUNT), 0) totalCashCount, nvl(sum(a.TOTAL_CHEQUE), 0) totalCheque, nvl(sum(a.TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(a.TOTAL_CREDIT), 0) totalCredit, nvl(sum(a.TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(a.TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(a.TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(a.TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(a.TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(a.TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(a.TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(a.TOTAL_COUPON), 0)  totalCoupon, nvl(sum(a.TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(a.TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(a.TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(a.TOTAL_OFFSET), 0) totalOffset, nvl(sum(a.TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(a.TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(a.TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(a.TOTAL_WT_69TRE), 0) totalWt69Tre ");
        query.append(" from DAY_END_CLOSING a inner join EMP_CLOSING b on a.EMP_CLOSING_ID = b.EMP_CLOSING_ID where a.EMP_CLOSING_ID = ? group by a.CLOSING_DATE, a.BRANCH_CODE, a.SHOP_NAME, a.EMP_CODE, a.DOC_STATUS ");
        query.append(" , a.EMP_NAME, a.EMP_CLOSING_ID, b.CREATEDTTM, b.BACKDATE_STATUS ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{id}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
        return endDayClosingList;
    }
    public List<EndDayClosing> findEndDayClosingShopGroup(Long id){
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT a.BRANCH_CODE, a.SHOP_NAME, b.CLOSING_EMP_CODE empCode, b.CLOSING_EMP_NAME empName, a.SHOP_CLOSING_ID shopClosingId1, b.CREATEDTTM createDttm, b.BACKDATE_STATUS backDateStatus ");//a.CLOSING_DATE,
        query.append(" , sum(a.TOTAL_EXC_AMOUNT) totalExcAmount, sum(a.TOTAL_VAT_AMOUNT) totalVatAmount ");
        query.append(" , sum(a.TOTAL_CHAEGE) totalCharge, sum(a.TOTAL_RECEIPT_COUNT) totalReceiptCount ");
        //28-05-2017
        query.append(" , nvl(sum(a.EXC_CANCEL_TOTAL_AMOUNT), 0) excCancelTotalAmount, nvl(sum(a.EXC_CANCEL_TOTAL_COUNT), 0) excCancelTotalCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_FULL_AMOUNT), 0) receiveWhFullAmount, nvl(sum(a.RECEIVE_WH_FULL_COUNT), 0) receiveWhFullCount ");
        query.append(" , nvl(sum(a.RECEIVE_WH_ABVR_AMOUNT), 0) receiveWhAbvrAmount, nvl(sum(a.RECEIVE_WH_ABVR_COUNT), 0) receiveWhAbvrCount ");
        query.append(" , nvl(sum(a.RECEIVE_ONLY_AMOUNT), 0) receiveOnlyAmount, nvl(sum(a.RECEIVE_ONLY_COUNT), 0) receiveOnlyCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_FULL_AMOUNT), 0) cancelReceiveWhFullAmount, nvl(sum(a.CANCEL_RECEIVE_WH_FULL_COUNT), 0) cancelReceiveWhFullCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_AMOUNT), 0) cancelReceiveWhAbvrAmount, nvl(sum(a.CANCEL_RECEIVE_WH_ABVR_COUNT), 0) cancelReceiveWhAbvrCount ");
        query.append(" , nvl(sum(a.CANCEL_RECEIVE_ONLY_AMOUNT), 0) cancelReceiveOnlyAmount, nvl(sum(a.CANCEL_RECEIVE_ONLY_COUNT), 0) cancelReceiveOnlyCount ");
        query.append(" , nvl(sum(a.BACK_DATE_TOTAL_AMOUNT), 0) backDateTotalAmount, nvl(sum(a.BACK_DATE_TOTAL_COUNT), 0) backDateTotalCount, nvl(sum(a.TOTAL_DISCOUNT), 0) totalDiscount ");
        query.append(" , nvl(sum(a.TOTAL_CANCEL_AMT), 0) totalCancelAmt, nvl(sum(a.TOTAL_CANCEL_CNT), 0) totalCancelCnt ");

        query.append(" , nvl(sum(a.TOTAL_CASH), 0) totalCash, nvl(sum(a.TOTAL_CASH_COUNT), 0) totalCashCount, nvl(sum(a.TOTAL_CHEQUE), 0) totalCheque, nvl(sum(a.TOTAL_CHEQUE_COUNT), 0) totalChequeCount, nvl(sum(a.TOTAL_CREDIT), 0) totalCredit, nvl(sum(a.TOTAL_CREDIT_COUNT), 0) totalCreditCount ");
        query.append(" , nvl(sum(a.TOTAL_MONEY_ORDER), 0) totalMoneyOrder, nvl(sum(a.TOTAL_MONEY_ORD_CNT), 0) totalMoneyOrdCnt, nvl(sum(a.TOTAL_MONEY_TRANSFER), 0) totalMoneyTransfer, nvl(sum(a.TOTAL_MONEY_TRNS_CNT), 0) totalMoneyTrnsCnt, nvl(sum(a.TOTAL_BILL_EXCHANGE), 0) totalBillExchange, nvl(sum(a.TOTAL_BILL_EXCH_CNT), 0) totalBillExchCnt ");
        query.append(" , nvl(sum(a.TOTAL_COUPON), 0)  totalCoupon, nvl(sum(a.TOTAL_COUPON_COUNT), 0)  totalCouponCount, nvl(sum(a.TOTAL_FOREIGN_TRANSFER), 0) totalForeignTransfer, nvl(sum(a.TOTAL_FOREIGN_TRNS_CNT), 0) totalForeignTrnsCnt, nvl(sum(a.TOTAL_OFFSET), 0) totalOffset, nvl(sum(a.TOTAL_OFFSET_COUNT), 0) totalOffsetCount ");
        query.append(" , nvl(sum(a.TOTAL_WT_3TRED), 0) totalWt3tred, nvl(sum(a.TOTAL_WT_69BIS), 0) totalWt69Bis, nvl(sum(a.TOTAL_WT_69TRE), 0) totalWt69Tre ");
        query.append(" from DAY_END_CLOSING a inner join SHOP_CLOSING b on a.SHOP_CLOSING_ID = b.SHOP_CLOSING_ID where a.SHOP_CLOSING_ID = ? group by a.BRANCH_CODE, a.SHOP_NAME, b.CLOSING_EMP_CODE, b.CLOSING_EMP_NAME, a.SHOP_CLOSING_ID ");//a.CLOSING_DATE,
        query.append(" , b.CREATEDTTM, b.BACKDATE_STATUS ");
        endDayClosingList = episJdbcTemplate.query(query.toString(), new Object[]{id}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));
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
        List<EndDayClosing> endDayClosingList = endDayClosingRepository.findByBranchCodeAndClosingDateAndBackDateStatus(dtl.getBranchCode(), new Date(dtl.getClosingDate().getTime()), dtl.getBackdateStatus());
        for(EndDayClosing dtl0: endDayClosingList){
            dtl0.setShopClosing(shopClosing);
            endDayClosingRepository.save(dtl0);
        }
        return shopClosing;
    }
    public ShopClosing createShopClosingBackDate(ShopClosing dtl){
        ShopClosing shopClosing = new ShopClosing();
        shopClosing = shopClosingRepository.findByBranchCodeAndClosingDateAndBackdateStatus(dtl.getBranchCode(), dtl.getClosingDate(), dtl.getBackdateStatus());
        if(shopClosing!=null && shopClosing.getId()!=null){
            dtl.setId(shopClosing.getId());
        }
        shopClosing = shopClosingRepository.save(dtl);
        List<EndDayClosing> endDayClosingList = endDayClosingRepository.findByBranchCodeAndDocStatusAndBackDateStatus(dtl.getBranchCode(), dtl.getDocStatus(), dtl.getBackdateStatus());
        for(EndDayClosing dtl0: endDayClosingList){
            dtl0.setShopClosing(shopClosing);
            endDayClosingRepository.save(dtl0);
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

}

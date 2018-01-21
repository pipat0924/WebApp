package th.net.cat.epis.batch.task;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.SystemProcessExitCodeMapper;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import th.net.cat.epis.entity.EndDayClosing;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.repo.EndDayClosingRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.util.AppConstants;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by nastanda on 5/19/2017 AD.
 */
@Component("endDayClosingTaskSystem")
public class EndDayClosingTask implements Tasklet{
    DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
    @Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;
    @Autowired
    EndDayClosingRepository endDayClosingRepository;
    ReceiptRepository receiptRepository;
    @Value("${epis.task.active}") String taskActive;
    
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
            String flagActive = "1";
        if(StringUtils.equals(flagActive, "1")) {
            StringBuilder queryStr = new StringBuilder();
            queryStr.append(" SELECT trunc(crp.PAYMENTDATE) closingDate, crc.BRANCHAREA branchCode, crp.POSID macNo ");
                    //queryStr.append(" , crc.UPDATEUSER empCode, sum(crc.CHARGE) totalExcAmount, sum(crc.VAT) totalVatAmount ");
            queryStr.append(" , mof.OFFICERCODE empCode, crc.UPDATEUSER userName ");///, sum(crc.CHARGE) totalExcAmount, sum(crc.VAT) totalVatAmount ");
            queryStr.append(" , msp.POSNO posNo, concat(concat(max(mof.OFFICERGIVENNAME), ' '), max(mof.OFFICERFAMILYNAME)) empName ");
                    //            queryStr.append(" , concat(concat(crc.BRANCHCODE, ' '),(select SHOPNAME from (select SHOPNAME from MASSHOP where BUAREA = crc.BRANCHAREA order by SHOPID desc) where ROWNUM = 1)) shopName ");
                    //            queryStr.append(" , (select SHOPNO from (select SHOPNO from MASSHOP where BUAREA = crc.BRANCHAREA order by SHOPID desc) where ROWNUM = 1 ) shopNo ");
            queryStr.append(" , concat(concat(crc.BRANCHAREA, ' '),(select ms.SHOPNAME from (select * from MASSHOP order by SHOPID desc) ms where ms.BUAREA = crc.BRANCHAREA and ROWNUM = 1)) shopName ");
            queryStr.append(" , (select ms.SHOPNO from (select * from MASSHOP order by SHOPID desc) ms where ms.BUAREA = crc.BRANCHAREA and ROWNUM = 1 ) shopNo ");

            queryStr.append(" , sum(nvl(case when crp.PAYMENTTYPE <>'AGENT' THEN round(((crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1))*100)/(100+crp.VATRATE),2) end, 0) + nvl(case when crp.PAYMENTTYPE ='AGENT'and crc.REF2 = 'FEE-IN' THEN crc.CHARGE end, 0) ) totalExcAmount ");
            queryStr.append(" , sum(nvl(case when crp.PAYMENTTYPE <>'AGENT' THEN round(((crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1))*crc.VATRATE)/(100+crc.VATRATE),2) end, 0) + nvl(case when crp.PAYMENTTYPE ='AGENT'and crc.REF2 = 'FEE-IN' THEN crc.VAT end, 0) ) totalVatAmount ");
            queryStr.append(" , sum(nvl(case when crp.PAYMENTTYPE <>'AGENT' THEN crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1) end, 0) + nvl(case when crp.PAYMENTTYPE ='AGENT'and crc.REF2 = 'FEE-IN' THEN crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1) end, 0) ) totalCharge ");
            queryStr.append(" , count(crc.RECEIPTID) - sum(case when crp.PAYMENTTYPE = 'AGENT' and crc.REF2 is null then 1 else 0 end) totalreceiptcount ");
                    //total amount not include cancel
            queryStr.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) EXC_CANCEL_TOTAL_AMOUNT ");
                        //total count not include cancel
            queryStr.append(" , nvl((SELECT count(*) - sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) EXC_CANCEL_TOTAL_COUNT ");
                        //total amount full receive_wh
            queryStr.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0))+sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 = 'FEE-IN' and a.RECEIPTTYPE = 'FULL' and a.FLG_HEADER = 1 then a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) else 0 end)-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_FULL_AMOUNT ");
                        //total count full receive_wh
            queryStr.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null and a.receipttype = 'FULL' and a.FLG_HEADER = 1 then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_FULL_COUNT ");
                        //total amount abvr receive_wh
            queryStr.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0))+sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 = 'FEE-IN' and a.receipttype = 'ABVR' and a.FLG_HEADER = 1 then a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) else 0 end)-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_ABVR_AMOUNT ");
                        //total count abvr receive_wh
            queryStr.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null and a.receipttype = 'ABVR' and a.FLG_HEADER = 1 then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_ABVR_COUNT ");
                        //total amount receive_only
            queryStr.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0))+sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 = 'FEE-IN' and a.receipttype = 'FULL' and a.FLG_HEADER = 2 then a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) else 0 end)-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 2 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_ONLY_AMOUNT ");
                        //total count receive_only
            queryStr.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null and a.receipttype = 'FULL' and a.FLG_HEADER = 2 then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 2 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_ONLY_COUNT ");
                        //total amount full wh_only
            queryStr.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0))+sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 = 'FEE-IN' and a.receipttype = 'FULL' and a.FLG_HEADER = 3 then a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) else 0 end)-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_FULL_AMOUNT ");
                        //total count full wh_only
            queryStr.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null and a.receipttype = 'FULL' and a.FLG_HEADER = 3 then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_FULL_COUNT ");
                        //total amount abvr wh_only
            queryStr.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0))+sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 = 'FEE-IN' and a.receipttype = 'ABVR' and a.FLG_HEADER = 3 then a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) else 0 end)-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_ABVR_AMOUNT ");
                        //total count abvr wh_only
            queryStr.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null and a.receipttype = 'ABVR' and a.FLG_HEADER = 3 then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_ABVR_COUNT ");

                        //cancel receive_wh full amount
            queryStr.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0))+sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.REF2 = 'FEE-IN' then c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) else 0 end)-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_WH_FULL_AMOUNT ");
                        //cancel receive_wh full count
            queryStr.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_WH_FULL_COUNT ");
                        //cancel receive_wh abvr amount
            queryStr.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0))+sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.REF2 = 'FEE-IN' then c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) else 0 end)-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_RECEIVE_WH_ABVR_AMOUNT ");
                        //cancel receive_wh abvr count
            queryStr.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_WH_ABVR_COUNT ");
                        //cancel receive_only amount
            queryStr.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0))+sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.REF2 = 'FEE-IN' then c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) else 0 end)-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 2 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_ONLY_AMOUNT ");
                        //cancel receive_only count
            queryStr.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 2 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_RECEIVE_ONLY_COUNT ");
                        //cancel wh_only full amount
            queryStr.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0))+sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.REF2 = 'FEE-IN' then c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) else 0 end)-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_FULL_AMOUNT ");
                        //cancel wh_only full count
            queryStr.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_FULL_COUNT ");
                        //cancel wh_only abvr amount
            queryStr.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0))+sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.REF2 = 'FEE-IN' then c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) else 0 end)-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_ABVR_AMOUNT ");
                        //cancel wh_only abvr count
            queryStr.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_ABVR_COUNT ");
                        // back date amount
                        //            queryStr.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0))+sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 = 'FEE-IN' then a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) else 0 end)-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
                        //            queryStr.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) TRANS_BACK_DATE_AMOUNT");
                        //                        // back date count
                        //            queryStr.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
                        //            queryStr.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0)  TRANS_BACK_DATE_TOTAL_COUNT ");
                        //pay by cash
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and c.CODE = 'CC' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'CC' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and ( trsm.EMP_CODE = crc.UPDATEUSER ) ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCash ");
                        //pay by cash count
            queryStr.append(" , nvl((select count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and c.CODE = 'CC' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'CC' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCashCount ");
                        //pay by cheque
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'CH' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCheque ");
                        //pay by cheque count
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'CH' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalChequeCount ");
                        //pay by credit card
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" left JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'CR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCredit ");
                        
                        //pay by credit card count
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'CR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalCreditCount ");
                        //pay by ธนาณัติ
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'MO' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalMoneyOrder ");
                        //pay by ธนาณัติ count
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'MO' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalMoneyOrdCnt ");
                        //pay by ตั๋วแลกเงิน
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'BX' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalBillExchange ");
                        //pay by ตั๋วแลกเงิน count
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'BX' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalBillExchCnt ");
                        //pay by Coupon
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'CP' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCoupon ");
                        //pay by Coupon count
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'CP' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalCouponCount ");
                        //pay by money transfer
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'TR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalMoneyTransfer ");
                        //pay by money transfer count
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'TR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalMoneyTrnsCnt ");
                        //pay by offset
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'OF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalOffset ");
                        //pay by offset count
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'OF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalOffsetCount ");
                        
                        //pay by money transfer(GFMIS) instead of backdate
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'GF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  transGfmisTotalAmount ");

                        //pay by money transfer(GFMIS) count instead of backdate
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'GF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0)  transGfmisTotalCount ");
                        
                        //pay by money transfer(FOREIGN) instead of backdate
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2, b.CURRENCYRATE) ");
            queryStr.append(" trsm where trsm.CODE = 'TF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  transForeignTotalAmount ");

                        //pay by money transfer(FOREIGN) to bath instead of backdate
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT_BTH end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, round(c.AMOUNT / nvl(b.CURRENCYRATE,1)) AMOUNT_BTH, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2, b.CURRENCYRATE) ");
            queryStr.append(" trsm where trsm.CODE = 'TF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  transForeignThTotalAmount ");

                        //pay by money transfer(FOREIGN) count instead of backdate
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'TF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE,trsm.CHEQUENO), 0)  transForeignTotalCount ");

                        //pay by money transfer(FOREIGN)-USD instead of backdate
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%US%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  foreignUsTotalAmount ");

                        //pay by money transfer(FOREIGN)-USD to bath instead of backdate
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT_BTH end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, round(c.AMOUNT / nvl(b.CURRENCYRATE,1)) AMOUNT_BTH, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT,b.CURRENCYRATE, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'TF' and upper(trsm.CURRENCYCODE) like '%US%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  foreignUsToThTotalAmount");

                        //pay by money transfer(FOREIGN)-USD count instead of backdate
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%US%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) foreignUsTotalCount ");

                        //pay by money transfer(FOREIGN)-USD instead of backdate
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%EU%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  foreignEuToThTotalAmount ");

                        //pay by money transfer(FOREIGN)-USD to bath instead of backdate
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT_BTH end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, round(c.AMOUNT / nvl(b.CURRENCYRATE,1)) AMOUNT_BTH, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT,b.CURRENCYRATE, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.CODE = 'TF' and upper(trsm.CURRENCYCODE) like '%EU%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  foreignEuTotalAmount");

                        //pay by money transfer(FOREIGN)-USD count instead of backdate
            queryStr.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%EU%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) foreignEuTotalCount ");

                        //pay by wt 3tred
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" INNER JOIN TRSDEDUCTION c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.DEDUCTIONTYPE = '3TREDECIM' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalWt3tred ");
                        //pay by wt 69Bis
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" INNER JOIN TRSDEDUCTION c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.DEDUCTIONTYPE = '69BIS' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalWt69bis ");
                        //pay by wt 69Tre
            queryStr.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append(" SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(" ,b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append(" INNER JOIN TRSDEDUCTION c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append(" GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append(" trsm where trsm.DEDUCTIONTYPE = '69TRE' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalWt69tre ");
                        //cancel receipt amount
            queryStr.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <> 'AGENT' then c1.TOTALCHARGE end,0))+sum(nvl(case when cp1.PAYMENTTYPE = 'AGENT' and c1.REF2 = 'FEE-IN' then c1.TOTALCHARGE end,0))-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(c1.AFTERSALEDISC_VAT, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) TOTAL_CANCEL_AMT ");
                        //cancel receipt count
            queryStr.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.REF2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr.append(" where trunc(c1.RECEIPTDTTM)=trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) TOTAL_CANCEL_CNT ");
                        // total discount
            queryStr.append(" , nvl((SELECT sum(nvl(a.DISCOUNT, 0)+nvl(a.EXC_DISCOUNT, 0)+nvl(a.AFTERSALEDISCOUNT, 0)+nvl(a.AFTERSALEDISC_VAT, 0)+nvl(a.SPECIAL_DISCOUNT, 0)) from CORRECEIPT a where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append(" and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) totalDiscount ");

                        //Agent
            queryStr.append(", nvl((select sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then trsm.TOTALCHARGE end,0)) from (");
            queryStr.append("SELECT trunc(a.RECEIPTDTTM) CLOSING_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr.append(",b.PAYMENTID/*, c.CODE, c.NAME, c.AMOUNT AMOUNT*/, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr.append("from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr.append("LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr.append("GROUP BY trunc(a.RECEIPTDTTM), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID/*, c.CODE, c.NAME, c.AMOUNT*/, b.PAYMENTTYPE, a.REF2) ");
            queryStr.append("trsm where trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) ");
            queryStr.append("and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr.append("GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) agentTotalAmount ");

                        //Agent total count full receive_wh
            queryStr.append(", nvl((SELECT sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null and a.receipttype = 'FULL' and a.FLG_HEADER = 1 then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append("and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) agentReceiveFullAgentCount ");
                        //Agent total count abvr receive_wh
            queryStr.append(", nvl((SELECT sum(case when b.PAYMENTTYPE = 'AGENT' and a.REF2 is null and a.receipttype = 'ABVR' and a.FLG_HEADER = 1 then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr.append("and trunc(a.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) agentReceiveAbvrCount ");
            queryStr.append(" FROM CORRECEIPT crc INNER JOIN CORPAYMENT crp on crc.PAYMENTID = crp.PAYMENTID ");
            queryStr.append(" inner join MASPOS msp on crp.POSID = msp.POSID ");

                        //queryStr.append(" inner join MASOFFICER mof on crp.UPDATEUSER = mof.USERNAME");// mof.OFFICERCODE ");
            queryStr.append(" INNER join MASOFFICER mof on ( crp.UPDATEUSER = mof.USERNAME  ) ");/// mof.OFFICERCODE ");
                        //queryStr.append(" INNER JOIN MASSHOP msh on crc.BRANCHAREA = msh.BUAREA ");
                        //queryStr.append(" WHERE crc.ATTRIBUTES NOT LIKE '%R%' ");
            queryStr.append(" WHERE trunc(crp.PAYMENTDATE) = ? and trunc(crc.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and crc.SHOP_CLOSING_ID is null and crc.EMP_CLOSING_ID is null ");
                        //queryStr.append(" GROUP BY trunc(crc.RECEIPTDTTM), crc.BRANCHAREA, crp.POSID, crc.UPDATEUSER ");
            queryStr.append(" GROUP BY trunc(crp.PAYMENTDATE), crc.BRANCHAREA, crc.BRANCHCODE, crp.POSID, msp.POSNO , crc.UPDATEUSER, mof.OFFICERCODE ");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");//for testing
            String paramDateAsString = "2017-10-21";
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            System.out.println(calendar.getTime());

            String userName = "BATCH";//SecurityContextHolder.getContext().getAuthentication().getName();
            List<EndDayClosing> endDayClosings = new ArrayList<>();
             endDayClosings = episJdbcTemplate.query(queryStr.toString(), new Object[]{calendar.getTime()}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));//calendar.getTime() for testing textFormat.parse(paramDateAsString)

            //********case back date day end closing********//

            StringBuilder queryStr2 = new StringBuilder();
            queryStr2.append(" SELECT 'Y' backDateStatus, trunc(crp.PAYMENTDATE) closingDate, trunc(crc.RECEIPTDTTM) receiptDate, crc.BRANCHAREA branchCode, crp.POSID macNo ");
                    //queryStr2.append(" , crc.UPDATEUSER empCode, sum(crc.CHARGE) totalExcAmount, sum(crc.VAT) totalVatAmount ");
            queryStr2.append(" , mof.OFFICERCODE empCode, crc.UPDATEUSER userName  ");
            queryStr2.append(" , sum(nvl(case when crp.PAYMENTTYPE <>'AGENT' THEN round(((crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1))*100)/(100+crp.VATRATE),2) end, 0) + nvl(case when crp.PAYMENTTYPE ='AGENT'and crc.REF2 = 'FEE-IN' THEN crc.CHARGE end, 0) ) totalExcAmount ");
            queryStr2.append(" , sum(nvl(case when crp.PAYMENTTYPE <>'AGENT' THEN round(((crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1))*crc.VATRATE)/(100+crc.VATRATE),2) end, 0) + nvl(case when crp.PAYMENTTYPE ='AGENT'and crc.REF2 = 'FEE-IN' THEN crc.VAT end, 0) ) totalVatAmount ");
            queryStr2.append(" , sum(nvl(case when crp.PAYMENTTYPE <>'AGENT' THEN crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1) end, 0) + nvl(case when crp.PAYMENTTYPE ='AGENT'and crc.REF2 = 'FEE-IN' THEN crc.TOTALCHARGE*nvl(crp.CURRENCYRATE,1) end, 0) ) totalCharge ");
            queryStr2.append(" , count(crc.RECEIPTID) - sum(case when crp.PAYMENTTYPE = 'AGENT' and crc.REF2 is null then 1 else 0 end) totalreceiptcount ");

            queryStr2.append(" , msp.POSNO posNo, concat(concat(max(mof.OFFICERGIVENNAME), ' '), max(mof.OFFICERFAMILYNAME)) empName ");
                    //            queryStr2.append(" , concat(concat(crc.BRANCHCODE, ' '),(select SHOPNAME from (select SHOPNAME from MASSHOP where BUAREA = crc.BRANCHAREA order by SHOPID desc) where ROWNUM = 1)) shopName ");
                    //            queryStr2.append(" , (select SHOPNO from (select SHOPNO from MASSHOP where BUAREA = crc.BRANCHAREA order by SHOPID desc) where ROWNUM = 1 ) shopNo ");
            queryStr2.append(" , concat(concat(crc.BRANCHAREA, ' '),(select ms.SHOPNAME from (select * from MASSHOP order by SHOPID desc) ms where ms.BUAREA = crc.BRANCHAREA and ROWNUM = 1)) shopName ");
            queryStr2.append(" , (select ms.SHOPNO from (select * from MASSHOP order by SHOPID desc) ms where ms.BUAREA = crc.BRANCHAREA and ROWNUM = 1 ) shopNo ");


                    //total amount not include cancel
            queryStr2.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) EXC_CANCEL_TOTAL_AMOUNT ");
                        //total count not include cancel
            queryStr2.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) EXC_CANCEL_TOTAL_COUNT ");
                        //total amount full receive_wh
            queryStr2.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_FULL_AMOUNT ");
                        //total count full receive_wh
            queryStr2.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_FULL_COUNT ");
                        //total amount abvr receive_wh
            queryStr2.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_ABVR_AMOUNT ");
                        //total count abvr receive_wh
            queryStr2.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 1 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_WH_ABVR_COUNT ");
                        //total amount receive_only
            queryStr2.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 2 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_ONLY_AMOUNT ");
                        //total count receive_only
            queryStr2.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 2 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) RECEIVE_ONLY_COUNT ");
                        //total amount full wh_only
            queryStr2.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_FULL_AMOUNT ");
                        //total count full wh_only
            queryStr2.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='FULL' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_FULL_COUNT ");
                        //total amount abvr wh_only
            queryStr2.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and b.POSID = crp.POSID and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_ABVR_AMOUNT ");
                        //total count abvr wh_only
            queryStr2.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.RECEIPTTYPE='ABVR' and a.FLG_HEADER = 3 and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) WH_ONLY_ABVR_COUNT ");

                        //cancel receive_wh full amount
            queryStr2.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) + nvl(case when cp1.PAYMENTTYPE ='AGENT'and c1.REF2 = 'FEE-IN' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) )-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_WH_FULL_AMOUNT ");
                        //cancel receive_wh full count
            queryStr2.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_WH_FULL_COUNT ");
                        //cancel receive_wh abvr amount
            queryStr2.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) + nvl(case when cp1.PAYMENTTYPE ='AGENT'and c1.REF2 = 'FEE-IN' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) )-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_RECEIVE_WH_ABVR_AMOUNT ");
                        //cancel receive_wh abvr count
            queryStr2.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 1 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_WH_ABVR_COUNT ");
                        //cancel receive_only amount
            queryStr2.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) + nvl(case when cp1.PAYMENTTYPE ='AGENT'and c1.REF2 = 'FEE-IN' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) )-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 2 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) CANCEL_RECEIVE_ONLY_AMOUNT ");
                        //cancel receive_only count
            queryStr2.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 2 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_RECEIVE_ONLY_COUNT ");
                        //cancel wh_only full amount
            queryStr2.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) + nvl(case when cp1.PAYMENTTYPE ='AGENT'and c1.REF2 = 'FEE-IN' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) )-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_FULL_AMOUNT ");
                        //cancel wh_only full count
            queryStr2.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='FULL' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_FULL_COUNT ");
                        //cancel wh_only abvr amount
            queryStr2.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) + nvl(case when cp1.PAYMENTTYPE ='AGENT'and c1.REF2 = 'FEE-IN' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) )-sum(nvl(c1.AFTERSALEDISCOUNT, 0)+nvl(case c1.AFTERSALEDISCOUNT when null THEN 0 else c1.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_ABVR_AMOUNT ");
                        //cancel wh_only abvr count
            queryStr2.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.RECEIPTTYPE='ABVR' and c1.FLG_HEADER = 3 and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0)  CANCEL_WH_ONLY_ABVR_COUNT ");
             /*// back date amount
              queryStr2.append(" , nvl((SELECT sum(nvl(case when b.PAYMENTTYPE <>'AGENT' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) + nvl(case when b.PAYMENTTYPE ='AGENT'and a.REF2 = 'FEE-IN' THEN a.TOTALCHARGE*nvl(b.CURRENCYRATE,1) end, 0) )-sum(nvl(a.AFTERSALEDISCOUNT, 0)+nvl(case a.AFTERSALEDISCOUNT when null THEN 0 else a.AFTERSALEDISC_VAT end, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
              queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and trunc(a.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%'), 0) backDateTotalAmount");
              // back date count
              queryStr2.append(" , nvl((SELECT count(*)-sum(case when b.PAYMENTTYPE = 'AGENT' and a.ref2 is null then 1 else 0 end) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
              queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and trunc(a.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%'), 0)  backDateTotalCount ");*/


                //pay by cash
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and c.CODE = 'CC' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'CC' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and ( trsm.EMP_CODE = crc.UPDATEUSER ) ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCash ");

                        //pay by cash count
            queryStr2.append(" , nvl((select count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and c.CODE = 'CC' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'CC' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCashCount ");

                        //pay by cheque
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'CH' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCheque ");

                        //pay by cheque count
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" INNER JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'CH' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalChequeCount ");
                        //pay by credit card
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" left JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'CR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCredit ");

                        //pay by credit card count
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID  WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'CR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalCreditCount ");
                        //pay by ธนาณัติ
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'MO' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalMoneyOrder ");

                        //pay by ธนาณัติ count
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'MO' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalMoneyOrdCnt ");
                        //pay by ตั๋วแลกเงิน
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'BX' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalBillExchange ");

                        //pay by ตั๋วแลกเงิน count
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'BX' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalBillExchCnt ");
                        //pay by Coupon
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'CP' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalCoupon ");
                        //pay by Coupon count
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'CP' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalCouponCount ");
                        //pay by money transfer
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalMoneyTransfer ");
                        //pay by money transfer count
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'TR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalMoneyTrnsCnt ");
                        //pay by offset
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2 ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'OF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalOffset ");
                        //pay by offset count
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, sum(a.TOTALCHARGE) TOTALCHARGE, a.REF2, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'OF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) totalOffsetCount ");



                        //pay by money transfer instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) backDateTotalAmount");

                        //pay by money transfer count instead of backdate
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'TR' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE,trsm.CHEQUENO), 0)  backDateTotalCount ");

                        //pay by money transfer(GFMIS) instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'GF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  transGfmisTotalAmount ");

                        //pay by money transfer(GFMIS) count instead of backdate
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'GF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0)  transGfmisTotalCount ");

                        //pay by money transfer(FOREIGN) instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  transForeignThTotalAmount ");

                        //pay by money transfer(FOREIGN) to bath instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT_BTH end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, round(c.AMOUNT / nvl(b.CURRENCYRATE,1)) AMOUNT_BTH, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT,b.CURRENCYRATE, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  transForeignTotalAmount ");

                        //pay by money transfer(FOREIGN) count instead of backdate
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0)  transForeignTotalCount ");

                        //pay by money transfer(FOREIGN)-USD instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%US%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) foreignUsToThTotalAmount ");

                        //pay by money transfer(FOREIGN)-USD to bath instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT_BTH end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, round(c.AMOUNT / nvl(b.CURRENCYRATE,1)) AMOUNT_BTH, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT,b.CURRENCYRATE, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF' and upper(trsm.CURRENCYCODE) like '%US%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) foreignUsTotalAmount ");

                        //pay by money transfer(FOREIGN)-USD count instead of backdate
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%US%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) foreignUsTotalCount ");


                        //pay by money transfer(FOREIGN)-EUR instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%EU%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0)  foreignEuTotalAmount ");

                        //pay by money transfer(FOREIGN)-EUR to bath instead of backdate
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT_BTH end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT AMOUNT, round(c.AMOUNT / nvl(b.CURRENCYRATE,1)) AMOUNT_BTH, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, c.AMOUNT,b.CURRENCYRATE, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF' and upper(trsm.CURRENCYCODE) like '%EU%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) foreignEuToThTotalAmount ");

                        //pay by money transfer(FOREIGN)-EUR count instead of backdate
            queryStr2.append(" , nvl((select sum(count(*)-sum(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.ref2 is null then 1 else 0 end)) from ( ");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.CODE, c.NAME, sum(c.AMOUNT) AMOUNT, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE, c.CHEQUENO ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" LEFT JOIN TRSMETHOD c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.CODE, c.NAME, b.CURRENCYCODE, b.PAYMENTTYPE, a.REF2, c.CHEQUENO) ");
            queryStr2.append(" trsm where trsm.CODE = 'TF'  and upper(trsm.CURRENCYCODE) like '%EU%' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE, trsm.CHEQUENO), 0) foreignEuTotalCount ");

                        //pay by wt 3tred
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" INNER JOIN TRSDEDUCTION c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.DEDUCTIONTYPE = '3TREDECIM' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalWt3tred ");
                        //pay by wt 69Bis
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" INNER JOIN TRSDEDUCTION c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.DEDUCTIONTYPE = '69BIS' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalWt69bis ");
                        //pay by wt 69Tre
            queryStr2.append(" , nvl((select sum(nvl(case when trsm.PAYMENTTYPE <> 'AGENT' then trsm.AMOUNT end,0))+sum(nvl(case when trsm.PAYMENTTYPE = 'AGENT' and trsm.REF2 = 'FEE-IN' then trsm.TOTALCHARGE end,0)) from (");
            queryStr2.append(" SELECT trunc(b.PAYMENTDATE) CLOSING_DATE, trunc(a.RECEIPTDTTM) RECEIPT_DATE, a.BRANCHAREA BRANCH_CODE, b.POSID MAC_NO, a.UPDATEUSER EMP_CODE ");
            queryStr2.append(" ,b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT AMOUNT, b.PAYMENTTYPE, a.REF2, sum(a.TOTALCHARGE) TOTALCHARGE ");
            queryStr2.append(" from CORRECEIPT a INNER JOIN CORPAYMENT b on a.PAYMENTID = b.PAYMENTID ");
            queryStr2.append(" INNER JOIN TRSDEDUCTION c on b.PAYMENTID = c.PAYMENTID WHERE a.ATTRIBUTES NOT LIKE '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null ");
            queryStr2.append(" GROUP BY trunc(a.RECEIPTDTTM), trunc(b.PAYMENTDATE), a.BRANCHAREA, b.POSID, a.UPDATEUSER, b.PAYMENTID, c.DEDUCTIONTYPE, c.AMOUNT, b.PAYMENTTYPE, a.REF2) ");
            queryStr2.append(" trsm where trsm.DEDUCTIONTYPE = '69TRE' AND trunc(trsm.CLOSING_DATE) = trunc(crp.PAYMENTDATE) and trunc(trsm.RECEIPT_DATE)=trunc(crc.RECEIPTDTTM) and trsm.RECEIPT_DATE < trunc(crp.PAYMENTDATE) ");
            queryStr2.append(" and trsm.BRANCH_CODE = crc.BRANCHAREA and trsm.MAC_NO = crp.POSID and  ( trsm.EMP_CODE = crc.UPDATEUSER )  ");
            queryStr2.append(" GROUP BY trsm.CLOSING_DATE, trsm.BRANCH_CODE, trsm.MAC_NO, trsm.EMP_CODE), 0) totalWt69tre ");
                        //cancel receipt amount
            queryStr2.append(" , nvl((select sum(nvl(case when cp1.PAYMENTTYPE <>'AGENT' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) + nvl(case when cp1.PAYMENTTYPE ='AGENT'and c1.REF2 = 'FEE-IN' THEN c1.TOTALCHARGE*nvl(cp1.CURRENCYRATE,1) end, 0) ) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) TOTAL_CANCEL_AMT ");
                        //cancel receipt count
            queryStr2.append(" , nvl((select count(*)-sum(case when cp1.PAYMENTTYPE = 'AGENT' and c1.ref2 is null then 1 else 0 end) from CORRECEIPT c1 inner join CORPAYMENT cp1 on c1.PAYMENTID = cp1.PAYMENTID ");
            queryStr2.append(" where trunc(cp1.PAYMENTDATE)=trunc(crp.PAYMENTDATE) and trunc(c1.RECEIPTDTTM)=trunc(crc.RECEIPTDTTM) and trunc(c1.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and c1.BRANCHAREA=crc.BRANCHAREA ");
            queryStr2.append(" and cp1.POSID = crp.POSID and ( c1.UPDATEUSER = crc.UPDATEUSER ) AND c1.ATTRIBUTES LIKE '%R%' and c1.SHOP_CLOSING_ID is null and c1.EMP_CLOSING_ID is null ");
            queryStr2.append(" and c1.RECEIPTNO NOT IN (select nvl(c2.REF_RECEIPTNO, 'XXX') from CORRECEIPT c2 where trunc(c2.RECEIPTDTTM)=trunc(c1.RECEIPTDTTM) GROUP BY c2.REF_RECEIPTNO) ");
            queryStr2.append(" GROUP BY trunc(c1.RECEIPTDTTM), c1.BRANCHAREA, c1.UPDATEUSER, cp1.POSID), 0) TOTAL_CANCEL_CNT ");
                        // total discount
            queryStr2.append(" , nvl((SELECT sum(nvl(a.DISCOUNT, 0)+nvl(a.EXC_DISCOUNT, 0)+nvl(a.AFTERSALEDISCOUNT, 0)+nvl(a.AFTERSALEDISC_VAT, 0)+nvl(a.SPECIAL_DISCOUNT, 0)) from CORRECEIPT a inner join CORPAYMENT b on a.PAYMENTID = b.PAYMENTID where a.BRANCHAREA = crc.BRANCHAREA and ( a.UPDATEUSER = crc.UPDATEUSER  ) ");
            queryStr2.append(" and trunc(b.PAYMENTDATE) = trunc(crp.PAYMENTDATE) and trunc(a.RECEIPTDTTM) = trunc(crc.RECEIPTDTTM) and trunc(a.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and a.ATTRIBUTES NOT LIKE  '%R%' and a.SHOP_CLOSING_ID is null and a.EMP_CLOSING_ID is null), 0) totalDiscount ");

            queryStr2.append(" FROM CORRECEIPT crc INNER JOIN CORPAYMENT crp on crc.PAYMENTID = crp.PAYMENTID ");
            queryStr2.append(" inner join MASPOS msp on crp.POSID = msp.POSID ");
            queryStr2.append(" inner join MASOFFICER mof on ( crp.UPDATEUSER = mof.USERNAME  ) ");// mof.OFFICERCODE ");
                        //            queryStr2.append(" INNER JOIN MASSHOP msh on crc.BRANCHAREA = msh.BUAREA ");
                        //queryStr.append(" WHERE crc.ATTRIBUTES NOT LIKE '%R%' ");
            queryStr2.append(" WHERE trunc(crc.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) and crc.SHOP_CLOSING_ID is null and crc.EMP_CLOSING_ID is null and trunc(crp.PAYMENTDATE) = ? ");
                        //queryStr.append(" GROUP BY trunc(crc.RECEIPTDTTM), crc.BRANCHAREA, crp.POSID, crc.UPDATEUSER ");
            queryStr2.append(" GROUP BY trunc(crp.PAYMENTDATE), trunc(crc.RECEIPTDTTM), crc.BRANCHAREA, crc.BRANCHCODE, crp.POSID, msp.POSNO , crc.UPDATEUSER, mof.OFFICERCODE ");

            String paramDateAsString2 = "2017-05-30";
            List<EndDayClosing> endDayClosings2 = new ArrayList<>();
            List<EndDayClosing> endDayClosingsList = new ArrayList<>();

            endDayClosings2 = episJdbcTemplate.query(queryStr2.toString(), new Object[]{calendar.getTime()}, BeanPropertyRowMapper.newInstance(EndDayClosing.class));//calendar.getTime() for testing textFormat.parse(paramDateAsString)
            endDayClosings.addAll(endDayClosings2);
            for (EndDayClosing dtl : endDayClosings) {
                List<EndDayClosing> tmpEndDayClosing = new ArrayList<>();
                List<EndDayClosing> tmpEndDayClosing2 = new ArrayList<>();
                dtl.setUpdateUser(userName);
                dtl.setUpdateDttm(new java.sql.Date(new Date().getTime()));
                Boolean flgEndClose = true;
                EndDayClosing endDayClosing = new EndDayClosing();
                /*if (dtl.getRefClosingId()!= null ) {
                    endDayClosing = endDayClosingRepository.findById(dtl.getRefClosingId());
                } else {
                    endDayClosing = null;
                }*/
                if(!StringUtils.isEmpty(dtl.getBackDateStatus())){
                    endDayClosing = endDayClosingRepository.findByEmpCodeAndMacNoAndBackDateStatusAndClosingDateAndReceiptDateAndDocStatusAndShopNoAndUserName(dtl.getEmpCode(), dtl.getMacNo(), dtl.getBackDateStatus(), dtl.getClosingDate(), dtl.getReceiptDate(), AppConstants.ENDDAY_CLOSE_STATUS_W, dtl.getShopNo(),dtl.getUserName());
                } else {
                    endDayClosing = endDayClosingRepository.findByEmpCodeAndMacNoAndClosingDateAndDocStatusAndShopNoAndUserNameAndBackDateStatus(dtl.getEmpCode(), dtl.getMacNo(), dtl.getClosingDate(), AppConstants.ENDDAY_CLOSE_STATUS_W, dtl.getShopNo(),dtl.getUserName(),null);
                }

                if (endDayClosing != null) {
                    if (StringUtils.equals(endDayClosing.getDocStatus(), AppConstants.ENDDAY_CLOSE_STATUS_W)) {
                        dtl.setId(endDayClosing.getId());
                        dtl.setCreateDttm(new java.sql.Date(new Date().getTime()));
                        dtl.setCreateUser(userName);
                        dtl.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_W);
                        endDayClosingRepository.save(dtl);
                    } else {
                        dtl.setId(endDayClosing.getId());
                    }
                } else {
                    dtl.setCreateDttm(new java.sql.Date(new Date().getTime()));
                    dtl.setCreateUser(userName);
                    dtl.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_W);
                    endDayClosingRepository.save(dtl);
                }
                String closingDate = date_format.format(dtl.getClosingDate());
                if (dtl.getBackDateStatus()!=null && dtl.getBackDateStatus().equalsIgnoreCase(AppConstants.ENDDAY_CLOSE_BACKDATE)) {
                // backdate
                    StringBuilder qryRctBdtStr = new StringBuilder();
                    qryRctBdtStr.append(" SELECT crc.RECEIPTID id, crc.RECEIPTNO no, crc.BRANCHAREA, crc.UPDATEUSER, crc.RECEIPTDTTM ");
                    qryRctBdtStr.append(" FROM CORRECEIPT crc INNER JOIN CORPAYMENT crp on crc.PAYMENTID = crp.PAYMENTID ");
                    qryRctBdtStr.append(" INNER JOIN MASPOS msp on crp.POSID = msp.POSID ");
                    //qryRctBdtStr.append(" INNER JOIN MASOFFICER mof on crp.UPDATEUSER = mof.USERNAME");// mof.OFFICERCODE ");
                    qryRctBdtStr.append(" INNER join MASOFFICER mof on ( crp.UPDATEUSER = mof.USERNAME  ) ");// mof.OFFICERCODE ");
                    qryRctBdtStr.append(" INNER JOIN MASSHOP msh on crc.BRANCHAREA = msh.BUAREA ");
                    qryRctBdtStr.append(" WHERE trunc(crc.RECEIPTDTTM)<trunc(crp.PAYMENTDATE) " +
                            " and crc.SHOP_CLOSING_ID is null and crc.EMP_CLOSING_ID is null " +
                            " and to_char(crp.PAYMENTDATE,'yyyy-MM-dd')  ='"+closingDate+"' ");
                    qryRctBdtStr.append(" and crc.BRANCHAREA = "+dtl.getBranchCode()+" and  ( crc.UPDATEUSER = '"+dtl.getUserName()+"'   ) ");
                    qryRctBdtStr.append(" and trunc(crc.RECEIPTDTTM) = trunc(to_date('"+dtl.getReceiptDate()+"','yyyy-mm-dd')) ");
                    qryRctBdtStr.append(" GROUP BY crc.RECEIPTID, crc.RECEIPTNO, crc.BRANCHAREA, crc.BRANCHCODE, crc.RECEIPTDTTM, crp.POSID, msp.POSNO , crc.UPDATEUSER, mof.OFFICERFAMILYNAME, mof.OFFICERGIVENNAME ");
                    List<Receipt> receiptBackDtList = new ArrayList<>();
                    receiptBackDtList = episJdbcTemplate.query(qryRctBdtStr.toString(), BeanPropertyRowMapper.newInstance(Receipt.class));

                    for (Receipt rct : receiptBackDtList) {
                        episJdbcTemplate.update("UPDATE CORRECEIPT "
                                + "SET CLOSING_ID = ? "
                                + "WHERE receiptid = ?", dtl.getId(), rct.getId() );
                    }
                } else {
                //normal

                    StringBuilder qryRctStr = new StringBuilder();
                    qryRctStr.append(" SELECT crc.RECEIPTID id, crc.RECEIPTNO no, crc.BRANCHAREA, crc.UPDATEUSER ");
                    qryRctStr.append(" FROM CORRECEIPT crc INNER JOIN CORPAYMENT crp on crc.PAYMENTID = crp.PAYMENTID ");
                    qryRctStr.append(" inner join MASPOS msp on crp.POSID = msp.POSID ");
                    //qryRctStr.append(" inner join MASOFFICER mof on crp.UPDATEUSER = mof.USERNAME");// mof.OFFICERCODE ");
                    qryRctStr.append(" INNER join MASOFFICER mof on ( crp.UPDATEUSER = mof.USERNAME  ) ");// mof.OFFICERCODE ");
                    qryRctStr.append(" INNER JOIN MASSHOP msh on crc.BRANCHAREA = msh.BUAREA ");
                    // qryRctStr.append(" WHERE trunc(crp.PAYMENTDATE) = trunc(to_date('"+dtl.getClosingDate()+"','yyyy-mm-dd'))  and trunc(crc.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and crc.SHOP_CLOSING_ID is null and crc.EMP_CLOSING_ID is null AND crc.ATTRIBUTES not like '%R%' ");
                    qryRctStr.append(" WHERE to_char(crp.PAYMENTDATE,'yyyy-MM-dd') = '"+closingDate+"'  " +
                            " and trunc(crc.RECEIPTDTTM) = trunc(crp.PAYMENTDATE) and crc.SHOP_CLOSING_ID is null and crc.EMP_CLOSING_ID is null ");
                    qryRctStr.append(" and crc.BRANCHAREA = "+dtl.getBranchCode()+" and  ( crc.UPDATEUSER = '"+dtl.getUserName()+"'   ) ");
                    qryRctStr.append(" GROUP BY crc.RECEIPTID, crc.RECEIPTNO, crc.BRANCHAREA, crc.BRANCHCODE,crp.POSID, msp.POSNO , crc.UPDATEUSER, mof.OFFICERFAMILYNAME, mof.OFFICERGIVENNAME ");
                    List<Receipt> receiptList = new ArrayList<>();
                    receiptList = episJdbcTemplate.query(qryRctStr.toString(), BeanPropertyRowMapper.newInstance(Receipt.class));

                    for (Receipt rct : receiptList) {
                        episJdbcTemplate.update("UPDATE CORRECEIPT "
                                + "SET CLOSING_ID = ? "
                                + "WHERE receiptid = ?", dtl.getId(), rct.getId() );
                    }

                }

            }
        }
        return null;
    }
}

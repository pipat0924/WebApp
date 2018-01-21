package th.net.cat.epis.service;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.cxf.common.i18n.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.PaidInvoiceDTO;
import th.net.cat.epis.dto.RevertInvProDto;
import th.net.cat.epis.entity.Document;
import th.net.cat.epis.entity.RevertPaymentInvoiceDtl;
import th.net.cat.epis.entity.RevertPaymentMst;
import th.net.cat.epis.entity.RevertPaymentProductDtl;
import th.net.cat.epis.repo.DocumentRepository;
import th.net.cat.epis.repo.RevertPaymentInvoiceDtlRepository;
import th.net.cat.epis.repo.RevertPaymentMstRepository;
import th.net.cat.epis.repo.RevertPaymentProductDtlRepository;
import th.net.cat.epis.to.report.RevertPaymentReqDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.leftPad;

/**
 * Created by Na Sanfeng on 10/26/2016.
 */

@Service
public class RevertPaymentService {

    @Resource(name = "episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;

    @Autowired
    RevertPaymentMstRepository revertPaymentMstRepository;
    @Autowired
    RevertPaymentInvoiceDtlRepository revertPaymentInvoiceDtlRepository;

    @Autowired
    RevertPaymentProductDtlRepository revertPaymentProductDtlRepository;
    @Autowired
    DocumentRepository documentRepo;


    public List<PaidInvoiceDTO> getPaidInvoiceList(String receiptNo, String invNo){
        List<PaidInvoiceDTO> invoiceDTOs = new ArrayList<PaidInvoiceDTO>();
        StringBuilder sql = new StringBuilder();
        List<Object> objectList = new ArrayList<Object>();
        sql.append(" select inv.INVOICENO, rpt.RECEIPTNO, rpt.ACCOUNTNO ");
        sql.append(" , rpt.RECEIPTDTTM, rpt.ACCOUNTNAME, rpt.PAYMENTCASE ");
        sql.append(" , rpt.TOTALCHARGE, rpt.UPDATEUSER, rpt.BRANCHNAME ");
        sql.append(" , inv.ISSUEDATE, inv.DUEDATE, inv.CHARGE ");
        sql.append(" , inv.DISCOUNT, inv.VAT, inv.BALANCEDUE ");
        sql.append(" , inv.DEDUCTION deduct, inv.BILLCYCLE, inv.RECEIVED ");
        sql.append(" , inv.DUEDATE duedateStr, rpt.ACCOUNTTYPE, rpt.ADDRESSLINE1 customerAddress ");
        sql.append(" , inv.ISSUEDATE issuedt ");
        sql.append(" from CORRECEIPT rpt left join TMPINVOICE inv on inv.RECEIPTID = rpt.RECEIPTID and inv.PAYMENTID = rpt.PAYMENTID ");
        sql.append(" where 1 = 1 and rpt.ATTRIBUTES NOT LIKE '%R' ");
        if(!StringUtils.isEmpty(receiptNo)){
            sql.append(" and rpt.RECEIPTNO like ? ");
            objectList.add('%'+receiptNo+'%');
        }
        if(!StringUtils.isEmpty(invNo)){
            sql.append(" and inv.INVOICENO like ? ");
            objectList.add('%'+invNo+'%');
        }
        invoiceDTOs = episJdbcTemplate.query(sql.toString(), objectList.toArray(), BeanPropertyRowMapper.newInstance(PaidInvoiceDTO.class));
        for(PaidInvoiceDTO dtl: invoiceDTOs){
            StringBuilder subSql = new StringBuilder().append(" SELECT BI_REF billRefNo, PRODUCT_CODE productCode, SUB_PRODUCT_CODE subProductCode ")
                    .append(" , REVENUE_TYPE_CODE revTypeCode, TOTAL_AMOUNT amount FROM INV_SUMMARY_SAP_IBACSS ")
                    .append(" WHERE CONTRNO = ? AND BI_REF=? ");
            List<RevertInvProDto> products = episJdbcTemplate.query(subSql.toString(), new Object[]{dtl.getAccountno(), dtl.getInvoiceno()}, BeanPropertyRowMapper.newInstance(RevertInvProDto.class));
            dtl.setProductList(products);
        }

        return invoiceDTOs;
    }

    @Transactional
    public RevertPaymentMst saveRevertPaymentRequest(RevertPaymentMst revertPaymentMst, List<RevertPaymentInvoiceDtl> invDtlList){

        RevertPaymentMst mst = revertPaymentMstRepository.save(revertPaymentMst);
        for(RevertPaymentInvoiceDtl dtl: invDtlList){
            RevertPaymentInvoiceDtl dtl1 = revertPaymentInvoiceDtlRepository.save(dtl);
            for(RevertPaymentProductDtl proDtl: dtl.getRevertPaymentProductDtls()){
                proDtl.setRevertInvDtlId(dtl1.getRevertInvDtlId());
                revertPaymentProductDtlRepository.save(proDtl);
            }
        }

        return mst;

    }

    public List<PaidInvoiceDTO> listReverPaymentReq(String reqRevNo, String receiptNo, String invoiceNo, String accountNo, String apprRevNo){
        List<PaidInvoiceDTO> invoiceDTOs = new ArrayList<PaidInvoiceDTO>();
        List<Object> args = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select mst.REVERT_REQ_NO, mst.RECEIPT_NO receiptno ");
        sql.append(" , mst.ACCOUNT_NO accountno, mst.ACCOUNT_NAME accountname, mst.PAYMENT_CASE paymentcase ");
        sql.append(" , mst.TOTAL_CHARGE, mst.REVERT_AMOUNT, mst.BRANCH_CODE, mst.BRANCH_NAME branchname ");
        sql.append(" , dtl.INVOICE_NO invoiceno, dtl.TOTAL_CHARGE totalcharge, dtl.REVERT_AMOUNT revertAmt ");
        sql.append(" , mst.REQ_REASON, to_char(mst.REQ_DTTM, 'DD/MM/YYYY HH24:MI:SS') reqDate ");
        sql.append(" , crpt.ADDRESSLINE1 customerAddress, crpt.TAXID taxId, mst.REQ_USER, crpt.ACCOUNTBRANCH customerBranch ");
        sql.append(" , dtl.RECEIVED_AMOUNT received, dtl.BILLCYCLE, mst.PAYMENT_CASE paymentcase ");
        sql.append(" , dtl.REVERT_INV_DTL_ID, case when mst.DOC_STATUS = 'W' THEN 'รออนุมัติ' else mst.DOC_STATUS END docStatus ");
        sql.append(" , to_char(crpt.RECEIPTDTTM, 'DD/MM/YYYY') receiptdttmStr, crpt.ACCOUNTTYPE ");
        sql.append(" , dtl.discount, dtl.deduct, dtl.balancedue, dtl.charge, dtl.vat ");
        sql.append(" , to_char(dtl.duedate, 'DD/MM/YYYY') duedateStr, to_char(dtl.issuedate, 'DD/MM/YYYY') issuedate ");
        sql.append(" , dtl.REVERT_INV_DTL_ID, mst.REVERT_APPR_NO, mst.APPR_REVERT_AMOUNT ");
        sql.append(" , dtl.APPR_REVERT_AMOUNT apprRevertAmt ");
        sql.append(" from REVERT_PAYMENT_MST mst left join REVERT_PAYMENT_INVOICE_DTL dtl ");
        sql.append(" on mst.REVERT_REQ_NO = dtl.REVERT_REQ_NO and mst.RECEIPT_NO = dtl.RECEIPT_NO ");
        sql.append(" inner join CORRECEIPT crpt on mst.RECEIPT_NO = crpt.RECEIPTNO ");
        sql.append(" where 1 = 1 and mst.DOC_STATUS='W' ");
        if(!StringUtils.isEmpty(reqRevNo)){
            sql.append(" and mst.REVERT_REQ_NO = ? ");
            args.add(reqRevNo);
        }
        if(!StringUtils.isEmpty(apprRevNo)){
            sql.append(" and mst.REVERT_APPR_NO = ? ");
            args.add(apprRevNo);
        }
        if(!StringUtils.isEmpty(receiptNo)){
            sql.append(" and mst.RECEIPT_NO = ? ");
            args.add(receiptNo);
        }
        if(!StringUtils.isEmpty(invoiceNo)){
            sql.append(" and dtl.INVOICE_NO = ? ");
            args.add(invoiceNo);
        }
        if(!StringUtils.isEmpty(accountNo)){
            sql.append(" and mst.ACCOUNT_NO = ? ");
            args.add(accountNo);
        }
        invoiceDTOs = episJdbcTemplate.query(sql.toString(), args.toArray(), BeanPropertyRowMapper.newInstance(PaidInvoiceDTO.class));
        for(PaidInvoiceDTO dtl: invoiceDTOs){
            List<RevertInvProDto> proDtos = episJdbcTemplate.query("select proDtl.PRODUCT_CODE, proDtl.PRODUCT_NAME, proDtl.SUB_PRODUCT_CODE , proDtl.SUB_PRODUCT_NAME, proDtl.REVENUE_TYPE_CODE revTypeCode, proDtl.TOTAL_AMOUNT amount, proDtl.REVERT_AMOUNT revertAmt, proDtl.REVERT_PRO_DTL_ID from REVERT_PAYMENT_PRODUCT_DTL proDtl where REVERT_INV_DTL_ID = ? ", new Object[]{dtl.getRevertInvDtlId()}, BeanPropertyRowMapper.newInstance(RevertInvProDto.class));
            for(RevertInvProDto proDto: proDtos){
                proDto.setApprRevertAmount(proDto.getRevertAmt());
            }
            dtl.setProductList(proDtos);
            dtl.setApprRevertAmt(dtl.getRevertAmt());
        }
        return invoiceDTOs;
    }

    public List<PaidInvoiceDTO> listReverPaymentReqAndAppr(String reqRevNo, String receiptNo, String invoiceNo, String accountNo, String apprRevNo){
        List<PaidInvoiceDTO> invoiceDTOs = new ArrayList<PaidInvoiceDTO>();
        List<Object> args = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select mst.REVERT_REQ_NO, mst.RECEIPT_NO receiptno ");
        sql.append(" , mst.ACCOUNT_NO accountno, mst.ACCOUNT_NAME accountname, mst.PAYMENT_CASE paymentcase ");
        sql.append(" , mst.TOTAL_CHARGE, mst.REVERT_AMOUNT, mst.BRANCH_CODE, mst.BRANCH_NAME branchname ");
        sql.append(" , dtl.INVOICE_NO invoiceno, dtl.TOTAL_CHARGE totalcharge, dtl.REVERT_AMOUNT revertAmt ");
        sql.append(" , mst.REQ_REASON, to_char(mst.REQ_DTTM, 'DD/MM/YYYY HH24:MI:SS') reqDate ");
        sql.append(" , crpt.ADDRESSLINE1 customerAddress, crpt.TAXID taxId, mst.REQ_USER, crpt.ACCOUNTBRANCH customerBranch ");
        sql.append(" , dtl.RECEIVED_AMOUNT received, dtl.BILLCYCLE, mst.PAYMENT_CASE paymentcase ");
        sql.append(" , dtl.REVERT_INV_DTL_ID, case when mst.DOC_STATUS = 'W' THEN 'รออนุมัติ' else mst.DOC_STATUS END docStatus ");
        sql.append(" , to_char(crpt.RECEIPTDTTM, 'DD/MM/YYYY') receiptdttmStr, crpt.ACCOUNTTYPE ");
        sql.append(" , dtl.discount, dtl.deduct, dtl.balancedue, dtl.charge, dtl.vat ");
        sql.append(" , to_char(dtl.duedate, 'DD/MM/YYYY') duedateStr, to_char(dtl.issuedate, 'DD/MM/YYYY') issuedate ");
        sql.append(" , dtl.REVERT_INV_DTL_ID, mst.REVERT_APPR_NO, mst.APPR_REVERT_AMOUNT ");
        sql.append(" , dtl.APPR_REVERT_AMOUNT apprRevertAmt, mst.APPR_USER ");
        sql.append(" from REVERT_PAYMENT_MST mst left join REVERT_PAYMENT_INVOICE_DTL dtl ");
        sql.append(" on mst.REVERT_REQ_NO = dtl.REVERT_REQ_NO and mst.RECEIPT_NO = dtl.RECEIPT_NO ");
        sql.append(" inner join CORRECEIPT crpt on mst.RECEIPT_NO = crpt.RECEIPTNO ");
        sql.append(" where 1 = 1 ");
        if(!StringUtils.isEmpty(reqRevNo)){
            sql.append(" and mst.REVERT_REQ_NO = ? ");
            args.add(reqRevNo);
        }
        if(!StringUtils.isEmpty(apprRevNo)){
            sql.append(" and mst.REVERT_APPR_NO = ? ");
            args.add(apprRevNo);
        }
        if(!StringUtils.isEmpty(receiptNo)){
            sql.append(" and mst.RECEIPT_NO = ? ");
            args.add(receiptNo);
        }
        if(!StringUtils.isEmpty(invoiceNo)){
            sql.append(" and dtl.INVOICE_NO = ? ");
            args.add(invoiceNo);
        }
        if(!StringUtils.isEmpty(accountNo)){
            sql.append(" and mst.ACCOUNT_NO = ? ");
            args.add(accountNo);
        }
        invoiceDTOs = episJdbcTemplate.query(sql.toString(), args.toArray(), BeanPropertyRowMapper.newInstance(PaidInvoiceDTO.class));
        for(PaidInvoiceDTO dtl: invoiceDTOs){
            List<RevertInvProDto> proDtos = episJdbcTemplate.query("select proDtl.PRODUCT_CODE, proDtl.PRODUCT_NAME, proDtl.SUB_PRODUCT_CODE , proDtl.SUB_PRODUCT_NAME, proDtl.REVENUE_TYPE_CODE revTypeCode, proDtl.TOTAL_AMOUNT amount, proDtl.REVERT_AMOUNT revertAmt, proDtl.REVERT_PRO_DTL_ID, proDtl.APPR_REVERT_AMOUNT from REVERT_PAYMENT_PRODUCT_DTL proDtl where REVERT_INV_DTL_ID = ? ", new Object[]{dtl.getRevertInvDtlId()}, BeanPropertyRowMapper.newInstance(RevertInvProDto.class));
            dtl.setProductList(proDtos);
            //dtl.setApprRevertAmt(dtl.getRevertAmt());
        }
        return invoiceDTOs;
    }

    @Transactional
    public RevertPaymentMst saveUpdateRevertPaymentRequest(RevertPaymentMst revertPaymentMst, List<RevertPaymentInvoiceDtl> invDtlList){

        RevertPaymentMst mst = revertPaymentMstRepository.save(revertPaymentMst);
        for(RevertPaymentInvoiceDtl dtl: invDtlList){
            RevertPaymentInvoiceDtl dtl1 = revertPaymentInvoiceDtlRepository.save(dtl);
            for(RevertPaymentProductDtl proDtl: dtl.getRevertPaymentProductDtls()){
                revertPaymentProductDtlRepository.save(proDtl);
            }
        }

        return mst;

    }


}

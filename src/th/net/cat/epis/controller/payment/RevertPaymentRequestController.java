package th.net.cat.epis.controller.payment;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.PaidInvoiceDTO;
import th.net.cat.epis.dto.RevertInvProDto;
import th.net.cat.epis.entity.RevertPaymentInvoiceDtl;
import th.net.cat.epis.entity.RevertPaymentMst;
import th.net.cat.epis.entity.RevertPaymentPK;
import th.net.cat.epis.entity.RevertPaymentProductDtl;
import th.net.cat.epis.service.KeyGeneratorService;
import th.net.cat.epis.service.RevertPaymentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Na Sanfeng on 10/19/2016.
 */
@Controller
public class RevertPaymentRequestController {
    @Resource(name = "episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;
    @Autowired RevertPaymentService revertPaymentService;
    @Autowired
    KeyGeneratorService keyGeneratorService;
    public final DecimalFormat formatter_PRICE = new DecimalFormat("###,##0.##");
    public final String STANDARD_DATE_PATTERN = "dd/MM/yyyy";
    public final Locale ENG_LOCALE = new Locale("en", "US");

    @ResponseBody
    @RequestMapping(value = "/revert-payment-request.json", method = RequestMethod.GET)
    private List<PaidInvoiceDTO> accessRevertPaymentPage(
        HttpServletRequest request, HttpServletResponse response
        ,@RequestParam(name = "receiptNo") String receiptNo
        ,@RequestParam(name = "invNo") String invNo
    ){
        String reqUser = EpContextHolder.getContext().getUserCode();
        List<PaidInvoiceDTO> invoiceDTOs = new ArrayList<PaidInvoiceDTO>();
        invoiceDTOs = revertPaymentService.getPaidInvoiceList(receiptNo, invNo);
        for(PaidInvoiceDTO dtl: invoiceDTOs){
            dtl.setReceiptdttmStr(convertToString(dtl.getReceiptdttm(), STANDARD_DATE_PATTERN, ENG_LOCALE));
            dtl.setTotalchargeStr(toCurrencyString(dtl.getTotalcharge()));
            dtl.setReqUser(reqUser);
        }
        return invoiceDTOs;
    }

    /*public List<PaidInvoiceDTO> getPaidInvoiceList(String receiptNo){
        List<PaidInvoiceDTO> invoiceDTOs = new ArrayList<PaidInvoiceDTO>();
        StringBuilder sql = new StringBuilder();
        List<Object> objectList = new ArrayList<Object>();
        sql.append(" select inv.INVOICENO, rpt.RECEIPTNO, rpt.ACCOUNTNO ");
        sql.append(" , rpt.RECEIPTDTTM, rpt.ACCOUNTNAME, rpt.PAYMENTCASE ");
        sql.append(" , rpt.TOTALCHARGE, rpt.UPDATEUSER, rpt.BRANCHNAME ");
        sql.append(" from TMPINVOICE inv, CORRECEIPT rpt ");
        sql.append(" where inv.RECEIPTID = rpt.RECEIPTID ");
        if(!StringUtils.isEmpty(receiptNo)){
            sql.append(" and rpt.RECEIPTNO = ? ");
            objectList.add(receiptNo);
        }
        invoiceDTOs = episJdbcTemplate.query(sql.toString(), objectList.toArray(), BeanPropertyRowMapper.newInstance(PaidInvoiceDTO.class));
        for(PaidInvoiceDTO dtl: invoiceDTOs){
            dtl.setReceiptdttmStr(convertToString(dtl.getReceiptdttm(), STANDARD_DATE_PATTERN, ENG_LOCALE));
            dtl.setTotalchargeStr(toCurrencyString(dtl.getTotalcharge()));
        }
        return invoiceDTOs;
    }*/

    @ResponseBody
    @RequestMapping(value = "/save-revert-payment_req.json", method = RequestMethod.POST)
    private List<PaidInvoiceDTO> saveRevertPaymentRequest(HttpServletRequest request, HttpServletResponse response
                                          , @RequestBody List<PaidInvoiceDTO> invoiceDTOList
    ){

        RevertPaymentMst mst = new RevertPaymentMst();
        RevertPaymentPK mstPk = new RevertPaymentPK();
        List<RevertPaymentInvoiceDtl> invoiceDtlList = new ArrayList<RevertPaymentInvoiceDtl>();
        String posNo = EpContextHolder.getContext().getPosNo();
        String branchCode = EpContextHolder.getContext().getBranchArea();
        String reqUser = EpContextHolder.getContext().getUserCode();
        DateFormat dateSoure = new SimpleDateFormat("dd/MM/yyyy");
        String docCode = "RVRQ";
        String reqNo = keyGeneratorService.getDocumentNo(posNo, branchCode, docCode, null);
        mstPk.setRevertReqNo(reqNo);

        mstPk.setReceiptNo(invoiceDTOList.get(0).getReceiptno());
        mst.setRevertPaymentPK(mstPk);
        mst.setAccountNo(invoiceDTOList.get(0).getAccountno());
        mst.setAccountName(invoiceDTOList.get(0).getAccountname());
        mst.setPaymentCase(invoiceDTOList.get(0).getPaymentcase());
        mst.setDocStatus("W");
        BigDecimal ttRevertAmt = new BigDecimal(0);
        for(PaidInvoiceDTO dtl: invoiceDTOList){
            RevertPaymentInvoiceDtl invDtl = new RevertPaymentInvoiceDtl();
            invDtl.setReceiptNo(mst.getRevertPaymentPK().getReceiptNo());
            invDtl.setRevertReqNo(mst.getRevertPaymentPK().getRevertReqNo());
            invDtl.setInvoiceNo(dtl.getInvoiceno());
            invDtl.setTotalCharge(dtl.getTotalcharge());
            invDtl.setRevertAmount(dtl.getRevertAmt());
            invDtl.setBillcycle(dtl.getBillcycle());
            invDtl.setReceivedAmount(dtl.getReceived());

            invDtl.setDiscount(dtl.getDiscount());
            invDtl.setDeduct(dtl.getDeduct());
            invDtl.setBalancedue(dtl.getBalancedue());
            invDtl.setCharge(dtl.getCharge());
            invDtl.setVat(dtl.getVat());
            //update 15-12-2016
                invDtl.setIssuedate(dtl.getIssuedt());
                invDtl.setDuedate(dtl.getDuedate());


            List<RevertPaymentProductDtl> productDtlList = new ArrayList<RevertPaymentProductDtl>();
            for(RevertInvProDto proDtl: dtl.getProductList()){
                RevertPaymentProductDtl prdtl = new RevertPaymentProductDtl();
                prdtl.setProductCode(proDtl.getProductCode());
                prdtl.setProductName(proDtl.getProductName());
                prdtl.setSubProductCode(proDtl.getSubProductCode());
                prdtl.setSubProductName(proDtl.getSubProductName());
                prdtl.setRevenueTypeCode(proDtl.getRevTypeCode());
                prdtl.setTotalAmount(proDtl.getAmount());
                prdtl.setRevertAmount(proDtl.getRevertAmt());

                productDtlList.add(prdtl);

            }
            invDtl.setRevertPaymentProductDtls(productDtlList);
            invoiceDtlList.add(invDtl);
            ttRevertAmt = ttRevertAmt.add(dtl.getRevertAmt());

            // for temporary response
            dtl.setDocStatus("รออนุมัติ");
            dtl.setRevertReqNo(reqNo);
        }
        mst.setRevertAmount(ttRevertAmt);
        mst.setReqReason(invoiceDTOList.get(0).getReqReason());
        mst.setBranchName(invoiceDTOList.get(0).getBranchname());
        mst.setReqDttm(new Date());
        mst.setBranchCode(branchCode);
        mst.setReqUser(reqUser);

        List<PaidInvoiceDTO> invoiceDTORes = new ArrayList<PaidInvoiceDTO>();
        RevertPaymentMst mstRes = revertPaymentService.saveRevertPaymentRequest(mst, invoiceDtlList);
        if(mstRes!=null){
            invoiceDTORes = revertPaymentService.listReverPaymentReq(mstRes.getRevertPaymentPK().getRevertReqNo(), "", "", "", "");
        }
        return invoiceDTORes;
    }

    public String toCurrencyString(BigDecimal value) {
        if (value == null) return "";
        return formatter_PRICE.format(value);
    }

    public String convertToString(Date date, String pattern, Locale locale) {
        String convertResult = null;
        if(date != null && org.apache.commons.lang.StringUtils.isNotBlank(pattern)) {
            Calendar calendar = locale == null ? Calendar.getInstance() : Calendar.getInstance(locale);
            calendar.setTime(date);
            convertResult = convertToString0(calendar, pattern);
        }
        return convertResult;
    }
    public static String convertToString0(Calendar calendar, String pattern) {
        return calendar != null && org.apache.commons.lang.StringUtils.isNotBlank(pattern) ? FastDateFormat.getInstance(pattern, Locale.getDefault()).format(calendar) : null;
    }

}

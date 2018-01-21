package th.net.cat.epis.controller.payment;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.PaidInvoiceDTO;
import th.net.cat.epis.dto.RevertInvProDto;
import th.net.cat.epis.entity.RevertPaymentInvoiceDtl;
import th.net.cat.epis.entity.RevertPaymentMst;
import th.net.cat.epis.entity.RevertPaymentPK;
import th.net.cat.epis.entity.RevertPaymentProductDtl;
import th.net.cat.epis.repo.RevertPaymentInvoiceDtlRepository;
import th.net.cat.epis.repo.RevertPaymentMstRepository;
import th.net.cat.epis.repo.RevertPaymentProductDtlRepository;
import th.net.cat.epis.service.KeyGeneratorService;
import th.net.cat.epis.service.RevertPaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nastanda on 12/9/2016 AD.
 */
@Controller
public class RevertPaymentApproveController {
    @Autowired
    RevertPaymentService revertPaymentService;
    @Autowired
    KeyGeneratorService keyGeneratorService;
    @Autowired
    RevertPaymentMstRepository revertPaymentMstRepository;
    @Autowired
    RevertPaymentInvoiceDtlRepository revertPaymentInvoiceDtlRepository;
    @Autowired
    RevertPaymentProductDtlRepository revertPaymentProductDtlRepository;

    @ResponseBody
    @RequestMapping(value = "/revert-payment-req-list.json", method = RequestMethod.GET)
    public List<PaidInvoiceDTO> listPaymentRequest(HttpServletRequest request, HttpServletResponse response
            ,@RequestParam(name = "receiptNo") String receiptNo
            ,@RequestParam(name = "invNo") String invNo
            ,@RequestParam(name = "accountNo") String accountNo
            ,@RequestParam(name = "reqRevNo") String reqRevNo
    ){
        List<PaidInvoiceDTO> rsList = new ArrayList<PaidInvoiceDTO>();
        rsList = revertPaymentService.listReverPaymentReq(reqRevNo, receiptNo, invNo, accountNo, "");
        return rsList;
    }

    @ResponseBody
    @RequestMapping(value = "/save-revert-payment_appr.json", method = RequestMethod.POST)
    private List<PaidInvoiceDTO> saveRevertPaymentRequest(HttpServletRequest request, HttpServletResponse response
            , @RequestBody List<PaidInvoiceDTO> invoiceDTOList
    ){

        RevertPaymentMst mst = new RevertPaymentMst();
        RevertPaymentPK mstPk = new RevertPaymentPK();
        List<RevertPaymentInvoiceDtl> invoiceDtlList = new ArrayList<RevertPaymentInvoiceDtl>();
        String posNo = EpContextHolder.getContext().getPosNo();
        String branchCode = EpContextHolder.getContext().getBranchArea();
        String apprUser = EpContextHolder.getContext().getUserCode();
        DateFormat dateSoure = new SimpleDateFormat("dd/MM/yyyy");
        String docCode = "RVAP";
        String apprNo = keyGeneratorService.getDocumentNo(posNo, branchCode, docCode, null);
        Boolean flgAppr = true;

        mstPk.setRevertReqNo(invoiceDTOList.get(0).getRevertReqNo());
        mstPk.setReceiptNo(invoiceDTOList.get(0).getReceiptno());

        mst = revertPaymentMstRepository.findByRevertPaymentPK(mstPk);
        mst.setRevertApprNo(apprNo);
        mst.setApprUser(apprUser);
        mst.setApprDttm(new Date());

        //mst.setRevertPaymentPK(mstPk);
        //mst.setAccountNo(invoiceDTOList.get(0).getAccountno());
        //mst.setAccountName(invoiceDTOList.get(0).getAccountname());
        //mst.setPaymentCase(invoiceDTOList.get(0).getPaymentcase());
        mst.setDocStatus(invoiceDTOList.get(0).getDocStatus());
        if(!StringUtils.equals(invoiceDTOList.get(0).getDocStatus(), "Y")){
            flgAppr = false;
        }else {
            flgAppr = true;
        }

        BigDecimal ttRevertAmt = new BigDecimal(0);
        BigDecimal ttRevertAmtAppr = new BigDecimal(0);

        for(PaidInvoiceDTO dtl: invoiceDTOList){
            RevertPaymentInvoiceDtl invDtl = new RevertPaymentInvoiceDtl();

            invDtl = revertPaymentInvoiceDtlRepository.findByRevertInvDtlId(dtl.getRevertInvDtlId());

            if(flgAppr){
                invDtl.setApprRevertAmount(dtl.getApprRevertAmt());
            }else{
                invDtl.setApprRevertAmount(new BigDecimal(0));
            }


            List<RevertPaymentProductDtl> productDtlList = new ArrayList<RevertPaymentProductDtl>();
            for(RevertInvProDto proDtl: dtl.getProductList()){
                RevertPaymentProductDtl prdtl = new RevertPaymentProductDtl();
                prdtl = revertPaymentProductDtlRepository.findByRevertProDtlId(proDtl.getRevertProDtlId());
                if(flgAppr){
                    prdtl.setApprRevertAmount(proDtl.getApprRevertAmount());
                }else{
                    prdtl.setApprRevertAmount(new BigDecimal(0));
                }

                /*prdtl.setProductCode(proDtl.getProductCode());
                prdtl.setProductName(proDtl.getProductName());
                prdtl.setSubProductCode(proDtl.getSubProductCode());
                prdtl.setSubProductName(proDtl.getSubProductName());
                prdtl.setRevenueTypeCode(proDtl.getRevTypeCode());
                prdtl.setTotalAmount(proDtl.getAmount());
                prdtl.setRevertAmount(proDtl.getRevertAmt());*/

                productDtlList.add(prdtl);

            }
            invDtl.setRevertPaymentProductDtls(productDtlList);
            invoiceDtlList.add(invDtl);
            ttRevertAmt = ttRevertAmt.add(dtl.getRevertAmt());
            ttRevertAmtAppr = ttRevertAmtAppr.add(dtl.getApprRevertAmt());

            // for temporary response
            //dtl.setDocStatus("รออนุมัติ");
            //dtl.setRevertReqNo(reqNo);

        }
        //mst.setRevertAmount(ttRevertAmt);
        if(flgAppr){
            mst.setApprRevertAmount(ttRevertAmtAppr);
        }

        //mst.setReqReason(invoiceDTOList.get(0).getReqReason());
        mst.setApprReason(invoiceDTOList.get(0).getApprReason());//haven't got yet
        //mst.setBranchName(invoiceDTOList.get(0).getBranchname());
        //mst.setReqDttm(new Date());
        //mst.setBranchCode(branchCode);
        //mst.setReqUser(reqUser);

        List<PaidInvoiceDTO> invoiceDTORes = new ArrayList<PaidInvoiceDTO>();
        RevertPaymentMst mstRes = revertPaymentService.saveUpdateRevertPaymentRequest(mst, invoiceDtlList);
        if(mstRes!=null){
            invoiceDTORes = revertPaymentService.listReverPaymentReqAndAppr(mstRes.getRevertPaymentPK().getRevertReqNo(), "", "", "", "");
        }
        return invoiceDTORes;
    }
}

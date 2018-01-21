package th.net.cat.epis.controller.payment;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import th.net.cat.epis.dto.PaidInvoiceDTO;
import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.repo.OfficerRepository;
import th.net.cat.epis.service.RevertPaymentService;
import th.net.cat.epis.to.report.RevertPaymentReqDTO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static th.net.cat.epis.util.AppConstants.FILE_TYPE_JRXML;
import static th.net.cat.epis.util.AppConstants.JASPER_JRXML_PATH;

/**
 * Created by nastanda on 12/2/2016 AD.
 */
@Controller
public class RevertPaymentPDFController implements ServletContextAware{
    private ServletContext context;
    @Autowired
    RevertPaymentService revertPaymentService;
    @Autowired
    OfficerRepository officerRepo;
    @Autowired RevertPaymentRequestController revertPaymentRequestController;
    public final DecimalFormat formatter_PRICE = new DecimalFormat("###,##0.##");

    @RequestMapping(value="/printRevertPaymentRQ.pdf")
    public void printPaymentReceipt(HttpServletRequest request, HttpServletResponse response//, PrintReceiptDTO printReceiptDTO
                                    , @RequestParam(name = "reqRevNo") String reqRevNo
    ) throws Exception {

        String JASPER_JRXML_FILENAME = getJasperFileName();
        //String REPORT_OUTPUT_FILENAME = request.getUserPrincipal().getName()+"-EpisPaymentReceipt";

        Map<String, Object> parameters = new HashMap<String, Object>();
        Officer officer = null;
        List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
        List<RevertPaymentReqDTO> collections = new ArrayList<RevertPaymentReqDTO>();
        collections = listRevertPaymentReqDTOs(reqRevNo);
        RevertPaymentReqDTO documentObject = new RevertPaymentReqDTO();
        documentObject = collections.get(0);
        officer = officerRepo.findByCode(documentObject.getReqUser());
        documentObject.setReqUser(officer.getGivenName() + " " + officer.getFamilyName());
        parameters.put("ReportSource", documentObject);
        response.setContentType("application/pdf");

        net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
        JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();

        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
        pushReportToOutputStream(response, jasperPrints);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    private String getJasperFileName(){
        String JASPER_JRXML_FILENAME = "EpisRevertPayment_RQ";//"";
        return JASPER_JRXML_FILENAME;
    }

    private void pushReportToOutputStream(HttpServletResponse response, List<JasperPrint> jasperPrints) throws IOException, JRException  {
        OutputStream outputStream = response.getOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    private List<RevertPaymentReqDTO> listRevertPaymentReqDTOs(String reqRevertNo){
        List<RevertPaymentReqDTO> rsList = new ArrayList<RevertPaymentReqDTO>();
        List<PaidInvoiceDTO> invDtls = new ArrayList<PaidInvoiceDTO>();
        invDtls =  revertPaymentService.listReverPaymentReqAndAppr(reqRevertNo, "", "", "", "");
        for(PaidInvoiceDTO dtl: invDtls){
            RevertPaymentReqDTO dtl1 = new RevertPaymentReqDTO();
            dtl1.setRevertReqNo(dtl.getRevertReqNo());
            dtl1.setAccountNo(dtl.getAccountno());
            dtl1.setAccountName(dtl.getAccountname());
            dtl1.setInvoiceNo(dtl.getInvoiceno());
            dtl1.setRevertAmount(dtl.getRevertAmt().setScale(2).toString());
            if(dtl.getApprRevertAmt()!=null){
                dtl1.setApprRevertAmount(dtl.getApprRevertAmt().setScale(2).toString());
                dtl1.setTtApprRevertAmount(dtl.getApprRevertAmount().setScale(2).toString());
                dtl1.setRevertApprNo(dtl.getRevertApprNo());
            }

            dtl1.setTtRevertAmount(dtl.getRevertAmount().setScale(2).toString());
            dtl1.setReqDate(dtl.getReqDate());
            dtl1.setCustomerAddress(dtl.getCustomerAddress());
            dtl1.setTaxId(dtl.getTaxId());
            dtl1.setReqReason(dtl.getReqReason());
            dtl1.setReqUser(dtl.getReqUser());
            dtl1.setBranchName(calculateBranchName(dtl.getBranchCode(), dtl.getBranchname()));
            if(dtl.getReceived()!=null) {
                dtl1.setTotalCharge(dtl.getReceived().setScale(2).toString());
            }
            dtl1.setBillcycle(dtl.getBillcycle());
            dtl1.setApprUser(dtl.getApprUser());
            dtl1.setCustomerBranch(dtl.getCustomerBranch());
            dtl1.setReceiptNo(dtl.getReceiptno());
            rsList.add(dtl1);

        }
        return rsList;
    }

    @RequestMapping(value="/printRevertPaymentAP.pdf")
    public void printRevertPaymentApprove(HttpServletRequest request, HttpServletResponse response//, PrintReceiptDTO printReceiptDTO
            , @RequestParam(name = "reqRevNo") String reqRevNo
    ) throws Exception {

        String JASPER_JRXML_FILENAME = "EpisRevertPayment_AP";

        Map<String, Object> parameters = new HashMap<String, Object>();
        Officer officer = null;
        List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
        List<RevertPaymentReqDTO> collections = new ArrayList<RevertPaymentReqDTO>();
        collections = listRevertPaymentReqDTOs(reqRevNo);
        RevertPaymentReqDTO documentObject = new RevertPaymentReqDTO();
        documentObject = collections.get(0);
        officer = officerRepo.findByCode(documentObject.getApprUser());
        documentObject.setApprUser(officer.getGivenName() + " " + officer.getFamilyName());
        parameters.put("ReportSource", documentObject);
        response.setContentType("application/pdf");

        net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport(context.getRealPath(JASPER_JRXML_PATH) + File.separatorChar + JASPER_JRXML_FILENAME + FILE_TYPE_JRXML);
        JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();

        jasperPrints.add(JasperFillManager.fillReport(report, parameters, dataSource));
        pushReportToOutputStream(response, jasperPrints);
    }

    private String calculateBranchName(String branchCode, String branchName) {
        return (StringUtils.equals(branchCode, "00000"))
                ? "สำนักงานใหญ่ "+branchCode+" "+branchName
                : "สาขาที่ออกใบขอคืนเงิน คือ "+ branchCode+" "+branchName;
    }

}

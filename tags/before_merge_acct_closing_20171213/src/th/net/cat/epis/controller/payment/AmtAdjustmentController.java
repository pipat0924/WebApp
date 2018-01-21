package th.net.cat.epis.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;

import th.net.cat.epis.dto.AmountAdjustmentDTO;
import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.dto.AmountAdjustmentSummaryDTO;
import th.net.cat.epis.entity.AmountAdjustment;
import th.net.cat.epis.entity.AmountAdjustmentDetail;
import th.net.cat.epis.entity.AmountAdjustmentHeader;
import th.net.cat.epis.service.AmtAdjustmentService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

@Controller
@SessionAttributes(value={ "service_key", "auth_token" })
public class AmtAdjustmentController implements ServletContextAware{
	private static Logger logger = Logger.getLogger(AmtAdjustmentController.class);
	@Autowired AmtAdjustmentService amtAdjustmentService;

	@Autowired
	ServletContext context; 
	
	@ResponseBody
	@RequestMapping(value="createAmountAdjustment.json", method=RequestMethod.POST)
	public CommonStatus<AmountAdjustmentDTO> createAmountAdjustment(AmountAdjustmentSummaryDTO gd) throws Exception {
		logger.info("createAmountAdjustment...Start");
		
		CommonStatus<AmountAdjustmentDTO> csRes = new CommonStatus<AmountAdjustmentDTO>();
		List<AmountAdjustment> aal = new ArrayList<AmountAdjustment>();
		List<AmountAdjustmentDetail> aadl = new ArrayList<AmountAdjustmentDetail>();
		AmountAdjustmentHeader aah = new AmountAdjustmentHeader();
		mapAmountAdjustment(aah, aal, aadl, gd);
		
		logger.info("AdjustAmount List Size >> "+aal.size()+" , AdjustAmountDetail List Size >> "+aadl.size());
		if(aal != null && aal.size() > 0){
			//Create adjust amount
			aah = amtAdjustmentService.createAmountAdjustment(aah,aal,aadl);
		}
		
		csRes.setData(amtAdjustmentService.getAmountAdjustGeneralList(aah));
		Map<String,String> param = new HashMap<String,String>();
		param.put("id", aah.getId()+"");
		csRes.setParam(param);
		csRes.setStatusCode("0");
		
		logger.info("createAmountAdjustment...Done");
		return csRes;
	}
	
	@ResponseBody
	@RequestMapping(value="createAmountAdjustmentReport.json", method = {RequestMethod.POST, RequestMethod.GET})//,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE
	public void createAmountAdjustmentReport(HttpServletRequest request, HttpServletResponse response,AmountAdjustmentSummaryDTO gd) throws Exception {
		logger.info("createAmountAdjustmentReport...Start");
		amtAdjustmentService.createAmountAdjustmentReport(context,request, response, gd);
		logger.info("createAmountAdjustmentReport...Done");
	}

	@ResponseBody
	@RequestMapping(value="createAmountAdjustmentReportApv.json", method = {RequestMethod.POST, RequestMethod.GET})//,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE
	public void createAmountAdjustmentReportApv(HttpServletRequest request, HttpServletResponse response,AmountAdjustmentSummaryDTO gd) throws Exception {
		logger.info("createAmountAdjustmentReport...Start");
		amtAdjustmentService.createAmountAdjustmentReportApv(context,request, response, gd);
		logger.info("createAmountAdjustmentReport...Done");
	}
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.context = sc;
	}
	
	@ResponseBody
	@RequestMapping(value="findInvoiceForAdjustAmountList.json", method={RequestMethod.POST, RequestMethod.GET})
	public CommonStatus<Map<String,Object>> findInvoiceForAdjustAmountList(@RequestParam("invNo") String  invNo) throws Exception {
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		dataRes.setData(amtAdjustmentService.findInvoiceForAdjustAmountList(invNo));
		return dataRes;
	}
	
	/*@ResponseBody
	@RequestMapping(value="findExistReceiptAdjustAmountList.json", method = {RequestMethod.POST, RequestMethod.GET})
	public CommonStatus<Map<String,Object>> findExistReceiptAdjustAmountList(@RequestParam("invNo") String  invNo) throws Exception {
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		dataRes.setData(amtAdjustmentService.findExistReceiptAdjustAmountList(invNo));
		return dataRes;
	}*/
	
	@ResponseBody
	@RequestMapping(value="findInvoiceReceiptAdjustAmountList.json", method = {RequestMethod.POST, RequestMethod.GET})
	public Object findInvoiceReceiptAdjustAmountList(@RequestParam("invNo") String  invNo,@Param("no") String no, @Param("payType") String payType, Pageable p) throws Exception {
		CommonStatus<Object> dataRes = new CommonStatus<Object>();
		dataRes.setData(amtAdjustmentService.findInvoiceReceiptAdjustAmountList(invNo, no, payType, p));
		return dataRes;
	}
	
	@ResponseBody
	@RequestMapping(value="findAdjustAmountList.json", method={RequestMethod.POST, RequestMethod.GET})
	public CommonStatus<Map<String,Object>> findAdjustAmountList(@RequestParam("invoiceNo") String  invoiceNo, @RequestParam("receiptNo") String  receiptNo, @RequestParam("accountNo") String  accountNo, @RequestParam("amountAdjustHeaderNo") String  amountAdjustHeaderNo) throws Exception {
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		dataRes.setData(amtAdjustmentService.findAdjustAmountList(invoiceNo, receiptNo, accountNo, amountAdjustHeaderNo));
		return dataRes;
	}
	
	@ResponseBody
	@RequestMapping(value="findAdjustAmountEditList.json", method={RequestMethod.POST, RequestMethod.GET})
	public CommonStatus<Map<String,Object>> findAdjustAmountInvoiceList(AmountAdjustmentSummaryDTO gd) throws Exception {
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		logger.info("findAdjustAmountInvoiceList...Start");
		
		dataRes.setData(amtAdjustmentService.findInvoiceForAdjustAmountList(gd));
		
		logger.info("findAdjustAmountInvoiceList...Done");
		return dataRes;
	}
	
	@ResponseBody
	@RequestMapping(value="findAdjustAmountDetailList.json", method={RequestMethod.POST, RequestMethod.GET})
	public CommonStatus<Map<String,Object>> findAdjustAmountDetailList(@RequestParam("adjAmtId") String  adjAmtId) throws Exception {
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		logger.info("findAdjustAmountDetailList...Start");
		
		dataRes.setData(amtAdjustmentService.findAdjustAmountDetailList(adjAmtId));
		
		logger.info("findAdjustAmountDetailList...Done");
		return dataRes;
	}
	
	@ResponseBody
	@RequestMapping(value="approveAmountAdjustment.json", method=RequestMethod.POST)
	public CommonStatus<Map<String,Object>> approveAmountAdjustment(AmountAdjustmentSummaryDTO gd) throws Exception {
		logger.info("approveAmountAdjustment...Start");
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		
		List<AmountAdjustment> aal = new ArrayList<AmountAdjustment>();
		List<AmountAdjustmentDetail> aadl = new ArrayList<AmountAdjustmentDetail>();
		AmountAdjustmentHeader aah = new AmountAdjustmentHeader();
		//Map data from screen
		mapAmountAdjustment(aah, aal, aadl, gd);
		
		//Approve data
		amtAdjustmentService.approveAdjustAmount(aah, aal, aadl);
		
		logger.info("approveAmountAdjustment...End");
		return dataRes;
	}
	
	//Map data from screen
	public void mapAmountAdjustment(AmountAdjustmentHeader aah,List<AmountAdjustment> aal , List<AmountAdjustmentDetail> aadl ,AmountAdjustmentSummaryDTO gd) throws IllegalAccessException, InvocationTargetException{
		for (Map<String, String> aa: gd.getRecords()){
			AmountAdjustment aae = new AmountAdjustment();
			BeanUtils.populate(aae, aa);
			aal.add(aae);
			logger.info("Bean ApproveAmount : "+aae.getApproveAmount() );
		}
		
		BeanUtils.populate(aah, gd.getHeader());
		logger.info("Header AdjustStatus : "+aah.getAdjustStatus());
		
		//Prepare amount adjustment detail.
		List<Map<String,String>> recordDetailList =  gd.getRecordDetailList();
		if(recordDetailList != null && recordDetailList.size() > 0){
			for (Map<String, String> aadm: recordDetailList){
				AmountAdjustmentDetail aad = new AmountAdjustmentDetail();
				BeanUtils.populate(aad, aadm);
				aadl.add(aad);
				logger.info("Header ApproveAmount : "+aad.getApproveAmount());
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value="findProductList.json", method={RequestMethod.POST, RequestMethod.GET})
	public CommonStatus<Map<String,Object>> findProductList(@RequestParam("billRefNo") String  billRefNo) throws Exception {
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		logger.info("findProductList...Start");
		
		dataRes.setData(amtAdjustmentService.findProductList(billRefNo));
		
		logger.info("findProductList...Done");
		return dataRes;
	}
}
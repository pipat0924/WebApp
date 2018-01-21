package th.net.cat.epis.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.service.bouncecheqeue.BounceChequeService;

@Controller
@SessionAttributes(value = { "service_key", "auth_token" })
public class ReceiptController {
	private static Logger logger = Logger.getLogger(ReceiptController.class);
	@Autowired
	private ReceiptRepository receiptRepository;
	@Autowired
	private CheckSupervisor checkSupervisor;
	
	BounceChequeService bService = new BounceChequeService();

	@Transactional
	@ResponseBody
	@RequestMapping(value = "/invNoPayTypeList.json", method = RequestMethod.GET)
	public List<Receipt> findInvNoPayTypeList(@RequestParam(value = "invNo") String invNo,
			@RequestParam(value = "no") String no, @RequestParam(value = "payType") String payType, @RequestParam(value = "chk") Boolean chk)
			throws java.lang.Exception {
		Pageable p = null;
		String userLogin = EpContextHolder.getContext().getUserName();
		List<String> updateUser = EpContextHolder.getContext().getOwners();

		if (StringUtils.isEmpty(invNo)) {
			invNo = null;
		}
		if (StringUtils.isEmpty(no)) {
			no = null;
		}
		System.out.println("===================" + invNo + "+++++++++++++++" + no);

		List<Receipt> listNew = new ArrayList<Receipt>();
		Page<Receipt> result;
//		if(chk) {
//			result = receiptRepository.findByInvoiceReceiptBefore(invNo, no, payType, updateUser, bService.beforeDate(), p);
//		}else{
			result = receiptRepository.findByInvoiceReceipt(invNo, no, payType, updateUser,  p);
//		}

		List<Receipt> list = result.getContent();
		for (Receipt receipt : list) {

			listNew.add(receipt);

		}
		return listNew;
	}
	
//	@Transactional
//	@ResponseBody
//	@RequestMapping(value = "/invNoPaysapAR.json", method = RequestMethod.GET)
//	public List<Receipt> findInvoicepaySapAR(@RequestParam(value = "invNo") String invNo,
//			@RequestParam(value = "no") String no, @RequestParam(value = "payType") String payType)
//			throws java.lang.Exception {
//		Pageable p = null;
//		//String userLogin = EpContextHolder.getContext().getUserName();
//		String souce = "OUTTBOSS";
//		List<String> updateUser = EpContextHolder.getContext().getOwners();
//
//		if (StringUtils.isEmpty(invNo)) {
//			invNo = null;
//		}
//		if (StringUtils.isEmpty(no)) {
//			no = null;
//		}
//		System.out.println("===================" + invNo + "+++++++++++++++" + no);
//
//		List<Receipt> listNew = new ArrayList<Receipt>();
//		Page<Receipt> result = receiptRepository
//				.findByInvoicePaySapAR(invNo, no, payType,souce, updateUser, p);
//
//		List<Receipt> list = result.getContent();
//		for (Receipt receipt : list) {
//
//			listNew.add(receipt);
//
//		}
//		return listNew;
//	}
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/searchCancelOTTBoss.json", method = RequestMethod.GET)
	public List<Receipt> findInvoicepaySapAR(@RequestParam(value = "invNo") String invNo,
			@RequestParam(value = "no") String no,@RequestParam(value ="souece") String souece, @RequestParam(value = "payType") String payType)
			throws java.lang.Exception {
		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listNew = new ArrayList<Receipt>();
		Page<Receipt> result = null;		
		List<String> soueceVal = new ArrayList<>();
		if(souece.equals("1")){
			soueceVal.add("TBOSS");
		}else if(souece.equals("2")){
			soueceVal.add("OTBOSS");
		}else if(souece.equals("3")) {
			soueceVal.add("SAP_AR");
		}else {
			soueceVal.add("TBOSS");
			soueceVal.add("OTBOSS");
		}			
		if(!invNo.equals("") && !no.equals("")) {
			result = receiptRepository.findByInvoiceCancelOTTBOSSByInvNoAndReceiptNo(invNo, no, soueceVal,soueceVal, updateUser, bService.beforeDate(), bService.afterDate(), p);
		}else if(!invNo.equals("")) {
		result = receiptRepository.findByInvoiceCancelOTTBOSSByInvNo(invNo, soueceVal, soueceVal, updateUser, bService.beforeDate(), bService.afterDate(), p);
		}else{
		result = receiptRepository.findByInvoiceCancelOTTBOSSByReciptNo(no, soueceVal, soueceVal, updateUser, bService.beforeDate(), bService.afterDate(), p);
		}
		List<Receipt> list = result.getContent();
		
		for (Receipt receipt : list) {
			listNew.add(receipt);
		}		
		return listNew;
	}

	@ResponseBody
	@Transactional
	@RequestMapping(value = "/invPayTypepenaltyExtend.json", method = RequestMethod.GET)
	public List<Receipt> findBypenaltyExtend(@RequestParam(value = "no") String no,
			@RequestParam(value = "payType") String payType) throws java.lang.Exception {

		if ("".equals(no)) {
			no = null;
		}

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByNoPenaltyExtend(no, payType, updateUser, bService.beforeDate(), bService.afterDate(), p);
		for (Receipt receipt : result) {
			listData.add(receipt);
		}

		return listData;
	}

	@ResponseBody
	@Transactional
	@RequestMapping(value = "/invPayTypeMNPfindNo.json", method = RequestMethod.GET)
	public List<Receipt> findinvPayTypeMNP(@RequestParam(value = "no") String no,
			@RequestParam(value = "mdn") String mdn, @RequestParam(value = "oderID") String oderID,
			@RequestParam(value = "payType") String payType) throws java.lang.Exception {

		if ("".equals(no)) {
			no = null;
		}
		if ("".equals(mdn)) {
			mdn = null;
		}
		if ("".equals(oderID)) {
			oderID = null;
		}
		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByInvoiceMNP(oderID, mdn, no, payType, updateUser, bService.beforeDate(), bService.afterDate(), p);
		for (Receipt receipt : result) {
			listData.add(receipt);
		}

		return listData;
	}

	@ResponseBody
	@Transactional
	@RequestMapping(value = "/invPayTypeTopup.json", method = RequestMethod.GET)
	public List<Receipt> findbyTopup(@RequestParam(value = "serviceNo") String serviceNo,
			@RequestParam(value = "receiptNo") String receiptNo,
			@RequestParam(value = "serviceCode") String serviceCode,
			@RequestParam(value = "payType") String payType)
			throws java.lang.Exception {

		if ("".equals(serviceNo)) {
			serviceNo = null;
		}
		if ("".equals(receiptNo)) {
			receiptNo = null;
		}
		
		List<String> sCode = new ArrayList<String>();
		if(serviceCode.trim().length() > 0) {
			sCode.add(serviceCode);
		}else {
			sCode.add("cat2callplus");
			sCode.add("cnema");
			sCode.add("my");
		}

		Page<Receipt> result = null;
		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		if(receiptNo != null && serviceNo != null) {
			result = receiptRepository.findByInvoiceAndServiceNoTopup(serviceNo, receiptNo, payType,sCode, updateUser, bService.beforeDate(), bService.afterDate(), p);
		}else if(receiptNo != null || serviceNo != null){
			result = receiptRepository.findByInvoiceOrServiceNoTopup(serviceNo, receiptNo, payType,sCode, updateUser, bService.beforeDate(), bService.afterDate(), p);	
		}else {
			result = receiptRepository.findByInvoiceOrServiceNoTopup("", "", payType,sCode, updateUser, bService.beforeDate(), bService.afterDate(), p);			
		}		
		for (Receipt receipt : result) {
			listData.add(receipt);
		}

		return listData;
	}

	@ResponseBody
	@Transactional
	@RequestMapping(value = "/invPayTypeCancelOther.json", method = RequestMethod.GET)
	public List<Receipt> findByCancelOther(@RequestParam(value = "invNo") String invNo,
			@RequestParam(value = "receiptNo") String ReceiptNo, @RequestParam(value = "payType") String payType)
			throws java.lang.Exception {

		if ("".equals(invNo)) {
			invNo = null;
		}
		if ("".equals(ReceiptNo)) {
			ReceiptNo = null;
		}
		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByInvoiceCancelOther(invNo, ReceiptNo, payType, updateUser, bService.beforeDate(), bService.afterDate(), p);
		for (Receipt receipt : result) {
			listData.add(receipt);
		}

		return listData;
	}

	@ResponseBody
	@Transactional
	@RequestMapping(value = "/invPayTypeTBOSS.json", method = RequestMethod.GET)
	public List<Receipt> findByTBOSS(@RequestParam(value = "invNo") String invNo,
			@RequestParam(value = "ReceiptNo") String ReceiptNo, @RequestParam(value = "ContractNo") String ContractNo,
			@RequestParam(value = "payType") String payType) throws java.lang.Exception {

		if ("".equals(invNo)) {
			invNo = null;
		}
		if ("".equals(ReceiptNo)) {
			ReceiptNo = null;
		}
		if ("".equals(ContractNo)) {
			ContractNo = null;
		}
		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByInvoiceTBOSS(invNo, ReceiptNo, ContractNo, payType, updateUser,
				p);
		for (Receipt receipt : result) {
			listData.add(receipt);
		}

		return listData;
	}

	@ResponseBody
	@Transactional
	@RequestMapping(value = "/invPayTypeAGENT.json", method = RequestMethod.GET)
	public List<Receipt> findByAGENT(@RequestParam(value = "receiptNo") String receiptNo, @RequestParam(value = "payType") String payType)
			throws java.lang.Exception {

		if ("".equals(receiptNo)) {
			receiptNo = null;
		}
		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();

		Page<Receipt> result = receiptRepository.findByInvoiceAGENT(receiptNo, payType, updateUser, bService.beforeDate(), bService.afterDate(), p);

		for (Receipt receipt : result) {
			listData.add(receipt);
		}

		return listData;
	}

	@ResponseBody
	@RequestMapping(value = "/cusAllCri.json", method = RequestMethod.GET)
	@Transactional
	public Page<Receipt> findReceiptByCus(@RequestParam(value = "custName") String custName,
			@RequestParam(value = "custNo") String custNo, @RequestParam(value = "taxNo") String taxNo,
			@RequestParam(value = "cusActSeg") String cusActSeg) throws java.lang.Exception {

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByReceiptOrderByNoDesc(custName, custNo, taxNo, cusActSeg,
				updateUser, p);
		/*
		 * for (Receipt receipt : result) { listData.add(receipt); }
		 */

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/searchReceiptsByinvNo.json", method = RequestMethod.GET)
	@Transactional
	public Page<Receipt> findReceiptByInvNo(@RequestParam(value = "invNo") String invNo) throws java.lang.Exception {

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByInvoices_NoStartingWith(invNo, updateUser, p);
		/*
		 * for (Receipt receipt : result) { listData.add(receipt); }
		 */

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/searchReceiptsByDate.json", method = RequestMethod.GET)
	@Transactional
	public Page<Receipt> findReceiptByDate(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) throws java.lang.Exception {

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		String user = EpContextHolder.getContext().getUserName();
		List<Receipt> listData = new ArrayList<Receipt>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", Locale.US);
		Date from = format.parse(fromDate);
		Date to = format.parse(toDate);

		Page<Receipt> result = receiptRepository.findByInvoices1(from, to, updateUser, p);
		/*
		 * for (Receipt receipt : result) { listData.add(receipt); }
		 */

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/searchReceiptByInvSubNo2.json", method = RequestMethod.GET)
	@Transactional
	public Page<Receipt> findReceiptByInvSubNo2(@RequestParam(value = "invSubNo") String invSubNo)
			throws java.lang.Exception {

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByInvoices(invSubNo, updateUser, p);
		/*
		 * for (Receipt receipt : result) { listData.add(receipt); }
		 */

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchReceiptByNo.json", method = RequestMethod.GET)
	@Transactional
	public Page<Receipt> findReceiptByNo(@RequestParam(value = "no") String no)
			throws java.lang.Exception {

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByNoStartingWith(no, updateUser, p);
		/*
		 * for (Receipt receipt : result) { listData.add(receipt); }
		 */

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchReceiptByTaxNo.json", method = RequestMethod.GET)
	@Transactional
	public Page<Receipt> findReceiptByTaxNo(@RequestParam(value = "taxNo") String taxNo)
			throws java.lang.Exception {

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		List<Receipt> listData = new ArrayList<Receipt>();
		Page<Receipt> result = receiptRepository.findByTaxNoStartingWith(taxNo, updateUser, p);
		/*
		 * for (Receipt receipt : result) { listData.add(receipt); }
		 */

		return result;
		
		
	}

	@ResponseBody
	@RequestMapping(value = "/searchPaymentByDate.json", method = RequestMethod.GET)
	@Transactional
	public Page<Receipt> findPaymentByDate(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) throws java.lang.Exception {

		Pageable p = null;
		List<String> updateUser = EpContextHolder.getContext().getOwners();
		String user = EpContextHolder.getContext().getUserName();
		List<Receipt> listData = new ArrayList<Receipt>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", Locale.US);
		Date from = format.parse(fromDate);
		Date to = format.parse(toDate);

		Page<Receipt> result = receiptRepository.findByPaymentDate(from, to, updateUser, p);
		/*
		 * for (Receipt receipt : result) { listData.add(receipt); }
		 */

		return result;
	}
	

	
}
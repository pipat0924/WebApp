package th.net.cat.epis.controller.bouncecheque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.dto.bouncecheque.BounceChequeDTO;
import th.net.cat.epis.dto.bouncecheque.CheckBounceChequeDTO;
import th.net.cat.epis.dto.bouncecheque.PayBounceChequeDTO;
import th.net.cat.epis.service.bouncecheqeue.BounceChequeService;

@Controller
public class BounceChequeController {

	@Autowired
	BounceChequeService bounceChequeService;
	private BounceChequeDTO bounceChequeDTO;

	@ResponseBody
	@RequestMapping(value = "searchBounceCheque", method = RequestMethod.POST)
	public BounceChequeDTO searchBounceCheque(@RequestBody BounceChequeDTO bean) {
		bounceChequeDTO = new BounceChequeDTO();
		bounceChequeDTO = bounceChequeService.searchPaySAP(bean.getAccount(), bean.getDocHead());
		return bounceChequeDTO;
	}

	@ResponseBody
	@RequestMapping(value = "findProductCH", method = RequestMethod.GET)
	public PayBounceChequeDTO findProductCH(@RequestParam("docHead") String docHead) {
		PayBounceChequeDTO payBounceChequeDTO = new PayBounceChequeDTO();
		payBounceChequeDTO = bounceChequeService.findSubProduct(docHead);
		return payBounceChequeDTO;
	}

	@ResponseBody
	@RequestMapping(value = "searchCheckBounceCheque", method = RequestMethod.POST)
	public BounceChequeDTO searchCheckBounceCheque(@RequestBody BounceChequeDTO bean) {
		bounceChequeDTO = new BounceChequeDTO();
		bounceChequeDTO = bounceChequeService.searchCheckBounceCheque(bean.getInvoiceNo(), bean.getBillNo(),
				bean.getChequeNo());
		return bounceChequeDTO;
	}

	@ResponseBody
	@RequestMapping(value = "saveBounceChequeSet", method = RequestMethod.POST)
	public void saveBounceChequeSet(@RequestBody List<CheckBounceChequeDTO> reqList) {
		bounceChequeService.saveBounceChequeSet(reqList);
	}

	@ResponseBody
	@RequestMapping(value = "avdSearchPayBounceCheque", method = RequestMethod.GET)
	public BounceChequeDTO avdSearchPayBounceCheque(@RequestParam("avdDocHead") String docHead,
			@RequestParam("accountNo") String accountNo, @RequestParam("cusName") String cusName,
			@RequestParam("avdRegionKey1") String avdRegionKey1, @RequestParam("chk") boolean chk) {
		bounceChequeDTO = new BounceChequeDTO();
		if (chk) {
			bounceChequeDTO = bounceChequeService.avdSearchPayBounceCheque(docHead);
		} else {
			bounceChequeDTO = bounceChequeService.searchPayAdvTab(accountNo, cusName, avdRegionKey1);
		}
		return bounceChequeDTO;
	}

	@ResponseBody
	@RequestMapping(value = "updateCheckBounceCheque", method = RequestMethod.POST)
	public String setCheckBounceCheque(@RequestBody BounceChequeDTO bounceChequeDTO) {
		// String status =
		// bounceChequeService.UpdateBounceCheque(bounceChequeDTO);
		String status = "Ok";
		return status;
	}

	@ResponseBody
	@RequestMapping(value = "createPaySAPAr", method = RequestMethod.POST)
	public BounceChequeDTO createPaySAPAr(@RequestBody BounceChequeDTO bounceChequeDTO) {
//		List<BounceChequeDTO> result = new ArrayList<BounceChequeDTO>();
		BounceChequeDTO chequeDTO = new BounceChequeDTO();
		try {
			chequeDTO = bounceChequeService.sapPaymentSummr(bounceChequeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		result.add(chequeDTO);
		return chequeDTO;
	}
	@ResponseBody
	@RequestMapping(value = "searchBounceChequeHistory", method = RequestMethod.POST)
	public BounceChequeDTO searchBounceChequeHistory(@RequestBody BounceChequeDTO bean) {
		bounceChequeDTO = new BounceChequeDTO();
		bounceChequeDTO = bounceChequeService.searchPaySapHistory(bean.getAccount(), bean.getDocHead());
		return bounceChequeDTO;
	}
}

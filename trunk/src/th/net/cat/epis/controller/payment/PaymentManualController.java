package th.net.cat.epis.controller.payment;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;

public class PaymentManualController {

	@ResponseBody
	@RequestMapping(value="createPaymentProductManual.json", method=RequestMethod.POST)
	public CreatePaymentResultDTO createPaymentProductManualJSON(@RequestBody SettlePaymentDTO paymentDTO) throws Exception {
		System.out.println("AAAAAAAAAAAAAAAAA");
		return null;
	}

}

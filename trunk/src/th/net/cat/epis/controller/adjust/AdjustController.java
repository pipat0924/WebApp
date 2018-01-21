package th.net.cat.epis.controller.adjust;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.service.adjuct.AdjuctService;

@Controller
public class AdjustController {

	@Autowired
	AdjuctService adjuctService;
	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;

	@RequestMapping(value = "/refundsearch.json", method = RequestMethod.GET)
	public List<Receipt> refundSearch(@RequestParam("inv") String inv, @RequestParam("epCode") String epCode) {
		List<Receipt> receipt = new ArrayList<Receipt>();

		if ("".equals(inv)) {
			inv = null;
		} else if ("".equals(epCode)) {
			epCode = null;
		}

		receipt = adjuctService.searchRefund(inv, epCode);
		return receipt;
	}

}

package th.net.cat.epis.controller.management;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.service.EpisService;
import th.net.cat.epis.service.UserService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppConstants.RECEIPT_PRINT_TYPES;
import th.net.cat.epis.util.AppUtil;

@Controller
public class ReprintController {
	private static Logger logger = Logger.getLogger(PackagePromotionController.class);
	@Autowired
	EpisService episService;
	@Autowired
	ReceiptRepository receiptRepo;
	@Autowired
	protected MessageSource messages;
	@Autowired
	protected UserService userService;

	@ResponseBody
	@RequestMapping(value = "/getReprintHistory.json", method = RequestMethod.POST)
	public th.net.cat.epis.dto.TrsreprintDTO getReprintHistory(HttpServletRequest request,
			@RequestBody th.net.cat.epis.dto.Trsreprint trsreprint) throws Exception {

		final th.net.cat.epis.dto.TrsreprintDTO trsreprintDTO = new th.net.cat.epis.dto.TrsreprintDTO();
		List<th.net.cat.epis.dto.Trsreprint> trsreprintList = null;
		trsreprintList = episService.getReprintHistory(trsreprint);
		if (AppUtil.isCollectionHasValue(trsreprintList)) {
			for (th.net.cat.epis.dto.Trsreprint trs : trsreprintList) {
				trs.setCategoryName(messages.getMessage(
						RECEIPT_PRINT_TYPES.values()[trs.getCategory().intValue() - 1].name() + "_HISTORY_PRINT_MSG",
						null, null));
				Date updatedttm = trs.getUpdatedttm();
				trs.setUpdatedttmStr(
						AppUtil.formatter_EN.format(updatedttm) + " " + AppUtil.formatter_EN_TIME.format(updatedttm));
				if(trs.getReprintflg()!=null){
                    if(trs.getReprintflg().equalsIgnoreCase(AppConstants.FLG_TRUE)) {
                        trs.setCategoryName(trs.getCategoryName() + " (" + AppConstants.repairPrint + ")");
                    }
                }
			}
		}
		trsreprintDTO.setData(trsreprintList);
		return trsreprintDTO;
	}

	@RequestMapping(value = "/chkRepairPrint.json", method = RequestMethod.POST)
	@ResponseBody
	public Integer chkRepairPrint(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestBody th.net.cat.epis.dto.Trsreprint trsreprint) throws Exception {
		Integer countRow = 0;
		if (trsreprint != null) {

			Long receiptId = trsreprint.getReceiptid();
			Receipt receipt = receiptRepo.findOne(receiptId);

			if (null != receipt.getId()) {
				countRow = episService.findReprintByReceiptID(receipt.getId(), 2L);
			}

		}

		return countRow;
	}

	@RequestMapping(value = "/saveReprint.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveReprint(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestBody th.net.cat.epis.dto.Trsreprint trsreprint) throws Exception {
		logger.info("into saveReprint");
		// save printing history
		th.net.cat.epis.entity.Trsreprint trsreprintEntity = new th.net.cat.epis.entity.Trsreprint();
		String updateuser = request.getUserPrincipal().getName();
		Map<String, String> result = new HashMap<String, String>();
		Long receiptId = trsreprint.getReceiptid();
		Receipt receipt = receiptRepo.findOne(receiptId);
		if (!trsreprint.getCategoryName().equals("COPY")) {
			if (userService.selectCheckAuthenSuperwiser(trsreprint.getApprovedby(), trsreprint.getPassword())) {
				if (trsreprint != null) {
					List<Map<String, Object>> supperwises = userService.selectSuperwiseser(receipt.getUpdateUser());
					for (Map<String, Object> supperwise : supperwises) {
						if ((supperwise.get("SUPERWISER") + "").equals(trsreprint.getApprovedby())) {
							BeanUtils.copyProperties(trsreprint, trsreprintEntity);
							trsreprintEntity.setUpdateuser(updateuser);
							int ordinal = RECEIPT_PRINT_TYPES.valueOf(trsreprint.getCategoryName()).ordinal();
							trsreprintEntity.setCategory(BigDecimal.valueOf(ordinal + 1));
							java.util.Date date = new java.util.Date();

							trsreprintEntity.setUpdatedttm(new Timestamp(date.getTime()));
							trsreprintEntity.setReceipt(receipt);
							episService.reprintReceipt(trsreprintEntity);
							result.put("Status", "SUCCESS");
							return result;
						} else {
							result.put("Status", "ERROR");
						}
					}
				}
			} else {
				result.put("Status", "ERROR");
			}
		} else {
			BeanUtils.copyProperties(trsreprint, trsreprintEntity);
			trsreprintEntity.setUpdateuser(updateuser);
			int ordinal = RECEIPT_PRINT_TYPES.valueOf(trsreprint.getCategoryName()).ordinal();
			trsreprintEntity.setCategory(BigDecimal.valueOf(ordinal + 1));
			java.util.Date date = new java.util.Date();

			trsreprintEntity.setUpdatedttm(new Timestamp(date.getTime()));
			trsreprintEntity.setReceipt(receipt);
			trsreprintEntity.setReprintflg("1");
			episService.reprintReceipt(trsreprintEntity);
			result.put("Status", "SUCCESS");
		}

		return result;
	}
}

package th.net.cat.epis.controller.manual;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.ManualDTO;
import th.net.cat.epis.dto.ManualEpisDTO;
import th.net.cat.epis.dto.PayManualSetterDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.entity.ManualEntity;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.repo.ManualRepository;
import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.service.manual.ManualService;
import th.net.cat.epis.util.FileUtil;

@Controller
public class ManualUpLoadFile {
	@Autowired
	PaymentService paymentService;
	@Autowired
	ManualRepository manualRepo;
	@Autowired
	ManualService manualService;
	@Autowired
	private ManualController manualController;
	@Autowired
	private HttpServletRequest request;

	@ResponseBody
	@RequestMapping(value = "creatPaymentManualOffLine.json", method = RequestMethod.GET)
	public void UplodaFileManual() throws Exception {
		File folder = new File("D:\\CAT\\File-Menual");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				byte[] jsonData = Files.readAllBytes(Paths.get(folder.getPath() + "/" + listOfFiles[i].getName()));
				ObjectMapper objectMapper = new ObjectMapper();
				SettlePaymentDTO paymentDTO = objectMapper.readValue(jsonData, SettlePaymentDTO.class);
				if (paymentDTO != null) {
					paymentDTO.getCustomers().get(0).setSouceType("OFFLINE");
					ManualEpisDTO manualDTO = manualService.insertDataManual(paymentDTO);
					if (manualDTO != null) {
						SaveFile(listOfFiles[i]);
//						List<PayManualSetterDTO> listManualDTO = new ArrayList<>();
//						PayManualSetterDTO payManualSetterDTO = new PayManualSetterDTO();
//						payManualSetterDTO.setPaymenId(manualDTO.getData().get(0).getPaymenId());
//						payManualSetterDTO.setInvoiceNo(manualDTO.getData().get(0).getInvoiceNo());
//						payManualSetterDTO.setReceiptNoManual(manualDTO.getData().get(0).getReceiptNoManual());
//						payManualSetterDTO.setPaidDate(manualDTO.getData().get(0).getPaidDate());
//						payManualSetterDTO.setBranchArea(manualDTO.getData().get(0).getBranchArea());
//						payManualSetterDTO.setBranchCode(manualDTO.getData().get(0).getBranchCode());
//						payManualSetterDTO.setPaidAmount(manualDTO.getData().get(0).getPaidAmount());
//						payManualSetterDTO.setRemark(manualDTO.getData().get(0).getRemark());
//						payManualSetterDTO.setSource("RECEIVEGATEWAY");
//						payManualSetterDTO.setClearing(manualDTO.getData().get(0).getClearing());
//						payManualSetterDTO.setCreateBy(manualDTO.getData().get(0).getCreateBy());
//						payManualSetterDTO.setCreateDate(manualDTO.getData().get(0).getCreateDate());
//						payManualSetterDTO.setUpdateBy(manualDTO.getData().get(0).getUpdateBy());
//						payManualSetterDTO.setUpdateDate(manualDTO.getData().get(0).getUpdateDate());
//						payManualSetterDTO.setRecordStatus(manualDTO.getData().get(0).getRecordStatus());
//						payManualSetterDTO.setAccountNo(manualDTO.getData().get(0).getAccountNo());
//						payManualSetterDTO.setManualId(manualDTO.getData().get(0).getManualId());						
//						listManualDTO.add(payManualSetterDTO);
//						CreatePaymentResultDTO createPaymentResultDTO = manualController.creatPaymentManualPadding(listManualDTO);
					}
				}
			} else if (listOfFiles[i].isDirectory()) {

				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

	}

	public static void main(String a[]) throws Exception {
		ManualUpLoadFile loadFile = new ManualUpLoadFile();
		loadFile.UplodaFileManual();

	}

	public void SaveFile(File fileManual) {
		String part = "D:\\CAT@EPIS\\EpisWeb\\WebContent\\file\\file-off-line\\";
		String filename = "";
		if (fileManual != null) {
			filename = fileManual.getName();
			String new_filename = FileUtil.genFileName(filename);
			File fileToCreate = new File(part, new_filename);
			try {
				FileUtils.copyFile(fileManual, fileToCreate);
				FileUtil.deleteFile(fileManual);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

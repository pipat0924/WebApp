package th.net.cat.epis.controller.management;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.dto.MasterDataDTO;
import th.net.cat.epis.entity.AmountAdjustmentDetail;
import th.net.cat.epis.entity.Attachment;
import th.net.cat.epis.service.AttachmentService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;

@Controller
@SessionAttributes(value={ "service_key", "auth_token" })
public class AttachmentController  implements ServletContextAware{
	private static Logger logger = Logger.getLogger(AttachmentController.class);
	
	@Autowired 
	AttachmentService attService;
	
	@Autowired
	ServletContext context; 
	private static ObjectMapper mapper = new ObjectMapper();
	@RequestMapping(value = "/uploadFileTemp", method = RequestMethod.POST)
    public CommonStatus<Map<String,Object>> uploadFileTemp(@RequestParam("file") MultipartFile file,
    		@RequestParam("headerId") String  headerId) throws Exception {
		logger.info("uploadFileTemp...Start >> headerId : "+headerId);
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		String filePath = "C:/upload/adj/";//For testing only
        //MultipartFile multipartFile = request.getFile("file");
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath + file.getOriginalFilename());
        Files.write(path, bytes);
        logger.info("uploadFileTemp...Done");
        return dataRes;
    }
	
	/* 
	 * - jsonData is Attachment object.
	 * - isTemp  = "Y" means upload data only ,  isTemp  = "N" means upload data and insert database
	*/ 
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public CommonStatus<Map<String,Object>> uploadFile(@RequestParam("file") MultipartFile file,
    		@RequestParam("jsonData") String  jsonData, @RequestParam(value="isTemp" , required=false, defaultValue="N") String  isTemp) throws Exception {
		logger.info("uploadFile...Start >>");
		logger.info("uploadFile...isTemp >>"+isTemp);
		Attachment att = mapper.readValue(jsonData, Attachment.class);
		logger.info("uploadFile...AttachPath >>"+att.getAttachPath());
		CommonStatus<Map<String,Object>> dataRes = attService.createAttachment(file, att,isTemp);
		
        logger.info("uploadFile...Done");
        return dataRes;
    }
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.context = sc;
	}
}

package th.net.cat.epis.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.dto.MasterDataDTO;
import th.net.cat.epis.entity.Attachment;
import th.net.cat.epis.repo.AttachmentRepository;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;

@org.springframework.stereotype.Service
public class AttachmentService {
	private static Logger logger = Logger.getLogger(AttachmentService.class);
	@Autowired
	AttachmentRepository attRepo;
	
	
	public CommonStatus<Map<String,Object>> createAndUploadAttachment(MultipartFile file,Attachment att , String isTemp) throws Exception{
		CommonStatus<Map<String,Object>> dataRes = new CommonStatus<Map<String,Object>>();
		List<MasterDataDTO> mdL = AppUtil.getGroupMasterData("SYSTEM_CONFIG");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		MasterDataDTO mdCmPath =  mdL.stream().filter(mdTemp -> AppConstants.ATT_COMMON_PATH.equals(mdTemp.getKey())).findFirst().get();
		MasterDataDTO mdAttachPath =  mdL.stream().filter(mdTemp -> att.getAttachPath().equals(mdTemp.getKey())).findFirst().get();
		logger.info("uploadFile...Start >> Common Attach Path : "+mdCmPath.getValue() +" , Attach Path : "+mdAttachPath.getValue());
		String filePath = mdCmPath.getValue() + mdAttachPath.getValue();
		if(AppUtil.isWindows()){
			filePath ="C:/"+ filePath ; 
		}
        byte[] bytes = file.getBytes();
        logger.info("uploadFile...Start >> file.getOriginalFilename() : "+file.getOriginalFilename());
        String[] fileArr = file.getOriginalFilename().split("\\.");
        logger.info("uploadFile...Start >> fileArr.length : "+fileArr.length );
        String fileExt = fileArr[fileArr.length-1];
        String fileName = file.getOriginalFilename().replaceAll("."+fileExt, "")+"_"+AppUtil.getDateYYYYMMDDHHMMSS()+"."+fileExt;
        logger.info("uploadFile...Start >> fileName : "+fileName);
        Path path = Paths.get(filePath + fileName);
        Files.write(path, bytes);
        
        //Insert data if isTemp = "N"
        att.setAttachName(fileName);
        if(AppConstants.NO.equalsIgnoreCase(isTemp)){
        	Attachment attCur = createAttachment(att);
        	BeanUtils.copyProperties(att, attCur);
        }
        
        //Return data to 
        ObjectMapper om = new ObjectMapper();
        Map<String,Object> dataMap = om.convertValue(att,Map.class);
        List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
        dataList.add(dataMap);
        dataRes.setData(dataList);
        return dataRes;
	}
	
	public Attachment createAttachment(Attachment att) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Calendar cal = Calendar.getInstance();
		//Create adjust amount header first.
    	att.setCreateDate(cal.getTime());
    	att.setUpdateDate(cal.getTime());
    	att.setCreateUser(userName);
    	att.setUpdateUser(userName);
    	Attachment attCur = attRepo.save(att);
    	return attCur;
	}
	
	public static void main(String[] args){
		String fileName = "026-printPaymentReceipt-20170105.pdf";
		String[] fileArr = fileName.split("\\.");
		 logger.info("uploadFile...Start >> fileArr.length : "+fileArr.length );
	}
}

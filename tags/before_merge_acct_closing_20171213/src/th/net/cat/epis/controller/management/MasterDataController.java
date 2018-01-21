package th.net.cat.epis.controller.management;


import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.rightPad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.batch.task.GenerateCreditListSendToHost;
import th.net.cat.epis.dto.CommonStatus;
import th.net.cat.epis.dto.MasterDataDTO;
import th.net.cat.epis.entity.MapGLServiceTpye;
import th.net.cat.epis.entity.MasterData;
import th.net.cat.epis.entity.Principal;
import th.net.cat.epis.initial.AfterInitialConfiguration;
import th.net.cat.epis.repo.MasterDataRepository;
import th.net.cat.epis.repo.PrincipalRepository;
import th.net.cat.epis.service.PaymentService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;
import th.net.cat.epis.util.DateUtil;

@Controller
@RequestMapping("/MasterData")
public class MasterDataController {
	private static Logger logger = Logger.getLogger(MasterDataController.class);
	@Autowired
    private ServletContext servletContext;
    @Autowired
    private MasterDataRepository masterDataRepository;
    @Autowired 
   	PrincipalRepository principalRepository;
//   	@ResponseBody
//   	@RequestMapping(value="/findAllMasterDataGroupKey", method=RequestMethod.POST)
//   	public Iterable<MasterDataDTO> findAllMasterDataGroupKey() throws Exception {
//   		return masterDataRepository.findByGroupKeyContainingIgnoreCase(groupKey);
//   	}
    
    @RequestMapping(value = "findByGroupKey.json", method = RequestMethod.GET)
  	public void findByGroupKey(@RequestParam("groupKey") String groupKey, ModelMap modelMap) {
  		modelMap.addAttribute("data", masterDataRepository.findByGroupKey(groupKey));
  		modelMap.addAttribute("statusCode", "0");
  	}
    
    @RequestMapping(value = "findListByGroupKeyAndKey.json", method = RequestMethod.GET)
  	public void findByGroupKeyAndKey(@RequestParam("groupKey") String groupKey,
  									 @RequestParam("key") String key, ModelMap modelMap) {
  		modelMap.addAttribute("data", masterDataRepository.findByGroupKeyAndKey(groupKey,key));
  		modelMap.addAttribute("statusCode", "0");
  	}
    @RequestMapping(value = "findByKey.json", method = RequestMethod.GET)
  	public void findByKey( @RequestParam("key") String key, ModelMap modelMap) {
  		modelMap.addAttribute("data", masterDataRepository.findByKey(key));
  		modelMap.addAttribute("statusCode", "0");
  	}
    
    @RequestMapping(value="/findByGroupKeyList.json", method=RequestMethod.POST)
    public void findByGroupKeyList(ModelMap modelMap,@RequestBody MasterDataDTO dtoParam) throws Exception {
    	
       	String statusCode = "-1";
       	int[] wt = new int[3];
       	try{
       		
       	
		    	Set<String> key = new HashSet<String>();
		    	if(StringUtils.equals(dtoParam.getKey(),AppConstants.WT_GOLV_KEY)){
		    		
		    		key.add(AppConstants.BIS_USAGE_CHARGE);
		    		key.add(AppConstants.BIS_RENTAL_CHARGE);
		    		key.add(AppConstants.BIS_AVERAGE_CHARGE);
		    		
		    	}else{
		    		key.add(AppConstants.TRE_USAGE_CHARGE);
		    		key.add(AppConstants.TRE_RENTAL_CHARGE);
		    		key.add(AppConstants.TRE_AVERAGE_CHARGE);
		    		
		    	}
		    	
		    	
		    	List<MasterData> masterData = masterDataRepository.findByKeyInAndGroupKey(key,AppConstants.WT_STR);
		    
		    	//List<String> wt = new ArrayList<String>();
		    	
		    	
		    	for(MasterData tmpData : masterData){
		    		if(StringUtils.contains(tmpData.getKey(), AppConstants.WT_USAGE)){
		    			wt[0] = Integer.valueOf(tmpData.getValue());
		    		}else if(StringUtils.contains(tmpData.getKey(), AppConstants.WT_RENTAL)){
		    			wt[1] = Integer.valueOf(tmpData.getValue());
		    		}else if(StringUtils.contains(tmpData.getKey(), AppConstants.WT_AVG)){
		    			wt[2] = Integer.valueOf(tmpData.getValue());
		    		}
		    	}
		    	if(masterData.size() != 0) statusCode = "1";
       	}catch(Exception e){
       		e.printStackTrace();
       	}
    	
    	modelMap.addAttribute("data", wt);
   		modelMap.addAttribute("statusCode", statusCode);
    		
    	
    }
       @RequestMapping(value="/searchByGroupKeyAndValueByKey.json", method=RequestMethod.POST)
       public void searchByRole(ModelMap modelMap,@RequestBody MasterDataDTO dtoParam) throws Exception {
       	String statusCode = "-1";
       	String groupKey = StringUtils.isEmpty(dtoParam.getGroupKey())? "" : dtoParam.getGroupKey();
           String key = StringUtils.isEmpty(dtoParam.getKey()) ?  "" : dtoParam.getKey();
           String value = StringUtils.isEmpty(dtoParam.getValue()) ? "" : dtoParam.getValue() ;
           List<MasterDataDTO> dtoList = new ArrayList<MasterDataDTO>();
           Iterable<MasterData> entityList = null;
           
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           
           
       	Set<String> roleName = new HashSet<String>();
       	if (!(authentication instanceof AnonymousAuthenticationToken)) {
       		
   			 for (GrantedAuthority auth : authentication.getAuthorities()) {
   				 roleName.add(auth.getAuthority());
   		     }
   		 }
   		 
       	List<MasterData> dataSearchList = masterDataRepository.findKeyByGroupList(roleName);//.findByCriteria(groupKey, key, value);
       	
           if(StringUtils.isEmpty(key)){
           	entityList = masterDataRepository.findByGroupKeyContainingIgnoreCaseAndValueContainingIgnoreCaseOrderByGroupKeyAsc(groupKey, value);
           }else{
           	entityList = masterDataRepository.findByKeyIgnoreCaseAndGroupKeyContainingIgnoreCaseAndValueContainingIgnoreCaseOrderByGroupKeyAsc(key, groupKey,value);//.findByCriteria(groupKey, key, value);
           }
           
           if(entityList != null){
           	statusCode = "0";
               for (MasterData entity : entityList) {
               	for(MasterData tmpData : dataSearchList){
               		if(StringUtils.equals(tmpData.getKey(), entity.getGroupKey())){
               			MasterDataDTO dto = new MasterDataDTO(entity);
                           dtoList.add(dto);
               		}
               	}
                   
               }
           }
           modelMap.addAttribute("data", dtoList);
   		modelMap.addAttribute("statusCode", statusCode);
       }
       
       @RequestMapping(value="/findDistinctGroupKey.json", method=RequestMethod.GET)
       public void  findDistinctGroupKey(ModelMap modelMap) throws Exception {
       	
       	String statusCode = "-1";
   		
       	
       	Set<String> principalNameList = new HashSet<String>();
       	Iterable<Principal> principalList = principalRepository.findAll();
       	for(Principal principalTmp:principalList){
       		principalNameList.add(principalTmp.getName());
       	}
           List<MasterDataDTO> dtoList = new ArrayList<MasterDataDTO>();
           Iterable<MasterData> entityList = masterDataRepository.findDistinctByGroupKeyNotIn(principalNameList);
       	
           
           
           if(entityList != null){
               for (MasterData entity : entityList) {
                   MasterDataDTO dto = new MasterDataDTO(entity);
                   dtoList.add(dto);
               }
               statusCode = "0";
           }
           
           modelMap.addAttribute("data", dtoList);
   		modelMap.addAttribute("statusCode", statusCode);
       }
       
       @RequestMapping(value="/searchGroupKeyByRole.json", method=RequestMethod.POST)
       public void  searchByGroupKey(ModelMap modelMap) throws Exception {
       	
       	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       	Set<String> roleName= new HashSet<String>();
       	String statusCode = "-1";
   		 if (!(authentication instanceof AnonymousAuthenticationToken)) {
   		     
   			 
   			 for (GrantedAuthority tmpAuth : authentication.getAuthorities()) {
   				 roleName.add(tmpAuth.getAuthority());
   		     }
   			 
   		 }
           List<MasterDataDTO> dtoList = new ArrayList<MasterDataDTO>();
           Iterable<MasterData> entityList = null;
           
           if(!roleName.equals(new HashSet<String>())){
           	entityList = masterDataRepository.findKeyByGroupList(roleName);
           	statusCode = "0";
           }
           
           if(entityList != null){
               for (MasterData entity : entityList) {
                   MasterDataDTO dto = new MasterDataDTO(entity);
                   dtoList.add(dto);
               }
           }
           
           modelMap.addAttribute("data", dtoList);
   		modelMap.addAttribute("statusCode", statusCode);
       }
    @ResponseBody
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public List<MasterDataDTO> search(@RequestBody MasterDataDTO dtoParam) throws Exception {
        String groupKey = (dtoParam.getGroupKey() != null) ? dtoParam.getGroupKey() : "";
        String key = (dtoParam.getKey() != null) ? dtoParam.getKey() : "";
        String value = (dtoParam.getValue() != null) ? dtoParam.getValue() : "";
        List<MasterDataDTO> dtoList = new ArrayList<MasterDataDTO>();
        Iterable<MasterData> entityList = masterDataRepository.findByCriteria(groupKey, key, value);
        if(entityList != null){
            for (MasterData entity : entityList) {
                MasterDataDTO dto = new MasterDataDTO(entity);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
    
    @ResponseBody
    @RequestMapping(value="/searchByGroupActive.json", method=RequestMethod.GET)
    public List<MasterData> searchByGroupActive(@RequestParam(value="groupKey") String groupKey) throws Exception {

        List<MasterData> dtoList = masterDataRepository.findByGroupKeyIgnoreCase(groupKey);
 
        return dtoList;
    }
    @ResponseBody
    @RequestMapping(value="/searchSubProduct.json", method=RequestMethod.GET)
    public List<MasterData> searchSubProduct(@RequestParam(value="groupKey") String groupKey,@RequestParam(value="property1") String property1) throws Exception {

        List<MasterData> dtoList = masterDataRepository.findByGroupKeyAndProperty1IgnoreCase(groupKey,property1);
 
        return dtoList;
    }
    @RequestMapping(value="/deleteById", method=RequestMethod.POST)
    public void deleteById(ModelMap modelMap,@RequestBody MasterDataDTO dto) throws Exception {
    	
    	String username = "";
    	String data = "-1";
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	Set<String> roleName = new HashSet<String>();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
		     
			 
			 for (GrantedAuthority auth : authentication.getAuthorities()) {
				 roleName.add(auth.getAuthority());
		     }
		 }
    	
    	if(dto.getId() != 0){
    		try{
    			MasterData updateData = masterDataRepository.findById(dto.getId()).get(0);
    			List<MasterData> entityList = masterDataRepository.findKeyByGroupList(roleName);
    			
    			
				for(MasterData tmpMaster : entityList ){
    				if(StringUtils.equals(tmpMaster.getKey(),updateData.getGroupKey())){
    					masterDataRepository.delete(updateData.getId());
    					data = "1";
    				}
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		
    	}
    	modelMap.addAttribute("data", "");
  		modelMap.addAttribute("statusCode", data);
       /* MasterData entity = (MasterData)masterDataRepository.save(dto.toEntity());
        MasterDataDTO savedDTO = new MasterDataDTO(entity);
        List<MasterDataDTO> dtoList = AfterInitialConfiguration.masterDataLookup.get(savedDTO.getGroupKey());
        if(dtoList == null) {
        	dtoList = new ArrayList<MasterDataDTO>();
        }
	    dtoList.add(savedDTO);
	    AfterInitialConfiguration.masterDataLookup.put(savedDTO.getGroupKey(), dtoList);*/
       
        
//        Map<String, List<MasterDataDTO>> masterDataLookup = (Map<String, List<MasterDataDTO>>) servletContext.getAttribute(AppConstants.MASTER_DATA_LOOKUP);
//        List<MasterDataDTO> dtoList = masterDataLookup.get(savedDTO.getGroupKey());
//        if(dtoList == null) {
//        	dtoList = new ArrayList<MasterDataDTO>();
//        }
//	    dtoList.add(savedDTO);
//	    masterDataLookup.put(savedDTO.getGroupKey(), dtoList);
//	    servletContext.setAttribute(AppConstants.MASTER_DATA_LOOKUP, masterDataLookup);
//	    return savedDTO;
    }
    @ResponseBody
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public MasterDataDTO save(@RequestBody MasterDataDTO dto) throws Exception {
    	
    	String createBy = "";
    	String updateBy ="";
    	Timestamp ts = new Timestamp(System.currentTimeMillis());
    	Timestamp createDate = ts;
    	Timestamp updateDate = ts;
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
		     
			 
			 createBy = authentication.getName();
			 updateBy = authentication.getName();
			 
			 
		 }
 
    	
    	if(dto.getId() != null){
    		try{
    			MasterData updateData = masterDataRepository.findById(dto.getId()).get(0);
    			createBy = StringUtils.isEmpty(updateData.getUpdateBy()) ? updateBy : updateData.getUpdateBy();
    			createDate = updateData.getUpdateDate() == null ? ts : updateData.getUpdateDate();
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		
    	}
    	dto.setCreateBy(createBy);
    	dto.setCreateDate(createDate);
    	dto.setUpdateBy(updateBy);
    	dto.setUpdateDate(updateDate);
        MasterData entity = (MasterData)masterDataRepository.save(dto.toEntity());
        MasterDataDTO savedDTO = new MasterDataDTO(entity);
        List<MasterDataDTO> dtoList = AfterInitialConfiguration.masterDataLookup.get(savedDTO.getGroupKey());
        if(dtoList == null) {
        	dtoList = new ArrayList<MasterDataDTO>();
        }
	    dtoList.add(savedDTO);
	    AfterInitialConfiguration.masterDataLookup.put(savedDTO.getGroupKey(), dtoList);
        return savedDTO;
       
        
//        Map<String, List<MasterDataDTO>> masterDataLookup = (Map<String, List<MasterDataDTO>>) servletContext.getAttribute(AppConstants.MASTER_DATA_LOOKUP);
//        List<MasterDataDTO> dtoList = masterDataLookup.get(savedDTO.getGroupKey());
//        if(dtoList == null) {
//        	dtoList = new ArrayList<MasterDataDTO>();
//        }
//	    dtoList.add(savedDTO);
//	    masterDataLookup.put(savedDTO.getGroupKey(), dtoList);
//	    servletContext.setAttribute(AppConstants.MASTER_DATA_LOOKUP, masterDataLookup);
//	    return savedDTO;
    }

    @ResponseBody
    @RequestMapping(value="/getGroupData", method=RequestMethod.GET)
    public List<MasterDataDTO> getGroupData(@RequestParam("key") String key) throws Exception {
        return AppUtil.getGroupMasterData(key);
//    	Map<String, List<MasterDataDTO>> masterDataLookup = (Map<String, List<MasterDataDTO>>) servletContext.getAttribute(AppConstants.MASTER_DATA_LOOKUP);
//        return masterDataLookup.get(key);
    }

    @ResponseBody
    @RequestMapping(value="/getSingleData", method=RequestMethod.GET)
    public MasterDataDTO getSingleData(@RequestParam("key") String key) throws Exception {
        return AppUtil.getSingleMasterData(key);
//    	Map<String, List<MasterDataDTO>> masterDataLookup = (Map<String, List<MasterDataDTO>>) servletContext.getAttribute(AppConstants.MASTER_DATA_LOOKUP);
//        return masterDataLookup.get(key).get(0);
    }
    
    @ResponseBody
    @RequestMapping(value="/getSpecificGroupData", method=RequestMethod.GET)
    public List<MasterDataDTO> getSpecificGroupData(@RequestParam("group") String group,@RequestParam("keyGroup") String keyGroup) throws Exception {
    	logger.info("getSpecificGroupData...Start >> group : "+group+" , keyGroup : "+keyGroup);
    	List<MasterDataDTO> results = AppUtil.getSpecificGroupData(group, keyGroup);
    	logger.info("getSpecificGroupData...End >> group : "+group+" , keyGroup : "+keyGroup);
        return results;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    Credit Limit
    
	String exportOutputPath = "/home/epis_user/cat/online/";	//"D://temp//";	//
	String taskActive = "1";
	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@Autowired PaymentService paymentService;
	
	@RequestMapping(value="/generateCreditlimitFile", method=RequestMethod.POST)
	public void generateCreditlimitFile() throws Exception {
		
		Connection transaction = null;
		try{
		transaction = episJdbcTemplate.getDataSource().getConnection();
		transaction.setAutoCommit(false);
		
			final Map<String, String> receiptNoMap = new HashMap<String, String>();
			final List<String> payemntAndReceiptList = new ArrayList<String>();
			episJdbcTemplate.query(
					"SELECT rownum " +
							", cre.CONTRACT  " +
							", cre.AR_REF " +
							", cre.PAY_TYPE " +
							", cre.AMOUNT_INC_VAT " +
							", cre.PAY_DATE " +
							", cre.MSISDN " +
//							", receipt.RECEIPTNO as RECEIPTNO " +
							", cre.RECEIPT_ID as RECEIPTNO " +
							", cre.VAT_AMOUNT " +
							", cre.AMOUNT_EX_VAT " +
							", cre.POST_DATE " +
							", cre.ACCOUNT_NO " +
							", cre.AR_INVDATE " +
							", cre.AR_DUEDATE " +
							",cre.STATUS " +
							",cre.UPDATED_TIME " +
							", cre.RECEIPT_ID as RECEIPT_ID " +
							" FROM CREDIT_LIMIT_TRANS cre "+ //"left join CORRECEIPT receipt " +
//							" on cre.RECEIPT_ID = receipt.RECEIPTID "+
							" where cre.STATUS='N'  ", new RowCountCallbackHandler() {
						//		+ " AND inv.attributes !='A' "
						//		+ "  AND rcp.updatesystem = 'CLT' ", new RowCountCallbackHandler(){
						
						@Override
						public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
							StringBuilder stringBuilder = new StringBuilder();
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("CONTRACT")), 10, " "));
//							String arRef = StringUtils.trimToEmpty(resultSet.getString("AR_REF"));
//							arRef = arRef.replace("Advance Payment", "0");
//							stringBuilder.append(AppUtil.toRightPaddingString(arRef, 9, ' '));
							stringBuilder.append(AppUtil.toRightPaddingString(StringUtils.trimToEmpty(resultSet.getString("AR_REF")).replace("Advance Payment", "0"), 9, ' '));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("PAY_TYPE")), 1, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AMOUNT_INC_VAT")), 13, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("PAY_DATE")), 8, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("MSISDN")), 20, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("RECEIPTNO")), 22, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("VAT_AMOUNT")), 13, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AMOUNT_EX_VAT")), 13, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("POST_DATE")), 19, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("ACCOUNT_NO")), 10, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AR_INVDATE")), 8, " "));
							stringBuilder.append(rightPad(StringUtils.trimToEmpty(resultSet.getString("AR_DUEDATE")), 8, " "));
							String key = resultSet.getString("CONTRACT") + "|" + "" + resultSet.getString("AR_REF") + "|" + resultSet.getString("RECEIPT_ID");
							receiptNoMap.put(key, key);
							payemntAndReceiptList.add(stringBuilder.toString());
						}
					});
			String creditLimitFileName = null;
			 
			
				if (payemntAndReceiptList.size() > 0) {
						creditLimitFileName = generateFileAndSendToHost(payemntAndReceiptList);
						Set set = receiptNoMap.entrySet();
						Iterator iterator = set.iterator();
						List<Object[]> parameters = new ArrayList<Object[]>();
						while (iterator.hasNext()) { //update -> batchupdate
							Map.Entry mentry = (Map.Entry) iterator.next();
							String key = (String) mentry.getKey();
							String[] key_array = key.split("\\|");
							String contract = key_array[0];
							String arRef = key_array[1];
							String receipId = key_array[2];
							parameters.add(new Object[] {contract, arRef, receipId });
							
							
						}
						episJdbcTemplate.batchUpdate("UPDATE CREDIT_LIMIT_TRANS SET STATUS = 'Y' WHERE CONTRACT = ? and AR_REF = ? and RECEIPT_ID = ? ", parameters);
						if (creditLimitFileName != null) {
							ftpCreditLimitToHost(creditLimitFileName);
						}
					
				}
				//logger.info("start commit");
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try{
					
				transaction.rollback();
				}catch(Exception e1){
					e.printStackTrace();
					throw e;
				}
				throw e;
			} finally {
				try{
					transaction.close();
				}catch(Exception e){throw e;}
			}
//		return 0;
	}

	public String generateFileAndSendToHost(List<String> payemntAndReceiptList) throws IOException {
		
		final String fileName = getFileFormatter();
			// auto create directory when not exists.
		//File folderOnline = new File(EXPORT_OUTPUT_PATH_TXT);
		File folderOnline = new File(exportOutputPath);
		if(!folderOnline.exists()) folderOnline.mkdir();
		File file = new File(exportOutputPath.concat(fileName));
		//File file = new File(EXPORT_OUTPUT_PATH_TXT.concat(fileName));
        	// creates the file.
        file.createNewFile();
        	// creates a FileWriter Object.
        FileWriter writer = new FileWriter(file); 
        	// writes the content to the file.
        for(String recepitData : payemntAndReceiptList) {
        	writer.write(recepitData); 
	        writer.write(System.getProperty( "line.separator" ));
        }
        
        writer.flush();
        writer.close();

		return fileName;
	}
	
	String server = "10.36.10.12";
	int port = 22;
	String username = "epis_user";
	String password = "password021166615";
	String directory = "/home/epis_user/test_ftp/";

	public void ftpCreditLimitToHost(String creditLimitFileName) throws Exception {
		FTPClient ftpClient = new FTPClient();
		try {
			logger.info("Creating FTP connection.");
			logger.info("server = "+ server);
			logger.info("port = "+ port);
			logger.info("username = "+ username);
			logger.info("password = "+ password);
			logger.info("directory = "+ directory);
			logger.info("fileName = "+ creditLimitFileName);
			ftpClient.connect(StringUtils.trim(server));
			ftpClient.login(StringUtils.trim(username), StringUtils.trim(password));
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			logger.info("Creating FTP connection created.");
			// APPROACH #1: uploads first file using an InputStream
			//File localFile = new File(EXPORT_OUTPUT_PATH_TXT.concat(creditLimitFileName));
			File localFile = new File(exportOutputPath.concat(creditLimitFileName));
			logger.info("localFile = "+ localFile.getAbsolutePath());
			String remoteFile = StringUtils.trim(directory).concat(creditLimitFileName);
			logger.info("remoteFile = "+ remoteFile);
			InputStream inputStream = new FileInputStream(localFile);
			logger.info("Start uploading file");
			logger.info("inputStream = "+ inputStream);
			boolean done = ftpClient.storeFile(remoteFile, inputStream);
			inputStream.close();
			if(done) {
				logger.info("The file is uploaded successfully.");
			}
			if(ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFileFormatter() {
		return new StringBuilder("PAY_EPIS").append("_").append(FastDateFormat.getInstance("yyyyMMdd", ENGLISH).format(new Date())).append("_").append(FastDateFormat.getInstance("hhmmss", ENGLISH).format(new Date())).append(AppConstants.REPORT_TYPE_TXT).toString();
	}
	
	
	
	
	
	
	
	
	int headerRowCount;
	
	String receiptUpdatedDateStr;
	String receiptDateStr;
	String currencyStr;
	String currencyRateStr;
	String branchNameStr;
	String branchCodeStr;
	String totalChargeStr;
	String chargeStr13;
	String chargeStr15;
//	String totalAmtStr;
	String vatStr;
	String wtStr;
	String transAmtStr;
	String taxBaseAmtStr13;
	String taxBaseAmtStr15;
	String branchAreaStr;
	String productCodeStr12;
	String productCodeStr18;
	String methodCodeStr;
	String chequeNoStr;
	String regionSapStr;
	String tradingPartStr;
	String canceledByStr;
	String receiveAmtStr;
	String billingGroupStr;
	String baNoStr;
	String invNoStr;
	String text;
	
	
	
	
	
	String payDateStr;
	String issueDateStr;
//	String currencyStr;
//	String currencyRateStr;
	String exchangeDateStr;
//	String branchNameStr;
//	String branchCodeStr;
	String accountNoStr;
	String amountStr;
//	String vatStr;
//	String wtStr;
	String payAmountStr;
	String amountIncludeVatStr;
	String amountExcludeVatStr13;
	String amountExcludeVatStr15;
	String businessAreaCodeStr;
//	String productCodeStr12;
//	String productCodeStr18;
//	String tradingPartStr;
	String postingDateStr;
//	String regionSapStr;
	String partnerCodeStr;
	String approveByStr;
	
	@RequestMapping(value="/generateIdocFile", method=RequestMethod.POST)
	public void generateBillingIdoc() throws Exception {
		Date date = new Date();//date = DateUtil.convertToDate("2017-08-30", DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
		StringBuffer strBuff = new StringBuffer();
    	strBuff.setLength(0);
    	String dateStr = DateUtil.convertToString(date, DateUtil.YYYY_MM_DD_DATE_PATTERN, DateUtil.ENG_LOCALE);
    	headerRowCount = 1;
    	
		episJdbcTemplate.query(
			" select "
			+"	trunc(t.PAY_DATE_DT) PAY_DATE "
			+"	,t.CURR "
			+"	,t.LOCATION_NAME "
			+"	,t.BUSINESS_PLACE "
			+"	,t.GL_ACCOUNT "
			+"	,t.PAYTYPE "
			+"	,sum(t.INV_AMT_BEFORE_VAT) INV_AMT_BEFORE_VAT "
			+"	,sum(t.INV_VAT) INV_VAT "
//			+"	,sum(t.PAY_WT) PAY_WT "
			+"  ,sum(t.PAY_TOTALAMOUNT) PAY_TOTALAMOUNT" 
			+"	,t.BUSINESS_AREA "
			+"	,t.PRO_PRODUCT_CODE "
			+"	,t.REGION_SAP "
			+"	,t.TRADING_PART "
			+"	,trunc(t.POST_DATE_DT) POST_DATE "
			+" from DW_EPIS t "
//			+" where trunc(t.SHOP_CLOSING_DATE_DT) = to_date('"+dateStr+"', 'YYYY-MM-DD') "
//			+" and (t.RECEIPT_STATUS not like '%R%' or trunc(t.REVERSE_DATE_DT) > trunc(t.PAY_DATE_DT)) "
			+" where paymentid in (4054) "
			+" group by "
			+"	trunc(t.PAY_DATE_DT) "
			+"	,t.CURR "
			+"	,t.LOCATION_NAME "
			+"	,t.BUSINESS_PLACE "
			+"	,t.GL_ACCOUNT "
			+"	,t.PAYTYPE "
			+"	,t.BUSINESS_AREA "
			+"	,t.PRO_PRODUCT_CODE "
			+"	,t.REGION_SAP "
			+"	,t.TRADING_PART "
			+"	,trunc(t.POST_DATE_DT) "
			
			, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet row) throws SQLException {
			    	
			    	payDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	issueDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	currencyStr = AppUtil.toLeftPaddingString(row.getString("CURR"), 5, ' ');
			    	currencyRateStr = AppUtil.toLeftPaddingString("1", 11, ' ');
			    	exchangeDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("PAY_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	branchNameStr = AppUtil.toLeftPaddingString(row.getString("LOCATION_NAME"), 16, ' ');
			    	branchNameStr = branchNameStr.replace("ศูนย์บริการลูกค้า", "ศบล. ");
			    	if(branchNameStr.length() > 16){
			    		branchNameStr = branchNameStr.substring(0,16);
			    	}
			    	branchCodeStr = AppUtil.toLeftPaddingString(row.getString("BUSINESS_PLACE"), 4, ' ');
			    	if(branchCodeStr.length() > 4) {
			    		branchCodeStr = branchCodeStr.substring(1, 5);
			    	}
			    	accountNoStr = AppUtil.toRightPaddingString(row.getString("GL_ACCOUNT"), 10, '0');
//			    	BigDecimal payAmount = (row.getBigDecimal("PAY_TOTALAMOUNT") != null) ? row.getBigDecimal("PAY_TOTALAMOUNT") : BigDecimal.ZERO;
			    	BigDecimal amountExcludeVat;// = (row.getBigDecimal("INV_AMT_BEFORE_VAT") != null) ? row.getBigDecimal("INV_AMT_BEFORE_VAT") : BigDecimal.ZERO;
			    	BigDecimal vat;// = (row.getBigDecimal("INV_VAT") != null) ? row.getBigDecimal("INV_VAT") : BigDecimal.ZERO;
			    	BigDecimal wt;// = (row.getBigDecimal("PAY_WT") != null) ? row.getBigDecimal("PAY_WT") : BigDecimal.ZERO;
			    	BigDecimal amountIncludeVat;// = amountExcludeVat.add(vat).add(wt);
			    	BigDecimal payAmount;// = amountExcludeVat.add(vat).subtract(wt);
			    	
			    	if("WT".equalsIgnoreCase(row.getString("PAYTYPE"))){
			    		amountExcludeVat = (row.getBigDecimal("INV_AMT_BEFORE_VAT") != null) ? row.getBigDecimal("INV_AMT_BEFORE_VAT") : BigDecimal.ZERO;
				    	vat = (row.getBigDecimal("INV_VAT") != null) ? row.getBigDecimal("INV_VAT") : BigDecimal.ZERO;
				    	wt = (row.getBigDecimal("PAY_TOTALAMOUNT") != null) ? row.getBigDecimal("PAY_TOTALAMOUNT") : BigDecimal.ZERO;
				    	amountIncludeVat = amountExcludeVat.add(vat).add(wt);
				    	payAmount = BigDecimal.ZERO;//amountExcludeVat.add(vat).subtract(wt);
			    	}else{
			    		amountExcludeVat = (row.getBigDecimal("INV_AMT_BEFORE_VAT") != null) ? row.getBigDecimal("INV_AMT_BEFORE_VAT") : BigDecimal.ZERO;
				    	vat = (row.getBigDecimal("INV_VAT") != null) ? row.getBigDecimal("INV_VAT") : BigDecimal.ZERO;
				    	wt = BigDecimal.ZERO;//(row.getBigDecimal("PAY_WT") != null) ? row.getBigDecimal("PAY_WT") : BigDecimal.ZERO;
				    	amountIncludeVat = amountExcludeVat.add(vat).add(wt);
				    	payAmount = amountExcludeVat.add(vat).subtract(wt);
			    	}
			    	
			    	payAmountStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(payAmount, "0.00") , 13, ' ');
			    	amountIncludeVatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountIncludeVat, "0.00") , 13, ' ');
			    	vatStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(vat, "0.00") , 13, ' ');
			    	wtStr = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(wt, "0.00") , 13, ' ');
			    	amountExcludeVatStr13 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountExcludeVat, "0.00") , 13, ' ');
			    	amountExcludeVatStr15 = AppUtil.toLeftPaddingString( AppUtil.toNumberFormat(amountExcludeVat, "0.00") , 15, ' ');
			    	businessAreaCodeStr = AppUtil.toLeftPaddingString(row.getString("BUSINESS_AREA"), 4, ' ');
			    	productCodeStr12 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 12, ' ');
			    	productCodeStr18 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 18, ' ');//productCodeStr18 = AppUtil.toLeftPaddingString(row.getString("PRO_PRODUCT_CODE"), 18, '0');
//			    	tradingPartStr = AppUtil.toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	postingDateStr = AppUtil.toLeftPaddingString(DateUtil.convertToString(row.getDate("POST_DATE"), DateUtil.YYYYMMDD_DATE_PATTERN, DateUtil.ENG_LOCALE), 8, ' ');
			    	regionSapStr = AppUtil.toLeftPaddingString(row.getString("REGION_SAP"), 12, ' ');
			    	partnerCodeStr = AppUtil.toLeftPaddingString(row.getString("TRADING_PART"), 6, ' ');
			    	
			    	
			    	
			    	
					// Header Loop
					for (int i = 1; i <= 2; i++) {
						
						// Detail Loop
						if(headerRowCount % 2 > 0){	//Header row 1
							for (int detailRowCount = 1; detailRowCount <= 3; detailRowCount++) {
								// Header
								strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( payDateStr );//B
								strBuff.append("RV");//C							
								strBuff.append("1000");//D
								strBuff.append( payDateStr ); //E
								strBuff.append( payDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( exchangeDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( AppUtil.toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( payDateStr.substring(0,4) ); //N
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":(detailRowCount==3)?"S":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":(detailRowCount==3)?"40":"  " );//T
								strBuff.append( "S" );//U
//								strBuff.append( accountNoStr );//V
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("11010102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("11149102", 10, '0') );//V
								}else if(detailRowCount == 3){
									strBuff.append( AppUtil.toRightPaddingString("11903102", 10, '0') );//V
								}
								
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
								if(detailRowCount == 1){
									strBuff.append( payAmountStr );//X
									strBuff.append( payAmountStr );//Y
								}else if(detailRowCount == 2){	
									strBuff.append( amountIncludeVatStr );//X
									strBuff.append( amountIncludeVatStr );//Y
								}else if(detailRowCount == 3){
									strBuff.append( wtStr );//X
									strBuff.append( wtStr );//Y
								}
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//Z
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AA
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AB
								if(detailRowCount == 1 || detailRowCount == 2){
									strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AC
								}else if(detailRowCount == 3){
									strBuff.append( branchCodeStr );//AC
								}
								strBuff.append( (detailRowCount == 1) ? businessAreaCodeStr : AppUtil.toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( payDateStr );//AE
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( (detailRowCount == 2) ? productCodeStr18 : AppUtil.toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}else if(detailRowCount == 2 || detailRowCount == 3){
									strBuff.append( AppUtil.toLeftPaddingString("PM:"+postingDateStr, 50, ' ') );//AW
								}
								strBuff.append( (detailRowCount == 2) ? regionSapStr : AppUtil.toLeftPaddingString("", 12, ' ') );//AX
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AY
								strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( (detailRowCount == 1 || detailRowCount == 2) ? partnerCodeStr : AppUtil.toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CE
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//CF
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//CG
								strBuff.append( AppUtil.toLeftPaddingString("", 1, ' ') );//CH
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CI
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//CJ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CK
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CL
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//CM
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CN
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CO
//								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
								strBuff.append("\n");
								
							}
						}else{	//Header row 2
							for (int detailRowCount = 1; detailRowCount <= 2; detailRowCount++) {
								// Header
								strBuff.append( AppUtil.toRightPaddingString(""+headerRowCount, 3, '0') ); //A
								strBuff.append( issueDateStr );//B
								strBuff.append("DW");//C
								strBuff.append("1000");//D
								strBuff.append( issueDateStr ); //E
								strBuff.append( payDateStr.substring(4,6) ); //F
								strBuff.append( currencyStr );//G
								strBuff.append( currencyRateStr );//H
								strBuff.append( exchangeDateStr );// I
								strBuff.append( branchNameStr );//J
								strBuff.append( AppUtil.toLeftPaddingString("Payment-EPIS", 25, ' ') );//K
								strBuff.append( branchCodeStr );//L
								strBuff.append( "RFBU" );//M
								strBuff.append( payDateStr.substring(0,4) ); //N
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//O
								strBuff.append( AppUtil.toLeftPaddingString("", 5, ' ') );//P
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//Q
								
								//Detail
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //R
								strBuff.append( (detailRowCount==1)?"S":(detailRowCount==2)?"H":" " );//S
								strBuff.append( (detailRowCount==1)?"40":(detailRowCount==2)?"50":"  " );//T
								strBuff.append( "S" );//U
//								strBuff.append( accountNoStr );//V
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("21115102", 10, '0') );//V
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("21110102", 10, '0') );//V
								}
								
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//W
								strBuff.append( vatStr );//X
								strBuff.append( vatStr );//Y
								strBuff.append( amountExcludeVatStr13 );//Z
								strBuff.append( amountExcludeVatStr13 );//AA
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//AB
								strBuff.append( branchCodeStr );//AC
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AD
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AE								
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AF
								strBuff.append( AppUtil.toLeftPaddingString("", 8, ' ') );//AG
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//AH
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AI
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//AJ
								strBuff.append( AppUtil.toLeftPaddingString("", 24, ' ') );//AK
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//AL
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AM
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AN
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//AO
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AP
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AQ
								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//AR
								strBuff.append( AppUtil.toLeftPaddingString("", 12, ' ') );//AS
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//AT
								strBuff.append( AppUtil.toLeftPaddingString("", 30, ' ') );//AU
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//AV (Not Official)
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toLeftPaddingString("PM:"+postingDateStr, 50, ' ') );//AW
								}else{
									strBuff.append( AppUtil.toLeftPaddingString("", 50, ' ') );//AW (Not Official)
								}
								strBuff.append( regionSapStr );//AX
								strBuff.append( productCodeStr12 );//AY
								strBuff.append( AppUtil.toLeftPaddingString("", 20, ' ') );//AZ
								strBuff.append( AppUtil.toLeftPaddingString("", 6, ' ') );//BA
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BB
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BC
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BD
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BE
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BF
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BG
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BH
								strBuff.append( AppUtil.toLeftPaddingString("", 35, ' ') );//BI
								strBuff.append( AppUtil.toLeftPaddingString("", 10, ' ') );//BJ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BK
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BL
								strBuff.append( AppUtil.toLeftPaddingString("", 11, ' ') );//BM
								strBuff.append( AppUtil.toLeftPaddingString("", 16, ' ') );//BN
								strBuff.append( AppUtil.toLeftPaddingString("", 15, ' ') );//BO
								strBuff.append( AppUtil.toLeftPaddingString("", 18, ' ') );//BP
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BQ
								strBuff.append( AppUtil.toLeftPaddingString("", 3, ' ') );//BR
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BS
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BT
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BU
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BV
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BW
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//BX
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BY
								strBuff.append( AppUtil.toLeftPaddingString("", 2, ' ') );//BZ
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CA
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CB
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CC
								strBuff.append( AppUtil.toLeftPaddingString("", 13, ' ') );//CD
								strBuff.append( AppUtil.toRightPaddingString(""+detailRowCount, 3, '0') ); //CE
								strBuff.append( (detailRowCount==1)?"DB":(detailRowCount==2)?"OB":"  " );//CF
								if(detailRowCount == 1){
									strBuff.append( AppUtil.toRightPaddingString("21115102", 10, '0') );//CG
									strBuff.append( "S" );//CH
								}else if(detailRowCount == 2){
									strBuff.append( AppUtil.toRightPaddingString("21110102", 10, '0') );//CG
									strBuff.append( "H" );//CH
								}
								strBuff.append( amountExcludeVatStr15 );//CI 
								strBuff.append( amountExcludeVatStr15 );//CJ 
								strBuff.append( vatStr );//CK
								strBuff.append( vatStr );//CL
								strBuff.append( "MWS" );//CM
								strBuff.append( "MWAS" );//CN
								strBuff.append( branchCodeStr );//CO
//								strBuff.append( AppUtil.toLeftPaddingString("", 4, ' ') );//CP
								strBuff.append("\n");
								
							}
						}
						headerRowCount++;
					}
				}
			}
		);
		
//		String path = "D:\\temp\\";
//		writeFileData(path+"EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(new Date(), "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC", strBuff);
		File file = new File(exportOutputPath+"EPISPAYMENTSUM_QAS_PAYMENT_"+DateUtil.convertToString(date, "yyyyMMddHHmmss", DateUtil.ENG_LOCALE)+".IDOC");
		FileUtils.writeStringToFile(file, strBuff.toString(), "Cp1252");
		
	}
	@RequestMapping(value="/findGlByServiceCode.json", method=RequestMethod.GET)
	public void findGlByServiceCode(ModelMap modelMap,
			@RequestParam(value="serviceCode") String serviceCode) throws Exception {
		if(isNotBlank(serviceCode)) {
			
  			final CommonStatus<MapGLServiceTpye> CmnStatus = new CommonStatus<MapGLServiceTpye>();

			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT GL_CODE");
			queryBuilder.append(" FROM MAP_GL_SERVICE_TYPE ");
			queryBuilder.append(" WHERE  SERVICE_CODE = '"+serviceCode+"'");
			
			episJdbcTemplate.query(queryBuilder.toString(), new RowCallbackHandler(){
  				@Override
  				public void processRow(ResultSet row) throws SQLException {
  					MapGLServiceTpye mapGl = new MapGLServiceTpye();
  					mapGl.setGlCode(row.getString(1));
  					CmnStatus.addData(mapGl);
  				}
  			});
  			
  			modelMap.addAttribute("data", CmnStatus.getData());
  			modelMap.addAttribute("statusCode", "0");
  			
  		} 
}
	
}

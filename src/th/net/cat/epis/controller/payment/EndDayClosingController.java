package th.net.cat.epis.controller.payment;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.EndDayClosingDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.entity.*;
import th.net.cat.epis.repo.EpisShopRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.entity.EmpClosing;
import th.net.cat.epis.entity.EndDayClosing;
import th.net.cat.epis.entity.ShopClosing;
import th.net.cat.epis.service.EndDayClosingService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nastanda on 5/11/2017 AD.
 */
@Controller
public class EndDayClosingController {
@Autowired
    EndDayClosingService endDayClosingService;
	@Autowired
	ReceiptRepository receiptRepository;
	@Autowired
	EpisShopRepository episShopRepo;
	@Resource(name="episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	
    @ResponseBody
    @RequestMapping(value="createEndDayClosingEmp.json", method= RequestMethod.POST)
    public List<EmpClosing> createEndDayClosingEmpJSON(EndDayClosingDTO closingDTO) throws Exception {
//    public EmpClosing createEndDayClosingEmpJSON(EndDayClosingDTO closingDTO) throws Exception {
            List<EmpClosing> empClosingList = new ArrayList<EmpClosing>();
            String userName = EpContextHolder.getContext().getUserName();//EpContextHolder.getContext().getUserCode();
            String userCode = EpContextHolder.getContext().getUserCode();
            closingDTO.setClosingEmpCode(userName);
            if(StringUtils.isEmpty(closingDTO.getEmpCode())){
                closingDTO.setEmpCode(userCode);
                closingDTO.setUserName(userName);
            }
            empClosingList = endDayClosingService.createEndDayClosing(closingDTO);
//            EmpClosing rtEmpClosing = endDayClosingService.createEndDayClosing(closingDTO);
            endDayClosingService.updateEmpClosingId(empClosingList);
        return empClosingList;
    }

  //@ResponseBody
    @RequestMapping(value="createEndDayClosingEmpAll.json", method= RequestMethod.POST)
    public List<EmpClosing> createEndDayClosingEmpAllJSON(EndDayClosingDTO closingDTO) throws Exception {
        List<EmpClosing> empClosingList = new ArrayList<EmpClosing>(); //EmpClosing rtResult = new EmpClosing();
        for(EndDayClosingDTO.Employee dtl: closingDTO.getEmployees()){
            EndDayClosingDTO sendParam = new EndDayClosingDTO();
            sendParam.setEmpCode(dtl.getEmpCode());
            sendParam.setBranchCode(dtl.getBranchCode());
            sendParam.setSearchDate(closingDTO.getSearchDate());
            closingDTO.setBranchCode(dtl.getBranchCode());
//            EmpClosing empClosing = endDayClosingService.createEndDayClosing(sendParam);
//            empClosingList.add(empClosing);
        }
            empClosingList = endDayClosingService.createEndDayClosing(closingDTO);
            endDayClosingService.updateEmpClosingId(empClosingList);
        return empClosingList;
    }
    /*@RequestMapping(value="findEndDayClosingEmpGroup.json", method= RequestMethod.POST)
    public EndDayClosing findEndDayClosingEmpGroupJSON(EndDayClosingDTO closingDTO) throws Exception {
        EndDayClosing dtl = endDayClosingService.createEndDayClosing(closingDTO);
        return dtl;
    }*/
    @RequestMapping(value="findEndDayClosingPosGroup.json", method=RequestMethod.GET)
    public List<EndDayClosing> findEndDayClosingPosGroupJSON(@RequestParam(value="startDate", required=true) String searchDate
    ) throws Exception {
        //EndDayClosing dtl = endDayClosingService.createEndDayClosing(closingDTO);
        String branchCode = EpContextHolder.getContext().getBranchArea();
        List<EndDayClosing> endDayClosingList = endDayClosingService.findEndDayClosingPosGroup(searchDate, branchCode);
        if(!CollectionUtils.isEmpty(endDayClosingList) && endDayClosingList.size()>0){
            ShopClosing shopClosing = endDayClosingService.findShopClosingById(endDayClosingList.get(0).getShopClosingId1());
            for(EndDayClosing dtl: endDayClosingList){
                dtl.setShopClosing(shopClosing);
            }

        }
        return endDayClosingList;
    }
    @RequestMapping(value="findEndDayClosingEmpGroup.json", method=RequestMethod.GET)
    public List<EndDayClosing> findEndDayClosingEmpGroupJSON(@RequestParam(value="startDate", required=true) String searchDate
    ) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchArea();
        List<EndDayClosing> endDayClosingList = endDayClosingService.findEndDayClosingEmpGroup(searchDate, branchCode);
        if(!CollectionUtils.isEmpty(endDayClosingList) && endDayClosingList.size()>0){
            ShopClosing shopClosing = endDayClosingService.findShopClosingById(endDayClosingList.get(0).getShopClosingId1());
            for(EndDayClosing dtl: endDayClosingList){
                dtl.setShopClosing(shopClosing);
            }
        }
        return endDayClosingList;
    }
    @RequestMapping(value="findEndDayClosingEmpGroupBackDate.json", method=RequestMethod.GET)
    public List<EndDayClosing> findEndDayClosingEmpGroupBackDateJSON(
    ) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchArea();
        List<EndDayClosing> endDayClosingList = endDayClosingService.findEndDayClosingEmpGroupBackDate(branchCode);
        return endDayClosingList;
    }
    @RequestMapping(value="findShopIdBackDate.json", method=RequestMethod.GET)
    public List<EndDayClosing> findShopIdBackDateJSON(@RequestParam(value="startDateBackDate", required=true) String startDateBackDate
    ) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchArea();
        List<EndDayClosing> endDayClosingList = endDayClosingService.findShopIdBackDate(startDateBackDate, branchCode);
        return endDayClosingList;
    }

    /*@RequestMapping(value="findHistEndDayClosingEmpGroupBackDate.json", method=RequestMethod.GET)
    public List<EndDayClosing> findHistEndDayClosingEmpGroupBackDateJSON(@RequestParam(value="closingDate", required=true) String closingDate
    ) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchArea();
        List<EndDayClosing> endDayClosingList = endDayClosingService.findHistEndDayClosingEmpGroupBackDate(closingDate, branchCode);
        return endDayClosingList;
    }*/

    @RequestMapping(value="findShopClosingDate.json", method=RequestMethod.GET)
    public List<EndDayClosing> findHistEndDayClosingEmpGroupBackDateJSON(@RequestParam(value="startDateBackDate", required=true) String startDateBackDate
    ) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchArea();
        List<EndDayClosing> endDayClosingList = endDayClosingService.findShopClosingDate(startDateBackDate, branchCode);
        return endDayClosingList;
    }
    @RequestMapping(value="findEndDayClosingEmpGroup2.json", method=RequestMethod.GET)
    public List<EndDayClosing> findEndDayClosingEmpGroup2JSON(@RequestParam(value="startDate", required=true) String searchDate
            , @RequestParam(value="backDateStatus") String backDateStatus, @RequestParam(value="searchTransDate") String searchTransDate
    ) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchArea();
      //  String userCode = EpContextHolder.getContext().getUserName();// EpContextHolder.getContext().getUserCode();
        String userCode = EpContextHolder.getContext().getUserCode();
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String newDate = dateFormat.format(new Date());
//        if(!StringUtils.equals(backDateStatus, AppConstants.ENDDAY_CLOSE_BACKDATE)){
        if (StringUtils.isNotBlank(searchDate)) {
            /*if (newDate.compareTo(searchDate)>=0) {
                endDayClosingList = endDayClosingService.findEndDayClosingEmpGroup(searchDate, branchCode, userCode);
            } else {
                endDayClosingList = endDayClosingService.findEndDayClosingEmpGroupBackDate(searchDate, branchCode, userCode);
            }*/
            endDayClosingList = endDayClosingService.findEndDayClosingEmpGroup(searchDate, branchCode, userCode);
            if (endDayClosingList == null || endDayClosingList.size() == 0) {
                endDayClosingList = endDayClosingService.findEndDayClosingEmpGroupBackDate(searchDate, branchCode, userCode);
            }
        }else{
            if (StringUtils.isNotBlank(searchTransDate)) {
                endDayClosingList = endDayClosingService.findEndDayClosingEmpGroupBackDateByTransDate(searchTransDate, branchCode, userCode);
            }
        }
        for(EndDayClosing dtl: endDayClosingList){
            if(dtl.getEmpClosingId1()!=null){
                EmpClosing emp = new EmpClosing();
                emp = endDayClosingService.findEmpClosingById(dtl.getEmpClosingId1());
                //emp.setId(dtl.getEmpClosingId());
                dtl.setEmpClosing(emp);
            }
            if(dtl.getEmpClosing()!=null) {
                dtl.setClosingDtStr(AppUtil.formatter_EN.format(dtl.getEmpClosing().getCreateDttm()));
                dtl.setClosingTimeStr(AppUtil.formatter_EN_TIME.format(dtl.getEmpClosing().getCreateDttm()));
            }else{
                dtl.setClosingDtStr(AppUtil.formatter_EN.format(new java.sql.Date(new Date().getTime())));
                dtl.setClosingTimeStr(AppUtil.formatter_EN_TIME.format(new java.sql.Date(new Date().getTime())));
            }
        }
        return endDayClosingList;
    }
    @ResponseBody
    @RequestMapping(value="createShopClosing.json", method=RequestMethod.POST)
    public ShopClosing createShopClosingJSON(EndDayClosingDTO closingDTO) throws Exception {
        SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");
        String branchCode = EpContextHolder.getContext().getBranchArea();
        //String userCode = EpContextHolder.getContext().getUserName();//EpContextHolder.getContext().getUserCode();
        String userCode = EpContextHolder.getContext().getUserCode();
        ShopClosing shopClosing = new ShopClosing();
        Date startDate = DateUtils.truncate(new Date(), Calendar.DATE);
        Date endDate = DateUtils.addMilliseconds(DateUtils.ceiling(new Date(), Calendar.DATE), -1);


       // if(!CollectionUtils.isEmpty(closingDTO.getEndDayClosingList()) && closingDTO.getEndDayClosingList().size()>0){
            shopClosing.setClosingDate(new java.sql.Date(textFormat.parse(closingDTO.getSearchDate()).getTime()));
            shopClosing.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
            shopClosing.setCreateDttm(new java.sql.Date(new Date().getTime()));
            shopClosing.setCreateUser(userCode);
            shopClosing.setUpdateDttm(new java.sql.Date(new Date().getTime()));
            shopClosing.setUpdateUser(userCode);
            shopClosing.setBranchCode(branchCode);
            shopClosing.setClosingEmpCode(EpContextHolder.getContext().getUserCode());
            //shopClosing.setClosingEmpCode(EpContextHolder.getContext().getUserName());
            shopClosing.setClosingEmpName(EpContextHolder.getContext().getUserGivenName()+" "+EpContextHolder.getContext().getUserFamilyName());
            //mark open_shop_id in tb shopClosing
            try {
                EpisShop episShop = episShopRepo.findByBranchAreaAndShopOpenDttm(branchCode, startDate, endDate);
                if (episShop != null) {
                    shopClosing.setOpenShopId(episShop.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            shopClosing = endDayClosingService.createShopClosing(shopClosing);
       // }
        return shopClosing;
    }

    @ResponseBody
    @RequestMapping(value="createShopClosingBackDate.json", method=RequestMethod.POST)
    public ShopClosing createShopClosingBackDateJSON(EndDayClosingDTO closingDTO) throws Exception {
        //SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");

        Map<Long, EndDayClosingDTO.Employee> closingMap = new HashMap<Long, EndDayClosingDTO.Employee>();
        for (EndDayClosingDTO.Employee data : closingDTO.getEmployees()) {
            closingMap.put(data.getClosingId(), data);
        }

      /*  EndDayClosing endDayClosingList = new EndDayClosing();
        Iterable<EndDayClosing> receipts = receiptRepo.findAll(receiptMap.keySet());*/

        String branchCode = EpContextHolder.getContext().getBranchArea();
        String userCode = EpContextHolder.getContext().getUserCode();
        String userName =EpContextHolder.getContext().getUserName();// EpContextHolder.getContext().getUserCode();
        ShopClosing shopClosing = new ShopClosing();
        // if(!CollectionUtils.isEmpty(closingDTO.getEndDayClosingList()) && closingDTO.getEndDayClosingList().size()>0){
        //shopClosing.setClosingDate(new java.sql.Date(textFormat.parse(closingDTO.getSearchDate()).getTime()));
        shopClosing.setClosingDate(new java.sql.Date(new Date().getTime()));
        shopClosing.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
        shopClosing.setBackdateStatus(AppConstants.ENDDAY_CLOSE_BACKDATE);
        shopClosing.setCreateDttm(new java.sql.Date(new Date().getTime()));
        shopClosing.setCreateUser(userCode);
        shopClosing.setUserName(userName);
        shopClosing.setUpdateDttm(new java.sql.Date(new Date().getTime()));
        shopClosing.setUpdateUser(userCode);
        shopClosing.setBranchCode(branchCode);
        shopClosing.setClosingEmpCode(EpContextHolder.getContext().getUserCode());
        //shopClosing.setClosingEmpCode(EpContextHolder.getContext().getUserName());
        shopClosing.setClosingEmpName(EpContextHolder.getContext().getUserGivenName()+" "+EpContextHolder.getContext().getUserFamilyName());
        shopClosing = endDayClosingService.createShopClosingBackDate(shopClosing, closingDTO);
        // }

        return shopClosing;
    }

    @ResponseBody
    @RequestMapping(value="updateEmpClosing.json", method=RequestMethod.POST)
    public Boolean updateEmpClosing(EndDayClosingDTO closingDTO) throws Exception {
        Boolean updateFlg = false;

        return updateFlg = endDayClosingService.updateShopClosingId(closingDTO);
    }

    @ResponseBody
    @RequestMapping(value="updateShopClosing.json", method=RequestMethod.POST)
    public Boolean updateShopClosing(EndDayClosingDTO closingDTO) throws Exception {
        Boolean updateFlg = false;

        return updateFlg = endDayClosingService.updateShopClosingId(closingDTO);
    }

    @RequestMapping(value="findDayEndClosingStatusClose.json", method=RequestMethod.GET)
    public List<EndDayClosing> findDayEndClosingStatusCloseJSON( HttpServletRequest request,HttpServletResponse response,@RequestParam(value="startDate", required=true) String searchDate
            , @RequestParam(value="backDateStatus") String backDateStatus, @RequestParam(value="searchTransDate") String searchTransDate
    ) throws Exception {

        String branchCode = EpContextHolder.getContext().getBranchArea();
        //String userCode = EpContextHolder.getContext().getUserName();//EpContextHolder.getContext().getUserCode();
        String userCode = EpContextHolder.getContext().getUserCode();
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();

        endDayClosingList = endDayClosingService.findEndDayClosingEmp(searchDate, searchTransDate, branchCode, userCode, backDateStatus);


        return endDayClosingList;
    }
}

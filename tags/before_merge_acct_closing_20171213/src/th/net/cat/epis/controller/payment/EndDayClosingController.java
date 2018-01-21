package th.net.cat.epis.controller.payment;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.dto.CreatePaymentResultDTO;
import th.net.cat.epis.dto.EndDayClosingDTO;
import th.net.cat.epis.dto.SettlePaymentDTO;
import th.net.cat.epis.entity.EmpClosing;
import th.net.cat.epis.entity.EndDayClosing;
import th.net.cat.epis.entity.ShopClosing;
import th.net.cat.epis.service.EndDayClosingService;
import th.net.cat.epis.util.AppConstants;
import th.net.cat.epis.util.AppUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nastanda on 5/11/2017 AD.
 */
@Controller
public class EndDayClosingController {
@Autowired
    EndDayClosingService endDayClosingService;
    @ResponseBody
    @RequestMapping(value="createEndDayClosingEmp.json", method= RequestMethod.POST)
    public EmpClosing createEndDayClosingEmpJSON(EndDayClosingDTO closingDTO) throws Exception {
            String userCode = EpContextHolder.getContext().getUserCode();
            closingDTO.setClosingEmpCode(userCode);
            if(StringUtils.isEmpty(closingDTO.getEmpCode())){
                closingDTO.setEmpCode(userCode);
            }
            EmpClosing rtEmpClosing = endDayClosingService.createEndDayClosing(closingDTO);
        return rtEmpClosing;
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
            EmpClosing empClosing = endDayClosingService.createEndDayClosing(sendParam);
            empClosingList.add(empClosing);
        }
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
    @RequestMapping(value="findEndDayClosingEmpGroup2.json", method=RequestMethod.GET)
    public List<EndDayClosing> findEndDayClosingEmpGroup2JSON(@RequestParam(value="startDate", required=true) String searchDate
                                                              , @RequestParam(value="backDateStatus") String backDateStatus
    ) throws Exception {
        String branchCode = EpContextHolder.getContext().getBranchArea();
        String userCode = EpContextHolder.getContext().getUserCode();
        List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
        if(!StringUtils.equals(backDateStatus, AppConstants.ENDDAY_CLOSE_BACKDATE)){
            endDayClosingList = endDayClosingService.findEndDayClosingEmpGroup(searchDate, branchCode, userCode);
        }else{
            endDayClosingList = endDayClosingService.findEndDayClosingEmpGroupBackDate(searchDate, branchCode, userCode);
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
        String userCode = EpContextHolder.getContext().getUserCode();
        ShopClosing shopClosing = new ShopClosing();
       // if(!CollectionUtils.isEmpty(closingDTO.getEndDayClosingList()) && closingDTO.getEndDayClosingList().size()>0){
            shopClosing.setClosingDate(new java.sql.Date(textFormat.parse(closingDTO.getSearchDate()).getTime()));
            shopClosing.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
            shopClosing.setCreateDttm(new java.sql.Date(new Date().getTime()));
            shopClosing.setCreateUser(userCode);
            shopClosing.setUpdateDttm(new java.sql.Date(new Date().getTime()));
            shopClosing.setUpdateUser(userCode);
            shopClosing.setBranchCode(branchCode);
            shopClosing.setClosingEmpCode(EpContextHolder.getContext().getUserCode());
            shopClosing.setClosingEmpName(EpContextHolder.getContext().getUserGivenName()+" "+EpContextHolder.getContext().getUserFamilyName());
            shopClosing = endDayClosingService.createShopClosing(shopClosing);
       // }
        return shopClosing;
    }

    @ResponseBody
    @RequestMapping(value="createShopClosingBackDate.json", method=RequestMethod.POST)
    public ShopClosing createShopClosingBackDateJSON(EndDayClosingDTO closingDTO) throws Exception {
        //SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");
        String branchCode = EpContextHolder.getContext().getBranchArea();
        String userCode = EpContextHolder.getContext().getUserCode();
        ShopClosing shopClosing = new ShopClosing();
        // if(!CollectionUtils.isEmpty(closingDTO.getEndDayClosingList()) && closingDTO.getEndDayClosingList().size()>0){
        //shopClosing.setClosingDate(new java.sql.Date(textFormat.parse(closingDTO.getSearchDate()).getTime()));
        shopClosing.setClosingDate(new java.sql.Date(new Date().getTime()));
        shopClosing.setDocStatus(AppConstants.ENDDAY_CLOSE_STATUS_S);
        shopClosing.setBackdateStatus(AppConstants.ENDDAY_CLOSE_BACKDATE);
        shopClosing.setCreateDttm(new java.sql.Date(new Date().getTime()));
        shopClosing.setCreateUser(userCode);
        shopClosing.setUpdateDttm(new java.sql.Date(new Date().getTime()));
        shopClosing.setUpdateUser(userCode);
        shopClosing.setBranchCode(branchCode);
        shopClosing.setClosingEmpCode(EpContextHolder.getContext().getUserCode());
        shopClosing.setClosingEmpName(EpContextHolder.getContext().getUserGivenName()+" "+EpContextHolder.getContext().getUserFamilyName());
        shopClosing = endDayClosingService.createShopClosingBackDate(shopClosing);
        // }
        return shopClosing;
    }
}

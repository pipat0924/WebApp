package th.net.cat.epis.controller.management;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import th.net.cat.epis.entity.MasAgent;
import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.repo.AgentRepository;
import th.net.cat.epis.repo.ReceiptRepository;
import th.net.cat.epis.util.AppUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Created by puthy on 5/28/17.
 */
@Controller
public class AgentController {

    @Autowired AgentRepository agentRepository;
    @Autowired ReceiptRepository receiptRepo;
    @Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;

    @ResponseBody
    @RequestMapping(value="/saveAgent.json", method = RequestMethod.POST)
    public Long saveAgent(HttpServletRequest request, @RequestBody MasAgent masAgent) throws java.lang.Exception {
        if(masAgent.getId() == null){
            if(agentRepository.findByCode(masAgent.getCode()).size() == 0){
                masAgent.setId(masAgent.getId());
                agentRepository.save(masAgent);
                return masAgent.getId();
            }else{
                return null;
            }
        }
        else{
            masAgent.setId(masAgent.getId());
            agentRepository.save(masAgent);
            return masAgent.getId();
        }
    }

    @ResponseBody
    @RequestMapping(value="/findAgentByCodeOrName.json", method=RequestMethod.POST)
    public List<MasAgent> findAgentByCodeOrNameJSON(@RequestBody MasAgent masAgent) throws java.lang.Exception {
        if(isNotBlank(masAgent.getCode()) && !isNotBlank(masAgent.getName())){
            return agentRepository.findByCodeStartingWithIgnoreCase(masAgent.getCode());
        }else if(!isNotBlank(masAgent.getCode()) && isNotBlank(masAgent.getName())){
            return agentRepository.findByNameStartingWithIgnoreCase(masAgent.getName());
        }else if(isNotBlank(masAgent.getCode()) && isNotBlank(masAgent.getName())) {
            return agentRepository.findAgentByCodeOrName(masAgent.getCode(),masAgent.getName());
        } return null;
    }

    @ResponseBody
    @RequestMapping(value="/findAgentDetailById.json", method=RequestMethod.GET)
    public MasAgent findAgentDetailById(ModelMap modelMap,
                                    @RequestParam(value="id") String id) throws java.lang.Exception {
        if(isNotBlank(id)) {
            return agentRepository.findOne(new Long(id));
        } return null;
    }

    @RequestMapping(value="loadMenuListPaymentAgent.json", method=RequestMethod.GET)
    public void loadMenuListPaymentAgent(ModelMap modelMap) {
        modelMap.addAttribute("data", agentRepository.findByIsPositive(Long.valueOf(1)));
        modelMap.addAttribute("statusCode", "0");
    }

    @RequestMapping(value="findAgentType.json", method=RequestMethod.GET)
    public void findAgentTypeJSON(ModelMap modelMap) {
        modelMap.addAttribute("data", agentRepository.findAllOrderByIdAsc());
        modelMap.addAttribute("statusCode", "0");
    }

    // picht 20171017
    @RequestMapping(value="findAllAgentType.json", method=RequestMethod.GET)
    public void findAllAgentTypeJSON(ModelMap modelMap) {
        modelMap.addAttribute("data", agentRepository.findAllOrderByNameAsc());
        modelMap.addAttribute("statusCode", "0");
    }
    // end picht 20171017

 @ResponseBody
 @RequestMapping(value="findAgentHisty.json", method=RequestMethod.GET)
 public List<Receipt> findAgentHistyJSON(HttpServletRequest request,
         @RequestParam(value="accountNo", required=true) String accountNo,
         @RequestParam(value="receiptNo", required=true) String receiptNo,
         @RequestParam(value="taxId", required=true) String taxId,
         @RequestParam(value="refCode", required=true) String refCode,
         @RequestParam(value="paymentType", required=true) String paymentType
 ) throws Exception {
     List<Receipt> rcptList = new ArrayList<Receipt>();

     if (!StringUtils.isBlank(receiptNo)) {
         rcptList = receiptRepo.findByNoStartingWithAndPayment_TypeOrderByUpdateDttmDescIdAsc(receiptNo, paymentType);
     } else
     if (!StringUtils.isBlank(taxId)) {
         if (refCode.equalsIgnoreCase("ALL")) {
             rcptList = receiptRepo.findByTaxNoAgentStartingWithAndPayment_TypeOrderByUpdateDttmDescIdAsc(taxId, paymentType);
         } else {
             rcptList = receiptRepo.findByRef1AndTaxNoAgentAndPayment_TypeOrderByUpdateDttmDescIdAsc(refCode, taxId, paymentType);
         }
     } else {
         if (refCode.equalsIgnoreCase("ALL")) {
             rcptList = receiptRepo.findByPayment_TypeOrderByUpdateDttmDescIdAsc(paymentType);
             /*List<MasAgent> masAgents = agentRepository.findAllOrderByIdAsc();
             StringBuilder taxList = new StringBuilder();

             for (MasAgent masA : masAgents) {
                 taxList.append(masA.getTaxId() + " ");

             }

             String taxs = taxList.toString();//.substring(0, taxList.length()-2);
             List<String> taxNoAgent = Arrays.asList(taxs.split(" "));


             rcptList = receiptRepo.findByTaxNoAgentAndPayment_Type(taxNoAgent ,paymentType);*/

         } else {
             rcptList = receiptRepo.findByRef1AndPayment_TypeOrderByUpdateDttmDescIdAsc(refCode, paymentType);
         }
     }

     for(Receipt rpt: rcptList){
         //String maskCC = rpt.getPayment().getMethod()!=null?AppUtil.maskCreditCardFromString(rpt.getPayment().getMethod(), "************####"):"";
//            String paymentCase = AppUtil.hideWTNumber(maskCC);
//            rpt.setPaymentCase(paymentCase);

         rpt.setUpdateDttmStr(AppUtil.formatter_EN.format(rpt.getUpdateDttm())+" "+AppUtil.formatter_EN_TIME.format(rpt.getUpdateDttm()));
         //set charge + total charge set in Amount
         rpt.setAmount(BigDecimal.ZERO);
         BigDecimal feesIncome = rpt.getFeesIncome()!=null?rpt.getFeesIncome():BigDecimal.ZERO;
         BigDecimal totalCharge = rpt.getTotalCharge()!=null?rpt.getTotalCharge():BigDecimal.ZERO;
         BigDecimal totalChargeAddChange = totalCharge.add(feesIncome);
         rpt.setAmount(totalChargeAddChange);
         List<MasAgent> agentList = new ArrayList<MasAgent>();
         //get Agent Name
         if (!StringUtils.isBlank(rpt.getRef1())) {
             agentList = agentRepository.findByCode(rpt.getRef1());

             if (agentList!=null){
                 for (MasAgent agent : agentList) {
                     rpt.setBillingServiceName(agent.getName());
                 }

             }
         }
     }
    return rcptList;
}



}

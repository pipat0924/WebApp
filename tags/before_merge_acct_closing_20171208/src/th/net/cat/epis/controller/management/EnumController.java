package th.net.cat.epis.controller.management;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.i18n.Exception;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.epis.dto.EnumDTO;
import th.net.cat.epis.dto.SrcAddrDTO;
import th.net.cat.epis.entity.Enum;
import th.net.cat.epis.repo.EnumRepository;
import th.net.cat.epis.util.AppConstants;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Controller
public class EnumController {
	
	@Autowired EnumRepository enumRepository;
	private final String SRC_CATEGORY = "source";
	@ResponseBody
	@RequestMapping(value="/findEnumById.json", method=RequestMethod.GET)
	public EnumDTO findEnumById(HttpServletRequest request
			,@RequestParam(value="id") Long id
			) throws Exception {
		
		EnumDTO enumDto = new EnumDTO();
		Enum enumEnt = enumRepository.findOne(id);
		th.net.cat.epis.dto.Enum enumObj = new th.net.cat.epis.dto.Enum();
		BeanUtils.copyProperties(enumEnt, enumObj);
		List<th.net.cat.epis.dto.Enum> enumList = new ArrayList<th.net.cat.epis.dto.Enum>();
		enumList.add(enumObj);
		enumDto.setData(enumList);
		return enumDto;
	}

	@ResponseBody
	@RequestMapping(value="/saveEnum.json", method=RequestMethod.POST)
	public Long saveEnum(HttpServletRequest request
			,@RequestBody th.net.cat.epis.dto.Enum enumBean
			) throws Exception {
		return saveEnum(enumBean);
	}
	
	private Long saveEnum(th.net.cat.epis.dto.Enum enumBean) throws Exception {
		Enum enumEnt;
		if(enumBean.getId() == null){
			enumEnt = new Enum();
		}else{
			enumEnt = enumRepository.findOne(enumBean.getId());
		}
		enumEnt.setCategory(enumBean.getCategory());
		enumEnt.setCode(enumBean.getCode());
		enumEnt.setMapCode1(enumBean.getMapCode1());
		enumEnt.setMapCode2(enumBean.getMapCode2());
		enumEnt.setMapCode3(enumBean.getMapCode3());
		enumEnt.setMapCode4(enumBean.getMapCode4());
		enumEnt.setDescFullEn(enumBean.getDescFullEn());
		enumEnt.setDescFullTh(enumBean.getDescFullTh());
		enumEnt.setDescAbvrEn(enumBean.getDescAbvrEn());
		enumEnt.setDescAbvrTh(enumBean.getDescAbvrTh());
//		enumEnt.setIsPositive(enumBean.getIsPositive());
		enumRepository.save(enumEnt);
		return enumEnt.getId();
	}
	
//	Start Authen
	@ResponseBody
	@RequestMapping(value="/findAuthenByMsgCode.json", method=RequestMethod.POST)
	public EnumDTO findAuthenByMsgCode(HttpServletRequest request, @RequestBody th.net.cat.epis.dto.Enum enumParam) throws Exception {
		
		EnumDTO enumDto = new EnumDTO();
		List<Enum> enumEntList = enumRepository.findByCategoryAndCodeContainingIgnoreCase(AppConstants.ENUM_CATEGORY_AUTHEN, enumParam.getCode());
		if(enumEntList != null && enumEntList.size() > 0){
			List<th.net.cat.epis.dto.Enum> enumList = new ArrayList<th.net.cat.epis.dto.Enum>();
			for (Enum enumEnt : enumEntList) {
				th.net.cat.epis.dto.Enum enumObj = new th.net.cat.epis.dto.Enum();
				BeanUtils.copyProperties(enumEnt, enumObj);
				enumList.add(enumObj);
			}
			enumDto.setData(enumList);
		}
		return enumDto;
	}

	@ResponseBody
	@RequestMapping(value="/saveAuthen.json", method=RequestMethod.POST)
	public Long saveAuthen(HttpServletRequest request
			,@RequestBody th.net.cat.epis.dto.Enum enumBean
			) throws Exception {

		enumBean.setCategory(AppConstants.ENUM_CATEGORY_AUTHEN);
		return saveEnum(enumBean);
	}
//	End Authen
	
//	Start GL
	@ResponseBody
	@RequestMapping(value="/loadPaymentGlCategoryGroup.json", method=RequestMethod.GET)
	public List<String> loadPaymentGlCategoryGroup(ModelMap modelMap) throws Exception {
		List<String> enumEntList = enumRepository.findCategoryGroup(AppConstants.ENUM_PAYMENT_GL_CATEGORY_GROUP);
		return enumEntList;
	}
	
	@ResponseBody
	@RequestMapping(value="/findGlByCriteria.json", method=RequestMethod.POST)
	public EnumDTO findGlByCriteria(HttpServletRequest request, @RequestBody th.net.cat.epis.dto.Enum enumParam) throws Exception {

		EnumDTO enumDto = new EnumDTO();
		List<Enum> enumEntList = enumRepository.findByCategoryAndCodeContainingIgnoreCaseAndMapCode1ContainingIgnoreCaseAndMapCode2ContainingIgnoreCase(enumParam.getCategory(), enumParam.getCode(), enumParam.getMapCode1(), enumParam.getMapCode2());
		if(enumEntList != null && enumEntList.size() > 0){
			List<th.net.cat.epis.dto.Enum> enumList = new ArrayList<th.net.cat.epis.dto.Enum>();
			for (Enum enumEnt : enumEntList) {
				th.net.cat.epis.dto.Enum enumObj = new th.net.cat.epis.dto.Enum();
				BeanUtils.copyProperties(enumEnt, enumObj);
				enumList.add(enumObj);
			}
			enumDto.setData(enumList);
		}
		return enumDto;
	}
//	End GL
	
//  Start Category Detail
	@ResponseBody
	@RequestMapping(value="/loadAllCategoryGroup.json", method=RequestMethod.GET)
	public List<String> loadAllCategoryGroup(ModelMap modelMap) throws Exception {
		List<String> enumEntList = enumRepository.findAllCategoryGroup();
		return enumEntList;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveSourceAddress.json", method=RequestMethod.POST)
	public Long saveSourceAddress(HttpServletRequest request
			,@RequestBody th.net.cat.epis.dto.SrcAddrDTO comDto
	) throws Exception {
		th.net.cat.epis.dto.Enum enumBean = new th.net.cat.epis.dto.Enum();
		//Enum enumBean = new Enum();
		String[] addr; String comAddress = "";
		enumBean.setCategory(SRC_CATEGORY);
		enumBean.setCode(comDto.getComCode());
		addr = comDto.getComAddress().split("\n");
		for(int i0=0;i0<addr.length;i0++){
			if(i0!=addr.length-1)
				comAddress +=addr[i0].trim()+"\r\n";
			else
				comAddress +=addr[i0].trim();
		}

		enumBean.setDescFullTh(comDto.getComName()+"\r\n"+comAddress+"\r\nเลขประจำตัวผู้เสียภาษีอากร: "+comDto.getTaxId());
		enumBean.setId(comDto.getId());
		//enumRepository.save(enumBean);
		Long rt = saveEnum2(enumBean);
		return rt;
		//return comDto.getId();
	}
	private Long saveEnum2(th.net.cat.epis.dto.Enum enumBean) throws Exception {
		Enum enumEnt;
		if(enumBean.getId() == null){
			List<Enum> enumList = enumRepository.findByCategoryAndCode(SRC_CATEGORY, enumBean.getCode());
			if(enumList.size()>0){
				return 0L;
			}
			enumEnt = new Enum();
		}else{
			enumEnt = enumRepository.findOne(enumBean.getId());
		}
		enumEnt.setCategory(enumBean.getCategory());
		enumEnt.setCode(enumBean.getCode());
		enumEnt.setMapCode1(enumBean.getMapCode1());
		enumEnt.setMapCode2(enumBean.getMapCode2());
		enumEnt.setMapCode3(enumBean.getMapCode3());
		enumEnt.setMapCode4(enumBean.getMapCode4());
		enumEnt.setDescFullEn(enumBean.getDescFullEn());
		enumEnt.setDescFullTh(enumBean.getDescFullTh());
		enumEnt.setDescAbvrEn(enumBean.getDescAbvrEn());
		enumEnt.setDescAbvrTh(enumBean.getDescAbvrTh());
		enumEnt.setIsPositive(1);
			enumRepository.save(enumEnt);

		return enumEnt.getId();
	}
	@ResponseBody
	@RequestMapping(value="/findSourceAddressByCriteria.json", method=RequestMethod.POST)
	public List<SrcAddrDTO> findSourceAddressByCriteria(HttpServletRequest request, @RequestBody th.net.cat.epis.dto.SrcAddrDTO enumParam) throws Exception {

		EnumDTO enumDto = new EnumDTO();
		List<Enum> enumEntList = new ArrayList<Enum>();
		enumEntList = enumRepository.findBySearchCriteria2(SRC_CATEGORY, enumParam.getComName());
        List<SrcAddrDTO> dtos = new ArrayList<SrcAddrDTO>();
		if(enumEntList != null && enumEntList.size() > 0){
            String[] arr;
			String[] txArr;
			for (Enum enumEnt : enumEntList) {

                arr = enumEnt.getDescFullTh().split("\n");
                SrcAddrDTO addrDtl = new SrcAddrDTO();
				String address = "";

                addrDtl.setComCode(enumEnt.getCode());
                addrDtl.setComName(arr[0]);
				for(int i = 1; i<arr.length-1;i++){
					address = address+arr[i]+"<br>";
				}
                addrDtl.setComAddress(address);
				txArr = arr[arr.length-1].split(" ");
                addrDtl.setTaxId(txArr[txArr.length-1]);
				addrDtl.setId(enumEnt.getId());
                dtos.add(addrDtl);
			}
			//enumDto.setData(dtos);
		}
		//return enumDto;
        return dtos;
	}

	
	@ResponseBody
	@RequestMapping(value="/findCategoryDetailByCriteria.json", method=RequestMethod.POST)
	public EnumDTO findCategoryDetailByCriteria(HttpServletRequest request, @RequestBody th.net.cat.epis.dto.Enum enumParam) throws Exception {

		EnumDTO enumDto = new EnumDTO();
		List<Enum> enumEntList = enumRepository.findBySearchCriteria //enumRepository.findByCategoryAndCodeContainingIgnoreCaseAndMapCode1ContainingIgnoreCaseAndMapCode2ContainingIgnoreCaseAndMapCode3ContainingIgnoreCaseAndMapCode4ContainingIgnoreCaseAndDescFullEnContainingIgnoreCaseAndDescFullThContainingIgnoreCaseAndDescAbvrEnContainingIgnoreCaseAndDescAbvrThContainingIgnoreCase
								(
									enumParam.getCategory()
									,enumParam.getCode()
									,enumParam.getMapCode1()
									,enumParam.getMapCode2()
									,enumParam.getMapCode3()
									,enumParam.getMapCode4()
									,enumParam.getDescFullEn()
									,enumParam.getDescFullTh()
									,enumParam.getDescAbvrEn()
									,enumParam.getDescAbvrTh()
								);
//		List<Enum> enumEntList = enumRepository.findByCategoryAndCodeContainingIgnoreCaseAndMapCode1ContainingIgnoreCaseAndMapCode2ContainingIgnoreCase(enumParam.getCategory(), enumParam.getCode(), enumParam.getMapCode1(), enumParam.getMapCode2());
		if(enumEntList != null && enumEntList.size() > 0){
			List<th.net.cat.epis.dto.Enum> enumList = new ArrayList<th.net.cat.epis.dto.Enum>();
			for (Enum enumEnt : enumEntList) {
				th.net.cat.epis.dto.Enum enumObj = new th.net.cat.epis.dto.Enum();
				BeanUtils.copyProperties(enumEnt, enumObj);
				enumList.add(enumObj);
			}
			enumDto.setData(enumList);
		}
		return enumDto;
	}
//  End Category Detail

	
	
	@ResponseBody

	@RequestMapping(value="/findSourceAddressById.json", method=RequestMethod.GET)
	public SrcAddrDTO findSourceAddressById(HttpServletRequest request
			,@RequestParam(value="id") Long id
	) throws Exception {
		EnumDTO enumDto = new EnumDTO();
		String[] arr;
		String[] txArr;
		Enum enumEnt = enumRepository.findOne(id);
			arr = enumEnt.getDescFullTh().split("\n");
			SrcAddrDTO addrDtl = new SrcAddrDTO();
			String address = "";

			addrDtl.setComCode(enumEnt.getCode());
			addrDtl.setComName(arr[0]);
			for(int i = 1; i<arr.length-1;i++){
				if(i!=arr.length-1){
					address = address+arr[i]+"\n";
				}else{
					address = address+arr[i];
				}
			}
			addrDtl.setComAddress(address);
			txArr = arr[arr.length-1].split(" ");
			addrDtl.setTaxId(txArr[txArr.length-1]);
			addrDtl.setId(enumEnt.getId());

		return addrDtl;
	}
	@RequestMapping(value="/findVatRateByCategory.json", method=RequestMethod.GET)
	public EnumDTO findVatRateByCategory(HttpServletRequest request
			,@RequestParam(value="category") String category
	) throws Exception {
		category = "vat.type";
		EnumDTO enumDto = new EnumDTO();
		List<th.net.cat.epis.dto.Enum> enumDtoList = new ArrayList<th.net.cat.epis.dto.Enum>();
		List<Enum> enumList = enumRepository.findByCategory(category);
		for(Enum enumEnt: enumList){
			th.net.cat.epis.dto.Enum enumObj = new th.net.cat.epis.dto.Enum();
			BeanUtils.copyProperties(enumEnt, enumObj);
			enumDtoList.add(enumObj);
		}

		enumDto.setData(enumDtoList);
		return enumDto;
	}




}

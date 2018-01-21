package th.net.cat.epis.controller.management;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import th.net.cat.epis.entity.DW;
import th.net.cat.epis.repo.DWRepository;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.List;



@Controller
public class DWController {
	@Autowired DWRepository dwRepository;

    @ResponseBody
	@RequestMapping(value="findDwListJSON.json", method=RequestMethod.POST)
	public List<DW> findDwListJSON(ModelMap modelMap,
			@RequestParam(value="postDate") String postDate) throws Exception {
		if(isNotBlank(postDate)) {
			
			System.out.println("Controller findDwListJSON postDate : "+postDate);
			
			List<DW> result = dwRepository.findByPostDate(postDate);
			
			
			return result; 
		} return null;
		}
}

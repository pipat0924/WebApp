package th.net.cat.epis.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import th.net.cat.epis.entity.Enum;
import th.net.cat.epis.entity.Office;
import th.net.cat.epis.repo.OfficeRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Created by T'Tee Puthy on 1/31/2017.
 */
@Controller
public class OfficeController {
    @Autowired OfficeRepository officeRepository;

    @ResponseBody
    @RequestMapping(value="/saveOffice.json", method = RequestMethod.POST)
    public Long saveUnit(HttpServletRequest request, @RequestBody Office offices) throws Exception {
        offices.setId(offices.getId());
        officeRepository.save(offices);
        return offices.getId();
    }
    @ResponseBody
    @RequestMapping(value="/findOfficeByBuPlaceCodeOrBuAreaCodeOrBuAreaNameAttribute.json", method=RequestMethod.POST)
    public List<Office> findOfficeByBuPlaceCodeOrBuAreaCodeOrBuAreaNameAttributeJSON(@RequestBody Office offices) throws Exception {
        if(isNotBlank(offices.getBuPlaceCode()) || isNotBlank(offices.getBuAreaCode()) || isNotBlank(offices.getBuAreaName())) {
            return officeRepository.findByBuPlaceCodeOrBuAreaCodeOrBuAreaName(offices.getBuPlaceCode(), offices.getBuAreaCode(), offices.getBuAreaName());
        } return null;
    }

    @ResponseBody
    @RequestMapping(value="/findOfficeDetailById.json", method=RequestMethod.GET)
    public Office findUnitDetailByIdJSON(ModelMap modelMap,
                                       @RequestParam(value="id") String id) throws Exception {
        if(isNotBlank(id)) {
            return officeRepository.findOne(new Long(id));
        } return null;
    }

}

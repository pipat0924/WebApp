package th.net.cat.epis.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import th.net.cat.epis.repo.EnumRepository;

/**
 * Created by T'Tee Puthy on 1/25/2017.
 */
@Controller
public class UnitController {

    @Autowired EnumRepository enumRepository;

}

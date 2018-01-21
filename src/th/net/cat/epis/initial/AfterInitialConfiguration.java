package th.net.cat.epis.initial;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import javax.annotation.PostConstruct;
        import javax.servlet.ServletContext;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import th.net.cat.epis.dto.MasterDataDTO;
        import th.net.cat.epis.entity.MasterData;
        import th.net.cat.epis.repo.MasterDataRepository;
        import th.net.cat.epis.util.AppConstants;

@Component
public class AfterInitialConfiguration {

    @Autowired
    private MasterDataRepository masterDataRepository;
    @Autowired
    private ServletContext servletContext;

    public static final Map<String, List<MasterDataDTO>> masterDataLookup = new HashMap<String, List<MasterDataDTO>>();

    @PostConstruct
    public void doAfterInitialConfig() {
    	masterDataLookup.clear();
        List<MasterData> masterDataList = (List<MasterData>) masterDataRepository.findAll();
        List<MasterDataDTO> dtoList;
        MasterDataDTO dto;
        
        if (masterDataList != null) {
            for (MasterData entity : masterDataList) {
            	dto = new MasterDataDTO(entity);
            	dtoList = masterDataLookup.get(dto.getGroupKey());
                if(dtoList == null) {
                	dtoList = new ArrayList<MasterDataDTO>();
                }
        	    dtoList.add(dto);
        	    masterDataLookup.put(dto.getGroupKey(), dtoList);
            }
        }
        
        servletContext.setAttribute(AppConstants.MASTER_DATA_LOOKUP, masterDataLookup);
        
//        servletContext.setAttribute(AppConstants.MASTER_DATA_LOOKUP, masterDataLookup);
//		<div class="col-sm-4"><input class="form-control" value="${(applicationScope['MASTER_DATA_LOOKUP'])['Test 2'].value}"></div>
//		var name = '<%= session.getAttribute("userName") %>';
        
    }
}

/*
@Component
public class StartupHousekeeper {
	@Autowired MasterDataRepository masterDataRepository;
  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() {	System.out.println("+++++++++++++++++++++++++ StartupHousekeeper ++++++++++++++++++++++++");
	  Map<String, MasterDataDTO> constants = new HashMap<String, MasterDataDTO>();
		List<MasterData> masterDataList = (List<MasterData>) masterDataRepository.findAll();
		if(masterDataList != null){
			for (MasterData masterData : masterDataList) {
				MasterDataDTO dto = new MasterDataDTO(masterData);
				constants.put(dto.getName(), dto);
			}
		}
  }
}
*/

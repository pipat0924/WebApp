package th.net.cat.epis.dto;

import java.util.List;

import th.net.cat.epis.entity.DeductionManualEntity;
import th.net.cat.epis.entity.ManualEntity;

public class ManualEpisDTO extends CommonStatus<ManualEntity>{
	
	private List<DeductionManualEntity> deductionManual;

	public List<DeductionManualEntity> getDeductionManual() {
		return deductionManual;
	}

	public void setDeductionManual(List<DeductionManualEntity> deductionManual) {
		this.deductionManual = deductionManual;
	}

	
	
	
}

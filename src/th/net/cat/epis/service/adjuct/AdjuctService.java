package th.net.cat.epis.service.adjuct;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import th.net.cat.epis.entity.Receipt;
import th.net.cat.epis.repo.AdjuctRepository;

@org.springframework.stereotype.Service
public class AdjuctService {
	@Autowired AdjuctRepository adjuctrepository;
	
	
	@Transactional
	public List<Receipt> searchRefund(String inv,String epCode){
		List<Receipt> result = adjuctrepository.findByinvoiceandepCode(inv, epCode);
		for(int i=0; i<result.size(); i++) {
				result.get(i).setAttributes((result.get(i).getAttributes().indexOf("R"))>0?"ปกติ":"ยกเลิก");
		}
		
		return result;
	}
}

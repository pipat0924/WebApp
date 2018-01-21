package th.net.cat.epis.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import th.net.cat.epis.batch.vo.Service;

@Component("serviceProcessor")
public class ServiceProcessor implements ItemProcessor<Service, Service> {

	@Override
	public Service process(Service paramI) throws Exception {
		System.out.println("processor");
		return paramI;
	}

}

package th.net.cat.epis.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import th.net.cat.epis.batch.vo.Service;

@Component("serviceWriter")
public class ServiceWriter implements ItemWriter<Service> {

	@Override
	public void write(List<? extends Service> arg0) throws Exception {
		System.out.println("write");
	}

}

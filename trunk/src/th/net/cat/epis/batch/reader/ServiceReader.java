package th.net.cat.epis.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import th.net.cat.epis.batch.vo.Service;

@Component("serviceReader")
public class ServiceReader implements ItemReader<Service> {

	@Override
	public Service read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return null;
	}

}
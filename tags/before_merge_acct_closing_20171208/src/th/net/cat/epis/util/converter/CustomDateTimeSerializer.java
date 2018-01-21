package th.net.cat.epis.util.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateTimeSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("en", "EN"));
		if (value != null) {
			jgen.writeString(dateFormat.format(value));
		} else {
			jgen.writeString("");
		}
	}

}


package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Airline;

@Component
@Transactional
public class AirlineToStringConverter implements Converter<Airline, String> {

	@Override
	public String convert(final Airline airline) {
		String result;

		if (airline == null)
			result = null;
		else
			result = String.valueOf(airline.getId());

		return result;
	}
}

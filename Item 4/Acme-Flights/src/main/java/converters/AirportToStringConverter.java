
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Airport;

@Component
@Transactional
public class AirportToStringConverter implements Converter<Airport, String> {

	@Override
	public String convert(final Airport airport) {
		String result;

		if (airport == null)
			result = null;
		else
			result = String.valueOf(airport.getId());

		return result;
	}
}

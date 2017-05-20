
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Flight;

@Component
@Transactional
public class FlightToStringConverter implements Converter<Flight, String> {

	@Override
	public String convert(final Flight flight) {
		String result;

		if (flight == null)
			result = null;
		else
			result = String.valueOf(flight.getId());

		return result;
	}
}

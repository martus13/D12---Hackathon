
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.AirlineConfiguration;

@Component
@Transactional
public class AirlineConfigurationToStringConverter implements Converter<AirlineConfiguration, String> {

	@Override
	public String convert(final AirlineConfiguration airlineConfiguration) {
		String result;

		if (airlineConfiguration == null)
			result = null;
		else
			result = String.valueOf(airlineConfiguration.getId());

		return result;
	}
}

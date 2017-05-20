
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Offertable;

@Component
@Transactional
public class OffertableToStringConverter implements Converter<Offertable, String> {

	@Override
	public String convert(final Offertable offertable) {
		String result;

		if (offertable == null)
			result = null;
		else
			result = String.valueOf(offertable.getId());

		return result;
	}
}

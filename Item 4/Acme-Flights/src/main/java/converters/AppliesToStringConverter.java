
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Applies;

@Component
@Transactional
public class AppliesToStringConverter implements Converter<Applies, String> {

	@Override
	public String convert(final Applies applies) {
		String result;

		if (applies == null)
			result = null;
		else
			result = String.valueOf(applies.getId());

		return result;
	}
}


package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.OffertableRepository;
import domain.Offertable;

@Component
@Transactional
public class StringToOffertableConverter implements Converter<String, Offertable> {

	@Autowired
	OffertableRepository	offertableRepository;


	@Override
	public Offertable convert(final String text) {
		Offertable result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.offertableRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

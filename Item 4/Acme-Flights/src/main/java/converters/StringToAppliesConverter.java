
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AppliesRepository;
import domain.Applies;

@Component
@Transactional
public class StringToAppliesConverter implements Converter<String, Applies> {

	@Autowired
	AppliesRepository	appliesRepository;


	@Override
	public Applies convert(final String text) {
		Applies result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.appliesRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

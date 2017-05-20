
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PointsCard;

@Component
@Transactional
public class PointsCardToStringConverter implements Converter<PointsCard, String> {

	@Override
	public String convert(final PointsCard pointsCard) {
		String result;

		if (pointsCard == null)
			result = null;
		else
			result = String.valueOf(pointsCard.getId());

		return result;
	}
}

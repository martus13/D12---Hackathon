
package converters;

import org.springframework.core.convert.converter.Converter;

import domain.CreditCard;

public class CreditCardToStringConverter implements Converter<CreditCard, String> {

	@Override
	public String convert(final CreditCard creditCard) {
		String result;

		if (creditCard == null)
			result = null;
		else
			result = String.valueOf(creditCard.getId());

		return result;
	}
}

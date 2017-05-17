
package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;
import domain.User;
import forms.CreditCardForm;

@Service
@Transactional
public class CreditCardService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CreditCardRepository	creditCardRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserService				userService;


	// Constructors -----------------------------------------------------------
	public CreditCardService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public CreditCard findOne(final int creditCardId) {
		Assert.isTrue(creditCardId != 0);

		CreditCard result;

		result = this.creditCardRepository.findOne(creditCardId);

		return result;
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> result;

		result = this.creditCardRepository.findAll();

		return result;
	}

	public CreditCard create() {
		CreditCard result;
		User user;

		result = new CreditCard();

		user = this.userService.findByPrincipal();

		result.setUser(user);

		return result;
	}

	public CreditCard save(CreditCard creditCard) {
		Assert.notNull(creditCard);

		Calendar expirationCalendar, calendar;

		calendar = Calendar.getInstance();
		expirationCalendar = Calendar.getInstance();

		// user de CC = user logueado
		Assert.isTrue(creditCard.getUser().equals(this.userService.findByPrincipal()));

		// brand name
		Assert.isTrue(creditCard.getBrandName().equals("VISA") || creditCard.getBrandName().equals("MASTERCARD") || creditCard.getBrandName().equals("DISCOVER") || creditCard.getBrandName().equals("DINNERS") || creditCard.getBrandName().equals("AMEX"));

		// expiration date -> al menos un día más
		expirationCalendar.set(creditCard.getExpirationYear(), creditCard.getExpirationMonth() - 1, 1);
		expirationCalendar.set(Calendar.DAY_OF_MONTH, expirationCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1); // cogemos el último día del mes menos uno, porque tiene que ser un día más

		Assert.isTrue(calendar.before(expirationCalendar) || calendar.equals(expirationCalendar));

		creditCard = this.creditCardRepository.save(creditCard);

		return creditCard;
	}

	public void delete(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		User user;

		user = this.userService.findByPrincipal();
		Assert.isTrue(user.equals(creditCard.getUser()));

		this.creditCardRepository.delete(creditCard);
	}

	// Other business methods -------------------------------------------------

	public CreditCard findByUser(final int userId) {
		CreditCard result;

		result = this.creditCardRepository.findByUser(userId);

		return result;
	}

	public boolean checkValidation(final CreditCard creditCard) {
		boolean result;
		Calendar calendar;
		Calendar expirationCalendar;

		calendar = Calendar.getInstance();
		expirationCalendar = Calendar.getInstance();

		if (creditCard == null)
			result = false;
		else {
			result = (creditCard.getBrandName().equals("VISA") || creditCard.getBrandName().equals("MASTERCARD") || creditCard.getBrandName().equals("DISCOVER") || creditCard.getBrandName().equals("DINNERS") || creditCard.getBrandName().equals("AMEX"));

			// expiration date -> al menos un día más
			expirationCalendar.set(creditCard.getExpirationYear(), creditCard.getExpirationMonth() - 1, 1);
			expirationCalendar.set(Calendar.DAY_OF_MONTH, expirationCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1); // cogemos el último día del mes menos uno, porque tiene que ser un día más

			result = result && (calendar.before(expirationCalendar) || calendar.equals(expirationCalendar));
		}
		return result;
	}

	public CreditCard reconstructCreate(final CreditCardForm creditCardForm) {
		Assert.notNull(creditCardForm);

		CreditCard creditCard;
		Calendar calendar;
		Calendar expirationCalendar;

		calendar = Calendar.getInstance();
		expirationCalendar = Calendar.getInstance();

		// expiration date -> al menos un día más
		expirationCalendar.set(creditCardForm.getExpirationYear(), creditCardForm.getExpirationMonth() - 1, 1);
		expirationCalendar.set(Calendar.DAY_OF_MONTH, expirationCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1); // cogemos el último día del mes menos uno, porque tiene que ser un día más

		Assert.isTrue(calendar.before(expirationCalendar) || calendar.equals(expirationCalendar));

		creditCard = this.create();

		creditCard.setHolderName(creditCardForm.getHolderName());
		creditCard.setBrandName(creditCardForm.getBrandName());
		creditCard.setNumber(creditCardForm.getNumber());
		creditCard.setExpirationMonth(creditCardForm.getExpirationMonth());
		creditCard.setExpirationYear(creditCardForm.getExpirationYear());
		creditCard.setCvv(creditCardForm.getCvv());

		return creditCard;
	}

	public CreditCard reconstructEdit(final CreditCardForm creditCardForm) {
		Assert.notNull(creditCardForm);

		CreditCard creditCard;
		User user;
		Calendar calendar;
		Calendar expirationCalendar;

		calendar = Calendar.getInstance();
		expirationCalendar = Calendar.getInstance();

		// expiration date -> al menos un día más
		expirationCalendar.set(creditCardForm.getExpirationYear(), creditCardForm.getExpirationMonth() - 1, 1);
		expirationCalendar.set(Calendar.DAY_OF_MONTH, expirationCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1); // cogemos el último día del mes menos uno, porque tiene que ser un día más

		Assert.isTrue(calendar.before(expirationCalendar) || calendar.equals(expirationCalendar));

		user = this.userService.findByPrincipal();
		creditCard = this.findByUser(user.getId());

		creditCard.setHolderName(creditCardForm.getHolderName());
		creditCard.setBrandName(creditCardForm.getBrandName());
		creditCard.setNumber(creditCardForm.getNumber());
		creditCard.setExpirationMonth(creditCardForm.getExpirationMonth());
		creditCard.setExpirationYear(creditCardForm.getExpirationYear());
		creditCard.setCvv(creditCardForm.getCvv());

		return creditCard;
	}

	public CreditCardForm desreconstruct(final CreditCard creditCard) {
		CreditCardForm creditCardForm;

		creditCardForm = new CreditCardForm();

		creditCardForm.setHolderName(creditCard.getHolderName());
		creditCardForm.setBrandName(creditCard.getBrandName());
		creditCardForm.setNumber(creditCard.getNumber());
		creditCardForm.setExpirationMonth(creditCard.getExpirationMonth());
		creditCardForm.setExpirationYear(creditCard.getExpirationYear());
		creditCardForm.setCvv(creditCard.getCvv());

		return creditCardForm;
	}
}

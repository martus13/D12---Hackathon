
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.User;

@Service
@Transactional
public class FinderService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private FinderRepository	finderRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserService			userService;

	@Autowired
	private CreditCardService	creditCardService;


	// Constructors -----------------------------------------------------------
	public FinderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Finder findOne(final int finderId) {
		Assert.isTrue(finderId != 0);

		Finder result;

		result = this.finderRepository.findOne(finderId);

		return result;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;

		result = this.finderRepository.findAll();

		return result;
	}

	public Finder create() {
		Finder result;
		final User user;

		result = new Finder();
		user = this.userService.findByPrincipal();

		result.setPassengersNumber(1);
		result.setChildrenNumber(0);
		result.setUser(user);

		return result;
	}

	public Finder save(Finder finder) {
		Assert.notNull(finder);
		Assert.isTrue(!finder.getDeparture().equals(finder.getDestination()));
		Assert.isTrue(!(finder.getReturnFlight() && finder.getReturnDate() == null));
		Assert.isTrue(finder.getReturnDate() == null || finder.getReturnDate().after(finder.getDepartureDate()) || finder.getReturnDate() == finder.getDepartureDate());

		Calendar calendar;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		Assert.isTrue(finder.getDepartureDate().after(calendar.getTime()));

		finder = this.finderRepository.save(finder);

		return finder;
	}

	public void delete(final Finder finder) {
		Assert.notNull(finder);

		this.finderRepository.delete(finder);
	}

	// Other business methods -------------------------------------------------

	public Finder findByUserId(final int userId) {
		Assert.isTrue(userId != 0);

		Finder result;

		result = this.finderRepository.findByUserId(userId);

		return result;
	}

	public Collection<User> findUsersByFinder(Finder finder) {
		final Collection<User> result = new ArrayList<User>();
		Calendar calendar;
		User user;

		calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, -10);
		user = this.userService.findByPrincipal();

		// primero: comprobar que tiene creditCard y que es valida
		Assert.isTrue(this.creditCardService.checkValidation(this.creditCardService.findByUser(user.getId())));

		finder = this.finderRepository.save(finder);

		return result;
	}

}

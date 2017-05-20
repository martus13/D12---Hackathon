
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AppliesRepository;
import domain.Applies;
import domain.Book;
import domain.Flight;
import domain.PointsCard;
import domain.User;

@Service
@Transactional
public class AppliesService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AppliesRepository	appliesRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserService			userService;

	@Autowired
	private PointsCardService	pointsCardService;

	@Autowired
	private BookService			bookService;


	// Constructors -----------------------------------------------------------
	public AppliesService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Applies findOne(final int appliesId) {
		Assert.notNull(appliesId != 0);
		Applies result;

		result = this.appliesRepository.findOne(appliesId);

		return result;
	}

	public Collection<Applies> findAll() {
		Collection<Applies> result;

		result = this.appliesRepository.findAll();

		return result;
	}

	public Applies create(final Book book, final PointsCard pointsCard, final Flight flight) {
		Applies result;
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(user.getId() == book.getUser().getId() && user.getId() == pointsCard.getUser().getId());

		result = new Applies();
		result.setBook(book);
		result.setPointsCard(pointsCard);
		result.setFlight(flight);

		return result;
	}

	public Applies save(Applies applies) {
		Assert.notNull(applies);
		User user;
		PointsCard pointsCard;
		Applies auxApplies;
		Book book;

		book = applies.getBook();

		user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(user.getId() == book.getUser().getId() && user.getId() == applies.getPointsCard().getUser().getId());

		pointsCard = applies.getPointsCard();
		Assert.isTrue(applies.getUsedPoints() <= pointsCard.getPoints());

		auxApplies = this.findByBookAndPointsCardId(book.getId(), pointsCard.getId(), applies.getFlight().getId());

		if (auxApplies != null && auxApplies.getId() != applies.getId()) {
			auxApplies.setUsedPoints(auxApplies.getUsedPoints() + applies.getUsedPoints());
			pointsCard.setPoints(pointsCard.getPoints() - applies.getUsedPoints());

			applies = this.appliesRepository.save(applies);
			this.pointsCardService.save(pointsCard);

		} else {
			if (applies.getId() == 0)
				pointsCard.setPoints(pointsCard.getPoints() - applies.getUsedPoints());

			applies = this.appliesRepository.save(applies);
			if (applies.getId() == 0)
				this.pointsCardService.save(pointsCard);
		}

		book.setOriginalPrice(this.bookService.getOriginPriceBook(book.getIsBusiness(), book.getPassengersNumber(), book.getChildrenNumber(), book.getFlights(), this.findByBookId(book.getId())));
		this.bookService.save(book);

		return applies;
	}

	public void delete(final Applies applies) {
		Assert.notNull(applies);
		User user;
		PointsCard pointsCard;
		Book book;

		book = applies.getBook();
		pointsCard = applies.getPointsCard();

		user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(user.getId() == book.getUser().getId() && user.getId() == pointsCard.getUser().getId());

		pointsCard.setPoints(pointsCard.getPoints() + applies.getUsedPoints());

		this.appliesRepository.delete(applies);

		this.pointsCardService.save(pointsCard);

		book.setOriginalPrice(this.bookService.getOriginPriceBook(book.getIsBusiness(), book.getPassengersNumber(), book.getChildrenNumber(), book.getFlights(), this.findByBookId(book.getId())));
		this.bookService.save(book);

	}
	// Other business methods -------------------------------------------------
	public Collection<Applies> findByBookId(final int bookId) {
		Assert.isTrue(bookId != 0);
		Collection<Applies> result;

		result = this.appliesRepository.findByBookId(bookId);

		return result;
	}

	public Collection<Applies> findByPointsCardId(final int pointsCardId) {
		Assert.isTrue(pointsCardId != 0);
		Collection<Applies> result;

		result = this.appliesRepository.findByPointsCardId(pointsCardId);

		return result;
	}

	public Applies findByBookAndPointsCardId(final int bookId, final int pointsCardId, final int flightId) {
		Assert.isTrue(bookId != 0 && pointsCardId != 0);
		Applies result;

		result = this.appliesRepository.findByBookAndPointsCardId(bookId, pointsCardId, flightId);

		return result;
	}
}

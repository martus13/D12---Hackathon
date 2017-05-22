
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BookRepository;
import domain.AirlineConfiguration;
import domain.Applies;
import domain.Book;
import domain.Finder;
import domain.Flight;
import domain.Offer;
import domain.PointsCard;
import domain.Season;
import domain.User;

@Service
@Transactional
public class BookService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private BookRepository				bookRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserService					userService;

	@Autowired
	private FinderService				finderService;

	@Autowired
	private AirlineConfigurationService	airlineConfigurationService;

	@Autowired
	private OfferService				offerService;

	@Autowired
	private SeasonService				seasonService;

	@Autowired
	private PointsCardService			pointsCardService;

	@Autowired
	private AppliesService				appliesService;


	// Constructors -----------------------------------------------------------
	public BookService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Book findOne(final int bookId) {
		Assert.isTrue(bookId != 0);
		Book result;

		result = this.bookRepository.findOne(bookId);

		return result;
	}
	public Collection<Book> findAll() {
		Collection<Book> result;

		result = this.bookRepository.findAll();

		return result;
	}

	public Book create(final Flight departure, final Flight destination) {
		Book result;
		User user;
		Finder finder;
		Calendar calendar;
		Collection<Flight> flights;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		finder = this.finderService.findByUserId(user.getId());
		Assert.notNull(finder);
		if (finder.getIsBusiness())
			Assert.isTrue(departure.getAvailableBusinessSeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()) && destination.getAvailableBusinessSeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()));
		else
			Assert.isTrue(departure.getAvailableEconomySeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()) && destination.getAvailableEconomySeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()));

		Assert.notNull(departure);
		flights = new ArrayList<Flight>();
		flights.add(departure);
		if (finder.getReturnFlight())
			flights.add(destination);

		result = new Book();
		result.setUser(user);
		result.setCreationMoment(calendar.getTime());
		result.setCancelationMoment(null);
		result.setFlights(flights);
		result.setPassengersNumber(finder.getPassengersNumber());
		result.setChildrenNumber(finder.getChildrenNumber());
		result.setIsBusiness(finder.getIsBusiness());

		result = this.calculatePrice(result);

		return result;
	}
	public Book save(Book book) {
		Assert.notNull(book);
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(book.getUser().equals(user));

		if (book.getId() == 0)
			for (final Flight f : book.getFlights()) {
				PointsCard pointsCard;

				if (book.getIsBusiness())
					Assert.isTrue(f.getAvailableBusinessSeats() >= (book.getChildrenNumber() + book.getPassengersNumber()));
				else
					Assert.isTrue(f.getAvailableEconomySeats() >= (book.getChildrenNumber() + book.getPassengersNumber()));

				pointsCard = this.pointsCardService.findByUserAndAirlineId(user.getId(), f.getAirline().getId());

				if (pointsCard == null) {
					// Aqui creamos una tarjeta de puntos nueva
					pointsCard = this.pointsCardService.create(user, f.getAirline());
					pointsCard.setPoints(1);
				} else
					pointsCard.setPoints(pointsCard.getPoints() + 1);
				this.pointsCardService.save(pointsCard);
			}

		book = this.bookRepository.save(book);

		return book;

	}

	public void delete(Book book) {
		Assert.notNull(book);
		User user;
		Calendar calendar;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(book.getUser().equals(user));

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		book.setCancelationMoment(calendar.getTime());
		book = this.bookRepository.save(book);
	}

	// Other business methods -------------------------------------------------

	public Book calculatePrice(final Book book) {
		Assert.notNull(book);

		Double originalPrice;
		Double totalFee;
		Collection<Season> seasons;
		Collection<Offer> offers;
		final Collection<Applies> applies;

		originalPrice = 0.0;
		totalFee = 0.0;
		seasons = new ArrayList<Season>();
		offers = new ArrayList<Offer>();

		if (book.getId() != 0)
			applies = this.appliesService.findByBookId(book.getId());
		else
			applies = null;

		for (final Flight f : book.getFlights()) {
			Double flightPrice;
			Season season;
			Offer offer;
			AirlineConfiguration airlineConfiguration;

			airlineConfiguration = this.airlineConfigurationService.findByAirlineId(f.getAirline().getId());

			if (book.getIsBusiness())
				flightPrice = f.getBusinessPrice();
			else
				flightPrice = f.getEconomyPrice();

			originalPrice += (flightPrice * book.getPassengersNumber()) + (book.getChildrenNumber() * (flightPrice - (flightPrice * airlineConfiguration.getChildrenDiscount() / 100)));
			originalPrice += (f.getDeparture().getRate() * (book.getPassengersNumber() + book.getChildrenNumber()));

			// Buscamos las temporadas
			season = this.seasonService.findActiveByDateAndAirlineId(f.getAirline().getId(), f.getDepartureDate());
			if (season != null) {
				seasons.add(season);
				if (season.getType() == "increase")
					flightPrice += flightPrice * season.getPricePercentage() / 100;
				else if (season.getType() == "discount")
					flightPrice -= flightPrice * season.getPricePercentage() / 100;
			}

			// Buscamos las ofertas
			offer = this.offerService.findByDateAndOffertableId(f.getId(), book.getCreationMoment());
			if (offer != null) {
				offers.add(offer);
				flightPrice -= flightPrice * offer.getDiscount() / 100;
			} else {
				// Si los vuelos no tienen oferta buscamos las ofertas de las aerolineas
				offer = this.offerService.findByDateAndOffertableId(f.getAirline().getId(), book.getCreationMoment());
				if (offer != null) {
					offers.add(offer);
					flightPrice -= flightPrice * offer.getDiscount() / 100;
				}
			}

			if (applies != null)
				for (final Applies a : applies)
					if (a.getFlight().equals(f))
						flightPrice -= a.getUsedPoints();

			totalFee += (flightPrice * book.getPassengersNumber()) + (book.getChildrenNumber() * (flightPrice - (flightPrice * airlineConfiguration.getChildrenDiscount() / 100)));
			totalFee += (f.getDeparture().getRate() * (book.getPassengersNumber() + book.getChildrenNumber()));
		}

		book.setOffers(offers);
		book.setSeasons(seasons);
		book.setOriginalPrice(originalPrice);
		book.setTotalFee(totalFee);
		return book;
	}
	public Collection<Book> findNotCancelledByFlightId(final int flightId) {
		Collection<Book> result;

		result = this.bookRepository.findNotCancelledByFlightId(flightId);

		return result;
	}

	public Collection<Book> findByUser(final int userId) {
		Collection<Book> result;

		result = this.bookRepository.findByUser(userId);

		return result;
	}

	public Collection<Book> findOfNotPassedFlightsBySeason(final Season season) {
		Collection<Book> result;

		result = this.bookRepository.findOfNotPassedFlightsBySeason(season.getAirline().getId(), season.getStartDay(), season.getStartMonth(), season.getEndDay(), season.getEndMonth());

		return result;
	}

}


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
		Collection<Season> seasons;
		Collection<Offer> offers;
		Double originalPrice;
		Double totalFee;
		Offer offer1;
		Offer offer2;
		Season season1;
		Season season2;

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
		// Calculamos el precio a pagar:
		originalPrice = this.getOriginPriceBook(finder.getIsBusiness(), finder.getPassengersNumber(), finder.getChildrenNumber(), flights, null);

		offers = new ArrayList<Offer>();
		// Buscamos las ofertas de los vuelos
		offer1 = this.offerService.findByDateAndOffertableId(departure.getId(), calendar.getTime());
		if (offer1 != null)
			offers.add(offer1);

		if (finder.getReturnFlight()) {
			offer2 = this.offerService.findByDateAndOffertableId(destination.getId(), calendar.getTime());
			if (offer2 != null)
				offers.add(offer2);
		}
		// Si los vuelos no tienen oferta buscamos las ofertas de las aerolineas
		if (offers.isEmpty()) {
			offer1 = this.offerService.findByDateAndOffertableId(departure.getAirline().getId(), calendar.getTime());
			if (offer1 != null)
				offers.add(offer1);

			if (finder.getReturnFlight()) {
				offer2 = this.offerService.findByDateAndOffertableId(destination.getAirline().getId(), calendar.getTime());
				if (offer2 != null)
					offers.add(offer2);
			}
		}
		// Comprobacion temporadas
		seasons = new ArrayList<Season>();
		season1 = this.seasonService.findActiveByDateAndAirlineId(departure.getAirline().getId(), calendar.getTime());
		if (season1 != null)
			seasons.add(season1);

		if (finder.getReturnFlight()) {
			season2 = this.seasonService.findActiveByDateAndAirlineId(departure.getAirline().getId(), calendar.getTime());
			if (season2 != null)
				seasons.add(season2);
		}
		totalFee = this.getTotalFeeBook(originalPrice, offers, seasons);

		result = new Book();
		result.setUser(user);
		result.setCreationMoment(calendar.getTime());
		result.setCancelationMoment(null);
		result.setFlights(flights);
		result.setSeasons(seasons);
		result.setOffers(offers);
		result.setPassengersNumber(finder.getPassengersNumber());
		result.setChildrenNumber(finder.getChildrenNumber());
		result.setIsBusiness(finder.getIsBusiness());
		result.setOriginalPrice(originalPrice);
		result.setTotalFee(totalFee);

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

	public Double getOriginPriceBook(final Boolean isBusiness, final Integer passengersNumber, final Integer childrenNumber, final Collection<Flight> flights, final Collection<Applies> applies) {
		Double result;
		Double flightPrice;
		AirlineConfiguration airlineConfiguration;

		flightPrice = 0.0;
		result = 0.0;

		for (final Flight f : flights) {
			for (final Applies a : applies)
				if (a.getFlight().equals(f))
					flightPrice += a.getUsedPoints();

			if (isBusiness) {
				flightPrice = f.getBusinessPrice() - flightPrice;
				result = flightPrice * passengersNumber;

				airlineConfiguration = this.airlineConfigurationService.findByAirlineId(f.getAirline().getId());
				result += childrenNumber * (flightPrice - (flightPrice * airlineConfiguration.getChildrenDiscount() / 100));
			} else {
				flightPrice = f.getEconomyPrice() - flightPrice;
				result = flightPrice * passengersNumber;

				airlineConfiguration = this.airlineConfigurationService.findByAirlineId(f.getAirline().getId());
				result += childrenNumber * (flightPrice - (flightPrice * airlineConfiguration.getChildrenDiscount() / 100));
			}
			result += f.getDeparture().getRate() * (childrenNumber + passengersNumber);
		}

		return result;
	}

	public Double getTotalFeeBook(final Double originalPrice, final Collection<Offer> offers, final Collection<Season> seasons) {
		Double result;

		result = originalPrice;

		for (final Offer o : offers)
			result -= result * (o.getDiscount() / 100);

		for (final Season s : seasons)
			if (s.getType() == "increase")
				result += result * (s.getPricePercentage() / 100);
			else if (s.getType() == "discount")
				result -= result * (s.getPricePercentage() / 100);

		return result;
	}

	public Collection<Book> findNotCancelledByFlightId(final int flightId) {
		Collection<Book> result;

		result = this.bookRepository.findNotCancelledByFlightId(flightId);

		return result;
	}

	public Collection<Book> findOfNotPassedFlightsBySeason(final Season season) {
		Collection<Book> result;

		result = this.bookRepository.findOfNotPassedFlightsBySeason(season.getAirline().getId(), season.getStartDay(), season.getStartMonth(), season.getEndDay(), season.getEndMonth());

		return result;
	}

}


package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BookRepository;
import domain.Actor;
import domain.AirlineConfiguration;
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
	private ActorService				actorService;

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

	public Book create(final Flight departure, final Integer seasonDepartureId, final Integer offerFlightDepartureId, final Integer offerAirlineDepartureId, final Flight destination, final Integer seasonDestinationId,
		final Integer offerFlightDestinationId, final Integer offerAirlineDestinationId) {
		final Book result;
		User user;
		Finder finder;
		Calendar calendar;
		Collection<Flight> flights;
		Double originalFee;
		Double totalFee;
		Season seasonDeparture;
		Season seasonDestination;
		Offer offerDeparture;
		Offer offerDestination;
		Collection<Season> seasons;
		Collection<Offer> offers;
		Double departurePrice;
		Double destinationPrice;
		Double childrenPrice;
		Double destinationAirportRate;
		AirlineConfiguration airlineConfiguration;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		finder = this.finderService.findByUserId(user.getId());
		Assert.notNull(finder);

		// Comprobamos que para la ida hayan asientos libres:
		if (finder.getIsBusiness())
			Assert.isTrue(departure.getAvailableBusinessSeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()));
		else
			Assert.isTrue(departure.getAvailableEconomySeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()));

		// Comprobamos que para la vuelta hayan asientos libres:
		destinationAirportRate = 0.0;
		if (finder.getReturnFlight()) {
			destinationAirportRate = destination.getDeparture().getRate();
			if (finder.getIsBusiness())
				Assert.isTrue(destination.getAvailableBusinessSeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()));
			else
				Assert.isTrue(destination.getAvailableEconomySeats() >= (finder.getChildrenNumber() + finder.getPassengersNumber()));
		}

		Assert.notNull(departure);
		flights = new ArrayList<Flight>();
		flights.add(departure);
		// Comprobamos el solapamiento
		Assert.isNull(this.findOverlappingByUserAndDepartureDate(user.getId(), departure.getDepartureDate()));
		if (finder.getReturnFlight()) {
			flights.add(destination);
			Assert.isNull(this.findOverlappingByUserAndDepartureDate(user.getId(), destination.getDepartureDate()));
		}

		destinationPrice = 0.0;
		if (finder.getIsBusiness()) {
			departurePrice = departure.getBusinessPrice();
			if (finder.getReturnFlight())
				destinationPrice = destination.getBusinessPrice();
		} else {
			departurePrice = departure.getEconomyPrice();
			if (finder.getReturnFlight())
				destinationPrice = destination.getEconomyPrice();
		}
		originalFee = departurePrice + destinationPrice;

		seasons = new ArrayList<Season>();
		if (seasonDepartureId != null) {
			seasonDeparture = this.seasonService.findOne(seasonDepartureId);
			seasons.add(seasonDeparture);

			if (seasonDeparture.getType() == "increase")
				departurePrice = departurePrice + (departurePrice * seasonDeparture.getPricePercentage() / 100);
			else if (seasonDeparture.getType() == "discount")
				departurePrice = departurePrice - (departurePrice * seasonDeparture.getPricePercentage() / 100);
		}
		if (finder.getReturnFlight() && seasonDestinationId != null) {
			seasonDestination = this.seasonService.findOne(seasonDestinationId);
			seasons.add(seasonDestination);

			if (seasonDestination != null)
				if (seasonDestination.getType() == "increase")
					destinationPrice = destinationPrice + (destinationPrice * seasonDestination.getPricePercentage() / 100);
				else if (seasonDestination.getType() == "discount")
					destinationPrice = destinationPrice - (destinationPrice * seasonDestination.getPricePercentage() / 100);
		}

		offers = new ArrayList<Offer>();
		if (offerFlightDepartureId != null) {
			offerDeparture = this.offerService.findOne(offerFlightDepartureId);
			if (offerDeparture == null)
				offerDeparture = this.offerService.findOne(offerAirlineDepartureId);
			if (offerDeparture != null)
				offers.add(offerDeparture);
			departurePrice = departurePrice - (departurePrice * offerDeparture.getDiscount() / 100);
		}

		if (finder.getReturnFlight() && offerFlightDestinationId != null) {
			offerDestination = this.offerService.findOne(offerFlightDestinationId);
			if (offerDestination == null)
				offerDestination = this.offerService.findOne(offerAirlineDestinationId);
			if (offerDestination != null)
				offers.add(offerDestination);
			destinationPrice = destinationPrice - (destinationPrice * offerDestination.getDiscount() / 100);
		}

		// calculamos el precio real (con temporada y oferta)
		if (finder.getChildrenNumber() > 0) {
			airlineConfiguration = this.airlineConfigurationService.findByAirlineId(departure.getAirline().getId());
			childrenPrice = departurePrice - (departurePrice * airlineConfiguration.getChildrenDiscount() / 100);

			if (finder.getReturnFlight()) {
				airlineConfiguration = this.airlineConfigurationService.findByAirlineId(departure.getAirline().getId());
				childrenPrice += destinationPrice - (destinationPrice * airlineConfiguration.getChildrenDiscount() / 100);
			}
		} else
			childrenPrice = 0.0;
		totalFee = departurePrice + destinationPrice + departure.getDeparture().getRate() + destinationAirportRate;

		totalFee = totalFee * finder.getPassengersNumber() + finder.getChildrenNumber() * (childrenPrice);

		totalFee = Math.round(totalFee * 100.0) / 100.0;

		result = new Book();
		result.setUser(user);
		result.setCreationMoment(calendar.getTime());
		result.setCancelationMoment(null);
		result.setFlights(flights);
		result.setPassengersNumber(finder.getPassengersNumber());
		result.setChildrenNumber(finder.getChildrenNumber());
		result.setIsBusiness(finder.getIsBusiness());
		result.setSeasons(seasons);
		result.setOffers(offers);
		result.setOriginalPrice(originalFee);
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

				// Comprobamos que no haya solapamiento
				Assert.isNull(this.findOverlappingByUserAndDepartureDate(user.getId(), f.getDepartureDate()));

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
		Actor actor;
		AirlineConfiguration airlineConfiguration;
		Calendar calendar;

		actor = this.actorService.findByPrincipal();
		Assert.notNull(actor);
		Assert.isTrue(this.actorService.checkAuthority(actor, "USER") || this.actorService.checkAuthority(actor, "MANAGER"));
		if (this.actorService.checkAuthority(actor, "USER"))
			Assert.isTrue(book.getUser().getId() == actor.getId());

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		for (final Flight f : book.getFlights()) {
			Calendar expirationDate;

			airlineConfiguration = this.airlineConfigurationService.findByAirlineId(f.getAirline().getId());

			expirationDate = Calendar.getInstance();
			expirationDate.setTime(f.getDepartureDate());
			expirationDate.add(Calendar.DAY_OF_MONTH, -airlineConfiguration.getMaxCancellationDays());

			// La fecha de cancelacion debe ser menor o igual que la fecha de salida menos los días máximos de cancelacion de la configuracion de la aerolinea
			Assert.isTrue(calendar.getTime().after(expirationDate.getTime()) || calendar.getTime() == expirationDate.getTime());
		}

		book.setCancelationMoment(calendar.getTime());
		book = this.bookRepository.save(book);
	}
	// Other business methods -------------------------------------------------

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

	public Collection<Book> findNotCancelledWithoutInvoices() {

		Collection<Book> result;

		result = this.findNotCancelledWithoutInvoices();

		return result;
	}

	public Book findOverlappingByUserAndDepartureDate(final int userId, final Date departureDate) {
		Book result;

		result = this.bookRepository.findOverlappingByUserAndDepartureDate(userId, departureDate);

		return result;
	}

}

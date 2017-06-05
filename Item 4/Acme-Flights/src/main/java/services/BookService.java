
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
import domain.Applies;
import domain.Book;
import domain.Finder;
import domain.Flight;
import domain.Manager;
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
	private ManagerService				managerService;

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
	private CreditCardService			creditCardService;

	@Autowired
	private AppliesService				appliesService;

	@Autowired
	private FlightService				flightService;


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
		// Comprobamos que el vuelo de ida y el de vuelta no sean el mismo
		Assert.isTrue(!departure.equals(destination));

		// Comprobamos la validez de la tarjeta de credito
		Assert.isTrue(this.creditCardService.checkValidation(this.creditCardService.findByUser(user.getId())));

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		finder = this.finderService.findByUserId(user.getId());
		Assert.notNull(finder);

		// Comprobamos que los vuelos no hayan pasado:
		Assert.isTrue(departure.getDepartureDate().after(calendar.getTime()) || departure.getDepartureDate() == calendar.getTime());
		if (finder.getReturnFlight())
			Assert.isTrue(destination.getDepartureDate().after(calendar.getTime()) || destination.getDepartureDate() == calendar.getTime());

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
				airlineConfiguration = this.airlineConfigurationService.findByAirlineId(destination.getAirline().getId());
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
		result.setTotalFee(totalFee);

		return result;
	}
	public Book save(Book book) {
		Assert.notNull(book);
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(book.getUser().equals(user));
		Assert.isNull(book.getCancelationMoment());

		if (book.getId() == 0)
			for (final Flight f : book.getFlights()) {
				PointsCard pointsCard;
				Calendar fechaActualMas1Año;

				// Comprobamos que no haya solapamiento
				Assert.isNull(this.findOverlappingByUserAndDepartureDate(user.getId(), f.getDepartureDate()));

				fechaActualMas1Año = Calendar.getInstance();
				fechaActualMas1Año.add(Calendar.MILLISECOND, -10);
				fechaActualMas1Año.add(Calendar.YEAR, +1);

				// Comprobamos disponibilidad asientos
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

				pointsCard.setExpirationMoment(fechaActualMas1Año.getTime());
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
		ArrayList<Flight> flights;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);
		flights = new ArrayList<Flight>(book.getFlights());

		actor = this.actorService.findByPrincipal();
		Assert.notNull(actor);
		Assert.isTrue(this.actorService.checkAuthority(actor, "USER") || this.actorService.checkAuthority(actor, "MANAGER"));

		if (this.actorService.checkAuthority(actor, "USER")) {
			// Comprobamos que el usuario que lo elimina sea el mismo que lo creo
			Assert.isTrue(book.getUser().getId() == actor.getId());

			// La fecha de cancelacion debe ser menor o igual que la fecha de salida menos los días máximos de cancelacion de la configuracion de la aerolinea
			for (final Flight f : book.getFlights()) {
				Calendar expirationDate;

				airlineConfiguration = this.airlineConfigurationService.findByAirlineId(f.getAirline().getId());

				expirationDate = Calendar.getInstance();
				expirationDate.setTime(f.getDepartureDate());
				expirationDate.add(Calendar.DAY_OF_MONTH, -airlineConfiguration.getMaxCancellationDays());

				Assert.isTrue(calendar.getTime().before(expirationDate.getTime()) || calendar.getTime() == expirationDate.getTime());
			}
		} else if (this.actorService.checkAuthority(actor, "MANAGER")) {
			Manager manager;

			manager = this.managerService.findOne(actor.getId());
			// comprobamos que el manager pueda eliminar la reserva
			Assert.notNull(this.findOneWithAirline(book.getId(), manager.getAirline().getId()));

			// eliminamos los applies relacionados
			for (final Applies a : this.appliesService.findByBookId(book.getId()))
				book = this.appliesService.delete(a);

		}

		for (final Flight f : flights) {
			if (book.getIsBusiness())
				f.setAvailableBusinessSeats(f.getAvailableBusinessSeats() - (book.getPassengersNumber() + book.getChildrenNumber()));
			else
				f.setAvailableEconomySeats(f.getAvailableEconomySeats() - (book.getPassengersNumber() + book.getChildrenNumber()));
			this.flightService.save(f);
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

		result = this.bookRepository.findNotCancelledWithoutInvoices();

		return result;
	}

	public Book findOverlappingByUserAndDepartureDate(final int userId, final Date departureDate) {
		Book result;

		result = this.bookRepository.findOverlappingByUserAndDepartureDate(userId, departureDate);

		return result;
	}

	public Book findOneWithAirline(final int bookId, final int airlineId) {
		Book result;

		result = this.bookRepository.findOneWithAirline(bookId, airlineId);

		return result;
	}

	public Collection<Book> findByOfferId(final int offerId) {
		Collection<Book> result;

		result = this.bookRepository.findByOfferId(offerId);

		return result;
	}

}

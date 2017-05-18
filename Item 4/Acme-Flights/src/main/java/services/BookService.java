
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
import domain.Book;
import domain.Finder;
import domain.Flight;
import domain.Offer;
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
		final Collection<Season> seasons;
		Collection<Offer> offers;
		Double originalPrice;
		Double totalFee;
		Offer offer1;
		Offer offer2;
		Season season1;
		final Season season2;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		finder = this.finderService.findByUserId(user.getId());
		Assert.notNull(finder);
		if (finder.getIsBusiness())
			Assert.isTrue(departure.getAvailableBusinessSeats() >= finder.getChildrenNumber() + finder.getPassengersNumber());
		else
			Assert.isTrue(departure.getAvailableEconomySeats() >= finder.getChildrenNumber() + finder.getPassengersNumber());

		// Calculamos el precio a pagar:
		originalPrice = this.getOriginPrice(finder.getIsBusiness(), finder.getPassengersNumber(), finder.getChildrenNumber(), departure, destination);
		totalFee = originalPrice;

		Assert.notNull(departure);
		flights = new ArrayList<Flight>();
		flights.add(departure);
		if (finder.getReturnFlight())
			flights.add(destination);

		offers = new ArrayList<Offer>();
		// Buscamos las ofertas de los vuelos
		offer1 = this.offerService.findByDateAndOffertableId(departure.getId(), calendar.getTime());
		if (offer1 != null) {
			offers.add(offer1);
			totalFee -= totalFee * (offer1.getDiscount() / 100);
		}

		if (finder.getReturnFlight()) {
			offer2 = this.offerService.findByDateAndOffertableId(destination.getId(), calendar.getTime());
			if (offer2 != null) {
				offers.add(offer2);
				totalFee -= totalFee * (offer2.getDiscount() / 100);
			}
		}
		// Si los vuelos no tienen oferta buscamos las ofertas de las aerolineas
		if (offers.isEmpty()) {
			offer1 = this.offerService.findByDateAndOffertableId(departure.getAirline().getId(), calendar.getTime());
			if (offer1 != null) {
				offers.add(offer1);
				totalFee -= totalFee * (offer1.getDiscount() / 100);
			}

			if (finder.getReturnFlight()) {
				offer2 = this.offerService.findByDateAndOffertableId(destination.getAirline().getId(), calendar.getTime());
				if (offer2 != null) {
					offers.add(offer2);
					totalFee -= totalFee * (offer2.getDiscount() / 100);
				}
			}
		}
		// Comprobacion temporadas
		seasons = new ArrayList<Season>();
		season1 = this.seasonService.findActiveByDateAndAirlineId(departure.getAirline().getId(), calendar.getTime());
		if (season1 != null) {
			seasons.add(season1);
			totalFee -= totalFee * (season1.getPricePercentage() / 100);
			if (season1.getType() == "increase")
				totalFee += totalFee * (season1.getPricePercentage() / 100);
			else if (season1.getType() == "discount")
				totalFee -= totalFee * (season1.getPricePercentage() / 100);
		}

		if (finder.getReturnFlight()) {
			season2 = this.seasonService.findActiveByDateAndAirlineId(departure.getAirline().getId(), calendar.getTime());
			if (season2 != null) {
				seasons.add(season2);
				if (season2.getType() == "increase")
					totalFee += totalFee * (season2.getPricePercentage() / 100);
				else if (season2.getType() == "discount")
					totalFee -= totalFee * (season2.getPricePercentage() / 100);
			}
		}

		result = new Book();
		result.setUser(user);
		result.setCreationMoment(calendar.getTime());
		result.setUsedPoints(0);
		result.setCancelationMoment(null);
		result.setFlights(flights);
		result.setSeasons(seasons);
		result.setOffers(offers);
		result.setPassengersNumber(finder.getPassengersNumber());
		result.setChildrenNumber(finder.getChildrenNumber());
		result.setIsBusiness(finder.getIsBusiness());
		result.setOriginalPrice(originalPrice);

		return result;
	}
	public Book save(Book book) {
		Assert.notNull(book);
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(book.getUser().equals(user));

		if (book.getId() == 0)
			// comprobamos que hay suficiente espacio para los vuelos que se quieren comprar
			for (final Flight f : book.getFlights())
				if (book.getIsBusiness())
					Assert.isTrue(f.getAvailableBusinessSeats() >= book.getChildrenNumber() + book.getPassengersNumber());
				else
					Assert.isTrue(f.getAvailableEconomySeats() >= book.getChildrenNumber() + book.getPassengersNumber());

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

	public Double getOriginPrice(final Boolean isBusiness, final Integer passengersNumber, final Integer childrenNumber, final Flight departure, final Flight destination) {
		Double result;
		AirlineConfiguration airlineConfiguration;

		if (isBusiness) {
			result = departure.getBusinessPrice() * passengersNumber;

			airlineConfiguration = this.airlineConfigurationService.findByAirlineId(departure.getAirline().getId());
			result += childrenNumber * (departure.getBusinessPrice() - (departure.getBusinessPrice() * airlineConfiguration.getChildrenDiscount() / 100));
		} else {
			result = departure.getEconomyPrice() * passengersNumber;

			airlineConfiguration = this.airlineConfigurationService.findByAirlineId(departure.getAirline().getId());
			result += childrenNumber * (departure.getEconomyPrice() - (departure.getEconomyPrice() * airlineConfiguration.getChildrenDiscount() / 100));
		}
		result += departure.getDeparture().getRate() * (childrenNumber + passengersNumber);

		if (destination != null) {
			if (isBusiness) {
				result = destination.getBusinessPrice() * passengersNumber;

				airlineConfiguration = this.airlineConfigurationService.findByAirlineId(destination.getAirline().getId());
				result += childrenNumber * (destination.getBusinessPrice() - (destination.getBusinessPrice() * airlineConfiguration.getChildrenDiscount() / 100));
			} else {
				result = destination.getEconomyPrice() * passengersNumber;

				airlineConfiguration = this.airlineConfigurationService.findByAirlineId(destination.getAirline().getId());
				result += childrenNumber * (destination.getEconomyPrice() - (destination.getEconomyPrice() * airlineConfiguration.getChildrenDiscount() / 100));
			}
			result += destination.getDeparture().getRate() * (childrenNumber + passengersNumber);
		}

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

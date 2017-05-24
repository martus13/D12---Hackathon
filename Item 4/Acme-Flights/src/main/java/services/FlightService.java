
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FlightRepository;
import domain.Book;
import domain.Configuration;
import domain.Finder;
import domain.Flight;
import domain.Manager;

@Service
@Transactional
public class FlightService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private FlightRepository		flightRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ManagerService			managerService;

	@Autowired
	private BookService				bookService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructors -----------------------------------------------------------
	public FlightService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Flight findOne(final int flightId) {
		Assert.isTrue(flightId != 0);
		Flight result;

		result = this.flightRepository.findOne(flightId);

		return result;
	}

	public Collection<Flight> findAll() {
		Collection<Flight> result;

		result = this.flightRepository.findAll();

		return result;
	}

	public Flight create() {
		Flight result;
		Manager manager;
		Calendar calendar;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		result = new Flight();
		result.setAirline(manager.getAirline());
		result.setCreationMoment(calendar.getTime());
		result.setCancelled(false);

		return result;
	}

	public Flight save(Flight flight) {
		Assert.notNull(flight);
		Manager manager;

		if (flight.getId() == 0) {
			manager = this.managerService.findByPrincipal();
			Assert.notNull(manager);
			Assert.isTrue(flight.getAirline().equals(manager.getAirline()));
		}

		flight = this.flightRepository.save(flight);

		return flight;
	}

	public void delete(final Flight flight) {
		Calendar calendar;
		Collection<Book> books;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		Assert.isTrue(flight.getDepartureDate().after(calendar.getTime()));

		books = this.bookService.findNotCancelledByFlightId(flight.getId());

		for (final Book b : books)
			this.bookService.delete(b);

		flight.setCancelled(true);
		this.save(flight);
	}
	// Other business methods -------------------------------------------------

	public Collection<Flight> findNotCancelled() {
		Collection<Flight> result;

		result = this.flightRepository.findNotCancelled();

		return result;
	}

	public Collection<Flight> findNotCancelledByAirline(final int airlineId) {
		Collection<Flight> result;

		result = this.flightRepository.findNotCancelledByAirline(airlineId);

		return result;
	}

	public Collection<Flight> findNotCancelledNotPassedWithBooksByAirlineId(final int airlineId) {
		Collection<Flight> result;

		result = this.flightRepository.findNotCancelledNotPassedWithBooksByAirlineId(airlineId);

		return result;
	}

	public Collection<Flight> findNotCancelledNotPassedByAirportId(final int airportId) {
		Collection<Flight> result;

		result = this.flightRepository.findNotCancelledNotPassedByAirportId(airportId);

		return result;
	}

	public Collection<Object[]> findByFinder(final Finder finder) {
		Collection<Object[]> result;
		Collection<Object[]> departures;
		Collection<Object[]> destinations;
		Integer totalPassengersNumber;
		Configuration configuration;
		Calendar calendar;
		final Calendar configurationCalendar;

		totalPassengersNumber = finder.getPassengersNumber() + finder.getChildrenNumber();
		departures = new ArrayList<Object[]>();
		destinations = new ArrayList<Object[]>();
		result = new ArrayList<Object[]>();

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		configuration = this.configurationService.findConfiguration();

		configurationCalendar = Calendar.getInstance();
		configurationCalendar.setTime(configuration.getCachedTime());

		if (finder.getIsBusiness()) {
			departures = this.findEconomyFlightsOfferAndSeasonByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), totalPassengersNumber);
			if (finder.getReturnFlight())
				destinations = this.findBusinessFlightsOfferAndSeasonByFinder(finder.getDestination().getId(), finder.getDeparture().getId(), finder.getReturnDate(), totalPassengersNumber);
		} else {
			departures = this.findEconomyFlightsOfferAndSeasonByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), totalPassengersNumber);
			if (finder.getReturnFlight())
				destinations = this.findBusinessFlightsOfferAndSeasonByFinder(finder.getDestination().getId(), finder.getDeparture().getId(), finder.getReturnDate(), totalPassengersNumber);
		}

		for (final Object[] fDeparture : departures)
			if (destinations.isEmpty()) {
				Object aux[];

				aux = new Object[1];

				aux[0] = fDeparture;
				result.add(aux);
			} else
				for (final Object[] fDestination : destinations) {
					Object aux[];

					aux = new Object[2];

					aux[0] = fDeparture;
					aux[1] = fDestination;
					result.add(aux);
				}

		return result;
	}

	public Collection<Object[]> findEconomyFlightsOfferAndSeasonByFinder(final int departureId, final int destinationId, final Date departureDate, final Integer totalPassengersNumber) {
		Collection<Object[]> result;

		result = this.flightRepository.findEconomyFlightsOfferAndSeasonByFinder(departureId, destinationId, departureDate, totalPassengersNumber);

		return result;
	}

	public Collection<Object[]> findBusinessFlightsOfferAndSeasonByFinder(final int departureId, final int destinationId, final Date departureDate, final Integer totalPassengersNumber) {
		Collection<Object[]> result;

		result = this.flightRepository.findBusinessFlightsOfferAndSeasonByFinder(departureId, destinationId, departureDate, totalPassengersNumber);

		return result;
	}

	public Object[] findMaxMinAvgFlightsPerAirport() {
		Object[] result;

		result = this.flightRepository.findMaxMinAvgFlightsPerAirport();

		return result;
	}

	public Collection<Object[]> findPercentFlightsPerAirline() {
		Collection<Object[]> result;

		result = this.flightRepository.findPercentFlightsPerAirline();

		return result;
	}
}


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
import domain.Finder;
import domain.Flight;
import domain.Manager;

@Service
@Transactional
public class FlightService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private FlightRepository	flightRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ManagerService		managerService;

	@Autowired
	private BookService			bookService;


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
		result.setAvailableBusinessSeats(0);
		result.setAvailableEconomySeats(0);

		return result;
	}

	public Flight save(Flight flight) {
		Assert.notNull(flight);
		Manager manager;

		if (flight.getId() == 0) {
			manager = this.managerService.findByPrincipal();
			Assert.notNull(manager);
			Assert.isTrue(flight.getAirline().equals(manager.getAirline()));
			flight.setAvailableBusinessSeats(flight.getBusinessSeats());
			flight.setAvailableEconomySeats(flight.getEconomySeats());
		} else if (!flight.getCancelled()) {
			flight.setAvailableBusinessSeats(this.findAvailableBusinessSeatByFlightId(flight.getId()));
			flight.setAvailableEconomySeats(this.findAvailableEconomySeatByFlightId(flight.getId()));
		}
		Assert.isTrue(!flight.getDeparture().equals(flight.getDestination()));
		Assert.isTrue(flight.getArrivalDate().after(flight.getDepartureDate()));
		Calendar calendar;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		Assert.isTrue(flight.getDepartureDate().after(calendar.getTime()));

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

	public Collection<Object[]> findNotCancelledNotPassedOfferAndSeason() {
		Collection<Object[]> result;

		result = this.flightRepository.findNotCancelledNotPassedOfferAndSeason();

		return result;
	}

	public Collection<Flight> findNotCancelledByAirlineId(final int airlineId) {
		Collection<Flight> result;

		result = this.flightRepository.findNotCancelledByAirlineId(airlineId);

		return result;
	}

	public Collection<Object[]> findNotCancelledByAirlineIdOfferAndSeason(final int airlineId) {
		Collection<Object[]> result;

		result = this.flightRepository.findNotCancelledByAirlineIdOfferAndSeason(airlineId);

		return result;
	}

	public Collection<Flight> findNotCancelledNotPassedByAirlineId(final int airlineId) {
		Collection<Flight> result;

		result = this.flightRepository.findNotCancelledNotPassedByAirlineId(airlineId);

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
		Calendar calendar;

		totalPassengersNumber = finder.getPassengersNumber() + finder.getChildrenNumber();
		departures = new ArrayList<Object[]>();
		destinations = new ArrayList<Object[]>();
		result = new ArrayList<Object[]>();

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);

		if (finder.getIsBusiness()) {
			departures = this.findBusinessFlightsOfferAndSeasonByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), totalPassengersNumber, finder.getUser().getId());
			if (finder.getReturnFlight())
				destinations = this.findBusinessFlightsOfferAndSeasonByFinder(finder.getDestination().getId(), finder.getDeparture().getId(), finder.getReturnDate(), totalPassengersNumber, finder.getUser().getId());
		} else {
			departures = this.findEconomyFlightsOfferAndSeasonByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), totalPassengersNumber, finder.getUser().getId());
			if (finder.getReturnFlight())
				destinations = this.findEconomyFlightsOfferAndSeasonByFinder(finder.getDestination().getId(), finder.getDeparture().getId(), finder.getReturnDate(), totalPassengersNumber, finder.getUser().getId());
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

	public Collection<Object[]> findFlightsOfferAndSeasonByFlightsId(final Collection<Flight> flights) {
		Collection<Object[]> result;
		ArrayList<Flight> arrayFlights;
		int flightDepartureId;
		int flightDestinationId;

		arrayFlights = new ArrayList<Flight>(flights);
		flightDepartureId = 0;
		flightDestinationId = 0;

		for (int i = 0; i < arrayFlights.size(); i++)
			if (i == 0)
				flightDepartureId = arrayFlights.get(i).getId();
			else if (i == 1)
				flightDestinationId = arrayFlights.get(i).getId();

		result = this.flightRepository.findFlightsOfferAndSeasonByFlightsId(flightDepartureId, flightDestinationId);

		return result;
	}
	public Collection<Object[]> findFlightsOfferAndSeasonNotCancelledMostBook() {
		Collection<Object[]> result;

		result = this.flightRepository.findFlightsOfferAndSeasonNotCancelledMostBook();

		return result;
	}

	public Collection<Object[]> findNotCancelledNotPassedSeasonByOffer(final int offerId) {
		Collection<Object[]> result;

		result = this.flightRepository.findNotCancelledNotPassedSeasonByOffer(offerId);

		return result;
	}

	public Integer findAvailableBusinessSeatByFlightId(final int flightId) {
		Integer result;

		result = this.flightRepository.findAvailableBusinessSeatByFlightId(flightId);

		return result;
	}

	public Integer findAvailableEconomySeatByFlightId(final int flightId) {
		Integer result;

		result = this.flightRepository.findAvailableEconomySeatByFlightId(flightId);

		return result;
	}

	public Collection<Object[]> findEconomyFlightsOfferAndSeasonByFinder(final int departureId, final int destinationId, final Date departureDate, final Integer totalPassengersNumber, final int userId) {
		Collection<Object[]> result;

		result = this.flightRepository.findEconomyFlightsOfferAndSeasonByFinder(departureId, destinationId, departureDate, totalPassengersNumber, userId);

		return result;
	}

	public Collection<Object[]> findBusinessFlightsOfferAndSeasonByFinder(final int departureId, final int destinationId, final Date departureDate, final Integer totalPassengersNumber, final int userId) {
		Collection<Object[]> result;

		result = this.flightRepository.findBusinessFlightsOfferAndSeasonByFinder(departureId, destinationId, departureDate, totalPassengersNumber, userId);

		return result;
	}

	public Object[] findMaxMinAvgFlightsPerAirport() {
		Object[] result;

		result = this.flightRepository.findMaxMinAvgFlightsPerAirport();

		return result;
	}

	public Collection<Object[]> findPercentFlightsPerAirlineHigh() {
		Collection<Object[]> result;

		result = this.flightRepository.findPercentFlightsPerAirlineHigh();

		return result;
	}

	public Collection<Object[]> findPercentFlightsPerAirlineLow() {

		return this.flightRepository.findPercentFlightsPerAirlineLow();
	}

	public Collection<Flight> findByUserAndAirline(final int userId, final int airlineId) {

		return this.flightRepository.findByUserAndAirline(userId, airlineId);
	}
}

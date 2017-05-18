
package services;

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

		return result;
	}

	public Flight save(Flight flight) {
		Assert.notNull(flight);
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Assert.isTrue(flight.getAirline().equals(manager.getAirline()));

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

	public Collection<Flight[]> findByFinder(final Finder finder) {
		Collection<Flight[]> result;
		Integer totalPassengersNumber;

		totalPassengersNumber = finder.getPassengersNumber() + finder.getChildrenNumber();

		if (finder.getIsBusiness()) {
			if (finder.getReturnFlight())
				result = this.findNotCancelledBusinessByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), totalPassengersNumber);
			else
				result = this.findNotCancelledBusinessWithReturnByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), finder.getReturnDate(), totalPassengersNumber);
		} else if (finder.getReturnFlight())
			result = this.findNotCancelledEconomyByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), totalPassengersNumber);
		else
			result = this.findNotCancelledEconomyWithReturnByFinder(finder.getDeparture().getId(), finder.getDestination().getId(), finder.getDepartureDate(), finder.getReturnDate(), totalPassengersNumber);

		return result;
	}

	public Collection<Flight[]> findNotCancelledEconomyByFinder(final int departureId, final int destinationId, final Date departureDate, final Integer totalPassengersNumber) {
		Collection<Flight[]> result;

		result = this.flightRepository.findNotCancelledEconomyByFinder(departureId, destinationId, departureDate, totalPassengersNumber);

		return result;
	}

	public Collection<Flight[]> findNotCancelledEconomyWithReturnByFinder(final int departureId, final int destinationId, final Date departureDate, final Date returnDate, final Integer totalPassengersNumber) {
		Collection<Flight[]> result;

		result = this.flightRepository.findNotCancelledEconomyWithReturnByFinder(departureId, destinationId, departureDate, returnDate, totalPassengersNumber);

		return result;
	}

	public Collection<Flight[]> findNotCancelledBusinessByFinder(final int departureId, final int destinationId, final Date departureDate, final Integer totalPassengersNumber) {
		Collection<Flight[]> result;

		result = this.flightRepository.findNotCancelledBusinessByFinder(departureId, destinationId, departureDate, totalPassengersNumber);

		return result;
	}

	public Collection<Flight[]> findNotCancelledBusinessWithReturnByFinder(final int departureId, final int destinationId, final Date departureDate, final Date returnDate, final Integer totalPassengersNumber) {
		Collection<Flight[]> result;

		result = this.flightRepository.findNotCancelledBusinessWithReturnByFinder(departureId, destinationId, departureDate, returnDate, totalPassengersNumber);

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

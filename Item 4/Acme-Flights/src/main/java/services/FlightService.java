
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FlightRepository;
import domain.Flight;

@Service
@Transactional
public class FlightService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private FlightRepository	flightRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ManagerService		managerService;


	// Constructors -----------------------------------------------------------
	public FlightService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

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

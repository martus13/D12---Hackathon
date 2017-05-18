
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AirportRepository;
import domain.Administrator;
import domain.Airport;
import domain.Flight;

@Service
@Transactional
public class AirportService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AirportRepository		airportRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private FlightService			flightService;


	// Constructors -----------------------------------------------------------
	public AirportService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Airport findOne(final int airportId) {
		Assert.isTrue(airportId != 0);
		Airport result;

		result = this.airportRepository.findOne(airportId);

		return result;
	}

	public Collection<Airport> findAll() {
		Collection<Airport> result;

		result = this.airportRepository.findAll();

		return result;
	}

	public Airport create() {
		Airport airport;
		Administrator administrator;
		Collection<Flight> flights;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		flights = new ArrayList<Flight>();

		airport = new Airport();
		airport.setOutgoings(flights);
		airport.setDeleted(false);

		return airport;
	}

	public Airport save(Airport airport) {
		Assert.notNull(airport);
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		airport = this.airportRepository.save(airport);

		return airport;

	}

	public void delete(final Airport airport) {
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		Assert.isTrue(this.flightService.findNotCancelledNotPassedByAirportId(airport.getId()).isEmpty());

		airport.setDeleted(true);
		this.airportRepository.save(airport);

	}
	// Other business methods -------------------------------------------------

	public Collection<Object[]> findMostVisitedCities() {
		Collection<Object[]> result;

		result = this.airportRepository.findMostVisitedCities();

		return result;
	}
}

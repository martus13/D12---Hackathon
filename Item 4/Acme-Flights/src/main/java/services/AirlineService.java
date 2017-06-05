
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AirlineRepository;
import domain.Administrator;
import domain.Airline;
import domain.Season;

@Service
@Transactional
public class AirlineService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AirlineRepository		airlineRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private FlightService			flightService;


	// Constructors -----------------------------------------------------------
	public AirlineService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Airline findOne(final int airlineId) {
		Assert.isTrue(airlineId != 0);
		Airline result;

		result = this.airlineRepository.findOne(airlineId);

		return result;
	}

	public Collection<Airline> findAll() {
		Collection<Airline> result;

		result = this.airlineRepository.findAll();

		return result;
	}

	public Page<Airline> findAllPaged(final Integer pageNumber, final Integer pageSize) {
		Page<Airline> result;
		PageRequest request;

		request = new PageRequest(pageNumber, pageSize);

		result = this.airlineRepository.findAllPaged(request);

		return result;
	}

	public Airline create() {
		Airline airline;
		Administrator administrator;
		Collection<Season> seasons;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		seasons = new ArrayList<Season>();

		airline = new Airline();
		airline.setRating(0.0);
		airline.setDeleted(false);
		airline.setSeasons(seasons);

		return airline;
	}

	public Airline save(Airline airline) {
		Assert.notNull(airline);

		airline = this.airlineRepository.save(airline);

		return airline;
	}

	public void delete(Airline airline) {
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		Assert.isTrue(this.flightService.findNotCancelledNotPassedByAirlineId(airline.getId()).isEmpty());

		airline.setDeleted(true);
		airline = this.airlineRepository.save(airline);

	}

	// Other business methods -------------------------------------------------

	public Collection<Airline> findNotDeleted() {
		Collection<Airline> result;

		result = this.airlineRepository.findNotDeleted();

		return result;

	}

	public Integer find10percentAirlines() {

		Double result;

		result = this.airlineRepository.find10percentAirlines();

		return (int) Math.round(result);

	}

	public Collection<Object[]> findMinMaxAvgRatingByAirline() {
		Collection<Object[]> result;

		result = this.airlineRepository.findMinMaxAvgRatingByAirline();

		return result;

	}

	public Collection<Object[]> findMinMaxAvgComfortRatingByAirline() {
		Collection<Object[]> result;

		result = this.airlineRepository.findMinMaxAvgComfortRatingByAirline();

		return result;
	}

	public Airline findByManager(final int managerId) {

		return this.airlineRepository.findByManager(managerId);
	}

	public Collection<Object[]> findAirlineLessBooks() {

		return this.airlineRepository.findAirlineLessBooks();
	}

	public Collection<Object[]> findAirlineMostBooks() {
		return this.airlineRepository.findAirlineMostBooks();
	}

	public Collection<Object[]> findAirlineMostFlights() {

		return this.airlineRepository.findAirlineMostFlights();
	}

	public Collection<Object[]> findAirlineLessFlights() {

		return this.airlineRepository.findAirlineLessFlights();
	}

	public Collection<Object[]> findAirlinesOrderByNumberOfBills() {
		return this.airlineRepository.findAirlinesOrderByNumberOfBills();
	}

	public Collection<Object[]> findPercentagePaidBills() {
		return this.airlineRepository.findPercentagePaidBills();
	}

	public Collection<Object[]> findMostPercentageDiscount() {
		return this.airlineRepository.findMostPercentageDiscount();
	}

	public Collection<Object[]> findLessPercentageDiscount() {
		return this.airlineRepository.findLessPercentageDiscount();

	}
	public Collection<Object[]> findPositiveComments() {
		return this.airlineRepository.findPositiveComments();
	}

	public Collection<Object[]> findMinMaxAvgServiceRatingByAirline() {
		return this.airlineRepository.findMinMaxAvgServiceRatingByAirline();
	}
}

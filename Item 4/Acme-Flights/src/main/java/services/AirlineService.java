
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		airline = this.airlineRepository.save(airline);

		return airline;
	}

	public void delete(Airline airline) {
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		// TODO: se marca como eliminado, y no se puede eliminar si tiene vuelos con reservas que aun no ha salido

		airline.setDeleted(true);
		airline = this.airlineRepository.save(airline);

	}

	// Other business methods -------------------------------------------------

	public Collection<Airline> findNotDeleted() {
		Collection<Airline> result;

		result = this.airlineRepository.findNotDeleted();

		return result;

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

}

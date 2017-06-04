
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SeasonRepository;
import domain.Book;
import domain.Flight;
import domain.Manager;
import domain.Season;

@Service
@Transactional
public class SeasonService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SeasonRepository	seasonReporitory;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private BookService			bookService;


	// Constructors -----------------------------------------------------------
	public SeasonService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Season findOne(final int seasonId) {
		Assert.isTrue(seasonId != 0);

		Season result;

		result = this.seasonReporitory.findOne(seasonId);

		return result;
	}

	public Collection<Season> findAll() {
		Collection<Season> result;

		result = this.seasonReporitory.findAll();

		return result;
	}

	public Season create() {
		Season result;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		result = new Season();
		result.setAirline(manager.getAirline());
		result.setInactive(false);

		return result;
	}

	public Season create(final Season season) {
		Season result;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		result = new Season();
		result.setAirline(manager.getAirline());
		result.setInactive(false);
		result.setTitle(season.getTitle());
		result.setStartDay(season.getStartDay());
		result.setStartMonth(season.getStartMonth());
		result.setEndDay(season.getEndDay());
		result.setEndMonth(season.getEndMonth());
		result.setType(season.getType());
		result.setPricePercentage(season.getPricePercentage());

		return result;
	}

	public Season save(Season season) {
		Assert.notNull(season);
		Manager manager;
		Season firstSeason = null;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Assert.isTrue(season.getAirline().equals(manager.getAirline()));
		// Comporbamos que los dias esten bien
		if (season.getStartMonth() == 1 || season.getStartMonth() == 3 || season.getStartMonth() == 5 || season.getStartMonth() == 7 || season.getStartMonth() == 8 || season.getStartMonth() == 10 || season.getStartMonth() == 12)
			Assert.isTrue(season.getStartDay() <= 31);
		else if (season.getStartMonth() == 2)
			Assert.isTrue(season.getStartDay() <= 29);
		else
			Assert.isTrue(season.getStartDay() <= 30);
		if (season.getEndMonth() == 1 || season.getEndMonth() == 3 || season.getEndMonth() == 5 || season.getEndMonth() == 7 || season.getEndMonth() == 8 || season.getEndMonth() == 10 || season.getEndMonth() == 12)
			Assert.isTrue(season.getEndDay() <= 31);
		else if (season.getEndMonth() == 2)
			Assert.isTrue(season.getEndDay() <= 29);
		else
			Assert.isTrue(season.getEndDay() <= 30);

		// Comprobamos que si la fecha de fin es menor que la de inicio, se tiene que dividir:
		if (season.getStartMonth() > season.getEndMonth() || (season.getStartMonth() == season.getEndMonth() && season.getStartDay() > season.getEndDay())) {

			firstSeason = this.create(season);
			firstSeason.setEndDay(31);
			firstSeason.setEndMonth(12);

			// Comprobamos que no se solapen temporadas
			Assert.isNull(this.findOverlappingByAirline(firstSeason.getAirline().getId(), firstSeason.getStartDay(), firstSeason.getStartMonth(), firstSeason.getEndDay(), firstSeason.getEndMonth()));

			season.setStartDay(1);
			season.setStartMonth(1);

		}
		// Comprobamos que no se solapen temporadas
		Assert.isNull(this.findOverlappingByAirline(season.getAirline().getId(), season.getStartDay(), season.getStartMonth(), season.getEndDay(), season.getEndMonth()));

		season = this.seasonReporitory.save(season);
		if (firstSeason != null)
			this.seasonReporitory.save(firstSeason);

		return season;
	}
	public void delete(final Season season) {
		Assert.notNull(season);
		Manager manager;
		Collection<Book> books;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Assert.isTrue(season.getAirline().equals(manager.getAirline()));

		books = this.bookService.findOfNotPassedFlightsBySeason(season);

		if (!books.isEmpty()) {
			// comprobar si tiene reservas de vuelos en ese intervalo, se marca como inactivo
			season.setInactive(true);
			this.seasonReporitory.save(season);
		} else
			// si no, se elimina
			this.seasonReporitory.delete(season);

	}
	// Other business methods -------------------------------------------------

	public Collection<Season> findActiveByAirlineId(final int airlineId) {
		Collection<Season> result;

		result = this.seasonReporitory.findActiveByAirlineId(airlineId);

		return result;
	}

	public Season findActiveByDateAndAirlineId(final int airlineId, final Date date) {
		Season result;

		result = this.seasonReporitory.findActiveByDateAndAirlineId(airlineId, date);

		return result;
	}

	public Season findActiveByFlight(final Flight flight) {
		Season result;

		result = this.seasonReporitory.findActiveByDateAndAirlineId(flight.getAirline().getId(), flight.getDepartureDate());

		return result;
	}

	public Season findOverlappingByAirline(final int airlineId, final Integer startDay, final Integer startMonth, final Integer endDay, final Integer endMonth) {
		Season result;

		result = this.seasonReporitory.findOverlappingByAirline(airlineId, startDay, startMonth, endDay, endMonth);

		return result;
	}

}

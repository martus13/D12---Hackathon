
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Season;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SeasonServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private SeasonService	seasonService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con Season.

	// Listar las temporadas de una aerolínea
	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{
				117, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testFindByAirlineId((int) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	// Crear temporadas:
	@Test
	public void driveCreateAndSave() {

		final Object testingData[][] = {

			{ // Bien:
				"manager3", "prueba1", 1, 4, 1, 5, "increase", 10.0, null
			}, { // Error! Manager no logueado:
				null, "prueba1", 1, 4, 1, 5, "increase", 10.0, IllegalArgumentException.class
			}, { // Error! Temporadas solapadas:
				"manager3", "prueba1", 10, 5, 20, 5, "discount", 2.0, IllegalArgumentException.class
			}, { // Error! Dias mal:
				"manager3", "prueba1", 30, 2, 31, 10, "increase", 1.0, IllegalArgumentException.class
			}, { // Bien:
				"manager3", "prueba1", 10, 12, 10, 1, "increase", 65.2, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Integer) testingData[i][4], (Integer) testingData[i][5], (String) testingData[i][6],
				(Double) testingData[i][7], (Class<?>) testingData[i][8]);
	}
	// Eliminar temporadas:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // Bien:
				"manager2", 123, null
			}, { // Error! no se corresponde el manager con la temporada:
				"manager2", 124, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void testFindByAirlineId(final int airlineId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Season> seasons;

			seasons = this.seasonService.findActiveByAirlineId(airlineId);
			Assert.notNull(seasons);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testCreateAndSave(final String username, final String title, final Integer startDay, final Integer startMonth, final Integer endDay, final Integer endMonth, final String type, final Double pricePercentage, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Season season;

			season = this.seasonService.create();
			season.setTitle(title);
			season.setStartDay(startDay);
			season.setStartMonth(startMonth);
			season.setEndDay(endDay);
			season.setEndMonth(endMonth);
			season.setType(type);
			season.setPricePercentage(pricePercentage);

			this.seasonService.save(season);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final int seasonId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Season season;

			season = this.seasonService.findOne(seasonId);

			this.seasonService.delete(season);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

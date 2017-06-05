
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Airport;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AirportServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AirportService	airportService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con Flight.

	// Crear vuelos:
	@Test
	public void driveCreateAndSave() {

		final Object testingData[][] = {

			{ // Bien:
				"admin", null, "Valencianido", "Valencia", "España", "VLC", 4.0, false
			}, {// Usuario distinto de administrador
				"manager2", IllegalArgumentException.class, "Valencianido", "Valencia", "España", "VLC", 4.0, false
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Double) testingData[i][6],
				(Boolean) testingData[i][7]);
	}
	// Eliminar vuelos:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // Intentar borrar un aeropuerto con vuelos
				"admin", 112, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void testCreateAndSave(final String username, final Class<?> expected, final String airportName, final String city, final String country, final String iataCode, final Double rate, final Boolean deleted) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Airport airport;

			airport = this.airportService.create();
			airport.setAirportName(airportName);
			airport.setCity(city);
			airport.setCountry(country);
			airport.setIataCode(iataCode);
			airport.setRate(rate);
			airport.setDeleted(deleted);

			airport = this.airportService.save(airport);
			Assert.notNull(airport);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final int airportId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Airport airport;

			airport = this.airportService.findOne(airportId);

			this.airportService.delete(airport);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

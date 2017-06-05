
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Airline;
import domain.Season;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AirlineServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AirlineService	airlineService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con Airline.

	// Crear Airline:
	@Test
	public void driveCreateAndSave() {
		final Object testingData[][] = {

			{ // Bien:
				"admin", "Tussan Airlines", "http://www.alfabetajuega.com/thumbnails/concursos/231.concurso_interior_generico.col.jpg", "665789345", "tussi@gmail.com", 2.0, null
			}, {//Error: Solo el administrador puede crearlo
				"manager", "Tussan Airlines", "http://www.alfabetajuega.com/thumbnails/concursos/231.concurso_interior_generico.col.jpg", "665789345", "tussi@gmail.com", 2.0, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Double) testingData[i][5], (Class<?>) testingData[i][6]);
	}
	// Eliminar Airline:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // No se puede borrar ya que tiene vuelos pendientes:
				"admin", 118, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void testCreateAndSave(final String username, final String name, final String picture, final String contactPhone, final String email, final Double rating, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Airline airline;
			Collection<Season> seasons;

			seasons = new ArrayList<Season>();
			airline = this.airlineService.create();
			airline.setContactPhone(contactPhone);
			airline.setEmail(email);
			airline.setName(name);
			airline.setPicture(picture);
			airline.setRating(rating);
			airline.setSeasons(seasons);

			airline = this.airlineService.save(airline);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final int airlineId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Airline airline;

			airline = this.airlineService.findOne(airlineId);

			this.airlineService.delete(airline);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

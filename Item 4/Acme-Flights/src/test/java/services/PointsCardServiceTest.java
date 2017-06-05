
package services;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Airline;
import domain.PointsCard;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PointsCardServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private PointsCardService	pointsCardService;

	@Autowired
	private AirlineService		airlineService;

	@Autowired
	private UserService			userService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con PointsCard.

	// Crear PointsCard:
	@Test
	public void driveCreateAndSave() {
		Calendar expirationMoment;

		expirationMoment = Calendar.getInstance();
		expirationMoment.add(Calendar.DAY_OF_MONTH, +10);

		final Object testingData[][] = {

			{ // Bien:
				164, 119, null
			}, { // Error! ya existia una tarjeta de ese usuario y esa aerolinea:
				163, 119, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((int) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	// Ejecutar un procedimiento para borrar los puntos de las tarjetas cuya ultima reserva se realizo hace mas de un año:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // Bien:
				"manager1", null
			}, { // Error! manager no logueado:
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void testCreateAndSave(final int userId, final int airlineId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			PointsCard pointsCard;
			User user;
			Airline airline;

			user = this.userService.findOne(userId);
			airline = this.airlineService.findOne(airlineId);

			pointsCard = this.pointsCardService.create(user, airline);
			pointsCard = this.pointsCardService.save(pointsCard);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			this.pointsCardService.resetExpired();

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

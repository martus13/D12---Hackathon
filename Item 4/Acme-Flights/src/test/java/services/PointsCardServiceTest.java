
package services;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Airline;
import domain.Manager;
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

	@Autowired
	private ManagerService		managerService;


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
				"manager", expirationMoment.getTime(), 25, 163, 117, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (Date) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Integer) testingData[i][4], (Class<?>) testingData[i][5]);
	}
	// Eliminar puntos de PointsCard:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // 
				"manager1", 163, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void testCreateAndSave(final String username, final Date expirationMoment, final Integer points, final Integer userId, final Integer airlineId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			PointsCard pointsCard;
			User user;
			Airline airline;

			user = this.userService.create();
			user.getUserAccount().setUsername("prueba1");
			user.getUserAccount().setPassword(this.userService.encryptPassword("prueba"));
			user.setName("prueba");
			user.setSurname("prueba");
			user.setContactPhone("666777888");
			user.setEmail("p@r.com");

			user = this.userService.save(user);

			airline = this.airlineService.findOne(airlineId);

			pointsCard = this.pointsCardService.create(user, airline);

			pointsCard = this.pointsCardService.save(pointsCard);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final int userId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Airline airline;
			Manager manager;
			PointsCard pointsCard;

			manager = this.managerService.findByPrincipal();
			airline = this.airlineService.findOne(manager.getAirline().getId());

			pointsCard = this.pointsCardService.findByUserAndAirlineId(userId, airline.getId());
			this.pointsCardService.delete(pointsCard);
			this.unauthenticate();
			Assert.isTrue(pointsCard.getPoints() == 0);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

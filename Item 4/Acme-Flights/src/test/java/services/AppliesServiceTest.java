
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
import domain.Applies;
import domain.Book;
import domain.Flight;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AppliesServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AppliesService	appliesService;

	@Autowired
	private FlightService	flightService;

	@Autowired
	private BookService		bookService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con Flight.

	// Listados:
	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{
				178, 173, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.testFindByBookId((int) testingData[i][0], (Class<?>) testingData[i][2]);
			this.testFindByPointsCardId((int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	// Crear vuelos:
	@Test
	public void driveCreateAndSave() {

		final Object testingData[][] = {

			{ // Bien:
				"user1", 178, 137, 1, null
			}, { // Error! User no logueado:
				null, 178, 137, 1, IllegalArgumentException.class
			}, { // Error! Usuario no book:
				"user1", 180, 132, 1, IllegalArgumentException.class
			}, { // Error! Mas puntos de los existentes:
				"user1", 178, 137, 100, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (int) testingData[i][3], (Class<?>) testingData[i][4]);
	}
	// Eliminar vuelos:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // Bien:
				"user1", 184, null
			}, { // Error! User no logueado:
				null, 184, IllegalArgumentException.class
			}, { // Error! Usuario no applies:
				"user2", 184, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void testFindByPointsCardId(final int bookId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Applies> applies;

			applies = this.appliesService.findByBookId(bookId);
			Assert.notNull(applies);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testFindByBookId(final int pointsCard, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Applies> applies;

			applies = this.appliesService.findByPointsCardId(pointsCard);
			Assert.notNull(applies);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testCreateAndSave(final String username, final int bookId, final int flightId, final Integer points, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Flight flight;
			Book book;
			Applies applies;

			flight = this.flightService.findOne(flightId);
			book = this.bookService.findOne(bookId);

			applies = this.appliesService.create(book, flight);
			applies.setUsedPoints(points);

			applies = this.appliesService.save(applies);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final int appliesId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Applies applies;

			applies = this.appliesService.findOne(appliesId);

			this.appliesService.delete(applies);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

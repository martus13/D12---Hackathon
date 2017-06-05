
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
import domain.Book;
import domain.Flight;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BookServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private BookService		bookService;

	@Autowired
	private FlightService	flightService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con Book.

	// Listar las reservas de un usuario:
	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{
				163, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testFindByUser((int) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Hacer una reserva
	@Test
	public void driverCreateAndSave() {
		final Object testingData[][] = {
			{	// Bien:
				"user1", 132, 130, null, 143, 133, 130, null, 143, null
			}, { // Error! User no logueado:
				null, 132, 130, null, 143, 133, 130, null, 143, IllegalArgumentException.class
			}, { // Error! Solapamiento:
				"user1", 137, null, null, null, 138, null, null, null, IllegalArgumentException.class
			}, { // Error! Tarjeta de crédito no válida:
				"user3", 132, 130, null, 143, 133, 130, null, 143, IllegalArgumentException.class
			}, { // Error! Aerolinea de ida y vuelta iguales:
				"user1", 132, null, null, null, 132, null, null, null, IllegalArgumentException.class
			}, { // Error! Vuelos pasados:
				"user1", 139, null, null, null, 140, null, null, null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (Integer) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Integer) testingData[i][4], (Integer) testingData[i][5], (Integer) testingData[i][6],
				(Integer) testingData[i][7], (Integer) testingData[i][8], (Class<?>) testingData[i][9]);
	}

	// Cancelar una reserva:
	@Test
	public void driverCancel() {
		final Object testingData[][] = {
			{	// Bien:
				"user2", 182, null
			}, { // Error! User no creo la reserva:
				"user2", 179, IllegalArgumentException.class
			}, { // Error! Ya ha pasado la fecha máxima permitida para cancelar la reserva:
				"user2", 178, IllegalArgumentException.class
			}, { // Error! El manager no puede cancelar la reserva:
				"manager2", 179, IllegalArgumentException.class
			}, { // Bien:
				"manager3", 182, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCancel((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void testFindByUser(final int userId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Book> books;

			books = this.bookService.findByUser(userId);
			Assert.notNull(books);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testCreateAndSave(final String username, final Integer departureId, final Integer season1Id, final Integer offert1Id, final Integer offer2Id, final Integer destinationId, final Integer season2Id, final Integer offer4Id,
		final Integer offer5Id, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Book book;
			Flight departure;
			Flight destination;

			departure = this.flightService.findOne(departureId);
			destination = this.flightService.findOne(destinationId);

			book = this.bookService.create(departure, season1Id, offert1Id, offer2Id, destination, season2Id, offer4Id, offer5Id);

			book = this.bookService.save(book);
			Assert.isTrue(book.getId() != 0);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testCancel(final String username, final int bookId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Book book;

			book = this.bookService.findOne(bookId);

			this.bookService.delete(book);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}

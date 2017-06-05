
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Airport;
import domain.Flight;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FlightServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private FlightService	flightService;

	@Autowired
	private AirportService	airportService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con Flight.

	// Listados:
	//// Listar todos los vuelos disponibles
	//// Listar los vuelos con más reservas que no han pasado aún
	//// Listar vuelos usando el buscador
	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{
				163, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.testFindNotCancelledNotPassedOfferAndSeason((Class<?>) testingData[i][1]);
			this.testFindFlightsOfferAndSeasonNotCancelledMostBook((Class<?>) testingData[i][1]);
		}
	}
	// Crear vuelos:
	@Test
	public void driveCreateAndSave() {
		Calendar departureDate;
		Calendar arrivalDate;
		Calendar wrongArrivalDate;
		Calendar wrongDpartureDate;

		departureDate = Calendar.getInstance();
		departureDate.add(Calendar.DAY_OF_MONTH, +10);

		arrivalDate = Calendar.getInstance();
		arrivalDate.add(Calendar.DAY_OF_MONTH, +20);

		wrongArrivalDate = departureDate;
		wrongArrivalDate.add(Calendar.DAY_OF_MONTH, -2);

		wrongDpartureDate = Calendar.getInstance();
		wrongDpartureDate.add(Calendar.DAY_OF_MONTH, -1);

		final Object testingData[][] = {

			{ // Bien:
				"manager1", 115, 114, departureDate.getTime(), arrivalDate.getTime(), 100.0, 50.0, 10, 100, null
			}, { // Error! Manager no logueado:
				null, 115, 114, departureDate.getTime(), arrivalDate.getTime(), 100.0, 50.0, 10, 100, IllegalArgumentException.class
			}, { // Error! Mismo aeropuerto de origen y destino:
				"manager1", 115, 115, departureDate.getTime(), arrivalDate.getTime(), 100.0, 50.0, 10, 100, IllegalArgumentException.class
			}, { // Error! fecha de llegada inferior a la de salida:
				"manager1", 115, 114, departureDate.getTime(), wrongArrivalDate.getTime(), 100.0, 50.0, 10, 100, IllegalArgumentException.class
			}, { // Error! fecha de salida inferior a la actual:
				"manager1", 115, 114, wrongDpartureDate.getTime(), arrivalDate.getTime(), 100.0, 50.0, 10, 100, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (Date) testingData[i][3], (Date) testingData[i][4], (Double) testingData[i][5], (Double) testingData[i][6], (Integer) testingData[i][7],
				(Integer) testingData[i][8], (Class<?>) testingData[i][9]);
	}
	// Eliminar vuelos:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // Bien:
				"manager2", 137, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void testFindNotCancelledNotPassedOfferAndSeason(final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Object[]> flights;

			flights = this.flightService.findNotCancelledNotPassedOfferAndSeason();
			Assert.notNull(flights);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testFindFlightsOfferAndSeasonNotCancelledMostBook(final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Object[]> flights;

			flights = this.flightService.findFlightsOfferAndSeasonNotCancelledMostBook();
			Assert.notNull(flights);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testCreateAndSave(final String username, final int departureId, final int destinationId, final Date departureDate, final Date arrivalDate, final Double businessPrice, final Double economyPrice, final Integer businessSeats,
		final Integer economySeats, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Flight flight;
			Airport departure;
			Airport destination;

			departure = this.airportService.findOne(departureId);
			destination = this.airportService.findOne(destinationId);

			flight = this.flightService.create();
			flight.setDeparture(departure);
			flight.setDestination(destination);
			flight.setDepartureDate(departureDate);
			flight.setArrivalDate(arrivalDate);
			flight.setBusinessPrice(businessPrice);
			flight.setEconomyPrice(economyPrice);
			flight.setBusinessSeats(businessSeats);
			flight.setEconomySeats(economySeats);

			flight = this.flightService.save(flight);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final int flightId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Flight flight;

			flight = this.flightService.findOne(flightId);

			this.flightService.delete(flight);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

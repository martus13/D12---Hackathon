
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.ExchangeRate;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ExchangeRateServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con ExchangeRate.

	// Crear ExchangeRate:
	@Test
	public void driveCreateAndSave() {

		final Object testingData[][] = {

			{ // Bien:
				"admin", null, "MON", "Money", 1.5
			}, { // Error! Solo los administradores pueden crearlo pueden:
				"manager", IllegalArgumentException.class, "MON", "Money", 1.5
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Double) testingData[i][4]);
	}
	// Eliminar ExchangeRate:
	@Test
	public void driveDelete() {
		final Object testingData[][] = {
			{ // Bien:
				"admin", 106, null
			}, { // Error! Solo los administradores pueden crearlo pueden:
				"manager", 106, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void testCreateAndSave(final String username, final Class<?> expected, final String isoCode, final String currency, final Double val1EUR) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			ExchangeRate exchangeRate;

			exchangeRate = this.exchangeRateService.create();
			exchangeRate.setCurrency(currency);
			exchangeRate.setIsoCode(isoCode);
			exchangeRate.setValue1EUR(val1EUR);

			exchangeRate = this.exchangeRateService.save(exchangeRate);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final String username, final int exchangeRatetId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			ExchangeRate exchangeRate;

			exchangeRate = this.exchangeRateService.findOne(exchangeRatetId);

			this.exchangeRateService.delete(exchangeRate);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}

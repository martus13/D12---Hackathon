
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Airline;
import domain.AirlineConfiguration;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AirlineConfigurationServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AirlineConfigurationService	airlineConfigurationService;

	@Autowired
	private AirlineService				airlineService;


	// Tests ------------------------------------------------------------------

	//Crear una nueva configuración de aerolínea
	@Test
	public void driverCreateAndSave() {
		final Object testingData[][] = {
			{	// Bien
				117, 1, 1, 0.0, 0.0, 0.0, null
			}, {//id erroneo 
				104, 1, 1, 0.0, 0.0, 0.0, IllegalArgumentException.class
			}, {//valores inválidos
				105, 1, -3, 0.0, 0.0, 0.0, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((int) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (double) testingData[i][3], (double) testingData[i][4], (double) testingData[i][5], (Class<?>) testingData[i][6]);

	}

	//Editar una configuración de aerolínea existente
	@Test
	public void driverEdit() {
		final Object testingData[][] = {
			{	// Bien
				120, 3, 16, 8.0, 10.0, 25.0, null
			}, {//id erroneo 
				104, 3, 16, 8.0, 10.0, 25.0, IllegalArgumentException.class
			}, {//valores inválidos
				105, 3, 16, -1.0, 10.0, 25.0, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.testEdit((int) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (double) testingData[i][3], (double) testingData[i][4], (double) testingData[i][5], (Class<?>) testingData[i][6]);

	}

	protected void testCreateAndSave(final int airlineId, final int maxCancellationDays, final int maxChildrenAge, final double childrenDiscount, final double maxBagWeight, final double overweightBagPrice, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			Airline airline;
			AirlineConfiguration airlineConfiguration;

			airline = this.airlineService.findOne(airlineId);
			Assert.notNull(airline);
			airlineConfiguration = this.airlineConfigurationService.create(airline);
			Assert.notNull(airlineConfiguration);

			airlineConfiguration.setMaxCancellationDays(maxCancellationDays);
			airlineConfiguration.setMaxChildrenAge(maxChildrenAge);
			airlineConfiguration.setChildrenDiscount(childrenDiscount);
			airlineConfiguration.setMaxBagWeight(maxBagWeight);
			airlineConfiguration.setOverweightBagPrice(overweightBagPrice);

			Assert.isTrue(maxCancellationDays > 0 && maxChildrenAge > 0 && childrenDiscount >= 0.0 && maxBagWeight >= 0.0 && overweightBagPrice >= 0.0);

			this.airlineConfigurationService.save(airlineConfiguration);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testEdit(final int airlineConfigurationId, final int maxCancellationDays, final int maxChildrenAge, final double childrenDiscount, final double maxBagWeight, final double overweightBagPrice, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			AirlineConfiguration airlineConfiguration;

			airlineConfiguration = this.airlineConfigurationService.findOne(airlineConfigurationId);
			Assert.notNull(airlineConfiguration);

			airlineConfiguration.setMaxCancellationDays(maxCancellationDays);
			airlineConfiguration.setMaxChildrenAge(maxChildrenAge);
			airlineConfiguration.setChildrenDiscount(childrenDiscount);
			airlineConfiguration.setMaxBagWeight(maxBagWeight);
			airlineConfiguration.setOverweightBagPrice(overweightBagPrice);

			Assert.isTrue(maxCancellationDays > 0 && maxChildrenAge > 0 && childrenDiscount >= 0.0 && maxBagWeight >= 0.0 && overweightBagPrice >= 0.0);

			this.airlineConfigurationService.save(airlineConfiguration);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	@Test
	public void testFindByAirlineId() {
		AirlineConfiguration airlineConfiguration;

		airlineConfiguration = this.airlineConfigurationService.findByAirlineId(117);
		Assert.notNull(airlineConfiguration);
	}

	@Test
	public void testFindByManager() {
		AirlineConfiguration airlineConfiguration;

		airlineConfiguration = this.airlineConfigurationService.findByManager(145);
		Assert.notNull(airlineConfiguration);
	}
}

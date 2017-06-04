
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
import domain.Campaign;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CampaignServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private CampaignService	campaignService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			{	// Bien
				"manager1", null
			}, {//usuario mal
				"m", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Eliminación de una campaña:
	@Test
	public void driverDelete() {
		final Object testingData[][] = {
			{//Bien
				"manager2", 151, null
			}, {//no se puede borrar porque tiene facturas sin pagar
				"manager1", 149, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	//Finds
	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{	// Bien
				"manager1", null, 118
			}, {//usuario mal
				"m", IllegalArgumentException.class, 118
			}, {
				"manager2", null, 119
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.testFindActiveByAirlineId((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2]);
			this.testFindByAirlineId((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2]);
			this.testFindNotDeletedByAirlineId((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2]);
		}
	}

	protected void testCreate(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Campaign campaign;

			campaign = this.campaignService.create();
			campaign = this.campaignService.save(campaign);

			Assert.notNull(campaign);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testDelete(final String username, final int campaignId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Campaign campaign;

			campaign = this.campaignService.findOne(campaignId);
			this.campaignService.delete(campaign);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testFindActiveByAirlineId(final String username, final Class<?> expected, final int airlineId) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Campaign campaign;

			campaign = this.campaignService.findActiveByAirlineId(airlineId);
			campaign = this.campaignService.save(campaign);
			System.out.println(campaign.getName());

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testFindByAirlineId(final String username, final Class<?> expected, final int airlineId) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Collection<Campaign> campaigns;

			campaigns = this.campaignService.findByAirlineId(airlineId);
			for (final Campaign c : campaigns)
				System.out.println(c.getName());

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testFindNotDeletedByAirlineId(final String username, final Class<?> expected, final int airlineId) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Collection<Campaign> campaigns;

			campaigns = this.campaignService.findNotDeletedByAirlineId(airlineId);
			for (final Campaign c : campaigns)
				System.out.println(c.getName());

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}

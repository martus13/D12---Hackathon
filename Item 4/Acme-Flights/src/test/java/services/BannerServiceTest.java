
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Banner;
import domain.Campaign;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BannerServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private BannerService	bannerService;

	@Autowired
	private CampaignService	campaignService;


	// Tests ------------------------------------------------------------------

	// Aquí se van a realizar los tests necesarios para comprobar el correcto funcionamiento de la administración
	// de los banners que se mostrarán en la pantalla principal.

	// Creación y edición de un banner:
	@Test
	public void driverCreateAndSave() {
		final Object testingData[][] = {
			{	// Bien
				"manager1", "http://chefyan.ca/files/2014/07/pad-thai-Banner-1020-x-400-588x230.jpg", 150, 154, null
			}, {// Error no existe la campaña
				"manager1", "http://chefyan.ca/files/2014/07/pad-thai-Banner-1020-x-400-588x230.jpg", 154, 150, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.testCreate((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (Class<?>) testingData[i][4]);
			this.testEdit((String) testingData[i][0], (int) testingData[i][3], (String) testingData[i][1], (Integer) testingData[i][2], (Class<?>) testingData[i][4]);
		}
	}

	// Eliminación de un banner:
	@Test
	public void driverDelete() {
		final Object testingData[][] = {
			{
				"manager1", 153, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void testCreate(final String username, final String picture, final int campaignId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Banner banner;
			Campaign campaign;

			campaign = this.campaignService.findOne(campaignId);
			Assert.notNull(campaign);

			banner = this.bannerService.create();

			banner.setPicture(picture);
			banner.setCampaign(this.campaignService.findOne(campaignId));
			banner = this.bannerService.save(banner);

			Assert.notNull(banner);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testEdit(final String username, final int bannerId, final String picture, final int campaignId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Campaign campaign;
			Banner banner;

			campaign = this.campaignService.findOne(campaignId);
			Assert.notNull(campaign);
			banner = this.bannerService.findOne(bannerId);
			banner.setPicture(picture);
			banner.setCampaign(campaign);

			banner = this.bannerService.save(banner);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testDelete(final String username, final int bannerId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			Banner banner;

			banner = this.bannerService.findOne(bannerId);

			this.bannerService.delete(banner);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}
}

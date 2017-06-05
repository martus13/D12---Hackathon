
package services;

import java.util.ArrayList;
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
import domain.Offer;
import domain.Offertable;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OfferServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private OfferService		offerService;

	@Autowired
	private OffertableService	offertableService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			{	// Bien
				"manager1", null, 133, 134
			}, {//usuario mal
				"m", IllegalArgumentException.class, 133, 134
			}, {//offertables incorrectos
				"manager2", IllegalArgumentException.class, 1330, 134
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreate((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3]);
	}

	@Test
	public void driverEdit() {
		Calendar correctCalendar1;
		Calendar correctCalendar2;
		Calendar wrongCalendar;

		correctCalendar1 = Calendar.getInstance();
		correctCalendar1.set(2017, 5, 24, 23, 58, 00);
		correctCalendar2 = Calendar.getInstance();
		correctCalendar2.set(2017, 5, 24, 23, 59, 00);

		wrongCalendar = Calendar.getInstance();
		wrongCalendar.set(2017, 5, 17, 23, 58, 00);

		final Object testingData[][] = {
			{	// Bien
				"manager1", null, 133, 134, correctCalendar1.getTime(), correctCalendar2.getTime()
			}, {
				"manager1", IllegalArgumentException.class, 133, 134, correctCalendar1.getTime(), wrongCalendar.getTime()
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSave((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Date) testingData[i][4], (Date) testingData[i][5]);
	}

	protected void testCreate(final String username, final Class<?> expected, final int offertable1, final int offertable2) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Offer offer;
			Collection<Offertable> offertables;

			offertables = new ArrayList<Offertable>();
			offertables.add(this.offertableService.findOne(offertable1));
			Assert.notNull(this.offertableService.findOne(offertable1));
			offertables.add(this.offertableService.findOne(offertable2));
			Assert.notNull(this.offertableService.findOne(offertable2));

			offer = this.offerService.create();
			offer.setOffertables(offertables);
			offer = this.offerService.save(offer);

			Assert.notNull(offer);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testSave(final String username, final Class<?> expected, final int offertable1, final int offertable2, final Date startMoment, final Date endMoment) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Offer offer;
			Collection<Offertable> offertables;

			offertables = new ArrayList<Offertable>();
			offertables.add(this.offertableService.findOne(offertable1));
			Assert.notNull(this.offertableService.findOne(offertable1));
			offertables.add(this.offertableService.findOne(offertable2));
			Assert.notNull(this.offertableService.findOne(offertable2));

			offer = this.offerService.create();
			offer.setOffertables(offertables);
			offer.setEndMoment(endMoment);
			offer.setStartMoment(startMoment);

			offer = this.offerService.save(offer);

			Assert.notNull(offer);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}

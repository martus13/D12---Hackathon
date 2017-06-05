
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
import domain.Airline;
import domain.Campaign;
import domain.MonthlyBill;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MonthlyBillServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private MonthlyBillService	monthlyBillService;

	@Autowired
	private AirlineService		airlineService;

	@Autowired
	private CampaignService		campaignService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driverCompute() {
		final Object testingData[][] = {
			{	// Bien
				"admin", null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCompute((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverPay() {
		final Object testingData[][] = {
			{
				"manager1", null, 161
			}, {// Cuota ya pagada
				"manager1", IllegalArgumentException.class, 160
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testPay((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2]);
	}

	@Test
	public void driverCreate() {
		Calendar correctCalendar;
		Calendar correctCalendar2;

		correctCalendar = Calendar.getInstance();
		correctCalendar.set(2012, 9, 31, 22, 22, 22);

		correctCalendar2 = Calendar.getInstance();
		correctCalendar2.set(2015, 9, 31, 22, 22, 22);
		final Object testingData[][] = {
			{
				"admin", null, 117, 148, correctCalendar.getTime(), "descripción", correctCalendar2.getTime(), 157.06
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreate((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Date) testingData[i][4], (String) testingData[i][5], (Date) testingData[i][6], (Double) testingData[i][7]);
	}

	protected void testCompute(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			this.monthlyBillService.computeMonthlyBills();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testPay(final String username, final Class<?> expected, final int monthlyBillId) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			MonthlyBill monthlyBill;

			monthlyBill = this.monthlyBillService.findOne(monthlyBillId);
			Assert.isNull(monthlyBill.getPaidMoment());
			this.monthlyBillService.pay(monthlyBill);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	//"admin", null,117,148,correctCalendar.getTime(),"descripción",Calendar.getInstance().getTime(),157.06
	protected void testCreate(final String username, final Class<?> expected, final int airlineId, final int campaignId, final Date creationMoment, final String description, final Date paidMoment, final Double totalFee) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			MonthlyBill monthlyBill;
			final Collection<Campaign> campaigns;
			Airline airline;
			Campaign campaign;

			airline = this.airlineService.findOne(airlineId);
			campaign = this.campaignService.findOne(campaignId);
			campaigns = new ArrayList<Campaign>();
			campaigns.add(campaign);

			monthlyBill = this.monthlyBillService.create();
			monthlyBill.setAirline(airline);
			monthlyBill.setCampaigns(campaigns);
			monthlyBill.setCreationMoment(creationMoment);
			monthlyBill.setDescription(description);
			monthlyBill.setPaidMoment(paidMoment);
			monthlyBill.setTotalFee(totalFee);
			monthlyBill = this.monthlyBillService.save(monthlyBill);

			Assert.notNull(monthlyBill);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	//	protected void testSave(final String username, final Class<?> expected, final int offertable1, final int offertable2, final Date startMoment, final Date endMoment) {
	//		Class<?> caught;
	//
	//		caught = null;
	//		try {
	//			this.authenticate(username);
	//			Offer offer;
	//			Collection<Offertable> offertables;
	//
	//			offertables = new ArrayList<Offertable>();
	//			offertables.add(this.offertableService.findOne(offertable1));
	//			Assert.notNull(this.offertableService.findOne(offertable1));
	//			offertables.add(this.offertableService.findOne(offertable2));
	//			Assert.notNull(this.offertableService.findOne(offertable2));
	//
	//			offer = this.offerService.create();
	//			offer.setOffertables(offertables);
	//			offer.setEndMoment(endMoment);
	//			offer.setStartMoment(startMoment);
	//
	//			offer = this.offerService.save(offer);
	//
	//			Assert.notNull(offer);
	//
	//			this.unauthenticate();
	//
	//		} catch (final Throwable oops) {
	//			caught = oops.getClass();
	//		}
	//
	//		this.checkExceptions(expected, caught);
	//
	//	}

}

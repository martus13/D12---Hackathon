
package services;

import java.util.ArrayList;
import java.util.Collection;

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

	//	// Eliminación de una campaña:
	//	@Test
	//	public void driverDelete() {
	//		final Object testingData[][] = {
	//			{//Bien
	//				"manager2", 151, null
	//			}, {//no se puede borrar porque tiene facturas sin pagar
	//				"manager1", 149, IllegalArgumentException.class
	//			}
	//		};
	//
	//		for (int i = 0; i < testingData.length; i++)
	//			this.testDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	//	}
	//
	//	//Finds
	//	@Test
	//	public void driverFind() {
	//		final Object testingData[][] = {
	//			{	// Bien
	//				"manager1", null, 118
	//			}, {//usuario mal
	//				"m", IllegalArgumentException.class, 118
	//			}, {
	//				"manager2", null, 119
	//			}
	//		};
	//
	//		for (int i = 0; i < testingData.length; i++) {
	//			this.testFindActiveByAirlineId((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2]);
	//			this.testFindByAirlineId((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2]);
	//			this.testFindNotDeletedByAirlineId((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2]);
	//		}
	//	}
	//
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

	//	public Collection<Offer> findByAirlineId(final int airlineId) {
	//		Collection<Offer> result;
	//
	//		result = this.offerRepository.findByAirlineId(airlineId);
	//
	//		return result;
	//	}
	//
	//	public Offer findByDateAndOffertableId(final int offertableId, final Date date) {
	//		Assert.isTrue(offertableId != 0);
	//		Assert.notNull(date);
	//
	//		Offer result;
	//
	//		result = this.offerRepository.findByDateAndOffertableId(offertableId, date);
	//
	//		return result;
	//	}

	//	public Collection<Offer> findOverlappingByOffer(final Offer offer) {
	//		Assert.notNull(offer);
	//
	//		Collection<Offer> result;
	//
	//		result = this.offerRepository.findOverlappingByOffer(offer.getOffertables(), offer.getStartMoment(), offer.getEndMoment(), offer.getId());
	//
	//		return result;
	//	}
	//	public Collection<Offer> findByDateAndOffertables(final Collection<Offertable> offertables, final Date date) {
	//		Assert.notNull(offertables);
	//		Assert.notNull(date);
	//
	//		Collection<Offer> result;
	//
	//		result = new ArrayList<Offer>();
	//
	//		for (final Offertable o : offertables) {
	//			Offer offer;
	//
	//			offer = this.offerRepository.findByDateAndOffertableId(o.getId(), date);
	//
	//			if (o instanceof Flight && offer == null)
	//				offer = this.offerRepository.findByDateAndOffertableId(((Flight) o).getAirline().getId(), date);
	//
	//			if (offer != null)
	//				result.add(offer);
	//
	//		}
	//
	//		return result;
	//	}

}

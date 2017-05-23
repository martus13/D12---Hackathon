
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OfferRepository;
import domain.Flight;
import domain.Offer;
import domain.Offertable;

@Service
@Transactional
public class OfferService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private OfferRepository	offerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public OfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Offer findOne(final int offerId) {
		Assert.isTrue(offerId != 0);

		Offer result;

		result = this.offerRepository.findOne(offerId);

		return result;
	}

	public Collection<Offer> findAll() {
		Collection<Offer> result;

		result = this.offerRepository.findAll();

		return result;
	}

	public Offer create() {
		Offer result;

		result = new Offer();

		result.setDiscount(0.0);

		return result;
	}

	public Offer save(Offer offer) {
		Assert.notNull(offer);

		offer = this.offerRepository.save(offer);

		return offer;
	}

	public void delete(final Offer offer) {
		Assert.notNull(offer);

		this.offerRepository.delete(offer);
	}

	// Other business methods -------------------------------------------------

	public Offer findByDateAndOffertableId(final int offertableId, final Date date) {
		Assert.isTrue(offertableId != 0);
		Assert.notNull(date);

		Offer result;

		result = this.offerRepository.findByDateAndOffertableId(offertableId, date);

		return result;
	}

	public Collection<Offer> findByDateAndOffertables(final Collection<Offertable> offertables, final Date date) {
		Assert.notNull(offertables);
		Assert.notNull(date);

		Collection<Offer> result;

		result = new ArrayList<Offer>();

		for (final Offertable o : offertables) {
			Offer offer;

			offer = this.offerRepository.findByDateAndOffertableId(o.getId(), date);

			if (o instanceof Flight && offer == null)
				offer = this.offerRepository.findByDateAndOffertableId(((Flight) o).getAirline().getId(), date);

			if (offer != null)
				result.add(offer);

		}

		return result;
	}
}

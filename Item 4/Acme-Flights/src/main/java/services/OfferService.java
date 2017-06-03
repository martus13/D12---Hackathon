
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
		Collection<Offertable> offertables;

		result = new Offer();
		offertables = new HashSet<Offertable>();

		result.setDiscount(0.0);
		result.setOffertables(offertables);

		return result;
	}

	public Offer save(Offer offer) {
		Assert.notNull(offer);
		Collection<Offer> offers;

		offers = this.findOverlappingByOffer(offer);
		Assert.isNull(offers);

		offer = this.offerRepository.save(offer);

		return offer;
	}

	public void delete(final Offer offer) {
		Assert.notNull(offer);

		this.offerRepository.delete(offer);
	}

	// Other business methods -------------------------------------------------

	public Collection<Offer> findByAirlineId(final int airlineId) {
		Collection<Offer> result;

		result = this.offerRepository.findByAirlineId(airlineId);

		return result;
	}

	public Offer findByDateAndOffertableId(final int offertableId, final Date date) {
		Assert.isTrue(offertableId != 0);
		Assert.notNull(date);

		Offer result;

		result = this.offerRepository.findByDateAndOffertableId(offertableId, date);

		return result;
	}

	public Collection<Offer> findOverlappingByOffer(final Offer offer) {
		Assert.notNull(offer);

		Collection<Offer> result;

		result = this.offerRepository.findOverlappingByOffer(offer.getOffertables(), offer.getStartMoment(), offer.getEndMoment());

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


package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OfferRepository;
import domain.Offer;

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

}

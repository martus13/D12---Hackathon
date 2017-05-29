
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.OffertableRepository;
import domain.Offertable;

@Service
@Transactional
public class OffertableService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private OffertableRepository	offertableRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public OffertableService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Offertable findOne(final int offertableId) {
		return this.offertableRepository.findOne(offertableId);
	}

	public Collection<Offertable> findAll() {

		return this.offertableRepository.findAll();
	}

	// Other business methods -------------------------------------------------

}

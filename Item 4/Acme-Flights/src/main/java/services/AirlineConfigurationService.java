
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AirlineConfigurationRepository;
import domain.AirlineConfiguration;

@Service
@Transactional
public class AirlineConfigurationService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AirlineConfigurationRepository	airlineConfigurationRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public AirlineConfigurationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	// Other business methods -------------------------------------------------

	public AirlineConfiguration findByAirlineId(final int airlineId) {
		AirlineConfiguration result;

		result = this.airlineConfigurationRepository.findByAirlineId(airlineId);

		return result;
	}

}

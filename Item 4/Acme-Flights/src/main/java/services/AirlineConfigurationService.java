
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AirlineConfigurationRepository;
import domain.Airline;
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

	public AirlineConfiguration findOne(final int airlineConfigId) {

		return this.airlineConfigurationRepository.findOne(airlineConfigId);
	}

	public Collection<AirlineConfiguration> findAll() {
		return this.airlineConfigurationRepository.findAll();
	}

	public AirlineConfiguration create(final Airline airline) {

		AirlineConfiguration result;

		result = new AirlineConfiguration();

		//		result.setMaxCancellationDays(1);
		//		result.setMaxChildrenAge(1);
		//		result.setChildrenDiscount(0.0);
		//		result.setMaxBagWeight(0.0);
		//		result.setOverweightBagPrice(0.0);
		result.setAirline(airline);

		return result;
	}

	public AirlineConfiguration save(final AirlineConfiguration airlineConfiguration) {
		Assert.notNull(airlineConfiguration);

		this.airlineConfigurationRepository.save(airlineConfiguration);

		return airlineConfiguration;

	}

	public void delete(final AirlineConfiguration airlineConfiguration) {
		Assert.notNull(airlineConfiguration);

		this.airlineConfigurationRepository.delete(airlineConfiguration);
	}

	// Other business methods -------------------------------------------------

	public AirlineConfiguration findByAirlineId(final int airlineId) {
		AirlineConfiguration result;

		result = this.airlineConfigurationRepository.findByAirlineId(airlineId);

		return result;
	}
	
	public AirlineConfiguration findByManager(int managerId){
		
		return this.airlineConfigurationRepository.findByManager(managerId);
	}

}

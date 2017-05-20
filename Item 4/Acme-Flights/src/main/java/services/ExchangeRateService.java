
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExchangeRateRepository;
import domain.Administrator;
import domain.ExchangeRate;

@Service
@Transactional
public class ExchangeRateService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ExchangeRateRepository	exchangeRateRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	AdministratorService			administratorService;


	// Constructors -----------------------------------------------------------
	public ExchangeRateService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ExchangeRate findOne(final int exchangeRateId) {
		Assert.isTrue(exchangeRateId != 0);
		ExchangeRate result;

		result = this.exchangeRateRepository.findOne(exchangeRateId);

		return result;
	}

	public Collection<ExchangeRate> findAll() {
		Collection<ExchangeRate> result;

		result = this.exchangeRateRepository.findAll();

		return result;
	}

	public ExchangeRate create() {
		ExchangeRate result;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		result = new ExchangeRate();

		return result;
	}

	public ExchangeRate save(ExchangeRate exchangeRate) {
		Assert.notNull(exchangeRate);
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		exchangeRate = this.exchangeRateRepository.save(exchangeRate);

		return exchangeRate;
	}

	public void delete(final ExchangeRate exchangeRate) {
		Assert.notNull(exchangeRate);
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		this.exchangeRateRepository.delete(exchangeRate);
	}

	// Other business methods -------------------------------------------------

}

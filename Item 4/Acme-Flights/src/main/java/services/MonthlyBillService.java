
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MonthlyBillRepository;
import domain.MonthlyBill;

@Service
@Transactional
public class MonthlyBillService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private MonthlyBillRepository	monthlyBillRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public MonthlyBillService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public MonthlyBill findOne(final int monthlyBillId) {
		Assert.isTrue(monthlyBillId != 0);

		MonthlyBill result;

		result = this.monthlyBillRepository.findOne(monthlyBillId);

		return result;
	}

	public Collection<MonthlyBill> findAll() {
		Collection<MonthlyBill> result;

		result = this.monthlyBillRepository.findAll();

		return result;
	}

	public MonthlyBill create() {
		MonthlyBill result;

		result = new MonthlyBill();

		result.setTotalFee(0.0);

		return result;
	}

	public MonthlyBill save(MonthlyBill monthlyBill) {
		Assert.notNull(monthlyBill);

		monthlyBill = this.monthlyBillRepository.save(monthlyBill);

		return monthlyBill;
	}

	public void delete(final MonthlyBill monthlyBill) {
		Assert.notNull(monthlyBill);

		this.monthlyBillRepository.delete(monthlyBill);
	}

	// Other business methods -------------------------------------------------

	public Collection<MonthlyBill> findByCampaignId(final int campaignId) {
		Collection<MonthlyBill> res;

		res = this.monthlyBillRepository.findByCampaignId(campaignId);

		return res;
	}

	public Collection<MonthlyBill> findByAirlineId(final int airlineId) {
		Collection<MonthlyBill> res;

		res = this.monthlyBillRepository.findByAirlineId(airlineId);

		return res;
	}
}

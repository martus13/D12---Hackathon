
package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MonthlyBillRepository;
import domain.Administrator;
import domain.Airline;
import domain.Campaign;
import domain.Manager;
import domain.MonthlyBill;

@Service
@Transactional
public class MonthlyBillService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private MonthlyBillRepository	monthlyBillRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ManagerService			managerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private CampaignService			campaignService;

	@Autowired
	private AirlineService			airlineService;


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

	public MonthlyBill pay(MonthlyBill monthlyBill) {
		Assert.notNull(monthlyBill);

		Manager manager;
		Calendar calendar;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		Assert.isTrue(monthlyBill.getAirline().equals(manager.getAirline()));

		calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, -10);

		monthlyBill.setPaidMoment(calendar.getTime());

		monthlyBill = this.monthlyBillRepository.save(monthlyBill);

		return monthlyBill;
	}

	public void computeMonthlyBills() {
		Collection<Airline> airlines;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		airlines = this.airlineService.findAll();

		for (final Airline a : airlines) {
			MonthlyBill monthlyBill;
			Campaign campaign;

			campaign = this.campaignService.findActiveByAirlineId(a.getId());

			if (campaign != null) {
				monthlyBill = this.create();
				monthlyBill.setCampaign(campaign);

				monthlyBill = this.save(monthlyBill);
			}

		}

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

	public Collection<MonthlyBill> findUnpaidMonthlyBillsByCampaignId(final int campaignId) {
		Collection<MonthlyBill> campaigns;

		campaigns = this.monthlyBillRepository.findUnpaidMonthlyBillsByCampaignId(campaignId);

		return campaigns;
	}
}

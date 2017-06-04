
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MonthlyBillRepository;
import domain.Administrator;
import domain.Airline;
import domain.Banner;
import domain.Campaign;
import domain.Configuration;
import domain.Flight;
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

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private BannerService			bannerService;

	@Autowired
	private FlightService			flightService;


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
		Calendar calendar;
		Collection<Campaign> campaigns;

		result = new MonthlyBill();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -10);
		campaigns = new ArrayList<Campaign>();

		result.setTotalFee(0.0);
		result.setCreationMoment(calendar.getTime());
		result.setCampaigns(campaigns);

		return result;
	}

	public MonthlyBill save(MonthlyBill monthlyBill) {
		Assert.notNull(monthlyBill);
		Configuration configuration;
		Integer counter;
		Double flightsCost;
		Double campaignCost;
		MonthlyBill lastMonthlyBill;
		Collection<Flight> flights;
		String description;
		Collection<Banner> banners;
		Collection<Campaign> campaigns;

		configuration = this.configurationService.findConfiguration();
		lastMonthlyBill = this.findLastMonthlyBill(monthlyBill.getAirline().getId());
		counter = 0;
		flightsCost = 0.0;
		campaignCost = 0.0;
		description = "";
		campaigns = monthlyBill.getCampaigns();

		for (final Campaign c : campaigns) {
			banners = this.bannerService.findByCampaignId(c.getId());

			if (!banners.isEmpty()) {
				for (final Banner b : banners) {
					counter += b.getNumDisplayed();

					b.setNumDisplayed(0); // Reiniciamos el numero de veces que se ha mostrado un banner
					this.bannerService.save(b);
				}
				campaignCost += configuration.getCampaignFee() * counter;
			}
		}

		if (lastMonthlyBill != null)
			flights = this.flightService.findByAirlineIdPeriod(monthlyBill.getAirline().getId(), lastMonthlyBill.getCreationMoment());
		else
			flights = this.flightService.findAll();

		description += "Se han ofertado: " + flights.size() + " vuelos.";
		flightsCost = configuration.getFee() * flights.size();

		monthlyBill.setDescription(description);
		monthlyBill.setTotalFee(campaignCost + flightsCost);
		if (monthlyBill.getId() == 0) {
			Calendar calendar;

			calendar = Calendar.getInstance();
			calendar.add(Calendar.MILLISECOND, -10);

			monthlyBill.setCreationMoment(calendar.getTime());
		}

		if (monthlyBill.getTotalFee() > 0)
			monthlyBill = this.monthlyBillRepository.save(monthlyBill);
		else
			monthlyBill = null;

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
			MonthlyBill lastMonthlyBill;
			long diferenciaDias;
			Calendar calendar;
			Calendar calendarIniDate;

			lastMonthlyBill = this.findLastMonthlyBill(a.getId());

			calendar = Calendar.getInstance();
			calendar.add(Calendar.MILLISECOND, -10);

			calendarIniDate = Calendar.getInstance();

			if (lastMonthlyBill != null)
				calendarIniDate.setTime(lastMonthlyBill.getCreationMoment());
			else
				calendarIniDate.setTime(this.campaignService.findFirstStartDateByAirline(a.getId()));

			diferenciaDias = calendar.getTimeInMillis() - calendarIniDate.getTimeInMillis();
			diferenciaDias = diferenciaDias / (24 * 60 * 60 * 1000);

			if (diferenciaDias >= 30) {
				final Collection<Campaign> campaigns;

				campaigns = this.campaignService.findByAirlineIdPeriod(a.getId(), calendarIniDate.getTime(), calendar.getTime());

				monthlyBill = this.create();
				monthlyBill.setCampaigns(campaigns);
				monthlyBill.setAirline(a);

				this.save(monthlyBill);
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

	public MonthlyBill findLastMonthlyBill(final int airlineId) {
		MonthlyBill campaigns;

		campaigns = this.monthlyBillRepository.findLastMonthlyBill(airlineId);

		return campaigns;
	}
}

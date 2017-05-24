
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Banner;
import domain.Campaign;
import domain.Manager;

@Service
@Transactional
public class BannerService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private BannerRepository	bannerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CampaignService		campaignService;

	@Autowired
	private ManagerService		managerService;


	// Constructors -----------------------------------------------------------
	public BannerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Banner findOne(final int bannerId) {
		Assert.isTrue(bannerId != 0);

		Banner result;

		result = this.bannerRepository.findOne(bannerId);

		return result;
	}

	public Collection<Banner> findAll() {
		Collection<Banner> result;

		result = this.bannerRepository.findAll();

		return result;
	}

	public Banner create() {
		Banner result;

		result = new Banner();

		result.setNumDisplayed(0);

		return result;
	}

	public Banner save(Banner banner) {
		Assert.notNull(banner);

		banner = this.bannerRepository.save(banner);

		return banner;
	}

	public void delete(final Banner banner) {
		Assert.notNull(banner);

		this.bannerRepository.delete(banner);
	}

	// Other business methods -------------------------------------------------

	public Collection<Campaign> findCampaignsByAirlineAndManager() {
		Collection<Campaign> campaigns;
		final Collection<Campaign> aux = new ArrayList<Campaign>();
		Manager manager;

		campaigns = this.campaignService.findAll();
		manager = this.managerService.findByPrincipal();
		manager.getAirline();

		for (final Campaign c : campaigns)
			if (c.getAirline().equals(manager.getAirline()))
				aux.add(c);
		return aux;
	}

	public Collection<Banner> findByAirlineId(final int airlineId) {
		Collection<Banner> res;

		res = this.bannerRepository.findByAirlineId(airlineId);

		return res;
	}
}

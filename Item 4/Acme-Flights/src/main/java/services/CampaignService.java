
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CampaignRepository;
import domain.Banner;
import domain.Campaign;
import domain.Manager;

@Service
@Transactional
public class CampaignService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CampaignRepository	campaignRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private BannerService		bannerService;

	@Autowired
	private ManagerService		managerService;


	// Constructors -----------------------------------------------------------
	public CampaignService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Campaign findOne(final int campaignId) {
		Assert.isTrue(campaignId != 0);

		Campaign result;

		result = this.campaignRepository.findOne(campaignId);

		return result;
	}

	public Collection<Campaign> findAll() {
		Collection<Campaign> result;

		result = this.campaignRepository.findAll();

		return result;
	}

	public Campaign create() {
		Campaign result;
		Manager manager;
		result = new Campaign();

		manager = this.managerService.findByPrincipal();

		result.setAirline(manager.getAirline());
		result.setMaxDisplayed(0);

		return result;
	}

	public Campaign save(Campaign campaign) {
		Assert.notNull(campaign);

		campaign = this.campaignRepository.save(campaign);

		return campaign;
	}

	public void delete(final Campaign campaign) {
		Assert.notNull(campaign);
		//Assert.isTrue(this.findUnpaidMonthlyBillsByCampaignId(campaign.getId()).size() == 0);
		Collection<Banner> banners;

		banners = this.bannerService.findByCampaignId(campaign.getId());

		for (final Banner b : banners)
			this.bannerService.delete(b);

		campaign.setDeleted(true);

		this.campaignRepository.save(campaign);
	}

	// Other business methods -------------------------------------------------

	public Collection<Campaign> findActiveCampaigns() {
		Collection<Campaign> campaigns;

		campaigns = this.campaignRepository.findActiveCampaigns();

		return campaigns;
	}

	public Collection<Campaign> findByAirlineId(final int airlineId) {
		Collection<Campaign> campaigns;

		campaigns = this.campaignRepository.findByAirlineId(airlineId);

		return campaigns;
	}

	public Collection<Campaign> findNotDeletedByAirlineId(final int airlineId) {
		Collection<Campaign> campaigns;

		campaigns = this.campaignRepository.findNotDeletedByAirlineId(airlineId);

		return campaigns;
	}

	public Collection<Campaign> findUnpaidMonthlyBillsByCampaignId(final int campaignId) {
		Collection<Campaign> campaigns;

		campaigns = this.campaignRepository.findUnpaidMonthlyBillsByCampaignId(campaignId);

		return campaigns;
	}
}

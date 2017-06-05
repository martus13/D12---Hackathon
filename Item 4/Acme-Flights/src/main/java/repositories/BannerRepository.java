
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

	@Query("select b from Banner b where b.campaign.airline.id=?1")
	Collection<Banner> findByAirlineId(int airlineId);

	@Query("select b from Banner b where b.campaign.id=?1")
	Collection<Banner> findByCampaignId(int campaignId);

	@Query("select b from Banner b where b.numDisplayed<b.campaign.maxDisplayed and current_timestamp between b.campaign.startDate and b.campaign.endDate")
	Collection<Banner> findAllCanBeDisplayed();

}

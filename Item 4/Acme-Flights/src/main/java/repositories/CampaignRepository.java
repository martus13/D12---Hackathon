
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;
import domain.MonthlyBill;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	@Query("select c from Campaign c where current_timestamp between c.startDate and c.endDate")
	Collection<Campaign> findActiveCampaigns();

	@Query("select c from Campaign c where c.airline.id=?1 and current_timestamp between c.startDate and c.endDate")
	Collection<Campaign> findActiveByAirlineId(int airlineId);

	@Query("select c from Campaign c where c.airline.id=?1")
	Collection<Campaign> findByAirlineId(int airlineId);

	@Query("select c from Campaign c where c.airline.id=?1 and c.deleted=false")
	Collection<Campaign> findNotDeletedByAirlineId(int airlineId);

	@Query("select m from MonthlyBill m where m.paidMoment=null and m.campaign.id=?1")
	Collection<MonthlyBill> findUnpaidMonthlyBillsByCampaignId(int campaignId);
}

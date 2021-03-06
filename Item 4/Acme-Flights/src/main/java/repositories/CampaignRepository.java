
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	@Query("select c from Campaign c where current_timestamp between c.startDate and c.endDate")
	Collection<Campaign> findActiveCampaigns();

	@Query("select c from Campaign c where c.airline.id=?1 and current_timestamp between c.startDate and c.endDate")
	Campaign findActiveByAirlineId(int airlineId);

	@Query("select c from Campaign c where c.airline.id=?1")
	Collection<Campaign> findByAirlineId(int airlineId);

	@Query("select c from Campaign c where c.airline.id=?1 and not (?3<c.startDate or ?2>c.endDate)")
	Collection<Campaign> findByAirlineIdPeriod(int airlineId, Date iniDate, Date finDate);

	@Query("select min(c.startDate) from Campaign c where c.airline.id=?1")
	Date findFirstStartDateByAirline(int airlineId);

	@Query("select c from Campaign c where c.airline.id=?1 and c.deleted=false")
	Collection<Campaign> findNotDeletedByAirlineId(int airlineId);

	@Query("select c from Campaign c where c.id!=?4 and c.airline.id=?1 and not (?3<c.startDate or ?2>c.endDate)")
	Campaign findOverlappingByCampaign(int airlineId, Date iniDate, Date finDate, int campaignId);
}

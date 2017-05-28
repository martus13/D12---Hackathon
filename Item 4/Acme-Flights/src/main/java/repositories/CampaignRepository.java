
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	@Query("select c from Campaign c where current_timestamp between c.startDate and c.endDate")
	Collection<Campaign> findActiveCampaigns();

	@Query("select c from Campaign c where c.airline.id=?1")
	Collection<Campaign> findByAirlineId(int airlineId);

}

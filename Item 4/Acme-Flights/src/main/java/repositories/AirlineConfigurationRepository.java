
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AirlineConfiguration;

@Repository
public interface AirlineConfigurationRepository extends JpaRepository<AirlineConfiguration, Integer> {

	@Query("select a from AirlineConfiguration a where a.airline.id=?1")
	AirlineConfiguration findByAirlineId(int airlineId);
}

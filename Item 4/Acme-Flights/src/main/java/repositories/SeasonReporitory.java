
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Season;

@Repository
public interface SeasonReporitory extends JpaRepository<Season, Integer> {

	@Query("select s from Season s where s.airline.id=?1")
	Collection<Season> findActiveByAirlineId(int airlineId);
}

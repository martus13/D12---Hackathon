
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Season;

@Repository
public interface SeasonReporitory extends JpaRepository<Season, Integer> {

	@Query("select s from Season s where s.airline.id=?1 and inactive=false")
	Collection<Season> findActiveByAirlineId(int airlineId);

	@Query("select s from Season s where s.airline.id=?1 and inactive=false and ?2 >= DATE_FORMAT(CONCAT(YEAR(?2),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and ?2<= DATE_FORMAT(CONCAT(YEAR(?2),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')")
	Season findActiveByDateAndAirlineId(int airlineId, Date departureDate);
}


package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PointsCard;

@Repository
public interface PointsCardRepository extends JpaRepository<PointsCard, Integer> {

	@Query("select p from PointsCard p where p.user.id=?1")
	Collection<PointsCard> findByUserId(int userId);

	@Query("select p from PointsCard p where p.user.id=?1 and p.airline.id=?2")
	PointsCard findByUserAndAirlineId(int userId, int airlineId);
}

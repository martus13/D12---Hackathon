
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PointsCard;

@Repository
public interface PointsCardRepository extends JpaRepository<PointsCard, Integer> {

}

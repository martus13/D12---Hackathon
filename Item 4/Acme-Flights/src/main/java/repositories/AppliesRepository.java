
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Applies;

@Repository
public interface AppliesRepository extends JpaRepository<Applies, Integer> {

	@Query("select a from Applies a where a.book.id=?1")
	Collection<Applies> findByBookId(int bookId);

	@Query("select a from Applies a where a.pointsCard.id=?1")
	Collection<Applies> findByPointsCardId(int pointsCardId);

	@Query("select a from Applies a where a.book.id=?1 and a.pointsCard.id=?2 and a.flight.id=?3")
	Applies findByBookAndPointsCardId(int bookId, int pointsCardId, int flightId);
}

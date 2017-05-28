
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c where c.airline.id=?1")
	Collection<Comment> findByAirline(int airlineId);
	
	@Query("select c from Comment c where c.user.id=?1 and c.airline.id=?2")
	Collection<Comment> findByUserAndAirline(int userId, int airlineId);

	@Query("select avg(c.rating.airline) from Comment c where c.airline.id=?1")
	Double findRatingCommetsByAirlineId(int airlineId);

}


package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offertable;

@Repository
public interface OffertableRepository extends JpaRepository<Offertable, Integer> {

	@Query("select o.offertables from Offer o where o.id=?1")
	Collection<Offertable> findByOfferId(int offerId);
}


package repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	@Query("select o from Offer o where o.offertable.id=?1 and ?2 between o.startMoment and o.endMoment")
	Offer findByDateAndOffertableId(int offertableId, Date date);
}

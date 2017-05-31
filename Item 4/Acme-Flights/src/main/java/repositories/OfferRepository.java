
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	@Query("select o from Offer o join o.offertables offertable where offertable.id=?1 or offertable.id in (select f.id from Flight f where f.airline.id=?1)")
	Collection<Offer> findByAirlineId(int airlineId);

	@Query("select o from Offer o join o.offertables offert where offert.id=?1 and ?2 between o.startMoment and o.endMoment")
	Offer findByDateAndOffertableId(int offertableId, Date date);

}


package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("select distinct b from Book b join b.flights f where f.id=?1 and b.cancelationMoment is null")
	Collection<Book> findNotCancelledByFlightId(int flightId);

	@Query("select b from Book b where b.user.id=?1")
	Collection<Book> findByUser(int userId);

	@Query("select distinct b from Book b join b.flights f where f.airline.id=?1 and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', ?3, '/', ?2), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', ?5, '/', ?4), '%Y-%m-%d')")
	Collection<Book> findOfNotPassedFlightsBySeason(int airlineId, Integer startDay, Integer startMonth, Integer endDay, Integer endMonth);

	@Query("select b from Book b join b.flights f where b.user.id=?1 and ?2 between f.departureDate and f.arrivalDate")
	Book findOverlappingByUserAndDepartureDate(int userId, Date departureDate);

	@Query("select b from Book b where b not in (select i.book from Invoice i) and b.cancelationMoment =null and 1<=ALL(select DATEDIFF(current_timestamp, f.arrivalDate) from Book b1 join b1.flights f where b1=b)")
	Collection<Book> findNotCancelledWithoutInvoices();

	@Query("select distinct b from Book b join b.flights f where b.id=?1 and f.airline.id=?2")
	Book findOneWithAirline(int bookId, int airlineId);

	@Query("select distinct b from Book b join b.offers o where o.id=?1")
	Collection<Book> findByOfferId(int offerId);
}

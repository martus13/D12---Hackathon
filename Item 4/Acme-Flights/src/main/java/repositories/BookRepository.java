
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("select distinct b from Book b join b.flights f where f.airline.id=?1 and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', ?3, '/', ?2), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', ?5, '/', ?4), '%Y-%m-%d')")
	Collection<Book> findOfNotPassedFlightsBySeason(int airlineId, Integer startDay, Integer startMonth, Integer endDay, Integer endMonth);
}

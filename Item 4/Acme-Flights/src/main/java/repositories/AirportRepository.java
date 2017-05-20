
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

	// 2. Las aerolíneas con menos reservas
	
	@Query("select s.airline.name from Book b join b.seasons s where b.cancelationMoment is null group by s.airline having count (b) <= ALL (select count(b1) from Book b1 join b1.seasons s1 where b1.cancelationMoment is null group by s1.airline)")
	Collection<String> findAirlineLessBooks();
	
	// 3. Las ciudades más visitadas
	@Query("select f.destination.city, count(b) from Book b join b.flights f where b.cancelationMoment is null group by f.destination.city having count(b)>=ALL(select count(b1) from Book b1 join b1.flights f1 where b1.cancelationMoment is null group by f1.destination.city)")
	Collection<Object[]> findMostVisitedCities();
	
	
}

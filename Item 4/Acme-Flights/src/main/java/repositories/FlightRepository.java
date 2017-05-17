
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("select f from Flight f where f.cancelled=false")
	Collection<Flight> findNotCancelled();

	@Query("select f from Flight f where f.cancelled=false and f.airline.id=?1")
	Collection<Flight> findNotCancelledByAirline(int airlineId);

	// 6. El máximo, el mínimo y la media de vuelos por aeropuerto
	@Query("select max(a.outgoings.size), min(a.outgoings.size), avg(a.outgoings.size) from Airport a")
	Object[] findMaxMinAvgFlightsPerAirport();

	// 9. El porcentaje de vuelos ofertados por cada aerolínea en las temporadas altas.
	@Query("select a, 100*(select count(distinct f) from Flight f join f.airline.seasons s where s.type='increase' and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d') and f.airline=a)/(select count(f1) from Flight f1 where f1.airline=a) from Airline a")
	Collection<Object[]> findPercentFlightsPerAirline();
}


package repositories;

import java.util.Collection;
import java.util.Date;

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

	@Query("select distinct f from Book b join b.flights f where f.airline.id=?1 and f.cancelled=false and CURRENT_TIMESTAMP<=f.departureDate and count(b)>0")
	Collection<Flight> findNotCancelledNotPassedWithBooksByAirlineId(int airlineId);

	@Query("select f from Flight f where (f.departure.id=?1 or f.destination.id=?1) and f.cancelled=false and CURRENT_TIMESTAMP<=f.departureDate")
	Collection<Flight> findNotCancelledNotPassedByAirportId(int airportId);

	@Query("select f from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and YEAR(f.departureDate)=YEAR(?3) and MONTH(f.departureDate)=MONTH(?3) and DAY(f.departureDate)=DAY(?3) and f.availableEconomySeats>=?4")
	Collection<Flight> findNotCancelledEconomyByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber);

	@Query("select f from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and YEAR(f.departureDate)=YEAR(?3) and MONTH(f.departureDate)=MONTH(?3) and DAY(f.departureDate)=DAY(?3) and f.availableBusinessSeats>=?4")
	Collection<Flight> findNotCancelledBusinessByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber);

	// 6. El máximo, el mínimo y la media de vuelos por aeropuerto
	@Query("select max(a.outgoings.size), min(a.outgoings.size), avg(a.outgoings.size) from Airport a")
	Object[] findMaxMinAvgFlightsPerAirport();

	// 9. El porcentaje de vuelos ofertados por cada aerolínea en las temporadas altas.
	@Query("select a, 100*(select count(distinct f) from Flight f join f.airline.seasons s where s.type='increase' and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d') and f.airline=a)/(select count(f1) from Flight f1 where f1.airline=a) from Airline a")
	Collection<Object[]> findPercentFlightsPerAirline();
}

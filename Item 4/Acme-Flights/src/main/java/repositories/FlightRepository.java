
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

	@Query("select fdep from Flight fdep where fdep.cancelled=false and fdep.departure.id=?1 and fdep.destination.id=?2 and DATE_FORMAT(fdep.departureDate, '%Y-%m-%d')=?3 and fdep.availableEconomySeats>=?4")
	Collection<Flight[]> findNotCancelledEconomyByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber);

	@Query("select fdep, fdes from Flight fdep, Flight fdes where fdep.cancelled=false and fdes.cancelled=false and fdep.departure.id=?1 and fdep.destination.id=?2 and fdes.departure.id=?2 and fdes.destination.id=?1 and DATE_FORMAT(fdep.departureDate, '%Y-%m-%d')=?3 and DATE_FORMAT(fdes.departureDate, '%Y-%m-%d')=?4 and fdep.availableEconomySeats>=?5 and fdes.availableEconomySeats>=?5")
	Collection<Flight[]> findNotCancelledEconomyWithReturnByFinder(int departureId, int destinationId, Date departureDate, Date returnDate, Integer totalPassengersNumber);

	@Query("select fdep from Flight fdep where fdep.cancelled=false and fdep.departure.id=?1 and fdep.destination.id=?2 and DATE_FORMAT(fdep.departureDate, '%Y-%m-%d')=?3 and fdep.availableBusinessSeats>=?4")
	Collection<Flight[]> findNotCancelledBusinessByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber);

	@Query("select fdep, fdes from Flight fdep, Flight fdes where fdep.cancelled=false and fdes.cancelled=false and fdep.departure.id=?1 and fdep.destination.id=?2 and fdes.departure.id=?2 and fdes.destination.id=?1 and DATE_FORMAT(fdep.departureDate, '%Y-%m-%d')=?3 and DATE_FORMAT(fdes.departureDate, '%Y-%m-%d')=?4 and fdep.availableEconomySeats>=?5 and fdes.availableBusinessSeats>=?5")
	Collection<Flight[]> findNotCancelledBusinessWithReturnByFinder(int departureId, int destinationId, Date departureDate, Date returnDate, Integer totalPassengersNumber);

	// 6. El máximo, el mínimo y la media de vuelos por aeropuerto
	@Query("select max(a.outgoings.size), min(a.outgoings.size), avg(a.outgoings.size) from Airport a")
	Object[] findMaxMinAvgFlightsPerAirport();

	// 9. El porcentaje de vuelos ofertados por cada aerolínea en las temporadas altas.
	@Query("select a, 100*(select count(distinct f) from Flight f join f.airline.seasons s where s.type='increase' and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d') and f.airline=a)/(select count(f1) from Flight f1 where f1.airline=a) from Airline a")
	Collection<Object[]> findPercentFlightsPerAirline();
}

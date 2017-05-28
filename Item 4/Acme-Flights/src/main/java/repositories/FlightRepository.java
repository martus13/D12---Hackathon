
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

	@Query("select f from Flight f where f.cancelled=false and current_timestamp <= f.departureDate")
	Collection<Flight> findNotCancelledNotPassed();

	@Query("select f from Flight f where f.cancelled=false and f.airline.id=?1")
	Collection<Flight> findNotCancelledByAirline(int airlineId);

	@Query("select f from Flight f where f.airline.id=?1 and f.cancelled=false and current_timestamp <= f.departureDate")
	Collection<Flight> findNotCancelledNotPassedByAirlineId(int airlineId);

	@Query("select f from Flight f where (f.departure.id=?1 or f.destination.id=?1) and f.cancelled=false and CURRENT_TIMESTAMP<=f.departureDate")
	Collection<Flight> findNotCancelledNotPassedByAirportId(int airportId);

	//@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment), (select coalesce(count(distinct b), 0) from Book b join b.flights f1 where b.user.id=?5 and f.departureDate between f1.departureDate and f1.arrivalDate) from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and YEAR(f.departureDate)=YEAR(?3) and MONTH(f.departureDate)=MONTH(?3) and DAY(f.departureDate)=DAY(?3) and f.availableEconomySeats>=?4")
	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment), (select coalesce(count(distinct b), 0) from Book b join b.flights f1 where b.user.id=?5 and f.departureDate between f1.departureDate and f1.arrivalDate) from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and f.availableEconomySeats>=?4 and 5>=DATEDIFF(?3, f.departureDate) and -5<=DATEDIFF(?3, f.departureDate)")
	Collection<Object[]> findEconomyFlightsOfferAndSeasonByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber, int userId);

	//@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment), (select coalesce(count(distinct b), 0) from Book b join b.flights f1 where b.user.id=?5 and f.departureDate between f1.departureDate and f1.arrivalDate) from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and YEAR(f.departureDate)=YEAR(?3) and MONTH(f.departureDate)=MONTH(?3) and DAY(f.departureDate)=DAY(?3) and f.availableBusinessSeats>=?4")
	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment), (select coalesce(count(distinct b), 0) from Book b join b.flights f1 where b.user.id=?5 and f.departureDate between f1.departureDate and f1.arrivalDate) from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and f.availableBusinessSeats>=?4 and 5>=DATEDIFF(?3, f.departureDate) and -5<=DATEDIFF(?3, f.departureDate)")
	Collection<Object[]> findBusinessFlightsOfferAndSeasonByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber, int userId);

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment) from Flight f where f.cancelled=false and f.id=?1 or f.id=?2")
	Collection<Object[]> findFlightsOfferAndSeasonByFlightsId(int flightDepartureId, int flightDestinationId);

	@Query("select f.businessSeats-(sum(b.passengersNumber)+sum(b.childrenNumber)) from Book b join b.flights f where f.id=?1 and b.isBusiness=true")
	Integer findAvailableBusinessSeatByFlightId(int flightId);

	@Query("select f.economySeats-(sum(b.passengersNumber)+sum(b.childrenNumber)) from Book b join b.flights f where f.id=?1 and b.isBusiness=false")
	Integer findAvailableEconomySeatByFlightId(int flightId);

	// 6. El m�ximo, el m�nimo y la media de vuelos por aeropuerto
	@Query("select max(a.outgoings.size), min(a.outgoings.size), avg(a.outgoings.size) from Airport a")
	Object[] findMaxMinAvgFlightsPerAirport();

	// 9. El porcentaje de vuelos ofertados por cada aerol�nea en las temporadas altas.
	@Query("select a, 100*(select count(distinct f) from Flight f join f.airline.seasons s where s.type='increase' and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d') and f.airline=a)/(select count(f1) from Flight f1 where f1.airline=a) from Airline a")
	Collection<Object[]> findPercentFlightsPerAirline();
}

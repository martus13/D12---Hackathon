
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

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment) from Flight f where f.cancelled=false and current_timestamp <= f.departureDate")
	Collection<Object[]> findNotCancelledNotPassedOfferAndSeason();

	@Query("select f from Flight f where f.cancelled=false and f.airline.id=?1")
	Collection<Flight> findNotCancelledByAirlineId(int airlineId);

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment) from Flight f where f.cancelled=false and f.airline.id=?1")
	Collection<Object[]> findNotCancelledByAirlineIdOfferAndSeason(int airlineId);

	@Query("select f from Flight f where f.airline.id=?1 and f.cancelled=false and current_timestamp <= f.departureDate")
	Collection<Flight> findNotCancelledNotPassedByAirlineId(int airlineId);

	@Query("select f from Flight f where (f.departure.id=?1 or f.destination.id=?1) and f.cancelled=false and CURRENT_TIMESTAMP<=f.departureDate")
	Collection<Flight> findNotCancelledNotPassedByAirportId(int airportId);

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment), (select coalesce(count(distinct b), 0) from Book b join b.flights f1 where b.user.id=?5 and f.departureDate between f1.departureDate and f1.arrivalDate) from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and f.availableEconomySeats>=?4 and 5>=DATEDIFF(?3, f.departureDate) and -5<=DATEDIFF(?3, f.departureDate) and f.departureDate>=current_timestamp")
	Collection<Object[]> findEconomyFlightsOfferAndSeasonByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber, int userId);

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment), (select coalesce(count(distinct b), 0) from Book b join b.flights f1 where b.user.id=?5 and f.departureDate between f1.departureDate and f1.arrivalDate) from Flight f where f.cancelled=false and f.departure.id=?1 and f.destination.id=?2 and f.availableBusinessSeats>=?4 and 5>=DATEDIFF(?3, f.departureDate) and -5<=DATEDIFF(?3, f.departureDate) and f.departureDate>=current_timestamp")
	Collection<Object[]> findBusinessFlightsOfferAndSeasonByFinder(int departureId, int destinationId, Date departureDate, Integer totalPassengersNumber, int userId);

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment) from Flight f where f.cancelled=false and f.id=?1 or f.id=?2")
	Collection<Object[]> findFlightsOfferAndSeasonByFlightsId(int flightDepartureId, int flightDestinationId);

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')), (select ov from Offer ov join ov.offertables ovo where ovo.id=f.id and current_date between ov.startMoment and ov.endMoment), (select oa from Offer oa join oa.offertables oao where oao.id=f.airline.id and current_date between oa.startMoment and oa.endMoment) from Flight f where f.cancelled=false and f.departureDate>=current_timestamp and (select coalesce(count(distinct b), 0) from Book b join b.flights f1 where b.cancelationMoment is null and f=f1)>=ALL(select coalesce(count(distinct b1), 0) from Book b1 join b1.flights f2 where b1.cancelationMoment is null and f2.departureDate>=current_timestamp group by f2)")
	Collection<Object[]> findFlightsOfferAndSeasonNotCancelledMostBook();

	@Query("select f, (select s from Season s where s.airline=f.airline and s.inactive=false and DATE_FORMAT(f.departureDate, '%Y-%m-%d') >= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and DATE_FORMAT(f.departureDate, '%Y-%m-%d')<= DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d')) from Flight f where f.cancelled=false and CURRENT_TIMESTAMP<=f.departureDate and f.id in (select offertable.id from Offer o join o.offertables offertable where o.id=?1)")
	Collection<Object[]> findNotCancelledNotPassedSeasonByOffer(int offerId);

	@Query("select f.businessSeats-(select coalesce(sum(b.passengersNumber)+sum(b.childrenNumber), 0) from Book b join b.flights f1 where f=f1 and b.isBusiness=true) from Flight f where f.id=?1")
	Integer findAvailableBusinessSeatByFlightId(int flightId);

	@Query("select f.economySeats-(select coalesce(sum(b.passengersNumber)+sum(b.childrenNumber), 0) from Book b join b.flights f1 where f=f1 and b.isBusiness=false) from Flight f where f.id=?1")
	Integer findAvailableEconomySeatByFlightId(int flightId);

	// 7. El máximo, el mínimo y la media de vuelos por aeropuerto de origen
	@Query("select max(a.outgoings.size), min(a.outgoings.size), avg(a.outgoings.size) from Airport a")
	Object[] findMaxMinAvgFlightsPerAirport();

	// 10. El porcentaje de vuelos ofertados por cada aerolínea en las temporadas altas.
	@Query("select a, 100*(select count(distinct f) from Flight f join f.airline.seasons s where s.type='increase' and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d') and f.airline=a)/(select count(f1) from Flight f1 where f1.airline=a) from Airline a")
	Collection<Object[]> findPercentFlightsPerAirlineHigh();

	// 11. El porcentaje de vuelos ofertados por cada aerolínea en las temporadas bajas.
	@Query("select a, 100*(select count(distinct f) from Flight f join f.airline.seasons s where s.type='discount' and f.departureDate>=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.startMonth, '/', s.startDay), '%Y-%m-%d') and f.departureDate<=DATE_FORMAT(CONCAT(YEAR(f.departureDate),'/', s.endMonth, '/', s.endDay), '%Y-%m-%d') and f.airline=a)/(select count(f1) from Flight f1 where f1.airline=a) from Airline a")
	Collection<Object[]> findPercentFlightsPerAirlineLow();

	@Query("select f from Book b join b.flights f where b.user.id=?1 and f.airline.id=?2")
	Collection<Flight> findByUserAndAirline(int userId, int airlineId);
}


package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {

	@Query("select a from Airline a where a.deleted=false")
	Collection<Airline> findNotDeleted();

	@Query("select m.airline from Manager m where m.id=?1")
	Airline findByManager(int managerId);

	@Query("select a from Airline a")
	Page<Airline> findAllPaged(Pageable pageRequest);

	@Query("select 0.1*count(a) from Airline a")
	Double find10percentAirlines();

	// 1. Las aeorlíneas con más reservas 
	@Query("select f.airline, count(b) from Book b join b.flights f where b.cancelationMoment is null group by f.airline having count(b)>=ALL(select count(b1) from Book b1 join b1.flights f1 where b1.cancelationMoment is null group by f1.airline)")
	Collection<Object[]> findAirlineMostBooks();

	// 2. Las aerolíneas con menos reservas
	@Query("select s.airline, count(b) from Book b join b.seasons s where b.cancelationMoment is null group by s.airline having count (b) <= ALL (select count(b1) from Book b1 join b1.seasons s1 where b1.cancelationMoment is null group by s1.airline)")
	Collection<Object[]> findAirlineLessBooks();

	// 5. Las aerolíneas con más vuelos
	@Query("select f.airline, count(f) from Flight f where f.cancelled='false' group by f.airline having count(f) >= ALL ( select count(f1) from Flight f1 where f1.cancelled='false' group by f1.airline)")
	Collection<Object[]> findAirlineMostFlights();

	// 6. Las aerolíneas con menos vuelos
	@Query("select f.airline, count(f) from Flight f where f.cancelled='false' group by f.airline having count(f) <= ALL ( select count(f1) from Flight f1 where f1.cancelled='false' group by f1.airline)")
	Collection<Object[]> findAirlineLessFlights();

	// 8. Un listado de las aerolíneas ordenadas por el número de facturas que tienen
	@Query("select a, (select count(m) from MonthlyBill m where m.airline=a) as numFacturas from Airline a order by numFacturas")
	Collection<Object[]> findAirlinesOrderByNumberOfBills();

	// 9. El porcentaje de facturas pagadas por cada aerolínea
	@Query("select mb.airline, 100.0*count(mb)/(select count(mb1) from MonthlyBill mb1 where mb1.airline=mb.airline)  from MonthlyBill mb where mb.paidMoment is not null group by mb.airline")
	Collection<Object[]> findPercentagePaidBills();

	// 12. Las aerolíneas que han realizado el mayor porcentaje de descuento en sus vuelos
	@Query("select o2, max(o.discount) from Offer o join o.offertables o2 where o2 in (select a from Airline a) group by o2")
	Collection<Object[]> findMostPercentageDiscount();

	// 13. Las aerolíneas que han realizado el menor porcentaje de descuento en sus vuelos
	@Query("select o2, min(o.discount) from Offer o join o.offertables o2 where o2 in (select a from Airline a) group by o2")
	Collection<Object[]> findLessPercentageDiscount();

	// 14. Una lista de aerolíneas con la media, el mínimo y el máximo número de las puntuaciones generales otorgadas por los usuarios en sus comentarios
	@Query("select c.airline, min(c.rating.airline), max(c.rating.airline), avg(c.rating.airline) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgRatingByAirline();

	// 15. El porcentaje de comentarios positivos de cada aerolínea
	@Query("select a, 100*(select count(c) from Comment c where c.airline=a and c.type='Positive')/(select count(c1) from Comment c1 where c1.airline=a) from Airline a")
	Collection<Object[]> findPositiveComments();

	// 16. El mínimo, el máximo y la media de las valoraciones proporcionadas por los usuarios al servicio ofrecido por cada aerolínea
	@Query("select c.airline, min(c.rating.service),max(c.rating.service),avg(c.rating.service) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgServiceRatingByAirline();

	// 17. El mínimo, el máximo y la media de las valoraciones proporcionadas por los usuarios a la comodidad de los asientos por cada aerolínea
	@Query(" select c.airline, min(c.rating.comfort), max(c.rating.comfort), avg(c.rating.comfort) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgComfortRatingByAirline();
}

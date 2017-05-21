
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {

	@Query("select a from Airline a where a.deleted=false")
	Collection<Airline> findNotDeleted();

	// 2. Las aerolíneas con menos reservas
	@Query("select s.airline.name from Book b join b.seasons s where b.cancelationMoment is null group by s.airline having count (b) <= ALL (select count(b1) from Book b1 join b1.seasons s1 where b1.cancelationMoment is null group by s1.airline)")
	Collection<String> findAirlineLessBooks();

	//5. Las aerolíneas con más y menos vuelos
	@Query("select f.airline.name from Flight f where cancelled='false' group by f.airline having count(f) >= ALL ( select count(f1) from Flight f1 where cancelled='false' group by f1.airline)")
	Collection<String> findAirlineMostFlights();

	@Query("select f.airline.name from Flight f where cancelled='false' group by f.airline having count(f) <= ALL ( select count(f1) from Flight f1 where cancelled='false' group by f1.airline)")
	Collection<String> findAirlineLessFlights();

	//8. El porcentaje de facturas pagadas por cada aerolínea 
	@Query("select mb.airline.name, 100.0*count(mb)/(select count(mb1) from MonthlyBill mb1 where mb1.airline=mb.airline)  from MonthlyBill mb where paidMoment is not null group by mb.airline")
	Collection<Object[]> findPercentagePaidBills();

	// 11. Las aerolíneas que han realizado el mayor y menor porcentaje de descuento en sus vuelos
	@Query("select o2, max(o.discount) from Offer o join o.offertables o2 where o2 in (select a from Airline a) group by o2")
	Collection<Object[]> findMostPercentageDiscount();

	@Query("select o2, min(o.discount) from Offer o join o.offertables o2 where o2 in (select a from Airline a) group by o2")
	Collection<Object[]> findLessPercentageDiscount();

	// 12. Una lista de aerolíneas con la media, el mínimo y el máximo número de las puntuaciones generales otorgadas por los usuarios en sus comentarios
	@Query("select c.airline, min(c.rating.airline), max(c.rating.airline), avg(c.rating.airline) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgRatingByAirline();

	// 14. El mínimo, el máximo y la media de las valoraciones proporcionadas por los usuarios al servicio ofrecido por cada aerolínea
	@Query("select c.airline.name, min(c.rating.service),max(c.rating.service),avg(c.rating.service) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgServiceRatingByAirline();

	// 15. El mínimo, el máximo y la media de las valoraciones proporcionadas por los usuarios a la comodidad de los asientos por cada aerolínea
	@Query(" select c.airline, min(c.rating.comfort), max(c.rating.comfort), avg(c.rating.comfort) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgComfortRatingByAirline();
}


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

	// 12. Una lista de aerolíneas con la media, el mínimo y el máximo número de las puntuaciones generales otorgadas por los usuarios en sus comentarios
	@Query("select c.airline, min(c.rating.airline), max(c.rating.airline), avg(c.rating.airline) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgRatingByAirline();

	// 15. El mínimo, el máximo y la media de las valoraciones proporcionadas por los usuarios a la comodidad de los asientos por cada aerolínea
	@Query(" select c.airline, min(c.rating.comfort), max(c.rating.comfort), avg(c.rating.comfort) from Comment c group by c.airline")
	Collection<Object[]> findMinMaxAvgComfortRatingByAirline();
}

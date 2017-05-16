
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
}

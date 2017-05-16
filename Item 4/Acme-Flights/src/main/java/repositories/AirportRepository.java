
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

}


package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Offertable;

@Repository
public interface OffertableRepository extends JpaRepository<Offertable, Integer> {

}

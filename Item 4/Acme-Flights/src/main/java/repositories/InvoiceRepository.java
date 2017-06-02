
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query("select i  from Invoice i where i.book.user.id=?1")
	Collection<Invoice> findByUser(int userId);

	@Query("select i  from Invoice i where i.book.id=?1")
	Invoice findByBook(int bookId);

	@Query("select distinct i from Invoice i join i.book.flights f where f.airline IN (select m.airline from Manager m where m.id=?1)")
	Collection<Invoice> findByManager(int managerId);
}

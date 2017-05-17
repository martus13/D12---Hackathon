
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BookRepository;
import domain.Book;
import domain.Season;

@Service
@Transactional
public class BookService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private BookRepository	bookRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserService		userService;


	// Constructors -----------------------------------------------------------
	public BookService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------
	public Collection<Book> findOfNotPassedFlightsBySeason(final Season season) {
		Collection<Book> result;

		result = this.bookRepository.findOfNotPassedFlightsBySeason(season.getAirline().getId(), season.getStartDay(), season.getStartMonth(), season.getEndDay(), season.getEndMonth());

		return result;
	}

}

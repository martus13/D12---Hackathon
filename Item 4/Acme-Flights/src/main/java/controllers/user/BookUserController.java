
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookService;
import services.FlightService;
import services.UserService;
import controllers.AbstractController;
import domain.Book;
import domain.Flight;
import domain.User;

@Controller
@RequestMapping("/book/user")
public class BookUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BookService		bookService;

	@Autowired
	private UserService		userService;

	@Autowired
	private FlightService	flightService;


	// Constructors -----------------------------------------------------------

	public BookUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------	
	@RequestMapping(value = "/listByUser", method = RequestMethod.GET)
	public ModelAndView listByUser() {
		ModelAndView result;
		Collection<Book> books;
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		books = this.bookService.findByUser(user.getId());

		result = new ModelAndView("book/list");
		result.addObject("requestURI", "book/user/listByChorbi.do");
		result.addObject("books", books);

		return result;
	}

	// Displaying -------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int bookId) {
		ModelAndView result;
		Book book;
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		book = this.bookService.findOne(bookId);
		Assert.isTrue(book.getUser().equals(user));

		result = new ModelAndView("book/display");
		result.addObject("requestURI", "book/user/display.do?bookId=" + bookId);
		result.addObject("book", book);

		return result;
	}

	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int departureId, @RequestParam final int destinationId) {
		ModelAndView result;
		Book book;
		Flight departure;
		Flight destination;

		departure = this.flightService.findOne(departureId);
		destination = this.flightService.findOne(destinationId);

		book = this.bookService.create(departure, destination);

		result = this.createEditModelAndView(book);

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Book book, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(book);

		} else
			try {
				book = this.bookService.save(book);
				result = new ModelAndView("redirect:listByUser.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(book, "book.commit.error");

			}
		return result;

	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Book book, final BindingResult binding) {

		ModelAndView result;

		this.bookService.delete(book);

		result = new ModelAndView("redirect:listByUser.do");

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Book book) {
		ModelAndView result;

		result = this.createEditModelAndView(book, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Book book, final String message) {
		ModelAndView result;

		result = new ModelAndView("book/create");
		result.addObject("book", book);
		result.addObject("message", message);

		return result;
	}

}

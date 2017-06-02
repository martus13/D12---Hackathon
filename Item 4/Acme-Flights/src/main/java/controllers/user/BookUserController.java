
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
import services.CreditCardService;
import services.ExchangeRateService;
import services.FlightService;
import services.InvoiceService;
import services.UserService;
import controllers.AbstractController;
import domain.Book;
import domain.CreditCard;
import domain.ExchangeRate;
import domain.Flight;
import domain.Invoice;
import domain.User;

@Controller
@RequestMapping("/book/user")
public class BookUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BookService			bookService;

	@Autowired
	private UserService			userService;

	@Autowired
	private FlightService		flightService;

	@Autowired
	private ExchangeRateService	exchangeRateService;

	@Autowired
	private CreditCardService	creditCardService;

	@Autowired
	private InvoiceService		invoiceService;


	// Constructors -----------------------------------------------------------

	public BookUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------	
	@RequestMapping(value = "/listByUser", method = RequestMethod.GET)
	public ModelAndView listByUser() {
		ModelAndView result;
		Collection<Book> books;
		Collection<ExchangeRate> exchangeRates;
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		books = this.bookService.findByUser(user.getId());
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("book/list");
		result.addObject("requestURI", "book/user/listByUser.do");
		result.addObject("books", books);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int bookId) {
		ModelAndView result;
		Book book;
		User user;
		Collection<Object[]> flights;
		Invoice invoice;
		Collection<ExchangeRate> exchangeRates;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		book = this.bookService.findOne(bookId);
		Assert.isTrue(book.getUser().equals(user));
		flights = this.flightService.findFlightsOfferAndSeasonByFlightsId(book.getFlights());
		invoice = this.invoiceService.findByBook(bookId);
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("book/display");
		result.addObject("requestURI", "book/user/display.do?bookId=" + bookId);
		result.addObject("book", book);
		result.addObject("flights", flights);
		result.addObject("exchangeRates", exchangeRates);
		result.addObject("invoice", invoice);

		return result;
	}

	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int departureId, @RequestParam final String season1, @RequestParam final String offerFlight1, @RequestParam final String offerAirline1, @RequestParam final int destinationId,
		@RequestParam final String season2, @RequestParam final String offerFlight2, @RequestParam final String offerAirline2) {
		ModelAndView result;
		Book book;
		Flight departure;
		Flight destination;
		Integer seasonDeparture;
		Integer offerFlightDeparture;
		Integer offerAirlineDeparture;
		Integer seasonDestination;
		Integer offerFlightDestination;
		Integer offerAirlineDestination;
		CreditCard creditCard;
		User user;

		departure = this.flightService.findOne(departureId);
		destination = this.flightService.findOne(destinationId);
		user = this.userService.findByPrincipal();

		if (season1 != "")
			seasonDeparture = Integer.valueOf(season1);
		else
			seasonDeparture = null;
		if (offerFlight1 != "")
			offerFlightDeparture = Integer.valueOf(offerFlight1);
		else
			offerFlightDeparture = null;
		if (offerAirline1 != "")
			offerAirlineDeparture = Integer.valueOf(offerAirline1);
		else
			offerAirlineDeparture = null;
		if (season2 != "")
			seasonDestination = Integer.valueOf(season2);
		else
			seasonDestination = null;
		if (offerFlight2 != "")
			offerFlightDestination = Integer.valueOf(offerFlight2);
		else
			offerFlightDestination = null;
		if (offerAirline2 != "")
			offerAirlineDestination = Integer.valueOf(offerAirline2);
		else
			offerAirlineDestination = null;

		creditCard = this.creditCardService.findByUser(user.getId());
		if (!this.creditCardService.checkValidation(creditCard)) {
			System.out.println("Invalid Credit Card");
			result = new ModelAndView("creditCard/list");
			result.addObject("validarionError", true);

		} else {

			book = this.bookService.create(departure, seasonDeparture, offerFlightDeparture, offerAirlineDeparture, destination, seasonDestination, offerFlightDestination, offerAirlineDestination);

			result = this.createEditModelAndView(book);
		}

		return result;
	}
	@RequestMapping(value = "/createWithoutReturn", method = RequestMethod.GET)
	public ModelAndView createWithoutReturn(@RequestParam final int departureId, @RequestParam final String season1, @RequestParam final String offerFlight1, @RequestParam final String offerAirline1) {
		ModelAndView result;
		Book book;
		Flight departure;
		Integer seasonDeparture;
		Integer offerFlightDeparture;
		Integer offerAirlineDeparture;

		departure = this.flightService.findOne(departureId);

		if (season1 != "")
			seasonDeparture = Integer.valueOf(season1);
		else
			seasonDeparture = null;
		if (offerFlight1 != "")
			offerFlightDeparture = Integer.valueOf(offerFlight1);
		else
			offerFlightDeparture = null;
		if (offerAirline1 != "")
			offerAirlineDeparture = Integer.valueOf(offerAirline1);
		else
			offerAirlineDeparture = null;

		book = this.bookService.create(departure, seasonDeparture, offerFlightDeparture, offerAirlineDeparture, null, null, null, null);

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
				result = new ModelAndView("redirect:../../applies/user/create.do?bookId=" + book.getId());

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(book, "book.commit.error");

			}
		return result;

	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int bookId) {

		ModelAndView result;
		Book book;

		book = this.bookService.findOne(bookId);

		try {

			this.bookService.delete(book);

			result = new ModelAndView("redirect:listByUser.do");

		} catch (final Throwable oops) {
			System.out.println(oops);

			result = new ModelAndView("book/display");
			result.addObject("requestURI", "book/user/display.do?bookId=" + bookId);
			result.addObject("book", book);
			result.addObject("deleteError", true);

		}

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
		Collection<Object[]> flights;
		Collection<ExchangeRate> exchangeRates;

		flights = this.flightService.findFlightsOfferAndSeasonByFlightsId(book.getFlights());
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("book/create");
		result.addObject("book", book);
		result.addObject("flights", flights);
		result.addObject("exchangeRates", exchangeRates);
		result.addObject("message", message);

		return result;
	}

}

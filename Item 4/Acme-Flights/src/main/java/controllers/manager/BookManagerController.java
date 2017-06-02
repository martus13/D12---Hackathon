package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookService;
import services.ExchangeRateService;
import services.FlightService;
import services.InvoiceService;

import controllers.AbstractController;

import domain.Book;
import domain.ExchangeRate;


@Controller
@RequestMapping("/book/manager")
public class BookManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BookService			bookService;

	@Autowired
	private FlightService		flightService;

	@Autowired
	private ExchangeRateService	exchangeRateService;

	// Constructors -----------------------------------------------------------

	public BookManagerController() {
		super();
	}
	
	
	// Displaying -------------------------------------------------------------

		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display(@RequestParam final int bookId) {
			ModelAndView result;
			Book book;
			Collection<Object[]> flights;
			Collection<ExchangeRate> exchangeRates;


			book = this.bookService.findOne(bookId);
			flights = this.flightService.findFlightsOfferAndSeasonByFlightsId(book.getFlights());
			exchangeRates = this.exchangeRateService.findAll();

			result = new ModelAndView("book/display");
			result.addObject("requestURI", "book/user/display.do?bookId=" + bookId);
			result.addObject("book", book);
			result.addObject("flights", flights);
			result.addObject("exchangeRates", exchangeRates);

			return result;
		}

}

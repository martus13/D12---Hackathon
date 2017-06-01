
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AppliesService;
import services.BookService;
import services.ExchangeRateService;
import controllers.AbstractController;
import domain.Applies;
import domain.Book;
import domain.ExchangeRate;
import domain.Flight;

@Controller
@RequestMapping("/applies/user")
public class AppliesUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AppliesService		appliesService;

	@Autowired
	private BookService			bookService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructors -----------------------------------------------------------
	public AppliesUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int bookId) {
		final ModelAndView result;
		Book book;

		book = this.bookService.findOne(bookId);

		result = this.createEditModelAndView(book);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int bookId, @Valid Applies applies, final BindingResult binding) {

		ModelAndView result;
		Book book;

		book = this.bookService.findOne(bookId);

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(applies.getBook());

		} else
			try {
				if (applies.getUsedPoints() == 0) {
					applies = this.appliesService.findOne(applies.getId());
					book = this.appliesService.delete(applies);
				} else
					applies = this.appliesService.save(applies);
				result = new ModelAndView("redirect:create.do?bookId=" + book.getId());

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(book, "applies.commit.error");

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
		ArrayList<Flight> flights;
		Applies applies1;
		Applies applies2;
		Collection<ExchangeRate> exchangeRates;

		flights = new ArrayList<Flight>(book.getFlights());
		exchangeRates = this.exchangeRateService.findAll();

		if (this.appliesService.findByBookAndPointsCardId(book.getId(), flights.get(0).getId()) == null)
			applies1 = this.appliesService.create(book, flights.get(0));
		else
			applies1 = this.appliesService.findByBookAndPointsCardId(book.getId(), flights.get(0).getId());

		if (this.appliesService.findByBookAndPointsCardId(book.getId(), flights.get(1).getId()) == null)
			applies2 = this.appliesService.create(book, flights.get(1));
		else
			applies2 = this.appliesService.findByBookAndPointsCardId(book.getId(), flights.get(1).getId());

		result = new ModelAndView("applies/create");
		result.addObject("requestURI", "applies/user/create.do?bookId=" + book.getId());
		result.addObject("applies1", applies1);
		result.addObject("applies2", applies2);
		result.addObject("book", book);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}
}

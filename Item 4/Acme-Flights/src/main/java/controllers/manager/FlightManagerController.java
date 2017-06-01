
package controllers.manager;

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

import services.AirportService;
import services.BookService;
import services.ExchangeRateService;
import services.FlightService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Airport;
import domain.Book;
import domain.ExchangeRate;
import domain.Flight;
import domain.Manager;

@Controller
@RequestMapping("/flight/manager")
public class FlightManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private FlightService		flightService;

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private AirportService		airportService;

	@Autowired
	private BookService			bookService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructors -----------------------------------------------------------

	public FlightManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/listByAirline", method = RequestMethod.GET)
	public ModelAndView listByAirline() {
		ModelAndView result;
		Collection<Object[]> flights;
		Manager manager;
		Collection<ExchangeRate> exchangeRates;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		flights = this.flightService.findNotCancelledByAirlineIdOfferAndSeason(manager.getAirline().getId());
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("flight/list");
		result.addObject("requestURI", "flight/manager/listByAirline.do");
		result.addObject("flights", flights);
		result.addObject("manager", manager);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int flightId) {
		ModelAndView result;
		Flight flight;
		Manager manager;
		Collection<Book> books;
		Collection<ExchangeRate> exchangeRates;

		flight = this.flightService.findOne(flightId);
		manager = this.managerService.findByPrincipal();
		books = this.bookService.findNotCancelledByFlightId(flightId);
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("flight/display");
		result.addObject("requestURI", "flight/manager/display.do?flightId=" + flightId);
		result.addObject("flight", flight);
		result.addObject("manager", manager);
		result.addObject("hasBooks", !books.isEmpty());
		result.addObject("books", books);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Flight flight;

		flight = this.flightService.create();

		result = this.createEditModelAndView(flight);

		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int flightId) {
		ModelAndView result;
		Flight flight;

		flight = this.flightService.findOne(flightId);

		result = this.createEditModelAndView(flight);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Flight flight, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(flight);

		} else
			try {
				flight = this.flightService.save(flight);
				result = new ModelAndView("redirect:listByAirline.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(flight, "flight.commit.error");

			}
		return result;

	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int flightId) {

		ModelAndView result;
		Flight flight;

		flight = this.flightService.findOne(flightId);
		this.flightService.delete(flight);

		result = new ModelAndView("redirect:listByAirline.do");

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Flight flight) {
		ModelAndView result;

		result = this.createEditModelAndView(flight, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Flight flight, final String message) {
		ModelAndView result;
		Collection<Airport> airports;

		airports = this.airportService.findNotDeleted();

		if (flight.getId() == 0)
			result = new ModelAndView("flight/create");
		else
			result = new ModelAndView("flight/edit");

		result.addObject("flight", flight);
		result.addObject("airports", airports);
		result.addObject("message", message);

		return result;
	}
}

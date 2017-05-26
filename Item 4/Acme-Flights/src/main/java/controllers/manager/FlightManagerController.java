
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
import services.FlightService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Airport;
import domain.Flight;
import domain.Manager;

@Controller
@RequestMapping("/flight/manager")
public class FlightManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private FlightService	flightService;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private AirportService	airportService;


	// Constructors -----------------------------------------------------------

	public FlightManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/listByAirline", method = RequestMethod.GET)
	public ModelAndView listByAirline() {
		ModelAndView result;
		Collection<Flight> flights;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		flights = this.flightService.findNotCancelledByAirline(manager.getAirline().getId());

		result = new ModelAndView("flight/list");
		result.addObject("requestURI", "flight/manager/listByAirline.do");
		result.addObject("flights", flights);

		return result;
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int flightId) {
		ModelAndView result;
		Flight flight;

		flight = this.flightService.findOne(flightId);

		result = new ModelAndView("flight/display");
		result.addObject("requestURI", "flight/manager/display.do?flightId=" + flightId);
		result.addObject("flight", flight);

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

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
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

	@RequestMapping(value = "/cancel", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Flight flight, final BindingResult binding) {

		ModelAndView result;

		try {
			this.flightService.delete(flight);

			result = new ModelAndView("redirect:listByAirline.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(flight, "flight.commit.error");
		}

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

		result = new ModelAndView("flight/create");
		result.addObject("flight", flight);
		result.addObject("airports", airports);
		result.addObject("message", message);

		return result;
	}
}

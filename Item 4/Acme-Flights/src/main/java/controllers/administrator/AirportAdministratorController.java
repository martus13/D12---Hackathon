
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AirportService;
import services.ExchangeRateService;
import controllers.AbstractController;
import domain.Airport;
import domain.ExchangeRate;

@Controller
@RequestMapping("/airport/administrator")
public class AirportAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AirportService		airportService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructors -----------------------------------------------------------

	public AirportAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Airport> airports;
		Collection<ExchangeRate> exchangeRates;

		airports = this.airportService.findNotDeleted();
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("airport/list");
		result.addObject("requestURI", "airport/administrator/list.do");
		result.addObject("airports", airports);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int airportId) {
		ModelAndView result;
		Airport airport;

		airport = this.airportService.findOne(airportId);

		result = new ModelAndView("airport/display");
		result.addObject("requestURI", "airport/administrator/display.do?airportId=" + airportId);
		result.addObject("airport", airport);

		return result;
	}

	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Airport airport;

		airport = this.airportService.create();

		result = this.createEditModelAndView(airport);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam final int airportId) {

		ModelAndView result;
		Airport airport;

		airport = this.airportService.findOne(airportId);

		result = this.createEditModelAndView(airport);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Airport airport, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(airport);

		} else
			try {
				airport = this.airportService.save(airport);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(airport, "airport.commit.error");

			}
		return result;

	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Airport airport, final BindingResult binding) {

		ModelAndView result;

		try {
			this.airportService.delete(airport);

			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(airport, "airport.commit.error");
		}

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Airport airport) {
		ModelAndView result;

		result = this.createEditModelAndView(airport, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Airport airport, final String message) {
		ModelAndView result;

		result = new ModelAndView("airport/edit");
		result.addObject("airport", airport);
		result.addObject("message", message);

		return result;
	}
}

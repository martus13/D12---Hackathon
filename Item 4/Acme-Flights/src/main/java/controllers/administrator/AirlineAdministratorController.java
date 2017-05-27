
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

import services.AirlineService;
import controllers.AbstractController;
import domain.Airline;

@Controller
@RequestMapping("/airline/administrator")
public class AirlineAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AirlineService	airlineService;


	// Constructors -----------------------------------------------------------

	public AirlineAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Airline> airlines;

		airlines = this.airlineService.findNotDeleted();

		result = new ModelAndView("airline/list");
		result.addObject("requestURI", "airline/administrator/list.do");
		result.addObject("airlines", airlines);

		return result;
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int airlineId) {
		ModelAndView result;
		Airline airline;

		airline = this.airlineService.findOne(airlineId);

		result = new ModelAndView("airline/display");
		result.addObject("requestURI", "airline/administrator/display.do?airlineId=" + airlineId);
		result.addObject("airline", airline);

		return result;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Airline airline;

		airline = this.airlineService.create();

		result = this.createEditModelAndView(airline);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam final int airlineId) {

		ModelAndView result;
		Airline airline;

		airline = this.airlineService.findOne(airlineId);

		result = this.createEditModelAndView(airline);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Airline airline, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(airline);

		} else
			try {
				airline = this.airlineService.save(airline);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(airline, "airline.commit.error");

			}
		return result;

	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Airline airline, final BindingResult binding) {

		ModelAndView result;

		try {
			this.airlineService.delete(airline);

			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(airline, "airline.commit.error");
		}

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Airline airline) {
		ModelAndView result;

		result = this.createEditModelAndView(airline, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Airline airline, final String message) {
		ModelAndView result;

		result = new ModelAndView("airline/edit");
		result.addObject("airline", airline);
		result.addObject("message", message);

		return result;
	}

}

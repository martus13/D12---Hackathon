
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FlightService;

@Controller
@RequestMapping("/flight")
public class FlightController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private FlightService	flightService;


	// Constructors -----------------------------------------------------------

	public FlightController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Object[]> flights;

		flights = this.flightService.findNotCancelledNotPassedOfferAndSeason();

		result = new ModelAndView("flight/list");
		result.addObject("requestURI", "flight/list.do");
		result.addObject("flights", flights);

		return result;
	}
}

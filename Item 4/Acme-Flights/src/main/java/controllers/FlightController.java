
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExchangeRateService;
import services.FlightService;
import domain.ExchangeRate;

@Controller
@RequestMapping("/flight")
public class FlightController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private FlightService		flightService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructors -----------------------------------------------------------

	public FlightController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Object[]> flights;
		Collection<ExchangeRate> exchangeRates;

		flights = this.flightService.findNotCancelledNotPassedOfferAndSeason();
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("flight/list");
		result.addObject("requestURI", "flight/list.do");
		result.addObject("flights", flights);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}
}

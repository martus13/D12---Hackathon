
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

import services.AirlineConfigurationService;
import services.AirlineService;
import services.ExchangeRateService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Airline;
import domain.AirlineConfiguration;
import domain.ExchangeRate;
import domain.Manager;

@Controller
@RequestMapping("/airlineConfiguration/manager")
public class AirlineConfigurationManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AirlineConfigurationService	airlineConfigurationService;

	@Autowired
	private ManagerService				managerService;

	@Autowired
	private AirlineService				airlineService;

	@Autowired
	private ExchangeRateService			exchangeRateService;


	// Constructors -----------------------------------------------------------

	public AirlineConfigurationManagerController() {
		super();
	}

	//Display-----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		AirlineConfiguration airlineConfiguration;
		Manager principal;
		Collection<ExchangeRate> exchangeRates;

		principal = this.managerService.findByPrincipal();
		airlineConfiguration = this.airlineConfigurationService.findByManager(principal.getId());
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("airlineConfiguration/display");
		result.addObject("airlineConfiguration", airlineConfiguration);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

	//Create ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		AirlineConfiguration airlineConfiguration;

		final Manager principal = this.managerService.findByPrincipal();
		final Airline airline = this.airlineService.findByManager(principal.getId());

		airlineConfiguration = this.airlineConfigurationService.create(airline);

		result = this.createEditModelAndView(airlineConfiguration);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int airlineConfigurationId) {
		ModelAndView result;
		AirlineConfiguration airlineConfiguration;

		airlineConfiguration = this.airlineConfigurationService.findOne(airlineConfigurationId);
		Assert.notNull(airlineConfiguration);

		result = this.createEditModelAndView(airlineConfiguration);

		return result;
	}

	//Save ------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AirlineConfiguration airlineConfiguration, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(airlineConfiguration);

		} else
			try {
				this.airlineConfigurationService.save(airlineConfiguration);
				result = new ModelAndView("redirect:display.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(airlineConfiguration, "airlineConfiguration.commit.error");

			}
		return result;
	}

	// Ancillary methods ----------------------------------------------------

	protected ModelAndView createEditModelAndView(final AirlineConfiguration airlineConfiguration) {
		ModelAndView result;

		result = this.createEditModelAndView(airlineConfiguration, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AirlineConfiguration airlineConfiguration, final String message) {
		ModelAndView result;

		result = new ModelAndView("airlineConfiguration/edit");
		result.addObject("airlineConfiguration", airlineConfiguration);
		result.addObject("message", message);

		return result;
	}
}

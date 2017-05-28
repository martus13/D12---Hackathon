package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import controllers.AbstractController;
import domain.Airline;
import domain.AirlineConfiguration;
import domain.Configuration;
import domain.Manager;

@Controller
@RequestMapping("/configuration/administrator")
public class ConfigurationAdminController extends AbstractController{
	

	// Services ---------------------------------------------------------------

	@Autowired
	private ConfigurationService	configurationService;

	// Constructors -----------------------------------------------------------

	public ConfigurationAdminController() {
		super();
	}

	
	//Display-----------------------------------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Configuration configuration = this.configurationService.findConfiguration();

		result = new ModelAndView("configuration/display");
		result.addObject("configuration", configuration);

		return result;
	}
	
	//Edit ----------------------------------------------------------------
		
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int configurationId){
		ModelAndView result;
		Configuration configuration;
		
		configuration = this.configurationService.findOne(configurationId);
		Assert.notNull(configuration);
		
		result=this.createEditModelAndView(configuration);
		
		return result;
	}
	
	//Save ------------------------------------------------------------------
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Configuration configuration, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(configuration);

		} else
			try {
				this.configurationService.save(configuration);
				result = new ModelAndView("redirect:display.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(configuration, "configuration.commit.error");

			}
		return result;
	}
	
	// Ancillary methods ----------------------------------------------------

	protected ModelAndView createEditModelAndView(final Configuration configuration) {
		ModelAndView result;

		result = this.createEditModelAndView(configuration, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Configuration configuration, final String message) {
		ModelAndView result;

		result = new ModelAndView("configuration/edit");
		result.addObject("configuration", configuration);
		result.addObject("message", message);

		return result;
	}
}

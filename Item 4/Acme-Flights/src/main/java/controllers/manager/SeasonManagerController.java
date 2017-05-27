
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

import services.ManagerService;
import services.SeasonService;
import controllers.AbstractController;
import domain.Manager;
import domain.Season;

@Controller
@RequestMapping("/season/manager")
public class SeasonManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SeasonService	seasonService;

	@Autowired
	private ManagerService	managerService;


	// Constructors -----------------------------------------------------------

	public SeasonManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/listByAirline", method = RequestMethod.GET)
	public ModelAndView listByAirline() {
		ModelAndView result;
		Collection<Season> seasons;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		seasons = this.seasonService.findActiveByAirlineId(manager.getAirline().getId());

		result = new ModelAndView("season/list");
		result.addObject("requestURI", "season/manager/listByAirline.do");
		result.addObject("seasons", seasons);

		return result;
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int seasonId) {
		ModelAndView result;
		Season season;

		season = this.seasonService.findOne(seasonId);

		result = new ModelAndView("season/display");
		result.addObject("requestURI", "season/manager/display.do?seasonId=" + seasonId);
		result.addObject("season", season);

		return result;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Season season;

		season = this.seasonService.create();

		result = this.createEditModelAndView(season);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Season season, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(season);

		} else
			try {
				season = this.seasonService.save(season);
				result = new ModelAndView("redirect:listByAirline.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(season, "season.commit.error");

			}
		return result;

	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int seasonId) {

		ModelAndView result;
		Season season;

		season = this.seasonService.findOne(seasonId);
		this.seasonService.delete(season);

		result = new ModelAndView("redirect:listByAirline.do");

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Season season) {
		ModelAndView result;

		result = this.createEditModelAndView(season, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Season season, final String message) {
		ModelAndView result;

		result = new ModelAndView("season/create");
		result.addObject("season", season);
		result.addObject("message", message);

		return result;
	}
}


package controllers.manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Banner;
import domain.Campaign;
import domain.Manager;

@Controller
@RequestMapping("/banner/manager")
public class BannerManagerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private BannerService	bannerService;

	@Autowired
	private ManagerService	managerService;


	// Constructors -----------------------------------------------------------

	public BannerManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Banner> banners;
		Manager manager;

		manager = this.managerService.findByPrincipal();

		banners = this.bannerService.findByAirlineId(manager.getAirline().getId());

		result = new ModelAndView("banner/list");
		result.addObject("requestURI", "banner/manager/list.do");
		result.addObject("banners", banners);

		return result;
	}
	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		Banner banner;

		banner = this.bannerService.create();

		result = this.createEditModelAndView(banner);

		return result;
	}

	// Edition ----------------------------------------------------------------		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int bannerId) {
		ModelAndView result;
		Banner banner;

		banner = this.bannerService.findOne(bannerId);

		result = this.createEditModelAndView(banner);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Banner banner, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(banner);

		} else
			try {
				banner = this.bannerService.save(banner);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(banner, "banner.commit.error");

			}
		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int bannerId) {

		ModelAndView result;
		Banner banner;

		banner = this.bannerService.findOne(bannerId);

		this.bannerService.delete(banner);

		result = new ModelAndView("redirect:list.do");

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Banner banner) {
		ModelAndView result;

		result = this.createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Banner banner, final String message) {
		ModelAndView result;
		Collection<Campaign> campaigns;

		campaigns = this.bannerService.findCampaignsByAirlineAndManager();

		result = new ModelAndView("banner/edit");
		result.addObject("campaigns", campaigns);
		result.addObject("banner", banner);
		result.addObject("message", message);

		return result;
	}
}

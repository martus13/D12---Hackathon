
package controllers.manager;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CampaignService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Campaign;
import domain.Manager;

@Controller
@RequestMapping("/campaign/manager")
public class CampaignManagerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CampaignService	campaignService;

	@Autowired
	private ManagerService	managerService;


	// Constructors -----------------------------------------------------------

	public CampaignManagerController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Campaign campaign;

		campaign = this.campaignService.create();

		result = new ModelAndView("campaign/edit");
		result.addObject("campaign", campaign);
		result.addObject("requestURI", "campaign/manager/edit.do");

		return result;

	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Manager manager;
		Collection<Campaign> campaigns;

		manager = this.managerService.findByPrincipal();
		campaigns = this.campaignService.findByAirlineId(manager.getAirline().getId());

		result = new ModelAndView("campaign/list");
		result.addObject("campaigns", campaigns);
		result.addObject("requestURI", "campaign/manager/list.do");
		result.addObject("now", new Date());

		return result;

	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int campaignId) {
		ModelAndView result;
		Campaign campaign;

		campaign = this.campaignService.findOne(campaignId);

		result = new ModelAndView("campaign/edit");
		result.addObject("campaign", campaign);
		result.addObject("requestURI", "campaign/manager/edit.do");

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Campaign campaign, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());

			result = this.createEditModelAndView(campaign);
		} else
			try {
				this.campaignService.save(campaign);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());

				result = this.createEditModelAndView(campaign, "campaign.commit.error");
			}

		return result;

	}

	// Delete -----------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam final int campaignId) {
		ModelAndView result;
		Campaign campaign;

		campaign = this.campaignService.findOne(campaignId);

		try {
			this.campaignService.delete(campaign);
			result = new ModelAndView("redirect:../manager/list.do?");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(campaign, "campaign.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Campaign campaign) {
		ModelAndView result;

		result = this.createEditModelAndView(campaign, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Campaign campaign, final String message) {
		ModelAndView result;

		result = new ModelAndView("campaign/edit");
		result.addObject("campaign", campaign);
		result.addObject("message", message);

		return result;
	}

}

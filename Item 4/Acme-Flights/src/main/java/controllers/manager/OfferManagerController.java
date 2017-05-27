
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

import services.FlightService;
import services.ManagerService;
import services.OfferService;
import controllers.AbstractController;
import domain.Flight;
import domain.Manager;
import domain.Offer;

@Controller
@RequestMapping("/offer/manager")
public class OfferManagerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OfferService	offerService;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private FlightService	flightService;


	// Constructors -----------------------------------------------------------

	public OfferManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Offer> offers;

		Manager manager;

		manager = this.managerService.findByPrincipal();
		offers = this.offerService.findByOffertableIdAll(manager.getAirline().getId());

		result = new ModelAndView("offer/list");
		result.addObject("requestURI", "offer/manager/list.do");
		result.addObject("offers", offers);

		return result;
	}

	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		Offer offer;

		offer = this.offerService.create();

		result = this.createEditModelAndView(offer);

		return result;
	}

	// Edition ----------------------------------------------------------------		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int offerId) {
		ModelAndView result;
		Offer offer;

		offer = this.offerService.findOne(offerId);

		result = this.createEditModelAndView(offer);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Offer offer, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(offer);

		} else
			try {
				offer = this.offerService.save(offer);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(offer, "offer.commit.error");

			}
		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int offerId) {

		ModelAndView result;
		Offer offer;

		offer = this.offerService.findOne(offerId);

		this.offerService.delete(offer);

		result = new ModelAndView("redirect:list.do");

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Offer offer) {
		ModelAndView result;

		result = this.createEditModelAndView(offer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Offer offer, final String message) {
		final ModelAndView result;
		Collection<Flight> flights;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		flights = this.flightService.findNotCancelledByAirline(manager.getAirline().getId());

		result = new ModelAndView("offer/edit");
		result.addObject("offer", offer);
		result.addObject("flights", flights);
		result.addObject("message", message);

		return result;
	}
}

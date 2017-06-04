
package controllers.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FlightService;
import services.ManagerService;
import services.OfferService;
import services.OffertableService;
import controllers.AbstractController;
import domain.Airline;
import domain.Flight;
import domain.Manager;
import domain.Offer;
import domain.Offertable;

@Controller
@RequestMapping("/offer/manager")
public class OfferManagerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OfferService		offerService;

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private FlightService		flightService;

	@Autowired
	private OffertableService	offertableService;


	// Constructors -----------------------------------------------------------

	public OfferManagerController() {
		super();
	}

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "offertables", new CustomCollectionEditor(Set.class) {

			@Override
			protected Object convertElement(final Object element) {
				Integer id = null;

				if (element instanceof String && !((String) element).equals(""))
					//From the JSP 'element' will be a String
					try {
						id = Integer.parseInt((String) element);
					} catch (final NumberFormatException e) {
						System.out.println("Element was " + ((String) element));
						e.printStackTrace();
					}
				else if (element instanceof Integer)
					//From the database 'element' will be a Long
					id = (Integer) element;

				return id != null ? OfferManagerController.this.offertableService.findOne(id) : null;
			}
		});
	}

	// Listing ----------------------------------------------------------------		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Offer> offers;

		Manager manager;

		manager = this.managerService.findByPrincipal();
		offers = this.offerService.findByAirlineId(manager.getAirline().getId());

		result = new ModelAndView("offer/list");
		result.addObject("requestURI", "offer/manager/list.do");
		result.addObject("offers", offers);
		result.addObject("manager", manager);

		return result;
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int offerId) {
		ModelAndView result;
		Offer offer;
		Manager manager;
		Offertable offertable;
		Airline airline;
		Collection<Object[]> flights;

		offer = this.offerService.findOne(offerId);
		manager = this.managerService.findByPrincipal();
		airline = null;
		offertable = manager.getAirline();
		if (this.offertableService.findByOfferId(offerId).contains(offertable))
			airline = manager.getAirline();

		flights = this.flightService.findNotCancelledNotPassedSeasonByOffer(offerId);

		result = new ModelAndView("offer/display");
		result.addObject("requestURI", "offerId/manager/display.do?offerId=" + offerId);
		result.addObject("offer", offer);
		result.addObject("airline", airline);
		result.addObject("flights", flights);

		return result;
	}
	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam final boolean isAirline) {
		ModelAndView result;
		Offer offer;
		Manager manager;

		offer = this.offerService.create();
		manager = this.managerService.findByPrincipal();

		if (isAirline)
			offer.addOffertable(manager.getAirline());

		result = this.createEditModelAndView(offer, isAirline);

		return result;
	}

	// Edition ----------------------------------------------------------------		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int offerId, @RequestParam final boolean isAirline) {
		ModelAndView result;
		Offer offer;

		offer = this.offerService.findOne(offerId);

		result = this.createEditModelAndView(offer, isAirline);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final boolean isAirline, @Valid Offer offer, final BindingResult binding) {

		ModelAndView result;
		Manager manager;
		Collection<Offertable> offertables;

		manager = this.managerService.findByPrincipal();
		offertables = new ArrayList<Offertable>();

		if (isAirline) {
			offertables.add(manager.getAirline());
			offer.setOffertables(offertables);
		} else
			offer.removeOffertable(manager.getAirline());

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(offer, isAirline);

		} else
			try {
				offer = this.offerService.save(offer);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(offer, isAirline, "offer.commit.error");

			}
		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int offerId) {

		ModelAndView result;
		Offer offer;

		offer = this.offerService.findOne(offerId);

		try {

			this.offerService.delete(offer);

			result = new ModelAndView("redirect:list.do");

		} catch (final Throwable oops) {
			Collection<Offer> offers;

			Manager manager;

			manager = this.managerService.findByPrincipal();
			offers = this.offerService.findByAirlineId(manager.getAirline().getId());

			result = new ModelAndView("offer/list");
			result.addObject("requestURI", "offer/manager/list.do");
			result.addObject("offers", offers);
			result.addObject("deleteError", true);

		}

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Offer offer, final boolean isAirline) {
		ModelAndView result;

		result = this.createEditModelAndView(offer, isAirline, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Offer offer, final boolean isAirline, final String message) {
		final ModelAndView result;
		Collection<Flight> flights;
		Offertable offertable;
		Collection<Offertable> offertables;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		flights = this.flightService.findNotCancelledNotPassedByAirlineId(manager.getAirline().getId());
		offertable = this.offertableService.findOne(manager.getAirline().getId());
		offertables = new ArrayList<Offertable>();

		offertables.add(offertable);

		result = new ModelAndView("offer/edit");
		result.addObject("offer", offer);
		result.addObject("flights", flights);
		result.addObject("airline", manager.getAirline());
		result.addObject("offertablesCollection", offertables);
		result.addObject("isAirline", isAirline);
		result.addObject("message", message);
		result.addObject("isCreate", offer.getId() == 0);

		return result;
	}
}


package controllers.manager;

import java.util.Collection;
import java.util.HashSet;
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
	    protected void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Set.class, "offertables", new CustomCollectionEditor(Set.class)
	          {
	            @Override
	            protected Object convertElement(Object element)
	            {
	                Integer id = null;

	                if(element instanceof String && !((String)element).equals("")){
	                    //From the JSP 'element' will be a String
	                    try{
	                        id = Integer.parseInt((String) element);
	                    }
	                    catch (NumberFormatException e) {
	                        System.out.println("Element was " + ((String) element));
	                        e.printStackTrace();
	                    }
	                }
	                else if(element instanceof Integer) {
	                    //From the database 'element' will be a Long
	                    id = (Integer) element;
	                }

	                return id != null ? offertableService.findOne(id) : null;
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
		
		offer.setOffertables(offer.getOffertables());
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
		flights = this.flightService.findNotCancelledByAirlineId(manager.getAirline().getId());

		result = new ModelAndView("offer/edit");
		result.addObject("offer", offer);
		result.addObject("flights", flights);
		result.addObject("airline", manager.getAirline());
		result.addObject("message", message);

		return result;
	}
}


package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import services.PointsCardService;
import controllers.AbstractController;
import domain.Manager;
import domain.PointsCard;

@Controller
@RequestMapping("/pointsCard/manager")
public class PointsCardManagerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private PointsCardService	pointsCardService;

	@Autowired
	private ManagerService		managerService;


	// Constructors -----------------------------------------------------------

	public PointsCardManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------		
	@RequestMapping(value = "/listByManager", method = RequestMethod.GET)
	public ModelAndView listByManager() {
		ModelAndView result;
		Collection<PointsCard> pointsCards;
		Manager manager;

		manager = this.managerService.findByPrincipal();

		pointsCards = this.pointsCardService.findByAirlineId(manager.getAirline().getId());

		result = new ModelAndView("pointsCard/list");
		result.addObject("requestURI", "pointsCard/manager/listByManager.do");
		result.addObject("pointsCards", pointsCards);

		return result;
	}
	// Creation ---------------------------------------------------------------		
	@RequestMapping(value = "/resetExpired", method = RequestMethod.GET)
	public ModelAndView resetExpired() {

		ModelAndView result;

		this.pointsCardService.resetExpired();

		result = new ModelAndView("redirect:listByManager.do");

		return result;

	}

}


package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PointsCardService;
import services.UserService;
import controllers.AbstractController;
import domain.PointsCard;
import domain.User;

@Controller
@RequestMapping("/pointsCard/user")
public class PointsCardUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private PointsCardService	pointsCardService;

	@Autowired
	private UserService			userService;


	// Constructors -----------------------------------------------------------

	public PointsCardUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------	
	@RequestMapping(value = "/listByUser", method = RequestMethod.GET)
	public ModelAndView listByUser() {
		ModelAndView result;
		Collection<PointsCard> pointsCards;
		User user;

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		pointsCards = this.pointsCardService.findByUserId(user.getId());

		result = new ModelAndView("pointsCard/list");
		result.addObject("pointsCards", pointsCards);

		return result;
	}
}

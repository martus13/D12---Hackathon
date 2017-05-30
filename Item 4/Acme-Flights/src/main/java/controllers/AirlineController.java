
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.AirlineConfigurationService;
import services.AirlineService;
import services.CommentService;
import services.SeasonService;
import domain.Actor;
import domain.Airline;
import domain.AirlineConfiguration;
import domain.Comment;
import domain.Season;

@Controller
@RequestMapping("/airline")
public class AirlineController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AirlineService				airlineService;

	@Autowired
	private CommentService				commentService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private AirlineConfigurationService	airlineConfigurationService;

	@Autowired
	private SeasonService				seasonService;


	// Constructors -----------------------------------------------------------

	public AirlineController() {
		super();
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int airlineId) {
		ModelAndView result;
		Airline airline;
		Boolean hasCommented = false;
		Boolean hasFlight = false;
		Actor principal;
		Collection<Comment> comments;
		AirlineConfiguration airlineConfiguration;
		Collection<Season> seasons;

		airline = this.airlineService.findOne(airlineId);

		if (LoginService.checkiPrincipal()) {
			principal = this.actorService.findByPrincipal();
			hasCommented = this.commentService.hasCommented(principal.getId(), airline.getId());
			hasFlight = this.commentService.hasFlight(principal.getId(), airline.getId());
		} else {
			principal = null;
			hasCommented = true;
			hasFlight = false;
		}

		comments = this.commentService.findByAirlineId(airlineId);
		airlineConfiguration = this.airlineConfigurationService.findByAirlineId(airlineId);
		seasons = this.seasonService.findActiveByAirlineId(airlineId);

		result = new ModelAndView("airline/display");
		result.addObject("requestURI", "airline/display.do?airlineId=" + airlineId);
		result.addObject("airline", airline);
		result.addObject("comments", comments);
		result.addObject("airlineConfiguration", airlineConfiguration);
		result.addObject("seasons", seasons);
		result.addObject("hasCommented", hasCommented);
		result.addObject("hasFlight", hasFlight);

		return result;
	}
}

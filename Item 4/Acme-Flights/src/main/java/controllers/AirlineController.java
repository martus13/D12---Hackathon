
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AirlineService;
import services.CommentService;
import domain.Airline;
import domain.Comment;

@Controller
@RequestMapping("/airline")
public class AirlineController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AirlineService	airlineService;
	
	@Autowired
	private CommentService commentService;


	// Constructors -----------------------------------------------------------

	public AirlineController() {
		super();
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int airlineId) {
		ModelAndView result;
		Airline airline;

		airline = this.airlineService.findOne(airlineId);
		Collection<Comment> comentarios = this.commentService.findByAirlineId(airlineId);
		
		result = new ModelAndView("airline/display");
		result.addObject("requestURI", "airline/display.do?airlineId=" + airlineId);
		result.addObject("airline", airline);
		result.addObject("comments", comentarios);

		return result;
	}
}

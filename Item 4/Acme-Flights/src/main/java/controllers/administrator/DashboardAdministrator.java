
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AirlineService;
import controllers.AbstractController;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministrator extends AbstractController {

	// Services ---------------------------------------------------------------
	
	@Autowired
	private AirlineService airlineService;

	// Constructors -----------------------------------------------------------

	public DashboardAdministrator() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;

		//1.
		
		//2. 
		Collection<Object[]> airlineLessBooks = this.airlineService.findAirlineLessBooks();
		
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("airlineLessBooks",airlineLessBooks);
		return result;
	}
}


package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AirlineService;
import services.AirportService;
import services.FlightService;
import controllers.AbstractController;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------
	
	@Autowired
	private AirlineService airlineService;
	
	@Autowired
	private AirportService airportService;
	
	@Autowired
	private FlightService flightService;

	// Constructors -----------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;

		//1.
		Collection<Object[]> airlineMostBooks = this.airlineService.findAirlineMostBooks();
		//2. 
		Collection<Object[]> airlineLessBooks = this.airlineService.findAirlineLessBooks();
		//3.
		Collection<Object[]> lessVisitedCities = this.airportService.findLessVisitedCities();
		//4. 
		Collection<Object[]> mostVisitedCities = this.airportService.findMostVisitedCities();
		//5. 
		Collection<Object[]> lessFlightsPerAirline = this.airlineService.findAirlineLessFlights();
		//6.
		Collection<Object[]> mostFlightsPerAirline = this.airlineService.findAirlineMostFlights();
		//7.
		Object[] minMaxAvgFlightsOrigin = this.flightService.findMaxMinAvgFlightsPerAirport();
		//8.
		Collection<Object[]> airlinesOrderByBills = this.airlineService.findAirlinesOrderByNumberOfBills();
		//9.
		Collection<Object[]> percentageBillsPaid = this.airlineService.findPercentagePaidBills();
		//10.
		Collection<Object[]> percentageOffertedFlightsHigh= this.flightService.findPercentFlightsPerAirlineHigh();
		//11.
		Collection<Object[]> percentageOffertedFlightsLow= this.flightService.findPercentFlightsPerAirlineLow();
		//12. 
		Collection<Object[]> findMostPercentageDiscount = this.airlineService.findMostPercentageDiscount();
		//13.
		Collection<Object[]> findLessPercentageDiscount = this.airlineService.findLessPercentageDiscount();
		//14.
		Collection<Object[]> findMinMaxAvgRatingByAirline = this.airlineService.findMinMaxAvgRatingByAirline();
		//15.
		Collection<Object[]> findPositiveComments = this.airlineService.findPositiveComments();
		//16.
		Collection<Object[]> findMinMaxAvgServiceRatingByAirline = this.airlineService.findMinMaxAvgServiceRatingByAirline();
		//17.
		Collection<Object[]> findMinMaxAvgComfortRatingByAirline = this.airlineService.findMinMaxAvgComfortRatingByAirline();
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("airlineLessBooks",airlineLessBooks);
		result.addObject("airlineMostBooks", airlineMostBooks);
		result.addObject("lessVisitedCities", lessVisitedCities);
		result.addObject("mostVisitedCities", mostVisitedCities);
		result.addObject("lessFlightsPerAirline", lessFlightsPerAirline);
		result.addObject("mostFlightsPerAirline", mostFlightsPerAirline);
		result.addObject("minMaxAvgFlightsOrigin", minMaxAvgFlightsOrigin);
		result.addObject("airlinesOrderByBills", airlinesOrderByBills);
		result.addObject("percentageBillsPaid", percentageBillsPaid);
		result.addObject("percentageOffertedFlightsHigh", percentageOffertedFlightsHigh);
		result.addObject("percentageOffertedFlightsLow", percentageOffertedFlightsLow);
		result.addObject("findMostPercentageDiscount", findMostPercentageDiscount);
		result.addObject("findLessPercentageDiscount", findLessPercentageDiscount);
		result.addObject("findMinMaxAvgRatingByAirline", findMinMaxAvgRatingByAirline);
		result.addObject("findPositiveComments", findPositiveComments);
		result.addObject("findMinMaxAvgServiceRatingByAirline", findMinMaxAvgServiceRatingByAirline);
		result.addObject("findMinMaxAvgComfortRatingByAirline", findMinMaxAvgComfortRatingByAirline);
		
		
		return result;
	}
}

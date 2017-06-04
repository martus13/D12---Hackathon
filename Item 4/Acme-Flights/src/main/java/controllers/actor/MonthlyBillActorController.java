
package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExchangeRateService;
import services.MonthlyBillService;
import controllers.AbstractController;
import domain.ExchangeRate;
import domain.MonthlyBill;

@Controller
@RequestMapping("/monthlyBill/actor")
public class MonthlyBillActorController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private MonthlyBillService	monthlyBillService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructor --------------------------------------------------------

	public MonthlyBillActorController() {
		super();
	}

	// Display ------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int monthlyBillId) {
		ModelAndView result;
		MonthlyBill monthlyBill;
		Collection<ExchangeRate> exchangeRates;

		exchangeRates = this.exchangeRateService.findAll();
		monthlyBill = this.monthlyBillService.findOne(monthlyBillId);

		result = new ModelAndView("monthlyBill/display");
		result.addObject("monthlyBill", monthlyBill);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

}

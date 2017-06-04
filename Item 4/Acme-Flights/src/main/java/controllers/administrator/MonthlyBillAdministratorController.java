
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ExchangeRateService;
import services.MonthlyBillService;
import controllers.AbstractController;
import domain.Administrator;
import domain.ExchangeRate;
import domain.MonthlyBill;

@Controller
@RequestMapping("/monthlyBill/administrator")
public class MonthlyBillAdministratorController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private MonthlyBillService		monthlyBillService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ExchangeRateService		exchangeRateService;


	// Constructor --------------------------------------------------------

	public MonthlyBillAdministratorController() {
		super();
	}

	// Listing ------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<MonthlyBill> monthlyBills;
		Collection<ExchangeRate> exchangeRates;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		exchangeRates = this.exchangeRateService.findAll();
		monthlyBills = this.monthlyBillService.findAll();

		result = new ModelAndView("monthlyBill/list");
		result.addObject("monthlyBills", monthlyBills);
		result.addObject("requestURI", "monthlyBill/administrator/list.do");
		result.addObject("exchangeRates", exchangeRates);
		result.addObject("errorUnpaid", false);

		return result;
	}

	// Creation -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "monthlyBills")
	public ModelAndView create() {
		ModelAndView result;

		this.monthlyBillService.computeMonthlyBills();

		result = new ModelAndView("redirect:list.do");

		return result;
	}

}

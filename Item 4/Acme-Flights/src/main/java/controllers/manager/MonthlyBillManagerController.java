
package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MonthlyBillService;
import controllers.AbstractController;
import domain.MonthlyBill;

@Controller
@RequestMapping("/monthlyBill/manager")
public class MonthlyBillManagerController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private MonthlyBillService	monthlyBillService;


	// Constructor --------------------------------------------------------

	public MonthlyBillManagerController() {
		super();
	}

	// Listing ------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<MonthlyBill> monthlyBills;

		monthlyBills = this.monthlyBillService.findAll();

		result = new ModelAndView("monthlyBill/list");
		result.addObject("monthlyBills", monthlyBills);
		result.addObject("requestURI", "monthlyBill/administrator/list.do");

		return result;
	}

	// Creation -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "monthlyBills")
	public ModelAndView create() {
		ModelAndView result;

		result = new ModelAndView("redirect:list.do");

		return result;
	}

}

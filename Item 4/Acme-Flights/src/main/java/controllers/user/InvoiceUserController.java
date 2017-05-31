
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InvoiceService;
import services.UserService;
import controllers.AbstractController;
import domain.Invoice;
import domain.User;

@Controller
@RequestMapping("/invoice/user")
public class InvoiceUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService		userService;

	@Autowired
	private InvoiceService	invoiceService;


	// Constructors -----------------------------------------------------------

	public InvoiceUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		User principal;
		Collection<Invoice> invoices;

		principal = this.userService.findByPrincipal();
		invoices = this.invoiceService.findByUser(principal.getId());

		result = new ModelAndView("invoice/list");
		result.addObject("invoices", invoices);

		return result;
	}
}

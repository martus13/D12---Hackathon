
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InvoiceService;
import controllers.AbstractController;
import domain.Invoice;

@Controller
@RequestMapping("/invoice/administrator")
public class InvoiceAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private InvoiceService	invoiceService;


	// Constructors -----------------------------------------------------------

	public InvoiceAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Invoice> invoices = this.invoiceService.findAll();

		result = new ModelAndView("invoice/list");
		result.addObject("invoices", invoices);

		return result;
	}

}

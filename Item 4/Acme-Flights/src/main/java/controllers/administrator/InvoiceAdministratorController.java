
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExchangeRateService;
import services.InvoiceService;
import controllers.AbstractController;
import domain.ExchangeRate;
import domain.Invoice;

@Controller
@RequestMapping("/invoice/administrator")
public class InvoiceAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private InvoiceService		invoiceService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructors -----------------------------------------------------------

	public InvoiceAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ExchangeRate> exchangeRates;
		final Collection<Invoice> invoices = this.invoiceService.findAll();

		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("invoice/list");
		result.addObject("invoices", invoices);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

	// Generate invoices ------------------------------------------------------	

	@RequestMapping(value = "/generateInvoices", method = RequestMethod.POST, params = "generate")
	public ModelAndView generateInvoices() {
		ModelAndView result;

		this.invoiceService.generateInvoices();

		result = new ModelAndView("redirect:list.do");

		return result;
	}

}

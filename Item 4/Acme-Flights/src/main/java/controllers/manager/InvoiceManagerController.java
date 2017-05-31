
package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExchangeRateService;
import services.InvoiceService;
import services.ManagerService;
import controllers.AbstractController;
import domain.ExchangeRate;
import domain.Invoice;
import domain.Manager;

@Controller
@RequestMapping("/invoice/manager")
public class InvoiceManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private InvoiceService		invoiceService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructors -----------------------------------------------------------

	public InvoiceManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Manager principal;
		Collection<Invoice> invoices;
		Collection<ExchangeRate> exchangeRates;

		exchangeRates = this.exchangeRateService.findAll();
		principal = this.managerService.findByPrincipal();
		invoices = this.invoiceService.findByManager(principal.getId());

		result = new ModelAndView("invoice/list");
		result.addObject("invoices", invoices);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}

	//Mark as paid

	@RequestMapping(value = "/markAsPaid", method = RequestMethod.POST, params = "paid")
	public ModelAndView markAsPaid(@RequestParam final int invoiceId) {
		ModelAndView result;
		Manager principal;
		Collection<Invoice> invoices;
		Invoice invoice;

		invoice = this.invoiceService.findOne(invoiceId);
		principal = this.managerService.findByPrincipal();
		invoices = this.invoiceService.findByManager(principal.getId());
		Assert.isTrue(invoices.contains(invoice));

		this.invoiceService.markAsPaid(invoice);

		result = new ModelAndView("redirect:list.do");
		return result;
	}

}

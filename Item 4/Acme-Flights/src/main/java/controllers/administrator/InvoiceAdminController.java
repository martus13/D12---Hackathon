package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BookService;
import services.InvoiceService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Invoice;
import domain.Manager;

@Controller
@RequestMapping("/invoice/administrator")
public class InvoiceAdminController extends AbstractController{
	
	
	// Services ---------------------------------------------------------------

		@Autowired
		private InvoiceService		invoiceService;



		// Constructors -----------------------------------------------------------

		public InvoiceAdminController() {
			super();
		}

		// Listing ----------------------------------------------------------------	

		
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView result;
			Collection<Invoice> invoices = this.invoiceService.findAll();
			
			result= new ModelAndView("invoice/list");
			result.addObject("invoices",invoices);
			
			return result;
		}

}

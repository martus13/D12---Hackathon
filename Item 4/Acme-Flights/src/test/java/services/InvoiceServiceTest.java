package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Book;
import domain.Invoice;


@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
public class InvoiceServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private BookService bookService;
	
	
	//Use case: generar automáticamente las facturas
	@Test 
	public void testGenerateInvoices(){
		
		Collection<Book> booksBefore = this.bookService.findNotCancelledWithoutInvoices();
		
		System.out.println("Había "+booksBefore.size()+" facturas sin generar");
		
		this.invoiceService.generateInvoices();
		
		Collection<Book> booksAfter = this.bookService.findNotCancelledWithoutInvoices();
		Assert.isTrue(booksAfter.size()==0);
		
		System.out.println("Todas las facturas generadas hasta la fecha");
		
	}
	
	//Use case: listar las facturas de un manager
	
	//Use case: listar las facturas de un usuario
	
	//Use case: marcar las factura como pagadas por el gerente 

}


package services;

import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import repositories.BookRepository;
import repositories.InvoiceRepository;
import domain.Administrator;
import domain.Book;
import domain.Flight;
import domain.Invoice;

@Service
@Transactional
public class InvoiceService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private InvoiceRepository	invoiceRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private BookService bookService;
	
//	@Autowired
//	private AdministratorService adminService;
	
	// Constructors -----------------------------------------------------------
	public InvoiceService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Invoice findOne(final int invoiceId) {
		return this.invoiceRepository.findOne(invoiceId);
	}

	public Collection<Invoice> findAll() {
		return this.invoiceRepository.findAll();
	}

	public Invoice create(final Book book) {
		Assert.notNull(book);
		final Invoice result = new Invoice();

		final Calendar creationMoment = Calendar.getInstance();
		creationMoment.set(Calendar.MILLISECOND, -10);

		result.setCreationMoment(creationMoment.getTime());
		result.setTotalFee(book.getTotalFee());
		result.setBook(book);

		return result;
	}

	public Invoice save(final Invoice invoice) {
		Assert.notNull(invoice);

		this.invoiceRepository.save(invoice);

		return invoice;
	}
	
	// Other business methods--------------------------------------------------------
	
	public void generateInvoices(){
		
		Collection<Book> books = this.bookService.findNotCancelledWithoutInvoices();
		
		for(Book b:books){
				Invoice invoice = this.create(b);
				this.save(invoice);
		}
	}
	
	public Collection<Invoice> findByUser(int userId){
		return this.invoiceRepository.findByUser(userId);
	}
	
	public Collection<Invoice> findByManager(int managerId){
		return this.invoiceRepository.findByManager(managerId);
	}
	
	public void markAsPaid(Invoice invoice){
		
		final Calendar paidMoment = Calendar.getInstance();
		paidMoment.set(Calendar.MILLISECOND, -10);
		
		invoice.setPaidMoment(paidMoment.getTime());
		this.save(invoice);
		
	}
	
}

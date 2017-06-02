
package services;

import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import domain.Book;
import domain.Invoice;

@Service
@Transactional
public class InvoiceService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private InvoiceRepository	invoiceRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private BookService			bookService;


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

	public void generateInvoices() {

		final Collection<Book> books = this.bookService.findNotCancelledWithoutInvoices();

		for (final Book b : books) {
			final Invoice invoice = this.create(b);
			this.save(invoice);
		}
	}

	public Collection<Invoice> findByUser(final int userId) {
		return this.invoiceRepository.findByUser(userId);
	}

	public Collection<Invoice> findByManager(final int managerId) {
		return this.invoiceRepository.findByManager(managerId);
	}

	public Invoice findByBook(final int bookId) {
		Invoice result;

		result = this.invoiceRepository.findByBook(bookId);

		return result;
	}

	public void markAsPaid(final Invoice invoice) {

		final Calendar paidMoment = Calendar.getInstance();
		paidMoment.set(Calendar.MILLISECOND, -10);

		invoice.setPaidMoment(paidMoment.getTime());
		this.save(invoice);

	}

}

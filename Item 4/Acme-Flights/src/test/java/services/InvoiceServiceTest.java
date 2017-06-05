
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
	private InvoiceService	invoiceService;

	@Autowired
	private BookService		bookService;


	//Use case: marcar las factura como pagadas por el gerente 
	@Test
	public void driverMarkAsPaid() {
		final Object testingData[][] = {
			{
				"manager1", 186, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testMarkAsPaid((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	//Use case: listar las facturas de un manager y las de un usuario:
	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{
				163, 145, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.testFindByUserId((int) testingData[i][0], (Class<?>) testingData[i][2]);
			this.testFindByManagerId((int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	//Use case: generar automáticamente las facturas
	@Test
	public void testGenerateInvoices() {

		final Collection<Book> booksBefore = this.bookService.findNotCancelledWithoutInvoices();

		System.out.println("Había " + booksBefore.size() + " facturas sin generar");

		this.invoiceService.generateInvoices();

		final Collection<Book> booksAfter = this.bookService.findNotCancelledWithoutInvoices();
		Assert.isTrue(booksAfter.size() == 0);

		System.out.println("Todas las facturas generadas hasta la fecha");

	}

	protected void testMarkAsPaid(final String username, final int invoiceId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Invoice invoice;

			invoice = this.invoiceService.findOne(invoiceId);
			this.invoiceService.markAsPaid(invoice);
			Assert.isTrue(invoice.getPaidMoment() != null);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testFindByUserId(final int userId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Invoice> invoices;

			invoices = this.invoiceService.findByUser(userId);
			Assert.notNull(invoices);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testFindByManagerId(final int manager, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Collection<Invoice> invoices;

			invoices = this.invoiceService.findByManager(manager);
			Assert.notNull(invoices);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}

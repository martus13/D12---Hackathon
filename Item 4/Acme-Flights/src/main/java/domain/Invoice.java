
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Invoice() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	creationMoment;
	private Date	paidMoment;
	private Double	totalFee;


	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getCreationMoment() {
		return this.creationMoment;
	}
	public void setCreationMoment(final Date creationMoment) {
		this.creationMoment = creationMoment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getPaidMoment() {
		return this.paidMoment;
	}
	public void setPaidMoment(final Date paidMoment) {
		this.paidMoment = paidMoment;
	}

	@NotNull
	@Min(0)
	public Double getTotalFee() {
		return this.totalFee;
	}
	public void setTotalFee(final Double totalFee) {
		this.totalFee = totalFee;
	}


	// Relationships ----------------------------------------------------------

	private Book	book;


	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Book getBook() {
		return this.book;
	}
	public void setBook(final Book book) {
		this.book = book;
	}

}

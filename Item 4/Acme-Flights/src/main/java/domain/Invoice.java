package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity{

	
	// Constructors -----------------------------------------------------------
	public Invoice() {
		super();
	}


	// Attributes -------------------------------------------------------------
	
	private Date creationMoment;
	private Date paidMoment;
	private Double totalFee;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	
	public Date getPaidMoment() {
		return paidMoment;
	}
	public void setPaidMoment(Date paidMoment) {
		this.paidMoment = paidMoment;
	}
	
	@NotNull
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	
	
	
	// Relationships ----------------------------------------------------------
	
	private Book book;

	@NotNull
	@Valid
	@OneToOne(optional=false)
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	
}

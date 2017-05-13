
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Book extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Book() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	creationMoment;
	private Integer	passengersNumber;
	private Integer	childrenNumber;
	private Boolean	isBusiness;
	private String	comment;
	private Date	cancelationMoment;
	private Integer	usedPoints;


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

	@NotNull
	@Min(1)
	public Integer getPassengersNumber() {
		return this.passengersNumber;
	}

	public void setPassengersNumber(final Integer passengersNumber) {
		this.passengersNumber = passengersNumber;
	}

	@NotNull
	@Min(0)
	public Integer getChildrenNumber() {
		return this.childrenNumber;
	}

	public void setChildrenNumber(final Integer childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	@NotNull
	public Boolean getIsBusiness() {
		return this.isBusiness;
	}

	public void setIsBusiness(final Boolean isBusiness) {
		this.isBusiness = isBusiness;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getCancelationMoment() {
		return this.cancelationMoment;
	}

	public void setCancelationMoment(final Date cancelationMoment) {
		this.cancelationMoment = cancelationMoment;
	}

	@NotNull
	@Min(0)
	public Integer getUsedPoints() {
		return this.usedPoints;
	}

	public void setUsedPoints(final Integer usedPoints) {
		this.usedPoints = usedPoints;
	}


	// Relationships ----------------------------------------------------------

	private User				user;
	private Collection<Flight>	flights;
	private Offer				offer;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@NotEmpty
	@Valid
	@ManyToMany()
	public Collection<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(final Collection<Flight> flights) {
		this.flights = flights;
	}

	@Valid
	@ManyToOne(optional = true)
	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(final Offer offer) {
		this.offer = offer;
	}

}

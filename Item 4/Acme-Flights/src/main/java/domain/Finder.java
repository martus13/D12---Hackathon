
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Finder extends DomainEntity {

	//Constructor-------------------------------

	public Finder() {
		super();
	}


	//Attributes-----------------------------------

	private Date	departureDate;
	private Date	returnDate;
	private Boolean	isBusiness;
	private Integer	passengersNumber;
	private Integer	childrenNumber;
	private Boolean	returnFlight;
	private Date	updatedMoment;


	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDepartureDate() {
		return this.departureDate;
	}

	public void setDepartureDate(final Date departureDate) {
		this.departureDate = departureDate;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(final Date returnDate) {
		this.returnDate = returnDate;
	}

	public Boolean getIsBusiness() {
		return this.isBusiness;
	}

	public void setIsBusiness(final Boolean isBusiness) {
		this.isBusiness = isBusiness;
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
	public Boolean getReturnFlight() {
		return this.returnFlight;
	}

	public void setReturnFlight(final Boolean returnFlight) {
		this.returnFlight = returnFlight;
	}

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getUpdatedMoment() {
		return this.updatedMoment;
	}

	public void setUpdatedMoment(final Date updatedMoment) {
		this.updatedMoment = updatedMoment;
	}


	// Relationships ----------------------------------------------------------
	private Airport				departure;
	private Airport				destination;
	private User				user;
	private Collection<Flight>	departureResults;
	private Collection<Flight>	destinationResults;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Airport getDeparture() {
		return this.departure;
	}

	public void setDeparture(final Airport departure) {
		this.departure = departure;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Airport getDestination() {
		return this.destination;
	}

	public void setDestination(final Airport destination) {
		this.destination = destination;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@NotNull
	@Valid
	@ManyToMany(mappedBy = "departureFinders")
	public Collection<Flight> getDepartureResults() {
		return this.departureResults;
	}

	public void setDepartureResults(final Collection<Flight> departureResults) {
		this.departureResults = departureResults;
	}

	@NotNull
	@Valid
	@ManyToMany()
	public Collection<Flight> getDestinationResults() {
		return this.destinationResults;
	}

	public void setDestinationResults(final Collection<Flight> destinationResults) {
		this.destinationResults = destinationResults;
	}

}

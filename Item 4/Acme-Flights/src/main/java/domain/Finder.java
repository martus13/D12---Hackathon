
package domain;

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
	private Boolean	isBusiness;
	private Integer	passengersNumber;
	private Boolean	returnFlight;
	private Integer	childrenNumber;


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getDepartureDate() {
		return this.departureDate;
	}

	public void setDepartureDate(final Date departureDate) {
		this.departureDate = departureDate;
	}

	public Boolean getIsBusiness() {
		return this.isBusiness;
	}

	public void setIsBusiness(final Boolean isBusiness) {
		this.isBusiness = isBusiness;
	}

	@Min(1)
	public Integer getPassengersNumber() {
		return this.passengersNumber;
	}

	public void setPassengersNumber(final Integer passengersNumber) {
		this.passengersNumber = passengersNumber;
	}

	@NotNull
	public Boolean getReturnFlight() {
		return this.returnFlight;
	}

	public void setReturnFlight(final Boolean returnFlight) {
		this.returnFlight = returnFlight;
	}

	@Min(0)
	public Integer getChildrenNumber() {
		return this.childrenNumber;
	}

	public void setChildrenNumber(final Integer childrenNumber) {
		this.childrenNumber = childrenNumber;
	}


	// Relationships ----------------------------------------------------------
	private Airline	airline;
	private Airport	departure;
	private Airport	destination;
	private User	user;


	@Valid
	@ManyToMany()
	public Airline getAirline() {
		return this.airline;
	}

	public void setAirline(final Airline airline) {
		this.airline = airline;
	}

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
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}

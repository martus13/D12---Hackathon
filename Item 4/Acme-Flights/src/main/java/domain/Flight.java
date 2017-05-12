
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Flight extends Offertable {

	// Constructors -----------------------------------------------------------
	public Flight() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	creationMoment;
	private Date	departureMoment;
	private Double	businessPrice;
	private Double	economyPrice;
	public Integer	businessSeats;
	public Integer	economySeats;
	public Integer	availableBusinessSeats;
	public Integer	availableEconomySeats;
	public Boolean	cancelled;


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
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDepartureMoment() {
		return this.departureMoment;
	}

	public void setDepartureMoment(final Date departureMoment) {
		this.departureMoment = departureMoment;
	}

	@NotNull
	@Min(0)
	public Double getBusinessPrice() {
		return this.businessPrice;
	}

	public void setBusinessPrice(final Double businessPrice) {
		this.businessPrice = businessPrice;
	}

	@NotNull
	@Min(0)
	public Double getEconomyPrice() {
		return this.economyPrice;
	}

	public void setEconomyPrice(final Double economyPrice) {
		this.economyPrice = economyPrice;
	}

	@NotNull
	@Min(0)
	public Integer getBusinessSeats() {
		return this.businessSeats;
	}

	public void setBusinessSeats(final Integer businessSeats) {
		this.businessSeats = businessSeats;
	}

	@NotNull
	@Min(0)
	public Integer getEconomySeats() {
		return this.economySeats;
	}

	public void setEconomySeats(final Integer economySeats) {
		this.economySeats = economySeats;
	}

	@NotNull
	@Min(0)
	public Integer getAvailableBusinessSeats() {
		return this.availableBusinessSeats;
	}

	public void setAvailableBusinessSeats(final Integer availableBusinessSeats) {
		this.availableBusinessSeats = availableBusinessSeats;
	}

	@NotNull
	@Min(0)
	public Integer getAvailableEconomySeats() {
		return this.availableEconomySeats;
	}

	public void setAvailableEconomySeats(final Integer availableEconomySeats) {
		this.availableEconomySeats = availableEconomySeats;
	}

	@NotNull
	public Boolean getCancelled() {
		return this.cancelled;
	}

	public void setCancelled(final Boolean cancelled) {
		this.cancelled = cancelled;
	}


	// Relationships ----------------------------------------------------------

	private Airline	airline;
	private Airport	departure;
	private Airport	destination;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
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

}

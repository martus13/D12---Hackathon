
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class Rating {

	// Constructors -----------------------------------------------------------
	public Rating() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Integer	flight;
	private Integer	airline;
	private Integer	service;
	private Integer	comfort;


	@NotNull
	@Range(min = 0, max = 5)
	public Integer getFlight() {
		return this.flight;
	}

	public void setFlight(final Integer flight) {
		this.flight = flight;
	}

	@NotNull
	@Range(min = 0, max = 5)
	public Integer getAirline() {
		return this.airline;
	}

	public void setAirline(final Integer airline) {
		this.airline = airline;
	}

	@NotNull
	@Range(min = 0, max = 5)
	public Integer getService() {
		return this.service;
	}

	public void setService(final Integer service) {
		this.service = service;
	}

	@NotNull
	@Range(min = 0, max = 5)
	public Integer getComfort() {
		return this.comfort;
	}

	public void setComfort(final Integer comfort) {
		this.comfort = comfort;
	}

	// toString ---------------------------------------------------------------

	@Override
	public String toString() {
		return "Rating [flight=" + this.flight + ", airline=" + this.airline + ", service=" + this.service + ", comfort=" + this.comfort + "]";
	}

}

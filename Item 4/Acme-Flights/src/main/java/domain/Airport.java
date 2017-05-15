
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Airport extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Airport() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	country;
	private String	state;
	private String	province;
	private String	city;
	private String	airportName;
	private String	iataCode;
	private Double	rate;


	@NotBlank
	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(final String province) {
		this.province = province;
	}

	@NotBlank
	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@NotBlank
	public String getAirportName() {
		return this.airportName;
	}

	public void setAirportName(final String airportName) {
		this.airportName = airportName;
	}

	@NotBlank
	@Size(max = 3)
	public String getIataCode() {
		return this.iataCode;
	}

	public void setIataCode(final String iataCode) {
		this.iataCode = iataCode;
	}

	@NotNull
	public Double getRate() {
		return this.rate;
	}

	public void setRate(final Double rate) {
		this.rate = rate;
	}

	// Relationships ----------------------------------------------------------

}

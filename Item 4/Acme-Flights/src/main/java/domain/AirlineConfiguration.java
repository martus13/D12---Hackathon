
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class AirlineConfiguration extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public AirlineConfiguration() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Integer	maxCancellationDays;
	private Integer	maxChildrenAge;
	private Double	childrenDiscount;
	private Double	maxBagWeight;
	private Double	overweightBagPrice;


	@NotNull
	@Min(1)
	public Integer getMaxCancellationDays() {
		return this.maxCancellationDays;
	}
	public void setMaxCancellationDays(final Integer maxCancellationDays) {
		this.maxCancellationDays = maxCancellationDays;
	}

	@NotNull
	@Min(1)
	public Integer getMaxChildrenAge() {
		return this.maxChildrenAge;
	}
	public void setMaxChildrenAge(final Integer maxChildrenAge) {
		this.maxChildrenAge = maxChildrenAge;
	}

	@NotNull
	@Min(0)
	public Double getChildrenDiscount() {
		return this.childrenDiscount;
	}
	public void setChildrenDiscount(final Double childrenDiscount) {
		this.childrenDiscount = childrenDiscount;
	}

	@NotNull
	@Min(0)
	public Double getMaxBagWeight() {
		return this.maxBagWeight;
	}
	public void setMaxBagWeight(final Double maxBagWeight) {
		this.maxBagWeight = maxBagWeight;
	}

	@NotNull
	@Min(0)
	public Double getOverweightBagPrice() {
		return this.overweightBagPrice;
	}
	public void setOverweightBagPrice(final Double overweightBagPrice) {
		this.overweightBagPrice = overweightBagPrice;
	}


	// Relationships ----------------------------------------------------------

	private Airline	airline;


	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Airline getAirline() {
		return this.airline;
	}

	public void setAirline(final Airline airline) {
		this.airline = airline;
	}

}

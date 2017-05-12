
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class ExchangeRate extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public ExchangeRate() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	currency;
	private String	description;
	private Double	value1EUR;


	@NotBlank
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Min(0)
	public Double getValue1EUR() {
		return this.value1EUR;
	}

	public void setValue1EUR(final Double value1eur) {
		this.value1EUR = value1eur;
	}

	// Relationships ----------------------------------------------------------

}

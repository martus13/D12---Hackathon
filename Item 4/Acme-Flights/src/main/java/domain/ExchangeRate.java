
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class ExchangeRate extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public ExchangeRate() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	isoCode;
	private String	currency;
	private Double	value1EUR;


	@NotBlank
	@Column(unique = true)
	@Size(max = 3)
	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(final String isoCode) {
		this.isoCode = isoCode;
	}

	@NotBlank
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
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

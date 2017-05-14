
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Offer extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Offer() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	startMoment;
	private Date	endMoment;
	private Double	discount;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getStartMoment() {
		return this.startMoment;
	}

	public void setStartMoment(final Date startMoment) {
		this.startMoment = startMoment;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getEndMoment() {
		return this.endMoment;
	}

	public void setEndMoment(final Date endMoment) {
		this.endMoment = endMoment;
	}

	@Min(0)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(final Double discount) {
		this.discount = discount;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Offertable>	offertables;


	@NotNull
	@Valid
	@ManyToMany()
	public Collection<Offertable> getOffertables() {
		return this.offertables;
	}

	public void setOffertables(final Collection<Offertable> offertables) {
		this.offertables = offertables;
	}

}

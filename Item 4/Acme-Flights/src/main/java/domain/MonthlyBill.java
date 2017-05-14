
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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class MonthlyBill extends DomainEntity {

	//Constructor-------------------------------

	public MonthlyBill() {
		super();
	}


	//Attributes-----------------------------------

	private Date	creationMoment;
	private Date	paidMoment;
	private String	description;
	private Double	totalFee;


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

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getPaidMoment() {
		return this.paidMoment;
	}

	public void setPaidMoment(final Date paidMoment) {
		this.paidMoment = paidMoment;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	@Min(0)
	public Double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(final Double totalFee) {
		this.totalFee = totalFee;
	}


	// Relationships ----------------------------------------------------------

	private Airline		airline;
	private Campaign	campaign;


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
	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

}

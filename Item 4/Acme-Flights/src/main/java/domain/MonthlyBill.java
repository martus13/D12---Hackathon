
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "paidMoment"), @Index(columnList = "creationMoment")
})
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

	@NotNull
	@Min(0)
	public Double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(final Double totalFee) {
		this.totalFee = totalFee;
	}


	// Relationships ----------------------------------------------------------

	private Airline					airline;
	private Collection<Campaign>	campaigns;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Airline getAirline() {
		return this.airline;
	}

	public void setAirline(final Airline airline) {
		this.airline = airline;
	}

	@NotEmpty
	@Valid
	@ManyToMany()
	public Collection<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(final Collection<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

}

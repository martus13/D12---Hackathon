
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "inactive"), @Index(columnList = "startMonth"), @Index(columnList = "startDay"), @Index(columnList = "endMonth"), @Index(columnList = "endDay"), @Index(columnList = "type")
})
public class Season extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Season() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	title;
	private Integer	startDay;
	private Integer	startMonth;
	private Integer	endDay;
	private Integer	endMonth;
	private String	type;
	private Double	pricePercentage;
	private Boolean	inactive;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Range(min = 1, max = 31)
	public Integer getStartDay() {
		return this.startDay;
	}

	public void setStartDay(final Integer startDay) {
		this.startDay = startDay;
	}

	@NotNull
	@Range(min = 1, max = 12)
	public Integer getStartMonth() {
		return this.startMonth;
	}

	public void setStartMonth(final Integer startMonth) {
		this.startMonth = startMonth;
	}

	@NotNull
	@Range(min = 1, max = 31)
	public Integer getEndDay() {
		return this.endDay;
	}

	public void setEndDay(final Integer endDay) {
		this.endDay = endDay;
	}

	@NotNull
	@Range(min = 1, max = 12)
	public Integer getEndMonth() {
		return this.endMonth;
	}

	public void setEndMonth(final Integer endMonth) {
		this.endMonth = endMonth;
	}

	@NotBlank
	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@NotNull
	@Range(min = 0, max = 100)
	public Double getPricePercentage() {
		return this.pricePercentage;
	}

	public void setPricePercentage(final Double pricePercentage) {
		this.pricePercentage = pricePercentage;
	}

	@NotNull
	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(final Boolean inactive) {
		this.inactive = inactive;
	}


	// Relationships ----------------------------------------------------------

	private Airline	airline;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Airline getAirline() {
		return this.airline;
	}

	public void setAirline(final Airline airline) {
		this.airline = airline;
	}

}


package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "expirationMoment")
})
public class PointsCard extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public PointsCard() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	expirationMoment;
	private Integer	points;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getExpirationMoment() {
		return this.expirationMoment;
	}

	public void setExpirationMoment(final Date expirationMoment) {
		this.expirationMoment = expirationMoment;
	}

	@NotNull
	@Min(0)
	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(final Integer points) {
		this.points = points;
	}


	// Relationships ----------------------------------------------------------

	private User	user;
	private Airline	airline;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

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

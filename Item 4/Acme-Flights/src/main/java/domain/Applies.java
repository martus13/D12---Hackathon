
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Applies extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Applies() {
		super();
	}


	// Attributes -------------------------------------------------------------
	private Integer	usedPoints;


	@NotNull
	@Min(0)
	public Integer getUsedPoints() {
		return this.usedPoints;
	}

	public void setUsedPoints(final Integer usedPoints) {
		this.usedPoints = usedPoints;
	}


	// Relationships ----------------------------------------------------------

	private Book		book;
	private PointsCard	pointsCard;
	private Flight		flight;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(final Book book) {
		this.book = book;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public PointsCard getPointsCard() {
		return this.pointsCard;
	}

	public void setPointsCard(final PointsCard pointsCard) {
		this.pointsCard = pointsCard;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(final Flight flight) {
		this.flight = flight;
	}

}

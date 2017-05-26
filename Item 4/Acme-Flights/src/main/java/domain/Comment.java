
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Comment() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	creationMoment;
	private String	type;
	private String	commentText;
	private Rating	rating;


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

	@NotBlank
	public String getType() {
		return this.type;
	}
	public void setType(final String type) {
		this.type = type;
	}

	public String getCommentText() {
		return this.commentText;
	}
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	@Valid
	@NotNull
	public Rating getRating() {
		return this.rating;
	}
	public void setRating(final Rating rating) {
		this.rating = rating;
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

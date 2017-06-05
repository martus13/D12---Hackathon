
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "deleted")
})
public class Airline extends Offertable {

	// Constructors -----------------------------------------------------------
	public Airline() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	name;
	private String	picture;
	private String	contactPhone;
	private String	email;
	private Double	rating;
	private Boolean	deleted;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotBlank
	@Pattern(regexp = "(\\+\\d{1,3} )?(\\(\\d{1,3}\\) )?(\\w{4,})")
	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(final String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotNull
	@Min(0)
	public Double getRating() {
		return this.rating;
	}

	public void setRating(final Double rating) {
		this.rating = rating;
	}

	@NotNull
	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(final Boolean deleted) {
		this.deleted = deleted;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Season>	seasons;


	@NotNull
	@Valid
	@OneToMany(mappedBy = "airline")
	public Collection<Season> getSeasons() {
		return this.seasons;
	}

	public void setSeasons(final Collection<Season> seasons) {
		this.seasons = seasons;
	}

}

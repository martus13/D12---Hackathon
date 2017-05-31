
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "numDisplayed")
})
public class Banner extends DomainEntity {

	//Constructor-------------------------------

	public Banner() {
		super();
	}


	//Attributes-----------------------------------

	private Integer	numDisplayed;
	private String	picture;


	@NotNull
	@Min(0)
	public Integer getNumDisplayed() {
		return this.numDisplayed;
	}

	public void setNumDisplayed(final Integer numDisplayed) {
		this.numDisplayed = numDisplayed;
	}

	@NotBlank
	@URL
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(final String picture) {
		this.picture = picture;
	}


	// Relationships ----------------------------------------------------------

	private Campaign	campaign;


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

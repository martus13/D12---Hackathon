
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Configuration() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Double	fee;
	private Double	campaignFee;


	@NotNull
	@Min(0)
	public Double getFee() {
		return this.fee;
	}
	public void setFee(final Double fee) {
		this.fee = fee;
	}

	@NotNull
	@Min(0)
	public Double getCampaignFee() {
		return this.campaignFee;
	}
	public void setCampaignFee(final Double campaignFee) {
		this.campaignFee = campaignFee;
	}

	// Relationships ----------------------------------------------------------

}

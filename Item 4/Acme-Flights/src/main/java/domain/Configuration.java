
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Configuration() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	cochedTime;
	private Double	fee;
	private Double	campaingFee;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "HH:mm:ss")
	public Date getCochedTime() {
		return this.cochedTime;
	}
	public void setCochedTime(final Date cochedTime) {
		this.cochedTime = cochedTime;
	}

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
	public Double getCampaingFee() {
		return this.campaingFee;
	}
	public void setCampaingFee(final Double campaingFee) {
		this.campaingFee = campaingFee;
	}

	// Relationships ----------------------------------------------------------

}

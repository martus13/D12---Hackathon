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
public class Configuration extends DomainEntity{
	
	// Constructors -----------------------------------------------------------
	public Configuration() {
		super();
	}
	
	// Attributes -------------------------------------------------------------
	
	private Date cochedTime;
	private double fee;
	private double campaingFee;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "HH:mm:ss")
	public Date getCochedTime() {
		return cochedTime;
	}
	public void setCochedTime(Date cochedTime) {
		this.cochedTime = cochedTime;
	}
	
	@NotNull
	@Min(0)
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	@NotNull
	@Min(0)
	public double getCampaingFee() {
		return campaingFee;
	}
	public void setCampaingFee(double campaingFee) {
		this.campaingFee = campaingFee;
	}
	
	
	
	
	// Relationships ----------------------------------------------------------
	

}

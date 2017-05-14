package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


	
@Entity
@Access(AccessType.PROPERTY)
public class AirlineConfiguration extends DomainEntity{
		
	// Constructors -----------------------------------------------------------
		public AirlineConfiguration() {
			super();
		}
		
	// Attributes -------------------------------------------------------------
		
		private int maxCancellationDays;
		private int maxChildrenAge;
		private double childrenDiscount;
		private double maxBagWieght;
		private double overweightBagPrice;
		
		
		@NotNull
		@Min(1)
		public int getMaxCancellationDays() {
			return maxCancellationDays;
		}
		public void setMaxCancellationDays(int maxCancellationDays) {
			this.maxCancellationDays = maxCancellationDays;
		}
		
		@NotNull
		@Min(1)
		public int getMaxChildrenAge() {
			return maxChildrenAge;
		}
		public void setMaxChildrenAge(int maxChildrenAge) {
			this.maxChildrenAge = maxChildrenAge;
		}
		
		@NotNull
		@Min(0)
		public double getChildrenDiscount() {
			return childrenDiscount;
		}
		public void setChildrenDiscount(double childrenDiscount) {
			this.childrenDiscount = childrenDiscount;
		}
		
		@NotNull
		@Min(0)
		public double getMaxBagWieght() {
			return maxBagWieght;
		}
		public void setMaxBagWieght(double maxBagWieght) {
			this.maxBagWieght = maxBagWieght;
		}
		
		@NotNull
		@Min(0)
		public double getOverweightBagPrice() {
			return overweightBagPrice;
		}
		public void setOverweightBagPrice(double overweightBagPrice) {
			this.overweightBagPrice = overweightBagPrice;
		}
		
		
		
	// Relationships ----------------------------------------------------------
		
		private Airline controlledBy;

		@NotNull
		@Valid
		@OneToOne(optional=false)
		public Airline getControlledBy() {
			return controlledBy;
		}
		public void setControlledBy(Airline controlledBy) {
			this.controlledBy = controlledBy;
		}
		
		
}

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;


@Entity
@Access(AccessType.PROPERTY)
public class Offertable extends DomainEntity{
	
	// Constructors -----------------------------------------------------------
		public Offertable() {
			super();
		}


	// Attributes -------------------------------------------------------------
		
	// Relationships ----------------------------------------------------------

}

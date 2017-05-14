package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity{
	
	// Constructors -----------------------------------------------------------
		public Actor() {
			super();
		}


	// Attributes -------------------------------------------------------------
		private String name;
		private String surname;
		private String contactPhone;
		private String email;
		
		
		@NotBlank
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@NotBlank
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		
		@NotBlank
		@Pattern(regexp = "(\\+\\d{1,3} )?(\\(\\d{1,3}\\) )?(\\w{4,})")
		public String getContactPhone() {
			return contactPhone;
		}
		public void setContactPhone(String contactPhone) {
			this.contactPhone = contactPhone;
		}
		
		@NotBlank
		@Email
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		// Relationships ----------------------------------------------------------

		private UserAccount	userAccount;
		
		@NotNull
		@Valid
		@OneToOne(cascade = CascadeType.ALL, optional = false)
		public UserAccount getUserAccount() {
			return this.userAccount;
		}
		public void setUserAccount(final UserAccount userAccount) {
			this.userAccount = userAccount;
		}

}

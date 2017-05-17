
package forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class ManagerForm {

	private String	username;
	private String	password;
	private String	confirmPassword;
	private String	name;
	private String	surname;
	private String	email;
	private String	contactPhone;


	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@NotNull
	public String getUsername() {
		return this.username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@NotNull
	public String getPassword() {
		return this.password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}

	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@NotNull
	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@NotNull
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@NotNull
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@NotNull
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@NotNull
	@Pattern(regexp = "(\\+\\d{1,3} )?(\\(\\d{1,3}\\) )?(\\w{4,})")
	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(final String contactPhone) {
		this.contactPhone = contactPhone;
	}

}

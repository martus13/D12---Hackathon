
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import forms.UserForm;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ActorRepository	actorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Actor findOne(final int actorId) {
		Actor result;

		result = this.actorRepository.findOne(actorId);

		return result;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();

		return result;
	}

	public Actor save(Actor actor) {

		if (actor.getId() == 0) {
			String password;
			String encryptedPassword;

			password = actor.getUserAccount().getPassword();
			encryptedPassword = this.encryptPassword(password);

			actor.getUserAccount().setPassword(encryptedPassword);

		}
		actor = this.actorRepository.save(actor);

		return actor;
	}

	// Other business methods -------------------------------------------------

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccountId(final int userAccountId) {
		Assert.notNull(userAccountId);

		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Boolean checkAuthority(final Actor actor, final String authority) {
		Boolean result;
		Collection<Authority> authorities;

		authorities = actor.getUserAccount().getAuthorities();

		result = false;
		for (final Authority a : authorities)
			result = result || (a.getAuthority().equals(authority));

		return result;
	}

	public Actor reconstructProfile(final UserForm actorForm) {
		Assert.notNull(actorForm);

		Actor actor;
		String password;

		Assert.isTrue(actorForm.getPassword().equals(actorForm.getConfirmPassword())); // Comprobamos que las dos contraseñas sean la misma

		actor = this.findByPrincipal();
		password = this.encryptPassword(actorForm.getPassword());

		actor.getUserAccount().setUsername(actorForm.getUsername());
		actor.getUserAccount().setPassword(password);
		actor.setName(actorForm.getName());
		actor.setSurname(actorForm.getSurname());
		actor.setEmail(actorForm.getEmail());
		actor.setContactPhone(actorForm.getContactPhone());

		return actor;
	}

	public UserForm desreconstructProfile() {
		UserForm actorForm;
		Actor actor;

		actorForm = new UserForm();
		actor = this.findByPrincipal();

		actorForm.setUsername(actor.getUserAccount().getUsername());
		actorForm.setName(actor.getName());
		actorForm.setSurname(actor.getSurname());
		actorForm.setEmail(actor.getEmail());
		actorForm.setContactPhone(actor.getContactPhone());

		return actorForm;
	}

	public String encryptPassword(String password) {
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);

		return password;
	}
}

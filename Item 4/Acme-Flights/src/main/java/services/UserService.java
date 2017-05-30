
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.User;
import forms.ActorForm;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private UserRepository	userRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User findOne(final int userId) {
		Assert.isTrue(userId != 0);

		User result;

		result = this.userRepository.findOne(userId);

		return result;
	}

	public Collection<User> findAll() {
		Collection<User> result;

		result = this.userRepository.findAll();

		return result;
	}

	public User create() {
		User result;
		UserAccount userAccount;
		Authority authority;

		result = new User();

		userAccount = new UserAccount();
		authority = new Authority();

		authority.setAuthority("USER");
		userAccount.getAuthorities().add(authority);

		result.setUserAccount(userAccount);

		return result;
	}

	public User save(User user) {
		Assert.notNull(user);

		if (user.getId() != 0) {
			User principal;

			principal = this.findByPrincipal();
			Assert.notNull(principal);
			Assert.isTrue(user.getId() == principal.getId());

		}

		user = this.userRepository.save(user);

		return user;
	}

	// Other business methods -------------------------------------------------
	public User findByPrincipal() {
		User result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public User findByUserAccountId(final int userAccountId) {
		Assert.notNull(userAccountId);

		User result;

		result = this.userRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public User reconstructCreate(final ActorForm actorForm) {
		Assert.notNull(actorForm);

		User user;
		String password;

		Assert.isTrue(actorForm.getPassword().equals(actorForm.getConfirmPassword())); // Comprobamos que las dos contraseñas sean la misma
		Assert.isTrue(actorForm.getIsAgree());

		user = this.create();
		password = this.encryptPassword(actorForm.getPassword());

		user.getUserAccount().setUsername(actorForm.getUsername());
		user.getUserAccount().setPassword(password);
		user.setName(actorForm.getName());
		user.setSurname(actorForm.getSurname());
		user.setEmail(actorForm.getEmail());
		user.setContactPhone(actorForm.getContactPhone());

		return user;
	}

	public ActorForm desreconstructCreate(final User user) {
		ActorForm actorForm;

		actorForm = new ActorForm();

		actorForm.setUsername(user.getUserAccount().getUsername());
		actorForm.setName(user.getName());
		actorForm.setSurname(user.getSurname());
		actorForm.setEmail(user.getEmail());
		actorForm.setContactPhone(user.getContactPhone());

		return actorForm;
	}

	public String encryptPassword(String password) {
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);

		return password;
	}
}

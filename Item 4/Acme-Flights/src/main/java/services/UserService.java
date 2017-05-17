
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.User;

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
}

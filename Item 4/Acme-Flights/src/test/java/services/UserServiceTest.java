
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private UserService	userService;


	// Tests ------------------------------------------------------------------

	// A continuación se van a realizar pruebas para comprobar el correcto funcionamiento de los casos de uso relacionados con User.

	// Registro de un user:
	@Test
	public void driverCreateAndSave() {
		final Object testingData[][] = {
			{
				"prueba1", "prueba1", "prueba", "apellido", "954668789", "prueba1@gmail.com", null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{
				"user1", 163, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.testFindOne((int) testingData[i][1], (Class<?>) testingData[i][2]);
			this.testFindByPrincipal((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	protected void testCreateAndSave(final String username, final String password, final String name, final String surname, final String contactPhone, final String email, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			User user;

			user = this.userService.create();
			user.getUserAccount().setUsername(username);
			user.getUserAccount().setPassword(this.userService.encryptPassword(password));
			user.setName(name);
			user.setSurname(surname);
			user.setContactPhone(contactPhone);
			user.setEmail(email);

			user = this.userService.save(user);
			Assert.isTrue(user.getId() != 0);
			Assert.isTrue(user.getUserAccount().getPassword().equals("3f1b7ccad63d40a7b4c27dda225bf941"));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testFindOne(final int userId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			User user;

			user = this.userService.findOne(userId);
			Assert.notNull(user);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testFindByPrincipal(final String username, final int userId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			User user;

			user = this.userService.findByPrincipal();
			Assert.notNull(user);
			Assert.isTrue(user.getId() == userId);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	@Test
	public void testFindAll() {
		Collection<User> users;

		users = this.userService.findAll();
		Assert.isTrue(users.size() == 3);
	}
}

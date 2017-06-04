
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Finder;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FinderServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private FinderService	finderService;

	@Autowired
	private UserService		userService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driverSave() {
		final Object testingData[][] = {
			{	// Bien
				"user1", null, 2, 5, true
			}, {//usuario incorrecto
				"m", IllegalArgumentException.class, 2, 5, true
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testEdit((String) testingData[i][0], (Class<?>) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Boolean) testingData[i][4]);
	}
	//Finds
	@Test
	public void driverFind() {
		final Object testingData[][] = {
			{	// Bien
				"user2", null
			}, {//usuario incorrecto
				"m", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testFindByUserId((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void testEdit(final String username, final Class<?> expected, final int childrenNumber, final int passengersNumber, final boolean isBusiness) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Finder finder;

			finder = this.finderService.findByUserId(this.userService.findByPrincipal().getId());
			finder.setChildrenNumber(childrenNumber);
			finder.setPassengersNumber(passengersNumber);
			finder.setIsBusiness(isBusiness);

			Assert.notNull(finder);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testFindByUserId(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			User user;
			Finder finder;

			user = this.userService.findByPrincipal();
			finder = this.finderService.findByUserId(user.getId());
			Assert.notNull(finder);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}

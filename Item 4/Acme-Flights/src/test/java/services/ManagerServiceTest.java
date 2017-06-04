
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
import domain.Actor;
import domain.Manager;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManagerServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private ManagerService	managerService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driverFindandEdit() {
		final Object testingData[][] = {
			{
				146, "MANAGER", null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.testFindOne((int) testingData[i][0], (Class<?>) testingData[i][2]);
			this.testEdit((int) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	protected void testFindOne(final int managerId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Actor actor;

			actor = this.managerService.findOne(managerId);
			Assert.notNull(actor);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testEdit(final int managerId, final String text, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			Manager manager;

			manager = this.managerService.findOne(managerId);
			manager.setName(text);

			manager = this.managerService.save(manager);
			Assert.isTrue(manager.getName() == text);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	@Test
	public void testFindAll() {
		Collection<Manager> managers;

		managers = this.managerService.findAll();
		Assert.isTrue(managers.size() == 3);
	}
}

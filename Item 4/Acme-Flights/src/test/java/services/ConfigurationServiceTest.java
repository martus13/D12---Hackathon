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
import domain.Airline;
import domain.Configuration;
import domain.Manager;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
public class ConfigurationServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private ConfigurationService configurationService;
	
	// Tests ------------------------------------------------------------------

		@Test
		public void driverFindAndEdit() {
			final Object testingData[][] = {
				{	// Bien
					105, "admin",2.0, 3.5, null
				}, {//id erroneo 
					104, "admin",2.0, 3.5, IllegalArgumentException.class
				}, {//admin no registrado
					105, null ,2.0, 3.5, IllegalArgumentException.class
				}
			};
			for (int i = 0; i < testingData.length; i++){
				
				this.testFindOneAndEdit((int) testingData[i][0], (String) testingData[i][1], (double)testingData[i][2],(double)testingData[i][3],(Class<?>) testingData[i][4]);
			}
				
		}

		protected void testFindOneAndEdit(final int configurationId, final String admin, final Double fee, final Double campaignFee, final Class<?> expected) {
			Class<?> caught;

			caught = null;
			try {
				
				this.authenticate(admin);
				Configuration configuration;

				configuration = this.configurationService.findOne(configurationId);
				Assert.notNull(configuration);
				
				configuration.setCampaignFee(campaignFee);
				configuration.setFee(fee);
				
				this.configurationService.save(configuration);
				Assert.isTrue(configuration.getFee() == fee);
				Assert.isTrue(configuration.getCampaignFee() == campaignFee);
				
				this.unauthenticate();
			} catch (final Throwable oops) {
				caught = oops.getClass();
			}

			this.checkExceptions(expected, caught);

		}
			
		@Test
		public void testConfiguration() {
			
			Configuration configuration = this.configurationService.findConfiguration();
			Assert.notNull(configuration);
			Assert.isTrue(configuration.getId()==105);
		}

}

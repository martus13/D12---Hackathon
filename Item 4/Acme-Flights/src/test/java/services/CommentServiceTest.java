
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
import domain.Comment;
import domain.Rating;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private CommentService	commentService;

	@Autowired
	private AirlineService	airlineService;


	// Tests ------------------------------------------------------------------

	//Use case: Crear un nuevo comentario sobre una aerolínea
	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			{	// Bien
				117, "user2", "Neutral", "Esto es un test", 3, 3, 3, 3, null
			}, {//id erroneo 
				112, "user1", "Neutral", "Esto es un test", 3, 3, 3, 3, IllegalArgumentException.class
			}, {//user con un comentario previo
				117, "user3", "Neutral", "Esto es un test", 3, 3, 3, 3, IllegalArgumentException.class
			}, {//Valores erróneos
				117, "user1", "positivo", "Esto es un test", 3, 3, 3, 3, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.testCreateAndSave((int) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (int) testingData[i][4], (int) testingData[i][5], (int) testingData[i][6], (int) testingData[i][7],
				(Class<?>) testingData[i][8]);

	}

	//Use case: Editar un comentario escrito por el usuario registrado

	@Test
	public void driverEdit() {
		final Object testingData[][] = {
			{	// Bien
				188, "user2", "Neutral", "Esto es un test", 3, 3, 3, 3, null
			}, {//id erroneo 
				112, "user1", "Neutral", "Esto es un test", 3, 3, 3, 3, IllegalArgumentException.class
			}, {//user autor de otro comentario
				117, "user3", "Neutral", "Esto es un test", 3, 3, 3, 3, IllegalArgumentException.class
			}, {//Valores erróneos
				188, "user2", "positivo", "Esto es un test", 3, 3, 3, 3, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.testFindOneAndSave((int) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (int) testingData[i][4], (int) testingData[i][5], (int) testingData[i][6], (int) testingData[i][7],
				(Class<?>) testingData[i][8]);

	}

	//Use case: eliminar un comentario escrito por el usuario registrado
	@Test
	public void driverDelete() {
		final Object testingData[][] = {
			{	// Bien
				188, "user2", null
			}, {//id erroneo 
				145, "user1", IllegalArgumentException.class
			}, {//user autor de otro comentario
				188, "user3", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.testDelete((int) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	//Use case: Listar los comentarios escritos sobre una aerolínea
	@Test
	public void testfindByAirlineId() {

		final Collection<Comment> comments = this.commentService.findByAirlineId(118);
		Assert.notNull(comments);

		Assert.isTrue(comments.size() == 2);
		System.out.println("Encontrados los dos comentarios");
	}
	protected void testCreateAndSave(final int airlineId, final String user, final String type, final String commentText, final int airlineRating, final int comfort, final int service, final int flight, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			this.authenticate(user);
			Airline airline;
			Comment comment;
			Rating rating;

			airline = this.airlineService.findOne(airlineId);
			Assert.notNull(airline);

			comment = this.commentService.create(airline);
			Assert.notNull(comment);
			Assert.isTrue(comment.getAirline().equals(airline));
			Assert.isTrue(comment.getUser().getUserAccount().getUsername().equals(user));

			rating = new Rating();
			rating.setAirline(airlineRating);
			rating.setComfort(comfort);
			rating.setFlight(flight);
			rating.setService(service);

			comment.setType(type);
			comment.setCommentText(commentText);
			comment.setRating(rating);

			this.commentService.save(comment);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testFindOneAndSave(final int commentId, final String user, final String type, final String commentText, final int airlineRating, final int comfort, final int service, final int flight, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			this.authenticate(user);

			Comment comment;
			Rating rating;

			comment = this.commentService.findOne(commentId);
			Assert.notNull(comment);
			Assert.isTrue(comment.getUser().getUserAccount().getUsername().equals(user));

			rating = comment.getRating();
			rating.setAirline(airlineRating);
			rating.setComfort(comfort);
			rating.setFlight(flight);
			rating.setService(service);

			comment.setType(type);
			comment.setCommentText(commentText);
			comment.setRating(rating);

			this.commentService.save(comment);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void testDelete(final int commentId, final String user, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			this.authenticate(user);

			Comment comment;

			comment = this.commentService.findOne(commentId);
			Assert.notNull(comment);
			Assert.isTrue(comment.getUser().getUserAccount().getUsername().equals(user));

			this.commentService.delete(comment);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}

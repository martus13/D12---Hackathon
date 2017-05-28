
package services;

import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Airline;
import domain.Comment;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService			userService;

	@Autowired
	private AirlineService		airlineService;


	// Constructors -----------------------------------------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comment findOne(final int commentId) {
		return this.commentRepository.findOne(commentId);
	}

	public Collection<Comment> findAll() {
		return this.commentRepository.findAll();
	}

	public Comment create(final Airline airline) {
		Assert.notNull(airline);

		Comment result;
		User user;
		Calendar creationMoment;

		result = new Comment();

		user = this.userService.findByPrincipal();
		Assert.notNull(user);

		creationMoment = Calendar.getInstance();
		creationMoment.set(Calendar.MILLISECOND, -10);

		result.setCreationMoment(creationMoment.getTime());
		result.setAirline(airline);
		result.setUser(user);

		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);

		Assert.isTrue(comment.getType().equals("Neutral") || comment.getType().equals("Positive") || comment.getType().equals("Negative"));

		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(principal.equals(comment.getUser()));

		this.commentRepository.save(comment);

		//Recalcular el rating de la aerolína afectada
		final Airline airline = comment.getAirline();
		Double rating;

		rating = this.findRatingCommetsByAirlineId(airline.getId());

		rating = Math.round(rating * 100.0) / 100.0;

		airline.setRating(rating);
		this.airlineService.save(airline);

		return comment;
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);

		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(principal.equals(comment.getUser()));

		this.commentRepository.delete(comment);
	}

	//Other business methods-----------------------------------------

	public Collection<Comment> findByAirlineId(final int airlineId) {

		return this.commentRepository.findByAirline(airlineId);
	}

	public Double findRatingCommetsByAirlineId(final int airlineId) {
		return this.commentRepository.findRatingCommetsByAirlineId(airlineId);
	}
}

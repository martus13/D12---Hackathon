
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
import domain.Flight;
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
	
	@Autowired
	private FlightService		flightService;


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
		Assert.isTrue(!this.hasCommented(user.getId(), airline.getId()));
		
		creationMoment = Calendar.getInstance();
		creationMoment.set(Calendar.MILLISECOND, -10);

		result.setCreationMoment(creationMoment.getTime());
		result.setAirline(airline);
		result.setUser(user);
		
		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);

		Assert.isTrue(this.hasFlight(comment.getUser().getId(), comment.getAirline().getId()));
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

	
	public Collection<Comment> findByUserAndAirline(int userId, int airlineId){
		return this.commentRepository.findByUserAndAirline(userId, airlineId);
	}
	
	public Boolean hasCommented(int userId, int airlineId){
		Collection<Comment> comentarios = this.commentRepository.findByUserAndAirline(userId, airlineId);
		Boolean res=true;
		
		if(comentarios.isEmpty()){
			res=false;
		}
		return res;
	}
	
	public Boolean hasFlight(int userId, int airlineId){
		Collection<Flight> vuelos = this.flightService.findByUserAndAirline(userId, airlineId);
		Boolean res=true;
		
		if(vuelos.isEmpty()){
			res=false;
		}
		return res;
	}
	
	public Double findRatingCommetsByAirlineId(final int airlineId) {
		return this.commentRepository.findRatingCommetsByAirlineId(airlineId);
	}

}

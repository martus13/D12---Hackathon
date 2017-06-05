
package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PointsCardRepository;
import domain.Airline;
import domain.Manager;
import domain.PointsCard;
import domain.User;

@Service
@Transactional
public class PointsCardService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private PointsCardRepository	pointsCardRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ManagerService			managerService;


	// Constructors -----------------------------------------------------------
	public PointsCardService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public PointsCard findOne(final int pointsCardId) {
		Assert.isTrue(pointsCardId != 0);
		PointsCard result;

		result = this.pointsCardRepository.findOne(pointsCardId);

		return result;
	}

	public Collection<PointsCard> findAll() {
		Collection<PointsCard> result;

		result = this.pointsCardRepository.findAll();

		return result;
	}

	public PointsCard create(final User user, final Airline airline) {
		PointsCard result;
		PointsCard overlappingPointsCard;
		Calendar calendar;

		overlappingPointsCard = this.findByUserAndAirlineId(user.getId(), airline.getId());
		Assert.isNull(overlappingPointsCard);

		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, +1);

		result = new PointsCard();
		result.setUser(user);
		result.setAirline(airline);
		result.setPoints(0);
		result.setExpirationMoment(calendar.getTime());

		return result;
	}

	public PointsCard save(PointsCard pointsCard) {
		Assert.notNull(pointsCard);

		pointsCard = this.pointsCardRepository.save(pointsCard);

		return pointsCard;
	}

	public void delete(final PointsCard pointsCard) {
		this.pointsCardRepository.delete(pointsCard);

	}

	public void resetExpired() {
		Collection<PointsCard> pointsCards;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);

		pointsCards = this.findExpiresByAirlineId(manager.getAirline().getId());

		for (final PointsCard p : pointsCards) {
			p.setPoints(0);
			this.save(p);
		}
	}
	// Other business methods -------------------------------------------------

	public Collection<PointsCard> findByUserId(final int userId) {
		Collection<PointsCard> result;

		result = this.pointsCardRepository.findByUserId(userId);

		return result;
	}

	public Collection<PointsCard> findByAirlineId(final int airlineId) {
		Collection<PointsCard> result;

		result = this.pointsCardRepository.findByAirlineId(airlineId);

		return result;
	}

	public PointsCard findByUserAndAirlineId(final int userId, final int airlineId) {
		PointsCard result;

		result = this.pointsCardRepository.findByUserAndAirlineId(userId, airlineId);

		return result;
	}

	public Collection<PointsCard> findExpiresByAirlineId(final int airlineId) {
		Collection<PointsCard> result;

		result = this.pointsCardRepository.findExpiresByAirlineId(airlineId);

		return result;
	}

}

package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Reservation;
import traveler.repository.ReservationRepository;

@Repository
public class ReservationRepositoryImpl extends GenericRepositoryImpl<Reservation, Long> implements ReservationRepository {

	@Override
	protected Class<Reservation> getEntityClass() {
		return Reservation.class;
	}

}

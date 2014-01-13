package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Hotel;
import traveler.repository.HotelRepository;

@Repository
public class HotelRepositoryImpl extends GenericRepositoryImpl<Hotel, Long> implements HotelRepository {

	@Override
	protected Class<Hotel> getEntityClass() {
		return Hotel.class;
	}

}

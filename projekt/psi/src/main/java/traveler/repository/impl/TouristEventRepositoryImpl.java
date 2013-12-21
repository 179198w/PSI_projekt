package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.TouristEvent;
import traveler.repository.TouristEventRepository;

@Repository
public class TouristEventRepositoryImpl extends GenericRepositoryImpl<TouristEvent, Long> implements TouristEventRepository {

	@Override
	protected Class<TouristEvent> getEntityClass() {
		return TouristEvent.class;
	}

}

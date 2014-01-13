package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.TouristEventComponent;
import traveler.repository.TouristEventComponentRepository;

@Repository
public class TouristEventComponentRepositoryImpl extends GenericRepositoryImpl<TouristEventComponent, Long> implements TouristEventComponentRepository {

	@Override
	protected Class<TouristEventComponent> getEntityClass() {
		return TouristEventComponent.class;
	}

}

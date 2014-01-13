package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.City;
import traveler.repository.CityRepository;

@Repository
public class CityRepositoryImpl extends GenericRepositoryImpl<City, Long> implements CityRepository {

	@Override
	protected Class<City> getEntityClass() {
		return City.class;
	}

}

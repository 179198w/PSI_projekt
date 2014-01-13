package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Country;
import traveler.repository.CountryRepository;

@Repository
public class CountryRepositoryImpl extends GenericRepositoryImpl<Country, Long> implements CountryRepository {

	@Override
	protected Class<Country> getEntityClass() {
		return Country.class;
	}

}

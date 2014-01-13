package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Period;
import traveler.repository.PeriodRepository;

@Repository
public class PeriodRepositoryImpl extends GenericRepositoryImpl<Period, Long> implements PeriodRepository {

	@Override
	protected Class<Period> getEntityClass() {
		return Period.class;
	}

}

package traveler.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import traveler.controller.command.PeriodFilterCommand;
import traveler.model.Period;
import traveler.repository.PeriodRepository;

@Repository
public class PeriodRepositoryImpl extends GenericRepositoryImpl<Period, Long> implements PeriodRepository {

	@Override
	protected Class<Period> getEntityClass() {
		return Period.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Period> getFiltered(PeriodFilterCommand filterCommand) {
		Criteria criteria = session().createCriteria(getEntityClass());
		if (filterCommand.getFrom() != null) {
			criteria.add(Restrictions.ge("from", filterCommand.getFrom()));
		}
		if (filterCommand.getTo() != null) {
			criteria.add(Restrictions.le("to", filterCommand.getTo()));
		}
		return criteria.list();
	}

}

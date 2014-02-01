package traveler.repository.impl;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import traveler.controller.command.TouristEventComponentFilterCommand;
import traveler.model.TouristEventComponent;
import traveler.repository.TouristEventComponentRepository;

@Repository
public class TouristEventComponentRepositoryImpl extends GenericRepositoryImpl<TouristEventComponent, Long> implements TouristEventComponentRepository {

	@Override
	protected Class<TouristEventComponent> getEntityClass() {
		return TouristEventComponent.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TouristEventComponent> getFiltered(TouristEventComponentFilterCommand filterCommand) {
		Criteria criteria = session().createCriteria(getEntityClass());
		if (!isNullOrEmpty(filterCommand.getName())) {
			criteria.add(Restrictions.ilike("name", filterCommand.getName(), MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

}

package traveler.repository.impl;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import traveler.controller.command.TouristEventFilterCommand;
import traveler.model.TouristEvent;
import traveler.repository.TouristEventRepository;

@Repository
public class TouristEventRepositoryImpl extends GenericRepositoryImpl<TouristEvent, Long> implements TouristEventRepository {

	@Override
	protected Class<TouristEvent> getEntityClass() {
		return TouristEvent.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TouristEvent> getAllWithRelatedData(TouristEventFilterCommand touristEventFilterCommand) {
		Criteria criteria = session().createCriteria(getEntityClass());
		criteria.createAlias("hotel", "hotel", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("hotel.city", "hotelCity", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("operator", "operator", JoinType.LEFT_OUTER_JOIN);
		
		if (!isNullOrEmpty(touristEventFilterCommand.getName())) {
			criteria.add(Restrictions.ilike("name", touristEventFilterCommand.getName(), MatchMode.ANYWHERE));
		}
		
		return criteria.list();
	}

}

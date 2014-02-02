package traveler.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import traveler.command.PriceFilterCommand;
import traveler.model.Price;
import traveler.repository.PriceRepository;

@Repository
public class PriceRepositoryImpl extends GenericRepositoryImpl<Price, Long> implements PriceRepository {

	@Override
	protected Class<Price> getEntityClass() {
		return Price.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Price> getFiltered(PriceFilterCommand filterCommand) {
		Criteria criteria = session().createCriteria(getEntityClass());
		criteria.createAlias("period", "period");
		criteria.createAlias("touristEventComponent", "touristEventComponent");
		if (filterCommand.getType() != null) {
			criteria.add(Restrictions.eq("type", filterCommand.getType()));
		}
		return criteria.list();
	}

}

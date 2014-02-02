package traveler.repository.impl;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import traveler.command.CatalogFilterCommand;
import traveler.model.Catalog;
import traveler.model.TouristEvent;
import traveler.repository.CatalogRepository;

@Repository
public class CatalogRepositoryImpl extends GenericRepositoryImpl<Catalog, Long> implements CatalogRepository {

	@Override
	protected Class<Catalog> getEntityClass() {
		return Catalog.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalog> listFiltered(CatalogFilterCommand filterCommand) {
		Criteria criteria = session().createCriteria(getEntityClass());
		if (!isNullOrEmpty(filterCommand.getName())) {
			criteria.add(Restrictions.ilike("name", filterCommand.getName(), MatchMode.ANYWHERE));
		}
		return criteria.list();
	}
	
}

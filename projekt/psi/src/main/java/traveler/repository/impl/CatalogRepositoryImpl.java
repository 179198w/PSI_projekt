package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Catalog;
import traveler.repository.CatalogRepository;

@Repository
public class CatalogRepositoryImpl extends GenericRepositoryImpl<Catalog, Long> implements CatalogRepository {

	@Override
	protected Class<Catalog> getEntityClass() {
		return Catalog.class;
	}

}

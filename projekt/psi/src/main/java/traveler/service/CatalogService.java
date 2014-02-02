package traveler.service;

import java.util.List;

import traveler.command.CatalogCommand;
import traveler.command.CatalogFilterCommand;
import traveler.model.Catalog;
import traveler.model.TouristEvent;


public interface CatalogService {

	List<Catalog> listCatalogs();

	void addCatalog(CatalogCommand catalogCommand);

	List<Catalog> listCatalogs(CatalogFilterCommand filterCommand);

	CatalogCommand getCatalogCommand(Long catalogId);

	void updateCatalog(CatalogCommand catalogCommand);
	
	void removeCatalog(Long catalogId);
}

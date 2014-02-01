package traveler.service;

import java.util.List;

import traveler.command.CatalogCommand;
import traveler.command.CatalogFilterCommand;
import traveler.model.Catalog;


public interface CatalogService {

	List<Catalog> listCatalogs();

	void addCatalog(CatalogCommand catalogCommand);

	List<Catalog> listCatalogs(CatalogFilterCommand filterCommand);

}

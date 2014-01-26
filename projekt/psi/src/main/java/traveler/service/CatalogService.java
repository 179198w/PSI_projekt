package traveler.service;

import java.util.List;

import traveler.controller.command.CatalogCommand;
import traveler.model.Catalog;


public interface CatalogService {

	List<Catalog> listCatalogs();

	void addCatalog(CatalogCommand catalogCommand);

}

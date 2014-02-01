package traveler.repository;

import java.util.List;

import traveler.command.CatalogFilterCommand;
import traveler.model.Catalog;

public interface CatalogRepository extends GenericRepository<Catalog, Long> {

	List<Catalog> listFiltered(CatalogFilterCommand filterCommand);

}

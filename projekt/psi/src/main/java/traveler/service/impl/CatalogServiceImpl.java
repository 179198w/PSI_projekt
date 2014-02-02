package traveler.service.impl;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import traveler.command.CatalogCommand;
import traveler.command.CatalogFilterCommand;
import traveler.model.Catalog;
import traveler.model.TouristEvent;
import traveler.repository.CatalogRepository;
import traveler.repository.TouristEventRepository;
import traveler.service.CatalogService;
import traveler.utils.MapperFacadeFactoryBean;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Inject
	private CatalogRepository catalogRepository;

	@Inject
	private MapperFacadeFactoryBean mapperFacade;
	
	@Inject
	private TouristEventRepository touristEventRepository;
	
	@Override
	public List<Catalog> listCatalogs() {
		return catalogRepository.getAll();
	}
	
	@Override
	public void addCatalog(CatalogCommand catalogCommand) {
		Catalog catalog = mapperFacade.getObject().map(catalogCommand, Catalog.class);
		
		List<TouristEvent> touristEvents = newArrayList();
		for (Long touristEventId : catalogCommand.getTouristEventIds()) {
			touristEvents.add(touristEventRepository.get(touristEventId));
		}
		catalog.setTouristEvents(touristEvents);
		catalogRepository.save(catalog);
	}

	@Override
	public List<Catalog> listCatalogs(CatalogFilterCommand filterCommand) {
		return catalogRepository.listFiltered(filterCommand);
	}
	
	public void removeCatalog(Long catalogId){
		Catalog cat=catalogRepository.get(catalogId);
		catalogRepository.delete(cat);
	}

	@Override
	@Transactional
	public CatalogCommand getCatalogCommand(Long catalogId) {
		Catalog catalog = catalogRepository.get(catalogId);
		CatalogCommand catalogCommand = mapperFacade.getObject().map(catalog, CatalogCommand.class);
		List<Long> touristEventIds = newArrayList();
		for (TouristEvent touristEvent : catalog.getTouristEvents()) {
			touristEventIds.add(touristEvent.getId());
		}
		catalogCommand.setTouristEventIds(touristEventIds);
		return catalogCommand;
	}

	@Override
	public void updateCatalog(CatalogCommand catalogCommand) {
		Catalog catalog = mapperFacade.getObject().map(catalogCommand, Catalog.class);
		
		List<TouristEvent> touristEvents = newArrayList();
		for (Long touristEventId : catalogCommand.getTouristEventIds()) {
			touristEvents.add(touristEventRepository.get(touristEventId));
		}
		catalog.setTouristEvents(touristEvents);
		catalogRepository.update(catalog);
	}
	
}

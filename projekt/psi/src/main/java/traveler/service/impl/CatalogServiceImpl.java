package traveler.service.impl;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import traveler.controller.command.CatalogCommand;
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
		for (Long touristEventId :catalogCommand.getTouristEvents()) {
			touristEvents.add(touristEventRepository.get(touristEventId));
		}
		catalog.setTouristEvents(touristEvents);
		catalogRepository.save(catalog);
	}
	
}

package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.model.Catalog;
import traveler.repository.CatalogRepository;
import traveler.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Inject
	private CatalogRepository catalogRepository;

	@Override
	public List<Catalog> listCatalogs() {
		return catalogRepository.getAll();
	}
	
}

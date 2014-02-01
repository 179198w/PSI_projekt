package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.controller.command.TouristEventComponentCommand;
import traveler.controller.command.TouristEventComponentFilterCommand;
import traveler.model.TouristEventComponent;
import traveler.repository.TouristEventComponentRepository;
import traveler.service.TouristEventComponentService;
import traveler.utils.MapperFacadeFactoryBean;

@Service
public class TouristEventComponentServiceImpl implements TouristEventComponentService {

	@Inject
	private TouristEventComponentRepository touristEventComponentRepository;

	@Inject
	private MapperFacadeFactoryBean mapperFacade;
	
	@Override
	public List<TouristEventComponent> listTouristEventComponent() {
		return touristEventComponentRepository.getAll();
	}

	@Override
	public void addTouristEventComponent(TouristEventComponentCommand touristEventComponentCommand) {
		TouristEventComponent touristEventComponent = mapperFacade.getObject().map(touristEventComponentCommand, TouristEventComponent.class);
		touristEventComponentRepository.save(touristEventComponent);
	}

	@Override
	public List<TouristEventComponent> listTouristEventComponent(TouristEventComponentFilterCommand filterCommand) {
		return touristEventComponentRepository.getFiltered(filterCommand);
	}
	
}

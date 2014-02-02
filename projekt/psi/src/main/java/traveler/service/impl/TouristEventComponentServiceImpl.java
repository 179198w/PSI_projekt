package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.command.TouristEventComponentCommand;
import traveler.command.TouristEventComponentFilterCommand;
import traveler.model.TouristEvent;
import traveler.model.TouristEventComponent;
import traveler.repository.TouristEventComponentRepository;
import traveler.repository.TouristEventRepository;
import traveler.service.TouristEventComponentService;
import traveler.utils.MapperFacadeFactoryBean;

@Service
public class TouristEventComponentServiceImpl implements TouristEventComponentService {

	@Inject
	private TouristEventComponentRepository touristEventComponentRepository;
	
	@Inject
	private TouristEventRepository touristEventRepository;
	
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

	@Override
	public TouristEventComponentCommand getTouristEventComponentCommand(Long touristEventComponentId) {
		return mapperFacade.getObject().map(getTouristEventComponent(touristEventComponentId), TouristEventComponentCommand.class);
	}

	@Override
	public TouristEventComponent getTouristEventComponent(Long touristEventComponentId) {
		return touristEventComponentRepository.get(touristEventComponentId);
	}

	@Override
	public void updateCatalog(TouristEventComponentCommand touristEventComponentCommand) {
		TouristEventComponent touristEventComponent = mapperFacade.getObject().map(touristEventComponentCommand, TouristEventComponent.class);
		touristEventComponentRepository.update(touristEventComponent);
	}

	@Override
	public List<TouristEventComponent> listTouristEventComponent(Long touristEventId) {
		return touristEventComponentRepository.getAllByTouristEvent(touristEventId);
	}
	
	public void removeComponent(Long compId){
		TouristEventComponent comp=touristEventComponentRepository.get(compId);
		List<TouristEvent> touristEvents=touristEventRepository.getAllByQuery("select t from TouristEvent t join t.touristEventComponents comp where comp.id="+comp.getId());
		for(TouristEvent t:touristEvents){
			List<TouristEventComponent> components=touristEventComponentRepository.getAllByQuery("select t from TouristEventComponent t join t.touristEvents comp where comp.id="+t.getId());
			t.setTouristEventComponents(components);
			t.getTouristEventComponents().remove(comp);
			touristEventRepository.update(t);
		}
		comp.setTouristEvents(null);
		touristEventComponentRepository.delete(comp);
	}
	
}

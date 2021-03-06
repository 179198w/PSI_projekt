package traveler.service;

import java.util.List;

import traveler.command.TouristEventComponentCommand;
import traveler.command.TouristEventComponentFilterCommand;
import traveler.model.TouristEventComponent;

public interface TouristEventComponentService {

	List<TouristEventComponent> listTouristEventComponent();

	void addTouristEventComponent(TouristEventComponentCommand touristEventComponentCommand);

	List<TouristEventComponent> listTouristEventComponent(TouristEventComponentFilterCommand filterCommand);

	TouristEventComponentCommand getTouristEventComponentCommand(Long touristEventComponentId);

	void updateCatalog(TouristEventComponentCommand touristEventComponentCommand);

	TouristEventComponent getTouristEventComponent(Long touristEventComponentId);

	List<TouristEventComponent> listTouristEventComponent(Long touristEventId);
	
	void removeComponent(Long compId);

}

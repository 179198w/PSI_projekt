package traveler.service;

import java.util.List;

import traveler.command.TouristEventComponentCommand;
import traveler.command.TouristEventComponentFilterCommand;
import traveler.model.TouristEventComponent;

public interface TouristEventComponentService {

	List<TouristEventComponent> listTouristEventComponent();

	void addTouristEventComponent(TouristEventComponentCommand touristEventComponentCommand);

	List<TouristEventComponent> listTouristEventComponent(TouristEventComponentFilterCommand filterCommand);

}

package traveler.service;

import java.util.List;

import traveler.controller.command.TouristEventComponentCommand;
import traveler.model.TouristEventComponent;

public interface TouristEventComponentService {

	List<TouristEventComponent> listTouristEventComponent();

	void addTouristEventComponent(TouristEventComponentCommand touristEventComponentCommand);

}

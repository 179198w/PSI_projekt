package traveler.service;

import java.util.List;

import traveler.controller.command.TouristEventCommand;
import traveler.model.TouristEvent;

public interface TouristEventService {

	List<TouristEvent> listTouristEventsWithRelatedData();

	void removeTouristEvent(Long touristEventId);

	void addTouristEvent(TouristEventCommand touristEventCommand, String rootPath);

	List<TouristEvent> listTouristEvents();

}

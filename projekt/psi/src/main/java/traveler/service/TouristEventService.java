package traveler.service;

import java.util.List;

import traveler.command.TouristEventCommand;
import traveler.command.TouristEventFilterCommand;
import traveler.model.TouristEvent;

public interface TouristEventService {

	List<TouristEvent> listTouristEventsWithRelatedData(TouristEventFilterCommand touristEventFilterCommand);

	void removeTouristEvent(Long touristEventId);

	void addTouristEvent(TouristEventCommand touristEventCommand, String rootPath);

	List<TouristEvent> listTouristEvents();

	void publishOrHideTouristEvent(Long touristEventId);

	void updateTouristEventCommand(TouristEventCommand touristEventCommand, String rootPath);

	TouristEventCommand getTouristEventCommand(Long touristEventId);

}

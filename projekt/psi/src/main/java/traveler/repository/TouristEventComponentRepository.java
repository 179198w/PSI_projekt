package traveler.repository;

import java.util.List;

import traveler.command.TouristEventComponentFilterCommand;
import traveler.model.TouristEventComponent;

public interface TouristEventComponentRepository extends GenericRepository<TouristEventComponent, Long> {

	List<TouristEventComponent> getFiltered(TouristEventComponentFilterCommand filterCommand);

	List<TouristEventComponent> getAllByTouristEvent(Long touristEventId);
	

}

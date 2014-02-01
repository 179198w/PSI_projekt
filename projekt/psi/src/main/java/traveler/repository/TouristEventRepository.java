package traveler.repository;

import java.util.List;

import traveler.command.TouristEventFilterCommand;
import traveler.model.TouristEvent;

public interface TouristEventRepository extends GenericRepository<TouristEvent, Long> {

	List<TouristEvent> getAllWithRelatedData(TouristEventFilterCommand touristEventFilterCommand);

}

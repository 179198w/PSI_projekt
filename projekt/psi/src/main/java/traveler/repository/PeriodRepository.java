package traveler.repository;

import java.util.List;

import traveler.controller.command.PeriodFilterCommand;
import traveler.model.Period;

public interface PeriodRepository extends GenericRepository<Period, Long> {

	List<Period> getFiltered(PeriodFilterCommand filterCommand);

}

package traveler.service;

import java.util.List;

import traveler.command.PeriodCommand;
import traveler.command.PeriodFilterCommand;
import traveler.model.Period;

public interface PeriodService {

	List<Period> listPeriods();

	void addPeriod(PeriodCommand periodCommand);

	List<Period> listPeriods(PeriodFilterCommand filterCommand);

}

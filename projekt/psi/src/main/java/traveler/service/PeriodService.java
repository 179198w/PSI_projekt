package traveler.service;

import java.util.List;

import traveler.controller.command.PeriodCommand;
import traveler.model.Period;

public interface PeriodService {

	List<Period> listPeriods();

	void addPeriod(PeriodCommand periodCommand);

}

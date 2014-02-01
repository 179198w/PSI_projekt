package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import traveler.command.PeriodCommand;
import traveler.command.PeriodFilterCommand;
import traveler.model.Period;
import traveler.model.TouristEvent;
import traveler.repository.PeriodRepository;
import traveler.repository.TouristEventRepository;
import traveler.service.PeriodService;

@Service
public class PeriodServiceImpl implements PeriodService {

	@Inject
	private PeriodRepository periodRepository;

	@Inject
	private TouristEventRepository touristEventRepository;
	
	@Override
	public List<Period> listPeriods() {
		return periodRepository.getAll();
	}

	@Override
	public void addPeriod(PeriodCommand periodCommand) {
		int repeatCount = periodCommand.getRepeatPeriod() ? periodCommand.getRepeatCount() : 1; 
		
		for (Long touristEventId : periodCommand.getTouristEventIds()) {
			TouristEvent touristEvent = touristEventRepository.get(touristEventId);
			
			for (int repetition = 0; repetition < repeatCount; repetition++) {
				Period period = new Period();
				
				LocalDate from = periodCommand.getRepeatPeriod() ? calculateDate(periodCommand.getFrom(), periodCommand.getPeriodSpaceType(), periodCommand.getPeriodSpace(), repetition) : periodCommand.getFrom();
				LocalDate to = periodCommand.getRepeatPeriod() ? calculateDate(periodCommand.getTo(), periodCommand.getPeriodSpaceType(), periodCommand.getPeriodSpace(), repetition) : periodCommand.getTo();

				period.setFrom(from);
				period.setTo(to);
				period.setTouristEvent(touristEvent);
				periodRepository.save(period);
			}
		}
	}
	
	private LocalDate calculateDate(LocalDate date, String type, Integer space, Integer times) {
		if ("days".equals(type)) {
			return date.plusDays(space * times);
		} else if ("weeks".equals(type)) {
			return date.plusWeeks(space * times);
		} else if ("months".equals(type)) {
			return date.plusMonths(space * times);
		}
		return date;
	}

	@Override
	public List<Period> listPeriods(PeriodFilterCommand filterCommand) {
		return periodRepository.getFiltered(filterCommand);
	}
	
}

package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import traveler.controller.command.PeriodCommand;
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
		
		for (Long touristEventId : periodCommand.getTouristEvents()) {
			TouristEvent touristEvent = touristEventRepository.get(touristEventId);
			
			for (int repetition = 0; repetition < repeatCount; repetition++) {
				Period period = new Period();
				period.setFrom(calculateDate(periodCommand.getFrom(), periodCommand.getPeriodSpaceType(), periodCommand.getPeriodSpace(), repetition));
				period.setTo(calculateDate(periodCommand.getTo(), periodCommand.getPeriodSpaceType(), periodCommand.getPeriodSpace(), repetition));
				period.setTouristEvent(touristEvent);
				periodRepository.save(period);
			}
		}
	}
	
	private LocalDate calculateDate(LocalDate date, String type, int space, int times) {
		if ("days".equals(type)) {
			return date.plusDays(space * times);
		} else if ("weeks".equals(type)) {
			return date.plusWeeks(space * times);
		} else if ("months".equals(type)) {
			return date.plusMonths(space * times);
		}
		return date;
	}
	
}

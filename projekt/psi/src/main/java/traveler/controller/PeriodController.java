package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.PeriodCommand;
import traveler.service.PeriodService;
import traveler.service.TouristEventService;

@Controller
public class PeriodController {

	@Inject
	private PeriodService periodService;
	
	@Inject
	private TouristEventService touristEventService;
	
	@RequestMapping("/lista-terminow")
	public String listPeriods(Model model) {
		model.addAttribute("periods", periodService.listPeriods());
		return "listPeriods";
	}
	
	@RequestMapping(value = "/dodaj-termin", method = RequestMethod.GET)
	public String addPeriodForm(Model model) {
		model.addAttribute("command", new PeriodCommand());
		model.addAttribute("touristEvents", touristEventService.listTouristEvents());
		return "addPeriod";
	}
	
	@RequestMapping(value = "/dodaj-termin", method = RequestMethod.POST)
	public String addPeriod(Model model, PeriodCommand periodCommand) {
		periodService.addPeriod(periodCommand);
		return listPeriods(model);
	}
	
}

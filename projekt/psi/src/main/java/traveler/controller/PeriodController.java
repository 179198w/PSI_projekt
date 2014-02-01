package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.CityCommand;
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
		model.addAttribute("periodCommand", new PeriodCommand());
		model.addAttribute("touristEvents", touristEventService.listTouristEvents());
		return "addPeriod";
	}
	
	@RequestMapping(value = "/dodaj-termin", method = RequestMethod.POST)
	public String addPeriod(Model model,@Valid PeriodCommand periodCommand, BindingResult result) {
		if(result.hasErrors()){
			return "addPeriod";
		}
		periodService.addPeriod(periodCommand);
		return listPeriods(model);
	}
	
}

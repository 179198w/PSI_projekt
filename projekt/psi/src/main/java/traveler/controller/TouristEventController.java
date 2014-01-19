package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.service.TouristEventService;

@Controller
public class TouristEventController {
	
	@Inject
	private TouristEventService touristEventService;
	
	@RequestMapping("/lista-imprez-turystycznych")
	public String listTouristEvents(Model model) {
		model.addAttribute("touristEvents", touristEventService.listTouristEvents());
		return "listTouristEvents";
	}
	
	@RequestMapping(value = "/dodaj-impreze-turystyczna", method = RequestMethod.GET)
	public String addTouristEventForm(Model model) {
		model.addAttribute("command", new TouristEventCommand());
		return "addTouristEvent";
	}
	
	@RequestMapping(value = "/dodaj-impreze-turystyczna", method = RequestMethod.POST)
	public String addTouristEvent(Model model, TouristEventCommand touristEventCommand) throws Exception {
		touristEventService.addTouristEvent(touristEventCommand);
		return listTouristEvents(model);
	}
	
	@RequestMapping(value = "/usun-impreze-turystyczna", method = RequestMethod.POST)
	public String removeTouristEvent(Model model, Long touristEventId) throws Exception {
		touristEventService.removeTouristEvent(touristEventId);
		return listTouristEvents(model);
	}
	
}

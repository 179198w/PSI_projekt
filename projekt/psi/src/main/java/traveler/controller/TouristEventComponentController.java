package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.TouristEventComponentCommand;
import traveler.model.TouristEventComponentType;
import traveler.service.TouristEventComponentService;

@Controller
public class TouristEventComponentController {

	@Inject
	private TouristEventComponentService touristEventComponentService;
	
	@RequestMapping("/lista-skladnikow")
	public String listTouristEventComponents(Model model) {
		model.addAttribute("touristEventComponents", touristEventComponentService.listTouristEventComponent());
		return "listTouristEventComponents";
	}
	
	@RequestMapping(value = "/dodaj-skladnik", method = RequestMethod.GET)
	public String addTouristEventComponentForm(Model model) {
		model.addAttribute("command", new TouristEventComponentCommand());
		model.addAttribute("types", TouristEventComponentType.values());
		return "addTouristEventComponent";
	}
	
	@RequestMapping(value = "/dodaj-skladnik", method = RequestMethod.POST)
	public String addTouristEventComponent(Model model, TouristEventComponentCommand touristEventComponentCommand) {
		touristEventComponentService.addTouristEventComponent(touristEventComponentCommand);
		return listTouristEventComponents(model);
	}
	
}
